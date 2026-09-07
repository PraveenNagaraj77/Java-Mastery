# HashMap

## 1. What is HashMap?

`HashMap` is a class in Java's Collections Framework that stores data in **key-value pairs**.

```java
Map<Integer, String> students = new HashMap<>();

students.put(101, "Praveen");
students.put(102, "Rahul");
students.put(103, "Anjali");
```

Each entry contains:

```text
Key → Value

101 → Praveen
102 → Rahul
103 → Anjali
```

### Important characteristics

* Stores data as key-value pairs
* Keys must be unique
* Values can be duplicated
* Allows one `null` key
* Allows multiple `null` values
* Does not guarantee iteration order
* Average `O(1)` lookup, insertion, and removal
* Part of the Java Collections Framework
* `HashMap` implements the `Map` interface

---

# 2. Why do we need HashMap?

Suppose we want to store student IDs and names.

Without a HashMap:

```java
int[] ids = {101, 102, 103};
String[] names = {"Praveen", "Rahul", "Anjali"};
```

Finding the name for ID `103` requires searching through the data.

With HashMap:

```java
Map<Integer, String> students = new HashMap<>();

students.put(101, "Praveen");
students.put(102, "Rahul");
students.put(103, "Anjali");

System.out.println(students.get(103));
```

Output:

```text
Anjali
```

HashMap is useful when we need to quickly associate one piece of information with another.

### Real-world examples

```text
Employee ID → Employee
User ID → User
Product ID → Product
Username → Password
Country Code → Country Name
Word → Frequency
Number → Frequency
```

---

# 3. HashMap Declaration

Recommended:

```java
Map<Integer, String> students = new HashMap<>();
```

Import:

```java
import java.util.HashMap;
import java.util.Map;
```

We generally program to the interface:

```java
Map<Integer, String> students = new HashMap<>();
```

rather than:

```java
HashMap<Integer, String> students = new HashMap<>();
```

The first approach gives us more flexibility to change the implementation later.

---

# 4. Basic Operations

## put()

Adds a key-value pair.

```java
Map<Integer, String> students = new HashMap<>();

students.put(101, "Praveen");
students.put(102, "Rahul");
```

Map:

```text
101 → Praveen
102 → Rahul
```

### What happens if the key already exists?

```java
students.put(101, "Nagaraj");
```

The old value is replaced.

```text
101 → Nagaraj
```

### Important

Keys are unique.

Values don't have to be unique.

```java
students.put(101, "Praveen");
students.put(102, "Praveen");
```

This is valid.

---

# 5. get()

Retrieves the value associated with a key.

```java
System.out.println(students.get(101));
```

Output:

```text
Praveen
```

If the key doesn't exist:

```java
System.out.println(students.get(999));
```

Output:

```text
null
```

---

# 6. containsKey()

Checks whether a key exists.

```java
students.containsKey(101);
```

Returns:

```text
true
```

Example:

```java
if (students.containsKey(101)) {
    System.out.println("Student exists");
}
```

This is preferred when we specifically want to check whether the **key** exists.

---

# 7. containsValue()

Checks whether a value exists.

```java
students.containsValue("Praveen");
```

Returns:

```text
true
```

Unlike key lookup, value searching generally requires scanning entries.

So:

```text
containsKey() → average O(1)
containsValue() → O(n)
```

---

# 8. remove()

Removes a mapping using its key.

```java
students.remove(102);
```

The entry with key `102` is removed.

---

# 9. size()

Returns the number of key-value mappings.

```java
System.out.println(students.size());
```

Example:

```text
3
```

---

# 10. isEmpty()

Checks whether the map contains no entries.

```java
if (students.isEmpty()) {
    System.out.println("Map is empty");
}
```

---

# 11. clear()

Removes all mappings.

```java
students.clear();
```

After this:

```text
{}
```

---

# 12. putIfAbsent()

Adds a value only if the key does not already exist.

```java
students.put(101, "Praveen");

students.putIfAbsent(101, "Nagaraj");
```

The existing value remains:

```text
101 → Praveen
```

But:

```java
students.putIfAbsent(102, "Rahul");
```

adds:

```text
102 → Rahul
```

