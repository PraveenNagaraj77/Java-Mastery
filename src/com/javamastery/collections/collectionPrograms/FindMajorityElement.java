package com.javamastery.collections.collectionPrograms;

import java.util.HashMap;
import java.util.Map;

public class FindMajorityElement {
    public static void main(String[] args) {
        int[] numbers = {2, 2, 1, 1, 1, 2, 2};
        Map<Integer, Integer> frequency = new HashMap<>();

        for (int number : numbers){
            frequency.put(number,frequency.getOrDefault(number,0)+1);
        }
        int count = numbers.length/2;

        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            if (entry.getValue()>count){
                System.out.println(entry.getKey());
                break;
            }
        }

    }
}
