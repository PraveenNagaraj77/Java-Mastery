# Big O Notation — Time and Auxiliary Space Complexity

## 1. What is Big O?

Big O notation describes how an algorithm's resource requirements grow as the input size increases.

It is commonly used to analyze:

- **Time complexity:** How the number of operations grows.
- **Space complexity:** How memory usage grows.
- **Auxiliary space complexity:** Extra memory used by an algorithm, excluding the input itself.

Big O focuses on the growth rate rather than exact execution time.

For example, a loop that processes every element in an array of length `n` has linear time complexity, `O(n)`.

## 2. Common Time Complexities

| Complexity | Name | Typical example |
|---|---|---|
| `O(1)` | Constant | Accessing an array element |
| `O(log n)` | Logarithmic | Repeatedly halving or doubling a search range |
| `O(n)` | Linear | Traversing an array |
| `O(n log n)` | Linearithmic | Efficient comparison sorting |
| `O(n²)` | Quadratic | Comparing every pair using nested loops |
| `O(n³)` | Cubic | Three nested linear loops |
| `O(2^n)` | Exponential | Some recursive subset-generation algorithms |
| `O(n!)` | Factorial | Generating all permutations |

These are common patterns, not universal guarantees for every algorithm in a category.

## 3. Rule 1 — Constant Time: O(1)

An operation takes constant time when its work does not grow with the input size.

```java
public static int getFirst(int[] nums) {
    return nums[0];
}
```

The method accesses one element regardless of the array's length.

- Time: `O(1)`
- Auxiliary space: `O(1)`

## 4. Rule 2 — Linear Time: O(n)

A loop that processes each input element once usually takes linear time.

```java
for (int i = 0; i < n; i++) {
    System.out.println(i);
}
```

The loop runs `n` times.

- Time: `O(n)`
- Auxiliary space: `O(1)` if only fixed-size variables are used.

A loop that increments by a fixed amount, such as `i += 2`, is also `O(n)`.

## 5. Rule 3 — Logarithmic Time: O(log n)

A loop is logarithmic when its counter grows or shrinks by a constant factor.

```java
for (int i = 1; i < n; i *= 2) {
    System.out.println(i);
}
```

The values are approximately:

`1, 2, 4, 8, 16, 32, ...`

After `k` iterations, the counter is approximately `2^k`. It reaches `n` after roughly `log₂(n)` iterations.

- Time: `O(log n)`
- Auxiliary space: `O(1)`

Repeatedly halving a value until it reaches a stopping condition also commonly produces `O(log n)` time.

## 6. Rule 4 — Sequential Loops: Add Their Costs

When loops run one after another, add their costs.

```java
for (int i = 0; i < n; i++) {
    // O(n)
}

for (int j = 1; j < n; j *= 2) {
    // O(log n)
}
```

Total:

`O(n) + O(log n) = O(n + log n) = O(n)`

The linear term dominates the logarithmic term.

Another example:

Two sequential logarithmic loops take:

`O(log n) + O(log n) = O(2 log n) = O(log n)`

**Remember:** Sequential loops are added, not multiplied.

## 7. Rule 5 — Nested Loops: Multiply Their Costs

When one loop runs inside another, multiply the iteration counts when the inner work repeats for each outer iteration.

### Example A: Two linear loops nested

```java
for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
        // Constant-time work
    }
}
```

The inner loop executes `n` times for each of `n` outer iterations.

`O(n) × O(n) = O(n²)`

### Example B: Logarithmic outer loop and linear inner loop

```java
for (int i = 1; i < n; i *= 2) {
    for (int j = 0; j < n; j++) {
        // Constant-time work
    }
}
```

- Outer loop: `O(log n)`
- Inner loop: `O(n)` per outer iteration

Total: `O(n log n)`.

### Example C: Two logarithmic loops nested

```java
for (int i = 1; i < n; i *= 2) {
    for (int j = 1; j < n; j *= 2) {
        // Constant-time work
    }
}
```

Each loop takes `O(log n)`.

Total: `O((log n)²)`.

**Remember:** For nested loops, multiply the iteration counts, not the loop variable values.

## 8. Rule 6 — Changing Inner-Loop Bounds

An inner loop does not always execute `n` times on every outer iteration.

```java
for (int i = 0; i < n; i++) {
    for (int j = i; j < n; j++) {
        // Constant-time work
    }
}
```

