# Strings in Java

## 1. What is a String?

A `String` in Java is an object that represents a sequence of characters.

```java
String name = "Praveen";
```

Unlike primitive types such as `int`, `char`, and `boolean`, `String` is a class in Java.

```java
String name = new String("Java");
```

However, String literals are normally preferred:

```java
String name = "Java";
```

---

# 2. Why are Strings Important?

Strings are used extensively in real-world applications:

* User names
* Email addresses
* Passwords
* URLs
* API requests and responses
* JSON data
* Database values
* File contents
* Logs
* Search operations

Almost every Java application works with Strings.

---

# 3. String Immutability

A String is **immutable**.

Immutable means that once a String object is created, its contents cannot be changed.

Example:

```java
String name = "Java";

name = name + " Mastery";
```

It may look like the existing String was modified.

It wasn't.

Conceptually:

```text
"Java"
   ↓
New String
   ↓
"Java Mastery"
```

The original `"Java"` object remains unchanged.

---

# 4. Why is String Immutable?

String immutability provides several advantages.

### 1. Security

Strings are commonly used for:

* File paths
* URLs
* Database connections
* Class names
* Credentials

Allowing String contents to change could create security problems.

### 2. String Pool

Because Strings cannot change, Java can safely reuse String objects.

```java
String a = "Java";
String b = "Java";
```

Both can refer to the same pooled String.

### 3. Hashing

Strings are frequently used as keys in collections such as:

```java
HashMap<String, Integer>
```

If a String could change after being used as a key, its hash value could become inconsistent.

### 4. Thread Safety

Immutable objects can safely be shared between multiple threads because their state cannot be modified.

---

# 5. String Pool

Java maintains a special area called the **String Pool** for String literals.

Example:

```java
String a = "Java";
String b = "Java";
```

Conceptually:

```text
          String Pool

          +-------+
a ------> | Java  | <------ b
          +-------+
```

The JVM can reuse the existing `"Java"` object instead of creating another identical object.

---

# 6. `==` vs `.equals()`

This is a very common interview question.

### `==`

Checks whether two references point to the same object.

### `.equals()`

Checks whether two Strings contain the same value.

Example:

```java
String a = "Java";
String b = "Java";

System.out.println(a == b);
System.out.println(a.equals(b));
```

Output:

```text
true
true
```

Because both literals can refer to the same String Pool object.

Now:

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

### Interview Rule

For String content comparison, normally use:

```java
a.equals(b)
```

not:

```java
a == b
```

---

# 7. Important String Methods

## length()

Returns the number of characters.

```java
String name = "Java";

System.out.println(name.length());
```

Output:

```text
4
```

---

## charAt()

Returns the character at a particular index.

```java
String name = "Java";

System.out.println(name.charAt(0));
```

Output:

```text
J
```

Indexes start from `0`.

```text
J a v a
0 1 2 3
```

---

## substring()

Extracts part of a String.

```java
String language = "JavaProgramming";

System.out.println(
    language.substring(4)
);
```

Output:

```text
Programming
```

Another example:

```java
language.substring(0, 4);
```

Output:

```text
Java
```

The ending index is exclusive.

---

## contains()

Checks whether a String contains another sequence.

```java
String message = "Welcome to Java";

System.out.println(
    message.contains("Java")
);
```

Output:

```text
true
```

---

## toUpperCase()

```java
String name = "java";

System.out.println(
    name.toUpperCase()
);
```

Output:

```text
JAVA
```

---

## toLowerCase()

```java
String name = "JAVA";

System.out.println(
    name.toLowerCase()
);
```

Output:

```text
java
```

---

## trim()

Removes leading and trailing whitespace.

```java
String name = "   Java   ";

System.out.println(name.trim());
```

Result:

```text
Java
```

---

## isEmpty()

Checks whether the String has zero characters.

```java
String value = "";

System.out.println(
    value.isEmpty()
);
```

Output:

```text
true
```

---

## isBlank()

Checks whether the String is empty or contains only whitespace.

```java
String value = "   ";

System.out.println(
    value.isBlank()
);
```

Output:

```text
true
```

`isBlank()` was introduced in Java 11.

---

# 8. String Concatenation

We can concatenate Strings using `+`.

```java
String firstName = "Praveen";
String lastName = "Nagaraj";

String fullName =
        firstName + " " + lastName;
```

Result:

```text
Praveen Nagaraj
```

For a small number of operations, this is perfectly fine.

For repeated modifications, prefer `StringBuilder`.

---

# 9. StringBuilder

`StringBuilder` is mutable.

```java
StringBuilder builder =
        new StringBuilder("Java");

builder.append(" Mastery");

System.out.println(builder);
```

Output:

```text
Java Mastery
```

Unlike String:

```text
String
→ Immutable
```

StringBuilder:

```text
StringBuilder
→ Mutable
```

---

# 10. StringBuilder Methods

### append()

```java
builder.append("Java");
```

### insert()

```java
builder.insert(0, "Learn ");
```

### delete()

```java
builder.delete(0, 5);
```

### replace()

```java
builder.replace(0, 4, "Spring");
```

### reverse()

```java
builder.reverse();
```

---

# 11. StringBuffer

`StringBuffer` is also mutable.

The main difference is thread safety.

```text
StringBuilder
→ Mutable
→ Not synchronized
→ Faster

StringBuffer
→ Mutable
→ Synchronized
→ Thread-safe
→ Slightly slower
```

