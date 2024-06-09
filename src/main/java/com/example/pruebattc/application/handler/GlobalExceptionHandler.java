package com.example.pruebattc.application.handler;

import com.example.pruebattc.application.exception.CurrencyConversionNotFoundException;
import com.example.pruebattc.application.exception.ExternalApiException;
import com.example.pruebattc.application.exception.InvalidCurrencyCodeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCurrencyCodeException.class)
    public ResponseEntity<String> handleInvalidCurrencyCodeException(InvalidCurrencyCodeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExternalApiException.class)
    public ResponseEntity<String> handleExternalApiException(ExternalApiException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CurrencyConversionNotFoundException.class)
    public ResponseEntity<String> handleCurrencyConversionNotFoundException(CurrencyConversionNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
