# Functional Interfaces

## 1. What is a Functional Interface?

A **Functional Interface** is an interface that contains exactly **one abstract method**.

It is also called a:

**SAM Interface — Single Abstract Method**

Example:

```java
@FunctionalInterface
public interface Calculator {

    int calculate(int a, int b);
}
```

Because `Calculator` has only one abstract method, it is a functional interface.

---

## 2. Why do we need Functional Interfaces?

Functional interfaces provide the **target type for Lambda Expressions**.

Example:

```java
Calculator addition = (a, b) -> a + b;
```

Here:

```text
Calculator
     ↓
calculate(int, int)
     ↓
(a, b) -> a + b
```

The functional interface tells Java what the Lambda expression represents.

---

## 3. Functional Interface and Lambda

A Lambda expression needs a target type.

Example:

```java
@FunctionalInterface
interface Calculator {

    int calculate(int a, int b);
}
```

Lambda implementation:

```java
Calculator addition = (a, b) -> a + b;
```

Without the functional interface, the compiler would not know what type the Lambda should represent.

Invalid:

```java
var addition = (a, b) -> a + b;
```

The Lambda does not have an independent type.

---

## 4. SAM — Single Abstract Method

SAM stands for:

```text
Single Abstract Method
```

Example:

```java
@FunctionalInterface
interface Calculator {

    int calculate(int a, int b);
}
```

There is only one abstract method:

```java
calculate()
```

Therefore it is a functional interface.

---

## 5. `@FunctionalInterface`

Java provides the annotation:

```java
@FunctionalInterface
```

Example:

```java
@FunctionalInterface
public interface Calculator {

    int calculate(int a, int b);
}
```

The annotation tells the compiler:

> This interface is intended to be a functional interface.

The compiler then verifies that the interface contains exactly one abstract method.

---

## 6. Is `@FunctionalInterface` Mandatory?

No.

This is still a valid functional interface:

```java
public interface Calculator {

    int calculate(int a, int b);
}
```

However, using:

```java
@FunctionalInterface
```

is recommended because it:

- Documents the developer's intention
- Provides compiler validation
- Prevents accidental addition of another abstract method

Recommended:

```java
@FunctionalInterface
public interface Calculator {

    int calculate(int a, int b);
}
```

---

## 7. What Happens If We Add Another Abstract Method?

Example:

```java
@FunctionalInterface
interface Calculator {

    int calculate(int a, int b);

    int multiply(int a, int b);
}
```

This causes a compilation error.

Why?

Because the interface now has:

```text
2 abstract methods
```

A functional interface can have only:

```text
1 abstract method
```

---

## 8. Default Methods Do Not Count

A functional interface can contain default methods.

Example:

```java
@FunctionalInterface
interface Calculator {

    int calculate(int a, int b);

    default void printMessage() {
        System.out.println("Calculator");
    }
}
```

This is valid.

Why?

Because:

```java
calculate()
```

is abstract.

But:

```java
printMessage()
```

is a default method with an implementation.

Therefore only one abstract method exists.

---

## 9. Static Methods Do Not Count

A functional interface can also contain static methods.

Example:

```java
@FunctionalInterface
interface Calculator {

    int calculate(int a, int b);

    static void info() {
        System.out.println("Calculator Interface");
    }
}
```

This is valid.

The static method is not an abstract method.

---

## 10. Functional Interface Structure

A functional interface can contain:

```text
1 Abstract Method
+
Multiple Default Methods
+
Multiple Static Methods
```

Example:

```java
@FunctionalInterface
interface Calculator {

    int calculate(int a, int b);

    default void printResult(int result) {
        System.out.println("Result: " + result);
    }

    static void info() {
        System.out.println("Calculator");
    }
}
```

This is valid because there is only one abstract method.

---

# 11. Three Ways to Implement a Functional Interface

A functional interface can be implemented using:

1. Normal class
2. Anonymous class
3. Lambda expression

---

## 12. Normal Class Implementation

```java
@FunctionalInterface
interface Calculator {

    int calculate(int a, int b);
}
```

Implementation:

