# Inheritance in Java

## 1. What is Inheritance?

**Inheritance** is an OOP mechanism where one class acquires properties and behavior from another class.

In Java, inheritance is implemented using:

```java
extends
```

Example:

```java
class Payment {

    protected double amount;

    void processPayment() {

        System.out.println(
                "Processing payment"
        );
    }
}
```

A child class:

```java
class UpiPayment extends Payment {

    void processUpiPayment() {

        System.out.println(
                "Processing UPI payment"
        );
    }
}
```

Now:

```text
Payment
   ↑
   │ extends
   │
UpiPayment
```

`UpiPayment` inherits accessible members from `Payment`.

---

# 2. Why Do We Need Inheritance?

Suppose our payment system supports:

```text
UPI
Credit Card
Debit Card
Net Banking
```

All payments have common information:

```text
amount
transactionId
paymentStatus
processPayment()
refund()
```

Without inheritance, we may repeat this code.

Inheritance allows us to put common functionality into a parent class.

```text
                 Payment
                    │
       ┌────────────┼────────────┐
       ↓            ↓            ↓
   UpiPayment   CardPayment   NetBanking
```

This reduces duplication and gives us a common type.

---

# 3. Parent Class

The class whose members are inherited is called the:

* Parent class
* Superclass
* Base class

Example:

```java
class Payment {

    double amount;

    void processPayment() {

        System.out.println(
                "Processing payment"
        );
    }
}
```

Here:

```text
Payment
```

is the parent class.

---

# 4. Child Class

The class that inherits from another class is called:

* Child class
* Subclass
* Derived class

Example:

```java
class UpiPayment extends Payment {

    String upiId;
}
```

Here:

```text
UpiPayment
```

is the child class.

---

# 5. Basic Example

```java
class Payment {

    double amount;

    void showAmount() {

        System.out.println(
                "Amount: " + amount
        );
    }
}
```

Child:

```java
class UpiPayment extends Payment {

    String upiId;

    void showUpiId() {

        System.out.println(
                "UPI ID: " + upiId
        );
    }
}
```

Usage:

```java
public class Main {

    public static void main(String[] args) {

        UpiPayment payment =
                new UpiPayment();

        payment.amount = 1500;
        payment.upiId = "user@upi";

        payment.showAmount();
        payment.showUpiId();
    }
}
```

Output:

```text
Amount: 1500.0
UPI ID: user@upi
```

`UpiPayment` can use:

```text
amount
showAmount()
```

from `Payment`.

And it has its own:

```text
upiId
showUpiId()
```

---

# 6. IS-A Relationship

Inheritance represents an **IS-A relationship**.

For example:

```text
UpiPayment IS-A Payment
```

```text
CardPayment IS-A Payment
```

```text
AdminUser IS-A User
```

```text
Manager IS-A Employee
```

The question to ask is:

> "Is the child truly a specialized form of the parent?"

If yes, inheritance may be appropriate.

---

# 7. HAS-A vs IS-A

This is extremely important for professional Java development.

### IS-A

Inheritance:

```text
UpiPayment IS-A Payment
```

Usually:

```java
class UpiPayment extends Payment
```

### HAS-A

Composition:

```text
Order HAS-A Payment
```

Usually:

```java
class Order {

    private Payment payment;
}
```

Don't use inheritance simply because two classes have common fields.

We'll study **Composition** separately.

---

# 8. What Gets Inherited?

A child class can inherit accessible members of its parent.

For example:

```java
class Payment {

    protected double amount;

    protected void process() {
    }
}
```

Child:

```java
class UpiPayment extends Payment {
}
```

The child can use:

```java
amount
process()
```

---

# 9. Private Members

Private members are not directly accessible from the child class.

Example:

```java
class Payment {

    private double amount;
}
```

Child:

```java
class UpiPayment extends Payment {

    void display() {

        System.out.println(amount);
    }
}
```

This doesn't compile.

Why?

Because:

```text
private
```

means direct access is restricted to the class that declares the member.

This is where **encapsulation and inheritance** meet.

---

# 10. Protected Members

`protected` allows access:

* Within the same package
* From subclasses, including subclasses in another package

Example:

```java
class Payment {

    protected double amount;
}
```

Child:

```java
class UpiPayment extends Payment {

    void display() {

        System.out.println(amount);
    }
}
```

