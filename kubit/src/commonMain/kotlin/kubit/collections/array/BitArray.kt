package kubit.collections.array

import kubit.collections.internal.throwIndexOutOfBoundsException
import kubit.collections.internal.throwIllegalArgumentException
import kotlin.jvm.JvmField

@PublishedApi
internal const val PAGE_SIZE = Int.SIZE_BITS
@PublishedApi
internal const val ALL_ONES = 0xFFFFFFFF.toInt()

/**
 * A class representing a bit array where each bit is stored as a boolean value in a compact, memory-efficient manner.
 * The boolean values are packed into integers, with each integer representing 32 bits.
 * This class provides methods to set and get the boolean values at specific indices, as well as perform various operations on the array.
 * 
 * This class is NOT synchronized. Simultaneous modifications and reads from multiple threads may lead to unpredictable behavior.
 *
 * @property size The size of the bit array, specifying the number of individual boolean values it can hold.
 */
class BitArray {

    @JvmField
    @PublishedApi
    internal val data: IntArray

    @Suppress("PropertyName")
    @JvmField
    @PublishedApi
    internal val _size: Int

    val size: Int
        get() = _size

    /**
     * Constructs a [BitArray] of the given size.
     *
     * @param size The size of the bit array.
     */
    constructor(size: Int) {
        data = IntArray(size / PAGE_SIZE + if (size % PAGE_SIZE != 0) 1 else 0)
        _size = size
    }

    private constructor(source: IntArray, size: Int) {
        data = source
        _size = size
    }

    /**
     * Gets the boolean value at the specified index.
     *
     * @param i The index to retrieve the value from.
     * @return The boolean value at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    operator fun get(i: Int): Boolean {
        checkIndex(i)
        return (data[i.page()] and i.bitMask()) != 0
    }

    /**
     * Sets the boolean value at the specified index.
     *
     * @param i The index to set the value at.
     * @param value The boolean value to set.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     * @see setTrue
     * @see setFalse
     */
    operator fun set(i: Int, value: Boolean) {
        checkIndex(i)
        val page = i.page()
        val mask = i.bitMask()
        val p = data[page]
        val inv = mask.inv()
        data[page] = if (value) {
            p or mask
        } else {
            p and inv
        }
    }

    /**
     * Sets all bits in the inclusive range `[from, to]` to `value`.
     *
     * No-op when `from > to`.
     *
     * @param from Inclusive start index.
     * @param to Inclusive end index.
     * @param value The boolean value to set in the range.
     * @throws IndexOutOfBoundsException If `from < 0` or `to >= size`.
     */
    fun set(from: Int, to: Int, value: Boolean) {
        if (from > to) return
        checkRangeInclusive(from, to)

        val firstPage = from.page()
        val lastPage = to.page()

        if (firstPage == lastPage) {
            val mask = bitMask(from = from % PAGE_SIZE, to = to % PAGE_SIZE)
            if (value) {
                data[firstPage] = data[firstPage] or mask
            } else {
                data[firstPage] = data[firstPage] and mask.inv()
            }
            return
        }

        val firstIndexInFirstPage = from % PAGE_SIZE
        val firstPageMask = bitMask(from = firstIndexInFirstPage, to = PAGE_SIZE - 1)
        data[firstPage] = if (value) {
            data[firstPage] or firstPageMask
        } else {
            data[firstPage] and firstPageMask.inv()
        }

        val lastIndexInLastPage = to % PAGE_SIZE
        val lastPageMask = bitMask(from = 0, to = lastIndexInLastPage)
        data[lastPage] = if (value) {
            data[lastPage] or lastPageMask
        } else {
            data[lastPage] and lastPageMask.inv()
        }

        if (lastPage - firstPage < 2) return

        // set all pages in between
        val pageValue = if (value) ALL_ONES else 0
        data.fill(pageValue, firstPage + 1, lastPage)
    }
    
    
    /**
     * Fills the specified range of this `BitArray` with the given boolean value.
     *
     * @param element The boolean value to fill the range with.
     * @param fromIndex Inclusive start index of the range to fill (default 0).
     * @param toIndex Exclusive end index of the range to fill (default `size`).
     * @throws IndexOutOfBoundsException if `fromIndex` or `toIndex - 1` are out of bounds.
     */
    fun fill(element: Boolean, fromIndex: Int = 0, toIndex: Int = size) {
        set(fromIndex, toIndex - 1, element)
    }

