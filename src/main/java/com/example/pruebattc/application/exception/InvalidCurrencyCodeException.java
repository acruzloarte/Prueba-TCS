package com.example.pruebattc.application.exception;

public class InvalidCurrencyCodeException extends RuntimeException {
    public InvalidCurrencyCodeException(String message) {
        super(message);
    }
}
