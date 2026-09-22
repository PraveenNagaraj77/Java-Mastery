package com.javamastery.java8.functionalInterfaces;

import java.util.function.Consumer;

public class ConsumeExample {
    public static void main(String[] args) {
        Consumer<String> printMessage = message-> System.out.println(message);

        printMessage.accept("Hello Java");
    }
}
