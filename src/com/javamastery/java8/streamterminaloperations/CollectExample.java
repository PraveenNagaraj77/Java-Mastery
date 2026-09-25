package com.javamastery.java8.streamterminaloperations;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectExample {
    public static void main(String[] args) {
       List<Integer> numbers = List.of(10, 20, 30, 40, 50);

        List<String> names = List.of(
                "Praveen",
                "Arun",
                "Praveen",
                "Kumar",
                "Arun"
        );

        System.out.println("-----------------");
        System.out.println("Collect into List");

       List<Integer> result = numbers.stream()
               .filter(number->number>25)
               .collect(Collectors.toList());

        System.out.println(result);

        System.out.println("-----------------");
        System.out.println("Collect into Set");

        Set<String> nameSet = names.stream()
                .collect(Collectors.toSet());

        System.out.println(nameSet);

        System.out.println("------------------");
        System.out.println("Filter + Map + Collect");

        List<String> users = List.of(
                "praveen",
                "arun",
                "kumar",
                "prakash",
                "pavan"
        );

        List<String> usersList = users.stream()
                .filter(user->user.startsWith("p"))
                .map(user->user.toUpperCase())
                .collect(Collectors.toList());

        System.out.println(usersList);

        System.out.println("--------------");
        System.out.println("Real world pattern");

        List<Integer> prices = List.of(
                100, 250, 500, 750, 1000
        );

        List<Integer> pricesList = prices.stream()
                .filter(price->price>500)
                .collect(Collectors.toList());

        System.out.println(pricesList);

    }
}
