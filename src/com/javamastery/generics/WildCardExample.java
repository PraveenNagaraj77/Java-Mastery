package com.javamastery.generics;

import java.util.ArrayList;
import java.util.List;

public class WildCardExample {
    static void printList(List<?> list){
        for (Object value : list){
            System.out.println(value);
        }
    }

    static double calculateSum(List<? extends  Number> numbers){
        double total =0;
        for ( Number number : numbers){
            total += number.doubleValue();
        }
        return  total;
    }

    static void addNumbers(List<? super Integer> numbers){
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
    }

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        List<Number> numbers = new ArrayList<>();
        List<Object> objects = new ArrayList<>();

        addNumbers(integers);
        addNumbers(numbers);
        addNumbers(objects);

        System.out.println(integers);
        System.out.println(numbers);
        System.out.println(objects);

    }

}
