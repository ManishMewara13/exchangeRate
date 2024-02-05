package com.example.controller;

import com.example.model.ExchangeRate;
import com.example.service.ExchangeRateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/exchangeRates")
public class ExchangeRateController {
    private static final Logger log = LoggerFactory.getLogger(ExchangeRateController.class);

    @Autowired
    private ExchangeRateService exchangeRateService;

    // Endpoint to get all exchange rates
    @GetMapping("/getAll")
    public List<ExchangeRate> getAllExchangeRates() {
        log.info("Retrieving all exchange rates.");
        return exchangeRateService.getAllExchangeRates();
    }

    // Endpoint to get exchange rate by ID
    @GetMapping("/{id}")
    public ExchangeRate getExchangeRateById(@PathVariable String id) {
        log.info("Retrieving exchange rate by ID: {}", id);
        return exchangeRateService.getExchangeRateById(id);
    }

    // Endpoint to create a new exchange rate
    @PostMapping
    public ExchangeRate createExchangeRate(@RequestBody ExchangeRate exchangeRate) {
        log.info("Creating new exchange rate.");
        return exchangeRateService.createExchangeRate(exchangeRate);
    }

    // Endpoint to update an existing exchange rate
    @PutMapping("/{id}")
    public ExchangeRate updateExchangeRate(@PathVariable String id, @RequestBody ExchangeRate exchangeRate) {
        log.info("Updating exchange rate with ID: {}", id);
        return exchangeRateService.updateExchangeRate(id, exchangeRate);
    }

    // Endpoint to delete an exchange rate
    @DeleteMapping("/{id}")
    public void deleteExchangeRate(@PathVariable String id) {
        log.info("Deleting exchange rate with ID: {}", id);
        exchangeRateService.deleteExchangeRate(id);
    }

    // Endpoint to calculate exchange rate difference over different time intervals
    @GetMapping("/diff")
    public String calculateExchangeRateDifference(@RequestParam("code") String currencyCode) {
        log.info("Calculating exchange rate difference for currency code: {}", currencyCode);

        // Retrieve exchange rate data for the last year
        List<ExchangeRate> exchangeRates = exchangeRateService.getExchangeRatesByCurrencyCode(currencyCode);

        // Calculate percentage increase/decrease for different time intervals
        double lastOneDayDiff = calculatePercentageDifference(exchangeRates.get(0).getRate(), exchangeRates.get(1).getRate());
        double lastOneWeekDiff = calculatePercentageDifference(exchangeRates.get(0).getRate(), exchangeRates.get(7).getRate());
        double lastOneMonthDiff = calculatePercentageDifference(exchangeRates.get(0).getRate(), exchangeRates.get(30).getRate());
        double lastThreeMonthDiff = calculatePercentageDifference(exchangeRates.get(0).getRate(), exchangeRates.get(90).getRate());
        double lastSixMonthDiff = calculatePercentageDifference(exchangeRates.get(0).getRate(), exchangeRates.get(180).getRate());
        double lastNineMonthDiff = calculatePercentageDifference(exchangeRates.get(0).getRate(), exchangeRates.get(270).getRate());
        double lastTwelveMonthDiff = calculatePercentageDifference(exchangeRates.get(0).getRate(), exchangeRates.get(364).getRate());

        // Prepare response
        return "{\n" +
                "\"Last one day\": " + lastOneDayDiff + "%,\n" +
                "\"Last one week\": " + lastOneWeekDiff + "%,\n" +
                "\"Last one month\": " + lastOneMonthDiff + "%,\n" +
                "\"Last 3 months\": " + lastThreeMonthDiff + "%,\n" +
                "\"Last 6 months\": " + lastSixMonthDiff + "%,\n" +
                "\"Last 9 months\": " + lastNineMonthDiff + "%,\n" +
                "\"Last 12 months\": " + lastTwelveMonthDiff + "%\n" +
                "}";
    }

    // Helper method to calculate percentage difference
    private double calculatePercentageDifference(double newValue, double oldValue) {
        return ((newValue - oldValue) / oldValue) * 100.0;
    }
}