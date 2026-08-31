package com.javamastery.controlflow;

public class LoopsDemo {
    public static void main(String[] args) {
        // ============================================
        // 1. FOR LOOP
        // Real-world: Process employee IDs
        // ============================================

        System.out.println("Employee Ids");

        for(int employeeId = 1001; employeeId<=1005;employeeId++){
            System.out.println("Processing Employee ID :"+employeeId);
        }

        // ============================================
        // 2. ARRAY TRAVERSAL
        // Real-world: Process employee salaries
        // ============================================

        System.out.println("\n=== Employee Salaries");

        double[] salaries = {45000,52000,38000,60000,48000};

        for (int i =0;i<salaries.length;i++){
            System.out.println("Employee" + (i+1) + "Salary" + salaries[i]);
        }

        // ============================================
        // 3. TOTAL SALARY
        // Real-world: Calculate total payroll
        // ============================================

        double totalSalary=0;
        for(int i=0;i<salaries.length;i++){
            totalSalary+=salaries[i];
        }
        System.out.println("\nTotal Payroll:"+totalSalary);

        // ============================================
        // 4. FIND HIGHEST SALARY
        // Real-world: Highest paid employee
        // ============================================

        double highestSalary = salaries[0];
        for(int i=0;i<salaries.length;i++){
            if(salaries[i]>highestSalary){
                highestSalary = salaries[i];
            }
        }
        System.out.println("Highest Salary:"+highestSalary);

        // ============================================
        // 5. ENHANCED FOR LOOP
        // Real-world: Display departments
        // ============================================

        System.out.println("\n===Departments==");
        String[] departments = {
                "Engineering",
                "HR",
                "Finance",
                "Marketing",
                "Sales"
        };
        for (String department : departments){
            System.out.println("Department:" + department   );
        }

        // ============================================
        // 6. CONTINUE
        // Real-world: Skip inactive employees
        // ============================================

        System.out.println("\n======Active Employees=====");
        boolean[] activeEmployees = {
                true,
                false,
                true,
                true,
                false
        };

        for (int i=0;i<activeEmployees.length;i++){
            if(!activeEmployees[i]){
                continue;
            }
            System.out.println("Employee " + (i+1)+"is Active");
        }

        // ============================================
        // 7. BREAK
        // Real-world: Search employee
        // ============================================

        System.out.println("\n===Employee Search==");
        int[] employeesIds = {
                1001,1002,1003,1004,1005
        };

        int targetEmployeeId = 1003;
        for(int i=0;i<employeesIds.length;i++){
            if(employeesIds[i]==targetEmployeeId){
                System.out.println("Employee found at index " + i + "Value" + employeesIds[i]);
                break;
            }
        }

        // ============================================
        // 9. DO-WHILE LOOP
        // Real-world: Application menu
        // ============================================

        System.out.println("\n==Application Menu===");
        int option =1;

        do{
            System.out.println("Showing application menu");
            System.out.println("Select an option:"+option);
            option++;
        }while (option<=3);

        // ============================================
        // 10. REVERSE LOOP
        // Real-world: Latest transactions first
        // ============================================

        System.out.println("\n=======Recent Transactions===");

        double[] transactions = {
                500,
                1200,
                300,
                800,
                1500
        };

        for (int i = transactions.length-1;i>=0;i--){
            System.out.println("Transaction:"+transactions[i]);
        }

        // ============================================
        // 11. NESTED LOOP
        // Real-world: Departments and employees
        // ============================================

        System.out.println("\n Department Employees=====");

        String[][] departmentEmployees = {
                {"John", "David"},
                {"Alice", "Sarah"},
                {"Michael", "Robert"}
        };

        for(int department =0;department<departmentEmployees.length;department++){
            System.out.println(
                    "Department " + (department + 1)
            );
            for (int employee=0;employee<departmentEmployees[department].length;employee++){
                System.out.println(departmentEmployees[department][employee]);
            }
        }
        // ============================================
        // 12. DSA: LINEAR SEARCH
        // ============================================

        System.out.println("\n=======Liner Search=== ");
        int target = 1004;
        int foundIndex = -1;

        for(int i =0;i<employeesIds.length;i++){
            if(employeesIds[i]==target){
                foundIndex = i;
                break;
            }
        }
        if(foundIndex!=-1){
            System.out.println("Employee found at index " + foundIndex);
        }else{
            System.out.println("Employee not found");
        }

        // ============================================
        // 13. DSA: REVERSE A NUMBER
        // ============================================

        System.out.println("\n ==========Reverse a Number");
        int number = 12345;
        int reveresed = 0;

        while(number!=0){
            int digit = number%10;
            reveresed= reveresed*10+digit;
            number/=10;
        }
        System.out.println("Reversed number:"+reveresed);


        // ============================================
        // 14. DSA: SUM OF DIGITS
        // ============================================


        System.out.println("\n Sum of Digits=======");
        int value = 9876;
        int digitSum = 0;
        while(value != 0){
            digitSum+=value%10;
            value/=10;
        }
        System.out.println("Digit Sum:"+digitSum);

        // ============================================
        // 15. DSA: EVEN / ODD COUNT
        // ============================================

        System.out.println("\n=== Even / Odd Count ===");

        int[] numbers = {10, 15, 22, 31, 40, 55};

        int evenCount = 0;
        int oddCount = 0;

        for (int numberValue : numbers) {

            if (numberValue % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }

        System.out.println("Even Numbers: " + evenCount);
        System.out.println("Odd Numbers: " + oddCount);

    }
}