This works.

However, don't make everything `protected` just to make inheritance convenient.

In professional code, prefer the narrowest access that makes sense.

---

# 11. Constructor and Inheritance

Constructors are **not inherited**.

Example:

```java
class Payment {

    Payment(double amount) {

        System.out.println(
                "Payment constructor"
        );
    }
}
```

Child:

```java
class UpiPayment extends Payment {

    UpiPayment(double amount) {

        System.out.println(
                "UPI constructor"
        );
    }
}
```

This will not compile because the parent has no no-argument constructor.

We need:

```java
super(amount);
```

---

# 12. `super()`

`super()` is used to invoke the parent class constructor.

Example:

```java
class Payment {

    protected double amount;

    Payment(double amount) {

        this.amount = amount;
    }
}
```

Child:

```java
class UpiPayment extends Payment {

    private String upiId;

    UpiPayment(
            double amount,
            String upiId
    ) {

        super(amount);

        this.upiId = upiId;
    }
}
```

Execution:

```text
new UpiPayment(...)
        ↓
UpiPayment constructor
        ↓
super(amount)
        ↓
Payment constructor
        ↓
Parent state initialized
        ↓
Child state initialized
```

---

# 13. Implicit `super()`

If the parent has a no-argument constructor:

```java
class Payment {

    Payment() {

        System.out.println(
                "Payment constructor"
        );
    }
}
```

Child:

```java
class UpiPayment extends Payment {

    UpiPayment() {

        System.out.println(
                "UPI constructor"
        );
    }
}
```

Java conceptually inserts:

```java
super();
```

at the beginning of the child constructor.

So:

```java
UpiPayment()
```

is conceptually:

```java
UpiPayment() {

    super();

    System.out.println(
            "UPI constructor"
    );
}
```

---

# 14. Constructor Execution Order

Consider:

```java
class Payment {

    Payment() {

        System.out.println(
                "Payment"
        );
    }
}
```

```java
class UpiPayment extends Payment {

    UpiPayment() {

        System.out.println(
                "UPI"
        );
    }
}
```

Create:

```java
new UpiPayment();
```

Output:

```text
Payment
UPI
```

Parent constructor executes first.

---

# 15. Multi-Level Inheritance

Java supports inheritance across multiple levels.

Example:

```text
Payment
   ↑
DigitalPayment
   ↑
UpiPayment
```

Code:

```java
class Payment {

}
```

```java
class DigitalPayment
        extends Payment {

}
```

```java
class UpiPayment
        extends DigitalPayment {

}
```

Now:

```text
UpiPayment
     ↓
DigitalPayment
     ↓
Payment
```

`UpiPayment` is indirectly derived from `Payment`.

---

# 16. Constructor Order in Multi-Level Inheritance

```java
class Payment {

    Payment() {

        System.out.println(
                "Payment"
        );
    }
}
```

```java
class DigitalPayment
        extends Payment {

    DigitalPayment() {

        System.out.println(
                "DigitalPayment"
        );
    }
}
```

```java
class UpiPayment
        extends DigitalPayment {

    UpiPayment() {

        System.out.println(
                "UpiPayment"
        );
    }
}
```

Create:

```java
new UpiPayment();
```

Output:

```text
Payment
DigitalPayment
UpiPayment
```

The chain executes from parent to child.

---

# 17. Method Inheritance

A child can use inherited methods.

```java
class Payment {

    void validatePayment() {

        System.out.println(
                "Validating payment"
        );
    }
}
```

```java
class UpiPayment extends Payment {
}
```

Then:

```java
UpiPayment payment =
        new UpiPayment();

payment.validatePayment();
```

The child can use the inherited behavior.

---

# 18. Method Overriding

A child can provide its own implementation of an inherited method.

Parent:

```java
class Payment {

    void process() {

        System.out.println(
                "Generic payment"
        );
    }
}
```

Child:

```java
class UpiPayment extends Payment {

    @Override
    void process() {

        System.out.println(
                "Processing UPI payment"
        );
    }
}
```

Now:

```java
UpiPayment payment =
        new UpiPayment();

payment.process();
```

Output:

```text
Processing UPI payment
```

This is called **method overriding**.

We'll study this deeply in `Polymorphism.md`.

---

# 19. Why `@Override`?

Use:

```java
@Override
```

when overriding a method.

Example:

```java
@Override
void process() {
}
```

It tells the compiler:

> "I intend to override a parent method."

If you accidentally make a mistake in the method signature, the compiler can detect it.

Without `@Override`, such mistakes can be harder to notice.

Professional Java code should generally use `@Override` when overriding.

---

# 20. `super.method()`

A child can call the parent implementation using:

```java
super.method();
```

Example:

```java
class Payment {

    void process() {

        System.out.println(
                "Generic payment validation"
        );
    }
}
```

Child:

```java
class UpiPayment extends Payment {

    @Override
    void process() {

        super.process();

        System.out.println(
                "UPI-specific processing"
        );
    }
}
```

Output:

```text
Generic payment validation
UPI-specific processing
```

This is useful when the child wants to **extend** parent behavior rather than completely replace it.

---

# 21. `super` vs `this`

Very important.

### `this`

Refers to the current object.

```java
this.amount
```

or:

```java
this();
```

calls another constructor in the same class.

### `super`

Refers to the parent portion/class.

```java
super.amount
```

or:

```java
super();
```

calls the parent constructor.

or:

```java
super.process();
```

calls the parent method.

Quick comparison:

```text
this
 ↓
Current class/object

super
 ↓
Parent class
```

---

# 22. Upcasting

A child object can be assigned to a parent reference.

Example:

```java
Payment payment =
        new UpiPayment();
```

This is called **upcasting**.

Conceptually:

```text
UpiPayment object
       ↓
Payment reference
```

Why is this useful?

Because we can work with different payment types through a common parent type.

```java
Payment p1 =
        new UpiPayment();

Payment p2 =
        new CardPayment();

Payment p3 =
        new NetBankingPayment();
```

This becomes extremely important when we study polymorphism.

---

# 23. Downcasting

We can explicitly cast a parent reference back to a child type.

```java
Payment payment =
        new UpiPayment();

UpiPayment upi =
        (UpiPayment) payment;
```

This is called downcasting.

But it can be dangerous.

Consider:

```java
Payment payment =
        new CardPayment();

UpiPayment upi =
        (UpiPayment) payment;
```

This causes:

```text
ClassCastException
```

because the actual object is a `CardPayment`.

---

# 24. `instanceof`

Before downcasting, we can check the actual type.

```java
if (payment instanceof UpiPayment) {

    UpiPayment upi =
            (UpiPayment) payment;
}
```

Modern Java also supports pattern matching:

```java
if (payment instanceof UpiPayment upi) {

    upi.processUpi();
}
```

This is cleaner and safer.

---

# 25. Java Supports Single Class Inheritance

A Java class can directly extend only one class.

Valid:

```java
class UpiPayment
        extends Payment {
}
```

Invalid:

```java
class UpiPayment
        extends Payment, DigitalPayment {
}
```

Java does not support multiple inheritance of classes.

---

# 26. Why Doesn't Java Support Multiple Class Inheritance?

Consider:

```text
        Payment
        /     \
       /       \
OnlinePayment  DigitalPayment
       \       /
        \     /
        UpiPayment
```

If both parents provide:

```java
void process()
```

which implementation should `UpiPayment` inherit?

This creates ambiguity.

Java avoids this class-level ambiguity by allowing a class to extend only one class.

Multiple behavior contracts can instead be modeled with **interfaces**.

We'll study that later.

---

# 27. Object Is the Root Class

All Java classes ultimately derive from:

```java
Object
```

For example:

```text
Object
   ↑
Payment
   ↑
UpiPayment
```

Therefore, methods from `Object` such as:

```text
toString()
equals()
hashCode()
getClass()
```

are available to objects.

We'll study these carefully later.

---

# 28. Final Classes and Inheritance

A `final` class cannot be extended.

Example:

```java
final class PaymentProcessor {
}
```

This is invalid:

```java
class CustomProcessor
        extends PaymentProcessor {
}
```

because the parent is `final`.

Java uses this when a class should not be subclassed.

A famous example is:

```java
String
```

`String` is final.

---

# 29. Final Methods

A final method cannot be overridden.

```java
class Payment {

    final void validate() {

        System.out.println(
                "Standard validation"
        );
    }
}
```

Child:

```java
class UpiPayment extends Payment {

    // ❌ Cannot override validate()
}
```

