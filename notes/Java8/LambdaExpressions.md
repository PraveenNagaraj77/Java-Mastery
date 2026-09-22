# Lambda Expressions

## 1. What is a Lambda Expression?

A **Lambda Expression** is a short way to provide an implementation of a **functional interface's single abstract method**.

It allows us to represent behavior as data and pass that behavior to methods.

Basic syntax:

```java
(parameters) -> expression
```

Example:

```java
(a, b) -> a + b
```

This means:

```text
Take a and b
   ↓
Add them
   ↓
Return the result
```

---

## 2. Why do we need Lambda Expressions?

Before Java 8, implementing a functional interface often required an anonymous class.

Example:

```java
Calculator addition = new Calculator() {
    @Override
    public int calculate(int a, int b) {
        return a + b;
    }
};
```

With Lambda:

```java
Calculator addition = (a, b) -> a + b;
```

Lambda expressions make code:

- Shorter
- More readable
- Easier to pass as behavior
- Useful with Collections
- Useful with Stream API
- Useful with functional interfaces

---

## 3. Lambda Expression Syntax

General syntax:

```java
(parameters) -> expression
```

or:

```java
(parameters) -> {
    statements;
}
```

Example:

```java
(a, b) -> a + b
```

Block body:

```java
(a, b) -> {
    int result = a + b;
    return result;
}
```

---

## 4. Lambda Expression Examples

### No parameters

```java
() -> System.out.println("Hello Java");
```

### One parameter

```java
name -> System.out.println(name);
```

Parentheses are optional for a single parameter:

```java
name -> System.out.println(name);
```

or:

```java
(name) -> System.out.println(name);
```

Both are valid.

### Multiple parameters

```java
(a, b) -> a + b
```

Multiple parameters require parentheses.

---

## 5. Expression Lambda

If the Lambda contains a single expression, braces and `return` are not required.

```java
(a, b) -> a + b
```

The result is automatically returned.

Equivalent block Lambda:

```java
(a, b) -> {
    return a + b;
}
```

### Important

This:

```java
(a, b) -> a + b
```

is equivalent to:

```java
(a, b) -> {
    return a + b;
}
```

---

## 6. Block Lambda

A block Lambda uses `{}` when multiple statements are required.

Example:

```java
(a, b) -> {
    int sum = a + b;
    System.out.println("Sum: " + sum);
    return sum;
}
```

When using a block body with a return type, `return` must be explicitly written.

---

## 7. Lambda Requires a Functional Interface

A Lambda expression does not have its own independent type.

It needs a **target type**.

Usually that target type is a **functional interface**.

Example:

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}
```

Now the Lambda can implement it:

```java
Calculator addition = (a, b) -> a + b;
```

The compiler understands:

```text
Calculator
    ↓
calculate(int, int)
    ↓
(a, b) -> a + b
```

---

## 8. Why Does Lambda Need a Functional Interface?

Consider:

```java
(a, b) -> a + b
```

The compiler needs to know:

- What type are `a` and `b`?
- What method is being implemented?
- What should the return type be?

The functional interface provides that information.

Example:

```java
Calculator addition = (a, b) -> a + b;
```

The compiler knows:

```java
calculate(int, int)
```

returns:

```java
int
```

Therefore the Lambda is valid.

---

## 9. Lambda Without Target Type

This is invalid:

```java
var addition = (a, b) -> a + b;
```

Why?

Because the Lambda itself does not have a standalone type.

The compiler needs a target functional interface.

Correct:

```java
Calculator addition = (a, b) -> a + b;
```

---

## 10. Custom Functional Interface Example

```java
@FunctionalInterface
interface Calculator {

    int calculate(int a, int b);
}
```

Different behaviors can be created using Lambdas:

```java
Calculator addition = (a, b) -> a + b;

Calculator subtraction = (a, b) -> a - b;

Calculator multiplication = (a, b) -> a * b;

