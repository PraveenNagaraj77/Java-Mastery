# JVM Architecture

## 1. What Is JVM Architecture?

JVM stands for **Java Virtual Machine**.

The JVM is the runtime environment responsible for executing Java bytecode.

A simplified JVM architecture can be represented as:

```text
                         JVM
                          |
        +-----------------+-----------------+
        |                                   |
        v                                   v
 Class Loader                         Runtime Data Areas
 Subsystem                                  |
        |                         +---------+---------+
        |                         |                   |
        |                         v                   v
        |                      Heap              Thread Memory
        |                                      (Stack, PC, etc.)
        |
        +-----------------------------+
                                      |
                                      v
                              Execution Engine
                                      |
                         +------------+------------+
                         |                         |
                         v                         v
                    Interpreter                  JIT
                         |                         |
                         +------------+------------+
                                      |
                                      v
                             Native Machine Code
                                      |
                                      v
                                    JNI
                                      |
                                      v
                              Native Libraries
```

The major areas we need to understand are:

1. Class Loader Subsystem
2. Runtime Data Areas
3. Execution Engine
4. JNI and Native Libraries

The Class Loader Subsystem is acknowledged here, but it is covered separately only when required.

---

# 2. Why Do We Need JVM Architecture?

Java source code does not directly execute on the operating system.

The general flow is:

```text
Java Source Code
       |
       v
Java Compiler
       |
       v
Bytecode
       |
       v
JVM
       |
       v
Native Machine Execution
```

The JVM provides the runtime environment required to:

* Load classes
* Manage memory
* Execute bytecode
* Optimize frequently executed code
* Manage threads
* Perform garbage collection
* Interact with native code

Therefore, JVM architecture helps us understand what actually happens internally when a Java program runs.

---

# 3. Major Components of JVM

At a high level:

```text
                 JVM
                  |
      +-----------+-----------+
      |           |           |
      v           v           v
Class Loader   Runtime     Execution
Subsystem      Data Areas   Engine
                  |
                  v
             JVM Memory
```

Additionally:

```text
Java Application
       |
       v
      JNI
       |
       v
Native Libraries
```

---

# 4. Class Loader Subsystem

The Class Loader is responsible for loading class definitions into the JVM when they are required.

For example:

```java
Employee employee = new Employee();
```

The JVM needs the `Employee` class definition.

Conceptually:

```text
Employee.class
      |
      v
Class Loader
      |
      v
JVM Runtime
```

The class-loading lifecycle conceptually involves:

```text
Loading
   |
   v
Linking
   |
   +-- Verification
   |
   +-- Preparation
   |
   +-- Resolution
   |
   v
Initialization
```

The detailed Class Loader architecture is intentionally skipped as a separate topic in this Java Mastery path and can be revisited if needed.

---

# 5. Runtime Data Areas

When the JVM executes a Java application, it needs memory.

These memory regions are called **Runtime Data Areas**.

Important areas include:

```text
Runtime Data Areas
       |
       +-- Heap
       |
       +-- Method Area
       |
       +-- JVM Stack
       |
       +-- PC Register
       |
       +-- Native Method Stack
```

A useful classification is:

```text
              Runtime Data Areas
                      |
          +-----------+-----------+
          |                       |
          v                       v
       Shared                  Per Thread
          |                       |
          v                       v
        Heap                    Stack
     Method Area              PC Register
                              Native Stack
```

The exact implementation can vary between JVMs, but this conceptual distinction is very important.

---

# 6. Heap

The **Heap** is the runtime memory area where objects and arrays are generally allocated.

Example:

```java
Employee employee = new Employee();
```

The object created by:

```java
new Employee()
```

is generally allocated in the heap.

Conceptually:

```text
Stack                     Heap
------                    ------
employee  --------------> Employee Object
```

The variable `employee` refers to the object.

The object itself is managed in heap memory.

---

# 7. Why Does the Heap Exist?

Objects can need to survive beyond a single method invocation.

Example:

```java
public Employee createEmployee() {

    Employee employee = new Employee();

    return employee;
}
```

The method returns a reference to the object.

The method's stack frame can disappear after the method returns, while the object can continue to exist as long as it remains reachable.

Therefore, objects are generally allocated in heap memory.

---

# 8. Heap Is Shared

