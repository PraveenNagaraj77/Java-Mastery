# CompletableFuture — Interview Cheat Sheet

## 1. What Is CompletableFuture?

`CompletableFuture<T>` is a Java class introduced in Java 8 that represents the result of an asynchronous computation that may complete in the future.

It implements:
- `Future<T>` — represents a pending result.
- `CompletionStage<T>` — supports chaining and combining asynchronous operations.

It allows us to execute tasks asynchronously, transform results, combine independent tasks, and handle failures without manually coordinating threads for every step.

### Example

```java
CompletableFuture<String> future =
        CompletableFuture.supplyAsync(() -> "Order processed");

System.out.println(future.join());
```

Output:

```text
Order processed
```

### Why Is It Needed?

Imagine an e-commerce backend that needs to:
- Verify payment.
- Check inventory.
- Calculate delivery details.
- Combine the results.
- Handle unavailable services.

Running independent tasks asynchronously can improve responsiveness and throughput when used appropriately.

---

## 2. Why Is CompletableFuture Needed?

Traditional sequential execution:

```java
String payment = checkPayment();
String inventory = checkInventory();
String delivery = calculateDelivery();
```

Each operation starts after the previous operation returns.

With `CompletableFuture`, independent tasks can be started without waiting for each one before starting the next.

```java
CompletableFuture<String> payment =
        CompletableFuture.supplyAsync(() -> checkPayment());

CompletableFuture<String> inventory =
        CompletableFuture.supplyAsync(() -> checkInventory());

CompletableFuture<String> delivery =
        CompletableFuture.supplyAsync(() -> calculateDelivery());

CompletableFuture.allOf(payment, inventory, delivery).join();
```

The tasks can overlap, depending on the executor and available threads.

**Benefits**
- Supports asynchronous execution.
- Provides fluent task chaining.
- Combines results from independent tasks.
- Supports exception handling.
- Supports timeouts and cancellation.
- Reduces the need for manual thread coordination.

Important: Asynchronous execution does not automatically make an application faster. Thread scheduling, blocking calls, available resources, and task dependencies all matter.

---

## 3. How Does CompletableFuture Work Internally?

A `CompletableFuture` tracks a computation's completion and its result or exception.

Conceptually:

```text
Create Future
     |
     v
Start Asynchronous Task
     |
     v
Task Completes
     |
     v
Store Result or Exception
     |
     v
Trigger Dependent Stages
     |
     v
Return Final Result
```

### Important Internal Concepts

**Completion state**

A future can complete:
- Normally with a result.
- Exceptionally with an exception.
- Through cancellation.

**Dependent stages**

Methods such as `thenApply()` create dependent futures. They do not modify the original future's result.

**Execution**

- Non-async continuations may execute on the thread completing the previous stage or immediately on the caller's thread if the previous stage is already complete.
- Async methods normally use the common `ForkJoinPool`, unless an executor is supplied.
- Completion and scheduling behavior depends on the method and executor.

**Exception propagation**

An exception can propagate through dependent stages until a recovery or handling stage processes it.

---

## 4. Syntax and Important Methods

### Create an asynchronous task that returns a result

```java
CompletableFuture<String> future =
        CompletableFuture.supplyAsync(() -> "Payment verified");
```

### Create an asynchronous task without a result

```java
CompletableFuture<Void> future =
        CompletableFuture.runAsync(() -> {
            System.out.println("Generating invoice");
        });
```

### Transform a result — `thenApply()`

```java
CompletableFuture<String> future =
        CompletableFuture.supplyAsync(() -> "ORD-101")
                .thenApply(id -> "Confirmed: " + id);
```

Use when a result is transformed into another value.

### Consume a result — `thenAccept()`

```java
CompletableFuture<Void> future =
        CompletableFuture.supplyAsync(() -> "ORD-101")
                .thenAccept(id -> System.out.println(id));
```

Use when you need to consume the result without producing another meaningful result.

### Chain another asynchronous operation — `thenCompose()`

```java
CompletableFuture<String> future =
        CompletableFuture.supplyAsync(() -> "ORD-101")
                .thenCompose(id ->
                        CompletableFuture.supplyAsync(
                                () -> "Delivery created for " + id
                        ));
```

Use when the next operation itself returns a `CompletableFuture`.

It avoids nested futures such as:

```java
CompletableFuture<CompletableFuture<String>>
```

### Combine two independent results — `thenCombine()`

```java
CompletableFuture<String> payment =
        CompletableFuture.supplyAsync(() -> "Payment verified");

CompletableFuture<String> inventory =
        CompletableFuture.supplyAsync(() -> "Inventory reserved");

CompletableFuture<String> result =
        payment.thenCombine(inventory,
                (p, i) -> p + " | " + i);

System.out.println(result.join());
```

