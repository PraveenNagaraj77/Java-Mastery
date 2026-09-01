# Interfaces in Java

## 1. What is an Interface?

An **interface** is a contract that defines what a class must do, without requiring the class to expose how it does it.

Simple definition:

> **Interface = Contract**

Example:

```java
interface PaymentGateway {

    void pay(double amount);
}
```

This says:

```text
Any class implementing PaymentGateway
must provide pay()
```

It does not care how `pay()` is implemented.

---

# 2. Real-World Example

Think about a **USB port**.

The computer provides:

```text
USB interface
```

Different devices can use it:

```text
Keyboard
Mouse
Pen Drive
External HDD
Controller
```

The computer doesn't need completely different connection logic for every device.

The interface defines the expected contract.

Similarly:

```text
PaymentGateway
       │
       ├── Razorpay
       ├── Stripe
       └── PayPal
```

All provide:

```java
pay()
```

but implementation differs.

---

# 3. Basic Syntax

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
                "Processing Razorpay payment"
        );
    }
}
```

Important keyword:

```text
implements
```

A class **implements** an interface.

---

# 4. Using the Interface

```java
PaymentGateway gateway =
        new RazorpayGateway();

gateway.pay(1000);
```

Notice:

```text
Reference Type
↓
PaymentGateway

Actual Object
↓
RazorpayGateway
```

This is:

```text
Abstraction
+
Polymorphism
```

---

# 5. Why Use Interfaces?

Interfaces help us achieve:

### Loose coupling

Instead of:

```java
RazorpayGateway gateway =
        new RazorpayGateway();
```

we can use:

```java
PaymentGateway gateway =
        new RazorpayGateway();
```

The rest of the application depends on:

```text
PaymentGateway
```

rather than:

```text
RazorpayGateway
```

---

# 6. Contract Concept

Suppose:

```java
interface NotificationService {

    void send(String message);
}
```

Any implementation must provide:

```java
send()
```

For example:

```java
class EmailNotificationService
        implements NotificationService {

    @Override
    public void send(String message) {

        System.out.println(
                "Sending email: "
                + message
        );
    }
}
```

Another:

```java
class SmsNotificationService
        implements NotificationService {

    @Override
    public void send(String message) {

        System.out.println(
                "Sending SMS: "
                + message
        );
    }
}
```

The contract remains:

```text
send()
```

Implementation changes.

---

# 7. Interface Reference

This is very important.

```java
NotificationService service =
        new EmailNotificationService();
```

The variable:

```text
service
```

is a:

```text
NotificationService reference
```

The object:

```text
new EmailNotificationService()
```

is:

```text
EmailNotificationService
```

Therefore:

```text
Reference
    ↓
Interface

Object
    ↓
Implementation
```

---

# 8. Multiple Implementations

One interface can have many implementations.

```text
             PaymentGateway
                   │
        ┌──────────┼──────────┐
        ↓          ↓          ↓
    Razorpay     Stripe     PayPal
```

All implement:

```java
pay()
```

Example:

```java
PaymentGateway gateway;

gateway = new RazorpayGateway();
gateway.pay(1000);

gateway = new StripeGateway();
gateway.pay(1000);

gateway = new PaypalGateway();
gateway.pay(1000);
```

Same method:

```text
pay()
```

Different implementation.

---

# 9. Interface Cannot Be Instantiated

This is invalid:

```java
PaymentGateway gateway =
        new PaymentGateway();
```

Why?

Because an interface defines a contract, not a concrete implementation.

You need an implementing class:

```java
PaymentGateway gateway =
        new RazorpayGateway();
```

---

# 10. Interface Methods

Traditionally, interface methods were abstract.

Example:

```java
interface PaymentGateway {

    void pay(double amount);
}
```

The method is implicitly:

```text
public
abstract
```

So conceptually:

```java
public abstract void pay(
        double amount
);
```

You don't need to explicitly write those modifiers.

---

# 11. Interface Methods Are Public

Consider:

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
    }
}
```

The implementation must be `public`.

This is invalid:

```java
class RazorpayGateway
        implements PaymentGateway {

    @Override
    void pay(double amount) {
    }
}
```

because it reduces visibility.

---

# 12. Interface Variables

