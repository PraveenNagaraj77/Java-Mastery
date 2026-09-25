package com.javamastery.java8.streamintermediateoperations;

import com.javamastery.java8.streamapi.Employee;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LimitExample {
    public static void main(String[] args) {

        List<Integer> numbers =
                Arrays.asList(10, 20, 30, 40, 50);

        List<com.javamastery.java8.streamapi.Employee> employees =
                Arrays.asList(
                        new com.javamastery.java8.streamapi.Employee("Praveen", "IT", 50000),
                        new com.javamastery.java8.streamapi.Employee("Praveen", "IT", 50000),
                        new com.javamastery.java8.streamapi.Employee("Udhaya", "IT", 70000),
                        new com.javamastery.java8.streamapi.Employee("Dhanush", "Food", 60000),
                        new com.javamastery.java8.streamapi.Employee("Ansil", "Non Voice", 70000),
                        new com.javamastery.java8.streamapi.Employee("Gokul", "SAP", 80000),
                        new Employee("Arul", "IT", 90000)
                );

        System.out.println("-------------------------");
        System.out.println("First 3 Numbers");

        numbers.stream()
                .limit(3)
                .forEach(System.out::println);

        System.out.println("-------------------------");
        System.out.println("First 3 Names");

        employees.stream()
                .map(Employee::getName)
                .limit(3)
                .forEach(System.out::println);

        System.out.println("-------------------------");
        System.out.println("First 3 IT Employees");

        employees.stream()
                .filter(employee -> employee.getDepartment().equals("IT"))
                .limit(3)
                .forEach(employee -> System.out.println(employee.getName() + " - " + employee.getDepartment() + " - "+ employee.getSalary()));

        System.out.println("-------------------------");
        System.out.println("Top 3 Highest Paid Employees");

        employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .limit(3)
                .forEach(employee -> System.out.println(employee.getName() + " - " + employee.getSalary()));

        System.out.println("-------------------------");
        System.out.println("Top 3 unique employee names from IT employees, ordered alphabetically");

        employees.stream()
                .filter(employee -> employee.getDepartment().equals("IT"))
                .map(Employee::getName)
                .distinct()
                .sorted()
                .limit(3)
                .forEach(System.out::println);
    }
}
