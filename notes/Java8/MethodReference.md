# Method References in Java

## 1. What is a Method Reference?

A **Method Reference** is a shorter and cleaner way to write a Lambda Expression when the Lambda only calls an existing method.

It uses the `::` operator.

### Lambda Expression

```java
name -> System.out.println(name)
```

### Method Reference

```java
System.out::println
```

Both represent the same behavior.

---

# 2. Why Method References?

Method References help make Java code:

- Shorter
- Cleaner
- More readable
- Easier to understand
- More functional-programming oriented

They are especially common with:

- Collections
- Streams
- Functional Interfaces
- Event handling
- Data processing

### Example

Instead of:

```java
names.forEach(name -> System.out.println(name));
```

We can write:

```java
names.forEach(System.out::println);
```

---

# 3. Method Reference Operator

Method References use:

```java
::
```

Example:

```java
System.out::println
```

Think of `::` as:

> "Use this existing method as the behavior."

---

# 4. Method Reference Syntax

There are four main types of Method References.

| Type | Syntax | Meaning |
|---|---|---|
| Static Method | `ClassName::staticMethod` | Reference to a static method |
| Particular Object | `object::instanceMethod` | Reference to a method of a specific object |
| Arbitrary Object | `ClassName::instanceMethod` | Reference to an instance method where the object comes later |
| Constructor | `ClassName::new` | Reference to a constructor |

### Mental Model

```text
ClassName::staticMethod
        ↓
Static Method


object::instanceMethod
        ↓
Specific Object


ClassName::instanceMethod
        ↓
Object Comes Later


ClassName::new
        ↓
Constructor
```

---

# 5. Method References Need a Target Type

A Method Reference does not have an independent type.

It needs a **target type**, usually a Functional Interface.

Example:

```java
Function<String, Integer> converter = Integer::parseInt;
```

Here:

```text
Function<String, Integer>
        ↓
Input  : String
Output : Integer
        ↓
Integer::parseInt
```

The compiler uses the Functional Interface's method signature to understand the Method Reference.

---

# 6. Type 1 — Static Method Reference

## Syntax

```java
ClassName::staticMethod
```

Used when the referenced method is `static`.

---

## Lambda

```java
Function<String, Integer> converter =
        value -> Integer.parseInt(value);
```

## Method Reference

```java
Function<String, Integer> converter =
        Integer::parseInt;
```

Both represent:

```java
String -> Integer
```

---

## Example

```java
import java.util.function.Function;

public class Main {

    public static void main(String[] args) {

        Function<String, Integer> converter =
                Integer::parseInt;

        System.out.println(converter.apply("100"));
        System.out.println(converter.apply("500"));
    }
}
```

Output:

```text
100
500
```

---

# 7. Custom Static Method Reference

Consider:

```java
public class MathUtils {

    public static int add(int a, int b) {
        return a + b;
    }
}
```

### Lambda

```java
BiFunction<Integer, Integer, Integer> addition =
        (a, b) -> MathUtils.add(a, b);
```

### Method Reference

```java
BiFunction<Integer, Integer, Integer> addition =
        MathUtils::add;
```

Usage:

```java
int result = addition.apply(20, 5);

System.out.println(result);
```

Output:

```text
25
```

### Key Point

The Lambda:

```java
(a, b) -> MathUtils.add(a, b)
```

simply forwards its arguments to an existing static method.

Therefore it can become:

```java
MathUtils::add
```

---

# 8. Type 2 — Instance Method Reference of a Particular Object

## Syntax

```java
object::instanceMethod
```

This is used when a **specific object already exists**.

---

## Example

```java
public class MessageService {

    public void printMessage(String message) {
        System.out.println(message);
    }
}
```

Create an object:

```java
MessageService service = new MessageService();
```

### Lambda

```java
Consumer<String> printer =
        message -> service.printMessage(message);
```

### Method Reference

```java
Consumer<String> printer =
        service::printMessage;
```

Usage:

```java
printer.accept("Order placed successfully");
```

Output:

```text
Order placed successfully
```

---

## What is happening?

This:

```java
service::printMessage
```

means:

> Use the `printMessage()` method of this particular `service` object.

### Mental Model

```text
service
   ↓
printMessage()
```

The object is already known.

---

# 9. Type 3 — Instance Method Reference of an Arbitrary Object

## Syntax

```java
ClassName::instanceMethod
```

This is different from:

```java
object::instanceMethod
```

The object is **not known when the Method Reference is created**.

The object will be supplied later through the Functional Interface.

