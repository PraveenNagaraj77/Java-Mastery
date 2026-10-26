# Arrays in Java — DSA Notes

## 1. What is an Array?

An array is a fixed-size data structure that stores elements of the same type.

```java
int[] numbers = {10, 20, 30, 40, 50};
int[] values = new int[5];
```

### Key properties

- Indexing starts at `0`.
- Last valid index: `arr.length - 1`.
- Array length is fixed after creation.
- Arrays can store primitives or object references.
- Java initializes array elements with default values.
- Accessing an invalid index throws `ArrayIndexOutOfBoundsException`.

## 2. Traversing an Array

```java
for (int i = 0; i < arr.length; i++) {
    System.out.println(arr[i]);
}
```

Time: O(n)  
Auxiliary space: O(1)

## 3. Find Sum, Minimum and Maximum

Initialize the result using the first element, then traverse the remaining elements.

```java
int max = arr[0];

for (int i = 1; i < arr.length; i++) {
    if (arr[i] > max) {
        max = arr[i];
    }
}
```

- Maximum: O(n) time, O(1) space.
- Minimum: O(n) time, O(1) space.
- Sum: O(n) time, O(1) space.

Handle an empty array before accessing `arr[0]`.

## 4. Count Even and Odd Elements

```java
int even = 0;
int odd = 0;

for (int value : arr) {
    if (value % 2 == 0) {
        even++;
    } else {
        odd++;
    }
}
```

Time: O(n)  
Auxiliary space: O(1)

## 5. Reverse an Array — Two Pointers

Use a left pointer at the beginning and a right pointer at the end.

```java
int left = 0;
int right = arr.length - 1;

while (left < right) {
    int temp = arr[left];
    arr[left] = arr[right];
    arr[right] = temp;

    left++;
    right--;
}
```

Time: O(n)  
Auxiliary space: O(1)

## 6. Check Whether an Array Is Sorted

For ascending order, each element must be greater than or equal to its predecessor.

```java
for (int i = 1; i < arr.length; i++) {
    if (arr[i] < arr[i - 1]) {
        return false;
    }
}
return true;
```

Time: O(n) worst case  
Auxiliary space: O(1)

Equal adjacent elements are allowed. Empty and single-element arrays are considered sorted.

## 7. Find the Second-Largest Distinct Element

Track the largest and second-largest distinct values in one traversal.

Important cases:
- Duplicates must not count as a second distinct value.
- Negative numbers must work.
- If no second distinct value exists, define how the method handles it.

Time: O(n)  
Auxiliary space: O(1)

## 8. Move Zeros to the End

Use a write pointer to place nonzero elements in their original relative order. Fill the remaining positions with zeros.

```java
int write = 0;

for (int value : arr) {
    if (value != 0) {
        arr[write++] = value;
    }
}

while (write < arr.length) {
    arr[write++] = 0;
}
```

Time: O(n)  
Auxiliary space: O(1)

This modifies the original array.

## 9. Remove Duplicates from a Sorted Array

Use two pointers:
- `i`: position of the last unique element.
- `j`: scans the remaining elements.

When a new value is found, advance `i` and copy the value there.

Return the new logical length. Elements beyond that length are irrelevant.

Time: O(n)  
Auxiliary space: O(1)

**Important:** This approach assumes the input is sorted.

## 10. Move Negative Numbers to One Side

Use two pointers from opposite ends. Advance the left pointer when it finds a negative number, and the right pointer when it finds a nonnegative number. Swap misplaced elements.

Time: O(n)  
Auxiliary space: O(1)

The order of elements is not guaranteed to be preserved.

## 11. Left-Rotate an Array by One

Save the first element, shift all remaining elements left, and place the saved value at the end.

Time: O(n)  
Auxiliary space: O(1)

## 12. Left-Rotate an Array by K Positions

A basic approach performs the one-position rotation repeatedly.

```java
k = k % arr.length;
```

This reduces unnecessary rotations when `k` is larger than the array length. Check for an empty array before using the modulo operation.

Repeated shifting:
- Time: O(n × k) after normalization.
- Auxiliary space: O(1).

The reversal algorithm can improve rotation to O(n) time and O(1) auxiliary space.

## 13. Find a Missing Number from 0 to N

If the array contains distinct numbers from `0` through `n`, with exactly one missing:

Expected sum = `n * (n + 1) / 2`

Missing number = expected sum − actual sum.

Time: O(n)  
Auxiliary space: O(1)

Use `long` for the sum if integer overflow is possible. The formula assumes the stated input constraints.

## 14. Two Sum — HashMap

Find two indices whose values add up to a target.

For each element:
1. Calculate `needed = target - arr[i]`.
2. Check whether `needed` already exists in the map.
3. If found, return the stored index and current index.
4. Otherwise, store the current value and index.

Time: O(n) average  
Auxiliary space: O(n)

Check before inserting the current element to avoid using the same element twice.

## 15. Best Time to Buy and Sell Stock

Given daily prices, choose a buy day and a later sell day to maximize profit.

Maintain:
- `minPrice`: minimum price seen so far.
- `maxProfit`: maximum profit seen so far.

For each price, calculate its profit using `price - minPrice`, update `maxProfit`, then update `minPrice`.

Time: O(n)  
Auxiliary space: O(1)

If no positive profit is possible, return `0`.

## 16. Common Array Patterns

| Pattern | Typical use |
|---|---|
| Single traversal | Sum, minimum, maximum |
| Two pointers | Reverse, partition |
| Read/write pointers | Move zeros, remove duplicates |
| Running minimum | Stock profit |
| HashMap | Two Sum, frequency counting |
| Mathematical formula | Missing number |
| In-place modification | Rotations, rearrangement |

## 17. Big-O Quick Revision

- O(1): constant work, such as accessing `arr[i]`.
- O(n): one traversal.
- O(n²): two nested loops over the array.
- O(log n): binary search on a sorted array.
- O(n) auxiliary space: an extra collection proportional to the input size.

Auxiliary space excludes the input array itself.

## 18. Interview Checklist

Before coding an array problem, ask:

1. Is the array empty or does it contain one element?
2. Are duplicates possible?
3. Can values be negative?
4. Is the array sorted?
5. Must the original array be modified?
6. Must element order be preserved?
7. Can arithmetic overflow?
8. Can I solve it in one traversal?
9. What are the time and auxiliary-space complexities?