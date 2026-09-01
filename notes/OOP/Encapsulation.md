# Encapsulation in Java

## 1. What is Encapsulation?

**Encapsulation** is the process of bundling an object's data and the methods that operate on that data into a single unit, while restricting direct access to the internal state.

In Java, encapsulation is commonly implemented using:

```text
private fields
+
public/protected methods
+
controlled access
+
validation
```

### Simple Definition

> **Encapsulation means protecting an object's internal state and allowing access to it through controlled operations.**

---

# 2. Why Do We Need Encapsulation?

Consider a bank account.

A customer's balance should not be directly modified like this:

```java
account.balance = -50000;
```

That would allow invalid states.

Instead, the account controls how its balance changes:

```java
account.deposit(5000);
account.withdraw(2000);
```

The object itself decides whether the operation is valid.

Conceptually:

```text
Outside World
      │
      │ deposit()
      │ withdraw()
      ↓
┌─────────────────────┐
│     BankAccount     │
│                     │
│ private balance     │
│                     │
│ validation          │
└─────────────────────┘
```

The internal state is protected.

---

# 3. Without Encapsulation

Consider:

```java
class BankAccount {

    double balance;
}
```

Anyone can do:

```java
BankAccount account =
        new BankAccount();

account.balance = -100000;
```

This is dangerous.

The class has no control over its state.

---

# 4. With Encapsulation

Instead:

```java
class BankAccount {

    private double balance;

    public void deposit(double amount) {

        if (amount <= 0) {

            System.out.println(
                    "Invalid deposit"
            );

            return;
        }

        balance += amount;
    }

    public void withdraw(double amount) {

        if (amount <= 0) {

            System.out.println(
                    "Invalid withdrawal"
            );

            return;
        }

        if (amount > balance) {

            System.out.println(
                    "Insufficient balance"
            );

            return;
        }

        balance -= amount;
    }

    public double getBalance() {

        return balance;
    }
}
```

Now:

```java
BankAccount account =
        new BankAccount();

account.deposit(10000);

account.withdraw(2500);

System.out.println(
        account.getBalance()
);
```

Output:

```text
7500.0
```

But this is impossible:

```java
account.balance = -5000;
```

because:

```java
balance
```

is:

```java
private
```

---

# 5. Access Modifiers

Encapsulation relies heavily on access modifiers.

Java provides:

```text
public
protected
default
private
```

The most restrictive is:

```text
private
```

A private field can only be accessed directly within its declaring class.

Example:

```java
class BankAccount {

    private double balance;
}
```

Outside:

```java
account.balance; // ❌
```

Inside:

```java
balance; // ✅
```

---

# 6. Getter

A getter is a method used to retrieve a value.

Example:

```java
private double balance;

public double getBalance() {

    return balance;
}
```

Usage:

```java
double currentBalance =
        account.getBalance();
```

The caller can read the value without directly accessing the field.

---

# 7. Setter

A setter is a method used to modify a value.

Example:

```java
private String email;

public void setEmail(String email) {

    this.email = email;
}
```

Usage:

```java
account.setEmail(
        "user@example.com"
);
```

However, **not every field should automatically have a setter**.

This is very important.

---

# 8. Getters and Setters Do NOT Automatically Mean Encapsulation

Consider:

```java
private double balance;

public void setBalance(double balance) {

    this.balance = balance;
}
```

Now someone can still do:

```java
account.setBalance(-100000);
```

The field is private, but the object's state is still poorly controlled.

Better:

```java
public void deposit(double amount) {

    if (amount <= 0) {
        throw new IllegalArgumentException(
                "Amount must be positive"
        );
    }

    balance += amount;
}
```

This is stronger encapsulation.

### Important Interview Point

> Encapsulation is not simply "private fields + getters and setters."

It is about **controlling how the object's state is accessed and modified**.

---

# 9. Real-Time Example — Digital Wallet

Let's build a more realistic example.

