# ☕ Java Collections — Last-Minute Revision Cheat Sheet

> **Purpose:** Last-minute interview revision + quickly identify which Collection to use from a problem statement.

---

# 1. Collection Pattern Identification

## 🚨 The Most Important Rule

When you see a problem, ask:

```text
1. Do I need duplicates?
2. Do I need order?
3. Do I need sorting?
4. Do I need key → value?
5. Do I need FIFO / LIFO?
6. Do I need fast lookup?
7. Do I need index-based access?
```

Then choose the Collection.

---

# 2. Quick Decision Table

| Problem Requirement                         | Use                    |
| ------------------------------------------- | ---------------------- |
| Need index-based access                     | `ArrayList`            |
| Need ordered elements + duplicates          | `ArrayList`            |
| Frequent insertion/removal at beginning/end | `LinkedList` / `Deque` |
| Need unique elements                        | `HashSet`              |
| Unique + insertion order                    | `LinkedHashSet`        |
| Unique + sorted order                       | `TreeSet`              |
| Key → Value                                 | `HashMap`              |
| Key → Value + insertion order               | `LinkedHashMap`        |
| Key → Value + sorted keys                   | `TreeMap`              |
| FIFO                                        | `Queue`                |
| FIFO + efficient implementation             | `ArrayDeque`           |
| LIFO / Stack                                | `Deque` + `ArrayDeque` |
| Add/remove from both ends                   | `Deque`                |
| Need safe removal while iterating           | `Iterator`             |
| Need forward + backward List traversal      | `ListIterator`         |
| Need natural sorting                        | `Comparable`           |
| Need custom/different sorting               | `Comparator`           |

---

# 3. ArrayList

### One-liner

> **ArrayList = ordered, duplicate-allowed, index-based dynamic array.**

### Use when

```text
Need:
✓ Order
✓ Duplicates
✓ Index access
✓ Frequent reading
```

### Pattern Recognition

If the problem says:

```text
"Get element at index..."
"Access by position..."
"Maintain order..."
"Store a list of items..."
```

Think:

```java
ArrayList
```

### Complexity

| Operation       |     Complexity |
| --------------- | -------------: |
| `get(index)`    |           O(1) |
| `set(index)`    |           O(1) |
| `add()`         | O(1) amortized |
| `add(index)`    |           O(n) |
| `remove(index)` |           O(n) |
| `contains()`    |           O(n) |

### Interview One-Liner

> I use ArrayList when I need fast index-based access and don't have frequent insertions/removals in the middle.

---

# 4. LinkedList

### One-liner

> **LinkedList = doubly linked list with efficient insertion/removal at known ends/positions.**

### Use when

```text
Need:
✓ Frequent add/remove at beginning/end
✓ List + Deque behavior
```

### Pattern Recognition

If the problem says:

```text
"Add at beginning"
"Remove from beginning"
"Add at end"
"Remove from end"
```

Think:

```java
LinkedList
```

But for pure queue/deque problems, prefer:

```java
ArrayDeque
```

### Complexity

| Operation       | Complexity |
| --------------- | ---------: |
| `addFirst()`    |       O(1) |
| `addLast()`     |       O(1) |
| `removeFirst()` |       O(1) |
| `removeLast()`  |       O(1) |
| `get(index)`    |       O(n) |
| `contains()`    |       O(n) |

### Interview One-Liner

> LinkedList is useful when frequent insertion/removal happens at the ends, but it is slower than ArrayList for random index access.

---

# 5. HashSet

### One-liner

> **HashSet = unique elements + fast average lookup.**

### Use when

```text
Need:
✓ No duplicates
✓ Fast contains()
✓ Fast add/remove
✓ Order doesn't matter
```

### Pattern Recognition

If you see:

```text
"Remove duplicates"
"Check if duplicate exists"
"Have we seen this before?"
"Find repeated element"
"Unique elements"
"Visited elements"
```

Think:

```java
HashSet
```

### Classic Pattern

