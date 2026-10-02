# Atomic Classes — Interview Cheat Sheet

## 1. What is it?

Atomic classes are classes from:

```java
java.util.concurrent.atomic
```

that provide **thread-safe atomic operations** on shared variables without requiring traditional `synchronized` blocks for those operations.

Common atomic classes:

```text
AtomicInteger
AtomicLong
AtomicBoolean
AtomicReference
```

The most common ones for interviews are:

```text
AtomicInteger → int
AtomicLong    → long
AtomicBoolean → boolean
```

Atomic operations are designed so that a shared value can be safely updated by multiple threads.

---

## 2. Why is it needed?

Multiple threads may update the same variable.

For example:

```java
int count = 0;

count++;
```

`count++` is not one operation.

Conceptually:

```text
READ
 ↓
ADD 1
 ↓
WRITE
```

Two threads can interfere with each other.

### Real-time example

Imagine two workers processing orders:

```text
Worker 1 → orderCount++
Worker 2 → orderCount++
```

Both may read the same value before either writes the updated value.

This can cause a lost update.

Atomic classes provide operations such as:

```java
count.incrementAndGet();
```

where the increment is performed atomically.

---

## 3. How does it work / Internal?

Atomic classes commonly use **CAS (Compare-And-Swap / Compare-And-Set)** based mechanisms.

The basic idea:

```text
Read current value
      ↓
Compare with expected value
      ↓
If equal → update
If different → retry/fail
```

For example:

```java
count.compareAndSet(10, 11);
```

Means:

```text
Is current value 10?
       ↓
    YES → change to 11
    NO  → don't change
```

The check and update happen atomically.

### Important

Atomic classes are not simply "variables with synchronized around every operation."

They provide specialized atomic operations designed for concurrent access.

---

## 4. Syntax

### AtomicInteger

```java
AtomicInteger count = new AtomicInteger(0);
```

### AtomicLong

```java
AtomicLong count = new AtomicLong(0);
```

### AtomicBoolean

```java
AtomicBoolean running = new AtomicBoolean(false);
```

Import:

```java
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicBoolean;
```

---

## 5. Simple Example

### AtomicInteger

```java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {

    public static void main(String[] args) {

        AtomicInteger count = new AtomicInteger(0);

        count.incrementAndGet();

        System.out.println(count.get());
    }
}
```

Output:

```text
1
```

### Important methods

```java
count.get();
count.set(10);

count.incrementAndGet();
count.getAndIncrement();

count.decrementAndGet();
count.getAndDecrement();

count.addAndGet(10);
count.getAndAdd(10);

count.compareAndSet(10, 20);
```

---

## 6. Real-Time Example

### Order processing counter

Imagine multiple backend workers process orders.

```java
import java.util.concurrent.atomic.AtomicInteger;

public class OrderCounter {

    public static void main(String[] args) throws InterruptedException {

        AtomicInteger orderCount = new AtomicInteger(0);

        Thread worker1 = new Thread(() -> {

            for (int i = 0; i < 100_000; i++) {
                orderCount.incrementAndGet();
            }

        });

        Thread worker2 = new Thread(() -> {

            for (int i = 0; i < 100_000; i++) {
                orderCount.incrementAndGet();
            }

        });

        worker1.start();
        worker2.start();

        worker1.join();
        worker2.join();

        System.out.println(orderCount.get());
    }
}
```

Expected:

```text
200000
```

Both threads safely update the same counter.

### Mental model

```text
              AtomicInteger
                   │
          ┌────────┴────────┐
          ↓                 ↓
      Worker 1           Worker 2
      +100,000           +100,000
          │                 │
          └────────┬────────┘
                   ↓
                200,000
```

---

## 7. Important Rules

### Rule 1 — `incrementAndGet()`

```java
count.incrementAndGet();
```

Increments first and returns the new value.

```text
count = 10

incrementAndGet()
      ↓
count = 11
returns 11
```

---

### Rule 2 — `getAndIncrement()`

```java
count.getAndIncrement();
```

Returns the old value first, then increments.

```text
count = 10

getAndIncrement()
      ↓
returns 10
count becomes 11
```

Remember:

```text
incrementAndGet → increment → return new value

getAndIncrement → return old value → increment
```

---

### Rule 3 — `compareAndSet()`

```java
count.compareAndSet(expected, newValue);
```

Example:

```java
count.compareAndSet(10, 20);
```

Means:

```text
If count == 10
    ↓
change count to 20
```

Returns:

```text
true  → update succeeded
false → expected value didn't match
```

---

### Rule 4 — AtomicBoolean

Useful for simple thread-safe state changes.

```java
AtomicBoolean started = new AtomicBoolean(false);
```

Example:

```java
if (started.compareAndSet(false, true)) {
    System.out.println("Service started");
}
```

Only one thread can successfully change:

```text
false → true
```

---

### Rule 5 — AtomicLong

Useful when an atomic counter requires `long`.

```java
AtomicLong requestCount = new AtomicLong(0);

requestCount.incrementAndGet();
```

Common examples:

```text
Request counters
Transaction counters
Sequence numbers
Large metrics
```

---

### Rule 6 — Atomic classes don't make an entire object thread-safe

For example:

```java
AtomicInteger count;
String name;
List<String> orders;
```

Making `count` atomic does not automatically make `name` or `orders` thread-safe.

Atomic guarantees apply to the operations provided by the atomic class.

---

### Rule 7 — Atomic does not mean "everything is atomic"

This is important.

Suppose:

```java
AtomicInteger count = new AtomicInteger(10);
```

This is atomic:

```java
count.incrementAndGet();
```

But a sequence of multiple operations may still require coordination.

```text
READ
 ↓
decision
 ↓
operation
 ↓
another operation
```

If the entire sequence must be protected as one critical section, consider:

```text
synchronized
Lock
```

---

### Rule 8 — Atomic classes are useful for lock-free style operations

Atomic classes can perform many common updates without explicit locking such as:

```java
synchronized (...) {
    count++;
}
```

Instead:

```java
count.incrementAndGet();
```

This can be simpler and more appropriate for simple shared-state operations.

---

## 8. Common Mistakes

### Mistake 1 — Thinking volatile is enough for `count++`

Wrong:

```java
volatile int count = 0;

count++;
```

`volatile` provides visibility but does not make `count++` atomic.

Use:

```java
AtomicInteger count = new AtomicInteger(0);

count.incrementAndGet();
```

---

### Mistake 2 — Printing before `join()`

Wrong:

```java
worker1.start();
worker2.start();

System.out.println(count.get());

worker1.join();
worker2.join();
```

The workers may not have finished.

Correct:

```java
worker1.start();
worker2.start();

worker1.join();
worker2.join();

System.out.println(count.get());
```

---

### Mistake 3 — Confusing `getAndIncrement()` and `incrementAndGet()`

```text
getAndIncrement()
→ returns old value

incrementAndGet()
→ returns new value
```

---

### Mistake 4 — Assuming CAS always succeeds

```java
count.compareAndSet(10, 20);
```

It succeeds only if the current value is still `10`.

Another thread may have changed it first.

---

### Mistake 5 — Using AtomicInteger for everything

AtomicInteger is useful for simple atomic state changes and counters.

For larger critical sections involving multiple variables or multiple operations, use:

```text
synchronized
Lock
```

when appropriate.

---

### Mistake 6 — Forgetting to use `get()`

This:

```java
System.out.println(count);
```

works because `AtomicInteger` has a `toString()` representation.

But for explicit code and interviews, prefer:

```java
System.out.println(count.get());
```

---

## 9. Interview Questions

### Q1. What are Atomic classes in Java?

Atomic classes provide thread-safe atomic operations on shared variables without requiring explicit synchronization for those individual operations.

They are available in:

```java
java.util.concurrent.atomic
```

---

### Q2. Why use AtomicInteger instead of volatile int?

`volatile` provides visibility but does not make compound operations such as `count++` atomic.

`AtomicInteger` provides atomic operations such as:

```java
incrementAndGet()
compareAndSet()
addAndGet()
```

---

### Q3. What is CAS?

CAS stands for **Compare-And-Set** or **Compare-And-Swap**.

It checks whether a value is equal to an expected value and updates it only if the comparison succeeds.

```java
count.compareAndSet(10, 20);
```

---

### Q4. What does compareAndSet() return?

```text
true
→ update succeeded

false
→ expected value didn't match
```

---

### Q5. What is the difference between incrementAndGet() and getAndIncrement()?

```text
incrementAndGet()
→ increment first → return new value

getAndIncrement()
→ return old value → increment
```

---

### Q6. Is AtomicInteger thread-safe?

Yes.

Its provided atomic operations are designed for safe concurrent access.

---

### Q7. Does AtomicInteger use synchronized?

