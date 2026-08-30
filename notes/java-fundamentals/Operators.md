# Java Operators

## 1. What is it?

An **operator** is a symbol that tells Java to perform an operation on one or more operands.

Example:

```java
int result = 10 + 20;
```

Here:

* `10` → operand
* `+` → operator
* `20` → operand
* `result` → stores the result

Java provides different categories of operators:

```text
Operators
│
├── Arithmetic
├── Unary
├── Relational
├── Logical
├── Assignment
├── Bitwise
├── Shift
├── Ternary
├── instanceof
└── Operator Precedence & Associativity
```

Operators are fundamental because they are used in almost every Java program, especially in:

* Conditions
* Loops
* Calculations
* DSA
* Searching
* Sorting
* Bit manipulation
* Business logic

---

# 2. Why do we need Operators?

Operators allow us to perform operations on data.

For example, in an employee application:

```java
double salary = 50000;
double bonus = 5000;

double totalSalary = salary + bonus;
```

The `+` operator allows us to calculate the total salary.

In an authentication system:

```java
if (age >= 18 && isVerified) {
    // allow access
}
```

Operators allow us to combine multiple conditions.

In DSA:

```java
if (number % 2 == 0) {
    // even number
}
```

The `%` operator is used to determine whether a number is even.

Therefore, operators provide the basic mechanism for manipulating and comparing values.

---

# 3. How does it work internally?

When Java encounters an expression such as:

```java
int result = 10 + 20;
```

the Java compiler analyzes the expression and determines:

1. The operands involved.
2. Their data types.
3. The applicable operator.
4. Whether type conversion or numeric promotion is required.
5. The resulting type.
6. Whether the expression is valid according to Java's rules.

The resulting bytecode is then executed by the JVM.

For example:

```java
int result = a + b;
```

For integer arithmetic, the JVM uses appropriate bytecode instructions to load the operands, perform the arithmetic operation, and store the result.

The important point for interviews is:

> Operators are not independent from Java's type system. The data types of the operands determine how an expression is evaluated and what type of result is produced.

For example:

```java
10 / 3
```

produces an `int`.

But:

```java
10 / 3.0
```

produces a `double`.

---

# 4. Arithmetic Operators

Arithmetic operators are used for mathematical calculations.

| Operator | Meaning             |
| -------- | ------------------- |
| `+`      | Addition            |
| `-`      | Subtraction         |
| `*`      | Multiplication      |
| `/`      | Division            |
| `%`      | Modulus / Remainder |

Example:

```java
int a = 20;
int b = 6;

System.out.println(a + b); // 26
System.out.println(a - b); // 14
System.out.println(a * b); // 120
System.out.println(a / b); // 3
System.out.println(a % b); // 2
```

---

## Addition

```java
int result = 10 + 20;
```

Result:

```text
30
```

The `+` operator also works with `String`.

```java
String name = "John";

System.out.println("Hello " + name);
```

Output:

```text
Hello John
```

When `+` involves a `String`, it can perform string concatenation.

Example:

```java
System.out.println("Age: " + 26);
```

Output:

```text
Age: 26
```

---

# 5. String Concatenation with `+`

Consider:

```java
System.out.println(10 + 20 + "Java");
```

Evaluation occurs from left to right:

```text
10 + 20
 ↓
30

30 + "Java"
 ↓
"30Java"
```

Output:

```text
30Java
```

Now consider:

```java
System.out.println("Java" + 10 + 20);
```

Evaluation:

```text
"Java" + 10
 ↓
"Java10"

"Java10" + 20
 ↓
"Java1020"
```

Output:

```text
Java1020
```

This is a common interview question.

---

# 6. Division Operator `/`

Division behaves according to the types of the operands.

```java
int result = 10 / 3;
```

Result:

```text
3
```

Because:

```text
int / int
   ↓
int
```

The fractional portion is discarded.

To obtain a decimal result:

```java
double result = 10 / 3.0;
```

Result:

```text
3.3333333333333335
```

Conceptually:

```text
int / double
     ↓
double
```

This is related to Java's numeric promotion rules.

---

# 7. Modulus `%`

The modulus operator returns the remainder of a division.

```java
int result = 10 % 3;
```

Result:

```text
1
```

Because:

```text
10 / 3 = 3 remainder 1
```

## DSA Example

Checking whether a number is even:

```java
if (number % 2 == 0) {
    System.out.println("Even");
}
```

Checking whether it is odd:

```java
if (number % 2 != 0) {
    System.out.println("Odd");
}
```

The modulus operator is heavily used in DSA.

Common applications:

* Even/odd checking
* Digit extraction
* Circular arrays
* Hashing
* Number reversal
* Digit-based problems

