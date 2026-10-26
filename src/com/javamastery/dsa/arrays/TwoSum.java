package com.javamastery.dsa.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static int[] calculateTwoSum(int[] arr,int target){
        Map<Integer,Integer> sumMap = new HashMap<>();

        for (int i = 0; i < arr.length-1; i++) {
            int needed = target-arr[i];
            if (sumMap.containsKey(needed)){
                return new int[] {sumMap.get(needed),i};
            }
            sumMap.put(arr[i],i);
        }
        throw new IllegalArgumentException("No Valid pair found");
    }
    public static void main(String[] args) {
        int[] arr = {2,7,11,15};
        int target = 9;
        int[] result = calculateTwoSum(arr,target);
        System.out.println(Arrays.toString(result
        ));
    }
}
