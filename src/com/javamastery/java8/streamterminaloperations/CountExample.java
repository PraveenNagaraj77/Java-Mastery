package com.javamastery.java8.streamterminaloperations;

import java.util.List;

public class CountExample {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(10, 20, 30, 40, 50);

        long count = numbers.stream().count();

        System.out.println(count);

        long count1 = numbers.stream()
                .filter(number->number>25)
                .count();

        System.out.println(count1);


        List<String> users = List.of(
                "Praveen",
                "Arun",
                "Praveen",
                "Kumar",
                "Arun",
                "Pavan"
        );

        long uniqueCount = users.stream()
                .distinct()
                .count();

        System.out.println(uniqueCount);


        List<Integer> prices = List.of(
                100, 500, 750, 1000, 1500, 2000
        );

        long priceCount = prices.stream()
                .filter(price->price>700).count();

        System.out.println(priceCount);

        List<Integer> numbers1 = List.of(
                10, 20, 30, 40, 50
        );

        long numbers1Count = numbers1.stream()
                .filter(n -> n > 15)
                .limit(2)
                .count();

        System.out.println(numbers1Count);
    }
}
