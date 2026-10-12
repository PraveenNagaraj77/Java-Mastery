
# NIO.2 File System — Interview Cheat Sheet

## 1. What Is NIO.2?

**NIO.2** is the enhanced Java file-system API introduced in Java 7. It provides modern APIs for representing paths, performing file operations, inspecting metadata, and traversing directories.

In Java 11, the main APIs are:

| API | Responsibility |
|---|---|
| `Path` | Represents a file or directory path |
| `Paths` | Provides utility methods for creating paths |
| `Files` | Performs file-system operations |
| `BasicFileAttributes` | Represents standard file metadata |
| `DirectoryStream` | Iterates over entries in a directory |
| `FileVisitor` | Supports customized file-tree traversal |

### Why Do We Need NIO.2?

The older `java.io.File` API supports basic file operations, but it provides limited error information for some operations and less flexible file-system functionality.

NIO.2 provides:
- Richer file operations.
- Better file attribute access.
- Directory traversal.
- Symbolic-link support.
- More detailed exception handling.
- Convenient Java 11 text-file APIs.

### Where Should We Use It?

Use NIO.2 for application configuration files, reports, file uploads, directory processing, backups, and file-system utilities.

---

## 2. Path vs Paths vs Files

These three are fundamental NIO.2 concepts.

| API | Purpose | Example |
|---|---|---|
| `Path` | Represents a location | `Path.of("data/orders.txt")` |
| `Paths` | Creates path objects | `Paths.get("data/orders.txt")` |
| `Files` | Performs operations | `Files.exists(path)` |

### Example

```java
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExample {

    public static void main(String[] args) {

        Path path1 = Path.of("data", "orders.txt");
        Path path2 = Paths.get("data", "orders.txt");

        System.out.println(path1);
        System.out.println(path2);
        System.out.println(path1.equals(path2));
    }
}
```

Both paths represent the same relative location on a typical file system, so the equality check returns `true`.

**Important:** A `Path` does not create a file. It only represents a path. Use `Files` to perform operations on the file system.

`Path.of()` is available in Java 11, making it appropriate for this project.

---

## 3. Relative Paths and Absolute Paths

A relative path is resolved against the application's working directory.

An absolute path identifies a location from the root of the file system.

```java
import java.nio.file.Path;

public class PathDetailsExample {

    public static void main(String[] args) {

        Path path = Path.of("data", "orders.txt");

        System.out.println("Relative: " + path);
        System.out.println("Absolute: " + path.toAbsolutePath());
        System.out.println("File name: " + path.getFileName());
        System.out.println("Parent: " + path.getParent());

        System.out.println(
                "Working directory: " +
                System.getProperty("user.dir")
        );
    }
}
```

### Important Methods

- `getFileName()` — returns the final name in the path.
- `getParent()` — returns the parent path, or `null` when there is no parent.
- `toAbsolutePath()` — converts the path to an absolute path.
- `normalize()` — removes redundant `.` and resolvable `..` components lexically.
- `resolve()` — combines a path with another path.
- `relativize()` — calculates a relative path between compatible paths.

### Important Rule

A relative path is resolved against the working directory, not automatically against the Java source file's directory.

`normalize()` does not access the file system and does not prove that the resulting path exists or stays within a particular directory.

---

## 4. Creating Files and Directories

NIO.2 provides convenient methods for creating files and directories.

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CreateFileExample {

    public static void main(String[] args) throws IOException {

        Path directory = Path.of("data", "orders");
        Path file = directory.resolve("order-1001.txt");

        Files.createDirectories(directory);

        if (Files.notExists(file)) {
            Files.createFile(file);
        }

        System.out.println(
                "File location: " + file.toAbsolutePath()
        );
    }
}
```

### Important Methods

| Method | Purpose |
|---|---|
| `Files.createFile(path)` | Creates a new file |
| `Files.createDirectory(path)` | Creates one directory |
| `Files.createDirectories(path)` | Creates missing parent directories |
| `path.resolve(name)` | Combines a parent path with a child path |

### Important Difference

`createDirectory()` requires the parent directory to exist.

`createDirectories()` creates missing parent directories as needed.

`createFile()` fails if the file already exists.

**Concurrency note:** Checking `notExists()` and then calling `createFile()` involves two operations. Another process may create the file between them. Handle `FileAlreadyExistsException` when necessary.

---

## 5. Reading and Writing Text Files in Java 11

Java 11 provides `Files.readString()` and `Files.writeString()` for convenient text-file operations.

```java
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextFileExample {

    public static void main(String[] args) throws IOException {

        Path file = Path.of("data", "order.txt");

        Files.createDirectories(file.getParent());

        Files.writeString(
                file,
                "Order ID: ORD-1001\nStatus: CONFIRMED\n",
                StandardCharsets.UTF_8
        );

        String content = Files.readString(
                file,
                StandardCharsets.UTF_8
        );

        System.out.println(content);
    }
}
```

### Important Methods

- `Files.readString(path)` — reads the complete file into a `String`.
- `Files.writeString(path, content)` — writes text to a file.
- `Files.newBufferedReader(path, charset)` — creates a buffered character reader.
- `Files.newBufferedWriter(path, charset)` — creates a buffered character writer.

### When Should We Use Them?

Use `readString()` and `writeString()` for reasonably sized text files.

For large files, prefer streaming or line-by-line processing to avoid holding the complete file in memory.

By default, `writeString()` creates a missing file and truncates an existing file unless appropriate options are supplied.

---

## 6. Copying, Moving, and Deleting Files

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileOperationsExample {

    public static void main(String[] args) throws IOException {

        Path source = Path.of("data", "order.txt");
        Path backup = Path.of("data", "order-backup.txt");
        Path archive = Path.of("data", "archive", "order.txt");

        Files.copy(
                source,
                backup,
                StandardCopyOption.REPLACE_EXISTING
        );

        Files.createDirectories(archive.getParent());

        Files.move(
                backup,
                archive,
                StandardCopyOption.REPLACE_EXISTING
        );

        boolean deleted = Files.deleteIfExists(archive);

        System.out.println("Deleted: " + deleted);
    }
}
```

