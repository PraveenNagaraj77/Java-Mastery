# Executor Framework — Interview Cheat Sheet

## 1. What is it?

The **Executor Framework** is a Java concurrency API used to manage and execute tasks using a pool of reusable threads.

It separates:

- **Task** → what should be executed
- **Execution** → how and where the task runs
- **Thread management** → creation, reuse, and shutdown of threads

Main package:

```java
java.util.concurrent
```

Core hierarchy:

```text
Executor
   ↓
ExecutorService
   ↓
ThreadPoolExecutor
```

---

## 2. Why is it needed?

Creating threads manually for every task can become inefficient:

```text
1000 Tasks
   ↓
1000 Threads
   ↓
High memory usage
Thread creation overhead
Poor resource management
```

Executor Framework allows us to use a limited number of reusable worker threads:

```text
1000 Tasks
    ↓
ExecutorService
    ↓
Thread Pool
    ↓
Limited Worker Threads
    ↓
Tasks executed and threads reused
```

Benefits:

- Reuses threads
- Controls number of concurrent tasks
- Reduces thread creation overhead
- Manages task execution
- Provides task submission APIs
- Supports graceful shutdown
- Provides `Future` for asynchronous results

---

## 3. How does it work / Internal?

### Basic flow

```text
Application
    ↓
Submit Task
    ↓
ExecutorService
    ↓
Thread Pool
    ↓
Available Worker Thread
    ↓
Execute Task
```

If all workers are busy:

```text
Tasks
 ↓
ExecutorService
 ↓
Worker-1 → Running
Worker-2 → Running
Worker-3 → Running
Task-4   → Waiting in Queue
Task-5   → Waiting in Queue
```

When a worker finishes:

```text
Worker-1 finishes
      ↓
Takes next queued task
      ↓
Executes it
```

This is why thread pools improve resource usage.

---

### Fixed Thread Pool

```java
ExecutorService executorService =
        Executors.newFixedThreadPool(3);
```

This creates a pool with a fixed number of worker threads.

```text
Thread Pool Size = 3

Worker-1
Worker-2
Worker-3
```

If 5 tasks are submitted:

```text
Task-1 → Worker-1
Task-2 → Worker-2
Task-3 → Worker-3
Task-4 → Queue
Task-5 → Queue
```

When a worker becomes free, it takes the next task.

---

## 4. Syntax

### Create ExecutorService

```java
ExecutorService executorService =
        Executors.newFixedThreadPool(3);
```

### Execute a Runnable

```java
executorService.execute(() -> {
    System.out.println("Processing Order");
});
```

### Submit a task

```java
Future<?> future =
        executorService.submit(() -> {
            System.out.println("Processing Order");
        });
```

`submit()` returns a `Future`.

### Shutdown

```java
executorService.shutdown();
```

---

### `execute()` vs `submit()`

```text
execute()
   ↓
Executor
   ↓
Runnable
   ↓
No Future
```

```text
submit()
   ↓
ExecutorService
   ↓
Runnable / Callable
   ↓
Future
```

Use `execute()` when you simply need to run a task.

Use `submit()` when you need to track the task or retrieve a result.

---

## 5. Simple Example

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDemo {

    public static void main(String[] args) {

        ExecutorService executorService =
                Executors.newFixedThreadPool(3);

        executorService.execute(() ->
                System.out.println(
                        Thread.currentThread().getName()
                                + " Processing Order"
                )
        );

        executorService.execute(() ->
                System.out.println(
                        Thread.currentThread().getName()
                                + " Processing Payment"
                )
        );

        executorService.execute(() ->
                System.out.println(
                        Thread.currentThread().getName()
                                + " Sending Notification"
                )
        );

        executorService.shutdown();
    }
}
```

Possible output:

```text
pool-1-thread-1 Processing Order
pool-1-thread-2 Processing Payment
pool-1-thread-3 Sending Notification
```

The exact order is not guaranteed.

---

## 6. Real-Time Example

Consider an e-commerce application.

A customer places an order.

The backend may need to perform multiple tasks:

```text
Order Created
     ↓
