package com.javamastery.java8.dateandtimeapi;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZoneDateTimeExample {
    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);

        ZonedDateTime indiaTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        System.out.println(indiaTime);

        ZonedDateTime newYorkTime = indiaTime.withZoneSameInstant(ZoneId.of("America/New_York"));

        System.out.println(newYorkTime);


        ZonedDateTime dateTime = ZonedDateTime.now();

        System.out.println(dateTime.toLocalDate());
        System.out.println(dateTime.toLocalTime());
        System.out.println(dateTime.getZone());
        System.out.println(dateTime.getOffset());

        //Global Meeting Scheduler

        ZonedDateTime meetingIndia = ZonedDateTime.of(
                2026,9,28,
                18,0,0,0,
                ZoneId.of("Asia/Kolkata")
        );

        ZonedDateTime meetingNewYork = meetingIndia.withZoneSameInstant(ZoneId.of("America/New_York"));

        ZonedDateTime meetingLondon = meetingIndia.withZoneSameInstant(ZoneId.of("Europe/London"));

        System.out.println("Meeting India : " + meetingIndia);
        System.out.println("Meeting London" + meetingLondon);
        System.out.println("Meeting Newyork" + meetingNewYork);


    }
}
