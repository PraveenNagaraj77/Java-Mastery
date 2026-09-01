# Abstraction in Java

## 1. What is Abstraction?

**Abstraction** means hiding unnecessary implementation details and exposing only the essential functionality.

In simple terms:

> **Show WHAT an object does, hide HOW it does it.**

Real-world example:

When you use an ATM:

```text
You see:

Insert Card
Enter PIN
Withdraw Money
Check Balance
```

You don't see:

```text
Bank server communication
Database queries
Encryption
Transaction validation
Network protocols
Account locking logic
```

The ATM exposes the necessary operations and hides the implementation.

That is abstraction.

---

# 2. Real-Time Software Example

Consider a payment system.

The application needs:

```text
pay()
```

It doesn't necessarily need to know the internal implementation of:

```text
UPI API
Bank API
Card Network
Payment Gateway
Encryption
Fraud Detection
```

We can expose:

```java
payment.pay(1000);
```

while hiding the implementation.

Conceptually:

```text
             Payment
                │
             pay()
                │
        ┌───────┼────────┐
        ↓       ↓        ↓
       UPI     Card    Wallet
        │       │        │
      Hidden  Hidden   Hidden
    implementation
```

---

# 3. Why Do We Need Abstraction?

Without abstraction, application code becomes tightly coupled to implementation details.

For example:

```java
StripeApi stripeApi =
        new StripeApi();

stripeApi.createPayment();
stripeApi.authenticate();
stripeApi.generateToken();
stripeApi.process();
stripeApi.verify();
```

The business layer now knows too much about Stripe.

Instead:

```java
PaymentGateway gateway =
        new StripePaymentGateway();

gateway.pay(1000);
```

The business layer only knows:

```text
PaymentGateway
     ↓
pay()
```

The implementation remains hidden.

This makes the application easier to:

* Change
* Extend
* Test
* Maintain
* Understand

---

# 4. Abstraction vs Encapsulation

This is one of the most important Java interview questions.

### Encapsulation

> **Encapsulation controls access to data and implementation details.**

Example:

```java
private double balance;
```

with:

```java
public double getBalance()
```

and:

```java
public void deposit(double amount)
```

The object's internal state is protected.

---

### Abstraction

> **Abstraction hides implementation complexity and exposes essential behavior.**

Example:

```java
interface PaymentGateway {

    void pay(double amount);
}
```

The caller knows:

```text
pay()
```

but doesn't need to know how the payment happens internally.

---

# 5. Simple Difference

Remember this:

```text
Encapsulation
→ HOW do I protect my data?

Abstraction
→ WHAT should I expose?
```

Another useful way:

```text
Encapsulation
→ Hide data / control access

Abstraction
→ Hide complexity / expose essential behavior
```

---

# 6. How Java Provides Abstraction

Java mainly provides abstraction using:

```text
Abstraction
│
├── Abstract Classes
│
└── Interfaces
```

We'll study interfaces separately in depth.

---

# 7. Abstract Class

An abstract class is declared using:

```java
abstract
```

Example:

```java
abstract class Payment {

}
```

An abstract class cannot be instantiated directly.

This is invalid:

```java
Payment payment =
        new Payment();
```

because `Payment` is abstract.

---

# 8. Why Use an Abstract Class?

An abstract class can define common behavior while leaving some behavior for subclasses.

Example:

```java
abstract class Payment {

    protected double amount;

    void validate() {

        System.out.println(
                "Validating payment"
        );
    }

    abstract void process();
}
```

Here:

```text
validate()
→ Common implementation

process()
→ Child must implement
```

This is a powerful combination.

---

# 9. Abstract Method

An abstract method has no implementation.

Example:

```java
abstract void process();
```

Notice:

```text
No body
No {}
```

It only defines the contract.

A subclass must implement it unless the subclass is also abstract.

---

# 10. Concrete Subclass

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

payment.validate();
payment.process();
```

Output:

```text
Validating payment
Processing UPI payment
```

The parent provides:

```text
validate()
```

The child provides:

```text
process()
```

---

# 11. Abstract Class Can Have Constructors

This is an important interview question.

Yes.

Example:

```java
abstract class Payment {

    protected double amount;

    Payment(double amount) {

        this.amount = amount;
    }

    abstract void process();
}
```

Child:

```java
class UpiPayment extends Payment {

    UpiPayment(double amount) {

        super(amount);
    }

    @Override
    void process() {

        System.out.println(
                "UPI payment: "
                + amount
        );
    }
}
```

Even though the abstract class cannot be instantiated directly, its constructor executes when a subclass object is created.

---

# 12. Abstract Class Can Have Normal Methods

An abstract class can contain:

```text
abstract methods
concrete methods
fields
constructors
static methods
final methods
```

Example:

```java
abstract class Payment {

