# Composition in Java

## 1. What is Composition?

**Composition** is an OOP technique where one class contains an object of another class to use its functionality.

Simple definition:

> **Composition means building a class using other objects.**

The relationship is:

```text
HAS-A
```

Example:

```text
Order HAS-A Payment
Order HAS-A Address
Order HAS-A Customer
```

Instead of inheriting from those classes, the `Order` class contains them.

---

# 2. Real-World Example

Consider an e-commerce order.

An order contains:

```text
Order
 ├── Customer
 ├── Address
 ├── Payment
 └── Cart
```

We don't say:

```text
Order IS-A Customer
```

That doesn't make sense.

We say:

```text
Order HAS-A Customer
Order HAS-A Payment
Order HAS-A Address
```

This is composition.

---

# 3. Basic Example

```java
class Engine {

    void start() {

        System.out.println(
                "Engine started"
        );
    }
}
```

Now:

```java
class Car {

    private Engine engine;

    Car() {

        this.engine = new Engine();
    }

    void startCar() {

        engine.start();

        System.out.println(
                "Car started"
        );
    }
}
```

Here:

```text
Car
 ↓
HAS-A
 ↓
Engine
```

The `Car` uses an `Engine`.

---

# 4. Why Not Inheritance?

We should NOT do:

```java
class Car extends Engine {
}
```

because:

```text
Car IS-A Engine
```

is logically incorrect.

A car **has an engine**.

Therefore:

```text
Composition
→ Car HAS-A Engine
```

is the correct relationship.

---

# 5. IS-A vs HAS-A

This is extremely important.

### IS-A

Usually represented by:

```java
extends
```

or:

```java
implements
```

Example:

```text
Dog IS-A Animal
RazorpayGateway IS-A PaymentGateway
```

---

### HAS-A

Usually represented using:

```java
class fields
```

Example:

```text
Order HAS-A Customer
Order HAS-A Payment
Car HAS-A Engine
```

---

# 6. Simple Mental Model

Ask:

> **"Is this object a type of the other object?"**

If yes:

```text
IS-A
```

Example:

```text
Dog IS-A Animal
```

Use inheritance/interface.

If the answer is:

> "Does this object contain/use another object?"

Then:

```text
HAS-A
```

Use composition.

Example:

```text
Order HAS-A Payment
```

---

# 7. Basic Composition Example

```java
class Payment {

    void process() {

        System.out.println(
                "Payment processed"
        );
    }
}
```

```java
class Order {

    private Payment payment;

    Order() {

        this.payment = new Payment();
    }

    void placeOrder() {

        payment.process();

        System.out.println(
                "Order placed"
        );
    }
}
```

Usage:

```java
public class Main {

    public static void main(String[] args) {

        Order order = new Order();

        order.placeOrder();
    }
}
```

Output:

```text
Payment processed
Order placed
```

---

# 8. Composition Structure

Conceptually:

```text
Order
 │
 └── Payment
       │
       └── process()
```

`Order` delegates payment processing to `Payment`.

This is called:

> **Delegation**

---

# 9. What is Delegation?

Delegation means:

> One object asks another object to perform a responsibility.

Example:

```java
class Order {

    private Payment payment;

    void placeOrder() {

        payment.process();
    }
}
```

The `Order` doesn't implement payment processing itself.

It delegates the responsibility to:

```text
Payment
```

This keeps responsibilities separated.

---

# 10. Composition + Interface

This is where composition becomes extremely powerful.

Create:

```java
interface PaymentGateway {

    void pay(double amount);
}
```

Implementation:

```java
class RazorpayGateway
        implements PaymentGateway {

    @Override
    public void pay(double amount) {

        System.out.println(
                "Razorpay payment: "
                + amount
        );
    }
}
```

Now:

```java
class OrderService {

    private PaymentGateway paymentGateway;

    OrderService(
            PaymentGateway paymentGateway
    ) {

        this.paymentGateway =
                paymentGateway;
    }

    void placeOrder(double amount) {

        paymentGateway.pay(amount);

        System.out.println(
                "Order placed"
        );
    }
}
```

Notice the relationship:

