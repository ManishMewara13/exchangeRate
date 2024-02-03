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

    @GetMapping("/getAll")
    public List<ExchangeRate> getAllExchangeRates() {
        log.info("inside getAll");
        return exchangeRateService.getAllExchangeRates();
    }

    @GetMapping("/{id}")
    public ExchangeRate getExchangeRateById(@PathVariable String id) {
        return exchangeRateService.getExchangeRateById(id);
    }

    @PostMapping
    public ExchangeRate createExchangeRate(@RequestBody ExchangeRate exchangeRate) {
        return exchangeRateService.createExchangeRate(exchangeRate);
    }

    @PutMapping("/{id}")
    public ExchangeRate updateExchangeRate(@PathVariable String id, @RequestBody ExchangeRate exchangeRate) {
        return exchangeRateService.updateExchangeRate(id, exchangeRate);
    }

    @DeleteMapping("/{id}")
    public void deleteExchangeRate(@PathVariable String id) {
        exchangeRateService.deleteExchangeRate(id);
    }

    @GetMapping("/api/exchangeRate/diff")
    public String calculateExchangeRateDifference(@RequestParam("code") String currencyCode) {
        // Retrieve exchange rate data from the service layer
        List<ExchangeRate> exchangeRates = exchangeRateService.getExchangeRatesByCurrencyCode(currencyCode);

        // Calculate percentage increase/decrease
        double lastOneDayDiff = calculatePercentageDifference(exchangeRates.get(0).getRate(), exchangeRates.get(1).getRate());
        double lastOneWeekDiff = calculatePercentageDifference(exchangeRates.get(0).getRate(), exchangeRates.get(7).getRate());
        // ... Similar calculations for other time intervals

        // Prepare response
        StringBuilder response = new StringBuilder();
        response.append("{");
        response.append("\"Last one day\": ").append(lastOneDayDiff).append("%,");
        response.append("\"Last one week\": ").append(lastOneWeekDiff).append("%,");
        // ... Append other time intervals
        response.append("}");

        return response.toString();
    }

    private double calculatePercentageDifference(double newValue, double oldValue) {
        return ((newValue - oldValue) / oldValue) * 100.0;
    }
}
