package com.javamastery.oop;

public class EncapsulationDemo {
    static  class Employee{
        private int employeeId;
        private String name;
        private double salary;

        public Employee(int employeeId,String name,double salary){
            this.employeeId = employeeId;
            this.name = name;
            setSalary(salary);
        }

        // Getter
        public int getEmployeeId() {
            return employeeId;
        }

        // Getter
        public String getName() {
            return name;
        }

        public void setName(String name){
            if(name!= null && !name.isBlank()){
                this.name=name;
            }
        }

        // Getter
        public double getSalary() {
            return salary;
        }

        // Setter with validation
        public void setSalary(double salary) {

            if (salary > 0) {
                this.salary = salary;
            } else {
                System.out.println(
                        "Invalid salary"
                );
            }
        }
        public void displayDetails() {

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
        Employee employee =
                new Employee(
                        101,
                        "John",
                        50000
                );

        employee.displayDetails();

        System.out.println();

        employee.setSalary(60000);

        System.out.println(
                "Updated Salary : "
                        + employee.getSalary()
        );

        employee.setSalary(-5000);

        System.out.println();

        employee.displayDetails();
    }

}
