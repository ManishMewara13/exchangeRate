package com.example.schedular;

import com.example.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRateScheduler {

    @Autowired
    private ExchangeRateService exchangeRateService;

    // Scheduled method to fetch and store exchange rate data every day at midnight
    @Scheduled(cron = "0 0 0 * * ?") // Run everyday at midnight
    public void fetchAndStoreExchangeRates() {
        // Logic to fetch and store exchange rates
        exchangeRateService.fetchAndStoreExchangeRates();
    }
}
