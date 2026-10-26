
package com.javamastery.dsa.strings;

import java.util.HashMap;
import java.util.Map;

public class FirstNonRepeatingCharacter {

    public static Character findFirstNonRepeatingCharacter(String str) {
        Map<Character, Integer> frequency = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
        }

        for (int i = 0; i < str.length(); i++) {
            if (frequency.get(str.charAt(i)) == 1) {
                return str.charAt(i);
            }
        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println(findFirstNonRepeatingCharacter("aabbcde")); // c
        System.out.println(findFirstNonRepeatingCharacter("swiss"));   // w
        System.out.println(findFirstNonRepeatingCharacter("aabb"));    // null
    }
}
