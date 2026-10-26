
package com.javamastery.dsa.hashing;

import java.util.HashMap;
import java.util.Map;

public class CheckTwoArraysAreEqual {

    public static boolean areArrayEqual(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }

        Map<Integer, Integer> frequency = new HashMap<>();

        // Count elements in the first array
        for (int value : arr1) {
            frequency.put(
                    value,
                    frequency.getOrDefault(value, 0) + 1
            );
        }

        // Subtract frequencies using the second array
        for (int value : arr2) {
            int count = frequency.getOrDefault(value, 0);

            if (count == 0) {
                return false;
            }

            frequency.put(value, count - 1);
        }

        // All frequencies must match
        for (int count : frequency.values()) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 2, 3};
        int[] arr2 = {3, 2, 1, 2};

        System.out.println(areArrayEqual(arr1, arr2));
    }
}