```text
OrderService
     │
     └── HAS-A
           ↓
    PaymentGateway
```

This is much more flexible.

---

# 11. Dependency Injection

Now we can inject the implementation:

```java
PaymentGateway gateway =
        new RazorpayGateway();

OrderService orderService =
        new OrderService(gateway);
```

Then:

```java
orderService.placeOrder(1500);
```

The `OrderService` doesn't create the payment gateway itself.

The dependency is supplied from outside.

This is:

> **Dependency Injection**

---

# 12. Why Is This Better?

Instead of:

```java
class OrderService {

    private RazorpayGateway gateway =
            new RazorpayGateway();
}
```

we use:

```java
class OrderService {

    private PaymentGateway gateway;

    OrderService(
            PaymentGateway gateway
    ) {

        this.gateway = gateway;
    }
}
```

Now `OrderService` doesn't care whether the implementation is:

```text
Razorpay
Stripe
PayPal
Mock
Test implementation
```

This gives us loose coupling.

---

# 13. Changing Implementations

Suppose we have:

```java
class StripeGateway
        implements PaymentGateway {

    @Override
    public void pay(double amount) {

        System.out.println(
                "Stripe payment: "
                + amount
        );
    }
}
```

We can change:

```java
PaymentGateway gateway =
        new StripeGateway();

OrderService orderService =
        new OrderService(gateway);
```

The `OrderService` code doesn't change.

Only the dependency changes.

---

# 14. Composition + Polymorphism

This is one of the most important combinations in Java.

```java
PaymentGateway gateway =
        new RazorpayGateway();
```

Here:

```text
Composition
+
Interface
+
Polymorphism
```

Then:

```java
OrderService orderService =
        new OrderService(gateway);
```

`OrderService` contains a reference to the abstraction.

---

# 15. Real-Time E-Commerce Example

Consider an order service.

```java
class OrderService {

    private PaymentGateway paymentGateway;

    private NotificationService notificationService;

    private OrderRepository orderRepository;
}
```

Now:

```text
                  OrderService
                       │
        ┌──────────────┼──────────────┐
        ↓              ↓              ↓
 PaymentGateway  NotificationService  OrderRepository
        │              │              │
        ↓              ↓              ↓
    Razorpay        EmailService    MySQLRepository
```

This is composition.

`OrderService` **HAS-A**:

```text
PaymentGateway
NotificationService
OrderRepository
```

---

# 16. Composition Over Inheritance

You will often hear:

> **Favor composition over inheritance.**

This doesn't mean:

> "Never use inheritance."

It means:

> **Don't use inheritance when composition gives a better design.**

Inheritance creates a strong relationship:

```text
Child
  ↓
Parent
```

Composition gives us more flexibility:

```text
Class
  ↓
Dependency
```

---

# 17. Problem With Deep Inheritance

Imagine:

```text
Vehicle
   ↓
Car
   ↓
ElectricCar
   ↓
LuxuryElectricCar
   ↓
SelfDrivingLuxuryElectricCar
```

This can become difficult to maintain.

Changes near the top can affect many classes.

The hierarchy becomes rigid.

---

# 18. Composition Alternative

Instead of:

```text
Car
 ↓
ElectricCar
 ↓
LuxuryElectricCar
```

we can compose features:

```text
Car
 ├── Engine
 ├── NavigationSystem
 ├── PaymentSystem
 ├── Battery
 └── EntertainmentSystem
```

Now individual components can be replaced independently.

---

# 19. Strategy Pattern Through Composition

This is a very important professional example.

Interface:

```java
interface DiscountStrategy {

    double calculateDiscount(
            double amount
    );
}
```

Implementation:

```java
class FestivalDiscount
        implements DiscountStrategy {

    @Override
    public double calculateDiscount(
            double amount
    ) {

        return amount * 0.20;
    }
}
```

Another:

```java
class PremiumDiscount
        implements DiscountStrategy {

    @Override
    public double calculateDiscount(
            double amount
    ) {

        return amount * 0.30;
    }
}
```

Order:

```java
class Order {

    private DiscountStrategy discountStrategy;

    Order(
            DiscountStrategy discountStrategy
    ) {

        this.discountStrategy =
                discountStrategy;
    }

    double getDiscount(double amount) {

        return discountStrategy
                .calculateDiscount(amount);
    }
}
```

Usage:

```java
Order order =
        new Order(
                new FestivalDiscount()
        );
```

Later:

```java
Order order =
        new Order(
                new PremiumDiscount()
        );
```

We changed the behavior without changing `Order`.

This is excellent composition.

---

# 20. Composition Makes Behavior Replaceable

Think:

```text
Order
 │
 └── DiscountStrategy
         │
         ├── FestivalDiscount
         ├── PremiumDiscount
         └── CouponDiscount
```

We can replace:

```text
DiscountStrategy
```

without changing:

```text
Order
```

This is one of the major advantages of composition.

---

# 21. Composition in Spring Boot

This concept is everywhere in Spring.

Example:

```java
@Service
class OrderService {

    private final PaymentService paymentService;

    private final NotificationService notificationService;

    OrderService(
            PaymentService paymentService,
            NotificationService notificationService
    ) {

        this.paymentService =
                paymentService;

        this.notificationService =
                notificationService;
    }
}
```

`OrderService` has dependencies:

```text
OrderService
    │
    ├── PaymentService
    └── NotificationService
```

Spring injects the implementations.

This is composition + dependency injection.

---

# 22. Constructor Injection

Constructor injection is the preferred style in modern Spring applications.

Example:

```java
@Service
public class OrderService {

    private final PaymentService paymentService;

    public OrderService(
            PaymentService paymentService
    ) {

        this.paymentService =
                paymentService;
    }
}
```

Conceptually:

```text
Spring
  ↓
creates dependency
  ↓
passes dependency
  ↓
OrderService constructor
```

Now:

```text
OrderService HAS-A PaymentService
```

---

# 23. Why `final`?

We commonly write:

```java
private final PaymentService paymentService;
```

because the dependency should be assigned once during construction.

This makes the dependency explicit and helps maintain immutability of the reference.

---

# 24. Composition and Testing

Composition makes unit testing easier.

Production:

```java
OrderService service =
        new OrderService(
                new RealPaymentService()
        );
```

Testing:

```java
OrderService service =
        new OrderService(
                new MockPaymentService()
        );
```

The `OrderService` doesn't need to change.

This is one reason dependency injection and composition are so valuable.

---

# 25. Composition vs Inheritance

| Composition              | Inheritance                       |
| ------------------------ | --------------------------------- |
| HAS-A                    | IS-A                              |
| Contains another object  | Extends another class             |
| `extends` not required   | Uses `extends`                    |
| Loose coupling           | Strong coupling                   |
| Behavior can be replaced | Behavior tied to hierarchy        |
| Often more flexible      | Useful for genuine type hierarchy |
| Good for dependencies    | Good for specialization           |

---

# 26. When Should You Use Inheritance?

Inheritance makes sense when the relationship is genuinely:

```text
IS-A
```

Example:

```text
SavingsAccount IS-A Account
```

or:

```text
Circle IS-A Shape
```

or:

```text
RazorpayGateway IS-A PaymentGateway
```

when represented through an interface.

---

# 27. When Should You Use Composition?

Composition makes sense when:

```text
HAS-A
```

Example:

```text
Order HAS-A PaymentGateway
Order HAS-A Customer
Order HAS-A Address
Order HAS-A Cart
```

or:

```text
Car HAS-A Engine
```

---

# 28. Composition vs Inheritance Example

Bad design:

```java
class Order
        extends PaymentService {
}
```

Why?

```text
Order IS-A PaymentService
```

doesn't make sense.

Better:

```java
class OrderService {

    private PaymentService paymentService;
}
```

Now:

```text
OrderService HAS-A PaymentService
```

Correct.

---

# 29. Strong vs Loose Composition

There are different ownership relationships.

For example:

```text
Order
 └── OrderItem
```

An order may strongly own its order items.

If the order is deleted, its items may no longer make sense independently.

This is often described as **composition** in the broader OOP/design sense.

---

# 30. Aggregation

Aggregation is a weaker HAS-A relationship.

