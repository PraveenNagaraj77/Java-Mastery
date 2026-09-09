package com.javamastery.collections.collectionPrograms;

import java.util.HashMap;
import java.util.Map;

public class CountPairsWithTargetSum {
    public static void main(String[] args) {
        int[] numbers = {1, 5, 7, -1, 5};
        int target = 6;

        int complement;
        int count = 0;

        Map<Integer, Integer> frequency = new HashMap<>();

        for (int num : numbers) {
            complement = target - num;

            count += frequency.getOrDefault(complement, 0);

            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }

        System.out.println("Number of pairs: " + count);
    }
}