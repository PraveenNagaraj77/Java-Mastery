# Constructors in Java

## 1. What is a Constructor?

A **constructor** is a special member of a class that is executed when an object is created.

Its main purpose is to **initialize the object's state**.

Example:

```java
class Product {

    String name;
    double price;

    Product(String name, double price) {

        this.name = name;
        this.price = price;
    }
}
```

Creating the object:

```java
Product product =
        new Product("Laptop", 65000);
```

The constructor:

```java
Product(String name, double price)
```

runs during object creation.

---

# 2. Why Do We Need Constructors?

Without a constructor:

```java
Product product =
        new Product();

product.name = "Laptop";
product.price = 65000;
```

We create the object first and initialize it afterward.

With a constructor:

```java
Product product =
        new Product(
                "Laptop",
                65000
        );
```

The object is created with its required state immediately.

This helps ensure that objects are created in a meaningful state.

---

# 3. Constructor Rules

A constructor has some important rules.

### Rule 1 — Same name as the class

```java
class Product {

    Product() {
    }
}
```

The constructor name must be:

```text
Product
```

because the class name is:

```text
Product
```

---

### Rule 2 — No return type

Correct:

```java
Product() {
}
```

Incorrect:

```java
void Product() {
}
```

The second one is a **method**, not a constructor.

---

### Rule 3 — Runs during object creation

```java
Product product =
        new Product();
```

The constructor executes as part of the object creation process.

---

# 4. Default Constructor

If you don't declare **any constructor**, Java provides a default constructor.

Example:

```java
class Product {

    String name;
    double price;
}
```

Conceptually, Java provides:

```java
Product() {
}
```

Then:

```java
Product product =
        new Product();
```

works.

### Important

The compiler provides this constructor **only when you haven't declared any constructor yourself**.

---

# 5. What Happens When You Add a Constructor?

Consider:

```java
class Product {

    String name;

    Product(String name) {

        this.name = name;
    }
}
```

Now this:

```java
Product product =
        new Product();
```

will NOT compile.

Why?

Because once you define a constructor, Java no longer automatically provides the no-argument default constructor.

You must explicitly create one if you need it:

```java
Product() {
}
```

---

# 6. No-Argument Constructor

A constructor that accepts no parameters is a **no-argument constructor**.

```java
class Product {

    String name;

    Product() {

        name = "Unknown";
    }
}
```

Usage:

```java
Product product =
        new Product();
```

The object's initial state becomes:

```text
name = Unknown
```

---

# 7. Parameterized Constructor

A constructor that accepts parameters is called a **parameterized constructor**.

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

Usage:

```java
Product product =
        new Product(
                "Laptop",
                65000
        );
```

Now:

```text
name  → Laptop
price → 65000
```

---

# 8. `this` in Constructors

Consider:

```java
class Product {

    String name;

    Product(String name) {

        this.name = name;
    }
}
```

There are two `name` variables:

```text
this.name
   ↓
instance variable

name
   ↓
constructor parameter
```

Therefore:

```java
this.name = name;
```

means:

> Assign the constructor parameter `name` to the current object's `name` field.

---

# 9. Constructor Overloading

A class can have multiple constructors with different parameter lists.

Example:

```java
class Product {

    String name;
    double price;
    int stock;

    Product() {

        name = "Unknown";
        price = 0;
        stock = 0;
    }

    Product(
            String name,
            double price
    ) {

        this.name = name;
        this.price = price;
        this.stock = 0;
    }

    Product(
            String name,
            double price,
            int stock
    ) {

        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
```

Now we can create objects in different ways:

```java
Product p1 =
        new Product();

Product p2 =
        new Product(
                "Laptop",
                65000
        );

Product p3 =
        new Product(
                "Phone",
                30000,
                25
        );
```

This is **constructor overloading**.

---

# 10. Constructor Chaining

Instead of repeating initialization logic, constructors can call other constructors.

We use:

```java
this()
```

Example:

```java
class Product {

    String name;
    double price;
    int stock;

    Product() {

        this(
                "Unknown",
                0,
                0
        );
    }

    Product(
            String name,
            double price
    ) {

        this(
                name,
                price,
                0
        );
    }

    Product(
            String name,
            double price,
            int stock
    ) {

        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
```

Now:

```text
Product()
   ↓
Product(String, double, int)
```

and:

```text
Product(String, double)
   ↓
Product(String, double, int)
```

This reduces duplicate code.

---

# 11. `this()` Rule

When using:

```java
this();
```

it must be the **first statement** inside the constructor.

Correct:

```java
Product() {

    this(
            "Unknown",
            0,
            0
    );
}
```

Incorrect:

```java
Product() {

    System.out.println("Creating product");

    this(
            "Unknown",
            0,
            0
    );
}
```

