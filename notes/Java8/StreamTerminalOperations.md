# Stream Terminal Operations — Last-Minute Revision Cheat Sheet

## 1. What Are Terminal Operations?

Terminal operations are Stream operations that:

- Trigger stream execution
- Produce a final result or side effect
- End the Stream pipeline
- Cannot be followed by another Stream operation on the same stream

Basic pipeline:

```text
Source → Intermediate Operations → Terminal Operation
```

Example:

```java
List<Integer> result = numbers.stream()
        .filter(n -> n > 20)
        .collect(Collectors.toList());
```

Here:

```text
filter()  → Intermediate
collect() → Terminal
```

---

# 2. Terminal Operations — Quick Reference

| Operation | Purpose | Return Type |
|---|---|---|
| `forEach()` | Perform action | `void` |
| `collect()` | Gather results | Collection / Result |
| `count()` | Count elements | `long` |
| `reduce()` | Combine many → one | `T` / `Optional<T>` |
| `min()` | Find smallest | `Optional<T>` |
| `max()` | Find largest | `Optional<T>` |
| `findFirst()` | Find first element | `Optional<T>` |
| `findAny()` | Find any element | `Optional<T>` |
| `anyMatch()` | At least one matches | `boolean` |
| `allMatch()` | Every element matches | `boolean` |
| `noneMatch()` | No element matches | `boolean` |

---

# 3. `forEach()`

## Purpose

Perform an action for every element.

```java
numbers.stream()
        .forEach(System.out::println);
```

Functional interface:

```text
Consumer<T>
```

Return:

```text
void
```

## Mental Model

```text
forEach → DO something
```

## Common Pattern

```java
numbers.stream()
        .filter(n -> n > 20)
        .forEach(System.out::println);
```

Pipeline:

```text
filter → SELECT
forEach → ACTION
```

## Important

`forEach()` does not:

- Transform elements
- Return a collection
- Return a value

## `forEach()` vs `forEachOrdered()`

```java
stream.forEach(...)
```

May not preserve encounter order when using parallel streams.

```java
stream.forEachOrdered(...)
```

Preserves encounter order for ordered streams.

---

# 4. `collect()`

## Purpose

Gather Stream results into a collection or another result structure.

Java 8 / Java 11:

```java
List<Integer> result = numbers.stream()
        .filter(n -> n > 20)
        .collect(Collectors.toList());
```

Set:

```java
Set<Integer> result = numbers.stream()
        .collect(Collectors.toSet());
```

Functional concept:

```text
Stream → Result Container
```

## Mental Model

```text
collect → GATHER results
```

## Common Patterns

```java
filter → collect → List
```

```java
map → collect → List
```

```java
filter → map → collect → List
```

Example:

```java
List<String> result = names.stream()
        .filter(name -> name.startsWith("P"))
        .map(String::toUpperCase)
        .collect(Collectors.toList());
```

## Important

Java 16+ has:

```java
stream.toList()
```

But for Java 8/11 interviews, know:

```java
collect(Collectors.toList())
```

---

# 5. `count()`

## Purpose

Count elements in a Stream.

```java
long count = numbers.stream()
        .count();
```

With filtering:

```java
long count = numbers.stream()
        .filter(n -> n > 20)
        .count();
```

Return type:

```text
long
```

NOT:

```text
int
```

## Mental Model

```text
count → HOW MANY?
```

## Common Patterns

```java
stream.count()
```

```java
stream.filter(...).count()
```

```java
stream.distinct().count()
```

```java
stream.limit(n).count()
```

```java
stream.skip(n).count()
```

## `size()` vs `count()`

Collection:

```java
list.size();
```

Returns:

```text
int
```

Stream:

```java
list.stream().count();
```

Returns:

```text
long
```

## Interview Tip

If you only need the count, don't unnecessarily:

```java
collect(Collectors.toList()).size();
```

Prefer:

```java
count();
```

---

# 6. `reduce()`

## Purpose

Combine multiple Stream elements into one result.

```text
MANY → ONE
```

Example:

```java
int sum = numbers.stream()
        .reduce(0, Integer::sum);
```

## Mental Model

```text
reduce → COMBINE many values into ONE
```

## Sum

```java
int sum = numbers.stream()
        .reduce(0, Integer::sum);
```

Identity:

```text
0
```

## Product

```java
int product = numbers.stream()
        .reduce(1, (a, b) -> a * b);
```

Identity:

```text
1
```

## Without Identity

```java
Optional<Integer> result = numbers.stream()
        .reduce(Integer::sum);
```

Why `Optional`?

Because the Stream may be empty.

## Common Patterns

```text
sum        → reduce(0, ...)
product    → reduce(1, ...)
custom     → reduce(identity, accumulator)
```

## `reduce()` vs `collect()`

```text
reduce  → MANY → ONE
collect → MANY → RESULT CONTAINER
```

Example:

```java
int sum = numbers.stream()
        .reduce(0, Integer::sum);
```

vs

```java
List<Integer> result = numbers.stream()
        .collect(Collectors.toList());
```

## Important

Don't use `reduce()` for everything.

Prefer specialized operations when they express the intent better:

```java
count()
min()
max()
sum()
collect()
```

---

# 7. `min()`

## Purpose

Find the smallest element.

```java
Optional<Integer> minimum = numbers.stream()
        .min(Integer::compareTo);
```

## Mental Model

```text
min → SMALLEST
```

Returns:

```text
Optional<T>
```

## With Objects

```java
Optional<Product> cheapest = products.stream()
        .min(Comparator.comparing(Product::getPrice));
```

Pattern:

```text
Objects + minimum field
        ↓
min(Comparator.comparing(...))
```

## Why Optional?

The Stream might be empty.

```java
Optional<Integer> result = numbers.stream()
        .min(Integer::compareTo);
```

Safe handling:

```java
result.orElse(0);
```

---

# 8. `max()`

## Purpose

Find the largest element.

```java
Optional<Integer> maximum = numbers.stream()
        .max(Integer::compareTo);
```

## Mental Model

```text
max → LARGEST
```

## With Objects

```java
Optional<Product> expensive = products.stream()
        .max(Comparator.comparing(Product::getPrice));
```

Pattern:

```text
Objects + maximum field
        ↓
max(Comparator.comparing(...))
```

---

# 9. `findFirst()`

## Purpose

Return the first element of the Stream.

```java
Optional<Integer> result = numbers.stream()
        .findFirst();
```

With filtering:

```java
Optional<Integer> result = numbers.stream()
        .filter(n -> n > 30)
        .findFirst();
```

Example:

```java
List<Integer> numbers = List.of(10, 20, 30, 40, 50);

Optional<Integer> result = numbers.stream()
        .filter(n -> n > 25)
        .findFirst();
```

Result:

```text
30
```

## Mental Model

```text
findFirst → FIRST matching element
```

Return:

```text
Optional<T>
```

## Common Pattern

```text
filter → findFirst
```

Example:

```java
Optional<User> user = users.stream()
        .filter(u -> u.getId() == 101)
        .findFirst();
```

---

# 10. `findAny()`

## Purpose

Return any element from the Stream.

```java
Optional<Integer> result = numbers.stream()
        .findAny();
```

With filtering:

```java
Optional<Integer> result = numbers.stream()
        .filter(n -> n > 30)
        .findAny();
```

## Mental Model

```text
findAny → ANY matching element
```

Return:

```text
Optional<T>
```

## `findFirst()` vs `findAny()`

```text
findFirst → specifically need the first
findAny   → any matching element is acceptable
```

`findAny()` can be useful with parallel processing when a specific encounter order is not required.

---

# 11. `anyMatch()`

## Purpose

Check whether at least one element matches a condition.

```java
boolean result = numbers.stream()
        .anyMatch(n -> n > 40);
```

Example:

```java
List<Integer> numbers = List.of(10, 20, 30, 40, 50);

boolean result = numbers.stream()
        .anyMatch(n -> n > 40);
```

Result:

```text
true
```

## Mental Model