### Difference

```java
put()
```

can replace an existing value.

```java
putIfAbsent()
```

does not replace an existing value.

---

# 13. getOrDefault()

Returns the value for a key.

If the key doesn't exist, it returns a default value.

```java
String name = students.getOrDefault(999, "Unknown");

System.out.println(name);
```

Output:

```text
Unknown
```

This method is extremely useful in **frequency-counting problems**.

Example:

```java
Map<Integer, Integer> frequency = new HashMap<>();

frequency.put(number,
        frequency.getOrDefault(number, 0) + 1);
```

This means:

```text
Get current frequency
        ↓
If missing → use 0
        ↓
Add 1
        ↓
Store updated frequency
```

Expanded version:

```java
int currentCount = frequency.getOrDefault(number, 0);

int newCount = currentCount + 1;

frequency.put(number, newCount);
```

---

# 14. replace()

Replaces the value for an existing key.

```java
students.put(101, "Praveen");

students.replace(101, "Nagaraj");
```

Now:

```text
101 → Nagaraj
```

If the key doesn't exist:

```java
students.replace(999, "Vijay");
```

Nothing is added.

### Difference

```java
put()
```

adds or replaces.

```java
replace()
```

only replaces an existing mapping.

---

# 15. putAll()

Copies all mappings from another map.

```java
Map<Integer, String> students1 = new HashMap<>();

students1.put(101, "Praveen");
students1.put(102, "Rahul");

Map<Integer, String> students2 = new HashMap<>();

students2.putAll(students1);
```

Now `students2` contains the mappings from `students1`.

---

# 16. Iterating Through HashMap

## Using entrySet()

This is the preferred approach when we need both key and value.

```java
for (Map.Entry<Integer, String> entry : students.entrySet()) {

    System.out.println(
            entry.getKey() + " : " + entry.getValue()
    );
}
```

Example:

```text
101 : Praveen
102 : Rahul
103 : Anjali
```

Remember:

```text
entry.getKey()   → key
entry.getValue() → value
```

---

# 17. keySet()

Use `keySet()` when we mainly need the keys.

```java
for (Integer key : students.keySet()) {
    System.out.println(key);
}
```

Example:

```text
101
102
103
```

---

# 18. values()

Use `values()` when we only need the values.

```java
for (String value : students.values()) {
    System.out.println(value);
}
```

Example:

```text
Praveen
Rahul
Anjali
```

---

# 19. Internal Working of HashMap ⭐

This is one of the most important HashMap interview topics.

When we execute:

```java
map.put("Praveen", 100);
```

conceptually:

```text
"Praveen"
     ↓
hashCode()
     ↓
hash calculation
     ↓
bucket
     ↓
store key-value entry
```

For:

```java
map.get("Praveen");
```

conceptually:

```text
"Praveen"
     ↓
hashCode()
     ↓
find bucket
     ↓
compare key
     ↓
return value
```

HashMap uses hashing to efficiently locate entries.

---

# 20. HashMap Bucket Concept

Conceptually, HashMap contains an internal table of buckets.

```text
Bucket 0
Bucket 1
Bucket 2
Bucket 3
Bucket 4
...
```

A key's hash is used to determine where the entry should be stored.

For example:

```text
Key
 ↓
hashCode()
 ↓
hash
 ↓
bucket index
 ↓
Entry
```

The exact internal calculation is an implementation detail, but the important interview concept is:

> HashMap uses the key's hash information to locate the appropriate bucket.

---

# 21. Collision ⭐

A collision happens when different keys end up in the same bucket.

Example conceptually:

```text
Key A ──→ Bucket 5
Key B ──→ Bucket 5
```

The keys are different, but their bucket location is the same.

HashMap handles collisions internally.

Modern Java implementations can use a linked structure and, under certain conditions, a balanced tree structure within a bucket.

---

# 22. hashCode() and equals()

HashMap relies on both `hashCode()` and `equals()` when working with keys.

Mental model:

```text
Key
 ↓
hashCode()
 ↓
Find bucket
 ↓
equals()
 ↓
Find exact key
```

### Important contract

If:

```java
a.equals(b)
```

