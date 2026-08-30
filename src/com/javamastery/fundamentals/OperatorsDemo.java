package com.javamastery.fundamentals;

public class OperatorsDemo {
    public static void main(String[] args) {

        // =========================================================
        // 1. ARITHMETIC OPERATORS
        // =========================================================

        int a = 20;
        int b = 6;

        System.out.println("=== Arithmetic Operators ===");

        System.out.println("Addition: " + (a + b));
        System.out.println("Subtraction: " + (a - b));
        System.out.println("Multiplication: " + (a * b));
        System.out.println("Division: " + (a / b));
        System.out.println("Modulus: " + (a % b));

        // =========================================================
        // 2. INTEGER VS DECIMAL DIVISION
        // =========================================================

        System.out.println("\n=== Integer vs Decimal Division ===");

        System.out.println("Integer division: " + (10 / 3));
        System.out.println("Double division: " + (10 / 3.0));

        // =========================================================
        // 3. UNARY OPERATORS
        // =========================================================

        System.out.println("\n=== Unary Operators ===");

        int number = 10;

        System.out.println("Original number: " + number);
        System.out.println("Unary plus: " + (+number));
        System.out.println("Unary minus: " + (-number));

        number++;

        System.out.println("After increment: " + number);

        number--;

        System.out.println("After decrement: " + number);

        // =========================================================
        // 4. PREFIX AND POSTFIX
        // =========================================================

        System.out.println("\n=== Prefix vs Postfix ===");

        int x = 10;

        System.out.println("Postfix x++: " + x++);
        System.out.println("Value after x++: " + x);

        System.out.println("Prefix ++x: " + (++x));
        System.out.println("Value after ++x: " + x);

        // =========================================================
        // 5. RELATIONAL OPERATORS
        // =========================================================

        System.out.println("\n=== Relational Operators ===");

        int age = 26;

        System.out.println("age > 18: " + (age > 18));
        System.out.println("age < 18: " + (age < 18));
        System.out.println("age >= 26: " + (age >= 26));
        System.out.println("age <= 25: " + (age <= 25));
        System.out.println("age == 26: " + (age == 26));
        System.out.println("age != 30: " + (age != 30));


        // =========================================================
        // 6. LOGICAL OPERATORS
        // =========================================================

        System.out.println("\n=== Logical Operators ===");

        boolean hasExperience = true;
        boolean hasJavaKnowledge = true;
        boolean knowsPython = false;

        System.out.println(
                "Experience AND Java: "
                        + (hasExperience && hasJavaKnowledge)
        );

        System.out.println(
                "Experience OR Python: "
                        + (hasExperience || knowsPython)
        );

        System.out.println(
                "NOT Python knowledge: "
                        + (!knowsPython)
        );

        // =========================================================
        // 7. SHORT-CIRCUIT EVALUATION
        // =========================================================

        System.out.println("\n=== Short-Circuit Evaluation ===");

        int value = 10;

        boolean result1 = value > 5 && value < 20;

        boolean result2 = value < 5 || value == 10;

        System.out.println("AND result: " + result1);
        System.out.println("OR result: " + result2);


        // =========================================================
        // 8. ASSIGNMENT OPERATORS
        // =========================================================

        System.out.println("\n=== Assignment Operators ===");

        int score = 100;

        score += 10;
        System.out.println("After += : " + score);

        score -= 20;
        System.out.println("After -= : " + score);

        score *= 2;
        System.out.println("After *= : " + score);

        score /= 3;
        System.out.println("After /= : " + score);

        score %= 5;
        System.out.println("After %= : " + score);


        // =========================================================
        // 9. TERNARY OPERATOR
        // =========================================================

        System.out.println("\n=== Ternary Operator ===");

        int employeeAge = 26;

        String status =
                employeeAge >= 18
                        ? "Adult"
                        : "Minor";

        System.out.println("Employee status: " + status);

        // =========================================================
        // 10. MODULUS — DSA USE CASE
        // =========================================================

        System.out.println("\n=== Modulus — DSA Example ===");

        int n = 17;

        if (n % 2 == 0) {
            System.out.println(n + " is even");
        } else {
            System.out.println(n + " is odd");
        }

        // =========================================================
        // 11. BITWISE OPERATORS
        // =========================================================

        System.out.println("\n=== Bitwise Operators ===");

        int p = 5;   // 0101
        int q = 3;   // 0011

        System.out.println("p & q: " + (p & q));
        System.out.println("p | q: " + (p | q));
        System.out.println("p ^ q: " + (p ^ q));
        System.out.println("~p: " + (~p));


        // =========================================================
        // 12. SHIFT OPERATORS
        // =========================================================

        System.out.println("\n=== Shift Operators ===");

        int shiftNumber = 8;

        System.out.println("8 << 1: " + (shiftNumber << 1));
        System.out.println("8 >> 1: " + (shiftNumber >> 1));
        System.out.println("8 >>> 1: " + (shiftNumber >>> 1));


        // =========================================================
        // 13. OPERATOR PRECEDENCE
        // =========================================================

        System.out.println("\n=== Operator Precedence ===");

        int result = 10 + 5 * 2;

        System.out.println("10 + 5 * 2 = " + result);

        int resultWithParentheses = (10 + 5) * 2;

        System.out.println(
                "(10 + 5) * 2 = "
                        + resultWithParentheses
        );

        // =========================================================
        // 15. DIVISION BY ZERO
        // =========================================================

        System.out.println("\n=== Division by Zero ===");

        double infinity = 10.0 / 0.0;
        double notANumber = 0.0 / 0.0;

        System.out.println("10.0 / 0.0: " + infinity);
        System.out.println("0.0 / 0.0: " + notANumber);

        // int integerDivisionByZero = 10 / 0;
        // This causes ArithmeticException.


    }
}
