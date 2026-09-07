package com.javamastery.collections.comparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparatorBasic {

    static class Employee {
        private int id;
        private String name;
        private double salary;

        public Employee(int id, double salary, String name) {
            this.id = id;
            this.salary = salary;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getSalary() {
            return salary;
        }
    }

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(101, 70000, "Praveen"));
        employees.add(new Employee(102, 50000, "Rahul"));
        employees.add(new Employee(103, 60000, "Anjali"));
        employees.add(new Employee(104, 80000, "Karthik"));

        Comparator<Employee> byName =
                Comparator.comparing(Employee::getName)
                        .thenComparingInt(Employee::getId)
                        .thenComparingDouble(Employee::getSalary);

        employees.sort(byName);

        for (Employee employee : employees) {
            System.out.println(
                    employee.getName() + " : " +
                            employee.getSalary()
            );
        }
    }
}