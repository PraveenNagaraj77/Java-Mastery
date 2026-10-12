
# Serialization and Deserialization — Interview Cheat Sheet

## 1. What Is Serialization?

**Serialization** is the process of converting an object's state into a format that can be stored or transmitted.

In Java native serialization, an object is converted into a byte stream.

**Deserialization** is the reverse process: reconstructing an object from serialized data.

### How It Works

```text
Java Object
    |
    v
Serialization
(ObjectOutputStream)
    |
    v
Byte Stream
(File / Storage / Transport)
    |
    v
Deserialization
(ObjectInputStream)
    |
    v
Reconstructed Java Object
```

### Why Do We Need It?

- Persisting object state for later restoration.
- Transferring objects between compatible Java systems.
- Supporting certain legacy caching and session-persistence mechanisms.
- Saving application state where native Java serialization is explicitly required.

### Where Should We Use It?

Use Java native serialization when working with systems that specifically require its object-stream format.

For APIs, interoperable data exchange, and many modern persistence scenarios, formats such as JSON are often preferable.

**Important:** Serialization is not encryption and does not automatically protect sensitive information.

---

## 2. The Serializable Interface

Java provides the `java.io.Serializable` interface.

```java
import java.io.Serializable;

public class Order implements Serializable {
    // Fields and methods
}
```

`Serializable` is a **marker interface**. It does not declare methods that a class must implement.

Implementing it marks the class as eligible for Java's default serialization mechanism.

### What Happens Internally?

When `ObjectOutputStream.writeObject()` is called:

1. Java checks whether the object supports serialization.
2. The serialization mechanism processes the object's serializable state.
3. Class and object-reference information is written as required.
4. The resulting bytes are sent to the underlying output stream.

Java serialization also handles shared references and cyclic object graphs.

### Important Rule

A serializable class can still fail during serialization if one of its non-transient instance fields refers to an object that cannot be serialized.

For example, a serializable `Order` containing a non-transient field referencing a non-serializable `Connection` object may cause `NotSerializableException`.

---

## 3. Creating the Order Class

**File:** `Order.java`

**Package:** `com.javamastery.javaIO.serialization`

```java
package com.javamastery.javaIO.serialization;

import java.io.Serializable;

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private String orderId;
    private String productName;
    private int quantity;

    private transient String sessionToken;

    private static String applicationName = "OrderService";

    public Order(
            String orderId,
            String productName,
            int quantity,
            String sessionToken
    ) {
        this.orderId = orderId;
        this.productName = productName;
        this.quantity = quantity;
        this.sessionToken = sessionToken;
    }

    public void displayDetails() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Product: " + productName);
        System.out.println("Quantity: " + quantity);
        System.out.println("Session Token: " + sessionToken);
        System.out.println("Application: " + applicationName);
    }
}
```

### Understand the Fields

- `orderId`: Ordinary instance field; serialized.
- `productName`: Ordinary instance field; serialized.
- `quantity`: Primitive instance field; serialized.
- `sessionToken`: Transient instance field; excluded from default serialization.
- `applicationName`: Static class field; not serialized as part of the object's instance state.
- `serialVersionUID`: Serialization compatibility identifier.

After deserialization, `sessionToken` will normally be `null`. The static `applicationName` value comes from the currently loaded class.

---

## 4. serialVersionUID

`serialVersionUID` identifies a version of a serializable class for compatibility checking.

```java
private static final long serialVersionUID = 1L;
```

### Why Is It Important?

Suppose an object is serialized today and its class is changed before the object is deserialized.

Java compares the serialized version identifier with the identifier of the local class.

- Matching identifiers allow compatibility checks to proceed.
- A mismatch normally causes `InvalidClassException`.
- A matching identifier does not guarantee that every class change is compatible.

If you do not declare an explicit `serialVersionUID`, Java can calculate one from class details. Some class changes may therefore result in a different generated identifier.

**Interview point:** An explicit `serialVersionUID` helps manage version compatibility, but developers must still assess whether changes to the serialized state are compatible.

---

## 5. Serialization Using ObjectOutputStream

`ObjectOutputStream` writes objects to an underlying output stream.

The key method is:

```java
output.writeObject(order);
```

### Example: Serialize an Order

**File:** `SerializationExample.java`

