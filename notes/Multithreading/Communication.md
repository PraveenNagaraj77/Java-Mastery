# Thread Communication — Interview Cheat Sheet

## 1. What is it?

Thread Communication allows threads to **coordinate with each other** when working with shared resources.

Java provides:

- `wait()`
- `notify()`
- `notifyAll()`

These are commonly used when one thread needs to wait until another thread changes some shared state.

### Basic idea

```text
Thread A
   ↓
wait()
   ↓
WAITING
   ↓
Thread B changes shared state
   ↓
notify() / notifyAll()
   ↓
Thread A wakes up
   ↓
reacquires lock
   ↓
continues
```

---

## 2. Why is it needed?

Without thread communication, a thread may repeatedly check whether work is available.

Example:

```text
Consumer → Is there an order?
Consumer → No

Consumer → Is there an order?
Consumer → No

Consumer → Is there an order?
Consumer → No
```

This is called **busy waiting / polling**.

Instead:

```text
Consumer
   ↓
wait()
   ↓
WAITING
   ↓
Producer adds order
   ↓
notifyAll()
   ↓
Consumer wakes
```

This allows threads to coordinate efficiently.

### Common use cases

- Producer–Consumer
- Order processing
- Background job queues
- Logging systems
- Notification systems
- File/data processing
- Worker thread coordination

---

## 3. How does it work / Internal?

### `wait()`

`wait()` tells the current thread:

> "I cannot continue right now. Let another thread change the shared state."

Example:

```java
synchronized (lock) {
    lock.wait();
}
```

When `wait()` is called:

```text
Thread
   ↓
WAITING
   ↓
Releases monitor lock
   ↓
Waits for notification
```

Important:

> `wait()` releases the lock.

---

### `notify()`

`notify()` wakes **one** thread waiting on the same object's monitor.

```java
synchronized (lock) {
    lock.notify();
}
```

Important:

- It wakes one waiting thread.
- You cannot choose which waiting thread wakes.
- The notified thread does not immediately get the lock.
- The notifier must release the lock first.
- The awakened thread then competes to reacquire the lock.

---

### `notifyAll()`

`notifyAll()` wakes **all threads** waiting on the same object's monitor.

```java
synchronized (lock) {
    lock.notifyAll();
}
```

Important:

```text
notify()
    ↓
One waiting thread wakes

notifyAll()
    ↓
All waiting threads wake
    ↓
Compete for the lock
```

`notifyAll()` does **not** mean all threads execute simultaneously.

Only one thread can hold the same monitor lock at a time.

---

### Same lock requirement

If a thread waits using:

```java
lock.wait();
```

the notification must be performed on the **same object**:

```java
lock.notify();
```

or:

```java
lock.notifyAll();
```

---

### Why synchronized is required?

`wait()`, `notify()`, and `notifyAll()` operate on an object's monitor.

Therefore the current thread must own that monitor.

Correct:

```java
synchronized (lock) {
    lock.wait();
}
```

Incorrect:

```java
lock.wait(); // IllegalMonitorStateException
```

---

### Condition-based waiting

The standard pattern is:

```java
synchronized (lock) {

    while (!condition) {
        lock.wait();
    }

    // Continue when condition is satisfied
}
```

Use `while`, not just `if`.

Why?

Because waking up does not guarantee that the condition is still satisfied.

```text
Wake up
   ↓
Check condition again
   ↓
Condition false → wait again
Condition true  → continue
```

---

## 4. Syntax

### wait()

```java
synchronized (lock) {
    lock.wait();
}
```

### notify()

```java
synchronized (lock) {
    lock.notify();
}
```

### notifyAll()

```java
synchronized (lock) {
    lock.notifyAll();
}
```

### Condition-based waiting

```java
synchronized (lock) {
    while (!condition) {
        lock.wait();
    }

    // perform operation
}
```

---

## 5. Simple Example

```java
public class WaitNotifyExample {

    public static void main(String[] args) throws InterruptedException {

        Object lock = new Object();

        Thread worker = new Thread(() -> {

            synchronized (lock) {

                System.out.println("Worker waiting...");

                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println("Worker resumed");
            }
        });

        Thread notifier = new Thread(() -> {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            synchronized (lock) {
                System.out.println("Sending notification...");
                lock.notify();
            }
        });

        worker.start();
        notifier.start();

        worker.join();
        notifier.join();
    }
}
```

Flow:

```text
Worker waiting
       ↓
wait()
       ↓
WAITING
       ↓
2 seconds
       ↓
Notifier → notify()
       ↓
Worker resumes
```

---

