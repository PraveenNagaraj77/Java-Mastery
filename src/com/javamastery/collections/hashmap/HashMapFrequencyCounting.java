package com.javamastery.collections.hashmap;

import java.util.HashMap;
import java.util.Map;

public class HashMapFrequencyCounting {

    public static void main(String[] args) {

        int[] numbers = {1, 2, 2, 3, 1, 2, 4};

        Map<Integer, Integer> frequency = new HashMap<>();

        for (int number : numbers) {
            frequency.put(
                    number,
                    frequency.getOrDefault(number, 0) + 1
            );
        }

        System.out.println(frequency);
    }
}