ExecutorService
     ├── Process Payment
     ├── Update Inventory
     ├── Send Notification
     └── Generate Invoice
```

Instead of creating a new thread manually for every task:

```java
new Thread(task).start();
```

we can use a thread pool:

```java
ExecutorService executorService =
        Executors.newFixedThreadPool(4);
```

Then submit tasks:

```java
executorService.execute(() -> processPayment());

executorService.execute(() -> updateInventory());

executorService.execute(() -> sendNotification());

executorService.execute(() -> generateInvoice());
```

The pool manages the worker threads.

---

## 7. Important Rules

### Rule 1: Prefer ExecutorService over manual thread management

Instead of:

```java
new Thread(task).start();
```

for large numbers of independent tasks, prefer:

```java
executorService.execute(task);
```

---

### Rule 2: Thread pools reuse threads

```text
Task-1
   ↓
Worker-1
   ↓
finished

Worker-1
   ↓
Task-4
```

The same worker thread can execute multiple tasks.

---

### Rule 3: `execute()` does not return a result

```java
executorService.execute(task);
```

There is no `Future` returned.

---

### Rule 4: `submit()` returns a Future

```java
Future<?> future =
        executorService.submit(task);
```

The `Future` can be used to:

- Check completion
- Retrieve a result
- Cancel a task
- Check cancellation

---

### Rule 5: Always shut down the executor

```java
executorService.shutdown();
```

`shutdown()`:

- Stops accepting new tasks
- Allows already submitted tasks to finish
- Eventually terminates the executor

---

### Rule 6: `shutdown()` does not immediately kill running tasks

```text
shutdown()
    ↓
No new tasks
    ↓
Existing tasks continue
    ↓
Tasks finish
    ↓
Executor terminates
```

---

### Rule 7: Number of threads controls concurrency

```java
Executors.newFixedThreadPool(3);
```

means at most approximately three tasks execute concurrently in that pool.

---

### Rule 8: Don't assume task execution order

Submitting:

```java
task1
task2
task3
```

does not guarantee:

```text
task1
task2
task3
```

will print in that exact order.

Thread scheduling is controlled by the JVM/OS scheduler.

---

## 8. Common Mistakes

### Mistake 1: Creating normal threads instead of using the executor

Wrong:

```java
ExecutorService executorService =
        Executors.newFixedThreadPool(3);

Thread thread = new Thread(task);
thread.start();
```

The executor is not managing that thread.

Correct:

```java
executorService.execute(task);
```

---

### Mistake 2: Forgetting shutdown

```java
ExecutorService executorService =
        Executors.newFixedThreadPool(3);

executorService.execute(task);

// Missing shutdown
```

Prefer:

```java
executorService.shutdown();
```

---

### Mistake 3: Confusing `execute()` and `submit()`

```text
execute()
→ fire-and-forget Runnable

submit()
→ task + Future
```

---

### Mistake 4: Assuming fixed pool means fixed number of tasks

```java
Executors.newFixedThreadPool(3);
```

does NOT mean only three tasks can be submitted.

It means only three worker threads execute tasks concurrently.

Example:

```text
100 Tasks
   ↓
3 Worker Threads
   ↓
Remaining tasks wait in queue
```

---

### Mistake 5: Assuming `submit()` waits for the task

`submit()` returns immediately with a `Future`.

```java
Future<Integer> future =
        executorService.submit(task);
```

To wait for the result:

```java
Integer result = future.get();
```

---

### Mistake 6: Assuming Future means the result is ready

```java
Future<Integer> future =
        executorService.submit(task);
```

The result may still be processing.

```text
Future
  ↓
Task may still be running
  ↓
get()
  ↓
Wait if necessary
  ↓
