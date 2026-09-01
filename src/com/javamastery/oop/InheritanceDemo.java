package com.javamastery.oop;

public class InheritanceDemo {
    static class Employee{
        protected int employeeId;
        protected String name;
        protected double salary;

        Employee(
                int employeeId,
                String name,
                double salary
        ) {

            this.employeeId = employeeId;
            this.name = name;
            this.salary = salary;

            System.out.println(
                    "Employee constructor called"
            );
        }

        void displayDetails() {

            System.out.println(
                    "ID     : " + employeeId
            );

            System.out.println(
                    "Name   : " + name
            );

            System.out.println(
                    "Salary : " + salary
            );
        }



        void work(){
            System.out.println(name+ " is working");
        }
    }

    static class Developer extends Employee{
        Developer(int employeeId,String name,double salary){
            super(employeeId,name,salary);
            System.out.println("Developer Constructor called");

        }

        void writeCode(){
            System.out.println(name + " is writing java code");
        }

        void work(){
            System.out.println(name + "is Developing a Software");
        }




    }

    public static void main(String[] args) {
        Developer developer = new Developer(101,"Praveen",70000);

        System.out.println();

        developer.displayDetails();
        developer.work();
        developer.writeCode();


    }

}