Calculator division = (a, b) -> a / b;
```

Example:

```java
System.out.println(addition.calculate(20, 5));
System.out.println(subtraction.calculate(20, 5));
System.out.println(multiplication.calculate(20, 5));
System.out.println(division.calculate(20, 5));
```

Output:

```text
25
15
100
4
```

---

## 11. Passing Lambda as a Method Argument

Lambda expressions can be passed directly to methods.

Example:

```java
public static int calculate(
        int a,
        int b,
        Calculator calculator
) {
    return calculator.calculate(a, b);
}
```

Now:

```java
int result = calculate(
        20,
        5,
        (a, b) -> a + b
);
```

The Lambda provides the behavior.

---

## 12. Behavior Parameterization

One of the most important concepts behind Lambdas is **behavior parameterization**.

Instead of hardcoding the behavior:

```java
public static int add(int a, int b) {
    return a + b;
}
```

We can pass the behavior:

```java
public static int calculate(
        int a,
        int b,
        Calculator calculator
) {
    return calculator.calculate(a, b);
}
```

Now different behaviors can be supplied:

```java
calculate(20, 5, (a, b) -> a + b);
```

```java
calculate(20, 5, (a, b) -> a - b);
```

```java
calculate(20, 5, (a, b) -> a * b);
```

This makes code more reusable.

---

## 13. Lambda with Conditional Logic

Lambda expressions can contain conditions.

Example:

```java
Calculator maximum = (a, b) -> a > b ? a : b;
```

Example:

```java
System.out.println(maximum.calculate(20, 50));
```

Output:

```text
50
```

---

## 14. Explicit Parameter Types

Parameter types can be explicitly specified:

```java
Calculator addition = (int a, int b) -> a + b;
```

Or inferred:

```java
Calculator addition = (a, b) -> a + b;
```

Both are valid.

### Important Rule

Do not mix explicit and inferred parameter types.

Invalid:

```java
(int a, b) -> a + b
```

Valid:

```java
(int a, int b) -> a + b
```

or:

```java
(a, b) -> a + b
```

---

## 15. Lambda and Return Values

Expression Lambda:

```java
(a, b) -> a + b
```

Automatically returns the result.

Block Lambda:

```java
(a, b) -> {
    return a + b;
}
```

Requires explicit `return`.

Invalid:

```java
(a, b) -> {
    a + b;
}
```

If the functional interface expects a return value, the block must return it.

---

## 16. Lambda and Local Variables

A Lambda can access local variables only if they are:

- `final`
- or effectively final

Example:

```java
int tax = 18;

Function<Integer, Integer> calculateTax =
        price -> price + (price * tax / 100);
```

This is valid because `tax` is not changed after initialization.

---

## 17. Effectively Final

A variable does not need to explicitly use the `final` keyword.

This is valid:

```java
int tax = 18;

Function<Integer, Integer> calculateTax =
        price -> price + (price * tax / 100);
```

Because `tax` is never reassigned.

This is invalid:

```java
int tax = 18;

tax = 20;

Function<Integer, Integer> calculateTax =
        price -> price + (price * tax / 100);
```

The variable is no longer effectively final.

### Remember

```text
Local variable captured by Lambda
        ↓
Must be final
OR
effectively final
```

---

## 18. Lambda and Instance Variables

Instance variables can be modified inside a Lambda because they belong to the object, not to the local variable capture mechanism.

Example:

```java
public class Counter {

    private int count = 0;

    public void increment() {

        Runnable task = () -> {
            count++;
        };

        task.run();
    }
}
```

The Lambda can access and modify `count`.

---

## 19. Lambda and `this`

Inside a Lambda:

```java
this
```

refers to the **enclosing class object**.

Example:

```java
public class UserService {

    private String name = "Praveen";

    public void printName() {

        Runnable task = () -> {
            System.out.println(this.name);
        };

        task.run();
    }
}
```

Here:

```java
this.name
```

refers to the `UserService` object's `name`.

---

## 20. Lambda vs Anonymous Class

### Anonymous Class

```java
Calculator calculator = new Calculator() {

    @Override
    public int calculate(int a, int b) {
        return a + b;
    }
};
```

### Lambda

```java
Calculator calculator = (a, b) -> a + b;
```

### Difference

| Lambda | Anonymous Class |
| --- | --- |
| Shorter syntax | More verbose |
| Used with functional interfaces | Can implement interfaces/classes |
| `this` refers to enclosing object | `this` refers to anonymous object |
| Mainly represents behavior | Represents an object implementation |
| Introduced in Java 8 | Available before Java 8 |

---

## 21. Lambda Does Not Automatically Create a Thread

A common misconception is:

> Lambda means multithreading.

This is incorrect.

Example:

```java
Runnable task = () -> {
    System.out.println("Running");
};
```

Calling:

```java
task.run();
```

does not create a new thread.

It executes on the current thread.

To create a new thread:

```java
Thread thread = new Thread(task);
thread.start();
```

### Remember

```text
Lambda
    ↓
