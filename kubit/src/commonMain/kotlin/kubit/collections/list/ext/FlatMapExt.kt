package kubit.collections.list.ext

import kubit.collections.list.*
import kotlin.jvm.JvmName

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

/**
 * Returns a single IntList of all elements yielded from results of transform function being invoked on each element of original collection.
 *
 * @param computeSize whether to precompute the size of the resulting list
 * @param transform the function to transform an element to a list of elements
 */
@OverloadResolutionByLambdaReturnType
@JvmName("flatMapList")
inline fun <T> List<T>.flatMap(computeSize: Boolean = false, transform: (T) -> List<Int>): IntList {
    val size = if (computeSize) sumOf { transform(it).size } else 16
    val result = MutableIntList(size)
    fastForEach { element ->
        result.addAll(transform(element))
    }
    return result
}

/**
 * Returns a single IntList of all elements yielded from results of transform function being invoked on each element of original collection.
 *
 * @param computeSize whether to precompute the size of the resulting list
 * @param transform the function to transform an element to a list of elements
 */
@OverloadResolutionByLambdaReturnType
inline fun <T> List<T>.flatMap(computeSize: Boolean = false, transform: (T) -> IntList): IntList {
    val size = if (computeSize) sumOf { transform(it).size } else 16
    val result = MutableIntList(size)
    fastForEach { element ->
        result.addAll(transform(element))
    }
    return result
}

/**
 * Returns a single LongList of all elements yielded from results of transform function being invoked on each element of original collection.
 *
 * @param computeSize whether to precompute the size of the resulting list
 * @param transform the function to transform an element to a list of elements
 */
@OverloadResolutionByLambdaReturnType
@JvmName("flatMapList")
inline fun <T> List<T>.flatMap(computeSize: Boolean = false, transform: (T) -> List<Long>): LongList {
    val size = if (computeSize) sumOf { transform(it).size } else 16
    val result = MutableLongList(size)
    fastForEach { element ->
        result.addAll(transform(element))
    }
    return result
}

/**
 * Returns a single LongList of all elements yielded from results of transform function being invoked on each element of original collection.
 *
 * @param computeSize whether to precompute the size of the resulting list
 * @param transform the function to transform an element to a list of elements
 */
@OverloadResolutionByLambdaReturnType
inline fun <T> List<T>.flatMap(computeSize: Boolean = false, transform: (T) -> LongList): LongList {
    val size = if (computeSize) sumOf { transform(it).size } else 16
    val result = MutableLongList(size)
    fastForEach { element ->
        result.addAll(transform(element))
    }
    return result
}

/**
 * Returns a single FloatList of all elements yielded from results of transform function being invoked on each element of original collection.
 *
 * @param computeSize whether to precompute the size of the resulting list
 * @param transform the function to transform an element to a list of elements
 */
@OverloadResolutionByLambdaReturnType
@JvmName("flatMapList")
inline fun <T> List<T>.flatMap(computeSize: Boolean = false, transform: (T) -> List<Float>): FloatList {
    val size = if (computeSize) sumOf { transform(it).size } else 16
    val result = MutableFloatList(size)
    fastForEach { element ->
        result.addAll(transform(element))
    }
    return result
}

/**
 * Returns a single FloatList of all elements yielded from results of transform function being invoked on each element of original collection.
 *
 * @param computeSize whether to precompute the size of the resulting list
 * @param transform the function to transform an element to a list of elements
 */
@OverloadResolutionByLambdaReturnType
inline fun <T> List<T>.flatMap(computeSize: Boolean = false, transform: (T) -> FloatList): FloatList {
    val size = if (computeSize) sumOf { transform(it).size } else 16
    val result = MutableFloatList(size)
    fastForEach { element ->
        result.addAll(transform(element))
    }
    return result
}

/**
 * Returns a single DoubleList of all elements yielded from results of transform function being invoked on each element of original collection.
 *
 * @param computeSize whether to precompute the size of the resulting list
 * @param transform the function to transform an element to a list of elements
 */
@OverloadResolutionByLambdaReturnType
@JvmName("flatMapList")
inline fun <T> List<T>.flatMap(computeSize: Boolean = false, transform: (T) -> List<Double>): DoubleList {
    val size = if (computeSize) sumOf { transform(it).size } else 16
    val result = MutableDoubleList(size)
    fastForEach { element ->
        result.addAll(transform(element))
    }
    return result
}

/**
 * Returns a single DoubleList of all elements yielded from results of transform function being invoked on each element of original collection.
 *
 * @param computeSize whether to precompute the size of the resulting list
 * @param transform the function to transform an element to a list of elements
 */
@OverloadResolutionByLambdaReturnType
inline fun <T> List<T>.flatMap(computeSize: Boolean = false, transform: (T) -> DoubleList): DoubleList {
    val size = if (computeSize) sumOf { transform(it).size } else 16
    val result = MutableDoubleList(size)
    fastForEach { element ->
        result.addAll(transform(element))
    }
    return result
}
