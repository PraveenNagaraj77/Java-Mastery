package com.javamastery.java8.dateandtimeapi;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

public class PeriodAndDurationExample {
    public static void main(String[] args) {
        System.out.println("Using Period with LocalDate");

        LocalDate startDate = LocalDate.of(2026,9,28);

        Period period = Period.ofMonths(3);
        LocalDate result = startDate.plus(period).minus(period.ofDays(10));
        System.out.println(result);

        LocalDate start =
                LocalDate.of(2020, 1, 1);

        LocalDate end =
                LocalDate.of(2026, 9, 28);

        Period periods =
                Period.between(start, end);

        System.out.println(periods.getYears());
        System.out.println(periods.getMonths());
        System.out.println(periods.getDays());


        LocalDate subscriptionStart =
                LocalDate.of(2026, 1, 15);

        LocalDate subscriptionEnd =
                subscriptionStart.plusMonths(12);

        Period subscriptionPeriod =
                Period.between(subscriptionStart, subscriptionEnd);

        System.out.println(subscriptionPeriod);


        System.out.println("Duration");
        Duration duration =
                Duration.ofHours(5);

        LocalTime starts =
                LocalTime.of(10, 30);

        Duration durations =
                Duration.ofHours(2);

        LocalTime ends =
                starts.plus(durations);

        System.out.println(ends);

        LocalTime startss =
                LocalTime.of(10, 30);

        LocalTime endss =
                LocalTime.of(14, 45);

        Duration durationss =
                Duration.between(startss, endss);

        System.out.println(durationss);


    }
}
