package com.example.pruebattc.application.service;

import com.example.pruebattc.application.exception.CurrencyConversionNotFoundException;
import com.example.pruebattc.application.exception.ExternalApiException;
import com.example.pruebattc.application.exception.InvalidCurrencyCodeException;
import com.example.pruebattc.domain.model.CurrencyConversion;
import com.example.pruebattc.domain.repository.ConversionRepository;
import com.example.pruebattc.infrastructure.client.ExchangeRateApiClient;
import com.example.pruebattc.infrastructure.dto.ExchangeRatesResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ConversionService {

    private final ExchangeRateApiClient exchangeRateApiClient;
    private final ConversionRepository conversionRepository;

    public ConversionService(ExchangeRateApiClient exchangeRateApiClient, ConversionRepository conversionRepository) {
        this.exchangeRateApiClient = exchangeRateApiClient;
        this.conversionRepository = conversionRepository;
    }

    public Mono<Double> convertCurrency(Double amount, String fromCurrency, String toCurrency) {
        return Mono.defer(() -> {
            try {
                ExchangeRatesResponse exchangeRates = exchangeRateApiClient.getLatestExchangeRates();
                Double rateFrom = exchangeRates.getRates().get(fromCurrency);
                Double rateTo = exchangeRates.getRates().get(toCurrency);

                if (rateFrom == null || rateTo == null) {
                    throw new InvalidCurrencyCodeException("Código de moneda no válido: " + fromCurrency + " o " + toCurrency);
                }

                Double convertedAmount = amount * (rateTo / rateFrom);

                return conversionRepository.save(CurrencyConversion.builder()
                                .fromCurrency(fromCurrency)
                                .toCurrency(toCurrency)
                                .rate(rateTo)
                                .amount(amount)
                                .convertedAmount(convertedAmount)
                                .build())
                        .flatMap(savedConversion -> Mono.just(convertedAmount));
            } catch (Exception e) {
                return Mono.error(new ExternalApiException("Error al obtener las tasas de cambio: " + e.getMessage()));
            }
        });
    }

    public Flux<CurrencyConversion> getAllCurrenciesConversion() {
        return conversionRepository.findAll()
                .onErrorResume(e -> Flux.error(new RuntimeException("Error al obtener las conversiones de moneda: " + e.getMessage())));
    }

    public Mono<ResponseEntity<CurrencyConversion>> getCurrencyConversionById(Long id) {
        return conversionRepository.findById(id)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.error(new CurrencyConversionNotFoundException("Conversión de moneda no encontrada para el ID: " + id)));
    }
}