    protected double amount;

    Payment(double amount) {

        this.amount = amount;
    }

    void validate() {

        System.out.println(
                "Payment validated"
        );
    }

    abstract void process();
}
```

So an abstract class can provide both:

```text
Common implementation
+
Required implementation
```

---

# 13. Abstract Class Cannot Be Instantiated

Invalid:

```java
Payment payment =
        new Payment(1000);
```

Why?

Because an abstract class may contain incomplete behavior.

For example:

```java
abstract void process();
```

Java doesn't know what generic payment processing should actually do.

---

# 14. But Abstract Reference Is Valid

This is valid:

```java
Payment payment =
        new UpiPayment(1000);
```

Notice:

```text
Reference
→ Payment

Object
→ UpiPayment
```

This combines:

```text
Abstraction
+
Polymorphism
```

---

# 15. Real-Time Example — Notification

Imagine our application supports:

```text
Email
SMS
Push
```

Abstract class:

```java
abstract class Notification {

    protected String recipient;

    Notification(String recipient) {

        this.recipient = recipient;
    }

    void validate() {

        if (
                recipient == null ||
                recipient.isBlank()
        ) {

            throw new IllegalArgumentException(
                    "Invalid recipient"
            );
        }
    }

    abstract void send(
            String message
    );
}
```

Email:

```java
class EmailNotification
        extends Notification {

    EmailNotification(String recipient) {

        super(recipient);
    }

    @Override
    void send(String message) {

        System.out.println(
                "Sending email to "
                + recipient
        );
    }
}
```

SMS:

```java
class SmsNotification
        extends Notification {

    SmsNotification(String recipient) {

        super(recipient);
    }

    @Override
    void send(String message) {

        System.out.println(
                "Sending SMS to "
                + recipient
        );
    }
}
```

Now:

```java
Notification notification =
        new EmailNotification(
                "user@example.com"
        );

notification.validate();

notification.send(
        "Order shipped"
);
```

The caller doesn't need to know how email delivery works.

---

# 16. Template Method Style

An abstract class can define the overall workflow while allowing subclasses to customize a specific step.

Example:

```java
abstract class OrderProcessor {

    final void processOrder() {

        validateOrder();

        processPayment();

        shipOrder();
    }

    private void validateOrder() {

        System.out.println(
                "Order validated"
        );
    }

    abstract void processPayment();

    private void shipOrder() {

        System.out.println(
                "Order shipped"
        );
    }
}
```

Child:

```java
class OnlineOrderProcessor
        extends OrderProcessor {

    @Override
    void processPayment() {

        System.out.println(
                "Processing online payment"
        );
    }
}
```

The parent controls the overall workflow.

The child provides the variable behavior.

This is a common abstraction pattern.

---

# 17. Abstract Class + Polymorphism

Consider:

```java
abstract class Payment {

    abstract void process();
}
```

Implementations:

```text
UpiPayment
CardPayment
WalletPayment
```

Then:

```java
Payment[] payments = {

        new UpiPayment(),
        new CardPayment(),
        new WalletPayment()
};
```

Loop:

```java
for (Payment payment : payments) {

    payment.process();
}
```

This combines:

```text
Abstraction
+
Inheritance
+
Polymorphism
```

These OOP concepts are not isolated.

They work together.

---

# 18. Interface as Abstraction

The second major mechanism is an interface.

Example:

```java
interface PaymentGateway {

    void pay(double amount);
}
```

This says:

> Any payment gateway must provide `pay()`.

Implementation:

```java
class StripeGateway
        implements PaymentGateway {

    @Override
    public void pay(double amount) {

        System.out.println(
                "Processing Stripe payment"
        );
    }
}
```

Another:

```java
class RazorpayGateway
        implements PaymentGateway {

    @Override
    public void pay(double amount) {

        System.out.println(
                "Processing Razorpay payment"
        );
    }
}
```

Caller:

```java
PaymentGateway gateway =
        new StripeGateway();

gateway.pay(1000);
```

The caller depends on:

```text
PaymentGateway
```

not:

```text
StripeGateway
```

---

# 19. Abstraction in Spring Boot

This is extremely important for professional Java development.

A typical Spring Boot application might have:

```text
Controller
    ↓
Service Interface
    ↓
Service Implementation
    ↓
Repository Interface
    ↓
Database
```

For example:

```java
public interface OrderService {

    Order createOrder(
            OrderRequest request
    );
}
```

Implementation:

```java
@Service
public class OrderServiceImpl
        implements OrderService {

    @Override
    public Order createOrder(
            OrderRequest request
    ) {

        // Business logic
    }
}
```

Controller:

```java
@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(
            OrderService orderService
    ) {

        this.orderService = orderService;
    }
}
```

The controller doesn't need to know the implementation details.

It depends on:

```text
OrderService
```

This is abstraction.

---

# 20. Why Interfaces Are Powerful

Suppose today:

```text
OrderService
      ↓
