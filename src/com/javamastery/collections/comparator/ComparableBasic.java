package com.javamastery.collections.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComparableBasic {
    static class Employee implements Comparable<Employee> {
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

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        public int compareTo(Employee other){
            return Double.compare(this.salary,other.salary);
        }
    }


    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(101, 70000, "Praveen"));
        employees.add(new Employee(102, 50000, "Rahul"));
        employees.add(new Employee(103, 60000, "Anjali"));
        employees.add(new Employee(104, 80000, "Karthik"));

        Collections.sort(employees);

        for (Employee emloyee : employees){
            System.out.println(emloyee.getName() +  " : " + emloyee.getSalary() );
        }

    }

}
