package com.example.demo;

import org.springframework.boot.SpringApplication;
import java.util.logging.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExchangeRateApplication {

    private static Logger log = Logger.getLogger(ExchangeRateApplication.class.getName());

    public static void main(String[] args) {
        log.info("Inside ExchangeRateApplication - main()");
        SpringApplication.run(ExchangeRateApplication.class, args);
    }
}
