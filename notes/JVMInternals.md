# JVM Internals — Interview Cheat Sheet

## 1. What Is JVM?

### What is it?
The Java Virtual Machine (JVM) is a runtime environment that loads, verifies, and executes Java bytecode.

### Why is it needed?
- Executes Java bytecode on supported platforms.
- Manages runtime memory.
- Provides garbage collection.
- Supports runtime execution and JIT compilation.
- Provides class loading and verification.

### How does it work?

```text
Product.java
     |
     v
javac Compiler
     |
     v
Product.class (Bytecode)
     |
     v
Class Loading
     |
     v
Linking and Initialization
     |
     v
Runtime Memory Areas
     |
     v
Interpreter + JIT Compiler
     |
     v
Native Machine Code Execution
```

### JDK vs JRE vs JVM

| Component | Purpose |
|---|---|
| JDK | Development tools, including the Java compiler |
| JRE | Conceptual runtime components needed to run Java applications |
| JVM | Loads and executes Java bytecode |

**Interview point:** Java source code is compiled into bytecode. The JVM executes that bytecode using runtime execution mechanisms such as interpretation and JIT compilation.

**Where to use it:** Explaining Java platform independence, application startup, and the difference between compilation and runtime execution.

---

## 2. JVM Architecture

### What is it?
JVM architecture describes the components responsible for loading classes, managing runtime memory, and executing bytecode.

### Main components

```text
                 Java Application
                        |
                        v
                  Class Loader
                        |
                        v
                 Runtime Data Areas
                        |
                        v
                  Execution Engine
                  /             \
                 v               v
           Interpreter       JIT Compiler
                  \             /
                   v           v
                  Native Execution

          Garbage Collector manages
          reclaimable heap memory.
```

### Runtime Data Areas

| Memory Area | Purpose | Scope |
|---|---|---|
| Heap | Objects and arrays | Shared among threads |
| Java Stack | Method frames and local execution state | Per thread |
| Method Area | Class-level runtime structures | Shared logical area |
| PC Register | Current execution position | Per thread |
| Native Method Stack | Supports native method execution | Generally per thread |

### Heap

- Stores objects and arrays.
- Shared by application threads.
- Managed by garbage collection.
- Can experience `OutOfMemoryError: Java heap space`.

```java
Product product = new Product();
```

The `Product` object is generally allocated on the heap. The local variable `product` holds a reference to it.

**Where to use it:** Understanding object allocation, garbage collection, memory usage, and heap-related errors.

### Java Stack

- Each thread has its own Java stack.
- Contains method frames.
- Frames hold local variables, intermediate computation state, and other method-execution information.
- Excessive recursion can cause `StackOverflowError`.

```java
static void processOrder() {
    int quantity = 5;
    calculateTotal(quantity);
}
```

The local variable `quantity` belongs to the method's execution frame conceptually. JVM optimizations can affect physical storage.

**Where to use it:** Debugging recursion problems and understanding method calls and thread execution.

### Method Area and Metaspace

- The Method Area is a logical runtime area defined by the JVM specification.
- In HotSpot, class metadata is generally stored in Metaspace, which uses native memory.
- Runtime constant pools are associated with classes and interfaces.
- Metaspace exhaustion can cause `OutOfMemoryError: Metaspace`.

**Important:** The Method Area and Metaspace are related concepts, but they are not interchangeable terms.

**Where to use it:** Investigating class metadata usage, class-loader retention, and Metaspace errors.

### PC Register

- Each JVM thread has its own PC register.
- It tracks the current JVM instruction position during normal Java method execution.
- Its value is undefined during native method execution.

**Where to use it:** Understanding thread execution at the JVM level.

### Native Method Stack

Supports execution of native methods, such as methods implemented using JNI.

**Where to use it:** Understanding Java-to-native interactions and native-code troubleshooting.

---

## 3. Class Loading

### What is it?
Class loading makes a class's bytecode available to the JVM so that the class can be used at runtime.