    /**
     * Sets all bits in the given inclusive `range` to `value`.
     *
     * No-op for `IntRange.EMPTY`.
     *
     * @param range Inclusive range of indices to set.
     * @param value The boolean value to set in the range.
     * @throws IndexOutOfBoundsException If `range.first < 0` or `range.last >= size` for non-empty ranges.
     */
    fun set(range: IntRange, value: Boolean) {
        set(range.first, range.last, value)
    }

    /**
     * Sets the value at the specified index to `true`.
     *
     * @param i The index to set to `true`.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     * @see setFalse
     */
    fun setTrue(i: Int) {
        checkIndex(i)
        val page = i.page()
        data[page] = data[page] or i.bitMask()
    }

    /**
     * Sets the value at the specified index to `false`.
     *
     * @param i The index to set to `false`.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     * @see setTrue
     */
    fun setFalse(i: Int) {
        checkIndex(i)
        val page = i.page()
        data[page] = data[page] and i.bitMask().inv()
    }

    /**
     * Performs the given action on each element in the array.
     *
     * @param action The action to perform on each element.
     */
    inline fun forEach(action: (Boolean) -> Unit) {
        if (_size == 0) return
        repeat(data.size - 1) { i ->
            val page = data[i]
            var mask = 1

            repeat(PAGE_SIZE) {
                action((page and mask) != 0)
                mask = mask shl 1
            }
        }

        val lastPageSize = if (_size % PAGE_SIZE == 0) PAGE_SIZE else _size % PAGE_SIZE
        val page = data[data.size - 1]
        var mask = 1
        repeat(lastPageSize) {
            action((page and mask) != 0)
            mask = mask shl 1
        }
    }

    /**
     * Performs the given action on each element in the array, providing the index of the element.
     *
     * @param action The action to perform on each element, with the index provided.
     */
    inline fun forEachIndexed(action: (Int, Boolean) -> Unit) {
        var i = 0
        forEach { action(i++, it) }
    }

    /**
     * Returns `true` if all elements are `true`, `false` otherwise.
     */
    fun allTrue(): Boolean {
        if (isEmpty()) return true

        val last = data.lastIndex
        for (i in 0 until last) {
            if (data[i] != ALL_ONES) return false
        }

        return data[last] == lastPageValidMask()
    }


    /**
     * Returns `true` if all elements are `false`, `false` otherwise.
     */
    fun allFalse(): Boolean {
        return data.all { it == 0 }
    }

    /**
     * Returns `true` if any element is `true`, `false` otherwise.
     */
    fun anyTrue(): Boolean {
        return !allFalse()
    }

    /**
     * Returns `true` if any element is `false`, `false` otherwise.
     */
    fun anyFalse(): Boolean {
        return !allTrue()
    }
    
    /**
     * Returns `true` if no element in the array is `true`.
     *
     * @return `true` when there are zero `true` bits, `false` otherwise.
     */
    fun noneTrue(): Boolean {
        return allFalse()
    }
    
    /**
     * Returns `true` if no element in the array is `false`.
     *
     * @return `true` when there are zero `false` bits, `false` otherwise.
     */
    fun noneFalse(): Boolean {
        return allTrue()
    }

    /**
     * Sets all elements in the array to `false`.
     */
    fun clear() {
        data.fill(0)
    }

    /**
     * Returns a copy of this [BitArray].
     */
    fun copyOf(): BitArray {
        return BitArray(data.copyOf(), _size)
    }
    
    /**
     * Returns `true` if this [BitArray] is equal to the specified [other] [BitArray].
     *
     * Two [BitArray] instances are considered equal if they have the same size and all their elements are equal.
     *
     * @param other The [BitArray] to compare this array to.
     * @return `true` if this array is equal to the specified array, `false` otherwise.
     */
    fun contentEquals(other: BitArray): Boolean {
        if (this._size != other._size) return false
        if (data.isEmpty()) return true
        
        val last = data.lastIndex
        for (i in 0 until last) if (data[i] != other.data[i]) return false
        
        val lastPageMask = lastPageValidMask()
        return (data[last] and lastPageMask) == (other.data[last] and lastPageMask)
    }

    /**
     * Returns `true` if any element matches the given boolean value.
     *
     * @param value the boolean value to test for existence.
     * @return `true` if at least one element matches the value, `false` otherwise.
     */
    operator fun contains(value: Boolean): Boolean = if (value) anyTrue() else anyFalse()

    /**
     * Returns a new [BitArray] which is the bitwise OR of this and [other].
     *
     * @param other another [BitArray] of same size.
     * @return the result of the operation.
     * @throws IllegalArgumentException if arrays are not the same size.
     */
    infix fun or(other: BitArray) = bitwiseOp(other) { a, b -> a or b }

