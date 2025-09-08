package kubit.string

import kubit.collections.internal.throwIndexOutOfBoundsException
import kotlin.jvm.JvmField

class InPlaceCharSequence @PublishedApi internal constructor(
    /**
     * The source CharSequence from which this subSequence was created.
     */
    @JvmField @PublishedApi internal val sourceCharSequence: CharSequence,
    /**
     * The start index (inclusive) within the [sourceCharSequence] from which this subSequence starts.
     */
    @JvmField @PublishedApi internal var start: Int,
    /**
     * The end index (exclusive) within the [sourceCharSequence] at which this subSequence ends.
     */
    @JvmField @PublishedApi internal var end: Int
) : CharSequence {

    init {
        require(start >= 0) { "Start index must be greater than or equal to 0" }
        require(end <= sourceCharSequence.length) { "End index must be less than or equal to the size of the source CharSequence" }
        require(start <= end) { "Start index must be less than or equal to end index" }
    }

    /**
     * Returns the size of this subSequence.
     */
    override val length: Int
        get() = end - start

    /**
     * Returns the element at the specified [index] in this subSequence.
     *
     * [index] is relative to this subSequence and not the [sourceCharSequence].
     *
     * @throws IndexOutOfBoundsException if [index] is out of range for this subSequence.
     */
    override fun get(index: Int): Char {
        checkIndex(index)
        return sourceCharSequence[start + index]
    }

    /**
     * Returns a new [InPlaceCharSequence] that is a subsequence of this character sequence,
     * starting at the specified [startIndex] and ending right before the specified [endIndex].
     *
     * @param startIndex the start index (inclusive).
     * @param endIndex the end index (exclusive).
     */
    override fun subSequence(startIndex: Int, endIndex: Int): CharSequence {
        checkRangeIndexes(startIndex, endIndex)
        return InPlaceCharSequence(sourceCharSequence, start + startIndex, start + endIndex)
    }

    
    /**
     * Returns a string representation of this in-place subsequence.
     *
     * This does not copy the underlying characters beyond what [String.substring] does;
     * it delegates to the backing [sourceCharSequence] to produce a new `String`
     * containing the characters in the range `[start, end)`.
     *
     * Note: calling this will allocate a new `String` representing the subsequence.
     */
    override fun toString(): String {
        return when {
            start == end -> ""
            start == 0 && end == sourceCharSequence.length -> sourceCharSequence.toString()
            else -> sourceCharSequence.subSequence(start, end).toString()
        }
    }

    /**
     * Checks that the given [startIndex] (inclusive) and [endIndex] (exclusive) define a valid
     * range within this sequence. Throws [IndexOutOfBoundsException] if invalid.
     */
    private inline fun checkRangeIndexes(startIndex: Int, endIndex: Int) {
        if (startIndex < 0) {
            throwIndexOutOfBoundsException(startIndex, length)
        }
        if (endIndex < startIndex || endIndex > length) {
            throwIndexOutOfBoundsException(endIndex, length)
        }
    }

    /**
     * Checks if the specified [index] is within the valid range for this subSequence.
     *
     * @throws IndexOutOfBoundsException if [index] is out of range for this subSequence.
     */
    private inline fun checkIndex(index: Int) {
        if (index < 0 || index >= length) {
            throwIndexOutOfBoundsException(index, length)
        }
    }
}