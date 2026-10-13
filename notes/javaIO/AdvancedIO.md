
# Java Advanced I/O — Interview Cheat Sheet

## 1. What Is Advanced I/O?

Advanced I/O covers techniques and APIs that help applications handle files, data streams, and network communication efficiently and reliably.

Important areas include:

- Buffered I/O
- NIO channels and buffers
- File transfer operations
- Character encoding
- Resource management
- Large-file processing
- Asynchronous I/O

## 2. Choose the Right API

| Requirement | Recommended starting point |
|---|---|
| Read a small text file | `Files.readString()` |
| Write a small text file | `Files.writeString()` |
| Process a large text file line-by-line | `BufferedReader` |
| Copy a file | `Files.copy()` |
| Read or write binary data | `InputStream` / `OutputStream` |
| Access a file at specific positions | `FileChannel` |
| Transfer data through a channel | `FileChannel.transferTo()` / `transferFrom()` |
| Work with directories and paths | `Path` and `Files` |
| Process network data | Socket channels or appropriate networking APIs |
| Run supported I/O operations asynchronously | `AsynchronousFileChannel` or asynchronous networking APIs |

Prefer the simplest API that meets the requirements.

## 3. Process Large Files Safely

Avoid loading a huge file entirely into memory.

Instead of:

```java
String content = Files.readString(path);
```

for a very large file, process it line-by-line:

```java
try (BufferedReader reader = Files.newBufferedReader(
        path, StandardCharsets.UTF_8)) {

    String line;

    while ((line = reader.readLine()) != null) {
        // Process one line at a time.
    }
}
```

This avoids retaining the entire file as one large string.

## 4. Resource Management

Use try-with-resources for resources implementing `AutoCloseable`.

```java
try (BufferedReader reader = Files.newBufferedReader(
        Path.of("orders.txt"), StandardCharsets.UTF_8)) {

    String line;

    while ((line = reader.readLine()) != null) {
        System.out.println(line);
    }
}
```

The reader closes automatically, even if an exception occurs.

Use the same pattern for streams, channels, and other closeable I/O resources.

## 5. Character Encoding

Text is represented as bytes in files. Encoding determines how characters map to bytes.

Prefer an explicit charset such as UTF-8:

```java
Files.writeString(
    Path.of("report.txt"),
    "Order processed",
    StandardCharsets.UTF_8
);
```

Do not treat arbitrary UTF-8 bytes as individual characters. Decode bytes using a charset-aware API.

## 6. File Copying and Transfers

For ordinary file copies:

```java
Files.copy(
    Path.of("source.txt"),
    Path.of("backup.txt"),
    StandardCopyOption.REPLACE_EXISTING
);
```

For controlled channel transfers, use `FileChannel` and its transfer methods.

Neither API guarantees the same performance in every environment. Select based on requirements and measure when performance matters.

## 7. Asynchronous I/O

Asynchronous I/O allows an application to initiate supported I/O operations and continue other work while completion is handled later.

For files, Java provides `AsynchronousFileChannel`.

Important concepts:

- Completion handlers
- Futures
- Completion callbacks
- Error handling
- Resource lifecycle

Asynchronous I/O does not automatically make every application faster. It adds complexity and is most useful when its concurrency model fits the workload.

## 8. Blocking vs Non-Blocking vs Asynchronous

**Blocking I/O:** An operation waits until it can make progress or complete.

**Non-blocking I/O:** An operation returns without waiting for data to be available, where the channel supports this mode.

**Asynchronous I/O:** The operation's completion is reported later, for example through a future or callback.

These terms are related but not interchangeable.

## 9. Common Production Mistakes

- Loading very large files entirely into memory.
- Using the wrong character encoding.
- Forgetting to close streams or channels.
- Ignoring partial channel writes.
- Assuming file existence checks eliminate race conditions.
- Assuming buffering or NIO always improves performance.
- Deserializing untrusted Java objects.
- Logging sensitive file contents.
- Ignoring permission errors, disk-full conditions, and I/O failures.
- Choosing asynchronous APIs without needing their added complexity.

## 10. Interview Questions

**Q1. How do you read a large text file efficiently?**

Use a buffered reader and process one line at a time rather than loading the entire file into memory.

**Q2. Why use UTF-8 explicitly?**

It avoids dependence on a platform's default charset and ensures consistent text interpretation.

**Q3. When would you use FileChannel instead of Files.copy()?**

Use FileChannel when you need more control over file positioning, buffer-based operations, or channel transfers. Use Files.copy() for ordinary copies.

**Q4. What is asynchronous I/O?**

I/O in which completion is reported later through a future or callback, allowing the initiating code to do other work.

**Q5. How do you prevent resource leaks?**

Use try-with-resources for closeable I/O resources.

**Q6. How do you improve I/O performance?**

Use suitable buffering, avoid unnecessary data copies, process large files incrementally, choose appropriate APIs, and benchmark real workloads.

## 11. Final Mental Model

Use high-level APIs for simple tasks.

Use streams for straightforward sequential data processing.

Use channels and buffers when you need lower-level control.

Use asynchronous APIs when their concurrency model fits the problem.

Always consider encoding, resource cleanup, error handling, memory use, and security.
