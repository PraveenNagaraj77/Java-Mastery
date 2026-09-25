# ☕ Java Stream Intermediate Operations — Last-Minute Revision Cheat Sheet

> **Purpose:** Last-minute interview revision + quickly identify which Stream intermediate operation to use from a problem statement.

---

# 1. Stream Pattern Identification

## 🚨 The Most Important Rule

When you see a Stream problem, ask:

```text
1. Do I need to select elements?
2. Do I need to transform elements?
3. Do I have nested collections?
4. Do I need sorting?
5. Do I need unique values?
6. Do I need only the first N?
7. Do I need to skip the first N?
8. Do I need to inspect/debug the pipeline?
```

Then choose the operation.

---

# 2. Quick Decision Table

| Problem Requirement | Use |
|---|---|
| Keep elements matching a condition | `filter()` |
| Transform each element | `map()` |
| Extract a field from objects | `map()` |
| Flatten nested collections | `flatMap()` |
| Sort elements naturally | `sorted()` |
| Sort using custom logic | `sorted(Comparator)` |
| Remove duplicates | `distinct()` |
| Keep first N elements | `limit()` |
| Skip first N elements | `skip()` |
| Observe/debug elements in pipeline | `peek()` |

---

# 3. Stream Pipeline

A typical Stream pipeline:

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
        .filter(employee -> employee.getDepartment().equals("IT"))
        .map(Employee::getName)
        .sorted()
        .forEach(System.out::println);
```

Here:

```text
filter() → SELECT
map()    → TRANSFORM
sorted() → ORDER
forEach() → FINISH
```

---

# 4. What Are Intermediate Operations?

Intermediate operations:

```text
✓ Return another Stream
✓ Are lazy
✓ Can be chained
✓ Build the Stream pipeline
✓ Do not normally trigger execution
```

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

---

# 5. Laziness

Intermediate operations do not execute immediately.

Example:

```java
numbers.stream()
        .filter(number -> number > 20)
        .map(number -> number * 2);
```

Nothing happens yet.

A terminal operation is required:

```java
numbers.stream()
        .filter(number -> number > 20)
        .map(number -> number * 2)
        .forEach(System.out::println);
```

Think:

```text
Intermediate
    ↓
Build pipeline

Terminal
    ↓
Trigger execution
```

---

# 6. filter()

## One-liner

> **filter() = select elements that satisfy a condition.**

## Mental Model

```text
filter() = SELECT
```

It answers:

> Which elements should continue?

## Use When

```text
✓ Need to select elements
✓ Need to remove unwanted elements
✓ Need condition-based filtering
✓ Need active users
✓ Need IT employees
✓ Need numbers greater than X
```

## Pattern Recognition

If the problem says:

```text
"Find employees with salary > 50000"
"Get only IT employees"
"Keep even numbers"
"Find active users"
"Remove invalid values"
```

Think:

```java
filter()
```

## Syntax

```java
stream.filter(condition)
```

## Example

```java
numbers.stream()
        .filter(number -> number > 20)
        .forEach(System.out::println);
```

## Functional Interface

`filter()` uses:

```java
Predicate<T>
```

Conceptually:

```java
boolean test(T value)
```

Therefore:

```java
.filter(number -> number > 20)
```

means:

```text
Number
   ↓
Predicate
   ↓
true / false
```

Only `true` elements continue.

## Multiple Filters

```java
employees.stream()
        .filter(employee ->
                employee.getDepartment().equals("IT"))
        .filter(employee ->
                employee.getSalary() > 60000)
        .forEach(System.out::println);
```

Think:

```text
Employees
    ↓
Department = IT
    ↓
Salary > 60000
    ↓
Result
```

## filter() with null values

```java
names.stream()
        .filter(Objects::nonNull)
        .forEach(System.out::println);
```

## Common Mistake

`filter()` does not transform elements.

Wrong:

```java
.filter(number -> number * 2)
```

Correct:

```java
.map(number -> number * 2)
```

---

# 7. map()

## One-liner

> **map() = transform each element into another value.**

## Mental Model

```text
map() = TRANSFORM
```

It answers:

> What should each element become?

## Use When

```text
✓ Extract a field
✓ Convert an object
✓ Transform values
✓ Employee → Name
✓ Employee → Salary
✓ String → Length
✓ Number → Number
✓ DTO → Entity
```

## Pattern Recognition

If the problem says:

```text
"Get only names"
"Extract salary"
"Convert price"
"Find length of each string"
"Transform each object"
```

Think:

```java
map()
```

## Syntax

```java
stream.map(transformation)
```

## Example

```java
numbers.stream()
        .map(number -> number * 2)
        .forEach(System.out::println);