```java
class AdditionCalculator implements Calculator {

    @Override
    public int calculate(int a, int b) {
        return a + b;
    }
}
```

Usage:

```java
Calculator calculator = new AdditionCalculator();

System.out.println(calculator.calculate(10, 5));
```

Output:

```text
15
```

---

## 13. Anonymous Class Implementation

Instead of creating a separate class:

```java
Calculator calculator = new Calculator() {

    @Override
    public int calculate(int a, int b) {
        return a + b;
    }
};
```

Usage:

```java
System.out.println(calculator.calculate(10, 5));
```

---

## 14. Lambda Implementation

With Java 8:

```java
Calculator calculator = (a, b) -> a + b;
```

This is much shorter than the anonymous class.

---

## 15. Functional Interface Comparison

### Normal Class

```java
class AdditionCalculator implements Calculator {

    @Override
    public int calculate(int a, int b) {
        return a + b;
    }
}
```

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

Lambda is generally preferred when the implementation is simple and the interface is functional.

---

# 16. Built-in Functional Interfaces

Java provides many functional interfaces in:

```java
java.util.function
```

The most important ones are:

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

---

# 17. Predicate

`Predicate<T>` represents a condition.

It accepts:

```text
1 input
```

and returns:

```text
boolean
```

Method:

```java
boolean test(T t);
```

Example:

```java
Predicate<Integer> isGreaterThan50 =
        number -> number > 50;
```

Usage:

```java
System.out.println(isGreaterThan50.test(75));
```

Output:

```text
true
```

Another example:

```java
System.out.println(isGreaterThan50.test(30));
```

Output:

```text
false
```

### Mental Model

```text
Predicate
    ↓
Question / Condition
    ↓
Input
    ↓
true / false
```

---

# 18. Function

`Function<T, R>` represents a transformation.

It accepts:

```text
1 input
```

and returns:

```text
1 output
```

Method:

```java
R apply(T t);
```

Example:

```java
Function<Integer, String> formatPrice =
        price -> "₹" + price;
```

Usage:

```java
System.out.println(formatPrice.apply(999));
```

Output:

```text
₹999
```

### Mental Model

```text
Function
    ↓
Transform
    ↓
Input
    ↓
Output
```

---

# 19. Consumer

`Consumer<T>` accepts an input and performs an action.

It returns:

```text
void
```

Method:

```java
void accept(T t);
```

Example:

```java
Consumer<String> printUserName =
        username -> System.out.println("Username: " + username);
```

Usage:

```java
printUserName.accept("Praveen");
```

Output:

```text
Username: Praveen
```

### Mental Model

```text
Consumer
    ↓
Action
    ↓
Input
    ↓
Nothing returned
```

---

# 20. Supplier

`Supplier<T>` provides a value without accepting any input.

Method:

```java
T get();
```

Example:

```java
Supplier<String> welcomeMessage =
        () -> "Welcome to Java Functional Interfaces";
```

Usage:

```java
System.out.println(welcomeMessage.get());
```

Output:

```text
Welcome to Java Functional Interfaces
```

### Mental Model

```text
Supplier
    ↓
Provide something
    ↓
No input
    ↓
Output
```

---

# 21. Predicate vs Function vs Consumer vs Supplier

| Interface | Input | Output | Method |
| --- | --- | --- | --- |
| Predicate<T> | 1 | boolean | `test()` |
| Function<T,R> | 1 | R | `apply()` |
| Consumer<T> | 1 | void | `accept()` |
| Supplier<T> | 0 | T | `get()` |

### Easy Memory Trick

```text
Predicate → Question
Function  → Transform
Consumer  → Consume
Supplier  → Supply
```

---

# 22. BiPredicate

`BiPredicate<T, U>` accepts two inputs and returns a boolean.

Method:

```java
boolean test(T t, U u);
```

Example:

```java
BiPredicate<Integer, Integer> isGreater =
        (a, b) -> a > b;
```

Usage:

```java
System.out.println(isGreater.test(20, 10));
```

Output:

```text
true
```

---

# 23. BiFunction

