# Iterator

## 1. What is Iterator?

An **Iterator** is an object used to traverse elements of a collection one by one.

It provides a standard way to iterate through collections without depending on indexes.

```java
Iterator<String> iterator = collection.iterator();
```

The main methods are:

```java
hasNext()
next()
remove()
```

---

# 2. Why is Iterator Needed?

Not every collection supports index-based access.

For example:

```java
List<String> names = new ArrayList<>();
```

A List supports:

```java
names.get(0);
```

But a Set does not have indexes:

```java
Set<String> names = new HashSet<>();
```

Iterator provides a common way to traverse both.

```text
ArrayList ──┐
LinkedList ─┤
HashSet ────┤──→ Iterator
TreeSet ────┘
```

---

# 3. Iterator Interface

The Iterator interface is part of:

```java
java.util
```

Import:

```java
import java.util.Iterator;
```

Basic syntax:

```java
Iterator<E> iterator = collection.iterator();
```

Example:

```java
List<String> names = new ArrayList<>();

names.add("Praveen");
names.add("Rahul");
names.add("Anjali");

Iterator<String> iterator = names.iterator();

while (iterator.hasNext()) {

    String name = iterator.next();

    System.out.println(name);
}
```

Output:

```text
Praveen
Rahul
Anjali
```

---

# 4. `hasNext()`

`hasNext()` checks whether another element exists.

```java
iterator.hasNext();
```

It returns:

```text
true
```

or:

```text
false
```

Example:

```java
while (iterator.hasNext()) {
    // process element
}
```

### Important

`hasNext()` does **not** move the iterator.

It only checks whether another element is available.

---

# 5. `next()`

`next()` returns the next element and moves the iterator forward.

Example:

```java
Iterator<String> iterator = names.iterator();

System.out.println(iterator.next());
System.out.println(iterator.next());
```

If the collection is:

```text
[A, B, C]
```

Output:

```text
A
B
```

Conceptually:

```text
Initial:

↑
[A] [B] [C]


After next():

[A] ↑ [B] [C]


After next():

[A] [B] ↑ [C]
```

If `next()` is called when no element remains, it throws:

```text
NoSuchElementException
```

Therefore, normally use:

```java
while (iterator.hasNext()) {
    iterator.next();
}
```

---

# 6. Standard Iterator Pattern

The most common Iterator pattern is:

```java
Iterator<String> iterator = names.iterator();

while (iterator.hasNext()) {

    String name = iterator.next();

    System.out.println(name);
}
```

Mental model:

```text
hasNext()
    ↓
Is another element available?
    ↓
YES
    ↓
next()
    ↓
Get element
    ↓
Process element
    ↓
Repeat
```

---

# 7. `remove()`

Iterator provides a safe way to remove elements during iteration.

```java
iterator.remove();
```

It removes the **last element returned by `next()`**.

Example:

```java
List<Integer> numbers = new ArrayList<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);

Iterator<Integer> iterator = numbers.iterator();

while (iterator.hasNext()) {

    Integer number = iterator.next();

    if (number == 20) {
        iterator.remove();
    }
}

System.out.println(numbers);
```

Output:

```text
[10, 30]
```

---

# 8. Why Use `iterator.remove()`?

Consider:

```java
for (Integer number : numbers) {

    if (number == 20) {
        numbers.remove(number);
    }
}
```

This can cause:

```text
ConcurrentModificationException
```

because the collection is structurally modified directly while the iteration is in progress.

Instead:

```java
Iterator<Integer> iterator = numbers.iterator();

while (iterator.hasNext()) {

    Integer number = iterator.next();

    if (number == 20) {
        iterator.remove();
    }
}
```

The iterator performs the removal itself.

---

# 9. ConcurrentModificationException

A `ConcurrentModificationException` can occur when a collection is structurally modified while an iterator is traversing it, outside of the iterator's supported modification mechanism.

Example:

```java
Iterator<Integer> iterator = numbers.iterator();

while (iterator.hasNext()) {

    Integer number = iterator.next();

    if (number == 20) {
        numbers.remove(number);
    }
}
```

The problem is:

```text
Iterator
   ↓
expects collection state
   ↓
Collection modified directly
   ↓
Iterator detects unexpected modification
   ↓
ConcurrentModificationException
```

---

# 10. Structural Modification

A structural modification changes the size or structure of a collection.

Examples:

```java
list.add(10);
list.remove(10);
list.clear();
```

These are structural modifications.

For an `ArrayList`, this:

```java
list.set(0, 100);
```

changes an existing value but does not change the list's size.

Therefore, it is not a structural modification of the same kind.

---

# 11. `modCount`

When discussing fail-fast iterators, you may encounter the concept of `modCount`.

For implementations such as `ArrayList`, a modification count is used internally to help detect unexpected structural modifications.

Conceptually:

```text
ArrayList

modCount = 3
       ↓
Iterator created
       ↓
expectedModCount = 3
```

If the list is directly modified:

```text
ArrayList.modCount = 4
Iterator.expectedModCount = 3
```

The mismatch can be detected during iterator operations.

```text
4 != 3
   ↓
ConcurrentModificationException
```

### Important

`modCount` is an implementation detail used by particular collection implementations. Do not assume every Java collection uses exactly the same mechanism.

---

# 12. Fail-Fast Iterator

An iterator that detects certain unexpected structural modifications and may throw `ConcurrentModificationException` is commonly called a **fail-fast iterator**.

Conceptually:

```text
Unexpected modification
        ↓
Detection
        ↓
Fail early
        ↓
ConcurrentModificationException
```

### Interview answer

> A fail-fast iterator detects certain structural modifications made to a collection outside the iterator and may throw ConcurrentModificationException.

Use **"may"** rather than saying it is guaranteed to detect every modification.

---

# 13. ConcurrentModificationException Does Not Require Multiple Threads

The name can be misleading.

This can happen in a single thread:

```java
for (Integer number : numbers) {

    numbers.remove(number);
}
```

Multiple threads are not required.

It means the collection was modified while iteration was in progress in a way that violates the iterator's expected usage.

---

# 14. Enhanced For Loop and Iterator

When iterating over a collection using an enhanced `for` loop:

```java
for (String name : names) {
    System.out.println(name);
}
```

the collection's iteration mechanism is conceptually based on an iterator.

Conceptually similar to:

```java
Iterator<String> iterator = names.iterator();

while (iterator.hasNext()) {

    String name = iterator.next();

    System.out.println(name);
}
```

Therefore, the enhanced `for` loop does not provide a special safe mechanism for structural removal.

For removal during iteration, use the Iterator directly:

```java
iterator.remove();
```

---

# 15. Safe Removal Pattern

Memorize this:

```java
Iterator<Integer> iterator = numbers.iterator();

while (iterator.hasNext()) {

    Integer number = iterator.next();

    if (/* condition */) {
        iterator.remove();
    }
}
```

Example:

```java
if (number % 2 == 0) {
    iterator.remove();
}
```

Input:

```text
[10, 15, 20, 25, 30]
```

Result:

```text
[15, 25]
```

---

# 16. Iterator `remove()` Rules

There are important rules around `remove()`.

### Rule 1: `next()` must come first

Correct:

```java
iterator.next();
iterator.remove();
```

Incorrect:

```java
iterator.remove();
```

before calling `next()`.

---

### Rule 2: Remove applies to the last returned element

```java
Integer number = iterator.next();

iterator.remove();
```

The element stored in `number` is the one removed.

---

### Rule 3: Don't remove twice for one `next()`

This is invalid:

```java
iterator.next();

iterator.remove();
iterator.remove();
```

After the first `remove()`, another `next()` is required before another `remove()`.

---

# 17. ListIterator

`ListIterator` is a specialized iterator designed for `List` implementations.

It provides all the basic Iterator functionality plus:

* Backward traversal
* Adding elements
* Replacing elements

Import:

```java
import java.util.ListIterator;
```

Create one using:

```java
ListIterator<String> iterator = names.listIterator();
```

---

# 18. Iterator vs ListIterator

```text
Iterator
    ↓
Forward traversal
```

```text
ListIterator
    ↓
Forward + Backward traversal
```

Comparison:

