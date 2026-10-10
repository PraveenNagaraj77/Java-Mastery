
# Concurrent Collections — Interview Cheat Sheet

## 1. What Are Concurrent Collections?

Concurrent collections are thread-safe data structures designed to be accessed and modified by multiple threads concurrently.

They are available primarily in the `java.util.concurrent` package.

Common examples:
- `ConcurrentHashMap`
- `CopyOnWriteArrayList`
- `BlockingQueue`
- `ArrayBlockingQueue`
- `LinkedBlockingQueue`
- `PriorityBlockingQueue`
- `SynchronousQueue`

Example:

```java
ConcurrentHashMap<String, Integer> statusCounts =
        new ConcurrentHashMap<>();
```

Multiple threads can safely perform supported map operations without manually synchronizing every operation.

---

## 2. Why Are They Needed?

Consider an order-processing service with multiple worker threads.

Several threads might:
- Update order statuses.
- Register event listeners.
- Submit orders to a processing queue.
- Count completed and failed payments.
- Transfer tasks between producers and consumers.

Ordinary collections such as `ArrayList`, `HashMap`, and `LinkedList` are not generally safe for concurrent structural modification.

Concurrent collections provide suitable synchronization, atomic operations, or snapshot behavior depending on the implementation.

### Benefits

- Safer concurrent access.
- Reduced need for manual synchronization.
- Specialized operations for common concurrency patterns.
- Better coordination between producers and consumers.
- Improved scalability for suitable workloads.

Important: Thread safety does not mean every group of operations is automatically atomic.

---

## 3. How Do They Work Internally?

Different concurrent collections use different strategies.

### 3.1 ConcurrentHashMap

`ConcurrentHashMap` supports concurrent access to a map.

Modern Java implementations use techniques such as:
- CAS (Compare-And-Set) for certain updates.
- Volatile reads and writes.
- Synchronization on individual bins when necessary.
- Atomic methods such as `putIfAbsent()`, `computeIfAbsent()`, and `merge()`.

It does not lock the entire map for every operation.

### 3.2 CopyOnWriteArrayList

`CopyOnWriteArrayList` uses a copy-on-write strategy.

- Readers can access the current array.
- A write creates a replacement array containing the updated elements.
- Existing iterators continue using the array snapshot captured when they were created.

This is useful when reads are frequent and writes are rare.

### 3.3 BlockingQueue

A `BlockingQueue` provides thread-safe operations for transferring elements between threads.

- `put()` waits when a bounded queue is full.
- `take()` waits when the queue is empty.
- `offer()` and `poll()` provide nonblocking alternatives.
- Timed variants wait only for a specified duration.

The queue handles the required coordination internally.

### 3.4 ArrayBlockingQueue

- Uses a fixed-size array.
- Has a fixed capacity.
- Preserves FIFO order.
- Uses locking to coordinate concurrent operations.

### 3.5 LinkedBlockingQueue

- Uses linked nodes.
- Can have a configured capacity.
- Defaults to `Integer.MAX_VALUE` when capacity is omitted.
- Uses separate put/take locks with coordination between them.

Use an explicit capacity when controlling queue growth matters.

### 3.6 PriorityBlockingQueue

- Uses priority-based retrieval.
- Normally uses natural ordering or a supplied comparator.
- Is effectively unbounded.
- Does not guarantee FIFO retrieval among elements with equal priority.

### 3.7 SynchronousQueue

- Has zero capacity.
- Does not store elements for later retrieval.
- Transfers elements directly between producers and consumers.
- `put()` waits until a consumer receives the element.

---

## 4. Syntax

Import the concurrent collections you need:

```java
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
```

### ConcurrentHashMap

```java
ConcurrentHashMap<String, Integer> counts =
        new ConcurrentHashMap<>();
```

### CopyOnWriteArrayList

```java
CopyOnWriteArrayList<String> listeners =
        new CopyOnWriteArrayList<>();
```

### BlockingQueue implementations

```java
BlockingQueue<String> boundedQueue =
        new ArrayBlockingQueue<>(100);

BlockingQueue<String> linkedQueue =
        new LinkedBlockingQueue<>(100);

BlockingQueue<Integer> priorityQueue =
        new PriorityBlockingQueue<>();

BlockingQueue<String> handoffQueue =
        new SynchronousQueue<>();
```

