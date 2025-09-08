package kubit.collections.array

import kubit.collections.internal.throwIllegalArgumentException

/**
 * Returns `true` if at least [n] elements in the list match the given [predicate].
 *
 * @param n The minimum number of elements that must match the predicate.
 * @param predicate A function that takes an element of type T and returns a Boolean.
 * @return `true` if at least [n] elements match the predicate, `false` otherwise.
 * @throws IllegalArgumentException if [n] is negative.
 */
inline fun <T> Array<T>.atLeast(n: Int, predicate: (T) -> Boolean): Boolean {
    if (n < 0) throwIllegalArgumentException("n must be >= 0")
    if (n == 0) return true
    var count = 0
    forEach { element ->
        if (predicate(element) && ++count >= n) {
            return true
        }
    }
    return false
}

/**
 * Returns `true` if at most [n] elements in the list match the given [predicate].
 *
 * @param n The maximum number of elements that may match the predicate.
 * @param predicate A function that takes an element of type T and returns a Boolean.
 * @return `true` if at most [n] elements match the predicate, `false` otherwise.
 * @throws IllegalArgumentException if [n] is negative.
 */
inline fun <T> Array<T>.atMost(n: Int, predicate: (T) -> Boolean): Boolean {
    if (n < 0) throwIllegalArgumentException("n must be >= 0")
    var count = 0
    forEach { element ->
        if (predicate(element) && ++count > n) {
            return false
        }
    }
    return true
}

/**
 * Returns `true` if at least [n] elements in the list match the given [predicate].
 *
 * @param n The minimum number of elements that must match the predicate.
 * @param predicate A function that takes an element of type Int and returns a Boolean.
 * @return `true` if at least [n] elements match the predicate, `false` otherwise.
 * @throws IllegalArgumentException if [n] is negative.
 */
inline fun IntArray.atLeast(n: Int, predicate: (Int) -> Boolean): Boolean {
    if (n < 0) throwIllegalArgumentException("n must be >= 0")
    if (n == 0) return true
    var count = 0
    forEach { element ->
        if (predicate(element) && ++count >= n) {
            return true
        }
    }
    return false
}

/**
 * Returns `true` if at most [n] elements in the list match the given [predicate].
 *
 * @param n The maximum number of elements that may match the predicate.
 * @param predicate A function that takes an element of type Int and returns a Boolean.
 * @return `true` if at most [n] elements match the predicate, `false` otherwise.
 * @throws IllegalArgumentException if [n] is negative.
 */
inline fun IntArray.atMost(n: Int, predicate: (Int) -> Boolean): Boolean {
    if (n < 0) throwIllegalArgumentException("n must be >= 0")
    var count = 0
    forEach { element ->
        if (predicate(element) && ++count > n) {
            return false
        }
    }
    return true
}

/**
 * Returns `true` if at least [n] elements in the list match the given [predicate].
 *
 * @param n The minimum number of elements that must match the predicate.
 * @param predicate A function that takes an element of type Long and returns a Boolean.
 * @return `true` if at least [n] elements match the predicate, `false` otherwise.
 * @throws IllegalArgumentException if [n] is negative.
 */
inline fun LongArray.atLeast(n: Int, predicate: (Long) -> Boolean): Boolean {
    if (n < 0) throwIllegalArgumentException("n must be >= 0")
    if (n == 0) return true
    var count = 0
    forEach { element ->
        if (predicate(element) && ++count >= n) {
            return true
        }
    }
    return false
}

/**
 * Returns `true` if at most [n] elements in the list match the given [predicate].
 *
 * @param n The maximum number of elements that may match the predicate.
 * @param predicate A function that takes an element of type Long and returns a Boolean.
 * @return `true` if at most [n] elements match the predicate, `false` otherwise.
 * @throws IllegalArgumentException if [n] is negative.
 */
inline fun LongArray.atMost(n: Int, predicate: (Long) -> Boolean): Boolean {
    if (n < 0) throwIllegalArgumentException("n must be >= 0")
    var count = 0
    forEach { element ->
        if (predicate(element) && ++count > n) {
            return false
        }
    }
    return true
}

/**
 * Returns `true` if at least [n] elements in the list match the given [predicate].
 *
 * @param n The minimum number of elements that must match the predicate.
 * @param predicate A function that takes an element of type Float and returns a Boolean.
 * @return `true` if at least [n] elements match the predicate, `false` otherwise.
 * @throws IllegalArgumentException if [n] is negative.
 */
inline fun FloatArray.atLeast(n: Int, predicate: (Float) -> Boolean): Boolean {
    if (n < 0) throwIllegalArgumentException("n must be >= 0")
    if (n == 0) return true
    var count = 0
    forEach { element ->
        if (predicate(element) && ++count >= n) {
            return true
        }
    }
    return false
}

/**
 * Returns `true` if at most [n] elements in the list match the given [predicate].
 *
 * @param n The maximum number of elements that may match the predicate.
 * @param predicate A function that takes an element of type Float and returns a Boolean.
 * @return `true` if at most [n] elements match the predicate, `false` otherwise.
 * @throws IllegalArgumentException if [n] is negative.
 */
inline fun FloatArray.atMost(n: Int, predicate: (Float) -> Boolean): Boolean {
    if (n < 0) throwIllegalArgumentException("n must be >= 0")
    var count = 0
    forEach { element ->
        if (predicate(element) && ++count > n) {
            return false
        }
    }
    return true
}

/**
 * Returns `true` if at least [n] elements in the list match the given [predicate].
 *
 * @param n The minimum number of elements that must match the predicate.
 * @param predicate A function that takes an element of type Double and returns a Boolean.
 * @return `true` if at least [n] elements match the predicate, `false` otherwise.
 * @throws IllegalArgumentException if [n] is negative.
 */
inline fun DoubleArray.atLeast(n: Int, predicate: (Double) -> Boolean): Boolean {
    if (n < 0) throwIllegalArgumentException("n must be >= 0")
    if (n == 0) return true
    var count = 0
    forEach { element ->
        if (predicate(element) && ++count >= n) {
            return true
        }
    }
    return false
}

/**
 * Returns `true` if at most [n] elements in the list match the given [predicate].
 *
 * @param n The maximum number of elements that may match the predicate.
 * @param predicate A function that takes an element of type Double and returns a Boolean.
 * @return `true` if at most [n] elements match the predicate, `false` otherwise.
 * @throws IllegalArgumentException if [n] is negative.
 */
inline fun DoubleArray.atMost(n: Int, predicate: (Double) -> Boolean): Boolean {
    if (n < 0) throwIllegalArgumentException("n must be >= 0")
    var count = 0
    forEach { element ->
        if (predicate(element) && ++count > n) {
            return false
        }
    }
    return true
}
