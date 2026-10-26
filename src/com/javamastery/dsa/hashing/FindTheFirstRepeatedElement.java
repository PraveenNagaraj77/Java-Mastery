package com.javamastery.dsa.hashing;

import java.util.HashSet;
import java.util.Set;

public class FindTheFirstRepeatedElement {
    public static Integer findFirstRepeated(int[] arr){
        Set<Integer> seen = new HashSet<>();

        for (int value : arr){
            if(!seen.add(value)){
                return value;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        int[] arr = {10, 5, 3, 4, 3, 5, 6};
        System.out.println(findFirstRepeated(arr));
    }
}
