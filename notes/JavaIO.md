
# ☕ Java I/O — Last-Minute Revision Cheat Sheet

> **Purpose:** Last-minute interview revision + quickly identify which Java I/O API to use from a problem statement.

---

# 1. Java I/O Pattern Identification

## 🚨 The Most Important Rule

When you see an I/O problem, ask:

```text
1. Is the data binary or text?
2. Am I reading or writing?
3. Is the file small or large?
4. Do I need line-by-line processing?
5. Do I need buffering?
6. Do I need file metadata or directory operations?
7. Do I need object serialization?
8. Do I need precise file positioning?
9. Do I need asynchronous or non-blocking I/O?
10. How will I handle exceptions and close resources?
```

Then choose the API.

---

# 2. Quick Decision Table

| Problem Requirement | Use |
|---|---|
| Read binary data | `InputStream` |
| Write binary data | `OutputStream` |
| Read text | `Reader` |
| Write text | `Writer` |
| Read bytes from a file | `FileInputStream` |
| Write bytes to a file | `FileOutputStream` |
| Read text line-by-line | `BufferedReader` |
| Write text efficiently | `BufferedWriter` |
| Reduce frequent underlying I/O operations | Buffered streams/writers |
| Read an entire small text file | `Files.readString()` |
| Write an entire text file | `Files.writeString()` |
| Work with paths | `Path` |
| Create, copy, move, delete files | `Files` |
| List or traverse directories | `Files.list()` / `Files.walk()` |
| Serialize Java objects | `ObjectOutputStream` |
| Deserialize Java objects | `ObjectInputStream` |
| Read/write a file at specific positions | `FileChannel` |
| Transfer data between channels | `transferTo()` / `transferFrom()` |
| Hold data during channel operations | `ByteBuffer` |
| Bridge streams and channels | `Channels` |
| Perform supported asynchronous file I/O | `AsynchronousFileChannel` |
| Ensure automatic resource closure | `try-with-resources` |

---

# 3. Java I/O Architecture

Java I/O provides APIs to move data between applications and external sources or destinations.

```text
                    Java I/O
                       |
          +------------+------------+
          |                         |
      java.io                    java.nio
          |                         |
    Traditional I/O           New I/O APIs
          |                         |
   +------+------+          +-------+-------+
   |             |          |               |
Byte Streams  Character   Path/Files      Channels
               Streams                       |
                                             Buffer
```

### Important Packages

```java
java.io
java.nio
java.nio.file
java.nio.channels
java.nio.charset
```

### Interview One-Liner

> Java I/O provides APIs for reading and writing data, while NIO adds path-based filesystem operations, buffers, channels, and additional I/O capabilities.

---

# 4. Byte Streams

### One-liner

> **Byte streams = process raw bytes.**

### Main Classes

```text
InputStream
├── FileInputStream
├── BufferedInputStream
└── ObjectInputStream

OutputStream
├── FileOutputStream
├── BufferedOutputStream
└── ObjectOutputStream
```

### Use When

```text
✓ Images
✓ PDFs
✓ Audio/video files
✓ Binary files
✓ Arbitrary byte data
```

### Read Pattern

```java
try (InputStream input =
         new FileInputStream("input.bin")) {

    int data;

    while ((data = input.read()) != -1) {
        // Process byte value
    }

} catch (IOException e) {
    e.printStackTrace();
}
```

### Efficient Chunk-Based Reading

```java
byte[] buffer = new byte[8192];
int count;

while ((count = input.read(buffer)) != -1) {
    // Process buffer[0] through buffer[count - 1]
}
```

### Important Rules

- `read()` returns an `int`.
- Byte values are returned in the range `0–255`.
- `-1` means end of stream.
- `FileOutputStream` truncates an existing file by default.
- Use append mode when appropriate:

```java
new FileOutputStream("output.bin", true);
```

### Interview One-Liner

> I use byte streams for binary data because they process bytes without performing character decoding.

---

# 5. Character Streams

### One-liner

> **Character streams = process decoded character data.**

### Main Classes

```text
Reader
├── FileReader
├── BufferedReader
└── InputStreamReader

Writer
├── FileWriter
├── BufferedWriter
└── OutputStreamWriter
```

### Use When

```text
✓ Text files
✓ Logs
✓ CSV files
✓ Text reports
✓ Character-based processing
```

### Example

```java
try (Reader reader = new FileReader(
         "input.txt", StandardCharsets.UTF_8)) {

    int character;

    while ((character = reader.read()) != -1) {
        System.out.print((char) character);
    }

} catch (IOException e) {
    e.printStackTrace();
}
```