---

# 8. Division by Zero

Integer division by zero:

```java
int result = 10 / 0;
```

causes:

```text
ArithmeticException
```

However, floating-point division behaves differently.

```java
double result = 10.0 / 0.0;
```

Result:

```text
Infinity
```

And:

```java
double result = 0.0 / 0.0;
```

Result:

```text
NaN
```

`NaN` means:

> Not a Number

Important interview distinction:

```text
Integer / 0
→ ArithmeticException

Floating-point / 0.0
→ Infinity

0.0 / 0.0
→ NaN
```

---

# 9. Unary Operators

A unary operator works on one operand.

Examples:

```text
+
-
++
--
!
~
```

Example:

```java
int number = 10;

System.out.println(-number);
```

Result:

```text
-10
```

---

# 10. Increment Operator `++`

The increment operator increases a variable by `1`.

```java
int x = 10;

x++;
```

Now:

```text
x = 11
```

Equivalent conceptually to:

```java
x = x + 1;
```

---

# 11. Decrement Operator `--`

The decrement operator decreases a variable by `1`.

```java
int x = 10;

x--;
```

Now:

```text
x = 9
```

Conceptually:

```java
x = x - 1;
```

---

# 12. Prefix Increment

```java
int x = 10;

int result = ++x;
```

First `x` is incremented:

```text
x = 11
```

Then the value is assigned to `result`.

Therefore:

```text
x = 11
result = 11
```

General rule:

> Prefix changes the variable first, then uses the value.

---

# 13. Postfix Increment

```java
int x = 10;

int result = x++;
```

First the current value is used:

```text
result = 10
```

Then `x` is incremented:

```text
x = 11
```

General rule:

> Postfix uses the current value first, then changes the variable.

---

# 14. Prefix vs Postfix

| Expression | Value used | Variable after operation |
| ---------- | ---------: | -----------------------: |
| `++x`      |  New value |              Incremented |
| `x++`      |  Old value |              Incremented |
| `--x`      |  New value |              Decremented |
| `x--`      |  Old value |              Decremented |

Example:

```java
int x = 10;

System.out.println(x++);
```

Output:

```text
10
```

Afterward:

```text
x = 11
```

Example:

```java
int x = 10;

System.out.println(++x);
```

Output:

```text
11
```

---

# 15. Relational Operators

Relational operators compare values.

They return a `boolean`.

| Operator | Meaning               |
| -------- | --------------------- |
| `>`      | Greater than          |
| `<`      | Less than             |
| `>=`     | Greater than or equal |
| `<=`     | Less than or equal    |
| `==`     | Equal                 |
| `!=`     | Not equal             |

Example:

```java
int age = 26;

System.out.println(age > 18);
```

Result:

```text
true
```

---

# 16. `==` Operator

For primitive values, `==` compares values.

```java
int a = 10;
int b = 10;

System.out.println(a == b);
```

Result:

```text
true
```

For objects, `==` compares references, not object contents.

Example:

```java
String a = new String("Java");
String b = new String("Java");

System.out.println(a == b);
```

This is generally:

```text
false
```

because `a` and `b` refer to different String objects.

For content comparison:

```java
a.equals(b)
```

This distinction becomes very important in OOP and String interviews.

---

# 17. Logical Operators

Logical operators work with boolean expressions.

```text
&&    Logical AND
||    Logical OR
!     Logical NOT
```

Example:

```java
int age = 26;
boolean verified = true;

if (age >= 18 && verified) {
    System.out.println("Allowed");
}
```

Both conditions must be true.

---

# 18. Logical AND `&&`

The result is true only when both operands are true.

| A     | B     | A && B |
| ----- | ----- | ------ |
| true  | true  | true   |
| true  | false | false  |
| false | true  | false  |
| false | false | false  |

Example:

```java
age >= 18 && isVerified
```

means:

> The employee must be at least 18 AND verified.

---

# 19. Logical OR `||`

The result is true if at least one operand is true.

| A     | B     | A || B |
| ----- | ----- | ------ |
| true  | true  | true   |
| true  | false | true   |
| false | true  | true   |
| false | false | false  |

Example:

```java
isAdmin || isManager
```

means:

> The user can access the operation if they are an admin OR a manager.

---

# 20. Logical NOT `!`

NOT reverses a boolean value.

```java
boolean active = true;

System.out.println(!active);
```

Result:

```text
false
```

---

# 21. Short-Circuit Evaluation

This is extremely important for interviews.

Java's:

```text
&&
||
```

operators use short-circuit evaluation.

For `&&`:

If the first condition is false, Java does not evaluate the second condition.