```java
Set<Integer> set = new HashSet<>();

if (!set.add(number)) {
    // duplicate found
}
```

### Complexity

Average:

```text
add()      → O(1)
remove()   → O(1)
contains() → O(1)
```

### Interview One-Liner

> Whenever I need uniqueness and fast membership checking, HashSet is usually my first choice.

---

# 6. LinkedHashSet

### One-liner

> **LinkedHashSet = HashSet + insertion order.**

### Use when

```text
Need:
✓ Unique elements
✓ Preserve original/insertion order
```

### Pattern Recognition

If the problem says:

```text
"Remove duplicates but preserve order"
"Unique elements in original order"
"First occurrence should remain"
```

Think:

```java
LinkedHashSet
```

### Example

```text
Input:
A B A C B D

Output:
A B C D
```

### Interview One-Liner

> Use LinkedHashSet when I need uniqueness while preserving insertion order.

---

# 7. TreeSet

### One-liner

> **TreeSet = unique elements + sorted order.**

### Use when

```text
Need:
✓ Unique elements
✓ Sorted order
✓ Range operations
✓ Nearest value
```

### Pattern Recognition

If the problem says:

```text
"Unique and sorted"
"Smallest element"
"Largest element"
"Next greater"
"Previous smaller"
"Closest value"
"Elements within a range"
```

Think:

```java
TreeSet
```

### Useful Methods

```java
first()
last()

lower(x)
higher(x)

floor(x)
ceiling(x)

headSet(x)
tailSet(x)
subSet(a, b)
```

### Complexity

```text
add()      → O(log n)
remove()   → O(log n)
contains() → O(log n)
```

### Interview One-Liner

> Use TreeSet when I need uniqueness together with sorted-order and navigation operations.

---

# 8. HashMap

### One-liner

> **HashMap = key → value + fast average lookup.**

### Use when

```text
Need:
✓ Key → Value
✓ Frequency counting
✓ Fast lookup
✓ Store relationships
✓ Remember previously seen values
```

### Pattern Recognition

If you see:

```text
"Count frequency"
"How many times?"
"Map ID to object"
"Find value using key"
"Store index of a number"
"Have we seen this value?"
"Two Sum"
"Count pairs"
```

Think:

```java
HashMap
```

---

## 🔥 Most Important HashMap Pattern

### Frequency

```java
Map<Integer, Integer> frequency = new HashMap<>();

frequency.put(
    number,
    frequency.getOrDefault(number, 0) + 1
);
```

Whenever you see:

> "How many times does each element occur?"

Think:

```text
HashMap
```

---

## Two Sum Pattern

```text
number → index
```

Example:

```text
target = 9

2 → 0
7 → 1
```

Think:

```java
HashMap
```

### Complexity

Average:

```text
put()       → O(1)
get()       → O(1)
containsKey → O(1)
remove()    → O(1)
```

### Interview One-Liner

> HashMap is my default choice when I need key-value mapping or fast average lookup.

---

# 9. LinkedHashMap

### One-liner

> **LinkedHashMap = HashMap + predictable iteration order.**

### Use when

```text
Need:
✓ Key → Value
✓ Fast lookup
✓ Preserve insertion order
```

### Pattern Recognition

If the problem says:

```text
"Maintain insertion order"
"Return entries in the order they were inserted"
"Unique keys + original order"
```

Think:

```java
LinkedHashMap
```

### Example

```text
Input:

101 → Java
102 → Spring
103 → React

Iteration:

101 → Java
102 → Spring
103 → React
```

### Special Feature

Access-order:

```java
new LinkedHashMap<>(16, 0.75f, true);
```

Useful for:

```text
LRU Cache
```

### Interview One-Liner

> LinkedHashMap is useful when I need HashMap-style lookup while maintaining predictable iteration order.

---

# 10. TreeMap

### One-liner

> **TreeMap = key-value pairs + sorted keys.**

### Use when

```text
Need:
✓ Key → Value
✓ Sorted keys
✓ Range queries
✓ Previous/next key
✓ Floor/ceiling key
```