OrderServiceImpl
```

Tomorrow we might have:

```text
OrderService
      ↓
OrderServiceImpl
OrderServiceV2
MockOrderService
```

The consumer can still depend on:

```text
OrderService
```

This gives us flexibility.

---

# 21. Abstraction and Loose Coupling

Without abstraction:

```text
OrderController
       ↓
Specific implementation
       ↓
Specific database/payment/API
```

The components become tightly coupled.

With abstraction:

```text
OrderController
       ↓
OrderService
       ↓
Implementation
```

The dependency is on a contract.

This is called:

> **Programming to an interface rather than an implementation.**

---

# 22. Abstraction vs Implementation

Think of a car.

You interact with:

```text
steering wheel
brake
accelerator
gear
```

You don't directly interact with:

```text
fuel injection
engine timing
transmission internals
ECU
combustion process
```

The controls are the abstraction.

The mechanical/electronic internals are implementation details.

Software works similarly.

---

# 23. Abstract Class vs Interface

This is one of the most important interview comparisons.

| Abstract Class                     | Interface                                 |
| ---------------------------------- | ----------------------------------------- |
| Declared with `abstract class`     | Declared with `interface`                 |
| Extended using `extends`           | Implemented using `implements`            |
| A class can extend one class       | A class can implement multiple interfaces |
| Can have instance fields           | Primarily defines a contract              |
| Can have constructors              | No constructors                           |
| Can have concrete methods          | Can have default/static/private methods   |
| Can have abstract methods          | Can have abstract methods                 |
| Useful for shared state + behavior | Useful for contracts/capabilities         |

---

# 24. When Should You Use Abstract Class?

Use an abstract class when subclasses share:

```text
State
+
Common behavior
+
A common conceptual identity
```

Example:

```text
Payment
├── amount
├── transactionId
├── validate()
└── abstract process()
```

The subclasses are genuinely specialized types of the same abstraction.

---

# 25. When Should You Use Interface?

Use an interface when you mainly want to define a:

```text
Contract
Capability
Role
Behavior
```

Example:

```java
interface Payable {

    void pay();
}
```

Different unrelated classes can implement it:

```text
Invoice
Subscription
Order
Payment
```

They don't need to belong to the same class hierarchy.

---

# 26. Multiple Interfaces

Java allows:

```java
class Order
        implements Payable, Trackable {

}
```

So:

```text
Order
 ├── Payable
 └── Trackable
```

This provides multiple behavioral contracts without multiple class inheritance.

---

# 27. Abstraction Does Not Mean "Hide Everything"

A common misunderstanding is:

> "Abstraction means hiding all implementation."

No.

It means:

> Hide **unnecessary** complexity and expose what the consumer needs.

For example:

```java
payment.pay(1000);
```

is useful.

But:

```java
payment.openSocket();
payment.encryptPayload();
payment.generateHash();
payment.sendHttpRequest();
```

may expose unnecessary implementation details to the business layer.

---

# 28. Abstraction + Encapsulation + Polymorphism

These concepts work together.

Example:

```java
interface PaymentGateway {

    void pay(double amount);
}
```

Implementation:

```java
class UpiGateway
        implements PaymentGateway {

    private String apiKey;

    @Override
    public void pay(double amount) {

        // complex implementation
    }
}
```

Here:

### Abstraction

```text
PaymentGateway
pay()
```

defines what the system can do.

### Encapsulation

```text
private apiKey
```

protects internal state.

### Polymorphism

```java
PaymentGateway gateway =
        new UpiGateway();
```

allows different implementations.

These concepts work together.

---

# 29. Four Pillars of OOP

You should now understand the four major pillars:

```text
                 OOP
                  │
       ┌──────────┼──────────┐
       ↓          ↓          ↓
Encapsulation  Inheritance  Polymorphism
                  │
                  ↓
             Abstraction
```

More conceptually:

```text
Encapsulation
→ Protect internal state

Inheritance
→ Reuse/specialize behavior

Polymorphism
→ Same abstraction, different behavior

Abstraction
→ Hide complexity, expose essentials
```

---

# 30. Real-Time Architecture

A professional application might look like:

```text
                    Controller
                        │
                        ↓
                OrderService
                  (Interface)
                        │
                        ↓
               OrderServiceImpl
                        │
             ┌──────────┴──────────┐
             ↓                     ↓
      PaymentGateway         OrderRepository
         (Interface)            (Interface)
             │                     │
             ↓                     ↓
       RazorpayGateway       JpaOrderRepository
```

The application is built around **abstractions**.

Concrete implementations can change.

The contracts remain stable.

---

# 31. Common Mistakes

### Mistake 1 — Confusing abstraction with encapsulation

Remember:

```text
Encapsulation
→ Access control

