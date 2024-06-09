package com.example.pruebattc.domain.repository;

import com.example.pruebattc.domain.model.CurrencyConversion;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversionRepository extends ReactiveCrudRepository<CurrencyConversion, Long> {
}
