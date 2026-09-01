# Polymorphism in Java

## 1. What is Polymorphism?

**Polymorphism** means:

> **One interface/reference can represent multiple forms of objects, allowing the same operation to behave differently depending on the actual object.**

The word comes from:

```text
Poly → Many
Morph → Forms
```

So:

```text
Polymorphism
→ Many forms
```

---

# 2. Real-World Example

Consider a food delivery application.

Different payment methods:

```text
UPI
Credit Card
Net Banking
Wallet
```

All perform:

```text
pay()
```

But the implementation is different.

```text
                    Payment
                       │
             ┌─────────┼─────────┐
             ↓         ↓         ↓
            UPI       Card     Wallet
             │         │         │
           pay()     pay()     pay()
             │         │         │
             ↓         ↓         ↓
        UPI logic   Card logic  Wallet logic
```

The caller can simply say:

```java
payment.pay();
```

but the actual behavior depends on the object.

That is polymorphism.

---

# 3. Types of Polymorphism in Java

There are two major forms we discuss in Java:

```text
Polymorphism
│
├── Compile-Time Polymorphism
│       ↓
│   Method Overloading
│
└── Runtime Polymorphism
        ↓
    Method Overriding
```

---

# 4. Compile-Time Polymorphism

Compile-time polymorphism is commonly achieved through **method overloading**.

Example:

```java
class PaymentService {

    void pay(double amount) {

        System.out.println(
                "Processing payment"
        );
    }

    void pay(
            double amount,
            String currency
    ) {

        System.out.println(
                "Processing payment in "
                + currency
        );
    }
}
```

Now:

```java
PaymentService service =
        new PaymentService();

service.pay(500);

service.pay(
        500,
        "INR"
);
```

The compiler determines which method to call based on the arguments.

---

# 5. Method Overloading

Methods are overloaded when they have:

* Same method name
* Different parameter list

Example:

```java
void search(String keyword) {
}

void search(String keyword, int page) {
}

void search(
        String keyword,
        int page,
        int pageSize
) {
}
```

All are:

```text
search()
```

but accept different arguments.

---

# 6. What Can Change During Overloading?

The parameter list must be different.

### Different number of parameters

```java
void send(String message) {
}

void send(
        String message,
        String recipient
) {
}
```

Valid.

### Different parameter types

```java
void calculate(int amount) {
}

void calculate(double amount) {
}
```

Valid.

### Different order

```java
void process(
        int id,
        String name
) {
}

void process(
        String name,
        int id
) {
}
```

Valid.

---

# 7. What Cannot Be Used Alone for Overloading?

Changing only the return type is not enough.

Invalid:

```java
int getAmount() {
    return 100;
}

double getAmount() {
    return 100.0;
}
```

The compiler cannot distinguish these calls only by return type.

So this is not valid method overloading.

---

# 8. Runtime Polymorphism

Runtime polymorphism is primarily achieved through **method overriding**.

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

Another child:

```java
class CardPayment extends Payment {

    @Override
    void process() {

        System.out.println(
                "Processing card payment"
        );
    }
}
```

---

# 9. The Most Important Example

Now:

```java
Payment payment;

payment = new UpiPayment();

payment.process();
```

Output:

```text
Processing UPI payment
```

Change the object:

```java
payment = new CardPayment();

payment.process();
```

Output:

```text
Processing card payment
```

The reference type remains:

```text
Payment
```

but the actual object changes.

This is runtime polymorphism.

---

# 10. Reference Type vs Object Type

This distinction is extremely important.

Consider:

```java
Payment payment =
        new UpiPayment();
```

There are two types:

```text
Reference type
      ↓
Payment

Actual object type
      ↓
UpiPayment
```

The reference determines what members are accessible at compile time.

The actual object determines which overridden instance method implementation runs at runtime.

---

# 11. Dynamic Method Dispatch

The JVM determines which overridden method implementation to execute based on the **actual object**.

Example:

```java
Payment payment =
        new UpiPayment();

payment.process();
```

Conceptually:

```text
Compile Time
     ↓
payment is Payment reference
     ↓
process() exists?
     ↓
YES
     ↓
Runtime
     ↓
Actual object = UpiPayment
     ↓
Execute UpiPayment.process()
```