### Wait for multiple futures — `allOf()`

```java
CompletableFuture<String> payment =
        CompletableFuture.supplyAsync(() -> "Payment verified");

CompletableFuture<String> inventory =
        CompletableFuture.supplyAsync(() -> "Inventory reserved");

CompletableFuture<Void> all =
        CompletableFuture.allOf(payment, inventory);

all.join();

System.out.println(payment.join());
System.out.println(inventory.join());
```

`allOf()` returns `CompletableFuture<Void>`. Retrieve individual results from their original futures.

If one or more futures fail, `allOf()` completes exceptionally after all supplied futures have completed.

### Use the first completed future — `anyOf()`

```java
CompletableFuture<String> server1 =
        CompletableFuture.supplyAsync(() -> "Server 1 response");

CompletableFuture<String> server2 =
        CompletableFuture.supplyAsync(() -> "Server 2 response");

CompletableFuture<Object> fastest =
        CompletableFuture.anyOf(server1, server2);

System.out.println(fastest.join());
```

`anyOf()` completes with the first supplied future to complete, whether normally or exceptionally. It does not cancel the other tasks.

### Retrieve a result — `join()` and `get()`

```java
String result = future.join();
```

```java
String result = future.get();
```

| Method | Behavior |
|---|---|
| `join()` | Waits and throws unchecked `CompletionException` for exceptional completion. |
| `get()` | Waits and throws checked `ExecutionException` or `InterruptedException`. |
| `get(timeout, unit)` | Waits up to a specified time and may throw `TimeoutException`. |

`get(timeout, unit)` timing out does not automatically complete the future with a timeout result.

### Handle exceptions — `exceptionally()`

```java
CompletableFuture<String> future =
        CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("Payment failed");
        }).exceptionally(ex -> "Payment status unavailable");

System.out.println(future.join());
```

Output:

```text
Payment status unavailable
```

`exceptionally()` recovers from an exception by returning a replacement result.

### Handle success or failure — `handle()`

```java
CompletableFuture<String> future =
        CompletableFuture.supplyAsync(() -> "Payment verified");

CompletableFuture<String> handled =
        future.handle((result, exception) -> {
            if (exception != null) {
                return "Payment unavailable";
            }

            return result;
        });
```

`handle()` runs for either normal or exceptional completion and produces a new result.

### Set a timeout — `orTimeout()`

Available since Java 9.

```java
CompletableFuture<String> future =
        CompletableFuture.supplyAsync(() -> "Payment verified")
                .orTimeout(2, TimeUnit.SECONDS);
```

If the timeout expires first, the future completes exceptionally.

### Set a fallback on timeout — `completeOnTimeout()`

Available since Java 9.

```java
CompletableFuture<String> future =
        CompletableFuture.supplyAsync(() -> "Payment verified")
                .completeOnTimeout(
                        "Payment status pending",
                        2,
                        TimeUnit.SECONDS
                );
```

If the timeout expires first, the future completes with the fallback value.

Both timeout methods affect the future on which they are called. A timeout does not guarantee that the underlying task stops running.

### Check completion and cancellation

```java
future.isDone();
future.isCancelled();
future.cancel(true);
```

- `isDone()` — true after normal completion, exceptional completion, or cancellation.
- `isCancelled()` — true if cancelled.
- `cancel(true)` — attempts cancellation of the future; it does not guarantee interruption of the underlying computation.

---

## 5. Simple Example

```java
package com.javamastery.multithreading.completablefuture;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {

    public static void main(String[] args) {

        CompletableFuture<String> future =
                CompletableFuture.supplyAsync(() -> "ORD-101");

        CompletableFuture<String> confirmed =
                future.thenApply(id -> "Order Confirmed: " + id);

        System.out.println(confirmed.join());
    }
}
```

Output:

```text
Order Confirmed: ORD-101
```

### Explanation

1. `supplyAsync()` starts a task that returns an order ID.
2. `thenApply()` transforms the ID into a confirmation message.
3. `join()` waits for the dependent future and retrieves its result.

---

## 6. Real-Time Example — Order Processing