    /**
     * Returns a new [BitArray] which is the bitwise AND of this and [other].
     *
     * @param other another [BitArray] of same size.
     * @return the result of the operation.
     * @throws IllegalArgumentException if arrays are not the same size.
     */
    infix fun and(other: BitArray) = bitwiseOp(other) { a, b -> a and b }

    /**
     * Returns a new [BitArray] which is the bitwise XOR of this and [other].
     *
     * @param other another [BitArray] of same size.
     * @return the result of the operation.
     * @throws IllegalArgumentException if arrays are not the same size.
     */
    infix fun xor(other: BitArray) = bitwiseOp(other) { a, b -> a xor b }

    /**
     * Returns a new [BitArray] which is the bitwise NAND of this and [other].
     *
     * @param other another [BitArray] of same size.
     * @return the result of the operation.
     * @throws IllegalArgumentException if arrays are not the same size.
     */
    infix fun nand(other: BitArray) = bitwiseOp(other) { a, b -> (a and b).inv() }

    /**
     * Returns a new [BitArray] which is the bitwise NOR of this and [other].
     *
     * @param other another [BitArray] of same size.
     * @return the result of the operation.
     * @throws IllegalArgumentException if arrays are not the same size.
     */
    infix fun nor(other: BitArray) = bitwiseOp(other) { a, b -> (a or b).inv() }

    /**
     * Performs a bitwise operation with [other] and returns a new [BitArray].
     *
     * @param other another [BitArray] of same size.
     * @param performOp function that takes two Ints (pages), returns result of operation.
     * @return a new [BitArray] with result of operation.
     * @throws IllegalArgumentException if arrays are not the same size.
     */
    private inline fun bitwiseOp(
        other: BitArray,
        performOp: (Int, Int) -> Int
    ): BitArray {
        requireSameSize(other)
        if (data.isEmpty()) return BitArray(0)

        val out = IntArray(data.size)

        val last = data.lastIndex
        for (i in 0 until last) out[i] = performOp(data[i], other.data[i])

        out[last] = performOp(data[last], other.data[last]) and lastPageValidMask()

        return BitArray(out, _size)
    }

    /**
     * Performs a bitwise OR with [other] in place, modifying this array.
     *
     * @param other another [BitArray] of same size.
     * @throws IllegalArgumentException if arrays are not the same size.
     */
    infix fun orInPlace(other: BitArray) = bitwiseOpInPlace(other) { a, b -> a or b }

    /**
     * Performs a bitwise AND with [other] in place, modifying this array.
     *
     * @param other another [BitArray] of same size.
     * @throws IllegalArgumentException if arrays are not the same size.
     */
    infix fun andInPlace(other: BitArray) = bitwiseOpInPlace(other) { a, b -> a and b }

    /**
     * Performs a bitwise XOR with [other] in place, modifying this array.
     *
     * @param other another [BitArray] of same size.
     * @throws IllegalArgumentException if arrays are not the same size.
     */
    infix fun xorInPlace(other: BitArray) = bitwiseOpInPlace(other) { a, b -> a xor b }

    /**
     * Performs a bitwise NAND with [other] in place, modifying this array.
     *
     * @param other another [BitArray] of same size.
     * @throws IllegalArgumentException if arrays are not the same size.
     */
    infix fun nandInPlace(other: BitArray) = bitwiseOpInPlace(other) { a, b -> (a and b).inv() }

    /**
     * Performs a bitwise NOR with [other] in place, modifying this array.
     *
     * @param other another [BitArray] of same size.
     * @throws IllegalArgumentException if arrays are not the same size.
     */
    infix fun norInPlace(other: BitArray) = bitwiseOpInPlace(other) { a, b -> (a or b).inv() }

    /**
     * Performs a bitwise operation with [other], modifying this array in place.
     *
     * @param other another [BitArray] of same size.
     * @param performOp function that takes two Ints (pages), returns result of operation.
     * @throws IllegalArgumentException if arrays are not the same size.
     */
    private inline fun bitwiseOpInPlace(other: BitArray, performOp: (Int, Int) -> Int) {
        requireSameSize(other)
        if (data.isEmpty()) return
        
        val last = data.lastIndex
        for (i in 0 until last) data[i] = performOp(data[i], other.data[i])
        data[last] = performOp(data[last], other.data[last]) and lastPageValidMask()
    }