### Class-Loading Lifecycle

```text
Loading
   |
   v
Linking
   |
   +--> Verification
   |
   +--> Preparation
   |
   +--> Resolution
   |
   v
Initialization
```

### 1. Loading

The JVM locates class bytecode and creates its runtime representation.

### 2. Linking

Linking contains three major activities.

**Verification**
- Checks bytecode validity and safety.
- Helps prevent invalid bytecode from being executed.

**Preparation**
- Allocates storage for static fields.
- Assigns their default values.

**Resolution**
- Resolves symbolic references to classes, fields, methods, and other runtime entities.
- Can occur lazily.

### 3. Initialization

Executes explicit static field initializers and static initialization blocks.

```java
class Product {

    static int count = 10;

    static {
        System.out.println("Product initialized");
    }
}
```

During preparation, `count` initially receives its default value of `0`. During initialization, `count = 10` is executed and the static block runs.

### When does class initialization occur?

Typical triggers include:

- Creating an object using `new`.
- Invoking a static method.
- Accessing a non-constant static field.
- Calling `Class.forName()` with initialization enabled.

Reading a compile-time constant may not trigger initialization.

### Class Loaders

Modern Java commonly uses this built-in hierarchy:

```text
Bootstrap ClassLoader
          |
          v
Platform ClassLoader
          |
          v
Application ClassLoader
```

| Class Loader | Purpose |
|---|---|
| Bootstrap | Loads core runtime classes |
| Platform | Loads platform-level classes and modules |
| Application | Loads application classes from the classpath or module path |

### Parent Delegation

A class loader generally asks its parent to load a class before attempting to load it itself.

**Why?** It helps prevent application classes from accidentally replacing core Java classes.

**Where to use it:** Plugin architectures, application servers, dynamic class loading, and class-loading troubleshooting.

---

## 4. Garbage Collection (GC)

### What is it?
Garbage Collection is the JVM process of reclaiming heap memory occupied by objects that are no longer reachable.

### Example

```java
Product product = new Product();

product = null;
```

If no other live reference can reach the object, it becomes eligible for garbage collection.

**Important:** Eligible for collection does not mean collected immediately.

### Reachability and GC Roots

GC roots are starting points used by the JVM to determine whether objects remain reachable.

Examples include:
- References in active thread stacks.
- References held by loaded classes and static fields.
- Active threads.
- Certain JNI and JVM internal references.

An object can remain reachable even when one local variable no longer references it.

### Generational Collection

Traditional generational collectors divide the heap into young and old generations.

```text
New Object
    |
    v
  Eden
    |
    v
Survivor Spaces
    |
    v
Old Generation
```

Objects that survive collections may be promoted to the old generation. The exact memory layout depends on the collector.

### Types of GC

| Type | Meaning |
|---|---|
| Minor GC | Commonly collects the young generation in generational collectors |
| Major GC | Often refers to old-generation collection; terminology varies |
| Full GC | A broader collection involving much or all of the heap, depending on the collector |

### Common Garbage Collectors

| Collector | Main Characteristic |
|---|---|
| Serial GC | Simple, single-threaded collection |
| Parallel GC | Focuses on throughput using parallel collection |
| G1 GC | Region-based, general-purpose collector designed to balance throughput and pause-time goals |
| ZGC | Designed for very low GC pauses |

In common Java 11 HotSpot server-class configurations, G1 is the default collector.

### Important GC Rules

- `System.gc()` is a request, not a guarantee.
- Garbage collection does not replace closing external resources.
- Avoid relying on `finalize()`; it is deprecated for removal.
- Not every garbage collector uses the same heap layout or collection strategy.
- GC timing is controlled by the JVM.

**Where to use it:** Understanding memory pressure, GC pauses, object retention, and Java application performance.

---

## 5. Memory Leaks and OutOfMemoryError

### What is a memory leak?

