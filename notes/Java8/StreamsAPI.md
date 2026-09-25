# ☕ Java Stream API — Last-Minute Interview Cheat Sheet

> **Purpose:** Quick revision of Stream API fundamentals + interview pattern recognition.

---

# 1. What Is Stream API?

Java Stream API was introduced in **Java 8**.

A Stream is used to **process data from a source** in a declarative way.

```text
Collection → stores data
Stream     → processes data
```

Example:

```java
employees.stream()
        .filter(employee -> employee.getSalary() > 60000)
        .forEach(System.out::println);
```

---

# 2. Stream Pipeline

The basic Stream pattern:

```text
Source
   ↓
Intermediate Operations
   ↓
Terminal Operation
```

Example:

```java
employees.stream()
        .filter(Employee::isActive)
        .map(Employee::getName)
        .forEach(System.out::println);
```

Think:

```text
Source → Process → Finish
```

---

# 3. Stream Has 3 Parts

| Part | Purpose | Examples |
|---|---|---|
| Source | Provides data | List, Set, Array |
| Intermediate | Processes data | filter, map, sorted |
| Terminal | Finishes pipeline | forEach, collect, count |

---

# 4. Creating Streams

## From Collection

```java
list.stream();
```

```java
set.stream();
```

## From Array

```java
Arrays.stream(numbers);
```

## Using Stream.of()

```java
Stream.of(10, 20, 30);
```

## Empty Stream

```java
Stream.empty();
```

## From Map

```java
map.entrySet().stream();
```

```java
map.keySet().stream();
```

```java
map.values().stream();
```

---

# 5. Primitive Streams

Java provides:

```text
IntStream
LongStream
DoubleStream
```

Example:

```java
IntStream.range(1, 5)
        .forEach(System.out::println);
```

Output:

```text
1 2 3 4
```

```java
IntStream.rangeClosed(1, 5)
        .forEach(System.out::println);
```

Output:

```text
1 2 3 4 5
```

Remember:

```text
range()       → end excluded
rangeClosed() → end included
```

---

# 6. Stream vs Collection

| Collection | Stream |
|---|---|
| Stores data | Processes data |
| Can be reused | Usually consumed once |
| Data structure | Processing pipeline |
| External iteration commonly used | Internal iteration |
| Can directly add/remove elements | Does not store elements |

Mental model:

```text
Collection = DATA
Stream     = PROCESSING
```

---

# 7. Stream Is Lazy

Intermediate operations do not execute immediately.

```java
numbers.stream()
        .filter(number -> number > 20);
```

Nothing executes yet.

A terminal operation triggers execution:

```java
numbers.stream()
        .filter(number -> number > 20)
        .forEach(System.out::println);
```

Remember:

```text
Intermediate → lazy
Terminal     → triggers execution
```

---

# 8. Intermediate Operations

Main intermediate operations:

```text
filter()
map()
flatMap()
sorted()
distinct()
limit()
skip()
peek()
```

They:

```text
✓ Return Stream
✓ Are chainable
✓ Are generally lazy
```

Quick recognition:

```text
filter   → SELECT
map      → TRANSFORM
flatMap  → FLATTEN
sorted   → ORDER
distinct → UNIQUE
limit    → TAKE
skip     → DISCARD
peek     → OBSERVE
```

> Detailed revision is covered in `StreamIntermediateOperations.md`.

---

# 9. Terminal Operations

Main terminal operations:

```text
forEach()
collect()
reduce()
count()
min()
max()
findFirst()
findAny()
anyMatch()
allMatch()
noneMatch()
```

Terminal operations:

```text
✓ Trigger execution
✓ Produce result/action
✓ End the Stream
```

Example:

```java
long count =
        numbers.stream()
                .filter(number -> number > 20)
                .count();
```

---

# 10. One Stream = One Use

A Stream cannot normally be reused after a terminal operation.

Wrong:

```java
Stream<Integer> stream =
        numbers.stream();

stream.count();

stream.forEach(System.out::println);
```

This causes:

```text
IllegalStateException
```

Correct:

```java
numbers.stream().count();

numbers.stream()
        .forEach(System.out::println);
```

Mental model:

```text
Collection → reusable
Stream     → one pipeline execution
```

---

# 11. Stream Does Not Modify the Source

Example:

```java
List<Integer> numbers =
        Arrays.asList(10, 20, 30);

numbers.stream()
        .map(number -> number * 2)
        .forEach(System.out::println);
```

Output:

```text
20
40
60
```

Original:

```text
10
20
30
```

remains unchanged.

Streams generally process data without modifying the source.

---

# 12. Internal Iteration

Traditional loop:

```java
for (Integer number : numbers) {
    System.out.println(number);
}
```

This is external iteration.

Stream:

```java
numbers.stream()
        .forEach(System.out::println);
```

The Stream handles the iteration internally.

Mental model:

```text
for loop
→ You control iteration

Stream
→ Stream pipeline controls iteration
```

---

# 13. Sequential Stream

Normal Stream:

```java
numbers.stream()
```

Processes elements sequentially.

```text
Element 1
   ↓
Element 2
   ↓
Element 3
   ↓
...
```

---

# 14. Parallel Stream

A parallel Stream can be created using:

```java
numbers.parallelStream();
```

