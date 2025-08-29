package kubit.collections.array

import kubit.collections.list.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

/**
 * Returns a IntList containing the results of applying the given transform function to each element in the original array.
 */
@OverloadResolutionByLambdaReturnType
inline fun IntArray.map(transform: (Int) -> Int): IntList {
    return IntList(size){ index -> transform(this[index]) }
}

/**
 * Returns a IntArray containing the results of applying the given transform function to each element in the original array.
 */
@OverloadResolutionByLambdaReturnType
inline fun IntArray.mapArray(transform: (Int) -> Int): IntArray {
    return IntArray(size) { index -> transform(this[index]) }
}

/**
 * Returns a LongList containing the results of applying the given transform function to each element in the original array.
 */
@OverloadResolutionByLambdaReturnType
inline fun LongArray.map(transform: (Long) -> Long): LongList {
    return LongList(size){ index -> transform(this[index]) }
}

/**
 * Returns a LongArray containing the results of applying the given transform function to each element in the original array.
 */
@OverloadResolutionByLambdaReturnType
inline fun LongArray.mapArray(transform: (Long) -> Long): LongArray {
    return LongArray(size) { index -> transform(this[index]) }
}

/**
 * Returns a FloatList containing the results of applying the given transform function to each element in the original array.
 */
@OverloadResolutionByLambdaReturnType
inline fun FloatArray.map(transform: (Float) -> Float): FloatList {
    return FloatList(size){ index -> transform(this[index]) }
}

/**
 * Returns a FloatArray containing the results of applying the given transform function to each element in the original array.
 */
@OverloadResolutionByLambdaReturnType
inline fun FloatArray.mapArray(transform: (Float) -> Float): FloatArray {
    return FloatArray(size) { index -> transform(this[index]) }
}

/**
 * Returns a DoubleList containing the results of applying the given transform function to each element in the original array.
 */
@OverloadResolutionByLambdaReturnType
inline fun DoubleArray.map(transform: (Double) -> Double): DoubleList {
    return DoubleList(size){ index -> transform(this[index]) }
}

/**
 * Returns a DoubleArray containing the results of applying the given transform function to each element in the original array.
 */
@OverloadResolutionByLambdaReturnType
inline fun DoubleArray.mapArray(transform: (Double) -> Double): DoubleArray {
    return DoubleArray(size) { index -> transform(this[index]) }
}

/**
 * Returns a LongList containing the results of applying the given transform function to each element in the original IntArray.
 */
@OverloadResolutionByLambdaReturnType
inline fun IntArray.map(transform: (Int) -> Long): LongList {
    return LongList(size){ index -> transform(this[index]) }
}

/**
 * Returns a LongArray containing the results of applying the given transform function to each element in the original IntArray.
 */
@OverloadResolutionByLambdaReturnType
inline fun IntArray.mapArray(transform: (Int) -> Long): LongArray {
    return LongArray(size) { index -> transform(this[index]) }
}

/**
 * Returns a FloatList containing the results of applying the given transform function to each element in the original IntArray.
 */
@OverloadResolutionByLambdaReturnType
inline fun IntArray.map(transform: (Int) -> Float): FloatList {
    return FloatList(size){ index -> transform(this[index]) }
}

/**
 * Returns a FloatArray containing the results of applying the given transform function to each element in the original IntArray.
 */
@OverloadResolutionByLambdaReturnType
inline fun IntArray.mapArray(transform: (Int) -> Float): FloatArray {
    return FloatArray(size) { index -> transform(this[index]) }
}

/**
 * Returns a DoubleList containing the results of applying the given transform function to each element in the original IntArray.
 */
@OverloadResolutionByLambdaReturnType
inline fun IntArray.map(transform: (Int) -> Double): DoubleList {
    return DoubleList(size){ index -> transform(this[index]) }
}

/**
 * Returns a DoubleArray containing the results of applying the given transform function to each element in the original IntArray.
 */