### Pattern Recognition

If the problem says:

```text
"Sort by key"
"Find smallest key"
"Find largest key"
"Next greater key"
"Previous smaller key"
"Find keys within range"
```

Think:

```java
TreeMap
```

### Useful Methods

```java
firstKey()
lastKey()

lowerKey(x)
higherKey(x)

floorKey(x)
ceilingKey(x)

headMap()
tailMap()
subMap()

descendingMap()
```

### Complexity

```text
put()         → O(log n)
get()         → O(log n)
remove()      → O(log n)
containsKey() → O(log n)
```

### Interview One-Liner

> Use TreeMap when key-value mapping is required and the keys must remain sorted.

---

# 11. Queue

### One-liner

> **Queue = FIFO → First In, First Out.**

### Pattern Recognition

If you see:

```text
First come, first served
Process in arrival order
Waiting line
Task processing
BFS
```

Think:

```java
Queue
```

### Important Methods

```java
offer() → add
poll()  → remove front
peek()  → inspect front
```

### Example

```text
A B C D

poll()

A removed

B C D
```

### Interview One-Liner

> Use Queue when elements must be processed in FIFO order.

---

# 12. Deque

### One-liner

> **Deque = Double Ended Queue.**

### Pattern Recognition

If you need:

```text
Add from front
Add from back
Remove from front
Remove from back
```

Think:

```java
Deque
```

### Important Methods

```java
offerFirst()
offerLast()

pollFirst()
pollLast()

peekFirst()
peekLast()
```

---

# 13. ArrayDeque

### One-liner

> **ArrayDeque = efficient Queue + Deque + Stack implementation.**

### Queue

```java
Deque<Integer> queue = new ArrayDeque<>();

queue.offer(10);
queue.offer(20);

queue.poll();
```

### Stack

```java
Deque<Integer> stack = new ArrayDeque<>();

stack.push(10);
stack.push(20);

stack.pop();
```

### Pattern Recognition

If you see:

```text
FIFO
LIFO
Stack
Queue
Sliding window
BFS
Monotonic stack/deque
Add/remove from both ends
```

Think:

```java
ArrayDeque
```

### Interview One-Liner

> ArrayDeque is generally preferred for typical Queue/Stack/Deque operations because it is optimized for operations at both ends.

---

# 14. Stack

### One-liner

> **Stack = LIFO → Last In, First Out.**

### Pattern Recognition

If you see:

```text
Undo
Backtracking
Parentheses matching
Expression evaluation
Browser history
Function call behavior
Next Greater Element
DFS-style processing
```

Think:

```java
Deque
```

implemented using:

```java
ArrayDeque
```

### Pattern

```java
Deque<Integer> stack = new ArrayDeque<>();

stack.push(10);
stack.push(20);
stack.push(30);

stack.pop(); // 30
```

### Interview One-Liner

> In modern Java, I prefer Deque with ArrayDeque instead of the legacy Stack class.

---

# 15. Iterator

### One-liner

> **Iterator = safely traverse a Collection and remove elements during traversal.**

### Pattern Recognition

If the problem says:

```text
"Remove elements while iterating"
"Traverse collection safely"
```

Think:

```java
Iterator
```

### Example

```java
Iterator<Integer> iterator = numbers.iterator();

while (iterator.hasNext()) {

    Integer number = iterator.next();

    if (number % 2 == 0) {
        iterator.remove();
    }
}
```

### Key Rule

```text
hasNext() → check
next()    → move/get
remove()  → remove last returned element
```

### Interview One-Liner

> Iterator is useful when I need controlled traversal and safe removal during collection iteration.

---

# 16. ListIterator

### One-liner

> **ListIterator = Iterator + backward traversal + modification operations.**

### Pattern Recognition

If you need:

```text
Forward traversal
Backward traversal
set()
add()
remove()
```

Think:

```java
ListIterator
```

### Important

Works with:

```text
List
```

Not:

```text
Set
Map
```

---

