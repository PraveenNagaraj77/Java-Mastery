# Java Variables & Data Types

## 1. What Is a Variable?

A **variable** is a named entity used to store or represent a value during program execution.

Example:

```java
int age = 26;
```

Here:

```text
int  → Data Type
age  → Variable Name
26   → Value
=    → Assignment Operator
```

A variable allows a program to store data and use that data later.

### Simple Mental Model

```text
Variable
   |
   v
+-------+
|  26   |
+-------+
```

---

# 2. Why Do We Need Variables?

Programs need to work with data.

For example, an employee management system may need:

```java
String name = "Praveen";
int age = 26;
double salary = 70000.50;
boolean active = true;
```

Without variables, we would not have a convenient way to:

* Store data
* Reuse data
* Modify data
* Perform calculations
* Pass data to methods
* Represent object state
* Maintain application state

### Real-World Example

An employee application may represent an employee as:

```java
String name = "John";
int age = 30;
double salary = 75000.00;
boolean active = true;
```

These variables represent different pieces of employee information.

---

# 3. How Does It Work Internally?

When we write:

```java
int age = 26;
```

Java knows:

```text
Variable name → age
Type          → int
Value         → 26
```

The exact physical storage depends on the kind of variable and JVM implementation.

Java variables can broadly be classified into:

```text
Variables
│
├── Local Variables
├── Instance Variables
└── Static Variables
```

The important distinction is:

### Local Variable

Associated with a method/block execution.

```java
void test() {
    int x = 10;
}
```

### Instance Variable

Belongs to an object.

```java
class Employee {
    int age;
}
```

### Static Variable

Belongs to the class.

```java
class Employee {
    static int count;
}
```

We should avoid saying:

> "Every variable is stored directly on the stack."

That is an oversimplification. The JVM can optimize execution, and the conceptual storage depends on the variable category.

---

# 4. Types of Variables

## 4.1 Local Variables

A variable declared inside a:

* Method
* Constructor
* Block

is a local variable.

Example:

```java
void calculate() {

    int salary = 50000;

    System.out.println(salary);
}
```

`salary` is a local variable.

### Scope

Its scope is limited to the block in which it is declared.

```java
void test() {

    int x = 10;

    if (x > 5) {

        int y = 20;

        System.out.println(x);
        System.out.println(y);
    }

    System.out.println(x);

    // System.out.println(y); // Compilation error
}
```

`y` cannot be accessed outside the `if` block.

---

## 4.2 Local Variables Don't Get Default Values

This is a common interview question.

```java
public static void main(String[] args) {

    int x;

    System.out.println(x);
}
```

This does not compile.

The compiler reports that the variable may not have been initialized.

You must assign a value first:

```java
int x = 10;
```

### Interview Rule

> Local variables must be definitely assigned before they are read.

---

# 5. Instance Variables

A variable declared inside a class but outside methods, constructors, and blocks, without `static`, is an instance variable.

Example:

```java
class Employee {

    int id;
    String name;
    double salary;
}
```

Here:

```text
id
name
salary
```

are instance variables.

They belong to individual objects.

Example:

```java
Employee e1 = new Employee();
Employee e2 = new Employee();
```

Conceptually:

```text
e1
│
├── id
├── name
└── salary

e2
│
├── id
├── name
└── salary
```

Each object has its own instance state.

---

# 6. Default Values of Instance Variables

Instance fields receive default values as part of object initialization.

Example:

```java
class Employee {

    int id;
    double salary;
    boolean active;
    String name;
}
```

Conceptually:

```text
id      → 0
salary  → 0.0
active  → false
name    → null
```

### Default Values

| Data Type       | Default Value |
| --------------- | ------------- |
| `byte`          | `0`           |
| `short`         | `0`           |
| `int`           | `0`           |
| `long`          | `0L`          |
| `float`         | `0.0f`        |
| `double`        | `0.0d`        |
| `char`          | `'\u0000'`    |
| `boolean`       | `false`       |
| Reference types | `null`        |

---

# 7. Static Variables

A variable declared using `static` is called a static variable or class variable.

Example:

```java
class Employee {

    int id;

    static String company = "ABC";
}
```

Here:

```text
id
↓
Instance Variable

company
↓
Static Variable
```

### Key Difference

```text
Instance Variable
→ Belongs to an object

Static Variable
→ Belongs to the class
```

Example:

```java
Employee.company = "XYZ";
```

The class-level field is shared by instances accessing that field.

---

# 8. Instance vs Static

Consider:

```java
class Employee {

    String name;

    static String company = "ABC";
}
```

Create two objects:

```java
Employee e1 = new Employee();
Employee e2 = new Employee();
```

Conceptually:

```text
             Employee Class
                   |
             company = "ABC"
                   |
          +--------+--------+
          |                 |
          v                 v
         e1                e2
          |                 |
       name              name
```

Each object has its own `name`.

The static `company` belongs to the class.

### Real-World Example

For an employee system:

```text
name       → different for each employee
employeeId → different for each employee
salary     → different for each employee

company    → potentially shared
```

---

# 9. What Is a Data Type?

A **data type** defines the kind of value that a variable can represent and determines the operations and conversions permitted by Java's type system.

Java has two broad categories:

```text
Data Types
│
├── Primitive Types
│
└── Reference Types
```

---

# 10. Primitive Data Types

Java has exactly **8 primitive data types**.

```text
Primitive Types
│
├── byte
├── short
├── int
├── long
├── float
├── double
├── char
└── boolean
```

They can be grouped as:

```text
Primitive
│
├── Integral
│   ├── byte
│   ├── short
│   ├── int
│   ├── long
│   └── char
│
├── Floating Point
│   ├── float
│   └── double
│
└── boolean
```

---

# 11. byte

```java
byte age = 26;
```

Size:

```text
8 bits
```

Range:

```text
-128 to 127
```

Formula:

```text
-2^7 to 2^7 - 1
```

Example:

```java
byte temperature = 30;
```

Useful when working with byte-oriented data or when the range is sufficient.

---

# 12. short

```java
short count = 1000;
```

Size:

```text
16 bits
```

Range:

```text
-32,768 to 32,767
```

Formula:

```text
-2^15 to 2^15 - 1
```

`short` is less commonly used than `int` in normal application code.

---

# 13. int

```java
int age = 26;
```

Size:

```text
32 bits
```

Range:

```text
-2,147,483,648
to
2,147,483,647
```

Formula:

```text
-2^31 to 2^31 - 1
```

`int` is the standard/default integer type for most Java programs.

Example:

```java
int employeeCount = 500;
```

---

# 14. long

```java
long population = 8_000_000_000L;
```

Size:

```text
64 bits
```

Range:

```text
-2^63 to 2^63 - 1
```

Notice the:

```java
L
```

suffix.

Why?

Integer literals are normally treated as `int` unless their type is otherwise indicated.

Therefore:

```java
long value = 8_000_000_000L;
```

is correct.

---

# 15. float

```java
float price = 99.99f;
```

Size:

```text
32-bit IEEE 754 floating-point
```

The `f` suffix is important.

This:

```java
float price = 99.99;
```

does not compile because `99.99` is a `double` literal by default.

Correct:

```java
float price = 99.99f;
```

---

# 16. double

```java
double salary = 75000.50;
```

Size:

```text
64-bit IEEE 754 floating-point
```

Decimal floating-point literals are `double` by default.

Example:

```java
double percentage = 95.5;
```

---

# 17. char

```java
char grade = 'A';
```

Size:

```text
16 bits
```

Java's `char` represents a **UTF-16 code unit**.

Use:

```java
'A'
```

not:

```java
"A"
```

because:

```text
'A' → char
"A" → String
```

---

# 18. char and Unicode

Example:

```java
char ch = '\u0041';
```

The value represents:

```text
A
```

Important interview point:

> `char` is 16 bits and represents a UTF-16 code unit. It does not represent every Unicode code point in a single `char`.

Some Unicode characters require two UTF-16 code units.

We will study this more deeply when we reach `String`.

---

# 19. boolean

```java
boolean active = true;
```

Possible values:

```text
true
false
```

Java does not treat `boolean` as a numeric type.

Invalid:

```java
boolean x = 1;
```

Invalid:

```java
int x = true;
```

---

# 20. Primitive Type Summary