This mechanism is called:

> **Dynamic Method Dispatch**

or commonly:

> **Runtime Method Dispatch**

---

# 12. Why Is This Powerful?

Imagine we have:

```java
Payment p1 =
        new UpiPayment();

Payment p2 =
        new CardPayment();

Payment p3 =
        new WalletPayment();
```

We can do:

```java
p1.process();
p2.process();
p3.process();
```

Same method call:

```text
process()
```

Different behavior.

This is the essence of runtime polymorphism.

---

# 13. Real-Time Example — Notification System

Suppose our application sends notifications through:

```text
Email
SMS
Push Notification
```

Parent:

```java
class Notification {

    void send(String message) {

        System.out.println(
                "Sending notification"
        );
    }
}
```

Email:

```java
class EmailNotification
        extends Notification {

    @Override
    void send(String message) {

        System.out.println(
                "Sending Email: "
                + message
        );
    }
}
```

SMS:

```java
class SmsNotification
        extends Notification {

    @Override
    void send(String message) {

        System.out.println(
                "Sending SMS: "
                + message
        );
    }
}
```

Push:

```java
class PushNotification
        extends Notification {

    @Override
    void send(String message) {

        System.out.println(
                "Sending Push Notification: "
                + message
        );
    }
}
```

Now:

```java
Notification notification;

notification =
        new EmailNotification();

notification.send("Order shipped");

notification =
        new SmsNotification();

notification.send("Order shipped");

notification =
        new PushNotification();

notification.send("Order shipped");
```

Same:

```java
notification.send();
```

Different behavior.

---

# 14. Polymorphism Through an Array

This is where the concept becomes more practical.

```java
Notification[] notifications = {

        new EmailNotification(),
        new SmsNotification(),
        new PushNotification()
};
```

Now:

```java
for (
        Notification notification :
        notifications
) {

    notification.send(
            "Your order has shipped"
    );
}
```

Output:

```text
Sending Email: Your order has shipped
Sending SMS: Your order has shipped
Sending Push Notification: Your order has shipped
```

We don't need:

```java
if (type == EMAIL)
```

or:

```java
if (type == SMS)
```

The objects themselves provide the correct behavior.

---

# 15. Polymorphism Reduces Conditional Logic

Without polymorphism:

```java
if (paymentType.equals("UPI")) {

    // UPI logic

} else if (
        paymentType.equals("CARD")
) {

    // Card logic

} else if (
        paymentType.equals("WALLET")
) {

    // Wallet logic
}
```

As the application grows:

```text
UPI
CARD
WALLET
NET_BANKING
APPLE_PAY
GOOGLE_PAY
...
```

this can become difficult to maintain.

With polymorphism:

```java
Payment payment =
        paymentFactory.create(type);

payment.process();
```

The correct implementation handles itself.

---

# 16. Polymorphism and Open/Closed Principle

Polymorphism supports an important SOLID principle:

> **Open for extension, closed for modification.**

Suppose we already have:

```text
Payment
├── UpiPayment
├── CardPayment
└── WalletPayment
```

Later we add:

```text
CryptoPayment
```

We can create:

```java
class CryptoPayment
        extends Payment {

    @Override
    void process() {

        System.out.println(
                "Processing crypto payment"
        );
    }
}
```

Existing code that works with:

```java
Payment
```

can potentially work with the new implementation without changing the calling logic.

---

# 17. Upcasting

Upcasting is assigning a child object to a parent reference.

```java
Payment payment =
        new UpiPayment();
```

This is automatic.

Why?

Because:

```text
UpiPayment IS-A Payment
```

Therefore:

```text
Child object
     ↓
Parent reference
```

---

# 18. Downcasting

Downcasting converts a parent reference back to a child reference.

```java
Payment payment =
        new UpiPayment();

UpiPayment upi =
        (UpiPayment) payment;
```

This requires an explicit cast.

But it should be used carefully.

---

# 19. Safe Downcasting

Use:

```java
instanceof
```

when you need to verify the actual type.

```java
if (payment instanceof UpiPayment upi) {

    upi.processUpiSpecificOperation();
}
```

