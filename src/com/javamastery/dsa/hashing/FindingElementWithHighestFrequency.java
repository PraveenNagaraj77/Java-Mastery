package com.javamastery.dsa.hashing;

import java.util.HashMap;
import java.util.Map;

public class FindingElementWithHighestFrequency {
    public static Integer findElement(int[] arr){

        Map<Integer,Integer> frequency = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            frequency.put(arr[i],frequency.getOrDefault(arr[i],0)+1);
        }
        int maxFrequency = 0;
        Integer maxElement = null;

        for (Map.Entry<Integer,Integer> entry : frequency.entrySet()){
           if(entry.getValue()>maxFrequency){
               maxFrequency = entry.getValue();
               maxElement = entry.getValue();
           }
        }
        return maxElement;
    }
    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 3, 4, 3, 2};
        System.out.println(findElement(arr));
    }
}
