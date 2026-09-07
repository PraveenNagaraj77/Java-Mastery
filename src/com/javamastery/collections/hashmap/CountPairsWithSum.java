package com.javamastery.collections.hashmap;

import java.util.HashMap;
import java.util.Map;

public class CountPairsWithSum {
    public static void main(String[] args) {
        int[] numbers = {1, 5, 7, -1, 5};
        int target = 6;

        Map<Integer,Integer> seen = new HashMap<>();

        for (int i = 0; i < numbers.length; i++) {
            int current = numbers[i];
            int needed = target-current;

            if(seen.containsKey(needed)){
                System.out.println(
                        "Pair found: " + needed + " + " + current + " = " + target
                );

                System.out.println(
                        "Indices: [" + seen.get(needed) + ", " + i + "]"
                );
            }
            seen.put(current, seen.getOrDefault(current, 0) + 1);

        }
    }
}
