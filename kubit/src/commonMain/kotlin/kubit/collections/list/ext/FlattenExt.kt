package kubit.collections.list.ext

import kubit.collections.list.*
import kotlin.jvm.JvmName

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

/**
 * Flattens a List of List<Int> into a single IntList.
 */
@OverloadResolutionByLambdaReturnType
@JvmName("flattenListInt")
inline fun List<List<Int>>.flatten(): IntList {
    val size = sumOf { it.size }
    val result = MutableIntList(size)
    forEach { list ->
        result.addAll(list)
    }
    return result
}

/**
 * Flattens a List of IntList into a single IntList.
 */
@OverloadResolutionByLambdaReturnType
inline fun List<IntList>.flatten(): IntList {
    val size = sumOf { it.size }
    val result = MutableIntList(size)
    forEach { list ->
        result.addAll(list)
    }
    return result
}

/**
 * Flattens a List of List<Long> into a single LongList.
 */
@OverloadResolutionByLambdaReturnType
@JvmName("flattenListLong")
inline fun List<List<Long>>.flatten(): LongList {
    val size = sumOf { it.size }
    val result = MutableLongList(size)
    forEach { list ->
        result.addAll(list)
    }
    return result
}

/**
 * Flattens a List of LongList into a single LongList.
 */
@OverloadResolutionByLambdaReturnType
inline fun List<LongList>.flatten(): LongList {
    val size = sumOf { it.size }
    val result = MutableLongList(size)
    forEach { list ->
        result.addAll(list)
    }
    return result
}

/**
 * Flattens a List of List<Float> into a single FloatList.
 */
@OverloadResolutionByLambdaReturnType
@JvmName("flattenListFloat")
inline fun List<List<Float>>.flatten(): FloatList {
    val size = sumOf { it.size }
    val result = MutableFloatList(size)
    forEach { list ->
        result.addAll(list)
    }
    return result
}

/**
 * Flattens a List of FloatList into a single FloatList.
 */
@OverloadResolutionByLambdaReturnType
inline fun List<FloatList>.flatten(): FloatList {
    val size = sumOf { it.size }
    val result = MutableFloatList(size)
    forEach { list ->
        result.addAll(list)
    }
    return result
}

/**
 * Flattens a List of List<Double> into a single DoubleList.
 */
@OverloadResolutionByLambdaReturnType
@JvmName("flattenListDouble")
inline fun List<List<Double>>.flatten(): DoubleList {
    val size = sumOf { it.size }
    val result = MutableDoubleList(size)
    forEach { list ->
        result.addAll(list)
    }
    return result
}

/**
 * Flattens a List of DoubleList into a single DoubleList.
 */
@OverloadResolutionByLambdaReturnType
inline fun List<DoubleList>.flatten(): DoubleList {
    val size = sumOf { it.size }
    val result = MutableDoubleList(size)
    forEach { list ->
        result.addAll(list)
    }
    return result
}
