package com.javamastery.dsa.hashing;

import java.util.HashSet;
import java.util.Set;

public class FindDuplicates {
    public static boolean duplicateCheck(int[] arr){
        if(arr==null ){
            throw  new IllegalArgumentException("Array Cannot be null");
        }
        Set<Integer> numberSet = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            if(!numberSet.add(arr[i])){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        System.out.println(duplicateCheck(new int[]{1, 2, 3, 4})); // false
        System.out.println(duplicateCheck(new int[]{1, 2, 3, 1})); // true
        System.out.println(duplicateCheck(new int[]{5, 5}));       // true
        System.out.println(duplicateCheck(new int[]{}));           // false
    }
}