## 6. Real-Time Example

### Producer–Consumer

Imagine an order-processing system.

```text
Customer
   ↓
Producer
   ↓
Order Queue
   ↓
Consumer
   ↓
Process Order
```

### Consumer

If there are no orders:

```java
synchronized (lock) {

    while (orderQueue.isEmpty()) {
        lock.wait();
    }

    String order = orderQueue.poll();
}
```

The consumer waits instead of continuously checking the queue.

### Producer

When an order arrives:

```java
synchronized (lock) {

    orderQueue.add("ORDER-101");

    lock.notifyAll();
}
```

The producer tells waiting consumers:

> "The queue has changed. Check again."

### Bounded queue

If the queue has limited capacity:

```text
Queue capacity = 3

[101] [102] [103]
```

Producer tries to add another order:

```text
Queue full
   ↓
Producer waits
```

Consumer removes an order:

```text
Consumer removes 101
   ↓
Space available
   ↓
notifyAll()
   ↓
Producer wakes
```

So communication works in both directions.

---

## 7. Important Rules

### Rule 1 — wait() releases the lock

```java
lock.wait();
```

puts the thread into `WAITING` and releases the monitor lock.

---

### Rule 2 — sleep() does NOT release the lock

```java
Thread.sleep(2000);
```

puts the thread into `TIMED_WAITING`, but it keeps the monitor lock.

```text
sleep() → waits for time → keeps lock

wait()  → waits for communication → releases lock
```

---

### Rule 3 — wait/notify must use the same lock

```java
lock.wait();
```

must correspond to:

```java
lock.notify();
```

or:

```java
lock.notifyAll();
```

---

### Rule 4 — Must own the monitor

Always call them inside:

```java
synchronized (lock) {
    ...
}
```

Otherwise:

```text
IllegalMonitorStateException
```

---

### Rule 5 — Prefer while over if

Use:

```java
while (!condition) {
    lock.wait();
}
```

not:

```java
if (!condition) {
    lock.wait();
}
```

Always re-check the condition after waking.

---

### Rule 6 — notify() doesn't release the lock

This:

```java
lock.notify();
```

only signals a waiting thread.

The notifier still owns the lock until the synchronized block exits.

---

### Rule 7 — notifyAll() wakes all waiting threads

But they still compete for the lock one at a time.

```text
notifyAll()
    ↓
Worker 1 ─┐
Worker 2 ─┼→ compete for lock
Worker 3 ─┘
```

---

### Rule 8 — Thread order is not guaranteed

Starting:

```java
thread1.start();
thread2.start();
thread3.start();
```

does not guarantee:

```text
Thread-1
Thread-2
Thread-3
```

The scheduler determines execution order.

---

### Rule 9 — `join()` is different

```java
worker.join();
```

means:

> Current thread waits for another thread to finish.

```java
lock.wait();
```

means:

> Current thread waits for another thread to change a shared condition.

---

## 8. Common Mistakes

### Mistake 1 — Calling wait() outside synchronized

```java
lock.wait();
```

without owning the lock causes:

```text
IllegalMonitorStateException
```

---

### Mistake 2 — Calling notify() on a different object

```java
lock1.wait();
lock2.notify();
```

These are unrelated locks.

Use the same shared lock.

---

### Mistake 3 — Using if instead of while

Bad:

```java
if (queue.isEmpty()) {
    lock.wait();
}
```

Better:

```java
while (queue.isEmpty()) {
    lock.wait();
}
```

---

### Mistake 4 — Thinking notify() immediately transfers the lock

Incorrect:

```text
notify()
   ↓
waiting thread immediately runs
```

Actual:

```text
notify()
   ↓
waiting thread becomes eligible
   ↓
notifier continues until synchronized block exits
   ↓
waiting thread competes for lock
   ↓
resumes
```

---

### Mistake 5 — Thinking notifyAll() runs all threads simultaneously

It wakes all waiting threads, but they still compete for the same lock.

---

### Mistake 6 — Using wait() for a fixed delay

Don't use:

```java
lock.wait();
```

when you simply want to wait two seconds.

Use:

```java
Thread.sleep(2000);
```

---

### Mistake 7 — Forgetting that wait() can be interrupted

`wait()` throws:

```java
InterruptedException
```

Handle it properly.

A common pattern:

```java
catch (InterruptedException e) {
    Thread.currentThread().interrupt();
}
```

---

## 9. Interview Questions

### 1. What is thread communication?

It is a mechanism that allows threads to coordinate their execution based on changes to shared state.

---

### 2. What are the methods used for thread communication?

```text
wait()
notify()
notifyAll()
```

