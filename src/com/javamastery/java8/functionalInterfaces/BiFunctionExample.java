package com.javamastery.java8.functionalInterfaces;

import java.util.function.BiFunction;

public class BiFunctionExample {
    public static void main(String[] args) {
        BiFunction<Integer,Integer,Integer> addition = (a,b)->a+b;
        System.out.println(addition.apply(20,5));
    }
}
