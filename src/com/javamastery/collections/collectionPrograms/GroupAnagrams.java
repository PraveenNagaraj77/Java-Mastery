package com.javamastery.collections.collectionPrograms;

import java.util.*;

public class GroupAnagrams {
    public static void main(String[] args) {
        String[] words = {
                "eat",
                "tea",
                "tan",
                "ate",
                "nat",
                "bat"
        };

        Map<String, List<String>> mapSet = new HashMap<>();

        for (String word : words){
            char[] character = word.toCharArray();
            Arrays.sort(character);
            String key = new String(character);
            mapSet.computeIfAbsent(key,k-> new ArrayList<>()).add(word);
        }

        System.out.println(mapSet.values());

    }
}