| Type      |          Size | Range / Values    |
| --------- | ------------: | ----------------- |
| `byte`    |        8 bits | -128 to 127       |
| `short`   |       16 bits | -32,768 to 32,767 |
| `int`     |       32 bits | -2³¹ to 2³¹-1     |
| `long`    |       64 bits | -2⁶³ to 2⁶³-1     |
| `float`   |       32 bits | IEEE 754          |
| `double`  |       64 bits | IEEE 754          |
| `char`    |       16 bits | UTF-16 code unit  |
| `boolean` | JVM-dependent | `true` / `false`  |

### Important Interview Point

Java does **not** specify a fixed storage size for `boolean`.

Do not say:

> "`boolean` always takes 1 byte."

---

# 21. Reference Data Types

Reference types represent objects and other reference-based entities.

Examples include:

* Classes
* Arrays
* Interfaces
* Enums
* Records

Example:

```java
Employee employee = new Employee();
```

Here:

```text
Employee
↓
Reference Type

employee
↓
Reference Variable

new Employee()
↓
Object
```

---

# 22. Primitive vs Reference

### Primitive

```java
int age = 26;
```

Conceptually:

```text
age
 ↓
26
```

### Reference

```java
Employee employee = new Employee();
```

Conceptually:

```text
employee
    |
    v
Employee Object
```

A reference variable refers to an object.

---

# 23. Reference Variables Can Be null

Example:

```java
Employee employee = null;
```

This means:

```text
employee
   |
   X
No object currently referenced
```

Primitive variables cannot contain `null`.

Invalid:

```java
int age = null;
```

---

# 24. Multiple References to the Same Object

Consider:

```java
Employee e1 = new Employee();

Employee e2 = e1;
```

Conceptually:

```text
e1 ─────┐
        |
        v
   Employee Object
        ^
        |
e2 ─────┘
```

Both variables refer to the same object.

Therefore:

```java
e1.name = "John";
```

can be observed through:

```java
System.out.println(e2.name);
```

because `e1` and `e2` refer to the same object.

---

# 25. Scope of Variables

Scope determines where a variable can be accessed.

Example:

```java
public void test() {

    int x = 10;

    if (x > 5) {

        int y = 20;

        System.out.println(x);
        System.out.println(y);
    }

    System.out.println(x);

    // y is not accessible here
}
```

`x` is accessible throughout its enclosing method after declaration.

`y` is accessible only inside the `if` block.

---

# 26. Lifetime of Variables

Scope and lifetime are related but not identical.

### Local Variable

Associated with a method invocation.

```java
void test() {
    int x = 10;
}
```

### Instance Variable

Part of an object's state.

```java
class Employee {
    int age;
}
```

### Static Variable

Associated with the class.

```java
class Employee {
    static int count;
}
```

The exact runtime lifetime depends on JVM execution and class/object lifecycle.

---

# 27. Variable Naming Rules

Valid:

```java
int age;
int employeeAge;
int _count;
int $value;
```

Invalid:

```java
int 1age;
int employee-name;
int class;
```

Java identifiers:

* Cannot begin with a digit.
* Cannot contain `-`.
* Cannot be a keyword.
* Are case-sensitive.

These are different:

```java
int age;
int Age;
int AGE;
```

---

# 28. `final` Variables

`final` prevents reassignment of a variable after it has been assigned.

Example:

```java
final int MAX_USERS = 100;
```

This is invalid:

```java
MAX_USERS = 200;
```

because the variable cannot be reassigned.

---

# 29. Final Reference

This is a very important interview concept.

```java
final Employee employee = new Employee();
```

You cannot reassign:

```java
employee = new Employee(); // Compilation error
```

But `final` does not automatically make the object immutable.

If the object's field is mutable:

```java
employee.name = "John";
```

may still be valid.

Therefore:

> `final reference` does not mean `immutable object`.

---

# 30. Constants

Java commonly uses:

```java
static final
```

for constants.

Example:

```java
public static final int MAX_USERS = 100;
```

Naming convention:

```text
UPPER_CASE_WITH_UNDERSCORES
```

Examples:

```java
MAX_SIZE
DEFAULT_TIMEOUT
MAX_RETRY_COUNT
```

