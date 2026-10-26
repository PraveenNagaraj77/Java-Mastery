package com.javamastery.dsa.hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSumUsingHashMap {
    public static int[] calculateTwoSum(int[] arr,int target){
        Map<Integer,Integer> numbersMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];
            if(numbersMap.containsKey(complement)){
                return new int[]{numbersMap.get(complement),i};
            }
            numbersMap.put(arr[i],i);
        }
        return new int[]{};
    }
    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(calculateTwoSum(arr,target)));
    }
}