```text
anyMatch → Is there AT LEAST ONE?
```

Return:

```text
boolean
```

## Real-World Example

Check whether any product is out of stock:

```java
boolean outOfStock = products.stream()
        .anyMatch(Product::isOutOfStock);
```

---

# 12. `allMatch()`

## Purpose

Check whether every element matches a condition.

```java
boolean result = numbers.stream()
        .allMatch(n -> n > 0);
```

Example:

```text
10 > 0 → true
20 > 0 → true
30 > 0 → true
40 > 0 → true
50 > 0 → true
```

Result:

```text
true
```

## Mental Model

```text
allMatch → Does EVERY element satisfy the condition?
```

Return:

```text
boolean
```

## Real-World Example

Check whether all products are active:

```java
boolean allActive = products.stream()
        .allMatch(Product::isActive);
```

---

# 13. `noneMatch()`

## Purpose

Check whether no elements match a condition.

```java
boolean result = numbers.stream()
        .noneMatch(n -> n < 0);
```

## Mental Model

```text
noneMatch → Does ZERO elements satisfy the condition?
```

Return:

```text
boolean
```

## Real-World Example

Check whether no account is blocked:

```java
boolean noBlockedAccounts = accounts.stream()
        .noneMatch(Account::isBlocked);
```

---

# 14. Match Operations Comparison

| Operation | Meaning |
|---|---|
| `anyMatch()` | At least ONE matches |
| `allMatch()` | EVERY element matches |
| `noneMatch()` | ZERO elements match |

Mental model:

```text
anyMatch  → SOME?
allMatch  → ALL?
noneMatch → NONE?
```

---

# 15. `Optional` in Terminal Operations

These commonly return `Optional`:

```text
min()
max()
findFirst()
findAny()
reduce() without identity
```

Why?

Because there may be no result.

Example:

```java
Optional<Integer> result = numbers.stream()
        .filter(n -> n > 1000)
        .findFirst();
```

There might be no matching number.

## `orElse()`

```java
int value = result.orElse(0);
```

Meaning:

```text
If value exists → return value
Otherwise → return 0
```

## `orElseGet()`

```java
int value = result.orElseGet(() -> calculateDefault());
```

Useful when the fallback calculation should only happen when needed.

## `ifPresent()`

```java
result.ifPresent(System.out::println);
```

Perform an action only when a value exists.

---

# 16. Terminal vs Intermediate Operations

## Intermediate Operations

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

Characteristics:

```text
→ Return Stream
→ Lazy
→ Chainable
→ Don't trigger execution by themselves
```

## Terminal Operations

```text
forEach()
collect()
count()
reduce()
min()
max()
findFirst()
findAny()
anyMatch()
allMatch()
noneMatch()
```

Characteristics:

```text
→ Produce final result / side effect
→ Trigger execution
→ End the Stream pipeline
```

---

# 17. Complete Stream Pipeline

Example:

```java
List<String> result = users.stream()
        .filter(user -> user.startsWith("P"))
        .map(String::toUpperCase)
        .sorted()
        .collect(Collectors.toList());
```

Pipeline:

```text
Source
  ↓
filter()      → Intermediate
  ↓
map()         → Intermediate
  ↓
sorted()      → Intermediate
  ↓
collect()     → Terminal
  ↓
List<String>
```

---

# 18. Terminal Operation Decision Tree

```text
What do I need?
        |
        +-- Perform an action
        |       ↓
        |    forEach()
        |
        +-- Gather results
        |       ↓
        |    collect()
        |
        +-- Count elements
        |       ↓
        |    count()
        |
        +-- Combine MANY → ONE
        |       ↓
        |    reduce()
        |
        +-- Smallest
        |       ↓
        |    min()
        |
        +-- Largest
        |       ↓
        |    max()
        |
        +-- First matching element
        |       ↓
        |    findFirst()
        |
        +-- Any matching element
        |       ↓
        |    findAny()
        |
        +-- At least one matches?
        |       ↓
        |    anyMatch()
        |
        +-- Every element matches?
        |       ↓
        |    allMatch()
        |
        +-- No elements match?
                ↓
             noneMatch()
```

