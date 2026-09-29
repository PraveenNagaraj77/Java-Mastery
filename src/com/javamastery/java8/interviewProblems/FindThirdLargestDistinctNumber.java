package com.javamastery.java8.interviewProblems;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FindThirdLargestDistinctNumber {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(
                10, 25, 10, 40, 30, 50, 25, 60
        );

        Optional<Integer> result = numbers.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst();

        result.ifPresent(System.out::println);

    }
;}
