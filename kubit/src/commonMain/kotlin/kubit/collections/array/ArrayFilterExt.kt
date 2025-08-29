package kubit.collections.array

import kubit.collections.list.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

/**
 * Returns a IntList containing only elements matching the given predicate.
 */
inline fun IntArray.filter(predicate: (Int) -> Boolean): IntList {
    val list = MutableIntList()
    for (element in this) if (predicate(element)) list.add(element)
    return list
}

/**
 * Returns a IntArray containing only elements matching the given predicate.
 */
inline fun IntArray.filterArray(predicate: (Int) -> Boolean): IntArray {
    return this.filter(predicate).toIntArray()
}

/**
 * Returns a LongList containing only elements matching the given predicate.
 */
inline fun LongArray.filter(predicate: (Long) -> Boolean): LongList {
    val list = MutableLongList()
    for (element in this) if (predicate(element)) list.add(element)
    return list
}

/**
 * Returns a LongArray containing only elements matching the given predicate.
 */
inline fun LongArray.filterArray(predicate: (Long) -> Boolean): LongArray {
    return this.filter(predicate).toLongArray()
}

/**
 * Returns a FloatList containing only elements matching the given predicate.
 */
inline fun FloatArray.filter(predicate: (Float) -> Boolean): FloatList {
    val list = MutableFloatList()
    for (element in this) if (predicate(element)) list.add(element)
    return list
}

/**
 * Returns a FloatArray containing only elements matching the given predicate.
 */
inline fun FloatArray.filterArray(predicate: (Float) -> Boolean): FloatArray {
    return this.filter(predicate).toFloatArray()
}

/**
 * Returns a DoubleList containing only elements matching the given predicate.
 */
inline fun DoubleArray.filter(predicate: (Double) -> Boolean): DoubleList {
    val list = MutableDoubleList()
    for (element in this) if (predicate(element)) list.add(element)
    return list
}

/**
 * Returns a DoubleArray containing only elements matching the given predicate.
 */
inline fun DoubleArray.filterArray(predicate: (Double) -> Boolean): DoubleArray {
    return this.filter(predicate).toDoubleArray()
}

/**
 * Returns a IntList containing only elements that, when transformed, match the predicate.
 */
inline fun Array<Int>.filter(predicate: (Int) -> Boolean): IntList {
    val list = MutableIntList()
    for (element in this) if (predicate(element)) list.add(element)
    return list
}

/**
 * Returns a IntArray containing only elements that, when transformed, match the predicate.
 */
inline fun Array<Int>.filterArray(predicate: (Int) -> Boolean): IntArray {
    return this.filter(predicate).toIntArray()
}

/**
 * Returns a LongList containing only elements that, when transformed, match the predicate.
 */
inline fun Array<Long>.filter(predicate: (Long) -> Boolean): LongList {
    val list = MutableLongList()
    for (element in this) if (predicate(element)) list.add(element)
    return list
}

/**
 * Returns a LongArray containing only elements that, when transformed, match the predicate.
 */
inline fun Array<Long>.filterArray(predicate: (Long) -> Boolean): LongArray {
    return this.filter(predicate).toLongArray()
}

/**
 * Returns a FloatList containing only elements that, when transformed, match the predicate.
 */
inline fun Array<Float>.filter(predicate: (Float) -> Boolean): FloatList {
    val list = MutableFloatList()
    for (element in this) if (predicate(element)) list.add(element)
    return list
}

/**
 * Returns a FloatArray containing only elements that, when transformed, match the predicate.
 */
inline fun Array<Float>.filterArray(predicate: (Float) -> Boolean): FloatArray {
    return this.filter(predicate).toFloatArray()
}

/**
 * Returns a DoubleList containing only elements that, when transformed, match the predicate.
 */
inline fun Array<Double>.filter(predicate: (Double) -> Boolean): DoubleList {
    val list = MutableDoubleList()
    for (element in this) if (predicate(element)) list.add(element)
    return list
}

/**
 * Returns a DoubleArray containing only elements that, when transformed, match the predicate.
 */
inline fun Array<Double>.filterArray(predicate: (Double) -> Boolean): DoubleArray {
    return this.filter(predicate).toDoubleArray()
}