Fields declared in an interface are implicitly:

```text
public
static
final
```

Example:

```java
interface PaymentGateway {

    int MAX_RETRY = 3;
}
```

Conceptually:

```java
public static final int MAX_RETRY = 3;
```

Therefore:

```java
PaymentGateway.MAX_RETRY
```

can be accessed directly.

---

# 13. Interface Variables Are Constants

This is invalid:

```java
PaymentGateway.MAX_RETRY = 5;
```

because interface fields are `final`.

They cannot be reassigned.

Better naming:

```java
interface PaymentGateway {

    int MAX_RETRY_COUNT = 3;
}
```

Constants are conventionally written in:

```text
UPPER_CASE
```

---

# 14. Default Methods

Java 8 introduced **default methods**.

A default method can contain an implementation.

Example:

```java
interface NotificationService {

    void send(String message);

    default void log() {

        System.out.println(
                "Notification logged"
        );
    }
}
```

Implementation:

```java
class EmailNotificationService
        implements NotificationService {

    @Override
    public void send(String message) {

        System.out.println(
                "Email sent"
        );
    }
}
```

Now:

```java
EmailNotificationService service =
        new EmailNotificationService();

service.send("Hello");
service.log();
```

---

# 15. Why Were Default Methods Introduced?

Imagine an existing interface:

```java
interface PaymentGateway {

    void pay();
}
```

Many classes already implement it:

```text
Razorpay
Stripe
PayPal
```

If we add:

```java
void refund();
```

all existing implementations would break because they would have to implement the new method.

Instead:

```java
default void refund() {

    // default implementation
}
```

can provide backward-compatible behavior.

This was an important reason for introducing default methods.

---

# 16. Static Methods in Interfaces

Interfaces can contain static methods.

Example:

```java
interface PaymentUtils {

    static boolean isValidAmount(
            double amount
    ) {

        return amount > 0;
    }
}
```

Call it using the interface:

```java
boolean valid =
        PaymentUtils.isValidAmount(1000);
```

Not through an object:

```java
// Not the intended way
paymentUtils.isValidAmount(1000);
```

Static interface methods belong to the interface itself.

---

# 17. Private Methods in Interfaces

Modern Java allows private methods inside interfaces.

Example:

```java
interface NotificationService {

    default void sendEmail() {

        validate();
        connect();
    }

    default void sendSms() {

        validate();
        connect();
    }

    private void validate() {

        System.out.println(
                "Validating"
        );
    }

    private void connect() {

        System.out.println(
                "Connecting"
        );
    }
}
```

Private methods help share implementation between default methods.

They are not accessible to implementing classes.

---

# 18. Interface Summary

An interface can contain:

```text
Abstract methods
Default methods
Static methods
Private methods
Constants
```

Modern Java interfaces are much more capable than the old "only abstract methods" definition.

---

# 19. Multiple Interfaces

Java does not support multiple class inheritance:

```java
class C extends A, B {
}
```

This is invalid.

But Java supports implementing multiple interfaces:

```java
class SmartDevice
        implements Printable, Scannable {

}
```

Example:

```java
interface Printable {

    void print();
}
```

```java
interface Scannable {

    void scan();
}
```

Implementation:

```java
class Printer
        implements Printable, Scannable {

    @Override
    public void print() {

        System.out.println(
                "Printing"
        );
    }

    @Override
    public void scan() {

        System.out.println(
                "Scanning"
        );
    }
}
```

Now:

```text
Printer
 ├── Printable
 └── Scannable
```

---

# 20. Interface Inheritance

An interface can extend another interface.

Example:

```java
interface Payment {

    void pay();
}
```

Another:

```java
interface Refundable
        extends Payment {

    void refund();
}
```

Now an implementation of `Refundable` must provide:

```text
pay()
refund()
```

Example:

```java
class Razorpay
        implements Refundable {

    @Override
    public void pay() {

        System.out.println(
                "Payment processed"
        );
    }

    @Override
    public void refund() {

        System.out.println(
                "Payment refunded"
        );
    }
}
```

---

# 21. Multiple Interface Inheritance

An interface can extend multiple interfaces.

```java
interface Printable {

    void print();
}
```

```java
interface Scannable {

    void scan();
}
```

Then:

```java
interface MultiFunctionDevice
        extends Printable, Scannable {

}
```

A class implementing `MultiFunctionDevice` must implement both:

```text
print()
scan()
```

---

# 22. Default Method Conflict

Suppose:

```java
interface A {

    default void execute() {

        System.out.println("A");
    }
}
```

And:

```java
interface B {

    default void execute() {

        System.out.println("B");
    }
}
```

Now:

```java
class C implements A, B {

}
```

This creates a conflict.

Which `execute()` should Java use?

The class must resolve it.

```java
class C implements A, B {

    @Override
    public void execute() {

        System.out.println(
                "C implementation"
        );
    }
}
```

---

# 23. Calling a Specific Default Method

Java provides:

```java
InterfaceName.super.method()
```

Example:

```java
class C implements A, B {

    @Override
    public void execute() {

        A.super.execute();
    }
}
```

Now the implementation from `A` is explicitly selected.

---

# 24. Functional Interface

A **functional interface** contains exactly one abstract method.

Example:

```java
@FunctionalInterface
interface Calculator {

    int calculate(
            int a,
            int b
    );
}
```

It can be implemented using a lambda:

```java
Calculator addition =
        (a, b) -> a + b;
```

Then:

```java
System.out.println(
        addition.calculate(10, 20)
);
```

Output:

```text
30
```

Functional interfaces are extremely important when we study:

```text
Lambda Expressions
Streams
Functional Programming
```

---

# 25. @FunctionalInterface

This annotation tells the compiler:

> This interface should contain exactly one abstract method.

Example:

```java
@FunctionalInterface
interface Validator {

    boolean validate(String value);
}
```

If you accidentally add another abstract method:

```java
@FunctionalInterface
interface Validator {

    boolean validate(String value);

    boolean check(String value);
}
```

the compiler reports an error.

---

# 26. Common Functional Interfaces

Java provides many built-in functional interfaces in:

```text
java.util.function
```

Important ones:

```text
Predicate<T>
Function<T, R>
Consumer<T>
Supplier<T>
```

We'll cover these deeply later.

---

# 27. Interface vs Abstract Class

This is a very important interview question.

| Interface                    | Abstract Class                   |
| ---------------------------- | -------------------------------- |
| `interface`                  | `abstract class`                 |
| `implements`                 | `extends`                        |
| Multiple interfaces possible | Only one class can be extended   |
| No constructor               | Can have constructor             |
| Fields are constants         | Can have instance state          |
| Good for contracts           | Good for shared state + behavior |
| Supports default methods     | Supports normal methods          |
| Supports static methods      | Supports static methods          |

---

# 28. When Should I Use an Interface?

Use an interface when you want to define:

```text
Contract
Capability
Behavior
Replaceable implementation
Loose coupling
```

Examples:

```text
PaymentGateway
NotificationService
FileStorage
EmailSender
AuthenticationProvider
MessagePublisher
```

---

# 29. When Should I Use an Abstract Class?

Use an abstract class when related classes share:

```text
State
Common implementation
Common lifecycle
Base behavior
```

Example:

```text
AbstractOrderProcessor
       │
       ├── OnlineOrderProcessor
       └── StoreOrderProcessor
```

The base class can contain shared state and workflow.

---

# 30. Interface in a Professional Application

Imagine:

```java
interface FileStorage {

    void upload(
            String filename
    );

    void delete(
            String filename
    );
}
```

Implementations:

```text
LocalFileStorage
S3FileStorage
AzureBlobStorage
```

Your service:

```java
class DocumentService {

    private final FileStorage storage;

    DocumentService(
            FileStorage storage
    ) {

        this.storage = storage;
    }

    void uploadDocument(
            String filename
    ) {

        storage.upload(filename);
    }
}
```

The service doesn't care whether the file goes to:

```text
Local disk
AWS S3
Azure Blob
```

It only knows:

```text
FileStorage
```

This is professional-level abstraction.

---

# 31. Interface + Dependency Injection

This pattern becomes extremely important in Spring Boot.

```java
public interface PaymentService {

    void processPayment(
            double amount
    );
}
```

Implementation:

```java
@Service
public class PaymentServiceImpl
        implements PaymentService {

    @Override
    public void processPayment(
            double amount
    ) {

        System.out.println(
                "Processing payment"
        );
    }
}
```

