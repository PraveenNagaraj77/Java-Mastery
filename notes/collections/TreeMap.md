# TreeMap in Java

## 1. What is TreeMap?

`TreeMap` is a class in the Java Collections Framework that stores **key-value pairs sorted by their keys**.

```java
TreeMap<Integer, String> map = new TreeMap<>();

map.put(105, "Vijay");
map.put(101, "Praveen");
map.put(104, "Anjali");
map.put(102, "Rahul");
```

Output when iterated:

```text
101 : Praveen
102 : Rahul
104 : Anjali
105 : Vijay
```

The keys are automatically maintained in sorted order.

### Key characteristics

* Stores key-value pairs
* Keys must be unique
* Keys are sorted
* Does not maintain insertion order
* Supports natural ordering or custom `Comparator`
* Does not allow `null` keys
* Allows multiple `null` values
* Basic operations are `O(log n)`

---

# 2. Why do we need TreeMap?

Different `Map` implementations solve different problems:

```text
HashMap
    ↓
Fast lookup
No guaranteed order

LinkedHashMap
    ↓
Fast lookup
Insertion/access order

TreeMap
    ↓
Sorted keys
Range/navigation operations
```

Use `TreeMap` when you need to:

* Keep keys sorted
* Find the smallest/largest key
* Find the nearest key
* Perform range queries
* Traverse keys in ascending/descending order

---

# 3. TreeMap Declaration

Prefer programming to the interface:

```java
Map<Integer, String> map = new TreeMap<>();
```

If you need TreeMap-specific methods:

```java
TreeMap<Integer, String> map = new TreeMap<>();
```

You can also use:

```java
NavigableMap<Integer, String> map = new TreeMap<>();
```

`NavigableMap` provides navigation operations such as:

```java
lowerKey()
higherKey()
floorKey()
ceilingKey()
```

---

# 4. Basic Example

```java
import java.util.TreeMap;

public class TreeMapExample {

    public static void main(String[] args) {

        TreeMap<Integer, String> map = new TreeMap<>();

        map.put(105, "Vijay");
        map.put(101, "Praveen");
        map.put(104, "Anjali");
        map.put(102, "Rahul");

        System.out.println(map);
    }
}
```

Output:

```text
{101=Praveen, 102=Rahul, 104=Anjali, 105=Vijay}
```

Notice that we inserted:

```text
105
101
104
102
```

But the map stores/iterates by sorted key order:

```text
101
102
104
105
```

---

# 5. How TreeMap Works Internally

`TreeMap` is implemented using a **Red-Black Tree**.

A Red-Black Tree is a **self-balancing Binary Search Tree (BST)**.

Conceptually:

```text
             104
            /   \
          102   105
         /
       101
```

The actual internal structure can differ because the tree continuously balances itself.

The important interview point is:

```text
TreeMap
   ↓
Red-Black Tree
   ↓
Self-balancing BST
   ↓
O(log n) operations
```

---

# 6. How Keys Are Compared

TreeMap needs a way to determine the ordering of keys.

There are two possibilities:

### Natural Ordering

If no comparator is supplied:

```java
TreeMap<Integer, String> map = new TreeMap<>();
```

Java uses the key's natural ordering.

For `Integer`:

```text
10 < 20 < 30 < 40
```

For `String`:

```text
"Anjali" < "Praveen" < "Rahul"
```

---

### Custom Comparator

You can provide your own ordering:

```java
TreeMap<Integer, String> map =
        new TreeMap<>(Comparator.reverseOrder());
```

Now:

```text
40
30
20
10
```

---

# 7. Important TreeMap Methods

## put()

Adds or updates a key-value pair.

```java
map.put(101, "Praveen");
```

If the key already exists, its value is replaced.

```java
map.put(101, "Rahul");
```

Now:

```text
101 → Rahul
```

---

## get()

Returns the value associated with a key.

```java
System.out.println(map.get(101));
```

Output:

```text
Praveen
```

If the key doesn't exist:

```java
map.get(999);
```

returns:

```text
null
```

---

## containsKey()

Checks whether a key exists.

```java
map.containsKey(101);
```

Returns:

```text
true
```

---

## containsValue()

Checks whether a value exists.

```java
map.containsValue("Praveen");
```

Returns:

```text
true
```

---

## remove()

Removes a key-value pair.

```java
map.remove(101);
```

---

## size()

```java
map.size();
```

Returns the number of mappings.

---

## isEmpty()

```java
map.isEmpty();
```

Checks whether the map contains no entries.

---

## clear()

```java
map.clear();
```

Removes all entries.

---

# 8. firstKey()

Returns the smallest key.

