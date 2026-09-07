# LinkedHashMap

## 1. What is LinkedHashMap?

`LinkedHashMap` is a class in the Java Collections Framework that stores data as **key-value pairs** while maintaining a predictable iteration order.

It extends `HashMap` and adds a linked structure to maintain ordering.

```java
import java.util.LinkedHashMap;
import java.util.Map;

Map<Integer, String> students = new LinkedHashMap<>();

students.put(101, "Praveen");
students.put(102, "Rahul");
students.put(103, "Anjali");
```

Output during iteration:

```text
101 : Praveen
102 : Rahul
103 : Anjali
```

The entries are returned in their insertion order by default.

---

# 2. Why do we need LinkedHashMap?

`HashMap` provides fast key-value lookup, but it does **not guarantee iteration order**.

Sometimes we need both:

```text
Fast lookup
+
Predictable order
```

That's where `LinkedHashMap` is useful.

### Example

Suppose we store a user's search history:

```text
Java
React
Spring Boot
Docker
```

We may want to retrieve data quickly while still displaying the searches in the order they were added.

`LinkedHashMap` is suitable for this type of requirement.

---

# 3. Declaration

Preferred:

```java
Map<Integer, String> students = new LinkedHashMap<>();
```

If LinkedHashMap-specific methods or constructors are required:

```java
LinkedHashMap<Integer, String> students =
        new LinkedHashMap<>();
```

### Import

```java
import java.util.LinkedHashMap;
```

---

# 4. Basic Operations

## put()

Adds a key-value pair.

```java
Map<Integer, String> students = new LinkedHashMap<>();

students.put(101, "Praveen");
students.put(102, "Rahul");
students.put(103, "Anjali");
```

Result:

```text
101 → Praveen
102 → Rahul
103 → Anjali
```

Keys must be unique.

---

## Duplicate Key

If the same key is inserted again, the value is replaced.

```java
students.put(101, "Nagaraj");
```

Now:

```text
101 → Nagaraj
102 → Rahul
103 → Anjali
```

The key `101` does not create another entry.

---

# 5. get()

Retrieves a value using its key.

```java
String name = students.get(101);

System.out.println(name);
```

Output:

```text
Praveen
```

If the key doesn't exist:

```java
students.get(999);
```

returns:

```text
null
```

---

# 6. containsKey()

Checks whether a key exists.

```java
System.out.println(students.containsKey(102));
```

Output:

```text
true
```

---

# 7. containsValue()

Checks whether a value exists.

```java
System.out.println(students.containsValue("Rahul"));
```

Output:

```text
true
```

Unlike `containsKey()`, this generally requires searching through the values.

---

# 8. remove()

Removes an entry using its key.

```java
students.remove(103);
```

---

# 9. size()

Returns the number of entries.

```java
System.out.println(students.size());
```

---

# 10. isEmpty()

Checks whether the map contains no entries.

```java
System.out.println(students.isEmpty());
```

---

# 11. clear()

Removes all entries.

```java
students.clear();
```

---

# 12. putIfAbsent()

Adds a key only if the key doesn't already exist.

```java
students.put(101, "Praveen");

students.putIfAbsent(101, "Nagaraj");
```

The existing value is not replaced.

Result:

```text
101 → Praveen
```

This is useful when we don't want to overwrite an existing value accidentally.

---

# 13. getOrDefault()

Returns the existing value if the key exists; otherwise returns the provided default.

```java
String name = students.getOrDefault(999, "Unknown");

System.out.println(name);
```

Output:

```text
Unknown
```

---

# 14. Iterating LinkedHashMap

The preferred way to iterate through both keys and values is `entrySet()`.

```java
for (Map.Entry<Integer, String> entry : students.entrySet()) {

    System.out.println(
        entry.getKey() + " : " + entry.getValue()
    );
}
```

Because LinkedHashMap maintains insertion order, iteration follows that order.

---

# 15. keySet()

Used when we only need keys.

```java
for (Integer key : students.keySet()) {
    System.out.println(key);
}
```

---

# 16. values()

Used when we only need values.

```java
for (String value : students.values()) {
    System.out.println(value);
}
```

---

# 17. Insertion Order

By default, LinkedHashMap maintains **insertion order**.

```java
LinkedHashMap<Integer, String> map = new LinkedHashMap<>();

map.put(101, "Praveen");
map.put(103, "Anjali");
map.put(102, "Rahul");

for (Map.Entry<Integer, String> entry : map.entrySet()) {
    System.out.println(entry.getKey() + " : " + entry.getValue());
}
```