```java
package com.javamastery.multithreading.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class OrderProcessingExample {

    public static void main(String[] args) {

        CompletableFuture<String> payment =
                CompletableFuture.supplyAsync(
                        () -> verifyPayment("ORD-101")
                );

        CompletableFuture<String> inventory =
                CompletableFuture.supplyAsync(
                        () -> reserveInventory("ORD-101")
                );

        CompletableFuture<String> delivery =
                CompletableFuture.supplyAsync(
                        () -> scheduleDelivery("ORD-101")
                );

        CompletableFuture<Void> allTasks =
                CompletableFuture.allOf(payment, inventory, delivery);

        try {
            allTasks.join();

            System.out.println(payment.join());
            System.out.println(inventory.join());
            System.out.println(delivery.join());

        } catch (RuntimeException ex) {
            System.out.println("Order processing failed");
        }

        CompletableFuture<String> externalService =
                CompletableFuture.supplyAsync(
                        () -> callExternalService()
                ).completeOnTimeout(
                        "External service temporarily unavailable",
                        2,
                        TimeUnit.SECONDS
                );

        System.out.println(externalService.join());
    }

    private static String verifyPayment(String orderId) {
        return orderId + ": Payment verified";
    }

    private static String reserveInventory(String orderId) {
        return orderId + ": Inventory reserved";
    }

    private static String scheduleDelivery(String orderId) {
        return orderId + ": Delivery scheduled";
    }

    private static String callExternalService() {
        return "External service responded";
    }
}
```

### Real-world considerations

- These example tasks return simulated results; production services need real validation and error handling.
- Payment, inventory, and delivery operations are not automatically transactional.
- If one operation fails after another has succeeded, a real application may need retries, compensation, or a workflow/state machine.
- Never treat a timeout as proof that a payment failed. The payment might have succeeded while the response was delayed; reconcile with the payment provider before retrying a potentially duplicate transaction.

---

## 7. Important Rules

1. `CompletableFuture` is both a `Future` and a `CompletionStage`.
2. `supplyAsync()` returns a result; `runAsync()` does not.
3. `thenApply()` transforms a value.
4. `thenAccept()` consumes a value.
5. `thenCompose()` chains asynchronous operations and flattens the resulting future.
6. `thenCombine()` combines two successful independent results.
7. `allOf()` waits for all supplied futures to complete.
8. `anyOf()` completes when the first supplied future completes.
9. `join()` throws unchecked exceptions; `get()` uses checked exceptions.
10. `exceptionally()` recovers from failure; `handle()` processes success or failure.
11. Async methods without an explicit executor generally use the common `ForkJoinPool`.
12. `thenApplyAsync()` schedules the continuation asynchronously; `thenApply()` does not guarantee a separate thread.
13. `orTimeout()` and `completeOnTimeout()` were introduced in Java 9.
14. A timeout does not guarantee task cancellation.
15. A completed future's result cannot simply be overwritten with another normal result.
16. Blocking with `join()` or `get()` inside asynchronous workflows can reduce throughput or cause thread starvation if used carelessly.
17. For blocking I/O, choose an executor designed for that workload rather than blindly using the common pool.

---

## 8. Common Mistakes

### Mistake 1: Using `thenApply()` for a method that returns a future

Incorrect when the intention is to flatten the result:

```java
future.thenApply(id -> fetchDetailsAsync(id));
```

This produces a nested future.

Prefer:

```java
future.thenCompose(id -> fetchDetailsAsync(id));
```

### Mistake 2: Assuming `allOf()` returns every result

Incorrect:

```java
CompletableFuture<String> result =
        CompletableFuture.allOf(future1, future2);
```

`allOf()` returns `CompletableFuture<Void>`, not a result-bearing future for all values.

### Mistake 3: Assuming `anyOf()` cancels the slower tasks

It does not automatically cancel them. Other tasks may continue running.

### Mistake 4: Ignoring exceptions

Calling `join()` on an exceptionally completed future throws a `CompletionException`.

Use `exceptionally()`, `handle()`, or a suitable try/catch strategy.

### Mistake 5: Assuming `cancel(true)` stops all work

Cancellation of a future is not a guarantee that its underlying task has stopped. Design cooperative cancellation when required.

### Mistake 6: Using the common pool for every workload

Blocking tasks can occupy common-pool workers and harm unrelated work. Consider a dedicated executor for blocking operations.

### Mistake 7: Treating timeout as business failure

A timeout means the response was not obtained within the allowed period. It does not prove that the remote operation did not succeed.

---

## 9. Interview Questions

### Q1. What is CompletableFuture?

A Java class for representing and composing asynchronous computations. It implements `Future` and `CompletionStage`.

### Q2. What is the difference between Future and CompletableFuture?

`Future` supports retrieving results and basic cancellation. `CompletableFuture` adds completion-stage chaining, combining, explicit completion, and rich exception handling.

### Q3. Difference between `thenApply()` and `thenCompose()`?

