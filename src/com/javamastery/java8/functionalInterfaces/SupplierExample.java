package com.javamastery.java8.functionalInterfaces;

import java.util.function.Supplier;

public class SupplierExample {
    public static void main(String[] args) {
        Supplier<String> messageSupplier = ()-> "Welcome to Java";

        System.out.println(messageSupplier.get());
    }
}
