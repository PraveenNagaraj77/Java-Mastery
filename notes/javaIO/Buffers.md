
# Java NIO Buffers — Interview Cheat Sheet

## 1. What Is a Buffer?

A Buffer is a temporary memory area used to hold data while it is being read or written.

Java NIO commonly uses `ByteBuffer` for byte-based I/O.

**Where to use it:** FileChannel operations, network I/O, and data processing.

## 2. Important Buffer Properties

Every buffer has four important properties:

- `capacity` — total number of elements the buffer can hold.
- `position` — location of the next read or write.
- `limit` — boundary beyond which reading or writing is not allowed.
- `mark` — an optional saved position that can be restored.

Capacity does not change after allocation. Position and limit change as the buffer is used.

## 3. Buffer Lifecycle

Typical write-then-read workflow:

1. Allocate a buffer.
2. Put data into it, or let a channel read into it.
3. Call `flip()`.
4. Read the available data, or let a channel write it.
5. Call `clear()` to reuse the buffer.

## 4. Example

```java
ByteBuffer buffer = ByteBuffer.allocate(10);

buffer.put((byte) 65);
buffer.put((byte) 66);
buffer.put((byte) 67);

System.out.println(buffer.position()); // 3
System.out.println(buffer.limit());    // 10

buffer.flip();

System.out.println(buffer.position()); // 0
System.out.println(buffer.limit());    // 3

while (buffer.hasRemaining()) {
    System.out.println((char) buffer.get());
}

buffer.clear();

System.out.println(buffer.position()); // 0
System.out.println(buffer.limit());    // 10
```

Output:

```text
3
10
0
3
A
B
C
0
10
```

## 5. Important Methods

| Method | Purpose |
|---|---|
| `allocate(size)` | Creates a heap buffer |
| `allocateDirect(size)` | Requests a direct buffer |
| `put(value)` | Writes data into the buffer |
| `get()` | Reads data from the buffer |
| `flip()` | Prepares written data for reading |
| `clear()` | Resets the buffer for writing |
| `rewind()` | Resets position to zero while retaining the limit |
| `compact()` | Keeps unread data and shifts it to the beginning |
| `hasRemaining()` | Checks whether data remains before the limit |
| `remaining()` | Returns `limit - position` |
| `mark()` | Saves the current position |
| `reset()` | Returns to the saved mark |

## 6. flip() vs clear() vs rewind()

### flip()

Use after writing data into a buffer, before reading that data.

- Limit becomes the current position.
- Position becomes zero.

### clear()

Use when you want to reuse the entire buffer for new data.

- Position becomes zero.
- Limit becomes capacity.
- Existing bytes are not erased.

### rewind()

Use when you want to read the same data again.

- Position becomes zero.
- Limit stays unchanged.

## 7. What Does compact() Do?

`compact()` preserves unread elements and moves them to the start of the buffer. The position is set after the preserved data so new data can be appended.

This is useful when a multistep processing operation leaves some data unread.

## 8. Heap vs Direct Buffers

**Heap buffer:**

```java
ByteBuffer buffer = ByteBuffer.allocate(1024);
```

- Backed by a Java heap array.
- Usually convenient for ordinary application processing.
- Managed as part of heap memory.

**Direct buffer:**

```java
ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
```

- Uses memory outside the ordinary Java heap.
- Can be useful for I/O-intensive workloads.
- Allocation and cleanup can be more expensive.

Direct buffers are not automatically faster. Benchmark the actual workload.

## 9. Buffer Types

- `ByteBuffer`
- `CharBuffer`
- `IntBuffer`
- `LongBuffer`
- `FloatBuffer`
- `DoubleBuffer`
- `ShortBuffer`

`ByteBuffer` is especially important for file and network channels.

## 10. Common Mistakes

- Calling `get()` before `flip()` when the buffer is in write mode.
- Forgetting to clear or compact before the next write cycle.
- Assuming `clear()` erases data.
- Confusing capacity with limit.
- Assuming a direct buffer always improves performance.
- Forgetting that `get()` advances position.

## 11. Interview Questions

**Q1. What is a ByteBuffer?**

A buffer that stores bytes and tracks position, limit, capacity, and optionally a mark.

**Q2. What does flip() do?**

It prepares the data currently in the buffer for reading by setting the limit to the current position and resetting position to zero.

**Q3. What does clear() do?**

It resets position and limit so the entire buffer can be reused. It does not erase the data.

**Q4. What is compact()?**

It preserves unread data and moves it to the beginning of the buffer.

**Q5. What is the difference between heap and direct buffers?**

Heap buffers use Java heap memory; direct buffers use memory outside the ordinary heap and may be useful for certain I/O workloads.

## 12. Final Mental Model

Capacity = total space.

Position = next location.

Limit = current boundary.

flip() = prepare to read.

clear() = prepare to write new data.

rewind() = read again.

compact() = preserve unread data and make room for more.
