# Classes and Objects in Java

## 1. What is a Class?

A **class** is a blueprint or template used to create objects.

A class defines:

* Data → fields / variables
* Behavior → methods
* Initialization → constructors
* Rules → access modifiers and other class members

A class itself is not normally the actual business entity in memory. Objects are created from the class.

### Real-World Example

Consider an e-commerce system.

We can model a product using a class:

```java
class Product {

    int productId;
    String name;
    double price;
    int stock;

    void displayProduct() {

        System.out.println(
                productId + " - " +
                name + " - ₹" +
                price
        );
    }
}
```

Here:

```text
Product
│
├── productId
├── name
├── price
├── stock
│
└── displayProduct()
```

`Product` is the class.

---

# 2. What is an Object?

An **object** is an instance of a class.

We create an object using:

```java
new
```

Example:

```java
Product product =
        new Product();
```

Here:

```text
Product
   ↓
Class / reference type

new Product()
   ↓
Object
```

The object represents an actual product in memory.

---

# 3. Class vs Object

| Class                                  | Object                          |
| -------------------------------------- | ------------------------------- |
| Blueprint                              | Actual instance                 |
| Logical definition                     | Runtime entity                  |
| Defines fields and methods             | Contains actual state           |
| Doesn't represent one specific product | Represents one specific product |
| Created using class definition         | Created using `new`             |

Example:

```text
Class
  ↓
Product

Objects
  ↓
Laptop
Phone
Keyboard
```

---

# 4. Creating Multiple Objects

One class can create many objects.

```java
Product laptop =
        new Product();

Product phone =
        new Product();

Product keyboard =
        new Product();
```

Conceptually:

```text
                Product
                   │
        ┌──────────┼──────────┐
        ↓          ↓          ↓
      laptop      phone     keyboard
```

Each object has its own instance state.

---

# 5. Instance Variables

Consider:

```java
class Product {

    int productId;
    String name;
    double price;
}
```

These are **instance variables**.

Each object gets its own copy of these fields.

Example:

```java
Product laptop =
        new Product();

laptop.productId = 101;
laptop.name = "Laptop";
laptop.price = 65000;


Product phone =
        new Product();

phone.productId = 102;
phone.name = "Phone";
phone.price = 30000;
```

Now:

```text
laptop
├── productId = 101
├── name = Laptop
└── price = 65000

phone
├── productId = 102
├── name = Phone
└── price = 30000
```

The fields belong to their respective objects.

---

# 6. Object Reference

This line:

```java
Product product =
        new Product();
```

contains two different things.

### Reference variable

```java
Product product;
```

`product` is a reference variable.

### Object

```java
new Product();
```

This creates the object.

Conceptually:

```text
Stack                         Heap

product ───────────────────→ Product object
                              productId
                              name
                              price
```

The reference points to the object.

---

# 7. Multiple References

Consider:

```java
Product product1 =
        new Product();

Product product2 =
        product1;
```

Now both references point to the same object.

```text
product1 ───────┐
                ↓
           Product object
                ↑
product2 ───────┘
```

Therefore:

```java
product1.price = 50000;

System.out.println(
        product2.price
);
```

prints:

```text
50000.0
```

because both references refer to the same object.

---

# 8. `null` Reference

A reference can contain:

```java
null
```

Example:

```java
Product product = null;
```

This means:

> The reference currently doesn't point to an object.

Calling:

```java
product.displayProduct();
```

causes:

```text
NullPointerException
```

because there is no object to invoke the method on.

---

# 9. Object State

The values stored inside an object represent its **state**.

Example:

```java
Product product =
        new Product();

product.name = "Laptop";
product.price = 65000;
product.stock = 10;
```

The state is:

```text
name  → Laptop
price → 65000
stock → 10
```

---

# 10. Object Behavior

Methods define what an object can do.

```java
class Product {

    String name;
    double price;
    int stock;

    void displayProduct() {

        System.out.println(name);
    }

    void reduceStock() {

        stock--;
    }
}
```

Here:

```text
State
→ name
→ price
→ stock

Behavior
→ displayProduct()
→ reduceStock()
```

This is one of the fundamental ideas behind OOP:

> Objects combine state and behavior.

---

# 11. Real-Time Example — Shopping Cart

A shopping cart is a better example of objects working together.

```java
class Product {

    String name;
    double price;

    void display() {

        System.out.println(
                name + " - ₹" + price
        );
    }
}
```

Cart:

```java
class ShoppingCart {

    double totalAmount;

    void addProduct(Product product) {

        totalAmount += product.price;
    }

    void displayTotal() {

        System.out.println(
                "Total: ₹" + totalAmount
        );
    }
}
```

Usage:

```java
Product laptop =
        new Product();

laptop.name = "Laptop";
laptop.price = 65000;

Product mouse =
        new Product();

mouse.name = "Mouse";
mouse.price = 1500;

ShoppingCart cart =
        new ShoppingCart();

cart.addProduct(laptop);
cart.addProduct(mouse);

cart.displayTotal();
```

Output:

```text
Total: ₹66500.0
```

Here we have:

```text
ShoppingCart
     │
     ├── Product
     └── Product
```

This is a simple example of **objects collaborating with each other**.

---

# 12. Class Members

A class can contain different types of members:

```text
Class
│
├── Instance Variables
├── Static Variables
├── Constructors
├── Instance Methods
├── Static Methods
├── Nested Classes
└── Blocks
```

Example:

```java
class Product {

    // Instance variable
    String name;

    // Static variable
    static int productCount;

    // Constructor
    Product(String name) {
        this.name = name;
        productCount++;
    }

    // Instance method
    void display() {
        System.out.println(name);
    }

    // Static method
    static void showProductCount() {
        System.out.println(productCount);
    }
}
```