A memory leak occurs when objects are no longer useful but remain reachable, preventing garbage collection from reclaiming them.

### Common Causes

- Unbounded static collections.
- Caches without eviction policies.
- Listeners or callbacks that are never removed.
- Unbounded queues.
- `ThreadLocal` values retained longer than intended.
- Objects unnecessarily retained by long-lived references.

### Example of a Potential Memory Leak

```java
import java.util.ArrayList;
import java.util.List;

public class MemoryLeakExample {

    private static final List<byte[]> CACHE = new ArrayList<>();

    public static void storeData() {
        CACHE.add(new byte[1024 * 1024]);
    }
}
```

Each call retains approximately 1 MiB of array data through the static list.

If the list continues growing, memory consumption can become excessive because the arrays remain reachable.

### Common OutOfMemoryError Messages

| Error | What to Investigate |
|---|---|
| `Java heap space` | Heap allocation, retained objects, or large allocations |
| `Metaspace` | Class metadata and class-loader retention |
| `unable to create native thread` | Thread creation, OS/process limits, or native memory |
| `Direct buffer memory` | Direct-buffer allocation and memory limits |

A memory leak can cause OOM, but OOM can also occur without a traditional memory leak.

### Heap Dump on OOM

```bash
java -XX:+HeapDumpOnOutOfMemoryError \
     -XX:HeapDumpPath=/tmp \
     -jar app.jar
```

The JVM attempts to create a heap dump when a supported heap-related `OutOfMemoryError` occurs.

### Diagnostic Commands

Find Java processes:

```bash
jps -l
```

Inspect heap information:

```bash
jcmd <pid> GC.heap_info
```

Inspect class histogram:

```bash
jcmd <pid> GC.class_histogram
```

Capture a heap dump:

```bash
jcmd <pid> GC.heap_dump /tmp/app.hprof
```

Capture a thread dump:

```bash
jstack <pid>
```

Replace `<pid>` with the actual Java process ID.

### Heap Dump Analysis

Tools such as Eclipse MAT can help investigate:

- Dominator trees.
- Retained heap.
- Paths to GC roots.
- Large collections.
- Unexpectedly retained object graphs.

### Memory Leak vs. OOM

| Memory Leak | OutOfMemoryError |
|---|---|
| Unneeded objects remain reachable | A memory allocation cannot be satisfied |
| May develop gradually | Can occur with or without a traditional leak |
| Can eventually cause OOM | Requires investigation of the specific error and memory area |

**Where to use it:** Production incidents, memory leak debugging, heap analysis, and performance investigations.

---

## 6. JVM Memory Tuning

### What is it?
JVM memory tuning means configuring heap size, stack size, and garbage collection to meet application performance and reliability requirements.

### Important JVM Options

| Option | Purpose |
|---|---|
| `-Xms` | Initial heap size |
| `-Xmx` | Maximum heap size |
| `-Xss` | Stack size per thread |
| `-XX:+UseG1GC` | Selects G1 GC |
| `-Xlog:gc*` | Enables detailed GC logging |

### Example

```bash
java -Xms256m -Xmx512m -jar app.jar
```

This configures:
- Initial heap size: 256 MiB.
- Maximum heap size: 512 MiB.

`-Xmx` limits the Java heap, not total process memory.

The JVM also uses native memory for:
- Thread stacks.
- Class metadata.
- Compiled code.
- Direct buffers.
- Other runtime structures.

### Enable GC Logs in Java 11

```bash
java -Xlog:gc*:file=gc.log:time,uptime,level,tags \
     -jar app.jar
```

GC logs help investigate collection frequency, memory behavior, and pause times.

### Tuning Workflow

1. Measure heap usage, process memory, GC pauses, and application latency.
2. Identify the bottleneck.
3. Inspect logs and diagnostic evidence.
4. Change one setting or application behavior at a time.
5. Benchmark and verify the result.

**Interview rule:** Never increase heap size or change collectors blindly. Diagnose first, tune second.

