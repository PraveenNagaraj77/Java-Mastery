# TreeSet

## 1. What is TreeSet?

`TreeSet` is a class in the Java Collections Framework that implements the `Set` interface.

It stores:

* **Unique elements**
* **Sorted elements**

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(50);
numbers.add(10);
numbers.add(30);
numbers.add(20);
numbers.add(10);

System.out.println(numbers);
```

Output:

```text
[10, 20, 30, 50]
```

The duplicate `10` is ignored and the elements are automatically sorted.

### Simple Definition

> `TreeSet` is a Set implementation that stores unique elements in sorted order.

---

# 2. Why do we need TreeSet?

Different Set implementations provide different behaviors:

| Collection      | Duplicates | Order               |
| --------------- | ---------- | ------------------- |
| `HashSet`       | ❌          | No guaranteed order |
| `LinkedHashSet` | ❌          | Insertion order     |
| `TreeSet`       | ❌          | Sorted order        |

### Example

Suppose we have:

```java
[50, 10, 40, 20, 30]
```

With `HashSet`:

```text
Order not guaranteed
```

With `LinkedHashSet`:

```text
[50, 10, 40, 20, 30]
```

With `TreeSet`:

```text
[10, 20, 30, 40, 50]
```

### Mental Model

```text
HashSet
    ↓
Unique

LinkedHashSet
    ↓
Unique + Insertion Order

TreeSet
    ↓
Unique + Sorted Order
```

---

# 3. Syntax

```java
TreeSet<Integer> numbers = new TreeSet<>();
```

Better when programming to the interface:

```java
Set<Integer> numbers = new TreeSet<>();
```

However, if you need TreeSet-specific methods such as `first()`, `last()`, `subSet()`, etc., you can use:

```java
TreeSet<Integer> numbers = new TreeSet<>();
```

---

# 4. Basic Example

```java
import java.util.TreeSet;

public class TreeSetDemo {
    public static void main(String[] args) {

        TreeSet<Integer> numbers = new TreeSet<>();

        numbers.add(50);
        numbers.add(10);
        numbers.add(40);
        numbers.add(20);
        numbers.add(10);

        System.out.println(numbers);
    }
}
```

Output:

```text
[10, 20, 40, 50]
```

Notice:

1. Duplicate `10` was removed.
2. Elements were sorted automatically.

---

# 5. How does TreeSet work internally?

This is an important interview question.

`TreeSet` is internally backed by a `TreeMap`.

Conceptually:

```text
TreeSet
   ↓
TreeMap
   ↓
Red-Black Tree
   ↓
Self-balancing Binary Search Tree
```

A Red-Black Tree is a self-balancing binary search tree.

Because the tree remains balanced, operations such as:

```java
add()
remove()
contains()
```

generally take:

```text
O(log n)
```

---

# 6. Sorting

`TreeSet` automatically sorts elements according to their natural ordering.

### Integers

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(50);
numbers.add(10);
numbers.add(30);
numbers.add(20);

System.out.println(numbers);
```

Output:

```text
[10, 20, 30, 50]
```

### Strings

```java
TreeSet<String> names = new TreeSet<>();

names.add("Praveen");
names.add("Rahul");
names.add("Anjali");
names.add("Vijay");

System.out.println(names);
```

Output:

```text
[Anjali, Praveen, Rahul, Vijay]
```

Strings are sorted according to their natural lexicographical ordering.

---

# 7. Duplicate Elements

TreeSet does not allow duplicate elements.

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(10);
numbers.add(30);
numbers.add(20);

System.out.println(numbers);
```

Output:

```text
[10, 20, 30]
```

You can also check whether an element was actually added:

```java
boolean result = numbers.add(10);
```

If the element already exists:

```text
false
```

If it doesn't exist:

```text
true
```

---

# 8. first() — Smallest Element

`first()` returns the smallest element.

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(50);
numbers.add(10);
numbers.add(30);
numbers.add(20);

int smallest = numbers.first();

System.out.println(smallest);
```

Output:

```text
10
```

### Interview Pattern

> Need the smallest element from a sorted TreeSet → `first()`

---

# 9. last() — Largest Element

`last()` returns the largest element.

```java
int largest = numbers.last();

System.out.println(largest);
```

Output:

```text
50
```

### Remember

```java
numbers.first(); // smallest
numbers.last();  // largest
```

---

# 10. headSet()

`headSet()` returns elements that are **less than** the specified value.

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);
numbers.add(40);
numbers.add(50);