The second version doesn't compile.

---

# 12. Constructor Chaining with `super()`

When inheritance is involved, constructors can also call the parent constructor using:

```java
super();
```

Example:

```java
class Payment {

    protected double amount;

    Payment(double amount) {

        this.amount = amount;
    }
}
```

Child class:

```java
class UpiPayment
        extends Payment {

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

The flow is:

```text
new UpiPayment(...)
        ↓
UpiPayment constructor
        ↓
super(amount)
        ↓
Payment constructor
        ↓
Payment state initialized
        ↓
UpiPayment state initialized
```

We'll explore this deeply in `Inheritance.md`.

---

# 13. Constructor Execution Order

Consider:

```java
class Parent {

    Parent() {

        System.out.println(
                "Parent constructor"
        );
    }
}
```

```java
class Child extends Parent {

    Child() {

        System.out.println(
                "Child constructor"
        );
    }
}
```

Create:

```java
Child child =
        new Child();
```

Output:

```text
Parent constructor
Child constructor
```

Why?

Because the parent portion of the object must be initialized before the child portion.

Conceptually:

```text
Child object
     │
     ├── Parent state
     │      ↓
     │   Parent constructor
     │
     └── Child state
            ↓
         Child constructor
```

---

# 14. What Happens Internally?

Consider:

```java
Product product =
        new Product(
                "Laptop",
                65000
        );
```

Conceptually, the JVM performs a sequence like:

```text
new
 ↓
Memory allocated for object
 ↓
Instance fields receive default values
 ↓
Constructor invocation
 ↓
Constructor initializes fields
 ↓
Reference returned
 ↓
Reference assigned to product
```

Before constructor initialization:

```text
name  → null
price → 0.0
```

After:

```text
name  → Laptop
price → 65000
```

---

# 15. Constructor vs Method

This is a common interview question.

| Constructor                   | Method                                  |
| ----------------------------- | --------------------------------------- |
| Initializes object            | Performs behavior                       |
| Same name as class            | Can have any valid name                 |
| No return type                | Has return type or `void`               |
| Called during object creation | Called explicitly or through other code |
| Cannot be inherited           | Methods can be inherited                |
| Cannot be overridden          | Can be overridden                       |
| Can be overloaded             | Can be overloaded                       |

Example:

```java
class Product {

    Product() {
        // Constructor
    }

    void display() {
        // Method
    }
}
```

---

# 16. Can a Constructor Be `private`?

Yes.

Example:

```java
class DatabaseConnection {

    private DatabaseConnection() {
    }
}
```

Now outside code cannot do:

```java
new DatabaseConnection();
```

This technique is used in certain designs such as utility-style classes and some singleton implementations.

We will discuss design patterns later rather than using Singleton casually in application code.

---

# 17. Can a Constructor Be `static`?

No.

```java
static Product() {
}
```

is invalid.

Why?

Because constructors initialize **objects**, while `static` members belong to the **class**.

---

# 18. Can a Constructor Be `final`?

No.

```java
final Product() {
}
```

is invalid.

`final` prevents overriding, but constructors are not inherited or overridden.

---

# 19. Can a Constructor Be `abstract`?

No.

```java
abstract Product() {
}
```

is invalid.

An abstract method represents behavior that subclasses must implement.

A constructor is used to initialize an object and must have an implementation.

---

# 20. Can an Abstract Class Have a Constructor?

Yes.

This is an important interview question.

```java
abstract class Payment {

    protected double amount;

    Payment(double amount) {

        this.amount = amount;
    }
}
```

Even though you cannot directly create:

```java
new Payment(500); // ❌
```

the constructor can execute when a subclass is created:

```java
class UpiPayment
        extends Payment {

    UpiPayment(double amount) {

        super(amount);
    }
}
```

---

# 21. Real-Time Example — Payment System

A payment system is a good example of constructor usage.

```java
class Payment {

    protected double amount;
    protected String transactionId;

    Payment(
            double amount,
            String transactionId
    ) {

        this.amount = amount;
        this.transactionId = transactionId;
    }
}
```

UPI:

```java
class UpiPayment
        extends Payment {

    private String upiId;

    UpiPayment(
            double amount,
            String transactionId,
            String upiId
    ) {

        super(
                amount,
                transactionId
        );

        this.upiId = upiId;
    }
}
```

Creating:

```java
UpiPayment payment =
        new UpiPayment(
                750,
                "TXN1001",
                "praveen@upi"
        );
```

The constructor guarantees that the payment starts with:

```text
Amount
Transaction ID
UPI ID
```

This is much better than creating an incomplete object and manually assigning every field afterward.

---

# 22. Constructor and Object Validity

Constructors can also enforce basic rules.

Example:

```java
class BankAccount {

