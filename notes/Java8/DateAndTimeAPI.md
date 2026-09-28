# Date & Time API — Interview Cheat Sheet

## 1. What is it?

The Java 8 Date/Time API, available in the `java.time` package, provides classes for handling dates, times, time zones, formatting, parsing, and time calculations.

| Class | Purpose |
|---|---|
| `LocalDate` | Date only |
| `LocalTime` | Time only |
| `LocalDateTime` | Date + time, no timezone |
| `ZonedDateTime` | Date + time + timezone |
| `Instant` | Exact point on the UTC timeline |
| `DateTimeFormatter` | Formatting and parsing |
| `Period` | Date-based amount |
| `Duration` | Time-based amount |

## 2. Why is it needed?

The Java 8 Date/Time API provides:

- Immutable and thread-safe date/time objects.
- Clear separation between dates, times, and time zones.
- Convenient date/time calculations.
- Better timezone handling.
- Simple formatting and parsing.

It is generally preferred over the older `java.util.Date` and `Calendar` APIs for new code.

## 3. How does it work / Internal?

```text
LocalDate       → Date only
LocalTime       → Time only
LocalDateTime   → Date + Time, no timezone
ZonedDateTime   → Date + Time + Timezone
Instant         → Exact global moment
DateTimeFormatter → Format and parse
Period          → Years + Months + Days
Duration        → Hours + Minutes + Seconds + Nanos
```

Most `java.time` classes are immutable. Operations such as `plusDays()` return a new object rather than modifying the original.

```java
LocalDate today = LocalDate.now();
LocalDate tomorrow = today.plusDays(1);
```

## 4. Syntax

### LocalDate

```java
LocalDate today = LocalDate.now();
LocalDate date = LocalDate.of(2026, 9, 28);
LocalDate future = today.plusDays(7);
```

### LocalTime

```java
LocalTime now = LocalTime.now();
LocalTime time = LocalTime.of(14, 30);
LocalTime future = time.plusHours(2);
```

### LocalDateTime

```java
LocalDateTime now = LocalDateTime.now();

LocalDateTime dateTime =
        LocalDateTime.of(2026, 9, 28, 14, 30);
```

### ZonedDateTime

```java
ZoneId zone = ZoneId.of("Asia/Kolkata");
ZonedDateTime indiaTime = ZonedDateTime.now(zone);
```

### Instant

```java
Instant now = Instant.now();

long epochSeconds = now.getEpochSecond();
long epochMillis = now.toEpochMilli();
```

### DateTimeFormatter

```java
DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

String result = dateTime.format(formatter);
```

### Period

```java
Period period = Period.ofMonths(3);
LocalDate result = startDate.plus(period);
```

### Duration

```java
Duration duration = Duration.ofHours(2);
LocalTime result = startTime.plus(duration);
```

## 5. Simple Example

### LocalDate

```java
LocalDate today = LocalDate.now();
LocalDate deliveryDate = today.plusDays(3);

System.out.println(deliveryDate);
```

### LocalTime

```java
LocalTime start = LocalTime.of(10, 30);
LocalTime end = start.plusHours(2);

System.out.println(end); // 12:30
```

### LocalDateTime

```java
LocalDateTime orderTime = LocalDateTime.now();
LocalDateTime deliveryTime = orderTime.plusHours(3);
```

### DateTimeFormatter

```java
LocalDate date = LocalDate.of(2026, 9, 28);

DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("dd-MM-yyyy");

System.out.println(date.format(formatter));
// 28-09-2026
```

## 6. Real-Time Example

### E-commerce Order System