The heap is generally shared among JVM threads.

Conceptually:

```text
                    Heap
                      |
          +-----------+-----------+
          |           |           |
          v           v           v
       Object A    Object B    Object C
          ^           ^
          |           |
      Thread 1     Thread 2
```

Multiple threads can access objects in the heap.

This is one reason thread safety and synchronization are important in Java.

---

# 9. Method Area

The JVM specification defines a **Method Area**.

It contains per-class or per-interface runtime information such as:

* Class metadata
* Field information
* Method information
* Runtime constant pool
* Method bytecode

For example:

```java
class Employee {

    int id;

    void work() {
        System.out.println("Working");
    }
}
```

The JVM needs runtime information about:

```text
Employee
 |
 +-- Class metadata
 +-- Field information
 +-- Method information
 +-- Method bytecode
 +-- Runtime constant pool
```

---

# 10. Method Area and Metaspace

This is an important interview topic.

The JVM specification defines the **Method Area**, but the specification does not require one particular implementation.

In **HotSpot JVM**:

```text
Java 7 and earlier
       |
       v
   PermGen
```

Starting with Java 8:

```text
Java 8+
       |
       v
   Metaspace
       |
       v
Native Memory
```

Therefore:

> **PermGen was replaced by Metaspace in Java 8 HotSpot.**

Since the job description you're preparing for mentions Java 11, this distinction is relevant.

---

# 11. JVM Stack

Each JVM thread has its own JVM stack.

When a method is called, a **stack frame** is created for that method invocation.

Example:

```java
public static void main(String[] args) {

    int x = 10;

    calculate();
}
```

Conceptually:

```text
Thread Stack
     |
     v
+----------------+
| calculate()    |
+----------------+
| main()         |
+----------------+
```

When `calculate()` is called:

```text
main()
  |
  v
calculate()
```

a new stack frame is created.

When `calculate()` returns, its frame is removed.

---

# 12. Stack Frame

A stack frame contains information required to execute a method.

Conceptually, it contains:

* Local variables
* Operand stack
* Reference to the runtime constant pool
* Return information

Example:

```java
public static int add(int a, int b) {

    int result = a + b;

    return result;
}
```

Simplified stack frame:

```text
add() Stack Frame
-----------------

Local Variables
a = 10
b = 20
result = 30

Operand Stack
...

Return Information
...
```

Each method invocation gets its own frame.

---

# 13. Stack vs Heap

This is one of the most frequently asked Java interview questions.

Consider:

```java
Employee employee = new Employee();
```

A simplified model is:

```text
Stack                     Heap
------                    ------
employee  --------------> Employee Object
```

The local reference variable is associated with the current method's stack frame.

The object is generally allocated in the heap.

### Remember:

```text
Stack → Method execution and stack frames
Heap  → Objects and arrays
```

This is a conceptual model. JVM implementations can apply optimizations that make the physical placement more complicated.

---

# 14. Program Counter Register

The **Program Counter (PC) Register** is associated with each JVM thread.

It keeps track of the current bytecode instruction being executed by that thread.

Conceptually:

```text
Thread
  |
  +-- PC Register
  |
  +-- JVM Stack
```

Suppose bytecode instructions are:

```text
Instruction 1
Instruction 2
Instruction 3
Instruction 4
```

The PC helps identify the current execution position.

Because every thread may execute different code at the same time, each thread needs its own PC register.

---

# 15. Native Method Stack

Java can interact with native code.

Native code may be implemented using languages such as:

* C
* C++

The Native Method Stack supports execution of native methods.

Conceptually:

```text
Java Code
    |
    v
   JNI
    |
    v
Native Code
    |
    v
Operating System
```

---

# 16. Execution Engine

Once the required classes are loaded and runtime memory is available, the JVM needs to execute the bytecode.

The **Execution Engine** performs this task.

Conceptually:

```text
              Execution Engine
                     |
          +----------+----------+
          |                     |
          v                     v
     Interpreter               JIT
          |                     |
          +----------+----------+
                     |
                     v
            Native Machine Code
```

---

# 17. Interpreter

The interpreter reads and executes bytecode instructions.

Conceptually:

```text
Bytecode
   |
   v
Interpreter
   |
   v
Execution
```