Modern Java allows pattern matching for `instanceof`.

However, excessive downcasting can be a design smell.

Good polymorphic design usually lets us call behavior through the common abstraction.

---

# 20. Method Overriding Rules

For overriding:

### Same method signature

Parent:

```java
void process() {
}
```

Child:

```java
@Override
void process() {
}
```

---

### Return type

The child can return the same type or a compatible **covariant return type**.

Example:

```java
class Payment {

    Payment create() {

        return new Payment();
    }
}
```

Child:

```java
class UpiPayment
        extends Payment {

    @Override
    UpiPayment create() {

        return new UpiPayment();
    }
}
```

`UpiPayment` is a subtype of `Payment`, so this is valid.

---

# 21. Access Modifier Rule

An overriding method cannot reduce visibility.

Parent:

```java
protected void process() {
}
```

Child cannot:

```java
private void process() {
}
```

because that would reduce accessibility.

But it can increase visibility:

```java
public void process() {
}
```

General rule:

```text
private
   ↓
package-private
   ↓
protected
   ↓
public
```

An override cannot move downward in visibility.

---

# 22. Can Private Methods Be Overridden?

No.

Example:

```java
class Payment {

    private void validate() {
    }
}
```

A child method:

```java
class UpiPayment
        extends Payment {

    private void validate() {
    }
}
```

is not overriding the parent method.

The parent's private method is not accessible to the child.

These are separate methods.

---

# 23. Can Static Methods Be Overridden?

No.

Static methods belong to the class, not the object.

If a child defines a static method with the same signature, it is called **method hiding**, not overriding.

Example:

```java
class Payment {

    static void info() {

        System.out.println(
                "Payment"
        );
    }
}
```

```java
class UpiPayment extends Payment {

    static void info() {

        System.out.println(
                "UPI"
        );
    }
}
```

This is method hiding.

---

# 24. Static Method vs Instance Method

Instance method:

```java
Payment payment =
        new UpiPayment();

payment.process();
```

Runtime object determines the overridden implementation.

Static:

```java
Payment.info();
```

Reference/class type determines which static method is called.

This distinction is frequently tested in interviews.

---

# 25. Final Methods and Polymorphism

A `final` method cannot be overridden.

```java
class Payment {

    final void validate() {
    }
}
```

Child cannot replace it.

Therefore, runtime polymorphism cannot change the implementation of that final method.

---

# 26. Abstract Classes and Polymorphism

Polymorphism becomes even more powerful with abstract classes.

```java
abstract class Payment {

    abstract void process();
}
```

Child:

```java
class UpiPayment
        extends Payment {

    @Override
    void process() {

        System.out.println(
                "Processing UPI"
        );
    }
}
```

Another:

```java
class CardPayment
        extends Payment {

    @Override
    void process() {

        System.out.println(
                "Processing Card"
        );
    }
}
```

Now:

```java
Payment payment =
        new UpiPayment();

payment.process();
```

or:

```java
payment =
        new CardPayment();

payment.process();
```

The parent defines the contract.

Children provide the implementation.

---

# 27. Interfaces and Polymorphism

Interfaces are one of the most important ways polymorphism is used in professional Java.

Example:

```java
interface PaymentGateway {

    void pay(double amount);
}
```

Implementations:

```java
class StripeGateway
        implements PaymentGateway {

    @Override
    public void pay(double amount) {

        System.out.println(
                "Stripe payment"
        );
    }
}
```

```java
class RazorpayGateway
        implements PaymentGateway {

    @Override
    public void pay(double amount) {

        System.out.println(
                "Razorpay payment"
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

Change implementation:

```java
gateway =
        new RazorpayGateway();

gateway.pay(1000);
```

The calling code doesn't need to know the concrete implementation.

This is the foundation of **programming to an interface**.

---

# 28. Polymorphism in Spring Boot

This concept is extremely important for Spring.

Imagine:

```java
interface PaymentService {

