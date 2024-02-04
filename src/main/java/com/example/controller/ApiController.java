package com.example.controller;

import com.example.service.ExternalApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Autowired
    private ExternalApiService externalApiService;

    @GetMapping("/api/exchangeRates")
    public String getExchangeRateData() {
        return externalApiService.fetchExchangeRateData();
    }
}
