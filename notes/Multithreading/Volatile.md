<<<<<<< HEAD
# volatile — Interview Cheat Sheet

## 1. What is it?

`volatile` is a Java keyword used in multithreading to guarantee **visibility of changes** to a variable across threads.

When one thread updates a `volatile` variable, other threads are guaranteed to see the updated value.

```java
private volatile boolean running = true;
```

Main purpose:

```text
volatile → visibility + ordering
```

It does **not** provide mutual exclusion and does **not** make compound operations atomic.

---

## 2. Why is it needed?

Multiple threads may access the same variable.

Without proper visibility guarantees, one thread is not guaranteed to immediately observe another thread's update.

### Real-time example

Imagine a background worker in a backend application.

```text
Application
    │
    ├── Main Thread
    │      │
    │      └── running = false
    │
    └── Worker Thread
           │
           └── while(running)
```

The main thread wants to stop the worker.

Using:

```java
volatile boolean running = true;
```

ensures the worker sees the updated value.

Typical use cases:

- Application shutdown flags
- Service running/stopped flags
- Worker control flags
- Configuration/state flags
- Simple status indicators shared between threads

---

## 3. How does it work / Internal?

Each thread can work with values from its own CPU cache/registers rather than always reading directly from shared memory.

Conceptually:

```text
Without volatile

Thread 1              Thread 2
   │                     │
   │ running = false     │
   │                     │
   └──────────────X──────┤
                         │
                  visibility not
                  properly guaranteed
```

With `volatile`:

```text
Thread 1              Thread 2
   │                     │
   │ running = false     │
   │────────────────────>│
   │                     │
   │                sees false
```

`volatile` establishes visibility and ordering guarantees according to the Java Memory Model.

### Important

`volatile` does NOT mean:

```text
"Only one thread can access this variable."
```

It means:

```text
"Updates to this variable are visible across threads."
```

---

## 4. Syntax

```java
volatile dataType variableName;
```

Example:

```java
private volatile boolean running = true;
```

Another example:

```java
private volatile boolean shutdownRequested = false;
```

---

## 5. Simple Example

```java
public class VolatileExample {

    static volatile boolean running = true;

    public static void main(String[] args) throws InterruptedException {

        Thread worker = new Thread(() -> {

            while (running) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }

            System.out.println("Worker Stopped");
        });

        worker.start();

        Thread.sleep(2000);

        running = false;

        worker.join();

        System.out.println("Main Completed");
    }
}
```

### Flow

```text
running = true
      ↓
Worker starts
      ↓
Worker keeps running
      ↓
Main waits 2 seconds
      ↓
running = false
      ↓
Worker sees false
      ↓
Worker stops
```

---

## 6. Real-Time Example

### Background order-processing service

Suppose an application has a worker processing orders.

```java
class OrderWorker implements Runnable {

    private volatile boolean running = true;

    @Override
    public void run() {

        while (running) {
            // Process orders
        }

        System.out.println("Order worker stopped");
    }

    public void stop() {
        running = false;
    }
}
```

Another thread can request shutdown:

```java
OrderWorker workerTask = new OrderWorker();

Thread worker = new Thread(workerTask);

worker.start();

// Later...
workerTask.stop();

worker.join();
```

Here:

```java
private volatile boolean running = true;
```

allows the worker thread to reliably observe:

```java
running = false;
```

### Real-world pattern

```text
Application
     │
     ↓
Order Worker
     │
     ├── running = true
     │
     ├── process orders
     │
     ├── process orders
     │
     ↓
Shutdown requested
     │
     ↓
running = false
     │
     ↓
Worker stops gracefully
```

This is a common type of use case for `volatile`.

---

## 7. Important Rules

### Rule 1 — volatile provides visibility

```java
volatile boolean running = true;
```

One thread changing the value becomes visible to other threads.

---

### Rule 2 — volatile does not provide atomicity

This is NOT thread-safe:

```java
volatile int count = 0;

count++;
```

Because:

```text
count++

   ↓

READ
   ↓
ADD 1
   ↓
WRITE
```

Two threads can perform these steps at the same time.