We'll study these concepts individually.

---

# 13. Instance vs Static

This is important.

### Instance

Belongs to an object.

```java
product.name
```

Each object can have a different value.

### Static

Belongs to the class.

```java
Product.productCount
```

There is one class-level value shared by the class.

Example:

```java
Product p1 =
        new Product("Laptop");

Product p2 =
        new Product("Phone");
```

Both objects contribute to:

```java
Product.productCount
```

---

# 14. Object Creation Internally

When we execute:

```java
Product product =
        new Product();
```

conceptually:

```text
1. JVM evaluates `new`
          ↓
2. Memory is allocated for object
          ↓
3. Instance fields receive default values
          ↓
4. Constructor executes
          ↓
5. Reference to object is returned
          ↓
6. Reference is assigned to `product`
```

For example:

```java
class Product {

    String name;
    double price;
}
```

Before assigning values, fields receive default values:

```text
String → null
double → 0.0
int    → 0
boolean → false
```

Local variables are different: they must be initialized before use.

---

# 15. `new` Keyword

The `new` keyword is commonly used to create objects.

```java
Product product =
        new Product();
```

It:

* Creates an object
* Allocates memory for its instance state
* Invokes a constructor
* Produces a reference to the new object

---

# 16. Objects and Garbage Collection

Consider:

```java
Product product =
        new Product();

product = null;
```

If no other reachable reference points to that object, it becomes **eligible for garbage collection**.

Conceptually:

```text
Before:

product ─────→ Product object


After:

product → null

Product object
     ↓
No reachable reference
     ↓
Eligible for GC
```

Java's garbage collector automatically manages reclaiming memory.

You don't manually `free()` objects like in C.

---

# 17. Real-Time Design Example

Consider an online shopping system.

We might have:

```text
User
Product
Cart
Order
Payment
Address
Notification
```

Each can become a class.

For example:

```java
class Order {

    int orderId;
    double totalAmount;

    void placeOrder() {
    }

    void cancelOrder() {
    }
}
```

Then:

```java
Order order =
        new Order();
```

The object represents one actual order.

---

# 18. Important OOP Mental Model

Don't think:

> "A class is just a file."

A class is a **type and blueprint**.

Don't think:

> "An object is just a variable."

An object is a **runtime instance containing state and behavior**.

Think:

```text
CLASS
 ↓
Defines what an object has and can do
 ↓
OBJECT
 ↓
Actual runtime instance
 ↓
STATE + BEHAVIOR
```

---

# 19. Interview Explanation

### What is a class?

> "A class is a user-defined reference type that acts as a blueprint for creating objects. It defines the state and behavior that objects of that type can have."

### What is an object?

> "An object is a runtime instance of a class. It has its own instance state and can invoke the behavior defined by its class."

### Class vs Object?

> "A class is the definition or blueprint, whereas an object is an actual runtime instance created from that class."

---

# 20. Common Interview Traps

### Can we create multiple objects from one class?

Yes.

```java
Product p1 = new Product();
Product p2 = new Product();
```

### Does every object have its own instance variables?

Yes, each object has its own instance state.

### Are static variables copied for every object?

No.

Static members belong to the class rather than each individual object.

### Can an object exist without a reference variable?

Yes.

For example:

```java
new Product();
```

creates an object, although its reference isn't stored in a named variable.

Such an object may quickly become unreachable and eligible for garbage collection.

### Can a reference variable point to `null`?

Yes.

```java
Product product = null;
```

---

# 21. Coding Practice

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
└── displayBalance()
```

Create two accounts and perform different transactions.

---

## Practice 2 — Shopping Cart

Create:

```text
Product
ShoppingCart
```

Allow the cart to:

* Add products
* Remove products
* Calculate total
* Display cart details

---

## Practice 3 — Hotel Room

Create:

```text
HotelRoom
├── roomNumber
├── roomType
├── pricePerNight
├── available
│
├── bookRoom()
├── cancelBooking()
└── displayRoom()
```

Create multiple rooms and simulate bookings.

---

# 22. Interview Questions

### Basic

1. What is a class?
2. What is an object?
3. What is the difference between a class and object?
4. How do you create an object in Java?
5. What does the `new` keyword do?
6. What is an instance variable?
7. What is an instance method?
8. What is an object reference?

### Intermediate

9. Where are objects created?
10. Where are reference variables stored?
11. What happens when an object is created?
12. What happens when a reference is assigned `null`?
13. What is the difference between instance and static members?
14. Can multiple references point to the same object?
15. What happens when no reference points to an object?
16. What is garbage collection?

### Design

17. How do you identify classes in a real-world system?
18. What is state vs behavior?
19. How do objects collaborate?
20. Why is OOP useful for large applications?

---

# 23. Quick Revision

```text
Class
→ Blueprint / type

Object
→ Runtime instance

new
→ Creates object

Reference
→ Points to an object

Instance variable
→ Object-specific state

Static variable
→ Class-level state

Method
→ Behavior

null
→ Reference points to no object

Garbage Collection
→ Reclaims unreachable objects
```

---

# 24. Final Mental Model

```text
              CLASS
                │
        ┌───────┴────────┐
        ↓                ↓
      STATE           BEHAVIOR
        │                │
     fields            methods
        │                │
        └───────┬────────┘
                ↓
             OBJECT
                │
        Runtime instance
                │
        ┌───────┴────────┐
        ↓                ↓
   Own state       Can perform behavior
```

### Interview one-liner

> **Class = blueprint/type. Object = runtime instance of that class containing its own state and capable of performing the behavior defined by the class.**