`BiFunction<T, U, R>` accepts two inputs and returns one result.

Method:

```java
R apply(T t, U u);
```

Example:

```java
BiFunction<Integer, Integer, Integer> addition =
        (a, b) -> a + b;
```

Usage:

```java
System.out.println(addition.apply(20, 5));
```

Output:

```text
25
```

### Mental Model

```text
BiFunction
     ↓
Input 1
Input 2
     ↓
Output
```

---

# 24. UnaryOperator

`UnaryOperator<T>` accepts one value and returns the same type.

It is a specialized form of `Function<T, T>`.

Example:

```java
UnaryOperator<Integer> square =
        number -> number * number;
```

Usage:

```java
System.out.println(square.apply(5));
```

Output:

```text
25
```

Conceptually:

```java
Function<Integer, Integer>
```

and:

```java
UnaryOperator<Integer>
```

both represent:

```text
Integer → Integer
```

---

# 25. BinaryOperator

`BinaryOperator<T>` accepts two values of the same type and returns the same type.

It is a specialized form of:

```java
BiFunction<T, T, T>
```

Example:

```java
BinaryOperator<Integer> addition =
        (a, b) -> a + b;
```

Usage:

```java
System.out.println(addition.apply(10, 20));
```

Output:

```text
30
```

### Mental Model

```text
T + T
 ↓
T
```

---

# 26. Functional Interface Hierarchy

A useful way to understand the interfaces:

```text
Predicate<T>
    Input → boolean

Function<T, R>
    Input → Output

Consumer<T>
    Input → void

Supplier<T>
    No Input → Output
```

Two-input versions:

```text
BiPredicate<T, U>
    Input + Input → boolean

BiFunction<T, U, R>
    Input + Input → Output
```

Same-type specialized versions:

```text
UnaryOperator<T>
    T → T

BinaryOperator<T>
    T + T → T
```

---

# 27. Real-World Example — User Validation

Suppose an application needs to check whether a user is active.

Use:

```java
Predicate<User> isActive =
        user -> user.isActive();
```

Now:

```java
if (isActive.test(user)) {
    System.out.println("User is active");
}
```

The Predicate represents a condition.

---

# 28. Real-World Example — Data Transformation

Suppose we need to convert a username into uppercase.

```java
Function<String, String> uppercase =
        username -> username.toUpperCase();
```

Usage:

```java
String result = uppercase.apply("praveen");

System.out.println(result);
```

Output:

```text
PRAVEEN
```

---

# 29. Real-World Example — Logging

Use `Consumer` when we want to perform an action.

```java
Consumer<String> logger =
        message -> System.out.println("LOG: " + message);
```

Usage:

```java
logger.accept("User logged in");
```

Output:

```text
LOG: User logged in
```

---

# 30. Real-World Example — Generating Values

Use `Supplier` when a value needs to be provided.

```java
Supplier<String> orderId =
        () -> "ORD-" + System.currentTimeMillis();
```

Usage:

```java
System.out.println(orderId.get());
```

The Supplier does not require input.

---

# 31. Custom Functional Interface vs Built-in Interface

Suppose we create:

```java
@FunctionalInterface
interface Calculator {

    int calculate(int a, int b);
}
```

We can often replace it with:

```java
BiFunction<Integer, Integer, Integer>
```

Example:

```java
BiFunction<Integer, Integer, Integer> addition =
        (a, b) -> a + b;
```

### When should we create a custom interface?

Use a custom functional interface when:

- The business meaning is important
- The name improves readability
- The behavior is specific to the application
- A built-in interface does not clearly represent the operation

Example:

```java
@FunctionalInterface
interface PriceCalculator {

    double calculate(double price);
}
```

This can communicate business intent better than a generic `Function<Double, Double>`.

---

# 32. Functional Interfaces and Stream API

Functional interfaces are heavily used by the Stream API.

For example:

```java
List<Integer> numbers = List.of(10, 20, 30, 40, 50);
```

Filtering:

```java
numbers.stream()
       .filter(number -> number > 20)
       .forEach(System.out::println);
```

Here:

```java
filter()
```

accepts a:

```java
Predicate<T>
```

And:

```java
forEach()
```

accepts a:

```java
Consumer<T>
```

This is why understanding functional interfaces is important before learning Streams.

---

# 33. Functional Interface and Method References

Functional interfaces can also be used with method references.

Example:

```java
Consumer<String> printer =
        System.out::println;
```

This is equivalent to:

```java
Consumer<String> printer =
        text -> System.out.println(text);
```

The method reference is simply a shorter form when an existing method already matches the functional interface.

---

# 34. Common Mistakes

### Mistake 1 — Thinking one method total is required

A functional interface can have:

```text
1 abstract method
+
multiple default methods
+
multiple static methods
```

Only abstract methods matter.

---

### Mistake 2 — Adding two abstract methods

Invalid:

```java
@FunctionalInterface
interface Calculator {

    int add(int a, int b);

    int subtract(int a, int b);
}
```

There are two abstract methods.

Therefore it is not a functional interface.

---

### Mistake 3 — Thinking `@FunctionalInterface` is mandatory

This is still valid:

```java
interface Calculator {

    int calculate(int a, int b);
}
```

The annotation is recommended but not mandatory.

---

### Mistake 4 — Confusing Predicate and Function

Predicate:

```java
Predicate<Integer> isAdult =
        age -> age >= 18;
```

Returns:

```text
boolean
```

Function:

```java
Function<Integer, String> format =
        age -> "Age: " + age;
```

Returns:

```text
String
```

---

### Mistake 5 — Confusing Consumer and Supplier

Consumer:

```java
Consumer<String> printer =
        value -> System.out.println(value);
```

Input:

```text
String
```

Output:

```text
void
```

Supplier:

```java
Supplier<String> message =
        () -> "Hello";
```

Input:

```text
none
```

Output:

```text
String
```

---

### Mistake 6 — Confusing Function and UnaryOperator

Function:

```java
Function<Integer, String>
```

Can have different input and output types.

```text
Integer → String
```

UnaryOperator:

```java
UnaryOperator<Integer>
```

Must have the same input and output type.

```text
Integer → Integer
```

---

# 35. Interview Questions

## Basic

### Q1. What is a Functional Interface?

A functional interface is an interface that contains exactly one abstract method.

---

### Q2. What is SAM?

SAM stands for:

**Single Abstract Method.**

It means the functional interface contains exactly one abstract method.

---

### Q3. What is `@FunctionalInterface`?

It is an annotation used to indicate that an interface is intended to be a functional interface.

The compiler validates that it contains only one abstract method.

---

### Q4. Is `@FunctionalInterface` mandatory?

No.

It is optional, but recommended.

---

### Q5. Can a functional interface contain default methods?

Yes.

Default methods do not count as abstract methods.

---

### Q6. Can a functional interface contain static methods?

Yes.

Static methods also do not count as abstract methods.

---

## Intermediate

### Q7. Why are functional interfaces important in Java 8?

They provide the target type for Lambda expressions and are heavily used by functional programming features such as the Stream API.

---

### Q8. Name some built-in functional interfaces.

Common examples are:

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

---

### Q9. What is the difference between Predicate and Function?

`Predicate<T>` takes an input and returns a boolean.

```java
Predicate<Integer> check =
        number -> number > 50;
```

`Function<T, R>` takes an input and returns a result.

```java
Function<Integer, String> convert =
        number -> "Number: " + number;
```

---

### Q10. What is the difference between Consumer and Supplier?

Consumer:

```text
Input → void
```

Supplier:

```text
No input → Output
```

---

### Q11. What is the difference between Function and UnaryOperator?

Function:

```text
T → R
```

Input and output types can be different.

UnaryOperator:

```text
T → T
```

Input and output must be the same type.

---

### Q12. What is the difference between BiFunction and BinaryOperator?

BiFunction:

```text
T + U → R
```

BinaryOperator:

```text
T + T → T
```

BinaryOperator is a specialized form of BiFunction.

---

# 36. Advanced Interview Questions

### Q13. Can a functional interface extend another interface?

Yes, as long as the resulting interface still has exactly one abstract method.