---

### Rule 3 — volatile does not provide mutual exclusion

`volatile` does not prevent multiple threads from accessing the variable simultaneously.

For mutual exclusion use:

```java
synchronized
```

or:

```java
Lock
```

---

### Rule 4 — Good for simple state flags

Good:

```java
volatile boolean running;
volatile boolean shutdownRequested;
volatile boolean serverReady;
```

These are simple state variables where threads need to observe the latest value.

---

### Rule 5 — Use AtomicInteger for atomic counters

Instead of:

```java
volatile int count = 0;

count++;
```

use:

```java
AtomicInteger count = new AtomicInteger(0);

count.incrementAndGet();
```

---

### Rule 6 — volatile does not replace synchronized

Use `volatile` when the main requirement is visibility of a simple variable.

Use `synchronized` when multiple operations must execute as one protected critical section.

---

### Rule 7 — `volatile` works with references too

Example:

```java
private volatile Config config;
```

A thread assigning a new reference makes that reference update visible to other threads.

However, `volatile` does not automatically make the object's internal mutable state thread-safe.

---

## 8. Common Mistakes

### Mistake 1 — Thinking volatile makes everything thread-safe

Wrong:

```java
volatile int count;

count++;
```

`volatile` does not make `count++` atomic.

---

### Mistake 2 — Using volatile for complex shared state

For example:

```java
volatile List<String> orders;
```

The reference may be visible, but operations on the list itself are not automatically thread-safe.

---

### Mistake 3 — Thinking volatile means locking

Wrong:

```text
volatile → locking
```

Correct:

```text
volatile → visibility
synchronized → mutual exclusion + visibility
Lock → mutual exclusion + additional locking features
AtomicInteger → atomic operations
```

---

### Mistake 4 — Forgetting the difference between visibility and atomicity

```text
Visibility
→ Can other threads see my latest value?

Atomicity
→ Can this operation happen without another thread interfering?
```

They are different problems.

---

### Mistake 5 — Assuming `volatile` makes `count++` safe

```java
volatile int count = 0;
count++;
```

Still unsafe.

Use:

```java
AtomicInteger count = new AtomicInteger(0);
count.incrementAndGet();
```

---

## 9. Interview Questions

### Q1. What is volatile in Java?

`volatile` is a keyword that guarantees visibility of changes to a variable across threads and provides ordering guarantees under the Java Memory Model.

---

### Q2. Why do we use volatile?

We use `volatile` when multiple threads share a variable and one thread's updates need to be reliably visible to other threads.

---

### Q3. Does volatile provide atomicity?

No.

`volatile` provides visibility, not atomicity for compound operations.

---

### Q4. Is this thread-safe?

```java
volatile int count = 0;

count++;
```

No.

`count++` consists of read, increment, and write operations.

---

### Q5. What should we use instead?

For a simple integer counter:

```java
AtomicInteger count = new AtomicInteger(0);

count.incrementAndGet();
```

---

### Q6. Does volatile provide mutual exclusion?

No.

`volatile` does not lock the variable or prevent multiple threads from accessing it simultaneously.

---

### Q7. What is the difference between volatile and synchronized?

```text
volatile
→ visibility + ordering

synchronized
→ mutual exclusion + visibility
```

---

### Q8. Can volatile be used with objects?

Yes.

A volatile reference can ensure visibility of reference updates.

But it does not make the object's internal mutable state automatically thread-safe.

---

### Q9. Give a real-world use case for volatile.

A worker shutdown flag:

```java
private volatile boolean running = true;
```

One thread can set:

```java
running = false;
```

and the worker thread can reliably observe the change.

---

### Q10. Is volatile enough for a counter?

No.

Use `AtomicInteger`, `LongAdder`, synchronization, or another appropriate concurrency mechanism depending on the use case.

---

## 10. Coding Practice

### Practice 1 — Worker Shutdown Flag

Create:

```java
static volatile boolean running = true;
```

Create a worker thread that runs while `running` is true.

Main thread should:

1. Start worker.
2. Wait 2 seconds.
3. Set `running = false`.
4. Join worker.
5. Print completion message.