---

## Example

### Lambda

```java
Function<String, String> uppercase =
        text -> text.toUpperCase();
```

### Method Reference

```java
Function<String, String> uppercase =
        String::toUpperCase;
```

Usage:

```java
System.out.println(uppercase.apply("java"));
```

Output:

```text
JAVA
```

---

# 10. How Arbitrary Object Method References Work

Consider:

```java
Function<String, String> uppercase =
        String::toUpperCase;
```

Equivalent Lambda:

```java
text -> text.toUpperCase()
```

Here `text` becomes the object on which `toUpperCase()` is called.

Conceptually:

```text
Function.apply("java")
        ↓
"java".toUpperCase()
        ↓
"JAVA"
```

So:

```java
String::toUpperCase
```

means:

> Take a String object later and call `toUpperCase()` on it.

---

# 11. Another Arbitrary Object Example

### Lambda

```java
Function<String, Integer> length =
        text -> text.length();
```

### Method Reference

```java
Function<String, Integer> length =
        String::length;
```

Usage:

```java
System.out.println(length.apply("Spring Boot"));
```

Output:

```text
11
```

The input String becomes the object on which `length()` is invoked.

---

# 12. Particular Object vs Arbitrary Object

This is an important interview concept.

## Particular Object

```java
MessageService service = new MessageService();

Consumer<String> printer =
        service::printMessage;
```

Equivalent Lambda:

```java
message -> service.printMessage(message)
```

The object is already known.

```text
service
   ↓
printMessage()
```

---

## Arbitrary Object

```java
Function<String, String> uppercase =
        String::toUpperCase;
```

Equivalent Lambda:

```java
text -> text.toUpperCase()
```

The object comes later.

```text
String input
     ↓
toUpperCase()
```

### Easy Way to Remember

```text
object::method
        ↓
Specific object already exists


ClassName::method
        ↓
Object comes later
```

---

# 13. Type 4 — Constructor Reference

## Syntax

```java
ClassName::new
```

Used when a Lambda simply creates an object.

---

## Example Class

```java
public class User {

    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```

### Lambda

```java
Function<String, User> userCreator =
        name -> new User(name);
```

### Constructor Reference

```java
Function<String, User> userCreator =
        User::new;
```

Usage:

```java
User user = userCreator.apply("Praveen");

System.out.println(user.getName());
```

Output:

```text
Praveen
```

---

# 14. Constructor Reference with No Arguments

Suppose the class has a no-argument constructor:

```java
public class User {

    public User() {
    }
}
```

### Lambda

```java
Supplier<User> userCreator =
        () -> new User();
```

### Constructor Reference

```java
Supplier<User> userCreator =
        User::new;
```

Usage:

```java
User user = userCreator.get();
```

---

# 15. Constructor Parameters Must Match

Consider:

```java
public class User {

    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

We can use:

```java
BiFunction<String, Integer, User> userCreator =
        User::new;
```

Equivalent Lambda:

```java
BiFunction<String, Integer, User> userCreator =
        (name, age) -> new User(name, age);
```

The Functional Interface parameters must match a valid constructor.

---

# 16. Method Reference with Collections

Method References are frequently used with Collections.

Example:

```java
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<String> names =
                Arrays.asList("Praveen", "Kowsalya", "Rahul");

        names.forEach(System.out::println);
    }
}
```

Output:

```text
Praveen
Kowsalya
Rahul
```

Equivalent Lambda:

```java
names.forEach(name -> System.out.println(name));
```

---

# 17. Method Reference with Sorting

Consider:

```java
List<String> names =
        Arrays.asList("Praveen", "Rahul", "Arun");
```

We can write:

```java
names.sort(String::compareTo);
```

This is equivalent to:

```java
names.sort((a, b) -> a.compareTo(b));
```

---

# 18. Method Reference Compatibility

A Method Reference does not have to use the same method name as the Functional Interface method.

For example:

```java
Function<String, Integer> converter =
        Integer::parseInt;
```

The Functional Interface method is:

```java
apply()
```

The referenced method is:

```java
parseInt()
```

They have different names.

What matters is the **method signature compatibility**.

---

# 19. Method Reference and Functional Interface Signature

Consider:

```java
Function<String, Integer> converter =
        Integer::parseInt;
```

`Function` expects:

```java
R apply(T t);
```

So effectively:

```text
String → Integer
```

`Integer.parseInt()` provides:

```text
String → int
```

Java handles the compatible return type through boxing:

```text
int → Integer
```

Therefore the Method Reference is valid.

---

# 20. Method Reference Does Not Execute Immediately

Consider:

```java
Consumer<String> printer =
        System.out::println;
