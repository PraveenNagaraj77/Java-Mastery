package com.javamastery.java8.interviewProblems;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMapExample {
    public static void main(String[] args) {
        List<List<String>> teams = Arrays.asList(
                Arrays.asList("Java", "Spring"),
                Arrays.asList("React", "Node"),
                Arrays.asList("Docker", "Kubernetes")
        );

        List<String> result = teams.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println(result);

    }
}
