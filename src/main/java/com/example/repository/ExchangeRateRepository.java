package com.example.repository;

import com.example.model.ExchangeRate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRateRepository extends MongoRepository<ExchangeRate, String> {
    // Define custom methods if needed
    List<ExchangeRate> findByCurrencyCode(String currencyCode);
}
