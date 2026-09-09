package com.javamastery.collections.collectionPrograms;

import java.util.HashMap;
import java.util.Map;

public class FirstNonRepeatingCharacter {
    public static void main(String[] args) {
        String text = "swwiss";

        Map<Character,Integer> frequency = new HashMap<>();

        for (char characters : text.toCharArray()){
            frequency.put(characters,frequency.getOrDefault(characters,0)+1);
        }

        for (char character : text.toCharArray()){
            if(frequency.get(character)==1){
                System.out.println("First Non Repeating Character is : " +character);
                break;
            }
        }

    }
}
