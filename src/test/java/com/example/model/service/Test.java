package com.example.model.service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Test {
    public static void main(String[] args) {
        secondFunc();
    }

    public static void secondFunc() {
        LocalDate today = LocalDate.now();
        LocalDate lastYear = today.minusYears(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        // Iterate through dates from last year to today
        while (!lastYear.isAfter(today)) {
            String formattedDate = lastYear.format(formatter);
            int year = lastYear.getYear();
            int month = lastYear.getMonthValue();
            int day = lastYear.getDayOfMonth();
            System.out.println("Date : " + year + month + day);

            // Move to the next day
            lastYear = lastYear.plusDays(1);
        }
    }



    public static void firstFunc() {
        //  Get today's date
        LocalDate today = LocalDate.now();

        // Get the date for the same day last year
        LocalDate lastYear = today.minusYears(1);

        // Format the dates
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        // Iterate through dates from last year to today
        LocalDate currentDate = lastYear;
        while (!currentDate.isAfter(today)) {
            String formattedDate = currentDate.format(formatter);
            System.out.println("Date: " + formattedDate);
            currentDate = currentDate.plusDays(1);
        }
    }
}
