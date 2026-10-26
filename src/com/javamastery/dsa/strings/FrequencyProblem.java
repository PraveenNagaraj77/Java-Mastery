package com.javamastery.dsa.strings;

import java.util.HashMap;
import java.util.Map;

public class FrequencyProblem {
    public static Map<Integer,Integer> countFrequencies(int[] arr){
        Map<Integer,Integer> frequency = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            frequency.put(arr[i],frequency.getOrDefault(arr[i],0)+1);
        }
        return frequency;
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 1, 2};
        System.out.println(countFrequencies(arr));
    }
}
