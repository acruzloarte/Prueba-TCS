package com.example.pruebattc.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.Map;

public class ExchangeRatesResponse {

    private String baseCode;
    private Map<String, Double> rates;

    @JsonAlias("base_code")
    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }
}