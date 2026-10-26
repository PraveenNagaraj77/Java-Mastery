# Java DSA Notes — Strings

## 1. What is a String?

A `String` is a sequence of characters represented by Java's `String` class.

```java
String name = "Praveen";
```

Strings are objects and are **immutable**: once a String object is created, its contents cannot be changed.

### Why are Strings immutable?

- String objects can safely be shared.
- String literals can be reused through the String Pool.
- Immutability supports security and reliable hashing.
- Strings are safe to share between threads without synchronization for their contents.

```java
String s = "Java";
s.concat(" Mastery");

System.out.println(s); // Java
```

`concat()` returns a new String. It does not modify `s`.

To retain the result:

```java
s = s.concat(" Mastery");
System.out.println(s); // Java Mastery
```

The variable `s` is reassigned to another String; the original object remains unchanged.

## 2. String Pool

Java can reuse String literals through a special area of memory called the String Pool.

```java
String a = "Java";
String b = "Java";

System.out.println(a == b);      // true
System.out.println(a.equals(b)); // true
```

Both variables refer to the same pooled literal.

Compare with:

```java
String a = new String("Java");
String b = new String("Java");

System.out.println(a == b);      // false
System.out.println(a.equals(b)); // true
```

These expressions create separate String objects, but their contents are equal.

**Interview rule:** Use `equals()` to compare String contents. Use `==` to compare references.

## 3. String vs StringBuilder vs StringBuffer

| Feature | String | StringBuilder | StringBuffer |
|---|---|---|---|
| Mutable | No | Yes | Yes |
| Thread-safe methods | Immutable contents | No synchronization guarantee | Synchronized methods |
| Best use | Fixed text | Repeated modifications in one thread | Shared mutable text requiring synchronized methods |

Example:

```java
StringBuilder sb = new StringBuilder("Java");
sb.append(" DSA");
sb.reverse();

System.out.println(sb); // ASD avaJ
```

Use `StringBuilder` when repeatedly appending or modifying text, especially inside loops.

## 4. Important String methods

| Method | Purpose | Example |
|---|---|---|
| `length()` | Number of UTF-16 code units | `"Java".length()` → `4` |
| `charAt(i)` | Character at index `i` | `"Java".charAt(0)` → `'J'` |
| `toCharArray()` | Copies contents into a char array | `"Java".toCharArray()` |
| `substring(a, b)` | Text from index `a` up to, but excluding, `b` | `"Java".substring(1, 3)` → `"av"` |
| `equals()` | Content comparison | `"Java".equals("Java")` → `true` |
| `equalsIgnoreCase()` | Case-insensitive comparison | `"Java".equalsIgnoreCase("JAVA")` → `true` |
| `contains()` | Checks for a sequence | `"Java DSA".contains("DSA")` → `true` |
| `indexOf()` | Finds first matching position | `"banana".indexOf('a')` → `1` |
| `toLowerCase()` | Converts to lowercase | `"JAVA".toLowerCase()` → `"java"` |
| `trim()` | Removes leading/trailing characters at or below U+0020 | `" Java ".trim()` → `"Java"` |

String indexes begin at `0`. Invalid indexes can throw `StringIndexOutOfBoundsException`.

## 5. Common String problem-solving patterns

### Pattern A: Traverse a String

```java
for (int i = 0; i < str.length(); i++) {
    char ch = str.charAt(i);
    // Process ch
}
```

Use for counting, searching, validation, and character comparisons.

### Pattern B: Two pointers

Use two indexes, commonly `left` and `right`, to process characters from opposite ends.

```java
int left = 0;
int right = str.length() - 1;

while (left < right) {
    // Compare or swap str's corresponding characters
    left++;
    right--;
}
```

Because Strings are immutable, convert to `char[]` if you need to swap characters.

### Pattern C: Frequency counting

Use a `HashMap<Character, Integer>` when character counts are needed.