Consumer:

```java
@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(
            PaymentService paymentService
    ) {

        this.paymentService =
                paymentService;
    }
}
```

Notice:

```text
Controller
     ↓
PaymentService
     ↓
PaymentServiceImpl
```

The controller depends on the interface.

This is called:

> **Dependency Inversion / Programming to an abstraction**

We'll study this properly when we reach **SOLID + Spring Boot**.

---

# 32. Interface + Testing

Interfaces also make testing easier.

Production:

```java
PaymentService paymentService =
        new RealPaymentService();
```

Testing:

```java
PaymentService paymentService =
        new MockPaymentService();
```

The consumer doesn't need to change.

This is extremely useful for:

```text
Unit Testing
Mocking
Dependency Injection
```

---

# 33. Interface + Strategy Pattern

Interfaces are frequently used to implement design patterns.

Example:

```java
interface DiscountStrategy {

    double calculateDiscount(
            double amount
    );
}
```

Implementations:

```text
RegularDiscount
FestivalDiscount
PremiumDiscount
CouponDiscount
```

Order service:

```java
class OrderService {

    private DiscountStrategy strategy;

    OrderService(
            DiscountStrategy strategy
    ) {

        this.strategy = strategy;
    }

    double calculateFinalPrice(
            double amount
    ) {

        return strategy.calculateDiscount(
                amount
        );
    }
}
```

Now the strategy can be replaced dynamically.

This is the **Strategy Pattern**.

We'll study design patterns later.

---

# 34. Real-Time Architecture Example

Imagine your e-commerce application.

```text
                     OrderController
                            │
                            ↓
                     OrderService
                      (Interface)
                            │
                            ↓
                    OrderServiceImpl
                            │
             ┌──────────────┼──────────────┐
             ↓              ↓              ↓
      PaymentGateway   NotificationService OrderRepository
         Interface          Interface         Interface
             │                 │                  │
             ↓                 ↓                  ↓
        Razorpay          EmailService       JpaRepository
        Stripe             SmsService
```

Interfaces form the boundaries between components.

This is why they are so important in professional Java.

---

# 35. Common Mistakes

### Mistake 1 — Trying to instantiate an interface

Wrong:

```java
new PaymentGateway();
```

---

### Mistake 2 — Forgetting `public`

Wrong:

```java
void pay() {
}
```

when implementing a public interface method.

Correct:

```java
public void pay() {
}
```

---

### Mistake 3 — Thinking interfaces only contain abstract methods

Modern Java interfaces can contain:

```text
abstract
default
static
private
```

methods.

---

### Mistake 4 — Thinking interface fields are normal variables

They are:

```text
public static final
```

constants.

---

### Mistake 5 — Creating interfaces without purpose

Don't blindly create:

```text
UserService
UserServiceImpl
```

for every tiny class just because you've seen the pattern.

Interfaces should represent a useful abstraction or contract.

---

# 36. Interview Explanation

### What is an interface?

> "An interface is a contract that defines behavior that implementing classes must provide. It is primarily used for abstraction, loose coupling, polymorphism, and defining replaceable implementations."

### Why use interfaces?

> "Interfaces allow code to depend on abstractions rather than concrete implementations. This reduces coupling and makes implementations easier to replace, test, and extend."

### Can Java implement multiple interfaces?

> "Yes. Java does not support multiple inheritance of classes, but a class can implement multiple interfaces."

### Can interfaces have method implementations?

> "Yes. Modern Java interfaces can contain default, static, and private methods with implementations, in addition to abstract methods."

### Can we instantiate an interface?

> "No. An interface cannot be instantiated directly, but an interface reference can point to an object of a class that implements it."

---

# 37. Most Important Interview Questions

## Basic

1. What is an interface?
2. Why do we use interfaces?
3. How do you implement an interface?
4. Can we instantiate an interface?
5. Can a class implement multiple interfaces?
6. Can an interface extend another interface?
7. Can an interface extend multiple interfaces?

## Intermediate

8. What are default methods?
9. Why were default methods introduced?
10. Can interfaces have static methods?
11. Can interfaces have private methods?
12. Can interfaces have constructors?
13. What are interface variables?
14. Why are interface variables `public static final`?
15. What is a functional interface?
16. What is `@FunctionalInterface`?

