package com.javamastery.collections.collectionPrograms;

import java.util.LinkedHashSet;
import java.util.Set;

public class RemoveDuplicateCharacters {
    public static void main(String[] args) {
        String text = "programming";
        Set<Character> characterSet = new LinkedHashSet<>();

        for (char c : text.toCharArray()){
            characterSet.add(c);
        }
        System.out.println(characterSet);
    }
}
