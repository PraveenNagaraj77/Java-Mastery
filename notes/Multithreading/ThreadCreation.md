# Thread Creation — Interview Cheat Sheet

## 1. What is it?

Java provides multiple ways to create and execute threads.

The commonly used approaches are:

1. Extend `Thread`
2. Implement `Runnable`
3. Use a lambda expression with `Runnable`

The important conceptual distinction is:

```text
Runnable
→ What task should be executed?

Thread
→ Which execution mechanism runs that task?
```

---

## 2. Why is it needed?

Thread creation is required when an application needs tasks to execute concurrently.

Examples:

- Background processing
- Sending notifications
- Report generation
- File processing
- Independent service operations
- Concurrent application tasks

However, production applications generally prefer:

```text
Runnable
    ↓
ExecutorService
    ↓
Thread Pool
```

instead of creating a new thread for every task.

---

## 3. How does it work / Internal?

### Approach 1: Extending Thread

A class extends `Thread` and overrides `run()`.

```java
class PaymentThread extends Thread {

    @Override
    public void run() {
        System.out.println("Processing payment");
    }
}
```

Execution:

```java
PaymentThread thread = new PaymentThread();
thread.start();
```

Conceptually:

```text
PaymentThread
     ↓
   Thread
     ↓
 start()
     ↓
 JVM executes run()
```

---

### Approach 2: Implementing Runnable

The class represents a task instead of being the thread itself.

```java
class PaymentTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Processing payment");
    }
}
```

Then:

```java
PaymentTask task = new PaymentTask();
Thread thread = new Thread(task);
thread.start();
```

Conceptually:

```text
Runnable
   ↓
Task
   ↓
Thread
   ↓
start()
   ↓
run()
```

This separates:

```text
Task
from
Execution
```

---

### Approach 3: Lambda with Runnable

Because `Runnable` is a functional interface:

```java
Runnable task = () -> {
    System.out.println("Processing payment");
};

Thread thread = new Thread(task);
thread.start();
```

Or directly:

```java
new Thread(() -> {
    System.out.println("Processing payment");
}).start();
```

---

## 4. Syntax

### Extending Thread

```java
class MyThread extends Thread {

    @Override
    public void run() {
        // task
    }
}

MyThread thread = new MyThread();
thread.start();
```

### Implementing Runnable

```java
class MyTask implements Runnable {

    @Override
    public void run() {
        // task
    }
}

Thread thread = new Thread(new MyTask());
thread.start();
```

### Lambda

```java
Thread thread = new Thread(() -> {
    // task
});

thread.start();
```

---

## 5. Simple Example

### Using Thread

```java
class ReportThread extends Thread {

    @Override
    public void run() {
        System.out.println("Generating report...");
    }
}

public class ThreadExample {

    public static void main(String[] args) {

        ReportThread thread = new ReportThread();

        thread.start();
    }
}
```

### Using Runnable

```java
class ReportTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Generating report...");
    }
}

public class RunnableExample {

    public static void main(String[] args) {

        Thread thread = new Thread(new ReportTask());

        thread.start();
    }
}
```

---

## 6. Real-Time Example

Consider an e-commerce order system.

After an order is created, independent tasks can run concurrently:

```text
Order Created
     |
     +── Payment Processing
     |
     +── Inventory Update
     |
     +── Notification
```

Example:

```java
class PaymentTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Processing payment...");
    }
}

class InventoryTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Updating inventory...");
    }
}

class NotificationTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Sending notification...");
    }
}

public class OrderProcessing {

    public static void main(String[] args) {

        new Thread(new PaymentTask()).start();
        new Thread(new InventoryTask()).start();
        new Thread(new NotificationTask()).start();
    }
}
```

In a production application, an `ExecutorService`/thread pool would generally be preferred over manually creating threads for every task.

---

## 7. Important Rules

### Rule 1: `start()` vs `run()`

This is one of the most important interview questions.

```java
thread.start();
```

creates/schedules execution on a separate thread.

```java
thread.run();
```

is just a normal method call on the current thread.

### Example

```java
Thread thread = new Thread(() -> {
    System.out.println(Thread.currentThread().getName());
});

thread.start();
```

The task executes on another thread.

But:

```java
thread.run();
```

executes directly on the current thread.

---

### Rule 2: Do not call `run()` when you want a new thread

Wrong:

```java
thread.run();
```

Correct:

```java
thread.start();
```

---

### Rule 3: `Runnable` represents a task

```text
Runnable
→ What should be done?
```

`Thread` handles execution.

```text
Thread
→ Execute the task
```

---

### Rule 4: Runnable is generally preferred

Why?

Java supports single inheritance.

If you extend `Thread`:

```java
class PaymentTask extends Thread
```

your class cannot extend another class.

With `Runnable`:

```java
class PaymentTask implements Runnable
```

your class can still extend another class if required.

It also separates task logic from thread management.

---

### Rule 5: A Thread object cannot be started twice

This is invalid:

```java
thread.start();
thread.start();
```

It throws:

```text
IllegalThreadStateException
```

---

### Rule 6: Lambda works with Runnable

`Runnable` is a functional interface because it has one abstract method:

```java
void run();
```

Therefore:

```java
Runnable task = () -> {
    System.out.println("Task running");
};
```

is valid.

---

## 8. Common Mistakes

### Mistake 1: Calling `run()` instead of `start()`

```java
thread.run();
```

does not create a new thread.

Use:

```java
thread.start();
```

---

### Mistake 2: Thinking Runnable creates a thread

This:

```java
Runnable task = () -> {
    // work
};
```

only defines a task.

A `Thread` or executor is required to execute it concurrently.

---

### Mistake 3: Creating thousands of threads manually

Avoid:

```text
Request 1 → new Thread()
Request 2 → new Thread()
Request 3 → new Thread()
...
```

This can create excessive overhead.

Prefer:

```text
Tasks
  ↓
ExecutorService
  ↓
Thread Pool
```

---

### Mistake 4: Assuming thread execution order

Starting:

```java
thread1.start();
thread2.start();
```

does not guarantee:

```text
Thread 1
Thread 2
```

The scheduler controls execution.

---

## 9. Interview Questions

### Q1. How can you create a thread in Java?

Common approaches include extending `Thread`, implementing `Runnable`, or using a lambda expression with `Runnable`.

### Q2. Thread vs Runnable?

`Thread` represents the execution mechanism, while `Runnable` represents the task to be executed.

### Q3. Why is Runnable generally preferred?

It separates task logic from thread management and avoids Java's single-inheritance limitation.

### Q4. What is the difference between `start()` and `run()`?

`start()` starts a new thread of execution, while `run()` is a normal method call.

### Q5. Can a thread be started twice?

No. Starting the same `Thread` object twice throws `IllegalThreadStateException`.

### Q6. Why does lambda work with Runnable?

Because `Runnable` is a functional interface containing one abstract method: `run()`.

### Q7. Does creating a Runnable create a thread?

No. Runnable only represents a task.

### Q8. What should be preferred in production?

For managing many tasks, use `ExecutorService` and thread pools instead of manually creating threads repeatedly.

---

## 10. Coding Practice

### Practice 1

Create a thread using `extends Thread`.

Task:

```text
Process Payment
```

---

### Practice 2

Create a task using `implements Runnable`.

Task:

```text
Send Email
```

---

### Practice 3

Create a `Runnable` using a lambda.

Task:

```text
Generate Report
```

---

### Practice 4

Create three independent tasks:

```text
Payment
Inventory
Notification
```

Execute them concurrently.

Observe that the output order is not guaranteed.

---

## 11. 10-Second Cheat Sheet

```text
extends Thread
→ Class IS a Thread

implements Runnable
→ Class IS a Task

Runnable
→ What should be executed?

Thread
→ Mechanism that executes the task

start()
→ Starts separate thread execution

run()
→ Normal method call

Runnable preferred
→ Separation + single inheritance flexibility

Lambda
→ Concise Runnable implementation

Production
→ ExecutorService + Thread Pool
```

---

## 12. Final Mental Model

```text
TASK
 ↓
Runnable
 ↓
Thread / Executor
 ↓
start()
 ↓
Separate execution
 ↓
run()
 ↓
Task executes
```

### Best conceptual model

```text
Runnable = WHAT
Thread   = HOW
Executor = HOW, AT SCALE
```