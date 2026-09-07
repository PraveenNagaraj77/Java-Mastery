# Comparable vs Comparator

Comparable and Comparator are used to define **ordering and sorting rules** for Java objects.

They are especially important when working with:

* `Collections.sort()`
* `List.sort()`
* `TreeSet`
* `TreeMap`
* Custom objects

---

# 1. What is Comparable?

`Comparable` is an interface used to define the **natural/default ordering** of objects.

The class itself implements `Comparable`.

```java
class Employee implements Comparable<Employee> {

    @Override
    public int compareTo(Employee other) {
        // sorting logic
    }
}
```

The comparison logic is written **inside the class**.

### Package

```java
java.lang.Comparable
```

No import is required.

---

# 2. Why Do We Need Comparable?

Java knows how to compare primitive values:

```java
10 < 20
```

But Java doesn't automatically know how to order custom objects.

For example:

```java
Employee e1 = new Employee(101, "Praveen", 70000);
Employee e2 = new Employee(102, "Rahul", 50000);
```

Which employee should come first?

* By ID?
* By name?
* By salary?

`Comparable` allows the class to define its **default/natural ordering**.

---

# 3. How Comparable Works

The class implements:

```java
Comparable<Employee>
```

and overrides:

```java
compareTo()
```

Example:

```java
class Employee implements Comparable<Employee> {

    private double salary;

    @Override
    public int compareTo(Employee other) {
        return Double.compare(this.salary, other.salary);
    }
}
```

This means:

```text
Employee with lower salary
        ↓
comes first
        ↓
Salary ascending
```

---

# 4. compareTo()

The method:

```java
compareTo(Employee other)
```

returns an integer.

### Return values

```text
Negative → this object comes before other
Zero     → both are considered equal in ordering
Positive → this object comes after other
```

Example:

```java
Double.compare(50000, 70000)
```

returns a negative value.

Therefore:

```text
50000 comes before 70000
```

---

# 5. Basic Comparable Example

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComparableBasic {

    static class Employee implements Comparable<Employee> {

        private int id;
        private String name;
        private double salary;

        public Employee(int id, String name, double salary) {
            this.id = id;
            this.name = name;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public double getSalary() {
            return salary;
        }

        @Override
        public int compareTo(Employee other) {
            return Double.compare(this.salary, other.salary);
        }
    }

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(101, "Praveen", 70000));
        employees.add(new Employee(102, "Rahul", 50000));
        employees.add(new Employee(103, "Anjali", 60000));
        employees.add(new Employee(104, "Karthik", 80000));

        Collections.sort(employees);

        for (Employee employee : employees) {
            System.out.println(
                    employee.getName() + " : " +
                    employee.getSalary()
            );
        }
    }
}
```

### Output

```text
Rahul : 50000.0
Anjali : 60000.0
Praveen : 70000.0
Karthik : 80000.0
```

The list is sorted according to the `compareTo()` implementation.

---

# 6. Comparator

`Comparator` is an interface used to define **custom ordering** for objects.

Unlike Comparable, the sorting logic is usually defined **outside the class**.

```java
Comparator<Employee> byName =
        Comparator.comparing(Employee::getName);
```

### Package

```java
java.util.Comparator
```

Import:

```java
import java.util.Comparator;
```

---

# 7. Why Do We Need Comparator?

Suppose `Employee` has a natural ordering by salary.

But sometimes we want:

```text
Sort by name
Sort by ID
Sort by salary
Sort by salary descending
```

We don't want to keep changing `compareTo()`.

`Comparator` allows us to create multiple sorting strategies.

```text
Employee
   │
   ├── byName
   ├── bySalary
   ├── byId
   └── bySalaryDescending
```

---

# 8. Basic Comparator Example

```java
Comparator<Employee> byName =
        Comparator.comparing(Employee::getName);
```

Then:

```java
employees.sort(byName);
```

The employees are sorted alphabetically by name.

---

# 9. Comparator by Salary

```java
Comparator<Employee> bySalary =
        Comparator.comparingDouble(Employee::getSalary);
```

Use:

```java
employees.sort(bySalary);
```

Result:

```text
Rahul : 50000
Anjali : 60000
Praveen : 70000
Karthik : 80000
```

---

# 10. Salary Descending

Use `reversed()`:

```java
Comparator<Employee> bySalaryDescending =
        Comparator.comparingDouble(Employee::getSalary)
                  .reversed();
```

Then:

```java
employees.sort(bySalaryDescending);
```

Result:

```text
Karthik : 80000
Praveen : 70000
Anjali : 60000
Rahul : 50000
```

---

# 11. Comparator by ID

For primitive `int`, use:

```java
Comparator<Employee> byId =
        Comparator.comparingInt(Employee::getId);
```

Then:

```java
employees.sort(byId);
```

---

# 12. Multiple Sorting Conditions

Comparator allows multiple sorting conditions using:

```java
thenComparing()
```

Example:

```java
Comparator<Employee> comparator =
        Comparator.comparing(Employee::getName)
                  .thenComparingInt(Employee::getId)
                  .thenComparingDouble(Employee::getSalary);
