# Hashing in Java — DSA Notes

## 1. What Is Hashing?

Hashing is a technique used to store and retrieve data efficiently using a key.

In Java, the main hashing-based collections are:

- `HashMap<K, V>` — stores key-value pairs.
- `HashSet<E>` — stores unique elements.

These collections use a hash code to help locate elements quickly.

### Why use hashing?

- Fast average-case lookup.
- Efficient frequency counting.
- Detecting duplicates.
- Finding complements in Two Sum.
- Checking membership and intersections.

---

## 2. HashMap

A `HashMap` stores data as key-value pairs.

```java
Map<String, Integer> ages = new HashMap<>();

ages.put("Alice", 25);
ages.put("Bob", 30);

System.out.println(ages.get("Alice")); // 25
```

### Important methods

| Method | Purpose |
|---|---|
| `put(key, value)` | Add or update a key-value pair |
| `get(key)` | Retrieve the value for a key |
| `getOrDefault(key, defaultValue)` | Return the value or a default |
| `containsKey(key)` | Check whether a key exists |
| `remove(key)` | Remove a key |
| `keySet()` | Get all keys |
| `values()` | Get all values |
| `entrySet()` | Iterate over key-value pairs |
| `size()` | Number of keys |

### Frequency counting pattern

```java
Map<Integer, Integer> frequency = new HashMap<>();

for (int value : arr) {
    frequency.put(
        value,
        frequency.getOrDefault(value, 0) + 1
    );
}
```

**How it works:**

1. If the value is not present, start from `0`.
2. Add `1` to its current count.
3. Store the updated count.

Example:

Input: `[1, 2, 2, 3, 1, 2]`

Result: `{1=2, 2=3, 3=1}`

The iteration order of a `HashMap` is not guaranteed.

---

## 3. HashSet

A `HashSet` stores unique values.

```java
Set<Integer> numbers = new HashSet<>();

numbers.add(10);
numbers.add(20);
numbers.add(10);

System.out.println(numbers); // [10, 20], order may vary
```

Adding `10` twice does not create two entries.

### Important methods

| Method | Purpose |
|---|---|
| `add(value)` | Add a value; returns `false` if already present |
| `contains(value)` | Check whether a value exists |
| `remove(value)` | Remove a value |
| `size()` | Number of unique values |
| `isEmpty()` | Check whether the set is empty |

### Duplicate detection pattern

```java
Set<Integer> seen = new HashSet<>();

for (int value : arr) {
    if (!seen.add(value)) {
        System.out.println("Duplicate: " + value);
        break;
    }
}
```

`add()` returns `true` when the value is newly added and `false` when it already exists.

---

## 4. HashMap vs HashSet vs LinkedHashMap

| Feature | HashMap | HashSet | LinkedHashMap |
|---|---|---|---|
| Stores | Key-value pairs | Unique values | Key-value pairs |
| Duplicate keys/values | Keys unique; values can repeat | Duplicate elements ignored | Keys unique; values can repeat |
| Preserves insertion order | No guarantee | No guarantee | Yes, by default |
| Common use | Counts, indices, mappings | Membership, duplicates | Ordered mappings |

**Interview rule:** Use a `HashSet` when you only need to know whether an element has appeared. Use a `HashMap` when you need to associate a value such as a count or index with a key.

---

## 5. Problem 1 — Find Duplicates

### Problem

Return `true` if any value appears more than once.

Input: `[1, 2, 3, 2]`

Output: `true`

### Approach

Use a set to remember values already encountered. If adding a value fails, a duplicate exists.

```java
public static boolean containsDuplicate(int[] arr) {
    Set<Integer> seen = new HashSet<>();

    for (int value : arr) {
        if (!seen.add(value)) {
            return true;
        }
    }

    return false;
}
```

**Complexity:** Average time `O(n)`, space `O(n)`.

---

## 6. Problem 2 — Count Frequencies

### Problem

Count how many times each number occurs.

Input: `[1, 2, 2, 3, 1, 2]`

Output: `{1=2, 2=3, 3=1}`

### Approach

Use `getOrDefault()` to retrieve the existing count or start at zero.

```java
public static Map<Integer, Integer> countFrequencies(int[] arr) {
    Map<Integer, Integer> frequency = new HashMap<>();

    for (int value : arr) {
        frequency.put(
            value,
            frequency.getOrDefault(value, 0) + 1
        );
    }

    return frequency;
}
```

**Complexity:** Average time `O(n)`, space `O(k)`, where `k` is the number of distinct values.

---

## 7. Problem 3 — Two Sum

### Problem

Return indices of two elements whose sum equals the target.

Input: `arr = [2, 7, 11, 15]`, `target = 9`

Output: `[0, 1]`

### Approach

For each value, calculate its complement:

`complement = target - currentValue`

If the complement is already in the map, return its stored index and the current index. Otherwise, store the current value and index.

```java
public static int[] twoSum(int[] arr, int target) {
    Map<Integer, Integer> indices = new HashMap<>();

    for (int i = 0; i < arr.length; i++) {
        int complement = target - arr[i];

        if (indices.containsKey(complement)) {
            return new int[] { indices.get(complement), i };
        }

        indices.put(arr[i], i);
    }

    return new int[0];
}
```

Print the result using:

```java
System.out.println(Arrays.toString(twoSum(arr, 9)));
```

**Complexity:** Average time `O(n)`, space `O(n)`.

**Important:** Check the complement before inserting the current value, so an element is not paired with itself.

---

## 8. Problem 4 — Intersection of Two Arrays

### Problem

Return the unique elements present in both arrays.

Input: `[1, 2, 2, 3, 4]` and `[2, 3, 3, 5]`

Output: `{2, 3}`

```java
public static Set<Integer> intersection(int[] arr1, int[] arr2) {
    Set<Integer> first = new HashSet<>();
    Set<Integer> result = new HashSet<>();

    for (int value : arr1) {
        first.add(value);
    }

    for (int value : arr2) {
        if (first.contains(value)) {
            result.add(value);
        }
    }

    return result;
}
```

**Complexity:** Average time `O(n + m)`, space `O(n + k)`, where `k` is the number of unique intersection elements.

**Common mistake:** Use `arr[i]` for the element, not `i`, which is only its index.

---

## 9. Problem 5 — First Repeated Element

### Problem

Return the first element whose occurrence is repeated while traversing left to right.

Input: `[10, 5, 3, 4, 3, 5, 6]`

Output: `3`

```java
public static Integer firstRepeated(int[] arr) {
    Set<Integer> seen = new HashSet<>();

    for (int value : arr) {
        if (!seen.add(value)) {
            return value;
        }
    }

   