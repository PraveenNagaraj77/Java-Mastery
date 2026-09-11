# Exception Handling in Java

## 1. What is Exception Handling?

An **exception** is an unexpected event that occurs during the execution of a program and interrupts the normal flow of execution.

For example:

```java
int a = 10;
int b = 0;

int result = a / b;
```

This causes:

```text
ArithmeticException
```

because division by zero is not allowed.

Without exception handling, the program may terminate abnormally.

Java provides **Exception Handling** to detect, handle, and recover from exceptional situations.

The main keywords used for exception handling are:

```text
try
catch
finally
throw
throws
```

Example:

```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero");
}
```

Output:

```text
Cannot divide by zero
```

Instead of allowing the application to terminate unexpectedly, we handle the problem gracefully.

---

# 2. Why Do We Need Exception Handling?

Real-world applications can encounter many unexpected situations.

Examples:

```text
Invalid input
File not found
Database failure
Network failure
Invalid state
Insufficient balance
User not found
Unauthorized access
Invalid configuration
```

For example, in a banking application:

```java
double balance = 5000;
double amount = 7000;
```

The application should not simply fail.

Instead, it should inform the user:

```text
Insufficient balance
```

Similarly, a file application may encounter:

```text
FileNotFoundException
```

A database application may encounter:

```text
SQLException
```

A user application may encounter:

```text
NullPointerException
```

Exception handling allows us to separate:

```text
Normal Business Logic
        +
Error Handling Logic
```

This makes applications:

- More reliable
- Easier to maintain
- Easier to debug
- More user-friendly
- Less likely to terminate unexpectedly

---

# 3. Exception Handling Keywords

Java provides five major keywords for exception handling.

| Keyword | Purpose |
|---|---|
| `try` | Contains code that may cause an exception |
| `catch` | Handles an exception |
| `finally` | Executes cleanup code |
| `throw` | Explicitly throws an exception |
| `throws` | Declares that a method may throw exceptions |

Basic structure:

```java
try {
    // risky code
} catch (Exception e) {
    // handle exception
} finally {
    // cleanup
}
```

---

# 4. How Does Exception Handling Work Internally?

Consider:

```java
try {
    int result = 10 / 0;
    System.out.println(result);
} catch (ArithmeticException e) {
    System.out.println("Arithmetic error");
}
```

Execution happens conceptually like this:

```text
Program starts
     |
     ↓
try block
     |
     ↓
10 / 0
     |
     ↓
ArithmeticException created
     |
     ↓
JVM searches for matching catch
     |
     ↓
catch (ArithmeticException e)
     |
     ↓
Exception handled
     |
     ↓
Program continues
```

When an exception occurs:

```text
Normal execution stops
```

at the statement where the exception occurred.

Example:

```java
try {
    System.out.println("A");

    int result = 10 / 0;

    System.out.println("B");
} catch (ArithmeticException e) {
    System.out.println("C");
}
```

Output:

```text
A
C
```

`B` is never executed.

---

# 5. Exception Hierarchy

Java exceptions are part of the `Throwable` hierarchy.

Conceptually:

```text
                         Throwable
                            |
               ┌────────────┴────────────┐
               ↓                         ↓
             Error                    Exception
                                         |
                              ┌──────────┴──────────┐
                              ↓                     ↓
                     RuntimeException       Checked Exceptions
                              |
                       Unchecked Exceptions
```

More detailed:

```text
Throwable
│
├── Error
│   ├── OutOfMemoryError
│   └── StackOverflowError
│
└── Exception
    │
    ├── RuntimeException
    │   ├── NullPointerException
    │   ├── ArithmeticException
    │   ├── NumberFormatException
    │   ├── ArrayIndexOutOfBoundsException
    │   └── IllegalArgumentException
    │
    ├── IOException
    ├── SQLException
    ├── FileNotFoundException
    └── ClassNotFoundException
```

---

# 6. Error vs Exception

Both `Error` and `Exception` extend `Throwable`.

However, they represent different types of problems.

## Error

Errors generally represent serious problems related to the JVM or runtime environment.

Examples:

```text
OutOfMemoryError
StackOverflowError
```

Application code generally should not try to recover from these.

---

## Exception

Exceptions generally represent conditions that an application may reasonably handle.

Examples:

```text
IOException
SQLException
NullPointerException
ArithmeticException
```

Example:

```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Invalid arithmetic operation");
}
```

---

# 7. Checked Exceptions

A **checked exception** is an exception that the Java compiler requires us to either:

1. Handle using `try-catch`
2. Declare using `throws`

Examples:

```text
IOException
SQLException
FileNotFoundException
ClassNotFoundException
```

Example:

```java
import java.io.FileReader;

public class Example {

    static void readFile() throws Exception {
        FileReader reader = new FileReader("data.txt");
    }
}
```

The compiler requires us to deal with the checked exception.

---

# 8. Unchecked Exceptions

Unchecked exceptions are exceptions that the compiler does not force us to handle or declare.

They are generally subclasses of:

```java
RuntimeException
```

Examples:

```text
NullPointerException
ArithmeticException
NumberFormatException
IllegalArgumentException
ArrayIndexOutOfBoundsException
```

Example:

```java
int result = 10 / 0;
```

The compiler allows this code to compile.

The exception occurs during runtime.

---

# 9. Checked vs Unchecked Exceptions

| Checked Exception | Unchecked Exception |
|---|---|
| Compiler checks handling/declaring | Compiler does not force handling |
| Usually subclasses of `Exception` excluding `RuntimeException` | Subclasses of `RuntimeException` |
| Must be handled or declared | Handling is optional |
| Often external/recoverable conditions | Often programming or validation errors |
| `IOException` | `NullPointerException` |
| `SQLException` | `ArithmeticException` |
| `FileNotFoundException` | `NumberFormatException` |

### Important Interview Wording

Do not say:

> Checked exceptions happen at compile time.

That is inaccurate.

Better answer:

> Checked exceptions are checked by the compiler at compile time to ensure that they are either handled or declared, while unchecked exceptions are not subject to this compiler requirement and generally occur during runtime.

---

# 10. try-catch

The most basic exception handling structure is:

```java
try {
    // risky code
} catch (Exception e) {
    // handling code
}
```

Example:

```java
public class TryCatchExample {

    public static void main(String[] args) {

        try {
            int result = 10 / 0;
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero");
        }

        System.out.println("Program continues");
    }
}
```

Output:

```text
Cannot divide by zero
Program continues
```

---

# 11. Why Do We Put Risky Code Inside try?

Only the code inside the `try` block is protected by its corresponding `catch`.

Incorrect:

```java
public class Example {

    public static void main(String[] args) {

        int result = 10 / 0;

        try {
            System.out.println("Hello");
        } catch (ArithmeticException e) {
            System.out.println("Handled");
        }
    }
}
```

The exception occurs before entering the `try`.

Therefore, the `catch` cannot handle it.

Correct:

```java
public class Example {

    public static void main(String[] args) {

        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Handled");
        }
    }
}
```

---

# 12. Multiple catch Blocks

A single `try` block can have multiple `catch` blocks.

Example:

```java
public class MultipleCatchesExample {

    public static void main(String[] args) {

        int a = 20;
        int b = 0;
        String name = null;

        try {

            int result = a / b;

            System.out.println(name.length());

        } catch (ArithmeticException e) {

            System.out.println("Arithmetic Exception");

        } catch (NullPointerException e) {

            System.out.println("Null Pointer Exception");

        } catch (Exception e) {

            System.out.println("Some other exception");
        }
    }
}
```

Output:

```text
Arithmetic Exception
```

Why?

Because:

```java
a / b
```

causes `ArithmeticException`.

The next statement:

```java
name.length();
```

is never executed.

---

# 13. Order of Multiple catch Blocks

The specific exception should come before the general exception.

Correct:

```java
try {

} catch (ArithmeticException e) {

} catch (Exception e) {

}
```

Incorrect:

```java
try {

} catch (Exception e) {

} catch (ArithmeticException e) {

}
```

Why?

Because:

```text
ArithmeticException
        ↓
RuntimeException
        ↓
Exception
        ↓
Throwable
```

`Exception` can already catch `ArithmeticException`.

Therefore, the second catch becomes unreachable.

---

# 14. Important Interview Trap: Unreachable catch

Consider:

```java
try {
    int result = 10 / 0;
} catch (Exception e) {
    System.out.println("Exception");
} catch (ArithmeticException e) {
    System.out.println("Arithmetic");
}
```

This code does **not compile**.

Why?

Because:

```text
ArithmeticException
        ↓
RuntimeException
        ↓
Exception
```

Therefore:

```java
catch (Exception e)
```

already catches `ArithmeticException`.

The later catch block is unreachable.

Correct:

```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Arithmetic");
} catch (Exception e) {
    System.out.println("Exception");
}
```

---

# 15. finally

The `finally` block is used for cleanup operations.

Syntax:

```java
try {
    // risky code
} catch (Exception e) {
    // handling
} finally {
    // cleanup
}
```

Example:

```java
try {
    int result = 10 / 2;
    System.out.println(result);
} catch (ArithmeticException e) {
    System.out.println("Error");
} finally {
    System.out.println("Finally executed");
}
```

Output:

```text
5
Finally executed
```

If an exception occurs:

```java
try {
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Exception handled");
} finally {
    System.out.println("Finally executed");
}
```

Output:

```text
Exception handled
Finally executed
```

---

# 16. Why Do We Need finally?

`finally` is commonly used for cleanup operations such as:

```text
Closing files
Closing database resources
Releasing connections
Releasing locks
Cleaning temporary resources
```

Example:

```java
FileReader reader = null;

try {
    reader = new FileReader("data.txt");
} catch (IOException e) {
    System.out.println("File error");
} finally {

    if (reader != null) {
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("Unable to close file");
        }
    }
}
```

Modern Java generally prefers **try-with-resources** for resources that implement `AutoCloseable`.

---

# 17. finally Execution Flow

### No exception

```text
try
 ↓
finally
 ↓
continue
```

### Exception handled

```text
try
 ↓
catch
 ↓
finally
 ↓
continue
```

### Exception not handled

```text
try
 ↓
finally
 ↓
exception propagates
```

Important:

> `finally` normally executes whether an exception occurs or not.

---

# 18. throw

The `throw` keyword is used to explicitly throw an exception.

Syntax:

```java
throw new ExceptionType("message");
```

Example:

```java
throw new IllegalArgumentException("Invalid age");
```

Example:

```java
public class ThrowExample {

    static void checkAge(int age) {

        if (age < 18) {
            throw new IllegalArgumentException(
                    "Age must be 18 or above"
            );
        }

        System.out.println("Eligible");
    }

    public static void main(String[] args) {

        try {
            checkAge(17);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
```

Output:

```text
Age must be 18 or above
```

---

# 19. Why Use throw?

`throw` is useful when we want to enforce business rules or validation.

Examples:

```text
Age must be 18+
Balance must be sufficient
Username cannot be empty
Product must be available
Order cannot be cancelled after shipping
```

Example:

```java
if (amount > balance) {
    throw new IllegalArgumentException("Insufficient balance");
}
```

This makes business rules explicit.

---

# 20. throws

The `throws` keyword is used in a method declaration to indicate that the method may throw an exception.

Syntax:

```java
returnType methodName() throws ExceptionType {
}
```

Example:

```java
static void readFile() throws IOException {
    FileReader reader = new FileReader("data.txt");
}
```

Here:

```java
throws IOException
```

means:

> This method may throw an IOException, and the caller is responsible for handling or further declaring it.

---

# 21. throw vs throws

| `throw` | `throws` |
|---|---|
| Used to actually throw an exception | Used to declare possible exceptions |
| Used inside method body | Used in method declaration |
| Throws one exception object at a time | Can declare multiple exception types |
| `throw new IOException()` | `throws IOException` |

Example:

```java
throw new IllegalArgumentException("Invalid age");
```

vs:

```java
static void readFile() throws IOException {
}
```

### Interview Memory Trick

```text
throw  → DO IT
throws → DECLARE IT
```

---

# 22. throw Example

```java
public class ThrowExample {

    static void withdraw(double balance, double amount) {

        if (amount > balance) {
            throw new IllegalArgumentException(
                    "Insufficient balance"
            );
        }

        System.out.println("Withdrawal successful");
    }

    public static void main(String[] args) {

        try {
            withdraw(5000, 7000);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
```

Output:

```text
Insufficient balance
```

This is a good example of using exceptions for business validation.

---

# 23. throws Example

```java
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ThrowsExample {

    static void readFile() throws FileNotFoundException {

        FileReader reader = new FileReader("data.txt");
    }

    public static void main(String[] args) {

        try {
            readFile();
        } catch (FileNotFoundException e) {

            System.out.println(e.getMessage());
            System.out.println("File could not be opened");
        }
    }
}
```

Here:

```java
readFile()
```

may throw:

```text
FileNotFoundException
```

The method declares it:

```java
throws FileNotFoundException
```

The caller handles it:

```java
catch (FileNotFoundException e)
```

---

# 24. Can throws Be Used with Unchecked Exceptions?

Yes.

Example:

```java
static void validate(int age)
        throws IllegalArgumentException {

    if (age < 18) {
        throw new IllegalArgumentException("Invalid age");
    }
}
```

