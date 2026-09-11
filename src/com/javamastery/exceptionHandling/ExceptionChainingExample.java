package com.javamastery.exceptionHandling;

public class ExceptionChainingExample {
    public static void main(String[] args) {
        try{
            throw  new IllegalArgumentException("Invalid Age");
        } catch (IllegalArgumentException e) {
            RuntimeException newException = new RuntimeException("Validation Failed",e);
            System.out.println(newException.getMessage());
            System.out.println(newException.getCause().getMessage());
        }
    }
}
