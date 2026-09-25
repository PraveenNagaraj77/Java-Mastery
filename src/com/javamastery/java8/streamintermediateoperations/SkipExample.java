package com.javamastery.java8.streamintermediateoperations;

import com.javamastery.java8.streamapi.Employee;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SkipExample {
    public static void main(String[] args) {
        List<Integer> numbers =
                Arrays.asList(10, 20, 30, 40, 50);

        List<Employee> employees =
                Arrays.asList(
                        new com.javamastery.java8.streamapi.Employee("Praveen", "IT", 50000),
                        new com.javamastery.java8.streamapi.Employee("Praveen", "IT", 50000),
                        new com.javamastery.java8.streamapi.Employee("Udhaya", "IT", 70000),
                        new com.javamastery.java8.streamapi.Employee("Dhanush", "Food", 60000),
                        new com.javamastery.java8.streamapi.Employee("Ansil", "Non Voice", 70000),
                        new com.javamastery.java8.streamapi.Employee("Gokul", "SAP", 80000),
                        new Employee("Arul", "IT", 90000)
                );

        System.out.println("----------------");
        System.out.println("Skip First 2 Numbers");

        numbers.stream()
                .skip(2)
                .forEach(System.out::println);

        System.out.println("----------------");
        System.out.println("Numbers after skipping the first 3.");

        numbers.stream()
                .skip(3)
                .forEach(System.out::println);

        System.out.println("----------------");
        System.out.println("Skip the first 2 IT Employees");

        employees.stream()
                .filter(employee -> employee.getDepartment().equals("IT"))
                .skip(2)
                .forEach(employee -> System.out.println(employee.getName() + " - " + employee.getDepartment() + " - " + employee.getSalary()));

        System.out.println("----------------");
        System.out.println("Find the 4th and 5th highest-paid employees.");

        employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .skip(3)
                .limit(2)
                .forEach(employee -> System.out.println(employee.getName() + " - " + employee.getDepartment() + " - " + employee.getSalary()));

        System.out.println("-------------");
        System.out.println("Pagination");

        int pageNumber = 2;
        int pageSize = 3;

        int skip = (pageNumber - 1) * pageSize;

        employees.stream()
                .skip(skip)
                .limit(pageSize)
                .forEach(employee -> System.out.println(employee.getName() + " - " + employee.getDepartment() + " - "+employee.getSalary()));

        System.out.println("-------------");
        System.out.println("Find the next 2 highest-paid IT employees after the top 2, and print their names and salaries.");

        employees.stream()
                .filter(employee -> employee.getDepartment().equals("IT"))
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .skip(2)
                .limit(2)
                .forEach(employee -> System.out.println(employee.getName() + " - " + employee.getSalary()));





    }
}
