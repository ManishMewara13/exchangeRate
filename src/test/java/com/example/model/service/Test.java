package com.example.model.service;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Test {
    public static void main(String[] args) {
        boolean documentsPresent = checkDocuments("20231202");
        System.out.println("Documents present: " + documentsPresent);
    }

    public static boolean checkDocuments(String date) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase("exchangeRateDb");
            MongoCollection<Document> collection = database.getCollection("exchangeRateCollection");
            Document query = new Document("date", date);
            return collection.countDocuments(query) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Handle exceptions by returning false
        }
    }
}