# 17. Comparable

### One-liner

> **Comparable = class defines its own natural/default ordering.**

### Pattern Recognition

If the problem says:

```text
"Natural ordering"
"Default sorting"
"Sort objects by their default property"
```

Think:

```java
Comparable
```

### Example

```java
class Employee implements Comparable<Employee> {

    @Override
    public int compareTo(Employee other) {
        return Double.compare(
            this.salary,
            other.salary
        );
    }
}
```

### Interview One-Liner

> Comparable is used when a class has one natural ordering.

---

# 18. Comparator

### One-liner

> **Comparator = external/custom sorting logic.**

### Pattern Recognition

If the problem says:

```text
Sort by name
Sort by salary
Sort by age
Sort descending
Sort using multiple fields
Different sorting requirements
```

Think:

```java
Comparator
```

### Example

```java
Comparator<Employee> byName =
    Comparator.comparing(Employee::getName);
```

Multiple fields:

```java
Comparator<Employee> comparator =
    Comparator.comparingDouble(Employee::getSalary)
              .thenComparing(Employee::getName)
              .thenComparingInt(Employee::getId);
```

### Interview One-Liner

> Use Comparator when sorting logic is external, custom, or when multiple different orderings are required.

---

# 19. The Big Three Sets

Memorize this:

```text
HashSet
   ↓
Unique
Fast lookup
No guaranteed order


LinkedHashSet
   ↓
Unique
Insertion order
Fast average lookup


TreeSet
   ↓
Unique
Sorted order
O(log n)
```

### Pattern

```text
Unique only?
    ↓
HashSet

Unique + preserve order?
    ↓
LinkedHashSet

Unique + sorted/navigation?
    ↓
TreeSet
```

---

# 20. The Big Three Maps

Memorize this:

```text
HashMap
   ↓
Key → Value
No guaranteed order
Fast average lookup


LinkedHashMap
   ↓
Key → Value
Insertion/access order
Fast average lookup


TreeMap
   ↓
Key → Value
Sorted keys
O(log n)
```

### Pattern

```text
Need key-value?
      ↓
   HashMap

Need key-value + insertion order?
      ↓
 LinkedHashMap

Need key-value + sorted keys?
      ↓
   TreeMap
```

---

# 21. The List Decision

```text
Need a List?
     ↓
Need fast index access?
     ↓
   ArrayList

Need frequent add/remove at ends?
     ↓
 LinkedList
```

But:

```text
Need Queue / Deque?
     ↓
 Prefer ArrayDeque
```

---

# 22. Queue vs Stack

### FIFO

```text
First In
   ↓
First Out

A B C

remove → A
```

Use:

```java
Queue
```

or:

```java
ArrayDeque
```

---

### LIFO

```text
Last In
   ↓
First Out

A B C

remove → C
```

Use:

```java
Deque
```

with:

```java
ArrayDeque
```

---

# 23. Common DSA Patterns

## Pattern 1 — Remove Duplicates

```text
Need uniqueness
```

Use:

```java
HashSet
```

If order matters:

```java
LinkedHashSet
```

---

## Pattern 2 — Detect Duplicate

Problem:

> Find whether an array contains duplicates.

Think:

```text
HashSet
```

Pattern:

```java
if (!set.add(number)) {
    // duplicate
}
```

---

## Pattern 3 — First Repeated Element

Problem:

```text
10 20 30 20 40
```

Think:

```text
HashSet
```

Why?

```text
Have we already seen this?
```

---

## Pattern 4 — Frequency Count

Problem:

> Count occurrences of each number.

Think:

```text
HashMap
```

Pattern:

```java
map.put(
    number,
    map.getOrDefault(number, 0) + 1
);
```

---

## Pattern 5 — First Non-Repeating Character

Problem:

```text
a a b c c
```

Think:

```text
HashMap
```

Strategy:

```text
Pass 1 → frequency
Pass 2 → find first frequency == 1
```

---

## Pattern 6 — Two Sum

Problem:

```text
Find two numbers whose sum = target
```

Think:

```text
HashMap
```

Pattern:

```text
number → index
```

---

## Pattern 7 — Count Pairs

Problem:

> Count pairs whose sum equals target.

Think:

```text
HashMap
```

Pattern:

```text
number → frequency
```

---

## Pattern 8 — Longest Consecutive Sequence

Problem:

```text
100 4 200 1 3 2
```

Think:

```text
HashSet
```

Why?

```text
Fast "does this number exist?"
```

---

## Pattern 9 — Sorted Unique Elements

Think:

```text
TreeSet
```

---

## Pattern 10 — Find Closest / Next / Previous

Problem:

```text
Find closest number
Find next greater
Find previous smaller
```

Think:

```text
TreeSet
```

Use:

```java
lower()
higher()
floor()
ceiling()
```

---

## Pattern 11 — Sorted Key-Value Data

Problem:

> Store employees by ID and keep IDs sorted.

Think:

```text
TreeMap
```

---

## Pattern 12 — Preserve Insertion Order

Problem:

> Remove duplicates while preserving original order.

Think:

```text
LinkedHashSet
```

If key-value:

```text
LinkedHashMap
```

---

## Pattern 13 — FIFO Processing

Problem:

> Process requests in the order they arrived.

Think:

```text
Queue
```

---

## Pattern 14 — LIFO Processing

Problem:

> Last operation should be processed first.

Think:

```text
Stack
```

Modern Java:

```text
Deque + ArrayDeque
```

---

## Pattern 15 — Remove While Iterating

Problem:

> Remove all elements satisfying a condition while traversing.

Think:

```text
Iterator
```

---

# 24. Fastest Pattern Recognition Cheat Sheet

```text
"index"
       ↓
ArrayList


"duplicate"
       ↓
HashSet


"duplicate + preserve order"
       ↓
LinkedHashSet


"unique + sorted"
       ↓
TreeSet


"frequency"
       ↓
HashMap


"key → value"
       ↓
HashMap


"key → value + insertion order"
       ↓
LinkedHashMap


"key → value + sorted keys"
       ↓
TreeMap


"next greater / previous smaller"
       ↓
TreeSet / TreeMap


"FIFO"
       ↓
Queue


"LIFO"
       ↓
Deque / ArrayDeque


"both ends"
       ↓
Deque / ArrayDeque


"remove while iterating"
       ↓
Iterator


"forward + backward List traversal"
       ↓
ListIterator


"natural sorting"
       ↓
Comparable


"custom sorting"
       ↓
Comparator
```

---

# 25. Complexity Cheat Sheet

| Collection    |             Get/Search |        Add |    Remove | Main Advantage           |
| ------------- | ---------------------: | ---------: | --------: | ------------------------ |
| ArrayList     | O(1) get / O(n) search |      O(1)* |      O(n) | Fast index access        |
| LinkedList    |                   O(n) |     O(1)** |    O(1)** | Efficient end operations |
| HashSet       |                  O(1)* |      O(1)* |     O(1)* | Uniqueness + fast lookup |
| LinkedHashSet |                  O(1)* |      O(1)* |     O(1)* | Unique + insertion order |
| TreeSet       |               O(log n) |   O(log n) |  O(log n) | Sorted unique data       |
| HashMap       |                  O(1)* |      O(1)* |     O(1)* | Fast key-value lookup    |
| LinkedHashMap |                  O(1)* |      O(1)* |     O(1)* | Key-value + order        |
| TreeMap       |               O(log n) |   O(log n) |  O(log n) | Sorted keys              |
| ArrayDeque    |              O(1) ends | O(1)* ends | O(1) ends | Queue/Stack/Deque        |

```text
* Average / amortized where applicable

** At the ends / once the relevant node is known
```

---

# 26. Null Cheat Sheet

```text
ArrayList       → allows null
LinkedList      → allows null

HashSet         → one null
LinkedHashSet   → one null
TreeSet         → generally no null

HashMap         → one null key + multiple null values
LinkedHashMap   → one null key + multiple null values
TreeMap         → generally no null keys with natural ordering

ArrayDeque      → does NOT allow null
```