Example:

```java
false && someMethod()
```

Java already knows the complete result must be:

```text
false
```

so `someMethod()` isn't evaluated.

For `||`:

If the first condition is true, Java does not evaluate the second condition.

```java
true || someMethod()
```

The result is already guaranteed to be:

```text
true
```

---

# 22. Why Short-Circuiting Matters

Consider:

```java
if (person != null && person.getName().equals("John")) {
    ...
}
```

If:

```java
person == null
```

the second expression isn't evaluated.

Therefore, the code avoids attempting:

```java
person.getName()
```

on a null reference.

This is a practical real-world use of short-circuit evaluation.

---

# 23. `&&` vs `&`

These are not always interchangeable.

```text
&& → logical AND with short-circuiting
&  → bitwise AND for integers
     or non-short-circuit boolean AND
```

Example:

```java
boolean result = condition1 && condition2;
```

Java may skip `condition2`.

With:

```java
boolean result = condition1 & condition2;
```

both operands are evaluated.

This distinction is frequently asked in interviews.

---

# 24. Assignment Operators

Basic assignment:

```java
int x = 10;
```

Compound assignment operators:

```text
+=
-=
*=
/=
%=
```

Example:

```java
int score = 100;

score += 10;
```

Conceptually:

```java
score = score + 10;
```

Result:

```text
110
```

---

# 25. Compound Assignment and Casting

This is an important Java rule.

Consider:

```java
byte x = 10;

x += 5;
```

This compiles.

Conceptually:

```java
x = (byte) (x + 5);
```

Compound assignment includes an implicit conversion back to the left-hand type.

But:

```java
byte x = 10;

x = x + 5;
```

does not compile because:

```text
x + 5
↓
int
```

and an `int` cannot be assigned to `byte` without conversion.

---

# 26. Ternary Operator

The ternary operator is Java's conditional operator.

Syntax:

```java
condition ? expression1 : expression2
```

Example:

```java
int age = 26;

String status = age >= 18 ? "Adult" : "Minor";
```

If the condition is true:

```text
Adult
```

Otherwise:

```text
Minor
```

It is useful for simple conditional assignments.

Avoid using deeply nested ternary expressions because they reduce readability.

---

# 27. Bitwise Operators

Bitwise operators work directly with individual bits of integer values.

```text
&    AND
|    OR
^    XOR
~    NOT
```

Example:

```java
int a = 5;
int b = 3;
```

Binary:

```text
5 = 0101
3 = 0011
```

### AND

```text
0101
0011
----
0001
```

Result:

```text
1
```

Therefore:

```java
5 & 3
```

produces:

```text
1
```

---

# 28. Bitwise OR `|`

```text
0101
0011
----
0111
```

Result:

```text
7
```

Therefore:

```java
5 | 3
```

produces:

```text
7
```

---

# 29. Bitwise XOR `^`

XOR produces `1` when the two bits are different.

```text
0101
0011
----
0110
```

Result:

```text
6
```

Therefore:

```java
5 ^ 3
```

produces:

```text
6
```

XOR is important in DSA and bit-manipulation problems.

---

# 30. Bitwise NOT `~`

`~` flips every bit.

For example:

```java
~5
```

does not simply mean:

```text
-5
```

For signed integers, Java uses two's-complement representation.

A useful identity is:

```text
~x = -(x + 1)
```

Therefore:

```text
~5 = -6
```

This is a common interview question.

---

# 31. Shift Operators

Java provides:

```text
<<
>>
>>>
```

### Left shift

```java
8 << 1
```

Binary:

```text
00001000
```

Shift left:

```text
00010000
```

Result:

```text
16
```

For many positive integer values, shifting left by one is equivalent to multiplying by 2, assuming no overflow occurs.

---

# 32. Right Shift `>>`

```java
8 >> 1
```

Result:

```text
4
```

For positive values, right shifting by one is generally equivalent to integer division by 2.

---

# 33. Unsigned Right Shift `>>>`

The difference becomes especially important for negative numbers.

```text
>>>
```

fills the leftmost bits with zero.

Whereas:

```text
>>
```

preserves the sign bit for signed integer types.

Example:

```java
int x = -8;

System.out.println(x >> 1);
System.out.println(x >>> 1);
```

The results are different.

This is an important bit-manipulation interview topic.

---

# 34. `instanceof`

`instanceof` checks whether an object reference is compatible with a particular type.

Example:

```java
Object value = "Java";

System.out.println(value instanceof String);
```

Result:

```text
true
```

It becomes especially important when learning:

* Inheritance
* Polymorphism
* Upcasting
* Downcasting

We'll study those topics deeply in OOP.

