package kubit.collections.inplace.primitives

import kubit.collections.list.*
import kotlin.math.min

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

/**
 * Returns a WindowedIntList with the given windowSize sliding by step.
 * Each window is a view into the original list.
 *
 * @param windowSize The desired size of each window. Must be greater than 0.
 * @param step The step size between consecutive windows. Defaults to 1.
 * @param partialWindows Whether to include partial windows at the end. Defaults to false.
 * @throws IllegalArgumentException if windowSize or step is less than or equal to 0.
 * @return A [WindowedIntList] containing the windows.
 */
fun IntList.windowedInPlace(windowSize: Int, step: Int = 1, partialWindows: Boolean = false): WindowedIntList {
    return WindowedIntList(this, windowSize, step, partialWindows)
}

/**
 * Iterates through the windows of the list and performs the given action on each window.
 *
 * @param windowSize The desired size of each window. Must be greater than 0.
 * @param step The step size between consecutive windows. Must be greater than 0. Defaults to 1.
 * @param partialWindows Whether to include partial windows at the end. Defaults to false.
 * @param action The function to be executed for each window.
 * @throws IllegalArgumentException if windowSize or step is less than or equal to 0.
 */
inline fun IntList.forEachWindow(
    windowSize: Int,
    step: Int = 1,
    partialWindows: Boolean = false,
    action: (InPlaceIntSubList) -> Unit,
) {
    if (isEmpty()) return
    require(windowSize > 0) { "Window size must be greater than 0" }
    require(step > 0) { "Step must be greater than 0" }
    if (size < windowSize && !partialWindows) return

    val current = InPlaceIntSubList(this, 0, min(windowSize, size))

    while (current.start < size) {
        action(current)
        current.start += step
        val newEnd = current.start + windowSize
        if (newEnd > size && !partialWindows) break
        current.end = newEnd.coerceAtMost(size)
    }
}

/**
 * Iterates through the windows of the list with their indices and performs the given action on each window.
 *
 * @param windowSize The desired size of each window. Must be greater than 0.
 * @param step The step size between consecutive windows. Must be greater than 0, Defaults to 1.
 * @param partialWindows Whether to include partial windows at the end. Defaults to false.
 * @param action The function to be executed for each window and its index.
 * @throws IllegalArgumentException if windowSize or step is less than or equal to 0.
 */
inline fun IntList.forEachWindowIndexed(
    windowSize: Int,
    step: Int = 1,
    partialWindows: Boolean = false,
    action: (index: Int, InPlaceIntSubList) -> Unit,
) {
    var index = 0
    forEachWindow(windowSize, step, partialWindows) {
        action(index++, it)
    }
}

/**
 * Returns a WindowedLongList with the given windowSize sliding by step.
 * Each window is a view into the original list.
 *
 * @param windowSize The desired size of each window. Must be greater than 0.
 * @param step The step size between consecutive windows. Defaults to 1.
 * @param partialWindows Whether to include partial windows at the end. Defaults to false.
 * @throws IllegalArgumentException if windowSize or step is less than or equal to 0.
 * @return A [WindowedLongList] containing the windows.
 */
fun LongList.windowedInPlace(windowSize: Int, step: Int = 1, partialWindows: Boolean = false): WindowedLongList {
    return WindowedLongList(this, windowSize, step, partialWindows)
}

/**
 * Iterates through the windows of the list and performs the given action on each window.
 *
 * @param windowSize The desired size of each window. Must be greater than 0.
 * @param step The step size between consecutive windows. Must be greater than 0. Defaults to 1.
 * @param partialWindows Whether to include partial windows at the end. Defaults to false.
 * @param action The function to be executed for each window.
 * @throws IllegalArgumentException if windowSize or step is less than or equal to 0.
 */
inline fun LongList.forEachWindow(
    windowSize: Int,
    step: Int = 1,
    partialWindows: Boolean = false,
    action: (InPlaceLongSubList) -> Unit,
) {
    if (isEmpty()) return
    require(windowSize > 0) { "Window size must be greater than 0" }
    require(step > 0) { "Step must be greater than 0" }
    if (size < windowSize && !partialWindows) return

    val current = InPlaceLongSubList(this, 0, min(windowSize, size))

    while (current.start < size) {
        action(current)
        current.start += step
        val newEnd = current.start + windowSize
        if (newEnd > size && !partialWindows) break
        current.end = newEnd.coerceAtMost(size)
    }
}

/**
 * Iterates through the windows of the list with their indices and performs the given action on each window.
 *
 * @param windowSize The desired size of each window. Must be greater than 0.
 * @param step The step size between consecutive windows. Must be greater than 0, Defaults to 1.
 * @param partialWindows Whether to include partial windows at the end. Defaults to false.
 * @param action The function to be executed for each window and its index.
 * @throws IllegalArgumentException if windowSize or step is less than or equal to 0.
 */
inline fun LongList.forEachWindowIndexed(
    windowSize: Int,
    step: Int = 1,
    partialWindows: Boolean = false,
    action: (index: Int, InPlaceLongSubList) -> Unit,
) {
    var index = 0
    forEachWindow(windowSize, step, partialWindows) {
        action(index++, it)
    }
}