@OverloadResolutionByLambdaReturnType
inline fun IntArray.mapArray(transform: (Int) -> Double): DoubleArray {
    return DoubleArray(size) { index -> transform(this[index]) }
}

/**
 * Returns a IntList containing the results of applying the given transform function to each element in the original LongArray.
 */
@OverloadResolutionByLambdaReturnType
inline fun LongArray.map(transform: (Long) -> Int): IntList {
    return IntList(size){ index -> transform(this[index]) }
}

/**
 * Returns a IntArray containing the results of applying the given transform function to each element in the original LongArray.
 */
@OverloadResolutionByLambdaReturnType
inline fun LongArray.mapArray(transform: (Long) -> Int): IntArray {
    return IntArray(size) { index -> transform(this[index]) }
}

/**
 * Returns a FloatList containing the results of applying the given transform function to each element in the original LongArray.
 */
@OverloadResolutionByLambdaReturnType
inline fun LongArray.map(transform: (Long) -> Float): FloatList {
    return FloatList(size){ index -> transform(this[index]) }
}

/**
 * Returns a FloatArray containing the results of applying the given transform function to each element in the original LongArray.
 */
@OverloadResolutionByLambdaReturnType
inline fun LongArray.mapArray(transform: (Long) -> Float): FloatArray {
    return FloatArray(size) { index -> transform(this[index]) }
}

/**
 * Returns a DoubleList containing the results of applying the given transform function to each element in the original LongArray.
 */
@OverloadResolutionByLambdaReturnType
inline fun LongArray.map(transform: (Long) -> Double): DoubleList {
    return DoubleList(size){ index -> transform(this[index]) }
}

/**
 * Returns a DoubleArray containing the results of applying the given transform function to each element in the original LongArray.
 */
@OverloadResolutionByLambdaReturnType
inline fun LongArray.mapArray(transform: (Long) -> Double): DoubleArray {
    return DoubleArray(size) { index -> transform(this[index]) }
}

/**
 * Returns a IntList containing the results of applying the given transform function to each element in the original FloatArray.
 */
@OverloadResolutionByLambdaReturnType
inline fun FloatArray.map(transform: (Float) -> Int): IntList {
    return IntList(size){ index -> transform(this[index]) }
}

/**
 * Returns a IntArray containing the results of applying the given transform function to each element in the original FloatArray.
 */
@OverloadResolutionByLambdaReturnType
inline fun FloatArray.mapArray(transform: (Float) -> Int): IntArray {
    return IntArray(size) { index -> transform(this[index]) }
}

/**
 * Returns a LongList containing the results of applying the given transform function to each element in the original FloatArray.
 */
@OverloadResolutionByLambdaReturnType
inline fun FloatArray.map(transform: (Float) -> Long): LongList {
    return LongList(size){ index -> transform(this[index]) }
}

/**
 * Returns a LongArray containing the results of applying the given transform function to each element in the original FloatArray.
 */
@OverloadResolutionByLambdaReturnType
inline fun FloatArray.mapArray(transform: (Float) -> Long): LongArray {
    return LongArray(size) { index -> transform(this[index]) }
}

/**
 * Returns a DoubleList containing the results of applying the given transform function to each element in the original FloatArray.
 */
@OverloadResolutionByLambdaReturnType
inline fun FloatArray.map(transform: (Float) -> Double): DoubleList {
    return DoubleList(size){ index -> transform(this[index]) }
}

/**
 * Returns a DoubleArray containing the results of applying the given transform function to each element in the original FloatArray.
 */
@OverloadResolutionByLambdaReturnType
inline fun FloatArray.mapArray(transform: (Float) -> Double): DoubleArray {
    return DoubleArray(size) { index -> transform(this[index]) }
}

/**
 * Returns a IntList containing the results of applying the given transform function to each element in the original DoubleArray.
 */
@OverloadResolutionByLambdaReturnType
inline fun DoubleArray.map(transform: (Double) -> Int): IntList {
    return IntList(size){ index -> transform(this[index]) }
}