```

Nothing is printed here.

The Method Reference only represents behavior.

The actual method executes when:

```java
printer.accept("Hello");
```

is called.

Output:

```text
Hello
```

### Mental Model

```text
Method Reference
       ↓
Represents behavior
       ↓
Functional Interface
       ↓
Method invocation
       ↓
Actual execution
```

---

# 21. Real-Time Example — E-Commerce Order Processing

Imagine an e-commerce application.

The system needs to:

1. Parse an order ID
2. Create an Order
3. Extract customer information
4. Process and print the order

We can use all four types of Method References.

---

## Order Class

```java
public class Order {

    private String customerName;

    public Order(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }
}
```

---

## OrderService

```java
public class OrderService {

    public void printOrder(Order order) {

        System.out.println(
                "Processing order for: "
                        + order.getCustomerName()
        );
    }
}
```

---

## Complete Example

```java
import java.util.function.Consumer;
import java.util.function.Function;

public class OrderProcessing {

    public static void main(String[] args) {

        // 1. Static Method Reference
        Function<String, Integer> orderIdParser =
                Integer::parseInt;

        int orderId =
                orderIdParser.apply("1001");

        System.out.println(
                "Order ID: " + orderId
        );


        // 2. Particular Object Instance Method Reference
        OrderService orderService =
                new OrderService();

        Consumer<Order> orderPrinter =
                orderService::printOrder;


        // 3. Arbitrary Object Instance Method Reference
        Function<Order, String> customerNameExtractor =
                Order::getCustomerName;


        // 4. Constructor Reference
        Function<String, Order> orderCreator =
                Order::new;


        // Create Order
        Order order =
                orderCreator.apply("Praveen");


        // Extract customer name
        String customerName =
                customerNameExtractor.apply(order);

        System.out.println(
                "Customer: " + customerName
        );


        // Process order
        orderPrinter.accept(order);
    }
}
```

Output:

```text
Order ID: 1001
Customer: Praveen
Processing order for: Praveen
```

---

# 22. Understanding All Four Types in the Real-Time Example

## 1. Static Method Reference

```java
Integer::parseInt
```

Equivalent Lambda:

```java
value -> Integer.parseInt(value)
```

Pattern:

```text
ClassName::staticMethod
```

---

## 2. Particular Object Method Reference

```java
orderService::printOrder
```

Equivalent Lambda:

```java
order -> orderService.printOrder(order)
```

Pattern:

```text
object::instanceMethod
```

The `orderService` object is already known.

---

## 3. Arbitrary Object Method Reference

```java
Order::getCustomerName
```

Equivalent Lambda:

```java
order -> order.getCustomerName()
```

Pattern:

```text
ClassName::instanceMethod
```

The `Order` object comes later.

---

## 4. Constructor Reference

```java
Order::new
```

Equivalent Lambda:

```java
name -> new Order(name)
```

Pattern:

```text
ClassName::new
```

---

# 23. The Most Important Conversion Patterns

### Pattern 1 — Static Method

Lambda:

```java
x -> ClassName.method(x)
```

Method Reference:

```java
ClassName::method
```

Example:

```java
value -> Integer.parseInt(value)
```

becomes:

```java
Integer::parseInt
```

---

### Pattern 2 — Specific Object

Lambda:

```java
x -> object.method(x)
```

Method Reference:

```java
object::method
```

Example:

```java
message -> service.printMessage(message)
```

becomes:

```java
service::printMessage
```

---

### Pattern 3 — Object Becomes the Receiver

Lambda:

```java
x -> x.method()
```

Method Reference:

```java
ClassName::method
```

Example:

```java
text -> text.toUpperCase()
```

becomes:

```java
String::toUpperCase
```

---

### Pattern 4 — Constructor

Lambda:

```java
x -> new ClassName(x)
```

Method Reference:

```java
ClassName::new
```

Example:

```java
name -> new User(name)
```

becomes:

```java
User::new
```

---

# 24. When Can a Lambda Be Replaced with a Method Reference?

Ask yourself:

> Is the Lambda only calling an existing method?

If yes, a Method Reference may be possible.

### Example

```java
name -> System.out.println(name)
```

Yes.

Use:

```java
System.out::println
```

---

### Example

```java
text -> text.toUpperCase()
```

Yes.

Use:

```java
String::toUpperCase
```

---

### Example

```java
name -> new User(name)
```

Yes.

Use:

```java
User::new
```

---

# 25. When Should You Keep the Lambda?

Not every Lambda can or should be converted into a Method Reference.

Consider:

```java
number -> number * 2
```

There is no existing method being directly called.

So keep:

```java
number -> number * 2
```

Another example:

```java
name -> {
    String formattedName = name.trim();
    System.out.println(formattedName);
}
```

This contains multiple operations.

A Method Reference is not a direct replacement.

---

# 26. Common Mistakes

## Mistake 1 — Calling the Method

Incorrect:

```java
System.out::println("Hello");
```

Correct:

```java
System.out::println
```

A Method Reference does not use `()`.

---

## Mistake 2 — Parentheses After Method Reference

Incorrect:

```java
String::toUpperCase()
```

Correct:

```java
String::toUpperCase
```

---

## Mistake 3 — Trying to Store Without a Target Type

Incorrect:

```java
var reference = String::toUpperCase;
```

A Method Reference needs a target type.

Correct:

```java
Function<String, String> reference =
        String::toUpperCase;