Output:

```text
101 : Praveen
103 : Anjali
102 : Rahul
```

Notice that the keys are not sorted.

They appear in the order they were inserted.

---

# 18. LinkedHashMap Does NOT Sort Keys

This is important.

```java
map.put(30, "C");
map.put(10, "A");
map.put(20, "B");
```

Output:

```text
30 : C
10 : A
20 : B
```

It does NOT automatically produce:

```text
10 : A
20 : B
30 : C
```

For sorted keys, use `TreeMap`.

---

# 19. Access Order

LinkedHashMap can also maintain **access order** instead of insertion order.

Constructor:

```java
LinkedHashMap<Integer, String> map =
        new LinkedHashMap<>(16, 0.75f, true);
```

The third parameter:

```text
true
```

means:

```text
accessOrder = true
```

---

# 20. Access Order Example

```java
LinkedHashMap<Integer, String> map =
        new LinkedHashMap<>(16, 0.75f, true);

map.put(101, "Praveen");
map.put(102, "Rahul");
map.put(103, "Anjali");

map.get(101);

for (Map.Entry<Integer, String> entry : map.entrySet()) {
    System.out.println(entry.getKey() + " : " + entry.getValue());
}
```

Output:

```text
102 : Rahul
103 : Anjali
101 : Praveen
```

Why?

Initially:

```text
101 → 102 → 103
```

After:

```java
map.get(101);
```

`101` becomes the most recently accessed entry:

```text
102 → 103 → 101
```

---

# 21. Insertion Order vs Access Order

Remember this:

```text
LinkedHashMap
│
├── false → Insertion Order
│
└── true  → Access Order
```

Example:

```java
new LinkedHashMap<>(16, 0.75f, false);
```

means insertion order.

```java
new LinkedHashMap<>(16, 0.75f, true);
```

means access order.

The default constructor uses insertion-order behavior.

---

# 22. What Counts as an Access?

In access-order mode, operations that access an existing mapping can affect its position.

The most common example is:

```java
map.get(key);
```

For example:

```java
map.get(101);
```

moves `101` toward the most-recently-accessed end.

This behavior is one of the reasons LinkedHashMap can be useful for implementing LRU-style caches.

---

# 23. Internal Working

Conceptually, LinkedHashMap combines two ideas:

```text
Hash-based lookup
        +
Linked ordering structure
```

A simplified mental model:

```text
Key
 ↓
hashCode()
 ↓
Hash-based location
 ↓
Find entry
```

At the same time, entries are connected to maintain their iteration order.

Conceptually:

```text
Entry 1 ↔ Entry 2 ↔ Entry 3 ↔ Entry 4
```

The hash-based structure provides efficient lookup, while the linked structure maintains predictable iteration order.

---

# 24. LinkedHashMap vs HashMap

| Feature                    | HashMap             | LinkedHashMap   |
| -------------------------- | ------------------- | --------------- |
| Key-value pairs            | Yes                 | Yes             |
| Unique keys                | Yes                 | Yes             |
| Duplicate values           | Yes                 | Yes             |
| Guaranteed iteration order | No                  | Yes             |
| Default order              | No guaranteed order | Insertion order |
| Access order option        | No                  | Yes             |
| Average get()              | O(1)                | O(1)            |
| Average put()              | O(1)                | O(1)            |
| Memory                     | Lower               | Higher          |

### Mental model

```text
HashMap
    ↓
Key → Value
Fast lookup
No guaranteed order

LinkedHashMap
    ↓
Key → Value
Fast lookup
+ insertion/access ordering
```

---

# 25. LinkedHashMap vs TreeMap

| Feature           | LinkedHashMap  | TreeMap                                             |
| ----------------- | -------------- | --------------------------------------------------- |
| Key-value pairs   | Yes            | Yes                                                 |
| Unique keys       | Yes            | Yes                                                 |
| Insertion order   | Yes            | No                                                  |
| Sorted keys       | No             | Yes                                                 |
| Average/basic get | O(1)           | O(log n)                                            |
| Average/basic put | O(1)           | O(log n)                                            |
| Null key          | Allows one     | Generally does not allow null with natural ordering |
| Main use          | Preserve order | Sorted keys                                         |

### Mental model

```text
HashMap       → Key-Value
LinkedHashMap → Key-Value + Insertion Order
TreeMap       → Key-Value + Sorted Key Order
```

---

# 26. Null Keys and Null Values

Like HashMap, LinkedHashMap permits:

* One `null` key
* Multiple `null` values

Example:

```java
LinkedHashMap<Integer, String> map =
        new LinkedHashMap<>();

map.put(null, "Unknown");
map.put(101, null);
map.put(102, null);
```

This is valid.

---

# 27. Time Complexity

Average-case complexity:

| Operation     | Complexity |
| ------------- | ---------: |
| put()         |       O(1) |
| get()         |       O(1) |
| remove()      |       O(1) |
| containsKey() |       O(1) |
| size()        |       O(1) |
| Iteration     |       O(n) |

LinkedHashMap requires additional memory compared with HashMap because it maintains ordering information.

---

# 28. Why is LinkedHashMap Slightly More Expensive Than HashMap?

HashMap mainly needs its hash-based structure.

LinkedHashMap additionally maintains links between entries to preserve ordering.

Conceptually:

```text
HashMap:

Bucket → Entry


LinkedHashMap:

Bucket → Entry
           ↕
       Linked Entry
           ↕
       Linked Entry
```

Therefore LinkedHashMap has some additional memory overhead.

---

# 29. Real-World Example — Search History

Suppose we want to store a user's searches while preserving the order:

```java
LinkedHashMap<Integer, String> searches =
        new LinkedHashMap<>();

searches.put(1, "Java");
searches.put(2, "Spring Boot");
searches.put(3, "React");
searches.put(4, "Docker");
```

Iteration:

```text
1 → Java
2 → Spring Boot
3 → React
4 → Docker
```

The insertion order is preserved.

---

# 30. Real-World Example — Recently Viewed Products

```java
LinkedHashMap<Integer, String> products =
        new LinkedHashMap<>();

products.put(101, "Laptop");
products.put(102, "Keyboard");
products.put(103, "Mouse");
```

This can be useful when displaying recently added/viewed items in a predictable order.

For more advanced "most recently accessed" behavior, access-order mode can be used.

---

# 31. LRU Cache Concept

LinkedHashMap's access-order mode makes it useful for implementing an **LRU (Least Recently Used) cache**.

```java
LinkedHashMap<Integer, String> cache =
        new LinkedHashMap<>(16, 0.75f, true);
```

Conceptually:

```text
Least Recently Used          Most Recently Used
          ↓                         ↓
        Entry  →  Entry  →  Entry
```

When an entry is accessed:

```java
cache.get(key);
```

it can move toward the most-recently-used end.

If the cache exceeds its capacity, the least-recently-used entry can be removed.

For Java interviews, remember:

```text
LinkedHashMap + accessOrder = true
                ↓
          LRU cache building block
```

---

# 32. Coding Practice

## Problem 1 — Preserve Insertion Order

Given:

```java
int[] numbers = {5, 2, 8, 5, 2, 9, 8};
```

Use LinkedHashMap to count frequencies.

Expected:

```text
5 → 2
2 → 2
8 → 2
9 → 1
```

Solution pattern:

```java
LinkedHashMap<Integer, Integer> frequency =
        new LinkedHashMap<>();

for (int number : numbers) {

    frequency.put(
        number,
        frequency.getOrDefault(number, 0) + 1
    );
}
```

The important part is that LinkedHashMap preserves the order in which each **new key** first appears.

---

# 33. Frequency Counting Pattern

The general pattern:

```java
frequency.put(
    value,
    frequency.getOrDefault(value, 0) + 1
);
```

This works with:

```text
HashMap
LinkedHashMap
```

The difference is ordering.

```text
HashMap
→ frequency counting
→ order not guaranteed

LinkedHashMap
→ frequency counting
→ first-seen key order preserved
```

---

# 34. Remove Duplicates While Preserving Order

For a simple sequence of values, LinkedHashSet is usually the more natural choice when only uniqueness is needed.

```java
LinkedHashSet<Integer> set =
        new LinkedHashSet<>();
```

But if we also need a value associated with each unique key, LinkedHashMap becomes useful.

Example:

```text
Input:
A B A C B

First occurrence:
A B C
```

LinkedHashMap can preserve the order of the unique keys.

---

# 35. Common Mistakes

### Mistake 1 — Assuming LinkedHashMap sorts keys

Wrong assumption:

```text
LinkedHashMap → sorted keys
```

Correct:

```text
LinkedHashMap → insertion/access order
TreeMap → sorted key order
```

---

### Mistake 2 — Thinking duplicate keys create duplicate entries

```java
map.put(101, "Praveen");
map.put(101, "Rahul");
```

There is still only one key:

```text
101 → Rahul
```

