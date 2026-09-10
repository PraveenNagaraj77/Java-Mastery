package com.javamastery.exceptionHandling;

public class MultipleCatchesHandling {
    public static void main(String[] args) {
        int a = 20;
        int b = 0;
        String name = null;

        try {
            int result  = a/b;
            System.out.println(name.length());
        }catch (ArithmeticException e){
            System.out.println(e);
        }catch (NullPointerException e){
            System.out.println(e);
        }catch (Exception e){
            System.out.println(e);
        }finally {
            System.out.println("Finally Executed");
        }
    }
}
