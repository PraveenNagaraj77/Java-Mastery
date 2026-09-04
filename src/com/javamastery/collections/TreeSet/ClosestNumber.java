package com.javamastery.collections.TreeSet;

import java.util.TreeSet;

public class ClosestNumber {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};
        int target = 26;

        TreeSet<Integer> numberSet = new TreeSet<>();

        for (int no : arr) {
            numberSet.add(no);
        }

        Integer lower = numberSet.lower(target);
        Integer higher = numberSet.higher(target);

        System.out.println(lower);
        System.out.println(higher);

        int closest;

        if(lower==null){
            closest = higher;
        } else if (higher == null) {
            closest = lower;
        }else{
            int lowerDifference = target-lower;
            int higherDifference = higher-target;

            if(lowerDifference<=higherDifference){
                closest=lower;
            }else {
                closest=higher;
            }
        }
        System.out.println("Closest :" +closest);
    }
}