Example:

```java
@FunctionalInterface
interface Parent {

    void execute();
}
```

```java
@FunctionalInterface
interface Child extends Parent {

}
```

`Child` is still a functional interface because it inherits one abstract method.

---

### Q14. Can a functional interface have methods inherited from Object?

Yes.

Methods such as:

```java
toString()
equals()
hashCode()
```

do not cause the interface to stop being functional because methods matching public methods from `Object` are not counted as additional abstract methods for this purpose.

---

### Q15. Why can't an interface with two abstract methods be used with a Lambda?

Because the compiler would not know which abstract method the Lambda is supposed to implement.

Example:

```java
interface Calculator {

    int add(int a, int b);

    int subtract(int a, int b);
}
```

A Lambda like:

```java
(a, b) -> a + b
```

would be ambiguous.

---

# 37. Interview Explanation

If the interviewer asks:

### "What is a Functional Interface?"

A good answer:

> "A functional interface is an interface that contains exactly one abstract method. It is also called a Single Abstract Method or SAM interface. Functional interfaces are important in Java 8 because Lambda expressions use them as their target type. A functional interface can also contain multiple default and static methods because those methods are not abstract. Java provides built-in functional interfaces such as Predicate, Function, Consumer, Supplier, BiFunction, and BiPredicate in the `java.util.function` package."

---

# 38. Functional Interface Cheat Sheet

```text
Functional Interface
        ↓
Exactly ONE abstract method
        ↓
SAM
        ↓
Can be implemented using Lambda
        ↓
@FunctionalInterface → compiler validation
```

### Built-in Interfaces

```text
Predicate<T>
    T → boolean
    test()

Function<T, R>
    T → R
    apply()

Consumer<T>
    T → void
    accept()

Supplier<T>
    () → T
    get()
```

### Two-input Interfaces

```text
BiPredicate<T, U>
    T + U → boolean
    test()

BiFunction<T, U, R>
    T + U → R
    apply()
```

### Same-type Interfaces

```text
UnaryOperator<T>
    T → T
    apply()

BinaryOperator<T>
    T + T → T
    apply()
```

---

# 39. Quick Memory Trick

```text
Predicate
    ↓
Question
    ↓
true / false

Function
    ↓
Transform
    ↓
new value

Consumer
    ↓
Action
    ↓
nothing returned

Supplier
    ↓
Provide
    ↓
value returned
```

Remember:

```text
Predicate → test()
Function  → apply()
Consumer  → accept()
Supplier  → get()
```

---

# 40. Complete Example

```java
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaceExample {

    public static void main(String[] args) {

        Predicate<Integer> isGreaterThan50 =
                number -> number > 50;

        System.out.println(
                isGreaterThan50.test(75)
        );

        Function<Integer, String> formatPrice =
                price -> "₹" + price;

        System.out.println(
                formatPrice.apply(999)
        );

        Consumer<String> printUserName =
                username ->
                        System.out.println(
                                "Username: " + username
                        );

        printUserName.accept("Praveen");

        Supplier<String> welcomeMessage =
                () -> "Welcome to Java Functional Interfaces";

        System.out.println(
                welcomeMessage.get()
        );
    }
}
```

Output:

```text
true
₹999
Username: Praveen
Welcome to Java Functional Interfaces
```

---

# 41. Key Takeaways

```text
Functional Interface
        ↓
Exactly ONE abstract method
        ↓
Also called SAM
        ↓
Used as Lambda target type
        ↓
@FunctionalInterface validates the rule
        ↓
Can contain default methods
        ↓
Can contain static methods
        ↓
java.util.function provides common interfaces
        ↓
Predicate → boolean
Function → result
Consumer → action
Supplier → value
```

### Remember

**Functional Interface = One Abstract Method + Lambda Target Type**

**Predicate = Question**

**Function = Transformation**

**Consumer = Action**

**Supplier = Provider**

**BiFunction = Two Inputs → One Output**

**UnaryOperator = One Input → Same Type Output**

**BinaryOperator = Two Same-Type Inputs → Same Type Output**