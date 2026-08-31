package com.javamastery.methods;

public class MethodsDemo {
    //Instance Variable
    private String companyName;
    //Consturctor
    public MethodsDemo(String companyName){
        this.companyName = companyName;
    }

    public static void main(String[] args) {
        //Void Method
        printWelcomeMessage();

        //Method WIth Paramenters
        displayEmployee("Praveen",101);

        //Method with Return Value
        double salary = calculateSalary(45000,5000);
        System.out.println("Total Salary:"+salary);

        //Method with Condition
        boolean eligible = isElgibleForBonus(6000);
        System.out.println("Eligible for Bonus:"+eligible);

        //Method Returning String
        String grade = getEmployeeGrade(850);
        System.out.println("Employee Grade:"+grade);

        //Array Passeed to Method
        int[] salaries = {
                45000,
                52000,
                38000,
                60000,
                48000
        };
        double averageSalary = calculateAverageSalary(salaries);
        System.out.println("Average Salary:"+averageSalary);

        //Find Maximum Using a Method

        int highestSalary = findHighestSalary(salaries);
        System.out.println("Highest Salary:"+highestSalary);

        //Linear Search DSA Connection

        int targetSalary = 600000;
        int index = findSalary(salaries,targetSalary);
        if(index!=-1){
            System.out.println("Salary found at Index: "+index);
        }else{
            System.out.println("Salary not found");
        }

        //Method Overloading
        System.out.println("\n=======Method Overloading===");

        System.out.println("Bonus " + calculateBonus(50000));

        System.out.println("Bonus " + calculateBonus(50000,10));

        System.out.println("Bonus " + calculateBonus(50000,10,5000));

        //Varargs

        System.out.println("\n=== Varargs ===");

        int total = calculateTotal(1000, 2000, 3000, 4000);

        System.out.println("Total: " + total);

        //Instance Method
        System.out.println("\n====Instance Method");
        MethodsDemo company = new MethodsDemo("Athenahealth");
        company.displayCompany();

        //Instance Method With Parameter
        company.displayEmployeeCompany("Praveen");

        // ============================================
        // 13. PASS-BY-VALUE - PRIMITIVE
        // ============================================

        System.out.println("\n=== Pass By Value ===");

        int employeeSalary = 50000;

        System.out.println(
                "Before method call: " + employeeSalary
        );

        changeSalary(employeeSalary);

        System.out.println(
                "After method call: " + employeeSalary
        );


        // ============================================
        // 14. METHOD CALLING ANOTHER METHOD
        // ============================================

        System.out.println("\n=== Method Chaining ===");

        double finalSalary = calculateFinalSalary(
                50000,
                5000,
                3000
        );

        System.out.println(
                "Final Salary: " + finalSalary
        );
    }

    // ================================================
    // METHOD 1
    // void method
    // ================================================

    public static void printWelcomeMessage(){
        System.out.println("Welcome to Employee Management System");
    };

    // ================================================
    // METHOD 2
    // Parameters
    // ================================================

    public static void displayEmployee(String name, int employeeId){
        System.out.println("EmployeeID :"+employeeId);
        System.out.println("Employee Name:"+name);
    }
    // ================================================
    // METHOD 3
    // Return value
    // ================================================
    public static double calculateSalary(double basicSalary,double allowance){
        return basicSalary + allowance;
    }

    // ================================================
    // METHOD 4
    // Boolean return
    // ================================================

    public static boolean isElgibleForBonus(double salary){
        return salary>=50000;
    }

    // ================================================
    // METHOD 5
    // String return
    // ================================================

    public static String getEmployeeGrade(int performanceScore){
        if(performanceScore>=900){
            return "A+";
        }else if(performanceScore>=800){
            return "A";
        }else if(performanceScore>=700){
            return "B";
        }else{
            return "C";
        }
    }

    // ================================================
    // METHOD 6
    // Array parameter
    // ================================================

    public static double calculateAverageSalary(int[] salaries){
        int total =0;
        for(int salary : salaries){
            total+=salary;
        }
        return (double)total / salaries.length;
    }

    // ================================================
    // METHOD 7
    // Find maximum
    // DSA pattern
    // ================================================

    public static int findHighestSalary(
            int[] salaries
    ) {

        int highest = salaries[0];

        for (int i = 1; i < salaries.length; i++) {

            if (salaries[i] > highest) {
                highest = salaries[i];
            }
        }

        return highest;
    }

    // ================================================
    // METHOD 8
    // Linear Search
    // DSA
    // ================================================

    public static int findSalary(
            int[] salaries,
            int target
    ) {

        for (int i = 0; i < salaries.length; i++) {

            if (salaries[i] == target) {
                return i;
            }
        }

        return -1;
    }


    // ================================================
    // METHOD OVERLOADING
    // ================================================

    public static double calculateBonus(
            double salary
    ) {

        return salary * 0.05;
    }


    public static double calculateBonus(
            double salary,
            double bonusPercentage
    ) {

        return salary * bonusPercentage / 100;
    }


    public static double calculateBonus(
            double salary,
            double bonusPercentage,
            double additionalBonus
    ) {

        return (salary * bonusPercentage / 100)
                + additionalBonus;
    }


    // ================================================
    // VARARGS
    // ================================================

    public static int calculateTotal(
            int... amounts
    ) {

        int total = 0;

        for (int amount : amounts) {
            total += amount;
        }

        return total;
    }

    // INSTANCE METHOD
    // ================================================

    public void displayCompany() {

        System.out.println(
                "Company: " + companyName
        );
    }

    // ================================================
    // INSTANCE METHOD WITH PARAMETER
    // ================================================

    public void displayEmployeeCompany(
            String employeeName
    ) {

        System.out.println(
                employeeName +
                        " works at " +
                        companyName
        );
    }


    // ================================================
    // PASS-BY-VALUE
    // ================================================

    public static void changeSalary(
            int salary
    ) {

        salary = 100000;

        System.out.println(
                "Inside method: " + salary
        );
    }

    // ================================================
    // METHOD CALLING OTHER METHODS
    // ================================================

    public static double calculateFinalSalary(
            double basicSalary,
            double allowance,
            double bonus
    ) {

        double salary = calculateSalary(
                basicSalary,
                allowance
        );

        return salary + bonus;
    }




}
