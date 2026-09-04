package com.javamastery.collections.TreeSet;

import java.util.TreeSet;

public class SmallerNumberGreater {
    public static void main(String[] args) {
        int[] arr = {10,20,30,40,50};
        int target = 30;


        TreeSet<Integer> numberSet = new TreeSet<>();
        for (int no : arr){
            numberSet.add(no);
        }
        System.out.println(numberSet.higher(target));
        System.out.println(numberSet.lower(target));

        System.out.println(numberSet.floor(target));
        System.out.println(numberSet.ceiling(target));


    }
}
