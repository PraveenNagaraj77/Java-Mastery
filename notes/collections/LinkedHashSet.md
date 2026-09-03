# LinkedHashSet

## 1. What is LinkedHashSet?

`LinkedHashSet` is a class in the Java Collections Framework that implements the `Set` interface.

It combines:

* **Uniqueness** from `Set`
* **Fast lookup** through hashing
* **Insertion-order preservation** through linked structure

```java
Set<String> technologies = new LinkedHashSet<>();

technologies.add("Java");
technologies.add("React");
technologies.add("SQL");
technologies.add("Java");

System.out.println(technologies);
```

Output:

```text
[Java, React, SQL]
```

The duplicate `"Java"` is ignored, while the insertion order is preserved.

---

# 2. Why do we need LinkedHashSet?

Suppose we have:

```text
Java
React
Java
SQL
React
Node
```

We want:

```text
Java
React
SQL
Node
```

Requirements:

1. Remove duplicates
2. Preserve the order in which elements first appeared
3. Have efficient lookup

`LinkedHashSet` is a good fit.

### Pattern

```text
Unique elements
      +
Insertion order
      ↓
LinkedHashSet
```

---

# 3. Syntax

```java
Set<String> technologies = new LinkedHashSet<>();
```

Or:

```java
LinkedHashSet<String> technologies = new LinkedHashSet<>();
```

Prefer programming to the interface:

```java
Set<String> technologies = new LinkedHashSet<>();
```

Use `LinkedHashSet` as the variable type when you specifically need methods or behavior tied to that implementation.

---

# 4. Important Properties

| Property             | LinkedHashSet      |
| -------------------- | ------------------ |
| Duplicates           | ❌ Not allowed      |
| Insertion order      | ✅ Preserved        |
| Index-based access   | ❌ No               |
| `null`               | ✅ One null allowed |
| `add()` average      | O(1)               |
| `remove()` average   | O(1)               |
| `contains()` average | O(1)               |
| Sorted automatically | ❌ No               |

---

# 5. HashSet vs LinkedHashSet

This is one of the most important interview comparisons.

| Feature              | HashSet          | LinkedHashSet |
| -------------------- | ---------------- | ------------- |
| Duplicates           | ❌                | ❌             |
| Insertion order      | ❌ Not guaranteed | ✅ Preserved   |
| Fast lookup          | ✅                | ✅             |
| `add()` average      | O(1)             | O(1)          |
| `remove()` average   | O(1)             | O(1)          |
| `contains()` average | O(1)             | O(1)          |
| Allows one null      | ✅                | ✅             |
| Sorted               | ❌                | ❌             |

### Example

#### HashSet

```java
Set<Integer> numbers = new HashSet<>();

numbers.add(50);
numbers.add(10);
numbers.add(30);
numbers.add(20);

System.out.println(numbers);
```

The iteration order is **not guaranteed**.

#### LinkedHashSet

```java
Set<Integer> numbers = new LinkedHashSet<>();

numbers.add(50);
numbers.add(10);
numbers.add(30);
numbers.add(20);

System.out.println(numbers);
```

Output:

```text
[50, 10, 30, 20]
```

The insertion order is preserved.

---

# 6. How Does LinkedHashSet Work Internally?

Conceptually, `LinkedHashSet` combines two ideas:

```text
            LinkedHashSet
                 |
        -------------------
        |                 |
   Hash-based         Linked structure
    storage                 |
        |                   |
 Fast lookup          Insertion order
```

The hash-based structure helps determine where an element belongs.

The linked structure maintains the order in which elements were inserted.

Conceptually:

```text
Java → React → SQL → Node
```

When iterating, the elements are returned in this insertion order.

---

# 7. Why Are Duplicates Not Allowed?

`LinkedHashSet` follows the `Set` contract.

When adding an element:

```java
set.add("Java");
```

If `"Java"` is already present, the second insertion is rejected.

Example:

```java
Set<String> skills = new LinkedHashSet<>();

skills.add("Java");
skills.add("React");
skills.add("Java");

System.out.println(skills);
```

Output:

```text
[Java, React]
```

---

# 8. Return Value of add()