### When to use?

Use `StringBuilder` for most single-threaded String construction.

Use `StringBuffer` when synchronized mutable String operations are specifically required.

---

# 12. String vs StringBuilder vs StringBuffer

| Feature                               | String                   | StringBuilder          | StringBuffer             |
| ------------------------------------- | ------------------------ | ---------------------- | ------------------------ |
| Mutable                               | No                       | Yes                    | Yes                      |
| Thread-safe                           | Yes, due to immutability | No                     | Yes                      |
| Synchronized                          | No                       | No                     | Yes                      |
| Performance for repeated modification | Lower                    | Higher                 | Lower than StringBuilder |
| Typical use                           | Fixed text               | Frequent modifications | Thread-safe mutable text |

---

# 13. String DSA — Character Frequency

A common DSA pattern is character frequency counting.

For lowercase English characters:

```java
int[] frequency = new int[26];
```

Then:

```java
frequency[character - 'a']++;
```

Example:

```text
banana
```

Frequency:

```text
a → 3
b → 1
n → 2
```

This pattern can be used for:

* Duplicate characters
* Character counting
* First non-repeating character
* Anagram checking

---

# 14. String DSA — Reverse a String

Using two pointers:

```java
char[] characters =
        input.toCharArray();

int left = 0;
int right = characters.length - 1;

while (left < right) {

    char temp = characters[left];

    characters[left] =
            characters[right];

    characters[right] = temp;

    left++;
    right--;
}
```

This uses the **Two Pointer pattern**.

---

# 15. String DSA — Palindrome

A palindrome reads the same forward and backward.

Examples:

```text
level
madam
racecar
```

Two-pointer approach:

```text
left →       ← right

l e v e l
```

Compare both ends and move toward the center.

Complexity:

```text
Time  : O(n)
Space : O(1)
```

---

# 16. String DSA — First Non-Repeating Character

Use two passes.

### First pass

Count frequencies.

### Second pass

Traverse the original String and find the first character with frequency `1`.

Example:

```text
swiss
```

Frequency:

```text
s → 3
w → 1
i → 1
```

Answer:

```text
w
```

Pattern:

```text
Frequency Counting
+
Original Order
```

---

# 17. String DSA — Anagram

Two Strings are anagrams if they contain the same characters with the same frequencies.

Example:

```text
listen
silent
```

Approach:

1. Compare lengths.
2. Count characters in the first String.
3. Subtract characters from the second String.
4. Verify that all counts are zero.

Complexity with a fixed 26-character alphabet:

```text
Time  : O(n)
Space : O(1)
```

---

# 18. Important String DSA Patterns

Remember the patterns rather than memorizing individual solutions.

```text
String Problem
      │
      ├── Frequency?
      │      ↓
      │   int[26]
      │   HashMap
      │
      ├── Compare both ends?
      │      ↓
      │   Two Pointers
      │
      ├── Continuous substring?
      │      ↓
      │   Sliding Window
      │
      └── Repeated modification?
             ↓
         StringBuilder
```

---

# 19. Common Interview Mistakes

### Mistake 1 — Using `==` for content comparison

Incorrect:

```java
if (a == b)
```

Prefer:

```java
if (a.equals(b))
```

---

### Mistake 2 — Forgetting String immutability

This:

```java
name.toUpperCase();
```

does not modify `name`.

Instead:

```java
name = name.toUpperCase();
```

---

### Mistake 3 — Using String concatenation repeatedly

Avoid:

```java
String result = "";

for (...) {
    result += value;
}
```

For large repeated operations, prefer:

```java
StringBuilder result =
        new StringBuilder();
```

---

### Mistake 4 — Off-by-one errors

Remember:

```java
substring(start, end)
```

uses an exclusive `end` index.

---

# 20. Interview Questions

### Beginner

1. What is a String in Java?
2. Is String a primitive or an object?
3. Why is String immutable?
4. What is the String Pool?
5. What is the difference between `==` and `.equals()`?
6. What does `charAt()` do?
7. What does `substring()` do?

### Intermediate

8. String vs StringBuilder?
9. StringBuilder vs StringBuffer?
10. Why is StringBuilder faster?
11. What happens when we concatenate Strings repeatedly?
12. Why are Strings useful as HashMap keys?
13. What is `intern()`?
14. What is the difference between `isEmpty()` and `isBlank()`?

### DSA

15. How do you reverse a String?
16. How do you check whether a String is a palindrome?
17. How do you find duplicate characters?
18. How do you find the first non-repeating character?
19. How do you check whether two Strings are anagrams?
20. What patterns can be used for String problems?

---

# 21. Quick Revision

```text
String
→ Immutable

String Pool
→ Reuses String literals

==
→ Reference comparison

equals()
→ Content comparison

StringBuilder
→ Mutable + fast

StringBuffer
→ Mutable + synchronized

Frequency Array
→ Character frequency

Two Pointers
→ Reverse / Palindrome

Sliding Window
→ Substring problems
```

---

# 22. Key Takeaway

Don't memorize String problems individually.

Recognize the underlying pattern:

```text
Duplicate Characters
        ↓
Frequency

First Non-Repeating
        ↓
Frequency + Order

Anagram
        ↓
Frequency Comparison

Palindrome
        ↓
Two Pointers

Reverse String
        ↓
Two Pointers

Large String Construction
        ↓
StringBuilder
```

That pattern-recognition approach is the foundation of our DSA preparation.
