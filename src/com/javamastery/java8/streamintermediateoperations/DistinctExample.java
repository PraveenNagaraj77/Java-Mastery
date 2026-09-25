package com.javamastery.java8.streamintermediateoperations;

import com.javamastery.java8.streamapi.Employee;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DistinctExample {

    public static void main(String[] args) {

        // ============================================================
        // 1. Remove Duplicate Numbers
        // ============================================================

        List<Integer> numbers =
                Arrays.asList(10, 20, 10, 30, 20, 40, 30);

        System.out.println("Remove Duplicate Numbers");

        numbers.stream()
                .distinct()
                .forEach(System.out::println);

        System.out.println();


        // ============================================================
        // 2. Remove Duplicate Names
        // ============================================================

        List<String> names =
                Arrays.asList(
                        "Java",
                        "Spring",
                        "Java",
                        "React",
                        "Spring"
                );

        System.out.println("Remove Duplicate Names");

        names.stream()
                .distinct()
                .forEach(System.out::println);

        System.out.println();


        // ============================================================
        // 3. Filter + Distinct
        // ============================================================

        List<Employee> employees =
                Arrays.asList(
                        new Employee("Praveen", "IT", 50000),
                        new Employee("Praveen", "IT", 50000),
                        new Employee("Udhaya", "IT", 70000),
                        new Employee("Dhanush", "Food", 60000),
                        new Employee("Ansil", "Non Voice", 70000),
                        new Employee("Gokul", "SAP", 80000),
                        new Employee("Arul", "IT", 90000)
                );

        System.out.println("Filter + Distinct");

        employees.stream()
                .filter(employee ->
                        employee.getDepartment().equals("IT")
                )
                .distinct()
                .forEach(employee ->
                        System.out.println(
                                employee.getName()
                                        + " - "
                                        + employee.getDepartment()
                                        + " - "
                                        + employee.getSalary()
                        )
                );

        System.out.println();


        // ============================================================
        // 4. Distinct + Sorted
        // ============================================================

        System.out.println("Distinct + Sorted");

        employees.stream()
                .filter(employee ->
                        employee.getDepartment().equals("IT")
                )
                .distinct()
                .sorted(Comparator.comparing(Employee::getName))
                .forEach(employee ->
                        System.out.println(employee.getName())
                );

        System.out.println();


        // ============================================================
        // 5. Distinct Employee Names
        // ============================================================

        System.out.println("Distinct Employee Names");

        employees.stream()
                .map(Employee::getName)
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }
}