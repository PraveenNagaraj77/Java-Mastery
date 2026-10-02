# Thread Control — Interview Cheat Sheet

## 1. What is it?

Thread control refers to mechanisms used to control or coordinate thread execution.

Important methods covered:

```text
sleep()
join()
yield()
interrupt()
```

They solve different problems:

```text
sleep()    → Wait for time
join()     → Wait for another thread
yield()    → Give scheduler a hint
interrupt()→ Request interruption/cancellation
```

---

## 2. Why is it needed?

Threads often need controlled execution.

Examples:

- Delay execution
- Wait for another task to finish
- Coordinate dependent operations
- Give other threads an opportunity to execute
- Gracefully stop/cancel long-running work

Without proper thread control, applications can suffer from:

- Incorrect execution order
- Unnecessary waiting
- Poor responsiveness
- Difficult cancellation
- Race conditions

---

## 3. How does it work / Internal?

Java provides methods on `Thread` that affect the current or target thread.

### `sleep()`

Pauses the **currently executing thread** for a specified duration.

```java
Thread.sleep(2000);
```

The thread enters:

```text
TIMED_WAITING
```

---

### `join()`

Makes the **currently executing thread wait for another thread to finish**.

```java
worker.join();
```

If main calls:

```java
worker.join();
```

then:

```text
Main
 ↓
waits
 ↓
Worker finishes
 ↓
Main continues
```

---

### `yield()`

Gives the scheduler a hint that the current thread is willing to allow another runnable thread to execute.

```java
Thread.yield();
```

It is only a hint.

The scheduler may:

- Run another thread
- Continue running the same thread
- Switch between threads

No execution order is guaranteed.

---

### `interrupt()`

Sends an interruption request to another thread.

```java
worker.interrupt();
```

It does **not forcibly kill the thread**.

If the target thread is blocked in an interruptible operation such as `sleep()`, it can receive:

```text
InterruptedException
```

---

## 4. Syntax

### sleep()

```java
Thread.sleep(2000);
```

Requires handling:

```java
try {
    Thread.sleep(2000);
} catch (InterruptedException e) {
    Thread.currentThread().interrupt();
}
```

---

### join()

```java
worker.join();
```

Or:

```java
worker.join(2000);
```

The second version waits for at most the specified time.

---

### yield()

```java
Thread.yield();
```

---

### interrupt()

```java
worker.interrupt();
```

Check interrupt status:

```java
worker.isInterrupted();
```

Check and clear current thread's interrupt status:

```java
Thread.interrupted();
```

---

## 5. Simple Example

### sleep()

```java
public class SleepExample {

    public static void main(String[] args)
            throws InterruptedException {

        System.out.println("Started");

        Thread.sleep(2000);

        System.out.println("Completed");
    }
}
```

The current thread waits approximately 2 seconds.

---

### join()

```java
public class JoinExample {

    public static void main(String[] args)
            throws InterruptedException {

        Thread worker = new Thread(() -> {
            System.out.println("Worker started");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println("Worker completed");
        });

        worker.start();

        worker.join();

        System.out.println("Main completed");
    }
}
```

Logical output:

```text
Worker started
~2 seconds
Worker completed
Main completed
```

---

### yield()

```java
Thread worker = new Thread(() -> {

    for (int i = 1; i <= 5; i++) {

        System.out.println("Worker: " + i);

        Thread.yield();
    }
});

worker.start();
```

`yield()` does not guarantee that another thread will run.

---

### interrupt()

```java
Thread worker = new Thread(() -> {

    try {
        Thread.sleep(10000);

        System.out.println("Task completed");

    } catch (InterruptedException e) {

        System.out.println("Task interrupted");

        Thread.currentThread().interrupt();
    }
});

worker.start();

Thread.sleep(2000);

worker.interrupt();
```

The worker can be interrupted before its 10-second sleep completes.

---

## 6. Real-Time Example

