package com.javamastery.strings;

public class StringDSADemo {
    public static String reverseUsingBuilder(String input){
        return new StringBuilder(input).reverse().toString();
    }

    public static String reverseUsingTwoPointers(String input){
        char[] characters = input.toCharArray();

        int left = 0;
        int right = characters.length-1;

        while(left<right){
            char temp = characters[left];
            characters[left] = characters[right];
            characters[right] = temp;

            left++;
            right--;
        }
        return new String(characters);
    }


    public static boolean isPalindrome(String input){
        int left =0;
        int right= input.length()-1;

        while (left<right){
            if(input.charAt(left)!= input.charAt(right)){
                return false;
            }
            left++;
            right--;

        }
        return  true;
    }

    public static void findDuplicateCharacters(String input){
        int[] frequency = new int[26];

        //count characters
        for(char charcter : input.toCharArray()){
            frequency[charcter - 'a']++;
        }

        //Print Duplicate Values
        for (int i =0;i<frequency.length;i++){
            System.out.println(
                    (char)('a'+i) + " : " + frequency[i]
            );
        }
    }






    public static Character firstNonRepeatingCharacter(String input){
        int[] frequency = new int[26];

        //count freq

        for (char character : input.toCharArray()){
            frequency[character-'a']++;

        }
        for (char character : input.toCharArray()){
            if(frequency[character - 'a']==1){
                return character;
            }
        }
        return  null;
    }

    public static boolean areAnagrams(String first,String second){
        //Length check
        if(first.length()!=second.length()){
            return false;
        }

        int[] frequency = new int[26];

        //add fre from first sring
        for(char character : first.toCharArray()){
            frequency[character-'a']++;
        }

        //sub

        for (char character : second.toCharArray()){
            frequency[character-'a']--;
        }

        //check all freq
        for (int count : frequency){
            if(count!=0){
                return false;
            }
        }
        return true;
    }





    public static void main(String[] args) {
        String first = "listen";
        String second = "silent";

        boolean result = areAnagrams(first,second);

        System.out.println("First String : "+first);
        System.out.println("Second String :" + second);
        System.out.println("Are Anagrams : " +result);
    }

}
