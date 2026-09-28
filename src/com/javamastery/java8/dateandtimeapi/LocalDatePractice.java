package com.javamastery.java8.dateandtimeapi;

import java.time.LocalDate;

public class LocalDatePractice {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println(today);

        LocalDate deliveryDate = today.plusDays(7);

        System.out.println(today.getYear());
        System.out.println(today.getMonth());
        System.out.println(today.getDayOfMonth());
        System.out.println(deliveryDate);
        System.out.println(today.plusMonths(1));
        System.out.println(today.plusYears(1));

        //Comparing dates

        LocalDate futureDate = today.plusDays(5);

        System.out.println(futureDate.isAfter(today));
        System.out.println(futureDate.isBefore(today));
        System.out.println(futureDate.isEqual(today));





    }
}
