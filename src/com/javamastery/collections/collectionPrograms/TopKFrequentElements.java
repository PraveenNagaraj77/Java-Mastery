package com.javamastery.collections.collectionPrograms;

import java.util.*;

public class TopKFrequentElements {
    public static void main(String[] args) {

        int[] numbers = {1, 1, 1, 2, 2, 3};
        int k = 2;

        // Step 1: Count frequency
        Map<Integer, Integer> frequency = new HashMap<>();

        for (int number : numbers) {
            frequency.put(
                    number,
                    frequency.getOrDefault(number, 0) + 1
            );
        }

        // Step 2: Max Heap based on frequency
        Queue<Map.Entry<Integer, Integer>> queue =
                new PriorityQueue<>(
                        (a, b) -> Integer.compare(b.getValue(), a.getValue())
                );

        // Step 3: Add all entries
        queue.addAll(frequency.entrySet());

        // Step 4: Get top K
        List<Integer> result = new ArrayList<>();

        while (k > 0 && !queue.isEmpty()) {
            result.add(queue.poll().getKey());
            k--;
        }

        System.out.println(result);
    }
}