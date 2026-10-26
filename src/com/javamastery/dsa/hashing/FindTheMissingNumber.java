
package com.javamastery.dsa.hashing;

public class FindTheMissingNumber {

    public static int findMissing(int[] arr) {
        int n = arr.length;

        // Sum of numbers from 0 to n
        int expectedSum = n * (n + 1) / 2;

        // Sum of the numbers present in the array
        int actualSum = 0;

        for (int i = 0; i < arr.length; i++) {
            actualSum += arr[i];
        }

        return expectedSum - actualSum;
    }

    public static void main(String[] args) {
        int[] arr = {9, 6, 4, 2, 3, 5, 7, 0, 1};

        System.out.println(findMissing(arr));
    }
}
