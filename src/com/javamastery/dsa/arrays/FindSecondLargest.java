
package com.javamastery.dsa.arrays;

public class FindSecondLargest {

    public static int secondLargest(int[] arr) {
        if (arr == null || arr.length < 2) {
            throw new IllegalArgumentException(
                    "Array must contain at least two distinct elements."
            );
        }

        int largest = arr[0];
        int secondLargest = 0;
        boolean foundSecond = false;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > largest) {
                secondLargest = largest;
                largest = arr[i];
                foundSecond = true;
            } else if (arr[i] < largest &&
                    (!foundSecond || arr[i] > secondLargest)) {
                secondLargest = arr[i];
                foundSecond = true;
            }
        }

        if (!foundSecond) {
            throw new IllegalArgumentException(
                    "No second-largest distinct element exists."
            );
        }

        return secondLargest;
    }

    public static void main(String[] args) {
        int[] arr = {12, 35, 1, 10, 34, 1};
        System.out.println(secondLargest(arr));
    }
}
