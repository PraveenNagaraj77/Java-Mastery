package com.javamastery.java8.dateandtimeapi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterExample {
    public static void main(String[] args) {

        LocalDateTime date = LocalDateTime.of(2026, 9, 28,14,20,35);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
        String formattedDate = date.format(formatter);
        System.out.println(formattedDate);

        System.out.println("Parsing String ");

        String dateText = "28-09-2026";

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate newDate = LocalDate.parse(dateText,formatter1);

        System.out.println(newDate);

        System.out.println("Parsing LOCAL DATE TIME");

        String text = "2026-09-28 14:30:45";
        DateTimeFormatter formatter2 =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime dateTime = LocalDateTime.parse(text,formatter2);
        System.out.println(dateTime);

        System.out.println("Built in Formatters");

        String result = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(result);

        String result1 = date.format(DateTimeFormatter.ISO_LOCAL_TIME);
        System.out.println(result1);

        String result2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        System.out.println(result2);

        //Real world example
        System.out.println("Real world");

        String requestTime = "28/09/2026 14:30";

        DateTimeFormatter formatter3 =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        LocalDateTime orderTime =
                LocalDateTime.parse(requestTime, formatter3);
        System.out.println(orderTime);

        LocalDateTime deliveryTime = orderTime.plusHours(3);
        String response = deliveryTime.format(formatter3);
        System.out.println(response);



    }
}