---

# 35. Operator Precedence

When an expression contains multiple operators, Java follows precedence rules.

Example:

```java
int result = 10 + 5 * 2;
```

Multiplication has higher precedence than addition.

Therefore:

```text
5 * 2 = 10

10 + 10 = 20
```

Result:

```text
20
```

It is equivalent to:

```java
int result = 10 + (5 * 2);
```

---

# 36. Parentheses

Parentheses can explicitly control evaluation.

```java
int result = (10 + 5) * 2;
```

First:

```text
10 + 5 = 15
```

Then:

```text
15 * 2 = 30
```

Result:

```text
30
```

Best practice:

> Use parentheses when they make an expression easier to understand, even when you technically don't need them.

---

# 37. Associativity

When operators have the same precedence, associativity determines evaluation direction.

Most arithmetic operators are evaluated left-to-right.

Example:

```java
int result = 20 / 5 * 2;
```

`/` and `*` have the same precedence.

Therefore:

```text
20 / 5
 ↓
4

4 * 2
 ↓
8
```

Result:

```text
8
```

It is not:

```text
20 / (5 * 2)
```

which would produce `2`.

---

# 38. Important Operator Traps

## Trap 1 — Integer division

```java
System.out.println(10 / 3);
```

Output:

```text
3
```

Not `3.333`.

---

## Trap 2 — Postfix vs Prefix

```java
int x = 10;

System.out.println(x++);
```

Output:

```text
10
```

Afterward:

```text
x = 11
```

But:

```java
int x = 10;

System.out.println(++x);
```

Output:

```text
11
```

---

## Trap 3 — `byte + byte`

```java
byte a = 10;
byte b = 20;

byte c = a + b;
```

Does not compile.

Reason:

```text
byte + byte
    ↓
   int
```

Correct:

```java
byte c = (byte) (a + b);
```

---

## Trap 4 — Constant expression

This compiles:

```java
byte c = 10 + 20;
```

because the compiler knows the constant result is `30`, which fits in a byte.

But this doesn't:

```java
byte a = 10;
byte b = 20;

byte c = a + b;
```

because the expression is promoted to `int`.

---

## Trap 5 — `==` with Strings

Do not use:

```java
String a = new String("Java");
String b = new String("Java");

if (a == b) {
    ...
}
```

to compare String contents.

Use:

```java
a.equals(b)
```

for content equality.

---

# 39. Real-World Example — Employee System

Suppose an employee has:

```java
double salary = 60000;
double bonusPercentage = 10;
```

Calculate the bonus:

```java
double bonus = salary * bonusPercentage / 100;
```

Then:

```java
double totalSalary = salary + bonus;
```

Operators allow the application to implement the business calculation.

---

# 40. Real-World Example — Authentication

Suppose a system requires:

* User must be active.
* User must be verified.
* User must be an admin OR manager.

```java
if (active && verified && (isAdmin || isManager)) {
    System.out.println("Access granted");
}
```

This single expression uses:

```text
&&
||
()
```

This is a realistic example of why operator knowledge matters in backend applications.

---

# 41. Real-World Example — DSA

Determine whether a number is even:

```java
int number = 42;

if (number % 2 == 0) {
    System.out.println("Even");
}
```

Here:

```text
%
```

finds the remainder and:

```text
==
```

compares it with zero.

This combination appears frequently in coding problems.

---

# 42. Interview Questions

## Basic

1. What is an operator?
2. What are the different categories of operators in Java?
3. What are arithmetic operators?
4. What is the difference between `/` and `%`?
5. What are unary operators?
6. What is the difference between prefix and postfix increment?
7. What are relational operators?
8. What are logical operators?
9. What is the ternary operator?
10. What is `instanceof`?

## Intermediate

11. Why does `10 / 3` produce `3`?
12. How can you get a decimal result from integer division?
13. What is short-circuit evaluation?
14. Difference between `&&` and `&`?
15. Difference between `||` and `|`?
16. Why does `byte + byte` result in `int`?
17. Why does `byte x = 10 + 20` compile?
18. What is operator precedence?
19. What is associativity?
20. What does `%` return?

## Advanced

21. What is binary numeric promotion?
22. What happens internally when `++x` is evaluated?
23. Difference between `==` and `.equals()`?
24. How does `~x` work?
25. Difference between `>>` and `>>>`?
26. What happens when integer division is performed by zero?
27. What happens when floating-point division is performed by zero?
28. Why can compound assignment compile where normal assignment doesn't?
29. How does `instanceof` work with inheritance?
30. Can operators be overloaded in Java?

---

# 43. Can Java Support Operator Overloading?