Consider a background order-processing task.

```text
Order Processing
      |
      ↓
Long-running operation
      |
      ↓
Application shutdown
      |
      ↓
interrupt()
      |
      ↓
Worker handles cancellation
      |
      ↓
Cleanup
      |
      ↓
Exit
```

### Dependency example

Suppose:

```text
Payment
   ↓
Order Confirmation
```

The confirmation should only happen after payment completes.

```java
paymentThread.start();

paymentThread.join();

System.out.println("Send order confirmation");
```

Here `join()` ensures the current thread waits for payment completion.

---

## 7. Important Rules

## `sleep()`

### Rule 1

`sleep()` affects the **currently executing thread**.

```java
Thread.sleep(2000);
```

It does not mean:

```java
worker.sleep(2000);
```

Even though Java allows calling a static method through an instance reference, doing so is misleading.

Always use:

```java
Thread.sleep(...)
```

---

### Rule 2

`sleep()` does not release a monitor lock.

This is important when learning synchronization.

```text
sleep()
→ Thread waits
→ Lock is NOT automatically released
```

---

### Rule 3

After sleeping, the thread becomes eligible to run.

It is not guaranteed to execute immediately.

---

# `join()`

### Rule 1

`join()` makes the **caller wait**.

```java
worker.join();
```

means:

```text
Current thread
     ↓
waits for worker
```

It does not make the worker wait.

---

### Rule 2

Timed join:

```java
worker.join(2000);
```

means the current thread waits for at most approximately 2 seconds.

The worker may finish earlier.

---

# `yield()`

### Rule 1

`yield()` is only a scheduler hint.

It does not guarantee:

- Context switching
- Another thread running
- Current thread stopping
- Specific execution order

### Rule 2

No duration is accepted.

Correct:

```java
Thread.yield();
```

Incorrect:

```java
Thread.yield(1000);
```

---

# `interrupt()`

### Rule 1

`interrupt()` does not kill a thread.

It sends a cancellation/interruption request.

---

### Rule 2

If a thread is sleeping:

```java
Thread.sleep(10000);
```

and another thread calls:

```java
worker.interrupt();
```

the sleeping thread can receive:

```java
InterruptedException
```

---

### Rule 3

CPU-bound code must cooperate.

For example:

```java
while (!Thread.currentThread().isInterrupted()) {

    // perform work
}
```

An interrupt request does not automatically stop this loop.

---

### Rule 4

When catching `InterruptedException`, restore the interrupt status when appropriate:

```java
catch (InterruptedException e) {
    Thread.currentThread().interrupt();
}
```

---

### `isInterrupted()` vs `Thread.interrupted()`

```text
worker.isInterrupted()
→ Checks status
→ Does NOT clear status

Thread.interrupted()
→ Checks current thread status
→ Clears status
```

---

## 8. Common Mistakes

### Mistake 1: Thinking sleep pauses another thread

Wrong mental model:

```java
worker.sleep(2000);
```

Correct:

```java
Thread.sleep(2000);
```

`sleep()` affects the currently executing thread.

---

### Mistake 2: Thinking join() stops the target thread

Wrong:

```text
worker.join()
→ worker waits
```

Correct:

```text
caller waits for worker
```

---

### Mistake 3: Thinking yield() guarantees switching

Wrong:

> `yield()` forces the scheduler to switch threads.

Correct:

> `yield()` gives the scheduler a hint that the current thread is willing to yield execution.

---

### Mistake 4: Thinking interrupt() kills a thread

Wrong:

```text
interrupt() → thread dies
```

Correct:

```text
interrupt()
→ cancellation/interruption request
→ target thread must respond appropriately
```

---

### Mistake 5: Swallowing InterruptedException

Avoid:

```java
catch (InterruptedException e) {
}
```

This silently loses the interruption signal.

Prefer appropriate handling, often:

```java
catch (InterruptedException e) {
    Thread.currentThread().interrupt();
}
```