    void processPayment(
            double amount
    );
}
```

Implementation:

```java
@Service
class UpiPaymentService
        implements PaymentService {

    @Override
    public void processPayment(
            double amount
    ) {

        System.out.println(
                "UPI payment"
        );
    }
}
```

Another implementation:

```java
@Service
class CardPaymentService
        implements PaymentService {

    @Override
    public void processPayment(
            double amount
    ) {

        System.out.println(
                "Card payment"
        );
    }
}
```

A service can depend on:

```java
PaymentService
```

instead of:

```java
UpiPaymentService
```

This enables loose coupling and dependency injection.

We'll connect this to Spring Boot later.

---

# 29. Compile Time vs Runtime Polymorphism

| Compile Time             | Runtime                                |
| ------------------------ | -------------------------------------- |
| Method overloading       | Method overriding                      |
| Resolved by compiler     | Resolved during runtime                |
| Same method name         | Same method signature                  |
| Different parameters     | Child provides implementation          |
| Inheritance not required | Usually inheritance/interface involved |

Example compile time:

```java
search("laptop");

search("laptop", 2);
```

Example runtime:

```java
Payment p =
        new UpiPayment();

p.process();
```

---

# 30. Overloading vs Overriding

This is one of the most common Java interview questions.

| Overloading                          | Overriding                                    |
| ------------------------------------ | --------------------------------------------- |
| Same class commonly                  | Parent-child relationship                     |
| Different parameters                 | Same signature                                |
| Compile-time                         | Runtime                                       |
| Return type alone cannot distinguish | Covariant return allowed                      |
| Inheritance not required             | Requires inheritance/interface implementation |
| Static methods can be overloaded     | Static methods are hidden, not overridden     |

---

# 31. The Most Important Mental Model

Remember:

```text
REFERENCE TYPE
      ↓
What methods can I access?

ACTUAL OBJECT TYPE
      ↓
Which overridden implementation runs?
```

Example:

```java
Payment payment =
        new UpiPayment();
```

Compile time:

```text
Payment
  ↓
Does process() exist?
YES
```

Runtime:

```text
Actual object
  ↓
UpiPayment
  ↓
Run UpiPayment.process()
```

---

# 32. Real-Time Order Processing Example

Imagine an e-commerce system.

```java
interface NotificationService {

    void notifyCustomer(
            String message
    );
}
```

Implementations:

```text
EmailNotificationService
SmsNotificationService
PushNotificationService
```

Order service:

```java
class OrderService {

    private NotificationService
            notificationService;

    OrderService(
            NotificationService notificationService
    ) {

        this.notificationService =
                notificationService;
    }

    void shipOrder() {

        notificationService.notifyCustomer(
                "Your order has shipped"
        );
    }
}
```

Now:

```java
OrderService service =
        new OrderService(
                new EmailNotificationService()
        );
```

Later:

```java
OrderService service =
        new OrderService(
                new SmsNotificationService()
        );
```

`OrderService` doesn't need to change.

This is **polymorphism + abstraction + composition**.

This pattern appears constantly in professional backend applications.

---

# 33. Common Mistakes

### Mistake 1 — Thinking polymorphism only means overloading

Polymorphism includes multiple forms, but runtime polymorphism through overriding is particularly important in object-oriented design.

---

### Mistake 2 — Confusing reference type with object type

```java
Payment p =
        new UpiPayment();
```

Don't say:

> "p is a UpiPayment variable."

More precisely:

```text
p
→ Payment reference