Prefer programming to an interface such as `BlockingQueue` when you do not need implementation-specific methods.

---

## 5. Simple Examples

### 5.1 ConcurrentHashMap

```java
ConcurrentHashMap<String, Integer> statusCounts =
        new ConcurrentHashMap<>();

statusCounts.put("COMPLETED", 1);

statusCounts.putIfAbsent("COMPLETED", 5);

statusCounts.merge("COMPLETED", 1, Integer::sum);
statusCounts.merge("PENDING", 1, Integer::sum);

System.out.println(statusCounts);
```

Expected counts:

```text
COMPLETED = 2
PENDING = 1
```

Map iteration and printed entry order are not guaranteed.

`putIfAbsent()` does not replace the existing `COMPLETED` value of `1`.

### 5.2 computeIfAbsent()

```java
ConcurrentHashMap<String, List<String>> orderEvents =
        new ConcurrentHashMap<>();

List<String> events = orderEvents.computeIfAbsent(
        "ORD-101",
        key -> new ArrayList<>()
);

events.add("PAYMENT_RECEIVED");
events.add("ORDER_CONFIRMED");

System.out.println(orderEvents);
```

`computeIfAbsent()` creates and stores a list if the key has no mapping.

Important: `ArrayList` itself is not thread-safe. If multiple threads modify the same list, protect those modifications or choose an appropriate thread-safe value type.

### 5.3 CopyOnWriteArrayList

```java
CopyOnWriteArrayList<String> listeners =
        new CopyOnWriteArrayList<>();

listeners.add("EmailListener");
listeners.add("SMSListener");

var iterator = listeners.iterator();

listeners.add("PushListener");

while (iterator.hasNext()) {
    System.out.println(iterator.next());
}

System.out.println("Current list: " + listeners);
```

The iterator prints the original snapshot:

```text
EmailListener
SMSListener
```

The current list includes all three listeners:

```text
Current list: [EmailListener, SMSListener, PushListener]
```

### 5.4 ArrayBlockingQueue

```java
BlockingQueue<String> queue =
        new ArrayBlockingQueue<>(2);

queue.put("ORD-101");
queue.put("ORD-102");

System.out.println(queue.take());
System.out.println(queue.remainingCapacity());
```

Output:

```text
ORD-101
1
```

### 5.5 Producer–Consumer

```java
BlockingQueue<String> queue =
        new ArrayBlockingQueue<>(2);

Thread producer = new Thread(() -> {
    try {
        queue.put("ORD-101");
        queue.put("ORD-102");
        queue.put("ORD-103");
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
});

Thread consumer = new Thread(() -> {
    try {
        for (int i = 0; i < 3; i++) {
            System.out.println("Consumed: " + queue.take());
        }
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
});

producer.start();
consumer.start();

try {
    producer.join();
    consumer.join();
} catch (InterruptedException e) {
    Thread.currentThread().interrupt();
}
```

The consumer receives the orders in FIFO order.

The producer may block when the queue is full until the consumer removes an element.

### 5.6 PriorityBlockingQueue

```java
PriorityBlockingQueue<Integer> queue =
        new PriorityBlockingQueue<>();

queue.put(30);
queue.put(10);
queue.put(20);

System.out.println(queue.take());
System.out.println(queue.take());
System.out.println(queue.take());
```

Output:

```text
10
20
30
```

Natural ordering retrieves the smallest integer first.

### 5.7 SynchronousQueue

```java
SynchronousQueue<String> queue =
        new SynchronousQueue<>();

Thread consumer = new Thread(() -> {
    try {
        System.out.println(queue.take());
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
});

consumer.start();

try {
    queue.put("ORD-101");
    consumer.join();
} catch (InterruptedException e) {
    Thread.currentThread().interrupt();
}
```

The producer cannot complete the `put()` until a consumer receives the element.

---

## 6. Real-Time Example

### Order Processing System

Imagine an e-commerce backend.

```text
Order API
   |
   v
BlockingQueue<Order>
   |
   v
Order Worker Threads
   |
   +--> Update status in ConcurrentHashMap
   |
   +--> Publish events to registered listeners
```

Possible collection choices:

- `BlockingQueue`: transfer submitted orders to workers.
- `ConcurrentHashMap`: track order statuses or count outcomes.
- `CopyOnWriteArrayList`: maintain a listener list that changes rarely.
- `PriorityBlockingQueue`: schedule tasks according to priority.
- `SynchronousQueue`: hand off work directly to a waiting consumer.

These are examples of possible designs, not requirements to use every collection in one application.

---

## 7. Important Rules

### ConcurrentHashMap

1. Does not allow null keys or null values.
2. `putIfAbsent()` atomically inserts only if no mapping exists.
3. `computeIfAbsent()` atomically computes and inserts a missing mapping.
4. `merge()` atomically updates a mapping for one key.
5. Thread safety of the map does not make mutable values thread-safe.
6. Do not assume multi-key operations form one atomic transaction.

### CopyOnWriteArrayList

1. Suitable for read-heavy, write-light workloads.
2. Writes copy the backing array.
3. Iterators are snapshots.
4. Iterators do not support iterator-based mutation such as `remove()`.
5. Frequent writes can be expensive.

### BlockingQueue

1. `put(e)` waits when a bounded queue is full.
2. `take()` waits when the queue is empty.
3. `offer(e)` returns false if immediate insertion fails.
4. `poll()` returns null if no element is available.
5. Timed `offer()` and `poll()` wait only up to their specified timeout.
6. Interruption should be handled appropriately.

### Queue implementations

1. `ArrayBlockingQueue` has fixed capacity.
2. `LinkedBlockingQueue` can be bounded or effectively unbounded.
3. `PriorityBlockingQueue` does not provide bounded backpressure.
4. `SynchronousQueue` has zero capacity.
5. A priority queue's iterator does not promise sorted traversal.

---

## 8. Common Mistakes

### Mistake 1: Check-then-act with ConcurrentHashMap

Unsafe approach:

```java
if (!map.containsKey(key)) {
    map.put(key, value);
}
```

Another thread can modify the map between the two operations.

Prefer:

```java
map.putIfAbsent(key, value);
```

### Mistake 2: Assuming every stored value is thread-safe

```java
ConcurrentHashMap<String, List<String>> map =
        new ConcurrentHashMap<>();
```

The map is concurrent, but a stored `ArrayList` can still be unsafe for concurrent modification.

### Mistake 3: Expecting CopyOnWriteArrayList iterators to see new elements

An existing iterator sees its original snapshot. Create a new iterator to observe the updated list.

### Mistake 4: Using PriorityBlockingQueue as a bounded queue

It is effectively unbounded. It will not provide normal fixed-capacity backpressure.

### Mistake 5: Using SynchronousQueue as storage

A `SynchronousQueue` does not store pending items. Every insertion requires a matching consumer handoff.

### Mistake 6: Ignoring producer backpressure

An unbounded work queue can accumulate excessive pending tasks. Choose capacity and overload-handling behavior deliberately.

### Mistake 7: Forgetting interruption

When catching `InterruptedException` and not propagating it, restore the interrupt status when appropriate:

```java
catch (InterruptedException e) {
    Thread.currentThread().interrupt();
}
```

### Mistake 8: Assuming thread-safe means universally faster

Concurrent collection performance depends on contention, read/write ratio, capacity, memory allocation, and workload.

---

## 9. Interview Questions

### Q1. What are concurrent collections?

Thread-safe data structures designed for concurrent access and modification by multiple threads.

### Q2. How does ConcurrentHashMap differ from HashMap?

`ConcurrentHashMap` supports concurrent map operations safely. `HashMap` is not designed for concurrent structural modification without external synchronization.

### Q3. What is the difference between putIfAbsent() and computeIfAbsent()?

`putIfAbsent()` accepts an already-created value. `computeIfAbsent()` invokes a function to produce a value only when the key has no mapping.

### Q4. What does merge() do?

It inserts a value when a mapping is absent or combines the existing value with the supplied value using a remapping function.

### Q5. Why use CopyOnWriteArrayList?

It suits workloads with frequent reads and rare writes. Iterators operate on snapshots.

### Q6. What is the disadvantage of CopyOnWriteArrayList?

Each write generally copies the backing array, making frequent modifications expensive.

### Q7. What is BlockingQueue?

A thread-safe queue that supports blocking insertion and retrieval operations.

### Q8. What is the difference between put() and offer()?