### Encoding Rule

```text
File bytes
    ↓
Charset decoder
    ↓
Characters
```

Writing follows the reverse direction.

Prefer explicit UTF-8 encoding for predictable text processing.

**Important:** Do not cast individual UTF-8 bytes to `char`. A UTF-8 character may require multiple bytes.

### Interview One-Liner

> I use character streams for text because they handle decoding and encoding between bytes and characters.

---

# 6. Buffered I/O

### One-liner

> **Buffering = reduce frequent interactions with the underlying I/O resource.**

### Classes

```text
BufferedInputStream
BufferedOutputStream
BufferedReader
BufferedWriter
```

### Pattern Recognition

If the problem says:

```text
"Read a large text file line-by-line"
"Process a log file"
"Write a report efficiently"
"Reduce frequent I/O operations"
```

Think:

```java
BufferedReader
BufferedWriter
BufferedInputStream
BufferedOutputStream
```

### Read Line-by-Line

```java
try (BufferedReader reader =
         Files.newBufferedReader(
             Path.of("orders.txt"),
             StandardCharsets.UTF_8)) {

    String line;

    while ((line = reader.readLine()) != null) {
        System.out.println(line);
    }

} catch (IOException e) {
    e.printStackTrace();
}
```

### Important Methods

```java
readLine()
newLine()
flush()
close()
```

- `readLine()` returns `null` at EOF.
- `newLine()` writes the platform's line separator.
- `flush()` pushes buffered output to the underlying resource.
- `close()` closes the resource and flushes pending output as appropriate.

### Interview One-Liner

> Buffered I/O can improve throughput by reducing frequent underlying I/O operations, especially when processing data in chunks.

---

# 7. try-with-resources

### One-liner

> **try-with-resources = automatically close resources.**

### Pattern Recognition

Whenever you open:

```text
InputStream
OutputStream
Reader
Writer
Channel
```

Think:

```java
try-with-resources
```

### Example

```java
try (BufferedReader reader =
         Files.newBufferedReader(Path.of("input.txt"))) {

    System.out.println(reader.readLine());

} catch (IOException e) {
    e.printStackTrace();
}
```

### Why Use It?

```text
Automatic closure
        ↓
Fewer resource leaks
        ↓
Safer exception handling
```

Resources implementing `AutoCloseable` can be managed this way.

### Interview One-Liner

> Try-with-resources ensures that closeable resources are closed automatically, even when an exception occurs.

---

# 8. File, Path and Files

### One-liner

```text
File  → Older filesystem abstraction
Path  → Represents a filesystem path
Files → Provides filesystem operations
```

### Pattern Recognition

If the problem says:

```text
"Create a directory"
"Copy a file"
"Move a file"
"Check file size"
"Read an entire text file"
"List files in a directory"
```

Think:

```java
Path
Files
```

### Java 11 Example

```java
Path path = Path.of("orders.txt");

Files.writeString(
    path,
    "Order processed",
    StandardCharsets.UTF_8
);

String content = Files.readString(
    path,
    StandardCharsets.UTF_8
);

long size = Files.size(path);

boolean exists = Files.exists(path);
```

### Common Operations

```java
Files.createFile(path);
Files.createDirectories(directory);
Files.copy(source, destination);
Files.move(source, destination);
Files.deleteIfExists(path);
Files.list(directory);
Files.walk(directory);
```

### Important Rules

- `Files.list()` lists immediate entries.
- `Files.walk()` traverses a directory tree.
- Streams returned by `Files.list()` and `Files.walk()` should be closed.
- `Files.exists()` is not a guarantee that a later operation will succeed.
- `Path.normalize()` does not resolve symbolic links or guarantee path security.
- `Files.readString()` and `Files.writeString()` are available in Java 11.

### Interview One-Liner

> I prefer Path and Files for modern filesystem operations because they provide a richer API than the older File class.

---

# 9. Serialization and Deserialization

### One-liner

> **Serialization = object graph to byte representation.**

> **Deserialization = reconstruct objects from that representation.**

### Important Classes

```text
Serializable
ObjectOutputStream
ObjectInputStream
```

### Pattern Recognition

If the problem says:

```text
"Save Java objects to a file"
"Restore a serialized object"
"Persist object state in Java's serialization format"
```

Think:

```java
ObjectOutputStream
ObjectInputStream
```

### Serialization