---

# 27. Ordering Cheat Sheet

```text
ArrayList
→ Insertion order


LinkedList
→ Insertion order


HashSet
→ No guaranteed order


LinkedHashSet
→ Insertion order


TreeSet
→ Sorted order


HashMap
→ No guaranteed order


LinkedHashMap
→ Insertion/access order


TreeMap
→ Sorted by key
```

---

# 28. Duplicate Cheat Sheet

```text
List
→ Duplicates allowed


HashSet
→ Duplicates NOT allowed


LinkedHashSet
→ Duplicates NOT allowed


TreeSet
→ Duplicates NOT allowed


Map
→ Keys unique
→ Values can duplicate
```

---

# 29. Internal Working — One-Liners

### ArrayList

```text
Dynamic array
→ index-based access
→ resizing when capacity is insufficient
```

### LinkedList

```text
Doubly linked nodes
→ previous + data + next
```

### HashSet

```text
Hashing
→ bucket
→ equals()
→ unique element
```

### LinkedHashSet

```text
Hash-based uniqueness
+
linked structure for insertion order
```

### TreeSet

```text
Red-Black Tree
→ sorted unique elements
```

### HashMap

```text
Hashing
→ bucket
→ key hashCode()
→ equals()
→ key-value mapping
```

### LinkedHashMap

```text
HashMap
+
linked structure for predictable order
```

### TreeMap

```text
Red-Black Tree
→ sorted keys
```

### ArrayDeque

```text
Resizable array-based deque
→ efficient operations at both ends
```

---

# 30. Comparable vs Comparator

```text
Comparable
    ↓
Class itself defines ordering
    ↓
compareTo()
    ↓
One natural ordering


Comparator
    ↓
External sorting logic
    ↓
compare()
    ↓
Multiple/custom orderings
```

### Easy Memory Trick

```text
Comparable = "I can compare myself"

Comparator = "Someone else compares me"
```

---

# 31. Set vs Map

### Set

```text
Only values

A
B
C
```

Use when:

```text
Uniqueness matters
```

---

### Map

```text
Key → Value

101 → Java
102 → Spring
103 → React
```

Use when:

```text
You need to associate one thing with another.
```

---

# 32. Collection vs Collections

### Collection

```text
Collection = interface
```

Examples:

```java
List
Set
Queue
```

### Collections

```text
Collections = utility class
```

Examples:

```java
Collections.sort()
Collections.reverse()
Collections.max()
Collections.min()
```

### Easy Memory Trick

```text
Collection  → Interface
Collections → Utility class
```

---

# 33. Map Is NOT a Collection

Important interview question:

> Does Map extend Collection?

Answer:

```text
NO
```

Hierarchy:

```text
Collection
├── List
├── Set
└── Queue

Map
├── HashMap
├── LinkedHashMap
└── TreeMap
```

Map is part of the **Java Collections Framework**, but it does not extend `Collection`.

---

# 34. Most Important Interview Comparisons

## ArrayList vs LinkedList

```text
ArrayList
→ Fast get(index)
→ Dynamic array

LinkedList
→ Doubly linked nodes
→ Efficient end insertion/removal
→ Slow random access
```

---

## HashSet vs LinkedHashSet

```text
HashSet
→ Unique
→ No guaranteed order

LinkedHashSet
→ Unique
→ Insertion order
```

---

## HashSet vs TreeSet

```text
HashSet
→ Unique
→ O(1) average
→ No guaranteed order

TreeSet
→ Unique
→ Sorted
→ O(log n)
```

---

## HashMap vs LinkedHashMap

```text
HashMap
→ Key-value
→ No guaranteed order

LinkedHashMap
→ Key-value
→ Predictable insertion/access order
```

---

## HashMap vs TreeMap

```text
HashMap
→ Fast average lookup
→ No guaranteed order

TreeMap
→ Sorted keys
→ O(log n)
```

