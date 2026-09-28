package com.javamastery.java8.dateandtimeapi;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class InstantExample {
    public static void main(String[] args) {
        Instant now = Instant.now();
        System.out.println(now);

        Instant instant = Instant.ofEpochSecond(0);
        System.out.println(instant);

        System.out.println(now.getEpochSecond());
        System.out.println(now.toEpochMilli());

        Instant future = now.plusSeconds(60).plusSeconds(300).plusSeconds(3600);

        Instant future1 = now.plus(2, ChronoUnit.HOURS);


        ZonedDateTime indiaTime =
                ZonedDateTime.now(
                        ZoneId.of("Asia/Kolkata")
                );

        Instant instant1 = indiaTime.toInstant();
        System.out.println(instant1);

        //Real world

        Instant orderCreatedAt = Instant.now();

        ZonedDateTime indianTime =
                orderCreatedAt.atZone(
                        ZoneId.of("Asia/Kolkata")
                );
        ZonedDateTime usTime =
                orderCreatedAt.atZone(
                        ZoneId.of("America/New_York")
                );

    }
}
