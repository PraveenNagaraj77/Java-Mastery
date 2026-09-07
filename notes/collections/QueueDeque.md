# Queue & Deque

## 1. What is Queue?

A **Queue** is a collection designed primarily for processing elements in **FIFO (First-In, First-Out)** order.

The element added first is normally the element removed first.

```text
Front                         Rear
  ↓                            ↓
[A] → [B] → [C] → [D]
 ↑
removed first
```

### Example

```java
Queue<String> queue = new ArrayDeque<>();

queue.offer("A");
queue.offer("B");
queue.offer("C");

System.out.println(queue);
```

Output:

```text
[A, B, C]
```

If we remove elements:

```java
queue.poll();
```

`A` is removed first.

---

# 2. Why is Queue Needed?

Queues are useful whenever tasks need to be processed in the order they arrive.

### Real-world examples

* Printer job processing
* CPU task scheduling
* Customer service systems
* Message processing
* Request handling
* Breadth-First Search (BFS)
* Order processing
* Event processing

Example:

```text
Customer 1 → Customer 2 → Customer 3

Service:
Customer 1
Customer 2
Customer 3
```

This naturally follows FIFO.

---

# 3. Queue Interface

Java provides the `Queue` interface:

```java
Queue<String> queue = new ArrayDeque<>();
```

`Queue` is an interface, so we need an implementation.

Common implementations include:

```text
Queue
 ├── LinkedList
 └── ArrayDeque
```

Example:

```java
Queue<String> queue = new LinkedList<>();
```

or preferably for typical queue operations:

```java
Queue<String> queue = new ArrayDeque<>();
```

---

# 4. Important Queue Methods

| Method      | Purpose      | Empty Behavior                    |
| ----------- | ------------ | --------------------------------- |
| `offer()`   | Add element  | Returns `false` if unable to add  |
| `add()`     | Add element  | Throws exception if unable to add |
| `poll()`    | Remove front | Returns `null`                    |
| `remove()`  | Remove front | Throws `NoSuchElementException`   |
| `peek()`    | View front   | Returns `null`                    |
| `element()` | View front   | Throws `NoSuchElementException`   |

---

# 5. `offer()`

Adds an element to the queue.

```java
Queue<String> queue = new ArrayDeque<>();

queue.offer("A");
queue.offer("B");
queue.offer("C");

System.out.println(queue);
```

Output:

```text
[A, B, C]
```

`offer()` is generally preferred when working with a `Queue` because it communicates queue semantics clearly.

---

# 6. `poll()`

Removes and returns the front element.

```java
Queue<String> queue = new ArrayDeque<>();

queue.offer("A");
queue.offer("B");
queue.offer("C");

System.out.println(queue.poll());
System.out.println(queue);
```

Output:

```text
A
[B, C]
```

If the queue is empty:

```java
queue.poll();
```

returns:

```text
null
```

---

# 7. `peek()`

Returns the front element without removing it.

```java
Queue<String> queue = new ArrayDeque<>();

queue.offer("A");
queue.offer("B");

System.out.println(queue.peek());
System.out.println(queue);
```

Output:

```text
A
[A, B]
```

The queue remains unchanged.

---

# 8. `add()` vs `offer()`

Both add elements.

```java
queue.add("A");
queue.offer("B");
```

The difference is how they behave when an element cannot be added.

```text
add()
 ↓
Throws exception

offer()
 ↓
Returns false
```

For normal `ArrayDeque` usage, this distinction usually does not matter because the deque grows dynamically, but it is important for interviews and bounded queue implementations.

---

# 9. `remove()` vs `poll()`

Both remove the front element.

```text
remove()
 ↓
No element?
 ↓
NoSuchElementException
```

```text
poll()
 ↓
No element?
 ↓
null
```

Example:

```java
Queue<String> queue = new ArrayDeque<>();

System.out.println(queue.poll());
```

Output:

```text
null
```

But:

```java
queue.remove();
```

throws:

```text
NoSuchElementException
```

---

# 10. `peek()` vs `element()`

Both inspect the front element without removing it.

```text
peek()
 ↓
Empty → null
```

```text
element()
 ↓
Empty → NoSuchElementException
```

For safer queue code, `peek()` is commonly preferred.

---

# 11. Queue Example

