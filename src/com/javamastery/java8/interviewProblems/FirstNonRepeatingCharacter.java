package com.javamastery.java8.interviewProblems;

import java.util.HashMap;
import java.util.Map;

public class FirstNonRepeatingCharacter {
    public static void main(String[] args) {
        String input = "swiss";

        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (char ch : input.toCharArray()) {
            frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
        }

        for (char ch : input.toCharArray()){
            if (frequencyMap.get(ch)==1){}
            System.out.println(ch);
            break;
        }

    }
}