`thenApply()` transforms a value. `thenCompose()` chains an operation that itself returns a future and flattens the result.

### Q4. Difference between `thenApply()` and `thenApplyAsync()`?

`thenApply()` may run in the thread completing the previous stage or the caller's thread if the previous stage has already completed. `thenApplyAsync()` schedules the continuation through an asynchronous executor.

### Q5. Difference between `thenCombine()` and `allOf()`?

`thenCombine()` combines two results using a function. `allOf()` waits for multiple futures but returns `Void`.

### Q6. Difference between `allOf()` and `anyOf()`?

`allOf()` waits for every supplied future to complete. `anyOf()` completes when the first supplied future completes, normally or exceptionally.

### Q7. What is the difference between `join()` and `get()`?

`join()` throws unchecked `CompletionException` on exceptional completion. `get()` throws checked `ExecutionException` and `InterruptedException`.

### Q8. How do you handle exceptions?

Use `exceptionally()` for recovery with a fallback, `handle()` to process success or failure, and `whenComplete()` for observing completion without transforming the result.

### Q9. How do you set a timeout?

Use `orTimeout()` to complete exceptionally after the deadline or `completeOnTimeout()` to provide a fallback value.

### Q10. Which executor does CompletableFuture use by default?

Async methods without an explicit executor generally use `ForkJoinPool.commonPool()`.

### Q11. Does `cancel(true)` guarantee that a task stops?

No. It requests cancellation of the future but does not guarantee interruption or termination of the underlying computation.

### Q12. When should you use CompletableFuture?

Use it when independent operations can overlap, asynchronous stages need to be composed, or results and failures must be coordinated without manually managing every thread.

---

## 10. Coding Practice

### Problem 1: Transform a result

Create a future that returns `"ORD-101"` and transform it to `"Order confirmed: ORD-101"`.

### Problem 2: Combine two tasks

Start payment verification and inventory reservation independently. Combine their results using `thenCombine()`.

### Problem 3: Wait for multiple tasks

Start payment, inventory, and delivery tasks. Use `allOf()` to wait for all tasks, then print their results.

### Problem 4: Recover from failure

Create a future that throws a `RuntimeException`. Use `exceptionally()` to return `"Service temporarily unavailable"`.

### Problem 5: Set a timeout

Create a task that takes three seconds. Use `completeOnTimeout()` with a one-second timeout and a fallback value.

### Problem 6: Use a custom executor

Create a fixed thread pool and pass it to `supplyAsync()`. Print the executing thread's name and shut down the executor.

### Problem 7: Compare synchronous and asynchronous continuations

Use `thenApply()` and `thenApplyAsync()` and inspect the thread names. Do not assume a specific thread name or ordering.

### Problem 8: Combine concepts

Build a simulated order workflow using asynchronous tasks, result combination, exception recovery, and a timeout.

---

## 11. 10-Second Cheat Sheet

| Method | Purpose |
|---|---|
| `supplyAsync()` | Run task that returns a value |
| `runAsync()` | Run task without a result |
| `thenApply()` | Transform a result |
| `thenAccept()` | Consume a result |
| `thenCompose()` | Chain dependent async work |
| `thenCombine()` | Combine two results |
| `allOf()` | Wait for all futures |
| `anyOf()` | First supplied future to complete |
| `join()` | Wait and retrieve, unchecked exception |
| `get()` | Wait and retrieve, checked exceptions |
| `exceptionally()` | Recover from an exception |
| `handle()` | Process success or failure |
| `whenComplete()` | Observe completion |
| `orTimeout()` | Complete exceptionally on timeout |
| `completeOnTimeout()` | Complete with a fallback |
| `cancel()` | Request cancellation |
| `isDone()` | Check completion |
| `isCancelled()` | Check cancellation |

---

## 12. Final Mental Model

Think of `CompletableFuture` as a pipeline for asynchronous work:

```text
Start tasks
    |
    v
Transform results
    |
    v
Chain dependent operations
    |
    v
Combine independent results
    |
    v
Handle failures and timeouts
    |
    v
Retrieve final result
```

**Remember:**

- `thenApply()` transforms.
- `thenCompose()` chains.
- `thenCombine()` merges two results.
- `allOf()` waits for all.
- `anyOf()` completes with the first supplied future to complete.
- `exceptionally()` recovers.
- `handle()` processes success or failure.
- An async API is not automatically non-blocking end-to-end; blocking operations and executor choices still matter.

**Java Mastery status:** CompletableFuture fundamentals and the planned advanced topics are covered. Next, continue with JVM internals: memory areas, heap vs. stack, garbage collection, and class loading.