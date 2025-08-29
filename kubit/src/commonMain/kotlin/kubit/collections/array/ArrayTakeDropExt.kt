package kubit.collections.array

import kubit.collections.list.*
import kubit.collections.list.internal.requirePrecondition

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

/**
 * Returns a IntList containing first [n] elements.
 * @throws IllegalArgumentException if [n] is negative.
 */
fun IntArray.take(n: Int): IntList {
    requirePrecondition(n >= 0) { "n should be >= 0" }
    val listSize = n.coerceAtMost(size)
    return MutableIntList(listSize).also { list ->
        copyInto(list.content, 0, 0, listSize)
        list._size = listSize
    }
}

/**
 * Returns a IntList containing last [n] elements.
 * @throws IllegalArgumentException if [n] is negative.
 */
fun IntArray.takeLast(n: Int): IntList {
    requirePrecondition(n >= 0) { "n should be >= 0" }
    val listSize = n.coerceAtMost(size)
    return MutableIntList(listSize).also { list ->
        copyInto(list.content, 0, size - listSize, size)
        list._size = listSize
    }
}

/**
 * Returns a IntList containing all elements except first [n] elements.
 * @throws IllegalArgumentException if [n] is negative.
 */
fun IntArray.drop(n: Int): IntList {
    requirePrecondition(n >= 0) { "n should be >= 0" }
    return takeLast(size - n.coerceAtMost(size))
}

/**
 * Returns a IntList containing all elements except last [n] elements.
 * @throws IllegalArgumentException if [n] is negative.
 */
fun IntArray.dropLast(n: Int): IntList {
    requirePrecondition(n >= 0) { "n should be >= 0" }
    return take(size - n.coerceAtMost(size))
}

/**
 * Returns a LongList containing first [n] elements.
 * @throws IllegalArgumentException if [n] is negative.
 */
fun LongArray.take(n: Int): LongList {
    requirePrecondition(n >= 0) { "n should be >= 0" }
    val listSize = n.coerceAtMost(size)
    return MutableLongList(listSize).also { list ->
        copyInto(list.content, 0, 0, listSize)
        list._size = listSize
    }
}

/**
 * Returns a LongList containing last [n] elements.
 * @throws IllegalArgumentException if [n] is negative.
 */
fun LongArray.takeLast(n: Int): LongList {
    requirePrecondition(n >= 0) { "n should be >= 0" }
    val listSize = n.coerceAtMost(size)
    return MutableLongList(listSize).also { list ->
        copyInto(list.content, 0, size - listSize, size)
        list._size = listSize
    }
}

/**
 * Returns a LongList containing all elements except first [n] elements.
 * @throws IllegalArgumentException if [n] is negative.
 */
fun LongArray.drop(n: Int): LongList {
    requirePrecondition(n >= 0) { "n should be >= 0" }
    return takeLast(size - n.coerceAtMost(size))
}

/**
 * Returns a LongList containing all elements except last [n] elements.
 * @throws IllegalArgumentException if [n] is negative.
 */
fun LongArray.dropLast(n: Int): LongList {
    requirePrecondition(n >= 0) { "n should be >= 0" }
    return take(size - n.coerceAtMost(size))
}

/**
 * Returns a FloatList containing first [n] elements.
 * @throws IllegalArgumentException if [n] is negative.
 */
fun FloatArray.take(n: Int): FloatList {
    requirePrecondition(n >= 0) { "n should be >= 0" }
    val listSize = n.coerceAtMost(size)
    return MutableFloatList(listSize).also { list ->
        copyInto(list.content, 0, 0, listSize)
        list._size = listSize
    }
}

/**
 * Returns a FloatList containing last [n] elements.
 * @throws IllegalArgumentException if [n] is negative.
 */
fun FloatArray.takeLast(n: Int): FloatList {
    requirePrecondition(n >= 0) { "n should be >= 0" }
    val listSize = n.coerceAtMost(size)
    return MutableFloatList(listSize).also { list ->
        copyInto(list.content, 0, size - listSize, size)
        list._size = listSize
    }
}

/**
 * Returns a FloatList containing all elements except first [n] elements.
 * @throws IllegalArgumentException if [n] is negative.
 */
fun FloatArray.drop(n: Int): FloatList {
    requirePrecondition(n >= 0) { "n should be >= 0" }
    return takeLast(size - n.coerceAtMost(size))
}

/**
 * Returns a FloatList containing all elements except last [n] elements.
 * @throws IllegalArgumentException if [n] is negative.
 */
fun FloatArray.dropLast(n: Int): FloatList {
    requirePrecondition(n >= 0) { "n should be >= 0" }
    return take(size - n.coerceAtMost(size))
}

/**
 * Returns a DoubleList containing first [n] elements.
 * @throws IllegalArgumentException if [n] is negative.
 */
fun DoubleArray.take(n: Int): DoubleList {
    requirePrecondition(n >= 0) { "n should be >= 0" }
    val listSize = n.coerceAtMost(size)
    return MutableDoubleList(listSize).also { list ->
        copyInto(list.content, 0, 0, listSize)
        list._size = listSize
    }
}

/**
 * Returns a DoubleList containing last [n] elements.
 * @throws IllegalArgumentException if [n] is negative.
 */
fun DoubleArray.takeLast(n: Int): DoubleList {
    requirePrecondition(n >= 0) { "n should be >= 0" }
    val listSize = n.coerceAtMost(size)
    return MutableDoubleList(listSize).also { list ->
        copyInto(list.content, 0, size - listSize, size)
        list._size = listSize
    }
}

/**
 * Returns a DoubleList containing all elements except first [n] elements.
 * @throws IllegalArgumentException if [n] is negative.
 */
fun DoubleArray.drop(n: Int): DoubleList {
    requirePrecondition(n >= 0) { "n should be >= 0" }
    return takeLast(size - n.coerceAtMost(size))
}

/**
 * Returns a DoubleList containing all elements except last [n] elements.
 * @throws IllegalArgumentException if [n] is negative.
 */
fun DoubleArray.dropLast(n: Int): DoubleList {
    requirePrecondition(n >= 0) { "n should be >= 0" }
    return take(size - n.coerceAtMost(size))
}