This can be useful when a specific behavior must remain unchanged.

---

# 30. Inheritance + Encapsulation

Inheritance and encapsulation must work together.

Bad:

```java
class Payment {

    public double amount;
}
```

Child:

```java
class UpiPayment extends Payment {

    void changeAmount() {

        amount = -5000;
    }
}
```

The inherited field can be corrupted.

Better:

```java
class Payment {

    private double amount;

    protected double getAmount() {

        return amount;
    }

    protected void setAmount(
            double amount
    ) {

        if (amount < 0) {

            throw new IllegalArgumentException(
                    "Amount cannot be negative"
            );
        }

        this.amount = amount;
    }
}
```

The parent maintains control over its state.

---

# 31. Real-Time Payment Architecture

A simple payment hierarchy could look like:

```text
                    Payment
                       │
          ┌────────────┼────────────┐
          ↓            ↓            ↓
     UpiPayment    CardPayment   BankTransfer
          │            │
          ↓            ↓
     upiId           cardNumber
```

Common state:

```text
amount
transactionId
status
```

Common behavior:

```text
validate()
refund()
```

Specialized behavior:

```text
processUpi()
processCard()
processBankTransfer()
```

This is a natural candidate for inheritance.

However, in a real production system, we'd carefully evaluate whether inheritance is actually the best design. Often **interfaces + composition** provide more flexibility.

---

# 32. When Should You Use Inheritance?

Inheritance is appropriate when:

### 1. There is a genuine IS-A relationship

```text
UpiPayment IS-A Payment
```

### 2. The child genuinely specializes the parent

```text
CardPayment
```

is a specialized form of:

```text
Payment
```

### 3. Shared behavior has meaningful semantic ownership

The common behavior genuinely belongs to the parent abstraction.

### 4. Substitution makes sense

A child should be usable wherever the parent is expected.

This leads into the **Liskov Substitution Principle**, which we'll discuss later under SOLID principles.

---

# 33. When Should You NOT Use Inheritance?

Don't use inheritance just because:

```text
Class A
Class B
```

have common fields.

For example:

```text
Order
Payment
```

might both have:

```text
id
createdAt
```

That doesn't mean:

```text
Order extends Payment
```

Clearly:

```text
Order IS-A Payment
```

is false.

Instead:

```text
Order HAS-A Payment
```

This is composition.

---

# 34. Inheritance vs Composition

### Inheritance

```java
class UpiPayment
        extends Payment {
}
```

Relationship:

```text
IS-A
```

### Composition

```java
class Order {

    private Payment payment;
}
```

Relationship:

```text
HAS-A
```

Professional Java development often favors **composition over inheritance** when inheritance doesn't represent a strong domain relationship.

---

# 35. Common Mistakes

### Mistake 1 — Thinking constructors are inherited

They are not.

```text
Constructors
→ Not inherited
```

---

### Mistake 2 — Using inheritance for code reuse only

Don't think:

> "These two classes have common code, so I'll use extends."

First ask:

> "Is there a genuine IS-A relationship?"

---

### Mistake 3 — Accessing private parent fields directly

```java
private double amount;
```

Child cannot directly access it.

Use controlled methods.

---

### Mistake 4 — Forgetting `super()`

If the parent requires arguments:

```java
Payment(double amount)
```

child must invoke:

```java
super(amount);
```

---

### Mistake 5 — Unsafe downcasting

```java
UpiPayment upi =
        (UpiPayment) payment;
```

Don't assume the actual object is `UpiPayment`.

Check when necessary.

---

### Mistake 6 — Deep inheritance hierarchies

Avoid unnecessarily creating:

```text
A
 ↓
B
 ↓
C
 ↓
D
 ↓
E
 ↓
F
```

Deep hierarchies can become difficult to understand and maintain.

---

# 36. Interview Explanation

### What is inheritance?

> "Inheritance is an OOP mechanism where a subclass derives from a superclass and can reuse its accessible state and behavior. In Java, class inheritance is implemented using the `extends` keyword."

### Why use inheritance?

> "Inheritance is useful when there is a genuine IS-A relationship and a subclass is a specialized form of its parent. It promotes reuse and enables polymorphism."

### What is `super()`?

> "`super()` invokes the constructor of the immediate parent class. It is used to initialize the parent portion of the object and must be the first statement in a constructor when explicitly used."

