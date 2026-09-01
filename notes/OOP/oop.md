# Object-Oriented Programming (OOP) in Java

## 1. What is OOP?

Object-Oriented Programming (OOP) is a programming approach where software is designed using **objects** that contain:

* **State** → data/properties
* **Behavior** → methods/actions

Java is primarily an object-oriented programming language.

Instead of thinking only in terms of functions and data separately, OOP allows us to model real-world entities as objects.

### Example

In an e-commerce system:

```text
Product
├── State
│   ├── productId
│   ├── name
│   ├── price
│   └── stock
│
└── Behavior
    ├── updatePrice()
    ├── reduceStock()
    └── displayDetails()
```

---

# 2. Why Do We Need OOP?

OOP helps us build software that is:

* Modular
* Reusable
* Maintainable
* Extensible
* Easier to test
* Easier to understand
* Better organized for large applications

For example, an e-commerce application may contain:

```text
User
Product
Cart
Order
Payment
Notification
Delivery
```

Each can be represented as a class with its own state and behavior.

---

# 3. The Four Pillars of OOP

The four commonly taught pillars are:

```text
                 OOP
                  │
       ┌──────────┼──────────┐
       ↓          ↓          ↓
Encapsulation  Inheritance  Abstraction
       │          │          │
       └──────────┼──────────┘
                  ↓
             Polymorphism
```

## 3.1 Encapsulation

> Bundling data and the methods that operate on that data while controlling access to the internal state.

Main concepts:

```text
private
public
protected
getters
setters
validation
```

Real-world example:

```text
BankAccount
    ↓
private balance
    ↓
deposit()
withdraw()
```

We don't allow arbitrary code to directly modify the balance.

Detailed note:

`Encapsulation.md`

---

# 3.2 Inheritance

Inheritance allows a class to acquire and extend behavior from another class.

Java uses:

```java
extends
```

Example:

```text
Vehicle
   ↓
Car
```

Meaning:

```text
Car IS-A Vehicle
```

Important concepts:

```text
extends
super
method overriding
IS-A relationship
constructor execution
```

Detailed note:

`Inheritance.md`

---

# 3.3 Polymorphism

Polymorphism means:

> One interface/reference can represent different implementations.

Two major forms:

```text
Polymorphism
│
├── Compile-Time
│   └── Method Overloading
│
└── Runtime
    └── Method Overriding
```

Example:

```java
Payment payment;

payment = new UpiPayment();
payment.process();

payment = new CardPayment();
payment.process();
```

The same method call:

```java
payment.process();
```

can execute different implementations.

Detailed note:

`Polymorphism.md`

---

# 3.4 Abstraction

Abstraction means:

> Exposing essential behavior while hiding implementation details.

Java provides abstraction through:

```text
abstract classes
interfaces
```

Example:

```java
abstract class Payment {

    abstract void processPayment();
}
```

The caller knows:

```text
processPayment()
```

but doesn't need to know the internal payment-processing implementation.

Detailed note:

`Abstraction.md`

---

# 4. Classes and Objects

A **class** is a blueprint.

An **object** is an instance created from that blueprint.

Example:

```java
class Product {

    String name;
    double price;
}
```

Creating an object:

```java
Product product =
        new Product();
```

Think:

```text
Class
 ↓
Blueprint

Object
 ↓
Actual instance
```

Detailed note:

`ClassesAndObjects.md`

---

# 5. Constructors

A constructor is used to initialize an object when it is created.

Example:

```java
class Product {

    String name;
    double price;

    Product(
            String name,
            double price
    ) {
        this.name = name;
        this.price = price;
    }
}
```

Object:

```java
Product product =
        new Product(
                "Laptop",
                65000
        );
```

Important concepts:

```text
constructor
this
default constructor
parameterized constructor
constructor overloading
constructor chaining
```

Detailed note:

`Constructors.md`

---

# 6. Interfaces

An interface defines a contract that implementing classes must follow.

Example:

```java
interface PaymentService {

    void processPayment(double amount);
}
```

Implementation:

```java
class UpiPayment
        implements PaymentService {

    @Override
    public void processPayment(
            double amount
    ) {

        System.out.println(
                "Processing UPI payment"
        );
    }
}
```

Important concepts:

```text
interface
implements
multiple interfaces
default methods
static methods
functional interfaces
```

Detailed note:

`Interfaces.md`

---

# 7. Composition

Composition represents a:

```text
HAS-A
```

relationship.

Example:

```text
Order
 ├── Customer
 ├── Payment
 ├── Address
 └── Notification
```

In Java:

```java
class Order {

    private Customer customer;
    private Payment payment;
}
```

This means:

```text
Order HAS-A Customer
Order HAS-A Payment
```

Composition is extremely important in professional Java development.

Detailed note:

`Composition.md`

---

# 8. IS-A vs HAS-A

One of the most important OOP design concepts.

## IS-A

Usually represents inheritance.

```text
Car IS-A Vehicle
```

Java:

```java
class Car extends Vehicle
```

## HAS-A

Usually represents composition.

```text
Order HAS-A Payment
```

Java:

```java
class Order {

    private Payment payment;
}
```

### Quick Rule

```text
IS-A
→ Inheritance

HAS-A
→ Composition
```

---

# 9. `this` and `super`

## `this`

Refers to the current object.

```java
this.name = name;
```

Used for:

* Accessing current object's fields
* Calling current class methods
* Calling another constructor using `this()`

## `super`

Refers to the parent-class portion of the object.

```java
super(name);
```

Used for:

* Calling parent constructor
* Accessing parent methods
* Accessing parent fields when accessible

---