```java
TreeMap<Integer, String> map = new TreeMap<>();

map.put(30, "C");
map.put(10, "A");
map.put(20, "B");

System.out.println(map.firstKey());
```

Output:

```text
10
```

---

# 9. lastKey()

Returns the largest key.

```java
System.out.println(map.lastKey());
```

Output:

```text
30
```

Mental model:

```text
firstKey() → smallest
lastKey()  → largest
```

---

# 10. lowerKey()

Returns the **largest key strictly less than** the given key.

```java
TreeMap<Integer, String> map = new TreeMap<>();

map.put(10, "A");
map.put(20, "B");
map.put(30, "C");

System.out.println(map.lowerKey(25));
```

Output:

```text
20
```

Because:

```text
10 < 20 < 25 < 30
       ↑
     answer
```

Important:

```text
lowerKey(20)
```

returns:

```text
10
```

It does NOT return `20`.

---

# 11. higherKey()

Returns the **smallest key strictly greater than** the given key.

```java
System.out.println(map.higherKey(25));
```

Output:

```text
30
```

For:

```java
map.higherKey(20);
```

Output:

```text
30
```

Mental model:

```text
lowerKey(x)  → < x
higherKey(x) → > x
```

---

# 12. floorKey()

Returns the **largest key less than or equal to** the given key.

```java
System.out.println(map.floorKey(25));
```

Output:

```text
20
```

If the key exists:

```java
map.floorKey(20);
```

Output:

```text
20
```

Because `floor` allows equality.

```text
floorKey(x) → <= x
```

---

# 13. ceilingKey()

Returns the **smallest key greater than or equal to** the given key.

```java
System.out.println(map.ceilingKey(25));
```

Output:

```text
30
```

If the key exists:

```java
map.ceilingKey(20);
```

Output:

```text
20
```

Because `ceiling` allows equality.

```text
ceilingKey(x) → >= x
```

---

# 14. Navigation Cheat Sheet

This is extremely important for interviews.

```text
lowerKey(x)
    ↓
largest key < x

higherKey(x)
    ↓
smallest key > x

floorKey(x)
    ↓
largest key <= x

ceilingKey(x)
    ↓
smallest key >= x
```

Easy way to remember:

```text
LOWER   → strictly below
HIGHER  → strictly above
FLOOR   → below or equal
CEILING → above or equal
```

---

# 15. subMap()

Returns a view of a portion of the map.

```java
map.subMap(20, 40);
```

By default:

```text
20 <= key < 40
```

Example:

```java
TreeMap<Integer, String> map = new TreeMap<>();

map.put(10, "A");
map.put(20, "B");
map.put(30, "C");
map.put(40, "D");
map.put(50, "E");

System.out.println(map.subMap(20, 40));
```

Output:

```text
{20=B, 30=C}
```

The lower boundary is inclusive.

The upper boundary is exclusive.

---

## Inclusive/Exclusive Version

You can explicitly control both boundaries:

```java
map.subMap(20, true, 40, true);
```

Result:

```text
{20=B, 30=C, 40=D}
```

General form:

```java
subMap(fromKey, fromInclusive, toKey, toInclusive)
```

---

# 16. headMap()

Returns keys before a specified key.

```java
map.headMap(30);
```

Default:

```text
key < 30
```

Example:

```text
{10=A, 20=B}
```

To include `30`:

```java
map.headMap(30, true);
```

Result:

```text
{10=A, 20=B, 30=C}
```

---

# 17. tailMap()

Returns keys from a specified key onward.

```java
map.tailMap(30);
```

Default:

```text
key >= 30
```

Example:

```text
{30=C, 40=D, 50=E}
```

To exclude `30`:

```java
map.tailMap(30, false);
```

Result:

```text
{40=D, 50=E}
```

---

# 18. descendingMap()

Returns a reverse-ordered view of the map.

```java
System.out.println(map.descendingMap());
```

Example:

```text
{50=E, 40=D, 30=C, 20=B, 10=A}
```

---

# 19. descendingKeySet()

Returns only the keys in descending order.

```java
System.out.println(map.descendingKeySet());
```

Output:

```text
[50, 40, 30, 20, 10]
```

Difference:

```text
descendingMap()
    ↓
keys + values

descendingKeySet()
    ↓
keys only
```

---

# 20. Iterating Through TreeMap

The preferred way to process both keys and values:

```java
for (Map.Entry<Integer, String> entry : map.entrySet()) {

    System.out.println(
            entry.getKey() + " : " + entry.getValue()
    );
}
```

Because TreeMap maintains sorted keys, the iteration follows key order.

---

# 21. Custom Objects as Keys

