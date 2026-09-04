package com.javamastery.collections.hashmap;

import java.util.*;

public class DuplicateCharacters {
    public static void main(String[] args) {
        String input = "programming";
        char[] characters = input.toCharArray();

        Map<Character,Integer> frequency = new HashMap<>();

        for (char character : characters){
            frequency.put(character,frequency.getOrDefault(character,0)+1);
        }

        Set<Character> characterSet = new LinkedHashSet<>();

        System.out.println(frequency);
        for (char character : characters){
            if(frequency.get(character)>1){
                characterSet.add(character);
            }
        }
        System.out.println(characterSet);


    }
}
