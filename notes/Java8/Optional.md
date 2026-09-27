# Optional — Last-Minute Revision

## What is Optional?

`Optional<T>` represents a value that **may or may not be present**.

```java
Optional<User> user;
```

Mental model:

```text
Optional → Value may exist OR may be absent
```

Main purpose:

> Make absence explicit and reduce accidental `NullPointerException`.

---

## Create Optional

```java
Optional.of("Java");
```

Value must NOT be `null`.

```java
Optional.ofNullable(value);
```

`null` → `Optional.empty()`.

```java
Optional.empty();
```

Creates an empty Optional.

---

## Important Methods

| Method | Purpose |
|---|---|
| `of()` | Create Optional, null not allowed |
| `ofNullable()` | Create Optional, null allowed |
| `empty()` | Empty Optional |
| `isPresent()` | Check value exists |
| `ifPresent()` | Execute if value exists |
| `get()` | Get value — risky if empty |
| `orElse()` | Default value |
| `orElseGet()` | Lazy default |
| `orElseThrow()` | Throw if empty |
| `map()` | Transform value |
| `flatMap()` | Transform + flatten Optional |
| `filter()` | Keep if condition matches |

---

## Most Important Differences

### `of()` vs `ofNullable()`

```text
of(null)          → NullPointerException
ofNullable(null)  → Optional.empty()
```

### `orElse()` vs `orElseGet()`

```text
orElse()     → default is evaluated immediately
orElseGet()  → default is generated only when needed
```

### `map()` vs `flatMap()`

```text
map()      → transform value
flatMap() → transform + flatten Optional
```

---

## Common Patterns

### Default value

```java
String name = optionalName
        .orElse("Unknown");
```

### Throw exception

```java
User user = userRepository.findById(id)
        .orElseThrow(() ->
                new RuntimeException("User not found")
        );
```

### Execute if present

```java
optionalName.ifPresent(
        name -> System.out.println(name)
);
```

### Transform

```java
Optional<String> upperName =
        name.map(String::toUpperCase);
```

### Filter

```java
Optional<String> result =
        name.filter(n -> n.length() > 5);
```

---

## Real-World Use

Very common with **Spring Data JPA**:

```java
Optional<User> findById(Long id);
```

Because the user may not exist.

Typical service code:

```java
public User getUser(Long id) {

    return userRepository.findById(id)
            .orElseThrow(() ->
                    new UserNotFoundException(
                            "User not found"
                    )
            );
}
```

Also commonly returned by Stream operations:

```text
findFirst()
findAny()
min()
max()
reduce()   // without identity
```

because the result may not exist.

---

## Important Interview Point

`Optional` is mainly useful for **return values where absence is a valid result**.

Good:

```java
Optional<User> findUserById(Long id);
```

Don't blindly use Optional everywhere as a replacement for `null`.

---

## Common Mistake

Avoid:

```java
if (optional.isPresent()) {
    optional.get();
}
```

Prefer:

```java
optional.ifPresent(...);
```

or:

```java
optional.orElse(...);
```

or:

```java
optional.orElseThrow(...);
```

---

## 10-Second Cheat Sheet

```text
Optional = value may be absent

of()          → null NOT allowed
ofNullable()  → null allowed
empty()       → empty

isPresent()   → exists?
ifPresent()   → execute if exists

get()         → get value (risky)

orElse()      → default
orElseGet()   → lazy default
orElseThrow() → exception

map()         → transform
flatMap()     → transform + flatten
filter()      → condition
```

### Remember

> **Optional = "This result may not exist; handle that explicitly."**