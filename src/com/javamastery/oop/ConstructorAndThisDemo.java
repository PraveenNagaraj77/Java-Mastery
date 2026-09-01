package com.javamastery.oop;

public class ConstructorAndThisDemo {

    static class Employee {

        int employeeId;
        String name;
        double salary;

        // Constructor
        Employee(
                int employeeId,
                String name,
                double salary
        ) {

            this.employeeId = employeeId;
            this.name = name;
            this.salary = salary;
        }

        void displayDetails() {

            System.out.println(
                    "Employee ID : " + employeeId
            );

            System.out.println(
                    "Name        : " + name
            );

            System.out.println(
                    "Salary      : " + salary
            );
        }
    }

    public static void main(String[] args) {

        Employee employee1 =
                new Employee(
                        101,
                        "John",
                        50000
                );

        Employee employee2 =
                new Employee(
                        102,
                        "Praveen",
                        70000
                );

        employee1.displayDetails();

        System.out.println();

        employee2.displayDetails();
    }
}