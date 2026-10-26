package com.javamastery.dsa.arrays;

public class LeftRotateByKPositions {
    public static void leftRotateByK(int[] arr,int k){
        if(arr== null || arr.length<=1){
            return;
        }
        k = k%arr.length;



        for (int rotation = 0; rotation < k ; rotation++) {
            int first = arr[0];
            for (int i = 0; i < arr.length-1; i++) {
                arr[i] = arr[i+1];
            }
            arr[arr.length-1] = first;
        }



    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        leftRotateByK(arr,k);
        for (int value : arr){
            System.out.print(value + " ");
        }
    }
}
