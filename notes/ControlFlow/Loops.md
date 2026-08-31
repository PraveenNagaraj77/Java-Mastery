# Java Loops — Interview Notes

## 1. What is it?

A **loop** is a control-flow mechanism in Java that repeatedly executes a block of code as long as a specified condition is satisfied.

Loops eliminate repetitive code and allow us to process multiple values efficiently.

### Without a loop

```java
System.out.println(1);
System.out.println(2);
System.out.println(3);
System.out.println(4);
System.out.println(5);
```

### With a loop

```java
for (int i = 1; i <= 5; i++) {
    System.out.println(i);
}
```

The loop executes the same block repeatedly with different values of `i`.

### Iteration

Each execution of the loop body is called an **iteration**.

For:

```java
for (int i = 1; i <= 5; i++) {
    System.out.println(i);
}
```

there are 5 iterations.

---

# 2. Why do we need loops?

Loops are required whenever the same operation needs to be performed repeatedly.

### Common use cases

* Traversing arrays
* Traversing strings
* Processing collections
* Searching data
* Sorting data
* Reading files
* Processing database records
* Processing API responses
* Validating user input
* Repeating business operations
* Implementing algorithms

### Real-world example

Suppose an application has 10,000 employees.

We need to print every employee's name.

Without loops, we would need thousands of statements.

Instead:

```java
for (Employee employee : employees) {
    System.out.println(employee.getName());
}
```

The loop allows the same operation to be applied to every employee.

---

# 3. How does it work internally?

At a high level, a loop works by repeatedly evaluating a condition and changing the flow of execution.

The general flow is:

```text
Initialization
      ↓
Condition
      ↓
Is condition true?
      ↓
   YES ───────→ Execute body
                   ↓
                 Update
                   ↓
               Condition
                   ↓
                  ...
                   ↓
   NO ─────────→ Exit loop
```

Java source code is compiled into bytecode.

The JVM executes the corresponding bytecode instructions repeatedly, including conditional branches that determine whether execution should continue or leave the loop.

For interviews, the important point is not memorizing JVM instructions.

The important concept is:

> A loop repeatedly evaluates a condition and controls program execution based on whether that condition is true or false.

---

# 4. Types of loops in Java

Java provides:

```text
1. for loop
2. while loop
3. do-while loop
4. enhanced for loop
```

We also have loop-control statements:

```text
break
continue
return
```

And an important concept:

```text
Nested loops
```

---

# 5. for Loop

## What is it?

The `for` loop is generally used when initialization, condition, and update can be expressed together.

## Syntax

```java
for (initialization; condition; update) {
    // body
}
```

## Example

```java
for (int i = 1; i <= 5; i++) {
    System.out.println(i);
}
```

Output:

```text
1
2
3
4
5
```

---

# 6. How does a for loop execute?

Consider:

```java
for (int i = 1; i <= 5; i++) {
    System.out.println(i);
}
```

Execution:

```text
1. int i = 1
2. Check i <= 5
3. Execute body
4. Execute i++
5. Check condition again
6. Repeat
7. Exit when condition becomes false
```

Detailed execution:

```text
i = 1
1 <= 5 → true
print 1
i++

i = 2
2 <= 5 → true
print 2
i++

...

i = 6
6 <= 5 → false
exit
```

### Important

Initialization executes only once.

The condition is checked before every iteration.

The update executes after the body.

---

# 7. Reverse for Loop

```java
for (int i = 5; i >= 1; i--) {
    System.out.println(i);
}
```

Output:

```text
5
4
3
2
1
```

This pattern is frequently used in DSA.

---

# 8. Increment by a Specific Value

The update expression doesn't have to be `i++`.

```java
for (int i = 0; i <= 10; i += 2) {
    System.out.println(i);
}
```

Output:

```text
0
2
4
6
8
10
```

---

# 9. Multiple Variables in a for Loop

Java allows multiple initialization and update expressions.

```java
for (int i = 0, j = 10; i < j; i++, j--) {
    System.out.println(i + " " + j);
}
```

This type of pattern can be useful when working with two indexes or two-pointer algorithms.

---

# 10. Infinite for Loop

A `for` loop can omit all three expressions.

```java
for (;;) {
    System.out.println("Running");
}
```