Atomic classes are implemented using low-level concurrency mechanisms and commonly rely on CAS rather than traditional `synchronized` locking for their core atomic operations.

---

### Q8. When should you use AtomicInteger?

Use it when multiple threads need to perform simple atomic operations on an integer, such as:

```text
Counters
Metrics
Sequence numbers
Flags represented as integers
```

---

### Q9. AtomicInteger vs synchronized?

```text
AtomicInteger
→ simple atomic operations
→ counters/state
→ CAS-based operations

synchronized
→ protects a critical section
→ multiple operations
→ multiple shared variables
```

---

### Q10. Can AtomicInteger replace synchronized everywhere?

No.

Atomic classes are ideal for specific atomic operations.

If multiple operations must be treated as one indivisible operation, a lock or synchronization mechanism may be required.

---

### Q11. What is AtomicBoolean useful for?

It is useful for thread-safe boolean state changes.

Example:

```java
AtomicBoolean started = new AtomicBoolean(false);

if (started.compareAndSet(false, true)) {
    startService();
}
```

---

### Q12. What is AtomicLong useful for?

It provides atomic operations on `long` values.

Example:

```java
AtomicLong requestCount = new AtomicLong(0);

requestCount.incrementAndGet();
```

---

## 10. Coding Practice

### Practice 1 — Atomic Counter

Create:

```java
AtomicInteger orderCount = new AtomicInteger(0);
```

Create two threads.

Each thread should increment the counter 100,000 times.

Expected:

```text
200000
```

---

### Practice 2 — Compare-And-Set

Create:

```java
AtomicInteger orderStatus = new AtomicInteger(0);
```

Use:

```text
0 → NEW
1 → PROCESSING
```

Two worker threads should attempt:

```java
orderStatus.compareAndSet(0, 1);
```

Only one thread should successfully claim the order.

---

### Practice 3 — AtomicBoolean

Create:

```java
AtomicBoolean serviceStarted = new AtomicBoolean(false);
```

Two threads should attempt:

```java
serviceStarted.compareAndSet(false, true);
```

Expected:

```text
One thread → started the service
One thread → service was already started
```

---

### Practice 4 — AtomicLong

Create:

```java
AtomicLong requestCount = new AtomicLong(0);
```

Create multiple worker threads.

Each worker increments the request count.

Print the final value after all threads complete.

---

## 11. 10-Second Cheat Sheet

```text
Atomic Classes
      ↓
java.util.concurrent.atomic
```

### Main classes

```text
AtomicInteger → int
AtomicLong    → long
AtomicBoolean → boolean
AtomicReference → object/reference
```

### Important operations

```java
get()
set()

incrementAndGet()
getAndIncrement()

decrementAndGet()
getAndDecrement()

addAndGet()
getAndAdd()

compareAndSet()
```

### Core concept

```text
CAS
 ↓
Compare expected value
 ↓
If equal → update
If different → fail
```

### Most important distinction

```text
volatile
→ visibility

Atomic
→ atomic operations

synchronized
→ mutual exclusion
```

---

## 12. Final Mental Model

Think about concurrency as three different problems.

### Problem 1 — Visibility

```text
"I changed a value.
Will another thread see it?"
```

Use:

```java
volatile
```

---

### Problem 2 — Atomic update

```text
"I need to update this shared value safely."
```

Use:

```java
AtomicInteger
AtomicLong
AtomicBoolean
```

---

### Problem 3 — Critical section

```text
"I need several operations to execute together,
and another thread must not interfere."
```

Use:

```java
synchronized
```

or:

```java
Lock
```

### Final comparison

```text
                 Concurrency Problem
                        │
          ┌─────────────┼─────────────┐
          ↓             ↓             ↓
     Visibility      Atomicity    Mutual Exclusion
          │             │             │
      volatile       Atomic*     synchronized
                                      /
                                    Lock
```

### One-line memory trick

```text
volatile     → SEE the latest value
Atomic       → UPDATE safely
synchronized → PROTECT a critical section
Lock         → CONTROL the lock
```

### Interview-ready answer

> Atomic classes provide thread-safe atomic operations for shared variables. Classes such as AtomicInteger, AtomicLong, and AtomicBoolean use concurrency mechanisms such as CAS to perform updates safely without traditional locking for those individual operations. They are useful for counters and simple shared state, while synchronized or Lock is more appropriate when multiple operations need to be protected as one critical section.