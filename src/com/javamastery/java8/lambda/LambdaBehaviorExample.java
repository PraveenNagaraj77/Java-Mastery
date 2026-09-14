package com.javamastery.java8.lambda;

public class LambdaBehaviorExample {

    public static int calculate(int a, int b, Calculator calculator) {
        return calculator.calculate(a, b);
    }

    public static void main(String[] args) {
        Calculator addition = (a, b) -> a + b;
        Calculator subtraction = (a, b) -> a - b;
        Calculator multiplication = (a, b) -> a * b;
        Calculator division = (a, b) -> a / b;
        Calculator maximum = (a, b) -> a > b ? a : b;

        int additionResult = calculate(20, 5, addition);
        System.out.println("Addition: " + additionResult);

        int subtractionResult = calculate(20, 5, subtraction);
        System.out.println("Subtraction: " + subtractionResult);

        int multiplicationResult = calculate(20,5,multiplication);
        System.out.println("Multiplication : " + multiplicationResult);

        int divisionResult = calculate(20,5,division);
        System.out.println("Division : " + divisionResult);

        int maximumResult = calculate(20,5,maximum);
        System.out.println("Maximum : " + maximumResult);



    }
}