# 10. Method Overloading vs Overriding

## Overloading

Same method name, different parameter list.

```java
add(int a, int b)

add(int a, int b, int c)
```

Usually considered:

```text
Compile-Time Polymorphism
```

## Overriding

Child class provides a new implementation of an inherited method.

```java
@Override
void processPayment() {
}
```

This enables:

```text
Runtime Polymorphism
```

---

# 11. OOP and Real-World System Design

We should not learn OOP only using artificial examples.

We'll apply OOP to actual systems.

### Banking

```text
BankAccount
Transaction
Customer
Payment
```

### E-Commerce

```text
Product
Cart
Order
Payment
Inventory
```

### Food Delivery

```text
Restaurant
Order
Customer
Payment
Delivery
Notification
```

### Hotel Booking

```text
Hotel
Room
Guest
Booking
Payment
```

### Ride Booking

```text
Driver
Passenger
Ride
Vehicle
Payment
```

These systems will help us understand why the OOP concepts actually exist.

---

# 12. OOP + Professional Java

OOP isn't just an interview topic.

It forms the foundation of frameworks and applications such as:

```text
Java
 ↓
OOP
 ↓
Collections
 ↓
Exception Handling
 ↓
Generics
 ↓
Java 8+
 ↓
Streams
 ↓
Multithreading
 ↓
JDBC
 ↓
Spring
 ↓
Spring Boot
 ↓
REST APIs
 ↓
Microservices
```

When we reach Spring Boot, concepts such as:

```text
Interfaces
Polymorphism
Abstraction
Composition
Dependency Injection
Loose Coupling
```

will become practical rather than theoretical.

---

# 13. Interview Perspective

When asked:

### "What are the four pillars of OOP?"

Answer:

> "The four fundamental principles of OOP are encapsulation, inheritance, polymorphism, and abstraction. Encapsulation protects and controls access to an object's state, inheritance allows a class to extend another class, polymorphism allows the same interface or reference to represent different implementations, and abstraction exposes essential behavior while hiding implementation details."

---

# 14. Important OOP Interview Questions

### Fundamentals

1. What is OOP?
2. Why do we use OOP?
3. What is a class?
4. What is an object?
5. What is the difference between class and object?
6. What is a constructor?
7. What is `this`?
8. What is `super`?

### Encapsulation

9. What is encapsulation?
10. Why are fields commonly declared `private`?
11. Are getters and setters always required?
12. Encapsulation vs abstraction?

### Inheritance

13. What is inheritance?
14. What is an IS-A relationship?
15. What is `extends`?
16. What is `super()`?
17. Why doesn't Java support multiple inheritance with classes?

### Polymorphism

18. What is polymorphism?
19. What is method overloading?
20. What is method overriding?
21. Overloading vs overriding?
22. What is dynamic method dispatch?
23. What is runtime polymorphism?

### Abstraction

24. What is abstraction?
25. What is an abstract class?
26. Can an abstract class have constructors?
27. Can an abstract class contain concrete methods?
28. Can we instantiate an abstract class?

### Interfaces

29. What is an interface?
30. What is `implements`?
31. Abstract class vs interface?
32. Can a class implement multiple interfaces?
33. What are default methods?
34. What are static methods in interfaces?
35. What is a functional interface?

### Design

36. IS-A vs HAS-A?
37. Inheritance vs composition?
38. Why is composition often preferred over inheritance?
39. What is loose coupling?
40. How does polymorphism help extensibility?

---

# 15. Our OOP Learning Order

We will study OOP in this order:

```text
01. Classes & Objects
        ↓
02. Constructors
        ↓
03. Encapsulation
        ↓
04. Inheritance
        ↓
05. Polymorphism
        ↓
06. Abstraction
        ↓
07. Interfaces
        ↓
08. Composition
        ↓
09. OOP Design Principles
        ↓
10. Real-World OOP Project
```

---

# 16. OOP Completion Criteria

We will consider OOP complete only when you can:

* Create classes and objects confidently
* Design constructors
* Explain encapsulation
* Use access modifiers correctly
* Implement inheritance
* Explain `this` and `super`
* Override methods
* Explain runtime polymorphism
* Design abstract classes
* Create and implement interfaces
* Use multiple interfaces
* Explain composition
* Distinguish IS-A and HAS-A
* Explain inheritance vs composition
* Design a small real-world system using OOP

Most importantly:

> **You should be able to look at a real system and decide where classes, interfaces, inheritance, composition, abstraction, and polymorphism belong.**

---

# 17. OOP Checklist

```text
Classes & Objects          ⬜
Constructors               ⬜
Encapsulation              ⬜
Access Modifiers           ⬜
this                       ⬜
Inheritance                ⬜
extends                    ⬜
super                      ⬜
Method Overloading         ⬜
Method Overriding          ⬜
Polymorphism               ⬜
Dynamic Dispatch            ⬜
Abstraction                ⬜
Abstract Classes           ⬜
Interfaces                 ⬜
implements                 ⬜
Default Methods            ⬜
Static Interface Methods   ⬜
Composition                ⬜
IS-A vs HAS-A              ⬜
Inheritance vs Composition ⬜
OOP Design                 ⬜
Real-World Project         ⬜
```

---

# Final Mental Model

```text
CLASS
  ↓
OBJECT
  ↓
ENCAPSULATION
  ↓
INHERITANCE
  ↓
POLYMORPHISM
  ↓
ABSTRACTION
  ↓
INTERFACES
  ↓
COMPOSITION
  ↓
GOOD OBJECT-ORIENTED DESIGN
```

This is the **OOP roadmap** for the Java Mastery project.
