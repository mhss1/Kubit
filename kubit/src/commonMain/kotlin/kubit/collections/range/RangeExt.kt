package kubit.collections.range

/**
 * Calculates the sum of elements in this [IntRange] using a mathematical approach.
 * Overflows are not handled.
 *
 * @return The sum of elements in this range. Returns 0 if the range is empty.
 */
fun IntRange.fastSum(): Int {
    if (isEmpty()) return 0
    val n = (last - first + 1)
    return n * (first + last) / 2
}

/**
 * Calculates the median of elements in this [IntRange].
 *
 * If the number of elements in the range is odd, the median is the middle element.
 * If the number of elements is even, the median is the average of the two middle elements.
 *
 * @return The median of elements in this range.
 * @throws ArithmeticException when the [IntRange] is empty.
 */
fun IntRange.median(): Double {
    if (isEmpty()) throw ArithmeticException("Median of an empty range is undefined.")
    val n = (last - first + 1)
    val mid = (first + (last - first) / 2)
    return if (n % 2 == 1) mid.toDouble()
    else (mid + mid + 1) / 2.0
}

/**
 * Calculates the average of elements in this [IntRange].
 *
 * @return The average of elements in this range.
 */
fun IntRange.fastAverage(): Double {
    if (isEmpty()) return 0.0
    return fastSum().toDouble() / (last - first + 1)
}