Example:

```text
Department
    ↓
Employees
```

An employee can exist independently of the department.

```text
Employee
    ↓
can exist without
    ↓
Department
```

So:

```text
Aggregation
→ Weak ownership

Composition
→ Strong ownership
```

In everyday Java programming, both are often implemented using object references; the distinction is primarily about the ownership/lifecycle relationship.

---

# 31. Composition vs Aggregation

| Composition                    | Aggregation                    |
| ------------------------------ | ------------------------------ |
| Strong ownership               | Weak ownership                 |
| Dependent lifecycle            | Independent lifecycle          |
| Part belongs strongly to whole | Part can exist separately      |
| Example: Order → OrderItem     | Example: Department → Employee |

---

# 32. Important Design Principle

Don't ask:

> "Can I use inheritance?"

Ask:

> **"What relationship actually exists between these objects?"**

If:

```text
IS-A
```

consider inheritance/interface.

If:

```text
HAS-A
```

consider composition.

---

# 33. Real-Time Example — DevLens Style Architecture

A backend system can be composed of services:

```text
DocumentService
    │
    ├── DocumentRepository
    ├── EmbeddingService
    ├── VectorStore
    └── FileParser
```

Conceptually:

```text
DocumentService
      │
      ├── HAS-A → DocumentRepository
      │
      ├── HAS-A → EmbeddingService
      │
      ├── HAS-A → VectorStore
      │
      └── HAS-A → FileParser
```

Each dependency has one responsibility.

This is much closer to professional backend architecture than building one giant class.

---

# 34. Composition and Single Responsibility Principle

Suppose one class handles:

```text
Payment
Email
Database
Logging
File processing
Authentication
```

That's a huge responsibility.

Instead:

```text
OrderService
PaymentService
NotificationService
OrderRepository
FileService
AuthService
```

Then compose them:

```text
OrderService
 ├── PaymentService
 ├── NotificationService
 └── OrderRepository
```

Each class has a focused responsibility.

This supports the **Single Responsibility Principle**.

---

# 35. Composition and Dependency Inversion

Suppose:

```java
class OrderService {

    private RazorpayGateway gateway;
}
```

This directly depends on a concrete implementation.

Better:

```java
class OrderService {

    private PaymentGateway gateway;
}
```

Now:

```text
OrderService
     ↓
PaymentGateway
     ↓
implementation
```

This follows the idea of depending on abstractions.

We'll study the formal **Dependency Inversion Principle** later in SOLID.

---

# 36. The Professional Mental Model

When designing a class, think:

```text
Does this class need to BE another type?
        ↓
      IS-A
        ↓
   Inheritance

Does this class need another object
to perform a responsibility?
        ↓
      HAS-A
        ↓
    Composition
```

Example:

```text
OrderService
    │
    ├── HAS-A PaymentGateway
    ├── HAS-A NotificationService
    └── HAS-A OrderRepository
```

This is a very common professional design.

---

# 37. Common Mistakes

### Mistake 1 — Using inheritance for code reuse only

Don't say:

> "I need this method, so I'll extend that class."

Inheritance should represent a meaningful relationship.

---

### Mistake 2 — Confusing IS-A and HAS-A

```text
Car IS-A Engine ❌

Car HAS-A Engine ✅
```

---

### Mistake 3 — Creating dependencies inside classes unnecessarily

Avoid:

```java
class OrderService {

    private PaymentService payment =
            new PaymentService();
}
```

Prefer dependency injection:

```java
class OrderService {

    private final PaymentService payment;

    OrderService(
            PaymentService payment
    ) {

        this.payment = payment;
    }
}
```

---

### Mistake 4 — Depending directly on concrete classes

Avoid:

```java
private RazorpayGateway gateway;
```

when the business logic only needs:

```java
private PaymentGateway gateway;
```

---

### Mistake 5 — Making giant classes

If a class contains too many responsibilities, look for objects that can be extracted and composed.

---

# 38. Interview Explanation

### What is composition?

> "Composition is an object-oriented design technique where a class contains references to other objects and delegates responsibilities to them. It represents a HAS-A relationship and is commonly used to achieve flexible and loosely coupled designs."

