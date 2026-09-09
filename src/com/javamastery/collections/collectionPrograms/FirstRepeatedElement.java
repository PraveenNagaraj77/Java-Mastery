package com.javamastery.collections.collectionPrograms;


import java.util.HashSet;
import java.util.Set;

public class FirstRepeatedElement {
    public static void main(String[] args) {
        int[] numbers = {10, 20, 30, 20, 40, 30};

        Set<Integer> numberSet = new HashSet<>();
        for (int no : numbers){
            if(!numberSet.add(no)){
                System.out.println("First Repeated No : " + no);
                break;
            }
        }

    }
}