/**
 * Returns a WindowedFloatList with the given windowSize sliding by step.
 * Each window is a view into the original list.
 *
 * @param windowSize The desired size of each window. Must be greater than 0.
 * @param step The step size between consecutive windows. Defaults to 1.
 * @param partialWindows Whether to include partial windows at the end. Defaults to false.
 * @throws IllegalArgumentException if windowSize or step is less than or equal to 0.
 * @return A [WindowedFloatList] containing the windows.
 */
fun FloatList.windowedInPlace(windowSize: Int, step: Int = 1, partialWindows: Boolean = false): WindowedFloatList {
    return WindowedFloatList(this, windowSize, step, partialWindows)
}

/**
 * Iterates through the windows of the list and performs the given action on each window.
 *
 * @param windowSize The desired size of each window. Must be greater than 0.
 * @param step The step size between consecutive windows. Must be greater than 0. Defaults to 1.
 * @param partialWindows Whether to include partial windows at the end. Defaults to false.
 * @param action The function to be executed for each window.
 * @throws IllegalArgumentException if windowSize or step is less than or equal to 0.
 */
inline fun FloatList.forEachWindow(
    windowSize: Int,
    step: Int = 1,
    partialWindows: Boolean = false,
    action: (InPlaceFloatSubList) -> Unit,
) {
    if (isEmpty()) return
    require(windowSize > 0) { "Window size must be greater than 0" }
    require(step > 0) { "Step must be greater than 0" }
    if (size < windowSize && !partialWindows) return

    val current = InPlaceFloatSubList(this, 0, min(windowSize, size))

    while (current.start < size) {
        action(current)
        current.start += step
        val newEnd = current.start + windowSize
        if (newEnd > size && !partialWindows) break
        current.end = newEnd.coerceAtMost(size)
    }
}

/**
 * Iterates through the windows of the list with their indices and performs the given action on each window.
 *
 * @param windowSize The desired size of each window. Must be greater than 0.
 * @param step The step size between consecutive windows. Must be greater than 0, Defaults to 1.
 * @param partialWindows Whether to include partial windows at the end. Defaults to false.
 * @param action The function to be executed for each window and its index.
 * @throws IllegalArgumentException if windowSize or step is less than or equal to 0.
 */
inline fun FloatList.forEachWindowIndexed(
    windowSize: Int,
    step: Int = 1,
    partialWindows: Boolean = false,
    action: (index: Int, InPlaceFloatSubList) -> Unit,
) {
    var index = 0
    forEachWindow(windowSize, step, partialWindows) {
        action(index++, it)
    }
}

/**
 * Returns a WindowedDoubleList with the given windowSize sliding by step.
 * Each window is a view into the original list.
 *
 * @param windowSize The desired size of each window. Must be greater than 0.
 * @param step The step size between consecutive windows. Defaults to 1.
 * @param partialWindows Whether to include partial windows at the end. Defaults to false.
 * @throws IllegalArgumentException if windowSize or step is less than or equal to 0.
 * @return A [WindowedDoubleList] containing the windows.
 */
fun DoubleList.windowedInPlace(windowSize: Int, step: Int = 1, partialWindows: Boolean = false): WindowedDoubleList {
    return WindowedDoubleList(this, windowSize, step, partialWindows)
}

/**
 * Iterates through the windows of the list and performs the given action on each window.
 *
 * @param windowSize The desired size of each window. Must be greater than 0.
 * @param step The step size between consecutive windows. Must be greater than 0. Defaults to 1.
 * @param partialWindows Whether to include partial windows at the end. Defaults to false.
 * @param action The function to be executed for each window.
 * @throws IllegalArgumentException if windowSize or step is less than or equal to 0.
 */
inline fun DoubleList.forEachWindow(
    windowSize: Int,
    step: Int = 1,
    partialWindows: Boolean = false,
    action: (InPlaceDoubleSubList) -> Unit,
) {
    if (isEmpty()) return
    require(windowSize > 0) { "Window size must be greater than 0" }
    require(step > 0) { "Step must be greater than 0" }
    if (size < windowSize && !partialWindows) return

    val current = InPlaceDoubleSubList(this, 0, min(windowSize, size))

    while (current.start < size) {
        action(current)
        current.start += step
        val newEnd = current.start + windowSize
        if (newEnd > size && !partialWindows) break
        current.end = newEnd.coerceAtMost(size)
    }
}

/**
 * Iterates through the windows of the list with their indices and performs the given action on each window.
 *
 * @param windowSize The desired size of each window. Must be greater than 0.
 * @param step The step size between consecutive windows. Must be greater than 0, Defaults to 1.
 * @param partialWindows Whether to include partial windows at the end. Defaults to false.
 * @param action The function to be executed for each window and its index.
 * @throws IllegalArgumentException if windowSize or step is less than or equal to 0.
 */
inline fun DoubleList.forEachWindowIndexed(
    windowSize: Int,
    step: Int = 1,
    partialWindows: Boolean = false,
    action: (index: Int, InPlaceDoubleSubList) -> Unit,
) {
    var index = 0
    forEachWindow(windowSize, step, partialWindows) {
        action(index++, it)
    }
}