```

---

## Mistake 4 — Confusing Object and Class

These are different:

```java
service::printMessage
```

and:

```java
MessageService::printMessage
```

The first refers to a **specific object**.

The second refers to an **instance method on an arbitrary object**, when the signature allows it.

---

## Mistake 5 — Thinking Method Reference Executes Immediately

This:

```java
Consumer<String> printer =
        System.out::println;
```

does not print anything.

The method executes only when:

```java
printer.accept("Hello");
```

is called.

---

# 27. Method References vs Lambda Expressions

| Lambda | Method Reference |
|---|---|
| More explicit | More concise |
| Can contain custom logic | Usually directly delegates to an existing method |
| Can have multiple statements | Represents an existing method |
| Uses `->` | Uses `::` |
| Can transform/modify logic | Reuses existing behavior |

### Example

Lambda:

```java
name -> System.out.println(name)
```

Method Reference:

```java
System.out::println
```

---

# 28. Method References vs Functional Interfaces

A Method Reference is **not** a Functional Interface.

Instead:

```text
Functional Interface
       ↓
Provides target type


Method Reference
       ↓
Provides implementation/behavior
```

Example:

```java
Consumer<String> printer =
        System.out::println;
```

Here:

```text
Consumer<String>
       ↓
Functional Interface


System.out::println
       ↓
Method Reference
```

---

# 29. Method References vs Lambda vs Method

These three are different concepts.

### Existing Method

```java
System.out.println("Hello");
```

This executes the method immediately.

### Lambda

```java
() -> System.out.println("Hello")
```

This represents behavior.

### Method Reference

```java
System.out::println
```

This also represents behavior.

The Functional Interface determines how the referenced method is used.

---

# 30. Interview Questions

## Q1. What is a Method Reference?

A Method Reference is a shorthand syntax for a Lambda Expression when the Lambda simply calls an existing method.

It uses the `::` operator.

Example:

```java
System.out::println
```

---

## Q2. What are the four types of Method References?

1. Static method reference
2. Instance method reference of a particular object
3. Instance method reference of an arbitrary object
4. Constructor reference

---

## Q3. What is the syntax for a static Method Reference?

```java
ClassName::staticMethod
```

Example:

```java
Integer::parseInt
```

---

## Q4. What is the difference between `object::method` and `ClassName::method`?

```java
object::method
```

refers to a method of a **specific object**.

```java
ClassName::method
```

can refer to an instance method where the object is supplied later.

Example:

```java
service::printMessage
```

versus:

```java
String::toUpperCase
```

---

## Q5. What is a Constructor Reference?

A Constructor Reference is a shorthand for a Lambda that creates an object.

Syntax:

```java
ClassName::new
```

Example:

```java
Function<String, User> creator =
        User::new;
```

Equivalent:

```java
Function<String, User> creator =
        name -> new User(name);
```

---

## Q6. Does a Method Reference execute the method immediately?

No.

It represents a reference to the method.

The method executes when the Functional Interface method is invoked.

---

## Q7. Can a Method Reference exist without a Functional Interface?

In normal Java usage, a Method Reference requires a target type.

Example:

```java
Function<String, Integer> parser =
        Integer::parseInt;
