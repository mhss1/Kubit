package kubit.collections.inplace

/**
 * Returns a list of windows with the given [windowSize] sliding with the given [step].
 * Each window is a view into the original list. Changes in the original list will be reflected in the windows.
 *
 * @param windowSize The desired size of each window. Must be greater than 0.
 * @param step The step size between consecutive windows. Defaults to 1.
 * @param partialWindows Whether or not to include partial windows at the end of the list if any. Defaults to `false`.
 * @throws IllegalArgumentException if windowSize or step is less than or equal to 0.
 * @return A [WindowedList] containing the windows.
 */
fun <T> List<T>.windowedInPlace(windowSize: Int, step: Int = 1, partialWindows: Boolean = false): WindowedList<T> {
    return WindowedList(this, windowSize, step, partialWindows)
}

/**
 * Iterates through the windows of the list and performs the given action on each window.
 *
 * @param windowSize The desired size of each window. Must be greater than 0.
 * @param step The step size between consecutive windows. Must be greater than 0. Defaults to 1.
 * @param partialWindows Whether or not to include partial windows at the end of the list if any. Defaults to `false`.
 * @param action The function to be executed for each window.
 * @throws IllegalArgumentException if windowSize or step is less than or equal to 0.
 */
inline fun <T> List<T>.forEachWindow(
    windowSize: Int,
    step: Int = 1,
    partialWindows: Boolean = false,
    action: (InPlaceSubList<T>) -> Unit,
) {
    if (isEmpty()) return
    require(windowSize > 0) { "Window size must be greater than 0" }
    require(step > 0) { "Step must be greater than 0" }
    if (size < windowSize && !partialWindows) return

    val current = InPlaceSubList(this, 0, minOf(windowSize - 1, lastIndex))

    while (current.start < size) {
        action(current)
        current.start += step
        val newEnd = current.start + windowSize - 1
        if (newEnd >= size && !partialWindows) break
        current.end = newEnd.coerceAtMost(lastIndex)
    }
}

/**
 * Iterates through the windows of the list with their indices and performs the given action on each window.
 *
 * @param windowSize The desired size of each window. Must be greater than 0.
 * @param step The step size between consecutive windows. Must be greater than 0, Defaults to 1.
 * @param partialWindows Whether or not to include partial windows at the end of the list if any. Defaults to `false`.
 * @param action The function to be executed for each window and its index.
 * @throws IllegalArgumentException if windowSize or step is less than or equal to 0.
 */
inline fun <T> List<T>.forEachWindowIndexed(
    windowSize: Int,
    step: Int = 1,
    partialWindows: Boolean = false,
    action: (index: Int, InPlaceSubList<T>) -> Unit,
) {
    var index = 0
    forEachWindow(windowSize, step, partialWindows) {
        action(index++, it)
    }
}