```

Transformation:

```text
10 → 20
20 → 40
30 → 60
```

## Functional Interface

`map()` uses:

```java
Function<T, R>
```

Conceptually:

```java
R apply(T value)
```

Think:

```text
Input
  ↓
Transformation
  ↓
Output
```

## Employee → Name

```java
employees.stream()
        .map(Employee::getName)
        .forEach(System.out::println);
```

Transformation:

```text
Employee → String
```

## Employee → Salary

```java
employees.stream()
        .map(Employee::getSalary)
        .forEach(System.out::println);
```

Transformation:

```text
Employee → Integer
```

## String → Length

```java
names.stream()
        .map(String::length)
        .forEach(System.out::println);
```

Transformation:

```text
String → Integer
```

## filter() + map()

Very common:

```java
employees.stream()
        .filter(employee ->
                employee.getDepartment().equals("IT"))
        .map(Employee::getName)
        .forEach(System.out::println);
```

Think:

```text
filter() → Which employees?
map()    → What do I want from them?
```

## Critical Type Tracking

Initially:

```text
Stream<Employee>
```

After:

```java
.map(Employee::getName)
```

the Stream becomes:

```text
Stream<String>
```

Therefore:

```java
.map(Employee::getSalary)
```

cannot be applied after that because the elements are now `String`.

---

# 8. flatMap()

## One-liner

> **flatMap() = transform nested data and flatten it into one Stream.**

## Mental Model

```text
flatMap()
=
map()
+
flatten
```

## Use When

```text
✓ List<List<T>>
✓ Employee → List<Skill>
✓ Order → List<OrderItem>
✓ Department → List<Employee>
✓ Nested collections
```

## Pattern Recognition

If the problem says:

```text
"Flatten a list of lists"
"Get all skills from employees"
"Get all order items"
"Flatten nested collections"
```

Think:

```java
flatMap()
```

## Example Data

```java
List<List<String>> skills =
        Arrays.asList(
                Arrays.asList("Java", "Spring Boot"),
                Arrays.asList("React", "JavaScript"),
                Arrays.asList("Docker", "Git")
        );
```

Using `map()`:

```java
skills.stream()
        .map(List::stream);
```

Conceptually:

```text
Stream<Stream<String>>
```

Using `flatMap()`:

```java
skills.stream()
        .flatMap(List::stream);
```

Conceptually:

```text
Stream<String>
```

## Example

```java
skills.stream()
        .flatMap(List::stream)
        .forEach(System.out::println);
```

Output:

```text
Java
Spring Boot
React
JavaScript
Docker
Git
```

## Employee Skills

```java
employees.stream()
        .flatMap(employee ->
                employee.getSkills().stream())
        .forEach(System.out::println);
```

Transformation:

```text
Employee
   ↓
List<String>
   ↓
String
```

## flatMap() + distinct()

```java
employees.stream()
        .flatMap(employee ->
                employee.getSkills().stream())
        .distinct()
        .forEach(System.out::println);
```

Useful for:

```text
All unique skills across employees
```

## flatMap() + filter()

```java
employees.stream()
        .flatMap(employee ->
                employee.getSkills().stream())
        .filter(skill -> skill.contains("Java"))
        .forEach(System.out::println);
```

## map() vs flatMap()

```text
map()
→ one input → one output

flatMap()
→ one input → zero, one, or many outputs
→ flatten
```

Example:

```java
.map(employee -> employee.getSkills())
```

Result:

```text
Stream<List<String>>
```

Whereas:

```java
.flatMap(employee ->
        employee.getSkills().stream())
```

Result:

```text
Stream<String>
```

---

# 9. sorted()

## One-liner

> **sorted() = arrange Stream elements in a particular order.**

## Mental Model

```text
sorted() = ORDER
```

## Use When

```text
✓ Need ascending order
✓ Need descending order
✓ Need alphabetical order
✓ Need highest salary
✓ Need lowest salary
✓ Need ranking
✓ Need Top N
```

## Pattern Recognition

If the problem says:

```text
"Sort"
"Highest"
"Lowest"
"Top 3"
"Ascending"
"Descending"
"Alphabetically"
```

Think:

```java
sorted()
```

## Natural Ordering

```java
numbers.stream()
        .sorted()
        .forEach(System.out::println);
```

## Descending Order

```java
numbers.stream()
        .sorted(Comparator.reverseOrder())
        .forEach(System.out::println);
```

## Sort Strings

```java
names.stream()
        .sorted()
        .forEach(System.out::println);
```

## Sort by String Length

```java
names.stream()
        .sorted(Comparator.comparing(String::length))
        .forEach(System.out::println);
```

Important:

This:

```java
names.stream()
        .map(String::length)
        .sorted();
