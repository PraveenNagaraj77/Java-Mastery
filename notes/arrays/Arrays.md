# Arrays in Java

## 1. What is an Array?

An array in Java is a **fixed-size, indexed collection of elements of the same data type**.

An array allows us to store multiple values under a single variable name and access each value using an index.

```java
int[] salaries = {
    45000,
    52000,
    38000,
    60000
};
```

Array representation:

```text
Index:     0       1       2       3
         ┌──────┬──────┬──────┬──────┐
Value:   │45000 │52000 │38000 │60000 │
         └──────┴──────┴──────┴──────┘
```

The first element is always at index `0`.

```java
salaries[0]    // 45000
salaries[1]    // 52000
salaries[3]    // 60000
```

---

# 2. Why Do We Need Arrays?

Suppose we need to store five employee salaries.

Without an array:

```java
int salary1 = 45000;
int salary2 = 52000;
int salary3 = 38000;
int salary4 = 60000;
int salary5 = 48000;
```

This becomes difficult to manage.

With an array:

```java
int[] salaries = {
    45000,
    52000,
    38000,
    60000,
    48000
};
```

Now we can process all values using loops.

```java
for (int salary : salaries) {
    System.out.println(salary);
}
```

Arrays are especially important in DSA because many algorithms operate on indexed collections.

Examples:

* Searching
* Sorting
* Reversing
* Finding minimum/maximum
* Two pointers
* Sliding window
* Prefix sum
* Binary search

---

# 3. How Does an Array Work Internally?

When we write:

```java
int[] numbers = new int[5];
```

Java creates an **array object** capable of storing five integers.

Conceptually:

```text
numbers
   |
   | reference
   ↓
┌─────┬─────┬─────┬─────┬─────┐
│  0  │  0  │  0  │  0  │  0  │
└─────┴─────┴─────┴─────┴─────┘
   0     1     2     3     4
```

The variable `numbers` contains a reference to the array object.

The array has:

* A fixed length
* Indexed elements
* Elements of one declared type
* Runtime bounds checking

When we access:

```java
numbers[2]
```

Java checks that index `2` is valid and accesses that element.

---

# 4. Array Declaration

Preferred syntax:

```java
int[] numbers;
```

Another valid syntax:

```java
int numbers[];
```

Preferred Java style:

```java
int[] numbers;
```

because it clearly communicates that `numbers` is an array of integers.

---

# 5. Array Creation

```java
int[] numbers = new int[5];
```

This creates an array with five elements.

The indexes are:

```text
0 1 2 3 4
```

The default values for an `int` array are:

```text
0 0 0 0 0
```

---

# 6. Array Initialization

We can initialize an array directly:

```java
int[] numbers = {
    10,
    20,
    30,
    40,
    50
};
```

Java automatically determines the length.

```java
System.out.println(numbers.length);
```

Output:

```text
5
```

We can also use:

```java
int[] numbers = new int[]{10, 20, 30};
```

---

# 7. Array Indexing

Java arrays use **zero-based indexing**.

For:

```java
int[] numbers = {10, 20, 30, 40};
```

we have:

```text
Index       Value

  0           10
  1           20
  2           30
  3           40
```

First element:

```java
numbers[0]
```

Last element:

```java
numbers[numbers.length - 1]
```

---

# 8. Why Does Index Start at Zero?

If an array starts at a base position, the offset of the first element is zero.

Conceptually:

```text
Base + 0 → first element
Base + 1 → second element
Base + 2 → third element
```

Therefore Java uses zero-based indexing.

This also makes:

```java
array.length - 1
```

the last valid index.

---

# 9. Updating an Array Element

Arrays are mutable.

```java
int[] salaries = {
    45000,
    52000,
    38000
};
```

Update:

```java
salaries[2] = 42000;
```

Now:

```text
45000  52000  42000
```

The array size does not change.

---

# 10. Array Length

Use:

```java
array.length
```

Example:

```java
int[] numbers = {10, 20, 30};

System.out.println(numbers.length);
```

Output:

```text
3
```

Important distinction:

### Array

```java
numbers.length
```

### String

```java
name.length()
```

### Collection

```java
list.size()
```

---

# 11. Traversing an Array

## Traditional for loop

```java
for (int i = 0; i < numbers.length; i++) {
    System.out.println(numbers[i]);
}
```

This is useful when we need the index.

Example:

```java
for (int i = 0; i < numbers.length; i++) {

    System.out.println(
        "Index: " + i +
        ", Value: " + numbers[i]
    );
}
```

---

# 12. Enhanced For Loop

We can also use:

```java
for (int number : numbers) {
    System.out.println(number);
}
```

This is useful when we only need the values.

If we need the index, use the traditional `for` loop.

---

# 13. Default Values of Arrays

When an array is created using `new`, Java initializes its elements with default values.

## int

```java
int[] numbers = new int[3];
```

```text
0 0 0
```

## double

```java
double[] values = new double[3];
```

```text
0.0 0.0 0.0
```

## boolean

```java
boolean[] flags = new boolean[3];
```

```text
false false false
```

## char

```java
char[] letters = new char[3];
```

Default value:

```text
\u0000
```

## Reference types

```java
String[] names = new String[3];
```

Default:

```text
null null null
```

---

# 14. Arrays Are Objects

This is an important interview concept.

Arrays in Java are objects.

For example:

```java
int[] numbers = new int[5];
```

The array is an object created at runtime.

The variable:

```java
numbers
```

holds a reference to that array object.

Therefore arrays have an object property:

```java
numbers.length
```

---

# 15. Arrays Have Fixed Size

Once an array is created:

```java
int[] numbers = new int[5];
```

its length is fixed at `5`.

We cannot do:

```java
numbers.add(10);
```

or dynamically increase its size.

If we need a dynamically growing collection, we can use:

```java
ArrayList<Integer>
```

which we will cover later under Java Collections.

---

# 16. Array of Objects

Arrays can contain object references.

Example:

```java
Employee[] employees = new Employee[3];
```

Important:

This creates an array capable of holding three `Employee` references.

It does **not** create three Employee objects.

Initially:

```text
employees
   ↓
┌────────┬────────┬────────┐
│ null   │ null   │ null   │
└────────┴────────┴────────┘
```

We must create the objects:

```java
employees[0] = new Employee();
employees[1] = new Employee();
employees[2] = new Employee();
```

---

# 17. Array Reference Behavior

Consider:

```java
int[] a = {10, 20, 30};

int[] b = a;
```

This does not create a new array.

Both variables refer to the same array.

```text
a ──────────┐
            ↓
        ┌─────────────┐
        │ 10 │ 20 │ 30│
        └─────────────┘
            ↑
b ──────────┘
```

Therefore:

```java
b[0] = 100;
```

will also affect what we see through `a`.

```java
System.out.println(a[0]);
```

Output:

```text
100
```

---

# 18. Passing Arrays to Methods

Arrays can be passed to methods.

```java
public static int findMaximum(int[] numbers) {

    int maximum = numbers[0];

    for (int number : numbers) {

        if (number > maximum) {
            maximum = number;
        }
    }

    return maximum;
}
```

Call:

```java
int[] numbers = {10, 50, 20, 90, 30};

int result = findMaximum(numbers);
```

Result:

```text
90
```

This pattern is fundamental to DSA.

---

# 19. Linear Search

Linear search checks elements one by one.

```java
public static int linearSearch(
        int[] numbers,
        int target
) {

    for (int i = 0; i < numbers.length; i++) {

        if (numbers[i] == target) {
            return i;
        }
    }

    return -1;
}
```

Example:

```java
int[] numbers = {10, 20, 30, 40};

linearSearch(numbers, 30);
```

Returns:

```text
2
```

If the value doesn't exist:

```text
-1
```

### Complexity

```text
Time:  O(n)
Space: O(1)
```

---

# 20. Finding Maximum

```java
public static int findMaximum(int[] numbers) {

    int maximum = numbers[0];

    for (int i = 1; i < numbers.length; i++) {

        if (numbers[i] > maximum) {
            maximum = numbers[i];
        }
    }

    return maximum;
}
```

Why start with:

```java
numbers[0]
```

instead of:

```java
0
```

Because array values could all be negative.

Example:

```text
{-10, -20, -5}
```

If we initialize:

```java
int maximum = 0;
```

we incorrectly get `0`.

Correct:

```java
int maximum = numbers[0];
```

---

# 21. Reversing an Array

A common DSA problem is reversing an array.

Example:

```text
Before:

10 20 30 40 50

After:

50 40 30 20 10
```

We can use two pointers:

```java
public static void reverseArray(int[] numbers) {

    int left = 0;
    int right = numbers.length - 1;

    while (left < right) {

        int temp = numbers[left];

        numbers[left] = numbers[right];

        numbers[right] = temp;

        left++;
        right--;
    }
}
```

Conceptually:

```text
left →                  ← right

10   20   30   40   50
```

Swap:

```text
50   20   30   40   10
```

Continue until:

```text
left >= right
```

Complexity:

```text
Time:  O(n)
Space: O(1)
```

---

# 22. Two-Dimensional Arrays

Java supports multidimensional arrays.

Example:

```java
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};
```

Access:

```java
matrix[1][2]
```

Result:

```text
6
```

---

# 23. Traversing a 2D Array

Use nested loops:

```java
for (int row = 0; row < matrix.length; row++) {

    for (int column = 0;
         column < matrix[row].length;
         column++) {

        System.out.print(matrix[row][column] + " ");
    }

    System.out.println();
}
```

Output:

```text
1 2 3
4 5 6
7 8 9
```

---

# 24. Jagged Arrays

Java's multidimensional arrays are actually arrays of arrays.

Therefore rows can have different lengths.

Example:

```java
int[][] numbers = {
    {1, 2},
    {3, 4, 5},
    {6}
};
```

Representation:

```text
Row 0 → 1 2
Row 1 → 3 4 5
Row 2 → 6
```

This is called a **jagged array**.

The important point is:

```java
int[][]
```

means:

> An array whose elements are themselves arrays.

---

# 25. Array Comparison

Consider:

```java
int[] a = {1, 2, 3};
int[] b = {1, 2, 3};
```

This:

```java
a == b
```

checks whether both references point to the same array object.

It does not compare their contents.

Therefore:

```java
System.out.println(a == b);
```

prints:

```text
false
```

For content comparison:

```java
Arrays.equals(a, b);
```

using:

```java
import java.util.Arrays;
```

---

# 26. Common Mistakes

## Mistake 1: Off-by-one error

Wrong:

```java
for (int i = 0; i <= numbers.length; i++)
```

Correct:

```java
for (int i = 0; i < numbers.length; i++)
```

The last valid index is:

```java
numbers.length - 1
```

---

## Mistake 2: Invalid index

For:

```java
int[] numbers = new int[5];
```

valid indexes:

```text
0 1 2 3 4
```

This is invalid:

```java
numbers[5];
```

It results in:

```text
ArrayIndexOutOfBoundsException
```

---

## Mistake 3: Assuming arrays are dynamic

This is invalid:

```java
numbers.add(10);
```

Normal Java arrays have fixed size.

---

## Mistake 4: Confusing `length` and `length()`

Array:

```java
numbers.length
```

String:

```java
name.length()
```

---

## Mistake 5: Assuming assignment copies an array

```java
int[] b = a;
```

copies the reference, not the contents.

---

## Mistake 6: Initializing maximum/minimum incorrectly

Avoid:

```java
int maximum = 0;
```

Use:

```java
int maximum = numbers[0];
```

when finding maximum values unless the problem guarantees non-negative values.

---

# 27. Array Advantages

### 1. Fast random access

```java
numbers[index]
```

is generally:

```text
O(1)
```

### 2. Simple structure

Arrays are straightforward and efficient for fixed-size data.

### 3. Efficient traversal

We can easily process elements using loops.

### 4. Foundation for DSA

Many DSA techniques are built on arrays.

---

# 28. Array Disadvantages

### 1. Fixed size

Cannot dynamically grow or shrink.

### 2. Insertion can be expensive

Inserting at the beginning may require shifting elements.

### 3. Deletion can be expensive

Deleting from the middle may require shifting elements.

### 4. Same declared element type

A normal array has a single component type.

---

# 29. Time Complexity of Common Array Operations

| Operation                     | Time Complexity |
| ----------------------------- | --------------: |
| Access by index               |            O(1) |
| Update by index               |            O(1) |
| Traverse                      |            O(n) |
| Linear Search                 |            O(n) |
| Find Maximum                  |            O(n) |
| Find Minimum                  |            O(n) |
| Reverse                       |            O(n) |
| Binary Search on sorted array |        O(log n) |
| Insert at beginning           |            O(n) |
| Delete from beginning         |            O(n) |

---

# 30. Interview Questions

## Basic

1. What is an array?
2. Why does array indexing start at zero?
3. How do you declare an array?
4. How do you initialize an array?
5. What is the length of an array?
6. Are arrays fixed-size?
7. Can an array store different data types?
8. What are the default values of array elements?

## Intermediate

9. Are arrays objects in Java?
10. Where is an array created?
11. What happens when an invalid index is accessed?
12. What is the difference between `array.length` and `String.length()`?
13. What happens when one array is assigned to another?
14. How do you compare two arrays?
15. Can an array contain objects?
16. What is an array of objects?
17. What is a multidimensional array?
18. What is a jagged array?

## MTS / DSA Important

