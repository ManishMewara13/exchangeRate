package com.example.schedular;

import com.example.ExchangeRateApplication;
import com.example.model.ExchangeRate;
import com.example.repository.ExchangeRateRepository;
import com.example.service.ExternalApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Component
public class ExchangeRateScheduler {
    private static Logger log = Logger.getLogger(ExchangeRateScheduler.class.getName());
    @Autowired
    private ExternalApiService externalApiService;
    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    // Define a scheduled task to fetch and store exchange rate data
    @Scheduled(cron = "0 0 0 * * ?") // Run everyday at midnight
    public void fetchAndStoreExchangeRates() {
        log.info("Inside ExchangeRateScheduler - fetchAndStoreExchangeRates");
        // Get date
        Calendar calendar = Calendar.getInstance();
        Date dateToday = calendar.getTime();
        calendar.add(Calendar.YEAR, -1);
        Date oneYearAgo = calendar.getTime();

        // Format the date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String lastDate = dateFormat.format(oneYearAgo);
        String toadayDate = dateFormat.format(dateToday);
        List<ExchangeRate> exchangeRates = externalApiService.fetchExchangeRatesForUSD(lastDate, toadayDate);

        // Save the fetched exchange rates to the MongoDB collection
        exchangeRateRepository.saveAll(exchangeRates);
    }
}