This creates an infinite loop.

Equivalent concept:

```java
while (true) {
    // code
}
```

An infinite loop is not necessarily a bug.

Some applications intentionally run continuously, such as server processes or event-processing systems, but they must have an appropriate way to terminate or stop processing.

---

# 11. while Loop

## What is it?

A `while` loop repeatedly executes a block as long as its condition remains true.

## Syntax

```java
while (condition) {
    // body
}
```

## Example

```java
int i = 1;

while (i <= 5) {
    System.out.println(i);
    i++;
}
```

---

# 12. How does while work?

Execution:

```text
Initialize
   ↓
Check condition
   ↓
True?
 ↓       ↓
YES      NO
 ↓        ↓
Body     Exit
 ↓
Update
 ↓
Condition
```

Unlike a `for` loop, the initialization and update are normally written separately.

---

# 13. When should we use while?

Use `while` when the number of iterations is not necessarily known in advance.

Example:

```java
while (number != 0) {
    number /= 10;
}
```

The number of iterations depends on the value of `number`.

### Real-world example

A program may repeatedly ask for valid input:

```java
while (!isValidInput) {
    // ask user for input
}
```

We don't necessarily know how many attempts will be required.

---

# 14. do-while Loop

## What is it?

A `do-while` loop executes its body first and checks the condition afterward.

Therefore:

> A `do-while` loop always executes its body at least once.

## Syntax

```java
do {
    // body
} while (condition);
```

Notice the semicolon after the condition.

## Example

```java
int i = 1;

do {
    System.out.println(i);
    i++;
} while (i <= 5);
```

---

# 15. while vs do-while

### while

```java
int i = 10;

while (i < 5) {
    System.out.println(i);
}
```

Output:

```text
No output
```

The condition is false before the body executes.

### do-while

```java
int i = 10;

do {
    System.out.println(i);
} while (i < 5);
```

Output:

```text
10
```

The body executes before the condition is checked.

---

# 16. Real-world do-while Example

Menu-driven applications are a classic use case.

```java
int option;

do {
    System.out.println("1. View Account");
    System.out.println("2. Transfer Money");
    System.out.println("3. Exit");

    // read option

} while (option != 3);
```

The menu needs to be displayed at least once.

---

# 17. Enhanced for Loop

The enhanced `for` loop is commonly called the **for-each loop**.

## Syntax

```java
for (Type variable : arrayOrIterable) {
    // body
}
```

## Example

```java
int[] numbers = {10, 20, 30, 40};

for (int number : numbers) {
    System.out.println(number);
}
```

Output:

```text
10
20
30
40
```

---

# 18. for vs Enhanced for

### Traditional for

```java
for (int i = 0; i < numbers.length; i++) {
    System.out.println(numbers[i]);
}
```

Advantages:

* Access to index
* Can move forward or backward
* Can skip positions
* More control over traversal
* Useful when modifying elements by index

### Enhanced for

```java
for (int number : numbers) {
    System.out.println(number);
}
```

Advantages:

* Cleaner syntax
* Easier to read
* Excellent for simple traversal

Limitation:

> The enhanced for loop does not directly expose the array index.

---

# 19. Nested Loops

A loop inside another loop is called a **nested loop**.

Example:

```java
for (int i = 1; i <= 3; i++) {

    for (int j = 1; j <= 3; j++) {

        System.out.println(
                "i = " + i + ", j = " + j
        );
    }
}
```

The inner loop executes completely for every iteration of the outer loop.

Conceptually:

```text
i = 1
    j = 1
    j = 2
    j = 3

i = 2
    j = 1
    j = 2
    j = 3

i = 3
    j = 1
    j = 2
    j = 3
```

---

# 20. Nested Loops and Time Complexity

Consider:

```java
for (int i = 0; i < n; i++) {

    for (int j = 0; j < n; j++) {

        // operation
    }
}
```

The outer loop runs `n` times.

For every outer iteration, the inner loop runs `n` times.

Therefore:

```text
n × n = n²
```

Time complexity:

```text
O(n²)
```

This is extremely important for DSA.

---

# 21. break

## What is it?

`break` immediately terminates the current loop.

Example:

```java
for (int i = 1; i <= 10; i++) {

    if (i == 5) {
        break;
    }

    System.out.println(i);
}
```

Output:

```text
1
2
3
4
```

When `i == 5`, execution leaves the loop completely.

---

# 22. Real-world use of break

Searching an array:

```java
int[] numbers = {10, 20, 30, 40, 50};

int target = 30;

for (int i = 0; i < numbers.length; i++) {

    if (numbers[i] == target) {
        System.out.println("Found at index " + i);
        break;
    }
}
```

Once the target is found, continuing to search is unnecessary.

This is called **early termination**.

---

# 23. continue

## What is it?

`continue` skips the current iteration and proceeds to the next iteration.

Example:

```java
for (int i = 1; i <= 5; i++) {

    if (i == 3) {
        continue;
    }

    System.out.println(i);
}
```

Output:

```text
1
2
4
5
```

When `i == 3`, the remaining statements in that iteration are skipped.

---

# 24. break vs continue

| `break`                    | `continue`                        |
| -------------------------- | --------------------------------- |
| Terminates the loop        | Skips current iteration           |
| Execution leaves the loop  | Execution remains inside the loop |
| Used for early termination | Used to ignore certain iterations |

Mental model:

```text
break
  ↓
Exit loop


continue
  ↓
Skip current iteration
  ↓
Next iteration
```

---

# 25. return Inside a Loop

`return` is different from both `break` and `continue`.

Example:

```java
public static int findNumber(int[] numbers, int target) {

    for (int i = 0; i < numbers.length; i++) {

        if (numbers[i] == target) {
            return i;
        }
    }

    return -1;
}
```

When the target is found:

```java
return i;
```

does two things:

1. Stops the loop.
2. Exits the entire method.

Therefore:

```text
continue
    ↓
Next iteration


break
    ↓
Exit loop


return
    ↓
Exit method
```

---

# 26. Loop + Array

Loops become extremely important when working with arrays.

Example:

```java
int[] numbers = {5, 10, 15, 20, 25};

int sum = 0;

for (int i = 0; i < numbers.length; i++) {
    sum += numbers[i];
}

System.out.println(sum);
```

The loop traverses every element.

Complexity:

```text
Time:  O(n)
Space: O(1)
```

This is one of the fundamental DSA patterns.

---

# 27. Finding Maximum Using a Loop

```java
int[] numbers = {10, 50, 20, 80, 30};

int max = numbers[0];

for (int i = 1; i < numbers.length; i++) {

    if (numbers[i] > max) {
        max = numbers[i];
    }
}

System.out.println(max);
```

The pattern is:

```text
Traverse
   ↓
Compare
   ↓
Update answer
```

This pattern appears frequently in DSA.

---

# 28. Linear Search

```java
int[] numbers = {10, 20, 30, 40, 50};

int target = 30;

for (int i = 0; i < numbers.length; i++) {

    if (numbers[i] == target) {
        System.out.println("Found at index: " + i);
        break;
    }
}
```

Time complexity:

```text
Best case:  O(1)
Worst case: O(n)
```

---

# 29. Reverse a Number

Loops are also used for number-based DSA problems.

```java
int number = 12345;
int reversed = 0;

while (number != 0) {

    int digit = number % 10;

    reversed = reversed * 10 + digit;

    number /= 10;
}

System.out.println(reversed);
```

Output:

```text
54321
```

Important concepts:

```text
% 10
→ extracts last digit

/ 10
→ removes last digit
```

---

# 30. Count Digits

```java
int number = 123456;
int count = 0;

while (number != 0) {
    number /= 10;
    count++;
}

System.out.println(count);
```

Output:

```text
6
```

---

# 31. Sum of Digits

```java
int number = 12345;
int sum = 0;

while (number != 0) {

    sum += number % 10;

    number /= 10;
}

System.out.println(sum);
```

Output:

```text
15
```

---

# 32. Prime Number Check

Basic approach:

```java
int number = 29;
boolean isPrime = true;

if (number < 2) {
    isPrime = false;
}

for (int i = 2; i < number; i++) {

    if (number % i == 0) {
        isPrime = false;
        break;
    }
}
```

Optimized approach:

```java
for (int i = 2; i * i <= number; i++) {

    if (number % i == 0) {
        isPrime = false;
        break;
    }
}
```

Why `i * i <= number`?

If a number has a factor greater than its square root, it must have a corresponding factor smaller than its square root.

Therefore, we only need to check up to `√n`.

Complexity improves from approximately:

```text
O(n)
```

to:

```text
O(√n)
```

This is an important example of **algorithmic optimization**.

---

# 33. Common Mistakes

## Mistake 1 — Off-by-one error

Wrong:

```java
for (int i = 0; i <= numbers.length; i++) {
    System.out.println(numbers[i]);
}
```

Correct:

```java
for (int i = 0; i < numbers.length; i++) {
    System.out.println(numbers[i]);
}
```

For an array of length `n`, valid indexes are:

```text
0 → n - 1
```

Therefore:

```java
i < numbers.length
```

is normally used.

---

## Mistake 2 — Infinite while loop

```java
int i = 0;

while (i < 10) {
    System.out.println(i);
}
```

`i` never changes.

Therefore the condition remains true forever.

Correct:

```java
int i = 0;

while (i < 10) {
    System.out.println(i);
    i++;
}
```

---

## Mistake 3 — Wrong update direction

Wrong:

```java
for (int i = 10; i > 0; i++) {
}
```

`i` keeps increasing.

Correct:

```java
for (int i = 10; i > 0; i--) {
}
```

---

## Mistake 4 — Forgetting break

If a search finds the required element but doesn't terminate early:

```java
for (int i = 0; i < numbers.length; i++) {

    if (numbers[i] == target) {
        System.out.println("Found");
    }
}
```

The loop continues searching even after finding the target.

Depending on the problem, this may be unnecessary work.

---

# 34. Interview Questions

## Basic

### Q1. What is a loop?

A loop repeatedly executes a block of code while a condition is satisfied.

### Q2. What types of loops are available in Java?

* `for`
* `while`
* `do-while`
* enhanced `for`

### Q3. What is the difference between `for` and `while`?

A `for` loop is generally convenient when initialization, condition, and update are closely related. A `while` loop is generally useful when the iteration count is not known beforehand and the condition controls repetition.

### Q4. What is the difference between `while` and `do-while`?

`while` checks the condition before execution.

`do-while` checks the condition after execution.

Therefore, `do-while` executes at least once.

### Q5. What is an enhanced for loop?

It is a simplified syntax used to iterate through arrays and `Iterable` collections.

---

# 35. Intermediate Interview Questions

### Q6. Can a for loop be infinite?

Yes.

```java
for (;;) {
}
```

### Q7. What happens if the condition of a for loop is omitted?

```java
for (;;) {
}
```

The condition is treated as always true, resulting in an infinite loop unless execution is interrupted by something such as `break`, `return`, an exception, or another termination mechanism.

### Q8. Can we have multiple variables in a for loop?

Yes.

```java
for (int i = 0, j = 10; i < j; i++, j--) {
}
```

### Q9. Can we use break inside nested loops?

Yes, but an ordinary `break` terminates only the **innermost loop**.

### Q10. How can we terminate an outer loop from an inner loop?

A labeled break can be used:

```java
outer:
for (int i = 0; i < 5; i++) {

    for (int j = 0; j < 5; j++) {

        if (condition) {
            break outer;
        }
    }
}
```

---

# 36. DSA Interview Questions

### Q11. What is the complexity of one loop?

If the loop runs `n` times:

```java
for (int i = 0; i < n; i++) {
}
```

Time complexity is:

```text
O(n)
```

### Q12. What is the complexity of two nested loops?

```java
for (int i = 0; i < n; i++) {

    for (int j = 0; j < n; j++) {
    }
}
```

Generally:

```text
O(n²)
```

### Q13. Does every nested loop automatically mean O(n²)?

No.

For example:

```java
for (int i = 0; i < n; i++) {

    for (int j = 0; j < 10; j++) {
    }
}
```

The inner loop runs a constant 10 times.

Therefore:

```text
O(10n)
= O(n)
```

This is an important interview distinction.

### Q14. What is an off-by-one error?

An off-by-one error occurs when a loop executes one time too many or one time too few because of an incorrect boundary condition.

Example:

```java
i <= array.length
```