Just like `HashSet`, `LinkedHashSet.add()` returns a boolean.

```java
boolean result = skills.add("Java");
```

### First insertion

```java
skills.add("Java");
```

Returns:

```text
true
```

### Duplicate insertion

```java
skills.add("Java");
```

Returns:

```text
false
```

This makes it useful for duplicate detection.

```java
if (!skills.add("Java")) {
    System.out.println("Duplicate found");
}
```

---

# 9. Removing Duplicates While Preserving Order

One of the most common use cases.

```java
String[] technologies = {
        "Java",
        "React",
        "Java",
        "Spring Boot",
        "React",
        "SQL",
        "Java",
        "Node.js"
};

Set<String> uniqueTechnologies = new LinkedHashSet<>();

for (String technology : technologies) {
    uniqueTechnologies.add(technology);
}

System.out.println(uniqueTechnologies);
```

Output:

```text
[Java, React, Spring Boot, SQL, Node.js]
```

### Pattern

```text
Array / List
     ↓
LinkedHashSet
     ↓
Remove duplicates
     +
Preserve first occurrence order
```

---

# 10. Removing Duplicate Characters

`LinkedHashSet` can also be used with characters.

```java
String input = "programming";

Set<Character> uniqueCharacters = new LinkedHashSet<>();

for (char character : input.toCharArray()) {
    uniqueCharacters.add(character);
}

System.out.println(uniqueCharacters);
```

Output:

```text
[p, r, o, g, a, m, i, n]
```

The first occurrence of each character is preserved.

---

# 11. Iterating LinkedHashSet

### Enhanced for loop

```java
for (String technology : technologies) {
    System.out.println(technology);
}
```

### forEach()

```java
technologies.forEach(System.out::println);
```

### Iterator

```java
Iterator<String> iterator = technologies.iterator();

while (iterator.hasNext()) {
    System.out.println(iterator.next());
}
```

Iteration follows insertion order.

---

# 12. Important Methods

### add()

```java
technologies.add("Java");
```

Adds an element if it doesn't already exist.

---

### remove()

```java
technologies.remove("Java");
```

Removes the element.

---

### contains()

```java
technologies.contains("Java");
```

Checks whether an element exists.

---

### size()

```java
technologies.size();
```

Returns the number of unique elements.

---

### isEmpty()

```java
technologies.isEmpty();
```

Checks whether the set is empty.

---

### clear()

```java
technologies.clear();
```

Removes all elements.

---

# 13. Time Complexity

Average-case complexity:

| Operation    | LinkedHashSet |
| ------------ | ------------: |
| `add()`      |          O(1) |
| `remove()`   |          O(1) |
| `contains()` |          O(1) |
| `size()`     |          O(1) |
| Iteration    |          O(n) |

Space complexity:

```text
O(n)
```

because the collection stores the elements and maintains additional linked-order information.

---

# 14. LinkedHashSet vs ArrayList

| Feature           | ArrayList | LinkedHashSet |
| ----------------- | --------- | ------------- |
| Duplicates        | ✅         | ❌             |
| Insertion order   | ✅         | ✅             |
| Index access      | ✅         | ❌             |
| `get(index)`      | O(1)      | Not supported |
| `contains()`      | O(n)      | O(1) average  |
| Duplicate removal | Manual    | Automatic     |

### Example

If you need:

```text
Fast index access
```

Use:

```java
ArrayList
```

If you need:

```text
Unique elements + insertion order
```

Use:

```java
LinkedHashSet
```

---

# 15. LinkedHashSet vs TreeSet

This distinction becomes important when learning `TreeSet`.

| Feature              | LinkedHashSet            | TreeSet               |
| -------------------- | ------------------------ | --------------------- |
| Duplicates           | ❌                        | ❌                     |
| Insertion order      | ✅                        | ❌                     |
| Sorted order         | ❌                        | ✅                     |
| Average `contains()` | O(1)                     | O(log n)              |
| Allows one null      | ✅                        | Generally ❌           |
| Main purpose         | Unique + insertion order | Unique + sorted order |

### Mental model

```text
HashSet
    ↓
Unique

LinkedHashSet
    ↓
Unique + insertion order

TreeSet
    ↓
Unique + sorted order
```

