package com.javamastery.java8.streamintermediateoperations;

import com.javamastery.java8.streamapi.Employee;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SortedExample {

    public static void main(String[] args) {

        // ============================================================
        // 1. Numbers - Natural Ordering
        // ============================================================

        List<Integer> numbers =
                Arrays.asList(50, 10, 40, 20, 30);

        System.out.println("Natural Ordering");

        numbers.stream()
                .sorted()
                .forEach(System.out::println);

        System.out.println();


        // ============================================================
        // 2. Numbers - Descending Order
        // ============================================================

        System.out.println("Descending Order");

        numbers.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);

        System.out.println();


        // ============================================================
        // 3. Names - Alphabetical Order
        // ============================================================

        List<String> names =
                Arrays.asList(
                        "Praveen",
                        "Rahul",
                        "Arun",
                        "Karthick",
                        "Siva"
                );

        System.out.println("Sort Alphabetically");

        names.stream()
                .sorted()
                .forEach(System.out::println);

        System.out.println();


        // ============================================================
        // 4. Names - Sort by Length
        // ============================================================

        System.out.println("Sort Names by Length");

        names.stream()
                .sorted(Comparator.comparing(String::length))
                .forEach(System.out::println);

        System.out.println();


        // ============================================================
        // 5. Employee Data
        // ============================================================

        List<Employee> employees =
                Arrays.asList(
                        new Employee("Praveen", "IT", 50000),
                        new Employee("Udhaya", "IT", 70000),
                        new Employee("Dhanush", "Food", 60000),
                        new Employee("Ansil", "Non Voice", 70000),
                        new Employee("Gokul", "SAP", 80000),
                        new Employee("Arul", "IT", 90000)
                );


        // ============================================================
        // 6. Employees - Salary Ascending
        // ============================================================

        System.out.println("Employees by Salary - Ascending");

        employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary))
                .forEach(employee ->
                        System.out.println(
                                employee.getName() + " - " + employee.getSalary()
                        )
                );

        System.out.println();


        // ============================================================
        // 7. Employees - Salary Descending
        // ============================================================

        System.out.println("Employees by Salary - Descending");

        employees.stream()
                .sorted(
                        Comparator.comparing(Employee::getSalary)
                                .reversed()
                )
                .forEach(employee ->
                        System.out.println(
                                employee.getName() + " - " + employee.getSalary()
                        )
                );

        System.out.println();


        // ============================================================
        // 8. Employees - Sort by Name
        // ============================================================

        System.out.println("Employees by Name");

        employees.stream()
                .sorted(Comparator.comparing(Employee::getName))
                .forEach(employee ->
                        System.out.println(employee.getName())
                );

        System.out.println();


        // ============================================================
        // 9. IT Employees - Salary Descending
        // ============================================================

        System.out.println("IT Employees by Salary - Descending");

        employees.stream()
                .filter(employee ->
                        employee.getDepartment().equals("IT")
                )
                .sorted(
                        Comparator.comparing(Employee::getSalary)
                                .reversed()
                )
                .forEach(employee ->
                        System.out.println(
                                employee.getName() + " - " + employee.getSalary()
                        )
                );

        System.out.println();


        // ============================================================
        // 10. Final Challenge
        // IT employees
        // Salary > 60000
        // Salary descending
        // Print names
        // ============================================================

        System.out.println(
                "IT Employees with Salary > 60000 - Salary Descending"
        );

        employees.stream()
                .filter(employee ->
                        employee.getDepartment().equals("IT")
                )
                .filter(employee ->
                        employee.getSalary() > 60000
                )
                .sorted(
                        Comparator.comparing(Employee::getSalary)
                                .reversed()
                )
                .map(Employee::getName)
                .forEach(System.out::println);
    }
}