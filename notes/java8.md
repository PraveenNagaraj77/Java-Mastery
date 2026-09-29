
# ☕ Java 8+ — Last-Minute Revision Cheat Sheet

> **Purpose:** Last-minute interview revision + quickly identify which Java 8 feature/pattern to use from a problem statement.

---

# 1. Java 8 Pattern Identification

## 🚨 The Most Important Rule

When you see a problem, don't immediately think about syntax.

Ask:

```text
1. Do I need to filter elements?
2. Do I need to transform elements?
3. Do I need to flatten nested data?
4. Do I need to find one result?
5. Do I need aggregation?
6. Do I need grouping?
7. Do I need frequency counting?
8. Do I need optional handling?
9. Do I need date/time manipulation?
10. Do I need custom functional behavior?
```

Then choose the Java 8 feature.

---

# 2. Quick Decision Table

| Problem Requirement | Think |
| --- | --- |
| Keep only matching elements | `filter()` |
| Transform each element | `map()` |
| Flatten nested collections | `flatMap()` |
| Remove duplicates | `distinct()` |
| Sort elements | `sorted()` |
| Take first N | `limit()` |
| Skip first N | `skip()` |
| Perform side-effect/action | `forEach()` |
| Count elements | `count()` |
| Find largest | `max()` |
| Find smallest | `min()` |
| Find first result | `findFirst()` |
| Find any result | `findAny()` |
| Check if any matches | `anyMatch()` |
| Check if all match | `allMatch()` |
| Check if none match | `noneMatch()` |
| Combine values | `reduce()` |
| Convert Stream to collection | `collect()` |
| Group by property | `groupingBy()` |
| Split into true/false groups | `partitioningBy()` |
| Count occurrences | `groupingBy() + counting()` |
| Join strings | `joining()` |
| Calculate sum | `mapToInt() + sum()` |
| Calculate average | `mapToInt() + average()` |
| Optional value may be absent | `Optional` |
| Custom behavior as parameter | Lambda |
| One abstract method contract | Functional Interface |
| Reuse existing method | Method Reference |
| Add implementation to interface | `default` |
| Utility method inside interface | `static` |
| Date only | `LocalDate` |
| Time only | `LocalTime` |
| Date + time | `LocalDateTime` |
| Date + time + timezone | `ZonedDateTime` |
| Exact global timestamp | `Instant` |
| Format/parse date-time | `DateTimeFormatter` |
| Date-based difference | `Period` |
| Time-based difference | `Duration` |

---

# 3. Lambda Expressions

## One-liner

> **Lambda = concise way to represent behavior/function as a value.**

### Syntax

```java
(parameters) -> expression
```

or:

```java
(parameters) -> {
    // statements
}
```

### Example

```java
(int a, int b) -> a + b
```

Simplified:

```java
(a, b) -> a + b
```

---

## Pattern Recognition

If the problem says:

```text
"Pass behavior"
"Pass logic"
"Perform custom operation"
"Use a function"
```

Think:

```text
Lambda
```

---

## Example

```java
List<Integer> numbers = Arrays.asList(10, 20, 30);

numbers.forEach(number -> System.out.println(number));
```

---

## Important Rules

```text
Lambda works with a Functional Interface.

Lambda does NOT create a standalone function.

Lambda represents behavior.
```

---

## Common Mistake

Wrong:

```java
(a, b) -> {
    a + b;
}
```

Correct:

```java
(a, b) -> a + b
```

or:

```java
(a, b) -> {
    return a + b;
}
```

---

# 4. Functional Interface

## One-liner

> **Functional Interface = interface with exactly one abstract method.**

### Example

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}
```

Use:

```java
Calculator addition = (a, b) -> a + b;
```

---

## Pattern Recognition

If the problem says:

```text
"One abstract method"
"Use lambda"
"Represent one behavior"
```

Think:

```text
Functional Interface
```

---

## Built-in Functional Interfaces

### Predicate

```java
Predicate<Integer>
```

Returns:

```text
boolean
```

Pattern:

```text
Check / condition
```

Example:

```java
Predicate<Integer> isEven =
        number -> number % 2 == 0;
```

---

### Function

```java
Function<T, R>
```

Returns:

```text
R
```

Pattern:

```text
Transform T → R
```

Example:

```java
Function<String, Integer> length =
        text -> text.length();
```

---

### Consumer

```java
Consumer<T>
```

Returns:

```text
void
```

Pattern:

```text
Perform action
```

Example:

```java
Consumer<String> print =
        text -> System.out.println(text);
```

---

### Supplier

```java
Supplier<T>
```

Takes:

```text
nothing
```

Returns:

```text
T
```

Pattern:

```text
Generate / supply value
```

Example:

```java
Supplier<Double> random =
        () -> Math.random();
```

---

## Memory Trick

```text
Predicate
→ Test

Function
→ Transform

Consumer
→ Consume

Supplier
→ Supply
```

---

# 5. Method References

## One-liner

> **Method Reference = shorter syntax for a lambda that directly calls an existing method.**

### Lambda

```java
name -> name.toUpperCase()
```

### Method Reference

```java
String::toUpperCase
```

---

## Four Forms

```text
1. Static method
ClassName::staticMethod

2. Instance method of a particular object
object::method

3. Instance method of an arbitrary object
ClassName::instanceMethod

4. Constructor
ClassName::new
```

---

## Example

```java
names.forEach(System.out::println);
```

Instead of:

```java
names.forEach(name -> System.out.println(name));
```

---

## Pattern Recognition

If:

```text
Lambda only calls an existing method
```

Think:

```text
Method Reference
```

---

# 6. Stream API

## One-liner

> **Stream = pipeline for processing data from a source using declarative operations.**

### Mental Model

```text
Source
  ↓
Intermediate Operations
  ↓
Terminal Operation
```

Example:

```java
numbers.stream()
       .filter(n -> n > 10)
       .map(n -> n * 2)
       .collect(Collectors.toList());
```

---

# 7. Stream Pipeline

```text
List
 ↓
stream()
 ↓
filter()
 ↓
map()
 ↓
sorted()
 ↓
collect()
```

### Important Rule

A Stream is:

```text
NOT a data structure
```

It does not store data.

It processes data from a source.

---

# 8. Intermediate vs Terminal Operations

## Intermediate

Returns another Stream.

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
Lazy
Chainable
```

