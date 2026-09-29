package com.javamastery.java8.interviewProblems;

import com.javamastery.java8.collectors.Employee;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SecondHighestSalary {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Arun", "IT", 60000),
                new Employee("Priya", "HR", 50000),
                new Employee("Rahul", "IT", 75000),
                new Employee("Meena", "HR", 65000),
                new Employee("Karthik", "Finance", 80000)
        );

        Optional<Employee> result = employees.stream()
                .filter(employee -> employee.getDepartment().equals("IT"))
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .skip(1)
                .findFirst();

        result.ifPresent(System.out::println);

    }
}