---

### Practice 2 — Prove volatile is not atomic

Create:

```java
static volatile int count = 0;
```

Create two threads.

Each thread should execute:

```java
for (int i = 0; i < 100_000; i++) {
    count++;
}
```

Expected mathematically:

```text
200000
```

But because `count++` is not atomic, the final value can be lower.

---

### Practice 3 — Solve with AtomicInteger

Replace:

```java
volatile int count = 0;
```

with:

```java
AtomicInteger count = new AtomicInteger(0);
```

Then:

```java
count.incrementAndGet();
```

Now two threads can safely increment the same counter.

---

## 11. 10-Second Cheat Sheet

```text
volatile
    ↓
Memory visibility
    ↓
One thread's update becomes visible to other threads

Good for:
    → flags
    → status
    → shutdown signals

Does NOT provide:
    → atomicity
    → mutual exclusion

volatile int count;
count++;
    ↓
NOT thread-safe

AtomicInteger
    ↓
Atomic operations

synchronized
    ↓
Mutual exclusion + visibility
```

### Most important interview line

> `volatile` guarantees visibility but does not make compound operations atomic.

---

## 12. Final Mental Model

Think about three different problems:

```text
Problem 1:
"Did the other thread see my update?"
        ↓
     volatile


Problem 2:
"Can I safely increment this shared counter?"
        ↓
   AtomicInteger


Problem 3:
"Only one thread should execute this critical section."
        ↓
 synchronized / Lock
```

### Final comparison

```text
┌──────────────────┬─────────────────────────────┐
│ Tool             │ Main Purpose                │
├──────────────────┼─────────────────────────────┤
│ volatile         │ Visibility                  │
│ AtomicInteger    │ Atomic operations           │
│ synchronized     │ Mutual exclusion            │
│ Lock             │ Advanced mutual exclusion   │
└──────────────────┴─────────────────────────────┘
```

### One-line memory trick

```text
volatile     → SEE
Atomic       → UPDATE SAFELY
synchronized → EXCLUSIVE ACCESS
```
=======
# volatile — Interview Cheat Sheet

## 1. What is it?

`volatile` is a Java keyword used in multithreading to guarantee **visibility of changes** to a variable across threads.

When one thread updates a `volatile` variable, other threads are guaranteed to see the updated value.

```java
private volatile boolean running = true;
```

Main purpose:

```text
volatile → visibility + ordering
```

It does **not** provide mutual exclusion and does **not** make compound operations atomic.

---

## 2. Why is it needed?

Multiple threads may access the same variable.

Without proper visibility guarantees, one thread is not guaranteed to immediately observe another thread's update.

### Real-time example

Imagine a background worker in a backend application.

```text
Application
    │
    ├── Main Thread
    │      │
    │      └── running = false
    │
    └── Worker Thread
           │
           └── while(running)
```

The main thread wants to stop the worker.

Using:

```java
volatile boolean running = true;
```

ensures the worker sees the updated value.

Typical use cases:

- Application shutdown flags
- Service running/stopped flags
- Worker control flags
- Configuration/state flags
- Simple status indicators shared between threads

---

## 3. How does it work / Internal?

Each thread can work with values from its own CPU cache/registers rather than always reading directly from shared memory.

Conceptually:

```text
Without volatile

Thread 1              Thread 2
   │                     │
   │ running = false     │
   │                     │
   └──────────────X──────┤
                         │
                  visibility not
                  properly guaranteed
```

With `volatile`:

```text
Thread 1              Thread 2
   │                     │
   │ running = false     │
   │────────────────────>│
   │                     │
   │                sees false
```

`volatile` establishes visibility and ordering guarantees according to the Java Memory Model.

### Important

`volatile` does NOT mean:

```text
"Only one thread can access this variable."
```

It means:

```text
"Updates to this variable are visible across threads."
```

---

## 4. Syntax

```java
volatile dataType variableName;
```

Example:

```java
private volatile boolean running = true;
```

Another example:

```java
private volatile boolean shutdownRequested = false;
```

---

## 5. Simple Example