**Where to use it:** Spring Boot deployment, production performance tuning, container memory planning, and GC troubleshooting.

---

## 7. Interpreter and JIT Compiler

### What is it?
The JVM can interpret bytecode and compile selected, frequently executed code into native machine code at runtime.

### Interpreter

Executes bytecode instructions at runtime.

### JIT Compiler

JIT means **Just-In-Time compiler**. It compiles selected bytecode into native machine code while the application is running.

### Execution Flow

```text
Java Source
     |
     v
javac Compiler
     |
     v
Bytecode
     |
     v
JVM Runtime
     |
     +--> Interpreter
     |
     +--> JIT Compiler
                |
                v
       Native Machine Code
```

### Interpreter vs. JIT

| Interpreter | JIT Compiler |
|---|---|
| Executes bytecode instructions | Compiles selected bytecode into native code |
| Can begin execution without compiling every method | Compilation introduces some overhead |
| Useful during initial execution | Can optimize frequently executed code |
| Does not require native compilation of every method | Uses runtime information to guide compilation |

### HotSpot and Tiered Compilation

HotSpot is a widely used JVM implementation.

- **C1:** Generally focuses on faster compilation.
- **C2:** Generally performs more aggressive optimizations.
- **Tiered compilation:** Allows execution and compilation to progress through multiple levels.

The JVM chooses code for compilation based on runtime behavior and optimization policies.

### Common JIT Optimizations

**Inlining:** Replaces a method call with the method body when beneficial.

**Dead-Code Elimination:** Removes unnecessary computations.

**Loop Optimizations:** Improves execution of loops.

**Escape Analysis:** Determines whether objects escape a scope, enabling certain optimizations such as scalar replacement.

**Deoptimization:** Stops using optimized code when its assumptions no longer hold.

### Warm-Up

A Java application may initially run more slowly while classes load, code is interpreted, profiling data accumulates, and selected methods are compiled.

Performance can improve after warm-up.

**Where to use it:** Performance benchmarking, long-running backend services, and explaining JVM runtime optimization.

---

## 8. Frequently Asked Interview Questions

### Q1. What is the JVM?

The JVM loads, verifies, and executes Java bytecode. It also manages runtime memory and supports execution across different platforms.

### Q2. Is Java compiled or interpreted?

Both. `javac` compiles source code into bytecode. The JVM interprets bytecode and may JIT-compile selected code into native machine code.

### Q3. What is the difference between heap and stack?

The heap stores objects and arrays. Each thread's Java stack stores method frames and local execution state.

### Q4. What is Metaspace?

In HotSpot, Metaspace stores class metadata in native memory. It is an implementation detail related to the JVM specification's Method Area.

### Q5. What is class loading?

It is the process of making class bytecode available to the JVM. The lifecycle includes loading, linking, and initialization.

### Q6. What are the three parts of linking?

Verification, preparation, and resolution.

### Q7. When does an object become eligible for GC?

When it is no longer reachable from GC roots.

### Q8. Does `System.gc()` force garbage collection?

No. It only requests collection; the JVM may ignore the request.

### Q9. What is the difference between a memory leak and OOM?

A memory leak retains unneeded objects. OOM occurs when a required memory allocation cannot be satisfied. A leak is one possible cause of OOM.

### Q10. What is the difference between `-Xms` and `-Xmx`?

`-Xms` sets the initial heap size. `-Xmx` sets the maximum heap size.

### Q11. Why can Java performance improve after warm-up?

Runtime profiling and JIT compilation can optimize frequently executed methods.

### Q12. How would you investigate heap OOM?

Inspect metrics and GC logs, capture a heap dump safely, analyze retained objects and paths to GC roots, identify the cause, and then fix and retest.

### Q13. What causes StackOverflowError?

Excessive call-stack usage, commonly caused by uncontrolled recursion.

### Q14. Does a larger heap always improve performance?

No. It may reduce collection frequency but increases potential memory consumption and does not fix memory leaks.