```

sorts the lengths:

```text
4
5
6
7
```

It does NOT sort the names by length while keeping the names.

Correct:

```java
names.stream()
        .sorted(Comparator.comparing(String::length))
        .forEach(System.out::println);
```

## Sort Employees by Salary

```java
employees.stream()
        .sorted(Comparator.comparing(Employee::getSalary))
        .forEach(employee ->
                System.out.println(
                        employee.getName()
                        + " - "
                        + employee.getSalary()
                ));
```

## Descending Salary

```java
employees.stream()
        .sorted(
                Comparator.comparing(Employee::getSalary)
                        .reversed()
        )
        .forEach(System.out::println);
```

## Multiple Fields

```java
employees.stream()
        .sorted(
                Comparator.comparing(Employee::getDepartment)
                        .thenComparing(Employee::getSalary)
        )
        .forEach(System.out::println);
```

## Important Difference

### Sort values

```java
employees.stream()
        .map(Employee::getSalary)
        .sorted();
```

Pipeline:

```text
Employee
   ↓
Salary
   ↓
Sorted Salary
```

### Sort employees

```java
employees.stream()
        .sorted(
                Comparator.comparing(Employee::getSalary)
        );
```

Pipeline:

```text
Employee
   ↓
Sorted Employee
```

The second approach preserves the Employee objects.

## Optimization Rule

Prefer:

```text
filter()
   ↓
sorted()
```

over:

```text
sorted()
   ↓
filter()
```

when filtering can reduce the data being sorted.

Example:

```java
employees.stream()
        .filter(employee ->
                employee.getDepartment().equals("IT"))
        .sorted(
                Comparator.comparing(Employee::getSalary)
        )
        .forEach(System.out::println);
```

---

# 10. distinct()

## One-liner

> **distinct() = remove duplicate elements from a Stream.**

## Mental Model

```text
distinct() = UNIQUE
```

## Use When

```text
✓ Remove duplicates
✓ Find unique values
✓ Unique names
✓ Unique skills
✓ Unique IDs
```

## Pattern Recognition

If the problem says:

```text
"Remove duplicates"
"Unique values"
"Distinct names"
"Unique skills"
```

Think:

```java
distinct()
```

## Example

```java
List<Integer> numbers =
        Arrays.asList(
                10, 20, 10, 30, 20, 40
        );

numbers.stream()
        .distinct()
        .forEach(System.out::println);
```

Result:

```text
10
20
30
40
```

## filter() + distinct()

```java
employees.stream()
        .filter(employee ->
                employee.getDepartment().equals("IT"))
        .map(Employee::getName)
        .distinct()
        .forEach(System.out::println);
```

## distinct() + sorted()

```java
numbers.stream()
        .distinct()
        .sorted()
        .forEach(System.out::println);
```

## Important: equals() and hashCode()

For objects, `distinct()` depends on equality semantics.

Example:

```java
new Employee("Praveen", "IT", 50000)
new Employee("Praveen", "IT", 50000)
```

These are two different object instances.

If `Employee` does not correctly implement:

```java
equals()
hashCode()
```

`distinct()` may not treat them as duplicates.

For common types such as:

```text
String
Integer
Long
```

equality behavior is already implemented.

## Easy Unique-Name Pattern

```java
employees.stream()
        .map(Employee::getName)
        .distinct()
        .forEach(System.out::println);
```

This is often easier than applying `distinct()` directly to Employee objects.

---

# 11. limit()

## One-liner

> **limit() = keep only the first N elements.**

## Mental Model

```text
limit() = TAKE
```

## Use When

```text
✓ First N elements
✓ Top N
✓ Limit results
✓ First page-like subset
✓ Reduce processing
```

## Pattern Recognition

If the problem says:

```text
"First 3"
"Top 5"
"Return only 10"
"Maximum N results"
```

Think:

```java
limit()
```

## Syntax

```java
stream.limit(n)
```

## Example

```java
numbers.stream()
        .limit(3)
        .forEach(System.out::println);
```

## Top 3 Highest Paid Employees

```java
employees.stream()
        .sorted(
                Comparator.comparing(Employee::getSalary)
                        .reversed()
        )
        .limit(3)
        .forEach(System.out::println);
```

Think:

```text
Employees
   ↓
Sort highest salary first
   ↓
Take first 3
```

## filter() + limit()

```java
employees.stream()
        .filter(employee ->
                employee.getDepartment().equals("IT"))
        .limit(3)
        .forEach(System.out::println);
```

Meaning:

```text
Keep IT employees
   ↓
Take first 3 IT employees
```

## distinct() + limit()

```java
employees.stream()
        .map(Employee::getName)
        .distinct()
        .limit(3)
        .forEach(System.out::println);