---

### 3. What does wait() do?

It puts the current thread into `WAITING` and releases the monitor lock until it is notified or otherwise awakened.

---

### 4. Does wait() release the lock?

Yes.

This is one of the most important differences between `wait()` and `sleep()`.

---

### 5. Does sleep() release the lock?

No.

---

### 6. What does notify() do?

It wakes one thread waiting on the same object's monitor.

---

### 7. What does notifyAll() do?

It wakes all threads waiting on the same object's monitor.

---

### 8. Does notify() immediately give the lock to the waiting thread?

No.

The notified thread must wait until the notifier releases the monitor and then compete to reacquire it.

---

### 9. Why must wait(), notify(), and notifyAll() be called inside synchronized?

Because the calling thread must own the object's monitor.

---

### 10. Why should wait() normally be inside a while loop?

Because the condition must be re-checked after the thread wakes.

---

### 11. What happens if wait() is called without synchronization?

```text
IllegalMonitorStateException
```

---

### 12. What is Producer–Consumer?

A concurrency pattern where producers generate data/tasks and consumers process them through a shared buffer or queue.

---

### 13. What happens when the queue is empty?

The consumer waits.

```java
while (queue.isEmpty()) {
    lock.wait();
}
```

---

### 14. What happens when the queue is full?

In a bounded Producer–Consumer design, the producer waits until space becomes available.

---

### 15. Is Producer–Consumer commonly implemented manually using wait/notify in production?

It can be, but modern Java applications generally prefer higher-level concurrency utilities such as `BlockingQueue`.

---

## 10. Coding Practice

### Practice 1 — wait/notify

Create:

```text
WaitNotifyPractice.java
```

Requirements:

- Create a shared lock.
- Worker prints `"Worker waiting"`.
- Worker calls `wait()`.
- Notifier sleeps for 2 seconds.
- Notifier calls `notify()`.
- Worker prints `"Worker resumed"`.

---

### Practice 2 — notifyAll

Create:

```text
NotifyAllPractice.java
```

Requirements:

- Create three worker threads.
- All workers wait on the same lock.
- Create a notifier.
- Call `notifyAll()`.
- Observe that all workers resume.
- Observe that resume order is not guaranteed.

---

### Practice 3 — Producer–Consumer

Create:

```text
ProducerConsumerPractice.java
```

Requirements:

- Create a shared `Queue<String>`.
- Create producer and consumer threads.
- Consumer waits when queue is empty.
- Producer adds orders.
- Producer calls `notifyAll()`.
- Consumer consumes orders using `poll()`.
- Use `while` for condition checking.

Basic pattern:

```java
synchronized (lock) {

    while (queue.isEmpty()) {
        lock.wait();
    }

    String item = queue.poll();
}
```

---

### Production alternative

Learn the same pattern using:

```java
BlockingQueue<String>
```

Example:

```java
BlockingQueue<String> queue =
        new ArrayBlockingQueue<>(10);
```

Producer:

```java
queue.put(order);
```

Consumer:

```java
String order = queue.take();
```

`BlockingQueue` handles the blocking and coordination internally.

---

## 11. 10-Second Cheat Sheet

```text
wait()
→ current thread waits
→ WAITING
→ releases monitor lock

notify()
→ wakes ONE waiting thread

notifyAll()
→ wakes ALL waiting threads

wait/notify/notifyAll
→ must be called while owning the monitor

wait()
→ use while(condition)

sleep()
→ waits for time
→ does NOT release lock

Producer–Consumer
→ Producer creates data
→ Queue stores data
→ Consumer processes data

Queue empty
→ Consumer waits

Queue full
→ Producer waits

Production
→ Prefer BlockingQueue
```

---

## 12. Final Mental Model

```text
                 THREAD COMMUNICATION

                       Shared State
                           │
                           ↓
                     ┌───────────┐
                     │   Lock    │
                     └───────────┘
                           │
             ┌─────────────┴─────────────┐
             ↓                           ↓
         Producer                    Consumer
             │                           │
         produces                    consumes
             │                           │
             └──────────→ Queue ←────────┘
                             
Queue Empty
    ↓
Consumer → wait()

Producer adds data
    ↓
notifyAll()

Consumer wakes
    ↓
re-check condition
    ↓
consume

Queue Full
    ↓
Producer → wait()

Consumer removes data
    ↓
notifyAll()

Producer wakes
    ↓
re-check condition
    ↓
produce

Interview:
wait = wait for condition
notify = wake one
notifyAll = wake all
while = re-check condition
BlockingQueue = production-friendly solution