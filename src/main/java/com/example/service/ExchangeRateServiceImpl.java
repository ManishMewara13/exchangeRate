package com.example.service;

import com.example.model.ExchangeRate;
import com.example.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeRateServiceImpl extends ExchangeRateService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Override
    public List<ExchangeRate> getExchangeRatesByCurrencyCode(String currencyCode) {
        // Implement the logic to retrieve exchange rates by currency code
        return exchangeRateRepository.findByCurrencyCode(currencyCode);
    }
}
