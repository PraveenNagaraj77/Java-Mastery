package com.javamastery.java8.functionalInterfaces;

import java.util.function.Function;

public class FunctionExample {
    public static void main(String[] args) {
        Function<String,Integer> getLength = text->text.length();
        System.out.println(getLength.apply("Java"));
        System.out.println(getLength.apply("Spring Boot"));
    }
}
