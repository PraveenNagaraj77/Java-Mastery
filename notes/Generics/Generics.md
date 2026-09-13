# Java Generics

## 1. What is Generics?

Generics allow us to write classes, interfaces, methods, and collections that work with different data types while maintaining **compile-time type safety**.

Instead of writing separate code for every data type, we use a type parameter such as:

```java
<T>
```

Example:

```java
List<String> names = new ArrayList<>();
```

Here:

```text
String → actual type
```

Generics tell the compiler what type of data is allowed.

---

# 2. Why Do We Need Generics?

Generics solve several important problems.

## 2.1 Compile-Time Type Safety

Without generics:

```java
List list = new ArrayList();

list.add("Java");
list.add(100);
```

Different types can be added to the same collection.

With generics:

```java
List<String> names = new ArrayList<>();

names.add("Java");

// names.add(100); // Compile-time error
```

The compiler prevents invalid data.

---

## 2.2 Avoid Explicit Casting

Without generics:

```java
List list = new ArrayList();

list.add("Java");

String value = (String) list.get(0);
```

We need explicit casting.

With generics:

```java
List<String> list = new ArrayList<>();

list.add("Java");

String value = list.get(0);
```

No explicit cast is required.

---

## 2.3 Code Reusability

Without generics, we might create:

```text
StringBox
IntegerBox
DoubleBox
```

With generics:

```java
Box<T>
```

The same class can work with:

```java
Box<String>
Box<Integer>
Box<Double>
```

---

## 2.4 Cleaner Code

Consider:

```java
Map<String, Integer> productStock = new HashMap<>();
```

We immediately know:

```text
Key   → String
Value → Integer
```

Generics make the expected data types clear.

---

# 3. How Generics Work Internally

Generics primarily provide type information during **compile time**.

Example:

```java
List<String> names = new ArrayList<>();

names.add("Praveen");

String name = names.get(0);
```

The compiler knows:

```text
List<String>
     ↓
   get()
     ↓
  String
```

Therefore the programmer does not need to manually cast the result.

Java implements generics using a mechanism called **type erasure**.

Generic type information is largely erased at runtime.

Conceptually:

```java
class Box<T> {

    private T value;

    public T getValue() {
        return value;
    }
}
```

is represented approximately like:

```java
class Box {

    private Object value;

    public Object getValue() {
        return value;
    }
}
```

The compiler inserts necessary casts where required.

---

# 4. Generic Type Parameters

A type parameter is a placeholder for a type.

Example:

```java
class Box<T> {
}
```

Here:

```text
T → Type Parameter
```

When we create:

```java
Box<String> box = new Box<>();
```

`T` becomes:

```text
String
```

When we create:

```java
Box<Integer> box = new Box<>();
```

`T` becomes:

```text
Integer
```

---

# 5. Common Generic Type Parameter Names

These are common Java naming conventions:

| Parameter | Meaning |
|---|---|
| `T` | Type |
| `E` | Element |
| `K` | Key |
| `V` | Value |
| `N` | Number |
| `R` | Result |

Examples:

```java
class Box<T>
```

```java
interface Repository<T>
```

```java
Map<K, V>
```

```java
List<E>
```

These are conventions, not reserved keywords.

---

# 6. Generic Classes

A generic class allows the same class to work with different types.

## Syntax

```java
class Box<T> {

    private T value;

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
```

## Example

```java
Box<String> stringBox = new Box<>();

stringBox.setValue("Laptop");

System.out.println(stringBox.getValue());
```

Output:

```text
Laptop
```

Another type:

```java
Box<Integer> integerBox = new Box<>();

integerBox.setValue(100);

System.out.println(integerBox.getValue());
```

Output:

```text
100
```

The same class works with different types.

---

# 7. Generic Methods

A generic method has its own type parameter.

## Syntax

```java
static <T> T methodName(T value) {
    return value;
}
```

Important:

```java
<T>
```

comes **before the return type**.

## Example

```java
static <T> T returnValue(T value) {
    return value;
}
```

Usage:

```java
returnValue(100);
returnValue("Praveen");
returnValue('C');
returnValue(true);
```

Java infers:

```text
100       → Integer
"Praveen" → String
'C'       → Character
true      → Boolean
```

---

## Multiple Type Parameters

A generic method can have multiple type parameters.

```java
static <K, V> void printPair(K key, V value) {
    System.out.println(key + " = " + value);
}
```

Usage:

```java
printPair("Product", "Laptop");

printPair(101, "Praveen");
```

Type inference:

```text
printPair("Product", "Laptop")

K = String
V = String
```

```text
printPair(101, "Praveen")

K = Integer
V = String
```

---

# 8. Generic Interfaces

Interfaces can also use generics.

```java
interface Repository<T> {

    void save(T value);

    T find();
}
```

Generic implementation:

```java
class GenericRepository<T> implements Repository<T> {

    private T value;

    @Override
    public void save(T value) {
        this.value = value;
    }

    @Override
    public T find() {
        return value;
    }
}
```

Usage:

```java
GenericRepository<String> repository = new GenericRepository<>();

repository.save("Laptop");

System.out.println(repository.find());
```

Output:

```text
Laptop
```

Another example:

```java
GenericRepository<Integer> repository = new GenericRepository<>();

repository.save(100);

System.out.println(repository.find());
```

Output:

```text
100
```

---

# 9. Generic Collections

Generics are heavily used with the Java Collections Framework.

Examples:

```java
List<String> names = new ArrayList<>();

Set<Integer> numbers = new HashSet<>();

Map<String, Integer> productStock = new HashMap<>();

Queue<String> queue = new ArrayDeque<>();
```

## Generic Map Example

```java
Map<String, Integer> productStock = new HashMap<>();

productStock.put("Laptop", 20);
productStock.put("Keyboard", 50);
productStock.put("Mouse", 100);
```

The compiler knows:

```text
Key   → String
Value → Integer
```

Therefore:

```java
// productStock.put(100, "Printer"); // Compile-time error
```

Correct:

```java
productStock.put("Printer", 100);
```

---

# 10. Diamond Operator

Java can infer the generic type using the diamond operator:

```java
<>
```

Instead of:

```java
List<String> names = new ArrayList<String>();
```

we normally write:

```java
List<String> names = new ArrayList<>();
```

The compiler infers:

```text
ArrayList<String>
```

from:

```java
List<String>
```

---

# 11. Generics and Primitive Types

Generics work with **reference types**, not primitive types.

Invalid:

```java
List<int> numbers; // ❌
```

Correct:

```java
List<Integer> numbers; // ✅
```

Use wrapper classes.

| Primitive | Wrapper |
|---|---|
| `int` | `Integer` |
| `long` | `Long` |
| `double` | `Double` |
| `float` | `Float` |
| `char` | `Character` |
| `boolean` | `Boolean` |
| `short` | `Short` |
| `byte` | `Byte` |

## Autoboxing

Java automatically converts primitive values to wrapper objects.

```java
List<Integer> numbers = new ArrayList<>();

numbers.add(10);
```

Conceptually:

```java
numbers.add(Integer.valueOf(10));
```

This is called **autoboxing**.

Unboxing converts a wrapper back to a primitive:

```java
Integer number = 100;

int value = number;
```

---

# 12. Bounded Type Parameters

Sometimes we want to restrict which types can be used.

We can use:

```java
extends
```

with a generic type parameter.

Syntax:

```java
<T extends Number>
```

This means:

```text
T must be Number or a subclass of Number
```

## Example

```java
static <T extends Number> double convertToDouble(T value) {
    return value.doubleValue();
}
```

Valid:

```java
convertToDouble(100);
convertToDouble(25.5);
convertToDouble(500L);
```

Invalid:

```java
// convertToDouble("100");
```

because `String` does not extend `Number`.

---

# 13. Why Use Bounded Type Parameters?

A bound allows Java to guarantee that certain methods are available.

Example:

```java
static <T extends Number> double convertToDouble(T value) {
    return value.doubleValue();
}
```

Because `T` extends `Number`, Java knows:

```java
value.doubleValue();
```

is valid.

Without the bound:

```java
static <T> double convertToDouble(T value) {

    // value.doubleValue(); // ❌

    return 0;
}
```

Java cannot guarantee that arbitrary `T` has `doubleValue()`.

---

# 14. Multiple Bounds

A generic type can have multiple bounds.

Syntax:

```java
<T extends ParentClass & Interface1 & Interface2>
```

Example:

```java
<T extends Number & Comparable<T>>
```

Important rule:

If there is a class bound, it must come first.

Correct:

```java
<T extends Number & Comparable<T>>
```

Incorrect:

```java
<T extends Comparable<T> & Number>
```

A generic type can extend one class and implement multiple interfaces.

---

# 15. Wildcards

A wildcard is:

```java
?
```

It represents an **unknown type**.

Example:

```java
List<?> list
```

This can refer to:

```text
List<String>
List<Integer>
List<Double>
List<Boolean>
```

There are three important wildcard forms:

```text
?                 → Unbounded wildcard
? extends Type    → Upper bounded wildcard
? super Type      → Lower bounded wildcard
```

---

# 16. Unbounded Wildcard `?`

Example:

```java
static void printList(List<?> list) {

    for (Object value : list) {
        System.out.println(value);
    }
}
```

Usage:

```java
List<String> strings = new ArrayList<>();

strings.add("Praveen");
strings.add("Java");

List<Integer> integers = new ArrayList<>();

integers.add(10);
integers.add(20);

List<Double> doubles = new ArrayList<>();

doubles.add(99.5);
doubles.add(100.5);

printList(strings);
printList(integers);
printList(doubles);
```

All are valid.

---

## Reading From `List<?>`

We can safely read values as `Object`.

```java
Object value = list.get(0);
```

Why?

Because the actual element type is unknown, but every reference type is an `Object`.

---

## Adding to `List<?>`

We generally cannot add a specific value.

```java
List<?> list;

// list.add("Java"); // ❌
// list.add(100);    // ❌
```

Why?

Because Java does not know the actual type.

The actual list could be:

```java
List<String>
```

or:

```java
List<Integer>
```

Adding a specific value could be unsafe.

---

# 17. `? extends`

A bounded wildcard can use:

```java
? extends Type
```

Example:

```java
List<? extends Number>
```

This means:

```text
A List of Number or any subclass of Number
```

Possible actual types:

```text
List<Integer>
List<Double>
List<Long>
List<Float>
```

---

## Example

```java
static double calculateSum(List<? extends Number> numbers) {

    double total = 0;

    for (Number number : numbers) {
        total += number.doubleValue();
    }

    return total;
}
```

Usage:

```java
List<Integer> integers = new ArrayList<>();

integers.add(10);
integers.add(20);
integers.add(30);

System.out.println(calculateSum(integers));
```

Output:

```text
60.0
```