```java
package com.javamastery.javaIO.serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializationExample {

    public static void main(String[] args) {

        Order order = new Order(
                "ORD-1001",
                "Wireless Keyboard",
                2,
                "TEMP-SECRET-123"
        );

        try (ObjectOutputStream output =
                     new ObjectOutputStream(
                             new FileOutputStream("order.ser"))) {

            output.writeObject(order);

            System.out.println("Order serialized successfully.");

        } catch (IOException e) {
            System.err.println(
                    "Serialization failed: " + e.getMessage()
            );
        }
    }
}
```

### Execution Flow

1. Create an `Order` object.
2. Create a `FileOutputStream`.
3. Wrap it in `ObjectOutputStream`.
4. Call `writeObject(order)`.
5. Java serializes the object's serializable state.
6. The serialized bytes are written to `order.ser`.
7. Try-with-resources closes the stream automatically.

The `.ser` extension is a convention, not a Java requirement.

### Important Note

`FileOutputStream` overwrites an existing file by default. Use an appropriate file strategy if you need to preserve existing contents.

---

## 6. Deserialization Using ObjectInputStream

`ObjectInputStream` reconstructs objects from serialized data.

The main method is:

```java
Object object = input.readObject();
```

Because `readObject()` returns an `Object`, the result is commonly cast to the expected type.

### Example: Deserialize an Order

**File:** `DeserializationExample.java`

```java
package com.javamastery.javaIO.serialization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializationExample {

    public static void main(String[] args) {

        try (ObjectInputStream input =
                     new ObjectInputStream(
                             new FileInputStream("order.ser"))) {

            Order order = (Order) input.readObject();

            System.out.println("Order deserialized successfully.");
            order.displayDetails();

        } catch (IOException e) {
            System.err.println(
                    "Deserialization failed: " + e.getMessage()
            );
        } catch (ClassNotFoundException e) {
            System.err.println(
                    "Order class could not be found: " + e.getMessage()
            );
        }
    }
}
```

Run `SerializationExample` first and then `DeserializationExample`, using the same project working directory.

### Expected Output

```text
Order deserialized successfully.
Order ID: ORD-1001
Product: Wireless Keyboard
Quantity: 2
Session Token: null
Application: OrderService
```

### Why Are Exceptions Handled?

- `IOException`: Covers I/O errors and serialization-related failures.
- `ClassNotFoundException`: Can occur if a required class cannot be found during deserialization.

In production code, validate the deserialized object's type and state before using it.

---

## 7. transient Keyword

The `transient` keyword excludes an instance field from Java's default serialization.

```java
private transient String sessionToken;
```

After default deserialization, a transient field receives its default value unless application code restores it.

| Field type | Default value |
|---|---|
| Reference type, such as `String` | `null` |
| `int` | `0` |
| `boolean` | `false` |
| Other primitive types | Corresponding zero value |

### When Should We Use transient?

- Temporary values that can be recomputed.
- In-memory cache fields that should be rebuilt.
- Session-specific information that should not be persisted with the object.

### Important Security Note

Marking a field `transient` is not a complete security solution. Avoid storing secrets in serialized objects unless there is a well-designed security requirement and appropriate protection.

Custom serialization logic can also change how fields are handled.

---

## 8. What Gets Serialized?

| Member | Included in default serialization? |
|---|---|
| Ordinary serializable instance field | Yes |
| Transient instance field | No |
| Static field | No, not as instance state |
| Non-transient field referencing a non-serializable object | Can cause `NotSerializableException` |
| Serializable object referenced by another serializable object | Usually serialized as part of the object graph |

### Important Interview Clarification

Static fields belong to the class, not individual objects. Their values after deserialization come from the currently loaded class, not from the serialized instance.

A serializable object graph can contain nested objects, so serialization eligibility must be considered throughout that graph.

---

## 9. Common Exceptions

### NotSerializableException

Occurs when Java attempts to serialize an object whose relevant state contains a non-serializable object.

### InvalidClassException

Can occur when the serialized class and local class have incompatible serialization versions.

### ClassNotFoundException

Can occur when a class required to reconstruct an object cannot be found.

### EOFException

Can occur when the input ends unexpectedly while serialized data is being read.

### IOException

Can occur due to file access problems, corrupt input, or other I/O failures.