```

Meaning:

```text
Get names
   ↓
Remove duplicates
   ↓
Take first 3 unique names
```

## Important: Order Matters

These are different.

### First 3 then remove duplicates

```java
stream.limit(3)
      .distinct();
```

### Remove duplicates then take 3

```java
stream.distinct()
      .limit(3);
```

The result can be different.

---

# 12. skip()

## One-liner

> **skip() = discard the first N elements.**

## Mental Model

```text
skip() = DISCARD FIRST N
```

## Use When

```text
✓ Skip first N
✓ Pagination
✓ Find elements after top N
✓ Ignore initial records
✓ Get a specific range
```

## Pattern Recognition

If the problem says:

```text
"Skip first 3"
"Get records after first 10"
"Page 2"
"Find 4th and 5th"
"Ignore top 2"
```

Think:

```java
skip()
```

## Syntax

```java
stream.skip(n)
```

## Example

```java
numbers.stream()
        .skip(2)
        .forEach(System.out::println);
```

Input:

```text
10 20 30 40 50
```

Output:

```text
30
40
50
```

## skip() + limit()

Very important pattern:

```java
numbers.stream()
        .skip(2)
        .limit(3)
        .forEach(System.out::println);
```

Meaning:

```text
Skip first 2
   ↓
Take next 3
```

---

# 13. Pagination Pattern

One of the most important real-world uses of:

```text
skip()
+
limit()
```

Formula:

```java
int skip =
        (pageNumber - 1) * pageSize;
```

Example:

```java
int pageNumber = 2;
int pageSize = 3;

int skip =
        (pageNumber - 1) * pageSize;

employees.stream()
        .skip(skip)
        .limit(pageSize)
        .forEach(System.out::println);
```

For:

```text
pageNumber = 2
pageSize = 3
```

we get:

```text
skip = (2 - 1) × 3
     = 3
```

So:

```text
Page 1 → records 1-3
Page 2 → records 4-6
Page 3 → records 7-9
```

---

# 14. sorted() + skip() + limit()

Very useful for ranking problems.

Problem:

> Find the 4th and 5th highest-paid employees.

Solution:

```java
employees.stream()
        .sorted(
                Comparator.comparing(Employee::getSalary)
                        .reversed()
        )
        .skip(3)
        .limit(2)
        .forEach(System.out::println);
```

Why?

```text
Sort descending
      ↓
Skip top 3
      ↓
Take next 2
```

Therefore:

```text
4th + 5th highest
```

---

# 15. peek()

## One-liner

> **peek() = observe elements as they flow through the Stream pipeline.**

## Mental Model

```text
peek() = OBSERVE / DEBUG
```

## Use When

```text
✓ Debug Stream pipeline
✓ Inspect intermediate values
✓ Understand lazy execution
✓ Trace transformations
✓ Check what passed a filter
```

## Pattern Recognition

If the problem says:

```text
"Debug the Stream"
"See values before filter"
"Inspect values after map"
"Trace the pipeline"
```

Think:

```java
peek()
```

## Syntax

```java
stream.peek(action)
```

## Functional Interface

`peek()` uses:

```java
Consumer<T>
```

Conceptually:

```java
void accept(T value)
```

Therefore:

```java
.peek(System.out::println)
```

works.

---

# 16. Basic peek()

```java
numbers.stream()
        .peek(number ->
                System.out.println(
                        "Peek: " + number
                ))
        .forEach(System.out::println);
```

Important:

`peek()` does not transform the element.

---

# 17. peek() + filter()

```java
numbers.stream()

        .peek(number ->
                System.out.println(
                        "Before filter: " + number
                ))

        .filter(number -> number > 20)

        .peek(number ->
                System.out.println(
                        "After filter: " + number
                ))

        .forEach(System.out::println);
```

The first `peek()` sees:

```text
All elements
```

The second `peek()` sees:

```text
Only elements that passed filter()
```

---

# 18. peek() + map()

```java
numbers.stream()

        .peek(number ->
                System.out.println(
                        "Before map: " + number
                ))

        .map(number -> number * 10)

        .peek(number ->
                System.out.println(
                        "After map: " + number
                ))

        .forEach(System.out::println);
```

Example:

```text
Before map: 10
After map: 100
```

This clearly shows:

```text
peek() → observe
map()  → transform
```

---

# 19. peek() and Laziness

This:

```java
numbers.stream()
        .peek(System.out::println);