---

## Terminal

Produces final result/side effect.

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

---

## Interview One-Liner

> Intermediate operations build the pipeline; terminal operations trigger stream execution.

---

# 9. filter()

## One-liner

> **filter() selects elements matching a condition.**

### Pattern Recognition

```text
Keep
Select
Only
Greater than
Less than
Matching
```

Think:

```java
filter()
```

### Example

```java
List<Integer> result = numbers.stream()
        .filter(n -> n > 10)
        .collect(Collectors.toList());
```

---

# 10. map()

## One-liner

> **map() transforms each element into another value.**

### Pattern Recognition

```text
Convert
Transform
Uppercase
Extract field
Calculate value
```

Think:

```java
map()
```

### Example

```java
List<String> names = employees.stream()
        .map(Employee::getName)
        .collect(Collectors.toList());
```

---

# 11. flatMap()

## One-liner

> **flatMap() transforms and flattens nested structures into one Stream.**

### Pattern Recognition

If you see:

```text
List<List<T>>
List<Set<T>>
Nested collections
Flatten
Combine nested lists
```

Think:

```java
flatMap()
```

### Example

```java
List<List<String>> teams = Arrays.asList(
        Arrays.asList("Java", "Spring"),
        Arrays.asList("React", "Node")
);

List<String> result = teams.stream()
        .flatMap(List::stream)
        .collect(Collectors.toList());
```

Output:

```text
Java Spring React Node
```

---

# 12. map() vs flatMap()

```text
map()
→ One input → one output

flatMap()
→ One input → multiple outputs
→ Flatten
```

### Example

```text
map:
[1, 2, 3]
 ↓
[2, 4, 6]


flatMap:
[[1,2], [3,4]]
 ↓
[1,2,3,4]
```

---

# 13. distinct()

## One-liner

> **distinct() removes duplicate elements.**

### Pattern Recognition

```text
Unique
Remove duplicates
Distinct values
```

Think:

```java
distinct()
```

### Example

```java
numbers.stream()
       .distinct()
       .collect(Collectors.toList());
```

---

# 14. sorted()

## One-liner

> **sorted() orders stream elements.**

### Natural order

```java
numbers.stream()
       .sorted()
```

### Descending

```java
numbers.stream()
       .sorted(Comparator.reverseOrder())
```

### Object sorting

```java
employees.stream()
         .sorted(Comparator.comparing(Employee::getSalary))
```

---

## Pattern Recognition

```text
Sort
Ascending
Descending
Highest first
Lowest first
```

Think:

```java
sorted()
```

---

# 15. limit()

## One-liner

> **limit(n) keeps only the first n elements.**

### Pattern Recognition

```text
Top N
First N
Only N results
```

Think:

```java
limit(n)
```

Example:

```java
numbers.stream()
       .limit(5)
```

---

# 16. skip()

## One-liner

> **skip(n) ignores the first n elements.**

### Pattern Recognition

```text
Skip first N
Remove first N
Nth element after sorting
```

Think:

```java
skip(n)
```

### Example

Third largest:

```java
numbers.stream()
       .distinct()
       .sorted(Comparator.reverseOrder())
       .skip(2)
       .findFirst();
```

Pattern:

```text
distinct
→ sorted descending
→ skip(n - 1)
→ findFirst
```

---

# 17. peek()

## One-liner

> **peek() performs an action while elements pass through the pipeline, mainly for debugging.**

Example:

```java
numbers.stream()
       .filter(n -> n > 10)
       .peek(System.out::println)
       .collect(Collectors.toList());
```

### Important

`peek()` is an intermediate operation and is lazy.

Do not use it as the primary way to perform business side effects.

---

# 18. forEach()

## One-liner

> **forEach() performs an action for every element and is a terminal operation.**

Example:

```java
numbers.stream()
       .forEach(System.out::println);
```

### Pattern Recognition

```text
Print
Send
Log
Perform action
```

Think:

```java
forEach()
```

---

# 19. count()

## One-liner

> **count() returns the number of elements.**

### Pattern Recognition

```text
How many?
Number of elements
Count matching elements
```

Example:

```java
long count = numbers.stream()
        .filter(n -> n > 50)
        .count();
```

---

# 20. max() and min()

## One-liner

> **max() finds the largest element; min() finds the smallest.**

### Pattern Recognition

```text
Highest
Largest
Maximum
Lowest
Smallest
Minimum
```

Think:

```text
max()
min()
```

### Example

```java
Optional<Integer> max =
        numbers.stream().max(Integer::compareTo);
```

---

## Important Efficiency Rule

If the problem only asks for maximum:

```text
Use max()
```

Don't:

```text
sort()
→ findFirst()
```

Why?

```text
max()   → O(n)

sorting → O(n log n)
```

---

# 21. findFirst()

## One-liner

> **findFirst() returns the first matching/result element as Optional.**

### Pattern Recognition

```text
First
First matching
First element satisfying condition
```

Think:

```java
filter(...)
.findFirst()
```

Example:

```java
Optional<Integer> result =
        numbers.stream()
               .filter(n -> n > 50)
               .findFirst();
```

---

# 22. findAny()

## One-liner

> **findAny() returns any matching element.**

Useful when:

```text
Any matching element is sufficient.
```

Especially relevant with parallel streams.

### Pattern Recognition

```text
Any
Any matching
Doesn't matter which
```

Think:

```java
findAny()
```

---

# 23. findFirst() vs findAny()

```text
findFirst()
→ First element according to encounter order

findAny()
→ Any matching element
→ Useful when exact first element isn't required
```

---

# 24. anyMatch()

## One-liner

> **anyMatch() checks whether at least one element satisfies a condition.**

### Pattern Recognition

```text
Does any?
Is there at least one?
Exists?
```

Example:

```java
boolean result = numbers.stream()
        .anyMatch(n -> n % 2 == 0);
```

---

# 25. allMatch()

## One-liner

> **allMatch() checks whether every element satisfies a condition.**

### Pattern Recognition

```text
All?
Every?
Are all valid?
```

Example:

```java
boolean result = numbers.stream()
        .allMatch(n -> n > 0);
```

---

# 26. noneMatch()

