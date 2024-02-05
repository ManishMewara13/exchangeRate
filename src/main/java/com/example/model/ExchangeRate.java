package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

/**
 * Model class representing an exchange rate.
 */
@Document(collection = "exchangeRateCollection")
@Component
public class ExchangeRate {
    @Id
    private String id;
    private String currencyCode;
    private double rate;
    private String date;

    // Default constructor
    public ExchangeRate() {
    }

    // Constructor with parameters
    public ExchangeRate(String currencyCode, double rate, String date) {
        this.currencyCode = currencyCode;
        this.rate = rate;
        this.date = date;
    }

    // Another constructor (looks like it was mistakenly left with incorrect parameter names)
    public ExchangeRate(String usd, double v) {
    }

    // toString method to represent the object as a string
    @Override
    public String toString() {
        return "ExchangeRate{" +
                "id='" + id + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", rate=" + rate +
                ", date='" + date + '\'' +
                '}';
    }

    // Getter and setter methods
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}