### Important Methods

| Method | Purpose |
|---|---|
| `Files.copy()` | Copies a file |
| `Files.move()` | Moves or renames a file |
| `Files.delete()` | Deletes a path |
| `Files.deleteIfExists()` | Deletes a path if it exists |

### Important Notes

- `REPLACE_EXISTING` requests replacement of an existing destination.
- `ATOMIC_MOVE` requests an atomic move when supported.
- Moving across file systems may require a non-atomic strategy or may fail.
- Deleting a non-empty directory generally fails.
- These operations can throw `IOException`.

---

## 7. Checking File Metadata

NIO.2 can inspect file properties without reading the file contents.

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class FileMetadataExample {

    public static void main(String[] args) throws IOException {

        Path path = Path.of("data", "order.txt");

        System.out.println("Exists: " + Files.exists(path));
        System.out.println("Regular file: " + Files.isRegularFile(path));
        System.out.println("Directory: " + Files.isDirectory(path));
        System.out.println("Readable: " + Files.isReadable(path));

        if (Files.exists(path)) {

            BasicFileAttributes attributes =
                    Files.readAttributes(
                            path,
                            BasicFileAttributes.class
                    );

            System.out.println("Size: " + attributes.size());
            System.out.println(
                    "Created: " + attributes.creationTime()
            );
            System.out.println(
                    "Modified: " + attributes.lastModifiedTime()
            );
        }
    }
}
```

### Common Metadata Methods

- `Files.size(path)` — size in bytes.
- `Files.getLastModifiedTime(path)` — last modification time.
- `Files.isRegularFile(path)` — checks whether a path represents a regular file.
- `Files.isDirectory(path)` — checks whether it represents a directory.
- `Files.isReadable(path)` — checks whether the file appears readable.
- `Files.readAttributes()` — reads a group of file attributes.

**Important:** These checks do not guarantee that a subsequent operation will succeed. The file may change or permissions may be altered between checking and using it.

---

## 8. Listing Directory Contents with Files.list()

`Files.list()` returns a stream of immediate entries in a directory.

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirectoryListExample {

    public static void main(String[] args) throws IOException {

        Path directory = Path.of("data");

        try (var entries = Files.list(directory)) {

            entries.forEach(System.out::println);

        }
    }
}
```

### How It Works

1. `Files.list()` opens the directory for iteration.
2. It returns a lazy `Stream<Path>`.
3. The stream processes entries as needed.
4. Try-with-resources closes the stream and its associated directory resource.

It lists only the immediate entries, not every nested file.

---

## 9. Recursive Directory Traversal with Files.walk()

`Files.walk()` traverses a directory tree, including nested directories.

Example directory structure:

```text
data/
├── orders.txt
├── reports/
│   ├── daily.csv
│   └── monthly/
│       └── summary.csv
└── archive/
    └── old.txt
```

### Example

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirectoryWalkExample {

    public static void main(String[] args) throws IOException {

        Path root = Path.of("data");

        try (var paths = Files.walk(root)) {

            paths.filter(Files::isRegularFile)
                 .forEach(System.out::println);

        }
    }
}
```

### Files.list() vs Files.walk()

| Feature | `Files.list()` | `Files.walk()` |
|---|---|---|
| Immediate entries | Yes | Yes |
| Nested directories | No | Yes |
| Returns a stream | Yes | Yes |
| Requires closing | Yes | Yes |
| Typical use | Display folder contents | Scan a directory tree |

By default, `Files.walk()` does not follow symbolic links.

For custom processing, filtering, or controlling how each file is visited, Java also provides `FileVisitor` and `walkFileTree()`.

---

## 10. Exception Handling in NIO.2

Common file-system exceptions include:

| Exception | Meaning |
|---|---|
| `NoSuchFileException` | A requested path does not exist |
| `FileAlreadyExistsException` | A path already exists where a new one is being created |
| `AccessDeniedException` | Access is denied |
| `DirectoryNotEmptyException` | A directory cannot be deleted because it is not empty |
| `IOException` | A general I/O failure |

### Example

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class SafeFileReadExample {

    public static void main(String[] args) {

        Path file = Path.of("data", "order.txt");

        try {

            String content = Files.readString(file);
            System.out.println(content);

        } catch (NoSuchFileException e) {

            System.err.println("The requested file was not found.");

        } catch (IOException e) {

            System.err.println(
                    "File operation failed: " + e.getMessage()
            );
        }
    }
}
```

