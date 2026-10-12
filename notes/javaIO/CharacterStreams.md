
# Character Streams — Interview Cheat Sheet

## 1. What Are Character Streams?

Character streams are Java I/O streams designed to read and write text data.

They convert between encoded bytes and Java characters using a character encoding such as UTF-8.

The two abstract base classes are:

- `Reader` — reads characters.
- `Writer` — writes characters.

Common implementations:

- `FileReader`
- `FileWriter`
- `BufferedReader`
- `BufferedWriter`
- `InputStreamReader`
- `OutputStreamWriter`

**Example:** Reading a `.txt` file, processing application logs, or generating a text report.

---

## 2. Why Do We Need Character Streams?

Files store data as bytes. Text must be decoded into characters before Java can process it as readable text.

Consider a file containing:

```text
Hello Java
Welcome to programming
```

A byte stream reads raw bytes. A character stream decodes those bytes according to a charset and provides character data.

Character streams are useful because they:

- Handle text encoding and decoding.
- Make text processing easier.
- Support character-oriented reading and writing.
- Provide convenient line-based processing through `BufferedReader`.
- Reduce repetitive low-level byte-processing code.

### Where to Use Them

- Reading text files.
- Processing application logs.
- Reading configuration files.
- Processing CSV and other text-based formats.
- Generating reports.
- Reading user-readable text from streams.

---

## 3. Internal Working

### Reading Text

The general flow is:

```text
Text file
   |
   v
Encoded bytes
   |
   v
Charset decoder
   |
   v
Java characters
   |
   v
Application logic
```

### Writing Text

```text
Java characters
   |
   v
Charset encoder
   |
   v
Encoded bytes
   |
   v
Text file
```

Character streams operate on characters at the Java API level, but files still contain bytes.

The encoding and decoding process translates between these representations.

### Important Base Classes

#### Reader

`Reader` is the abstract base class for character input.

Important methods:

```java
int read()
int read(char[] buffer)
int read(char[] buffer, int offset, int length)
void close()
```

`read()` returns a character value as an `int`, or `-1` when the end of the stream is reached.

A Java `char` represents a UTF-16 code unit. A supplementary Unicode character may require two code units.

#### Writer

`Writer` is the abstract base class for character output.

Important methods:

```java
void write(int c)
void write(char[] buffer)
void write(String text)
void flush()
void close()
```

`flush()` pushes buffered output toward the underlying destination. `close()` closes the stream and flushes pending output.

---

## 4. Important Character Stream Classes

| Class | Purpose |
|---|---|
| `Reader` | Abstract character input |
| `Writer` | Abstract character output |
| `FileReader` | Reads text from a file |
| `FileWriter` | Writes text to a file |
| `BufferedReader` | Buffers character input and reads lines |
| `BufferedWriter` | Buffers character output |
| `InputStreamReader` | Converts bytes into characters |
| `OutputStreamWriter` | Converts characters into bytes |

### Important Relationships

```text
Reader
  |
  +-- InputStreamReader
  |      |
  |      +-- FileReader
  |
  +-- BufferedReader

Writer
  |
  +-- OutputStreamWriter
  |      |
  |      +-- FileWriter
  |
  +-- BufferedWriter
```

This is a simplified conceptual hierarchy; `BufferedReader` and `BufferedWriter` wrap other readers and writers.

**Interview point:** `InputStreamReader` bridges byte streams and character streams. `FileReader` is a convenient file-reading class built on this concept.

---

## 5. Character Encoding and UTF-8

Character encoding defines how text is represented as bytes.

UTF-8 is a widely used Unicode encoding.

Examples:

- `A` typically uses one byte in UTF-8.
- Many Tamil characters use multiple bytes.
- Many emoji use four bytes in UTF-8.

The exact number of bytes depends on the character.

### Why Casting Bytes to `char` Is Not Enough

This code reads bytes, not decoded text:

```java
int data;

while ((data = input.read()) != -1) {
    System.out.print((char) data);
}
```

It may work for simple ASCII text but is not a correct general solution for UTF-8 text.

A multibyte UTF-8 character must be decoded as a sequence of bytes.

### Specify UTF-8 Explicitly

```java
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ReadTextExample {
    public static void main(String[] args) {
        try (FileReader reader =
                     new FileReader("sample.txt", StandardCharsets.UTF_8)) {

            int data;

            while ((data = reader.read()) != -1) {
                System.out.print((char) data);
            }

        } catch (IOException e) {
            System.err.println("Unable to read file: " + e.getMessage());
        }
    }
}
```