---

# 16. Real-World Examples

## Search History

A user searches:

```text
Java
React
Java
SQL
React
Node
```

We want:

```text
Java
React
SQL
Node
```

Use:

```java
Set<String> searchHistory = new LinkedHashSet<>();
```

---

## Recently Viewed Products

```text
Laptop
Phone
Laptop
Keyboard
Phone
Monitor
```

We can preserve the first appearance while eliminating duplicates.

---

## Unique Tags

Input:

```text
java, backend, java, spring, backend
```

Result:

```text
java, backend, spring
```

while preserving the original order.

---

# 17. Common Mistakes

### Mistake 1 — Expecting index access

This doesn't work:

```java
technologies.get(0);
```

`LinkedHashSet` has no index.

---

### Mistake 2 — Expecting sorted order

`LinkedHashSet` preserves **insertion order**, not sorted order.

```text
Input:
50, 10, 30, 20

LinkedHashSet:
50, 10, 30, 20
```

If you want:

```text
10, 20, 30, 50
```

use `TreeSet`.

---

### Mistake 3 — Assuming HashSet and LinkedHashSet are identical

Both remove duplicates, but:

```text
HashSet
→ order not guaranteed

LinkedHashSet
→ insertion order preserved
```

---

### Mistake 4 — Calling implementation-specific methods through Set

If you have:

```java
Set<String> skills = new LinkedHashSet<>();
```

you can use methods defined by `Set`.

But if you need something specific to `LinkedHashSet`, the variable may need to be declared as `LinkedHashSet`.

---

# 18. DSA Patterns

### Pattern 1 — Unique + Preserve Order

```text
Need unique elements
+
Need original insertion order
        ↓
LinkedHashSet
```

---

### Pattern 2 — Remove duplicates from an array

```java
Set<Integer> unique = new LinkedHashSet<>();

for (int number : numbers) {
    unique.add(number);
}
```

---

### Pattern 3 — Remove duplicate characters

```java
Set<Character> uniqueCharacters = new LinkedHashSet<>();

for (char character : input.toCharArray()) {
    uniqueCharacters.add(character);
}
```

---

# 19. Interview Questions

### Q1. What is LinkedHashSet?

`LinkedHashSet` is a `Set` implementation that stores unique elements while preserving insertion order.

---

### Q2. What is the difference between HashSet and LinkedHashSet?

`HashSet` does not guarantee iteration order, whereas `LinkedHashSet` maintains insertion order.

---

### Q3. Does LinkedHashSet allow duplicates?

No. Duplicate elements are ignored.

---

### Q4. Does LinkedHashSet allow null?

Yes. It permits one `null` element.

---

### Q5. Does LinkedHashSet maintain sorted order?

No.

It maintains **insertion order**.

For sorted unique elements, use `TreeSet`.

---

### Q6. What is the average time complexity of `contains()`?

Average:

```text
O(1)
```

---

### Q7. Does LinkedHashSet provide index-based access?

No.

It does not have methods such as:

```java
get(0)
```

---

### Q8. When would you use LinkedHashSet instead of HashSet?

When you need both:

```text
Uniqueness
+
Insertion order
```

---

# 20. Interview Explanation

If an interviewer asks:

> "What is LinkedHashSet and when would you use it?"

You can answer:

> "`LinkedHashSet` is a Set implementation that stores unique elements while maintaining insertion order. It uses hashing for efficient average O(1) add, remove, and contains operations, while its linked structure maintains the iteration order. I would use it when I need to remove duplicates but still preserve the order in which elements were first inserted."

---

# 21. Final Mental Model

Remember the three Set implementations:

```text
                 SET
                  |
        ---------------------
        |         |         |
        ↓         ↓         ↓
    HashSet  LinkedHashSet  TreeSet
        |         |           |
     Unique     Unique      Unique
        |         |           |
      Fast      Fast        Sorted
               + Order
```

### One-line memory trick

```text
HashSet       → Unique
LinkedHashSet → Unique + Insertion Order
TreeSet       → Unique + Sorted Order
```

This distinction is one of the most important things to remember for Java Collections interviews.
