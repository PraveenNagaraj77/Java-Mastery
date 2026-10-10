# Callable & Future — Interview Cheat Sheet

## 1. What is it?

### Callable

`Callable<T>` is similar to `Runnable`, but it can:

- Return a result.
- Throw checked exceptions.
- Be submitted to an `ExecutorService`.

```java
Callable<Integer> task = () -> {
    return 500;
};
```

### Future

`Future<T>` represents the result of an asynchronous task.

It allows us to:

- Get the result.
- Check completion.
- Check cancellation.
- Cancel the task.
- Wait with a timeout.

```text
Callable<T>
    ↓
ExecutorService.submit()
    ↓
Future<T>
    ↓
future.get()
    ↓
Result
```

---

## 2. Why is it needed?

`Runnable` cannot directly return a result.

```java
Runnable task = () -> {
    // return value not possible
};
```

`Callable` solves this:

```java
Callable<Integer> task = () -> {
    return 500;
};
```

`Future` allows the calling thread to retrieve that result later.

### Real-world need

In an e-commerce application:

```text
Order Service
     ↓
Submit payment calculation
     ↓
Callable
     ↓
Future
     ↓
Payment result
```

The main thread does not need to calculate the result itself.

---

## 3. How does it work / Internal?

### Execution flow

```text
Callable<T>
     ↓
executorService.submit(callable)
     ↓
Task enters Executor Thread Pool
     ↓
Worker Thread executes call()
     ↓
Result is stored
     ↓
Future holds the result
     ↓
future.get()
     ↓
Caller receives result
```

Example:

```java
ExecutorService executorService =
        Executors.newFixedThreadPool(2);

Callable<Integer> task = () -> {
    return 500;
};

Future<Integer> future =
        executorService.submit(task);

Integer result = future.get();
```

### Important

`submit()` schedules the task.

`get()` retrieves the result and waits if necessary.

```text
submit() → START / SCHEDULE TASK

get()    → WAIT + GET RESULT
```

---

## 4. Syntax

### Callable

```java
Callable<T> task = () -> {
    return value;
};
```

### Submit Callable

```java
Future<T> future =
        executorService.submit(task);
```

### Get result

```java
T result = future.get();
```

### Get with timeout

```java
T result =
    future.get(2, TimeUnit.SECONDS);
```

### Check state

```java
future.isDone();

future.isCancelled();
```

### Cancel

```java
future.cancel(true);
```

---

## 5. Simple Example

```java
import java.util.concurrent.Callable;

public class CallableExample {

    public static void main(String[] args) throws Exception {

        Callable<Integer> task = () -> {
            int price = 400;
            int deliveryFee = 50;

            return price + deliveryFee;
        };

        Integer result = task.call();

        System.out.println("Order Total: " + result);
    }
}
```

Output:

```text
Order Total: 450
```

### Important

Calling:

```java
task.call();
```

directly is synchronous.

For asynchronous execution, use:

```java
ExecutorService
```

---

## 6. Real-Time Example

### E-commerce Order Processing

```java
import java.util.concurrent.*;

public class OrderProcessing {

    public static void main(String[] args)
            throws ExecutionException, InterruptedException {

        ExecutorService executorService =
                Executors.newFixedThreadPool(2);

        Callable<Integer> order1 = () -> {
            int price = 500;
            int deliveryFee = 50;

            return price + deliveryFee;
        };

        Callable<Integer> order2 = () -> {
            int price = 800;
            int deliveryFee = 100;

            return price + deliveryFee;
        };

        Future<Integer> order1Future =
                executorService.submit(order1);

        Future<Integer> order2Future =
                executorService.submit(order2);

        int order1Total = order1Future.get();
        int order2Total = order2Future.get();

        System.out.println("Order 1: " + order1Total);
        System.out.println("Order 2: " + order2Total);

        executorService.shutdown();
    }
}
```

Possible output:

```text
Order 1: 550
Order 2: 900
```

### Important execution idea

Both tasks are submitted before calling `get()`:

```java
submit(order1);
submit(order2);

order1Future.get();
order2Future.get();
```

Therefore, both tasks can execute concurrently using the thread pool.

---

## 7. Important Rules

### Rule 1 — Runnable vs Callable

```text
Runnable
→ run()
→ void
→ cannot return result
→ cannot declare checked exceptions

Callable<T>
→ call()
→ returns T
→ can throw checked exceptions
```

---

### Rule 2 — Callable usually works with ExecutorService

```java
Future<Integer> future =
        executorService.submit(callable);
```

---

### Rule 3 — Future.get() can block

If the task is still running:

```java
future.get();
```

waits until the task completes.

```text
Task running
     ↓
future.get()
     ↓
WAIT
     ↓
Task completes
     ↓
Result returned
```

