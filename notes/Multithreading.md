# Multithreading — Mini Cheat Sheet

## 1. What Is Multithreading?

Multithreading allows multiple threads to make progress concurrently within a single process.

**Why use it?**
- Improve application responsiveness.
- Handle multiple tasks or requests.
- Perform background processing.
- Utilize available CPU resources efficiently.

**Where to use it**
- Web servers processing multiple user requests.
- E-commerce applications processing orders.
- Background invoice generation.
- Concurrent file processing.

**Remember:** Concurrency means tasks make progress during overlapping periods. Parallelism means tasks execute simultaneously.

---

## 2. Thread Creation

### Runnable

```java
Runnable task = () -> System.out.println("Task running");
new Thread(task).start();
```

**Where to use it**
- Simple background tasks.
- Learning thread fundamentals.
- Small standalone tasks that need a new thread.

**Prefer:** `Runnable` when you need to represent a task without returning a result.

### Thread

```java
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Task running");
    }
}
```

**Where to use it**
- Understanding how Java threads work.
- Specialized thread behavior in limited cases.

For most application tasks, prefer `Runnable`, `Callable`, or an executor instead of creating a thread for every task.

**Remember:** `start()` initiates a new thread; calling `run()` directly does not.

---

## 3. Thread Lifecycle and Control

Useful methods:

```java
thread.start();
thread.join();
Thread.sleep(1000);
Thread.currentThread().getName();
```

- `start()` — starts thread execution.
- `join()` — waits for another thread to terminate.
- `sleep()` — pauses the current thread temporarily.
- `currentThread()` — identifies the executing thread.

**Where to use it**
- `join()` — wait for a worker to finish before continuing.
- `sleep()` — simple timing demonstrations or polling with a delay; prefer proper coordination mechanisms in production.
- `currentThread()` — logging, debugging, and identifying execution threads.

**Important:** `sleep()` does not release monitors held by the current thread.

---

## 4. Synchronization and Race Conditions

A race condition occurs when concurrent access to shared mutable state produces timing-dependent results.

```java
synchronized void increment() {
    count++;
}
```

`synchronized` provides mutual exclusion and memory visibility through monitor locking.

**Where to use it**
- Multiple threads updating a shared account balance.
- Protecting shared in-memory counters.
- Maintaining consistency of shared mutable objects.

**Remember**
- Instance synchronized methods lock the current object.
- Static synchronized methods lock the class object.
- Use synchronized blocks to protect smaller critical sections.
- All accesses to shared state must follow a consistent synchronization strategy.

---

## 5. volatile

```java
private volatile boolean running = true;
```

Ensures visibility of writes to a variable across threads under Java's memory model.

**Where to use it**
- A shutdown flag read by one or more worker threads.
- A simple status flag where updates do not depend on compound operations.

**Do not use it alone for**
- `count++`
- Check-then-act operations.
- Multi-variable invariants.

**Remember:** `volatile` provides visibility and ordering guarantees, not mutual exclusion or atomicity for compound operations.

---

## 6. Atomic Classes

```java
AtomicInteger count = new AtomicInteger();

count.incrementAndGet();
count.get();
```

**Where to use it**
- Concurrent counters.
- Request totals and metrics.
- Generating simple atomic sequence numbers.
- Atomic state updates on a single variable.

**Choose `AtomicInteger` instead of a synchronized counter** when the requirement is a suitable atomic operation on one integer.

For complex state changes involving several variables, consider locking or another appropriate concurrency design.

---

## 7. Thread Communication

```java
synchronized (lock) {
    while (!condition) {
        lock.wait();
    }

    // Continue after the condition becomes true.
}
```

- `wait()` — releases the monitor and waits.
- `notify()` — wakes one waiting thread.
- `notifyAll()` — wakes all threads waiting on that monitor.

**Where to use it**
- Coordinating threads that share a condition.
- Learning low-level producer-consumer coordination.
- Waiting until a shared state change occurs.

**Preferred in many real applications:** use `BlockingQueue` for producer-consumer workflows instead of manually managing `wait()` and `notify()`.

Always check the condition in a `while` loop and call these methods while holding the same object's monitor.

---

## 8. ReentrantLock

```java
ReentrantLock lock = new ReentrantLock();

lock.lock();
try {
    // Critical section
} finally {
    lock.unlock();
}
```

**Where to use it**
- When `tryLock()` is needed.
- When lock acquisition must be interruptible.
- When explicit lock management or optional fairness is useful.

**Remember**
- Release the lock in `finally` after successfully acquiring it.
- Prefer `synchronized` for simple locking when advanced lock features are unnecessary.

---

## 9. ExecutorService

```java
ExecutorService executor =
        Executors.newFixedThreadPool(3);

try {
    executor.submit(() -> System.out.println("Task running"));
} finally {
    executor.shutdown();
}
```

Manages task execution using a pool of reusable threads.

**Where to use it**
- Processing background jobs.
- Running multiple independent tasks.
- Managing a controlled number of worker threads.
- Batch jobs and asynchronous application work.

**Remember**
- `execute()` submits a task without returning a `Future`.
- `submit()` returns a `Future`.
- `shutdown()` stops accepting new tasks and allows submitted tasks to finish.
- Choose pool sizes and queues according to workload and resource limits.

---

## 10. Callable and Future