### Composition vs inheritance?

> "Inheritance represents an IS-A relationship and creates a strong coupling between parent and child. Composition represents a HAS-A relationship and allows behavior to be delegated to replaceable objects."

### Why prefer composition over inheritance?

> "Composition generally provides greater flexibility because dependencies can be changed or replaced without modifying the class hierarchy. It also reduces tight coupling and works well with dependency injection."

### What is delegation?

> "Delegation is when one object passes responsibility for an operation to another object that specializes in performing that operation."

---

# 39. Interview Questions

## Basic

1. What is composition?
2. What is a HAS-A relationship?
3. What is an IS-A relationship?
4. What is delegation?
5. What is aggregation?
6. What is the difference between composition and aggregation?

## Intermediate

7. Composition vs inheritance?
8. Why is composition often preferred over inheritance?
9. How does composition support loose coupling?
10. How does composition work with interfaces?
11. What is dependency injection?
12. How does constructor injection use composition?

## Advanced

13. How does composition support SOLID?
14. How does composition support the Strategy Pattern?
15. How does composition improve testability?
16. Why shouldn't inheritance be used just for code reuse?
17. How is composition used in Spring Boot?
18. What does "favor composition over inheritance" mean?
19. Give a real-world example where inheritance would be wrong and composition would be correct.
20. How would you design an order system using composition?

---

# 40. Hands-On Practice ⭐

## Car System

Create:

```text
Engine
Car
CarApplication
```

`Car` should contain:

```java
private Engine engine;
```

Implement:

```text
startEngine()
startCar()
```

Understand:

```text
Car HAS-A Engine
```

---

# 41. Hands-On Practice ⭐⭐

## Order System

Create:

```text
OrderService
PaymentGateway
NotificationService
OrderRepository
```

Implement:

```text
RazorpayGateway
EmailNotificationService
InMemoryOrderRepository
```

`OrderService` must receive dependencies through its constructor.

Architecture:

```text
OrderService
    │
    ├── PaymentGateway
    ├── NotificationService
    └── OrderRepository
```

---

# 42. Hands-On Practice ⭐⭐⭐

## Discount Strategy

Create:

```text
DiscountStrategy
├── RegularDiscount
├── FestivalDiscount
└── PremiumDiscount
```

Then:

```java
class Order {

    private DiscountStrategy strategy;

    Order(
            DiscountStrategy strategy
    ) {

        this.strategy = strategy;
    }
}
```

Test:

```java
Order regular =
        new Order(
                new RegularDiscount()
        );

Order festival =
        new Order(
                new FestivalDiscount()
        );

Order premium =
        new Order(
                new PremiumDiscount()
        );
```

The `Order` class should not contain:

```text
if regular
if festival
if premium
```

The strategy handles the behavior.

---

# 43. Final Mental Model

```text
                    OOP RELATIONSHIPS

                         Class A
                            │
              ┌─────────────┴─────────────┐
              │                           │
             IS-A                       HAS-A
              │                           │
              ↓                           ↓
         Inheritance                Composition
              │                           │
              ↓                           ↓
         "I am a..."                "I contain/use..."
```

Examples:

```text
Dog IS-A Animal
Circle IS-A Shape
```

versus:

```text
Order HAS-A Payment
Order HAS-A Customer
Car HAS-A Engine
```

---

# 44. The Professional Java Pattern

A very common professional structure is:

```text
              OrderService
                   │
        ┌──────────┼──────────┐
        ↓          ↓          ↓
     Payment   Notification  Repository
     Service     Service
        │          │
        ↓          ↓
    Interface    Interface
        │          │
        ↓          ↓
   Razorpay     Email
```

This combines:

```text
Composition
+
Interfaces
+
Abstraction
+
Polymorphism
+
Dependency Injection
+
Loose Coupling
```

This is the direction we want to take your Java knowledge toward.

---

# 45. One-Line Interview Answer

> **Composition is a HAS-A relationship where a class uses other objects through its fields or dependencies, allowing responsibilities to be delegated and implementations to be replaced more flexibly than with tightly coupled inheritance.**