instead of:

```java
i < array.length
```

---

# 37. Detailed Interview Explanation

### "Explain loops in Java."

> "A loop in Java is a control-flow mechanism that allows us to repeatedly execute a block of code while a particular condition is satisfied. Loops are primarily used to avoid repetitive code and to process multiple values such as array elements, collection elements, database records, or user input."
>
> "Java provides `for`, `while`, `do-while`, and enhanced `for` loops. A `for` loop is generally preferred when the initialization, termination condition, and update are closely related, especially when we know or can control the number of iterations. A `while` loop is useful when repetition depends primarily on a condition and the number of iterations may not be known in advance. A `do-while` loop differs because it executes its body at least once before checking the condition."
>
> "During execution, a loop evaluates its condition and, if the condition is true, executes its body. The loop then performs its update or proceeds to the next condition check. This continues until the condition becomes false or execution is explicitly terminated."
>
> "Loops are particularly important in DSA because array and string problems frequently require traversal. For example, a linear search uses a loop to inspect each element until the target is found. Finding a maximum value similarly requires traversing the input while maintaining the current maximum."
>
> "Nested loops are also important because they directly affect time complexity. If one loop performs `n` iterations, the complexity is generally O(n). If two loops each perform `n` iterations and one is nested inside the other, the total number of operations is approximately n², resulting in O(n²). However, we should analyze the actual loop bounds rather than assuming every nested loop is O(n²)."
>
> "Java also provides `break` and `continue` for controlling loop execution. `break` terminates the current loop, while `continue` skips the current iteration. A `return` statement can also terminate a loop, but it exits the entire method rather than merely exiting the loop."
>
> "So, for me, understanding loops is not only about remembering syntax. I need to understand initialization, condition checking, iteration, termination, nested execution, and the resulting time complexity because these concepts form the foundation of algorithm design and DSA."

---

# 38. Key Interview Mental Model

Whenever you see a loop, ask:

```text
1. Where does it start?
2. What is the condition?
3. What changes on every iteration?
4. What work happens inside?
5. How many times can it execute?
6. Can it terminate early?
7. What is the time complexity?
8. What is the space complexity?
```

This mental model is extremely important when solving DSA problems.

---

# 39. Quick Revision

```text
for
→ Best when initialization, condition and update are controlled together

while
→ Best when repetition depends on a condition

do-while
→ Body executes at least once

enhanced for
→ Simple traversal of arrays / Iterable collections

break
→ Exit loop

continue
→ Skip current iteration

return
→ Exit method

nested loop
→ Loop inside another loop

single loop
→ Often O(n)

nested n × n loop
→ Often O(n²)

array traversal
→ Fundamental DSA pattern
```

---

# 40. Important Code Patterns

### Traverse array

```java
for (int i = 0; i < arr.length; i++) {
    // use arr[i]
}
```

### Traverse backwards

```java
for (int i = arr.length - 1; i >= 0; i--) {
    // use arr[i]
}
```

### Find maximum

```java
int max = arr[0];

for (int i = 1; i < arr.length; i++) {
    if (arr[i] > max) {
        max = arr[i];
    }
}
```

### Linear search

```java
for (int i = 0; i < arr.length; i++) {
    if (arr[i] == target) {
        return i;
    }
}

return -1;
```

### Skip elements

```java
for (int i = 0; i < arr.length; i++) {

    if (condition) {
        continue;
    }

    // process element
}
```

### Early termination

```java
for (int i = 0; i < arr.length; i++) {

    if (condition) {
        break;
    }
}
```

---

# 41. Java 11 Compatibility

The core loops covered in this document are available in **Java 11**:

```text
for
while
do-while
enhanced for
break
continue
```

Our local environment is Java 21, but for the Athenahealth MTS preparation we should always distinguish features available in the **Java 11 baseline** from newer Java features.

Loops themselves do not create a Java 11 compatibility concern.

---

# 42. Final Takeaway

The most important thing to remember is:

```text
Loop
 ↓
Repeated execution
 ↓
Iteration
 ↓
Traversal
 ↓
Algorithm
 ↓
Time Complexity
```

Loops are not merely a Java syntax topic.

They are the bridge between **Java fundamentals and DSA**.
