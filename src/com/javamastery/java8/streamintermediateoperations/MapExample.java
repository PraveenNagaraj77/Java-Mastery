package com.javamastery.java8.streamintermediateoperations;

import com.javamastery.java8.streamapi.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MapExample {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Praveen", "IT", 70000),
                new Employee("Rahul", "HR", 50000),
                new Employee("Arun", "IT", 90000),
                new Employee("Karthik", "Finance", 60000)
        );

        List<String> names =
                Arrays.asList(
                        "Praveen",
                        null,
                        "Rahul"
                );
        List<String> names1 =
                Arrays.asList(
                        "praveen",
                        "rahul",
                        "arun",
                        "karthik"
                );

        List<Integer> numbers =
                Arrays.asList(10, 20, 30, 40, 50);

        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);

        employees.stream()
                .map(Employee::getSalary)
                .forEach(System.out::println);

        employees.stream()
                .map(employee ->
                        employee.getName()
                                + " - "
                                + employee.getDepartment()
                                + " - "
                                + employee.getSalary())
                .forEach(System.out::println);

        names.stream()
                .filter(Objects::nonNull)
                .map(String::toUpperCase)
                .forEach(System.out::println);

        numbers.stream()
                .map(number -> number * 2)
                .forEach(System.out::println);

        employees.stream()
                .filter(employee -> employee.getDepartment().equals("IT"))
                .map(Employee::getName)
                .forEach(System.out::println);

        employees.stream()
                .filter(employee -> employee.getDepartment().equals("IT"))
                .filter(employee -> employee.getSalary() > 60000)
                .map(Employee::getName)
                .forEach(System.out::println);

        numbers.stream()
                .filter(number -> number % 2 == 0)
                .map(number -> number * 10)
                .forEach(System.out::println);

    }
}