---

# 31. Type Conversion

Java supports conversions between compatible primitive numeric types.

Example:

```java
int x = 10;

long y = x;
```

This is a widening conversion.

Conceptually:

```text
int
 ↓
long
```

No explicit cast is normally required.

---

# 32. Widening Conversion

Widening converts a value to a type with a broader range/representation.

Example:

```java
int x = 100;

long y = x;

double z = y;
```

Conceptually:

```text
int → long → double
```

Java can perform many such conversions implicitly.

---

# 33. Narrowing Conversion

Narrowing converts to a smaller/incompatible range type and generally requires an explicit cast.

Example:

```java
long x = 100;

int y = (int) x;
```

Syntax:

```java
(targetType) value
```

Example:

```java
double price = 99.99;

int value = (int) price;
```

Result:

```text
99
```

The fractional part is discarded.

---

# 34. Data Loss During Casting

Example:

```java
int x = 130;

byte y = (byte) x;
```

A `byte` can only represent:

```text
-128 to 127
```

Therefore the original value cannot be represented as a `byte`.

The resulting value follows Java's narrowing conversion rules and can wrap around.

Explicit casting does not guarantee that the original value will be preserved.

---

# 35. Integer Overflow

Consider:

```java
int x = Integer.MAX_VALUE;

x++;

System.out.println(x);
```

`Integer.MAX_VALUE` is:

```text
2,147,483,647
```

Adding one produces:

```text
-2,147,483,648
```

Java integer arithmetic uses fixed-width two's-complement representation.

Normal integer overflow does not automatically throw an exception.

---

# 36. Numeric Literals

### Integer

```java
int x = 100;
```

### Long

```java
long x = 100L;
```

### Float

```java
float x = 10.5f;
```

### Double

```java
double x = 10.5;
```

### Character

```java
char x = 'A';
```

### Boolean

```java
boolean x = true;
```

---

# 37. Underscores in Numeric Literals

Java allows underscores to improve readability.

Example:

```java
int salary = 100_000;

long population = 8_000_000_000L;
```

These:

```java
100_000
```

and:

```java
100000
```

represent the same numeric value.

The underscores are purely for readability.

---

# 38. Real-World Example

Consider an Employee class:

```java
class Employee {

    int id;

    String name;

    double salary;

    boolean active;

    static String company = "ABC";
}
```

Classification:

```text
id
↓
Instance Variable

name
↓
Instance Variable

salary
↓
Instance Variable

active
↓
Instance Variable

company
↓
Static Variable
```

Create employees:

```java
Employee e1 = new Employee();
Employee e2 = new Employee();
```

Conceptually:

```text
Employee Class
│
└── company = "ABC"
      │
      ├──────────────┐
      │              │
      v              v
    e1 object      e2 object
      │              │
      ├── id         ├── id
      ├── name       ├── name
      ├── salary     ├── salary
      └── active     └── active
```

This demonstrates the difference between **class-level state** and **object-level state**.

---

# 39. Common Mistakes

## Mistake 1 — Local Variable Without Initialization

```java
int x;

System.out.println(x);
```

Incorrect.

Local variables must be definitely assigned before use.

---

## Mistake 2 — String Is Primitive

Incorrect:

```text
String → primitive
```

Correct:

```text
String → reference type
```

---

## Mistake 3 — Boolean Is a Number

Incorrect:

```java
boolean active = 1;
```

Java does not convert `1` to `true`.

---

## Mistake 4 — Forgetting `f`

Incorrect:

```java
float price = 10.5;
```

Correct:

```java
float price = 10.5f;
```

---

## Mistake 5 — Forgetting `L`

For a large integer literal:

```java
long population = 8_000_000_000L;
```

Use `L` when needed to make the literal a `long`.

---

## Mistake 6 — `final` Means Immutable

Incorrect.

```java
final Employee employee = new Employee();
```

The reference cannot be reassigned, but the object can still be mutable.

---

## Mistake 7 — Every Variable Is Stored on the Stack

This is an oversimplification.

Variable storage depends on whether the variable is local, instance, static, and on JVM implementation/optimization.

---

## Mistake 8 — `char` Is ASCII

Incorrect.

Java `char` represents a UTF-16 code unit.