System.out.println(numbers.headSet(30));
```

Output:

```text
[10, 20]
```

The specified value `30` is excluded.

```text
headSet(30)
       ↓
elements < 30
```

---

# 11. tailSet()

`tailSet()` returns elements that are **greater than or equal to** the specified value.

```java
System.out.println(numbers.tailSet(30));
```

Output:

```text
[30, 40, 50]
```

```text
tailSet(30)
       ↓
elements >= 30
```

---

# 12. subSet()

`subSet()` returns elements within a range.

```java
System.out.println(numbers.subSet(20, 50));
```

Output:

```text
[20, 30, 40]
```

The first value is inclusive and the second value is exclusive.

```text
subSet(from, to)

from → inclusive
to   → exclusive
```

Therefore:

```java
numbers.subSet(20, 50);
```

means:

```text
20 <= element < 50
```

If we want values from `15` to `30`, inclusive:

```java
numbers.subSet(15, 31);
```

Output:

```text
[15, 20, 25, 30]
```

---

# 13. descendingSet()

`descendingSet()` returns the elements in descending order.

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(10);
numbers.add(30);
numbers.add(20);
numbers.add(40);

System.out.println(numbers.descendingSet());
```

Output:

```text
[40, 30, 20, 10]
```

### Remember

```java
numbers              → ascending
numbers.descendingSet() → descending
```

---

# 14. Higher()

`higher()` returns the **smallest element strictly greater than** the given value.

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);
numbers.add(40);
numbers.add(50);

System.out.println(numbers.higher(25));
```

Output:

```text
30
```

Because `30` is the smallest number greater than `25`.

### Important

```java
higher(x)
```

means:

```text
element > x
```

---

# 15. Lower()

`lower()` returns the **largest element strictly smaller than** the given value.

```java
System.out.println(numbers.lower(25));
```

Output:

```text
20
```

Because `20` is the largest number smaller than `25`.

### Important

```java
lower(x)
```

means:

```text
element < x
```

---

# 16. Ceiling()

`ceiling()` returns the smallest element that is **greater than or equal to** the given value.

```java
System.out.println(numbers.ceiling(25));
```

Output:

```text
30
```

But:

```java
System.out.println(numbers.ceiling(30));
```

Output:

```text
30
```

Because `30` itself exists.

### Remember

```text
ceiling(x)
    ↓
smallest element >= x
```

---

# 17. Floor()

`floor()` returns the largest element that is **less than or equal to** the given value.

```java
System.out.println(numbers.floor(25));
```

Output:

```text
20
```

And:

```java
System.out.println(numbers.floor(20));
```

Output:

```text
20
```

### Remember

```text
floor(x)
    ↓
largest element <= x
```

---

# 18. TreeSet Navigation Methods

These methods are very important for interviews:

| Method       | Meaning                 |
| ------------ | ----------------------- |
| `first()`    | Smallest element        |
| `last()`     | Largest element         |
| `higher(x)`  | Smallest element `> x`  |
| `lower(x)`   | Largest element `< x`   |
| `ceiling(x)` | Smallest element `>= x` |
| `floor(x)`   | Largest element `<= x`  |

### Easy Mental Model

```text
          lower(x)     x     higher(x)
             ↓         ↓         ↓
          smaller    target    greater

floor(x)    → <= x
ceiling(x)  → >= x
```

---

# 19. Common TreeSet Methods

```java
add()
remove()
contains()
size()
isEmpty()
clear()
first()
last()
headSet()
tailSet()
subSet()
descendingSet()
higher()
lower()
ceiling()
floor()
```

---

# 20. Null Values

Unlike `HashSet` and `LinkedHashSet`, you should generally not use `null` with a `TreeSet`.

Example:

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(10);
numbers.add(null);
```

This can result in:

```text
NullPointerException
```

Why?

Because TreeSet needs to compare elements to maintain sorted order, and `null` cannot be naturally compared with an integer.

### Interview Answer

> TreeSet generally does not support null elements because maintaining sorted order requires comparison between elements.

---

# 21. TreeSet with Strings

```java
TreeSet<String> technologies = new TreeSet<>();

technologies.add("React");
technologies.add("Java");
technologies.add("Node");
technologies.add("Spring Boot");

System.out.println(technologies);
```

Output is sorted according to String's natural ordering.

---

# 22. TreeSet vs HashSet vs LinkedHashSet

