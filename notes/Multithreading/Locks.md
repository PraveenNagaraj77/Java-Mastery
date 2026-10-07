# Locks — Interview Cheat Sheet

## 1. What is it?

A **Lock** is a Java concurrency mechanism used to control access to shared resources when multiple threads execute concurrently.

It provides **mutual exclusion**, meaning only one thread can enter a protected critical section at a time.

The main Lock API is:

```java
java.util.concurrent.locks.Lock
```

A common implementation is:

```java
ReentrantLock
```

Basic pattern:

```java
Lock lock = new ReentrantLock();

lock.lock();

try {
    // Critical Section
} finally {
    lock.unlock();
}
```

---

## 2. Why is it needed?

Multiple threads may access the same shared data at the same time.

Example:

```text
Customer-1 ──┐
Customer-2 ──┼──→ Shared Stock
Customer-3 ──┘
```

Without synchronization:

```text
Stock = 1

Thread-1 → sees stock = 1
Thread-2 → sees stock = 1

Both may sell the same item.
```

A lock protects the critical section:

```text
Thread
   ↓
Acquire Lock
   ↓
Critical Section
   ↓
Release Lock
```

Locks are useful when we need more control than basic `synchronized`.

---

## 3. How does it work / Internal?

`Lock` is an interface.

```java
public interface Lock
```

`ReentrantLock` is one implementation:

```text
Lock
  ↑
ReentrantLock
```

When a thread calls:

```java
lock.lock();
```

If the lock is available:

```text
Thread-1 → acquires lock
```

If another thread tries to acquire the same lock:

```text
Thread-1 → owns lock
Thread-2 → waits
Thread-3 → waits
```

When Thread-1 executes:

```java
lock.unlock();
```

another waiting thread can acquire the lock.

### Reentrant behavior

`ReentrantLock` is called "reentrant" because the same thread can acquire the same lock multiple times.

Example:

```java
lock.lock();

try {
    lock.lock();

    try {
        // Nested critical section
    } finally {
        lock.unlock();
    }

} finally {
    lock.unlock();
}
```

Conceptually:

```text
First lock()  → hold count = 1
Second lock() → hold count = 2

First unlock()  → hold count = 1
Second unlock() → hold count = 0 → lock released
```

Every successful `lock()` must have a corresponding `unlock()`.

---

## 4. Syntax

### Basic Lock

```java
Lock lock = new ReentrantLock();

lock.lock();

try {
    // Critical Section
} finally {
    lock.unlock();
}
```

### tryLock()

```java
if (lock.tryLock()) {

    try {
        // Critical Section
    } finally {
        lock.unlock();
    }

} else {
    // Lock unavailable
}
```

### Timed tryLock()

```java
if (lock.tryLock(5, TimeUnit.SECONDS)) {

    try {
        // Critical Section
    } finally {
        lock.unlock();
    }

} else {
    // Could not acquire lock within 5 seconds
}
```

### lockInterruptibly()

```java
try {

    lock.lockInterruptibly();

    try {
        // Critical Section
    } finally {
        lock.unlock();
    }

} catch (InterruptedException e) {

    Thread.currentThread().interrupt();

    // Handle cancellation
}
```

### Fair Lock

```java
Lock lock = new ReentrantLock(true);
```

### Non-Fair Lock

```java
Lock lock = new ReentrantLock();
```

---

## 5. Simple Example

```java
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {

    static int counter = 0;

    static Lock lock = new ReentrantLock();

    static void increment() {

        lock.lock();

        try {
            counter++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter);
    }
}
```

Expected:

```text
2000
```

The lock protects:

```java
counter++;
```

so only one thread modifies the shared variable at a time.

---

## 6. Real-Time Example

Consider an online shop with limited stock.

```java
static int stock = 10;

static Lock stockLock = new ReentrantLock();

static void sellItem() {

    stockLock.lock();

    try {

        if (stock > 0) {

            stock--;

            System.out.println(
                Thread.currentThread().getName()
                + " sold item. Stock: "
                + stock
            );

        } else {

            System.out.println("Out of Stock");
        }

    } finally {

        stockLock.unlock();
    }
}
```

Multiple customers can call:

```java
sellItem();
```

but only one customer can execute the protected section at a time.

```text
Customer-1
     ↓
Acquire Lock
     ↓
Check Stock
     ↓
Decrease Stock
     ↓
Release Lock
     ↓
Customer-2
```

This prevents two customers from incorrectly modifying the same stock value simultaneously.

---

## 7. Important Rules

### Rule 1: Always unlock in finally

Correct:

```java
lock.lock();

try {
    // Critical Section
} finally {
    lock.unlock();
}
```

This ensures the lock is released even if an exception occurs.

---

### Rule 2: Only unlock a lock you acquired

With `tryLock()`:

```java
if (lock.tryLock()) {

    try {
        // Work
    } finally {
        lock.unlock();
    }
}
```

Do not do:

```java
lock.tryLock();

lock.unlock();
```

because `tryLock()` may return `false`.

---

### Rule 3: `lock()` waits

```java
lock.lock();
```

If another thread owns the lock, the current thread waits.

---

### Rule 4: `tryLock()` does not wait indefinitely

```java
if (lock.tryLock()) {
    // Acquired
} else {
    // Unavailable
}
```

It immediately returns:

```text
true  → acquired
false → unavailable
```

---

### Rule 5: Timed tryLock waits only for a limited time

```java
lock.tryLock(5, TimeUnit.SECONDS);
```

The thread waits for at most 5 seconds.

---

### Rule 6: `lockInterruptibly()` allows interruption while waiting

```java
lock.lockInterruptibly();
```

A thread waiting to acquire the lock can respond to:

```java
thread.interrupt();
```

and receive:

```java
InterruptedException
```

---

### Rule 7: ReentrantLock is reentrant

The same thread can acquire the same lock multiple times.

Each acquisition must have a corresponding release.

---

### Rule 8: Fairness is optional

```java
new ReentrantLock(true);
```

enables fair locking.

Default:

```java
new ReentrantLock();
```

is non-fair.

---

### Rule 9: Fair does not mean perfectly deterministic

Fair locking favors eligible longest-waiting threads, but thread scheduling can still affect observed execution.

Do not say:

```text
Fair Lock = guaranteed exact FIFO execution
```

---

### Rule 10: Keep the critical section small

Avoid:

```java
lock.lock();

try {
    // Large amount of unrelated work
    // Network calls
    // Long calculations
    // File operations
} finally {
    lock.unlock();
}
```

Prefer protecting only the shared resource that needs synchronization.

---

## 8. Common Mistakes

### Mistake 1: Forgetting unlock()

Bad:

```java
lock.lock();

try {
    // Work
}
```

Correct:

```java
lock.lock();

try {
    // Work
} finally {
    lock.unlock();
}
```

---

### Mistake 2: Unlocking without acquiring

Bad:

```java
if (lock.tryLock()) {
    // Work
}

lock.unlock();
```

Correct:

```java
if (lock.tryLock()) {

    try {
        // Work
    } finally {
        lock.unlock();
    }
}
```

---

### Mistake 3: Using `tryLock()` like `lock()`

Wrong mental model:

```text
tryLock() → wait until available
```

Correct:

```text
tryLock() → try immediately
```

---

### Mistake 4: Ignoring InterruptedException

Bad:

```java
catch (InterruptedException e) {
}
```

Better:

```java
catch (InterruptedException e) {
    Thread.currentThread().interrupt();
}
```

---

### Mistake 5: Assuming fair lock means guaranteed FIFO

Fairness provides an acquisition policy that favors waiting threads.

It does not mean every observed execution order will always be perfectly predictable.

---

### Mistake 6: Holding locks for too long

Long critical sections reduce concurrency.

Keep the protected section as small as practical.

---

### Mistake 7: Deadlock

Example:

```text
Thread-1:
Lock A → waits for Lock B

Thread-2:
Lock B → waits for Lock A
```

Both threads wait forever.

Avoid unnecessary multiple-lock dependencies and maintain a consistent lock acquisition order.

---

## 9. Interview Questions

### Q1. What is a Lock in Java?

A Lock is a concurrency mechanism used to provide mutual exclusion and control access to shared resources.

---

### Q2. Which package contains Lock?

```java
java.util.concurrent.locks
```

---

### Q3. What is ReentrantLock?

`ReentrantLock` is a concrete implementation of the `Lock` interface that allows the same thread to acquire the same lock multiple times.

---

### Q4. Why use ReentrantLock instead of synchronized?

`ReentrantLock` provides additional control such as:

```text
tryLock()
Timed tryLock()
lockInterruptibly()
Fairness
```

`synchronized` provides simpler automatic lock management.

---

### Q5. What is tryLock()?

`tryLock()` attempts to acquire the lock immediately.

It returns:

```text
true  → lock acquired
false → lock unavailable
```

---

### Q6. What is timed tryLock()?

```java
lock.tryLock(5, TimeUnit.SECONDS);
```

It waits for a maximum specified duration to acquire the lock.

---

### Q7. What is lockInterruptibly()?

It allows a thread waiting to acquire a lock to respond to interruption.

If interrupted while waiting, it throws:

```java
InterruptedException
```

---

### Q8. What is a fair ReentrantLock?

```java
new ReentrantLock(true);
```

A fair lock favors eligible threads that have been waiting longer.

---

### Q9. What is the default fairness policy?

```java
new ReentrantLock();
```

creates a non-fair lock.

