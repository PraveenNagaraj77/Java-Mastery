package com.javamastery.collections.collectionPrograms;

import java.util.HashSet;
import java.util.Set;

public class FindMissingPositive {
    public static void main(String[] args) {
        int[] numbers = {3, 4, -1, 1};

        Set<Integer> numberSet = new HashSet<>();

        for (int num : numbers){
            numberSet.add(num);
        }

        for (int i = 1; i <=numbers.length+1; i++) {
            if(!numberSet.contains(i)){
                System.out.println(i);
                break;
            }
        }


    }
}