## One-liner

> **noneMatch() checks whether no elements satisfy a condition.**

### Pattern Recognition

```text
None?
No element?
Doesn't contain?
```

Example:

```java
boolean result = numbers.stream()
        .noneMatch(n -> n < 0);
```

---

# 27. Match Operation Decision

```text
"Does ANY match?"
        ↓
anyMatch()


"Do ALL match?"
        ↓
allMatch()


"Does NONE match?"
        ↓
noneMatch()
```

### Efficiency

These operations can short-circuit.

```text
anyMatch()
→ stops after first true

allMatch()
→ stops after first false

noneMatch()
→ stops after first true
```

---

# 28. reduce()

## One-liner

> **reduce() combines multiple elements into one result.**

### Pattern Recognition

```text
Combine
Accumulate
Product
Custom aggregation
One result from many values
```

Think:

```java
reduce()
```

### Sum

```java
int sum = numbers.stream()
        .reduce(0, Integer::sum);
```

### Product

```java
int product = numbers.stream()
        .reduce(1, (a, b) -> a * b);
```

---

# 29. reduce() Mental Model

For:

```text
1 2 3 4
```

Sum:

```text
(((0 + 1) + 2) + 3) + 4
```

Result:

```text
10
```

---

# 30. reduce() vs collect()

```text
reduce()
→ Combine elements into one value


collect()
→ Accumulate elements into a mutable result container
```

Example:

```text
reduce
→ sum
→ product
→ maximum/custom aggregation


collect
→ List
→ Set
→ Map
→ grouping
```

---

# 31. collect()

## One-liner

> **collect() gathers stream results into a collection or another mutable result structure.**

### Pattern Recognition

```text
Convert Stream to List
Convert Stream to Set
Create Map
Group
Partition
Join
Aggregate with Collector
```

Think:

```java
collect()
```

---

# 32. Collectors.toList()

```java
List<String> names = employees.stream()
        .map(Employee::getName)
        .collect(Collectors.toList());
```

Pattern:

```text
Need List
```

---

# 33. Collectors.toSet()

```java
Set<String> departments = employees.stream()
        .map(Employee::getDepartment)
        .collect(Collectors.toSet());
```

Pattern:

```text
Need unique result
```

---

# 34. Collectors.toMap()

## One-liner

> **toMap() converts stream elements into key-value pairs.**

Example:

```java
Map<Integer, String> employeeMap =
        employees.stream()
                .collect(Collectors.toMap(
                        Employee::getId,
                        Employee::getName
                ));
```

---

## Duplicate Key Problem

If duplicate keys are possible:

```java
Collectors.toMap(
        Employee::getDepartment,
        Employee::getName,
        (existing, replacement) -> existing
)
```

Important:

```text
toMap()
→ duplicate keys cause exception
→ use merge function when duplicates are possible
```

---

# 35. Collectors.joining()

## One-liner

> **joining() combines strings into one String.**

Example:

```java
String result = names.stream()
        .collect(Collectors.joining(", "));
```

Output:

```text
Java, Spring, React
```

---

# 36. groupingBy()

## One-liner

> **groupingBy() groups elements based on a classification key.**

### Pattern Recognition

Whenever you see:

```text
Group by
For each department
Per category
Per city
Per status
Per type
```

Think:

```java
groupingBy()
```

### Example

```java
Map<String, List<Employee>> result =
        employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment
                ));
```

---

# 37. groupingBy() + counting()

## Pattern

If the problem says:

```text
"How many employees per department?"
"Frequency of each element?"
"Count occurrences?"
```

Think:

```text
groupingBy()
+
counting()
```

Example:

```java
Map<String, Long> count =
        employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.counting()
                ));
```

---

# 38. groupingBy() + summingInt()

Problem:

> Total salary per department.

Think:

```text
groupingBy
+
summingInt
```

Example:

```java
Map<String, Integer> result =
        employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.summingInt(Employee::getSalary)
                ));
```

---

# 39. groupingBy() + averagingInt()

Problem:

> Average salary per department.

Think:

```text
groupingBy
+
averagingInt
```

Example:

```java
Map<String, Double> result =
        employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingInt(Employee::getSalary)
                ));
```

---

# 40. groupingBy() + maxBy()

Problem:

> Highest-paid employee in each department.

Think:

```text
groupingBy
+
maxBy
```

Example:

```java
Map<String, Optional<Employee>> result =
        employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.maxBy(
                                Comparator.comparing(Employee::getSalary)
                        )
                ));
```

### Important

`maxBy()` returns:

```text
One maximum element
```

If ties must all be returned:

```text
group
→ find maximum salary
→ filter all employees having that salary
```

---

# 41. partitioningBy()

## One-liner

> **partitioningBy() splits elements into exactly two groups: true and false.**

### Pattern Recognition

```text
Split into two groups
Pass / Fail
Adult / Minor
Even / Odd
Above / Below condition
```

Think:

```java
partitioningBy()
```

Example:

```java
Map<Boolean, List<Integer>> result =
        numbers.stream()
                .collect(Collectors.partitioningBy(
                        n -> n > 50
                ));
```

Result:

```text
true  → numbers > 50
false → numbers <= 50
```

---

# 42. groupingBy() vs partitioningBy()

```text
groupingBy()
→ Multiple possible groups


partitioningBy()
→ Exactly two groups
→ true / false
```

Example:

```text
department
→ groupingBy()


salary > 50000
→ partitioningBy()
```

---

# 43. summarizingInt()

## One-liner

> **summarizingInt() calculates count, sum, min, average and max together.**

Example:

```java
IntSummaryStatistics stats =
        numbers.stream()
                .collect(Collectors.summarizingInt(
                        Integer::intValue
                ));
```

Available:

```java
stats.getCount();
stats.getSum();
stats.getMin();
stats.getAverage();
stats.getMax();
```

### Pattern Recognition

If the problem asks for several statistics together:

```text
count + sum + min + max + average
```

Think:

```text
summarizingInt()
```

---

# 44. averagingInt()

Problem:

> Find average salary.

Think:

```java
Collectors.averagingInt(Employee::getSalary)
```

Returns:

```text
Double
```

---

# 45. Java 8 Stream Pattern Dictionary

