package kubit.collections.list.ext

import kubit.collections.list.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

/**
 * Returns a IntList containing the results of applying the given transform function to each element and its index in the original list.
 */
@OverloadResolutionByLambdaReturnType
inline fun <T> List<T>.mapIndexed(transform: (Int, T) -> Int): IntList {
    return MutableIntList(size).also { result ->
        forEachIndexed { index, element ->
            result.content[result._size++] = transform(index, element)
        }
    }
}

/**
 * Returns a LongList containing the results of applying the given transform function to each element and its index in the original list.
 */
@OverloadResolutionByLambdaReturnType
inline fun <T> List<T>.mapIndexed(transform: (Int, T) -> Long): LongList {
    return MutableLongList(size).also { result ->
        forEachIndexed { index, element ->
            result.content[result._size++] = transform(index, element)
        }
    }
}

/**
 * Returns a FloatList containing the results of applying the given transform function to each element and its index in the original list.
 */
@OverloadResolutionByLambdaReturnType
inline fun <T> List<T>.mapIndexed(transform: (Int, T) -> Float): FloatList {
    return MutableFloatList(size).also { result ->
        forEachIndexed { index, element ->
            result.content[result._size++] = transform(index, element)
        }
    }
}

/**
 * Returns a DoubleList containing the results of applying the given transform function to each element and its index in the original list.
 */
@OverloadResolutionByLambdaReturnType
inline fun <T> List<T>.mapIndexed(transform: (Int, T) -> Double): DoubleList {
    return MutableDoubleList(size).also { result ->
        forEachIndexed { index, element ->
            result.content[result._size++] = transform(index, element)
        }
    }
}

/**
 * Returns a IntList containing the results of applying the given transform function to each element and its index in the original IntList.
 */
@OverloadResolutionByLambdaReturnType
inline fun IntList.mapIndexed(transform: (Int, Int) -> Int): IntList {
    return MutableIntList(size) { i -> transform(i, content[i]) }
}

/**
 * Returns a LongList containing the results of applying the given transform function to each element and its index in the original IntList.
 */
@OverloadResolutionByLambdaReturnType
inline fun IntList.mapIndexed(transform: (Int, Int) -> Long): LongList {
    return MutableLongList(size) { i -> transform(i, content[i]) }
}

/**
 * Returns a FloatList containing the results of applying the given transform function to each element and its index in the original IntList.
 */
@OverloadResolutionByLambdaReturnType
inline fun IntList.mapIndexed(transform: (Int, Int) -> Float): FloatList {
    return MutableFloatList(size) { i -> transform(i, content[i]) }
}

/**
 * Returns a DoubleList containing the results of applying the given transform function to each element and its index in the original IntList.
 */
@OverloadResolutionByLambdaReturnType
inline fun IntList.mapIndexed(transform: (Int, Int) -> Double): DoubleList {
    return MutableDoubleList(size) { i -> transform(i, content[i]) }
}

/**
 * Returns a IntList containing the results of applying the given transform function to each element and its index in the original LongList.
 */
@OverloadResolutionByLambdaReturnType
inline fun LongList.mapIndexed(transform: (Int, Long) -> Int): IntList {
    return MutableIntList(size) { i -> transform(i, content[i]) }
}

/**
 * Returns a LongList containing the results of applying the given transform function to each element and its index in the original LongList.
 */
@OverloadResolutionByLambdaReturnType
inline fun LongList.mapIndexed(transform: (Int, Long) -> Long): LongList {
    return MutableLongList(size) { i -> transform(i, content[i]) }
}

/**
 * Returns a FloatList containing the results of applying the given transform function to each element and its index in the original LongList.
 */
@OverloadResolutionByLambdaReturnType
inline fun LongList.mapIndexed(transform: (Int, Long) -> Float): FloatList {
    return MutableFloatList(size) { i -> transform(i, content[i]) }
}

/**
 * Returns a DoubleList containing the results of applying the given transform function to each element and its index in the original LongList.
 */
