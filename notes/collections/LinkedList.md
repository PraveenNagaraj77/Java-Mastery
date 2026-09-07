# LinkedList

`LinkedList` is a Java collection that stores elements using a **doubly linked list** structure.

It implements both:

```text
List
Deque
```

This allows it to behave as:

* A List
* A Queue
* A Deque

---

# 1. What is LinkedList?

`LinkedList` is a class in the Java Collections Framework that stores elements as a sequence of nodes.

Each node conceptually contains:

```text
Previous | Data | Next
```

Example:

```text
null
  ↓
[10] ⇄ [20] ⇄ [30] ⇄ [40]
                         ↓
                        null
```

Each node maintains a reference to:

* The previous node
* The current element
* The next node

---

# 2. Package

```java
java.util.LinkedList
```

Import:

```java
import java.util.LinkedList;
```

---

# 3. Why Do We Need LinkedList?

`ArrayList` is backed by a dynamically resized array.

When elements are inserted or removed from the middle, other elements may need to be shifted.

Example:

```text
[10, 20, 30, 40, 50]
```

Remove `30`:

```text
[10, 20, 40, 50]
```

Elements after `30` must shift.

With a linked list, nodes can be unlinked without shifting all later elements.

Conceptually:

```text
Before:

10 ⇄ 20 ⇄ 30 ⇄ 40 ⇄ 50

Remove 30:

10 ⇄ 20 ⇄ 40 ⇄ 50
```

The links around the removed node are changed.

---

# 4. Basic Syntax

```java
LinkedList<Integer> numbers = new LinkedList<>();
```

Recommended when you specifically need LinkedList operations:

```java
LinkedList<Integer> numbers =
        new LinkedList<>();
```

If you only need List behavior:

```java
List<Integer> numbers =
        new LinkedList<>();
```

Programming to the interface is generally preferred when implementation-specific methods aren't needed.

---

# 5. Basic Example

```java
import java.util.LinkedList;

public class LinkedListBasic {

    public static void main(String[] args) {

        LinkedList<Integer> numbers =
                new LinkedList<>();

        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);

        System.out.println(numbers);
    }
}
```

Output:

```text
[10, 20, 30, 40]
```

---

# 6. How LinkedList Works Internally

A simplified node looks like:

```text
class Node {

    Node previous;

    E item;

    Node next;
}
```

Example:

```text
       previous       next
          ↓            ↓
      ┌───────┐    ┌───────┐
      │       │    │       │
null ←│  10   │ ⇄  │  20   │⇄ ...
      │       │    │       │
      └───────┘    └───────┘
```

Java's `LinkedList` maintains references to the beginning and end of the list.

Conceptually:

```text
first
  ↓
[10] ⇄ [20] ⇄ [30] ⇄ [40]
                         ↑
                        last
```

---

# 7. Adding Elements

## add()

```java
numbers.add(10);
```

Adds the element to the end.

Example:

```text
Before:
[10, 20]

add(30)

After:
[10, 20, 30]
```

---

# 8. addFirst()

Adds an element to the beginning.

```java
numbers.addFirst(5);
```

Example:

```text
Before:
[10, 20, 30]

After:
[5, 10, 20, 30]
```

---

# 9. addLast()

Adds an element to the end.

```java
numbers.addLast(40);
```

Example:

```text
Before:
[10, 20, 30]

After:
[10, 20, 30, 40]
```

---

# 10. add(index, element)

Adds an element at a specific position.

```java
numbers.add(1, 15);
```

Example:

```text
Before:
[10, 20, 30]

After:
[10, 15, 20, 30]
```

The list must locate the node at the specified index before inserting.

---

# 11. Removing Elements

## remove()

```java
numbers.remove();
```

For `LinkedList`, this removes the first element.

Example:

```text
Before:
[10, 20, 30]

remove()

After:
[20, 30]
```

---

# 12. removeFirst()

```java
numbers.removeFirst();
```

Removes the first element.

```text
[10, 20, 30]

      ↓

[20, 30]
```

---

# 13. removeLast()

```java
numbers.removeLast();
```

Removes the last element.

```text
[10, 20, 30]

      ↓

[10, 20]
```

---

# 14. remove(index)

```java
numbers.remove(1);
```

Removes the element at index `1`.

Example:

```text
[10, 20, 30, 40]

remove(1)

[10, 30, 40]
```

The list must first locate the node at that index.

---