**Best practice:** Use try-with-resources, maintain class compatibility deliberately, and handle expected failures.

---

## 10. Security Risks of Native Deserialization

Java native deserialization can be dangerous when input comes from an untrusted source.

Malicious serialized data may exploit vulnerable classes or gadget chains and trigger unexpected behavior.

### Safer Practices

- Never blindly deserialize arbitrary untrusted input.
- Prefer explicit formats such as JSON for APIs and interoperable storage.
- If native serialization is required, use strict class allowlists and appropriate deserialization filters, such as `ObjectInputFilter`.
- Validate reconstructed object state before using it.
- Do not assume serialization provides encryption or integrity protection.

---

## 11. Common Interview Questions

### Q1. What is serialization?

Serialization converts an object's state into a storable or transmittable representation. Java native serialization uses a byte stream.

### Q2. What is deserialization?

Deserialization reconstructs an object from serialized data.

### Q3. What is Serializable?

It is a marker interface that enables Java's default object serialization mechanism.

### Q4. What is serialVersionUID?

It is a version identifier used to check compatibility between serialized data and the local class.

### Q5. What does transient do?

It excludes an instance field from default serialization.

### Q6. Are static variables serialized?

Not as part of an object's instance state.

### Q7. What does writeObject() do?

It writes an object to an `ObjectOutputStream`.

### Q8. Why does readObject() require a cast?

It returns an `Object`, so a cast is needed when assigning it to a specific class type.

### Q9. Can a serializable class contain a non-serializable field?

Yes, but default serialization may fail if that field is non-transient and not handled through custom serialization.

### Q10. Is native Java deserialization safe for untrusted data?

No. Untrusted deserialization can introduce serious security risks.

### Q11. Does serialization encrypt an object's data?

No. Serialization converts object state into a representation; it does not automatically encrypt or protect it.

### Q12. Does a matching serialVersionUID guarantee compatibility?

No. It allows the version check to pass, but incompatible state or class changes may still cause errors or incorrect application behavior.

---

## 12. Coding Practice

### Problem 1: Add a Price Field

Add a `double price` field to `Order`, serialize it, and verify that the value is restored.

### Problem 2: Test transient

Mark a field as `transient` and verify its value after deserialization.

### Problem 3: Test Serializable

Remove `Serializable` from `Order` and observe the resulting failure.

### Problem 4: Test serialVersionUID

Serialize an object, change the class's explicit `serialVersionUID`, recompile, and attempt deserialization. Observe the compatibility error.

### Problem 5: Serialize a Nested Object

Create a serializable `ProductDetails` class and add it as a field in `Order`. Test serialization with and without `Serializable` on `ProductDetails`.

### Problem 6: Validate Deserialized State

After deserialization, check that the order ID is present and the quantity is positive before using the object.

---

## 13. Interview Explanation

"Serialization in Java converts an object's state into a byte stream, and deserialization reconstructs the object from that stream. The `Serializable` interface is a marker interface used by Java's native serialization mechanism. We use `ObjectOutputStream.writeObject()` to serialize and `ObjectInputStream.readObject()` to deserialize. The `transient` keyword excludes fields from default serialization, while `serialVersionUID` helps manage compatibility between class versions. In production, I would also consider versioning, data validation, and deserialization security risks."

---

## 14. 10-Second Cheat Sheet

- `Serializable` — enables default Java object serialization.
- `ObjectOutputStream` — writes objects.
- `ObjectInputStream` — reads objects.
- `writeObject()` — serialize.
- `readObject()` — deserialize.
- `transient` — exclude an instance field from default serialization.
- `serialVersionUID` — compatibility identifier.
- `NotSerializableException` — non-serializable state cannot be serialized.
- `InvalidClassException` — serialization compatibility problem.
- Never blindly deserialize untrusted input.

---

## 15. Final Mental Model

```text
Object in Memory
       |
       v
ObjectOutputStream
       |
       v
Serialized Byte Stream
       |
       v
File or Other Storage
       |
       v
ObjectInputStream
       |
       v
Reconstructed Object
```

**Remember:** Serialization stores an object's serializable state; it does not preserve every aspect of the original runtime environment. Static and transient fields are not restored as ordinary instance state, and successful deserialization depends on compatible classes and safe input.
