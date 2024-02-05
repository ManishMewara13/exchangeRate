package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
public class ExchangeRateApplication {
    private static final Logger log = LoggerFactory.getLogger(ExchangeRateApplication.class);

    public static void main(String[] args) {
        log.info("Starting ExchangeRateApplication...");
        SpringApplication.run(ExchangeRateApplication.class, args);
        log.info("ExchangeRateApplication started successfully.");
    }
}
