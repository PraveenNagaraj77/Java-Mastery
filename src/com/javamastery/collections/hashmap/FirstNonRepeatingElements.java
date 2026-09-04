package com.javamastery.collections.hashmap;

import java.util.HashMap;
import java.util.Map;

public class FirstNonRepeatingElements {
    public static void main(String[] args) {
        String input = "aabbcdde";
        char[] characters = input.toCharArray();

        Map<Character,Integer> frequency = new HashMap<>();

        for(char charcter : characters){
            frequency.put(charcter,frequency.getOrDefault(charcter,0)+1);
        }

        for(char character : characters){
            if(frequency.get(character)==1){
                System.out.println(character);
                break;
            }
        }



    }
}
