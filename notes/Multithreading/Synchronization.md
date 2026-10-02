# Synchronization — Interview Cheat Sheet

## 1. What is it?

Synchronization is a mechanism used to control access to shared mutable data when multiple threads execute concurrently.

It prevents multiple threads from modifying the same critical section at the same time.

Main purpose:

```text
Multiple Threads
       ↓
Shared Data
       ↓
Race Condition
       ↓
Synchronization
       ↓
One thread at a time
```

Java provides synchronization using the `synchronized` keyword.

---

## 2. Why is it needed?

Consider a shared counter:

```java
static int counter = 0;
```

Two threads execute:

```java
counter++;
```

`counter++` is not a single atomic operation.

Conceptually:

```text
READ counter
     ↓
ADD 1
     ↓
WRITE counter
```

Two threads can interfere with each other.

Example:

```text
Initial counter = 100

Thread 1 → READ 100
Thread 2 → READ 100

Thread 1 → WRITE 101
Thread 2 → WRITE 101
```

Expected:

```text
102
```

Actual:

```text
101
```

This is a race condition.

Synchronization protects the critical section and prevents this problem.

---

## 3. How does it work / Internal?

Java synchronization uses a **monitor lock** associated with an object or class.

Example:

```java
synchronized (lock) {
    counter++;
}
```

A thread must acquire the lock before entering the synchronized block.

```text
Thread 1
   ↓
Acquire Lock
   ↓
Execute Critical Section
   ↓
Release Lock
```

If another thread tries to acquire the same lock:

```text
Thread 2
   ↓
Try Lock
   ↓
Lock Already Held
   ↓
BLOCKED / waits for lock
```

After Thread 1 releases the lock, another waiting thread can acquire it.

### Important

Synchronization only works when competing threads use the **same lock**.

```java
synchronized (lock1) {
    // protected by lock1
}
```

is not mutually exclusive with:

```java
synchronized (lock2) {
    // protected by lock2
}
```

because `lock1` and `lock2` are different locks.

---

## 4. Syntax

### Synchronized Method

```java
public synchronized void increment() {
    counter++;
}
```

For an instance method, the lock is:

```java
this
```

Equivalent to:

```java
public void increment() {
    synchronized (this) {
        counter++;
    }
}
```

---

### Synchronized Block

```java
public void increment() {

    synchronized (this) {
        counter++;
    }
}
```

Only the code inside the block is protected.

---

### Class Lock

```java
synchronized (MyClass.class) {
    // critical section
}
```

Used when threads need to coordinate using a class-level lock.

---

### Static Synchronized Method

```java
public static synchronized void increment() {
    counter++;
}
```

Equivalent to:

```java
public static void increment() {
    synchronized (MyClass.class) {
        counter++;
    }
}
```

---

## 5. Simple Example

### Race Condition

```java
static int counter = 0;

Thread t1 = new Thread(() -> {
    for (int i = 0; i < 1000; i++) {
        counter++;
    }
});

Thread t2 = new Thread(() -> {
    for (int i = 0; i < 1000; i++) {
        counter++;
    }
});
```

Expected:

```text
2000
```

But without synchronization, the result can be inconsistent because of concurrent access.

---

### Fixed with synchronized block

```java
static int counter = 0;

Thread t1 = new Thread(() -> {
    for (int i = 0; i < 1000; i++) {
        synchronized (MyClass.class) {
            counter++;
        }
    }
});

Thread t2 = new Thread(() -> {
    for (int i = 0; i < 1000; i++) {
        synchronized (MyClass.class) {
            counter++;
        }
    }
});
```

Both threads use the same class lock.

Therefore:

```text
Thread 1 → 🔒 → counter++ → 🔓
Thread 2 → 🔒 → counter++ → 🔓
```

---

## 6. Real-Time Example

### Shop Inventory

Suppose a shop has:

```java
static int stock = 10;
```

Two customers try to buy items simultaneously.

Without synchronization:

```text
Customer 1 → reads stock = 1
Customer 2 → reads stock = 1

Customer 1 → sells item
Customer 2 → sells item
```

The same item could effectively be sold twice.

With synchronization:

```java
synchronized (Shop.class) {

    if (stock > 0) {
        stock--;
        System.out.println("Item sold");
    } else {
        System.out.println("Out of stock");
    }
}
```

Now:

```text
Customer 1
    ↓
🔒 Lock
    ↓
Check stock
    ↓
Decrease stock
    ↓
🔓 Unlock

Customer 2
    ↓
🔒 Lock
    ↓
Check updated stock
```

This prevents the shared stock from being incorrectly modified concurrently.

---

## 7. Important Rules

### Rule 1 — Synchronization protects shared mutable data

Typical examples:

```text
Bank Balance
Inventory
Order Count
Ticket Availability
Shared Counter
Payment Status
```

---

### Rule 2 — `counter++` is not atomic

```java
counter++;
```

is conceptually:

```text
READ
ADD
WRITE
```

Therefore it can suffer from a race condition.

---

### Rule 3 — Keep the critical section small

Prefer:

```java
public void process() {

    // Other work

    synchronized (this) {
        stock--;
    }

    // Other work
}
```

instead of unnecessarily locking the entire method.

Smaller critical sections can improve concurrency.

---

### Rule 4 — Same lock is required

Correct:

```java
synchronized (lock) {
    counter++;
}
```

and another thread also uses:

```java
synchronized (lock) {
    counter++;
}
```

Incorrect protection:

```java
synchronized (lock1) {
    counter++;
}
```

and:

```java
synchronized (lock2) {
    counter++;
}
```

Different locks do not protect each other.

---

### Rule 5 — `synchronized(this)` uses an object lock

```java
synchronized (this) {
    // ...
}
```

Each object has its own lock.

For:

```java
Shop shop1 = new Shop();
Shop shop2 = new Shop();
```

there are two different object locks.

```text
shop1 → 🔒 Lock 1

shop2 → 🔒 Lock 2
```

Threads using different objects can execute concurrently.

---

### Rule 6 — Class lock is shared

```java
synchronized (Shop.class) {
    // ...
}
```

All objects of that class use the same class-level lock.

```text
shop1 ──┐
shop2 ──┼──→ Shop.class 🔒
shop3 ──┘
```

Only one thread can hold that class lock at a time.

---

### Rule 7 — Instance synchronized method uses object lock

```java
public synchronized void sellItem() {
    // ...
}
```

Equivalent to:

```java
synchronized (this) {
    // ...
}
```

---

### Rule 8 — Static synchronized method uses class lock

```java
public static synchronized void sellItem() {
    // ...
}
```

Equivalent to:

```java
synchronized (Shop.class) {
    // ...
}
```

---

### Rule 9 — `join()` and `synchronized` solve different problems

`join()`:

```text
Wait for another thread to finish
```

`synchronized`:

```text
Control access to shared data
```

Example:

```java
t1.start();
t2.start();

t1.join();
t2.join();
```

This ensures both threads finish before the main thread continues.

It does NOT prevent race conditions inside the threads.

---

### Rule 10 — Race condition does not always produce wrong output

You may run an unsynchronized program and get:

```text
2000
```

That does not prove the code is thread-safe.

A race condition depends on thread timing and execution interleaving.

---

### Rule 11 — Synchronization also provides memory visibility

Synchronization is not only about preventing simultaneous execution.

Entering and exiting synchronized sections establishes memory visibility guarantees between synchronized accesses using the same monitor.

This is part of Java's happens-before relationship.

---

## 8. Common Mistakes

### Mistake 1 — Thinking `counter++` is atomic

```java
counter++;
```

It is not atomic.

---

### Mistake 2 — Using different locks

```java
synchronized (lock1) {
    counter++;
}
```

and:

```java
synchronized (lock2) {
    counter++;
}
```

This does not provide mutual exclusion between the two sections.

---

### Mistake 3 — Assuming synchronized makes everything single-threaded

Incorrect:

> synchronized makes the whole application single-threaded.

Correct:

> synchronized allows only one thread at a time to enter code protected by the same lock.

Different locks can still allow concurrent execution.

---

### Mistake 4 — Locking too much code

Avoid unnecessarily large critical sections:

```java
synchronized (lock) {

    // 500 lines of unrelated work

}
```

Protect only the shared critical operation when practical.

---

### Mistake 5 — Forgetting what `this` means

```java
synchronized (this)
```

means:

> Lock the current object.

It does not mean class-level locking.

---

### Mistake 6 — Confusing object lock and class lock

```java
synchronized (this)
```

→ object lock

```java
synchronized (MyClass.class)
```

→ class lock

---

### Mistake 7 — Putting code outside the synchronized block accidentally