Another example:

```java
List<Double> doubles = new ArrayList<>();

doubles.add(10.5);
doubles.add(20.5);

System.out.println(calculateSum(doubles));
```

Output:

```text
31.0
```

---

# 18. Why Can't We Add to `? extends`?

Consider:

```java
List<? extends Number> numbers;
```

It could actually be:

```java
List<Integer>
```

or:

```java
List<Double>
```

Suppose Java allowed:

```java
numbers.add(10);
```

If the actual list were:

```java
List<Double>
```

we would be trying to insert an `Integer` into a `List<Double>`.

Therefore Java prevents it.

```java
// numbers.add(10); // ❌
```

---

## What Can We Read?

We can safely read as:

```java
Number number = numbers.get(0);
```

because every possible element type is a subtype of `Number`.

---

# 19. `? super`

A lower-bounded wildcard uses:

```java
? super Type
```

Example:

```java
List<? super Integer>
```

This means the list can be:

```text
List<Integer>
List<Number>
List<Object>
```

because:

```text
Object
  ↑
Number
  ↑
Integer
```

---

# 20. Consumer Example With `? super`

```java
static void addNumbers(List<? super Integer> numbers) {

    numbers.add(10);
    numbers.add(20);
    numbers.add(30);
}
```

Usage:

```java
List<Integer> integers = new ArrayList<>();

List<Number> numbers = new ArrayList<>();

List<Object> objects = new ArrayList<>();

addNumbers(integers);
addNumbers(numbers);
addNumbers(objects);
```

All are valid.

---

## Why Can We Add Integer?

Because all of these can safely hold an `Integer`:

```text
List<Integer>
List<Number>
List<Object>
```

Therefore:

```java
numbers.add(10);
```

is safe.

---

# 21. Reading From `? super`

Consider:

```java
List<? super Integer> numbers = new ArrayList<Number>();

numbers.add(10);
```

Adding is safe.

But:

```java
// Integer value = numbers.get(0); // ❌
```

This is not allowed.

Why?

Because the actual list could be:

```text
List<Integer>
List<Number>
List<Object>
```

Java cannot guarantee that the returned object is specifically an `Integer`.

The safe type is:

```java
Object value = numbers.get(0);
```

Therefore:

```java
Object value = numbers.get(0); // ✅
```

---

# 22. PECS

One of the most important rules in Java Generics is:

> **PECS = Producer Extends, Consumer Super**

This rule helps us decide whether to use:

```java
? extends
```

or:

```java
? super
```

---

## Producer → `extends`

A producer gives us values.

Example:

```java
static void printNumbers(List<? extends Number> numbers) {

    for (Number number : numbers) {
        System.out.println(number);
    }
}
```

The list is producing values for us.

Therefore:

```text
Producer → Extends
```

---

## Consumer → `super`

A consumer accepts values from us.

Example:

```java
static void addNumbers(List<? super Integer> numbers) {

    numbers.add(10);
    numbers.add(20);
}
```

The list consumes `Integer` values.

Therefore:

```text
Consumer → Super
```

---

## PECS Mental Model

```text
? extends
     ↓
Producer
     ↓
Mainly READ


? super
     ↓
Consumer
     ↓
Mainly ADD
```

---

# 23. Important Wildcard Comparison

| Wildcard | Meaning | Reading | Adding |
|---|---|---|---|
| `?` | Unknown type | `Object` | Generally no |
| `? extends Number` | Number or subclass | `Number` | Generally no |
| `? super Integer` | Integer or parent | `Object` | `Integer` allowed |

---

# 24. Generic Type Invariance

A very important concept:

> Java generics are invariant.

Suppose:

```java
String extends Object
```

This does **not** mean:

```java
List<String> extends List<Object>
```

Therefore:

```java
List<String> strings = new ArrayList<>();

// List<Object> objects = strings; // ❌
```

This is not allowed.

---

## Why?

Imagine Java allowed:

```java
List<String> strings = new ArrayList<>();

List<Object> objects = strings;

objects.add(100);
```

Now `strings` would contain an `Integer`.

But `strings` is supposed to contain only `String`.

That would break type safety.

Therefore Java prevents this.

---

# 25. `List<String>` vs `List<?>`

This is valid:

```java
List<String> strings = new ArrayList<>();

List<?> unknown = strings;
```

Why?

Because `List<?>` means:

```text
I don't know the exact type.
```

It does not claim that the list is a `List<Object>`.

---

# 26. Generic Class vs Generic Method

## Generic Class

```java
class Box<T> {

    private T value;
}
```

The type parameter belongs to the class.

Usage:

```java
Box<String> box = new Box<>();
```

---

## Generic Method

```java
static <T> T getValue(T value) {
    return value;
}
```

The type parameter belongs only to the method.

The class itself does not need to be generic.

Example:

```java
class Utility {

    static <T> T getValue(T value) {
        return value;
    }
}
```

This is a non-generic class containing a generic method.

---

# 27. Raw Types

A raw type is using a generic class without specifying its type parameter.

Example:

```java
List list = new ArrayList();
```

This is called a raw `List`.

It should generally be avoided.

---

## Problem With Raw Types

```java
List list = new ArrayList();

list.add("Java");
list.add(100);
list.add(true);
```

Different types can be inserted.

This removes compile-time type safety.

Later:

```java
String value = (String) list.get(1);
```

This can cause:

```text
ClassCastException
```

because the element is actually an `Integer`.

---

## Preferred Approach

Use:

```java
List<String> list = new ArrayList<>();
```

Now:

```java
list.add("Java");

// list.add(100); // Compile-time error
```

---

# 28. Type Erasure

Type erasure is one of the most important generic concepts for interviews.

Java generics were designed to maintain compatibility with older Java code.

Therefore generic type information is mainly removed during compilation.

Example:

```java
class Box<T> {

    private T value;

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
```

Conceptually, after type erasure:

```java
class Box {

    private Object value;

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
```

The compiler inserts the necessary casts.

For example:

```java
Box<String> box = new Box<>();

box.setValue("Java");

String value = box.getValue();
```

Conceptually, the compiler handles the necessary conversion from `Object` to `String`.

---

# 29. Why Does Java Use Type Erasure?

The main reason is:

> Backward compatibility.

Generics were introduced into Java after the language already had a large amount of existing non-generic code.

Type erasure allowed generic code to work with the existing Java ecosystem without requiring a completely new runtime type system.

---

# 30. Restrictions of Generics

There are several important restrictions.

---

## 30.1 Cannot Use Primitive Types

Invalid:

```java
List<int> numbers; // ❌
```

Correct:

```java
List<Integer> numbers; // ✅
```

---

## 30.2 Cannot Create Generic Arrays Directly

Invalid:

```java
T[] values = new T[10]; // ❌
```

The runtime does not know the actual type of `T`.

---

## 30.3 Cannot Use Parameterized Type With `instanceof`

Invalid:

```java
if (obj instanceof List<String>) {
    // ❌
}
```

Because generic type information is erased.

You can use:

```java
if (obj instanceof List<?>) {
    // ✅
}
```

---

## 30.4 Cannot Create Object Using Type Parameter

Invalid:

```java
class Box<T> {

    T value = new T(); // ❌
}
```

Java does not know the runtime type of `T`.

---

## 30.5 Cannot Create Static Field of Type Parameter

Invalid:

```java
class Box<T> {

    static T value; // ❌
}
```

A static field belongs to the class itself, while `T` belongs to a particular generic instance/type.

---

## 30.6 Cannot Extend `Throwable` With a Generic Class

This is not allowed:

```java
class MyException<T> extends Exception {
}
```

Generic classes cannot be used as throwable exception types.

---

# 31. Bounded Wildcard vs Bounded Type Parameter

These look similar but are different.

## Bounded Type Parameter

```java
static <T extends Number> void process(T value) {
}
```

Here we define a type parameter `T`.

The method can use `T` in multiple places.

Example:

```java
static <T extends Number> T process(T value) {
    return value;
}
```

---

## Bounded Wildcard

```java
static void process(List<? extends Number> numbers) {
}
```

Here we are not introducing a new type parameter.

We are saying:

```text
The list contains some unknown subtype of Number.
```

---

## Comparison

| Bounded Type Parameter | Bounded Wildcard |
|---|---|
| `<T extends Number>` | `? extends Number` |
| Introduces a type parameter | Represents an unknown type |
| Can refer to `T` multiple times | No named type |
| Useful when types need to be related | Useful for flexible input/output |

---

# 32. Generic Method With Related Types

Generic type parameters are useful when multiple parameters must have the same type.

Example:

```java
static <T> void copyValue(T source, T destination) {
}
```

The same `T` is used for both.

A wildcard does not express the same relationship as clearly.

This is one reason to choose a type parameter when multiple values need to share a type relationship.

---

# 33. `extends` in Type Parameters vs Wildcards

These two are related but have different purposes.

### Type Parameter

```java
<T extends Number>
```

Means:

```text
T is a specific type that extends Number.
```

### Wildcard

```java
? extends Number
```

Means:

```text
Some unknown type that extends Number.
```

Mental model:

```text
<T extends Number>
    ↓
Named type

<? extends Number>
    ↓
Unknown type
```

---

# 34. Complete Wildcard Example

```java
package com.javamastery.generics;

import java.util.ArrayList;
import java.util.List;

public class WildCardExample {

    static void printList(List<?> list) {

        for (Object value : list) {
            System.out.println(value);
        }
    }

    static double calculateSum(List<? extends Number> numbers) {

        double total = 0;

        for (Number number : numbers) {
            total += number.doubleValue();
        }

        return total;
    }

    static void addNumbers(List<? super Integer> numbers) {

        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
    }

    public static void main(String[] args) {

        List<Integer> integers = new ArrayList<>();

        List<Number> numbers = new ArrayList<>();

        List<Object> objects = new ArrayList<>();

        addNumbers(integers);
        addNumbers(numbers);
        addNumbers(objects);

        System.out.println(integers);
        System.out.println(numbers);
        System.out.println(objects);

        System.out.println(calculateSum(integers));

        printList(integers);
        printList(numbers);
        printList(objects);
    }
}
```

---

# 35. Common Mistakes

## Mistake 1: Using Primitive Types

```java
List<int> numbers; // ❌
```

Use:

```java
List<Integer> numbers; // ✅
```

---

## Mistake 2: Treating `List<String>` as `List<Object>`

```java
List<String> strings = new ArrayList<>();

// List<Object> objects = strings; // ❌
```

Generics are invariant.

---

## Mistake 3: Adding to `? extends`

```java
List<? extends Number> numbers;

// numbers.add(10); // ❌
```

`? extends` is primarily for reading.

---

## Mistake 4: Expecting Integer From `? super Integer`

```java
List<? super Integer> numbers;

// Integer value = numbers.get(0); // ❌
```

