package com.javamastery.java8.dateandtimeapi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalDateTimeExample {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        LocalDateTime dateTime = LocalDateTime.of(2026,9,28,14,30,45);

        System.out.println(dateTime);

        System.out.println(dateTime.getDayOfYear());
        System.out.println(dateTime.getYear());
        System.out.println(dateTime.getMonth());
        System.out.println(dateTime.getDayOfMonth());

        System.out.println(dateTime.getHour());
        System.out.println(dateTime.getMinute());
        System.out.println(dateTime.getSecond());
        System.out.println(dateTime.getNano());


        LocalDateTime future = dateTime.plusDays(2).plusHours(4).plusMinutes(30);
        System.out.println(future);


        LocalDateTime orderTime = LocalDateTime.of(2026,9,28,10,30);

        LocalDateTime deliveryTime = orderTime.plusHours(3);

        System.out.println(deliveryTime.isAfter(orderTime));
        System.out.println(deliveryTime.isBefore(orderTime));
        System.out.println(deliveryTime.isEqual(orderTime));

        LocalDate date = dateTime.toLocalDate();
        LocalTime time = dateTime.toLocalTime();

        //Combining Local Date + Local Time

        LocalDate newDate = LocalDate.of(2026, 9, 28);
        LocalTime newTime = LocalTime.of(14, 30);

        LocalDateTime updatedTime = LocalDateTime.of(newDate,newTime);



        System.out.println(updatedTime);

        ///Order Processing

        LocalDateTime orderCreatedAt = LocalDateTime.now();

        LocalDateTime expectedDelivery =
                orderCreatedAt.plusDays(3);

        System.out.println("Order Created: " + orderCreatedAt);
        System.out.println("Expected Delivery: " + expectedDelivery);

    }
}