```

prints:

```text
Nothing
```

because there is no terminal operation.

Add:

```java
.forEach(System.out::println);
```

Now the pipeline executes.

Important:

```text
peek()
→ intermediate
→ lazy
→ requires terminal operation
```

---

# 20. peek() vs forEach()

| `peek()` | `forEach()` |
|---|---|
| Intermediate | Terminal |
| Lazy | Triggers execution |
| Returns Stream | Returns `void` |
| Observe/debug | Final action |
| Can continue pipeline | Ends pipeline |

Mental model:

```text
peek()
→ "Let me look at this."

forEach()
→ "I'm done. Perform this action."
```

---

# 21. Don't Use peek() for Business Logic

Avoid using:

```java
.peek(order ->
        order.setStatus("PROCESSED"))
```

for important business logic.

`peek()` is mainly intended for:

```text
Debugging
Tracing
Observation
Learning Stream behavior
```

For intentional final side effects, use an appropriate terminal operation or explicit business logic.

---

# 22. Parallel Stream Warning

Be careful with:

```java
parallelStream()
```

For example:

```java
employees.parallelStream()
        .peek(employee ->
                System.out.println(employee.getName()))
        .forEach(...);
```

Execution order may not be what you expect.

Therefore:

```text
Do not depend on peek()
for business logic or execution order.
```

---

# 23. filter() vs map()

One of the most important interview comparisons.

```text
filter()
   ↓
SELECT

map()
   ↓
TRANSFORM
```

Example:

```java
employees.stream()
        .filter(employee ->
                employee.getSalary() > 60000)
```

Means:

```text
Which employees?
```

Whereas:

```java
employees.stream()
        .map(Employee::getName)
```

Means:

```text
What should each employee become?
```

---

# 24. map() vs flatMap()

```text
map()
→ One element becomes one element

flatMap()
→ One element can become multiple elements
→ Flatten result
```

Example:

```java
.map(employee -> employee.getSkills())
```

Result:

```text
Stream<List<String>>
```

Example:

```java
.flatMap(employee ->
        employee.getSkills().stream())
```

Result:

```text
Stream<String>
```

---

# 25. limit() vs skip()

```text
limit(N)
→ Keep first N

skip(N)
→ Discard first N
```

Example:

```text
Input:

10 20 30 40 50
```

### limit(2)

```text
10 20
```

### skip(2)

```text
30 40 50
```

---

# 26. distinct() vs filter()

```text
filter()
→ Remove elements based on condition

distinct()
→ Remove duplicates
```

Example:

```java
numbers.stream()
        .filter(number -> number > 20)
```

Means:

```text
Keep numbers > 20
```

Whereas:

```java
numbers.stream()
        .distinct()
```

Means:

```text
Remove duplicate numbers
```

---

# 27. sorted() vs Comparator

`sorted()` is the Stream operation.

`Comparator` defines custom ordering.

Example:

```java
employees.stream()
        .sorted(
                Comparator.comparing(Employee::getSalary)
        );
```

Think:

```text
sorted()
→ "I want to sort."

Comparator
→ "Here is how to sort."
```

---

# 28. Operation Order Matters

Stream operations are not interchangeable.

Example:

```java
numbers.stream()
        .distinct()
        .limit(3)
```

is different from:

```java
numbers.stream()
        .limit(3)
        .distinct()
```

Similarly:

```java
employees.stream()
        .filter(...)
        .sorted(...)
```

can be more efficient than:

```java
employees.stream()
        .sorted(...)
        .filter(...)
```

because fewer elements may need to be sorted.

---

# 29. Common Real-World Pipelines

## Example 1 — IT Employee Names

```java
employees.stream()
        .filter(employee ->
                employee.getDepartment().equals("IT"))
        .map(Employee::getName)
        .forEach(System.out::println);
```

Pattern:

```text
filter
→ map
```

---

## Example 2 — Top 3 Salaries

```java
employees.stream()
        .sorted(
                Comparator.comparing(Employee::getSalary)
                        .reversed()
        )
        .limit(3)
        .forEach(System.out::println);
```

Pattern:

```text
sorted
→ limit
```

---

## Example 3 — Unique IT Employee Names

```java
employees.stream()
        .filter(employee ->
                employee.getDepartment().equals("IT"))
        .map(Employee::getName)
        .distinct()
        .forEach(System.out::println);
```

Pattern:

```text
filter
→ map
→ distinct
```

---

## Example 4 — Second Page

```java
int pageNumber = 2;
int pageSize = 5;

int skip =
        (pageNumber - 1) * pageSize;

employees.stream()
        .skip(skip)
        .limit(pageSize)
        .forEach(System.out::println);
```

Pattern:

```text
skip
→ limit
```

---

## Example 5 — 4th and 5th Highest Salary

```java
employees.stream()
        .sorted(
                Comparator.comparing(Employee::getSalary)
                        .reversed()
        )
        .skip(3)
        .limit(2)
        .forEach(System.out::println);