One advantage is that code can begin executing without requiring all code to be compiled into native machine code beforehand.

However, repeatedly interpreting frequently executed code can be less efficient than executing optimized native code.

This is where JIT compilation becomes important.

---

# 18. JIT Compiler

JIT stands for:

> **Just-In-Time Compiler**

The JVM can identify frequently executed code, often referred to as **hot code**.

Example:

```java
for (int i = 0; i < 1_000_000; i++) {
    calculate();
}
```

The method:

```text
calculate()
```

may be executed many times.

The JVM can identify this frequently executed code and compile it into native machine code.

Conceptually:

```text
Hot Code
   |
   v
JIT Compiler
   |
   v
Native Machine Code
   |
   v
Optimized Execution
```

---

# 19. Why Does JVM Use Both Interpreter and JIT?

This is an important interview question.

If the JVM immediately compiled every piece of code:

```text
Entire Application
       |
       v
JIT Compilation
       |
       v
Execution
```

startup could become slower because compilation takes time.

Instead, the JVM can begin execution using interpretation and optimize frequently executed code later.

Conceptually:

```text
Application Starts
       |
       v
Interpreter
       |
       v
Code Executes
       |
       v
JVM Detects Hot Code
       |
       v
JIT Compiler
       |
       v
Optimized Native Code
       |
       v
Faster Execution
```

This provides a balance between:

* Startup performance
* Runtime performance

---

# 20. Garbage Collection

Java provides automatic memory management.

Consider:

```java
Employee employee = new Employee();
```

Later:

```java
employee = null;
```

If there are no other reachable references to the object, it may become eligible for garbage collection.

Conceptually:

```text
Heap

Employee A  ← reachable

Employee B  ← unreachable

Employee C  ← reachable

        |
        v
Garbage Collector
        |
        v
Memory can eventually be reclaimed
```

Important:

> Becoming eligible for garbage collection does not mean that the object is immediately removed.

Garbage collection is controlled by the JVM's garbage-collection mechanisms.

---

# 21. JNI

JNI stands for:

> **Java Native Interface**

JNI provides a mechanism for Java code to interact with native code.

Conceptually:

```text
Java Application
      |
      v
     JNI
      |
      v
Native Library
      |
      v
Operating System
```

Native libraries can provide functionality that is implemented outside the Java language/runtime.

---

# 22. Native Libraries

Native libraries contain platform-specific compiled code.

They may be written using:

```text
C
C++
```

The JVM can interact with these libraries through native interfaces such as JNI.

This is also one reason JVM implementations themselves need to be platform-specific.

---

# 23. Complete JVM Architecture

Combining the major concepts:

```text
                         JVM
                          |
         +----------------+----------------+
         |                                 |
         v                                 v
  Class Loader                    Runtime Data Areas
  Subsystem                              |
                                  +------+------+
                                  |             |
                                  v             v
                               Shared        Per Thread
                                  |             |
                                  v        +----+----+
                                Heap       |    |    |
                            Method Area  Stack  PC  Native
                                                   Stack
                                  |
                                  v
                          Execution Engine
                                  |
                         +--------+--------+
                         |                 |
                         v                 v
                    Interpreter          JIT
                         |                 |
                         +--------+--------+
                                  |
                                  v
                         Native Machine Code
                                  |
                                  v
                                 JNI
                                  |
                                  v
                          Native Libraries
                                  |
                                  v
                         Operating System
```

---

# 24. Real-World Example

Consider a Spring Boot backend application.

Suppose multiple users send requests simultaneously:

```text
Client 1 ──→ Request 1
Client 2 ──→ Request 2
Client 3 ──→ Request 3
Client 4 ──→ Request 4
```

The application may process these requests using multiple threads:

```text
JVM
 |
 +-- Thread 1
 |
 +-- Thread 2
 |
 +-- Thread 3
 |
 +-- Thread 4
```

Each thread has its own:

```text
Stack
PC Register
Native Method Stack
```

The threads can share:

```text
Heap
Method Area / class-related runtime information
```

Suppose a request creates:

```java
Employee employee = new Employee();
```

Conceptually:

```text
Thread Stack
     |
     | employee reference
     |
     v
Heap
     |
     v
Employee Object
```

The JVM executes the application's bytecode using the Execution Engine.

