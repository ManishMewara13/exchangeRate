package com.example.schedular;

import com.example.model.ExchangeRate;
import com.example.repository.ExchangeRateRepository;
import com.example.service.ExternalApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Scheduler class to fetch and store exchange rates periodically.
 */
@Component
public class ExchangeRateScheduler {
    private static final Logger log = LoggerFactory.getLogger(ExchangeRateScheduler.class);

    @Autowired
    private ExternalApiService externalApiService;

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    /**
     * Scheduled method to fetch and store exchange rates from external API.
     * Runs everyday at midnight.
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void fetchAndStoreExchangeRates() {
        log.info("Inside ExchangeRateScheduler - fetchAndStoreExchangeRates");

        // Fetch exchange rates from external API
        List<ExchangeRate> exchangeRates = externalApiService.fetchExchangeRatesForUSD();

        // Print the size of the fetched exchange rates (for debugging purposes)
        log.info("Fetched exchange rates size: {}", exchangeRates.size());

        // Save fetched exchange rates to the database
        exchangeRateRepository.saveAll(exchangeRates);
    }

    /**
     * Event listener to trigger fetching and storing exchange rates when the application is ready.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReadyEvent() {
        fetchAndStoreExchangeRates();
    }
}