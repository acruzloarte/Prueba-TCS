package com.example.pruebattc.infrastructure.client;

import com.example.pruebattc.infrastructure.config.FeignConfig;
import com.example.pruebattc.infrastructure.dto.ExchangeRatesResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "exchangeRateClient", url = "${exchange.rate.api.url}", configuration = FeignConfig.class)
public interface ExchangeRateApiClient {

    @GetMapping("/latest/USD")
    ExchangeRatesResponse getLatestExchangeRates();
}