However, declaring unchecked exceptions using `throws` is usually optional.

For example:

```java
static void validate(int age) {
    if (age < 18) {
        throw new IllegalArgumentException("Invalid age");
    }
}
```

is also valid.

---

# 25. Exception Propagation

Exception propagation means an exception moves upward through the method call stack until a matching handler is found.

Consider:

```java
public class ExceptionPropagationExample {

    static void methodC() {

        int result = 10 / 0;
    }

    static void methodB() {

        methodC();
    }

    static void methodA() {

        methodB();
    }

    public static void main(String[] args) {

        try {
            methodA();
        } catch (ArithmeticException e) {
            System.out.println("Exception handled in main");
        }
    }
}
```

Flow:

```text
main()
  ↓
methodA()
  ↓
methodB()
  ↓
methodC()
  ↓
ArithmeticException
  ↓
methodB() has no handler
  ↓
methodA() has no handler
  ↓
main() has matching handler
  ↓
Exception handled
```

Output:

```text
Exception handled in main
```

---

# 26. Exception Propagation Mental Model

Think of method calls as a stack:

```text
main
 ↓
methodA
 ↓
methodB
 ↓
methodC
```

If `methodC()` throws an exception and does not handle it:

```text
methodC
   ↓
methodB
   ↓
methodA
   ↓
main
```

The JVM searches upward until it finds a matching handler.

---

# 27. Exception Propagation with finally

Consider:

```java
public class Example {

    static void methodC() {

        try {
            int result = 10 / 0;
        } finally {
            System.out.println("methodC finally");
        }
    }

    public static void main(String[] args) {

        try {
            methodC();
        } catch (ArithmeticException e) {
            System.out.println("Handled in main");
        }
    }
}
```

Output:

```text
methodC finally
Handled in main
```

The `finally` block executes before the exception continues propagating.

---

# 28. Multi-Catch

Java allows multiple exception types to be handled using a single catch block.

Syntax:

```java
catch (ExceptionType1 | ExceptionType2 e) {
}
```

Example:

```java
public class MultiCatchExample {

    public static void main(String[] args) {

        try {

            String str = "100";

            int number = Integer.parseInt(str);

            int result = number / 0;

            System.out.println(result);

        } catch (NumberFormatException | ArithmeticException e) {

            System.out.println("Invalid input");
        }
    }
}
```

Output:

```text
Invalid input
```

---

# 29. Important Multi-Catch Rule

The exception types in a multi-catch cannot have a parent-child relationship.

Invalid:

```java
catch (Exception | ArithmeticException e) {
}
```

Why?

Because:

```text
ArithmeticException is already an Exception
```

Therefore, the second type is redundant.

Correct:

```java
catch (NumberFormatException | ArithmeticException e) {
}
```

because these are sibling exception types.

---

# 30. Rethrowing an Exception

Rethrowing means catching an exception and throwing it again.

Example:

```java
public class RethrowExample {

    public static void main(String[] args) {

        try {

            int result = 10 / 0;

        } catch (ArithmeticException e) {

            System.out.println("Invalid arithmetic operation");

            throw e;
        }
    }
}
```

Output:

```text
Invalid arithmetic operation
```

Then the exception continues upward because it was rethrown.

---

# 31. Why Rethrow Exceptions?

Rethrowing is useful when:

- We want to log an exception
- We want to perform some local handling
- We want a higher layer to make the final decision
- We want to preserve the original exception

Example architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

A lower layer may log or add context and then rethrow the exception.

---

# 32. Exception Chaining

Exception chaining means one exception is associated with another exception as its cause.

Example:

```java
public class ExceptionChainingExample {

    public static void main(String[] args) {

        try {

            throw new IllegalArgumentException("Invalid Age");

        } catch (IllegalArgumentException e) {

            RuntimeException newException =
                    new RuntimeException(
                            "Validation Failed",
                            e
                    );

            System.out.println(newException.getMessage());

            System.out.println(
                    newException.getCause().getMessage()
            );
        }
    }
}
```

Output:

```text
Validation Failed
Invalid Age
```

The relationship is:

```text
RuntimeException
       |
       ↓
cause
       |
       ↓
IllegalArgumentException
```

---

# 33. Why Exception Chaining?

Exception chaining is useful when we want to add higher-level context while preserving the original cause.

Example:

```text
DatabaseException
       |
       ↓
SQLException
```

The application may say:

```text
Failed to load user
```

while preserving the original database exception as the cause.

This makes debugging easier.

---

# 34. Custom Exceptions

Java allows us to create our own exception classes.

Custom exceptions are useful when built-in exceptions do not clearly represent a business problem.

Examples:

```text
InsufficientBalanceException
InvalidAgeException
UserNotFoundException
ProductOutOfStockException
OrderAlreadyCancelledException
```

---

# 35. Custom Unchecked Exception

To create a custom unchecked exception:

```java
class InsufficientBalanceException
        extends RuntimeException {
}
```

Usually we provide a constructor:

```java
public class InsufficientBalanceException
        extends RuntimeException {

    public InsufficientBalanceException(String message) {
        super(message);
    }
}
```

---

# 36. Custom Exception Example

```java
public class InsufficientBalanceException
        extends RuntimeException {

    public InsufficientBalanceException(String message) {
        super(message);
    }
}
```

Use it:

```java
public class CustomExceptionExample {

    static void withdraw(double balance, double amount) {

        if (amount > balance) {

            throw new InsufficientBalanceException(
                    "Insufficient balance"
            );
        }

        System.out.println("Withdrawal successful");
    }

    public static void main(String[] args) {

        try {

            withdraw(5000, 7000);

        } catch (InsufficientBalanceException e) {

            System.out.println(e.getMessage());
        }
    }
}
```

Output:

```text
Insufficient balance
```

---

# 37. Why Use super(message)?

Consider:

```java
public InsufficientBalanceException(String message) {
    super(message);
}
```

`super(message)` calls the constructor of the parent class:

```java
RuntimeException
```

This stores the exception message.

Therefore:

```java
e.getMessage()
```

can return:

```text
Insufficient balance
```

---

# 38. Custom Checked Exception

A custom checked exception extends `Exception`.

Example:

```java
public class InvalidAgeException extends Exception {

    public InvalidAgeException(String message) {
        super(message);
    }
}
```

Because it extends `Exception` and not `RuntimeException`, it is a checked exception.

---

# 39. Custom Checked Exception Example

```java
public class InvalidAgeException extends Exception {

    public InvalidAgeException(String message) {
        super(message);
    }
}
```

Use it:

```java
public class UserRegistration {

    static void registerUser(int age)
            throws InvalidAgeException {

        if (age < 18) {

            throw new InvalidAgeException(
                    "User must be 18 or older"
            );
        }

        System.out.println("Registration successful");
    }

    public static void main(String[] args) {

        try {

            registerUser(16);

        } catch (InvalidAgeException e) {

            System.out.println(e.getMessage());
        }
    }
}
```

Output:

```text
User must be 18 or older
```

---

# 40. Checked vs Unchecked Custom Exceptions

### Checked

```java
class InvalidAgeException extends Exception {
}
```

Caller must handle or declare it.

### Unchecked

```java
class InvalidAgeException extends RuntimeException {
}
```

Caller is not forced to handle it.

---

# 41. When Should We Create Custom Exceptions?

Use a custom exception when the error represents a meaningful domain or business condition.

For example:

```text
InsufficientBalanceException
```

is clearer than:

```text
IllegalArgumentException
```

for a banking application.

Similarly:

```text
ProductOutOfStockException
```

clearly describes an e-commerce problem.

Custom exceptions improve:

- Readability
- Maintainability
- Debugging
- Business logic clarity
- Error handling

---

# 42. try-with-resources

Java provides **try-with-resources** to automatically close resources.

It is commonly used with resources such as:

```text
FileReader
BufferedReader
InputStream
OutputStream
Database resources
```

A resource must implement:

```java
AutoCloseable
```

or:

```java
Closeable
```

---

# 43. Basic try-with-resources Syntax

```java
try (Resource resource = createResource()) {

    // use resource

} catch (Exception e) {

    // handle exception
}
```

Example:

```java
import java.io.FileReader;
import java.io.IOException;

public class TryWithResourcesExample {

    public static void main(String[] args) {

        try (FileReader reader =
                     new FileReader("data.txt")) {

            int data = reader.read();

            System.out.println((char) data);

        } catch (IOException e) {

            System.out.println(
                    "File Error: " + e.getMessage()
            );
        }
    }
}
```

The `reader` is automatically closed.

---

# 44. Why try-with-resources?

Without try-with-resources, we may need:

```java
FileReader reader = null;

try {
    reader = new FileReader("data.txt");
} catch (IOException e) {
    System.out.println("Error");
} finally {

    if (reader != null) {
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("Close failed");
        }
    }
}
```

This is verbose and error-prone.

With try-with-resources:

```java
try (FileReader reader =
             new FileReader("data.txt")) {

    // use reader

} catch (IOException e) {

    System.out.println("File Error");
}
```

Java automatically closes the resource.

---

# 45. AutoCloseable

A resource used in try-with-resources must implement:

```java
AutoCloseable
```

Example:

```java
class MyResource implements AutoCloseable {

    public void use() {
        System.out.println("Using resource");
    }

    @Override
    public void close() {
        System.out.println("Resource closed");
    }
}
```

Use:

```java
public class Example {

    public static void main(String[] args) {

        try (MyResource resource = new MyResource()) {

            resource.use();

        }
    }
}
```

Output:

```text
Using resource
Resource closed
```

---

# 46. Multiple Resources in try-with-resources

Multiple resources can be declared.

```java
try (
    FileReader reader1 = new FileReader("data1.txt");
    FileReader reader2 = new FileReader("data2.txt")
) {

    // use resources

} catch (IOException e) {

    System.out.println(e.getMessage());
}
```

Resources are automatically closed.

---

# 47. getMessage()

The `getMessage()` method returns the exception message.

Example:

```java
try {

    throw new IllegalArgumentException(
            "Invalid age"
    );

} catch (IllegalArgumentException e) {

    System.out.println(e.getMessage());
}
```

Output:

```text
Invalid age
```

---

# 48. printStackTrace()

`printStackTrace()` prints information about the exception and the call stack.

Example:

```java
try {

    int result = 10 / 0;

} catch (ArithmeticException e) {

    e.printStackTrace();
}
```

Typical output contains:

```text
java.lang.ArithmeticException: / by zero
    at Example.main(Example.java:...)
```

It helps developers identify:

- Exception type
- Exception message
- Where the exception occurred
- Method call chain

---

# 49. getCause()

`getCause()` returns the original cause of an exception.

Example:

```java
Exception cause =
        new IllegalArgumentException("Invalid age");

RuntimeException exception =
        new RuntimeException(
                "Validation failed",
                cause
        );

System.out.println(
        exception.getCause().getMessage()
);
```

Output:

```text
Invalid age
```

---

# 50. Useful Exception Methods

| Method | Purpose |
|---|---|
| `getMessage()` | Returns exception message |
| `printStackTrace()` | Prints stack trace |
| `getCause()` | Returns underlying cause |
| `toString()` | Returns exception class + message |

Example:

```java
catch (Exception e) {

    System.out.println(e.getMessage());

    e.printStackTrace();

    System.out.println(e.getCause());

    System.out.println(e.toString());
}
```

---

# 51. Common Built-in Exceptions

## NullPointerException

Occurs when trying to use an object reference that is `null`.

```java
String name = null;

System.out.println(name.length());
```

---

## ArithmeticException

Occurs during invalid arithmetic operations.

```java
int result = 10 / 0;
```

---

## NumberFormatException

Occurs when converting an invalid string to a number.

```java
int number = Integer.parseInt("abc");
```

---

## ArrayIndexOutOfBoundsException

Occurs when accessing an invalid array index.

```java
int[] numbers = {10, 20, 30};

System.out.println(numbers[5]);
```

---

## StringIndexOutOfBoundsException

Occurs when accessing an invalid String index.

```java
String name = "Java";

System.out.println(name.charAt(10));
```

---

## IllegalArgumentException

Occurs when a method receives an invalid argument.

```java
if (age < 0) {
    throw new IllegalArgumentException("Invalid age");
}
```

---

## ClassCastException

Occurs when an object is incorrectly cast to an incompatible type.

```java
Object value = "Java";

Integer number = (Integer) value;
```

---

# 52. Common Mistake: Catching Exception Everywhere

Avoid:

```java
try {

    // everything

} catch (Exception e) {

    System.out.println("Something went wrong");
}
```

This may hide the actual problem.

Prefer specific exceptions where appropriate:

```java
try {

} catch (FileNotFoundException e) {

} catch (IOException e) {

}
```

This makes error handling more meaningful.

---

# 53. Common Mistake: Empty catch Block

Avoid:

```java
try {

} catch (Exception e) {

}
```

This silently ignores the exception.

It makes debugging extremely difficult.

Better:

```java
catch (Exception e) {

    System.out.println(
            "Operation failed: " + e.getMessage()
    );
}
```

Or use proper application logging.

---

# 54. Common Mistake: Using Exception for Normal Program Flow

Do not use exceptions for normal control flow.

Bad:

```java
try {

    int number = Integer.parseInt(input);

} catch (NumberFormatException e) {

    // normal validation logic
}
```

Sometimes this may be necessary depending on the API, but exceptions should generally represent exceptional situations rather than normal expected branching.

---

# 55. Common Mistake: Losing the Original Exception

Bad:

```java
catch (Exception e) {

    throw new RuntimeException(
            "Something went wrong"
    );
}
```

The original cause is lost.

Better:

```java
catch (Exception e) {

    throw new RuntimeException(
            "Something went wrong",
            e
    );
}
```

Now the original exception is preserved.

This is exception chaining.

---

# 56. Common Mistake: Wrong catch Order

Wrong:

```java
try {

} catch (Exception e) {

} catch (IOException e) {

}
```

This does not compile because `IOException` is already covered by `Exception`.

Correct:

```java
try {

} catch (IOException e) {

} catch (Exception e) {

}
```

---

# 57. Common Mistake: Assuming finally Always Executes

Normally, `finally` executes.

However, there are situations where it may not execute, such as:

```java
System.exit(0);
```

Example:

```java
try {

    System.out.println("Try");

    System.exit(0);

} finally {

    System.out.println("Finally");
}
```

The JVM terminates, so `finally` does not get normal execution.

Therefore, the interview-safe statement is:

> The finally block normally executes whether an exception occurs or not, except in cases where normal JVM execution is terminated.

---

# 58. Common Mistake: Catching Exception and Doing Nothing

Bad:

```java
catch (Exception e) {
}
```

This is called swallowing the exception.

The error disappears without any useful handling or logging.

Prefer:

```java
catch (Exception e) {

    System.err.println(
            "Operation failed: " + e.getMessage()
    );
}
```

In production applications, proper logging should generally be used.

---

# 59. Exception Handling in a Real Application

Consider an e-commerce application.

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

Suppose the database operation fails.

The repository may encounter:

```text
SQLException
```

The application may convert it into a domain/application exception:

```text
DatabaseOperationException
```

The service may propagate it.

The controller or global exception handler may return:

```text
HTTP 500
```

or an appropriate error response.

Conceptually:

```text
Database
   ↓
SQLException
   ↓
Repository
   ↓
Application Exception
   ↓
Service
   ↓
Controller
   ↓
HTTP Error Response
```

This is commonly used in Spring Boot applications.

---

# 60. Business Exception Example

Consider a food delivery application.

```java
public class RestaurantClosedException
        extends RuntimeException {

    public RestaurantClosedException(String message) {
        super(message);
    }
}
```

Service:

```java
public void placeOrder(boolean restaurantOpen) {

    if (!restaurantOpen) {

        throw new RestaurantClosedException(
                "Restaurant is currently closed"
        );
    }

    System.out.println("Order placed");
}
```

This is better than using a generic exception because the business meaning is clear.

---

# 61. Exception Handling Best Practices

### 1. Catch specific exceptions

Prefer:

```java
catch (FileNotFoundException e)
```

over:

```java
catch (Exception e)
```

when the specific exception is known.

---

### 2. Do not swallow exceptions

Avoid:

```java
catch (Exception e) {
}
```

---

### 3. Preserve the original cause

Use:

```java
throw new RuntimeException(
        "Operation failed",
        e
);
```

---

### 4. Use meaningful custom exceptions

Examples:

```text
UserNotFoundException
InsufficientBalanceException
ProductOutOfStockException
InvalidOrderStateException
```

---

### 5. Use try-with-resources

Prefer:

```java
try (FileReader reader = new FileReader("data.txt")) {
}
```

for resources that support `AutoCloseable`.

---

### 6. Do not overuse exceptions

Exceptions should represent exceptional situations, not every normal branch.

---

### 7. Provide meaningful messages

Bad:

```java
throw new RuntimeException("Error");
```

Better:

```java
throw new UserNotFoundException(
        "User with ID 101 was not found"
);
```

---

# 62. Important Exception Handling Comparisons

## throw vs throws

```text
throw
    ↓
Actually throws an exception

throws
    ↓
Declares possible exceptions
```

---

## Checked vs Unchecked

```text
Checked
    ↓
Compiler requires handle/declare

Unchecked
    ↓
Compiler does not require handle/declare
```

---

## final vs finally vs finalize

### final

Keyword used for:

```text
Variable
Method
Class
```

Example:

```java
final int MAX = 100;
```

---

### finally

Block used for cleanup:

```java
try {
} finally {
}
```

---

### finalize

An old/deprecated mechanism historically associated with garbage collection.

It should not be used in modern Java application development.

---

# 63. finally vs try-with-resources

Traditional cleanup:

```java
FileReader reader = null;

try {

    reader = new FileReader("data.txt");

} finally {

    if (reader != null) {
        reader.close();
    }
}
```

Modern approach:

```java
try (FileReader reader =
             new FileReader("data.txt")) {

}
```

Try-with-resources is generally cleaner and safer for `AutoCloseable` resources.

---

# 64. Can try Exist Without catch?

Yes.

A `try` block can exist with `finally`.

Example:

```java
try {

    System.out.println("Code");

} finally {

    System.out.println("Cleanup");
}
```

This is valid.

---

# 65. Can try Exist Without catch and finally?

No.

This is invalid:

```java
try {

    System.out.println("Hello");
}
```

A `try` block must be followed by at least:

```text
catch
```

or:

```text
finally
```

---

# 66. Can We Have Multiple finally Blocks?

No.

This is invalid:

```java
try {

} finally {

} finally {

}
```

A `try` statement can have at most one `finally` block.

---

# 67. Can We Have Multiple catch Blocks?

Yes.

Example:

```java
try {

} catch (ArithmeticException e) {

} catch (NullPointerException e) {

} catch (Exception e) {

}
```

---

# 68. Can We Have try Inside try?

Yes.

Nested try blocks are allowed.

Example:

```java
try {

    try {

        int result = 10 / 0;

    } catch (ArithmeticException e) {

        System.out.println("Inner catch");

    }

} catch (Exception e) {

    System.out.println("Outer catch");
}
```

Output:

```text
Inner catch
```

The inner catch handles the exception.

---

# 69. What Happens If Inner catch Cannot Handle the Exception?

Example:

```java
try {

    try {

        String name = null;

        System.out.println(name.length());

    } catch (ArithmeticException e) {

        System.out.println("Inner arithmetic");
    }

} catch (NullPointerException e) {

    System.out.println("Outer null pointer");
}
```

The inner catch cannot handle `NullPointerException`.

Therefore, the exception propagates to the outer catch.

Output:

```text
Outer null pointer
```

---

# 70. Can We Throw a Checked Exception Without Handling It?

Yes, if the method declares it using `throws`.

Example:

```java
static void readFile() throws IOException {

    throw new IOException("File error");
}
```

The caller must either:

```java
try-catch
```

or:

```java
throws IOException
```

---

# 71. Can We Throw an Unchecked Exception Without Declaring It?

Yes.

Example:

```java
static void validateAge(int age) {

    if (age < 18) {

        throw new IllegalArgumentException(
                "Invalid age"
        );
    }
}
```

No `throws` declaration is required.

---

# 72. Can a Constructor Throw an Exception?

Yes.

Example:

```java
public class User {

    public User(String name) throws Exception {

        if (name == null) {
            throw new Exception("Name cannot be null");
        }
    }
}
```

The caller must handle or declare the checked exception.

---

# 73. Can a Method Throw Multiple Exceptions?

Yes.

Using `throws`:

```java
static void process()
        throws IOException, SQLException {

}
```

Multiple exception types can be declared.

---

# 74. Exception Handling and Method Overriding

When overriding a method, a child method cannot throw broader checked exceptions than the parent method.

Example:

```java
class Parent {

    void process() throws IOException {
    }
}
```

Child:

```java
class Child extends Parent {

    @Override
    void process() throws FileNotFoundException {
    }
}
```

This is valid because:

```text
FileNotFoundException
        ↓
IOException
```

The child throws a narrower checked exception.

---

# 75. Runtime Exceptions and Method Overriding

Unchecked exceptions do not have the same restriction.

Example:

```java
class Parent {

    void process() {
    }
}
```

Child:

```java
class Child extends Parent {

    @Override
    void process() throws RuntimeException {
    }
}
```

This is allowed because `RuntimeException` is unchecked.

---

# 76. Exception Handling and Inheritance

Exceptions also follow inheritance.

Example:

```java
ArithmeticException
        ↓
RuntimeException
        ↓
Exception
        ↓
Throwable
```

Therefore:

```java
catch (Exception e)
```

can catch:

```text
ArithmeticException
NullPointerException
NumberFormatException
```

and many other exceptions.

But:

```java
catch (ArithmeticException e)
```

only handles `ArithmeticException` and its subclasses.

---

# 77. Real-World Banking Example

Custom exception:

```java
public class InsufficientBalanceException
        extends RuntimeException {

    public InsufficientBalanceException(String message) {
        super(message);
    }
}
```

Service:

```java
public class BankAccount {

    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than zero"
            );
        }

        if (amount > balance) {
            throw new InsufficientBalanceException(
                    "Insufficient balance"
            );
        }

        balance -= amount;

        System.out.println(
                "Withdrawal successful"
        );
    }
}
```

Usage:

```java
public class BankApplication {

    public static void main(String[] args) {

        BankAccount account =
                new BankAccount(5000);

        try {

            account.withdraw(7000);

        } catch (InsufficientBalanceException e) {

            System.out.println(e.getMessage());
        }
    }
}
```

Output:

```text
Insufficient balance
```

---

# 78. Real-World User Registration Example

Custom checked exception:

```java
public class InvalidAgeException
        extends Exception {

    public InvalidAgeException(String message) {
        super(message);
    }
}
```

Service:

```java
public class RegistrationService {

    public void register(int age)
            throws InvalidAgeException {

        if (age < 18) {

            throw new InvalidAgeException(
                    "User must be 18 or older"
            );
        }

        System.out.println(
                "Registration successful"
        );
    }
}
```

Usage:

```java
public class Application {

    public static void main(String[] args) {

        RegistrationService service =
                new RegistrationService();

        try {

            service.register(16);

        } catch (InvalidAgeException e) {

            System.out.println(e.getMessage());
        }
    }
}
```

---

# 79. Exception Handling Flow

A useful mental model:

```text
                 Method starts
                       |
                       ↓
                 Execute try
                       |
                ┌──────┴──────┐
                ↓             ↓
          No Exception    Exception
                |             |
                ↓             ↓
             finally      Find catch
                              |
                       ┌──────┴──────┐
                       ↓             ↓
                  Match found   No match
                       |             |
                       ↓             ↓
                    catch       propagate
                       |             |
                       ↓             ↓
                    finally      finally
                       |             |
                       └──────┬──────┘
                              ↓
                       Continue / Propagate
```

---

# 80. Exception Handling Interview Questions

## Basic Questions

### 1. What is an exception?

An exception is an unexpected event during program execution that disrupts the normal flow of execution.

---

### 2. What is exception handling?

Exception handling is a mechanism provided by Java to detect and handle runtime problems so that the application can respond gracefully.

---

### 3. What is the difference between Error and Exception?

`Error` generally represents serious JVM/runtime problems, while `Exception` generally represents conditions that application code can potentially handle.

---

### 4. What is the difference between checked and unchecked exceptions?

Checked exceptions are checked by the compiler and must be handled or declared.

Unchecked exceptions are subclasses of `RuntimeException` and are not required by the compiler to be handled or declared.

---

### 5. What are the exception handling keywords?

```text
try
catch
finally
throw
throws
```

---

### 6. What is the difference between throw and throws?

`throw` explicitly throws an exception object.

`throws` declares that a method may throw one or more exceptions.

---

### 7. Can we have multiple catch blocks?

Yes.

---

### 8. What is the correct order of catch blocks?

Specific exceptions should come before general exceptions.

---

### 9. What is finally?

`finally` is a block generally used for cleanup code and normally executes whether an exception occurs or not.

---

### 10. Can try exist without catch?

Yes, if it has a `finally` block.

---

# 81. Intermediate Interview Questions

### 11. Can finally execute if there is no exception?

Yes.

---

### 12. Can finally execute if there is an exception?

Yes, normally.

---

### 13. Can finally fail to execute?

Yes, in situations such as:

```java
System.exit(0);
```

where the JVM terminates.

---

### 14. Can we have multiple finally blocks?

No.

---

### 15. Can we have multiple catch blocks?

Yes.

---

### 16. Can a catch block handle multiple exceptions?

Yes, using multi-catch:

```java
catch (IOException | SQLException e)
```

---

### 17. Can multi-catch contain parent and child exceptions?

No.

Invalid:

```java
catch (Exception | IOException e)
```

---

### 18. What happens if no catch handles an exception?

The exception propagates to the caller.

If nobody handles it, the thread terminates and the JVM prints the stack trace.

---

### 19. What is exception propagation?

It is the process of an exception moving upward through the method call stack until a matching handler is found.

---

### 20. What is exception chaining?

Exception chaining means associating one exception with another as its cause.

---

# 82. Advanced Interview Questions

### 21. Why should we preserve the original exception cause?

Because it provides the root cause and makes debugging easier.

Example:

```java
throw new RuntimeException(
        "Failed to process order",
        e
);
```