```

Meaning:

```text
First compare name
        ↓
If name is same
        ↓
Compare ID
        ↓
If ID is same
        ↓
Compare salary
```

---

# 13. Why Tie-Breakers Matter

Consider:

```text
Employee 101 → Rahul → 50000
Employee 102 → Rahul → 50000
```

If the comparator only uses:

```java
Comparator.comparing(Employee::getName)
```

both employees compare as equal because both names are `"Rahul"`.

For ordered collections such as `TreeSet` and `TreeMap`, a comparison result of `0` means the objects/keys are equivalent **for ordering purposes**.

A tie-breaker can distinguish them:

```java
Comparator<Employee> comparator =
        Comparator.comparing(Employee::getName)
                  .thenComparingInt(Employee::getId);
```

Now:

```text
Rahul + ID 101
Rahul + ID 102
```

are different in ordering.

---

# 14. Comparable vs Comparator

| Comparable                                          | Comparator                      |
| --------------------------------------------------- | ------------------------------- |
| Natural/default ordering                            | Custom ordering                 |
| `compareTo()`                                       | `compare()`                     |
| Implemented by the class                            | Usually defined separately      |
| Usually one natural ordering                        | Multiple sorting strategies     |
| `java.lang`                                         | `java.util`                     |
| Modifies class design                               | Keeps sorting logic external    |
| Used by `Collections.sort(list)` with no comparator | Used by `list.sort(comparator)` |

---

# 15. Mental Model

Remember this:

```text
Comparable
    ↓
Natural Ordering
    ↓
Inside the Class
    ↓
compareTo()
```

```text
Comparator
    ↓
Custom Ordering
    ↓
Outside the Class
    ↓
compare()
```

### Simple memory trick

```text
Comparable → "How should I normally be sorted?"

Comparator → "How should I be sorted this time?"
```

---

# 16. compareTo() vs compare()

### Comparable

```java
employee1.compareTo(employee2);
```

### Comparator

```java
comparator.compare(employee1, employee2);
```

Both follow the same general result convention:

```text
negative → first comes before second
zero     → equal in ordering
positive → first comes after second
```

---

# 17. Comparator Factory Methods

Modern Java provides useful methods for creating comparators.

### Object property

```java
Comparator.comparing(Employee::getName);
```

### int property

```java
Comparator.comparingInt(Employee::getId);
```

### double property

```java
Comparator.comparingDouble(Employee::getSalary);
```

### Long property

```java
Comparator.comparingLong(Employee::getId);
```

These are usually cleaner than writing `compare()` manually.

---

# 18. Avoid Manual Subtraction

Avoid:

```java
return this.id - other.id;
```

Although it often works for small integers, it can overflow for extreme integer values.

Prefer:

```java
return Integer.compare(this.id, other.id);
```

For `double`:

```java
return Double.compare(this.salary, other.salary);
```

For Comparator:

```java
Comparator.comparingInt(Employee::getId);
```

or:

```java
Comparator.comparingDouble(Employee::getSalary);
```

---

# 19. Comparable with TreeSet

`TreeSet` can use the natural ordering defined by `Comparable`.

Example:

```java
class Employee implements Comparable<Employee> {

    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.id, other.id);
    }
}
```

Then:

```java
TreeSet<Employee> employees = new TreeSet<>();

employees.add(employee1);
employees.add(employee2);
employees.add(employee3);
```

The employees are ordered by ID.

---

# 20. Comparator with TreeSet

We can also provide a Comparator directly:

```java
TreeSet<Employee> employees =
        new TreeSet<>(
                Comparator.comparingDouble(Employee::getSalary)
        );
```

Now the TreeSet orders employees by salary.

This is one of the important differences between Comparable and Comparator.

---

# 21. Comparator with TreeMap

Comparator can also determine the ordering of `TreeMap` keys.

```java
TreeMap<Employee, String> employees =
        new TreeMap<>(
                Comparator.comparingDouble(Employee::getSalary)
        );
```

The keys are ordered according to salary.

Remember:

```text
TreeMap
    ↓
compare keys
    ↓
Comparator / Comparable
```

---

# 22. Common Mistakes

## Mistake 1 — Implementing Comparable in the wrong class

Wrong:

```java
class ComparableBasic
        implements Comparable<Employee>
