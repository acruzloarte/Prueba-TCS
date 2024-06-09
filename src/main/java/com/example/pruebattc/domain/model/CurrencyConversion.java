package com.example.pruebattc.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("currency_conversion")
public class CurrencyConversion {
    @Id
    private Long id;
    private String fromCurrency;
    private String toCurrency;
    private Double rate;
    private Double amount;
    private Double convertedAmount;
}
