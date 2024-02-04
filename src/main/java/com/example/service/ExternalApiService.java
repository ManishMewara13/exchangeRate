package com.example.service;

import com.example.model.ExchangeRate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ExternalApiService {

    private final String EXTERNAL_API_URL = "external_api_url";

    private final RestTemplate restTemplate;

    public ExternalApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String fetchExchangeRateData() {
        // Make HTTP request to the external API
        return restTemplate.getForObject(EXTERNAL_API_URL + "/exchange-rates", String.class);
    }

    public List<ExchangeRate> fetchExchangeRates() {
        // Make HTTP GET request to the external API
        ExchangeRate[] exchangeRateArray = restTemplate.getForObject(EXTERNAL_API_URL, ExchangeRate[].class);

        // Convert the array to a list
        assert exchangeRateArray != null;
        return Arrays.asList(exchangeRateArray);
    }

    public List<ExchangeRate> fetchExchangeRatesForUSD(String lastDate, String todayDate) {
        // Append the date parameter to the API URL if required by the API
        String apiUrl = EXTERNAL_API_URL + "?lastDate=" + lastDate + "?todayDate=" + todayDate;

        // Make an HTTP GET request to the external API
        ExchangeRate[] exchangeRateArray = restTemplate.getForObject(apiUrl, ExchangeRate[].class);

        // Convert the array to a list
        assert exchangeRateArray != null;
        return Arrays.asList(exchangeRateArray);
    }


}