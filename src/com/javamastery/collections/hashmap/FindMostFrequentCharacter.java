package com.javamastery.collections.hashmap;

import java.util.HashMap;
import java.util.Map;

public class FindMostFrequentCharacter {
    public static void main(String[] args) {
        String input = "programming";
        char[] characters = input.toCharArray();

        // Find maximum frequency
        char maxCharacter = '\0';
        int maxFrequency = 0;

        Map<Character,Integer> frequency = new HashMap<>();

        for (char character : characters){
            frequency.put(character,frequency.getOrDefault(character,0)+1);
        }

        for (Map.Entry<Character,Integer> character : frequency.entrySet()){
            if(character.getValue()>maxFrequency){
                maxFrequency = character.getValue();
                maxCharacter = character.getKey();
            }
        }

        System.out.println(maxCharacter);
        System.out.println(maxFrequency);
    }
}
