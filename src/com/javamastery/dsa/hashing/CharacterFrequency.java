package com.javamastery.dsa.hashing;

import java.util.HashMap;
import java.util.Map;

public class CharacterFrequency {
    public static Map<Character,Integer> countFrequncy(String str){
        Map<Character,Integer> frequency = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (frequency.containsKey(ch)) {
                frequency.put(ch, frequency.get(ch) + 1);
            } else {
                frequency.put(ch, 1);
            }
        }
        return frequency;
    }
    public static void main(String[] args) {
        String input = "banana";
        System.out.println(countFrequncy(input));

    }
}
