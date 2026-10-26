package com.javamastery.dsa.arrays;

public class FindTheMissingNumber {

    public static int missingNumber(int[] arr) {
        int n = arr.length;
        int actualSum = 0;
        int expectedSum = n*(n+1)/2;
        for (int i = 0; i < n; i++) {
            actualSum += arr[i];
        }
        return expectedSum - actualSum;
    }
    public static void main(String[] args) {
        int[] arr = {3,0,1};
        System.out.println(missingNumber(arr));
    }
}