Represents behavior

Thread
    ↓
Provides execution mechanism
```

Lambda and multithreading are separate concepts.

---

## 22. Lambda with Runnable

`Runnable` is a functional interface.

Its method is:

```java
void run();
```

Therefore:

```java
Runnable task = () -> {
    System.out.println("Task executed");
};
```

Execute directly:

```java
task.run();
```

Execute on a new thread:

```java
new Thread(task).start();
```

---

## 23. Lambda with Collections

Lambdas are commonly used with collections.

Example:

```java
List<String> skills = new ArrayList<>();

skills.add("Java");
skills.add("Spring Boot");
skills.add("React");
```

Using `forEach()`:

```java
skills.forEach(skill -> System.out.println(skill));
```

Method reference:

```java
skills.forEach(System.out::println);
```

---

## 24. Lambda and Standard Functional Interfaces

Java provides commonly used functional interfaces in:

```java
java.util.function
```

Examples:

```text
Predicate
Function
Consumer
Supplier
BiPredicate
BiFunction
UnaryOperator
BinaryOperator
```

Example:

```java
Predicate<Integer> isAdult = age -> age >= 18;
```

```java
Function<String, Integer> length = text -> text.length();
```

```java
Consumer<String> printer = text -> System.out.println(text);
```

```java
Supplier<String> message = () -> "Hello Java";
```

---

## 25. Lambda as a Replacement for Simple Anonymous Classes

Before Java 8:

```java
Runnable task = new Runnable() {

    @Override
    public void run() {
        System.out.println("Processing...");
    }
};
```

With Lambda:

```java
Runnable task = () -> System.out.println("Processing...");
```

The Lambda makes the intended behavior clearer.

---

## 26. Common Lambda Patterns

### Check condition

```java
number -> number > 50
```

### Transform value

```java
price -> price * 1.18
```

### Print value

```java
name -> System.out.println(name)
```

### Generate value

```java
() -> "Welcome"
```

### Combine values

```java
(a, b) -> a + b
```

### Compare values

```java
(a, b) -> a > b ? a : b
```

---

## 27. Common Mistakes

### Mistake 1 — Lambda without a target type

Invalid:

```java
var operation = (a, b) -> a + b;
```

Correct:

```java
BiFunction<Integer, Integer, Integer> operation =
        (a, b) -> a + b;
```

---

### Mistake 2 — Mixing explicit and inferred types

Invalid:

```java
(int a, b) -> a + b
```

Correct:

```java
(int a, int b) -> a + b
```

or:

```java
(a, b) -> a + b
```

---

### Mistake 3 — Forgetting return in block Lambda

Invalid:

```java
(a, b) -> {
    a + b;
}
```

Correct:

```java
(a, b) -> {
    return a + b;
}
```

---

### Mistake 4 — Modifying captured local variable

Invalid:

```java
int count = 10;