# 15. Accessing Elements

## get()

```java
numbers.get(2);
```

Returns the element at index `2`.

Example:

```text
[10, 20, 30, 40]

get(2)

30
```

Unlike `ArrayList`, LinkedList does not provide constant-time random access.

---

# 16. getFirst()

```java
numbers.getFirst();
```

Returns the first element.

```text
[10, 20, 30]

getFirst()

10
```

---

# 17. getLast()

```java
numbers.getLast();
```

Returns the last element.

```text
[10, 20, 30]

getLast()

30
```

---

# 18. Peek Methods

LinkedList also implements `Deque`.

Therefore it supports:

```java
peek()
peekFirst()
peekLast()
```

Example:

```java
System.out.println(numbers.peekFirst());
System.out.println(numbers.peekLast());
```

Unlike `removeFirst()` / `removeLast()`, peek methods do not remove elements.

---

# 19. Queue Behavior

Because `LinkedList` implements `Queue`, it can be used as a queue.

```java
Queue<String> queue =
        new LinkedList<>();

queue.offer("A");
queue.offer("B");
queue.offer("C");
```

Conceptually:

```text
Front
 ↓
[A] [B] [C]
          ↑
         Rear
```

Remove:

```java
queue.poll();
```

Result:

```text
B C
```

This follows FIFO:

```text
First In
   ↓
First Out
```

---

# 20. Deque Behavior

LinkedList also implements `Deque`.

```java
Deque<Integer> deque =
        new LinkedList<>();
```

We can add/remove from both ends.

```java
deque.addFirst(10);
deque.addLast(20);

deque.removeFirst();
deque.removeLast();
```

Conceptually:

```text
       addFirst
          ↓
       [10] ⇄ [20]
                  ↑
               addLast
```

---

# 21. LinkedList as a Stack

Because LinkedList implements `Deque`, it can also behave like a stack.

```java
Deque<Integer> stack =
        new LinkedList<>();

stack.push(10);
stack.push(20);
stack.push(30);

System.out.println(stack.pop());
```

Output:

```text
30
```

Stack behavior:

```text
Last In
   ↓
First Out
```

However, for a typical stack implementation, `ArrayDeque` is generally preferred.

---

# 22. Time Complexity

| Operation             | Complexity |
| --------------------- | ---------: |
| `add()` at end        |       O(1) |
| `addFirst()`          |       O(1) |
| `addLast()`           |       O(1) |
| `add(index, element)` |       O(n) |
| `get(index)`          |       O(n) |
| `getFirst()`          |       O(1) |
| `getLast()`           |       O(1) |
| `removeFirst()`       |       O(1) |
| `removeLast()`        |       O(1) |
| `remove(index)`       |       O(n) |
| `contains()`          |       O(n) |
| `indexOf()`           |       O(n) |
| `peekFirst()`         |       O(1) |
| `peekLast()`          |       O(1) |

### Important

`remove(index)` is generally **O(n)** because finding the node can require traversal.

Once the node is located, unlinking the node itself is O(1).

---

# 23. ArrayList vs LinkedList

| Feature                 | ArrayList      | LinkedList         |
| ----------------------- | -------------- | ------------------ |
| Internal structure      | Dynamic array  | Doubly linked list |
| Random access           | Fast           | Slow               |
| `get(index)`            | O(1)           | O(n)               |
| Add at end              | O(1) amortized | O(1)               |
| Add/remove at beginning | O(n)           | O(1)               |
| Add/remove by index     | O(n)           | O(n) generally     |
| Memory                  | Lower overhead | Higher overhead    |
| Implements List         | Yes            | Yes                |
| Implements Deque        | No             | Yes                |

---

# 24. Why is get(index) O(n)?

Suppose:

```java
LinkedList<Integer> numbers =
        new LinkedList<>();

numbers.get(4);
```

Unlike an array:

```text
Array:
[10][20][30][40][50]
              ↑
           direct access
```

A linked list doesn't have direct indexed memory access.

It must follow links:

```text
[10]
 ↓
[20]
 ↓
[30]
 ↓
[40]
 ↓
[50]
```

Java's LinkedList can traverse from whichever end is closer, but indexed access is still O(n) in the general case.

---

# 25. Why LinkedList Uses More Memory

Each node stores more than just the element.

Conceptually:

```text
Node
├── previous reference
├── element
└── next reference
```

Therefore, LinkedList has additional memory overhead compared with an array-backed structure.