```

Pattern:

```text
sorted
→ skip
→ limit
```

---

## Example 6 — Unique Skills

```java
employees.stream()
        .flatMap(employee ->
                employee.getSkills().stream())
        .distinct()
        .forEach(System.out::println);
```

Pattern:

```text
flatMap
→ distinct
```

---

## Example 7 — Debug Pipeline

```java
employees.stream()

        .peek(employee ->
                System.out.println(
                        "Initial: " + employee.getName()
                ))

        .filter(employee ->
                employee.getDepartment().equals("IT"))

        .peek(employee ->
                System.out.println(
                        "After IT filter: "
                                + employee.getName()
                ))

        .filter(employee ->
                employee.getSalary() > 60000)

        .peek(employee ->
                System.out.println(
                        "After salary filter: "
                                + employee.getName()
                ))

        .sorted(
                Comparator.comparing(Employee::getSalary)
                        .reversed()
        )

        .peek(employee ->
                System.out.println(
                        "After sorting: "
                                + employee.getName()
                ))

        .forEach(employee ->
                System.out.println(
                        "Final: " + employee.getName()
                ));
```

---

# 30. Intermediate Operations — Quick Mental Models

Memorize these:

```text
filter()
→ SELECT

map()
→ TRANSFORM

flatMap()
→ FLATTEN

sorted()
→ ORDER

distinct()
→ UNIQUE

limit()
→ TAKE

skip()
→ DISCARD

peek()
→ OBSERVE
```

---

# 31. The Stream Pipeline Cheat Code

When reading a Stream problem:

```text
Need to choose WHICH elements?
        ↓
    filter()


Need to change WHAT elements become?
        ↓
      map()


Have nested collections?
        ↓
    flatMap()


Need ORDER?
        ↓
     sorted()


Need UNIQUE?
        ↓
    distinct()


Need FIRST N?
        ↓
     limit()


Need AFTER N?
        ↓
      skip()


Need DEBUG / OBSERVE?
        ↓
      peek()
```

---

# 32. Common Interview Questions

## Q1. What are intermediate operations?

> Intermediate operations are Stream operations that return another Stream, allowing operations to be chained. They are generally lazy and execute when a terminal operation is invoked.

---

## Q2. Are intermediate operations lazy?

Yes.

Examples:

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

They don't execute until a terminal operation triggers the pipeline.

---

## Q3. Does an intermediate operation modify the original collection?

Generally, Stream operations process elements without modifying the source collection.

However, avoid introducing side effects inside Stream operations.

---

## Q4. What is the difference between filter() and map()?

```text
filter()
→ selects elements

map()
→ transforms elements
```

---

## Q5. What is the difference between map() and flatMap()?

```text
map()
→ one input → one output

flatMap()
→ one input → multiple/zero/one outputs
→ flatten
```

---

## Q6. What is the difference between limit() and skip()?

```text
limit(N)
→ keep first N

skip(N)
→ discard first N
```

---

## Q7. What is distinct() based on?

`distinct()` relies on equality semantics, primarily:

```java
equals()
hashCode()
```

for object uniqueness.

---

## Q8. Why can sorted() fail for custom objects?

Because:

```java
.sorted()
```

requires the elements to have a natural ordering, typically through:

```java
Comparable
```

For custom sorting, use:

```java
Comparator
```

Example:

```java
.sorted(
        Comparator.comparing(Employee::getSalary)
)
```

---

## Q9. What functional interface does filter() use?

```text
Predicate<T>
```

---

## Q10. What functional interface does map() use?

```text
Function<T, R>
```

---

## Q11. What functional interface does peek() use?

```text
Consumer<T>
```

---

## Q12. Is peek() a terminal operation?

No.

```text
peek()
→ intermediate

forEach()
→ terminal
```

---

## Q13. Why doesn't this print anything?

```java
numbers.stream()
        .peek(System.out::println);
```

Because:

```text
peek()
→ lazy
→ no terminal operation
→ pipeline never executes
```

---

## Q14. Can intermediate operations be chained?

Yes.

Example:

```java
employees.stream()
        .filter(...)
        .map(...)
        .distinct()
        .sorted(...)
        .limit(5)
        .forEach(...);
```

---

# 33. Common Mistakes

## Mistake 1 — Forgetting Terminal Operation

```java
numbers.stream()
        .filter(number -> number > 20);
```

No execution.

Need:

```java
numbers.stream()
        .filter(number -> number > 20)
        .forEach(System.out::println);