## Advanced

17. What happens when two interfaces have the same default method?
18. How do you resolve a default method conflict?
19. Interface vs abstract class?
20. How does an interface help loose coupling?
21. How are interfaces used in Spring Boot?
22. How do interfaces help dependency injection?
23. How do interfaces help unit testing?
24. What does programming to an interface mean?
25. When should you choose an interface over an abstract class?

---

# 38. Hands-On Practice ⭐

## Payment Gateway System

Create:

```text
PaymentGateway.java
RazorpayGateway.java
StripeGateway.java
PaypalGateway.java
PaymentApp.java
```

Interface:

```java
interface PaymentGateway {

    void pay(double amount);

    void refund(double amount);
}
```

Implement all methods in:

```text
RazorpayGateway
StripeGateway
PaypalGateway
```

Then:

```java
PaymentGateway gateway =
        new RazorpayGateway();

gateway.pay(1500);
gateway.refund(500);
```

Change:

```java
gateway =
        new StripeGateway();
```

without changing the rest of the calling logic.

---

# 39. Hands-On Practice ⭐⭐

## File Storage System

Create:

```text
FileStorage
├── LocalFileStorage
├── S3FileStorage
└── AzureFileStorage
```

Interface:

```java
interface FileStorage {

    void upload(String filename);

    void delete(String filename);
}
```

Then:

```java
FileStorage storage =
        new LocalFileStorage();

storage.upload("resume.pdf");
```

Later:

```java
storage =
        new S3FileStorage();

storage.upload("resume.pdf");
```

The consumer code should work with both.

---

# 40. Hands-On Practice ⭐⭐⭐

## Notification System

Create:

```text
NotificationService
├── EmailNotification
├── SmsNotification
└── PushNotification
```

Interface:

```java
interface NotificationService {

    void send(
            String recipient,
            String message
    );
}
```

Then:

```java
NotificationService service =
        new EmailNotification();

service.send(
        "user@example.com",
        "Order shipped"
);
```

Replace it with:

```java
service =
        new SmsNotification();
```

No change to the consumer logic.

---

# 41. Professional Challenge ⭐⭐⭐⭐

Build a small **Order Processing System**.

Use these interfaces:

```text
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

Your `OrderService` should depend only on interfaces:

```java
class OrderService {

    private final PaymentGateway paymentGateway;

    private final NotificationService notificationService;

    private final OrderRepository orderRepository;

}
```

The goal is:

```text
OrderService
      ↓
Interfaces
      ↓
Implementations
```

not:

```text
OrderService
      ↓
Concrete classes everywhere
```

This is the architecture mindset we want to develop.

---

# 42. Final Mental Model

Remember:

```text
                 INTERFACE
                    │
                 CONTRACT
                    │
          ┌─────────┼─────────┐
          ↓         ↓         ↓
       Stripe    Razorpay   PayPal
          │         │         │
          ↓         ↓         ↓
       pay()      pay()      pay()
```

The interface tells us:

```text
WHAT
```

The implementation tells us:

```text
HOW
```

Therefore:

```text
Interface
→ What should be done?

Implementation
→ How should it be done?
```

---

# 43. The Professional Java Mental Model

When you see:

```java
PaymentGateway gateway;
```

think:

```text
Don't care about the implementation yet.
I only care about the contract.
```

When you see:

```java
gateway = new RazorpayGateway();
```

think:

```text
Concrete implementation selected.
```

When you see:

```java
gateway.pay(1000);
```

think:

```text
Polymorphism
→ runtime selects the implementation.
```

When you see:

```java
class OrderService {

    private PaymentGateway gateway;
}
```

think:

```text
Loose coupling
→ dependency on abstraction.
```

When Spring injects:

```java
PaymentGateway gateway
```

think:

```text
Dependency Injection
+
Abstraction
+
Polymorphism
```

These connections are what turn individual Java concepts into **professional Java development knowledge**.

---

# 44. One-Line Interview Answer

> **An interface is a contract that defines behavior without requiring a specific implementation, enabling abstraction, polymorphism, loose coupling, multiple implementations, and dependency injection.**
