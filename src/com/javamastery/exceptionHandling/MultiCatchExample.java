package com.javamastery.exceptionHandling;

public class MultiCatchExample {
    public static void main(String[] args) {
        try{
            String str = new String("100");
            int number = Integer.parseInt(str);
            int result = number/0;
            System.out.println(result);
        } catch (NumberFormatException | ArithmeticException e) {
            System.out.println("Invalid Input");
        }
    }
}
