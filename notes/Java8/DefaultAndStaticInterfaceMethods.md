# Default & Static Methods in Interfaces — Interview Cheat Sheet

## 1. What is it?

Java 8 introduced `default` and `static` methods in interfaces.

### Default Method

A `default` method has an implementation inside an interface.

```java
interface PaymentService {

    default void logTransaction() {
        System.out.println("Transaction logged");
    }
}
```

The implementing class can use the default method directly or override it.

### Static Method

A `static` method belongs to the interface itself.

```java
interface PaymentService {

    static boolean isValidAmount(double amount) {
        return amount > 0;
    }
}
```

It is called using the interface name:

```java
PaymentService.isValidAmount(500);
```

Mental model:

```text
default → inherited behavior → can override
static  → interface utility  → cannot override
```

---

## 2. Why is it needed?

### Default Methods

Before Java 8, adding a new method to an existing interface could break all implementing classes.

```java
interface PaymentService {
    void pay();
}
```

If a new method was added:

```java
void refund();
```

every existing implementation would have to implement it.

With a default method:

```java
default void refund() {
    System.out.println("Refund initiated");
}
```

existing implementations continue working.

### Main Purpose

```text
Backward compatibility
Interface evolution
Provide default behavior
```

### Static Methods

Static methods are useful for utility/helper operations related to the interface.

```java
PaymentService.isValidAmount(500);
```

---

## 3. How does it work / Internal?

### Default Method

```java
interface Logger {

    default void log() {
        System.out.println("Default Logging");
    }
}
```

Implementation:

```java
class Application implements Logger {
}
```

The class inherits the default implementation.

```java
Application app = new Application();

app.log();
```

Output:

```text
Default Logging
```

### Overriding Default Method

```java
class Application implements Logger {

    @Override
    public void log() {
        System.out.println("Application Logging");
    }
}
```

Now the class-specific implementation is used.

### Multiple Default Method Conflict

```java
interface A {

    default void show() {
        System.out.println("A");
    }
}

interface B {

    default void show() {
        System.out.println("B");
    }
}
```

If a class implements both:

```java
class Demo implements A, B {
}
```

Java cannot decide which `show()` to use.

The class must override it:

```java
class Demo implements A, B {

    @Override
    public void show() {
        B.super.show();
    }
}
```

You can also choose:

```java
A.super.show();
```

### Static Method

```java
interface PaymentService {

    static boolean isValidAmount(double amount) {
        return amount > 0;
    }
}
```

Call:

```java
PaymentService.isValidAmount(500);
```

Static interface methods are not inherited and cannot be overridden.

Mental model:

```text
Default:
Interface → Implementing Class → Object

Static:
Interface → Static Method
```

---

## 4. Syntax

### Default Method

```java
interface MyInterface {

    default void methodName() {
        // implementation
    }
}
```

### Static Method

```java
interface MyInterface {

    static void methodName() {
        // implementation
    }
}
```

### Override Default Method

```java
@Override
public void methodName() {
    // new implementation
}
```

### Resolve Default Method Conflict

```java
@Override
public void show() {
    A.super.show();
}
```

or:

```java
@Override
public void show() {
    B.super.show();
}
```

### Call Static Method

```java
MyInterface.methodName();
```

---

## 5. Simple Example

```java
interface Logger {

    default void log() {
        System.out.println("Default Logging");
    }

    static void validate() {
        System.out.println("Validation");
    }
}
```

```java
class Application implements Logger {

    @Override
    public void log() {
        System.out.println("Application Logging");
    }

    public static void main(String[] args) {

        Application app = new Application();

        app.log();

        Logger.validate();
    }
}
```

Output:

```text
Application Logging
Validation
```

Mental model:

```text
app.log()
    ↓
default method overridden by Application

Logger.validate()
    ↓
static method belongs to Logger
```

---

## 6. Real-Time Example

Payment processing system:

```java
interface PaymentService {

    void pay(double amount);

    default void logTransaction(double amount) {
        System.out.println("Transaction logged: ₹" + amount);
    }

    static boolean isValidAmount(double amount) {
        return amount > 0;
    }
}
```

Implementation:

```java
class UPIPayment implements PaymentService {

    @Override
    public void pay(double amount) {

        if (!PaymentService.isValidAmount(amount)) {
            System.out.println("Invalid payment amount");
            return;
        }

        System.out.println("Processing UPI payment: ₹" + amount);

        logTransaction(amount);
    }

    @Override
    public void logTransaction(double amount) {
        System.out.println("UPI transaction logged: ₹" + amount);
    }
}
```

Usage:

```java
public class PaymentApplication {

    public static void main(String[] args) {

        PaymentService payment = new UPIPayment();

        payment.pay(1500);

        payment.pay(-500);

        System.out.println(
                PaymentService.isValidAmount(2500)
        );
    }
}
```

Output:

```text
Processing UPI payment: ₹1500.0
UPI transaction logged: ₹1500.0

Invalid payment amount

true
```

Concepts demonstrated:

```text
pay()
→ abstract method

logTransaction()
→ default method
→ overridden by UPIPayment

isValidAmount()
→ static method
→ called using PaymentService.isValidAmount()
```

---

## 7. Important Rules

### Default Methods

- Introduced in Java 8.
- Can contain implementation.
- Can be inherited by implementing classes.
- Can be overridden.
- Called through an object.
- Used mainly for backward compatibility and default behavior.

### Static Methods

- Introduced in Java 8.
- Belong to the interface.
- Are not inherited.
- Cannot be overridden.
- Must be called using the interface name.

```java
PaymentService.isValidAmount(1000);
```