@OverloadResolutionByLambdaReturnType
inline fun LongList.mapIndexed(transform: (Int, Long) -> Double): DoubleList {
    return MutableDoubleList(size) { i -> transform(i, content[i]) }
}

/**
 * Returns a IntList containing the results of applying the given transform function to each element and its index in the original FloatList.
 */
@OverloadResolutionByLambdaReturnType
inline fun FloatList.mapIndexed(transform: (Int, Float) -> Int): IntList {
    return MutableIntList(size) { i -> transform(i, content[i]) }
}

/**
 * Returns a LongList containing the results of applying the given transform function to each element and its index in the original FloatList.
 */
@OverloadResolutionByLambdaReturnType
inline fun FloatList.mapIndexed(transform: (Int, Float) -> Long): LongList {
    return MutableLongList(size) { i -> transform(i, content[i]) }
}

/**
 * Returns a FloatList containing the results of applying the given transform function to each element and its index in the original FloatList.
 */
@OverloadResolutionByLambdaReturnType
inline fun FloatList.mapIndexed(transform: (Int, Float) -> Float): FloatList {
    return MutableFloatList(size) { i -> transform(i, content[i]) }
}

/**
 * Returns a DoubleList containing the results of applying the given transform function to each element and its index in the original FloatList.
 */
@OverloadResolutionByLambdaReturnType
inline fun FloatList.mapIndexed(transform: (Int, Float) -> Double): DoubleList {
    return MutableDoubleList(size) { i -> transform(i, content[i]) }
}

/**
 * Returns a IntList containing the results of applying the given transform function to each element and its index in the original DoubleList.
 */
@OverloadResolutionByLambdaReturnType
inline fun DoubleList.mapIndexed(transform: (Int, Double) -> Int): IntList {
    return MutableIntList(size) { i -> transform(i, content[i]) }
}

/**
 * Returns a LongList containing the results of applying the given transform function to each element and its index in the original DoubleList.
 */
@OverloadResolutionByLambdaReturnType
inline fun DoubleList.mapIndexed(transform: (Int, Double) -> Long): LongList {
    return MutableLongList(size) { i -> transform(i, content[i]) }
}

/**
 * Returns a FloatList containing the results of applying the given transform function to each element and its index in the original DoubleList.
 */
@OverloadResolutionByLambdaReturnType
inline fun DoubleList.mapIndexed(transform: (Int, Double) -> Float): FloatList {
    return MutableFloatList(size) { i -> transform(i, content[i]) }
}

/**
 * Returns a DoubleList containing the results of applying the given transform function to each element and its index in the original DoubleList.
 */
@OverloadResolutionByLambdaReturnType
inline fun DoubleList.mapIndexed(transform: (Int, Double) -> Double): DoubleList {
    return MutableDoubleList(size) { i -> transform(i, content[i]) }
}

/**
 * Returns a List containing the results of applying the given transform function to each element and its index in the original IntList.
 */
@OverloadResolutionByLambdaReturnType
inline fun <R> IntList.mapIndexed(transform: (Int, Int) -> R): List<R> {
    return MutableList(size) { i -> transform(i, content[i]) }
}

/**
 * Returns a List containing the results of applying the given transform function to each element and its index in the original LongList.
 */
@OverloadResolutionByLambdaReturnType
inline fun <R> LongList.mapIndexed(transform: (Int, Long) -> R): List<R> {
    return MutableList(size) { i -> transform(i, content[i]) }
}

/**
 * Returns a List containing the results of applying the given transform function to each element and its index in the original FloatList.
 */
@OverloadResolutionByLambdaReturnType
inline fun <R> FloatList.mapIndexed(transform: (Int, Float) -> R): List<R> {
    return MutableList(size) { i -> transform(i, content[i]) }
}

/**
 * Returns a List containing the results of applying the given transform function to each element and its index in the original DoubleList.
 */
@OverloadResolutionByLambdaReturnType
inline fun <R> DoubleList.mapIndexed(transform: (Int, Double) -> R): List<R> {
    return MutableList(size) { i -> transform(i, content[i]) }
}
