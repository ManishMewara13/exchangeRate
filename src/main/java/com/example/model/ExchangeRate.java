package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "exchangeRateCollection")
public class ExchangeRate {
    @Id
    private String id;
    private String currencyCode;
    private double rate;
    private Date date;

    public ExchangeRate() {
        // Default constructor
    }

    public ExchangeRate(String currencyCode, double rate, Date date) {
        this.currencyCode = currencyCode;
        this.rate = rate;
        this.date = date;
    }

    public ExchangeRate(String usd, double v) {
    }

    // Getters and setters
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "id='" + id + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", rate=" + rate +
                ", date=" + date +
                '}';
    }
}