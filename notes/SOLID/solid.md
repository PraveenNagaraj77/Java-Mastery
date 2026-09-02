# SOLID Principles

## Overview

**SOLID** is a set of five object-oriented design principles that help us write software that is:

* Maintainable
* Flexible
* Scalable
* Testable
* Loosely coupled
* Easier to extend

SOLID stands for:

| Letter | Principle                       | Main Idea                                            |
| ------ | ------------------------------- | ---------------------------------------------------- |
| **S**  | Single Responsibility Principle | One class → one responsibility                       |
| **O**  | Open/Closed Principle           | Extend behavior without modifying existing code      |
| **L**  | Liskov Substitution Principle   | Child should safely replace parent                   |
| **I**  | Interface Segregation Principle | Don't force classes to implement unnecessary methods |
| **D**  | Dependency Inversion Principle  | Depend on abstractions, not concrete implementations |

---

# 1. Single Responsibility Principle — SRP

## What is it?

> A class should have one responsibility and one reason to change.

A class should focus on one logical responsibility.

### ❌ Bad Design

```java
class OrderService {

    public void createOrder() {
        System.out.println("Order created");
    }

    public void calculatePrice() {
        System.out.println("Calculating price");
    }

    public void sendEmail() {
        System.out.println("Sending email");
    }

    public void saveToDatabase() {
        System.out.println("Saving order");
    }
}
```

This class has multiple responsibilities:

```text
Order creation
Price calculation
Email notification
Database persistence
```

### ✅ Better Design

```java
class OrderService {

    public void createOrder() {
        System.out.println("Order created");
    }
}
```

```java
class PriceCalculator {

    public void calculatePrice() {
        System.out.println("Calculating price");
    }
}
```

```java
class EmailService {

    public void sendEmail() {
        System.out.println("Sending email");
    }
}
```

```java
class OrderRepository {

    public void save() {
        System.out.println("Saving order");
    }
}
```

Each class now has a clear responsibility.

## Why is SRP needed?

Without SRP:

* Classes become large
* Code becomes difficult to maintain
* Changes become risky
* Testing becomes difficult
* Responsibilities become tightly coupled

## Important Point

SRP does **not** mean:

> One class must contain only one method.

It means:

> One class should have one responsibility and one reason to change.

---

# 2. Open/Closed Principle — OCP

## What is it?

> Software entities should be open for extension but closed for modification.

In simple words:

> We should be able to add new behavior without repeatedly modifying existing stable code.

### ❌ Bad Design

```java
class PaymentService {

    public void pay(String type, double amount) {

        if (type.equals("UPI")) {
            System.out.println("UPI payment");
        }
        else if (type.equals("CARD")) {
            System.out.println("Card payment");
        }
        else if (type.equals("CASH")) {
            System.out.println("Cash payment");
        }
    }
}
```

If we add Net Banking:

```java
else if (type.equals("NET_BANKING")) {
    ...
}
```

The existing class must be modified.

### ✅ Better Design

Create an abstraction:

```java
interface Payment {

    void pay(double amount);
}
```

```java
class UpiPayment implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("UPI payment");
    }
}
```

```java
class CardPayment implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("Card payment");
    }
}
```

Now add:

```java
class NetBankingPayment implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("Net Banking payment");
    }
}
```

Existing implementations don't need to change.

## Why is OCP needed?

It helps us:

* Add new functionality safely
* Reduce modification of existing code
* Reduce regression bugs
* Improve maintainability
* Support changing business requirements

## Key Idea

```text
Existing code
     ↓
Don't modify unnecessarily
     ↓
Extend using new implementations
```

---

# 3. Liskov Substitution Principle — LSP

## What is it?

> Objects of a child class should be replaceable with objects of the parent class without breaking the expected behavior of the program.

Simple version:

> A child class should properly honor the contract of its parent.

### ❌ Bad Design

```java
class Bird {

    public void fly() {
        System.out.println("Flying");
    }
}
```

```java
class Sparrow extends Bird {

    @Override
    public void fly() {
        System.out.println("Sparrow flying");
    }
}
```

This works.

But:

```java
class Penguin extends Bird {

    @Override
    public void fly() {
        throw new UnsupportedOperationException(
                "Penguins cannot fly"
        );
    }
}
```

Now:

```java
Bird bird = new Penguin();

bird.fly();
```

The program breaks.

The parent contract suggests:

```text
Bird → fly()
```

But Penguin cannot fulfill that behavior.

Therefore the inheritance design is wrong.

### ✅ Better Design

```java
class Bird {
}
```

```java
interface FlyingBird {

    void fly();
}
```

```java
class Sparrow extends Bird implements FlyingBird {

    @Override
    public void fly() {
        System.out.println("Sparrow flying");
    }
}
```