```java
package com.javamastery.collections.queue;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueBasic {

    public static void main(String[] args) {

        Queue<String> queue = new ArrayDeque<>();

        queue.offer("A");
        queue.offer("B");
        queue.offer("C");
        queue.offer("D");

        System.out.println(queue);

        System.out.println(queue.peek());

        System.out.println(queue.poll());
        System.out.println(queue.poll());

        System.out.println(queue);

        System.out.println(queue.peek());
    }
}
```

Output:

```text
[A, B, C, D]
A
A
B
[C, D]
C
```

---

# 12. Queue Internal Mental Model

Think of a queue like this:

```text
             Queue
               ↓

Front                     Rear
  ↓                         ↓
[A] → [B] → [C] → [D]
```

### `offer(E)`

Adds at the rear:

```text
[A] → [B] → [C] → [D] → [E]
```

### `poll()`

Removes from the front:

```text
[B] → [C] → [D] → [E]
```

### `peek()`

Looks at the front:

```text
[B] → [C] → [D] → [E]
 ↑
peek
```

---

# 13. Deque

**Deque** means:

> Double-Ended Queue

It allows insertion and removal from **both ends**.

```text
Front                         Rear
 ↓                             ↓
[A] ↔ [B] ↔ [C] ↔ [D]
 ↑                             ↑
add/remove                 add/remove
```

Java provides:

```java
Deque<E>
```

---

# 14. Creating a Deque

A common implementation is:

```java
Deque<String> deque = new ArrayDeque<>();
```

Example:

```java
deque.offerFirst("A");
deque.offerLast("B");
```

Result:

```text
[A, B]
```

---

# 15. Important Deque Methods

| Method          | Purpose           |
| --------------- | ----------------- |
| `offerFirst()`  | Add to front      |
| `offerLast()`   | Add to rear       |
| `pollFirst()`   | Remove from front |
| `pollLast()`    | Remove from rear  |
| `peekFirst()`   | View front        |
| `peekLast()`    | View rear         |
| `addFirst()`    | Add to front      |
| `addLast()`     | Add to rear       |
| `removeFirst()` | Remove front      |
| `removeLast()`  | Remove rear       |

---

# 16. `offerFirst()`

Adds an element to the front.

```java
Deque<String> deque = new ArrayDeque<>();

deque.offerLast("A");
deque.offerLast("B");

deque.offerFirst("C");

System.out.println(deque);
```

Output:

```text
[C, A, B]
```

---

# 17. `offerLast()`

Adds an element to the rear.

```java
deque.offerLast("D");
```

Result:

```text
[C, A, B, D]
```

---

# 18. `pollFirst()`

Removes from the front.

```java
System.out.println(deque.pollFirst());
```

If:

```text
[C, A, B, D]
```

Output:

```text
C
```

Remaining:

```text
[A, B, D]
```

---

# 19. `pollLast()`

Removes from the rear.

```java
System.out.println(deque.pollLast());
```

Output:

```text
D
```

Remaining:

```text
[A, B]
```

---

# 20. Deque Example

```java
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
```

Output:

```text
C
B
C
B
[A]
```

---

# 21. ArrayDeque

`ArrayDeque` is a resizable-array implementation of the `Deque` interface.

It can be used as:

```text
Queue
Stack
Deque
```

Example:

```java
Deque<Integer> deque = new ArrayDeque<>();
```

---

# 22. ArrayDeque as Queue

Use normal queue operations:

```java
Deque<String> queue = new ArrayDeque<>();

queue.offerLast("A");
queue.offerLast("B");
queue.offerLast("C");

queue.pollFirst();
```

This gives FIFO behavior:

```text
A → B → C
↑
removed first
```

---

# 23. ArrayDeque as Stack

A `Deque` can also be used as a stack.

```java
Deque<Integer> stack = new ArrayDeque<>();

stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(stack.pop());
```

Output:

```text
30
```

Because Stack follows:

```text
LIFO
Last-In, First-Out
```

---

# 24. Stack Using Deque

```java
package com.javamastery.collections.queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackUsingDeque {

    public static void main(String[] args) {

        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);

        System.out.println(stack);

        System.out.println(stack.peek());

        System.out.println(stack.pop());
        System.out.println(stack.pop());

        System.out.println(stack);

        System.out.println(stack.peek());
    }
}
```

Output:

```text
[40, 30, 20, 10]
40
40
30
[20, 10]
20
```

---

# 25. Why Prefer Deque Over Stack?

Java has a legacy `Stack` class:

```java
Stack<Integer> stack = new Stack<>();
```

Modern Java code commonly prefers:

```java
Deque<Integer> stack = new ArrayDeque<>();
```

Advantages:

* More modern API
* Supports both stack and deque operations
* Better abstraction
* Designed for double-ended operations

Interview answer:

> For stack behavior in modern Java, I would generally use `Deque` with `ArrayDeque` instead of the legacy `Stack` class.

---

# 26. ArrayDeque vs LinkedList

Both can implement `Queue`/`Deque`.

| Feature                                 | ArrayDeque     | LinkedList     |
| --------------------------------------- | -------------- | -------------- |
| Queue                                   | ✅              | ✅              |
| Deque                                   | ✅              | ✅              |
| Stack                                   | ✅              | ✅              |
| Null elements                           | ❌              | ✅              |
| Random access                           | ❌              | ❌              |
| Typical end operations                  | Very efficient | Very efficient |
| Per-element node overhead               | Lower          | Higher         |
| Recommended for typical Queue/Deque use | ✅              | Sometimes      |

For normal Queue/Deque operations:

```java
Deque<String> deque = new ArrayDeque<>();
```

is generally the preferred choice.

---

# 27. Why ArrayList Is Poor as a Queue

You technically can write:

```java
List<String> list = new ArrayList<>();
```

But using:

```java
list.remove(0);
```

is inefficient.

Why?

Because removing index `0` requires the remaining elements to shift.

```text
Before:

[A][B][C][D][E]

remove A

[B][C][D][E]
 ↑
elements shifted
```

This is generally **O(n)**.

With `ArrayDeque`:

```java
queue.poll();
```

front removal is designed for queue usage and is **O(1)**.

---

# 28. Time Complexity

For `ArrayDeque`:

| Operation      |     Complexity |
| -------------- | -------------: |
| `offer()`      | O(1) amortized |
| `poll()`       |           O(1) |
| `peek()`       |           O(1) |
| `offerFirst()` | O(1) amortized |
| `offerLast()`  | O(1) amortized |
| `pollFirst()`  |           O(1) |
| `pollLast()`   |           O(1) |
| `peekFirst()`  |           O(1) |
| `peekLast()`   |           O(1) |
| `push()`       | O(1) amortized |
| `pop()`        |           O(1) |

---

# 29. Queue vs Deque

```text
Queue
  ↓
Primarily FIFO

Front → remove
Rear  → add
```

```text
Deque
  ↓
Both ends available

Front → add/remove
Rear  → add/remove
```

### Mental model

```text
Queue

[A] → [B] → [C] → [D]
 ↑                 ↑
remove             add
```

```text
Deque

      add/remove
           ↓
[A] ↔ [B] ↔ [C] ↔ [D]
 ↑                 ↑
add/remove      add/remove
```

---

# 30. Common Mistakes

### Mistake 1: Using raw types

Avoid:

```java
Queue queue = new LinkedList();
```

Prefer:

```java
Queue<String> queue = new ArrayDeque<>();
```

---

### Mistake 2: Using ArrayList as a Queue

Avoid:

```java
list.remove(0);
```

for heavy queue processing.

Prefer:

```java
Queue<String> queue = new ArrayDeque<>();
```

---

### Mistake 3: Confusing `peek()` and `poll()`

```java
peek()
```

only looks.

```java
poll()
```

removes.

---

### Mistake 4: Confusing FIFO and LIFO

Queue:

```text
FIFO
First In → First Out
```

Stack:

```text
LIFO
Last In → First Out
```

---

### Mistake 5: Forgetting ArrayDeque does not allow null

This is invalid:

```java
Deque<String> deque = new ArrayDeque<>();

deque.offer(null);
```

`ArrayDeque` does not permit `null` elements.

---

# 31. Real-World Example — Food Order Processing

Imagine a food delivery system.

Orders arrive:

```text
Order 101
Order 102
Order 103
Order 104
```

Store them:

```java
Queue<Integer> orders = new ArrayDeque<>();

orders.offer(101);
orders.offer(102);
orders.offer(103);
orders.offer(104);
```

Processing:

```java
while (!orders.isEmpty()) {

    Integer orderId = orders.poll();

    System.out.println("Processing order: " + orderId);
}
```

Output:

```text
Processing order: 101
Processing order: 102
Processing order: 103
Processing order: 104
```

This is a natural FIFO use case.

---

# 32. Queue in DSA