```text
Keep/select
→ filter()


Transform
→ map()


Flatten
→ flatMap()


Remove duplicates
→ distinct()


Sort
→ sorted()


First N
→ limit()


Skip N
→ skip()


Perform action
→ forEach()


Count
→ count()


Largest
→ max()


Smallest
→ min()


First
→ findFirst()


Any
→ findAny()


Any exists
→ anyMatch()


All satisfy
→ allMatch()


None satisfy
→ noneMatch()


Combine
→ reduce()


List
→ collect(toList())


Set
→ collect(toSet())


Map
→ collect(toMap())


Group
→ groupingBy()


Frequency
→ groupingBy() + counting()


Two groups
→ partitioningBy()


Join strings
→ joining()


Sum
→ mapToInt() + sum()


Average
→ mapToInt() + average()
```

---

# 46. The SELECT → TRANSFORM → AGGREGATE Model

This is one of the most important Java 8 interview mental models.

## SELECT

Question:

> Which elements do I want?

Think:

```java
filter()
```

---

## TRANSFORM

Question:

> What should each element become?

Think:

```java
map()
```

---

## AGGREGATE

Question:

> What final result do I need from all elements?

Think:

```text
count()
sum()
average()
min()
max()
reduce()
collect()
```

---

## Example

Problem:

> Find the total salary of IT employees.

Think:

```text
SELECT
→ filter IT

TRANSFORM
→ salary

AGGREGATE
→ sum
```

Code:

```java
int totalSalary = employees.stream()
        .filter(e -> e.getDepartment().equals("IT"))
        .mapToInt(Employee::getSalary)
        .sum();
```

---

# 47. Java 8 Efficiency Rules

## 🚨 Don't Sort When You Only Need Max

Bad:

```java
numbers.stream()
       .sorted(Comparator.reverseOrder())
       .findFirst();
```

Better:

```java
numbers.stream()
       .max(Integer::compareTo);
```

Complexity:

```text
sort → O(n log n)
max  → O(n)
```

---

## Don't Collect When You Only Need a Boolean

Instead of:

```text
filter
→ collect
→ check size
```

Use:

```java
anyMatch()
```

---

## Don't Count When You Only Need Existence

Instead of:

```text
filter
→ count
→ count > 0
```

Use:

```java
anyMatch()
```

---

## Don't Sort to Find Nth Result Without Thinking

For:

```text
3rd largest
```

A simple Stream solution:

```text
distinct
→ sorted descending
→ skip(2)
→ findFirst
```

Complexity:

```text
O(n log n)
```

For very large datasets, a heap-based DSA solution may be more appropriate.

---

# 48. Short-Circuit Operations

Some operations can stop early.

```text
findFirst()
findAny()
anyMatch()
allMatch()
noneMatch()
limit()
```

Example:

```java
numbers.stream()
       .anyMatch(n -> n == 100);
```

If `100` is found early, processing can stop.

---

# 49. Stream Laziness

Intermediate operations do not execute immediately.

Example:

```java
numbers.stream()
       .filter(n -> {
           System.out.println(n);
           return n > 10;
       });
```

Nothing happens yet.

A terminal operation is required:

```java
.collect(Collectors.toList());
```

Then the pipeline executes.

---

# 50. Stream Reuse

A Stream can be consumed only once.

Wrong:

```java
Stream<Integer> stream = numbers.stream();

stream.count();
stream.forEach(System.out::println);
```

The second operation fails because the Stream has already been consumed.

Correct:

```java
numbers.stream().count();

numbers.stream().forEach(System.out::println);
```

---

# 51. Optional

## One-liner

> **Optional represents a value that may or may not be present.**

### Pattern Recognition

If a method can legitimately return:

```text
value OR no value
```

Think:

```java
Optional<T>
```

---

# 52. Creating Optional

### of()

```java
Optional<String> value =
        Optional.of("Java");
```

Important:

```text
of(null) → NullPointerException
```

---

### ofNullable()

```java
Optional<String> value =
        Optional.ofNullable(name);
```

```text
null → Optional.empty()
value → Optional[value]
```

---

### empty()

```java
Optional<String> value =
        Optional.empty();
```

---

# 53. Optional Methods

```text
isPresent()
→ Is value present?


ifPresent()
→ Execute action if present


get()
→ Get value
→ Dangerous if empty


orElse()
→ Default value


orElseGet()
→ Lazily generate default


orElseThrow()
→ Throw exception if absent


map()
→ Transform contained value


flatMap()
→ Transform into Optional without nesting


filter()
→ Keep value if condition matches
```

---

# 54. orElse() vs orElseGet()

```text
orElse()
→ Default expression is evaluated immediately


orElseGet()
→ Supplier runs only when Optional is empty
```

Example:

```java
optional.orElse(createDefault());
```

versus:

```java
optional.orElseGet(() -> createDefault());
```

### Interview Rule

> Use `orElseGet()` when the fallback operation is expensive or has side effects.

---

# 55. map() vs flatMap() with Optional

```text
map()
→ T → R


flatMap()
→ T → Optional<R>
```

If the mapping function already returns Optional:

```text
Use flatMap()
```

to avoid:

```text
Optional<Optional<T>>
```

---

# 56. Optional + Stream Pattern

Many Stream operations return Optional:

```text
max()
min()
findFirst()
findAny()
reduce() without identity
```

Example:

```java
Optional<Integer> result =
        numbers.stream()
               .filter(n -> n > 50)
               .findFirst();
```

Safely use:

```java
result.ifPresent(System.out::println);
```

---

# 57. Optional Best Practice

Use Optional mainly when:

```text
A result may legitimately be absent.
```

Especially useful for return values:

```java
Optional<User> findById(Long id)
```

Avoid blindly wrapping every variable in Optional.

---

# 58. Default Methods in Interfaces

## One-liner

> **default method = interface method with an implementation.**

Example:

```java
interface PaymentService {

    void pay();

    default void logTransaction() {
        System.out.println("Transaction logged");
    }
}
```

---

## Why?

Before Java 8, adding a new abstract method to an interface could break existing implementations.

Default methods allow interfaces to evolve while providing a default implementation.

---

# 59. Default Method Pattern

```text
Interface
    ↓
default method
    ↓
Implementation inherits it
    ↓
Can override if required
```