The value is replaced.

---

### Mistake 3 — Confusing key order with value order

LinkedHashMap maintains the order of its **entries**, not an independent ordering of values.

---

### Mistake 4 — Forgetting accessOrder

These are different:

```java
new LinkedHashMap<>();
```

and:

```java
new LinkedHashMap<>(16, 0.75f, true);
```

The first uses insertion-order behavior.

The second uses access-order behavior.

---

### Mistake 5 — Assuming all operations reorder the map

The important idea is that access-order mode tracks accesses to existing entries.

For example:

```java
map.get(101);
```

can move the entry.

---

# 36. Interview Questions

### Q1. What is LinkedHashMap?

`LinkedHashMap` is a Map implementation that stores key-value pairs like HashMap while maintaining a predictable iteration order.

---

### Q2. What is the difference between HashMap and LinkedHashMap?

HashMap does not guarantee iteration order, while LinkedHashMap maintains insertion order by default and can optionally maintain access order.

---

### Q3. Does LinkedHashMap allow duplicate keys?

No.

Keys must be unique.

If the same key is inserted again, its value is replaced.

---

### Q4. Does LinkedHashMap allow duplicate values?

Yes.

```java
map.put(1, "Java");
map.put(2, "Java");
```

is valid.

---

### Q5. Does LinkedHashMap allow null?

Yes.

It permits one null key and multiple null values.

---

### Q6. Does LinkedHashMap sort keys?

No.

Use TreeMap when sorted key order is required.

---

### Q7. What is access order?

Access order means entries are ordered according to their recent access rather than their original insertion order.

It can be enabled using:

```java
new LinkedHashMap<>(16, 0.75f, true);
```

---

### Q8. Why is LinkedHashMap useful for LRU caches?

Because access-order mode moves recently accessed entries toward the end, making it possible to identify the least recently used entry at the opposite end.

---

### Q9. What is the time complexity of get() in LinkedHashMap?

Average:

```text
O(1)
```

---

### Q10. Why does LinkedHashMap use more memory than HashMap?

Because it maintains additional links between entries to preserve iteration order.

---

### Q11. LinkedHashMap vs TreeMap?

```text
LinkedHashMap → predictable insertion/access order
TreeMap       → sorted key order
```

---

# 37. Interview Explanation

If an interviewer asks:

> What is LinkedHashMap?

You can answer:

> "LinkedHashMap is a Map implementation that extends HashMap's hash-based key-value storage while maintaining a predictable iteration order. By default, it maintains insertion order, but it can also be configured for access order. Average lookup, insertion, and removal are O(1). It's useful when I need fast key-based access while also needing predictable ordering, and its access-order mode can be used as a building block for LRU caches."

---

# 38. Quick Comparison

```text
                MAP
                 │
       ┌─────────┼──────────┐
       ↓         ↓          ↓
   HashMap   LinkedHashMap  TreeMap
       │         │          │
       ↓         ↓          ↓
  No order   Insertion/   Sorted
             Access order  keys
       │         │          │
       └─────────┼──────────┘
                 ↓
          Key → Value
```

---

# 39. Mental Model

The most important thing to remember:

```text
HashMap
    ↓
Fast Key → Value lookup
No guaranteed order


LinkedHashMap
    ↓
Fast Key → Value lookup
+
Predictable order


TreeMap
    ↓
Key → Value
+
Sorted keys
```

And:

```text
LinkedHashMap
       │
       ├── default → insertion order
       │
       └── true    → access order
                         ↓
                       LRU
```

---

# 40. Key Takeaways

Before moving to TreeMap, make sure you remember:

* `LinkedHashMap` stores key-value pairs.
* Keys are unique.
* Values can be duplicated.
* Average `put()`, `get()`, `remove()` are O(1).
* Default behavior preserves insertion order.
* It does not sort keys.
* It supports one null key and multiple null values.
* `entrySet()` is commonly used for iteration.
* `getOrDefault()` is useful for frequency counting.
* `putIfAbsent()` prevents overwriting an existing mapping.
* `new LinkedHashMap<>(16, 0.75f, true)` enables access-order behavior.
* Access-order mode is useful for LRU cache implementations.
* LinkedHashMap uses additional memory compared with HashMap because it maintains ordering links.

## Final Mental Model

```text
HashMap
→ Key + Value
→ Fast
→ No guaranteed order

LinkedHashMap
→ Key + Value
→ Fast
→ Insertion / Access order

TreeMap
→ Key + Value
→ Sorted keys
→ O(log n)
```
