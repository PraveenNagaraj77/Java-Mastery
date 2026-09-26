# Collectors — Interview Cheat Sheet

## 1. What is Collectors?

`Collectors` is a utility class in `java.util.stream` used with `Stream.collect()` to gather or summarize stream results.

```java
import java.util.stream.Collectors;
```

Mental model:

```text
Stream → collect() → Collector → Result
```

---

## 2. Most Important Collectors

| Collector | Purpose | Result |
|---|---|---|
| `toList()` | Collect elements | `List` |
| `toSet()` | Collect unique elements | `Set` |
| `toMap()` | Convert elements to key/value | `Map` |
| `joining()` | Join strings | `String` |
| `counting()` | Count elements | `Long` |
| `groupingBy()` | Group elements | `Map<K, List<T>>` |
| `partitioningBy()` | Split by true/false | `Map<Boolean, List<T>>` |
| `summarizingInt()` | Statistics | `IntSummaryStatistics` |
| `averagingInt()` | Calculate average | `Double` |

---

# 3. toList()

Collect stream elements into a `List`.

```java
List<Integer> result = numbers.stream()
        .filter(n -> n > 25)
        .collect(Collectors.toList());
```

```text
Stream → List
```

---

# 4. toSet()

Collect elements into a `Set`.

Duplicates are removed.

```java
Set<String> names = namesList.stream()
        .collect(Collectors.toSet());
```

```text
Stream → Set
```

### Interview Point

`HashSet` does not guarantee insertion order.

---

# 5. toMap()

Convert stream elements into a `Map`.

```java
Map<Integer, Product> productMap = products.stream()
        .collect(Collectors.toMap(
                Product::getId,
                p -> p
        ));
```

Mental model:

```text
key → value
```

### Duplicate Keys

If duplicate keys are possible, provide a merge function.

```java
Map<Integer, Product> productMap = products.stream()
        .collect(Collectors.toMap(
                Product::getId,
                p -> p,
                (existing, replacement) -> existing
        ));
```

Without a merge function, duplicate keys can cause:

```text
IllegalStateException
```

### Common Interview Question

**How do you handle duplicate keys with `toMap()`?**

Answer:

> Provide a merge function as the third argument.

---

# 6. joining()

Combine strings into one string.

```java
String result = technologies.stream()
        .collect(Collectors.joining(" | "));
```

Output:

```text
Java | Spring | React
```

### With Prefix and Suffix

```java
String result = names.stream()
        .collect(Collectors.joining(", ", "[", "]"));
```

Output:

```text
[Praveen, Arun, Kumar]
```

Mental model:

```text
joining() → String
```

---

# 7. counting()

Count elements.

```java
Long count = employees.stream()
        .collect(Collectors.counting());
```

Usually more useful with `groupingBy()`.

---

# 8. groupingBy()

One of the **most important Collectors interview patterns**.

Group objects based on a property.

```java
Map<String, List<Employee>> employeesByDepartment =
        employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment
                ));
```

Result:

```text
IT        → [employees]
Food      → [employees]
Logistics → [employees]
```

Mental model:

```text
groupingBy() → GROUP BY
```

Similar SQL concept:

```sql
GROUP BY department
```

---

# 9. groupingBy() + counting()

Count elements in each group.

```java
Map<String, Long> countByDepartment =
        employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.counting()
                ));
```

Result:

```text
IT=3
Food=2
Logistics=4
```

Mental model:

```text
groupingBy() + counting()
→ GROUP BY + COUNT
```

---

# 10. groupingBy() + averagingInt()

Calculate average for each group.

```java
Map<String, Double> averageSalaryByDepartment =
        employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingInt(Employee::getSalary)
                ));
```

Mental model:

```text
GROUP BY department
+ AVG(salary)
```

---

# 11. partitioningBy()

Split elements into exactly two groups:

```text
true
false
```

Example:

```java
Map<Boolean, List<Integer>> result =
        numbers.stream()
                .collect(Collectors.partitioningBy(
                        n -> n > 50
                ));
```

Result:

```text
true  → [55, 70, 85]
false → [10, 25, 40]
```

Mental model:

```text
partitioningBy() → TRUE / FALSE
```

### Difference

```text
groupingBy()      → multiple groups based on key

partitioningBy()  → exactly two groups: true / false
```

---

# 12. summarizingInt()

Get multiple statistics at once.

```java
IntSummaryStatistics stats =
        salaries.stream()
                .collect(Collectors.summarizingInt(
                        Integer::intValue
                ));
```

Provides:

```java
stats.getCount();
stats.getSum();
stats.getMin();
stats.getMax();
stats.getAverage();
```

Mental model:

```text
summarizingInt()
→ count + sum + min + max + average
```

Useful when you need several statistics from the same stream.

---

# 13. averagingInt()

Calculate average.

```java
Double average =
        employees.stream()
                .collect(Collectors.averagingInt(
                        Employee::getSalary
                ));
```

Returns:

```text
Double
```

Mental model:

```text
averagingInt() → Average → Double
```

---

# 14. Most Important Interview Patterns

### Group objects

```java
Collectors.groupingBy(Employee::getDepartment)
```

### Count by group

```java
Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.counting()
)
```

### Average by group

```java
Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.averagingInt(Employee::getSalary)
)
```

### Handle duplicate Map keys

```java
Collectors.toMap(
        Product::getId,
        Function.identity(),
        (existing, replacement) -> existing
)
```

### Split into two groups

```java
Collectors.partitioningBy(n -> n > 50)
```

### Get statistics

```java
Collectors.summarizingInt(Integer::intValue)
```

---

# 15. groupingBy() vs partitioningBy()

| | `groupingBy()` | `partitioningBy()` |
|---|---|---|
| Groups | Multiple | Two |
| Key | Any type | `Boolean` |
| Result | `Map<K, ...>` | `Map<Boolean, ...>` |
| Example | Department | Pass/Fail |

---

# 16. Collector vs collect()

Important distinction:

```java
stream.collect(...)
```

`collect()` is the **terminal operation**.

```java
Collectors.groupingBy(...)
Collectors.toList()
Collectors.toSet()
```

These create **Collector strategies** that tell `collect()` how to accumulate the stream.

Mental model:

```text
collect()     → performs collection
Collectors    → tells it HOW
```

---

# 17. Common Mistakes

### Mistake 1 — Forgetting duplicate keys

```java
Collectors.toMap(
        Product::getId,
        p -> p
);
```

If duplicate IDs exist → exception.

Use a merge function when required.

---

### Mistake 2 — Confusing groupingBy and partitioningBy

```text
groupingBy      → categories/groups
partitioningBy  → true/false
```

---

### Mistake 3 — Forgetting return types

```text
toList()        → List
toSet()         → Set
toMap()         → Map
joining()       → String
counting()      → Long
averagingInt()  → Double
```

---

# 18. 10-Second Cheat Sheet

```text
toList()        → List
toSet()         → Set
toMap()         → Map
joining()       → String
counting()      → Count
groupingBy()    → Group
partitioningBy  → TRUE / FALSE
summarizingInt  → Statistics
averagingInt    → Average
```

### Most Important

```text
groupingBy()
groupingBy() + counting()
toMap() + merge function
partitioningBy()
summarizingInt()
```

### Final Mental Model

```text
Stream
   ↓
collect()
   ↓
Collectors
   ↓
┌──────────────────────────────┐
│ toList       → List           │
│ toSet        → Set            │
│ toMap        → Map            │
│ joining      → String         │
│ groupingBy   → Groups         │
│ partitioning → True / False   │
│ counting     → Count          │
│ summarizing  → Statistics     │
│ averaging    → Average        │
└──────────────────────────────┘
```