Read as:

```java
Object value = numbers.get(0);
```

---

## Mistake 5: Confusing `extends` With Inheritance of Generic Types

This:

```java
Integer extends Number
```

does not mean:

```java
List<Integer> extends List<Number>
```

---

## Mistake 6: Using Raw Types

Avoid:

```java
List list = new ArrayList();
```

Prefer:

```java
List<String> list = new ArrayList<>();
```

---

## Mistake 7: Forgetting Where `<T>` Goes in Generic Methods

Correct:

```java
static <T> T getValue(T value) {
    return value;
}
```

Incorrect:

```java
// static T <T> getValue(T value) // ❌
```

---

## Mistake 8: Thinking `throws` and Generics Are Related

Generics:

```java
<T>
```

are about type safety.

Exception declaration:

```java
throws IOException
```

is about exception propagation.

They are completely different concepts.

---

# 36. Advantages of Generics

## 36.1 Type Safety

Errors are detected during compilation.

## 36.2 Less Casting

Generic collections return the correct type.

## 36.3 Code Reusability

The same class/method can work with different types.

## 36.4 Better Readability

The expected type is visible in the declaration.

## 36.5 Better API Design

Generic APIs can be flexible while maintaining type safety.

---

# 37. Limitations of Generics

## 37.1 No Primitive Types

Must use wrapper classes.

## 37.2 Type Erasure

Generic type information is largely unavailable at runtime.

## 37.3 Generic Array Restrictions

Cannot directly create:

```java
new T[]
```

## 37.4 Limited Runtime Type Information

Parameterized types cannot generally be checked directly with:

```java
instanceof List<String>
```

---

# 38. Generics and Collections Interview Examples

## Example 1: Type Safety

```java
List<String> names = new ArrayList<>();

names.add("Java");

// names.add(100); // ❌
```

---

## Example 2: Generic Map

```java
Map<String, Integer> stock = new HashMap<>();

stock.put("Laptop", 20);
stock.put("Mouse", 100);
```

---

## Example 3: Generic Method

```java
static <T> T identity(T value) {
    return value;
}
```

---

## Example 4: Bounded Type

```java
static <T extends Number> double sum(T a, T b) {
    return a.doubleValue() + b.doubleValue();
}
```

---

## Example 5: Upper-Bounded Wildcard

```java
static double sum(List<? extends Number> numbers) {

    double total = 0;

    for (Number number : numbers) {
        total += number.doubleValue();
    }

    return total;
}
```

---

## Example 6: Lower-Bounded Wildcard

```java
static void addValues(List<? super Integer> numbers) {

    numbers.add(10);
    numbers.add(20);
}
```

---

# 39. Interview Questions

## Q1. What are Generics?

Generics are a Java feature that allows classes, interfaces, methods, and collections to work with different types while providing compile-time type safety.

---

## Q2. Why do we use Generics?

Main reasons:

1. Compile-time type safety
2. Less explicit casting
3. Code reusability
4. Cleaner APIs
5. Better readability

---

## Q3. What is a type parameter?

A type parameter is a placeholder representing a type.

Example:

```java
class Box<T>
```

Here `T` is the type parameter.

---

## Q4. What is the difference between type parameter and type argument?

Type parameter:

```java
class Box<T>
```

`T` is a type parameter.

Type argument:

```java
Box<String>
```

`String` is the actual type argument.

---

## Q5. Can Generics use primitive types?

No.

Invalid:

```java
List<int>
```

Use:

```java
List<Integer>
```

---

## Q6. What is the diamond operator?

The diamond operator:

```java
<>
```

allows Java to infer the generic type.

Example:

```java
List<String> list = new ArrayList<>();
```

---

## Q7. What is a generic class?

A class that declares a type parameter.

Example:

```java
class Box<T> {
    private T value;
}
```

---

## Q8. What is a generic method?

A method that declares its own type parameter.

Example:

```java
static <T> T getValue(T value) {
    return value;
}
```

---

## Q9. Can a non-generic class contain a generic method?

Yes.

Example:

```java
class Utility {

    static <T> T getValue(T value) {
        return value;
    }
}
```

---

## Q10. What is a bounded type parameter?

It restricts the allowed types.

Example:

```java
<T extends Number>
```

Only `Number` and its subclasses are allowed.

---

## Q11. What is a wildcard?

A wildcard:

```java
?
```

represents an unknown type.

Example:

```java
List<?> list;
```

---

## Q12. What is `? extends`?

It is an upper-bounded wildcard.

```java
List<? extends Number>
```

It means the list contains some unknown subtype of `Number`.

It is primarily used for reading.

---

## Q13. What is `? super`?

It is a lower-bounded wildcard.

```java
List<? super Integer>
```

It means the list can be an `Integer` list or a list of one of its supertypes.

It is primarily used for adding `Integer` values.

---

## Q14. What is PECS?

PECS means:

```text
Producer Extends
Consumer Super
```

Use:

```java
? extends
```

when a structure produces values.

Use:

```java
? super
```

when a structure consumes values.

---

## Q15. Why can't we add to `List<? extends Number>`?

Because the actual list could be:

```java
List<Integer>
```

or:

```java
List<Double>
```

Java cannot guarantee that the value being added matches the unknown subtype.

---

## Q16. Why can we add Integer to `List<? super Integer>`?

Because the actual list can be:

```text
List<Integer>
List<Number>
List<Object>
```

All of them can safely contain an `Integer`.

---