```java
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class OrderDateTimeExample {

    public static void main(String[] args) {

        // Expected delivery date
        LocalDate orderDate = LocalDate.now();

        LocalDate expectedDelivery =
                orderDate.plus(Period.ofDays(3));

        System.out.println("Expected delivery: " + expectedDelivery);

        // Format order timestamp
        LocalDateTime orderTime = LocalDateTime.now();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println(
                "Order time: " + orderTime.format(formatter)
        );

        // Measure processing time
        Instant start = Instant.now();

        // Simulate business processing
        for (int i = 0; i < 100_000; i++) {
            Math.sqrt(i);
        }

        Instant end = Instant.now();

        Duration processingTime =
                Duration.between(start, end);

        System.out.println(
                "Processing time: " + processingTime.toMillis() + " ms"
        );

        // Display a moment in different timezones
        ZonedDateTime indiaTime =
                orderTime.atZone(ZoneId.of("Asia/Kolkata"));

        ZonedDateTime londonTime =
                indiaTime.withZoneSameInstant(
                        ZoneId.of("Europe/London")
                );

        System.out.println("India: " + indiaTime);
        System.out.println("London: " + londonTime);
    }
}
```

## 7. Important Rules

### LocalDate

Use when only the date matters.

Examples: birth dates, holidays, invoice dates, delivery dates.

### LocalTime

Use when only the time matters.

Examples: shop opening hours, meeting times, delivery windows.

### LocalDateTime

Represents date and time without a timezone. It does not independently identify a unique global moment.

### ZonedDateTime

Represents date, time, and timezone.

```java
ZonedDateTime newYorkTime =
        indiaTime.withZoneSameInstant(
                ZoneId.of("America/New_York")
        );
```

- `withZoneSameInstant()` preserves the actual moment.
- `withZoneSameLocal()` preserves the local clock reading, potentially changing the actual moment.

### Instant

Represents an exact point on the UTC timeline. Useful for event timestamps, audit records, and measuring elapsed time.

Convert between an `Instant` and a timezone-aware value:

```java
ZonedDateTime localTime =
        instant.atZone(ZoneId.of("Asia/Kolkata"));

Instant sameInstant = localTime.toInstant();
```

### DateTimeFormatter

Formatting: Java date/time object → String.

Parsing: String → Java date/time object.

```java
DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("dd-MM-yyyy");

String text = date.format(formatter);

LocalDate parsedDate =
        LocalDate.parse(text, formatter);
```

Important pattern symbols:

| Pattern | Meaning |
|---|---|
| `dd` | Day |
| `MM` | Month |
| `yyyy` | Year |
| `HH` | 24-hour clock |
| `hh` | 12-hour clock |
| `mm` | Minute |
| `ss` | Second |
| `a` | AM/PM |

### Period

Represents calendar-based years, months, and days.

```java
LocalDate expiryDate =
        startDate.plus(Period.ofMonths(12));
```

### Duration

Represents elapsed time in hours, minutes, seconds, and nanoseconds.

```java
Duration elapsed =
        Duration.between(startInstant, endInstant);

long milliseconds = elapsed.toMillis();
```

## 8. Common Mistakes

### Mistake 1: Using `mm` for month

Incorrect:

```java
"dd-mm-yyyy"
```

Correct:

```java
"dd-MM-yyyy"
```

`MM` means month; `mm` means minute.

### Mistake 2: Confusing `HH` and `hh`

- `HH`: 24-hour clock.
- `hh`: 12-hour clock.

For a 12-hour display with AM/PM:

```java
"dd/MM/yyyy hh:mm:ss a"
```

### Mistake 3: Treating LocalDateTime as a global timestamp

`LocalDateTime` has no timezone.

Use `Instant` for a global moment, or `ZonedDateTime` when the timezone is relevant.

### Mistake 4: Forgetting immutability

Incorrect assumption:

```java
date.plusDays(5); // Does not modify date
```

Correct:

```java
date = date.plusDays(5);
```

### Mistake 5: Confusing Period and Duration

- `Period`: calendar-based amount.
- `Duration`: elapsed-time amount.

### Mistake 6: Calling static methods through instances

Avoid:

```java
period.ofDays(10);
```

Prefer:

```java
Period.ofDays(10);
```

### Mistake 7: Ignoring inclusive boundaries

This excludes the start and end:

```java
time.isAfter(start) && time.isBefore(end);
```

For an inclusive range:

```java
!time.isBefore(start) && !time.isAfter(end);
```

