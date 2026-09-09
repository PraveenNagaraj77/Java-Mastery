package com.javamastery.collections.collectionPrograms;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        int[] numbers = {2, 7, 11, 15};
        int target = 9;

        Map<Integer,Integer> numbersMap = new HashMap<>();
        int current = 0;
        int index =0;
        for (int num : numbers){
            current = target-num;
            if(numbersMap.containsKey(current)){
                System.out.println(numbersMap.get(current) + ", " + index);
                break;
            }else{
                numbersMap.put(num, index);
            }
            index++;
        }

    }
}