is `true`, then:

```java
a.hashCode() == b.hashCode()
```

must also be true.

But the reverse is not guaranteed.

Same hash code does NOT necessarily mean objects are equal.

---

# 23. Custom Objects as HashMap Keys

Suppose:

```java
Map<Employee, String> employees = new HashMap<>();
```

If `Employee` is used as a key, `equals()` and `hashCode()` should be implemented correctly.

Example:

```java
class Employee {

    private final int id;
    private final String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof Employee)) return false;

        Employee other = (Employee) obj;

        return id == other.id &&
               Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
```

Without correctly implementing these methods, HashMap may not recognize logically equal objects as the same key.

---

# 24. Null Keys and Null Values

HashMap allows:

```text
One null key
Multiple null values
```

Example:

```java
Map<Integer, String> map = new HashMap<>();

map.put(null, "Unknown");
map.put(101, null);
map.put(102, null);
```

This is valid.

Important:

```text
HashMap → allows null key
TreeMap  → generally does not allow null keys with natural ordering
```

---

# 25. HashMap Order

HashMap does **not guarantee insertion order**.

Example:

```java
Map<Integer, String> map = new HashMap<>();

map.put(3, "C");
map.put(1, "A");
map.put(2, "B");
```

Do not write code that depends on the printed order.

If insertion order is required:

```java
LinkedHashMap
```

If sorted key order is required:

```java
TreeMap
```

---

# 26. HashMap vs LinkedHashMap vs TreeMap

| Feature        | HashMap      | LinkedHashMap            | TreeMap               |
| -------------- | ------------ | ------------------------ | --------------------- |
| Key-value      | Yes          | Yes                      | Yes                   |
| Unique keys    | Yes          | Yes                      | Yes                   |
| Order          | No guarantee | Insertion order          | Sorted key order      |
| Average lookup | O(1)         | O(1)                     | O(log n)              |
| Null key       | One allowed  | One allowed              | Generally not allowed |
| Main use       | Fast lookup  | Lookup + insertion order | Sorted keys           |

### Mental model

```text
HashMap
    ↓
Key-Value + Fast Lookup

LinkedHashMap
    ↓
Key-Value + Fast Lookup + Insertion Order

TreeMap
    ↓
Key-Value + Sorted Keys
```

---

# 27. HashMap Time Complexity

Average case:

| Operation       | Average |
| --------------- | ------: |
| put()           |    O(1) |
| get()           |    O(1) |
| remove()        |    O(1) |
| containsKey()   |    O(1) |
| containsValue() |    O(n) |

Worst-case behavior can be affected by collisions and implementation details.

For interviews, remember:

> HashMap provides average O(1) key-based lookup, insertion, and removal.

---

# 28. Frequency Counting ⭐

One of the most important HashMap DSA patterns.

Problem:

```java
int[] numbers = {1, 2, 2, 3, 1, 2, 4};
```

Find the frequency of each number.

Solution:

```java
Map<Integer, Integer> frequency = new HashMap<>();

for (int number : numbers) {

    frequency.put(
            number,
            frequency.getOrDefault(number, 0) + 1
    );
}
```

Result conceptually:

```text
1 → 2
2 → 3
3 → 1
4 → 1
```

### Pattern

```text
element → frequency
```

This pattern is extremely important in DSA.

---

# 29. First Non-Repeating Character

Example:

```java
String input = "aabbcdde";
```

First count frequencies:

```java
Map<Character, Integer> frequency = new HashMap<>();

for (char character : input.toCharArray()) {
    frequency.put(
            character,
            frequency.getOrDefault(character, 0) + 1
    );
}
```

Then scan the original string:

```java
for (char character : input.toCharArray()) {

    if (frequency.get(character) == 1) {
        System.out.println(character);
        break;
    }
}
```

Output:

```text
c
```

### Important pattern

```text
Pass 1 → Count
Pass 2 → Find based on count
```

We scan the original string in the second pass because HashMap does not guarantee order.

---

# 30. Duplicate Characters

Example:

```java
String input = "programming";
```

Use:

```text
HashMap → count frequency
HashSet → keep duplicates unique
```

Conceptually:

```text
String
 ↓
HashMap
 ↓
Find frequency > 1
 ↓
HashSet
 ↓
Unique duplicate characters
```

---

# 31. Most Frequent Character

Pattern:

```text
Character → Frequency
```

Then iterate through the entries:

```java
char maxCharacter = '\0';
int maxFrequency = 0;

for (Map.Entry<Character, Integer> entry : frequency.entrySet()) {

    if (entry.getValue() > maxFrequency) {
        maxFrequency = entry.getValue();
        maxCharacter = entry.getKey();
    }
}
```

Important:

```java
entry.getKey()
```

returns the character.

```java
entry.getValue()
```

returns the frequency.

---

# 32. Two Sum ⭐

One of the most important HashMap DSA patterns.

Given:

```java
int[] numbers = {2, 7, 11, 15};
int target = 9;
```

We need two numbers whose sum is `9`.

Pattern:

```text
current number
      ↓
needed = target - current
      ↓
Does HashMap contain needed?
      ↓
Yes → pair found
No  → store current
```

Implementation:

```java
Map<Integer, Integer> seen = new HashMap<>();

for (int i = 0; i < numbers.length; i++) {

    int current = numbers[i];
    int needed = target - current;

    if (seen.containsKey(needed)) {

        System.out.println(
                "Pair found: " +
                needed + " + " +
                current +
                " = " +
                target
        );

        System.out.println(
                "Indices: [" +
                seen.get(needed) +
                ", " +
                i +
                "]"
        );

        break;
    }

    seen.put(current, i);
}
```

Here:

```text
number → index
```

is stored in the HashMap.

Time:

```text
O(n)
```

Space:

```text
O(n)
```

---

# 33. Count Pairs With Given Sum

For counting all pairs, the HashMap stores:

```text
number → frequency
```

Pattern:

```java
int count = 0;

for (int number : numbers) {

    int needed = target - number;

    if (seen.containsKey(needed)) {
        count += seen.get(needed);
    }

    seen.put(
            number,
            seen.getOrDefault(number, 0) + 1
    );
}
```

### Important distinction

Two Sum:

```text
number → index
```

Count Pairs:

```text
number → frequency
```

---

# 34. HashMap DSA Mental Models

When you see a DSA problem, ask:

### Need fast lookup?

Think:

```text
HashMap / HashSet
```

### Need frequency?

Think:

```text
HashMap
element → count
```

### Need to find a complement?

Think:

```text
needed = target - current
HashMap lookup
```

### Need index?

Think:

```text
element → index
```

### Need uniqueness?

Think:

```text
HashSet
```

### Need unique elements while preserving insertion order?

Think:

```text
LinkedHashSet
```

---

# 35. Common Mistakes

## Mistake 1: Assuming HashMap maintains insertion order

Wrong:

```text
HashMap preserves insertion order
```

Correct:

```text
HashMap does not guarantee iteration order.
```

---

## Mistake 2: Thinking keys can be duplicated

```java
map.put(101, "Praveen");
map.put(101, "Rahul");
```

There is still only one key:

```text
101 → Rahul
```

The old value was replaced.

---

## Mistake 3: Confusing key and value

For:

```text
101 → Praveen
```

```text
101    = key
Praveen = value
```

---

## Mistake 4: Using get() to check whether a key exists

Avoid:

```java
if (map.get(101) != null)
```

because the key may exist with a `null` value.

Prefer:

```java
if (map.containsKey(101))
```

---

## Mistake 5: Forgetting getOrDefault()

Instead of:

```java
if (frequency.containsKey(number)) {
    frequency.put(number, frequency.get(number) + 1);
} else {
    frequency.put(number, 1);
}
```

we can simplify to:

```java
frequency.put(
        number,
        frequency.getOrDefault(number, 0) + 1
);
```

---

## Mistake 6: Using index when frequency is required

For Two Sum:

```text
number → index
```

For frequency counting:

```text
number → frequency
```

Choose the map based on what the problem requires.

---

# 36. Interview Questions

### Q1. What is HashMap?

`HashMap` is a Map implementation that stores key-value pairs and provides average O(1) key-based lookup, insertion, and removal.