---

# 26. LinkedList vs ArrayDeque

For queue/deque operations, you will often see:

```java
Deque<Integer> deque =
        new ArrayDeque<>();
```

instead of:

```java
Deque<Integer> deque =
        new LinkedList<>();
```

For typical queue/deque use cases, `ArrayDeque` is generally preferred because it is designed specifically for double-ended operations and avoids the per-node overhead of LinkedList.

### General guideline

```text
Need List + frequent indexed access
        ↓
ArrayList
```

```text
Need Queue/Deque
        ↓
ArrayDeque
```

```text
Need LinkedList-specific behavior
        ↓
LinkedList
```

---

# 27. LinkedList and Null

`LinkedList` permits `null` elements.

Example:

```java
LinkedList<String> names =
        new LinkedList<>();

names.add(null);
names.add("Java");
```

This is valid.

`ArrayDeque`, in contrast, does not permit null elements.

---

# 28. Common Mistakes

## Mistake 1 — Using LinkedList for frequent get(index)

Avoid:

```java
for (int i = 0; i < numbers.size(); i++) {
    System.out.println(numbers.get(i));
}
```

for performance-sensitive LinkedList code.

Repeated indexed access can result in O(n²) traversal behavior.

Prefer:

```java
for (Integer number : numbers) {
    System.out.println(number);
}
```

or use an iterator.

---

## Mistake 2 — Thinking LinkedList makes every insertion O(1)

This is not always true.

This:

```java
addFirst()
addLast()
removeFirst()
removeLast()
```

is O(1).

But:

```java
add(index, element)
remove(index)
```

are generally O(n) because the list must locate the position.

---

## Mistake 3 — Assuming LinkedList is always faster

LinkedList is not automatically faster than ArrayList.

For many general-purpose List workloads, ArrayList is the better default because of:

* Fast indexed access
* Better cache locality
* Lower per-element overhead
* Efficient iteration

---

## Mistake 4 — Confusing LinkedList with a singly linked list

Java's `LinkedList` is a **doubly linked list**.

Conceptually:

```text
previous ⇄ node ⇄ next
```

not:

```text
node → next
```

---

# 29. Real-World Examples

## Browser Navigation

A sequence of pages can conceptually be represented as:

```text
Google ⇄ YouTube ⇄ GitHub ⇄ LinkedIn
```

Navigation can move backward and forward.

---

## Music Playlist

```text
Song A ⇄ Song B ⇄ Song C ⇄ Song D
```

You can move forward or backward through the playlist.

---

## Queue

LinkedList can implement:

```text
Customer A
Customer B
Customer C
```

The first customer added is served first.

---

# 30. Coding Example — Basic LinkedList

```java
import java.util.LinkedList;

public class LinkedListBasic {

    public static void main(String[] args) {

        LinkedList<String> names =
                new LinkedList<>();

        names.add("Java");
        names.add("Spring");
        names.add("React");

        names.addFirst("HTML");
        names.addLast("Node");

        System.out.println(names);

        System.out.println(names.getFirst());
        System.out.println(names.getLast());

        names.removeFirst();
        names.removeLast();

        System.out.println(names);
    }
}
```

---

# 31. Coding Example — LinkedList as Queue

```java
import java.util.LinkedList;
import java.util.Queue;

public class LinkedListQueue {

    public static void main(String[] args) {

        Queue<String> queue =
                new LinkedList<>();

        queue.offer("Customer 1");
        queue.offer("Customer 2");
        queue.offer("Customer 3");

        System.out.println(queue.peek());

        System.out.println(queue.poll());

        System.out.println(queue);
    }
}
```

Output:

```text
Customer 1
Customer 1
[Customer 2, Customer 3]
```

---

# 32. Coding Example — LinkedList as Deque

```java
import java.util.Deque;
import java.util.LinkedList;

public class LinkedListDeque {

    public static void main(String[] args) {

        Deque<Integer> deque =
                new LinkedList<>();

        deque.offerFirst(20);
        deque.offerFirst(10);
        deque.offerLast(30);
        deque.offerLast(40);

        System.out.println(deque);

        System.out.println(deque.pollFirst());
        System.out.println(deque.pollLast());

        System.out.println(deque);
    }
}
```

Output:

```text
[10, 20, 30, 40]

10
40

[20, 30]
```

---

# 33. Interview Questions

### Q1. What is LinkedList?

