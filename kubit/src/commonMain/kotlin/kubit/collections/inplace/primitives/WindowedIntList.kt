package kubit.collections.inplace.primitives

import kubit.collections.inplace.throwIndexOutOfBoundsException
import kubit.collections.list.IntList
import kotlin.jvm.JvmField

/**
 * Represents a list of windows as a list of [InPlaceIntSubList] created from a source IntList.
 * Each window is a view into the original list. Changes in the original list will be reflected in the windows.
 *
 * @param sourceList The original IntList.
 * @param windowSize The desired size of each window. Must be greater than 0.
 * @param stepSize The step size between consecutive windows. Defaults to 1.
 * @param partialWindows Whether or not to include partial windows at the end of the list if any. Defaults to `false`.
 * @throws IllegalArgumentException if windowSize or stepSize is less than or equal to 0
 */
class WindowedIntList(
    private val sourceList: IntList,
    private val windowSize: Int,
    private val stepSize: Int = 1,
    private val partialWindows: Boolean = false,
) : AbstractList<InPlaceIntSubList>() {

    init {
        require(windowSize > 0) { "Window size must be greater than 0" }
        require(stepSize > 0) { "Step size must be greater than 0" }
    }

    /**
     * Internal array storing the windows as [InPlaceIntSubList] instances.
     */
    @PublishedApi
    @JvmField
    internal val windows = initWindowsArray()

    /**
     * Returns the total number of windows in the list.
     */
    override val size: Int
        get() = windows.size

    /**
     * Iterates through the windows and performs the given action on each window.
     *
     * @param action The function to be executed for each window.
     */
    inline fun fastForEach(action: (InPlaceIntSubList) -> Unit) {
        windows.forEach(action)
    }

    /**
     * Iterates through the windows with their indices and performs the given action on each window.
     *
     * @param action The function to be executed for each window and its index.
     */
    inline fun fastForEachIndexed(action: (index: Int, InPlaceIntSubList) -> Unit) {
        windows.forEachIndexed(action)
    }

    /**
     * Returns the window at the specified index.
     *
     * @param index The index of the window to retrieve.
     * @return The window at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    override fun get(index: Int): InPlaceIntSubList {
        checkIndex(index)
        return windows[index]
    }

    private fun checkIndex(index: Int) {
        if (index < 0 || index >= size) {
            throwIndexOutOfBoundsException(index, size)
        }
    }

    private fun initWindowsArray(): Array<InPlaceIntSubList> {
        var index = 0
        return Array(calculateWindowSize(sourceList.size, windowSize, stepSize, partialWindows)) {
            val end = index + windowSize
            InPlaceIntSubList(sourceList, index, end.coerceAtMost(sourceList.size)).also { index += stepSize }
        }
    }

    private fun calculateWindowSize(
        sourceSize: Int,
        windowSize: Int,
        stepSize: Int,
        partialWindows: Boolean
    ) = when {
        partialWindows -> (sourceSize + stepSize - 1) / stepSize
        sourceSize < windowSize -> 0
        else -> (sourceSize - windowSize + stepSize) / stepSize
    }
}
