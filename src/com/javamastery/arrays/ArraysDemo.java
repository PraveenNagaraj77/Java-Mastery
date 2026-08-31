package com.javamastery.arrays;

public class ArraysDemo {
    public static void main(String[] args) {
        // =====================================================
        // 1. ARRAY DECLARATION AND INITIALIZATION
        // =====================================================

        int[] employeeSalaries = {
                45000,
                52000,
                38000,
                60000,
                48000
        };

        System.out.println("======Employee Salaries======");

        for (int salary : employeeSalaries){
            System.out.println(salary);
        }


        // =====================================================
        // 2. ACCESSING ELEMENTS USING INDEX
        // =====================================================
        System.out.println("\n=== Accessing Elements ===");

        System.out.println(
                "First Salary: " + employeeSalaries[0]
        );

        System.out.println(
                "Third Salary: " + employeeSalaries[2]
        );

        System.out.println(
                "Last Salary: " + employeeSalaries[employeeSalaries.length-1]
        );


        // =====================================================
        // 3. UPDATING AN ELEMENT
        // =====================================================
        System.out.println("\n=== Updating Salary ===");

        System.out.println(
                "Before Update: " + employeeSalaries[2]
        );

        employeeSalaries[2] = 42000;
        System.out.println(
                "After Update: " + employeeSalaries[2]
        );


        // =====================================================
        // 4. ARRAY LENGTH
        // =====================================================

        System.out.println("\n=== Array Length ===");

        System.out.println("Number of Employees : " + employeeSalaries.length);

        // =====================================================
        // 5. TRADITIONAL FOR LOOP
        // =====================================================

        System.out.println("\n=== Traditional For Loop ===");

        for (int i = 0; i < employeeSalaries.length; i++) {
            System.out.println("Index : " + i + " " +"Salary: " + employeeSalaries[i]);
        }

        // =====================================================
        // 6. ENHANCED FOR LOOP
        // =====================================================

        System.out.println("\n=== Enhanced For Loop ===");

        for (int salary : employeeSalaries) {

            System.out.println(
                    "Salary: " + salary
            );
        }

        // =====================================================
        // 7. FIND TOTAL SALARY
        // =====================================================


        int totalSalary = calculateTotalSalary(employeeSalaries);
        System.out.println("\nTotal Salary: " + totalSalary);

        // =====================================================
        // 8. FIND AVERAGE SALARY
        // =====================================================

        double averageSalary = calculateAverageSalary(
                employeeSalaries
        );

        System.out.println(
                "Average Salary: " + averageSalary
        );

        // =====================================================
        // 9. FIND HIGHEST SALARY
        // =====================================================

        int highestSalary = findHighestSalary(
                employeeSalaries
        );

        System.out.println(
                "Highest Salary: " + highestSalary
        );



        // =====================================================
        // 10. FIND LOWEST SALARY
        // =====================================================

        int lowestSalary = findLowestSalary(
                employeeSalaries
        );

        System.out.println(
                "Lowest Salary: " + lowestSalary
        );

        // =====================================================
        // 11. LINEAR SEARCH
        // =====================================================

        int targetSalary = 60000;

        int salaryIndex = linearSearch(
                employeeSalaries,
                targetSalary
        );

        System.out.println(
                "\nSearching for salary: " + targetSalary
        );

        if (salaryIndex != -1) {

            System.out.println(
                    "Salary found at index: "
                            + salaryIndex
            );

        } else {

            System.out.println(
                    "Salary not found"
            );
        }

        // =====================================================
        // 12. COUNT OCCURRENCES
        // =====================================================

        int[] attendance = {
                1, 0, 1, 1, 0, 1, 1
        };

        int presentDays = countOccurrences(
                attendance,
                1
        );

        System.out.println(
                "\nEmployee Present Days: "
                        + presentDays
        );


        // =====================================================
        // 13. CHECK WHETHER ARRAY IS SORTED
        // =====================================================

        int[] sortedSalaries = {
                30000,
                35000,
                40000,
                45000,
                50000
        };

        boolean sorted = isSorted(
                sortedSalaries
        );

        System.out.println(
                "\nIs Salary Array Sorted? "
                        + sorted
        );

        // =====================================================
        // 14. REVERSE ARRAY
        // =====================================================

        int[] numbers = {
                10, 20, 30, 40, 50
        };

        System.out.println(
                "\nBefore Reverse:"
        );

        printArray(numbers);

        reverseArray(numbers);

        System.out.println(
                "After Reverse:"
        );

        printArray(numbers);


        // =====================================================
        // 15. ARRAY OF STRINGS
        // =====================================================

        String[] employeeNames = {
                "John",
                "David",
                "Sarah",
                "Michael"
        };

        System.out.println(
                "\n=== Employee Names ==="
        );

        printNames(employeeNames);







        // =====================================================
        // 17. TWO-DIMENSIONAL ARRAY
        // =====================================================

        int[][] monthlySales = {
                {10000, 12000, 15000},
                {8000, 11000, 13000},
                {15000, 16000, 18000}
        };

        System.out.println(
                "\n=== Monthly Sales ==="
        );

        printMatrix(monthlySales);


        // =====================================================
        // 18. JAGGED ARRAY
        // =====================================================

        int[][] employeeWorkingHours = {
                {8, 8, 7},
                {9, 8},
                {8, 7, 8, 9}
        };

        System.out.println(
                "\n=== Jagged Array ==="
        );

        printMatrix(employeeWorkingHours);
        // =====================================================
        // 19. ARRAY REFERENCE BEHAVIOR
        // =====================================================

        int[] original = {
                10, 20, 30
        };

        int[] reference = original;

        reference[0] = 100;

        System.out.println(
                "\n=== Array Reference ==="
        );

        System.out.println(
                "Original[0]: " + original[0]
        );

        System.out.println(
                "Reference[0]: " + reference[0]
        );
    }

