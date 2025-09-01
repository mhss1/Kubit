package kubit.collections.list.ext

import kubit.collections.list.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

/**
 * Returns a IntList containing only elements matching the given predicate.
 */
inline fun List<Int>.filter(predicate: (Int) -> Boolean): IntList {
    val result = MutableIntList()
    forEach { element ->
        if (predicate(element)) result.add(element)
    }
    return result
}

/**
 * Returns a IntList containing only elements NOT matching the given predicate.
 */
inline fun List<Int>.filterNot(predicate: (Int) -> Boolean): IntList {
    val result = MutableIntList()
    forEach { element ->
        if (!predicate(element)) result.add(element)
    }
    return result
}

/**
 * Returns a IntList containing all non-null elements.
 */
fun List<Int?>.filterNotNull(): IntList {
    val result = MutableIntList()
    forEach { element ->
        element?.let { result.add(it) }
    }
    return result
}

/**
 * Returns a LongList containing only elements matching the given predicate.
 */
inline fun List<Long>.filter(predicate: (Long) -> Boolean): LongList {
    val result = MutableLongList()
    forEach { element ->
        if (predicate(element)) result.add(element)
    }
    return result
}

/**
 * Returns a LongList containing only elements NOT matching the given predicate.
 */
inline fun List<Long>.filterNot(predicate: (Long) -> Boolean): LongList {
    val result = MutableLongList()
    forEach { element ->
        if (!predicate(element)) result.add(element)
    }
    return result
}

/**
 * Returns a LongList containing all non-null elements.
 */
fun List<Long?>.filterNotNull(): LongList {
    val result = MutableLongList()
    forEach { element ->
        element?.let { result.add(it) }
    }
    return result
}

/**
 * Returns a FloatList containing only elements matching the given predicate.
 */
inline fun List<Float>.filter(predicate: (Float) -> Boolean): FloatList {
    val result = MutableFloatList()
    forEach { element ->
        if (predicate(element)) result.add(element)
    }
    return result
}

/**
 * Returns a FloatList containing only elements NOT matching the given predicate.
 */
inline fun List<Float>.filterNot(predicate: (Float) -> Boolean): FloatList {
    val result = MutableFloatList()
    forEach { element ->
        if (!predicate(element)) result.add(element)
    }
    return result
}

/**
 * Returns a FloatList containing all non-null elements.
 */
fun List<Float?>.filterNotNull(): FloatList {
    val result = MutableFloatList()
    forEach { element ->
        element?.let { result.add(it) }
    }
    return result
}

/**
 * Returns a DoubleList containing only elements matching the given predicate.
 */
inline fun List<Double>.filter(predicate: (Double) -> Boolean): DoubleList {
    val result = MutableDoubleList()
    forEach { element ->
        if (predicate(element)) result.add(element)
    }
    return result
}

/**
 * Returns a DoubleList containing only elements NOT matching the given predicate.
 */
inline fun List<Double>.filterNot(predicate: (Double) -> Boolean): DoubleList {
    val result = MutableDoubleList()
    forEach { element ->
        if (!predicate(element)) result.add(element)
    }
    return result
}

/**
 * Returns a DoubleList containing all non-null elements.
 */
fun List<Double?>.filterNotNull(): DoubleList {
    val result = MutableDoubleList()
    forEach { element ->
        element?.let { result.add(it) }
    }
    return result
}
