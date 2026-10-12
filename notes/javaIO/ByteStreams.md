# Byte Streams — Interview Cheat Sheet

## 1. What are Byte Streams?

Byte streams transfer raw bytes in Java.

- `InputStream`: Reads bytes.
- `OutputStream`: Writes bytes.

A byte contains 8 bits and has 256 possible bit patterns.

## 2. Why are they needed?

Byte streams are useful for:
- Copying images, PDFs, ZIP files, and other binary files.
- Reading raw file contents.
- Writing binary data.
- Transferring data between streams.

**Where to use:** Binary file processing, file copying, and raw data transfer.

## 3. Internal Working

For `InputStream.read()`:

1. Java requests the next byte.
2. The stream obtains data from its underlying source.
3. The method returns an integer from 0 to 255.
4. It returns `-1` when the stream reaches EOF.

The JVM and operating system may use buffers and filesystem caching. One `read()` call does not necessarily mean one physical disk operation.

## 4. Important Classes

| Class | Purpose |
|---|---|
| `InputStream` | Abstract base for byte input |
| `OutputStream` | Abstract base for byte output |
| `FileInputStream` | Reads bytes from a file |
| `FileOutputStream` | Writes bytes to a file |
| `BufferedInputStream` | Adds input buffering |
| `BufferedOutputStream` | Adds output buffering |
| `ByteArrayInputStream` | Reads bytes from memory |
| `ByteArrayOutputStream` | Accumulates bytes in memory |

## 5. Important Methods

### InputStream

- `read()` — reads one byte; returns `-1` at EOF.
- `read(byte[])` — reads into an array; returns the count or `-1`.
- `read(byte[], offset, length)` — reads into part of an array.
- `close()` — closes the stream.

### OutputStream

- `write(int)` — writes the low-order 8 bits of an integer.
- `write(byte[])` — writes a byte array.
- `write(byte[], offset, length)` — writes a section of an array.
- `flush()` — pushes buffered output toward the destination.
- `close()` — closes the stream.

## 6. Reading Bytes

```java
try (FileInputStream input =
         new FileInputStream("sample.txt")) {

    int data;

    while ((data = input.read()) != -1) {
        System.out.println(data);
    }
}
```

**Why `int`?** It represents byte values 0–255 and the EOF marker -1.

## 7. Writing Bytes

```java
byte[] data = {65, 66, 67};

try (FileOutputStream output =
         new FileOutputStream("output.txt")) {

    output.write(data);
}
```

For this example, the written bytes represent `ABC` in ASCII-compatible encoding.

**Important:** `FileOutputStream` overwrites an existing file by default. Use append mode when required.

## 8. Copying a File Efficiently

```java
byte[] buffer = new byte[8192];
int count;

while ((count = input.read(buffer)) != -1) {
    output.write(buffer, 0, count);
}
```

The returned count indicates how many bytes are valid in the buffer. Never assume every read fills the entire buffer.

## 9. Byte Streams vs Character Streams

| Byte Streams | Character Streams |
|---|---|
| `InputStream` / `OutputStream` | `Reader` / `Writer` |
| Raw bytes | Text characters |
| Suitable for binary data | Suitable for text processing |
| No automatic character decoding | Character decoding/encoding is handled by appropriate implementations |

Byte streams can read text-file bytes, but you need the correct character encoding to decode them properly.

## 10. Common Mistakes

1. Confusing the numeric result of `read()` with a decoded character.
2. Ignoring the `-1` EOF marker.
3. Assuming a bulk read fills the buffer.
4. Writing the entire buffer instead of only the bytes read.
5. Forgetting to close streams.
6. Accidentally truncating a destination file.
7. Loading very large files entirely into memory.

## 11. Interview Questions

**Q1. What is a byte stream?**

A stream that transfers raw bytes through `InputStream` and `OutputStream`.

**Q2. Why does `read()` return an int?**

To represent all 256 byte values plus `-1` for EOF.

**Q3. Can byte streams process text files?**

Yes, but they don't automatically decode bytes into characters.

**Q4. Why use buffering?**

It can reduce underlying I/O operations and improve performance.

**Q5. What does `write(int)` write?**

The low-order eight bits of the supplied integer.

**Q6. What happens if the source file is missing?**

Opening `FileInputStream` generally throws `FileNotFoundException`.

**Q7. How should large files be copied?**

Use buffered or chunk-based processing, or use `Files.copy()` when its behavior suits the requirement.

## 12. Coding Practice

1. Write byte values 65, 66, and 67 to a file.
2. Count the bytes in a file.
3. Copy an image or PDF and verify the copy.
4. Rewrite the copy program to use a byte array buffer.
5. Handle a missing source file gracefully.

## 13. Where to Use Byte Streams

- Image and PDF copying.
- ZIP and other binary file processing.
- Raw byte transfer.
- Low-level stream processing.
- Binary content handled by network or file APIs.

## 14. Ten-Second Cheat Sheet

- Input: `InputStream`
- Output: `OutputStream`
- One-byte read: `read()`
- EOF: `-1`
- Byte range returned by `read()`: 0–255
- Efficient copying: buffer + actual byte count
- Resource cleanup: try-with-resources
- Text decoding: use the correct character encoding

## Final Mental Model

Byte streams move raw bytes. Choose them when byte-level data preservation matters. Use buffering or chunk-based processing for efficiency, handle EOF correctly, and close resources safely.