    private double balance;

    BankAccount(double initialBalance) {

        if (initialBalance < 0) {

            throw new IllegalArgumentException(
                    "Balance cannot be negative"
            );
        }

        this.balance = initialBalance;
    }
}
```

Now:

```java
BankAccount account =
        new BankAccount(-500);
```

is rejected.

This is an important connection between:

```text
Constructor
+
Encapsulation
+
Validation
```

---

# 23. Common Mistakes

### Mistake 1 — Adding a return type

```java
void Product() {
}
```

This is a method, not a constructor.

---

### Mistake 2 — Expecting a default constructor

```java
class Product {

    Product(String name) {
    }
}
```

Then:

```java
new Product();
```

doesn't compile.

You need:

```java
Product() {
}
```

if you want a no-argument constructor.

---

### Mistake 3 — Using `this()` anywhere except first statement

Incorrect:

```java
Product() {

    System.out.println("Hello");

    this("Laptop");
}
```

---

### Mistake 4 — Confusing `this()` and `this`

```java
this()
```

calls another constructor.

```java
this.name
```

refers to the current object's field.

---

### Mistake 5 — Confusing `super()` and `super`

```java
super()
```

calls the parent constructor.

```java
super.method()
```

calls a parent method.

---

# 24. Interview Explanation

### What is a constructor?

> "A constructor is a special member of a class that is invoked during object creation and is primarily used to initialize the object's state. It has the same name as the class and doesn't have a return type."

### Why do we need constructors?

> "Constructors allow us to initialize an object at creation time and can enforce required initial state or validation, reducing the possibility of creating improperly initialized objects."

### What is constructor overloading?

> "Constructor overloading means defining multiple constructors in the same class with different parameter lists, allowing objects to be initialized in different ways."

### What is constructor chaining?

> "Constructor chaining is the process of one constructor invoking another constructor, either in the same class using `this()` or in a parent class using `super()`."

---

# 25. Interview Questions

### Basic

1. What is a constructor?
2. Why do we need constructors?
3. What are the rules of constructors?
4. Does a constructor have a return type?
5. Can constructors be overloaded?
6. What is a default constructor?
7. What is a no-argument constructor?

### Intermediate

8. When does Java provide a default constructor?
9. What happens if we define a parameterized constructor?
10. What is constructor chaining?
11. What is `this()`?
12. What is `super()`?
13. Why must `this()` be the first statement?
14. Why must `super()` be the first statement?
15. Can constructors be inherited?
16. Can constructors be overridden?

### Advanced

17. Can an abstract class have a constructor?
18. Can a constructor be private?
19. Can a constructor be static?
20. Can a constructor be final?
21. Can a constructor be abstract?
22. What happens internally when `new` is used?
23. What is the constructor execution order in inheritance?
24. Can a constructor call another constructor?
25. Can `this()` and `super()` appear together in the same constructor?

---

# 26. Coding Practice

## Practice 1 — Bank Account

Create constructors for:

```text
BankAccount
├── accountNumber
├── holderName
├── balance
```

Support:

```text
BankAccount()
BankAccount(accountNumber, holderName)
BankAccount(accountNumber, holderName, balance)
```

Use constructor chaining to avoid duplicate code.

---

## Practice 2 — Product

Create:

```text
Product
├── productId
├── name
├── price
├── stock
```

Create overloaded constructors.

Add validation so price and stock cannot be negative.

---

## Practice 3 — Payment

Create:

```text
Payment
    ↓
UpiPayment
    ↓
CardPayment
```

Use:

```text
super()
```

to initialize common payment information.

---

# 27. Quick Revision

```text
Constructor
→ Initializes object

Same name as class
→ Yes

Return type
→ No

Called when?
→ During object creation

Default constructor
→ Compiler provides it only if no constructor is declared

Parameterized constructor
→ Accepts arguments

Overloading
→ Multiple constructors with different parameters

this()
→ Calls another constructor in same class

super()
→ Calls parent constructor

Constructor inheritance
→ No

Constructor overriding
→ No

Can abstract class have constructor?
→ Yes

Can constructor be private?
→ Yes

Can constructor be static?
→ No

Can constructor be final?
→ No

Can constructor be abstract?
→ No
```

---

# 28. Final Mental Model

```text
              CLASS
                │
                │ new
                ↓
          OBJECT CREATION
                │
                ↓
          Constructor runs
                │
       ┌────────┴────────┐
       ↓                 ↓
 Initialize state     Validate state
       │                 │
       └────────┬────────┘
                ↓
          READY OBJECT
```

### The key idea

> **A constructor is the controlled entry point for creating and initializing an object.**

Good constructors help ensure that an object starts life in a valid and meaningful state.
