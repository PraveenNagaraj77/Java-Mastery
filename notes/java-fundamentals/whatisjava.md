# Java — What Is Java?

## 1. What Is Java?

Java is a **high-level, class-based, object-oriented, general-purpose programming language** designed to be portable across different platforms.

Java was originally developed at **Sun Microsystems** and was led by **James Gosling** and his team. Java was first released publicly in 1995.

One of Java's most important characteristics is its **platform-independent bytecode execution model**.

Java source code is normally compiled into **bytecode**, rather than directly into platform-specific machine code.

The basic execution model is:

```text
Java Source Code
       |
       | javac
       v
   Bytecode
   (.class)
       |
       v
      JVM
       |
       v
Native Machine Code
```

The JVM provides the platform-specific layer required to execute the bytecode.

---

# 2. Why Do We Need Java?

Before Java became popular, languages such as C and C++ commonly required programs to be compiled for a specific operating system and hardware architecture.

For example:

```text
C/C++ Source Code
       |
       v
Compiler
       |
       v
Windows Machine Code
```

A different compilation would generally be required for another platform:

```text
C/C++ Source Code
       |
       v
Compiler
       |
       v
Linux Machine Code
```

Java uses a different model:

```text
Java Source Code
       |
       v
Java Compiler
       |
       v
Platform-Independent Bytecode
       |
       +------------+------------+
       |            |            |
       v            v            v
   Windows JVM   Linux JVM   macOS JVM
```

The same Java bytecode can be executed on different operating systems when a compatible JVM is available.

This led to Java's famous principle:

> **Write Once, Run Anywhere (WORA)**

The important technical distinction is:

> **Java bytecode is platform-independent, while the JVM is platform-dependent.**

---

# 3. Major Characteristics of Java

## 3.1 Object-Oriented

Java is primarily an object-oriented programming language.

Programs can be designed using:

* Classes
* Objects
* Encapsulation
* Inheritance
* Polymorphism
* Abstraction

Example:

```java
class Employee {

    String name;
    double salary;

    void work() {
        System.out.println(name + " is working");
    }
}
```

The concepts of OOP will be studied in detail separately.

---

## 3.2 Platform Independent

Java source code is compiled into bytecode.

The bytecode can be executed on different platforms through their respective JVM implementations.

```text
.java
  |
  v
Bytecode
  |
  +----------+----------+
  |          |          |
  v          v          v
Windows     Linux      macOS
 JVM         JVM        JVM
```

Therefore, Java applications can be portable across operating systems.

---

## 3.3 High-Level Language

Java is a high-level programming language because it abstracts many low-level machine details.

Developers generally do not need to manually manage memory using operations such as:

```text
malloc()
free()
```

as they would in C.

Java provides automatic memory management through **Garbage Collection**.

---

## 3.4 Robust

Java provides several features that help developers build reliable applications:

* Strong type checking
* Exception handling
* Automatic memory management
* Array bounds checking
* No direct pointer arithmetic
* Runtime checks

These features reduce several categories of programming errors.

---

## 3.5 Secure

Java provides several security-related mechanisms, including:

* Strong type checking
* Bytecode verification
* Access control
* No direct pointer arithmetic
* Controlled memory access

Modern Java security is much more extensive, but these are important fundamental concepts.

---

## 3.6 Multithreaded

Java provides built-in support for concurrent programming.

Example:

```java
Thread thread = new Thread(() -> {
    System.out.println("Running in another thread");
});

thread.start();
```

Java provides APIs and abstractions for:

* Threads
* Synchronization
* Executors
* Locks
* Atomic operations
* Concurrent collections
* CompletableFuture

Multithreading will be studied separately.

---

## 3.7 Distributed

Java provides APIs and libraries for building applications that communicate over networks.

Examples include:

* Networking APIs
* HTTP clients
* Socket programming
* Enterprise frameworks
* Distributed application frameworks

Java is widely used for backend and enterprise applications.

---

## 3.8 Portable

Java's bytecode-based execution model allows applications to move between platforms when a compatible JVM is available.

The portability comes from the separation between:

```text
Java Program
     |
     v
Bytecode
     |
     v
Platform-Specific JVM
     |
     v
Operating System / Hardware
```

---

## 3.9 Garbage Collected

Java automatically manages dynamically allocated memory.

For example:

```java
Employee employee = new Employee();
```

The `new` operation creates an object.

When an object is no longer reachable from the application, it may eventually become eligible for garbage collection.

The JVM's garbage collector can reclaim memory occupied by eligible objects.

Important:

> Garbage Collection is automatic, but it does not mean memory management does not matter in Java.

Developers still need to understand object references, object lifetimes, memory usage, and potential memory leaks caused by unwanted references.

---

# 4. Is Java 100% Object-Oriented?

## No.

Java is an object-oriented language, but it is **not considered a purely object-oriented language**.

One important reason is that Java supports **primitive data types**.