| Feature          | HashSet         | LinkedHashSet                | TreeSet                 |
| ---------------- | --------------- | ---------------------------- | ----------------------- |
| Duplicates       | ❌               | ❌                            | ❌                       |
| Insertion order  | ❌               | ✅                            | ❌                       |
| Sorted order     | ❌               | ❌                            | ✅                       |
| Index access     | ❌               | ❌                            | ❌                       |
| Average add      | O(1)            | O(1)                         | O(log n)                |
| Average contains | O(1)            | O(1)                         | O(log n)                |
| Average remove   | O(1)            | O(1)                         | O(log n)                |
| Null             | One allowed     | One allowed                  | Generally not supported |
| Main use         | Fast uniqueness | Uniqueness + insertion order | Uniqueness + sorting    |

---

# 23. TreeSet vs ArrayList

| Feature              | ArrayList                       | TreeSet            |
| -------------------- | ------------------------------- | ------------------ |
| Duplicates           | ✅                               | ❌                  |
| Insertion order      | ✅                               | ❌                  |
| Sorted automatically | ❌                               | ✅                  |
| Index access         | ✅                               | ❌                  |
| `get(index)`         | O(1)                            | Not supported      |
| `contains()`         | O(n)                            | O(log n)           |
| `add()`              | O(1) amortized                  | O(log n)           |
| Best for             | Ordered collection/index access | Unique sorted data |

---

# 24. Real-World Example

### Example: Unique Sorted Product Prices

Suppose an e-commerce application receives product prices:

```java
int[] prices = {999, 499, 1999, 999, 799, 499, 1499};
```

We want:

* Remove duplicate prices
* Automatically sort prices

TreeSet is a good choice:

```java
TreeSet<Integer> prices = new TreeSet<>();

for (int price : productPrices) {
    prices.add(price);
}

System.out.println(prices);
```

Output:

```text
[499, 799, 999, 1499, 1999]
```

We can easily find:

```java
prices.first();  // cheapest
prices.last();   // most expensive
```

And:

```java
prices.higher(1000);
```

can find the next available price above `1000`.

---

# 25. DSA Pattern — Unique + Sorted

When a problem says:

> Remove duplicates and return elements in sorted order.

Think:

```text
TreeSet
```

Example:

```java
int[] arr = {5, 2, 8, 2, 1, 5, 3};

TreeSet<Integer> set = new TreeSet<>();

for (int number : arr) {
    set.add(number);
}

System.out.println(set);
```

Output:

```text
[1, 2, 3, 5, 8]
```

---

# 26. DSA Pattern — Find Next Greater Element

If the problem asks:

> Find the smallest element greater than a target.

Think:

```java
higher()
```

Example:

```java
TreeSet<Integer> numbers = new TreeSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(30);
numbers.add(40);

System.out.println(numbers.higher(25));
```

Output:

```text
30
```

---

# 27. DSA Pattern — Find Previous Smaller Element

If the problem asks:

> Find the largest element smaller than a target.

Think:

```java
lower()
```

Example:

```java
System.out.println(numbers.lower(25));
```

Output:

```text
20
```

---

# 28. DSA Pattern — Closest Value

For problems involving values around a target, TreeSet's navigation methods are useful:

```java
lower()
higher()
floor()
ceiling()
```

Example:

```text
Numbers:
10 20 30 40 50

Target:
25
```

Results:

```text
lower(25)   → 20
higher(25)  → 30
floor(25)   → 20
ceiling(25) → 30
```

This makes TreeSet very useful for **nearest/closest value problems**.

---

# 29. Common Mistakes

### Mistake 1 — Expecting insertion order

```java
TreeSet<Integer> numbers = new TreeSet<>();
```

does NOT preserve insertion order.

It maintains sorted order.

---

### Mistake 2 — Expecting duplicates

```java
numbers.add(10);
numbers.add(10);
```

Only one `10` remains.

---

### Mistake 3 — Using index access

TreeSet does not support:

```java
numbers.get(0); // ❌
```

TreeSet is not index-based.

Use:

```java
numbers.first();
```

for the smallest element.

---

### Mistake 4 — Confusing `higher()` and `ceiling()`

```text
higher(x)  → > x
ceiling(x) → >= x
```

Example:

If `30` exists:

```java
higher(30)   → 40
ceiling(30)  → 30
```

---

### Mistake 5 — Confusing `lower()` and `floor()`

```text
lower(x) → < x
floor(x) → <= x
```

If `30` exists:

```java
lower(30) → 20
floor(30) → 30
```

---

