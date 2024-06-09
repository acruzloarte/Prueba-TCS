CREATE TABLE currency_conversion (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     from_currency VARCHAR(255) NOT NULL,
                                     to_currency VARCHAR(255) NOT NULL,
                                     rate DOUBLE NOT NULL,
                                     amount DOUBLE NOT NULL,
                                     converted_amount DOUBLE
);