```

The Functional Interface provides the target type.

---

## Q8. Can every Lambda be converted into a Method Reference?

No.

Only when the Lambda directly delegates to an existing compatible method.

Example:

```java
x -> x.toUpperCase()
```

can become:

```java
String::toUpperCase
```

But:

```java
x -> x * 2
```

does not have a direct existing method to reference.

---

## Q9. Does the method name have to match the Functional Interface method name?

No.

Example:

```java
Function<String, Integer> parser =
        Integer::parseInt;
```

The Functional Interface method is:

```java
apply()
```

The referenced method is:

```java
parseInt()
```

Compatibility depends on the method signature, not the method name.

---

## Q10. What operator is used for Method References?

```java
::
```

---

# 31. Interview Explanation

If an interviewer asks:

> "Explain Method References in Java."

You can answer:

> Method Reference is a shorthand syntax for a Lambda Expression when the Lambda simply delegates to an existing method. It uses the `::` operator and requires a target type such as a Functional Interface. Java supports four main types: static method references using `ClassName::staticMethod`, instance methods of a particular object using `object::instanceMethod`, instance methods of an arbitrary object using `ClassName::instanceMethod`, and constructor references using `ClassName::new`. Method References are commonly used with Collections and the Stream API to make code more concise and readable.

---

# 32. Quick Cheat Sheet

```text
====================================================
             METHOD REFERENCES CHEAT SHEET
====================================================

Operator:

    ::


1. Static Method
----------------

    ClassName::staticMethod

Lambda:

    x -> ClassName.method(x)

Example:

    Integer::parseInt


2. Particular Object
--------------------

    object::instanceMethod

Lambda:

    x -> object.method(x)

Example:

    service::printMessage


3. Arbitrary Object
-------------------

    ClassName::instanceMethod

Lambda:

    x -> x.method()

Example:

    String::toUpperCase


4. Constructor
--------------

    ClassName::new

Lambda:

    x -> new ClassName(x)

Example:

    User::new


====================================================
```

---

# 33. Four Types — One-Line Memory Trick

```text
Class::staticMethod  → Static method

object::method       → Specific object

Class::method        → Object comes later

Class::new            → Constructor
```

Remember:

```text
STATIC       → Class
PARTICULAR   → Object
ARBITRARY    → Class
CONSTRUCTOR  → new
```

---

# 34. Lambda to Method Reference Conversion Checklist

When you see a Lambda, ask:

### Step 1

Is it simply calling an existing method?

```java
x -> something.method(x)
```

### Step 2

Is the method static?

```java
ClassName.method(x)
```

Then:

```java
ClassName::method
```

### Step 3

Is a specific object being used?

```java
object.method(x)
```

Then:

```java
object::method
```

### Step 4

Is the Lambda calling a method on its own parameter?

```java
x -> x.method()
```

Then:

```java
ClassName::method
```

### Step 5

Is it creating an object?

```java
x -> new ClassName(x)
```

Then:

```java
ClassName::new
```

---

# 35. Method Reference Mental Model

```text
                 METHOD REFERENCE
                         |
              Uses the :: operator
                         |
       +-----------------+------------------+
       |                 |                  |
       ↓                 ↓                  ↓
    Static          Instance Method     Constructor
       |                 |                  |
       ↓                 ↓                  ↓
ClassName::method   object::method      ClassName::new
                         |
                         |
                 ClassName::method
                         |
                  Arbitrary Object
```

---

# 36. Key Takeaways

- Method Reference is a shorthand for certain Lambda Expressions.
- It uses the `::` operator.
- It requires a target type, usually a Functional Interface.
- There are four main types:
    - Static method
    - Particular object instance method
    - Arbitrary object instance method
    - Constructor
- `object::method` means a specific object is already known.
- `ClassName::method` for an instance method means the object can come later.
- `ClassName::new` references a constructor.
- Method References do not execute methods immediately.
- Method Reference compatibility depends on the Functional Interface method signature.
- They are heavily used with Collections and the Stream API.
- Not every Lambda can be converted into a Method Reference.
- Method References make code shorter and often more readable.

---

# 37. Final Memory Phrase

```text
METHOD REFERENCES = REUSE EXISTING METHODS CLEANLY

:: means "use this existing behavior"

Class::staticMethod  → Static
object::method       → Specific Object
Class::method        → Object Later
Class::new           → Constructor
```

### The most important conversion patterns:

```java
x -> ClassName.method(x)
        ↓
ClassName::method
```

```java
x -> object.method(x)
        ↓
object::method
```

```java
x -> x.method()
        ↓
ClassName::method
```

```java
x -> new ClassName(x)
        ↓
ClassName::new
```

Once these four patterns become familiar, Method References become very easy to recognize in real Java code.