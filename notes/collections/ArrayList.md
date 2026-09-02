# ArrayList

## 1. What is ArrayList?

`ArrayList` is a resizable array implementation of the `List` interface in Java.

It stores elements in insertion order and allows duplicate and `null` values.

```java
List<String> skills = new ArrayList<>();
```

---

## 2. Why do we need ArrayList?

A normal array has a fixed size:

```java
String[] skills = new String[5];
```

If more elements are needed, a new array must be created manually.

`ArrayList` manages resizing automatically.

```java
List<String> skills = new ArrayList<>();

skills.add("Java");
skills.add("Spring Boot");
skills.add("React");
```

---

## 3. How does ArrayList work internally?

ArrayList internally uses an array to store elements.

Conceptually:

```text
ArrayList
    ↓
Internal Array
    ↓
[Java] [Spring] [React] [SQL]
```

When the current capacity is insufficient:

```text
Old Array
[Java] [Spring] [React]

          ↓ resize

New Array
[Java] [Spring] [React] [SQL] [...]
```

The existing elements are copied into the new internal array.

---

## 4. Declaration

Recommended:

```java
List<String> skills = new ArrayList<>();
```

Instead of:

```java
ArrayList<String> skills = new ArrayList<>();
```

### Why?

Because we should generally **program to the interface, not the implementation**.

This allows the implementation to be changed easily:

```java
List<String> skills = new LinkedList<>();
```

---

## 5. Important Properties

| Property                  | ArrayList |
| ------------------------- | --------- |
| Maintains insertion order | Yes       |
| Allows duplicates         | Yes       |
| Allows null               | Yes       |
| Index-based access        | Yes       |
| Dynamic size              | Yes       |
| Thread-safe               | No        |
| Random access             | Fast      |

---

## 6. Important Methods

### `add()`

Adds an element.

```java
skills.add("Java");
```

### `get()`

Retrieves an element using its index.

```java
String skill = skills.get(0);
```

### `set()`

Replaces an element.

```java
skills.set(0, "Java 21");
```

### `remove(index)`

Removes the element at the specified index.

```java
skills.remove(2);
```

### `remove(value)`

Removes the first matching value.

```java
skills.remove("React");
```

### `contains()`

Checks whether an element exists.

```java
skills.contains("Java");
```

### `size()`

Returns the number of elements.

```java
skills.size();
```

### `isEmpty()`

Checks whether the list contains no elements.

```java
skills.isEmpty();
```

### `clear()`

Removes all elements.

```java
skills.clear();
```

---

## 7. Iterating Through ArrayList

### Traditional for loop

Useful when the index is required.

```java
for (int i = 0; i < skills.size(); i++) {
    System.out.println(i + " - " + skills.get(i));
}
```

### Enhanced for loop

Useful when only the elements are required.

```java
for (String skill : skills) {
    System.out.println(skill);
}
```

### forEach()

```java
skills.forEach(skill -> System.out.println(skill));
```

Method reference:

```java
skills.forEach(System.out::println);
```

### Iterator

Useful when safely removing elements while iterating.

```java
Iterator<String> iterator = skills.iterator();

while (iterator.hasNext()) {
    String skill = iterator.next();

    if (skill.equals("React")) {
        iterator.remove();
    }
}
```

---

## 8. Time Complexity

| Operation             |     Complexity |
| --------------------- | -------------: |
| `get(index)`          |           O(1) |
| `set(index)`          |           O(1) |
| `add(element)`        | O(1) amortized |
| `add(index, element)` |           O(n) |
| `remove(index)`       | O(n) generally |
| `contains(element)`   |           O(n) |
| `size()`              |           O(1) |

### Why is middle removal O(n)?

Because after removing an element, the elements after it must shift left.

```text
Before:

[Java, Spring, React, SQL, Docker]
              ↑
            remove

After:

[Java, Spring, SQL, Docker]
```

`SQL` and `Docker` must be shifted.

---

## 9. Important Interview Trap — `remove()`

With `List<Integer>`:

```java
List<Integer> numbers = new ArrayList<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);

numbers.remove(1);
```

This removes the element at **index 1**, which is `20`.

If you want to remove the value `1`:

```java
numbers.remove(Integer.valueOf(1));
```

This happens because `ArrayList` has overloaded `remove()` methods:

```java
remove(int index)
remove(Object value)
```

---

## 10. ConcurrentModificationException

Don't directly modify an ArrayList while using an enhanced `for` loop.

Avoid:

```java
for (String skill : skills) {
    if (skill.equals("React")) {
        skills.remove(skill);
    }
}
```

This can result in:

```text
ConcurrentModificationException
```

Use `Iterator` instead:

```java
Iterator<String> iterator = skills.iterator();

while (iterator.hasNext()) {
    String skill = iterator.next();

    if (skill.equals("React")) {
        iterator.remove();
    }
}
```

---

# 11. ArrayList vs Array

| Array                | ArrayList          |
| -------------------- | ------------------ |
| Fixed size           | Dynamic size       |
| Can store primitives | Stores objects     |
| `.length`            | `.size()`          |
| Less overhead        | More functionality |
| Manual resizing      | Automatic resizing |

Example:

```java
int[] numbers = new int[5];
```

vs

```java
List<Integer> numbers = new ArrayList<>();
```

---

# 12. Real-World Example

A job portal can use an ArrayList to store a candidate's skills:

```java
List<String> skills = new ArrayList<>();

skills.add("Java");
skills.add("Spring Boot");
skills.add("React");
skills.add("SQL");
skills.add("Docker");
```

The candidate can add, remove, or update skills dynamically.

---

# 13. Common Mistakes

### Mistake 1 — Invalid index

```java
skills.get(10);
```

If the list doesn't contain index `10`:

```text
IndexOutOfBoundsException
```

---

### Mistake 2 — Confusing `size()` and `length`

Array:

```java
array.length
```

ArrayList:

```java
list.size()
```

---

### Mistake 3 — Removing during enhanced for

```java
for (String skill : skills) {
    skills.remove(skill);
}
```

Can cause:

```text
ConcurrentModificationException
```

Use `Iterator`.

---

### Mistake 4 — Assuming `remove(1)` removes value `1`

With `List<Integer>`:

```java
list.remove(1);
```

means **remove index 1**.

Use:

```java
list.remove(Integer.valueOf(1));
```

to remove the value.

---

# 14. Interview Questions

### Basic

**Q1. What is ArrayList?**

ArrayList is a resizable array implementation of the `List` interface.

**Q2. Does ArrayList allow duplicates?**

Yes.

**Q3. Does ArrayList maintain insertion order?**

Yes.

**Q4. Does ArrayList allow null values?**

Yes.

**Q5. Is ArrayList thread-safe?**

No, ArrayList is not synchronized by default.

---

### Intermediate

**Q6. How does ArrayList grow?**

When its current capacity is insufficient, it creates a larger internal array and copies the existing elements into it.

**Q7. Why is `get()` O(1)?**

Because ArrayList provides direct index-based access to its underlying array.

**Q8. Why is removing from the middle O(n)?**

Because elements after the removed element must be shifted.

**Q9. ArrayList vs LinkedList?**

ArrayList is backed by a dynamic array and provides fast random access.

LinkedList is node-based and is better suited to certain insertion/removal operations when the position/node is already known.

**Q10. Why use `List<String> list = new ArrayList<>();`?**

Because we should generally program to the interface rather than the concrete implementation.

---

# 15. Interview Explanation

If the interviewer asks:

### "Explain ArrayList."

A good answer:

> "ArrayList is a resizable array implementation of the List interface. It maintains insertion order, allows duplicates and null values, and provides fast index-based access. Internally, it uses an array, and when the current capacity is insufficient, it creates a larger array and copies the existing elements. `get()` and `set()` are O(1), while insertion or removal in the middle is generally O(n) because elements need to be shifted."

---

# 16. Key Takeaways

```text
ArrayList
   ↓
Resizable Array
   ↓
Ordered
   ↓
Duplicates allowed
   ↓
Null allowed
   ↓
Fast index access
   ↓
get() → O(1)
   ↓
Middle insertion/removal → O(n)
   ↓
Iterator → safe removal during iteration
```

### Remember

**ArrayList = Dynamic Array + Fast Index Access**
