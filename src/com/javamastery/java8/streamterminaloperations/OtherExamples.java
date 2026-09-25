package com.javamastery.java8.streamterminaloperations;

import java.util.List;
import java.util.Optional;

public class OtherExamples {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(10, 20, 30, 40, 50);

        Optional<Integer> result = numbers.stream()
                .filter(n -> n > 25)
                .findFirst();

        System.out.println(result.orElse(0));

        Optional<Integer> result1 = numbers.stream()
                .filter(n -> n > 25)
                .findAny();

        System.out.println(result1.orElse(0));

        boolean result2 = numbers.stream()
                .allMatch(n -> n > 0);

        System.out.println(result2);
    }
}
