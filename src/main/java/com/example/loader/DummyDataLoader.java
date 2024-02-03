package com.example.loader;

import com.example.model.ExchangeRate;
import com.example.repository.ExchangeRateRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DummyDataLoader implements CommandLineRunner {

    private final ExchangeRateRepository exchangeRateRepository;

    public DummyDataLoader(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        ExchangeRate dummyRate = new ExchangeRate();
        dummyRate.setCurrencyCode("USD");
        dummyRate.setRate(1.0);

        exchangeRateRepository.save(dummyRate);
    }
}
