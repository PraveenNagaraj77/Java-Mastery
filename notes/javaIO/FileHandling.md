
# File Handling — Interview Cheat Sheet

## 1. What Is File Handling?

File handling is the process of creating, reading, writing, copying, moving, deleting, and managing files and directories using Java.

It is commonly used for:

- Saving uploaded documents.
- Reading configuration files.
- Generating reports.
- Managing application logs.
- Processing files in the background.
- Creating and cleaning up temporary files.

Java provides two main approaches:

- `java.io.File` — the older filesystem API.
- `java.nio.file.Path` and `java.nio.file.Files` — the modern NIO.2 filesystem API.

**Interview point:** `Path` represents a filesystem path, while `Files` provides static methods to perform operations on that path.

---

## 2. Why Do We Need File Handling?

Applications frequently need to interact with files outside the Java program's memory.

For example, a reporting application may need to:

1. Create a report directory.
2. Generate a report file.
3. Write report content.
4. Read the file when needed.
5. Move completed reports into an archive.
6. Delete obsolete temporary files.

File handling allows Java applications to perform these operations programmatically.

### Where to Use It

- File uploads and downloads.
- Configuration management.
- Log processing.
- Report generation.
- Importing and exporting data.
- Batch processing.
- Temporary-file management.

---

## 3. File vs Path vs Files

These three concepts are important for interviews.

| API | Purpose |
|---|---|
| `File` | Represents a filesystem path using the older API |
| `Path` | Represents a filesystem path using NIO.2 |
| `Files` | Provides filesystem operations on paths |

### File

`java.io.File` represents a file or directory path.

```java
import java.io.File;

File file = new File("sample.txt");

System.out.println(file.exists());
System.out.println(file.isFile());
System.out.println(file.isDirectory());
System.out.println(file.getAbsolutePath());
```

Important methods:

- `exists()` — checks whether the path exists.
- `isFile()` — checks whether it represents a regular file.
- `isDirectory()` — checks whether it represents a directory.
- `getName()` — returns the final name component.
- `getAbsolutePath()` — returns an absolute path.
- `length()` — returns the file length in bytes.
- `mkdir()` — creates one directory.
- `mkdirs()` — creates missing parent directories.
- `delete()` — attempts to delete a file or empty directory.
- `listFiles()` — lists immediate children.

### Path

`Path` represents a filesystem path.

```java
import java.nio.file.Path;
import java.nio.file.Paths;

Path path = Paths.get("data", "reports", "report.txt");

System.out.println(path.getFileName());
System.out.println(path.getParent());
System.out.println(path.toAbsolutePath());
```

In Java 11, you can also use:

```java
Path path = Path.of("data", "reports", "report.txt");
```

`Path.of()` was introduced in Java 11. `Paths.get()` also works in Java 11 and earlier supported Java versions.

### Files

`Files` provides static methods to perform filesystem operations.

```java
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

Path path = Paths.get("sample.txt");

System.out.println(Files.exists(path));
System.out.println(Files.isRegularFile(path));
System.out.println(Files.isDirectory(path));
```

**Mental model:** `Path` describes where a file is; `Files` performs operations on that path.

---

## 4. Relative Paths vs Absolute Paths

### Relative Path

A relative path is interpreted relative to the program's working directory.

```java
Path path = Paths.get("data", "report.txt");
```

### Absolute Path

An absolute path identifies a location from the filesystem root.

```java
Path path = Paths.get("C:", "projects", "data", "report.txt");
```

The exact syntax depends on the operating system. On Windows, a complete absolute path commonly looks like:

```text
C:\projects\data\report.txt
```

### Common Mistake

A relative path is generally resolved against the process's working directory, not automatically against the directory containing the Java source file.

To debug path problems:

```java
System.out.println(Paths.get("sample.txt").toAbsolutePath());
System.out.println(System.getProperty("user.dir"));
```

---

## 5. Creating Files and Directories

### Create a File

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreateFileExample {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("data", "sample.txt");

        Files.createDirectories(path.getParent());

        if (Files.notExists(path)) {
            Files.createFile(path);
            System.out.println("File created.");
        } else {
            System.out.println("File already exists.");
        }
    }
}
```

`Files.createFile()` creates a new file and throws `FileAlreadyExistsException` if the path already exists.

The existence check above is illustrative. In concurrent applications, another process can create the file between the check and the creation attempt, so production code should handle the exception.

### Create One Directory

```java
Path directory = Paths.get("reports");

Files.createDirectory(directory);
```

The parent directory must already exist.

### Create Nested Directories

```java
Path directory = Paths.get("data", "reports", "2026");