```java
class DigitalWallet {

    private String walletId;
    private String ownerName;
    private double balance;

    DigitalWallet(
            String walletId,
            String ownerName
    ) {

        this.walletId = walletId;
        this.ownerName = ownerName;
        this.balance = 0;
    }

    public void addMoney(double amount) {

        if (amount <= 0) {

            throw new IllegalArgumentException(
                    "Amount must be positive"
            );
        }

        balance += amount;
    }

    public void spendMoney(double amount) {

        if (amount <= 0) {

            throw new IllegalArgumentException(
                    "Amount must be positive"
            );
        }

        if (amount > balance) {

            throw new IllegalArgumentException(
                    "Insufficient wallet balance"
            );
        }

        balance -= amount;
    }

    public double getBalance() {

        return balance;
    }

    public String getOwnerName() {

        return ownerName;
    }
}
```

Usage:

```java
DigitalWallet wallet =
        new DigitalWallet(
                "WALLET1001",
                "Praveen"
        );

wallet.addMoney(5000);

wallet.spendMoney(1200);

System.out.println(
        wallet.getBalance()
);
```

Output:

```text
3800.0
```

Notice:

There is **no**:

```java
setBalance()
```

because the balance should change only through valid wallet operations.

That's good encapsulation.

---

# 10. Encapsulation Protects Invariants

An **invariant** is a condition that should remain true for an object.

For our wallet:

```text
balance >= 0
```

We don't want:

```text
balance = -5000
```

The class protects the invariant:

```java
if (amount > balance) {

    throw new IllegalArgumentException(
            "Insufficient wallet balance"
    );
}
```

Therefore:

```text
External code
      ↓
spendMoney()
      ↓
Validation
      ↓
State change
```

The object remains valid.

---

# 11. Encapsulation and Data Hiding

These concepts are related but not exactly identical.

### Data Hiding

Restricting direct access to internal data.

Example:

```java
private double balance;
```

### Encapsulation

Bundling state + behavior and controlling how the state can be accessed or changed.

Example:

```java
private double balance;

public void deposit(double amount) {
    // controlled modification
}
```

### Simple distinction

```text
Data Hiding
→ Hide internal state

Encapsulation
→ Hide + control access through behavior
```

---

# 12. Encapsulation vs Abstraction

This is a very common interview question.

### Encapsulation

Focuses on:

> **How do we protect and control state?**

Example:

```java
private double balance;
```

### Abstraction

Focuses on:

> **What should the user know and what implementation details can be hidden?**

Example:

```java
interface PaymentService {

    void pay(double amount);
}
```

The caller knows:

```text
pay()
```

but doesn't need to know the internal payment implementation.

### Quick Comparison

```text
Encapsulation
→ Protect state

Abstraction
→ Hide implementation complexity
```

---

# 13. Encapsulation with `private`

Example:

```java
class User {

    private String password;
}
```

External code cannot directly access:

```java
user.password;
```

Instead, the class controls operations involving the password.

For sensitive values, we often shouldn't expose them through a getter at all.

This demonstrates an important principle:

> **Encapsulation does not mean every field must be readable and writable from outside.**

---

# 14. Immutable Objects

Encapsulation can also help create immutable objects.

An immutable object cannot change its state after creation.

Example:

```java
final class UserProfile {

    private final String userId;
    private final String name;

    UserProfile(
            String userId,
            String name
    ) {

        this.userId = userId;
        this.name = name;
    }

    public String getUserId() {

        return userId;
    }

    public String getName() {

        return name;
    }
}
```

There are:

```text
private fields
+
final fields
+
no setters
```

Once created:

```java
UserProfile profile =
        new UserProfile(
                "U101",
                "Praveen"
        );
```

the state cannot be changed through the class API.

We'll study **immutability** more deeply later.

---

# 15. Encapsulation and Collections

This is a very important real-world problem.

Bad design:

```java
class ShoppingCart {

    public List<String> items;
}
```

External code can do anything:

```java
cart.items.clear();
```

or:

```java
cart.items.add(null);
```

Better:

```java
class ShoppingCart {

    private List<String> items =
            new ArrayList<>();

    public void addItem(String item) {

        if (item == null) {
            return;
        }

        items.add(item);
    }

    public List<String> getItems() {

        return List.copyOf(items);
    }
}
```

Now external code gets a snapshot/unmodifiable copy rather than direct control over the internal list.

This becomes very important when we study **Collections** and **Spring Boot entity/service design**.

---

# 16. Encapsulation in Spring Boot

You'll frequently see:

```java
public class User {

    private Long id;

    private String name;

    private String email;

    // getters and setters
}
```

This is commonly used in Java applications.

But professional encapsulation goes beyond blindly generating getters and setters.

For example, instead of:

```java
order.setStatus(
        "DELIVERED"
);
```

a domain-oriented design might provide:

```java
order.markAsDelivered();
```

Why?

Because the `Order` class can enforce business rules.

For example:

```java
public void markAsDelivered() {

    if (!status.equals("OUT_FOR_DELIVERY")) {

        throw new IllegalStateException(
                "Order cannot be delivered"
        );
    }

    status = "DELIVERED";
}
```

Now the object controls its own valid state.

---

# 17. Encapsulation and Business Rules

This is where encapsulation becomes valuable in professional software.

Imagine an order system:

```text
Order
│
├── status
├── totalAmount
├── items
│
├── addItem()
├── removeItem()
├── cancel()
└── markAsDelivered()
```

Instead of allowing:

```java
order.status = "DELIVERED";
```

we expose:

```java
order.markAsDelivered();
```

The object decides whether that transition is legal.

For example:

```text
PLACED
   ↓
CONFIRMED
   ↓
PREPARING
   ↓
OUT_FOR_DELIVERY
   ↓
DELIVERED
```

But:

```text
DELIVERED
   ↓
PLACED
```

should probably not be allowed.

Encapsulation gives the object control over these rules.

---

# 18. Encapsulation and Validation

Validation belongs close to the state it protects when practical.

Example:

```java
class Product {

    private double price;

    public void setPrice(double price) {

        if (price < 0) {

            throw new IllegalArgumentException(
                    "Price cannot be negative"
            );
        }

        this.price = price;
    }
}
```

Now:

```java
product.setPrice(-500);
```

is rejected.

---

# 19. Strong vs Weak Encapsulation

### Weak

```java
private double balance;

public void setBalance(double balance) {

    this.balance = balance;
}
```

The field is hidden, but the business rule is weak.

### Stronger

```java
private double balance;

public void deposit(double amount) {

    validateAmount(amount);

    balance += amount;
}

public void withdraw(double amount) {

    validateAmount(amount);

    if (amount > balance) {
        throw new IllegalArgumentException(
                "Insufficient balance"
        );
    }

    balance -= amount;
}
```

The object's state is controlled through meaningful operations.

---

# 20. Common Mistakes

## Mistake 1 — Making fields public

```java
public double balance;
```

This exposes internal state directly.

Prefer:

```java
private double balance;
```

when the field should be protected.

---

## Mistake 2 — Blindly generating setters

Don't create:

```java
setBalance()
setStatus()
setOrderId()
```

without thinking about whether those values should actually be changed externally.

Ask:

> "Should this field be mutable?"

---

## Mistake 3 — Returning mutable internal collections

Avoid:

```java
public List<Item> getItems() {

    return items;
}
```

if callers shouldn't directly modify the internal list.

Consider:

```java
return List.copyOf(items);
```

or another appropriate controlled view/copy depending on the design.

---

## Mistake 4 — Treating getters as mandatory

Not every private field needs a getter.

For example:

```java
private String passwordHash;
```

should generally not have:

```java
getPasswordHash()
```

just because the field exists.

---

## Mistake 5 — Confusing Encapsulation with Abstraction

Remember:

```text
Encapsulation
→ Control access to state

Abstraction
→ Hide unnecessary implementation complexity
```

