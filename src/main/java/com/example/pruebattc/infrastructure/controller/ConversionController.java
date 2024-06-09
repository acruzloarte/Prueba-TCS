package com.example.pruebattc.infrastructure.controller;

import com.example.pruebattc.application.service.ConversionService;
import com.example.pruebattc.domain.model.CurrencyConversion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController("/api/v1")
public class ConversionController {


    private final ConversionService conversionService;

    public ConversionController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @PostMapping("/conversion")
    public Mono<Double> convertCurrency(
            @RequestParam Double amount,
            @RequestParam String fromCurrency,
            @RequestParam String toCurrency
    ) {

        return conversionService.convertCurrency(amount, fromCurrency, toCurrency);
    }

    @GetMapping("/conversion")
    public Flux<CurrencyConversion> getAllCurrencyConversions() {
        return conversionService.getAllCurrenciesConversion();
    }

    @GetMapping("/conversion/{id}")
    public Mono<ResponseEntity<CurrencyConversion>> getCurrencyConversionById(@PathVariable Long id) {
        return conversionService.getCurrencyConversionById(id);
    }

}