---

# 40. Interview Questions

## Basic

1. What is a variable?
2. What are the types of variables in Java?
3. What is a local variable?
4. What is an instance variable?
5. What is a static variable?
6. What is a data type?
7. How many primitive data types are there?
8. What are the eight primitive data types?
9. What is the difference between primitive and reference types?
10. What are the default values of instance variables?

## Intermediate

11. Why don't local variables have default values?
12. What is the difference between instance and static variables?
13. What is variable scope?
14. What is variable lifetime?
15. Can a primitive variable contain `null`?
16. Can a reference variable contain `null`?
17. What happens when two references point to the same object?
18. What is `final`?
19. Does `final` make an object immutable?
20. What is a constant?

## Advanced

21. Why is String a reference type?
22. Where are local variables stored?
23. Where are instance variables stored?
24. Where are static variables stored?
25. Is every object always physically allocated on the heap?
26. What is widening conversion?
27. What is narrowing conversion?
28. Why is explicit casting required for narrowing?
29. What happens during integer overflow?
30. What is the difference between `float` and `double`?
31. Why do we use `L` with long literals?
32. Why do we use `f` with float literals?
33. Is `char` an ASCII type?
34. Does Java define the exact storage size of `boolean`?
35. What happens when an object reference becomes `null`?

---

# 41. ⭐ Detailed Interview Explanation

## Question: What are variables and data types in Java?

### Interview Answer

> **"A variable in Java is a named program variable used to represent a value during program execution. Every variable has a declared type, which determines the kind of value it can represent and the operations that are allowed on that value."**
>
> **"Java has two broad categories of types: primitive types and reference types. Java provides eight primitive types: byte, short, int, long, float, double, char, and boolean. Reference types include classes, arrays, interfaces, enums, records, and other object types. A reference variable refers to an object rather than representing the object itself."**
>
> **"Variables can also be classified according to where they are declared. A local variable is declared inside a method, constructor, or block and must be definitely assigned before it is read. An instance variable is a non-static field belonging to an object, so each object has its own instance state. A static variable is associated with the class and represents class-level state."**
>
> **"Java also provides the final modifier. A final variable cannot be reassigned after it has been initialized. However, if the variable is a reference, final prevents changing the reference but does not automatically make the referenced object immutable."**
>
> **"Java supports primitive type conversions. Widening conversions can generally happen implicitly, while narrowing conversions usually require explicit casting and may result in data loss."**
>
> **"Understanding variables and types is fundamental because these concepts are used throughout Java, including object-oriented programming, method parameters, collections, memory management, and multithreading."**

---

# 42. ⭐ Interview: Primitive vs Reference Types

### Question

**What is the difference between primitive and reference types?**

### Detailed Answer

> **"Java has eight primitive types: byte, short, int, long, float, double, char, and boolean. Primitive variables represent primitive values according to Java's type system. Reference types represent objects and other reference-based entities such as arrays. A reference variable holds a reference to an object."**
>
> **"For example, in `int age = 26`, `age` represents the primitive value 26. In `Employee employee = new Employee()`, `employee` is a reference variable that refers to an Employee object. A reference can also contain null, whereas a primitive variable cannot."**
>
> **"This distinction becomes particularly important when we discuss parameter passing, object identity, equality, garbage collection, and collections."**

---

# 43. ⭐ Interview: Instance vs Static Variables

### Question

**What is the difference between instance and static variables?**

### Detailed Answer

> **"An instance variable is a non-static field associated with an individual object. Every object has its own instance state. A static variable, on the other hand, is associated with the class rather than a particular object and represents class-level state."**
>
> **"For example, in an Employee class, `name` would normally be an instance variable because each employee has a different name. A field such as `company` could be static if it represents a value shared at the class level. When we create two Employee objects, each object has its own name, while both access the class-level company field."**

---

# 44. ⭐ Interview: Does `final` Make an Object Immutable?

### Question

**Does final make an object immutable?**

### Detailed Answer

> **"No. The final modifier on a reference means that the reference cannot be reassigned after initialization. It does not automatically make the referenced object immutable."**
>
> **"For example, `final Employee employee = new Employee()` prevents us from assigning another Employee object to the variable `employee`. However, if Employee has mutable fields, those fields can still be modified. Therefore, immutability is a property of the object's class design, while final only prevents reassignment of the variable."**

