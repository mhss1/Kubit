package kubit.collections.inplace

import kotlin.jvm.JvmField

/**
 * A sublist view of a [sourceList] that provides access to a portion of the list within the specified
 * range ([start], [end]). Modifications to the original list are reflected in the sublist.
 *
 * This class does not modify the original list and only provides read-only access to the sublist.
 *
 */
class InPlaceSubList<T> @PublishedApi internal constructor(
    /**
     * The source list from which this sublist was created.
     */
    @JvmField @PublishedApi internal val sourceList: List<T>,
    /**
     * The start index (inclusive) within the [sourceList] from which this sublist starts.
     */
    @JvmField @PublishedApi internal var start: Int,
    /**
     * The end index (exclusive) within the [sourceList] at which this sublist ends.
     */
    @JvmField @PublishedApi internal var end: Int
) : AbstractList<T>() {

    init {
        require(start >= 0) { "Start index must be greater than or equal to 0" }
        require(end <= sourceList.size) { "End index must be less than or equal to the size of the source list" }
        require(start <= end) { "Start index must be less than or equal to end index" }
    }

    /**
     * Returns the size of this sublist.
     */
    override val size: Int
        get() = end - start

    /**
     * Returns the element at the specified [index] in this sublist.
     *
     * [index] is relative to this sublist and not the [sourceList].
     *
     * @throws IndexOutOfBoundsException if [index] is out of range for this sublist.
     */
    override fun get(index: Int): T {
        checkIndex(index)
        return sourceList[start + index]
    }

    /**
     * Performs the given [action] for each element in this sublist.
     *
     * This method is faster and more efficient than [List.forEach].
     *
     * @param action The function to be executed
     */
    inline fun fastForEach(action: (T) -> Unit) {
        for (i in start until end) {
            action(sourceList[i])
        }
    }

    /**
     * Performs the given [action] for each element in this sublist,
     * providing its index relative to this sublist.
     *
     * This method is faster and more efficient than [List.forEachIndexed].
     *
     * @param action The function to be executed
     */
    inline fun fastForEachIndexed(action: (index: Int, T) -> Unit) {
        for (i in start until end) {
            action(i - start, sourceList[i])
        }
    }

    /**
     * Checks if the specified [index] is within the valid range for this sublist.
     *
     * @throws IndexOutOfBoundsException if [index] is out of range for this sublist.
     */
    private inline fun checkIndex(index: Int) {
        if (index < 0 || index >= size) {
            throwIndexOutOfBoundsException(index, size)
        }
    }
}