```java
Map<Character, Integer> frequency = new HashMap<>();

for (int i = 0; i < str.length(); i++) {
    char ch = str.charAt(i);
    frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
}
```

`getOrDefault(ch, 0)` returns the existing count or `0` when the key is absent.

### Pattern D: Frequency comparison

To compare character counts between two Strings:
1. Count characters in the first String.
2. Subtract counts for characters in the second.
3. Detect missing characters or counts that would become negative.

### Pattern E: Two-pass processing

Some problems require both frequency information and original order:
1. First pass: build a frequency map.
2. Second pass: traverse the original String to find the first qualifying character.

A HashMap does not guarantee the original String's character order, so the second pass is important.

## 6. Problem 1 — Reverse a String

**Goal:** Return the characters in reverse order.

Example:

```text
Input:  "praveen"
Output: "neevarp"
```

### Approach

1. Convert the String to a `char[]`.
2. Set `left` to the first index and `right` to the last.
3. Swap the two characters.
4. Move `left` forward and `right` backward.
5. Stop when the pointers meet or cross.

```java
public static String reverse(String str) {
    char[] chars = str.toCharArray();

    int left = 0;
    int right = chars.length - 1;

    while (left < right) {
        char temp = chars[left];
        chars[left] = chars[right];
        chars[right] = temp;

        left++;
        right--;
    }

    return new String(chars);
}
```

**Complexity**
- Time: `O(n)`
- Auxiliary space: `O(n)` for the character array.

## 7. Problem 2 — Palindrome Check

**Goal:** Determine whether a String reads the same forwards and backwards.

Examples:

```text
"abba"  -> true
"madam" -> true
"hello" -> false
```

### Approach

1. Start pointers at both ends.
2. Compare the characters.
3. If they differ, return `false`.
4. Otherwise move both pointers inward.
5. If all pairs match, return `true`.

```java
public static boolean isPalindrome(String str) {
    int left = 0;
    int right = str.length() - 1;

    while (left < right) {
        if (str.charAt(left) != str.charAt(right)) {
            return false;
        }

        left++;
        right--;
    }

    return true;
}
```

**Complexity**
- Time: `O(n)`
- Auxiliary space: `O(1)`.

This version is case-sensitive and considers spaces and punctuation. For example, `"Madam"` is not a palindrome under these exact rules.

## 8. Problem 3 — Character Frequency

**Goal:** Count how many times each character occurs.

Example:

```text
Input: "banana"
Output: b=1, a=3, n=2
```

### Approach

Traverse the String once and increment the count for each character.

```java
public static Map<Character, Integer> countFrequency(String str) {
    Map<Character, Integer> frequency = new HashMap<>();

    for (int i = 0; i < str.length(); i++) {
        char ch = str.charAt(i);
        frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
    }

    return frequency;
}
```

**Common mistake**

This can throw a `NullPointerException`:

```java
frequency.put(ch, frequency.get(ch) + 1);
```

On the first occurrence, `get(ch)` returns `null`. Use `getOrDefault(ch, 0)` instead.

**Complexity**
- Time: `O(n)` average
- Space: `O(k)`, where `k` is the number of distinct characters.

## 9. Problem 4 — Check Anagrams

**Goal:** Check whether two Strings contain the same characters with the same frequencies, regardless of order.

Examples:

```text
"listen", "silent" -> true
"aabb", "baba"     -> true
"hello", "world"   -> false
```

### Approach

1. If the lengths differ, return `false`.
2. Count characters in the first String.
3. Traverse the second String and decrement the corresponding counts.
4. If a character has no remaining count, return `false`.
5. If every character is consumed successfully, return `true`.

```java
public static boolean areAnagrams(String first, String second) {
    if (first.length() != second.length()) {
        return false;
    }

    Map<Character, Integer> frequency = new HashMap<>();

    for (int i = 0; i < first.length(); i++) {
        char ch = first.charAt(i);
        frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
    }

    for (int i = 0; i < second.length(); i++) {
        char ch = second.charAt(i);
        int count = frequency.getOrDefault(ch, 0);

        if (count == 0) {
            return false;
        }

        frequency.put(ch, count - 1);
    }

    return true;
}
```