### What is method overriding?

> "Method overriding occurs when a subclass provides its own implementation of an inherited instance method with a compatible signature."

### Why doesn't Java support multiple inheritance of classes?

> "Java restricts a class to extending one class to avoid ambiguity associated with multiple inherited implementations. Multiple type contracts can instead be achieved using interfaces."

---

# 37. Interview Questions

## Basic

1. What is inheritance?
2. What is a superclass?
3. What is a subclass?
4. What is the `extends` keyword?
5. What is an IS-A relationship?
6. What are the benefits of inheritance?
7. Are constructors inherited?

## Intermediate

8. What is `super()`?
9. What is `super.method()`?
10. What is method overriding?
11. What is `@Override`?
12. What is upcasting?
13. What is downcasting?
14. What is `instanceof`?
15. What happens when a child object is created?
16. What is constructor execution order?

## Advanced

17. Why doesn't Java support multiple inheritance of classes?
18. How does inheritance interact with encapsulation?
19. What is the difference between inheritance and composition?
20. When should you avoid inheritance?
21. What is the difference between `this` and `super`?
22. Can a final class be inherited?
23. Can a final method be overridden?
24. Can a private method be overridden?
25. Can an abstract class have constructors?
26. What is the role of `Object` in Java inheritance?
27. What is the Liskov Substitution Principle?
28. Why is composition often preferred over inheritance?

---

# 38. Coding Practice

## Practice 1 — Payment System ⭐

Create:

```text
Payment
├── amount
├── transactionId
├── validate()
└── refund()

UpiPayment
├── upiId
└── process()

CardPayment
├── cardLastFourDigits
└── process()
```

Use inheritance.

Override:

```java
process()
```

in each child.

---

## Practice 2 — Food Delivery

Create:

```text
Delivery
├── deliveryId
├── distance
└── calculateDeliveryFee()

BikeDelivery
CarDelivery
```

Override:

```java
calculateDeliveryFee()
```

with different pricing rules.

---

## Practice 3 — Notification System

Create:

```text
Notification
       ↑
   ┌───┼────┐
   ↓   ↓    ↓
 Email SMS Push
```

Common:

```text
recipient
send()
```

Each child should provide its own sending implementation.

---

# 39. Hands-On Challenge ⭐

Build a small payment system.

### Parent

```java
Payment
```

Fields:

```text
transactionId
amount
```

Methods:

```text
validate()
process()
refund()
```

### Children

```text
UpiPayment
CardPayment
BankTransferPayment
```

Each payment type should override:

```text
process()
```

Then create:

```java
Payment payment1 =
        new UpiPayment(...);

Payment payment2 =
        new CardPayment(...);

Payment payment3 =
        new BankTransferPayment(...);
```

Call:

```java
payment1.process();
payment2.process();
payment3.process();
```

Notice that the same parent reference can represent different child objects.

**This is the bridge from Inheritance → Polymorphism.**

---

# 40. Quick Revision

```text
Inheritance
→ Acquiring behavior/state from parent

extends
→ Creates class inheritance

Parent
→ Superclass / Base class

Child
→ Subclass / Derived class

IS-A
→ Inheritance relationship

super()
→ Parent constructor

super.method()
→ Parent method

this
→ Current object/class

Method overriding
→ Child provides new implementation

Upcasting
→ Child object → Parent reference

Downcasting
→ Parent reference → Child reference

instanceof
→ Runtime type check

final class
→ Cannot be extended

final method
→ Cannot be overridden

Constructors
→ Not inherited

Java class inheritance
→ Single inheritance

Composition
→ HAS-A relationship
```

---

# 41. Final Mental Model

Think about a payment platform:

```text
                         Payment
                            │
                  Common payment rules
                            │
          ┌─────────────────┼─────────────────┐
          ↓                 ↓                 ↓
     UpiPayment        CardPayment      BankTransfer
          │                 │                 │
      UPI logic         Card logic        Bank logic
```

The parent answers:

> "What is common to every payment?"

The child answers:

> "What is specific to this payment type?"

That is the core purpose of inheritance.

### One-line interview answer

> **Inheritance allows a subclass to specialize a superclass by reusing its accessible state and behavior, representing a genuine IS-A relationship and enabling polymorphism.**