The charset-aware `FileReader` constructor is available in Java 11 and later.

**Important:** Casting each returned value to `char` does not combine a surrogate pair into one supplementary Unicode character. Use code-point-aware processing when working with Unicode code points.

---

## 6. Writing Text Using FileWriter

`FileWriter` writes character data to a file.

```java
package com.javamastery.javaIO.streams.characterStreams;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class WriteTextExample {
    public static void main(String[] args) {
        String message = "Hello Java!\nWelcome to Character Streams.";

        try (FileWriter writer =
                     new FileWriter("output.txt", StandardCharsets.UTF_8)) {

            writer.write(message);

            System.out.println("Text written successfully.");

        } catch (IOException e) {
            System.err.println("Unable to write text: " + e.getMessage());
        }
    }
}
```

### How It Works

1. A `String` stores the message.
2. `FileWriter` encodes the characters using UTF-8.
3. The encoded bytes are written to `output.txt`.
4. Try-with-resources closes the writer automatically.

### Append Instead of Overwrite

The normal file-writing constructor overwrites an existing file.

To append:

```java
try (FileWriter writer =
         new FileWriter("output.txt", StandardCharsets.UTF_8, true)) {
    writer.write("\nNew log entry");
}
```

The third argument, `true`, enables append mode.

---

## 7. Reading Text Line by Line with BufferedReader

`BufferedReader` provides buffered character input and the convenient `readLine()` method.

It is useful when processing large text files one line at a time.

```java
package com.javamastery.javaIO.streams.characterStreams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ReadLinesExample {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(
                new FileReader("sample.txt", StandardCharsets.UTF_8))) {

            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.err.println("Unable to read file: " + e.getMessage());
        }
    }
}
```

### How readLine() Works

- Returns the next line as a `String`.
- Removes the line terminator from the returned string.
- Returns `null` when the end of the stream is reached.

### End-of-File Comparison

| Method | EOF value |
|---|---|
| `InputStream.read()` | `-1` |
| `Reader.read()` | `-1` |
| `BufferedReader.readLine()` | `null` |

**Interview tip:** Always check `readLine()` against `null`, not `-1` or an empty string.

An empty line is a valid line and may return `""`.

---

## 8. Writing Text Using BufferedWriter

`BufferedWriter` collects characters in a buffer before passing them to the underlying writer.

This can reduce the frequency of write operations.

```java
package com.javamastery.javaIO.streams.characterStreams;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class WriteLinesExample {
    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("report.txt", StandardCharsets.UTF_8))) {

            writer.write("Daily Report");
            writer.newLine();

            writer.write("Orders processed: 120");
            writer.newLine();

            writer.write("Status: Completed");
            writer.newLine();

            System.out.println("Report created successfully.");

        } catch (IOException e) {
            System.err.println("Unable to write report: " + e.getMessage());
        }
    }
}
```

### Important Methods

- `write(String)` — writes text.
- `newLine()` — writes the platform-appropriate line separator.
- `flush()` — flushes buffered output.
- `close()` — closes the writer and flushes pending output.

Try-with-resources handles closing automatically.

---

## 9. Character Streams vs Byte Streams

| Feature | Byte Streams | Character Streams |
|---|---|---|
| Base classes | `InputStream`, `OutputStream` | `Reader`, `Writer` |
| Data handled by API | Bytes | Characters |
| Primary purpose | Binary data | Text data |
| Typical examples | Images, PDFs, ZIP files | Text files, logs, CSV |
| Encoding | Interpretation depends on application | Encodes/decodes text |
| Common classes | `FileInputStream`, `FileOutputStream` | `FileReader`, `FileWriter` |

### Rule of Thumb

- Use byte streams for raw binary data.
- Use character streams for text.
- Specify the charset when the encoding is known.
- For simple whole-file text operations in Java 11, consider `Files.readString()` and `Files.writeString()`.

---

## 10. Java 11 Alternative: Files.readString()

For small text files, the NIO.2 API is often simpler.

### Read a File

```java
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadWholeFileExample {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("sample.txt");

        String content = Files.readString(path, StandardCharsets.UTF_8);

        System.out.println(content);
    }
}
```

### Write a File

```java
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WriteWholeFileExample {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("output.txt");

        Files.writeString(
                path,
                "Hello Java",
                StandardCharsets.UTF_8
        );

        System.out.println("File written successfully.");
    }
}
```

**Important:** `Files.readString()` loads the whole file into memory. For very large files, line-by-line processing is usually more appropriate.

---

## 11. Common Mistakes

