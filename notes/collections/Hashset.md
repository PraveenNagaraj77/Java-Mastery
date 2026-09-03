# HashSet

## 1. What is HashSet?

`HashSet` is a collection in Java that stores **unique elements**.

It implements the `Set` interface.

```java
Set<String> technologies = new HashSet<>();
```

Example:

```java
technologies.add("Java");
technologies.add("React");
technologies.add("Java");
```

Result:

```text
[Java, React]
```

The duplicate `"Java"` is not added.

---

## 2. Why do we need HashSet?

Use `HashSet` when the main requirement is:

* Unique elements
* Fast lookup
* Fast insertion
* Fast removal

Example:

```text
Input:
Java
React
Java
SQL
React
Node.js

HashSet:
Java
React
SQL
Node.js
```

A common DSA pattern is:

> **Need uniqueness or fast membership checking → Think HashSet.**

---

## 3. Basic Syntax

```java
import java.util.HashSet;
import java.util.Set;

Set<String> skills = new HashSet<>();
```

Adding elements:

```java
skills.add("Java");
skills.add("Spring Boot");
skills.add("React");
```

---

## 4. Important Properties

| Property           | HashSet            |
| ------------------ | ------------------ |
| Duplicates         | Not allowed        |
| `null`             | One `null` allowed |
| Index-based access | Not supported      |
| Insertion order    | Not guaranteed     |
| Lookup             | O(1) average       |
| Internal structure | Hash table         |

Example:

```java
Set<String> skills = new HashSet<>();

skills.add("Java");
skills.add("React");
skills.add("Java");
```

The second `"Java"` is ignored.

---

# 5. How HashSet Works Internally

HashSet is based on a **hash table**.

Conceptually:

```text
Element
   ↓
hashCode()
   ↓
Hash value
   ↓
Bucket
   ↓
Store/Search
```

For example:

```text
"Java"
   ↓
hashCode()
   ↓
hash value
   ↓
bucket
```

Think of a HashSet as having multiple buckets:

```text
Bucket 0 → [ ]
Bucket 1 → [ ]
Bucket 2 → [Java]
Bucket 3 → [ ]
Bucket 4 → [React]
Bucket 5 → [ ]
...
```

The hash value helps determine which bucket should be searched.

This is why HashSet can provide **O(1) average-time lookup**.

---

# 6. What Happens During `add()`?

When we execute:

```java
skills.add("Java");
```

Conceptually:

```text
"Java"
   ↓
hashCode()
   ↓
Find bucket
   ↓
Check existing elements
   ↓
Add if not already present
```

If `"Java"` already exists:

```text
"Java"
   ↓
hashCode()
   ↓
Same bucket
   ↓
equals()
   ↓
Already exists
   ↓
Don't add
```

---

# 7. `hashCode()` and `equals()`

This is one of the most important HashSet interview concepts.

HashSet uses:

```text
hashCode()
     ↓
Find candidate bucket

equals()
     ↓
Check actual equality
```

### Simple mental model

> `hashCode()` → **Where should I look?**

> `equals()` → **Is this actually the same object logically?**

---

# 8. `hashCode()`

Every Java object inherits `hashCode()` from `Object`.

```java
String skill = "Java";

System.out.println(skill.hashCode());
```

It returns an `int`.

The hash value helps hash-based collections locate an appropriate bucket.

---

# 9. `equals()`

`equals()` determines whether two objects should be considered logically equal.

Example:

```java
String a = new String("Java");
String b = new String("Java");

System.out.println(a.equals(b));
```

Output:

```text
true
```

Because the contents are equal.

---

# 10. `==` vs `equals()`

For objects:

### `==`

Checks whether two references point to the **same object**.

### `equals()`

Checks **logical equality**, according to the class implementation.

Example:

```java
String a = new String("Java");
String b = new String("Java");

System.out.println(a == b);
System.out.println(a.equals(b));
```

Output:

```text
false
true
```

The objects are different, but their contents are equal.

---

# 11. HashSet with Custom Objects

Consider:

```java
class User {

    String name;
    int age;

    User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

Now:

```java
User user1 = new User("Praveen", 26);
User user2 = new User("Praveen", 26);
```

Even though the data is the same:

```text
user1 → [Praveen, 26]

user2 → [Praveen, 26]
```

they are two different objects.

Without overriding `equals()` and `hashCode()`:

```java
user1.equals(user2)
```

will normally return:

```text
false
```

Therefore:

```java
Set<User> users = new HashSet<>();

users.add(user1);
users.add(user2);
```

can contain both objects.

---

# 12. Overriding `equals()`

If our business rule says:

> Two Users are equal when their name and age are equal.

We can override `equals()`:

```java
@Override
public boolean equals(Object obj) {

    if (this == obj) {
        return true;
    }

    if (!(obj instanceof User)) {
        return false;
    }

    User other = (User) obj;

    return age == other.age &&
           name.equals(other.name);
}
```

Now:

```text
User("Praveen", 26)
        ↓
equals()
        ↓
User("Praveen", 26)
        ↓
true
```

---

# 13. Overriding `hashCode()`

Because `equals()` uses:

```text
name
age
```

our `hashCode()` should use the same fields.

```java
@Override
public int hashCode() {
    return Objects.hash(name, age);
}
```

Import:

```java
import java.util.Objects;
```

---

# 14. `equals()` / `hashCode()` Contract

This is extremely important.

### Rule 1

If:

```java
a.equals(b)
```

returns:

```text
true
```

then:

```java
a.hashCode() == b.hashCode()
```

must also be true.

### Rule 2

If:

```java
a.hashCode() == b.hashCode()
```

it does **not** necessarily mean:

```java
a.equals(b)
```

is true.

Different objects can have the same hash code.

This is called a:

> **Hash collision**

---

# 15. Hash Collision

Example conceptually:

```text
Object A → hashCode() → 100

Object B → hashCode() → 100
```

Both can end up in the same bucket:

```text
Bucket 100
    ↓
[A]
[B]
```

HashSet then uses `equals()` to determine whether they are actually equal.

```text
Same hash
   ↓
Same bucket
   ↓
equals()
   ↓
Different?
   ↓
Both can exist
```

---

# 16. Complete Custom Object Example

```java
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class User {

    private final String name;
    private final int age;

    User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof User)) {
            return false;
        }

        User other = (User) obj;

        return age == other.age &&
               name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
```

Usage:

```java
Set<User> users = new HashSet<>();

users.add(new User("Praveen", 26));
users.add(new User("Praveen", 26));
users.add(new User("Rahul", 28));

System.out.println(users.size());
```

Output:

```text
2
```

The duplicate `Praveen, 26` is not added.

---

# 17. Important HashSet Methods

### `add()`

```java
skills.add("Java");
```

Adds an element if it doesn't already exist.

---

### `contains()`

```java
skills.contains("Java");
```

Checks whether an element exists.

Average:

```text
O(1)
```

---

### `remove()`

```java
skills.remove("Java");
```

Removes an element.

Average:

```text
O(1)
```

---

### `size()`

```java
skills.size();
```

Returns the number of elements.

---

### `isEmpty()`

```java
skills.isEmpty();
```

Checks whether the set contains no elements.

---

### `clear()`

```java
skills.clear();
```

Removes all elements.

---

# 18. Iterating HashSet

Enhanced `for` loop:

```java
for (String skill : skills) {
    System.out.println(skill);
}
```

Or:

```java
skills.forEach(skill -> System.out.println(skill));
```

Remember:

> HashSet does **not guarantee iteration order**.

---

# 19. HashSet Time Complexity

| Operation    | Average Time |
| ------------ | -----------: |
| `add()`      |         O(1) |
| `remove()`   |         O(1) |
| `contains()` |         O(1) |
| `size()`     |         O(1) |

The O(1) values are **average-case** because hashing performance depends on how elements are distributed among buckets.

---

# 20. HashSet vs ArrayList

| Feature      | ArrayList            | HashSet                   |
| ------------ | -------------------- | ------------------------- |
| Duplicates   | Allowed              | Not allowed               |
| Order        | Insertion order      | Not guaranteed            |
| Index access | Yes                  | No                        |
| `get(index)` | O(1)                 | Not available             |
| `contains()` | O(n)                 | O(1) average              |
| Main purpose | Ordered/indexed data | Unique data + fast lookup |

### Mental model

```text
ArrayList
    ↓