---

# 19. Common Interview Patterns

## Filter + Count

```java
long count = users.stream()
        .filter(User::isActive)
        .count();
```

```text
SELECT → COUNT
```

---

## Filter + Collect

```java
List<User> activeUsers = users.stream()
        .filter(User::isActive)
        .collect(Collectors.toList());
```

```text
SELECT → GATHER
```

---

## Map + Collect

```java
List<String> names = users.stream()
        .map(User::getName)
        .collect(Collectors.toList());
```

```text
TRANSFORM → GATHER
```

---

## Filter + FindFirst

```java
Optional<User> user = users.stream()
        .filter(u -> u.getId() == 101)
        .findFirst();
```

```text
SELECT → FIRST
```

---

## Filter + AnyMatch

```java
boolean exists = users.stream()
        .anyMatch(u -> u.getId() == 101);
```

```text
SELECT → DOES ONE EXIST?
```

---

## Filter + Min

```java
Optional<Product> cheapest = products.stream()
        .filter(Product::isAvailable)
        .min(Comparator.comparing(Product::getPrice));
```

```text
SELECT → SMALLEST
```

---

## Filter + Max

```java
Optional<Product> expensive = products.stream()
        .filter(Product::isAvailable)
        .max(Comparator.comparing(Product::getPrice));
```

```text
SELECT → LARGEST
```

---

# 20. Short-Circuiting Terminal Operations

Some terminal operations can stop processing once the answer is known.

These include:

```text
findFirst()
findAny()
anyMatch()
allMatch()
noneMatch()
```

Examples:

```java
numbers.stream()
        .anyMatch(n -> n > 100);
```

Once a matching element is found, the Stream does not need to continue checking every element.

Similarly:

```java
numbers.stream()
        .findFirst();
```

Only the required result needs to be determined.

## Mental Model

```text
Short-circuit → Stop when the answer is known
```

---

# 21. Stream Cannot Be Reused

After a terminal operation, the Stream is closed.

Wrong:

```java
Stream<Integer> stream = numbers.stream();

stream.count();

stream.forEach(System.out::println);
```

This results in:

```text
IllegalStateException
```

Correct:

```java
numbers.stream().count();

numbers.stream().forEach(System.out::println);
```

Mental model:

```text
ONE STREAM → ONE TERMINAL OPERATION
```

---

# 22. Terminal Operations and Laziness

Intermediate operations are lazy:

```java
numbers.stream()
        .filter(n -> n > 20)
        .map(n -> n * 2);
```

Nothing meaningful is executed until a terminal operation appears.

```java
numbers.stream()
        .filter(n -> n > 20)
        .map(n -> n * 2)
        .collect(Collectors.toList());
```

Now execution is triggered.

Mental model:

```text
Intermediate → BUILD pipeline
Terminal     → START execution
```

---

# 23. `reduce()` vs `sum()`

For numeric primitive streams:

```java
int sum = numbers.stream()
        .mapToInt(Integer::intValue)
        .sum();
```

This is often clearer than:

```java
int sum = numbers.stream()
        .reduce(0, Integer::sum);
```

Use:

```text
sum()   → simple numeric sum
reduce() → custom reduction logic
```

---

# 24. `min/max` vs Sorting

If you only need the smallest:

Prefer:

```java
products.stream()
        .min(Comparator.comparing(Product::getPrice));
```

Instead of:

```java
products.stream()
        .sorted(Comparator.comparing(Product::getPrice))
        .findFirst();
```

Why?

```text
Need minimum → min()
Need maximum → max()
Need ordered results → sorted()
```

Don't sort unnecessarily.

---

# 25. Common Mistakes

### Mistake 1 — Forgetting `count()` returns `long`

```java
long count = stream.count();
```

Not:

```java
int count = stream.count();
```

---

### Mistake 2 — Calling `get()` blindly on Optional

Risky:

```java
result.get();
```

If empty:

```text
NoSuchElementException
```

Prefer:

```java
result.orElse(defaultValue);
```

or:

```java
result.ifPresent(...);
```

---

### Mistake 3 — Using `reduce()` for everything

Don't use:

```java
reduce()
```

when a clearer operation already exists:

```text
count()
min()
max()
collect()
sum()
```

---

### Mistake 4 — Assuming `findAny()` means first

```text
findAny() ≠ guaranteed first
```

If you specifically need the first:

```java
findFirst();
```

---

### Mistake 5 — Using `forEach()` when you need a result

Wrong mindset:

```java
List<Integer> result = new ArrayList<>();

numbers.stream()
        .filter(n -> n > 20)
        .forEach(result::add);
```

Prefer:

```java
List<Integer> result = numbers.stream()
        .filter(n -> n > 20)
        .collect(Collectors.toList());
```

---

### Mistake 6 — Reusing a Stream

```java
stream.count();
stream.collect(...);
```

Not allowed.

Create a new Stream.

---

# 26. Interview Questions

### Q1. What is a terminal operation?

An operation that triggers Stream execution and produces a final result or side effect.

---

### Q2. Give examples of terminal operations.

```text
forEach
collect
count
reduce
min
max
findFirst
findAny
anyMatch
allMatch
noneMatch
```

---

### Q3. What is the difference between `map()` and `collect()`?

```text
map()     → transforms elements
collect() → gathers final results
```

---

### Q4. What is the difference between `reduce()` and `collect()`?

```text
reduce  → MANY → ONE
collect → MANY → RESULT CONTAINER
```

---

### Q5. Why does `min()` return Optional?

Because the Stream can be empty and therefore may not have a minimum.

---

### Q6. Why does `count()` return `long`?

The Stream API defines `count()` to return `long`.

---

### Q7. Difference between `findFirst()` and `findAny()`?

```text
findFirst → first element according to encounter order
findAny   → any element
```

---

### Q8. Difference between `anyMatch()`, `allMatch()`, and `noneMatch()`?

```text
anyMatch  → at least one
allMatch  → every element
noneMatch → no elements
```

---

### Q9. Are terminal operations lazy?

No.

Terminal operations trigger evaluation of the lazy intermediate pipeline.

---

### Q10. Can a Stream be reused?

No.

A Stream can be consumed only once.

---

# 27. Terminal Operations — Pattern Recognition

```text
DO something
    → forEach()

GATHER results
    → collect()

HOW MANY?
    → count()

MANY → ONE
    → reduce()

SMALLEST?
    → min()

LARGEST?
    → max()

FIRST?
    → findFirst()

ANY?
    → findAny()

AT LEAST ONE?
    → anyMatch()

EVERY?
    → allMatch()

ZERO?
    → noneMatch()
```

---

# 28. Final 10-Second Cheat Sheet

```text
forEach()    → ACTION
collect()    → GATHER
count()      → HOW MANY?
reduce()     → MANY → ONE
min()        → SMALLEST
max()        → LARGEST
findFirst()  → FIRST
findAny()    → ANY
anyMatch()   → SOME?
allMatch()   → ALL?
noneMatch()  → NONE?
```

### Stream Pipeline Mental Model

```text
SOURCE
  ↓
filter()      → SELECT
  ↓
map()         → TRANSFORM
  ↓
flatMap()     → FLATTEN
  ↓
sorted()      → ORDER
  ↓
distinct()    → UNIQUE
  ↓
limit/skip    → CONTROL
  ↓
TERMINAL OPERATION
  ↓
FINAL RESULT
```

### Golden Rule

```text
Intermediate operations → build the pipeline

Terminal operation      → executes the pipeline
```

### Most Important Interview Pattern

```text
filter → map → collect
filter → count
filter → findFirst
filter → anyMatch
filter → min/max
filter → map → reduce
```

### Final Mental Model

```text
Stream

Intermediate:
SELECT → TRANSFORM → FLATTEN → ORDER → UNIQUE → CONTROL

Terminal:
ACTION → GATHER → COUNT → REDUCE → MIN/MAX → FIND → MATCH
```