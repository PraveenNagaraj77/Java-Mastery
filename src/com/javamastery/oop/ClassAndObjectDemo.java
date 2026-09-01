package com.javamastery.oop;

public class ClassAndObjectDemo {
    static class Employee{
        //state
        int employeeId;
        String name;
        double salary;

        // Behaviour

        void work(){
            System.out.println(name+"is working");
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

        public static void main(String[] args) {

            // Object 1
            Employee employee1 =
                    new Employee();

            employee1.employeeId = 101;
            employee1.name = "John";
            employee1.salary = 50000;

            // Object 2
            Employee employee2 =
                    new Employee();

            employee2.employeeId = 102;
            employee2.name = "Praveen";
            employee2.salary = 70000;

            employee1.displayDetails();
            employee1.work();

            System.out.println();

            employee2.displayDetails();
            employee2.work();


        }

    }
}
