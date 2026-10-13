
# Java NIO Channels — Interview Cheat Sheet

## 1. What Is a Channel?

A Channel is an abstraction for transferring data between a Java program and an entity such as a file or network connection.

Channels commonly work with `ByteBuffer` objects.

**Where to use it:** File processing, file copying, random file access, and network I/O.

## 2. Important Channel Types

- `FileChannel` — reads, writes, and transfers file data.
- `ReadableByteChannel` — reads bytes into a buffer.
- `WritableByteChannel` — writes bytes from a buffer.
- `SocketChannel` — communicates over a TCP connection.
- `ServerSocketChannel` — accepts incoming TCP connections.
- `DatagramChannel` — sends and receives UDP datagrams.
- `Channels` — utility class that bridges streams and channels.

## 3. Streams vs Channels

| Streams | Channels |
|---|---|
| Use stream read/write APIs | Work with buffers |
| Commonly process data sequentially | Can support position-based file access |
| Traditional file streams are blocking | Some channel types support non-blocking I/O |
| Usually require application-managed copying | Support channel-to-channel transfer operations |

NIO is not automatically faster; performance depends on the workload.

## 4. Open a FileChannel

```java
Path path = Path.of("orders.txt");

try (FileChannel channel =
         FileChannel.open(path, StandardOpenOption.READ)) {

    System.out.println("Size: " + channel.size());

} catch (IOException e) {
    e.printStackTrace();
}
```

Common options:

- `READ` — open for reading.
- `WRITE` — open for writing.
- `CREATE` — create the file if absent.
- `TRUNCATE_EXISTING` — remove existing contents when opening for writing.
- `APPEND` — request writes at the end of the file.

Use `try-with-resources` to close channels reliably.

## 5. Read Using a Channel

```java
try (FileChannel channel =
         FileChannel.open(path, StandardOpenOption.READ)) {

    ByteBuffer buffer = ByteBuffer.allocate(8192);
    int bytesRead;

    while ((bytesRead = channel.read(buffer)) != -1) {
        buffer.flip();

        while (buffer.hasRemaining()) {
            System.out.print((char) buffer.get());
        }

        buffer.clear();
    }
}
```

This example treats bytes as characters and is suitable only for compatible single-byte text. For UTF-8 text, use a proper `CharsetDecoder` or a higher-level text API.

## 6. Write Using a Channel

```java
Path outputPath = Path.of("output.txt");
ByteBuffer buffer = StandardCharsets.UTF_8.encode("Order saved");

try (FileChannel channel = FileChannel.open(
        outputPath,
        StandardOpenOption.CREATE,
        StandardOpenOption.WRITE,
        StandardOpenOption.TRUNCATE_EXISTING)) {

    while (buffer.hasRemaining()) {
        channel.write(buffer);
    }
}
```

The write loop is important because one call may not write all remaining bytes.

## 7. Important FileChannel Methods

- `read(buffer)` — reads bytes into a buffer.
- `write(buffer)` — writes bytes from a buffer.
- `position()` — returns the current file position.
- `position(long)` — changes the file position.
- `size()` — returns the file size.
- `truncate(size)` — reduces the file to the specified size.
- `transferTo()` — transfers data to another channel.
- `transferFrom()` — transfers data from another channel.
- `force(true)` — requests that content and metadata updates be forced to storage.

## 8. Real-World Example

A backend service generates a large report and needs to copy it to an archive without loading the entire file into memory.

A `FileChannel` and a reusable `ByteBuffer` can transfer the data in chunks. For straightforward copies, consider `Files.copy()` first.

## 9. Common Mistakes

- Forgetting to close the channel.
- Assuming one `write()` call writes everything.
- Forgetting `flip()` before consuming data from a buffer.
- Forgetting `clear()` before reusing a buffer for reading.
- Assuming all channels support non-blocking I/O.
- Assuming NIO always performs better than traditional I/O.

## 10. Interview Questions

**Q1. What is a channel?**

An abstraction for reading data from or writing data to a source or destination.

**Q2. What is FileChannel?**

A channel used for file reading, writing, positioning, and transfer operations.

**Q3. Why does FileChannel use ByteBuffer?**

The buffer holds the bytes transferred between the channel and the application.

**Q4. Does FileChannel support non-blocking I/O?**

No. FileChannel operations are blocking. Some selectable network channels support non-blocking operation.

**Q5. What is the advantage of transferTo()?**

It provides an API for transferring file data directly to another channel, potentially reducing application-level copying.

## 11. Final Mental Model

Channel = data transfer.

ByteBuffer = temporary memory holding transferred data.

FileChannel = file operations with positioning and transfer capabilities.
