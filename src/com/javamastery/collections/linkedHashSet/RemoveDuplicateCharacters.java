package com.javamastery.collections.linkedHashSet;

import java.util.LinkedHashSet;
import java.util.Set;

public class RemoveDuplicateCharacters {
    public static void main(String[] args) {
        String input = "programming";
        char[] characters = input.toCharArray();

        Set<Character> duplicateSet = new LinkedHashSet<>();

        for (char letters  : characters){
            duplicateSet.add(letters);
        }
        System.out.println(duplicateSet);
    }
}