## Q17. Why can we only safely read Object from `List<? super Integer>`?

Because the actual list could be:

```text
List<Integer>
List<Number>
List<Object>
```

Java can guarantee only that the returned value is an `Object`.

---

## Q18. Are generics invariant?

Yes.

Even though:

```java
String extends Object
```

this is not valid:

```java
List<String> → List<Object>
```

Generics are invariant.

---

## Q19. What is a raw type?

A raw type is using a generic class without specifying its type parameter.

Example:

```java
List list = new ArrayList();
```

Raw types should generally be avoided because they remove compile-time type safety.

---

## Q20. What is type erasure?

Type erasure is the process through which generic type information is largely removed during compilation.

It allows Java to maintain compatibility with older pre-generics code.

---

## Q21. Why does Java use type erasure?

Mainly for backward compatibility with older Java code and the existing Java runtime ecosystem.

---

## Q22. Can we create `new T()`?

No.

```java
T value = new T(); // ❌
```

The runtime does not know the actual type of `T`.

---

## Q23. Can we create `new T[10]`?

No.

```java
T[] values = new T[10]; // ❌
```

Because the runtime does not know the actual component type.

---

## Q24. Can we use `instanceof List<String>`?

No.

```java
obj instanceof List<String> // ❌
```

Because of type erasure.

But:

```java
obj instanceof List<?> // ✅
```

is allowed.

---

# 40. Interview Prediction Questions

## Question 1

What happens?

```java
List<String> names = new ArrayList<>();

names.add("Java");

// names.add(100);
```

Answer:

```text
Compile-time error
```

because the list accepts only `String`.

---

## Question 2

What happens?

```java
List<? extends Number> numbers = new ArrayList<Integer>();

// numbers.add(10);
```

Answer:

```text
Compile-time error
```

Because the actual list could be a `List<Double>`.

---

## Question 3

What happens?

```java
List<? super Integer> numbers = new ArrayList<Number>();

numbers.add(10);
```

Answer:

```text
Compiles successfully
```

---

## Question 4

What happens?

```java
List<? super Integer> numbers = new ArrayList<Number>();

// Integer value = numbers.get(0);
```

Answer:

```text
Compile-time error
```

The safe type is:

```java
Object value = numbers.get(0);
```

---

## Question 5

Does this compile?

```java
List<String> strings = new ArrayList<>();

// List<Object> objects = strings;
```

Answer:

```text
No
```

Generics are invariant.

---

## Question 6

What does this mean?

```java
<T extends Number>
```

Answer:

```text
T must be Number or a subclass of Number.
```

---

## Question 7

What does this mean?

```java
List<? extends Number>
```

Answer:

```text
A List of some unknown type that extends Number.
```

---

## Question 8

What does this mean?

```java
List<? super Integer>
```

Answer:

```text
A List of Integer or one of Integer's supertypes.
```

Possible types:

```text
List<Integer>
List<Number>
List<Object>
```

---

## Question 9

What is the output?

```java
static <T> T identity(T value) {
    return value;
}

public static void main(String[] args) {

    String result = identity("Java");

    System.out.println(result);
}
```

Answer:

```text
Java
```

`T` is inferred as `String`.

---

## Question 10

What is the type of `value`?

```java
List<String> names = new ArrayList<>();

names.add("Java");

String value = names.get(0);
```

Answer:

```text
String
```

No explicit cast is needed because of generics.

---

# 41. Coding Practice

## Problem 1 — Generic Box

Create:

```java
class Box<T>
```

Requirements:

- private `T value`
- `setValue()`
- `getValue()`

Test with:

```text
String
Integer
Double
```

---

## Problem 2 — Generic Method

Create:

```java
static <T> T identity(T value)
```

Test:

```text
Integer
String
Boolean
Character
```

---

## Problem 3 — Generic Pair

Create:

```java
static <K, V> void printPair(K key, V value)
```

Test:

```java
Product → Laptop
101 → Praveen
```

---

## Problem 4 — Bounded Generic

Create:

```java
static <T extends Number> double square(T value)
```

Test:

```text
Integer
Double
Long
```

---

## Problem 5 — Unbounded Wildcard

Create:

```java
static void printList(List<?> list)
```

Test with:

```text
List<String>
List<Integer>
List<Double>
```

---

## Problem 6 — Upper-Bounded Wildcard

Create:

```java
static double calculateSum(List<? extends Number> numbers)
```

Test with:

```text
List<Integer>
List<Double>
```

---

## Problem 7 — Lower-Bounded Wildcard

Create:

```java
static void addNumbers(List<? super Integer> numbers)
```

Test with:

```text
List<Integer>
List<Number>
List<Object>
```

---

# 42. Real-World Example — Generic Repository

Generics are heavily used in application architecture.

A simple repository abstraction:

```java
interface Repository<T> {

    void save(T value);

    T find();
}
```

Implementation:

```java
class RepositoryImpl<T> implements Repository<T> {

    private T value;

    @Override
    public void save(T value) {
        this.value = value;
    }

    @Override
    public T find() {
        return value;
    }
}
```

Usage:

```java
Repository<String> userRepository = new RepositoryImpl<>();

userRepository.save("Praveen");

String user = userRepository.find();
```

Another repository:

```java
Repository<Integer> orderRepository = new RepositoryImpl<>();

orderRepository.save(101);

Integer orderId = orderRepository.find();
```

The same repository structure can work with different domain types.

---

# 43. Real-World Example — API Response