```

Usually we want:

```java
class Employee implements Comparable<Employee>
```

because Employee defines its natural ordering.

---

## Mistake 2 — Forgetting compareTo()

If a class implements:

```java
Comparable<Employee>
```

it must implement:

```java
compareTo(Employee other)
```

unless the class is abstract.

---

## Mistake 3 — Confusing compareTo and compare

Comparable:

```java
compareTo()
```

Comparator:

```java
compare()
```

---

## Mistake 4 — Using Comparator when you need natural ordering

If a class has one obvious default ordering, Comparable can be appropriate.

Example:

```text
Student → roll number
Product → product ID
```

---

## Mistake 5 — Using Comparable for every possible ordering

Suppose employees can be sorted by:

```text
ID
Name
Salary
Department
Joining Date
```

Don't keep changing `compareTo()`.

Use different Comparators.

---

## Mistake 6 — Comparator returns 0 accidentally

Example:

```java
Comparator.comparingDouble(Employee::getSalary)
```

Two employees with the same salary compare as `0`.

For `TreeSet`/`TreeMap`, that can make distinct objects/keys equivalent for the collection's ordering.

Use tie-breakers when necessary:

```java
Comparator<Employee> comparator =
        Comparator.comparingDouble(Employee::getSalary)
                  .thenComparingInt(Employee::getId);
```

---

# 23. Real-World Example

Imagine an employee management system.

The application may need:

```text
Default view
    → Sort by employee ID

Salary report
    → Sort by salary descending

Employee directory
    → Sort by name

Joining report
    → Sort by joining date
```

A natural ordering might be:

```java
Employee implements Comparable<Employee>
```

for ID.

Then custom Comparators:

```java
bySalary
byName
byJoiningDate
```

can handle the other requirements.

---

# 24. Interview Questions

### Q1. What is Comparable?

`Comparable` is an interface used to define the natural/default ordering of objects using `compareTo()`.

---

### Q2. What is Comparator?

`Comparator` is an interface used to define custom ordering of objects using `compare()`.

---

### Q3. What is the main difference?

```text
Comparable → natural ordering → inside class
Comparator → custom ordering → outside class
```

---

### Q4. Can a class have multiple Comparators?

Yes.

For example:

```java
byName
bySalary
byId
```

---

### Q5. Can a class implement multiple Comparables?

No. A class can implement `Comparable<T>` only once because the interface provides a single `compareTo()` method for its natural ordering.

It can, however, have many different `Comparator`s.

---

### Q6. Where is Comparable located?

```java
java.lang
```

---

### Q7. Where is Comparator located?

```java
java.util
```

---

### Q8. What does compareTo() return?

It returns:

```text
negative → less than
zero     → equal
positive → greater than
```

---

### Q9. What does `reversed()` do?

It reverses the ordering of a Comparator.

```java
Comparator.comparingDouble(Employee::getSalary)
          .reversed();
```

This gives salary descending instead of ascending.

---

### Q10. Can TreeSet use Comparator?

Yes.

```java
TreeSet<Employee> employees =
        new TreeSet<>(comparator);
```

---

### Q11. Can TreeMap use Comparator?

Yes.

The Comparator determines the ordering of the keys.

---

# 25. Interview Explanation

If the interviewer asks:

> Explain Comparable and Comparator.

You can answer:

> "Comparable and Comparator are used for ordering objects in Java. Comparable is used to define the natural ordering of a class, so the class implements Comparable and overrides compareTo(). Comparator is used to define custom sorting logic externally through compare(). A class generally has one natural ordering using Comparable, but we can create multiple Comparators for different sorting requirements such as sorting employees by name, salary, or ID."

---

# 26. Quick Reference

```text
Comparable
------------------------------------------------
Package: java.lang
Method: compareTo()
Purpose: Natural ordering
Location: Inside class
Usually: One natural ordering

Example:
class Employee implements Comparable<Employee> {

    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.id, other.id);
    }
}
```

```text
Comparator
------------------------------------------------
Package: java.util
Method: compare()
Purpose: Custom ordering
Location: Usually outside class
Usually: Multiple sorting strategies

Example:
Comparator<Employee> bySalary =
        Comparator.comparingDouble(Employee::getSalary);
```

---

# 27. Most Important Things to Remember

```text
Comparable
    ↓
Natural ordering
    ↓
compareTo()
    ↓
Inside the class
```

```text
Comparator
    ↓
Custom ordering
    ↓
compare()
    ↓
Outside the class
```

### Common Comparator methods

```java
Comparator.comparing(...)
Comparator.comparingInt(...)
Comparator.comparingLong(...)
Comparator.comparingDouble(...)
Comparator.thenComparing(...)
Comparator.reversed()
```

### Prefer

```java
Integer.compare(a, b)
Double.compare(a, b)
```

over:

```java
a - b
```

---

# 28. Final Interview Cheat Sheet

| Concept             | Comparable               | Comparator            |
| ------------------- | ------------------------ | --------------------- |
| Purpose             | Natural ordering         | Custom ordering       |
| Method              | `compareTo()`            | `compare()`           |
| Package             | `java.lang`              | `java.util`           |
| Defined             | Inside class             | Outside class         |
| Number of orderings | Usually one              | Multiple              |
| `List.sort()`       | Can use natural ordering | Can accept comparator |
| `TreeSet`           | Supported                | Supported             |
| `TreeMap`           | Supported for keys       | Supported for keys    |

### One-line memory trick

> **Comparable = "I know how to compare myself."**

> **Comparator = "You tell me how to compare these objects."**