### Mistake 6 — Forgetting `subSet()` upper bound is exclusive

```java
numbers.subSet(10, 30);
```

means:

```text
10 <= x < 30
```

Not:

```text
10 <= x <= 30
```

---

# 30. Interview Questions

### Q1. What is TreeSet?

> TreeSet is a Set implementation that stores unique elements in sorted order. It is backed internally by a TreeMap, which uses a Red-Black Tree.

### Q2. Does TreeSet allow duplicates?

> No. TreeSet follows Set semantics and stores only unique elements.

### Q3. Is TreeSet sorted?

> Yes. TreeSet maintains elements according to their natural ordering or a supplied Comparator.

### Q4. What is the time complexity of TreeSet operations?

> `add()`, `remove()`, and `contains()` generally take O(log n) time because TreeSet is backed by a self-balancing tree.

### Q5. What is the difference between HashSet and TreeSet?

> HashSet provides unique elements with no guaranteed iteration order and generally O(1) average lookup, while TreeSet provides unique elements in sorted order with O(log n) operations.

### Q6. What is the difference between LinkedHashSet and TreeSet?

> LinkedHashSet maintains insertion order, while TreeSet maintains sorted order.

### Q7. Does TreeSet allow null?

> Generally no. TreeSet needs to compare elements for ordering, and null cannot be naturally compared with non-null elements.

### Q8. What does `higher()` do?

> It returns the smallest element strictly greater than the given element.

### Q9. What does `ceiling()` do?

> It returns the smallest element greater than or equal to the given element.

### Q10. What does `lower()` do?

> It returns the largest element strictly smaller than the given element.

### Q11. What does `floor()` do?

> It returns the largest element less than or equal to the given element.

### Q12. What is TreeSet internally based on?

> TreeSet is backed by a TreeMap, which uses a Red-Black Tree.

---

# 31. Interview Explanation

If an interviewer asks:

### "Explain TreeSet."

You can answer:

> "TreeSet is a Set implementation in Java that stores unique elements in sorted order. Internally, it is backed by a TreeMap, which uses a self-balancing Red-Black Tree. Because of this, operations such as add, remove, and contains generally take O(log n) time. TreeSet also provides useful navigation methods such as first, last, higher, lower, ceiling, and floor, which make it useful for problems involving sorted unique data and finding nearest values."

---

# 32. TreeSet Coding Practice Completed

### Problem 1 — Remove duplicates and sort

Input:

```text
50 10 40 20 10 30 20
```

Output:

```text
[10, 20, 30, 40, 50]
```

---

### Problem 2 — Find smallest and largest

```java
int smallest = numbers.first();
int largest = numbers.last();
```

---

### Problem 3 — Find numbers in a range

```java
numbers.subSet(15, 31);
```

Output:

```text
[15, 20, 25, 30]
```

---

### Problem 4 — Find numbers below a value

```java
numbers.headSet(25);
```

---

### Problem 5 — Find numbers from a value onward

```java
numbers.tailSet(20);
```

---

### Problem 6 — Descending order

```java
numbers.descendingSet();
```

---

# 33. TreeSet Quick Reference

```java
TreeSet<Integer> numbers = new TreeSet<>();
```

### Basic

```java
numbers.add(10);
numbers.remove(10);
numbers.contains(10);
numbers.size();
numbers.isEmpty();
numbers.clear();
```

### First / Last

```java
numbers.first();
numbers.last();
```

### Range

```java
numbers.headSet(30);
numbers.tailSet(30);
numbers.subSet(10, 30);
```

### Navigation

```java
numbers.lower(30);
numbers.floor(30);
numbers.higher(30);
numbers.ceiling(30);
```

### Reverse

```java
numbers.descendingSet();
```

---

# 34. Final Mental Model

```text
                 TREESET
                    │
          ┌─────────┴─────────┐
          ↓                   ↓
       UNIQUE               SORTED
          │                   │
          └─────────┬─────────┘
                    ↓
              O(log n) operations
                    │
                    ↓
            Red-Black Tree
                    │
                    ↓
                TreeMap
```

### The most important rule:

> **Need UNIQUE + SORTED data → Think TreeSet.**

And for interview problems:

```text
Smallest              → first()
Largest               → last()

Before x              → headSet(x)
From x onward         → tailSet(x)
Range                 → subSet(x, y)

Next greater          → higher(x)
Previous smaller      → lower(x)

>= x                  → ceiling(x)
<= x                  → floor(x)

Descending            → descendingSet()
```