object
→ UpiPayment
```

---

### Mistake 3 — Thinking static methods are overridden

They are hidden, not overridden.

---

### Mistake 4 — Using downcasting everywhere

If you frequently write:

```java
if (x instanceof A) ...
else if (x instanceof B) ...
else if (x instanceof C) ...
```

you may not be using polymorphism effectively.

---

### Mistake 5 — Using inheritance only for polymorphism

Inheritance should represent a meaningful relationship.

Interfaces and composition are often better when you need interchangeable behavior.

---

# 34. Interview Explanation

### What is polymorphism?

> "Polymorphism is the ability of a common reference or abstraction to represent different concrete implementations, allowing the same operation to produce different behavior depending on the actual object."

### What are the types of polymorphism in Java?

> "The commonly discussed types are compile-time polymorphism, achieved through method overloading, and runtime polymorphism, achieved through method overriding and dynamic method dispatch."

### What is runtime polymorphism?

> "Runtime polymorphism occurs when a parent or interface reference refers to a child or implementing object, and the JVM selects the overridden instance method based on the actual object at runtime."

### What is dynamic method dispatch?

> "Dynamic method dispatch is the runtime mechanism by which Java selects the overridden instance method implementation based on the actual object rather than merely the reference type."

### Why is polymorphism useful?

> "Polymorphism allows code to depend on abstractions instead of concrete implementations. This reduces coupling, makes systems easier to extend, and allows different implementations to be substituted without changing the calling code."

---

# 35. Interview Questions

## Basic

1. What is polymorphism?
2. What does polymorphism mean?
3. What are the types of polymorphism?
4. What is method overloading?
5. What is method overriding?
6. What is the difference between overloading and overriding?

## Intermediate

7. What is runtime polymorphism?
8. What is dynamic method dispatch?
9. What is upcasting?
10. What is downcasting?
11. What is `instanceof`?
12. Can static methods be overridden?
13. Can private methods be overridden?
14. Can final methods be overridden?
15. Can constructors be overridden?
16. What is covariant return type?

## Advanced

17. How does JVM select an overridden method?
18. What is the difference between reference type and object type?
19. How does polymorphism reduce coupling?
20. How is polymorphism used with interfaces?
21. How is polymorphism used in Spring Boot?
22. How does polymorphism support SOLID principles?
23. Why is programming to an interface useful?
24. When would you prefer composition over inheritance?
25. How would you design a payment system using polymorphism?

---

# 36. Coding Practice ⭐

## Practice 1 — Payment System

Create:

```text
Payment
├── UpiPayment
├── CardPayment
└── WalletPayment
```

All should implement:

```java
process()
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

Observe how the same method call produces different behavior.

---

# 37. Coding Practice ⭐⭐

## Notification System

Create:

```text
Notification
├── EmailNotification
├── SmsNotification
└── PushNotification
```

Each should override:

```java
send(String message)
```

Then:

```java
Notification[] notifications = {

        new EmailNotification(),
        new SmsNotification(),
        new PushNotification()
};
```

Process them through the parent type.

---

# 38. Coding Practice ⭐⭐⭐

## Payment Gateway

Create:

```java
interface PaymentGateway {

    void pay(double amount);
}
```

Implement:

```text
StripeGateway
RazorpayGateway
PaypalGateway
```

Then:

```java
PaymentGateway gateway;

gateway = new StripeGateway();
gateway.pay(1000);

gateway = new RazorpayGateway();
gateway.pay(1000);

gateway = new PaypalGateway();
gateway.pay(1000);
```

The calling code should depend only on:

```text
PaymentGateway
```

not the concrete implementation.

---

# 39. Quick Revision

```text
Polymorphism
→ Many forms

Compile-time
→ Method overloading

Runtime
→ Method overriding

Overloading
→ Same method name
   Different parameters

Overriding
→ Child provides implementation
   of inherited method

Upcasting
→ Child object → Parent reference

Downcasting
→ Parent reference → Child reference

Dynamic dispatch
→ Runtime chooses overridden method

Reference type
→ Determines accessible members

Actual object type
→ Determines overridden implementation

Static method
→ Hidden, not overridden

Private method
→ Not overridden

Final method
→ Cannot be overridden

Interface
→ Powerful abstraction for polymorphism

Spring
→ Interfaces + implementations
   + dependency injection
```

---

# 40. Final Mental Model

```text
                 ABSTRACTION
                     │
                     ↓
              PaymentService
                     │
          ┌──────────┼──────────┐
          ↓          ↓          ↓
         UPI        CARD       WALLET
          │          │          │
        process()  process()  process()
          │          │          │
          └──────────┼──────────┘
                     ↓
              SAME METHOD CALL
                     ↓
             DIFFERENT BEHAVIOR
```

The caller doesn't need to know the concrete implementation.

It simply works with:

```java
PaymentService
```

and says:

```java
paymentService.process();
```

The actual object determines what happens.

---

# 41. One-Line Interview Answer

> **Polymorphism allows a common parent type or interface to represent multiple concrete implementations, enabling the same operation to exhibit different behavior based on the actual object at runtime.**