Abstraction
→ Complexity hiding
```

---

### Mistake 2 — Trying to instantiate an abstract class

Invalid:

```java
new Payment();
```

if:

```java
abstract class Payment
```

---

### Mistake 3 — Forgetting abstract methods must be implemented

If:

```java
abstract void process();
```

exists, a concrete child must implement it.

---

### Mistake 4 — Creating an abstract class when an interface is enough

Don't create inheritance hierarchies unnecessarily.

Sometimes:

```java
interface PaymentGateway
```

is a better abstraction than:

```java
abstract class PaymentGateway
```

---

### Mistake 5 — Creating interfaces for everything

Interfaces are useful, but don't create an interface simply because "professional Java uses interfaces."

The abstraction should have a meaningful purpose.

---

# 32. Interview Explanation

### What is abstraction?

> "Abstraction is the process of exposing only the essential behavior of an object while hiding unnecessary implementation details. In Java, abstraction is primarily achieved through abstract classes and interfaces."

### Abstract class?

> "An abstract class is a class that cannot be instantiated directly and can contain both abstract methods and concrete implementations. It is useful when related subclasses share common state or behavior."

### Why use interfaces?

> "Interfaces define contracts that implementations must follow. They help reduce coupling and allow multiple implementations to be substituted without changing the consumer."

### Encapsulation vs abstraction?

> "Encapsulation focuses on protecting and controlling access to an object's internal state, while abstraction focuses on hiding implementation complexity and exposing essential behavior."

---

# 33. Interview Questions

## Basic

1. What is abstraction?
2. Why do we need abstraction?
3. How does Java achieve abstraction?
4. What is an abstract class?
5. What is an abstract method?
6. Can an abstract class be instantiated?
7. Can an abstract class have constructors?
8. Can an abstract class have concrete methods?

## Intermediate

9. Can an abstract class have fields?
10. Can an abstract class have static methods?
11. Can an abstract class have final methods?
12. What happens if a subclass doesn't implement an abstract method?
13. What is the difference between abstract class and interface?
14. What is the difference between abstraction and encapsulation?
15. Why can an abstract class have a constructor if it cannot be instantiated?

## Advanced

16. When would you choose an abstract class over an interface?
17. When would you choose an interface over an abstract class?
18. How does abstraction reduce coupling?
19. How is abstraction used in Spring Boot?
20. What does "programming to an interface" mean?
21. How do abstraction and polymorphism work together?
22. How does abstraction support SOLID principles?
23. Why shouldn't every class automatically have an interface?
24. How would you design a payment system using abstraction?

---

# 34. Coding Practice ⭐

## Practice 1 — Payment System

Create:

```text
abstract class Payment
```

Fields:

```text
transactionId
amount
```

Methods:

```text
validate()
abstract process()
```

Create:

```text
UpiPayment
CardPayment
WalletPayment
```

Each must implement:

```java
process()
```

---

# 35. Coding Practice ⭐⭐

## Notification System

Create:

```java
abstract class Notification
```

Common:

```text
recipient
validate()
```

Abstract:

```text
send()
```

Implement:

```text
EmailNotification
SmsNotification
PushNotification
```

Then process:

```java
Notification[] notifications = {

        new EmailNotification(...),
        new SmsNotification(...),
        new PushNotification(...)
};

for (Notification notification :
        notifications) {

    notification.validate();
    notification.send();
}
```

---

# 36. Coding Practice ⭐⭐⭐

## Payment Gateway

Create:

```java
interface PaymentGateway {

    void pay(double amount);
}
```

Implement:

```text
RazorpayGateway
StripeGateway
PaypalGateway
```

Then create:

```java
PaymentGateway gateway;
```

Switch implementations without changing the consumer logic.

This exercise combines:

```text
Abstraction
+
Polymorphism
+
Interfaces
+
Loose coupling
```

---

# 37. Final Mental Model

Think about a professional application.

The business layer should say:

```java
paymentGateway.pay(amount);
```

It should NOT need to know:

```text
How HTTP request is created
How authentication works
How API token is generated
How JSON is serialized
How retry logic works
How response is parsed
How errors are mapped
```

Those are implementation details.

Therefore:

```text
                 PaymentGateway
                     │
                     │ pay()
                     │
          ┌──────────┼──────────┐
          ↓          ↓          ↓
      Razorpay     Stripe     PayPal
          │          │          │
       Hidden      Hidden      Hidden
    implementation details
```

The abstraction is the contract.

The implementations are replaceable.

---

# 38. One-Line Interview Answer

> **Abstraction is the process of exposing essential behavior while hiding unnecessary implementation details, primarily achieved in Java through abstract classes and interfaces.**