    // =========================================================
    // METHOD 1 - TOTAL
    // =========================================================

    public static int calculateTotalSalary(int[] salaries){
        int total=0;
        for (int salary : salaries){
            total+=salary;
        }
        return total;
    }

    // =========================================================
    // METHOD 2 - AVERAGE
    // =========================================================
    public static double calculateAverageSalary(int[] salaries){
        int total = calculateTotalSalary(salaries);
        return (double) total / salaries.length;
    }

    // =========================================================
    // METHOD 3 - MAXIMUM
    // =========================================================

    public static int findHighestSalary(int[] salaries){
        int highest = salaries[0];

        for (int i = 1; i < salaries.length; i++) {

            if (salaries[i] > highest) {
                highest = salaries[i];
            }
        }

        return highest;
    }

    // =========================================================
    // METHOD 4 - MINIMUM
    // =========================================================

    public static int findLowestSalary(
            int[] salaries
    ) {

        int lowest = salaries[0];

        for (int i = 1; i < salaries.length; i++) {

            if (salaries[i] < lowest) {
                lowest = salaries[i];
            }
        }

        return lowest;
    }


    // =========================================================
    // METHOD 5 - LINEAR SEARCH
    // =========================================================

    public static int linearSearch(
            int[] numbers,
            int target
    ) {

        for (int i = 0; i < numbers.length; i++) {

            if (numbers[i] == target) {
                return i;
            }
        }

        return -1;
    }

    // =========================================================
    // METHOD 6 - COUNT OCCURRENCES
    // =========================================================

    public static int countOccurrences(
            int[] numbers,
            int target
    ) {

        int count = 0;

        for (int number : numbers) {

            if (number == target) {
                count++;
            }
        }

        return count;
    }

    // =========================================================
    // METHOD 7 - CHECK SORTED
    // =========================================================

    public static boolean isSorted(
            int[] numbers
    ) {

        for (int i = 1; i < numbers.length; i++) {

            if (numbers[i] < numbers[i - 1]) {
                return false;
            }
        }

        return true;
    }

    // =========================================================
    // METHOD 8 - REVERSE ARRAY
    // =========================================================

    public static void reverseArray(
            int[] numbers
    ) {

        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {

            int temp = numbers[left];

            numbers[left] = numbers[right];

            numbers[right] = temp;

            left++;
            right--;
        }
    }



    // =========================================================
    // METHOD 9 - PRINT ARRAY
    // =========================================================

    public static void printArray(
            int[] numbers
    ) {

        for (int number : numbers) {
            System.out.print(number + " ");
        }

        System.out.println();
    }

    // =========================================================
    // METHOD 10 - PRINT NAMES
    // =========================================================

    public static void printNames(
            String[] names
    ) {

        for (String name : names) {
            System.out.println(name);
        }
    }



    // =========================================================
    // METHOD 12 - PRINT 2D ARRAY
    // =========================================================

    public static void printMatrix(
            int[][] matrix
    ) {

        for (int row = 0; row < matrix.length; row++) {

            for (
                    int column = 0;
                    column < matrix[row].length;
                    column++
            ) {

                System.out.print(
                        matrix[row][column] + " "
                );
            }

            System.out.println();
        }
    }


    // =========================================================
    // METHOD 11 - PRINT EMPLOYEES
    // =========================================================

    public static void printEmployees(
            Employee[] employees
    ) {

        for (Employee employee : employees) {

            System.out.println(
                    "ID: " + employee.id
                            + ", Name: " + employee.name
                            + ", Salary: " + employee.salary
            );
        }
    }


    // =========================================================
    // EMPLOYEE CLASS
    // =========================================================

    static class Employee {

        int id;
        String name;
        double salary;

        Employee(
                int id,
                String name,
                double salary
        ) {

            this.id = id;
            this.name = name;
            this.salary = salary;
        }
    }

}