Queues are extremely important in DSA.

Common applications:

### BFS

```text
Start
  ↓
Queue
  ↓
Process level by level
```

### Sliding Window

Deque is commonly used to efficiently maintain candidates for problems such as:

* Sliding Window Maximum
* Sliding Window Minimum

### Scheduling

```text
Task 1
Task 2
Task 3
Task 4
```

A queue can process tasks in arrival order.

---

# 33. Queue Quick Reference

```text
Queue
 ├── FIFO
 ├── offer()     → add
 ├── poll()      → remove
 └── peek()      → inspect
```

```text
Deque
 ├── offerFirst()
 ├── offerLast()
 ├── pollFirst()
 ├── pollLast()
 ├── peekFirst()
 └── peekLast()
```

---

# 34. Stack Using Deque Quick Reference

```text
Deque<Integer> stack = new ArrayDeque<>();

push() → add to top
pop()  → remove top
peek() → inspect top
```

```text
push(10)
push(20)
push(30)

Stack:

30 ← top
20
10
```

```java
stack.pop();
```

removes:

```text
30
```

---

# 35. Interview Questions

### Q1. What is a Queue?

A Queue is a collection designed primarily for FIFO processing, where the first element inserted is normally the first element removed.

---

### Q2. What is a Deque?

Deque stands for Double-Ended Queue. It allows insertion and removal from both the front and rear.

---

### Q3. Difference between Queue and Deque?

```text
Queue → primarily one-directional FIFO processing

Deque → insertion/removal from both ends
```

---

### Q4. Difference between `poll()` and `remove()`?

`poll()` returns `null` when the queue is empty.

`remove()` throws `NoSuchElementException`.

---

### Q5. Difference between `peek()` and `element()`?

`peek()` returns `null` when empty.

`element()` throws `NoSuchElementException`.

---

### Q6. Difference between `offer()` and `add()`?

Both attempt to add an element.

`offer()` returns `false` if the element cannot be added.

`add()` throws an exception if it cannot be added.

---

### Q7. Can ArrayDeque contain null?

No.

```java
ArrayDeque
    ↓
null elements not allowed
```

---

### Q8. Why use ArrayDeque instead of Stack?

`ArrayDeque` provides modern deque/stack functionality and is generally preferred over the legacy `Stack` class.

---

### Q9. Why is ArrayList not ideal for Queue?

Removing from the beginning using `remove(0)` requires shifting remaining elements, which is O(n).

---

### Q10. What is the difference between FIFO and LIFO?

```text
FIFO → First In, First Out → Queue

LIFO → Last In, First Out → Stack
```

---

# 36. Interview Explanation

If an interviewer asks:

> Explain Queue and Deque in Java.

You can answer:

> A Queue is a collection used primarily for FIFO processing, where elements are added at the rear and removed from the front. Java provides the Queue interface with methods such as offer, poll, and peek. For typical queue operations, I can use ArrayDeque.
>
> A Deque, or Double-Ended Queue, extends this concept by allowing insertion and removal from both ends. ArrayDeque implements Deque and can also be used to implement stack behavior using push, pop, and peek. In modern Java, I generally prefer Deque with ArrayDeque over the legacy Stack class.

---

# 37. Final Mental Model

Remember this:

```text
                 Collections
                      │
              Queue / Deque
                      │
              ┌───────┴───────┐
              │               │
            Queue            Deque
              │               │
             FIFO        Both Ends
              │               │
        offer/poll/peek   First + Last
                              │
                         ArrayDeque
                              │
                    ┌─────────┴─────────┐
                    │                   │
                  Queue               Stack
                   FIFO                LIFO
                    │                   │
             offer/poll            push/pop
```

### One-line memory trick

```text
Queue  → FIFO
Deque  → Both Ends
Stack  → LIFO
```

---

## Collection Roadmap

```text
Collections
│
├── Collection Framework Overview ✅
│
├── List
│   ├── ArrayList ✅
│   └── LinkedList ✅
│
├── Set
│   ├── HashSet ✅
│   ├── LinkedHashSet ✅
│   └── TreeSet ✅
│
├── Map
│   ├── HashMap ✅
│   ├── LinkedHashMap ✅
│   └── TreeMap ✅
│
├── Queue / Deque ✅
│
├── Iterator ⏳
│
├── Comparable vs Comparator ⏳
│
└── Collection Interview Problems ⏳
```