or:

```java
numbers.stream()
        .parallel();
```

It can process work using multiple threads.

Example:

```java
numbers.parallelStream()
        .forEach(System.out::println);
```

Important:

```text
parallelStream()
≠ automatically faster
```

For small/simple operations, parallel processing may add unnecessary overhead.

---

# 15. Sequential vs Parallel

| Sequential | Parallel |
|---|---|
| `stream()` | `parallelStream()` |
| One sequential processing pipeline | Can process using multiple threads |
| Predictable encounter behavior for ordered operations | Ordering/behavior can differ depending on operation |
| Good default | Use when parallelism is actually beneficial |

Interview answer:

> A parallel Stream can improve performance for suitable large, independent workloads, but it introduces overhead and should not be used blindly.

---

# 16. Stream Does Not Store Data

Wrong mental model:

```text
Stream = Collection
```

Correct:

```text
Collection
    ↓
stores data

Stream
    ↓
processes data
```

Example:

```java
List<String> names =
        Arrays.asList("Praveen", "Arul");

names.stream();
```

The Stream is a processing view/pipeline over the source.

---

# 17. Stream Pipeline Example

```java
List<String> result =
        employees.stream()

                .filter(employee ->
                        employee.getDepartment().equals("IT"))

                .map(Employee::getName)

                .distinct()

                .sorted()

                .limit(5)

                .collect(Collectors.toList());
```

Pattern:

```text
Source
  ↓
filter
  ↓
map
  ↓
distinct
  ↓
sorted
  ↓
limit
  ↓
collect
```

---

# 18. Lazy Evaluation Example

```java
numbers.stream()
        .filter(number -> {
            System.out.println("Filtering " + number);
            return number > 20;
        });
```

Output:

```text
Nothing
```

Because:

```text
No terminal operation
        ↓
No execution
```

Add:

```java
.forEach(System.out::println);
```

Now execution starts.

---

# 19. Stream Pipeline Mental Model

Think:

```text
SOURCE
  ↓
"Where does data come from?"
  ↓
INTERMEDIATE
  ↓
"How should data be processed?"
  ↓
TERMINAL
  ↓
"What is the final result?"
```

---

# 20. Common Stream Sources

```text
List
Set
Array
Map entries
Stream.of()
IntStream
LongStream
DoubleStream
```

Examples:

```java
list.stream();

set.stream();

Arrays.stream(array);

map.entrySet().stream();

Stream.of(1, 2, 3);

IntStream.range(1, 10);
```

---

# 21. Stream API Quick Comparison

```text
Collection
→ Store data

stream()
→ Create sequential Stream

parallelStream()
→ Create parallel Stream

Intermediate operation
→ Process / transform

Terminal operation
→ Execute / finish

Stream
→ Cannot normally be reused
```

---

# 22. Most Important Interview Questions

### What is Stream API?

> Stream API is a Java 8 feature used to process collections and other data sources using declarative pipelines.

### Does a Stream store data?

> No. A Stream processes data from a source.

### Are Streams lazy?

> Intermediate operations are lazy and execute when a terminal operation triggers the pipeline.

### Can a Stream be reused?

> No. Once consumed by a terminal operation, the Stream cannot normally be reused.

### Does Stream modify the source?

> Stream operations generally do not modify the source collection.

### What is a Stream pipeline?

> A pipeline consists of a source, zero or more intermediate operations, and a terminal operation.

### Difference between Collection and Stream?

```text
Collection → stores data
Stream     → processes data
```

### Difference between stream() and parallelStream()?

```text
stream()
→ sequential

parallelStream()
→ potentially parallel
```

### Why use Streams?

```text
✓ Less boilerplate
✓ Declarative processing
✓ Easy chaining
✓ Functional programming
✓ Lazy evaluation
✓ Convenient data transformation
```

---

# 23. Stream API Golden Rules

```text
1. Stream processes data; Collection stores data.

2. Stream pipeline:
   Source → Intermediate → Terminal

3. Intermediate operations are lazy.

4. Terminal operation triggers execution.

5. Intermediate operations return Stream.

6. Terminal operation ends the Stream.

7. A Stream is normally consumed only once.

8. Stream operations generally don't modify the source.

9. stream() is sequential by default.

10. parallelStream() enables parallel processing when appropriate.
```

---

# 24. 10-Second Stream Interview Cheat Sheet

```text
Need to process a collection?
        ↓
     stream()


Need to select?
        ↓
     filter()


Need to transform?
        ↓
      map()


Need to flatten?
        ↓
    flatMap()


Need to sort?
        ↓
     sorted()


Need unique values?
        ↓
    distinct()


Need first N?
        ↓
     limit()


Need skip N?
        ↓
      skip()


Need debug?
        ↓
      peek()


Need final result?
        ↓
   Terminal operation
```

---

# 🏆 Final Mental Model

```text
SOURCE
  ↓
STREAM
  ↓
INTERMEDIATE OPERATIONS
  ↓
TERMINAL OPERATION
  ↓
RESULT
```

Remember:

```text
Collection = DATA

Stream = PROCESS

Intermediate = BUILD PIPELINE

Terminal = EXECUTE PIPELINE
```

And the most important rule:

```text
No Terminal Operation
        ↓
No Stream Execution
```