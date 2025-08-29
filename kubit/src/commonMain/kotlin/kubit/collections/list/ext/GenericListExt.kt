package kubit.collections.list.ext

import kubit.collections.list.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

/**
 * A fast forEach implementation that uses a for-loop for RandomAccess Lists
 * and falls back to forEach for other List types.
 */
inline fun <T> List<T>.fastForEach(action: (T) -> Unit) {
    if (this is RandomAccess) for (i in indices) action(this[i])
    else forEach(action)
}

/**
 * A fast forEachIndexed implementation that uses a for-loop for RandomAccess Lists
 * and falls back to forEachIndexed for other List types.
 */
inline fun <T> List<T>.fastForEachIndexed(action: (Int, T) -> Unit) {
    if (this is RandomAccess) for (i in indices) action(i, this[i])
    else forEachIndexed(action)
}

/**
 * Returns a IntList containing only elements matching the given predicate.
 */
inline fun List<Int>.filter(predicate: (Int) -> Boolean): IntList {
    val result = MutableIntList()
    fastForEach { element ->
        if (predicate(element)) result.add(element)
    }
    return result
}

/**
 * Returns a IntList containing only elements NOT matching the given predicate.
 */
inline fun List<Int>.filterNot(predicate: (Int) -> Boolean): IntList {
    val result = MutableIntList()
    fastForEach { element ->
        if (!predicate(element)) result.add(element)
    }
    return result
}

/**
 * Returns a IntList containing all non-null elements.
 */
fun List<Int?>.filterNotNull(): IntList {
    val result = MutableIntList()
    fastForEach { element ->
        element?.let { result.add(it) }
    }
    return result
}

/**
 * Returns a LongList containing only elements matching the given predicate.
 */
inline fun List<Long>.filter(predicate: (Long) -> Boolean): LongList {
    val result = MutableLongList()
    fastForEach { element ->
        if (predicate(element)) result.add(element)
    }
    return result
}

/**
 * Returns a LongList containing only elements NOT matching the given predicate.
 */
inline fun List<Long>.filterNot(predicate: (Long) -> Boolean): LongList {
    val result = MutableLongList()
    fastForEach { element ->
        if (!predicate(element)) result.add(element)
    }
    return result
}

/**
 * Returns a LongList containing all non-null elements.
 */
fun List<Long?>.filterNotNull(): LongList {
    val result = MutableLongList()
    fastForEach { element ->
        element?.let { result.add(it) }
    }
    return result
}

/**
 * Returns a FloatList containing only elements matching the given predicate.
 */
inline fun List<Float>.filter(predicate: (Float) -> Boolean): FloatList {
    val result = MutableFloatList()
    fastForEach { element ->
        if (predicate(element)) result.add(element)
    }
    return result
}

/**
 * Returns a FloatList containing only elements NOT matching the given predicate.
 */
inline fun List<Float>.filterNot(predicate: (Float) -> Boolean): FloatList {
    val result = MutableFloatList()
    fastForEach { element ->
        if (!predicate(element)) result.add(element)
    }
    return result
}

/**
 * Returns a FloatList containing all non-null elements.
 */
fun List<Float?>.filterNotNull(): FloatList {
    val result = MutableFloatList()
    fastForEach { element ->
        element?.let { result.add(it) }
    }
    return result
}

/**
 * Returns a DoubleList containing only elements matching the given predicate.
 */
inline fun List<Double>.filter(predicate: (Double) -> Boolean): DoubleList {
    val result = MutableDoubleList()
    fastForEach { element ->
        if (predicate(element)) result.add(element)
    }
    return result
}

/**
 * Returns a DoubleList containing only elements NOT matching the given predicate.
 */
inline fun List<Double>.filterNot(predicate: (Double) -> Boolean): DoubleList {
    val result = MutableDoubleList()
    fastForEach { element ->
        if (!predicate(element)) result.add(element)
    }
    return result
}

/**
 * Returns a DoubleList containing all non-null elements.
 */
fun List<Double?>.filterNotNull(): DoubleList {
    val result = MutableDoubleList()
    fastForEach { element ->
        element?.let { result.add(it) }
    }
    return result
}