---

### Q10. Why is finally important with Lock?

Because it guarantees that the lock is released even if an exception occurs.

```java
finally {
    lock.unlock();
}
```

---

### Q11. What happens if unlock() is called by a thread that doesn't own the lock?

`ReentrantLock` throws:

```java
IllegalMonitorStateException
```

---

### Q12. Is ReentrantLock reentrant?

Yes.

The owning thread can acquire the same lock multiple times.

---

### Q13. Does tryLock() block?

Normal:

```java
tryLock()
```

does not wait indefinitely.

Timed:

```java
tryLock(timeout, unit)
```

can wait for the specified duration.

---

### Q14. What is the difference between Lock and synchronized?

```text
synchronized
→ simpler
→ automatic release
→ no tryLock()
→ no timed acquisition
→ no interruptible lock acquisition
→ no configurable fairness

Lock / ReentrantLock
→ explicit lock/unlock
→ tryLock()
→ timed acquisition
→ interruptible acquisition
→ fairness option
```

---

### Q15. Lock vs Atomic vs volatile?

```text
volatile
→ Visibility + ordering

Atomic
→ Atomic thread-safe operations

Lock
→ Mutual exclusion / critical section
```

---

## 10. Coding Practice

### Practice 1 — Basic Lock

Create two threads that increment a shared counter 10,000 times each using `ReentrantLock`.

Expected:

```text
20000
```

---

### Practice 2 — Inventory

Create:

```text
stock = 10
```

Create multiple customer threads.

Each customer attempts to sell items using `ReentrantLock`.

Ensure stock never becomes negative.

---

### Practice 3 — tryLock()

Create a payment terminal.

Only one customer can use it at a time.

If the terminal is busy:

```text
Payment Terminal Busy
```

Use:

```java
tryLock()
```

---

### Practice 4 — Timed tryLock()

Create two payment workers.

Worker-1 holds the terminal for 10 seconds.

Worker-2 waits only 5 seconds.

Expected behavior:

```text
Worker-1 → Processing Payment

Worker-2 → waits

After 5 seconds:
Worker-2 → Payment Terminal Busy
```

---

### Practice 5 — lockInterruptibly()

Create:

```text
Worker-1 → holds lock
Worker-2 → waits for lock
Main      → interrupts Worker-2
```

Worker-2 should catch:

```java
InterruptedException
```

and restore the interrupt status:

```java
Thread.currentThread().interrupt();
```

---

### Practice 6 — Fair Lock

Create five workers:

```text
Worker-1
Worker-2
Worker-3
Worker-4
Worker-5
```

Use:

```java
new ReentrantLock(true);
```

Observe lock acquisition behavior.

Then compare with:

```java
new ReentrantLock();
```

Remember that non-fair scheduling is not guaranteed to produce a different order every run.

---

## 11. 10-Second Cheat Sheet

```text
Lock
→ Manual mutual exclusion

ReentrantLock
→ Common Lock implementation

lock()
→ Wait until lock is available

unlock()
→ Release lock

tryLock()
→ Try immediately

tryLock(timeout)
→ Wait for limited time

lockInterruptibly()
→ Waiting for lock can be interrupted

Reentrant
→ Same thread can acquire same lock again

Fair
→ new ReentrantLock(true)

Non-Fair
→ new ReentrantLock()

finally
→ Always release acquired lock

Main rule
→ lock() → try → finally → unlock()
```

---

## 12. Final Mental Model

Think of a Lock as a **single key to a shared resource**.

```text
                 Shared Resource
                       ↑
                       │
                  ┌────┴────┐
                  │   LOCK  │
                  └────┬────┘
                       │
            Only one thread at a time
                       │
        ┌──────────────┼──────────────┐
        ↓              ↓              ↓
    Worker-1        Worker-2       Worker-3
        │
     gets lock
        │
   Critical Section
        │
     unlock()
        │
        ↓
    Next Worker
```

Different lock operations answer different requirements:

```text
Need mutual exclusion?
        ↓
     lock()

Need non-blocking attempt?
        ↓
    tryLock()

Need limited waiting?
        ↓
 tryLock(timeout)

Need cancellation while waiting?
        ↓
 lockInterruptibly()

Need waiting-order fairness?
        ↓
 ReentrantLock(true)
```

### Final comparison

```text
volatile
   ↓
Visibility

Atomic
   ↓
Atomic operations

synchronized
   ↓
Simple mutual exclusion

ReentrantLock
   ↓
Flexible mutual exclusion
   ├── tryLock()
   ├── timed tryLock()
   ├── lockInterruptibly()
   └── fairness
```

**Core interview sentence:**

> `ReentrantLock` provides explicit mutual exclusion with additional control over lock acquisition, including non-blocking attempts, timed acquisition, interruptible acquisition, and fairness.