---

### Rule 4 — get(timeout) does not cancel

```java
future.get(2, TimeUnit.SECONDS);
```

means:

> Wait for at most 2 seconds for the result.

If the task is still running:

```text
TimeoutException
```

The underlying task may continue running.

```text
Timeout
  ↓
Stop waiting
  ≠
Cancel task
```

---

### Rule 5 — isDone()

```java
future.isDone();
```

returns `true` when the task has reached a terminal state:

- Successfully completed
- Failed
- Cancelled

---

### Rule 6 — isCancelled()

```java
future.isCancelled();
```

returns `true` only when the Future was successfully cancelled.

Example:

```text
Completed normally
isDone()       → true
isCancelled()  → false

Cancelled
isDone()       → true
isCancelled()  → true
```

---

### Rule 7 — cancel(true)

```java
future.cancel(true);
```

requests cancellation and may interrupt a running task.

It does NOT forcibly kill a thread.

The task should cooperate with interruption.

---

### Rule 8 — cancel(false)

```java
future.cancel(false);
```

requests cancellation without interrupting a running task.

It is especially useful when a task is still waiting in the executor queue.

---

### Rule 9 — ExecutionException

If the Callable throws an exception:

```java
Callable<Integer> task = () -> {
    throw new IllegalArgumentException("Invalid amount");
};
```

Then:

```java
future.get();
```

throws:

```text
ExecutionException
```

The original exception can be accessed using:

```java
e.getCause();
```

Flow:

```text
Callable
   ↓
IllegalArgumentException
   ↓
Future
   ↓
future.get()
   ↓
ExecutionException
   ↓
getCause()
   ↓
Original Exception
```

---

### Rule 10 — InterruptedException

`future.get()` can throw `InterruptedException` if the thread waiting for the result is interrupted.

Good practice:

```java
catch (InterruptedException e) {
    Thread.currentThread().interrupt();
}
```

---

## 8. Common Mistakes

### Mistake 1 — Calling call() directly when asynchronous execution is expected

```java
task.call();
```

This executes synchronously.

Use:

```java
executorService.submit(task);
```

---

### Mistake 2 — Calling get() immediately after every submit

```java
Future<Integer> f1 = executor.submit(task1);
int r1 = f1.get();

Future<Integer> f2 = executor.submit(task2);
int r2 = f2.get();
```

This can unnecessarily make the caller wait for each task before submitting the next one.

Prefer:

```java
Future<Integer> f1 = executor.submit(task1);
Future<Integer> f2 = executor.submit(task2);

int r1 = f1.get();
int r2 = f2.get();
```

---

### Mistake 3 — Thinking timeout cancels the task

```java
future.get(2, TimeUnit.SECONDS);
```

Timeout only stops the caller from waiting.

It does not automatically cancel the task.

---

### Mistake 4 — Forgetting shutdown

```java
executorService.shutdown();
```

Always properly shut down an executor when it is no longer needed.

---

### Mistake 5 — Using Future<?> when a specific result exists

Avoid:

```java
Future<?> future = executor.submit(task);
```

Prefer:

```java
Future<Integer> future = executor.submit(task);
```

This preserves type safety.

---

### Mistake 6 — Assuming cancel(true) forcibly kills the thread

Incorrect:

```text
cancel(true) = kill thread
```

Correct:

```text
cancel(true)
→ request cancellation
→ may interrupt running task
→ task must cooperate
```

---

## 9. Interview Questions

### Q1. What is Callable?

`Callable<T>` represents a task that can return a result and throw checked exceptions.

---

### Q2. Runnable vs Callable?

| Runnable | Callable |
|---|---|
| `run()` | `call()` |
| Returns `void` | Returns `T` |
| Cannot return result | Can return result |
| Cannot declare checked exceptions | Can throw checked exceptions |
| Used for tasks without result | Used for tasks producing result |

---

### Q3. What is Future?

`Future<T>` represents the result of an asynchronous computation and provides methods to retrieve, monitor, or cancel that computation.

---

### Q4. What happens when `future.get()` is called?

If the task is incomplete, the calling thread waits until the task finishes. Then it returns the result or throws an exception.

---

### Q5. What is the difference between `get()` and `get(timeout)`?

```text
get()
→ waits indefinitely

get(timeout)
→ waits only for specified duration
→ throws TimeoutException if result isn't ready
```

---

### Q6. Does timeout cancel the task?

No.

```java
future.get(2, TimeUnit.SECONDS);
```

only limits how long the caller waits.

---

### Q7. What does `cancel(true)` do?

It requests cancellation and may interrupt the running task.

It does not forcibly terminate the thread.

---

### Q8. What is `ExecutionException`?

`ExecutionException` is thrown by `Future.get()` when the asynchronous task itself fails.

