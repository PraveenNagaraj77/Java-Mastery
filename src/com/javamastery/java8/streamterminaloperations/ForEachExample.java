package com.javamastery.java8.streamterminaloperations;

import java.util.List;

public class ForEachExample {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(10, 20, 30, 40, 50);

        List<String> users = List.of(
                "praveen",
                "arun",
                "kumar",
                "prakash"
        );

        numbers.stream()
                .forEach(System.out::println);

        System.out.println("-----------------");
        System.out.println("Filter +  Foreach");

        numbers.stream()
                .filter(number->number>25)
                .forEach(System.out::println);

        System.out.println("-----------------");
        System.out.println("Map +  Foreach");

        numbers.stream()
                .map(number->number*2)
                .forEach(System.out::println);

        System.out.println("-----------------");
        System.out.println("Print the names that start with p in uppercase");

        users.stream()
                .filter(user->user.startsWith("p"))
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }
}