```

---

## Mistake 2 — Using filter() for Transformation

Wrong:

```java
.filter(number -> number * 2)
```

Correct:

```java
.map(number -> number * 2)
```

---

## Mistake 3 — Using map() Instead of flatMap()

For:

```text
List<List<String>>
```

This:

```java
.map(List::stream)
```

produces nested Streams.

Use:

```java
.flatMap(List::stream)
```

to flatten.

---

## Mistake 4 — Sorting After Extracting the Wrong Value

If you need:

```text
Employees sorted by salary
```

Don't immediately do:

```java
.map(Employee::getSalary)
.sorted()
```

unless you only need salary values.

To keep Employee objects:

```java
.sorted(
        Comparator.comparing(Employee::getSalary)
)
```

---

## Mistake 5 — Using sorted() Without Comparable

For custom objects:

```java
employees.stream()
        .sorted();
```

may fail if `Employee` does not implement `Comparable`.

Use:

```java
employees.stream()
        .sorted(
                Comparator.comparing(Employee::getSalary)
        );
```

---

## Mistake 6 — Using peek() for Business Logic

Avoid:

```java
.peek(order ->
        order.setStatus("PROCESSED"))
```

Prefer explicit business logic or an appropriate terminal operation.

---

## Mistake 7 — Wrong Order of Operations

These can produce different results:

```java
distinct()
→ limit()
```

vs:

```java
limit()
→ distinct()
```

Always think about what the Stream contains at each stage.

---

# 34. Operation Order Examples

## Example A

```java
numbers.stream()
        .filter(number -> number > 20)
        .map(number -> number * 2)
```

Pipeline:

```text
Numbers
   ↓
Select > 20
   ↓
Multiply by 2
```

---

## Example B

```java
numbers.stream()
        .map(number -> number * 2)
        .filter(number -> number > 20)
```

Pipeline:

```text
Numbers
   ↓
Multiply by 2
   ↓
Select > 20
```

These are NOT necessarily equivalent.

---

## Example C

```java
employees.stream()
        .filter(employee ->
                employee.getDepartment().equals("IT"))
        .sorted(
                Comparator.comparing(Employee::getSalary)
        )
```

Better when only IT employees need sorting.

---

# 35. Intermediate Operation Classification

Some intermediate operations are:

```text
Stateless
```

and some are:

```text
Stateful
```

## Mostly Stateless

```text
filter()
map()
flatMap()
peek()
```

They generally process each element independently.

## Stateful

```text
sorted()
distinct()
limit()
skip()
```

They may need information about other elements or the position within the stream.

---

# 36. Stateful Operation Notes

## sorted()

May need to see the elements before producing sorted output.

```text
Need ordering information
```

## distinct()

Needs to remember previously encountered elements.

```text
Need duplicate tracking
```

## limit()

Needs to know how many elements have already passed.

```text
Need count
```

## skip()

Needs to know how many elements have already been skipped.

```text
Need count
```

---

# 37. Complexity / Performance Cheat Sheet

Stream operations do not all have the same performance characteristics.

| Operation | General Behavior |
|---|---|
| `filter()` | Usually O(n) |
| `map()` | Usually O(n) |
| `flatMap()` | Depends on total flattened elements |
| `sorted()` | Usually O(n log n) |
| `distinct()` | Usually O(n) average with hashing |
| `limit()` | Can stop after N elements |
| `skip()` | Must pass/skip the required number of elements |
| `peek()` | Usually O(n), depending on terminal operation |

These are general algorithmic expectations, not guarantees for every Stream/source/parallel configuration.

---

# 38. Short-Circuiting

Some intermediate operations can allow a pipeline to stop processing early when combined with suitable terminal operations.

Especially important:

```text
limit()
```

Example:

```java
numbers.stream()
        .limit(3)
        .forEach(System.out::println);
```

Only the required number of elements need to continue.

This can be useful with large or potentially unbounded streams.

---

# 39. Real-World Stream Patterns

## Pattern 1 — Filter + Map

```text
Find IT employee names
```

```java
employees.stream()
        .filter(employee ->
                employee.getDepartment().equals("IT"))
        .map(Employee::getName);
```

Think:

```text
SELECT → TRANSFORM
```

---

## Pattern 2 — Filter + Sort

```text
Sort IT employees by salary
```

```java
employees.stream()
        .filter(employee ->
                employee.getDepartment().equals("IT"))
        .sorted(
                Comparator.comparing(Employee::getSalary)
        );
```

Think:

```text
SELECT → ORDER
```

---

## Pattern 3 — Sort + Limit

```text
Top 3 highest-paid employees
```

```java
employees.stream()
        .sorted(
                Comparator.comparing(Employee::getSalary)
                        .reversed()
        )
        .limit(3);
```

Think:

```text
ORDER → TAKE
```

---

## Pattern 4 — Filter + Map + Distinct

```text
Unique IT employee names
```

```java
employees.stream()
        .filter(employee ->
                employee.getDepartment().equals("IT"))
        .map(Employee::getName)
        .distinct();
