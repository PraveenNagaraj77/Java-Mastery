package com.javamastery.collections.queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class DequeBasic {
    public static void main(String[] args) {
        Deque<String> deque = new ArrayDeque<>();
        deque.offerLast("A");
        deque.offerLast("B");

        deque.offerFirst("C");

        System.out.println(deque.peekFirst());
        System.out.println(deque.peekLast());

        System.out.println(deque.pollFirst());
        System.out.println(deque.pollLast());
        System.out.println(deque);
    }
}