```java
synchronized (lock) {
    // protected
}

System.out.println("This is not protected");
```

Only code inside `{ }` is protected.

---

## 9. Interview Questions

### Q1. What is synchronization?

Synchronization is a mechanism that controls access to shared mutable data so that multiple threads do not execute conflicting critical sections simultaneously.

### Q2. What is a race condition?

A race condition occurs when multiple threads access shared mutable data concurrently and the result depends on their execution timing.

### Q3. What is a critical section?

A critical section is a part of code that accesses shared mutable data and must be protected from concurrent conflicting access.

### Q4. What does `synchronized` provide?

It provides mutual exclusion and memory visibility through monitor locking.

### Q5. What lock does a synchronized instance method use?

The current object (`this`).

### Q6. What lock does a static synchronized method use?

The class object's monitor, such as `MyClass.class`.

### Q7. What is the difference between synchronized method and synchronized block?

A synchronized method locks the entire method, while a synchronized block locks only a specific section of code.

### Q8. Can two synchronized methods execute simultaneously?

It depends on their locks.

If both are instance synchronized methods on the same object:

```text
Same object → same lock → cannot execute simultaneously
```

If they operate on different objects:

```text
Different objects → different locks → can execute simultaneously
```

### Q9. Does `synchronized` guarantee fairness?

No.

It does not guarantee which waiting thread will acquire the lock next.

### Q10. Does `sleep()` release a synchronized lock?

No.

If a thread calls `sleep()` while holding a monitor lock, it keeps the lock while sleeping.

### Q11. Does `join()` prevent race conditions?

No.

`join()` only makes the calling thread wait for another thread to finish.

### Q12. Why use synchronized blocks instead of synchronized methods?

When only a small critical section needs protection, a synchronized block gives more precise control over locking.

---

## 10. Coding Practice

### Practice 1 — Race Condition

Create:

```java
static int counter = 0;
```

Run two threads:

```java
for (int i = 0; i < 1000; i++) {
    counter++;
}
```

Observe the behavior.

---

### Practice 2 — Synchronized Method

Create a shop:

```java
static int stock = 10;
```

Create:

```java
public synchronized void sellItem() {
    if (stock > 0) {
        stock--;
    }
}
```

Have two threads attempt to sell 6 items each.

Expected final stock:

```text
0
```

---

### Practice 3 — Synchronized Block

Convert the method into:

```java
public void sellItem() {

    synchronized (this) {
        if (stock > 0) {
            stock--;
        }
    }
}
```

Understand why only the critical section is protected.

---

### Practice 4 — Object vs Class Lock

Create two objects:

```java
Shop shop1 = new Shop();
Shop shop2 = new Shop();
```

Test:

```java
synchronized (this)
```

Then test:

```java
synchronized (Shop.class)
```

Observe how the locking behavior changes.

---

## 11. 10-Second Cheat Sheet

```text
Race Condition
    ↓
Multiple threads + shared mutable data
    ↓
Incorrect / timing-dependent result

Critical Section
    ↓
Code accessing shared mutable data

synchronized
    ↓
One thread at a time for the same lock

synchronized(this)
    ↓
Object lock

synchronized(MyClass.class)
    ↓
Class lock

synchronized method
    ↓
Entire method protected

synchronized block
    ↓
Only selected section protected

join()
    ↓
Wait for another thread

sleep()
    ↓
Wait for time
```

---

## 12. Final Mental Model

Think about a **shared shop register**:

```text
                 SHARED RESOURCE
                       ↓
                  🧾 Register
                       ↓
              Multiple Threads
                 ↙          ↘
          Customer 1      Customer 2
                 ↘          ↙
                  🔒 LOCK
                     ↓
              One at a time
                     ↓
             Update safely
                     ↓
                  🔓 UNLOCK
```

The complete synchronization mental model:

```text
Multiple Threads
       ↓
Shared Mutable Data
       ↓
Race Condition
       ↓
Identify Critical Section
       ↓
Choose a Lock
       ↓
synchronized
       ↓
Mutual Exclusion + Visibility
       ↓
Thread-safe access
```

### Remember

```text
synchronized = "Protect this shared operation with a lock."
```

And:

```text
this
 ↓
Object Lock

ClassName.class
 ↓
Class Lock
```

Next topic:

```text
Thread Communication
        ↓
   wait()
   notify()
   notifyAll()
```

This is where threads learn to **coordinate with each other**, rather than simply protecting shared data.