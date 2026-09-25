package com.javamastery.java8.streamintermediateoperations;

import java.util.Arrays;
import java.util.List;

public class FlatMapExample {
    public static void main(String[] args) {
        List<String> skills1 = Arrays.asList("Java", "Spring Boot");
        List<String> skills2 = Arrays.asList("React", "JavaScript");
        List<String> skills3 = Arrays.asList("Docker", "Git");



        List<List<String>> allSkills =
                Arrays.asList(skills1, skills2, skills3);


        List<List<Integer>> numbers =
                Arrays.asList(
                        Arrays.asList(1, 2, 3),
                        Arrays.asList(4, 5, 6),
                        Arrays.asList(7, 8, 9)
                );

        allSkills.stream()
                .flatMap(List::stream)
                .forEach(System.out::println);


        System.out.println("--------------");

        List<Employee> employees =
                Arrays.asList(
                        new Employee(
                                "Praveen",
                                Arrays.asList("Java", "Spring Boot", "React")
                        ),
                        new Employee(
                                "Rahul",
                                Arrays.asList("Java", "Docker")
                        ),
                        new Employee(
                                "Arun",
                                Arrays.asList("React", "Node.js", "MongoDB")
                        )
                );

        employees.stream()
                .flatMap(employee -> employee.getSkills().stream())
                .distinct()
                .forEach(System.out::println);

        employees.stream()
                .flatMap(employee -> employee.getSkills().stream())
                .filter(skill -> skill.contains("Java"))
                .forEach(System.out::println);

        numbers.stream()
                .flatMap(List::stream)
                .filter(number->number%2==0)
                .forEach(System.out::println);

        System.out.println("-------------------");

    }
}
