package com.javamastery.exceptionHandling;

public class ExceptionBasic {
    public static void main(String[] args) {

        int a = 20;
        int b = 0;

        try {
            int result = a/b;
            System.out.println(result);
        }catch (ArithmeticException e){
            System.out.println(e);
        }

        System.out.println("//Program Contiues");
    }
}