"I care about order/index."

HashSet
    ↓
"I care about uniqueness/lookup."
```

---

# 21. HashSet vs LinkedHashSet vs TreeSet

| Collection    | Duplicate | Order               |
| ------------- | --------- | ------------------- |
| HashSet       | ❌         | No guaranteed order |
| LinkedHashSet | ❌         | Insertion order     |
| TreeSet       | ❌         | Sorted order        |

Example:

```text
HashSet
→ unique

LinkedHashSet
→ unique + insertion order

TreeSet
→ unique + sorted order
```

---

# 22. Common Mistakes

### Mistake 1 — Assuming HashSet maintains insertion order

```java
set.add("Java");
set.add("React");
set.add("SQL");
```

Don't depend on the output order.

---

### Mistake 2 — Trying to access by index

```java
set.get(0); // ❌
```

HashSet doesn't provide index-based access.

---

### Mistake 3 — Overriding only `equals()`

If you override `equals()`, you should also override `hashCode()`.

---

### Mistake 4 — Assuming same hash means equal

```text
same hashCode
     ≠
equal objects
```

Hash collisions are possible.

---

### Mistake 5 — Changing fields used by `equals()` / `hashCode()` after insertion

Objects used inside a HashSet should ideally have stable equality/hash-related fields.

---

# 23. DSA Patterns Using HashSet

HashSet becomes extremely useful in DSA.

### Pattern 1 — Remove duplicates

```text
Input:
1 2 2 3 3 4

HashSet:
1 2 3 4
```

---

### Pattern 2 — Check duplicates

```text
Input:
1 5 3 5

HashSet:
1
5
3
5 → already exists
```

Therefore duplicate found.

---

### Pattern 3 — Fast membership check

Instead of repeatedly searching a list:

```text
ArrayList.contains()
→ O(n)
```

Use:

```text
HashSet.contains()
→ O(1) average
```

---

# 24. Interview Questions

### Q1. What is HashSet?

> HashSet is a Set implementation that stores unique elements using a hash table and provides average O(1) insertion, removal, and lookup.

### Q2. Does HashSet maintain insertion order?

> No. HashSet does not guarantee iteration order.

### Q3. Can HashSet contain null?

> Yes. HashSet permits one null element.

### Q4. How does HashSet detect duplicates?

> It uses `hashCode()` to locate the candidate bucket and `equals()` to determine whether an equivalent element already exists.

### Q5. Why override both `equals()` and `hashCode()`?

> Because hash-based collections depend on both methods. If two objects are logically equal, they must produce the same hash code.

### Q6. Can two objects have the same hash code?

> Yes. This is called a hash collision.

### Q7. Does same hash code mean objects are equal?

> No.

### Q8. HashSet vs ArrayList?

> ArrayList is useful when order and index-based access matter. HashSet is useful when uniqueness and fast membership checking matter.

---

# 25. Interview Explanation

If an interviewer asks:

**"How does HashSet work internally?"**

You can answer:

> "HashSet is backed by a hash table. When an element is added, Java uses its hash code to determine the appropriate bucket. If there are existing elements in that bucket, `equals()` is used to determine whether the new element is logically equal to an existing one. If it is equal, the duplicate isn't added. This gives HashSet average O(1) time for add, remove, and contains."

---

# 🧠 Final Mental Model

```text
                    HashSet
                       │
                       ↓
                  hashCode()
                       │
                       ↓
                    Bucket
                       │
                       ↓
                    equals()
                       │
              ┌────────┴────────┐
              ↓                 ↓
            true              false
              ↓                 ↓
          Duplicate          New element
          Don't add             Add
```

### Remember:

```text
HashSet
   ↓
Unique elements
   ↓
Hashing
   ↓
hashCode() → locate bucket
   ↓
equals() → verify equality
   ↓
Average O(1) lookup
```