```

Think:

```text
SELECT → TRANSFORM → UNIQUE
```

---

## Pattern 5 — Sort + Skip + Limit

```text
4th and 5th highest-paid employees
```

```java
employees.stream()
        .sorted(
                Comparator.comparing(Employee::getSalary)
                        .reversed()
        )
        .skip(3)
        .limit(2);
```

Think:

```text
ORDER → SKIP → TAKE
```

---

## Pattern 6 — FlatMap + Distinct

```text
All unique employee skills
```

```java
employees.stream()
        .flatMap(employee ->
                employee.getSkills().stream())
        .distinct();
```

Think:

```text
FLATTEN → UNIQUE
```

---

## Pattern 7 — Pagination

```text
Get page 3 with 10 records per page
```

```java
int pageNumber = 3;
int pageSize = 10;

int skip =
        (pageNumber - 1) * pageSize;

employees.stream()
        .skip(skip)
        .limit(pageSize);
```

Think:

```text
SKIP → TAKE
```

---

## Pattern 8 — Debugging

```text
Understand what reaches each stage
```

```java
employees.stream()
        .peek(...)
        .filter(...)
        .peek(...)
        .map(...)
        .peek(...)
        .forEach(...);
```

Think:

```text
OBSERVE → PROCESS → OBSERVE
```

---

# 40. Ultimate Pattern Recognition Cheat Sheet

```text
"Which elements?"
        ↓
    filter()


"What should each element become?"
        ↓
      map()


"Nested collection?"
        ↓
    flatMap()


"Sort?"
        ↓
    sorted()


"Remove duplicates?"
        ↓
    distinct()


"First N?"
        ↓
    limit()


"After first N?"
        ↓
     skip()


"Debug / inspect pipeline?"
        ↓
      peek()
```

---

# 41. The Big 8 — Memorize This

```text
filter()
→ SELECT

map()
→ TRANSFORM

flatMap()
→ FLATTEN

sorted()
→ ORDER

distinct()
→ UNIQUE

limit()
→ TAKE

skip()
→ DISCARD

peek()
→ OBSERVE
```

---

# 42. Stream Intermediate Operations vs Terminal Operations

## Intermediate

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
→ Returns Stream
→ Lazy
→ Chainable
→ Does not finish pipeline
```

## Terminal

Examples:

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

Characteristics:

```text
→ Produces final result / action
→ Triggers execution
→ Ends Stream pipeline
```

---

# 43. Final Interview Cheat Code

When you see:

```text
SELECT
```

Think:

```text
filter()
```

When you see:

```text
TRANSFORM
```

Think:

```text
map()
```

When you see:

```text
NESTED → FLAT
```

Think:

```text
flatMap()
```

When you see:

```text
ORDER
```

Think:

```text
sorted()
```

When you see:

```text
UNIQUE
```

Think:

```text
distinct()
```

When you see:

```text
FIRST N
```

Think:

```text
limit()
```

When you see:

```text
AFTER N
```

Think:

```text
skip()
```

When you see:

```text
DEBUG / OBSERVE
```

Think:

```text
peek()
```

---

# 44. Final Mental Model

Don't memorize every Stream method individually.

Remember these 8 words:

```text
SELECT
TRANSFORM
FLATTEN
ORDER
UNIQUE
TAKE
SKIP
OBSERVE
```

Map them directly:

```text
SELECT
   ↓
filter()


TRANSFORM
   ↓
map()


FLATTEN
   ↓
flatMap()


ORDER
   ↓
sorted()


UNIQUE
   ↓
distinct()


TAKE
   ↓
limit()


SKIP
   ↓
skip()


OBSERVE
   ↓
peek()
```

---

# 🏆 Final Stream Intermediate Operations Cheat Code

```text
Need to select?
    → filter()

Need to transform?
    → map()

Need to flatten nested data?
    → flatMap()

Need to sort?
    → sorted()

Need unique values?
    → distinct()

Need first N?
    → limit()

Need records after N?
    → skip()

Need to inspect/debug the pipeline?
    → peek()
```

And remember:

```text
Intermediate operations
        ↓
     LAZY
        ↓
Need a terminal operation
        ↓
   Pipeline executes
```

The most important distinction:

```text
filter()   → Which elements?
map()      → What do they become?
flatMap()  → How do I flatten them?
sorted()   → In what order?
distinct() → Which duplicates should disappear?
limit()    → How many should I keep?
skip()     → How many should I ignore?
peek()     → What is flowing through?
```

> **Don't memorize Stream operations. Recognize what the problem is asking you to do, then choose the operation.**