Generics are commonly useful when designing API response wrappers.

Example:

```java
class ApiResponse<T> {

    private int statusCode;

    private T data;

    public ApiResponse(int statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
```

For a user:

```java
ApiResponse<User> response;
```

For a product:

```java
ApiResponse<Product> response;
```

For a list:

```java
ApiResponse<List<Product>> response;
```

This is a common pattern in backend development.

---

# 44. Generics in Spring Boot

Generics are heavily used in Spring and Spring Boot applications.

Examples include:

```java
ResponseEntity<User>
```

```java
ResponseEntity<List<User>>
```

```java
Optional<User>
```

```java
List<User>
```

```java
Page<User>
```

```java
JpaRepository<User, Long>
```

For example:

```java
public interface UserRepository
        extends JpaRepository<User, Long> {
}
```

Here:

```text
User → Entity type
Long → ID type
```

Generics make Spring APIs type-safe.

---

# 45. Generic Interface With Fixed Type

A generic interface does not always have to remain generic in the implementation.

Example:

```java
interface Repository<T> {

    void save(T value);
}
```

We can implement it with a specific type:

```java
class ProductRepository implements Repository<Product> {

    @Override
    public void save(Product product) {
        System.out.println(product);
    }
}
```

Here the implementation is specifically for:

```text
Product
```

---

# 46. Generic Interface With Generic Implementation

We can also keep the implementation generic:

```java
class GenericRepository<T> implements Repository<T> {

    private T value;

    @Override
    public void save(T value) {
        this.value = value;
    }

    public T find() {
        return value;
    }
}
```

Then:

```java
GenericRepository<String> stringRepository =
        new GenericRepository<>();

GenericRepository<Integer> integerRepository =
        new GenericRepository<>();
```

---

# 47. Important Comparisons

## Generic Class vs Generic Method

| Generic Class | Generic Method |
|---|---|
| Type belongs to class | Type belongs to method |
| `class Box<T>` | `static <T> T method()` |
| Used throughout class | Used within method |
| Object determines type | Method invocation can infer type |

---

## `?` vs `? extends` vs `? super`

| Syntax | Meaning |
|---|---|
| `?` | Unknown type |
| `? extends Number` | Unknown subtype of Number |
| `? super Integer` | Integer or Integer's supertype |

---

## `extends` vs `super`

| `extends` | `super` |
|---|---|
| Upper bound | Lower bound |
| Producer | Consumer |
| Mainly read | Mainly add |
| `? extends Number` | `? super Integer` |

---

## Type Parameter vs Wildcard

| Type Parameter | Wildcard |
|---|---|
| `<T>` | `?` |
| Named type | Unknown type |
| Can relate multiple parameters | Represents flexibility |
| `<T extends Number>` | `? extends Number` |

---

## Generic vs Raw Type

| Generic | Raw |
|---|---|
| `List<String>` | `List` |
| Type safe | Not type safe |
| Compile-time checking | Reduced compile-time checking |
| Preferred | Avoid |

---

# 48. Generics Mental Model

```text
                         GENERICS
                            |
        +-------------------+-------------------+
        |                   |                   |
     Classes             Methods            Interfaces
        |                   |                   |
      Box<T>             <T> T               Repo<T>
                            |
                       Type Safety
                            |
             +--------------+--------------+
             |              |              |
          Bounds         Wildcards      Type Erasure
             |              |              |
        T extends X       ?               Runtime
                         /   \
                   extends   super
                      |        |
                  Producer   Consumer
                      |        |
                    READ      ADD
```

---

# 49. PECS Mental Model

Remember this:

```text
P → Producer
E → Extends

C → Consumer
S → Super
```

Therefore:

```text
Producer → ? extends
Consumer → ? super
```

Example:

```java
List<? extends Number>
```

Think:

```text
"Give me Numbers"
```

Example:

```java
List<? super Integer>
```

Think:

```text
"I want to put Integers into you"
```

---

# 50. Quick Revision

```text
Generics
    ↓
Compile-time type safety
    ↓
Less casting
    ↓
Code reusability
```

### Generic Class

```java
class Box<T>
```

### Generic Method

```java
<T> T method(T value)
```

### Generic Interface

```java
interface Repository<T>
```

### Bounded Type

```java
<T extends Number>
```

### Unbounded Wildcard

```java
?
```

### Upper-Bounded Wildcard

```java
? extends Number
```

### Lower-Bounded Wildcard

```java
? super Integer
```

### PECS

```text
Producer Extends
Consumer Super
```

### Primitive

```java
List<int> // ❌
List<Integer> // ✅
```

### Raw Type

```java
List list; // Avoid
```

### Invariance

```java
List<String> ≠ List<Object>
```

### Type Erasure

```text
Generic type information is largely erased at runtime.
```

---

# 51. Detailed Interview Explanation

### Interview Question:

**"Explain Java Generics."**

### Answer:

> Generics are a feature in Java that allows us to define classes, interfaces, methods, and collections using type parameters. They provide compile-time type safety, reduce the need for explicit casting, and improve code reusability.
>
> For example, instead of using a raw `List`, I can use `List<String>`, which ensures that only String values can be added and that values retrieved from the list are already known to be String.
>
> Java also supports generic classes, generic methods, bounded type parameters, and wildcards.
>
> A bounded type parameter such as `<T extends Number>` restricts the allowed types. Wildcards provide flexibility using `?`, `? extends`, and `? super`.
>
> For wildcards, an important rule is PECS, which means Producer Extends and Consumer Super. We generally use `? extends` when we are reading or consuming values from a producer, and `? super` when we need to add values to a consumer.
>
> Java implements generics using type erasure, meaning generic type information is largely removed at runtime. This was primarily done to maintain backward compatibility with older Java code.
>
> Overall, generics improve type safety, readability, reusability, and API design.