---

# 60. Multiple Default Method Conflict

If two interfaces provide the same default method:

```java
interface A {
    default void show() {
        System.out.println("A");
    }
}

interface B {
    default void show() {
        System.out.println("B");
    }
}
```

Then:

```java
class Demo implements A, B {
}
```

causes a conflict.

Resolve:

```java
@Override
public void show() {
    A.super.show();
}
```

or:

```java
B.super.show();
```

---

# 61. Static Methods in Interfaces

## One-liner

> **Interface static method belongs to the interface itself.**

Example:

```java
interface PaymentUtil {

    static boolean isValidAmount(double amount) {
        return amount > 0;
    }
}
```

Call:

```java
PaymentUtil.isValidAmount(500);
```

Important:

```text
Static interface methods are not inherited.
They cannot be overridden.
Call them using InterfaceName.method().
```

---

# 62. Default vs Static Interface Methods

```text
default
→ Has implementation
→ Inherited by implementation class
→ Can be overridden
→ Called through object/reference


static
→ Belongs to interface
→ Not inherited
→ Cannot be overridden
→ Called using InterfaceName
```

---

# 63. Date & Time API

Java 8 introduced the modern:

```java
java.time
```

API.

Main classes:

```text
LocalDate
LocalTime
LocalDateTime
ZonedDateTime
Instant
DateTimeFormatter
Period
Duration
```

---

# 64. LocalDate

## One-liner

> **LocalDate = date without time or timezone.**

Example:

```java
LocalDate today = LocalDate.now();

LocalDate deliveryDate =
        today.plusDays(7);
```

### Pattern Recognition

```text
Birthday
Due date
Holiday
Calendar date
Delivery date
```

Think:

```java
LocalDate
```

---

# 65. LocalTime

## One-liner

> **LocalTime = time without date or timezone.**

Example:

```java
LocalTime now = LocalTime.now();

LocalTime openingTime =
        LocalTime.of(9, 0);
```

### Pattern Recognition

```text
Opening time
Closing time
Meeting time
Business hours
```

Think:

```java
LocalTime
```

---

# 66. LocalDateTime

## One-liner

> **LocalDateTime = date + time without timezone.**

Example:

```java
LocalDateTime orderTime =
        LocalDateTime.now();
```

### Pattern Recognition

```text
Order timestamp
Appointment date + time
Local application event
```

Think:

```java
LocalDateTime
```

---

# 67. ZonedDateTime

## One-liner

> **ZonedDateTime = date + time + timezone.**

Example:

```java
ZoneId india =
        ZoneId.of("Asia/Kolkata");

ZonedDateTime now =
        ZonedDateTime.now(india);
```

### Pattern Recognition

```text
Different countries
Global meetings
International users
Timezone conversion
```

Think:

```java
ZonedDateTime
```

---

# 68. withZoneSameInstant() vs withZoneSameLocal()

## withZoneSameInstant()

```text
Same actual moment
Different local clock time
```

Use for:

```text
Timezone conversion
```

Example:

```java
meetingTime.withZoneSameInstant(
        ZoneId.of("America/New_York")
);
```

---

## withZoneSameLocal()

```text
Same local clock time
Different actual moment
```

### Interview Rule

```text
Same instant
→ withZoneSameInstant()


Same local time
→ withZoneSameLocal()
```

---

# 69. Instant

## One-liner

> **Instant = exact point on the global UTC timeline.**

Example:

```java
Instant now = Instant.now();
```

### Pattern Recognition

```text
Global timestamp
Audit timestamp
Database timestamp
Event ordering
Exact moment
```

Think:

```java
Instant
```

---

# 70. Instant vs LocalDateTime

```text
LocalDateTime
→ Human/local date + time
→ No timezone
→ Not a unique global moment


Instant
→ Exact global moment
→ UTC timeline
→ Good for timestamps
```

### Backend Pattern

```text
Store/handle universal event time
→ Instant / UTC

Display to user
→ Convert to user's timezone
```

---

# 71. DateTimeFormatter

## One-liner

> **DateTimeFormatter = format date/time to String and parse String to date/time.**

### Formatting

```java
DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("dd-MM-yyyy");

String result =
        LocalDate.now().format(formatter);
```

### Parsing

```java
LocalDate date =
        LocalDate.parse(
                "29-09-2026",
                formatter
        );
```

---

# 72. Important DateTime Patterns

```text
dd-MM-yyyy
29-09-2026


yyyy-MM-dd
2026-09-29


yyyy-MM-dd HH:mm:ss
2026-09-29 14:30:45


dd/MM/yyyy HH:mm
29/09/2026 14:30
```

---

# 73. DateTimeFormatter Common Mistakes

### MM vs mm

```text
MM → Month

mm → Minute
```

### HH vs hh

```text
HH → 24-hour clock

hh → 12-hour clock
```

If using:

```text
hh
```

you generally need:

```text
a
```

for AM/PM.

### yyyy vs YYYY

Prefer:

```text
yyyy
```

for normal calendar year formatting.

---

# 74. Period

## One-liner

> **Period = date-based amount measured in years, months and days.**

Example:

```java
Period period =
        Period.ofMonths(3);
```

### Pattern Recognition

```text
Age
Subscription duration
Calendar date difference
Years/months/days
```

Think:

```java
Period
```

---

# 75. Duration

## One-liner

> **Duration = time-based amount measured in seconds/nanos and commonly represented as hours/minutes.**

Example:

```java
Duration duration =
        Duration.ofHours(5);
```

### Pattern Recognition

```text
API execution time
Elapsed time
Timer
Hours/minutes/seconds
```

Think:

```java
Duration
```

---

# 76. Period vs Duration

```text
Period
→ Date-based
→ Years / Months / Days


Duration
→ Time-based
→ Seconds / Nanos
→ Hours / Minutes representation
```

### Easy Memory Trick

```text
Period
→ Calendar


Duration
→ Clock
```

---

# 77. Java 8 Date/Time Decision Tree

```text
Need only date?
    ↓
LocalDate


Need only time?
    ↓
LocalTime


Need date + time?
    ↓
LocalDateTime


Need timezone?
    ↓
ZonedDateTime


Need exact global timestamp?
    ↓
Instant


Need String formatting/parsing?
    ↓
DateTimeFormatter


Need date-based difference?
    ↓
Period


Need time-based difference?
    ↓
Duration
```

