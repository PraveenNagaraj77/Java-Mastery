package com.javamastery.oop;

public class PolymorphismDemo {
    static class Employee{
        void work(){
            System.out.println("Employee is working");
        }
    }
    static class Developer extends Employee{
        @Override
        void work() {
            System.out.println("Developer is writing code");
        }
    }
    static  class Tester extends  Employee{
        @Override
        void work() {
            System.out.println("Tester is Testing the Software");
        }
    }

    static class Manager extends Employee{
        @Override
        void work() {
            System.out.println("Manager is managing the team");
        }
    }

    public static void main(String[] args) {
        Employee employee;

        employee = new Employee();
        employee.work();

        employee = new Developer();
        employee.work();

        employee = new Tester();
        employee.work();

        employee = new Manager();
        employee.work();

    }

}
