package com.example.service;

import com.example.model.ExchangeRate;
import com.example.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the ExchangeRateService interface.
 */
@Service
public class ExchangeRateServiceImpl extends ExchangeRateService {
    private static final Logger log = LoggerFactory.getLogger(ExchangeRateServiceImpl.class);

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    /**
     * Retrieves exchange rates by currency code.
     *
     * @param currencyCode The currency code to search for.
     * @return List of exchange rates with the specified currency code.
     */
    @Override
    public List<ExchangeRate> getExchangeRatesByCurrencyCode(String currencyCode) {
        log.info("Retrieving exchange rates by currency code: {}", currencyCode);
        return exchangeRateRepository.findByCurrencyCode(currencyCode);
    }
}