```java
class Penguin extends Bird {
}
```

Now only birds capable of flying implement `FlyingBird`.

## Why is LSP needed?

LSP helps us:

* Design correct inheritance hierarchies
* Avoid unexpected behavior
* Prevent runtime failures
* Make polymorphism reliable

## Key Question

Whenever you use inheritance, ask:

> "Can the child safely replace the parent?"

If the answer is no, reconsider the inheritance relationship.

---

# 4. Interface Segregation Principle — ISP

## What is it?

> Clients should not be forced to depend on methods they do not use.

Simple version:

> Prefer small, focused interfaces over large interfaces.

### ❌ Bad Design

```java
interface Machine {

    void print();

    void scan();

    void fax();
}
```

A simple printer only supports printing:

```java
class SimplePrinter implements Machine {

    @Override
    public void print() {
        System.out.println("Printing");
    }

    @Override
    public void scan() {
        // Not supported
    }

    @Override
    public void fax() {
        // Not supported
    }
}
```

The printer is forced to implement unnecessary methods.

### ✅ Better Design

```java
interface Printer {

    void print();
}
```

```java
interface Scanner {

    void scan();
}
```

```java
interface Fax {

    void fax();
}
```

Simple printer:

```java
class SimplePrinter implements Printer {

    @Override
    public void print() {
        System.out.println("Printing");
    }
}
```

Multifunction printer:

```java
class MultiFunctionPrinter
        implements Printer, Scanner, Fax {

    @Override
    public void print() {
        System.out.println("Printing");
    }

    @Override
    public void scan() {
        System.out.println("Scanning");
    }

    @Override
    public void fax() {
        System.out.println("Faxing");
    }
}
```

## Why is ISP needed?

It helps us:

* Keep interfaces small
* Avoid unnecessary implementations
* Reduce coupling
* Improve maintainability
* Make implementations easier to understand

## Key Idea

```text
❌ Large interface
      ↓
Many unnecessary methods

✅ Small interfaces
      ↓
Only required capabilities
```

---

# 5. Dependency Inversion Principle — DIP

## What is it?

> High-level modules should not depend directly on low-level modules. Both should depend on abstractions.

And:

> Abstractions should not depend on details. Details should depend on abstractions.

### ❌ Bad Design

```java
class MySQLDatabase {

    public void save() {
        System.out.println("Saving to MySQL");
    }
}
```

```java
class OrderService {

    private MySQLDatabase database =
            new MySQLDatabase();

    public void createOrder() {

        System.out.println("Creating order");

        database.save();
    }
}
```

Dependency:

```text
OrderService
     ↓
MySQLDatabase
```

`OrderService` is tightly coupled to MySQL.

### Problem

If we change MySQL to PostgreSQL, `OrderService` must be modified.

---

## ✅ Better Design

Create an abstraction:

```java
interface OrderRepository {

    void save();
}
```

MySQL implementation:

```java
class MySQLRepository implements OrderRepository {

    @Override
    public void save() {
        System.out.println("Saving to MySQL");
    }
}
```

PostgreSQL implementation:

```java
class PostgreSQLRepository implements OrderRepository {

    @Override
    public void save() {
        System.out.println("Saving to PostgreSQL");
    }
}
```

Now:

```java
class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public void createOrder() {

        System.out.println("Creating order");

        repository.save();
    }
}
```

Now:

```java
OrderRepository repository =
        new MySQLRepository();

OrderService service =
        new OrderService(repository);
```

We can switch to:

```java
OrderRepository repository =
        new PostgreSQLRepository();
```

without modifying `OrderService`.

## Why is DIP needed?

It helps us:

* Reduce tight coupling
* Make code easier to test
* Make implementations replaceable
* Support changing technologies
* Improve flexibility

---

# DIP vs Dependency Injection

These two concepts are related but **not the same**.

### Dependency Inversion Principle

A **design principle**:

> High-level code should depend on abstractions rather than concrete implementations.

### Dependency Injection

A **technique** for providing dependencies from outside the class.

Example:

```java
public OrderService(OrderRepository repository) {
    this.repository = repository;
}
```

Spring Boot commonly uses Dependency Injection to implement this style of design.

```text
Dependency Inversion
        ↓
Design principle

Dependency Injection
        ↓
Technique
```

---

# SOLID Summary

| Principle   | Remember This                      |
| ----------- | ---------------------------------- |
| **S — SRP** | One responsibility                 |
| **O — OCP** | Extend, don't unnecessarily modify |
| **L — LSP** | Child must honor parent contract   |
| **I — ISP** | Small, focused interfaces          |
| **D — DIP** | Depend on abstractions             |

---

# SOLID in One Example

Imagine an e-commerce application.