The original exception can be obtained using:

```java
e.getCause();
```

---

### Q9. What does `isDone()` mean?

It returns `true` when the task has completed, failed, or been cancelled.

---

### Q10. Can `isDone()` and `isCancelled()` both be true?

Yes.

For a cancelled task:

```text
isDone()      → true
isCancelled() → true
```

---

### Q11. What is the difference between `cancel(false)` and `cancel(true)`?

```text
cancel(false)
→ do not interrupt running task

cancel(true)
→ may interrupt running task
```

---

### Q12. Is Future asynchronous?

`Future` itself does not execute a task. `ExecutorService` executes the task and returns a `Future` representing its result.

---

## 10. Coding Practice

### Practice 1 — Basic Callable

Create:

```text
CallableBasicPractice.java
```

Calculate:

```text
Product Price = 500
Delivery Fee = 50
Total = 550
```

Return the total using `Callable<Integer>`.

---

### Practice 2 — Multiple Callable Tasks

Create:

```text
CallableFuturePractice.java
```

Requirements:

- Create a fixed thread pool with 2 threads.
- Create two Callable order tasks.
- Submit both.
- Store results using `Future<Integer>`.
- Retrieve results using `get()`.
- Shutdown executor.

Expected concept:

```text
Callable
   ↓
ExecutorService
   ↓
Future<Integer>
   ↓
get()
```

---

### Practice 3 — Exception Handling

Create:

```text
CallableExceptionPractice.java
```

Create:

```java
Callable<Integer> failedOrder = () -> {
    throw new IllegalArgumentException("Invalid Order Amount");
};
```

Submit it and handle:

```java
ExecutionException
```

Then print:

```text
Order processing failed
Cause: Invalid Order Amount
```

---

### Practice 4 — Future Timeout

Create:

```text
FutureTimeoutPractice.java
```

Requirements:

- Callable sleeps for 5 seconds.
- Caller waits only 2 seconds.
- Use:

```java
future.get(2, TimeUnit.SECONDS);
```

Handle:

```java
TimeoutException
```

Important observation:

```text
Timeout
→ caller stops waiting
→ task may continue
```

---

### Practice 5 — Future Cancellation

Create:

```text
FutureCancellationPractice.java
```

Use:

```java
ExecutorService executorService =
        Executors.newFixedThreadPool(1);
```

Task 1:

```text
Long task started
sleep 5 seconds
Long task completed
```

Task 2:

```text
Second task started
return 1000
```

Submit both.

Because there is only one worker:

```text
Task 1 → RUNNING
Task 2 → QUEUED
```

Cancel Task 2:

```java
boolean cancelled = secondTask.cancel(false);
```

Print:

```text
Cancelled: true
Is Cancelled: true
Is Done: true
```

`Second task started` should NOT appear.

---

## 11. 10-Second Cheat Sheet

```text
Callable<T>
→ task that returns T
→ can throw checked exceptions

Future<T>
→ represents asynchronous result

submit()
→ schedules task

get()
→ waits + gets result

get(timeout)
→ waits maximum specified time

isDone()
→ completed / failed / cancelled

isCancelled()
→ cancelled successfully

cancel(false)
→ cancel without interrupting running task

cancel(true)
→ request cancellation + may interrupt

ExecutionException
→ async task failed

InterruptedException
→ waiting thread was interrupted

TimeoutException
→ result wasn't ready within timeout
```

### Core comparison

```text
Runnable
→ run()
→ void

Callable<T>
→ call()
→ T

Future<T>
→ result of Callable
```

---

## 12. Final Mental Model

Think of an online order system:

```text
              ExecutorService
                    │
          ┌─────────┴─────────┐
          ↓                   ↓
     Callable<Order>     Callable<Order>
          │                   │
          ↓                   ↓
       Future<Order>       Future<Order>
          │                   │
          └─────────┬─────────┘
                    ↓
                  get()
                    ↓
                Order Result
```

The most important distinction:

```text
Callable
→ WHAT TASK SHOULD RETURN A RESULT?

ExecutorService
→ WHO EXECUTES THE TASK?

Future
→ WHERE DO I TRACK / RETRIEVE THE RESULT?

get()
→ WAIT + RETRIEVE RESULT

Timeout
→ STOP WAITING

Cancel
→ REQUEST TASK CANCELLATION

Interrupt
→ SIGNAL A THREAD TO STOP / COOPERATE
```

### Interview-ready mental model

> `Callable` represents a task that returns a value and can throw checked exceptions. `ExecutorService` executes the Callable and returns a `Future`, which represents the pending result. `Future.get()` waits for and retrieves the result, while methods such as `isDone()`, `isCancelled()`, `cancel()`, and timed `get()` allow us to manage the task lifecycle.