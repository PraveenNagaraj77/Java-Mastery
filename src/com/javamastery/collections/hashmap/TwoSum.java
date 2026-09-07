package com.javamastery.collections.hashmap;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        int[] numbers = {2, 7, 11, 15};
        int target = 9;

        Map<Integer, Integer> seen = new HashMap<>();

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

                break;
            }
            seen.put(current,i);

        }



    }
}
