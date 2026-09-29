package com.javamastery.java8.dateandtimeapi;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class OrderDateTimeExample {

    public static void main(String[] args) {

        // Expected delivery date
        LocalDate orderDate = LocalDate.now();

        LocalDate expectedDelivery =
                orderDate.plus(Period.ofDays(3));

        System.out.println("Expected delivery: " + expectedDelivery);

        // Format order timestamp
        LocalDateTime orderTime = LocalDateTime.now();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println(
                "Order time: " + orderTime.format(formatter)
        );

        // Measure processing time
        Instant start = Instant.now();

        // Simulate business processing
        for (int i = 0; i < 100_000; i++) {
            Math.sqrt(i);
        }

        Instant end = Instant.now();

        Duration processingTime =
                Duration.between(start, end);

        System.out.println(
                "Processing time: " + processingTime.toMillis() + " ms"
        );

        // Display a moment in different timezones
        ZonedDateTime indiaTime =
                orderTime.atZone(ZoneId.of("Asia/Kolkata"));

        ZonedDateTime londonTime =
                indiaTime.withZoneSameInstant(
                        ZoneId.of("Europe/London")
                );

        System.out.println("India: " + indiaTime);
        System.out.println("London: " + londonTime);
    }
}