```java
ExecutorService executor = Executors.newSingleThreadExecutor();

try {
    Future<Integer> future = executor.submit(() -> 100);
    System.out.println(future.get());
} finally {
    executor.shutdown();
}
```

- `Callable<V>` — returns a result and may throw checked exceptions.
- `Future<V>` — represents a pending result.
- `get()` — waits for and retrieves the result.

**Where to use it**
- Running a calculation in the background and retrieving its result later.
- Batch processing with one result per task.
- Legacy executor-based asynchronous workflows.

**Limitation:** `get()` blocks. Use `CompletableFuture` when you need richer asynchronous chaining and composition.

---

## 11. CompletableFuture

```java
CompletableFuture<String> future =
        CompletableFuture.supplyAsync(() -> "ORD-101")
                .thenApply(id -> "Confirmed: " + id)
                .exceptionally(ex -> "Order unavailable");

System.out.println(future.join());
```

**Where to use it**
- Calling multiple independent services.
- Chaining dependent asynchronous operations.
- Combining payment, inventory, and delivery results.
- Handling service failures and timeouts.
- Building asynchronous workflows without manually coordinating every thread.

| Method | Purpose |
|---|---|
| `supplyAsync()` | Async task returning a result |
| `runAsync()` | Async task without a result |
| `thenApply()` | Transform a result |
| `thenCompose()` | Chain dependent futures |
| `thenCombine()` | Combine two results |
| `allOf()` | Wait for all supplied futures |
| `anyOf()` | Complete with the first supplied future to complete |
| `exceptionally()` | Recover from failure |
| `handle()` | Process success or failure |
| `orTimeout()` | Complete exceptionally on timeout |
| `completeOnTimeout()` | Complete with a fallback on timeout |

**Important**
- Async methods without an explicit executor generally use the common `ForkJoinPool`.
- `thenApplyAsync()` schedules the continuation asynchronously.
- Timeouts do not guarantee the underlying task stops.
- `join()` blocks until completion, so avoid blocking carelessly inside asynchronous pipelines.

---

## 12. Concurrent Collections

| Collection | Where to use it |
|---|---|
| `ConcurrentHashMap` | Shared caches, concurrent counters, and lookup tables |
| `CopyOnWriteArrayList` | Read-heavy lists with infrequent updates |
| `ArrayBlockingQueue` | Bounded producer-consumer pipelines |
| `LinkedBlockingQueue` | Producer-consumer workflows with configurable capacity |
| `PriorityBlockingQueue` | Tasks retrieved according to priority |
| `SynchronousQueue` | Direct handoff between producers and consumers |

**Remember**
- Use concurrent collections when multiple threads access shared collection state.
- Prefer bounded queues when you need explicit capacity control and backpressure.
- Thread-safe collections do not automatically make their stored mutable objects thread-safe.

---

## 13. Common Problems

| Problem | Meaning | Where it appears |
|---|---|---|
| Race condition | Result depends on timing | Concurrent account updates |
| Deadlock | Threads wait on each other's locks | Multiple locks acquired in inconsistent order |
| Starvation | A thread cannot get enough execution or resources | Unfair contention or overloaded pools |
| Visibility issue | A thread sees stale state | Shared flags without proper coordination |
| Thread starvation | Workers are blocked and needed work cannot run | Blocking tasks sharing a limited executor |
| Resource leak | Threads or locks are not properly managed | Executors never shut down; locks not released |

**How to avoid them**
- Protect shared mutable state.
- Keep critical sections small.
- Use consistent lock ordering.
- Prefer higher-level concurrency utilities.
- Configure executors deliberately.
- Handle cancellation, exceptions, and shutdown properly.

---

## 14. Interview Quick Recall

| Question | Quick answer |
|---|---|
| `start()` vs `run()` | Starts a thread vs ordinary method call |
| `sleep()` vs `wait()` | Sleep doesn't release held monitors; wait releases the monitor it waits on |
| `synchronized` vs `volatile` | Mutual exclusion and visibility vs visibility and ordering guarantees |
| `Runnable` vs `Callable` | No result vs result |
| `execute()` vs `submit()` | No returned future vs returned future |
| `Future` vs `CompletableFuture` | Basic result handling vs composable asynchronous stages |
| `notify()` vs `notifyAll()` | Wake one waiter vs all waiters |
| `HashMap` vs `ConcurrentHashMap` | Not thread-safe vs concurrent operations supported |
| `AtomicInteger` vs `volatile int` | Atomic integer operations vs visibility without atomic increments |
| `allOf()` vs `anyOf()` | Wait for all futures vs first supplied future to complete |

---

## 15. Final Mental Model — What Should I Use?

```text
Need a separate task?
    └── Runnable / Callable

Need managed threads?
    └── ExecutorService

Need to protect shared state?
    ├── Simple critical section → synchronized
    ├── Advanced locking → ReentrantLock
    ├── Single-variable atomic update → AtomicInteger
    └── Simple cross-thread flag → volatile

Need producer-consumer coordination?
    └── BlockingQueue

Need a shared concurrent map?
    └── ConcurrentHashMap

Need a background result?
    └── Callable + Future

Need asynchronous chaining and combining?
    └── CompletableFuture
```

**Golden rule:** First identify the concurrency problem. Then choose the simplest mechanism that safely solves it.

Do not use multithreading just because it is available. Use it when concurrency improves throughput, responsiveness, or resource utilization without compromising correctness.