---

# 78. Java 8 Interview Problem Patterns

## Problem 1 — First Matching Element

> Find first number greater than 10.

Think:

```text
filter
→ findFirst
```

---

## Problem 2 — Largest Matching Element

> Find largest even number.

Think:

```text
filter
→ max
```

Do NOT:

```text
filter
→ sorted
→ findFirst
```

unless sorting is actually required.

---

## Problem 3 — Second Largest

> Find second-largest number.

Think:

```text
distinct
→ sorted descending
→ skip(1)
→ findFirst
```

If "second-largest distinct":

```text
distinct
→ sorted descending
→ skip(1)
→ findFirst
```

---

## Problem 4 — Frequency

> Count occurrences.

Think:

```text
groupingBy
→ counting
```

Example:

```java
Map<String, Long> frequency =
        names.stream()
             .collect(Collectors.groupingBy(
                     name -> name,
                     Collectors.counting()
             ));
```

---

## Problem 5 — Remove Duplicates

Think:

```text
distinct()
```

---

## Problem 6 — Flatten

Think:

```text
flatMap()
```

---

## Problem 7 — Any Matching

Think:

```text
anyMatch()
```

---

## Problem 8 — All Matching

Think:

```text
allMatch()
```

---

## Problem 9 — None Matching

Think:

```text
noneMatch()
```

---

## Problem 10 — Group By

Think:

```text
groupingBy()
```

---

## Problem 11 — Group + Count

Think:

```text
groupingBy()
+
counting()
```

---

## Problem 12 — Group + Sum

Think:

```text
groupingBy()
+
summingInt()
```

---

## Problem 13 — Group + Average

Think:

```text
groupingBy()
+
averagingInt()
```

---

## Problem 14 — Group + Maximum

Think:

```text
groupingBy()
+
maxBy()
```

If ties matter:

```text
group
→ find maximum
→ filter all tied elements
```

---

# 79. Employee Stream Pattern Cheat Sheet

Given:

```java
List<Employee> employees;
```

### Employees in IT

```java
employees.stream()
        .filter(e -> e.getDepartment().equals("IT"))
```

### Employee names

```java
employees.stream()
        .map(Employee::getName)
```

### Highest salary

```java
employees.stream()
        .max(Comparator.comparing(Employee::getSalary))
```

### Average salary

```java
employees.stream()
        .mapToInt(Employee::getSalary)
        .average()
```

### Total salary

```java
employees.stream()
        .mapToInt(Employee::getSalary)
        .sum()
```

### Group by department

```java
employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment
        ));
```

### Count per department

```java
employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.counting()
        ));
```

### Total salary per department

```java
employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.summingInt(
                        Employee::getSalary
                )
        ));
```

---

# 80. Java 8 Pattern Recognition — The Big Picture

When a problem arrives:

```text
                    PROBLEM
                       │
                       ▼
              What is being asked?
                       │
       ┌───────────────┼────────────────┐
       ▼               ▼                ▼
    SELECT          TRANSFORM        AGGREGATE
       │               │                │
       ▼               ▼                ▼
    filter()         map()          max/min
                                      count
                                      sum
                                      average
                                      reduce
                                      collect
```

Then ask:

```text
Is data nested?
→ flatMap()


Need unique?
→ distinct()


Need sorted?
→ sorted()


Need first N?
→ limit()


Need skip?
→ skip()


Need grouping?
→ groupingBy()


Need frequency?
→ groupingBy + counting()


Need two groups?
→ partitioningBy()
```

---

# 81. Java 8 vs Collections Pattern

Java 8 doesn't replace Collections.

They work together.

Example:

```text
Problem
 ↓
Choose Collection
 ↓
Choose Stream operation
```

Example:

> Find duplicate numbers.

```text
Collection:
HashSet / HashMap


Java 8:
groupingBy + counting
```

Another:

> Find unique sorted numbers.

```text
Collection approach:
TreeSet


Stream approach:
distinct + sorted
```

---

# 82. Streams vs Traditional Loop

Traditional:

```java
for (Integer number : numbers) {
    if (number > 10) {
        System.out.println(number);
    }
}
```

Stream:

```java
numbers.stream()
       .filter(n -> n > 10)
       .forEach(System.out::println);
```

### Interview Point

Streams provide:

```text
Declarative style
Pipeline operations
Less boilerplate
Easy composition
Potential parallel processing
```

But:

```text
Stream ≠ automatically faster
```

Use the clearest solution.

---

# 83. Common Stream Mistakes

## Mistake 1 — Forgetting Terminal Operation

```java
numbers.stream()
       .filter(n -> n > 10);
```

Nothing happens.

Need:

```java
.collect(...)
```

or another terminal operation.

---

## Mistake 2 — Reusing Stream

```java
Stream<Integer> stream = numbers.stream();

stream.count();
stream.forEach(...); // Error
```

A Stream cannot be reused.

---

## Mistake 3 — Sorting unnecessarily

```text
Need max
→ use max()

Don't sort unless ordering is actually needed.
```

---

## Mistake 4 — Using forEach to build results

Avoid:

```java
List<Integer> result = new ArrayList<>();

numbers.stream()
       .filter(...)
       .forEach(result::add);
```

Prefer:

```java
List<Integer> result = numbers.stream()
        .filter(...)
        .collect(Collectors.toList());
```

---

## Mistake 5 — Using peek() for business logic

`peek()` is primarily useful for debugging/inspection.

---

## Mistake 6 — Calling Optional.get() blindly

Dangerous:

```java
optional.get();
```

Safer:

```java
optional.ifPresent(...);
```

or:

```java
optional.orElse(...);
```

or:

```java
optional.orElseThrow(...);
```

---

## Mistake 7 — Forgetting duplicate keys in toMap()

This can throw:

```text
IllegalStateException
```

when duplicate keys exist.

Use a merge function when required.

---

# 84. Common Optional Mistakes

```text
Optional.of(null)
→ NullPointerException


optional.get()
→ NoSuchElementException if empty


orElse(expensiveMethod())
→ expensiveMethod() evaluated even if value exists
```

Prefer:

```java
orElseGet(() -> expensiveMethod())
```

when lazy evaluation matters.

---

# 85. Common Date/Time Mistakes

```text
MM vs mm
HH vs hh
yyyy vs YYYY
```

Also remember:

```text
LocalDateTime
→ no timezone


Instant
→ exact global moment
```

---

# 86. Java 8 Interview Comparisons

## map vs flatMap

```text
map
→ Transform


flatMap
→ Transform + Flatten
```

---

## intermediate vs terminal

```text
Intermediate
→ returns Stream
→ lazy


Terminal
→ produces result/side effect
→ triggers execution
```

---

## findFirst vs findAny

```text
findFirst
→ first according to encounter order


findAny
→ any matching element
```

---

## anyMatch vs allMatch vs noneMatch

```text
anyMatch
→ at least one


allMatch
→ every


noneMatch
→ zero
```

---

## reduce vs collect

```text
reduce
→ many values → one value


collect
→ gather into result structure
```

---

## groupingBy vs partitioningBy

```text
groupingBy
→ multiple groups


partitioningBy
→ exactly two groups
```

---

## orElse vs orElseGet

```text
orElse
→ fallback evaluated eagerly


orElseGet
→ fallback evaluated lazily
```

---

## Period vs Duration

```text
Period
→ calendar/date amount


Duration
→ time/clock amount
```

---

## LocalDateTime vs ZonedDateTime

```text
LocalDateTime
→ date + time
→ no timezone


ZonedDateTime
→ date + time + timezone
```

---

## ZonedDateTime vs Instant

```text
ZonedDateTime
→ human-readable date/time in a zone


Instant
→ exact point on global timeline
```

---

# 87. Java 8 Complexity Cheat Sheet

For a Stream over `n` elements:

```text
filter()       → O(n)
map()          → O(n)
flatMap()      → O(n) relative to total elements processed
distinct()     → O(n) average + memory
sorted()       → O(n log n)
limit()        → O(k) where possible
skip()         → depends on stream/source
count()        → O(n) generally
max()/min()    → O(n)
anyMatch()     → O(n) worst case, can short-circuit
allMatch()     → O(n) worst case, can short-circuit
noneMatch()    → O(n) worst case, can short-circuit
findFirst()    → O(n) worst case, can short-circuit
reduce()       → O(n)
```

Important:

```text
Stream API does not magically change algorithmic complexity.
```

For example:

```text
sorted()
```

still requires sorting.

---

# 88. Java 8 Efficiency Rules

### Rule 1

> Don't sort when you only need min/max.

```text
max()
min()
```

---

### Rule 2

> Don't collect when you only need existence.

```text
anyMatch()
```

---

### Rule 3

> Don't count when you only need a boolean.

```text
anyMatch()
allMatch()
noneMatch()
```

---

### Rule 4

> Use `distinct()` when uniqueness is required.

---

### Rule 5

> Use `groupingBy()` when the words "per", "each", or "group by" appear.

---

### Rule 6

> Use `flatMap()` when nested collections must become one collection.

---

### Rule 7

> Use primitive streams for numeric aggregation when appropriate.

```java
mapToInt()
mapToLong()
mapToDouble()
```

Then:

```java
sum()
average()
min()
max()
```

---

# 89. Java 8 Problem → Pattern Dictionary

```text
"only numbers greater than 50"
→ filter


"convert names to uppercase"
→ map


"extract employee names"
→ map


"flatten all teams"
→ flatMap


"remove duplicates"
→ distinct


"sort descending"
→ sorted(reverseOrder)


"top 5"
→ sorted + limit


"3rd largest"
→ distinct + sorted(desc) + skip(2) + findFirst


"first matching"
→ filter + findFirst


"any matching"
→ anyMatch


"all matching"
→ allMatch


"none matching"
→ noneMatch


"highest"
→ max


"lowest"
→ min


"total"
→ sum / reduce


"average"
→ average


"combine into one value"
→ reduce


"convert to List"
→ collect(toList)


"convert to Set"
→ collect(toSet)


"create key-value map"
→ collect(toMap)


"group by department"
→ groupingBy


"count per department"
→ groupingBy + counting


"total per department"
→ groupingBy + summing


"average per department"
→ groupingBy + averaging


"split into two groups"
→ partitioningBy


"join strings"
→ joining


"optional result"
→ Optional
```

---

# 90. The Hard Pattern — GROUP → AGGREGATE → FILTER

This is an important interview pattern.

Problem:

> Find all highest-paid employees in each department.

Think:

```text
GROUP
↓
groupingBy(department)

AGGREGATE
↓
find maximum salary

FILTER
↓
keep all employees having maximum salary
```

Expected:

```text
IT
→ Rahul 75000
→ Vijay 75000

HR
→ Sneha 70000

Finance
→ Divya 90000
```

Important distinction:

```text
groupingBy + maxBy()
→ one maximum employee


groupingBy
→ maximum salary
→ filter
→ all tied maximum employees
```

---

# 91. Java 8 Interview Problem-Solving Framework

When an interviewer gives you a Stream problem:

```text
STEP 1
What is the output?

        ↓

STEP 2
SELECT / TRANSFORM / AGGREGATE?

        ↓

STEP 3
Is there a condition?

        ↓

STEP 4
Is there uniqueness?

        ↓

STEP 5
Is there grouping?

        ↓

STEP 6
Is ordering required?

        ↓

STEP 7
Can I avoid sorting?

        ↓

STEP 8
Can the operation short-circuit?

        ↓

STEP 9
What Collection/result type is needed?

        ↓

STEP 10
Time + Space complexity?
```

---

# 92. Java 8 One-Line Mental Models

```text
Lambda
→ Pass behavior


Functional Interface
→ One abstract behavior


Method Reference
→ Reuse existing method


Stream
→ Data processing pipeline


filter
→ Select


map
→ Transform


flatMap
→ Flatten


distinct
→ Unique


sorted
→ Order


limit
→ Take


skip
→ Ignore first N


max/min
→ Extreme value


findFirst
→ First


findAny
→ Any


anyMatch
→ At least one


allMatch
→ Every


noneMatch
→ None


reduce
→ Combine


collect
→ Gather


groupingBy
→ Group


partitioningBy
→ Split into two


Optional
→ Maybe a value


default method
→ Interface implementation


static interface method
→ Interface utility


LocalDate
→ Date


LocalTime
→ Time


LocalDateTime
→ Date + Time


ZonedDateTime
→ Date + Time + Zone


Instant
→ Exact global moment


Formatter
→ String ↔ Date/Time


Period
→ Calendar difference


Duration
→ Clock difference
```