**Complexity**
- Time: `O(n)` average
- Space: `O(k)`.

This version is case-sensitive and treats spaces as characters.

## 10. Problem 5 — First Non-Repeating Character

**Goal:** Return the first character that appears exactly once.

Examples:

```text
"swiss"   -> 'w'
"aabbcde" -> 'c'
"aabb"    -> null
```

### Approach

1. Count all characters using a frequency map.
2. Traverse the original String from left to right.
3. Return the first character whose count is `1`.
4. Return `null` if none exists.

```java
public static Character findFirstNonRepeatingCharacter(String str) {
    Map<Character, Integer> frequency = new HashMap<>();

    for (int i = 0; i < str.length(); i++) {
        char ch = str.charAt(i);
        frequency.put(ch, frequency.getOrDefault(ch, 0) + 1);
    }

    for (int i = 0; i < str.length(); i++) {
        char ch = str.charAt(i);

        if (frequency.get(ch) == 1) {
            return ch;
        }
    }

    return null;
}
```

**Complexity**
- Time: `O(n)` average
- Space: `O(k)`.

The second loop preserves the original order and identifies the *first* unique character.

## 11. Common interview mistakes

1. Using `==` instead of `equals()` to compare String contents.
2. Assuming a String can be modified in place.
3. Accessing a missing HashMap key and adding `1` to `null`.
4. Confusing a character with its index.
5. Returning `true` without checking all necessary conditions.
6. Forgetting to return from a method when a match is found.
7. Assuming HashMap iteration preserves insertion order.
8. Forgetting edge cases such as empty Strings, repeated characters, and strings of different lengths.
9. Ignoring case, spaces, and punctuation requirements in the problem statement.

## 12. Interview revision questions

**Q1. Why is String immutable?**

String immutability supports safe sharing, String Pool reuse, security, and reliable hashing.

**Q2. What is the difference between `==` and `equals()`?**

`==` compares references; `equals()` compares String contents.

**Q3. Why use StringBuilder?**

It allows mutable character sequences and is efficient for repeated String modifications.

**Q4. Why use a HashMap for character frequency?**

It associates each character with a count, allowing efficient average-time lookups and updates.

**Q5. Why does finding the first non-repeating character need a second pass?**

The frequency map gives counts but does not guarantee original character order. A second traversal finds the first unique character in that order.

**Q6. What is the time complexity of reversing a String with two pointers?**

`O(n)`, because approximately half the characters are swapped, giving linear time overall.

**Q7. What is the difference between an anagram check and a palindrome check?**

Anagrams compare character frequencies between two Strings. Palindromes compare a single String against its reverse.

## 13. Quick complexity summary

| Problem | Time | Auxiliary space |
|---|---|---|
| Reverse String | `O(n)` | `O(n)` |
| Palindrome Check | `O(n)` | `O(1)` |
| Character Frequency | `O(n)` average | `O(k)` |
| Check Anagrams | `O(n)` average | `O(k)` |
| First Non-Repeating Character | `O(n)` average | `O(k)` |

Here, `n` is the String length and `k` is the number of distinct characters.

## 14. Practice checklist

- [ ] Reverse a String without using `StringBuilder.reverse()`.
- [ ] Check a palindrome.
- [ ] Count character frequencies.
- [ ] Check whether two Strings are anagrams.
- [ ] Find the first non-repeating character.
- [ ] Explain the approach before coding.
- [ ] State time and space complexity.
- [ ] Test empty input, repeated characters, and edge cases.

---

**Key takeaway:** For String problems, first determine whether you need traversal, two pointers, frequency counting, or a combination of these patterns. Explain the algorithm in plain English before translating it into Java.