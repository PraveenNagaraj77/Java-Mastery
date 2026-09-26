package com.javamastery.java8.collectors;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorsExample {

    public static void main(String[] args) {

        List<Integer> numbers =
                List.of(10, 20, 30, 40, 50);

        List<String> names =
                List.of("Praveen", "Arun", "Praveen", "Kumar", "Arun");

        System.out.println("-------toList---------");

        List<Integer> number = numbers.stream()
                .filter(num -> num > 25)
                .collect(Collectors.toList());

        System.out.println(number);

        System.out.println("-------toSet---------");

        Set<String> nameSet = names.stream()
                .collect(Collectors.toSet());

        System.out.println(nameSet);

        System.out.println("-------toMap---------");

        List<Product> products = Arrays.asList(
                new Product(101, "Laptop", 70000),
                new Product(102, "Phone", 30000),
                new Product(103, "Monitor", 15000)
        );

        Map<Integer, Product> productMap = products.stream()
                .collect(Collectors.toMap(
                        Product::getId,
                        p -> p,
                        (existing, replacement) -> existing
                ));

        productMap.forEach((key, value) ->
                System.out.println(key + " " + value));

        System.out.println("-------Joining---------");

        List<String> technologies =
                List.of("Java", "Spring", "React");

        String technology = technologies.stream()
                .collect(Collectors.joining(" | "));

        System.out.println(technology);

        System.out.println("-------groupingBy---------");

        List<Employee> employees = Arrays.asList(
                new Employee("Praveen", "IT", 90000),
                new Employee("Dhanush", "Food", 100000),
                new Employee("Udhaya", "Logistics", 100000)
        );

        Map<String, List<Employee>> employeesByDepartment =
                employees.stream()
                        .collect(Collectors.groupingBy(Employee::getDepartment));

        employeesByDepartment.entrySet().stream()
                .forEach(entry ->
                        System.out.println(
                                entry.getKey() + " - " + entry.getValue()
                        ));

        System.out.println("-------GroupingBy+Counting-----");

        Map<String, Long> countByDepartment =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.counting()
                        ));

        System.out.println(countByDepartment);

        System.out.println("-------partitioningBy------");

        List<Integer> numbers1 =
                List.of(10, 25, 40, 55, 70, 85);

        Map<Boolean, List<Integer>> result =
                numbers1.stream()
                        .collect(Collectors.partitioningBy(n -> n > 50));

        System.out.println(result);

        System.out.println("-------summarizingInt------");

        List<Integer> salaries =
                List.of(30000, 45000, 50000, 65000, 80000);

        IntSummaryStatistics stats =
                salaries.stream()
                        .collect(Collectors.summarizingInt(Integer::intValue));

        System.out.println(stats.getCount());
        System.out.println(stats.getSum());
        System.out.println(stats.getMin());
        System.out.println(stats.getMax());
        System.out.println(stats.getAverage());

        System.out.println("-----AverageInt");

        Double average =
                employees.stream()
                        .collect(Collectors.averagingInt(Employee::getSalary));

        System.out.println(average);
    }
}