package com.javamastery.dsa.strings;

public class PallindromeCheck {
    public static boolean pallindrome(String str ){
        int left = 0;
        int right = str.length()-1;

        while(left<right){
            if(str.charAt(left) == str.charAt(right)){
                left++;
                right--;
            }else{
                return false;
            }
        }
        return true;

    }
    public static void main(String[] args) {
        String input = "abba";
        System.out.println(pallindrome(input));
    }
}