---

# 52. Interview-Level Mental Model

When you see:

```java
<T>
```

think:

```text
"I am defining a type parameter."
```

When you see:

```java
?
```

think:

```text
"I don't know the exact type."
```

When you see:

```java
? extends Number
```

think:

```text
"Some unknown subtype of Number."
```

Mainly read.

When you see:

```java
? super Integer
```

think:

```text
"Integer or one of its parent types."
```

Safe to add Integer.

When you see:

```java
<T extends Number>
```

think:

```text
"T has a Number constraint."
```

When you see:

```java
List<String>
```

think:

```text
"Only Strings are allowed."
```

When you see:

```java
List
```

think:

```text
"Raw type — avoid."
```

---

# 53. Important Interview Traps

## Trap 1

```java
List<Integer> numbers = new ArrayList<>();

// List<Number> values = numbers;
```

Answer:

```text
Compile-time error
```

Generics are invariant.

---

## Trap 2

```java
List<? extends Number> numbers = new ArrayList<Integer>();

// numbers.add(10);
```

Answer:

```text
Compile-time error
```

Because the actual type could be another Number subtype.

---

## Trap 3

```java
List<? super Integer> numbers = new ArrayList<Number>();

numbers.add(10);
```

Answer:

```text
Valid
```

---

## Trap 4

```java
List<? super Integer> numbers = new ArrayList<Number>();

// Integer value = numbers.get(0);
```

Answer:

```text
Compile-time error
```

Safe read type:

```java
Object value = numbers.get(0);
```

---

## Trap 5

```java
List<?> list = new ArrayList<String>();

// list.add("Java");
```

Answer:

```text
Compile-time error
```

The exact type is unknown.

---

## Trap 6

```java
List<?> list = new ArrayList<String>();

Object value = list.get(0);
```

Answer:

```text
Valid
```

---

## Trap 7

```java
<T extends Number>
```

Does this mean only `Number` is allowed?

No.

It means:

```text
Number and its subclasses
```

are allowed.

---

# 54. Key Takeaways

1. Generics provide compile-time type safety.
2. Generics reduce explicit casting.
3. Generics improve code reusability.
4. Generic classes use type parameters such as `<T>`.
5. Generic methods declare their own type parameters.
6. Generic interfaces can be parameterized.
7. Generics work with reference types, not primitives.
8. Use wrapper classes for primitive values.
9. Bounded types restrict allowed types.
10. `?` means unknown type.
11. `? extends` represents an unknown subtype.
12. `? super` represents a type or one of its supertypes.
13. `? extends` is mainly used for reading.
14. `? super` is mainly used for adding.
15. PECS means Producer Extends, Consumer Super.
16. Generic types are invariant.
17. `List<String>` is not a `List<Object>`.
18. Raw types should generally be avoided.
19. Type erasure removes generic type information largely at runtime.
20. Generic arrays cannot be created directly using `new T[]`.
21. Parameterized types cannot generally be checked using `instanceof`.
22. Generics are heavily used throughout the Java Collections Framework.
23. Generics are heavily used in Spring and Spring Boot APIs.

---

# 55. Final Generics Cheat Sheet

```text
====================================================
                JAVA GENERICS
====================================================

Purpose:
    Compile-time type safety
    Less casting
    Code reusability
    Cleaner APIs

----------------------------------------------------
GENERIC CLASS
----------------------------------------------------

class Box<T> {
    private T value;
}

Box<String> box = new Box<>();

----------------------------------------------------
GENERIC METHOD
----------------------------------------------------

static <T> T getValue(T value) {
    return value;
}

----------------------------------------------------
GENERIC INTERFACE
----------------------------------------------------

interface Repository<T> {
    void save(T value);
}

----------------------------------------------------
BOUNDED TYPE
----------------------------------------------------

<T extends Number>

Number or subclass of Number

----------------------------------------------------
WILDCARDS
----------------------------------------------------

?                  → Unknown type

? extends Number   → Number or subclass
                      Mainly READ

? super Integer    → Integer or parent
                      Mainly ADD

----------------------------------------------------
PECS
----------------------------------------------------

Producer → Extends
Consumer → Super

----------------------------------------------------
PRIMITIVES
----------------------------------------------------

List<int>       ❌
List<Integer>   ✅

----------------------------------------------------
RAW TYPE
----------------------------------------------------

List list;      → Avoid

List<String>    → Preferred

----------------------------------------------------
INVARIANCE
----------------------------------------------------

String extends Object

BUT:

List<String> is NOT List<Object>

----------------------------------------------------
TYPE ERASURE
----------------------------------------------------

Generic type information is largely removed
during compilation/runtime representation.

----------------------------------------------------
IMPORTANT RESTRICTIONS
----------------------------------------------------

new T()       ❌
new T[]       ❌
List<int>     ❌
instanceof List<String> ❌

----------------------------------------------------
MENTAL MODEL
----------------------------------------------------

<T>                 → Type parameter
?                   → Unknown type
? extends           → Producer / Read
? super             → Consumer / Add
extends Number      → Restriction
Generics            → Compile-time type safety

====================================================
                  GENERICS DONE
====================================================
```