```java
try (ObjectOutputStream out =
         new ObjectOutputStream(
             new FileOutputStream("order.ser"))) {

    out.writeObject(order);

} catch (IOException e) {
    e.printStackTrace();
}
```

### Deserialization

```java
try (ObjectInputStream in =
         new ObjectInputStream(
             new FileInputStream("order.ser"))) {

    Order order = (Order) in.readObject();

} catch (IOException | ClassNotFoundException e) {
    e.printStackTrace();
}
```

### Important Rules

- The class must implement `Serializable` for ordinary default Java serialization.
- `serialVersionUID` helps manage version compatibility.
- `transient` fields are skipped by default serialization.
- Static fields belong to the class, not to the serialized instance.
- Serialization is not encryption.
- Never deserialize untrusted data without appropriate safeguards.
- JSON is often a better choice for APIs and cross-language communication.

### Interview One-Liner

> Java serialization converts an object graph into a byte representation, and deserialization reconstructs it; untrusted serialized data creates security risks.

---

# 10. NIO Channels

### One-liner

> **Channel = data transfer; Buffer = temporary storage for transferred data.**

### Important Types

```text
FileChannel
ReadableByteChannel
WritableByteChannel
SocketChannel
ServerSocketChannel
DatagramChannel
```

### Pattern Recognition

If the problem says:

```text
"Access a file at a specific position"
"Transfer data between channels"
"Read or write using buffers"
"Use lower-level file I/O controls"
```

Think:

```java
FileChannel
```

### Example

```java
try (FileChannel channel =
         FileChannel.open(
             Path.of("orders.txt"),
             StandardOpenOption.READ)) {

    ByteBuffer buffer = ByteBuffer.allocate(8192);
    int count;

    while ((count = channel.read(buffer)) != -1) {
        buffer.flip();

        // Process the bytes in the buffer.

        buffer.clear();
    }
}
```

### Important Methods

```java
read(buffer)
write(buffer)
position()
size()
truncate()
transferTo()
transferFrom()
force()
```

### Interview One-Liner

> FileChannel supports buffer-based file I/O, positioning, and transfer operations. Its operations are blocking; some network channel types support non-blocking I/O.

---

# 11. ByteBuffer

### One-liner

> **ByteBuffer = temporary memory used to store bytes during channel operations.**

### Four Important Properties

```text
capacity → Total element capacity
position → Next read/write position
limit    → Current boundary
mark     → Optional saved position
```

### Essential Methods

| Method | Purpose |
|---|---|
| `allocate(size)` | Create a heap buffer |
| `allocateDirect(size)` | Request a direct buffer |
| `put()` | Write into the buffer |
| `get()` | Read from the buffer |
| `flip()` | Prepare existing data for reading |
| `clear()` | Reset for reuse |
| `rewind()` | Reset position to zero |
| `compact()` | Preserve unread data and make room for more |
| `hasRemaining()` | Check whether elements remain |

### Memory Trick

```text
Fill
  ↓
flip()
  ↓
Read
  ↓
clear()
  ↓
Reuse
```

### Important Rules

- `flip()` sets the limit to the current position and resets position to zero.
- `clear()` resets position and limit; it does not erase data.
- `rewind()` resets position but preserves the limit.
- `compact()` preserves unread elements.
- Direct buffers are not automatically faster than heap buffers.

### Interview One-Liner

> ByteBuffer manages data and its position, limit, and capacity during channel-based I/O.

---

# 12. Important Comparisons

## Byte Streams vs Character Streams

```text
Byte Streams
    ↓
Raw bytes
    ↓
Binary data

Character Streams
    ↓
Decoded characters
    ↓
Text data
```

## File vs Path vs Files

```text
File
    ↓
Older filesystem abstraction

Path
    ↓
Represents a filesystem path

Files
    ↓
Filesystem operations
```

## BufferedReader vs Scanner

```text
BufferedReader
→ Efficient line-oriented text reading
→ No automatic primitive parsing

Scanner
→ Token-based parsing
→ Can parse integers and other values
```

## Files.copy() vs FileChannel

```text
Files.copy()
→ Simple file copy

FileChannel
→ More control over positions and channel transfers
```

## Blocking vs Non-Blocking vs Asynchronous

```text
Blocking
→ Waits for I/O progress

Non-blocking
→ Returns without waiting for data when supported

Asynchronous
→ Completion reported later through a future or callback
```

These are related but distinct I/O models.

---

# 13. Common I/O Exceptions

