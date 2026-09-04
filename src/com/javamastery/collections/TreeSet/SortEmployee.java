package com.javamastery.collections.TreeSet;

import java.util.Comparator;
import java.util.TreeSet;

public class SortEmployee {
    public static void main(String[] args) {
        TreeSet<Employee> employees = new TreeSet<>(Comparator.comparingDouble(Employee::getSalary).thenComparing(Employee::getName).thenComparing(Employee::getId));
        employees.add(new Employee(1,"Praveen",70000));
        employees.add(new Employee(2,"Rahul",50000));
        employees.add(new Employee(3,"Anjali",50000));
        employees.add(new Employee(4,"Vijay",80000));



        for (Employee employee : employees){
            System.out.println(employee.getName() + " " +employee.getSalary());
        }

    }
}