The inner-loop iteration counts are:

- When `i = 0`: `n`
- When `i = 1`: `n - 1`
- When `i = 2`: `n - 2`
- ...
- When `i = n - 1`: `1`

Total:

`n + (n - 1) + ... + 1 = n(n + 1) / 2`

Expanding:

`(n² + n) / 2`

Ignoring constants and lower-order terms gives `O(n²)`.

Even though the inner loop runs fewer times on later iterations, the overall complexity remains quadratic.

## 9. Simplifying Big O Expressions

When simplifying a sum:

1. Remove constant factors.
2. Keep the dominant growth term.
3. Drop lower-order terms.

Examples:

- `O(3n) = O(n)`
- `O(n + log n) = O(n)`
- `O(n² + n) = O(n²)`
- `O(n² + 5n + 10) = O(n²)`
- `O(2 log n) = O(log n)`

For a product of iteration counts:

- `O(n) × O(n) = O(n²)`
- `O(n) × O(log n) = O(n log n)`
- `O(log n) × O(log n) = O((log n)²)`

## 10. Auxiliary Space Complexity

Auxiliary space is the extra memory an algorithm allocates or uses beyond its input.

### Example A: Constant auxiliary space

```java
public static int findMax(int[] nums) {
    int max = nums[0];

    for (int i = 1; i < nums.length; i++) {
        if (nums[i] > max) {
            max = nums[i];
        }
    }

    return max;
}
```

The method uses a fixed number of variables.

- Time: `O(n)`
- Auxiliary space: `O(1)`

The input array is not counted as auxiliary space.

### Example B: Linear auxiliary space

```java
int[] copy = new int[n];
```

The new array stores `n` elements.

- Space: `O(n)`

### Example C: Quadratic auxiliary space

```java
int[][] matrix = new int[n][n];
```

The matrix contains `n × n = n²` cells.

- Space: `O(n²)`

If nested loops fill every cell, the time complexity is also `O(n²)`.

### Important distinction

Nested loops do not automatically mean high space complexity. A method can perform `O(n²)` operations while using only `O(1)` auxiliary space.

Conversely, a method can use `O(n²)` space simply by allocating an `n × n` matrix.

Analyze time and auxiliary space separately.

## 11. A Reliable Interview Approach

For any Java method, follow these steps:

1. Identify the input size, usually `n`.
2. Count how many times each loop executes.
3. Determine whether loops are sequential or nested.
4. For changing bounds, analyze the actual iteration pattern.
5. Count the dominant operations and simplify the result.
6. Identify additional arrays, collections, matrices, or recursive call stacks.
7. Exclude the input itself when calculating auxiliary space.
8. State time and auxiliary space separately and explain why.

## 12. Common Mistakes

- Multiplying the costs of sequential loops instead of adding them.
- Assuming every nested loop is `O(n²)` without checking its bounds.
- Treating a doubling loop as `O(n)` instead of `O(log n)`.
- Assuming nested loops necessarily use `O(n²)` memory.
- Counting the input array as newly allocated auxiliary space.
- Forgetting that an `n × n` matrix contains `n²` cells.
- Choosing the wrong multiple-choice option even when the reasoning is correct.

## 13. Practice Results

| Exercise | Time | Auxiliary space |
|---|---|---|
| Sum array elements | `O(n)` | `O(1)` |
| Create an `n × n` matrix | `O(n²)` | `O(n²)` |
| Doubling outer loop with linear inner loop | `O(n log n)` | `O(1)` |
| Inner loop starts at outer-loop index | `O(n²)` | `O(1)` |
| Find maximum in an array | `O(n)` | `O(1)` |

## 14. Final Revision Checklist

Before moving to the next DSA topic, make sure you can:

- [ ] Explain `O(1)`, `O(log n)`, `O(n)`, `O(n log n)`, and `O(n²)`.
- [ ] Analyze a single loop.
- [ ] Analyze doubling and halving loops.
- [ ] Add the costs of sequential loops.
- [ ] Multiply the costs of nested loops.
- [ ] Analyze loops with changing bounds.
- [ ] Calculate auxiliary space for variables, arrays, and matrices.
- [ ] Explain why time and auxiliary space may have different complexities.

**Next step:** Practice identifying time and auxiliary space independently, then begin short Java coding exercises and LeetCode problems while maintaining these notes.
