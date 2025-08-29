package kubit.collections.list.ext

import kubit.collections.list.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

/**
 * Iterates over each element in all nested lists and applies the given [action].
 */
fun <T> List<List<T>>.forEachFlattened(action: (T) -> Unit) {
    fastForEach { list ->
        list.fastForEach { item ->
            action(item)
        }
    }
}

/**
 * Returns a flattened list containing only the elements that match the given [predicate].
 */
fun <T> List<List<T>>.filterFlattened(predicate: (T) -> Boolean): List<T> {
    val result = mutableListOf<T>()
    fastForEach { list ->
        list.fastForEach { item ->
            if (predicate(item)) result.add(item)
        }
    }
    return result
}

/**
 * Returns a flattened list produced by applying [transform] to each element of the nested lists (generic version).
 */
@OverloadResolutionByLambdaReturnType
fun <T, E> List<List<T>>.mapFlattened(transform: (T) -> E): List<E> {
    val totalSize = sumOf { it.size }
    val result = ArrayList<E>(totalSize)
    fastForEach { list ->
        list.fastForEach { item ->
            result.add(transform(item))
        }
    }
    return result
}

/**
 * Returns a flattened IntList produced by applying [transform] to each element of the nested lists.
 */
 @OverloadResolutionByLambdaReturnType
fun <T> List<List<T>>.mapFlattened(transform: (T) -> Int): IntList {
    val totalSize = sumOf { it.size }
    val result = MutableIntList(totalSize)
    fastForEach { list ->
        list.fastForEach { item ->
            result.add(transform(item))
        }
    }
    return result
}

/**
 * Returns a flattened IntList containing only elements that match the given [predicate].
 */
fun List<List<Int>>.filterFlattened(predicate: (Int) -> Boolean): IntList {
    val result = MutableIntList()
    fastForEach { list ->
        list.fastForEach { item ->
            if (predicate(item)) result.add(item)
        }
    }
    return result
}

/**
 * Returns a flattened LongList produced by applying [transform] to each element of the nested lists.
 */
 @OverloadResolutionByLambdaReturnType
fun <T> List<List<T>>.mapFlattened(transform: (T) -> Long): LongList {
    val totalSize = sumOf { it.size }
    val result = MutableLongList(totalSize)
    fastForEach { list ->
        list.fastForEach { item ->
            result.add(transform(item))
        }
    }
    return result
}

/**
 * Returns a flattened LongList containing only elements that match the given [predicate].
 */
fun List<List<Long>>.filterFlattened(predicate: (Long) -> Boolean): LongList {
    val result = MutableLongList()
    fastForEach { list ->
        list.fastForEach { item ->
            if (predicate(item)) result.add(item)
        }
    }
    return result
}

/**
 * Returns a flattened FloatList produced by applying [transform] to each element of the nested lists.
 */
 @OverloadResolutionByLambdaReturnType
fun <T> List<List<T>>.mapFlattened(transform: (T) -> Float): FloatList {
    val totalSize = sumOf { it.size }
    val result = MutableFloatList(totalSize)
    fastForEach { list ->
        list.fastForEach { item ->
            result.add(transform(item))
        }
    }
    return result
}

/**
 * Returns a flattened FloatList containing only elements that match the given [predicate].
 */
fun List<List<Float>>.filterFlattened(predicate: (Float) -> Boolean): FloatList {
    val result = MutableFloatList()
    fastForEach { list ->
        list.fastForEach { item ->
            if (predicate(item)) result.add(item)
        }
    }
    return result
}

/**
 * Returns a flattened DoubleList produced by applying [transform] to each element of the nested lists.
 */
 @OverloadResolutionByLambdaReturnType
fun <T> List<List<T>>.mapFlattened(transform: (T) -> Double): DoubleList {
    val totalSize = sumOf { it.size }
    val result = MutableDoubleList(totalSize)
    fastForEach { list ->
        list.fastForEach { item ->
            result.add(transform(item))
        }
    }
    return result
}

/**
 * Returns a flattened DoubleList containing only elements that match the given [predicate].
 */
fun List<List<Double>>.filterFlattened(predicate: (Double) -> Boolean): DoubleList {
    val result = MutableDoubleList()
    fastForEach { list ->
        list.fastForEach { item ->
            if (predicate(item)) result.add(item)
        }
    }
    return result
}

/**
 * Finds the first element in the nested lists that matches [predicate], or returns null if none found (generic version).
 */
fun <T> List<List<T>>.findFlattened(predicate: (T) -> Boolean): T? {
    fastForEach { list ->
        list.fastForEach { item ->
            if (predicate(item)) return item
        }
    }
    return null
}
