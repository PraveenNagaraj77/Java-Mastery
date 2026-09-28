package com.javamastery.java8.dateandtimeapi;

import java.time.LocalTime;

public class LocalTimePractice {
    public static void main(String[] args) {
        LocalTime currentTime = LocalTime.now();
        System.out.println(currentTime);

        System.out.println(currentTime.getHour());
        System.out.println(currentTime.getMinute());

        LocalTime newTime = currentTime.plusHours(2);

        System.out.println(newTime);

        LocalTime minusMinutes = currentTime.minusMinutes(30);
        System.out.println(minusMinutes);

        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(18, 0);

        System.out.println(currentTime.isAfter(startTime));
        System.out.println(currentTime.isBefore(endTime));

        //Delivery Time Checker

        LocalTime deliveryStartTime = LocalTime.of(10,00);
        LocalTime deliveryEndTime = LocalTime.of(22,00);

        LocalTime delivery = LocalTime.of(11,00);

        if (!delivery.isBefore(deliveryStartTime)
                && !delivery.isAfter(deliveryEndTime)) {

            System.out.println("Delivery is Available");

        } else {

            System.out.println("Delivery is currently unavailable");
        }






    }
}
