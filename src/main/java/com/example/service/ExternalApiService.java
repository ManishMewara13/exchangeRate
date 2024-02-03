package com.example.service;

import com.example.model.ExchangeRate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ExternalApiService {

    private final String EXTERNAL_API_URL = "http://127.0.0.1:8080/";

    private final RestTemplate restTemplate;

    public ExternalApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String fetchExchangeRateData() {
        // Make HTTP request to the external API
        String response = restTemplate.getForObject(EXTERNAL_API_URL + "/exchange-rates", String.class);
        return response;
    }

    public List<ExchangeRate> fetchExchangeRates() {
        // Make HTTP GET request to the external API
        ExchangeRate[] exchangeRateArray = restTemplate.getForObject(EXTERNAL_API_URL, ExchangeRate[].class);

        // Convert the array to a list
        List<ExchangeRate> exchangeRates = Arrays.asList(exchangeRateArray);

        return exchangeRates;
    }

}