### Mistake 1: Reading UTF-8 Text as Individual Bytes

**Problem:** Multibyte characters can be corrupted if each byte is independently cast to `char`.

**Solution:** Use a character reader with the correct charset.

### Mistake 2: Ignoring the Charset

**Problem:** The default charset can differ between environments.

**Solution:** Specify UTF-8 when the file uses UTF-8.

### Mistake 3: Incorrect EOF Condition

Incorrect:

```java
while ((line = reader.readLine()) != "") {
    System.out.println(line);
}
```

Correct:

```java
while ((line = reader.readLine()) != null) {
    System.out.println(line);
}
```

### Mistake 4: Forgetting Resource Closure

**Problem:** Resources may remain open or buffered output may not be fully written.

**Solution:** Use try-with-resources.

### Mistake 5: Accidentally Overwriting a File

**Problem:** A normal `FileWriter` constructor truncates an existing file.

**Solution:** Use append mode when appending is intended.

### Mistake 6: Assuming One char Equals One Unicode Character

**Problem:** Supplementary Unicode code points require two UTF-16 code units.

**Solution:** Use code-point-aware APIs when processing Unicode code points.

---

## 12. Interview Questions

### Q1. What are character streams?

Character streams read and write text through the `Reader` and `Writer` class families.

### Q2. What is the difference between InputStream and Reader?

`InputStream` reads bytes, while `Reader` reads decoded characters.

### Q3. What is the difference between FileReader and BufferedReader?

`FileReader` reads text from a file. `BufferedReader` adds buffering and convenient methods such as `readLine()`.

### Q4. What is the purpose of InputStreamReader?

It bridges a byte stream and a character stream by decoding bytes into characters using a charset.

### Q5. What is the purpose of OutputStreamWriter?

It bridges a character stream and a byte stream by encoding characters into bytes.

### Q6. Why should we specify UTF-8?

It makes text encoding explicit and avoids relying on an environment-dependent default charset.

### Q7. What does readLine() return at EOF?

It returns `null`.

### Q8. Why use BufferedReader?

It buffers character input and provides convenient line-based reading.

### Q9. Why use BufferedWriter?

It buffers character output and can reduce the number of underlying write operations.

### Q10. Does a character stream eliminate bytes?

No. Files store bytes. Character streams encode or decode between bytes and characters.

### Q11. Can every Unicode character fit in one Java char?

No. A Java `char` is a UTF-16 code unit. Some Unicode code points require a surrogate pair.

### Q12. When should Files.readString() be preferred?

For straightforward operations on reasonably sized text files where loading the complete file into memory is acceptable.

---

## 13. Coding Practice

### Problem 1: Read a Text File

Read a UTF-8 text file using `FileReader` and print its contents.

### Problem 2: Write a Text File

Write a message to `output.txt` using `FileWriter`.

### Problem 3: Read Line by Line

Use `BufferedReader` to print each line of a file.

### Problem 4: Generate a Report

Use `BufferedWriter` to write a report with multiple lines and `newLine()`.

### Problem 5: Process Tamil Text

Create a UTF-8 text file containing English and Tamil text. Read it using the correct charset and verify that the text is preserved.

### Problem 6: Count Lines

Read a file line by line and count the number of lines.

Hint: Increment a counter for every non-null result from `readLine()`.

---

## 14. Interview Explanation

"Java character streams are designed for text processing. `Reader` and `Writer` are their abstract base classes. Classes such as `FileReader` and `FileWriter` provide convenient file access, while `BufferedReader` and `BufferedWriter` improve efficiency. Character streams encode and decode text using a charset, so I specify UTF-8 when the file encoding is known. For line-oriented processing, I use `BufferedReader.readLine()`, which returns `null` at the end of the stream."

---

## 15. Ten-Second Cheat Sheet

- **Byte input/output:** `InputStream` / `OutputStream`
- **Character input/output:** `Reader` / `Writer`
- **Read text file:** `FileReader`
- **Write text file:** `FileWriter`
- **Read lines:** `BufferedReader.readLine()`
- **Write lines:** `BufferedWriter.newLine()`
- **Charset:** Specify UTF-8 when appropriate.
- **EOF for read():** `-1`
- **EOF for readLine():** `null`
- **Resource management:** try-with-resources
- **Small whole-file text:** `Files.readString()` / `Files.writeString()`

---

## Final Mental Model

**Byte streams handle raw bytes. Character streams handle decoded text.**

Use `Reader` and `Writer` for text, choose the correct charset, use buffering for efficient processing, and use try-with-resources to close resources safely.
