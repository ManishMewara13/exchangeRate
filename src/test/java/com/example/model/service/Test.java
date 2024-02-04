package com.example.model.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {
    public static void main(String[] args) {

        // Get date
        Calendar calendar = Calendar.getInstance();
        Date dateToday = calendar.getTime();
        calendar.add(Calendar.YEAR, -1);
        Date oneYearAgo = calendar.getTime();

        // Format the date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String lastDate = dateFormat.format(oneYearAgo);
        String toadayDate = dateFormat.format(dateToday);

        System.out.println("Date oneYearAgo: " + lastDate);
        System.out.println("Date toadayDate: " + toadayDate);
    }
}