`put()` waits for space in a bounded queue. `offer()` attempts immediate insertion and returns false if it cannot insert.

### Q9. What is the difference between take() and poll()?

`take()` waits for an element. `poll()` returns immediately with an element or null if empty.

### Q10. ArrayBlockingQueue vs LinkedBlockingQueue?

`ArrayBlockingQueue` uses a fixed array and fixed capacity. `LinkedBlockingQueue` uses linked nodes and can be configured with a capacity.

### Q11. Does PriorityBlockingQueue preserve insertion order?

No. It retrieves according to priority. Equal-priority elements do not have a general FIFO guarantee.

### Q12. What is SynchronousQueue?

A zero-capacity queue that transfers elements directly between a producer and consumer.

### Q13. Does ConcurrentHashMap make its values thread-safe?

No. Its guarantees apply to map operations, not automatically to mutable objects stored as values.

### Q14. What is backpressure?

A way to control incoming work when downstream processing cannot keep up, often by limiting queue capacity and defining overload behavior.

### Q15. Can multiple map operations form one atomic transaction?

Not automatically. Atomicity of an individual mapping operation does not make a sequence of operations across multiple keys atomic.

---

## 10. Coding Practice

### Exercise 1: Concurrent Status Counter

Create a `ConcurrentHashMap<String, Integer>` and count:

```text
COMPLETED
PENDING
COMPLETED
FAILED
COMPLETED
PENDING
```

Use `merge()` to obtain the counts:

```text
COMPLETED = 3
PENDING = 2
FAILED = 1
```

### Exercise 2: Listener Registry

Create a `CopyOnWriteArrayList<String>`.

1. Add two listeners.
2. Create an iterator.
3. Add a third listener.
4. Print the existing iterator.
5. Print the current list.

Explain why the outputs differ.

### Exercise 3: Bounded Queue

Create an `ArrayBlockingQueue<String>` with capacity 3.

1. Add three orders.
2. Print `remainingCapacity()`.
3. Remove one order.
4. Print `remainingCapacity()` again.

Expected capacities: `0`, then `1`.

### Exercise 4: Producer–Consumer

Use one producer and one consumer with a bounded queue.

Produce and consume three orders. Ensure both threads terminate cleanly.

### Exercise 5: Priority Queue

Insert `50`, `15`, `40`, and `5` into a `PriorityBlockingQueue<Integer>`.

Call `take()` four times and explain the output.

### Exercise 6: Synchronous Handoff

Create a `SynchronousQueue<String>` with a producer and consumer thread.

Show that the producer's `put()` cannot complete until a consumer receives the element.

---

## 11. 10-Second Cheat Sheet

- `ConcurrentHashMap` — concurrent key-value operations.
- `putIfAbsent()` — insert only if no mapping exists.
- `computeIfAbsent()` — compute a missing value.
- `merge()` — atomically combine a value for one key.
- `CopyOnWriteArrayList` — read-heavy lists, snapshot iterators.
- `BlockingQueue` — producer–consumer coordination.
- `ArrayBlockingQueue` — fixed-capacity FIFO queue.
- `LinkedBlockingQueue` — linked FIFO queue, optionally bounded.
- `PriorityBlockingQueue` — priority-based retrieval, effectively unbounded.
- `SynchronousQueue` — direct handoff, zero capacity.
- Backpressure — control pending work.
- Thread-safe container ≠ thread-safe contained objects.

---

## 12. Final Mental Model

Think of concurrent collections as tools for different shared-data problems:

- **Need shared key-value data?** Use `ConcurrentHashMap`.
- **Need frequent reads and rare list updates?** Consider `CopyOnWriteArrayList`.
- **Need workers to consume submitted tasks?** Use `BlockingQueue`.
- **Need fixed-capacity FIFO buffering?** Use `ArrayBlockingQueue`.
- **Need linked FIFO buffering with configurable capacity?** Use `LinkedBlockingQueue`.
- **Need priority-based retrieval?** Use `PriorityBlockingQueue`.
- **Need a direct producer-to-consumer handoff?** Use `SynchronousQueue`.

Choose the collection based on the required behavior and workload—not just because it is thread-safe.

**Java Mastery status:** Concurrent collections core concepts covered. Next topic: `CompletableFuture` for asynchronous execution, chaining, combining tasks, and handling failures.
