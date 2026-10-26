
package com.javamastery.dsa.strings;

import java.util.HashMap;
import java.util.Map;

public class CheckAnagrams {

    public static boolean areAnagrams(String first, String second) {
        if (first.length() != second.length()) {
            return false;
        }

        Map<Character, Integer> frequency = new HashMap<>();

        // Step 1: Count characters in the first string
        for (int i = 0; i < first.length(); i++) {
            char ch = first.charAt(i);
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }

        // Step 2: Consume those counts using the second string
        for (int i = 0; i < second.length(); i++) {
            char ch = second.charAt(i);
            int count = frequency.getOrDefault(ch, 0);

            if (count == 0) {
                return false;
            }

            frequency.put(ch, count - 1);
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(areAnagrams("listen", "silent")); // true
        System.out.println(areAnagrams("world", "hello"));   // false
        System.out.println(areAnagrams("aabb", "baba"));     // true
        System.out.println(areAnagrams("aabb", "abbb"));     // false
    }
}