```java
public class VolatileExample {

    static volatile boolean running = true;

    public static void main(String[] args) throws InterruptedException {

        Thread worker = new Thread(() -> {

            while (running) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }

            System.out.println("Worker Stopped");
        });

        worker.start();

        Thread.sleep(2000);

        running = false;

        worker.join();

        System.out.println("Main Completed");
    }
}
```

### Flow

```text
running = true
      ↓
Worker starts
      ↓
Worker keeps running
      ↓
Main waits 2 seconds
      ↓
running = false
      ↓
Worker sees false
      ↓
Worker stops
```

---

## 6. Real-Time Example

### Background order-processing service

Suppose an application has a worker processing orders.

```java
class OrderWorker implements Runnable {

    private volatile boolean running = true;

    @Override
    public void run() {

        while (running) {
            // Process orders
        }

        System.out.println("Order worker stopped");
    }

    public void stop() {
        running = false;
    }
}
```

Another thread can request shutdown:

```java
OrderWorker workerTask = new OrderWorker();

Thread worker = new Thread(workerTask);

worker.start();

// Later...
workerTask.stop();

worker.join();
```

Here:

```java
private volatile boolean running = true;
```

allows the worker thread to reliably observe:

```java
running = false;
```

### Real-world pattern

```text
Application
     │
     ↓
Order Worker
     │
     ├── running = true
     │
     ├── process orders
     │
     ├── process orders
     │
     ↓
Shutdown requested
     │
     ↓
running = false
     │
     ↓
Worker stops gracefully
```

This is a common type of use case for `volatile`.

---

## 7. Important Rules

### Rule 1 — volatile provides visibility

```java
volatile boolean running = true;
```

One thread changing the value becomes visible to other threads.

---

### Rule 2 — volatile does not provide atomicity

This is NOT thread-safe:

```java
volatile int count = 0;

count++;
```

Because:

```text
count++

   ↓

READ
   ↓
ADD 1
   ↓
WRITE
```

Two threads can perform these steps at the same time.

---

### Rule 3 — volatile does not provide mutual exclusion

`volatile` does not prevent multiple threads from accessing the variable simultaneously.

For mutual exclusion use:

```java
synchronized
```

or:

```java
Lock
```

---

### Rule 4 — Good for simple state flags

Good:

```java
volatile boolean running;
volatile boolean shutdownRequested;
volatile boolean serverReady;
```

These are simple state variables where threads need to observe the latest value.

---

### Rule 5 — Use AtomicInteger for atomic counters

Instead of:

```java
volatile int count = 0;

count++;
```

use:

```java
AtomicInteger count = new AtomicInteger(0);

count.incrementAndGet();
```

---

### Rule 6 — volatile does not replace synchronized

Use `volatile` when the main requirement is visibility of a simple variable.

Use `synchronized` when multiple operations must execute as one protected critical section.

---

### Rule 7 — `volatile` works with references too

Example:

```java
private volatile Config config;
```

A thread assigning a new reference makes that reference update visible to other threads.

However, `volatile` does not automatically make the object's internal mutable state thread-safe.

---

## 8. Common Mistakes

### Mistake 1 — Thinking volatile makes everything thread-safe

Wrong:

```java
volatile int count;

count++;
```

`volatile` does not make `count++` atomic.

---

### Mistake 2 — Using volatile for complex shared state

For example:

```java
volatile List<String> orders;
```

The reference may be visible, but operations on the list itself are not automatically thread-safe.

---

### Mistake 3 — Thinking volatile means locking

Wrong:

```text
volatile → locking
```

Correct:

```text
volatile → visibility
synchronized → mutual exclusion + visibility
Lock → mutual exclusion + additional locking features
AtomicInteger → atomic operations
```

---

### Mistake 4 — Forgetting the difference between visibility and atomicity

```text
Visibility
→ Can other threads see my latest value?

Atomicity
→ Can this operation happen without another thread interfering?
```

They are different problems.

---

### Mistake 5 — Assuming `volatile` makes `count++` safe

```java
volatile int count = 0;
count++;
```

Still unsafe.

Use:

```java
AtomicInteger count = new AtomicInteger(0);
count.incrementAndGet();
```

---

## 9. Interview Questions

### Q1. What is volatile in Java?

`volatile` is a keyword that guarantees visibility of changes to a variable across threads and provides ordering guarantees under the Java Memory Model.

---

### Q2. Why do we use volatile?

We use `volatile` when multiple threads share a variable and one thread's updates need to be reliably visible to other threads.

---

### Q3. Does volatile provide atomicity?

No.

`volatile` provides visibility, not atomicity for compound operations.

---

### Q4. Is this thread-safe?

```java
volatile int count = 0;

count++;
```

No.

`count++` consists of read, increment, and write operations.

---

### Q5. What should we use instead?

For a simple integer counter:

```java
AtomicInteger count = new AtomicInteger(0);

count.incrementAndGet();
```

---

### Q6. Does volatile provide mutual exclusion?

No.

`volatile` does not lock the variable or prevent multiple threads from accessing it simultaneously.

---

### Q7. What is the difference between volatile and synchronized?

```text
volatile
→ visibility + ordering

synchronized
→ mutual exclusion + visibility
```

---

### Q8. Can volatile be used with objects?

Yes.

A volatile reference can ensure visibility of reference updates.

But it does not make the object's internal mutable state automatically thread-safe.

---

### Q9. Give a real-world use case for volatile.

A worker shutdown flag:

```java
private volatile boolean running = true;
```

One thread can set:

```java
running = false;
```

and the worker thread can reliably observe the change.

---

### Q10. Is volatile enough for a counter?

No.

Use `AtomicInteger`, `LongAdder`, synchronization, or another appropriate concurrency mechanism depending on the use case.

---

## 10. Coding Practice

### Practice 1 — Worker Shutdown Flag

Create:

```java
static volatile boolean running = true;
```

Create a worker thread that runs while `running` is true.

Main thread should:

1. Start worker.
2. Wait 2 seconds.
3. Set `running = false`.
4. Join worker.
5. Print completion message.

---

### Practice 2 — Prove volatile is not atomic

Create:

```java
static volatile int count = 0;
```

Create two threads.

Each thread should execute:

```java
for (int i = 0; i < 100_000; i++) {
    count++;
}
```

Expected mathematically:

```text
200000
```

But because `count++` is not atomic, the final value can be lower.

---

### Practice 3 — Solve with AtomicInteger

Replace:

```java
volatile int count = 0;
```

with:

```java
AtomicInteger count = new AtomicInteger(0);
```

Then:

```java
count.incrementAndGet();
```

Now two threads can safely increment the same counter.

---

## 11. 10-Second Cheat Sheet

```text
volatile
    ↓
Memory visibility
    ↓
One thread's update becomes visible to other threads

Good for:
    → flags
    → status
    → shutdown signals

Does NOT provide:
    → atomicity
    → mutual exclusion

volatile int count;
count++;
    ↓
NOT thread-safe

AtomicInteger
    ↓
Atomic operations

synchronized
    ↓
Mutual exclusion + visibility
```

### Most important interview line

> `volatile` guarantees visibility but does not make compound operations atomic.

---

## 12. Final Mental Model

Think about three different problems:

```text
Problem 1:
"Did the other thread see my update?"
        ↓
     volatile


Problem 2:
"Can I safely increment this shared counter?"
        ↓
   AtomicInteger


Problem 3:
"Only one thread should execute this critical section."
        ↓
 synchronized / Lock
```

### Final comparison

```text
┌──────────────────┬─────────────────────────────┐
│ Tool             │ Main Purpose                │
├──────────────────┼─────────────────────────────┤
│ volatile         │ Visibility                  │
│ AtomicInteger    │ Atomic operations           │
│ synchronized     │ Mutual exclusion            │
│ Lock             │ Advanced mutual exclusion   │
└──────────────────┴─────────────────────────────┘
```

### One-line memory trick

```text
volatile     → SEE
Atomic       → UPDATE SAFELY
synchronized → EXCLUSIVE ACCESS
```
<<<<<<< HEAD
=======
>>>>>>> a3e05f4