Java does **not** support user-defined operator overloading like C++.

For example, you cannot define your own behavior for:

```text
+
-
*
```

for custom classes.

However, Java has built-in special behavior for `+` with Strings.

Example:

```java
"Hello " + "World"
```

performs String concatenation.

---

# 44. ⭐ Detailed Interview Explanation

### Question: What are operators in Java?

A strong interview explanation:

> "Operators are symbols or constructs used to perform operations on one or more operands. Java provides several categories of operators, including arithmetic, unary, relational, logical, assignment, bitwise, shift, ternary, and `instanceof` operators."
>
> "Arithmetic operators are used for calculations, relational operators compare values and produce boolean results, logical operators combine boolean expressions, assignment operators assign or update values, and bitwise and shift operators operate at the bit level."
>
> "One important aspect of Java operators is that their behavior is influenced by the operands' data types. For example, `10 / 3` produces `3` because both operands are integers, while `10 / 3.0` produces a floating-point result. Java also performs numeric promotion during expressions, which is why adding two `byte` values produces an `int` result."
>
> "Java's logical `&&` and `||` operators use short-circuit evaluation. With `&&`, if the first condition is false, the second condition is not evaluated. With `||`, if the first condition is true, the second condition is skipped. This is useful both for performance and for safely evaluating conditions such as null checks."
>
> "Another important area is prefix and postfix increment. `++x` increments the variable before its value is used, whereas `x++` uses the original value first and increments it afterward."
>
> "Operator precedence and associativity determine how expressions containing multiple operators are evaluated. Parentheses can be used to explicitly control evaluation and improve readability."
>
> "Operators are fundamental to Java programming because they are used extensively in conditions, loops, calculations, business logic, and DSA algorithms."

---

# 45. ⭐ MTS Interview Example

The interviewer may ask:

```java
int x = 5;

System.out.println(x++ + ++x);
```

Don't try to memorize the answer.

Analyze it step-by-step.

Initial:

```text
x = 5
```

`x++`:

```text
uses 5
then x becomes 6
```

`++x`:

```text
x becomes 7
then uses 7
```

Therefore:

```text
5 + 7 = 12
```

Output:

```text
12
```

However, in production code, avoid writing expressions like this. They are unnecessarily difficult to read and easy to misunderstand.

---

# 46. Quick Revision

```text
ARITHMETIC
+  -  *  /  %

UNARY
+  -  ++  --

RELATIONAL
>  <  >=  <=  ==  !=

LOGICAL
&&  ||  !

ASSIGNMENT
=  +=  -=  *=  /=  %=

BITWISE
&  |  ^  ~

SHIFT
<<  >>  >>>

TERNARY
?:

TYPE CHECK
instanceof
```

### Remember these interview rules

```text
10 / 3
→ 3

10 / 3.0
→ 3.333...

10 % 3
→ 1

byte + byte
→ int

++x
→ increment first, use afterward

x++
→ use first, increment afterward

&&
→ short-circuit AND

||
→ short-circuit OR

&
→ bitwise AND / non-short-circuit boolean AND

|
→ bitwise OR / non-short-circuit boolean OR

== with primitives
→ value comparison

== with objects
→ reference comparison

.equals()
→ content/logical equality when implemented appropriately

~x
→ bitwise complement

>>>
→ unsigned right shift

?: 
→ conditional/ternary operator
```

---

# 47. Coding Practice

Complete these without looking at the solution.

### Problem 1 — Even or Odd

Write a program that determines whether a number is even or odd using `%`.

### Problem 2 — Maximum of Two Numbers

Find the larger of two numbers using the ternary operator.

### Problem 3 — Prefix/Postfix

Predict the output before running:

```java
int x = 5;

int a = x++;
int b = ++x;

System.out.println(x);
System.out.println(a);
System.out.println(b);
```

### Problem 4 — Expression

Predict:

```java
int result = 10 + 20 * 3 - 5;
```

### Problem 5 — Bitwise

Predict:

```java
int a = 5;
int b = 3;

System.out.println(a & b);
System.out.println(a | b);
System.out.println(a ^ b);
System.out.println(~a);
```

### Problem 6 — Short Circuit

Explain what happens:

```java
int x = 10;

boolean result =
        x < 5 && (10 / 0 > 1);
```

Does this throw an exception?

Why?

---

# 48. Final Interview Takeaway

The most important thing is not memorizing every operator.

You should understand:

```text
Operator
   ↓
Operand types
   ↓
Type promotion/conversion
   ↓
Precedence
   ↓
Evaluation order
   ↓
Result
```

When you see an unfamiliar expression in an interview, analyze it using these rules rather than guessing.
