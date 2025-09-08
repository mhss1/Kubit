package kubit.collections.inplace.primitives

import kubit.collections.internal.throwIndexOutOfBoundsException
import kubit.collections.list.IntList
import kotlin.jvm.JvmField

/**
 * A sublist view of a [sourceList] that provides access to a portion of the list within the specified
 * range ([start], [end]). Modifications to the original list are reflected in the sublist.
 *
 * This class does not modify the original list and only provides read-only access to the sublist.
 *
 */
class InPlaceIntSubList @PublishedApi internal constructor(
    /**
     * The source list from which this sublist was created.
     */
     @JvmField @PublishedApi internal val sourceList: IntList,
    /**
     * The start index (inclusive) within the [sourceList] from which this sublist starts.
     */
     @JvmField @PublishedApi internal var start: Int,
    /**
     * The end index (exclusive) within the [sourceList] at which this sublist ends.
     */
     @JvmField @PublishedApi internal var end: Int
) {

    init {
        require(start >= 0) { "Start index must be greater than or equal to 0" }
        require(end <= sourceList.size) { "End index must be less than or equal to the size of the source list" }
        require(start <= end) { "Start index must be less than or equal to end index" }
    }

    /**
     * Returns the size of this sublist.
     */
    val size: Int
        get() = end - start

    /**
     * Returns the element at the specified [index] in this sublist.
     *
     * [index] is relative to this sublist and not the [sourceList].
     *
     * @throws IndexOutOfBoundsException if [index] is out of range for this sublist.
     */
    operator fun get(index: Int): Int {
        checkIndex(index)
        return sourceList.content[start + index]
    }

    /**
     * Performs the given [action] for each element in this sublist.
     *
     * @param action The function to be executed
     */
    inline fun forEach(action: (Int) -> Unit) {
        for (i in start until end) {
            action(sourceList.content[i])
        }
    }

    /**
     * Performs the given [action] for each element in this sublist,
     * providing its index relative to this sublist.
     *
     *
     * @param action The function to be executed
     */
    inline fun forEachIndexed(action: (index: Int, Int) -> Unit) {
        for (i in start until end) {
            action(i - start, sourceList.content[i])
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