Java has eight primitive types:

```text
byte
short
int
long
float
double
char
boolean
```

Example:

```java
int age = 26;
```

`age` stores a primitive value.

Java also provides wrapper classes for primitive types:

```text
byte      -> Byte
short     -> Short
int       -> Integer
long      -> Long
float     -> Float
double    -> Double
char      -> Character
boolean   -> Boolean
```

Example:

```java
Integer age = 26;
```

Here, `Integer` is a class and represents an object.

---

# 5. Is Java Compiled or Interpreted?

The best answer is:

> **Java uses both compilation and runtime execution mechanisms including interpretation and Just-In-Time compilation.**

The process is approximately:

```text
.java
  |
  | javac
  v
.class
  |
  v
Bytecode
  |
  v
JVM
  |
  +-------------------+
  |                   |
  v                   v
Interpreter           JIT
  |                   |
  +---------+---------+
            |
            v
    Native Machine Code
```

## Compilation

The Java compiler:

```text
javac
```

converts Java source code:

```text
HelloWorld.java
```

into:

```text
HelloWorld.class
```

The `.class` file contains Java bytecode.

## Runtime Execution

The JVM loads and executes the bytecode.

Modern JVMs use:

* Interpretation
* Just-In-Time compilation

The JIT compiler can compile frequently executed code into native machine code during runtime.

This can significantly improve performance for long-running applications.

---

# 6. Java Program Execution

Consider:

```java
public class HelloWorld {

    public static void main(String[] args) {

        System.out.println("Hello World");
    }
}
```

The file is:

```text
HelloWorld.java
```

## Step 1 — Compilation

Run:

```bash
javac HelloWorld.java
```

The compiler generates:

```text
HelloWorld.class
```

The `.class` file contains bytecode.

---

## Step 2 — Execution

Run:

```bash
java HelloWorld
```

The JVM starts the runtime process.

At a high level, the JVM:

1. Loads the required class
2. Verifies the bytecode
3. Performs linking-related operations
4. Initializes the class
5. Locates the `main()` method
6. Executes the bytecode

The exact JVM lifecycle is more detailed and will be covered in the JVM section.

---

# 7. Java Architecture — Big Picture

Keep this mental model:

```text
                 Java Program
                      |
                      v
                  .java File
                      |
                      v
               Java Compiler
                   javac
                      |
                      v
                  Bytecode
                  .class
                      |
                      v
                     JVM
                      |
          +-----------+-----------+
          |                       |
          v                       v
    Class Loader          Runtime Memory
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
```

This architecture will be studied in much greater depth when we learn:

* JDK
* JRE
* JVM
* Class Loader
* Runtime Data Areas
* Execution Engine
* JIT Compiler
* Garbage Collection

---

# 8. Real-World Example

Consider a Spring Boot backend application.

Example:

```java
@RestController
public class EmployeeController {

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }
}
```

A developer may develop this application on a Windows machine.

The Java application is compiled and packaged for deployment.

The application can then be deployed to a Linux server running a compatible Java runtime.

Conceptually:

```text
Developer Machine
       |
       v
Java Source Code
       |
       v
Compilation
       |
       v
Java Application
       |
       v
Linux Server
       |
       v
Linux JVM
       |
       v
Application Execution
```

The Java source code does not need to be rewritten simply because the application moved from Windows to Linux.

This portability is one of Java's major strengths in enterprise/backend development.

---

# 9. Common Interview Mistakes

## Mistake 1 — "Java is completely platform independent."

This statement is incomplete.

Better:

> **Java bytecode is platform-independent, while the JVM is platform-specific.**

---

## Mistake 2 — "JVM converts Java source code directly into machine code."

Incorrect.

The normal flow is:

```text
.java
  |
  v
javac
  |
  v
Bytecode
  |
  v
JVM
  |
  v
Execution
```

---

## Mistake 3 — "Java is only interpreted."

Not accurate for modern JVMs.

Modern JVMs use both interpretation and JIT compilation.

---

## Mistake 4 — "Java is 100% object-oriented."

Incorrect.

Java supports primitive data types.

---

## Mistake 5 — "JVM and Java are the same thing."

They are not.

Java is the programming language and platform ecosystem.

JVM is the runtime environment that executes Java bytecode.

---

# 10. Important Terms

## Source Code

The code written by the developer.

