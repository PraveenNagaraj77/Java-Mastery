package com.javamastery.collections.collectionPrograms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindIntersectionWithFrequency {
    public static void main(String[] args) {
        int[] numbers1 = {1, 2, 2, 3, 4, 5};
        int[] numbers2 = {2, 2, 3, 6};

        Map<Integer,Integer> frequency = new HashMap<>();

        for (int num : numbers2){
            frequency.put(num,frequency.getOrDefault(num,0)+1);
        }

        List<Integer> newList = new ArrayList<>();

        for (int no : numbers1)
        {
            if(frequency.getOrDefault(no,0)>0){
                newList.add(no);
                frequency.put(no,frequency.getOrDefault(no,0)-1);
            }

        }
        System.out.println(newList);
    }
}
