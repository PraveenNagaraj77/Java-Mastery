package com.javamastery.collections.collectionPrograms;

import java.util.HashSet;
import java.util.Set;

public class FirstRepeatedCharacter {
    public static void main(String[] args) {
        String text = "programming";
        Set<Character> characterSet = new HashSet<>();

        for (char c : text.toCharArray()){
            if(characterSet.contains(c)){
                System.out.println(c);
                break;
            }else{
                characterSet.add(c);
            }
        }

    }
}