---

### Q2. Can HashMap have duplicate keys?

No. Keys must be unique.

If the same key is inserted again, its value is replaced.

---

### Q3. Can HashMap have duplicate values?

Yes.

```java
map.put(1, "Java");
map.put(2, "Java");
```

is valid.

---

### Q4. Does HashMap maintain insertion order?

No.

HashMap does not guarantee iteration order.

---

### Q5. How does HashMap work internally?

It uses hashing to determine a bucket for a key. During lookup, hash information helps locate the bucket and key comparison is then used to identify the correct entry.

---

### Q6. What is a collision?

A collision occurs when different keys map to the same bucket.

HashMap handles collisions internally.

---

### Q7. Why are equals() and hashCode() important?

They are used to correctly identify keys.

If two objects are equal according to `equals()`, they must have the same hash code.

---

### Q8. What is the average time complexity of HashMap get()?

```text
O(1)
```

on average.

---

### Q9. Difference between HashMap and Hashtable?

Important general differences:

```text
HashMap
→ not synchronized
→ allows null key and null values
→ generally preferred for non-thread-safe use

Hashtable
→ legacy class
→ synchronized
→ does not allow null keys or values
```

For modern applications, use more appropriate concurrent collections when thread safety is required.

---

### Q10. HashMap vs HashSet?

```text
HashMap
→ key-value pairs

HashSet
→ unique elements
```

Conceptually, HashSet is backed by a HashMap internally.

---

# 37. Interview Explanation

If an interviewer asks:

> "Explain HashMap."

A strong answer:

> `HashMap` is a Map implementation in Java that stores data as key-value pairs. Keys are unique while values can be duplicated. It uses hashing to locate entries efficiently, providing average O(1) time for operations such as get, put, and remove. HashMap does not guarantee iteration order and allows one null key and multiple null values. When custom objects are used as keys, equals() and hashCode() must follow their contract correctly.

---

# 38. Quick Reference

```text
HashMap
│
├── Key → Value
├── Keys unique
├── Values can duplicate
├── No guaranteed order
├── One null key
├── Multiple null values
│
├── put()
├── get()
├── getOrDefault()
├── putIfAbsent()
├── containsKey()
├── containsValue()
├── remove()
├── replace()
├── putAll()
├── size()
├── isEmpty()
└── clear()
```

### Most important concepts

```text
HashMap
   ↓
Hashing
   ↓
Bucket
   ↓
Collision
   ↓
equals() + hashCode()
```

### DSA patterns

```text
Frequency Counting
        ↓
element → count

Two Sum
        ↓
element → index

Count Pairs
        ↓
element → frequency
```

---

# 39. HashMap Checklist

Before moving on, make sure you can explain:

* [x] What is HashMap?
* [x] Why HashMap is needed
* [x] Key-value concept
* [x] `put()`
* [x] `get()`
* [x] `containsKey()`
* [x] `containsValue()`
* [x] `remove()`
* [x] `putIfAbsent()`
* [x] `getOrDefault()`
* [x] `replace()`
* [x] `putAll()`
* [x] `size()`
* [x] `isEmpty()`
* [x] `clear()`
* [x] Iteration using `entrySet()`
* [x] `keySet()`
* [x] `values()`
* [x] Internal working
* [x] Buckets
* [x] Collisions
* [x] `hashCode()`
* [x] `equals()`
* [x] Null keys and values
* [x] HashMap ordering
* [x] Time complexity
* [x] HashMap vs HashSet
* [x] HashMap vs LinkedHashMap
* [x] HashMap vs TreeMap
* [x] Frequency counting
* [x] First non-repeating character
* [x] Duplicate characters
* [x] Most frequent character
* [x] Two Sum
* [x] Count pairs with given sum

---

# 40. Key Takeaway

The most important HashMap mental model is:

```text
What information do I need to remember?
                ↓
Choose what the key represents
                ↓
Choose what the value represents
                ↓
Use HashMap for fast lookup
```

Examples:

```text
Student ID → Student
Number → Frequency
Number → Index
Character → Frequency
Value → Position
```

Once you identify **what the key and value should represent**, many HashMap problems become much easier.
