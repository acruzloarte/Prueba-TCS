package com.example.pruebattc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
@EnableFeignClients
public class PruebaTtcApplication {
    public static void main(String[] args) {
        SpringApplication.run(PruebaTtcApplication.class, args);
    }
}