---

## Queue vs Deque

```text
Queue
→ Usually one-ended FIFO abstraction


Deque
→ Both ends
→ Can behave as Queue
→ Can behave as Stack
```

---

# 35. Golden Interview Rules 🏆

### Rule 1

> **Need uniqueness? → Set**

### Rule 2

> **Need key-value mapping? → Map**

### Rule 3

> **Need fast average lookup? → HashMap / HashSet**

### Rule 4

> **Need insertion order? → LinkedHashSet / LinkedHashMap**

### Rule 5

> **Need sorted data? → TreeSet / TreeMap**

### Rule 6

> **Need index access? → ArrayList**

### Rule 7

> **Need FIFO? → Queue**

### Rule 8

> **Need LIFO? → Deque + ArrayDeque**

### Rule 9

> **Need both ends? → Deque**

### Rule 10

> **Need safe removal during iteration? → Iterator**

### Rule 11

> **Need natural sorting? → Comparable**

### Rule 12

> **Need custom sorting? → Comparator**

---

# 36. The Ultimate 10-Second Decision Tree

When you get a coding problem:

```text
                    START
                      │
                      ▼
             Need Key → Value?
                /           \
              YES            NO
               │              │
               ▼              ▼
             MAP?          Need uniqueness?
               │            /          \
               │          YES           NO
               │           │              │
               │           ▼              ▼
               │      Need sorted?    Need index?
               │       /      \        /      \
               │     YES      NO     YES       NO
               │      │        │      │         │
               │      ▼        ▼      ▼         ▼
               │   TreeSet  HashSet  ArrayList  Queue/
               │                              LinkedList/
               │                               Deque
               │
               ▼
        Need sorted keys?
          /          \
        YES           NO
         │             │
         ▼             ▼
     TreeMap       HashMap
                       │
                       ▼
              Need insertion order?
                  │
              LinkedHashMap
```

---

# 37. Final Mental Model

Don't memorize every method.

Remember these **7 words**:

```text
LIST
→ Order / Index


SET
→ Unique


MAP
→ Key → Value


QUEUE
→ FIFO


DEQUE
→ Both Ends


TREE
→ Sorted


HASH
→ Fast Average Lookup
```

Then combine them:

```text
ArrayList
→ List + index


HashSet
→ Set + hash


LinkedHashSet
→ Set + hash + order


TreeSet
→ Set + sorted


HashMap
→ Map + hash


LinkedHashMap
→ Map + hash + order


TreeMap
→ Map + sorted


ArrayDeque
→ Queue/Stack + both ends
```

---

# 🧠 Final Interview Cheat Code

```text
Duplicate?
    → HashSet

Duplicate + preserve order?
    → LinkedHashSet

Unique + sorted?
    → TreeSet

Frequency?
    → HashMap

Two Sum?
    → HashMap

Count pairs?
    → HashMap

Fast lookup?
    → HashMap / HashSet

Sorted keys?
    → TreeMap

Next/previous/closest?
    → TreeSet / TreeMap

Index access?
    → ArrayList

FIFO?
    → Queue / ArrayDeque

LIFO?
    → Deque / ArrayDeque

Both ends?
    → Deque / ArrayDeque

Remove during iteration?
    → Iterator

Forward + backward List traversal?
    → ListIterator

Natural sorting?
    → Comparable

Custom sorting?
    → Comparator
```

---

# 🎯 The Main Goal

When an interviewer gives you a problem, don't immediately start coding.

First ask:

```text
"What operation does this problem need?"
```

Then:

```text
"Which Collection gives me that operation efficiently?"
```

Then code.

Example:

```text
Problem:
Find first repeated number.

Question:
What do I need?

→ Have I seen this number before?

Collection:
→ HashSet

Pattern:
→ contains / add

Complexity:
→ O(n) average
```

That is **Collection pattern recognition**.

---

> **Don't memorize Collections. Recognize the requirement, then choose the Collection.**
