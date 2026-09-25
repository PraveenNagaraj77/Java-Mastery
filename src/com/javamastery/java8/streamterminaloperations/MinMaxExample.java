package com.javamastery.java8.streamterminaloperations;

import java.util.List;
import java.util.Optional;

public class MinMaxExample {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(10, 30, 20, 50, 40);

        Optional<Integer> min = numbers.stream()
                .min(Integer::compareTo);
        Optional<Integer> max = numbers.stream()
                .max(Integer::compareTo);


//        System.out.println(min.get());
//        System.out.println(max.get());


        System.out.println(min.orElse(0));
        System.out.println(max.orElse(0));


    }
}
