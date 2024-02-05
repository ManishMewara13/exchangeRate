package com.example.repository;

import com.example.model.ExchangeRate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for interacting with ExchangeRate entities in the MongoDB database.
 */
@Repository
public interface ExchangeRateRepository extends MongoRepository<ExchangeRate, String> {

    /**
     * Retrieves a list of ExchangeRate entities by their currency code.
     * @param currencyCode The currency code to search for.
     * @return A list of ExchangeRate entities with the specified currency code.
     */
    List<ExchangeRate> findByCurrencyCode(String currencyCode);
}
