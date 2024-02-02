package com.example.service;

import com.example.model.ExchangeRate;
import com.example.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class ExchangeRateService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private ExternalApiService externalApiService;

    public List<ExchangeRate> getAllExchangeRates() {
        return exchangeRateRepository.findAll();
    }

    public ExchangeRate getExchangeRateById(String id) {
        return exchangeRateRepository.findById(id).orElse(null);
    }

    public ExchangeRate createExchangeRate(ExchangeRate exchangeRate) {
        return exchangeRateRepository.save(exchangeRate);
    }

    public ExchangeRate updateExchangeRate(String id, ExchangeRate newExchangeRate) {
        ExchangeRate existingExchangeRate = exchangeRateRepository.findById(id).orElse(null);
        if (existingExchangeRate != null) {
            existingExchangeRate.setCurrencyCode(newExchangeRate.getCurrencyCode());
            existingExchangeRate.setRate(newExchangeRate.getRate());
            existingExchangeRate.setDate(newExchangeRate.getDate());
            return exchangeRateRepository.save(existingExchangeRate);
        }
        return null;
    }

    public void deleteExchangeRate(String id) {
        exchangeRateRepository.deleteById(id);
    }

    public void fetchAndStoreExchangeRates() {
        // Logic to fetch exchange rates from an external API
        List<ExchangeRate> exchangeRates = externalApiService.fetchExchangeRates();

        // Save the fetched exchange rates to the database
        exchangeRateRepository.saveAll(exchangeRates);
    }

    public abstract List<ExchangeRate> getExchangeRatesByCurrencyCode(String currencyCode);
}