### Q15. What is parent delegation?

A class loader generally asks its parent to load a class before attempting to load it itself.

### Q16. What is method inlining?

A JIT optimization that replaces a method call with the method's body when beneficial.

### Q17. What is deoptimization?

The JVM stops using optimized code when assumptions used by that code no longer hold.

### Q18. What is the difference between `OutOfMemoryError` and `StackOverflowError`?

OOM indicates an allocation failure. StackOverflowError commonly indicates excessive call-stack depth.

---

## 9. Common Mistakes

- Saying Java source code executes directly without compilation.
- Claiming every Java method is JIT-compiled.
- Assuming unreachable objects are collected immediately.
- Treating Method Area and HotSpot Metaspace as identical concepts.
- Assuming `System.gc()` guarantees collection.
- Assuming every OOM is caused by a heap memory leak.
- Assuming `-Xmx` limits total process memory.
- Increasing heap size without examining evidence.
- Assuming every garbage collector uses the same heap layout.
- Confusing heap exhaustion with stack overflow.
- Assuming GC replaces closing files and database connections.
- Assuming JIT optimizations are guaranteed for every method.

---

## 10. Coding Practice

### Practice 1: Object Reachability

Create two objects, remove one reference, and explain which object may become eligible for GC.

### Practice 2: Static Initialization

Create a class with a static field and a static initialization block. Observe when initialization happens.

### Practice 3: Stack Overflow

Write a recursive method without a terminating condition. Observe `StackOverflowError` in a controlled local test.

### Practice 4: Memory Retention

Create a static collection that retains objects. Explain why GC cannot reclaim the retained objects.

### Practice 5: JVM Diagnostics

Run a Java program and use `jps`, `jcmd`, and `jstack` to inspect the running process.

---

## 11. Ten-Second Cheat Sheet

| Concept | Remember |
|---|---|
| JVM | Executes bytecode |
| Heap | Objects and arrays |
| Java Stack | Method frames and local execution state |
| Method Area | Logical area for class-level runtime structures |
| Metaspace | HotSpot class metadata in native memory |
| Class Loading | Loading → Linking → Initialization |
| Linking | Verification → Preparation → Resolution |
| GC | Reclaims memory from unreachable objects |
| Memory Leak | Unneeded objects remain reachable |
| `-Xms` | Initial heap size |
| `-Xmx` | Maximum heap size |
| Interpreter | Executes bytecode |
| JIT | Compiles selected bytecode into native code |
| Warm-Up | Runtime profiling and compilation may improve performance |
| Heap Dump | Snapshot for object-retention analysis |
| GC Logs | Help investigate collection behavior |

---

## 12. Final Mental Model

```text
SOURCE CODE
     |
     v
COMPILATION
     |
     v
BYTECODE
     |
     v
CLASS LOADING
     |
     v
LINKING + INITIALIZATION
     |
     v
RUNTIME MEMORY
     |
     +--> Heap
     +--> Java Stacks
     +--> Method Metadata
     +--> Other Runtime Areas
     |
     v
EXECUTION ENGINE
     |
     +--> Interpreter
     +--> JIT Compiler
     |
     v
APPLICATION EXECUTION
     |
     +--> Garbage Collection
     +--> Memory Monitoring
     +--> Runtime Tuning
```

## Final Mental Model — Remember This

- **JVM:** Executes bytecode.
- **Heap:** Stores objects and arrays.
- **Stack:** Holds method frames and local execution state.
- **Class Loading:** Makes classes available at runtime.
- **GC:** Reclaims memory from unreachable objects.
- **Memory Leak:** Unneeded objects remain reachable.
- **Memory Tuning:** Configure and measure JVM behavior.
- **Interpreter:** Executes bytecode.
- **JIT:** Compiles selected bytecode into native code.
- **Troubleshooting:** Measure, inspect evidence, fix, and verify.

**JVM Internals module completed.**