Files.createDirectories(directory);
```

This creates any missing parent directories.

### Important Differences

| Method | Behavior |
|---|---|
| `Files.createFile()` | Creates a new file |
| `Files.createDirectory()` | Creates one directory |
| `Files.createDirectories()` | Creates missing parent directories too |
| `File.mkdir()` | Creates one directory and returns a boolean |
| `File.mkdirs()` | Attempts to create nested directories and returns a boolean |

---

## 6. Reading and Writing Text Files in Java 11

For small text files, Java 11 provides `Files.readString()` and `Files.writeString()`.

### Writing Text

```java
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WriteFileExample {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("message.txt");

        Files.writeString(
                path,
                "Hello Java!\nFile handling is easy.",
                StandardCharsets.UTF_8
        );

        System.out.println("Text saved successfully.");
    }
}
```

By default, `Files.writeString()` creates a file if needed or truncates an existing file.

### Reading Text

```java
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadFileExample {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("message.txt");

        String content = Files.readString(
                path,
                StandardCharsets.UTF_8
        );

        System.out.println(content);
    }
}
```

### Important Consideration

`Files.readString()` loads the complete file into memory. For very large files, use buffered or streaming approaches instead.

---

## 7. Copying Files

Use `Files.copy()` to copy a file.

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CopyFileExample {
    public static void main(String[] args) throws IOException {
        Path source = Paths.get("message.txt");
        Path destination = Paths.get("message-copy.txt");

        Files.copy(
                source,
                destination,
                StandardCopyOption.REPLACE_EXISTING
        );

        System.out.println("File copied.");
    }
}
```

### Important Rules

- The source must exist.
- The destination's parent directory must exist.
- If the destination exists, copying generally fails unless `REPLACE_EXISTING` is specified.
- Filesystem providers may differ in how they support particular copy options.

---

## 8. Moving and Renaming Files

Use `Files.move()` to move a file to another location or rename it.

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MoveFileExample {
    public static void main(String[] args) throws IOException {
        Path source = Paths.get("message-copy.txt");
        Path destination = Paths.get("archive", "message.txt");

        Files.createDirectories(destination.getParent());

        Files.move(source, destination);

        System.out.println("File moved.");
    }
}
```

### Replace an Existing Destination

```java
import java.nio.file.StandardCopyOption;

Files.move(
        source,
        destination,
        StandardCopyOption.REPLACE_EXISTING
);
```

### Important Points

- Moving can also be used to rename a file.
- Moving across filesystems may require a copy-and-delete fallback.
- `ATOMIC_MOVE` can request an atomic move, but support depends on the filesystem.
- Handle `IOException` when the move cannot be completed.

---

## 9. Deleting Files

### Delete a File

```java
Path path = Paths.get("message.txt");

Files.delete(path);
```

`Files.delete()` throws an exception if the path does not exist or cannot be deleted.

### Delete Only If It Exists

```java
Files.deleteIfExists(path);
```

`Files.deleteIfExists()` returns `true` if it deletes the entry and `false` if the entry did not exist.

Deleting a non-empty directory normally fails. Its contents must be removed first.

### Older File API

```java
File file = new File("message.txt");

boolean deleted = file.delete();

System.out.println("Deleted: " + deleted);
```

The older `File.delete()` method returns a boolean and provides less diagnostic information than the NIO.2 API.

---

## 10. Reading File Metadata

Metadata provides information about a file without reading its entire content.

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class FileMetadataExample {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("message.txt");

        System.out.println("Exists: " + Files.exists(path));
        System.out.println("Regular file: " + Files.isRegularFile(path));
        System.out.println("Readable: " + Files.isReadable(path));
        System.out.println("Size: " + Files.size(path) + " bytes");
        System.out.println(
                "Last modified: " + Files.getLastModifiedTime(path)
        );

        BasicFileAttributes attributes =
                Files.readAttributes(path, BasicFileAttributes.class);

        System.out.println("Created: " + attributes.creationTime());
        System.out.println("Directory: " + attributes.isDirectory());
        System.out.println("Regular: " + attributes.isRegularFile());
    }
}
```

### Important Metadata Methods

| Method | Purpose |
|---|---|
| `Files.exists(path)` | Checks whether a path appears to exist |
| `Files.isRegularFile(path)` | Checks whether the path is a regular file |
| `Files.isDirectory(path)` | Checks whether the path is a directory |
| `Files.isReadable(path)` | Checks whether the path appears readable |
| `Files.size(path)` | Returns file size in bytes |
| `Files.getLastModifiedTime(path)` | Returns last-modified time |
| `Files.readAttributes(...)` | Reads filesystem attributes |

### Important Note

Filesystem checks may become outdated between the check and the subsequent operation. For critical operations, perform the operation and handle its exception instead of assuming a prior check guarantees success.

---

## 11. Listing Files and Directories