| Feature            | Iterator | ListIterator |
| ------------------ | -------- | ------------ |
| Forward traversal  | ✅        | ✅            |
| Backward traversal | ❌        | ✅            |
| `remove()`         | ✅        | ✅            |
| `add()`            | ❌        | ✅            |
| `set()`            | ❌        | ✅            |
| Works with List    | ✅        | ✅            |
| Works with Set     | ✅        | ❌            |

---

# 19. `ListIterator.hasPrevious()`

Checks whether an element exists behind the current cursor.

```java
iterator.hasPrevious();
```

Returns:

```text
true
```

or:

```text
false
```

Example:

```java
while (iterator.hasPrevious()) {
    System.out.println(iterator.previous());
}
```

---

# 20. `ListIterator.previous()`

Moves backward and returns the previous element.

Example:

```java
List<String> names = new ArrayList<>();

names.add("Java");
names.add("Spring");
names.add("React");
names.add("Node");

ListIterator<String> iterator = names.listIterator();

while (iterator.hasNext()) {
    System.out.println(iterator.next());
}

while (iterator.hasPrevious()) {
    System.out.println(iterator.previous());
}
```

Output:

```text
Java
Spring
React
Node
Node
React
Spring
Java
```

---

# 21. Cursor Movement

Consider:

```text
Java → Spring → React → Node
```

Initially:

```text
↑
Java → Spring → React → Node
```

After moving forward through the entire list:

```text
Java → Spring → React → Node
                              ↑
```

Now:

```java
iterator.previous();
```

moves backward:

```text
Java → Spring → React → Node
                         ↑
```

and returns:

```text
Node
```

---

# 22. Important Pairing

Remember:

```text
hasNext()      → next()
hasPrevious()  → previous()
```

Do not mix them.

Correct:

```java
while (iterator.hasNext()) {
    iterator.next();
}
```

Correct:

```java
while (iterator.hasPrevious()) {
    iterator.previous();
}
```

---

# 23. `ListIterator.set()`

`set()` replaces the last element returned by `next()` or `previous()`.

Example:

```java
List<String> names = new ArrayList<>();

names.add("Java");
names.add("Spring");
names.add("React");
names.add("Node");

ListIterator<String> iterator = names.listIterator();

while (iterator.hasNext()) {

    String name = iterator.next();

    if (name.equals("React")) {
        iterator.set("React.js");
    }
}

System.out.println(names);
```

Output:

```text
[Java, Spring, React.js, Node]
```

### Important

`set()` does not change the size of the list.

It replaces an existing element.

---

# 24. `ListIterator.add()`

`ListIterator` can insert an element during iteration.

Example:

```java
List<String> names = new ArrayList<>();

names.add("A");
names.add("B");
names.add("C");

ListIterator<String> iterator = names.listIterator();

while (iterator.hasNext()) {

    String name = iterator.next();

    if (name.equals("B")) {
        iterator.add("X");
    }
}

System.out.println(names);
```

Output:

```text
[A, B, X, C]
```

---

# 25. ListIterator Example

```java
package com.javamastery.collections.iterator;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListIteratorBasic {

    public static void main(String[] args) {

        List<String> names = new ArrayList<>();

        names.add("Java");
        names.add("Spring");
        names.add("React");
        names.add("Node");

        ListIterator<String> iterator = names.listIterator();

        // Forward
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        // Backward
        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }

        // Replace
        while (iterator.hasNext()) {

            String name = iterator.next();

            if (name.equals("React")) {
                iterator.set("React.js");
            }
        }

        System.out.println(names);
    }
}
```

Output:

```text
Java
Spring
React
Node
Node
React
Spring
Java
[Java, Spring, React.js, Node]
```

---

# 26. Iterator vs Enhanced For Loop

### Enhanced for loop

```java
for (String name : names) {
    System.out.println(name);
}
```

Advantages:

* Simple
* Readable
* Good for normal traversal

### Iterator

```java
Iterator<String> iterator = names.iterator();

while (iterator.hasNext()) {
    String name = iterator.next();
}
```

Advantages:

* Explicit traversal control
* Safe removal through `iterator.remove()`
* Works naturally with collections without indexes

