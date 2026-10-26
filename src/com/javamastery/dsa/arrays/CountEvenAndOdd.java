package com.javamastery.dsa.arrays;

public class CountEvenAndOdd {
    public static int[] evenOdd(int[] arr){
        int oddCount = 0 ;
        int evenCount = 0;

        for (int i = 0; i < arr.length; i++) {
            if(arr[i] % 2 == 0){
                evenCount++;
            }else {
                oddCount++;
            }
        }
        return new int[]{evenCount,oddCount};

    }
    public static void main(String[] args) {
        int[] arr = {12, 7, 25, 8, 18, 3};
        int[] counts = evenOdd(arr);

        System.out.println("Even Count : " + counts[0]);
        System.out.println("Odd Count : " + counts[1]);
    }
}