TreeMap can use custom objects as keys.

Example:

```java
TreeMap<Employee, String> employees =
        new TreeMap<>(
                Comparator.comparingDouble(Employee::getSalary)
        );
```

Now employees are sorted according to salary.

```java
employees.put(
        new Employee(101, "Praveen", 70000),
        "Developer"
);

employees.put(
        new Employee(102, "Rahul", 50000),
        "Developer"
);

employees.put(
        new Employee(103, "Anjali", 60000),
        "Developer"
);
```

Iteration:

```text
Rahul   : 50000
Anjali  : 60000
Praveen : 70000
```

---

# 22. Comparator for Custom Objects

We can define multiple sorting criteria.

```java
Comparator<Employee> comparator =
        Comparator.comparingDouble(Employee::getSalary)
                .thenComparing(Employee::getName)
                .thenComparingInt(Employee::getId);
```

This means:

```text
salary
   ↓
same salary?
   ↓
compare name
   ↓
same name?
   ↓
compare ID
```

So employees are ordered by:

```text
1. Salary
2. Name
3. ID
```

---

# 23. Critical TreeMap Comparator Trap

Consider:

```java
TreeMap<Employee, String> employees =
        new TreeMap<>(
                Comparator.comparingDouble(Employee::getSalary)
        );
```

Suppose:

```text
Rahul   → 50000
Karthik → 50000
```

The comparator compares:

```text
50000 vs 50000
```

Result:

```text
0
```

For TreeMap purposes, the keys are considered equivalent.

Therefore, both mappings are **not retained as separate keys**.

The later `put()` replaces the value associated with the equivalent key.

### Important interview statement

> In a TreeMap, the comparator/natural ordering determines key ordering and whether keys are equivalent for map purposes.

Therefore, if duplicate salaries are possible, use tie-breakers:

```java
Comparator<Employee> comparator =
        Comparator.comparingDouble(Employee::getSalary)
                .thenComparing(Employee::getName)
                .thenComparingInt(Employee::getId);
```

---

# 24. TreeMap and null

TreeMap generally does **not allow a null key** with natural ordering.

```java
TreeMap<Integer, String> map = new TreeMap<>();

map.put(null, "A");
```

This results in a `NullPointerException`.

However, null values are allowed:

```java
map.put(101, null);
```

This is valid.

### Interview answer

```text
Null key   → Not allowed with natural ordering
Null value → Allowed
```

---

# 25. TreeMap Complexity

| Operation       | Complexity |
| --------------- | ---------: |
| `put()`         |   O(log n) |
| `get()`         |   O(log n) |
| `remove()`      |   O(log n) |
| `containsKey()` |   O(log n) |
| `firstKey()`    |   O(log n) |
| `lastKey()`     |   O(log n) |
| `lowerKey()`    |   O(log n) |
| `higherKey()`   |   O(log n) |
| `floorKey()`    |   O(log n) |
| `ceilingKey()`  |   O(log n) |
| Iteration       |       O(n) |

Why?

```text
TreeMap
   ↓
Red-Black Tree
   ↓
Height ≈ log n
   ↓
Search / Insert / Delete ≈ O(log n)
```

---

# 26. TreeMap vs HashMap vs LinkedHashMap

| Feature            | HashMap             | LinkedHashMap          | TreeMap                   |
| ------------------ | ------------------- | ---------------------- | ------------------------- |
| Key-value          | Yes                 | Yes                    | Yes                       |
| Unique keys        | Yes                 | Yes                    | Yes                       |
| Ordering           | No guaranteed order | Insertion/access order | Sorted keys               |
| Average lookup     | O(1)                | O(1)                   | O(log n)                  |
| Null key           | One allowed         | One allowed            | Not with natural ordering |
| Null values        | Yes                 | Yes                    | Yes                       |
| Range operations   | No                  | No                     | Yes                       |
| Navigation         | No                  | No                     | Yes                       |
| Internal structure | Hash table          | Hash table + links     | Red-Black Tree            |

---

# 27. When Should You Use TreeMap?

Use TreeMap when you need:

### 1. Sorted keys

```java
TreeMap<Integer, String> map = new TreeMap<>();
```

### 2. Smallest/largest key

```java
map.firstKey();
map.lastKey();
```

### 3. Nearest key

```java
map.floorKey(50);
map.ceilingKey(50);
```

### 4. Range queries

```java
map.subMap(20, 50);
```

### 5. Reverse ordering

```java
map.descendingMap();
```

---

# 28. When Should You NOT Use TreeMap?

If you only need fast key lookup and don't care about ordering:

```java
HashMap
```

is generally a better choice.

