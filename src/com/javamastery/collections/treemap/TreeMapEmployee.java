package com.javamastery.collections.treemap;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapEmployee {
    public static void main(String[] args) {
        TreeMap<Employee,String> employees =
                new TreeMap<>(Comparator.comparingDouble(Employee::getSalary).thenComparing(Employee::getName).thenComparing(Employee::getId));

        employees.put(
                new Employee(101, "Praveen", 70000),
                "Developer"
        );

        employees.put(
                new Employee(102, "Rahul", 50000),
                "Developer"
        );

        employees.put(
                new Employee(103, "Anjali", 60000),
                "Developer"
        );

        employees.put(
                new Employee(104, "Vijay", 80000),
                "Manager"
        );

        employees.put(
                new Employee(105, "Karthik", 50000),
                "Developer"
        );

        for (Map.Entry<Employee, String> entry : employees.entrySet()) {

            Employee employee = entry.getKey();

            System.out.println(
                    employee.getName() +
                            " : " +
                            employee.getSalary() +
                            " : " +
                            entry.getValue()
            );
        }


    }



}