Frequently executed code may be JIT-compiled.

Objects that become unreachable may eventually have their memory reclaimed by garbage collection.

This is how JVM architecture directly relates to real backend applications.

---

# 25. Common Mistakes

## Mistake 1

### "Everything in Java is stored in the stack."

Incorrect.

Objects and arrays are generally allocated in the heap.

---

## Mistake 2

### "Heap belongs to one thread."

Incorrect.

The heap is generally shared between JVM threads.

---

## Mistake 3

### "Stack is shared by all threads."

Incorrect.

Each JVM thread has its own JVM stack.

---

## Mistake 4

### "Garbage Collector immediately deletes unreachable objects."

Incorrect.

An unreachable object becomes eligible for garbage collection. Actual reclamation occurs according to the JVM's garbage-collection behavior.

---

## Mistake 5

### "JIT compiles the entire application before it starts."

Incorrect.

JIT compilation happens during runtime and focuses on code that benefits from compilation and optimization.

---

## Mistake 6

### "Method Area is the same thing as PermGen."

Outdated.

PermGen was used by older HotSpot JVMs.

Java 8+ HotSpot uses Metaspace for class metadata.

---

## Mistake 7

### "JVM memory is exactly the same in every JVM implementation."

Incorrect.

The JVM Specification defines required runtime concepts, but implementation details can differ between JVMs.

---

# 26. Interview Questions

## Basic

1. What is JVM architecture?
2. What are the major components of JVM?
3. What is Heap?
4. What is Stack?
5. What is Method Area?
6. What is PC Register?
7. What is Native Method Stack?
8. What is Execution Engine?
9. What is Interpreter?
10. What is JIT?

## Intermediate

11. What happens when an object is created?
12. Where are objects generally stored?
13. Where are arrays generally stored?
14. Is heap shared between threads?
15. Is JVM stack shared between threads?
16. What is a stack frame?
17. What does a stack frame contain?
18. What is the difference between stack and heap?
19. Why does every thread have its own stack?
20. Why does every thread have its own PC register?

## Advanced

21. What is hot code?
22. How does JIT improve performance?
23. Why does JVM use both interpretation and JIT compilation?
24. What is Metaspace?
25. Why was PermGen removed?
26. What is JNI?
27. Why does Java need native libraries?
28. What causes StackOverflowError?
29. What causes OutOfMemoryError?
30. When does an object become eligible for garbage collection?
31. Is garbage collection immediate?
32. What happens when multiple threads access the same heap object?
33. How does JVM architecture support multithreading?

---

# 27. ⭐ Detailed MTS Interview Explanation

## Question: Explain JVM Architecture.

A strong interview answer:

> **"JVM stands for Java Virtual Machine and it is responsible for executing Java bytecode. At a high level, JVM architecture consists of the Class Loader Subsystem, Runtime Data Areas, Execution Engine, and mechanisms for interacting with native code through JNI."**
>
> **"The Class Loader loads the required class definitions into the JVM. Once the classes are available, the JVM uses Runtime Data Areas to provide the memory required for execution. Important runtime areas include the heap, method area, JVM stacks, PC registers, and native method stacks."**
>
> **"The heap is generally shared among JVM threads and is primarily used for objects and arrays. Each JVM thread has its own stack, and every method invocation creates a stack frame. The frame contains execution-related information such as local variables and the operand stack. Each thread also has its own PC register, which tracks its current bytecode execution position."**
>
> **"The Execution Engine is responsible for executing bytecode. The JVM can interpret bytecode and can also use Just-In-Time compilation. Frequently executed or hot code can be compiled into native machine code and optimized during runtime. This allows the JVM to balance startup time with long-term application performance."**
>
> **"The JVM also provides automatic memory management through garbage collection. Objects that are no longer reachable can become eligible for garbage collection, allowing their memory to eventually be reclaimed."**
>
> **"Finally, Java can interact with native code through JNI and native libraries when required."**
>
> **"So, in summary, the Class Loader loads classes, Runtime Data Areas provide memory for execution, the Execution Engine executes and optimizes bytecode, garbage collection manages dynamically allocated memory, and JNI provides interaction with native code. Together these components form the runtime architecture of the JVM."**

---

# 28. ⭐ Stack vs Heap — Interview Explanation

