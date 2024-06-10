package com.example.pruebattc;

import com.example.pruebattc.application.service.ConversionService;
import com.example.pruebattc.domain.repository.ConversionRepository;
import com.example.pruebattc.infrastructure.client.ExchangeRateApiClient;
import com.example.pruebattc.infrastructure.dto.ExchangeRatesResponse;
import com.example.pruebattc.domain.model.CurrencyConversion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ConversionServiceTest {

    @Mock
    private ExchangeRateApiClient exchangeRateApiClient;

    @Mock
    private ConversionRepository conversionRepository;

    @InjectMocks
    private ConversionService conversionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConvertCurrency() {
        ExchangeRatesResponse response = new ExchangeRatesResponse();
        Map<String, Double> rates = new HashMap<>();
        rates.put("USD", 1.0);
        rates.put("EUR", 0.85);
        response.setRates(rates);

        when(exchangeRateApiClient.getLatestExchangeRates()).thenReturn(response);

        Double amount = 100.0;
        String fromCurrency = "USD";
        String toCurrency = "EUR";

        Mono<Double> result = conversionService.convertCurrency(amount, fromCurrency, toCurrency);

        result.subscribe(convertedAmount -> assertEquals(85.0, convertedAmount));
    }

    @Test
    void testGetAllCurrencyConversions() {
        when(conversionRepository.findAll()).thenReturn(Flux.empty());

        // Ejecutar el método bajo prueba
        Flux<CurrencyConversion> result = conversionService.getAllCurrenciesConversion();

        // Verificar el resultado esperado (lista vacía)
        assertEquals(0, result.collectList().block().size());
    }

    @Test
    void testGetCurrencyConversionById() {
        Long id = 1L;
        CurrencyConversion conversion = new CurrencyConversion();
        conversion.setId(id);
        when(conversionRepository.findById(id)).thenReturn(Mono.just(conversion));
        Mono<ResponseEntity<CurrencyConversion>> result = conversionService.getCurrencyConversionById(id);
        assertEquals(200, result.block().getStatusCodeValue());
        assertEquals(id, result.block().getBody().getId());
    }


}