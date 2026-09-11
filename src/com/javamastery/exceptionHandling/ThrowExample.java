package com.javamastery.exceptionHandling;

public class ThrowExample {

    static void checkAge(int age){
        if(age<18){
            throw new IllegalArgumentException("Age Must be 18 or above");
        }else{
            System.out.println("Eligible");
        }
    }


    public static void main(String[] args) {
        try {
            checkAge(17);
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
