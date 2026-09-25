package com.javamastery.java8.streamintermediateoperations;

import com.javamastery.java8.streamapi.Order;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FilterExample {
    public static void main(String[] args) {
        List<Order> orders = Arrays.asList(
                new Order(101,"PAID",1500),
                new Order(102, "PENDING", 500),
                new Order(103, "PAID", 2500),
                new Order(104, "CANCELLED", 1000)
        );



        List<String> names =
                Arrays.asList(
                        "Praveen",
                        null,
                        "Rahul",
                        null,
                        "Karthik",
                        "Rohan",
                        "Siva",
                        "Arun"
                );

        List<String> names1 =
                Arrays.asList(
                        "Praveen",
                        "Rahul",
                        "Karthik",
                        "Rohan",
                        "Siva",
                        "Arun"
                );


        List<String> technologies =
                Arrays.asList(
                        "Java",
                        "Spring Boot",
                        "React",
                        "JavaScript",
                        "Docker"
                );

        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10,22,24,26,27,32,36,39,37,52,53,57,59,60,72);

        orders.stream()
                .filter(order->order.getStatus().equals("PAID"))
                .filter(order -> order.getAmount()>1000)
                .forEach(order -> System.out.println(order.getOrderId()));

        System.out.println("-----------------");
        names.stream()
                .filter(Objects::nonNull)
                .forEach(System.out::println);

        System.out.println("-----------------");

        technologies.stream()
                .filter(technology->technology.contains("Java"))
                .forEach(System.out::println);

        System.out.println("-----------------");

        names1.stream().filter(name->name.length()>5)
                        .forEach(System.out::println);

        System.out.println("-----------------");

        numbers.stream()
                .filter(number->number%2 == 0)
                .forEach(System.out::println);

        numbers.stream()
                .filter(number->number%2 != 0)
                .forEach(System.out::println);

        System.out.println("-----------------");

        numbers.stream()
                .filter(number -> number > 50)
                .forEach(System.out::println);

        System.out.println("-----------------");

        numbers.stream()
                .filter(number->number >=20 && number<=50)
                .forEach(System.out::println);


        System.out.println("-----------------");

        List<Integer> numbers1 =
                Arrays.asList(10, 20, 30, 40, 50);

        List<Integer> result =
                numbers1.stream()
                        .filter(number -> number > 20)
                        .collect(Collectors.toList());
        System.out.println(result);

    }
}
