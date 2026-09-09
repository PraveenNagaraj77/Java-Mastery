package com.javamastery.collections.collectionPrograms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortListOfEmployeesByMultipleFields {
    static class Employee{
        private int id;
        private String name;
        private double salary;

        public Employee(int id, String name, double salary) {
            this.id = id;
            this.name = name;
            this.salary = salary;
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
        Employee employee1 = new Employee(1,"Praveen",50000);
        Employee employee2 = new Employee(2,"Dhanush",60000);

        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);

        Comparator<Employee> bySalary = Comparator.comparingDouble(Employee::getSalary).thenComparing(Employee::getName).thenComparingInt(Employee::getId);

        employees.sort(bySalary);

        for (Employee employee : employees) {
            System.out.println(
                    employee.getId() + " " +
                            employee.getName() + " " +
                            employee.getSalary()
            );
        }


    }
}