### ListIterator

```java
ListIterator<String> iterator = names.listIterator();
```

Additional capabilities:

* Forward traversal
* Backward traversal
* `add()`
* `set()`
* `remove()`

---

# 27. Iterator vs ListIterator vs Enhanced For

```text
Enhanced for
    ↓
Simple traversal

Iterator
    ↓
Forward traversal
    ↓
Safe removal

ListIterator
    ↓
Forward + backward
    ↓
Add + remove + replace
```

---

# 28. Common Mistakes

## Mistake 1: Calling `next()` without checking

Avoid:

```java
while (true) {
    System.out.println(iterator.next());
}
```

This eventually throws:

```text
NoSuchElementException
```

Prefer:

```java
while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

---

## Mistake 2: Directly modifying the collection

Avoid:

```java
while (iterator.hasNext()) {

    Integer number = iterator.next();

    if (number == 20) {
        numbers.remove(number);
    }
}
```

Prefer:

```java
while (iterator.hasNext()) {

    Integer number = iterator.next();

    if (number == 20) {
        iterator.remove();
    }
}
```

---

## Mistake 3: Using `next()` after `hasPrevious()`

Incorrect:

```java
while (iterator.hasPrevious()) {
    iterator.next();
}
```

Correct:

```java
while (iterator.hasPrevious()) {
    iterator.previous();
}
```

---

## Mistake 4: Calling `remove()` before `next()`

Incorrect:

```java
iterator.remove();
```

Correct:

```java
iterator.next();
iterator.remove();
```

---

## Mistake 5: Calling `remove()` twice

Incorrect:

```java
iterator.next();
iterator.remove();
iterator.remove();
```

Call `next()` again before another removal.

---

# 29. Real-World Example

Imagine an application storing active users:

```java
List<String> users = new ArrayList<>();

users.add("Praveen");
users.add("Rahul");
users.add("Inactive");
users.add("Anjali");
```

We want to remove inactive users while traversing.

```java
Iterator<String> iterator = users.iterator();

while (iterator.hasNext()) {

    String user = iterator.next();

    if (user.equals("Inactive")) {
        iterator.remove();
    }
}
```

Result:

```text
[Praveen, Rahul, Anjali]
```

This is a practical use of Iterator's safe removal capability.

---

# 30. Iterator and Different Collections

Iterator can be obtained from many Collection implementations:

```java
List<String> list = new ArrayList<>();
Iterator<String> iterator = list.iterator();
```

```java
Set<String> set = new HashSet<>();
Iterator<String> iterator = set.iterator();
```

```java
Set<String> set = new TreeSet<>();
Iterator<String> iterator = set.iterator();
```

The same basic traversal pattern works:

```java
while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

---

# 31. Iterator with Map

A `Map` does not directly implement `Collection`, so it does not have:

```java
map.iterator();
```

Instead, iterate through a Map's views:

```java
map.entrySet().iterator();
```

Example:

```java
Map<Integer, String> map = new HashMap<>();

map.put(101, "Praveen");
map.put(102, "Rahul");

Iterator<Map.Entry<Integer, String>> iterator =
        map.entrySet().iterator();

while (iterator.hasNext()) {

    Map.Entry<Integer, String> entry = iterator.next();

    System.out.println(
        entry.getKey() + " : " + entry.getValue()
    );
}
```

This is an important connection between Iterator and the Map topic you already studied.

---

# 32. Iterator Complexity

Iterator traversal itself is generally **O(n)** to visit all elements.

```java
while (iterator.hasNext()) {
    iterator.next();
}
```

For `n` elements:

```text
Time: O(n)
```

The exact cost of an individual `next()` can depend on the collection implementation.

For example, iterating through an `ArrayList` is generally efficient because its elements are stored in an array-backed structure.

---

# 33. Interview Questions

### Q1. What is an Iterator?

> Iterator is an interface used to traverse elements of a collection one by one without depending on indexes.

---

### Q2. What are the main Iterator methods?

```text
hasNext()
next()
remove()
```

Modern Java also provides:

```text
forEachRemaining()
```

---

### Q3. What does `hasNext()` do?

