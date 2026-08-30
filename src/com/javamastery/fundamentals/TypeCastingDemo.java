package com.javamastery.fundamentals;

public class TypeCastingDemo {

    public static void main(String[] args) {

        int salary = 45000;
        int age = 26;
        byte birthday = 21;
        long population = 800000000L;
        double weight = 82.45;
        char gender = 'M';


        // =====================================================
        // WIDENING CONVERSION
        // =====================================================

        long longNumber = salary;
        float floatNumber = salary;
        double doubleNumber = salary;

        System.out.println("=== Widening Conversion ===");

        System.out.println("int to long: " + longNumber);
        System.out.println("int to float: " + floatNumber);
        System.out.println("int to double: " + doubleNumber);


        // =====================================================
        // NARROWING CONVERSION
        // =====================================================

        System.out.println("\n=== Narrowing Conversion ===");

        int updatedWeight = (int) weight;

        System.out.println("double to int: " + updatedWeight);


        // int -> byte

        byte updatedAge = (byte) age;

        System.out.println("int to byte: " + updatedAge);


        // =====================================================
        // CHAR CONVERSION
        // =====================================================

        System.out.println("\n=== Char Conversion ===");

        int newGender = gender;

        System.out.println("char to int: " + newGender);


        // =====================================================
        // NUMERIC PROMOTION
        // =====================================================

        System.out.println("\n=== Numeric Promotion ===");

        int birthdaySum = birthday + birthday;

        System.out.println("byte + byte: " + birthdaySum);


        // Explicitly converting int result back to byte

        byte count = (byte) birthdaySum;

        System.out.println("int to byte: " + count);


        // =====================================================
        // CONSTANT EXPRESSION
        // =====================================================

        System.out.println("\n=== Constant Expression ===");

        byte constantResult = 10 + 20;

        System.out.println("10 + 20 assigned to byte: " + constantResult);


        // =====================================================
        // LONG TO FLOAT
        // =====================================================

        System.out.println("\n=== Long to Float ===");

        float newPopulation = (float) population;

        System.out.println("long to float: " + newPopulation);


        // =====================================================
        // NEGATIVE DOUBLE TO INT
        // =====================================================

        System.out.println("\n=== Truncation ===");

        double decimalValue = -82.45;

        int convertedValue = (int) decimalValue;

        System.out.println("Original value: " + decimalValue);
        System.out.println("Converted value: " + convertedValue);
    }
}