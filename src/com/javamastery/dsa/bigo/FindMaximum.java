package com.javamastery.dsa.bigo;

public class FindMaximum {
    public static int findMax(int[] nums){
         int largest = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if(nums[i]>largest){
                largest = nums[i];
            }
        }
        return largest;
    };

    public static void main(String[] args){
        int[] nums = {4,9,2,15,7};
        System.out.println(findMax(nums));
    }
}