```text
                    SOLID
                      │
        ┌─────────────┼─────────────┐
        │             │             │
       SRP           OCP           LSP
        │             │             │
Separate          Add new        Safe child
responsibilities  payment        implementations
        │          methods            │
        │             │               │
        └─────────────┼───────────────┘
                      │
                 ISP + DIP
                      │
              Focused interfaces
              + loose coupling
```

---

# Why SOLID Matters in Real Applications

SOLID becomes especially important as an application grows.

Without good design:

```text
Small application
      ↓
More features
      ↓
More conditions
      ↓
Large classes
      ↓
Tight coupling
      ↓
Difficult maintenance
```

With SOLID:

```text
New requirement
      ↓
New implementation
      ↓
Existing code remains stable
      ↓
Easier testing
      ↓
Easier maintenance
```

SOLID is particularly useful in:

* Spring Boot applications
* REST APIs
* Microservices
* Enterprise applications
* Large team projects
* Systems with frequently changing requirements

---

# Interview Theory

## Q1. What is SOLID?

**Answer:**

SOLID is a collection of five object-oriented design principles that help developers create maintainable, flexible, scalable, testable, and loosely coupled software.

---

## Q2. What does SOLID stand for?

**Answer:**

```text
S → Single Responsibility Principle
O → Open/Closed Principle
L → Liskov Substitution Principle
I → Interface Segregation Principle
D → Dependency Inversion Principle
```

---

## Q3. Explain SRP.

**Answer:**

> A class should have one responsibility and one reason to change. It helps keep classes focused and makes the code easier to maintain and test.

---

## Q4. Does SRP mean one method per class?

**Answer:**

No.

SRP means **one responsibility**, not one method.

A class can have multiple methods as long as they belong to the same responsibility.

---

## Q5. Explain OCP.

**Answer:**

> The Open/Closed Principle says that software should be open for extension but closed for modification. New behavior should preferably be added through new implementations rather than repeatedly modifying existing stable code.

---

## Q6. How do interfaces help achieve OCP?

**Answer:**

Interfaces allow us to define a common abstraction and add new implementations without modifying the code that depends on the abstraction.

Example:

```java
interface Payment {
    void pay(double amount);
}
```

We can add:

```text
UpiPayment
CardPayment
CashPayment
NetBankingPayment
```

without changing the `Payment` interface users.

---

## Q7. Explain LSP.

**Answer:**

> Liskov Substitution Principle states that a subclass should be replaceable for its parent without breaking the expected behavior of the application.

The child should honor the parent's contract.

---

## Q8. Give a common example of LSP violation.

**Answer:**

The classic example is a `Penguin` extending a `Bird` class where `Bird` requires a `fly()` method.

Since a penguin cannot fly, substituting `Penguin` for `Bird` causes unexpected behavior.

The inheritance hierarchy should be redesigned.

---

## Q9. Explain ISP.

**Answer:**

> Interface Segregation Principle says that a class should not be forced to depend on methods it does not use. We should prefer small and focused interfaces.

---

## Q10. Explain DIP.

**Answer:**

> Dependency Inversion Principle says that high-level modules should not depend directly on low-level concrete implementations. Both should depend on abstractions.

---

## Q11. What is the difference between DIP and Dependency Injection?

**Answer:**

**DIP** is a design principle.

**Dependency Injection** is a technique used to provide dependencies from outside a class.

Spring Boot commonly uses Dependency Injection to achieve loosely coupled designs.

---

## Q12. How does Spring Boot relate to SOLID?

Spring Boot applications commonly use:

```text
Interfaces
     ↓
Dependency Injection
     ↓
Loose coupling
     ↓
Easier testing
     ↓
SOLID-friendly architecture
```

For example:

```java
@Service
class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }
}
```

`UserService` depends on the `UserRepository` abstraction instead of creating a concrete database implementation itself.

---

# Interview Quick Revision

Before an interview, remember these five sentences:

### S

> **One class should have one responsibility and one reason to change.**

### O

> **Add new behavior by extension rather than modifying stable existing code.**

### L

> **A child should be safely replaceable for its parent.**

### I

> **Don't force a class to implement methods it doesn't need.**

### D

> **Depend on abstractions rather than concrete implementations.**

---

# Final Mental Model

```text
S → Keep classes focused
O → Make extensions easy
L → Make inheritance safe
I → Keep interfaces focused
D → Keep dependencies loosely coupled
```

Together:

```text
             SOLID
               ↓
        Better OOP Design
               ↓
        Loose Coupling
               ↓
       Easier Maintenance
               ↓
         Easier Testing
               ↓
       Easier Extension
```

**SOLID is not a set of rules that must be applied mechanically to every class.**

The goal is to recognize **design problems** and use these principles to make the code easier to change and maintain.