---

# 93. 🚨 Ultimate Java 8 Decision Tree

```text
                    JAVA 8 PROBLEM
                          │
                          ▼
                 What is the requirement?
                          │
        ┌─────────────────┼──────────────────┐
        ▼                 ▼                  ▼
      BEHAVIOR          PROCESS DATA       OPTIONAL
        │                 │                  │
        ▼                 ▼                  ▼
     Lambda           Stream API          Optional
        │                 │
        ▼                 │
 Functional Interface      │
        │                 │
        └────────┐         │
                 ▼         ▼
             Method     What operation?
             Reference       │
                    ┌───────┼────────┐
                    ▼       ▼        ▼
                  SELECT TRANSFORM AGGREGATE
                    │       │        │
                    ▼       ▼        ▼
                  filter   map      max
                           flatMap  min
                                    count
                                    sum
                                    average
                                    reduce
                                    collect
```

Then:

```text
Need uniqueness?
→ distinct()


Need sorting?
→ sorted()


Need first N?
→ limit()


Need skip?
→ skip()


Need grouping?
→ groupingBy()


Need frequency?
→ groupingBy + counting()


Need two groups?
→ partitioningBy()
```

---

# 94. 🏆 Golden Java 8 Interview Rules

### Rule 1

> **Select → filter()**

### Rule 2

> **Transform → map()**

### Rule 3

> **Flatten → flatMap()**

### Rule 4

> **Unique → distinct()**

### Rule 5

> **Sort → sorted()**

### Rule 6

> **Maximum/minimum → max()/min()**

### Rule 7

> **First → findFirst()**

### Rule 8

> **Any → findAny()/anyMatch() depending on requirement**

### Rule 9

> **All → allMatch()**

### Rule 10

> **None → noneMatch()**

### Rule 11

> **Frequency → groupingBy() + counting()**

### Rule 12

> **Group → groupingBy()**

### Rule 13

> **Two groups → partitioningBy()**

### Rule 14

> **Combine → reduce()**

### Rule 15

> **Convert/gather → collect()**

### Rule 16

> **May be absent → Optional**

### Rule 17

> **Date → LocalDate**

### Rule 18

> **Time → LocalTime**

### Rule 19

> **Date + Time → LocalDateTime**

### Rule 20

> **Timezone → ZonedDateTime**

### Rule 21

> **Exact global timestamp → Instant**

---

# 95. 🚨 Final 10-Second Java 8 Cheat Sheet

Before coding, ask:

```text
1. SELECT?
   → filter()

2. TRANSFORM?
   → map()

3. FLATTEN?
   → flatMap()

4. UNIQUE?
   → distinct()

5. SORT?
   → sorted()

6. FIRST N?
   → limit()

7. SKIP N?
   → skip()

8. MAX / MIN?
   → max() / min()

9. FIRST / ANY?
   → findFirst() / findAny()

10. ANY / ALL / NONE?
    → anyMatch() / allMatch() / noneMatch()

11. COMBINE?
    → reduce()

12. LIST / SET / MAP?
    → collect()

13. GROUP?
    → groupingBy()

14. FREQUENCY?
    → groupingBy() + counting()

15. TWO GROUPS?
    → partitioningBy()

16. MAY BE ABSENT?
    → Optional

17. DATE?
    → LocalDate

18. TIME?
    → LocalTime

19. DATE + TIME?
    → LocalDateTime

20. TIMEZONE?
    → ZonedDateTime

21. GLOBAL TIMESTAMP?
    → Instant
```

---

# 96. Final Mental Model

Don't memorize 30 Stream methods individually.

Remember:

```text
                 JAVA 8
                   │
       ┌───────────┴───────────┐
       │                       │
    BEHAVIOR                DATA
       │                       │
    Lambda                  Stream
       │                       │
 Functional Interface           │
       │                       │
 Method Reference               │
                               │
                ┌──────────────┼──────────────┐
                │              │              │
              SELECT        TRANSFORM      AGGREGATE
                │              │              │
             filter          map          max/min
                            flatMap        count
                                          sum/avg
                                          reduce
                                          collect
```

Then remember the modifiers:

```text
UNIQUE
→ distinct()

SORT
→ sorted()

TOP N
→ limit()

SKIP
→ skip()

GROUP
→ groupingBy()

FREQUENCY
→ groupingBy + counting()

TWO GROUPS
→ partitioningBy()
```

And for non-Stream Java 8 features:

```text
BEHAVIOR
→ Lambda / Functional Interface / Method Reference

MISSING VALUE
→ Optional

INTERFACE EVOLUTION
→ default / static methods

DATE
→ LocalDate

TIME
→ LocalTime

DATE + TIME
→ LocalDateTime

TIMEZONE
→ ZonedDateTime

GLOBAL MOMENT
→ Instant

FORMAT/PARSE
→ DateTimeFormatter

DATE DIFFERENCE
→ Period

TIME DIFFERENCE
→ Duration
```

---

# 🎯 Final Interview Principle

When given a Java 8 problem:

```text
DON'T ASK:

"What Stream method do I remember?"


ASK:

"What is the problem asking me to DO?"
```

Then translate the requirement:

```text
Problem wording
       ↓
Operation
       ↓
Java 8 pattern
       ↓
Stream / Collection / Optional
       ↓
Implementation
       ↓
Time + Space complexity
```

The goal is not to memorize:

```text
filter()
map()
flatMap()
...
```

The goal is to see:

```text
"Find employees with salary > 50K"
             ↓
          SELECT
             ↓
          filter()


"Get employee names"
             ↓
        TRANSFORM
             ↓
           map()


"Employees per department"
             ↓
           GROUP
             ↓
        groupingBy()


"How many per department?"
             ↓
       GROUP + COUNT
             ↓
 groupingBy + counting()
```

> **Java 8 mastery = reading the requirement → identifying the pattern → choosing the simplest efficient operation.**