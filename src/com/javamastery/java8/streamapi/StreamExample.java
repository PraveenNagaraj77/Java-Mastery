package com.javamastery.java8.streamapi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10,20,30,40,50);

        List<String> names =
                Arrays.asList("praveen", "rahul", "arun");

        numbers.stream()
                .filter(number->number >20)
                .map(number->number*2)
                .forEach(System.out::println);

        names.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        List<Employee> employees = Arrays.asList(
                new Employee("Praveen", "IT", 70000),
                new Employee("Rahul", "HR", 50000),
                new Employee("Arun", "IT", 90000),
                new Employee("Karthik", "Finance", 60000)
        );

        System.out.println("-------------");

        employees.stream()
                .filter(employee -> employee.getDepartment().equals("IT"))
                .filter(employee -> employee.getSalary()>60000)
                .forEach(employee -> System.out.println(employee.getName()));
    }
}
