# Multithreading Fundamentals — Interview Cheat Sheet

## 1. What is it?

**Multithreading** is the ability of a program to execute multiple threads concurrently within the same process.

A **thread** is the smallest unit of execution within a process.

**Concurrency** means multiple tasks can make progress during overlapping periods.

**Parallelism** means multiple tasks are actually executing simultaneously, usually on multiple CPU cores.

### Simple mental model

```text
Process
   ↓
Contains multiple Threads
   ↓
Each thread executes a task
   ↓
Threads share process resources
   ↓
Shared data can cause race conditions
```

---

## 2. Why is it needed?

Multithreading is useful for:

- Improving application responsiveness
- Handling multiple tasks concurrently
- Better CPU utilization
- Running background tasks
- Processing independent operations simultaneously
- Handling multiple requests in server applications

### Real-world example

An e-commerce application receives an order.

Different tasks may be handled concurrently:

```text
Order Created
     |
     +── Process Payment
     |
     +── Update Inventory
     |
     +── Send Notification
```

Instead of waiting for every operation sequentially, independent tasks can make progress concurrently.

---

## 3. How does it work / Internal?

A Java application runs inside a **process**.

A process can contain multiple threads.

Threads inside the same process share resources such as:

- Heap
- Method Area
- Loaded classes
- Objects

But each thread has its own:

- Stack
- Program counter
- Execution state

### Memory model

```text
Process
│
├── Heap
│    └── Shared objects
│
├── Method Area
│    └── Class information
│
├── Thread 1
│    └── Own Stack
│
├── Thread 2
│    └── Own Stack
│
└── Thread 3
     └── Own Stack
```

Because threads share objects, multiple threads can access the same data.

This creates concurrency problems such as:

```text
Race Condition
Deadlock
Visibility Problems
Data Inconsistency
```

Synchronization mechanisms are used to control access to shared data.

---

## 4. Syntax

Basic thread creation:

```java
Thread thread = new Thread(() -> {
    // task
});

thread.start();
```

Check the current thread:

```java
Thread.currentThread();
```

Get thread name:

```java
Thread.currentThread().getName();
```

---

## 5. Simple Example

```java
public class ThreadExample {

    public static void main(String[] args) {

        Thread worker = new Thread(() -> {
            System.out.println("Worker thread is running");
        });

        worker.start();

        System.out.println("Main thread is running");
    }
}
```

Possible output:

```text
Main thread is running
Worker thread is running
```

The order can vary because thread scheduling is controlled by the JVM/OS scheduler.

---

## 6. Real-Time Example

Consider an online shopping application.

After an order is placed:

```text
Order Service
     |
     +── Payment Processing
     |
     +── Inventory Update
     |
     +── Notification
```

Each independent task can be handled concurrently.

For example:

```java
Runnable paymentTask = () -> {
    System.out.println("Processing payment...");
};

Runnable inventoryTask = () -> {
    System.out.println("Updating inventory...");
};

Runnable notificationTask = () -> {
    System.out.println("Sending notification...");
};

new Thread(paymentTask).start();
new Thread(inventoryTask).start();
new Thread(notificationTask).start();
```

In production applications, manually creating threads for every task is generally avoided. Thread pools and the Executor Framework are preferred.

---

## 7. Important Rules

### Process vs Thread

| Process | Thread |
|---|---|
| Running program | Execution unit inside process |
| Has separate memory/resources | Shares process resources |
| Heavier | Lighter |
| Process communication is relatively expensive | Thread communication is easier through shared memory |
| Can contain multiple threads | Exists inside a process |

### Important distinction

Do not define a thread as:

> "A smaller process."

Better:

> A thread is an independent unit of execution within a process.

### Concurrency vs Parallelism

```text
Concurrency
→ Multiple tasks make progress during overlapping periods.

Parallelism
→ Multiple tasks execute at the same time.
```

Concurrency does not necessarily mean simultaneous execution.

---

## 8. Common Mistakes

### Mistake 1: Saying concurrency means simultaneous execution

Incorrect:

```text
Concurrency = tasks execute at exactly the same time
```

Correct:

```text
Concurrency = multiple tasks can make progress during overlapping periods
```

---

### Mistake 2: Calling a thread a mini-process

A thread is not technically a mini-process.

It is an execution unit inside a process.

---

### Mistake 3: Assuming thread execution order

This:

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

### Mistake 4: Assuming shared memory is always good

Shared memory makes communication easier but introduces risks:

```text
Shared Data
    ↓
Multiple Threads
    ↓
Concurrent Modification
    ↓
Race Condition
```

---

## 9. Interview Questions

### Q1. What is multithreading?

Multithreading allows multiple threads within a process to execute concurrently.

### Q2. What is a thread?

A thread is the smallest unit of execution within a process.

### Q3. What is concurrency?

Concurrency is the ability of a system to manage multiple tasks whose execution overlaps in time.

### Q4. What is parallelism?

Parallelism means multiple tasks are actually executing simultaneously, generally on different CPU cores.

### Q5. Process vs Thread?

A process is a running program with its own resources, while a thread is an execution unit within a process that shares many process resources.

### Q6. Why can multithreading cause problems?

Because threads can share mutable data, concurrent access can cause race conditions and inconsistent results.

### Q7. Do threads have their own memory?

Threads have their own stacks and execution state, but share process-level resources such as heap objects.

### Q8. Does multithreading always improve performance?

No.

Thread creation, scheduling, synchronization and context switching have overhead. Multithreading is beneficial when tasks can effectively overlap or use available CPU resources.

---

## 10. Coding Practice

### Practice 1

Create two threads:

```text
Thread 1 → Process Payment
Thread 2 → Send Notification
```

Start both threads.

---

### Practice 2

Create three threads:

```text
Inventory Update
Email Notification
Report Generation
```

Observe that execution order is not guaranteed.

---

### Practice 3

Create a shared counter accessed by two threads.

Observe that concurrent modification can produce inconsistent results.

This leads directly to:

```text
Race Condition
```

---

## 11. 10-Second Cheat Sheet

```text
Process
→ Running program

Thread
→ Unit of execution inside a process

Multithreading
→ Multiple threads executing concurrently

Concurrency
→ Multiple tasks make overlapping progress

Parallelism
→ Multiple tasks execute simultaneously

Threads share
→ Heap / process resources

Threads have their own
→ Stack / execution state

Main risk
→ Race conditions

Solution
→ Synchronization / concurrency utilities
```

---

## 12. Final Mental Model

```text
APPLICATION
     ↓
   PROCESS
     ↓
 ┌───┴───────────────┐
 ↓       ↓           ↓
Thread  Thread      Thread
 ↓       ↓           ↓
Task    Task        Task
 └───────┼───────────┘
         ↓
   Shared Resources
         ↓
   Concurrent Access
         ↓
    Race Conditions
         ↓
   Synchronization
```