Catch specific exceptions when different failures require different handling. Use a general `IOException` handler for other I/O failures.

---

## 11. java.io.File vs NIO.2

| Feature | `java.io.File` | NIO.2 |
|---|---|---|
| Path representation | `File` | `Path` |
| File operations | Basic methods | Rich `Files` API |
| Error information | Often limited boolean results | Exceptions often provide more detail |
| Metadata | Basic methods | Attribute APIs |
| Directory traversal | Basic listing | Streams and file-tree traversal |
| Java 11 text APIs | Stream/reader approach | `readString()` and `writeString()` |

The older `File` API remains useful in existing applications, but `Path` and `Files` are generally preferred for new file-system code.

---

## 12. Common Mistakes

### Mistake 1: Assuming Path Creates a File

```java
Path path = Path.of("data/order.txt");
```

This only creates a path object. It does not create the file.

### Mistake 2: Confusing createDirectory() and createDirectories()

Use `createDirectory()` for one directory whose parent exists. Use `createDirectories()` when parent directories may be missing.

### Mistake 3: Loading a Huge File into Memory

`Files.readString()` loads the entire file into a `String`. Use streaming for large files.

### Mistake 4: Forgetting to Close Directory Streams

The streams returned by `Files.list()` and `Files.walk()` should be closed. Try-with-resources is the recommended approach.

### Mistake 5: Assuming a File Check Guarantees Access

`Files.exists()` and `Files.isReadable()` do not guarantee that a later operation will succeed.

### Mistake 6: Assuming normalize() Resolves the Actual File

`normalize()` operates on path components lexically. It does not prove that the target exists or resolve symbolic links.

---

## 13. Common Interview Questions

### Q1. What is NIO.2?

The enhanced Java file-system API introduced in Java 7.

### Q2. What is the difference between Path and Files?

`Path` represents a location, while `Files` performs operations on that location.

### Q3. Does creating a Path create a file?

No. A separate file-system operation is required.

### Q4. What is the difference between createDirectory() and createDirectories()?

`createDirectory()` creates one directory; `createDirectories()` creates missing parent directories as needed.

### Q5. What is the difference between Files.list() and Files.walk()?

`list()` returns immediate directory entries, while `walk()` traverses nested directories.

### Q6. What does Files.readString() do?

It reads the complete file into a `String`. It was introduced in Java 11.

### Q7. What does resolve() do?

It combines a path with another path, commonly to identify a child file.

### Q8. What is BasicFileAttributes?

An interface that provides standard metadata such as size, creation time, modification time, and file type.

### Q9. Why use try-with-resources for Files.list()?

The returned stream manages an underlying directory resource and should be closed.

### Q10. Can Files.exists() guarantee that a later read will succeed?

No. The file system may change between checking and performing the operation.

---

## 14. Coding Practice

1. Create a nested directory structure and a text file inside it.
2. Write an order summary using `Files.writeString()` and read it using `Files.readString()`.
3. Copy a file into a backup location and move the backup into an archive directory.
4. Print all regular files under a directory using `Files.walk()`.
5. Display the size and last modification time of every regular file.
6. Handle missing files using `NoSuchFileException`.
7. Compare `Files.list()` and `Files.walk()` on a nested directory structure.

---

## 15. Interview Explanation

"NIO.2 is Java's modern file-system API. `Path` represents a file or directory location, and `Files` provides methods for creating, reading, writing, copying, moving, deleting, and inspecting files. I use `Files.readString()` and `writeString()` for reasonably sized text files in Java 11, and streaming APIs for larger files. For directory traversal, I use `Files.list()` for immediate entries and `Files.walk()` for nested directories. I also use try-with-resources and handle `IOException` and more specific file-system exceptions."

---

## 16. 10-Second Cheat Sheet

- `Path` — represents a location.
- `Files` — performs file operations.
- `Path.of()` — creates a path in Java 11.
- `Files.createDirectories()` — creates missing directories.
- `Files.readString()` / `Files.writeString()` — convenient Java 11 text operations.
- `Files.copy()` / `Files.move()` / `Files.deleteIfExists()` — file management.
- `Files.readAttributes()` — reads metadata.
- `Files.list()` — immediate directory entries.
- `Files.walk()` — recursive directory traversal.
- Use try-with-resources for I/O resources.

---

## 17. Final Mental Model

```text
Path
  |
  | Identifies a location
  v
Files
  |
  | Create / Read / Write / Copy / Move / Delete
  | Inspect attributes
  | List / Traverse directories
  v
Operating System File System
```

**Remember:** `Path` identifies where something is; `Files` defines what you can do with it.