Runnable task = () -> {
    count++;
};
```

Local variables captured by Lambdas must be final or effectively final.

---

### Mistake 5 — Thinking Lambda automatically creates a thread

```java
Runnable task = () -> System.out.println("Running");
```

This only defines the behavior.

It does not automatically create a thread.

---

## 28. Interview Questions

### Basic

**Q1. What is a Lambda Expression?**

A Lambda expression is a concise way to provide an implementation of the single abstract method of a functional interface.

---

**Q2. When were Lambda expressions introduced?**

Lambda expressions were introduced in **Java 8**.

---

**Q3. What is the basic syntax of a Lambda?**

```java
(parameters) -> expression
```

or:

```java
(parameters) -> {
    statements;
}
```

---

**Q4. Can Lambda expressions exist without functional interfaces?**

A Lambda requires a target type, typically a functional interface.

---

**Q5. What is a functional interface?**

An interface containing exactly one abstract method.

---

### Intermediate

**Q6. Why does Lambda require a target type?**

A Lambda does not have an independent type. The target functional interface tells the compiler which abstract method the Lambda implements and what parameter and return types are expected.

---

**Q7. What is behavior parameterization?**

Behavior parameterization means passing behavior as an argument so that the same method can execute different behaviors.

Example:

```java
calculate(10, 5, (a, b) -> a + b);
```

---

**Q8. What is the difference between expression and block Lambda?**

Expression Lambda:

```java
(a, b) -> a + b
```

Block Lambda:

```java
(a, b) -> {
    return a + b;
}
```

A block Lambda requires an explicit `return` when the functional interface expects a return value.

---

**Q9. What is effectively final?**

A local variable is effectively final when it is assigned once and never reassigned.

Such variables can be accessed from a Lambda.

---

**Q10. What does `this` refer to inside a Lambda?**

`this` refers to the enclosing class instance.

---

## 29. Advanced Interview Questions

**Q11. Can a Lambda have multiple statements?**

Yes.

Use a block Lambda:

```java
(a, b) -> {
    int result = a + b;
    System.out.println(result);
    return result;
}
```

---

**Q12. Can a Lambda modify a local variable?**

It cannot modify a captured local variable because the variable must be final or effectively final.

---

**Q13. Can a Lambda access instance variables?**

Yes.

Example:

```java
private int count;

Runnable task = () -> count++;
```

---

**Q14. Does every Lambda create an object?**

A Lambda represents behavior and is implemented by the JVM/runtime. It should not be conceptually treated simply as "creating an anonymous class object."

---

**Q15. Lambda vs method reference?**

Lambda:

```java
name -> System.out.println(name)
```

Method reference:

```java
System.out::println
```

A method reference is a more concise way of expressing certain Lambdas that simply invoke an existing method.

---

## 30. Real-World Example

Suppose an application needs different pricing strategies.

Instead of creating separate methods:

```java
public static double calculatePrice(
        double price,
        Function<Double, Double> strategy
) {
    return strategy.apply(price);
}
```

Now different pricing behavior can be passed:

```java
double discountPrice =
        calculatePrice(
                1000,
                price -> price * 0.90
        );
```

Another strategy:

```java
double taxPrice =
        calculatePrice(
                1000,
                price -> price * 1.18
        );
```

The method stays the same.

Only the behavior changes.

This is **behavior parameterization**.

---

## 31. Lambda Mental Model

Think of Lambda as:

```text
Functional Interface
        ↓
Single Abstract Method
        ↓
Lambda provides implementation
        ↓
Behavior can be passed around
```

Example:

```java
Predicate<Integer> isAdult = age -> age >= 18;
```

Mental model:

```text
Predicate
   ↓
test()
   ↓
age -> age >= 18
   ↓
Question
   ↓
true / false
```

---

## 32. Lambda Cheat Sheet

```text
Lambda
    ↓
Java 8
    ↓
Short way to implement functional interface
    ↓
Requires target type
    ↓
Usually functional interface
    ↓
Syntax
    ↓
(parameters) -> expression
    ↓
or
    ↓
(parameters) -> { statements; }
```

### Syntax Examples

```java
() -> "Hello"
```

```java
name -> name.toUpperCase()
```

```java
(a, b) -> a + b
```

```java
(a, b) -> {
    int result = a + b;
    return result;
}
```

---

## 33. Key Takeaways

```text
Lambda Expression
       ↓
Introduced in Java 8
       ↓
Concise way to represent behavior
       ↓
Works with functional interfaces
       ↓
Requires a target type
       ↓
Can be passed as an argument
       ↓
Enables behavior parameterization
       ↓
Used heavily with Collections and Streams
       ↓
Local captured variables must be final/effectively final
       ↓
this refers to enclosing class
```

### Remember

**Lambda = Behavior as a value**

**Functional Interface = Target type for Lambda**

**Lambda + Functional Interface = Implementation of behavior**