Example:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello");
    }
}
```

File:

```text
HelloWorld.java
```

---

## Bytecode

The intermediate instruction format generated by the Java compiler.

File:

```text
HelloWorld.class
```

Bytecode is designed to be executed by a JVM.

---

## JVM

Java Virtual Machine.

It provides the runtime environment for executing Java bytecode.

---

## JDK

Java Development Kit.

It provides the tools required to develop Java applications.

We will study JDK in detail in the next topic.

---

## JRE

Historically, Java Runtime Environment referred to the runtime components needed to run Java applications.

The relationship between JDK, JRE, and JVM needs careful treatment because the packaging model changed in modern Java versions.

We will study this in the next topic.

---

# 11. Interview Questions

## Basic

1. What is Java?
2. Why is Java popular?
3. What are the main features of Java?
4. Why is Java platform independent?
5. What is bytecode?
6. What is a `.class` file?
7. What is JVM?
8. Is Java compiled or interpreted?
9. What is JIT?
10. Is Java 100% object-oriented?

## Intermediate

11. Why does Java use bytecode?
12. Why is JVM platform-specific?
13. What happens when you execute `java HelloWorld`?
14. What is the difference between source code, bytecode, and machine code?
15. Why can the same bytecode execute on different operating systems?
16. Why is Java considered robust?
17. How does Java provide portability?
18. How does garbage collection work at a high level?
19. Why doesn't Java support pointer arithmetic?
20. What role does the JVM play in Java's platform independence?

## Advanced Follow-Up Questions

21. What happens between class loading and execution?
22. What is the role of the Class Loader?
23. What are runtime data areas?
24. What is the Execution Engine?
25. What is JIT compilation?
26. Why does the JVM use both interpretation and JIT compilation?
27. What is native machine code?
28. What is the difference between JVM and JDK?
29. What is the difference between JDK, JRE, and JVM?
30. Is Java truly platform independent?

---

# 12. ⭐ Detailed Interview Explanation

## Question: What is Java?

A strong MTS interview answer:

> **Java is a high-level, class-based, object-oriented, general-purpose programming language and platform that is designed to be portable across different operating systems. One of its most important characteristics is its bytecode-based execution model. When we write Java source code in a `.java` file, the Java compiler, `javac`, compiles that source code into platform-independent bytecode, which is stored in a `.class` file. This bytecode is then executed by the Java Virtual Machine, or JVM.**
>
> **The JVM is platform-specific, meaning there are different JVM implementations for different operating systems and hardware architectures. However, the bytecode is designed to be platform-independent. This separation is what enables Java's "Write Once, Run Anywhere" principle. For example, I can develop a Java application on a Windows machine and deploy it to a Linux server without rewriting the Java source code, provided the required Java runtime is available on the server.**
>
> **At runtime, the JVM loads and verifies the required classes and executes the bytecode through its execution engine. Modern JVMs use interpretation as well as Just-In-Time compilation. The JIT compiler can identify frequently executed code and compile it into native machine code, which improves performance for long-running applications.**
>
> **Java also provides automatic memory management through garbage collection, strong type checking, exception handling, multithreading support, and a large standard library. These characteristics make Java particularly suitable for enterprise and backend systems. For example, a Spring Boot application can be developed locally and deployed to a Linux-based production environment while maintaining the same Java application code.**
>
> **So, when I explain Java, I would not define it only as an object-oriented language. I would describe it as a high-level, class-based programming language and platform whose bytecode and JVM architecture provide portability, while the JVM handles runtime execution and optimization.**

---

# 13. ⭐ Important Interview Follow-Up

### Interviewer:

**"You said Java is platform independent. Is the JVM platform independent?"**

### Answer:

> **"No. The JVM itself is platform-dependent. Different operating systems and hardware architectures require different JVM implementations. The Java bytecode is platform-independent, and the JVM provides the platform-specific layer that executes that bytecode on the underlying operating system and hardware. So the correct statement is that Java bytecode is platform-independent, not that the JVM itself is platform-independent."**

---

# 14. Quick Revision

```text
Java
 |
 +-- High-level
 |
 +-- Class-based
 |
 +-- Object-oriented
 |
 +-- General-purpose
 |
 +-- Platform-independent bytecode
 |
 +-- Automatic memory management
 |
 +-- Garbage Collection
 |
 +-- Multithreading
 |
 +-- Exception Handling
 |
 +-- Strong type checking
```

### Execution

```text
.java
  |
  | javac
  v
.class
  |
  v
Bytecode
  |
  v
JVM
  |
  +--> Class Loader
  |
  +--> Runtime Data Areas
  |
  +--> Execution Engine
           |
           +--> Interpreter
           |
           +--> JIT
```

### Most Important Interview Point

```text
Bytecode = Platform Independent
JVM      = Platform Specific
```

---

# 15. Key Takeaways

* Java is a high-level, class-based, object-oriented language.
* Java source code is compiled into bytecode.
* Bytecode is stored in `.class` files.
* The JVM executes Java bytecode.
* Java bytecode is platform-independent.
* JVM implementations are platform-specific.
* Java uses both interpretation and JIT compilation.
* Java is not 100% object-oriented because it has primitive types.
* Java provides automatic memory management through garbage collection.
* Java's portability makes it highly suitable for enterprise and backend applications.
* Spring Boot applications commonly use Java's portability when moving from development environments to production servers.
* Understanding Java execution requires understanding JDK, JRE, JVM, Class Loader, Runtime Data Areas, and Execution Engine.