`LinkedList` is a doubly linked list implementation in Java that implements both `List` and `Deque`.

---

### Q2. What is the internal structure of LinkedList?

It is based on nodes where each node maintains references to the previous node and next node.

```text
previous ⇄ element ⇄ next
```

---

### Q3. Is LinkedList faster than ArrayList?

Not always.

LinkedList is efficient for operations at the ends, but ArrayList provides O(1) indexed access and is often the better general-purpose List.

---

### Q4. What is the complexity of get(index)?

```text
O(n)
```

in the general case.

Java may traverse from the closer end, but indexed access is still O(n) generally.

---

### Q5. What is the complexity of addFirst()?

```text
O(1)
```

---

### Q6. What is the complexity of removeLast()?

```text
O(1)
```

---

### Q7. Why is remove(index) O(n)?

Because the LinkedList generally needs to traverse to the requested index first.

Once the node is found, unlinking it is O(1).

---

### Q8. Can LinkedList contain duplicates?

Yes.

```java
LinkedList<Integer> numbers =
        new LinkedList<>();

numbers.add(10);
numbers.add(10);
```

is valid.

---

### Q9. Can LinkedList contain null?

Yes.

---

### Q10. Does LinkedList maintain insertion order?

Yes.

Elements are iterated in list order.

---

### Q11. Is Java LinkedList singly or doubly linked?

Doubly linked.

---

### Q12. Can LinkedList be used as a Queue?

Yes.

It implements `Queue` indirectly through `Deque`.

---

### Q13. Can LinkedList be used as a Deque?

Yes.

```java
Deque<Integer> deque =
        new LinkedList<>();
```

---

### Q14. Why might ArrayDeque be preferred over LinkedList for Queue/Deque?

For typical queue/deque operations, ArrayDeque is designed specifically for this use case and generally has lower memory overhead and better cache locality.

---

# 34. Interview Explanation

If the interviewer asks:

> Explain LinkedList in Java.

You can answer:

> "LinkedList is a doubly linked list implementation in the Java Collections Framework. Each node maintains references to the previous and next nodes. It implements both List and Deque, so it can be used as a list, queue, or deque. Operations at the beginning and end are O(1), while indexed access is O(n) because the list needs to traverse to the required position. Compared with ArrayList, LinkedList has higher memory overhead and slower random access, so ArrayList is usually preferred for general-purpose List usage."

---

# 35. Quick Reference

```text
LinkedList
    ↓
Doubly Linked List
    ↓
List + Deque
```

### List operations

```java
add()
add(index, element)
get(index)
remove(index)
contains()
```

### Beginning

```java
addFirst()
getFirst()
removeFirst()
peekFirst()
```

### End

```java
addLast()
getLast()
removeLast()
peekLast()
```

### Queue

```java
offer()
peek()
poll()
```

### Stack

```java
push()
peek()
pop()
```

---

# 36. Complexity Cheat Sheet

```text
addFirst()      → O(1)
addLast()       → O(1)
removeFirst()   → O(1)
removeLast()    → O(1)

get(index)      → O(n)
add(index)      → O(n) generally
remove(index)   → O(n) generally
contains()      → O(n)

peekFirst()     → O(1)
peekLast()      → O(1)
```

---

# 37. Key Takeaways

Remember these points for interviews:

1. `LinkedList` is a **doubly linked list**.
2. It implements both **List and Deque**.
3. `get(index)` is **O(n)** generally.
4. `addFirst()` and `addLast()` are **O(1)**.
5. `removeFirst()` and `removeLast()` are **O(1)**.
6. Indexed insertion/removal is generally **O(n)** because of traversal.
7. LinkedList has higher memory overhead than ArrayList.
8. `ArrayList` is usually preferred for general-purpose List usage.
9. `ArrayDeque` is generally preferred for typical Queue/Deque usage.
10. LinkedList allows `null` elements.
11. LinkedList preserves insertion order.
12. Avoid repeated `get(index)` operations on LinkedList when performance matters.

### Final Mental Model

```text
ArrayList
    ↓
Dynamic Array
    ↓
Fast index access
    ↓
Usually best general-purpose List

LinkedList
    ↓
Doubly Linked Nodes
    ↓
Fast operations at ends
    ↓
Slow indexed access

ArrayDeque
    ↓
Deque
    ↓
Fast operations at both ends
    ↓
Usually preferred for Queue/Stack/Deque
```