19. Why is array access O(1)?
20. What is the time complexity of linear search?
21. What is the time complexity of traversing an array?
22. What is the time complexity of finding the maximum?
23. How do you reverse an array in-place?
24. What is the difference between an array and ArrayList?
25. How are arrays passed to methods?
26. Does assigning one array variable to another copy the array?
27. How would you find the second-largest element?
28. How would you remove duplicates from an array?
29. How would you rotate an array?
30. How would you determine whether an array is sorted?

---

# 31. Detailed Interview Explanation

### Question: What is an array in Java?

**Interview Answer:**

> An array in Java is a fixed-size object that stores multiple elements of the same component type. Each element is associated with an integer index, starting from zero, which allows us to access elements directly using that index.
>
> For example, if I have an integer array containing employee salaries, I can access the third salary using `salaries[2]`. Because the index directly identifies the position of the element, accessing or updating an element by index is generally O(1).
>
> The array size is fixed once the array is created. If I create `new int[5]`, the array will have five positions and its length cannot be changed. If I require a dynamically growing data structure, I would use something like `ArrayList` instead.
>
> Internally, an array is an object, and the array variable stores a reference to that object. This is important when assigning arrays. For example, if I write `int[] b = a`, Java does not create a copy of the array. It copies the reference, so both variables refer to the same array.
>
> Java also supports multidimensional arrays. A declaration such as `int[][] matrix` is technically an array whose elements are themselves arrays. Because of this, Java can support jagged arrays where individual rows have different lengths.
>
> Arrays are particularly important in DSA because they provide constant-time indexed access and form the foundation for algorithms such as linear search, binary search, two-pointer techniques, sliding window, prefix sums, and sorting algorithms.
>
> The main advantages are fast indexed access, simplicity, and efficient traversal. The main limitation is the fixed size, and inserting or deleting elements at arbitrary positions can require shifting elements and therefore take O(n) time.

---

# 32. Why Is Array Access O(1)?

Suppose:

```java
int[] numbers = {
    10, 20, 30, 40, 50
};
```

and we want:

```java
numbers[3]
```

We don't need to inspect:

```text
10
20
30
```

first.

The index identifies the required position directly.

Conceptually:

```text
base address + index × element size
```

gives the location of the desired element.

Therefore array indexing is considered:

```text
O(1)
```

This is one of the most important reasons arrays are useful in DSA.

---

# 33. Array vs ArrayList

| Array                         | ArrayList                |
| ----------------------------- | ------------------------ |
| Fixed size                    | Dynamic size             |
| Can store primitives          | Stores objects/wrappers  |
| `length`                      | `size()`                 |
| Less abstraction              | Rich collection API      |
| Very efficient indexed access | Efficient indexed access |
| Cannot directly add/remove    | Supports add/remove      |

Example:

```java
int[] numbers = new int[5];
```

vs.

```java
ArrayList<Integer> numbers = new ArrayList<>();
```

We will study `ArrayList` and the entire **Collections Framework** later.

---

# 34. DSA Connection

Arrays are the foundation for many important DSA patterns.

Start recognizing these patterns:

```text
Array
 │
 ├── Traversal
 │
 ├── Linear Search
 │
 ├── Maximum / Minimum
 │
 ├── Two Pointers
 │
 ├── Sliding Window
 │
 ├── Prefix Sum
 │
 ├── Binary Search
 │
 ├── Sorting
 │
 └── Hashing
```

Examples of problems we'll solve later:

```text
Find maximum
Find minimum
Find second largest
Reverse array
Check sorted array
Move zeros
Remove duplicates
Two Sum
Rotate array
Maximum subarray
Prefix sum
Binary search
```

---

# 35. Quick Revision

Remember these core points:

```text
Array
    ↓
Fixed-size collection

Index
    ↓
Starts from 0

Last index
    ↓
length - 1

Access
    ↓
O(1)

Traversal
    ↓
O(n)

Linear Search
    ↓
O(n)

Array
    ↓
Object

array = anotherArray
    ↓
Reference copied

2D Array
    ↓
Array of arrays

Array
    ↓
Fixed size

ArrayList
    ↓
Dynamic size
```

---

# 36. Interview-Level Mental Model

When an interviewer gives you an array problem, immediately ask:

```text
1. Is the array sorted?
2. Do I need the index?
3. Can I modify the array?
4. Do I need extra space?
5. Is there a duplicate?
6. Can I use two pointers?
7. Can I use a sliding window?
8. Can I use prefix sums?
9. Can I use binary search?
10. What are the time and space constraints?
```

This mindset will become extremely important when we start the dedicated DSA phase.