---

### 22. What is try-with-resources?

It is a Java feature that automatically closes resources implementing `AutoCloseable`.

---

### 23. What is AutoCloseable?

`AutoCloseable` is an interface that allows resources to define a `close()` method that can be automatically invoked by try-with-resources.

---

### 24. Why is try-with-resources preferred over finally for resource cleanup?

It reduces boilerplate and ensures resources are automatically closed.

---

### 25. Can constructors throw exceptions?

Yes.

---

### 26. Can a method throw multiple exceptions?

Yes.

```java
void process()
        throws IOException, SQLException {
}
```

---

### 27. Can unchecked exceptions be declared using throws?

Yes, although it is not required.

---

### 28. Can we throw checked exceptions using throw?

Yes, but they must be handled or declared.

Example:

```java
throw new IOException("File error");
```

---

### 29. Can we create custom exceptions?

Yes.

```java
class UserNotFoundException
        extends RuntimeException {
}
```

---

### 30. Why do we create custom exceptions?

To represent meaningful application or business-specific error conditions.

---

# 83. Coding Practice

## Practice 1 — Basic try-catch

Write a program that divides two numbers and handles division by zero.

Expected:

```text
Cannot divide by zero
```

---

## Practice 2 — Multiple catch

Write a program that can produce:

```text
ArithmeticException
NullPointerException
NumberFormatException
```

Handle each separately.

---

## Practice 3 — finally

Write a program that demonstrates that `finally` executes when:

1. No exception occurs
2. Exception occurs and is handled

---

## Practice 4 — throw

Create:

```java
checkAge(int age)
```

If age is less than 18:

```text
throw IllegalArgumentException
```

---

## Practice 5 — throws

Create:

```java
readFile()
```

that declares:

```java
throws FileNotFoundException
```

Handle it from `main`.

---

## Practice 6 — Custom unchecked exception

Create:

```java
InsufficientBalanceException
```

Use it in:

```java
withdraw(balance, amount)
```

---

## Practice 7 — Custom checked exception

Create:

```java
InvalidAgeException
```

Use it in:

```java
registerUser(age)
```

---

## Practice 8 — try-with-resources

Create a program that reads a character from a file using:

```java
FileReader
```

and try-with-resources.

---

## Practice 9 — Exception propagation

Create:

```text
methodA()
methodB()
methodC()
```

Throw an exception in `methodC()` and handle it in `main()`.

---

## Practice 10 — Exception chaining

Create an exception:

```text
DatabaseException
```

whose cause is:

```text
SQLException
```

---

# 84. Prediction Questions

## Question 1

What is the output?

```java
try {
    System.out.println("A");
    int result = 10 / 0;
    System.out.println("B");
} catch (ArithmeticException e) {
    System.out.println("C");
}

System.out.println("D");
```

Answer:

```text
A
C
D
```

---

## Question 2

What is the output?

```java
try {
    System.out.println("A");
} catch (Exception e) {
    System.out.println("B");
} finally {
    System.out.println("C");
}
```

Answer:

```text
A
C
```

---

## Question 3

What happens?

```java
try {
    int result = 10 / 0;
} catch (Exception e) {
    System.out.println("Exception");
} catch (ArithmeticException e) {
    System.out.println("Arithmetic");
}
```

Answer:

```text
Compilation error
```

Because the `ArithmeticException` catch is unreachable.

---

## Question 4

What is the output?

```java
String name = null;

try {
    System.out.println(name.length());
} catch (NullPointerException e) {
    System.out.println("Null");
}
```

Answer:

```text
Null
```

---

## Question 5

What is the output?

```java
try {

    int number = Integer.parseInt("abc");

} catch (NumberFormatException e) {

    System.out.println("Invalid number");

} finally {

    System.out.println("Done");
}
```

Answer:

```text
Invalid number
Done
```

---

# 85. Detailed Interview Explanation

### Question:

**Explain exception handling in Java.**

### Strong Interview Answer:

> Exception handling in Java is a mechanism used to handle unexpected conditions that occur during program execution without allowing the application to terminate abnormally.
>
> Java provides five main keywords for exception handling: `try`, `catch`, `finally`, `throw`, and `throws`.
>
> We place risky code inside a `try` block. If an exception occurs, the JVM searches for a matching `catch` block to handle it. The `finally` block is generally used for cleanup operations and normally executes whether an exception occurs or not.
>
> Java exceptions are broadly categorized into checked and unchecked exceptions. Checked exceptions are checked by the compiler and must either be handled or declared using `throws`. Examples include `IOException` and `SQLException`. Unchecked exceptions extend `RuntimeException`, such as `NullPointerException`, `ArithmeticException`, and `NumberFormatException`.
>
> The `throw` keyword is used to explicitly throw an exception, while `throws` is used in a method declaration to indicate that the method may throw certain exceptions.
>
> Java also supports custom exceptions and try-with-resources, which is useful for automatically closing resources that implement `AutoCloseable`.
>
> Overall, exception handling helps applications handle failures gracefully, separate error-handling logic from business logic, and make applications more reliable and maintainable.

---

# 86. Interview Mental Model

Think about exception handling in this order:

```text
1. Something goes wrong
          ↓
2. Exception object is created
          ↓
3. Current execution stops
          ↓
4. JVM searches for matching catch
          ↓
5. If found → catch executes
          ↓
6. finally normally executes
          ↓
7. Program continues
```

If no matching catch exists:

```text
Exception
    ↓
Caller
    ↓
Caller
    ↓
Caller
    ↓
No handler
    ↓
Thread terminates
```

---

# 87. Real-World Layered Exception Model

In a backend application:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

Suppose the database fails:

```text
SQLException
    ↓
Repository
    ↓
DatabaseException
    ↓
Service
    ↓
Controller
    ↓
Global Exception Handler
    ↓
HTTP Response
```

For example:

```text
Database Error
        ↓
500 Internal Server Error
```

Or for a business exception:

```text
InsufficientBalanceException
        ↓
400 Bad Request
```

In Spring Boot, this concept is commonly implemented using mechanisms such as:

```text
@RestControllerAdvice
@ExceptionHandler
```

This will become important when learning Spring Boot exception handling.

---

# 88. Quick Revision

```text
Exception
    ↓
Unexpected runtime event

try
    ↓
Contains risky code

catch
    ↓
Handles exception

finally
    ↓
Cleanup

throw
    ↓
Explicitly throws exception

throws
    ↓
Declares possible exception

Checked
    ↓
Compiler requires handle/declare

Unchecked
    ↓
RuntimeException hierarchy

Custom Exception
    ↓
Application-specific error

try-with-resources
    ↓
Automatic resource cleanup

Propagation
    ↓
Exception moves up call stack

Chaining
    ↓
Preserve original cause
```

