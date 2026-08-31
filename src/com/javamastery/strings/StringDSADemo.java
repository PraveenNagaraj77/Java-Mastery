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





    public static void main(String[] args) {
        String input = "Java";
        String result = reverseUsingTwoPointers(input);

        System.out.println("Input : " + input);
        System.out.println("Output : " + result);

        String input1 = "level";
        Boolean result1 = isPalindrome(input1);

        System.out.println("Input : " + input1);
        System.out.println("Output : " + result1);

    }

}
