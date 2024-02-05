package com.example.service;

import com.example.model.ExchangeRate;
import com.example.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service class to manage exchange rates.
 */
@Service
public abstract class ExchangeRateService {
    private static final Logger log = LoggerFactory.getLogger(ExchangeRateService.class);

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private ExternalApiService externalApiService;

    /**
     * Retrieve all exchange rates.
     * @return List of all exchange rates.
     */
    public List<ExchangeRate> getAllExchangeRates() {
        log.info("Retrieving all exchange rates.");
        return exchangeRateRepository.findAll();
    }

    /**
     * Retrieve an exchange rate by its ID.
     * @param id The ID of the exchange rate to retrieve.
     * @return The exchange rate if found, null otherwise.
     */
    public ExchangeRate getExchangeRateById(String id) {
        log.info("Retrieving exchange rate by ID: {}", id);
        return exchangeRateRepository.findById(id).orElse(null);
    }

    /**
     * Create a new exchange rate.
     * @param exchangeRate The exchange rate to create.
     * @return The created exchange rate.
     */
    public ExchangeRate createExchangeRate(ExchangeRate exchangeRate) {
        log.info("Creating new exchange rate.");
        return exchangeRateRepository.save(exchangeRate);
    }

    /**
     * Update an existing exchange rate.
     * @param id The ID of the exchange rate to update.
     * @param newExchangeRate The updated exchange rate object.
     * @return The updated exchange rate if found, null otherwise.
     */
    public ExchangeRate updateExchangeRate(String id, ExchangeRate newExchangeRate) {
        log.info("Updating exchange rate with ID: {}", id);
        ExchangeRate existingExchangeRate = exchangeRateRepository.findById(id).orElse(null);
        if (existingExchangeRate != null) {
            existingExchangeRate.setCurrencyCode(newExchangeRate.getCurrencyCode());
            existingExchangeRate.setRate(newExchangeRate.getRate());
            existingExchangeRate.setDate(newExchangeRate.getDate());
            return exchangeRateRepository.save(existingExchangeRate);
        }
        return null;
    }

    /**
     * Delete an exchange rate by its ID.
     * @param id The ID of the exchange rate to delete.
     */
    public void deleteExchangeRate(String id) {
        log.info("Deleting exchange rate with ID: {}", id);
        exchangeRateRepository.deleteById(id);
    }

    /**
     * Abstract method to retrieve exchange rates by currency code.
     * @param currencyCode The currency code to search for.
     * @return List of exchange rates with the specified currency code.
     */
    public abstract List<ExchangeRate> getExchangeRatesByCurrencyCode(String currencyCode);
}