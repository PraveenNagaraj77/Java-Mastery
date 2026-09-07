package com.javamastery.collections.linkedHashMap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapExample {
    public static void main(String[] args) {
        int[] numbers = {5, 2, 8, 5, 2, 9, 8};

        LinkedHashMap<Integer,Integer> frequency = new LinkedHashMap();

        for (int number : numbers){
            frequency.put(number,frequency.getOrDefault(number,0)+1);
        }
        System.out.println(frequency);

    }
}
