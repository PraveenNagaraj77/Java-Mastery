package com.javamastery.generics;

public class BoundedGenericExample {

    static <T extends Number> double convertToDouble(T value) {
        return value.doubleValue();
    }

    public static void main(String[] args) {

        System.out.println(convertToDouble(100));
        System.out.println(convertToDouble(25.5));
        System.out.println(convertToDouble(500L));

        // This will not compile because String does not extend Number
        // System.out.println(convertToDouble("100"));
    }
}