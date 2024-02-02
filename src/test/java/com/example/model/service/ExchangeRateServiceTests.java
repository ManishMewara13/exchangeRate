package com.example.model.service;

import com.example.model.ExchangeRate;
import com.example.repository.ExchangeRateRepository;
import com.example.service.ExchangeRateServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExchangeRateServiceTests {

    @Mock
    private ExchangeRateRepository exchangeRateRepository;

    @InjectMocks
    private ExchangeRateServiceImpl exchangeRateService;

    @Test
    public void testGetExchangeRatesByCurrencyCode() {
        // Mock data
        ExchangeRate rate1 = new ExchangeRate("USD", 1.0);
        ExchangeRate rate2 = new ExchangeRate("USD", 1.2);
        List<ExchangeRate> expectedRates = Arrays.asList(rate1, rate2);

        // Mock repository behavior
        when(exchangeRateRepository.findByCurrencyCode(anyString())).thenReturn(expectedRates);

        // Test method
        List<ExchangeRate> actualRates = exchangeRateService.getExchangeRatesByCurrencyCode("USD");

        // Assertions
        assertEquals(expectedRates.size(), actualRates.size());
        assertEquals(expectedRates.get(0).getRate(), actualRates.get(0).getRate());
        assertEquals(expectedRates.get(1).getRate(), actualRates.get(1).getRate());
    }
}