Don't choose TreeMap simply because it is a Map.

Choose based on the requirement:

```text
Need fast lookup?
    → HashMap

Need insertion/access order?
    → LinkedHashMap

Need sorted keys/navigation/ranges?
    → TreeMap
```

---

# 29. Common Mistakes

### Mistake 1: Assuming TreeMap maintains insertion order

Wrong:

```text
TreeMap → insertion order
```

Correct:

```text
TreeMap → sorted key order
```

---

### Mistake 2: Confusing lowerKey and floorKey

```text
lowerKey(20)
→ largest key < 20

floorKey(20)
→ largest key <= 20
```

---

### Mistake 3: Confusing higherKey and ceilingKey

```text
higherKey(20)
→ smallest key > 20

ceilingKey(20)
→ smallest key >= 20
```

---

### Mistake 4: Thinking TreeMap uses equals() first

TreeMap primarily relies on:

```text
Comparator
    OR
natural ordering
```

for comparison and ordering.

---

### Mistake 5: Using a comparator that returns 0 too often

Example:

```java
Comparator.comparingDouble(Employee::getSalary)
```

Two different employees with the same salary can become equivalent keys.

Use tie-breakers when distinct objects must remain distinct:

```java
.thenComparing(Employee::getName)
.thenComparingInt(Employee::getId)
```

---

### Mistake 6: Expecting O(1) lookup

TreeMap is:

```text
O(log n)
```

not:

```text
O(1)
```

---

# 30. Interview Questions

### Basic

**Q1. What is TreeMap?**

TreeMap is a `Map` implementation that stores key-value pairs sorted according to the keys using natural ordering or a supplied `Comparator`.

---

**Q2. What is the internal data structure of TreeMap?**

A self-balancing **Red-Black Tree**.

---

**Q3. What is the time complexity of TreeMap operations?**

Basic search, insertion, and deletion are `O(log n)`.

---

**Q4. Does TreeMap maintain insertion order?**

No. It maintains sorted key order.

---

**Q5. Does TreeMap allow duplicate keys?**

No.

---

**Q6. Does TreeMap allow null keys?**

Not with natural ordering. A null key generally causes `NullPointerException`.

---

**Q7. Can TreeMap have duplicate values?**

Yes.

---

### Intermediate

**Q8. Difference between `lowerKey()` and `floorKey()`?**

```text
lowerKey(x) → largest key < x

floorKey(x) → largest key <= x
```

---

**Q9. Difference between `higherKey()` and `ceilingKey()`?**

```text
higherKey(x) → smallest key > x

ceilingKey(x) → smallest key >= x
```

---

**Q10. How can you sort a TreeMap in descending order?**

```java
TreeMap<Integer, String> map =
        new TreeMap<>(Comparator.reverseOrder());
```

---

**Q11. Can TreeMap use custom objects as keys?**

Yes, but TreeMap needs a valid ordering through either:

* `Comparable`
* `Comparator`

---

**Q12. What happens if a TreeMap comparator returns 0 for two different objects?**

TreeMap treats the keys as equivalent for map purposes, so the mappings are not stored as separate keys.

---

# 31. Interview Explanation

If an interviewer asks:

**"Explain TreeMap."**

You can answer:

> "`TreeMap` is a `Map` implementation in Java that stores key-value pairs in sorted order based on the keys. Internally, it uses a self-balancing Red-Black Tree, so basic operations like get, put, and remove take O(log n) time. It supports natural ordering or a custom Comparator and provides useful navigation operations such as lowerKey, higherKey, floorKey, and ceilingKey. I would choose TreeMap when I need sorted keys, range queries, or nearest-key operations."

---

# 32. Quick Reference

```text
TreeMap
│
├── Key-value pairs
├── Unique keys
├── Sorted keys
├── Red-Black Tree
├── O(log n) basic operations
│
├── firstKey()
├── lastKey()
│
├── lowerKey()    → <
├── higherKey()   → >
├── floorKey()    → <=
├── ceilingKey()  → >=
│
├── subMap()
├── headMap()
├── tailMap()
│
├── descendingMap()
└── descendingKeySet()
```

### Map family mental model

```text
HashMap
    ↓
Fast lookup
No guaranteed order

LinkedHashMap
    ↓
Fast lookup
Predictable insertion/access order

TreeMap
    ↓
Sorted keys
Navigation
Range queries
O(log n)
```

### Most important interview rule

```text
TreeMap
   ↓
Comparator / Comparable
   ↓
Ordering + key equivalence
```

If:

```java
compare(a, b) == 0
```

then TreeMap treats `a` and `b` as equivalent keys for map purposes.
