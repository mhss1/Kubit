package kubit.collections.list.ext

import kubit.collections.list.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

/**
 * Returns a IntList containing the results of applying the given transform function to each element in the original list.
 */
@OverloadResolutionByLambdaReturnType
inline fun <T> List<T>.map(transform: (T) -> Int): IntList {
    return MutableIntList(size).also { result ->
        forEach { element ->
            result.content[result._size++] = transform(element)
        }
    }
}

/**
 * Returns a list containing only the non-null results of applying the given transform function to each element in the original list.
 */
@OverloadResolutionByLambdaReturnType
inline fun <T> List<T>.mapNotNull(transform: (T) -> Int?): IntList {
    val result = MutableIntList()
    forEach { element ->
        transform(element)?.let { result.add(it) }
    }
    return result
}

/**
 * Returns a LongList containing the results of applying the given transform function to each element in the original list.
 */
@OverloadResolutionByLambdaReturnType
inline fun <T> List<T>.map(transform: (T) -> Long): LongList {
    return MutableLongList(size).also { result ->
        forEach { element ->
            result.content[result._size++] = transform(element)
        }
    }
}

/**
 * Returns a list containing only the non-null results of applying the given transform function to each element in the original list.
 */
@OverloadResolutionByLambdaReturnType
inline fun <T> List<T>.mapNotNull(transform: (T) -> Long?): LongList {
    val result = MutableLongList()
    forEach { element ->
        transform(element)?.let { result.add(it) }
    }
    return result
}

/**
 * Returns a FloatList containing the results of applying the given transform function to each element in the original list.
 */
@OverloadResolutionByLambdaReturnType
inline fun <T> List<T>.map(transform: (T) -> Float): FloatList {
    return MutableFloatList(size).also { result ->
        forEach { element ->
            result.content[result._size++] = transform(element)
        }
    }
}

/**
 * Returns a list containing only the non-null results of applying the given transform function to each element in the original list.
 */
@OverloadResolutionByLambdaReturnType
inline fun <T> List<T>.mapNotNull(transform: (T) -> Float?): FloatList {
    val result = MutableFloatList()
    forEach { element ->
        transform(element)?.let { result.add(it) }
    }
    return result
}

/**
 * Returns a DoubleList containing the results of applying the given transform function to each element in the original list.
 */
@OverloadResolutionByLambdaReturnType
inline fun <T> List<T>.map(transform: (T) -> Double): DoubleList {
    return MutableDoubleList(size).also { result ->
        forEach { element ->
            result.content[result._size++] = transform(element)
        }
    }
}

/**
 * Returns a list containing only the non-null results of applying the given transform function to each element in the original list.
 */
@OverloadResolutionByLambdaReturnType
inline fun <T> List<T>.mapNotNull(transform: (T) -> Double?): DoubleList {
    val result = MutableDoubleList()
    forEach { element ->
        transform(element)?.let { result.add(it) }
    }
    return result
}

/**
 * Returns a IntList containing the results of applying the given transform function to each element in the original IntList.
 */
@OverloadResolutionByLambdaReturnType
inline fun IntList.map(transform: (Int) -> Int): IntList {
    return MutableIntList(size) { i -> transform(content[i]) }
}

/**
 * Returns a LongList containing the results of applying the given transform function to each element in the original IntList.
 */
@OverloadResolutionByLambdaReturnType
inline fun IntList.map(transform: (Int) -> Long): LongList {
    return MutableLongList(size) { i -> transform(content[i]) }
}

/**
 * Returns a FloatList containing the results of applying the given transform function to each element in the original IntList.
 */
@OverloadResolutionByLambdaReturnType
inline fun IntList.map(transform: (Int) -> Float): FloatList {
    return MutableFloatList(size) { i -> transform(content[i]) }
}

/**
 * Returns a DoubleList containing the results of applying the given transform function to each element in the original IntList.
 */
@OverloadResolutionByLambdaReturnType
inline fun IntList.map(transform: (Int) -> Double): DoubleList {
    return MutableDoubleList(size) { i -> transform(content[i]) }
}

/**
 * Returns a IntList containing the results of applying the given transform function to each element in the original LongList.
 */
@OverloadResolutionByLambdaReturnType
inline fun LongList.map(transform: (Long) -> Int): IntList {
    return MutableIntList(size) { i -> transform(content[i]) }
}