---

# 89. Exception Handling Cheat Sheet

| Concept | Meaning |
|---|---|
| Exception | Unexpected event during execution |
| Error | Serious JVM/runtime problem |
| `try` | Risky code |
| `catch` | Exception handler |
| `finally` | Cleanup block |
| `throw` | Explicitly throw exception |
| `throws` | Declare possible exception |
| Checked | Compiler requires handling/declaring |
| Unchecked | RuntimeException hierarchy |
| Custom exception | Application-specific exception |
| Propagation | Exception moves up call stack |
| Rethrow | Throw caught exception again |
| Chaining | Preserve original cause |
| try-with-resources | Automatic resource cleanup |
| `getMessage()` | Get exception message |
| `getCause()` | Get original cause |
| `printStackTrace()` | Print stack trace |

---

# 90. Key Comparisons

## throw vs throws

```text
throw
    ↓
Inside method body
    ↓
Actually throws exception

throws
    ↓
Method declaration
    ↓
Declares possible exception
```

---

## Checked vs Unchecked

```text
Checked
    ↓
Compiler checks
    ↓
Handle or declare

Unchecked
    ↓
RuntimeException
    ↓
Compiler does not force handling
```

---

## Error vs Exception

```text
Error
    ↓
Serious JVM/runtime problems

Exception
    ↓
Application-level problems
    ↓
Often recoverable/handleable
```

---

## finally vs try-with-resources

```text
finally
    ↓
Manual cleanup

try-with-resources
    ↓
Automatic resource cleanup
```

---

# 91. DSA / Real-World Connection

Exception handling itself is not usually a DSA topic, but it is important for writing reliable algorithms and applications.

For example:

### Array Access

```java
int[] numbers = {10, 20, 30};

try {
    System.out.println(numbers[5]);
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("Invalid index");
}
```

### Parsing

```java
try {
    int number = Integer.parseInt(input);
} catch (NumberFormatException e) {
    System.out.println("Invalid number");
}
```

### File Processing

```java
try (FileReader reader =
             new FileReader("data.txt")) {

} catch (IOException e) {

}
```

### Backend Applications

Exception handling becomes especially important when working with:

```text
REST APIs
Databases
Spring Boot
File processing
Authentication
External APIs
Microservices
```

---

# 92. Important Rules to Remember

### Rule 1

A `try` block must be followed by either:

```text
catch
```

or:

```text
finally
```

---

### Rule 2

Specific catches must come before general catches.

Correct:

```java
catch (IOException e) {
} catch (Exception e) {
}
```

---

### Rule 3

A checked exception must be:

```text
Handled
OR
Declared
```

---

### Rule 4

Unchecked exceptions do not need to be handled or declared.

---

### Rule 5

`throw` actually throws an exception.

---

### Rule 6

`throws` declares possible exceptions.

---

### Rule 7

`finally` normally executes regardless of whether an exception occurs.

---

### Rule 8

Try-with-resources automatically closes `AutoCloseable` resources.

---

### Rule 9

Custom checked exceptions extend:

```java
Exception
```

Custom unchecked exceptions extend:

```java
RuntimeException
```

---

### Rule 10

Do not catch a parent exception before its child.

Wrong:

```java
catch (Exception e)
catch (IOException e)
```

Correct:

```java
catch (IOException e)
catch (Exception e)
```

---

# 93. Interview-Level Mental Model

When you see exception-handling code, ask these questions in order:

```text
1. Where is the try block?
        ↓
2. What code can actually execute inside it?
        ↓
3. What exception can occur first?
        ↓
4. Which catch can handle it?
        ↓
5. Are catches ordered correctly?
        ↓
6. Does finally execute?
        ↓
7. Does the exception propagate?
        ↓
8. Is it checked or unchecked?
        ↓
9. Is the exception rethrown?
        ↓
10. Is the original cause preserved?
```

This mental model is extremely useful for interview prediction questions.

---

# 94. Final Interview Checklist

Before considering Exception Handling complete, you should be able to explain:

- What is an exception?
- Why do we need exception handling?
- Exception hierarchy
- `Throwable`
- `Error`
- `Exception`
- Checked exceptions
- Unchecked exceptions
- `RuntimeException`
- `try`
- `catch`
- Multiple catch
- Catch ordering
- `finally`
- `throw`
- `throws`
- `throw` vs `throws`
- Exception propagation
- Rethrowing
- Exception chaining
- Custom exceptions
- Checked custom exceptions
- Unchecked custom exceptions
- `try-with-resources`
- `AutoCloseable`
- `getMessage()`
- `getCause()`
- `printStackTrace()`
- Common built-in exceptions
- Nested try
- Multi-catch
- Exception handling best practices
- Exception handling in layered applications

---

# 95. Key Takeaways

1. An exception is an unexpected event that disrupts normal program execution.

2. Java provides:

```text
try
catch
finally
throw
throws
```

3. Checked exceptions are checked by the compiler and must be handled or declared.

4. Unchecked exceptions are subclasses of `RuntimeException`.

5. `throw` explicitly throws an exception.

6. `throws` declares that a method may throw exceptions.

7. Specific catch blocks must come before general catch blocks.

8. `finally` is normally used for cleanup.

9. Try-with-resources automatically closes `AutoCloseable` resources.

10. Exception propagation moves an exception upward through the call stack.

11. Exception chaining preserves the original cause.

12. Custom exceptions make business errors more meaningful.

13. Avoid swallowing exceptions.

14. Preserve original causes when wrapping exceptions.

15. Prefer specific exception handling over blindly catching `Exception`.

16. Exception handling is especially important in backend applications involving:

```text
REST APIs
Databases
Files
Authentication
External Services
Microservices
Spring Boot
```

---

# 96. Final Mental Picture

```text
                         Throwable
                            |
                ┌───────────┴───────────┐
                ↓                       ↓
              Error                  Exception
                                        |
                              ┌─────────┴─────────┐
                              ↓                   ↓
                    RuntimeException       Checked Exception
                              |
                         Unchecked
```

Exception handling flow:

```text
                    try
                     |
             ┌───────┴───────┐
             ↓               ↓
       No Exception      Exception
             |               |
             |          Matching catch
             |               |
             |          Handle / Rethrow
             |               |
             └───────┬───────┘
                     ↓
                  finally
                     |
                     ↓
              Continue execution
```

Core memory:

```text
try      → risky code
catch    → handle error
finally  → cleanup
throw    → explicitly throw
throws   → declare exception
```

And the most important interview distinction:

```text
throw
    → actually throws an exception

throws
    → declares that a method may throw an exception
```

**Exception Handling = Detect → Handle → Cleanup → Recover / Propagate**