    /**
     * Returns `true` if any bit set in [other] is also set in this array (intersection).
     *
     * @param other another [BitArray] of same size.
     * @return `true` if there is at least one overlapping `true` bit, `false` otherwise.
     * @throws IllegalArgumentException if arrays are not the same size.
     */
    infix fun intersects(other: BitArray): Boolean {
        requireSameSize(other)
        if (data.isEmpty()) return false

        val last = data.lastIndex
        for (i in 0 until last) if ((data[i] and other.data[i]) != 0) return true
        return ((data[last] and other.data[last]) and lastPageValidMask()) != 0
    }

    /**
     * Returns `true` if every `true` bit in this array is also `true` in [other] (subset test).
     *
     * @param other another [BitArray] of same size.
     * @return `true` if this array is a subset of [other], `false` otherwise.
     * @throws IllegalArgumentException if arrays are not the same size.
     */
    infix fun isSubsetOf(other: BitArray): Boolean {
        requireSameSize(other)
        if (data.isEmpty()) return true
        val last = data.lastIndex

        for (i in 0 until last) if ((data[i] and other.data[i].inv()) != 0) return false
        return ((data[last] and other.data[last].inv()) and lastPageValidMask()) == 0
    }

    /**
     * Returns `true` if every `true` bit in [other] is also `true` in this array (superset test).
     *
     * @param other another [BitArray] of same size.
     * @return `true` if this array is a superset of [other], `false` otherwise.
     * @throws IllegalArgumentException if arrays are not the same size.
     */
    infix fun isSupersetOf(other: BitArray): Boolean = other.isSubsetOf(this)

    /**
     * Returns A [BooleanArray] representation of this [BitArray].
     */
    fun toBooleanArray(): BooleanArray = BooleanArray(_size) { getUnchecked(it) }

    /**
     * Counts the number of occurrences of the specified boolean value in the [BitArray].
     *
     * @param value The boolean value to count.
     * @return The number of occurrences of the specified boolean value.
     */
    fun count(value: Boolean): Int {
        val count = data.sumOf { it.countOneBits() }
        return if (value) count else _size - count
    }

    /**
     * Returns `true` if the array is empty (has a size of 0), `false` otherwise.
     */
    fun isEmpty(): Boolean = _size == 0

    /**
     * Returns the range of valid indices for the array.
     */
    val indices: IntRange
        get() = 0 until _size

    /**
     * Throws an [IllegalArgumentException] if [other] does not have the same size as this.
     */
    private inline fun requireSameSize(other: BitArray) {
        if (this._size != other._size) throwIllegalArgumentException("Size mismatch: ${this._size} != ${other._size}")
    }

    /**
     * Returns a mask covering only the bits within the used size in the last integer page.
     *
     * @return a mask with ones in all valid bits of the last integer.
     */
    private inline fun lastPageValidMask(): Int {
        val lastPageBits = _size % PAGE_SIZE
        return if (lastPageBits == 0) ALL_ONES else bitMask(0, lastPageBits - 1)
    }

    /**
     * Calculates the index of the integer in the `data` array for the given element index.
     * Uses a bit shift right by 5 (equivalent to integer division by 32).
     */
    private inline fun Int.page() = this ushr 5

    /**
     * Creates a bit mask with a single '1' bit at the position corresponding to the element index within an integer.
     */
    private inline fun Int.bitMask() = 1 shl (this and (PAGE_SIZE - 1))

    /**
     * Creates a bitmask covering the bit positions from `from` (inclusive) to `to` (inclusive)
     * within a 32-bit integer.
     *
     * @param from The starting bit index (inclusive), in range 0..31.
     * @param to The ending bit index (inclusive), in range 0..31.
     * @return The integer mask with bits in the given range set to `1`.
     */
    private inline fun bitMask(from: Int, to: Int): Int {
        val leftMask = ALL_ONES shl from
        val rightMask = ALL_ONES ushr (PAGE_SIZE - to - 1)
        return leftMask and rightMask
    }

    /**
     * Gets the boolean value at the specified index without performing bounds checks.
     */
    private inline fun getUnchecked(i: Int) = (data[i.page()] and i.bitMask()) != 0

    
    private inline fun checkIndex(index: Int) {
        if (index < 0 || index >= _size) throwIndexOutOfBoundsException(index, _size)
    }
    
    private inline fun checkRangeInclusive(from: Int, to: Int) {
        if (from < 0) throwIndexOutOfBoundsException(from, size)
        if (to >= size) throwIndexOutOfBoundsException(to, size)
    }
}