package com.example.service;

import com.example.model.ExchangeRate;
import com.example.repository.ExchangeRateRepository;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ExternalApiService {
    private static final Logger log = LoggerFactory.getLogger(ExternalApiService.class);

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    /**
     * Fetch exchange rates for a given year from an external API.
     *
     * @param year The year for which exchange rates are to be fetched.
     * @return A map of exchange rates for each date in the specified year.
     */
    public Map<String, Double> getExchangeRatesForYear(int year) {
        log.info("Fetching exchange rates for year: {}", year);
        Map<String, Double> exchangeRates = new HashMap<>();
        HttpClient httpClient = HttpClient.newHttpClient();

        String baseUrl = "https://v6.exchangerate-api.com/v6/28b8780f8804d0f8bf92d7e9/history/INR/%d/%d/%d";

        for (int month = 1; month <= 12; month++) {
            String formattedMonth = String.format("%02d", month);
            for (int day = 1; day <= 31; day++) {
                String formattedDay = String.format("%02d", day);

                if (!checkDocuments(year + formattedMonth + formattedDay)) {
                    log.debug("Date: {}-{}-{}", year, formattedMonth, formattedDay);

                    String url = String.format(baseUrl, year, month, day);
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .build();

                    try {
                        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                        if (response.statusCode() == 200) {
                            String responseBody = response.body();
                            String date = String.format("%d%02d%02d", year, month, day);
                            double usdRate = parseUSDFromResponse(responseBody);
                            exchangeRates.put(date, usdRate);
                        } else {
                            break;
                        }
                    } catch (Exception e) {
                        log.error("Error fetching exchange rates: {}", e.getMessage());
                    }
                } else {
                    log.debug("MongoDB has the document for this date: {}-{}-{}", year, formattedMonth, formattedDay);
                }
            }
        }
        return exchangeRates;
    }

    /**
     * Parse USD exchange rate from the response body of the external API.
     *
     * @param responseBody The response body containing exchange rate data.
     * @return The USD exchange rate.
     * @throws JSONException If there is an error parsing the JSON response.
     */
    private static double parseUSDFromResponse(String responseBody) throws JSONException {
        JSONObject jsonResponse = new JSONObject(responseBody);
        JSONObject conversionRates = jsonResponse.getJSONObject("conversion_rates");
        return conversionRates.getDouble("USD");
    }

    /**
     * Fetch exchange rates for USD from the external API and store them in the database.
     *
     * @return A list of ExchangeRate objects containing the fetched exchange rates.
     */
    public List<ExchangeRate> fetchExchangeRatesForUSD() {
        log.info("Fetching exchange rates for USD.");
        Random random = new Random();
        List<ExchangeRate> exchangeRatesList = new ArrayList<>();
        int currentYear = LocalDate.now().getYear();
        log.debug("Current year: {}", currentYear);
        int year = currentYear - 1;
        Map<String, Double> exchangeRates = getExchangeRatesForYear(year);
        for (Map.Entry<String, Double> entry : exchangeRates.entrySet()) {
            ExchangeRate exchangeRate = new ExchangeRate();
            exchangeRate.setId(String.valueOf(random.nextInt(Integer.MAX_VALUE) + 1));
            exchangeRate.setCurrencyCode("USD");
            exchangeRate.setDate(entry.getKey());
            exchangeRate.setRate(entry.getValue());
            exchangeRatesList.add(exchangeRate);
        }
        exchangeRatesList.sort(Comparator.comparing(ExchangeRate::getDate).reversed());
        log.debug("Fetch and store exchange rates done; Size = {}", exchangeRatesList.size());
        return exchangeRatesList;
    }

    /**
     * Check if exchange rate documents exist for the specified date in the MongoDB database.
     *
     * @param date The date for which to check the existence of documents.
     * @return True if documents exist for the date, false otherwise.
     */
    public static boolean checkDocuments(String date) {
        log.debug("Checking documents for date: {}", date);
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("exchangeRateDb");
            MongoCollection<Document> collection = database.getCollection("exchangeRateCollection");
            Document query = new Document("date", date);
            long count = collection.countDocuments(query);
            return count > 0;
        } catch (Exception e) {
            log.error("Error checking documents: {}", e.getMessage());
            return false;
        }
    }
}