### List Immediate Children

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ListFilesExample {
    public static void main(String[] args) throws IOException {
        Path directory = Paths.get("data");

        try (Stream<Path> paths = Files.list(directory)) {
            paths.forEach(System.out::println);
        }
    }
}
```

`Files.list()` returns a stream containing the immediate entries of a directory. It does not recursively visit subdirectories.

The stream should be closed, so try-with-resources is appropriate.

### Recursively List Files

```java
try (Stream<Path> paths = Files.walk(Paths.get("data"))) {
    paths.filter(Files::isRegularFile)
         .forEach(System.out::println);
}
```

`Files.walk()` traverses a directory tree.

For very large directory trees, process the stream carefully and handle I/O errors.

---

## 12. File Handling and Exceptions

Filesystem operations can fail for many reasons:

- A file does not exist.
- The application lacks permissions.
- A destination already exists.
- A directory is not empty.
- A path is invalid.
- The underlying filesystem is unavailable.

Common exceptions include:

| Exception | Typical cause |
|---|---|
| `IOException` | General I/O failure |
| `NoSuchFileException` | Required path does not exist |
| `FileAlreadyExistsException` | A new file or directory conflicts with an existing path |
| `AccessDeniedException` | Access is denied |
| `DirectoryNotEmptyException` | Attempt to delete a non-empty directory |

Most filesystem methods expose `IOException` or a more specific subclass.

Handle exceptions at an appropriate application boundary. Avoid silently ignoring filesystem failures.

---

## 13. Common Mistakes

1. **Using the wrong relative path:** inspect the working directory and absolute path.
2. **Confusing `File`, `Path`, and `Files`:** remember their distinct responsibilities.
3. **Assuming `mkdir()` creates parent directories:** use `mkdirs()` or `Files.createDirectories()`.
4. **Overwriting data unintentionally:** understand each method's default behavior.
5. **Ignoring exceptions:** filesystem operations can fail even after an existence check.
6. **Assuming `Files.list()` is recursive:** use `Files.walk()` for traversal.
7. **Forgetting to close directory streams:** use try-with-resources.
8. **Loading huge files into memory:** use buffered or streaming APIs.
9. **Assuming file attributes are always available:** support depends on the filesystem.
10. **Using an existence check as a guarantee:** the path may change before the next operation.

---

## 14. Interview Questions

### Q1. What is file handling in Java?

File handling is the process of interacting with files and directories to read, write, create, copy, move, delete, or inspect them.

### Q2. What is the difference between File and Path?

`File` is the older `java.io` abstraction. `Path` is the NIO.2 abstraction for representing filesystem paths.

### Q3. What is the difference between Path and Files?

`Path` represents a location in the filesystem. `Files` provides static methods to operate on that location.

### Q4. What is the difference between mkdir() and mkdirs()?

`mkdir()` creates one directory. `mkdirs()` also attempts to create missing parent directories.

### Q5. What is the difference between createDirectory() and createDirectories()?

`createDirectory()` creates one directory and requires its parent to exist. `createDirectories()` creates missing parents too.

### Q6. How do you copy a file in Java 11?

Use `Files.copy(source, destination, options)`.

### Q7. How do you move or rename a file?

Use `Files.move(source, destination, options)`.

### Q8. How do you delete a file only if it exists?

Use `Files.deleteIfExists(path)`.

### Q9. How do you get the size of a file?

Use `Files.size(path)`. The result is measured in bytes.

### Q10. What is the difference between Files.list() and Files.walk()?

`Files.list()` lists immediate children. `Files.walk()` traverses a directory tree.

### Q11. Why is Path and Files preferred for modern Java code?

They provide flexible path operations, convenient filesystem methods, and more descriptive exceptions.

### Q12. What is the risk of checking exists() before creating a file?

Another process may change the filesystem between the check and the creation attempt. The operation itself must still handle exceptions.

---

## 15. Coding Practice

Complete these tasks in order:

- [ ] Check whether a file exists and print its absolute path.
- [ ] Create a nested directory using `Files.createDirectories()`.
- [ ] Create a text file inside that directory.
- [ ] Write and read UTF-8 text.
- [ ] Copy a file using `Files.copy()`.
- [ ] Replace an existing destination when copying.
- [ ] Move a file into an archive directory.
- [ ] Print file size and last-modified time.
- [ ] List all regular files recursively using `Files.walk()`.
- [ ] Delete a file using `Files.deleteIfExists()`.

---

## 16. Interview Explanation

"Java provides the older `java.io.File` class and the modern NIO.2 filesystem API. `Path` represents a filesystem path, while `Files` provides operations such as creating directories, reading and writing files, copying, moving, deleting, and retrieving metadata. In Java 11, I generally prefer `Path` and `Files` for new filesystem code because they provide flexible operations and meaningful exceptions. I also use try-with-resources when working with filesystem streams."

---

## 17. Ten-Second Cheat Sheet

- `File` — older filesystem abstraction.
- `Path` — represents a filesystem path.
- `Files` — performs filesystem operations.
- `Files.createFile()` — creates a new file.
- `Files.createDirectories()` — creates nested directories.
- `Files.copy()` — copies a path.
- `Files.move()` — moves or renames a path.
- `Files.deleteIfExists()` — deletes if present.
- `Files.size()` — gets size in bytes.
- `Files.list()` — lists immediate children.
- `Files.walk()` — traverses a directory tree.
- `Files.readString()` / `Files.writeString()` — convenient Java 11 text operations.
- Try-with-resources — safely closes streams.

---

## Final Mental Model

**Path tells Java where the file is; Files tells Java what to do with it.**

Use `File` when working with older code. Prefer `Path` and `Files` for modern Java filesystem operations.
