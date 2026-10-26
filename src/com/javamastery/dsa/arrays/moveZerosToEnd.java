package com.javamastery.dsa.arrays;

public class moveZerosToEnd {
    public static void moveZeros(int[] arr){
        int nonZeroIndex = 0;

        for (int i = 0; i < arr.length; i++) {
            if(arr[i]!=0){
                arr[nonZeroIndex] = arr[i];
                nonZeroIndex++;
            }
        }

        while (nonZeroIndex < arr.length) {
            arr[nonZeroIndex] = 0;
            nonZeroIndex++;
        }

    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 3, 12};
        moveZeros(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }


}