---

# 45. ⭐ Interview: Why Don't Local Variables Have Default Values?

### Question

**Why does Java not provide default values for local variables?**

### Detailed Answer

> **"Java requires local variables to be definitely assigned before they are read. Local variables are temporary variables associated with a particular method or block execution, and requiring explicit initialization helps the compiler detect accidental use of an uninitialized value."**
>
> **"For example, if we declare `int x;` and immediately try to print `x`, the compiler rejects the program because it cannot prove that x has been assigned a value. This is different from instance fields, which receive default values as part of object initialization."**

---

# 46. ⭐ Important Mental Model

When you see:

```java
Employee employee = new Employee();
```

do not simply think:

> "`employee` is an object."

Think:

```text
Employee
   ↓
Reference Type

employee
   ↓
Reference Variable

new Employee()
   ↓
Object
```

When you see:

```java
int age = 26;
```

think:

```text
int
 ↓
Primitive Type

age
 ↓
Variable

26
 ↓
Primitive Value
```

This mental model will become extremely important when we study:

* Methods
* Parameter passing
* Classes and Objects
* Inheritance
* Polymorphism
* Strings
* Collections
* Garbage Collection
* Multithreading

---

# 47. Coding Practice

## Problem 1

Will this compile?

```java
public class Main {

    public static void main(String[] args) {

        int x;

        System.out.println(x);
    }
}
```

Explain why.

---

## Problem 2

What is the output?

```java
class Employee {

    String name;
}

public class Main {

    public static void main(String[] args) {

        Employee e1 = new Employee();
        Employee e2 = e1;

        e1.name = "Praveen";

        System.out.println(e2.name);
    }
}
```

Explain why.

---

## Problem 3

What happens?

```java
final int x = 10;

x = 20;
```

---

## Problem 4

What happens?

```java
final Employee employee = new Employee();

employee.name = "John";
```

Does `final` prevent this?

---

## Problem 5

What is the output?

```java
int x = Integer.MAX_VALUE;

x++;

System.out.println(x);
```

Explain the reason.

---

## Problem 6

Identify each variable:

```java
class Employee {

    static int employeeCount;

    int id;

    void display() {

        int salary = 50000;

        System.out.println(salary);
    }
}
```

Identify:

```text
employeeCount → ?
id            → ?
salary        → ?
```

---

# 48. Quick Revision

```text
VARIABLES
│
├── Local
│   ├── Inside method/block/constructor
│   └── Must be definitely assigned before use
│
├── Instance
│   ├── Belongs to object
│   └── Gets default value
│
└── Static
    ├── Belongs to class
    └── Class-level state
```

```text
DATA TYPES
│
├── Primitive
│   ├── byte
│   ├── short
│   ├── int
│   ├── long
│   ├── float
│   ├── double
│   ├── char
│   └── boolean
│
└── Reference
    ├── Class
    ├── Array
    ├── Interface
    ├── Enum
    └── Other reference types
```

### Remember

```text
Local
→ No default value

Instance
→ Default value

Static
→ Class-level

Primitive
→ 8 types
→ Cannot be null

Reference
→ Refers to object
→ Can be null

final
→ Cannot reassign variable

final reference
→ Reference cannot change
→ Object may still be mutable

Widening
→ Usually implicit

Narrowing
→ Usually explicit cast
→ May lose information

int
→ 32-bit signed integer

long
→ 64-bit signed integer

float
→ 32-bit IEEE 754

double
→ 64-bit IEEE 754

char
→ 16-bit UTF-16 code unit
```

---

# 49. MTS Interview Focus

For the MTS interview, make sure you can confidently explain:

* Primitive vs reference types
* Local vs instance vs static variables
* Default values
* Scope
* `final`
* `final` reference vs immutable object
* Widening and narrowing
* Integer overflow
* `char` and Unicode
* `float` vs `double`
* `int` vs `long`
* `null`
* Why local variables must be initialized

These concepts will repeatedly appear in later topics such as **OOP, Collections, Strings, Exception Handling, and Multithreading**.