### Default Method Conflict

If two interfaces provide the same default method:

```text
A.show()
+
B.show()
↓
Conflict
↓
Implementing class must override
```

Resolve using:

```java
A.super.show();
```

or:

```java
B.super.show();
```

### Java API Examples

Java 8 added default methods to existing interfaces such as:

```java
Collection.removeIf()
Iterable.forEach()
```

This allowed Java to evolve existing interfaces without breaking implementations.

### Default vs Static

| Feature | `default` | `static` |
|---|---|---|
| Java version | Java 8 | Java 8 |
| Has implementation | Yes | Yes |
| Inherited | Yes | No |
| Can override | Yes | No |
| Called using object | Yes | No |
| Called using interface | No | Yes |
| Main purpose | Default behavior | Utility/helper |

Mental model:

```text
default → object.method()

static → Interface.method()
```

---

## 8. Common Mistakes

### Mistake 1 — Calling Static Method Through Object

Wrong:

```java
payment.isValidAmount(500);
```

Correct:

```java
PaymentService.isValidAmount(500);
```

### Mistake 2 — Calling Static Method Through Implementation Class

Wrong:

```java
UPIPayment.isValidAmount(500);
```

Correct:

```java
PaymentService.isValidAmount(500);
```

### Mistake 3 — Forgetting Default Method Conflict

Wrong:

```java
class Demo implements A, B {
}
```

If both interfaces contain the same default method, compilation fails.

Correct:

```java
class Demo implements A, B {

    @Override
    public void show() {
        A.super.show();
    }
}
```

### Mistake 4 — Thinking Default Methods Must Be Overridden

They don't.

```java
class Demo implements Logger {
}
```

`Demo` can simply inherit the default implementation.

### Mistake 5 — Thinking Static Methods Can Be Overridden

They cannot.

Static interface methods belong to the interface itself.

---

## 9. Interview Questions

### Q1. Why were default methods introduced in Java 8?

To provide backward compatibility and allow new methods to be added to interfaces without forcing all existing implementations to implement them.

### Q2. Can a default method be overridden?

Yes.

```java
@Override
public void log() {
}
```

### Q3. Can an interface have static methods?

Yes, since Java 8.

### Q4. Can interface static methods be overridden?

No.

### Q5. Are interface static methods inherited?

No.

### Q6. How do you call an interface static method?

```java
InterfaceName.methodName();
```

### Q7. What happens when two interfaces have the same default method?

The implementing class must override the method and resolve the conflict.

### Q8. How do you explicitly call a specific interface's default method?

```java
A.super.show();
```

### Q9. Can an interface contain both default and static methods?

Yes.

```java
interface Service {

    default void process() {
    }

    static void validate() {
    }
}
```

### Q10. What is the main difference between default and static methods?

```text
default → inherited + can be overridden

static → not inherited + cannot be overridden
```

### Q11. Why can't static interface methods be called through an object?

Because they belong to the interface itself, not to the implementing object's instance.

### Q12. What is the purpose of `InterfaceName.super.method()`?

It explicitly selects a particular interface's default implementation when multiple interfaces provide the same default method.

---

## 10. Coding Practice

### Practice 1 — Default Method

Create:

```java
interface Logger {

    default void log() {
        System.out.println("Default Logging");
    }
}
```

Create a class implementing `Logger` and call `log()`.

### Practice 2 — Override Default Method

Override `log()`:

```java
@Override
public void log() {
    System.out.println("Application Logging");
}
```

### Practice 3 — Multiple Default Conflict

Create:

```java
interface A {

    default void show() {
        System.out.println("A");
    }
}

interface B {

    default void show() {
        System.out.println("B");
    }
}
```

Implement both and resolve the conflict using:

```java
A.super.show();
```

### Practice 4 — Static Method

Add:

```java
static void validate() {
    System.out.println("Validation from A");
}
```

Call:

```java
A.validate();
```

### Practice 5 — Payment System

Create:

```java
interface PaymentService {

    void pay(double amount);

    default void logTransaction(double amount) {
        System.out.println("Transaction logged");
    }

    static boolean isValidAmount(double amount) {
        return amount > 0;
    }
}
```

Implement it using a `UPIPayment` class.

---

## 11. 10-Second Cheat Sheet

```text
DEFAULT
→ Java 8
→ Has implementation
→ Inherited
→ Can override
→ object.method()

STATIC
→ Java 8
→ Belongs to interface
→ Not inherited
→ Cannot override
→ Interface.method()

DEFAULT CONFLICT
→ Two interfaces have same default method
→ Class must override
→ A.super.method()
→ B.super.method()

WHY DEFAULT?
→ Backward compatibility
→ Interface evolution
```

### Most Important Interview Points

```text
default = inherited behavior

static = interface utility

default can be overridden

static cannot be overridden

static methods are not inherited

multiple default conflict → implementing class must resolve
```

---

## 12. Final Mental Model

```text
                    INTERFACE
                        │
            ┌───────────┴───────────┐
            │                       │
         DEFAULT                  STATIC
            │                       │
     Has implementation       Has implementation
            │                       │
       Inherited                  Not inherited
            │                       │
      Can override             Cannot override
            │                       │
     object.method()       Interface.method()
            │                       │
            ▼                       ▼
     Object behavior          Interface utility
```

```text
Abstract Method
→ Class MUST implement

Default Method
→ Class MAY override

Static Method
→ Interface owns it
→ Class does NOT inherit it
```

### Final One-Line Rule

```text
default = inherited behavior that can be overridden
static  = interface-owned utility that cannot be overridden
``` 