---

### Mistake 6: Forgetting InterruptedException

These methods can throw `InterruptedException`:

```java
Thread.sleep(...);
thread.join();
```

Handle it with:

```java
try/catch
```

or propagate it:

```java
throws InterruptedException
```

---

## 9. Interview Questions

### Q1. What does `sleep()` do?

It pauses the currently executing thread for a specified amount of time.

---

### Q2. Does `sleep()` release a lock?

No. `sleep()` does not release the monitor lock held by the thread.

---

### Q3. What state does a sleeping thread enter?

`TIMED_WAITING`.

---

### Q4. What does `join()` do?

It causes the current thread to wait until the target thread terminates, subject to any timeout.

---

### Q5. Does `join()` stop the target thread?

No. The target continues executing while the caller waits.

---

### Q6. What is `yield()`?

It is a hint to the scheduler that the current thread is willing to yield execution to another runnable thread.

---

### Q7. Is `yield()` guaranteed to switch threads?

No.

---

### Q8. What does `interrupt()` do?

It sends an interruption request to a thread.

---

### Q9. Does `interrupt()` kill a thread?

No.

---

### Q10. What happens when a sleeping thread is interrupted?

`InterruptedException` is thrown and the sleep is terminated.

---

### Q11. Difference between `isInterrupted()` and `Thread.interrupted()`?

```text
isInterrupted()
→ checks status without clearing it

Thread.interrupted()
→ checks current thread's status and clears it
```

---

### Q12. Why restore interrupt status?

Because catching `InterruptedException` clears the interrupt status. Restoring it preserves the cancellation signal for higher-level code.

---

## 10. Coding Practice

### Practice 1 — sleep()

Create a worker that:

```text
Print "Task Started"
Sleep 2 seconds
Print "Task Completed"
```

---

### Practice 2 — join()

Create a worker that:

```text
Print "Task Started"
Sleep 3 seconds
Print "Task Completed"
```

Main should call:

```java
worker.join();
```

Then print:

```text
Main Continues after worker completion
```

---

### Practice 3 — yield()

Create two threads:

```text
Worker 1 → print 1 to 10
Worker 2 → print 1 to 10
```

Call:

```java
Thread.yield();
```

after each print.

Observe that execution order is not guaranteed.

---

### Practice 4 — interrupt()

Create a worker that sleeps for 10 seconds.

Main should:

```text
Start worker
↓
Wait 2 seconds
↓
Interrupt worker
```

Worker should catch `InterruptedException` and restore the interrupt status.

---

## 11. 10-Second Cheat Sheet

```text
sleep()
→ Current thread waits for time
→ TIMED_WAITING
→ Does NOT release monitor lock

join()
→ Current thread waits for another thread
→ Target thread continues running

yield()
→ Scheduler hint
→ No guarantee of context switch

interrupt()
→ Cancellation/interruption request
→ Does NOT kill thread

isInterrupted()
→ Check status
→ Does NOT clear

Thread.interrupted()
→ Check current thread
→ Clears status
```

### Fast comparison

```text
sleep()    → WAIT FOR TIME
join()     → WAIT FOR THREAD
yield()    → GIVE OTHERS A CHANCE
interrupt()→ REQUEST INTERRUPTION
```

---

## 12. Final Mental Model

```text
                THREAD CONTROL
                     |
       ┌─────────────┼─────────────┐
       ↓             ↓             ↓
    sleep()        join()        yield()
       ↓             ↓             ↓
 Wait for time   Wait for       Give scheduler
                 another        a hint
                 thread
                     |
                     |
                 interrupt()
                     ↓
             Request cancellation
                     ↓
          Thread responds cooperatively
```

### Core interview model

```text
sleep()
→ Time-based waiting

join()
→ Dependency-based waiting

yield()
→ Scheduler hint

interrupt()
→ Cooperative cancellation
```