/**
 * Returns a LongList containing the results of applying the given transform function to each element in the original LongList.
 */
@OverloadResolutionByLambdaReturnType
inline fun LongList.map(transform: (Long) -> Long): LongList {
    return MutableLongList(size) { i -> transform(content[i]) }
}

/**
 * Returns a FloatList containing the results of applying the given transform function to each element in the original LongList.
 */
@OverloadResolutionByLambdaReturnType
inline fun LongList.map(transform: (Long) -> Float): FloatList {
    return MutableFloatList(size) { i -> transform(content[i]) }
}

/**
 * Returns a DoubleList containing the results of applying the given transform function to each element in the original LongList.
 */
@OverloadResolutionByLambdaReturnType
inline fun LongList.map(transform: (Long) -> Double): DoubleList {
    return MutableDoubleList(size) { i -> transform(content[i]) }
}

/**
 * Returns a IntList containing the results of applying the given transform function to each element in the original FloatList.
 */
@OverloadResolutionByLambdaReturnType
inline fun FloatList.map(transform: (Float) -> Int): IntList {
    return MutableIntList(size) { i -> transform(content[i]) }
}

/**
 * Returns a LongList containing the results of applying the given transform function to each element in the original FloatList.
 */
@OverloadResolutionByLambdaReturnType
inline fun FloatList.map(transform: (Float) -> Long): LongList {
    return MutableLongList(size) { i -> transform(content[i]) }
}

/**
 * Returns a FloatList containing the results of applying the given transform function to each element in the original FloatList.
 */
@OverloadResolutionByLambdaReturnType
inline fun FloatList.map(transform: (Float) -> Float): FloatList {
    return MutableFloatList(size) { i -> transform(content[i]) }
}

/**
 * Returns a DoubleList containing the results of applying the given transform function to each element in the original FloatList.
 */
@OverloadResolutionByLambdaReturnType
inline fun FloatList.map(transform: (Float) -> Double): DoubleList {
    return MutableDoubleList(size) { i -> transform(content[i]) }
}

/**
 * Returns a IntList containing the results of applying the given transform function to each element in the original DoubleList.
 */
@OverloadResolutionByLambdaReturnType
inline fun DoubleList.map(transform: (Double) -> Int): IntList {
    return MutableIntList(size) { i -> transform(content[i]) }
}

/**
 * Returns a LongList containing the results of applying the given transform function to each element in the original DoubleList.
 */
@OverloadResolutionByLambdaReturnType
inline fun DoubleList.map(transform: (Double) -> Long): LongList {
    return MutableLongList(size) { i -> transform(content[i]) }
}

/**
 * Returns a FloatList containing the results of applying the given transform function to each element in the original DoubleList.
 */
@OverloadResolutionByLambdaReturnType
inline fun DoubleList.map(transform: (Double) -> Float): FloatList {
    return MutableFloatList(size) { i -> transform(content[i]) }
}

/**
 * Returns a DoubleList containing the results of applying the given transform function to each element in the original DoubleList.
 */
@OverloadResolutionByLambdaReturnType
inline fun DoubleList.map(transform: (Double) -> Double): DoubleList {
    return MutableDoubleList(size) { i -> transform(content[i]) }
}

/**
 * Returns an List<R> containing the results of applying the given transform function to each element in the original IntList.
 */
@OverloadResolutionByLambdaReturnType
inline fun <R> IntList.map(transform: (Int) -> R): List<R> {
    return MutableList(size) { i -> transform(content[i]) }
}

/**
 * Returns an List<R> containing the results of applying the given transform function to each element in the original LongList.
 */
@OverloadResolutionByLambdaReturnType
inline fun <R> LongList.map(transform: (Long) -> R): List<R> {
    return MutableList(size) { i -> transform(content[i]) }
}

/**
 * Returns an List<R> containing the results of applying the given transform function to each element in the original FloatList.
 */
@OverloadResolutionByLambdaReturnType
inline fun <R> FloatList.map(transform: (Float) -> R): List<R> {
    return MutableList(size) { i -> transform(content[i]) }
}

/**
 * Returns an List<R> containing the results of applying the given transform function to each element in the original DoubleList.
 */
@OverloadResolutionByLambdaReturnType
inline fun <R> DoubleList.map(transform: (Double) -> R): List<R> {
    return MutableList(size) { i -> transform(content[i]) }
}
