package com.javamastery.dsa.arrays;

public class FindLargest {

    public static int findMax(int[] arr){
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if(arr[i]>max){
                max = arr[i];
            }
        }
        return max;
    }
    public static void main(String[] args) {
        int[] arr =  {-8, -3, -15, -2};
        System.out.println(findMax(arr));
    }
}