### Mistake 8: Confusing year pattern symbols

For ordinary calendar-date formatting, prefer `yyyy` over `YYYY`. `YYYY` represents the week-based year and can produce unexpected results near year boundaries.

## 9. Interview Questions

**Q1. What is the Java 8 Date/Time API?**

The `java.time` API provides modern, immutable classes for dates, times, timezones, parsing, formatting, and temporal calculations.

**Q2. LocalDate vs LocalDateTime?**

`LocalDate` represents a date only. `LocalDateTime` represents a date and time, without a timezone.

**Q3. LocalDateTime vs ZonedDateTime?**

`LocalDateTime` has no timezone. `ZonedDateTime` includes a timezone.

**Q4. What is Instant?**

An `Instant` represents an exact point on the UTC timeline.

**Q5. Instant vs LocalDateTime?**

`Instant` identifies a global moment. `LocalDateTime` alone does not identify a unique global moment.

**Q6. Period vs Duration?**

`Period` represents calendar-based years, months, and days. `Duration` represents elapsed time.

**Q7. What is DateTimeFormatter?**

It formats date/time objects into strings and parses strings into date/time objects.

**Q8. What is the difference between MM and mm?**

`MM` is month; `mm` is minute.

**Q9. What is the difference between HH and hh?**

`HH` is the 24-hour clock; `hh` is the 12-hour clock.

**Q10. Why is the Java 8 Date/Time API preferred over Date and Calendar?**

It offers clearer APIs, immutability, thread safety, better timezone support, and easier date/time calculations.

**Q11. What does withZoneSameInstant() do?**

It changes the timezone while preserving the same actual moment.

**Q12. What does Period.between() return?**

A calendar-based difference represented in years, months, and days.

**Q13. What does Duration.between() return?**

A `Duration` representing elapsed time between two temporal values.

**Q14. Are java.time classes immutable?**

The principal date/time classes, including `LocalDate`, `LocalTime`, `LocalDateTime`, `ZonedDateTime`, `Instant`, `Period`, and `Duration`, are immutable.

## 10. Coding Practice

1. Create a `LocalDate` and calculate a date seven days later.
2. Check whether a delivery time falls within a specified time window.
3. Calculate a delivery timestamp three hours after an order timestamp.
4. Convert a meeting time from India to London and New York using `withZoneSameInstant()`.
5. Compare two `Instant` objects.
6. Parse `28/09/2026 14:30`, add three hours, and format the result.
7. Calculate the period between `2020-01-15` and `2026-09-28`.
8. Calculate the duration between `10:30` and `14:45`.
9. Measure code execution time using `Instant` and `Duration`.
10. Explain why a subscription expiry calculation and API execution-time measurement use different classes.

## 11. 10-Second Cheat Sheet

```text
LocalDate         → Date only
LocalTime         → Time only
LocalDateTime     → Date + Time, no timezone
ZonedDateTime     → Date + Time + Timezone
Instant           → Exact global moment
DateTimeFormatter → Formatting + Parsing
Period            → Years + Months + Days
Duration          → Hours + Minutes + Seconds + Nanos

MM → Month
mm → Minute
HH → 24-hour clock
hh → 12-hour clock

withZoneSameInstant() → Same moment, different timezone
```

## 12. Final Mental Model

Choose the class based on the question you need to answer:

```text
What date?                         → LocalDate
What time?                         → LocalTime
What date and time?                → LocalDateTime
What timezone?                     → ZonedDateTime
What exact global moment?          → Instant
How should I display or parse it?  → DateTimeFormatter
How much calendar time?            → Period
How much elapsed time?             → Duration
```

Backend rule of thumb:

- Use `Instant` for globally meaningful event timestamps.
- Use `ZonedDateTime` when timezone rules matter.
- Use `LocalDate` for date-only business concepts.
- Use `Period` for calendar-based calculations.
- Use `Duration` for elapsed-time calculations.
- Use `DateTimeFormatter` for parsing and display.

Remember: choose the temporal type based on the meaning of the data, not just how it looks on the screen.