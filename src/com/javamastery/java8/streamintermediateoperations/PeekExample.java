package com.javamastery.java8.streamintermediateoperations;

import java.util.Arrays;
import java.util.List;

public class PeekExample {
    public static void main(String[] args) {
        List<Integer> numbers =
                Arrays.asList(10, 20, 30, 40, 50);

        numbers.stream()
                .peek(number-> System.out.println("Before Filter : " + number))
                .filter(number -> number > 20)
                .peek(number ->
                        System.out.println("After filter: " + number)
                )
                .forEach(System.out::println);


        numbers.stream()
                .peek(number ->
                        System.out.println("Before map: " + number)
                )
                .map(number -> number * 10)
                .peek(number ->
                        System.out.println("After map: " + number)
                )
                .forEach(System.out::println);



    }
}