---

# 21. Interview Explanation

### What is encapsulation?

> "Encapsulation is the OOP principle of bundling an object's state and behavior together while restricting direct access to its internal state. In Java, it is commonly achieved using private fields and controlled methods that validate or manage state changes."

### Why use private fields?

> "Private fields prevent external code from directly modifying an object's internal state. This allows the class to control how its state is accessed and changed and helps maintain valid object invariants."

### Are getters and setters encapsulation?

> "Getters and setters can be part of an encapsulated design, but simply making fields private and generating unrestricted getters and setters is not sufficient. Good encapsulation provides controlled access based on the object's business rules."

### Encapsulation vs data hiding?

> "Data hiding focuses on restricting direct access to internal implementation details, whereas encapsulation combines state and behavior and provides controlled access to that state."

---

# 22. Interview Questions

### Basic

1. What is encapsulation?
2. Why is encapsulation important?
3. How do you achieve encapsulation in Java?
4. What is data hiding?
5. What is the role of `private`?

### Intermediate

6. Are getters and setters necessary for encapsulation?
7. Why shouldn't fields usually be public?
8. Can a private field be accessed outside its class?
9. Why do we validate data inside setters or domain methods?
10. What is the difference between encapsulation and data hiding?

### Advanced

11. Encapsulation vs abstraction?
12. How does encapsulation protect invariants?
13. How would you encapsulate a collection?
14. Should every field have a getter?
15. Should every field have a setter?
16. How does encapsulation help maintainability?
17. How does encapsulation appear in Spring Boot applications?
18. How would you design an immutable class?
19. How does encapsulation support loose coupling?
20. Give a real-world example of strong encapsulation.

---

# 23. Coding Practice

## Practice 1 — Bank Account

Create:

```text
BankAccount
├── accountNumber
├── holderName
├── balance
│
├── deposit()
├── withdraw()
└── getBalance()
```

Rules:

```text
deposit > 0
withdraw > 0
withdraw <= balance
balance >= 0
```

Do not create:

```java
setBalance()
```

---

## Practice 2 — Digital Wallet

Create:

```text
DigitalWallet
├── walletId
├── ownerName
├── balance
│
├── addMoney()
├── spendMoney()
└── getBalance()
```

Rules:

```text
Amount must be positive
Cannot spend more than balance
Balance cannot become negative
```

---

## Practice 3 — Order System ⭐

Create:

```text
Order
├── orderId
├── status
├── totalAmount
│
├── addItem()
├── cancel()
├── confirm()
├── markAsDelivered()
└── getStatus()
```

Implement valid status transitions.

For example:

```text
PLACED
  ↓
CONFIRMED
  ↓
PREPARING
  ↓
OUT_FOR_DELIVERY
  ↓
DELIVERED
```

Don't allow invalid transitions.

---

# 24. Quick Revision

```text
Encapsulation
→ Protect and control object state

private
→ Restricts direct access

Getter
→ Controlled read access

Setter
→ Controlled write access

Validation
→ Protects object invariants

Data Hiding
→ Hide internal state/details

Strong Encapsulation
→ Expose meaningful operations
   instead of unrestricted state mutation

Immutable Object
→ State cannot change after creation

Key principle
→ Object controls its own state
```

---

# 25. Final Mental Model

Think about a bank account.

Bad design:

```text
Outside Code
     │
     ↓
balance = -50000
```

Good design:

```text
Outside Code
     │
     ├── deposit()
     │
     └── withdraw()
              ↓
        ┌──────────────┐
        │ BankAccount  │
        │              │
        │ balance      │ ← private
        │              │
        │ validation   │
        └──────────────┘
```

The outside world says:

> "I want to withdraw ₹2,000."

The object decides:

> "Is that operation valid?"

That's the heart of encapsulation.

---

# 26. One-Line Interview Answer

> **Encapsulation protects an object's internal state by restricting direct access and exposing controlled operations through which the object can maintain its own valid state.**
