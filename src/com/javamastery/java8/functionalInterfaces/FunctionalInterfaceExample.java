package com.javamastery.java8.functionalInterfaces;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaceExample {
    public static void main(String[] args) {
        Predicate<Integer> isGreaterThan50 = number->number>50;
        System.out.println(isGreaterThan50.test(75));
        System.out.println(isGreaterThan50.test(30));

        Function<Integer,String> formatPrice = price->"$"+price;

        System.out.println(formatPrice.apply(999));

        Consumer<String> printUserName = username-> System.out.println("Username : " + username);
        printUserName.accept("Praveen");

        Supplier<String> welcomeMessage = ()->"Welcome to Java Functional Interfaces";
        System.out.println(welcomeMessage.get());

    }
}