> It checks whether another element is available without advancing the iterator.

---

### Q4. What does `next()` do?

> It returns the next element and advances the iterator. If no element remains, it throws `NoSuchElementException`.

---

### Q5. What does `iterator.remove()` remove?

> It removes the last element returned by `next()`.

---

### Q6. Why use Iterator instead of directly removing from a collection during iteration?

> Iterator provides a supported removal mechanism that keeps the iterator's traversal state consistent and avoids the typical fail-fast modification problem.

---

### Q7. What is ConcurrentModificationException?

> It can occur when a collection is structurally modified during iteration outside the iterator's supported modification mechanism.

---

### Q8. Does ConcurrentModificationException require multiple threads?

> No. It can occur in a single thread when a collection is modified directly while being iterated.

---

### Q9. What is a fail-fast iterator?

> A fail-fast iterator detects certain unexpected structural modifications and may throw ConcurrentModificationException.

---

### Q10. Difference between Iterator and ListIterator?

> Iterator supports forward traversal, while ListIterator is designed for Lists and supports forward and backward traversal along with add, remove, and set operations.

---

### Q11. Can ListIterator work with HashSet?

No.

`ListIterator` is specifically designed for `List` implementations.

---

### Q12. Can Iterator work with HashSet?

Yes.

```java
Set<String> set = new HashSet<>();

Iterator<String> iterator = set.iterator();
```

---

### Q13. Can we traverse a Map using Iterator?

Not directly.

A Map is not a `Collection`.

We can iterate through:

```java
map.entrySet()
map.keySet()
map.values()
```

For example:

```java
Iterator<Map.Entry<Integer, String>> iterator =
        map.entrySet().iterator();
```

---

### Q14. Difference between `remove()` and `set()` in ListIterator?

```text
remove()
    ↓
Removes an element
    ↓
List size decreases
```

```text
set()
    ↓
Replaces an existing element
    ↓
List size remains unchanged
```

---

# 34. Interview Explanation

If an interviewer asks:

> Explain Iterator in Java.

You can answer:

> Iterator is an interface in the Java Collections Framework used to traverse collection elements one by one. The main methods are `hasNext()`, `next()`, and `remove()`. `hasNext()` checks whether another element exists, `next()` returns and advances to the next element, and `remove()` safely removes the last element returned by `next()`.
>
> Iterator is particularly useful when we need to remove elements during iteration. Direct structural modification of a collection while iterating can cause a `ConcurrentModificationException`, whereas using `iterator.remove()` provides the supported removal mechanism.
>
> For Lists, Java also provides `ListIterator`, which supports forward and backward traversal and additional operations such as `add()` and `set()`.

---

# 35. Quick Reference

## Iterator

```java
Iterator<String> iterator = collection.iterator();

while (iterator.hasNext()) {

    String value = iterator.next();

    if (/* condition */) {
        iterator.remove();
    }
}
```

```text
hasNext() → check next element
next()    → get next element
remove()  → remove last returned element
```

---

## ListIterator

```java
ListIterator<String> iterator = list.listIterator();
```

```text
hasNext()      → check forward
next()         → move forward

hasPrevious()  → check backward
previous()     → move backward

remove()       → remove
set()          → replace
add()          → insert
```

---

# 36. Final Mental Model

```text
                    Iterator
                       │
              ┌────────┼────────┐
              ↓        ↓        ↓
          hasNext()   next()  remove()
              │        │        │
            check     get      delete
              │        │        │
              └────────┴────────┘
                       │
                Forward traversal
```

For Lists:

```text
                 ListIterator
                      │
          ┌───────────┼───────────┐
          ↓           ↓           ↓
      Forward      Backward    Modify
          │           │           │
     next()       previous()   add()
     hasNext()    hasPrevious() set()
                                remove()
```

### ⭐ Remember

```text
Iterator
→ Forward traversal
→ Safe removal

ListIterator
→ Forward + Backward
→ Add + Remove + Replace

Enhanced for
→ Simple traversal
```

---

## Collections Progress

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
├── Iterator ✅
│
├── Comparable vs Comparator ⏳
│
└── Collection Interview Problems ⏳
```