If the interviewer asks:

### "What is the difference between Stack and Heap?"

Answer:

> **"The JVM stack is thread-specific and is primarily associated with method execution. Every method invocation creates a stack frame containing information such as local variables and the operand stack. When the method returns, its frame is removed. The heap, on the other hand, is generally shared among JVM threads and is primarily used for dynamically allocated objects and arrays. For example, in `Employee employee = new Employee()`, the Employee object is generally allocated in the heap, while the local reference variable is associated with the current method's stack frame. The heap is also managed by garbage collection, while stack frames are created and removed as methods are invoked and return."**

---

# 29. ⭐ Object Creation Mental Model

Consider:

```java
Employee employee = new Employee();
```

Think through the process:

```text
1. Employee class is required
          |
          v
2. Class Loader makes class available
          |
          v
3. JVM allocates memory for object
          |
          v
4. Object is created in heap
          |
          v
5. Constructor executes
          |
          v
6. Reference is assigned to employee
          |
          v
7. Method continues execution
```

This is a simplified conceptual model.

We will study object creation in much greater detail when we reach:

> **Classes, Objects and Constructors**

---

# 30. ⭐ StackOverflowError vs OutOfMemoryError

## StackOverflowError

Commonly occurs when a thread's stack cannot accommodate additional stack frames.

Example:

```java
static void test() {
    test();
}
```

The method keeps calling itself:

```text
test()
  ↓
test()
  ↓
test()
  ↓
test()
  ↓
...
```

Eventually:

```text
StackOverflowError
```

---

## OutOfMemoryError

Occurs when the JVM cannot satisfy a memory allocation request.

For example:

```java
List<byte[]> list = new ArrayList<>();

while (true) {
    list.add(new byte[1024 * 1024]);
}
```

The program continuously allocates memory.

Eventually the JVM may be unable to allocate more memory and throw an:

```text
OutOfMemoryError
```

---

# 31. Quick Revision

Remember the JVM architecture:

```text
                         JVM
                          |
       +------------------+------------------+
       |                                     |
       v                                     v
 Class Loader                         Runtime Data Areas
                                           |
                              +------------+------------+
                              |                         |
                              v                         v
                           Shared                    Per Thread
                              |                         |
                           Heap                 Stack / PC / Native
                        Method Area
                              |
                              v
                       Execution Engine
                              |
                       +------+------+
                       |             |
                       v             v
                  Interpreter        JIT
                       |             |
                       +------+------+
                              |
                              v
                     Native Execution
                              |
                              v
                             JNI
                              |
                              v
                     Native Libraries
```

---

# 32. Most Important Mental Model

When you see:

```java
Employee employee = new Employee();
```

think:

```text
Class Loader
     ↓
Employee class
     ↓
Heap
     ↓
Employee object
     ↓
Stack frame
     ↓
employee reference
     ↓
Constructor execution
     ↓
Execution Engine
     ↓
Bytecode execution
     ↓
Eventually Garbage Collection
     ↓
Memory reclaimed
```

---

# 33. Key Takeaways

* JVM stands for Java Virtual Machine.
* JVM executes Java bytecode.
* JVM architecture consists of several runtime components.
* The Class Loader loads required class definitions.
* Runtime Data Areas provide memory required for execution.
* The heap is generally shared between JVM threads.
* Objects and arrays are generally allocated in the heap.
* Each JVM thread has its own JVM stack.
* Each method invocation creates a stack frame.
* Stack frames contain method execution information.
* Each JVM thread has its own PC register.
* Native Method Stack supports native method execution.
* The Method Area stores class-related runtime information.
* In HotSpot Java 8+, Metaspace replaced PermGen for class metadata.
* The Execution Engine executes bytecode.
* The interpreter executes bytecode directly.
* JIT compiles frequently executed code into native machine code.
* Hot code can benefit from runtime optimization.
* Java provides automatic memory management through garbage collection.
* An unreachable object becomes eligible for garbage collection but is not necessarily collected immediately.
* JNI allows Java applications to interact with native code.
* `StackOverflowError` is commonly associated with exhausting a thread's stack.
* `OutOfMemoryError` occurs when the JVM cannot satisfy a memory allocation request.
* JVM implementation details can vary, but the conceptual runtime architecture remains fundamental.
