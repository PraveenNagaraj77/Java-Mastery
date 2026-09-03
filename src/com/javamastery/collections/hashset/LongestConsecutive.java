package com.javamastery.collections.hashset;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutive {

    public static void main(String[] args) {

        int[] numbers = {100, 4, 200, 1, 3, 2};

        Set<Integer> numbersSet = new HashSet<>();

        // Add all numbers to HashSet
        for (int number : numbers) {
            numbersSet.add(number);
        }

        int longestSequence = 0;

        // Check every number
        for (int number : numbersSet) {

            // Start only if number-1 does not exist
            if (!numbersSet.contains(number - 1)) {

                int currentNumber = number;
                int currentSequence = 1;

                // Find consecutive numbers
                while (numbersSet.contains(currentNumber + 1)) {
                    currentNumber++;
                    currentSequence++;
                }

                // Update longest sequence
                if (currentSequence > longestSequence) {
                    longestSequence = currentSequence;
                }
            }
        }

        System.out.println("Longest Consecutive Sequence Length: " + longestSequence);
    }
}