Result
```

---

## 9. Interview Questions

### Q1. What is the Executor Framework?

The Executor Framework is a Java concurrency API that manages task execution using reusable worker threads and thread pools.

---

### Q2. Why use ExecutorService instead of creating threads manually?

ExecutorService manages thread creation, reuse, task execution, and shutdown, reducing the overhead and complexity of manual thread management.

---

### Q3. What is a thread pool?

A thread pool is a collection of reusable worker threads that execute submitted tasks.

---

### Q4. What does `newFixedThreadPool(3)` mean?

It creates an ExecutorService with a fixed pool of three worker threads.

At most three tasks can execute concurrently in that pool.

---

### Q5. What is the difference between `execute()` and `submit()`?

`execute()` accepts a `Runnable` and does not return a result.

`submit()` accepts `Runnable` or `Callable` and returns a `Future`.

---

### Q6. What does `shutdown()` do?

It stops accepting new tasks while allowing already submitted tasks to complete.

---

### Q7. Does `shutdown()` immediately stop running tasks?

No.

Already submitted tasks are allowed to finish.

---

### Q8. Are tasks executed in submission order?

Not necessarily.

The scheduler determines when worker threads execute tasks.

---

### Q9. What happens if all worker threads are busy?

Additional tasks wait in the executor's task queue until a worker becomes available.

---

### Q10. What is the relationship between Executor, ExecutorService, and ThreadPoolExecutor?

```text
Executor
   ↓
Basic task execution abstraction

ExecutorService
   ↓
Adds lifecycle and task submission management

ThreadPoolExecutor
   ↓
Concrete configurable thread-pool implementation
```

---

## 10. Coding Practice

### Practice 1 — Fixed Thread Pool

Create:

```text
ExecutorBasicPractice.java
```

Requirements:

- Create a fixed thread pool with 3 threads.
- Submit 5 order-processing tasks.
- Print the worker thread name.
- Print `"Processing Order"`.
- Shutdown the executor.

Expected concept:

```text
5 Tasks
   ↓
3 Worker Threads
   ↓
Tasks are executed
   ↓
Workers are reused
```

---

### Practice 2 — Submit + Future

Create:

```text
ExecutorSubmitPractice.java
```

Submit three `Callable<Integer>` tasks:

```text
Order-1 → 500
Order-2 → 750
Order-3 → 1000
```

Store the results:

```java
Future<Integer> order1;
Future<Integer> order2;
Future<Integer> order3;
```

Retrieve them using:

```java
order1.get();
order2.get();
order3.get();
```

---

### Practice 3 — Future Methods

Practice:

```java
future.get();
future.isDone();
future.isCancelled();
future.cancel(true);
```

Understand that:

```text
isDone()
→ task reached a terminal state

isCancelled()
→ task was cancelled

cancel(true)
→ requests cancellation/interruption
```

---

## 11. 10-Second Cheat Sheet

```text
Executor Framework
        ↓
Manages task execution
        ↓
ExecutorService
        ↓
Thread Pool
        ↓
Reusable Worker Threads
```

```text
execute()
→ Runnable
→ no Future
→ no result

submit()
→ Runnable / Callable
→ Future
→ track/retrieve result
```

```text
newFixedThreadPool(3)
→ 3 worker threads
→ extra tasks wait in queue
→ workers are reused
```

```text
shutdown()
→ stop accepting new tasks
→ finish existing tasks
→ terminate
```

Core classes:

```text
Executor
ExecutorService
Executors
ThreadPoolExecutor
Future
```

---

## 12. Final Mental Model

Think of `ExecutorService` as a **task manager with a team of reusable workers**.

```text
                ExecutorService
                      │
             ┌────────┼────────┐
             ↓        ↓        ↓
         Worker-1  Worker-2  Worker-3
             │        │        │
             ↓        ↓        ↓
          Task-1   Task-2   Task-3
             │
             ↓
          Finished
             │
             ↓
       Worker reused
             │
             ↓
          Task-4
```

The key separation is:

```text
Runnable / Callable
        ↓
       TASK
"What should be done?"

ExecutorService
        ↓
    EXECUTION
"Where/how should it run?"

Thread Pool
        ↓
    RESOURCES
"How many workers should run?"
```

### One-line interview summary

> **Executor Framework provides a managed way to execute concurrent tasks using reusable threads, reducing manual thread-management overhead and providing APIs such as `execute()`, `submit()`, `shutdown()`, and `Future` for task management.**