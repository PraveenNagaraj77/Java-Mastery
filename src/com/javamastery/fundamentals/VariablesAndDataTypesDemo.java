package com.javamastery.fundamentals;

public class VariablesAndDataTypesDemo {
    public static void main(String[] args) {

        // =========================================================
        // 1. Creating Objects
        // =========================================================

        Employee e1 = new Employee();

        e1.employeeId=1;
        e1.name = "John";
        e1.age=26;
        e1.salary=50000.00;
        e1.experience = 5.5f;
        e1.grade = 'A';
        e1.active = true;

        Employee e2 = new Employee();
        e2.employeeId = 2;
        e2.name = "David";
        e2.age = 30;
        e2.salary = 50000.75;
        e2.experience = 7.0f;
        e2.grade = 'B';
        e2.active = false;

        // =========================================================
        // 2. Static Variable
        // =========================================================

        System.out.println("Company : "+Employee.company);

        Employee.company = "HCL";

        System.out.println("Company after changes"+Employee.company);

        System.out.println("Employee 1 Company: " + e1.company);
        System.out.println("Employee 2 Company: " + e2.company);



        // =========================================================
        // 3. Static Final Constant
        // =========================================================

        System.out.println(
                "Maximum Working Hours: " + Employee.MAX_WORKING_HOURS
        );

        // =========================================================
        // 4. Local Variables
        // =========================================================

        int employeeCount = 2;
        double averageSalary = (e1.salary + e2.salary) / employeeCount;

        System.out.println("Employee Count: " + employeeCount);
        System.out.println("Average Salary: " + averageSalary);

        // 5. Widening Conversion
        // int -> long -> double
        // =========================================================

        int number = 100;

        long longNumber = number;

        double doubleNumber = longNumber;

        System.out.println("\n--- Widening Conversion ---");
        System.out.println("int value: " + number);
        System.out.println("long value: " + longNumber);
        System.out.println("double value: " + doubleNumber);

        // =========================================================
        // 6. Narrowing Conversion
        // double -> int
        // =========================================================

        double originalSalary = 45000.99;

        int convertedSalary = (int)originalSalary;

        System.out.println("\n--- Narrowing Conversion ---");
        System.out.println("Original double: " + originalSalary);
        System.out.println("Converted int: " + convertedSalary);

        Employee e3 = null;
        System.out.println("\n--- Null Reference ---");
        System.out.println("Employee e3: " + e3);

        // 8. Variable Scope
        // =========================================================

        int outerVariable = 100;

        System.out.println("\n--- Variable Scope ---");
        System.out.println("Outer variable: " + outerVariable);

        {
            int blockVariable = 200;

            System.out.println("Block variable: " + blockVariable);
            System.out.println("Outer variable inside block: " + outerVariable);
        }

        // blockVariable cannot be accessed here
        // System.out.println(blockVariable);


        // =========================================================
        // 9. Employee Details
        // =========================================================

        System.out.println("\n--- Employee 1 ---");

        System.out.println("ID: " + e1.employeeId);
        System.out.println("Name: " + e1.name);
        System.out.println("Age: " + e1.age);
        System.out.println("Salary: " + e1.salary);
        System.out.println("Experience: " + e1.experience);
        System.out.println("Grade: " + e1.grade);
        System.out.println("Active: " + e1.active);


        System.out.println("\n--- Employee 2 ---");

        System.out.println("ID: " + e2.employeeId);
        System.out.println("Name: " + e2.name);
        System.out.println("Age: " + e2.age);
        System.out.println("Salary: " + e2.salary);
        System.out.println("Experience: " + e2.experience);
        System.out.println("Grade: " + e2.grade);
        System.out.println("Active: " + e2.active);




    }
}
