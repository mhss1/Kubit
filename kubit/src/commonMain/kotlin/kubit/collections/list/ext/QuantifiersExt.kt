package kubit.collections.list.ext

import kubit.collections.internal.throwIllegalArgumentException

/**
 * Returns `true` if at least [n] elements in the list match the given [predicate].
 *
 * @param n The minimum number of elements that must match the predicate.
 * @param predicate A function that takes an element of type T and returns a Boolean.
 * @return `true` if at least [n] elements match the predicate, `false` otherwise.
 * @throws IllegalArgumentException if [n] is negative.
 */
inline fun <T> List<T>.atLeast(n: Int, predicate: (T) -> Boolean): Boolean {
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
inline fun <T> List<T>.atMost(n: Int, predicate: (T) -> Boolean): Boolean {
    if (n < 0) throwIllegalArgumentException("n must be >= 0")
    var count = 0
    forEach { element ->
        if (predicate(element) && ++count > n) {
            return false
        }
    }
    return true
}