package com.javamastery.collections.collectionPrograms;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
    public static void main(String[] args) {

        int[] numbers = {100, 4, 200, 1, 3, 2};

        Set<Integer> numbersSet = new HashSet<>();

        for (int num : numbers) {
            numbersSet.add(num);
        }

        int longest = 0;

        for (int num : numbersSet) {

            // Start only if this is the beginning of a sequence
            if (!numbersSet.contains(num - 1)) {

                int current = num;
                int length = 1;

                while (numbersSet.contains(current + 1)) {
                    current++;
                    length++;
                }

                longest = Math.max(longest, length);
            }
        }

        System.out.println("Longest Consecutive Sequence: " + longest);
    }
}