| Exception | Meaning |
|---|---|
| `IOException` | General I/O failure |
| `FileNotFoundException` | A file cannot be opened or found by the relevant API |
| `NoSuchFileException` | Filesystem entry does not exist |
| `AccessDeniedException` | Access is denied |
| `EOFException` | Unexpected end of input in APIs that use this exception |
| `ClassNotFoundException` | A class needed during deserialization cannot be found |

### Golden Rule

```text
Handle errors
    +
Close resources
    +
Avoid swallowing exceptions
```

---

# 14. Common Interview Mistakes

1. Using `byte` instead of `int` for `read()`.
2. Forgetting that EOF is `-1`.
3. Confusing bytes with decoded characters.
4. Ignoring character encoding.
5. Forgetting try-with-resources.
6. Assuming `FileOutputStream` preserves existing contents.
7. Assuming `flush()` guarantees physical storage durability.
8. Loading huge files entirely into memory.
9. Forgetting to close `Files.list()` and `Files.walk()` streams.
10. Confusing `File`, `Path`, and `Files`.
11. Assuming `Files.exists()` prevents race conditions.
12. Deserializing untrusted data.
13. Forgetting `flip()` or `clear()` when reusing a buffer.
14. Assuming one channel write consumes every remaining byte.
15. Assuming NIO or buffering always improves performance.

---

# 15. Top Interview Questions

**Q1. What is Java I/O?**

APIs for reading and writing data from files, streams, and other sources or destinations.

**Q2. What is the difference between byte and character streams?**

Byte streams process bytes; character streams process decoded characters.

**Q3. Why does read() return int?**

It must represent all byte values from 0 to 255 and the EOF value `-1`.

**Q4. Why use buffering?**

Buffering can reduce frequent underlying I/O operations and improve throughput.

**Q5. What is try-with-resources?**

A construct that automatically closes resources implementing `AutoCloseable`.

**Q6. What is the difference between File, Path and Files?**

File is an older abstraction, Path represents a path, and Files provides filesystem operations.

**Q7. What is serialization?**

Converting an object graph into a byte representation that can later be reconstructed.

**Q8. What is FileChannel?**

A channel supporting file reading, writing, positioning, and transfer operations.

**Q9. What does flip() do?**

It prepares a buffer's current contents for reading.

**Q10. How do you process a large text file?**

Use incremental reading, such as BufferedReader, instead of loading the entire file into memory.

**Q11. Does NIO always outperform traditional I/O?**

No. Performance depends on the workload and implementation.

**Q12. How do you prevent resource leaks?**

Use try-with-resources and handle failures appropriately.

---

# 16. Fastest Pattern Recognition Cheat Sheet

```text
"Binary file"
    → InputStream / OutputStream

"Text file"
    → Reader / Writer

"Read line-by-line"
    → BufferedReader

"Write text efficiently"
    → BufferedWriter

"Read entire small text file"
    → Files.readString()

"Create/copy/move/delete file"
    → Files

"File path"
    → Path

"Save Java object"
    → ObjectOutputStream

"Restore Java object"
    → ObjectInputStream

"Precise file positioning"
    → FileChannel

"Channel data transfer"
    → FileChannel.transferTo() / transferFrom()

"Temporary byte storage"
    → ByteBuffer

"Prepare buffer for reading"
    → flip()

"Reuse buffer"
    → clear()

"Preserve unread buffer data"
    → compact()

"Automatic resource closure"
    → try-with-resources

"Consistent text encoding"
    → StandardCharsets.UTF_8

"Process huge text file"
    → BufferedReader / incremental processing
```

---

# 17. The Ultimate Mental Model

Memorize these keywords:

```text
BYTES
→ InputStream / OutputStream

TEXT
→ Reader / Writer

BUFFERING
→ Buffered* classes

FILESYSTEM
→ Path / Files

OBJECT PERSISTENCE
→ ObjectInputStream / ObjectOutputStream

CHANNEL TRANSFER
→ FileChannel

TEMPORARY DATA
→ ByteBuffer

RESOURCE SAFETY
→ try-with-resources

TEXT ENCODING
→ Charset / UTF-8
```

---

# 🏆 Final Interview Cheat Code

Before writing code, ask:

```text
What kind of data?
    ↓
Bytes or text?

What operation?
    ↓
Read, write, copy, traverse, or serialize?

What scale?
    ↓
Small file or large file?

What control is required?
    ↓
Simple API or channel/buffer operations?

What safeguards?
    ↓
Encoding, exceptions, resource closure, security?
```

Then select the API that fits the requirements.

> **Don't memorize every I/O class. Identify the data type, operation, workload, and required control — then choose the simplest suitable API.**
