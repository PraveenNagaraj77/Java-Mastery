package com.javamastery.collections.collectionPrograms;

import java.util.HashSet;
import java.util.Set;

public class FindingTheMissingNumber {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 5, 6};

        Set<Integer> numberSet = new HashSet<>();

        for (int num : numbers){
            numberSet.add(num);
        }

        for (int i = 1; i <=6; i++) {
            if(!numberSet.contains(i)){
                System.out.println(i);
                break;
            }
        }


    }
}