/**
 * Returns a IntArray containing the results of applying the given transform function to each element in the original DoubleArray.
 */
@OverloadResolutionByLambdaReturnType
inline fun DoubleArray.mapArray(transform: (Double) -> Int): IntArray {
    return IntArray(size) { index -> transform(this[index]) }
}

/**
 * Returns a LongList containing the results of applying the given transform function to each element in the original DoubleArray.
 */
@OverloadResolutionByLambdaReturnType
inline fun DoubleArray.map(transform: (Double) -> Long): LongList {
    return LongList(size){ index -> transform(this[index]) }
}

/**
 * Returns a LongArray containing the results of applying the given transform function to each element in the original DoubleArray.
 */
@OverloadResolutionByLambdaReturnType
inline fun DoubleArray.mapArray(transform: (Double) -> Long): LongArray {
    return LongArray(size) { index -> transform(this[index]) }
}

/**
 * Returns a FloatList containing the results of applying the given transform function to each element in the original DoubleArray.
 */
@OverloadResolutionByLambdaReturnType
inline fun DoubleArray.map(transform: (Double) -> Float): FloatList {
    return FloatList(size){ index -> transform(this[index]) }
}

/**
 * Returns a FloatArray containing the results of applying the given transform function to each element in the original DoubleArray.
 */
@OverloadResolutionByLambdaReturnType
inline fun DoubleArray.mapArray(transform: (Double) -> Float): FloatArray {
    return FloatArray(size) { index -> transform(this[index]) }
}

/**
 * Returns a IntList containing the results of applying the given transform function to each element in the original Array<T>.
 */
@OverloadResolutionByLambdaReturnType
inline fun <T> Array<T>.map(transform: (T) -> Int): IntList {
    return MutableIntList(size){ index -> transform(this[index]) }
}

/**
 * Returns a IntArray containing the results of applying the given transform function to each element in the original Array<T>.
 */
@OverloadResolutionByLambdaReturnType
inline fun <T> Array<T>.mapArray(transform: (T) -> Int): IntArray {
    return IntArray(size) { index -> transform(this[index]) }
}

/**
 * Returns a LongList containing the results of applying the given transform function to each element in the original Array<T>.
 */
@OverloadResolutionByLambdaReturnType
inline fun <T> Array<T>.map(transform: (T) -> Long): LongList {
    return MutableLongList(size){ index -> transform(this[index]) }
}

/**
 * Returns a LongArray containing the results of applying the given transform function to each element in the original Array<T>.
 */
@OverloadResolutionByLambdaReturnType
inline fun <T> Array<T>.mapArray(transform: (T) -> Long): LongArray {
    return LongArray(size) { index -> transform(this[index]) }
}

/**
 * Returns a FloatList containing the results of applying the given transform function to each element in the original Array<T>.
 */
@OverloadResolutionByLambdaReturnType
inline fun <T> Array<T>.map(transform: (T) -> Float): FloatList {
    return MutableFloatList(size){ index -> transform(this[index]) }
}

/**
 * Returns a FloatArray containing the results of applying the given transform function to each element in the original Array<T>.
 */
@OverloadResolutionByLambdaReturnType
inline fun <T> Array<T>.mapArray(transform: (T) -> Float): FloatArray {
    return FloatArray(size) { index -> transform(this[index]) }
}

/**
 * Returns a DoubleList containing the results of applying the given transform function to each element in the original Array<T>.
 */
@OverloadResolutionByLambdaReturnType
inline fun <T> Array<T>.map(transform: (T) -> Double): DoubleList {
    return MutableDoubleList(size){ index -> transform(this[index]) }
}

/**
 * Returns a DoubleArray containing the results of applying the given transform function to each element in the original Array<T>.
 */
@OverloadResolutionByLambdaReturnType
inline fun <T> Array<T>.mapArray(transform: (T) -> Double): DoubleArray {
    return DoubleArray(size) { index -> transform(this[index]) }
}
