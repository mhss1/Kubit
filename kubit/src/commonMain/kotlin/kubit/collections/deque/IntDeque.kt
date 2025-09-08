package kubit.collections.deque

import kubit.collections.internal.throwIllegalArgumentException
import kubit.collections.internal.throwIndexOutOfBoundsException
import kubit.collections.internal.throwNoSuchElementException
import kotlin.jvm.JvmField

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to the kotlin source file.
//
// This file was generated from a template in the template directory.
// Make a change to the original template and run the generatePrimitiveDeques.sh script
// to ensure the change is available on all versions of the deque.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

/**
 * [IntDeque] is a double-ended queue for [Int] values backed by a circular buffer.
 *
 * - Amortized O(1) insertion/removal at both ends.
 * - Indexed access, insertion and removal are supported (may shift elements internally).
 * - Not thread-safe: synchronize externally if multiple threads mutate concurrently.
 *
 * @constructor Creates a deque with the given [initialCapacity]. The deque grows automatically
 * as needed when elements are added beyond the current capacity.
 * @throws IllegalArgumentException if [initialCapacity] is negative.
 */
class IntDeque(initialCapacity: Int) {

    init {
        if (initialCapacity < 0) throwIllegalArgumentException("Deque capacity must be >= 0")
    }

    @PublishedApi
    @JvmField
    internal var data = IntArray(initialCapacity)

    @PublishedApi
    @JvmField
    internal var head = 0

    @PublishedApi
    @JvmField
    @Suppress("PropertyName")
    internal var _size = 0

    /** The number of elements in this deque. */
    inline val size: Int get() = _size
    
    /** The last valid element index, or -1 when the deque is empty. */
    inline val lastIndex: Int get() = _size - 1

    /** Returns the first element, or throws if this deque is empty. */
    fun first(): Int {
        if (_size == 0) throwNoSuchElementException("Deque is empty")
        return data[head]
    }

    /** Returns the first element, or `null` if this deque is empty. */
    fun firstOrNull(): Int? = if (_size == 0) null else data[head]

    /** Returns the last element, or throws if this deque is empty. */
    fun last(): Int {
        if (_size == 0) throwNoSuchElementException("Deque is empty")
        return data[lastIndex.internalIndex()]
    }

    /** Returns the last element, or `null` if this deque is empty. */
    fun lastOrNull(): Int? = if (_size == 0) null else data[lastIndex.internalIndex()]

    /**
     * Returns the element at [index].
     * @throws IndexOutOfBoundsException if [index] is out of bounds [0, size).
     */
    operator fun get(index: Int): Int {
        if (index < 0 || index >= _size) throwIndexOutOfBoundsException("Index must be between 0 and size")
        return data[index.internalIndex()]
    }

    /**
     * Replaces the element at [index] with [value].
     * @return the previous element at [index].
     * @throws IndexOutOfBoundsException if [index] is out of bounds [0, size).
     */
    operator fun set(index: Int, value: Int): Int {
        if (index < 0 || index >= _size) throwIndexOutOfBoundsException("Index must be between 0 and size")

        val internalIndex = index.internalIndex()
        val old = data[internalIndex]
        data[internalIndex] = value

        return old
    }

    /** Adds [value] to the end of this deque. Alias for [addLast]. */
    fun add(value: Int) = addLast(value)

    /** Adds [value] to the end of this deque. */
    fun addLast(value: Int) {
        ensureCapacity(_size + 1)
        data[_size.internalIndex()] = value
        _size++
    }

    /** Adds [value] to the front (head) of this deque. */
    fun addFirst(value: Int) {
        ensureCapacity(_size + 1)
        head = head.previousIndex()
        data[head] = value
        _size++
    }

    /** Pushes [value] onto the front (stack-style). Alias for [addFirst]. */
    fun push(value: Int) = addFirst(value)

    /** Pops and returns the front element (stack-style). Alias for [removeFirst]. */
    fun pop(): Int = removeFirst()

    /** Enqueues [value] at the end (queue-style). Alias for [addLast]. */
    fun enqueue(value: Int) = addLast(value)

    /** Dequeues and returns the front element (queue-style). Alias for [removeFirst]. */
    fun dequeue(): Int = removeFirst()

    /**
     * Removes and returns the first element.
     * @throws NoSuchElementException if the deque is empty.
     */
    fun removeFirst(): Int {
        if (_size == 0) throwNoSuchElementException("Deque is empty")
        val value = data[head]
        head = head.nextIndex()
        _size--
        return value
    }

    /** Removes and returns the first element, or `null` if the deque is empty. */
    fun removeFirstOrNull(): Int? = if (_size == 0) null else removeFirst()

    /**
     * Removes and returns the last element.
     * @throws NoSuchElementException if the deque is empty.
     */
    fun removeLast(): Int {
        if (_size == 0) throwNoSuchElementException("Deque is empty")
        val internalIndex = lastIndex.internalIndex()
        val value = data[internalIndex]
        _size--
        return value
    }

    /** Removes and returns the last element, or `null` if the deque is empty. */
    fun removeLastOrNull(): Int? = if (_size == 0) null else removeLast()

    /**
     * Removes and returns the element at [index].
     * The internal storage may shift elements towards either end to fill the gap.
     * @throws IndexOutOfBoundsException if [index] is out of bounds [0, size).
     */
    fun removeAt(index: Int): Int {
        if (index < 0 || index >= _size) throwIndexOutOfBoundsException("Index must be between 0 and size")

        if (index == 0) return removeFirst()
        if (index == lastIndex) return removeLast()

        val internalIndex = index.internalIndex()
        val item = data[internalIndex]

        if (index < _size / 2) {
            shiftForward(head, internalIndex)
            head = head.nextIndex()
        } else {
            shiftBack(internalIndex.nextIndex(), _size.internalIndex())
        }

        _size--
        return item
    }

    /**
     * Inserts [value] at [index]. Elements may be shifted to make room for the new element.
     * @throws IndexOutOfBoundsException if [index] is out of bounds [0, size].
     */
    fun add(index: Int, value: Int) {
        if (index < 0 || index > _size) throwIndexOutOfBoundsException("Index must be between 0 and size")

        if (index == 0) return addFirst(value)
        if (index == _size) return addLast(value)

        ensureCapacity(_size + 1)

        val internalIndex = index.internalIndex()

        if (index < (_size + 1) / 2) {
            shiftBack(head, internalIndex)

            data[internalIndex.previousIndex()] = value
            head = head.previousIndex()
        } else {
            shiftForward(internalIndex, _size.internalIndex())
            data[internalIndex] = value
        }

        _size++
    }

    private inline fun shiftBack(startIndex: Int, endIndex: Int) {
        if (startIndex == endIndex) return
        if (startIndex < endIndex) {
            data[startIndex.previousIndex()] = data[startIndex]
            data.copyInto(data, startIndex, startIndex + 1, endIndex)
        } else {
            data.copyInto(data, startIndex - 1, startIndex, data.size)
            data[data.lastIndex] = data[0]
            if (endIndex != 0) data.copyInto(data, 0, 1, endIndex)
        }
    }


    private inline fun shiftForward(startIndex: Int, endIndex: Int) {
        if (startIndex == endIndex) return
        if (startIndex < endIndex) {
            data.copyInto(data, startIndex + 1, startIndex, endIndex)
        } else {
            data.copyInto(data, 1, 0, endIndex)
            data[0] = data[data.lastIndex]
            data.copyInto(data, startIndex + 1, startIndex, data.lastIndex)
        }
    }

    /** Iterates elements from first to last, invoking [action] for each element. */
    inline fun forEach(action: (Int) -> Unit) {
        if (_size == 0) return

        val startIndex = head
        val endIndex = _size.internalIndex()

        if (startIndex < endIndex) {
            for (i in head until endIndex) {
                action(data[i])
            }
        } else {
            for (i in head until data.size) {
                action(data[i])
            }
            for (i in 0 until endIndex) {
                action(data[i])
            }
        }
    }

    /** Iterates elements from last to first, invoking [action] for each element. */
    inline fun forEachReversed(action: (Int) -> Unit) {
        if (_size == 0) return

        val startIndex = head
        val endIndex = _size.internalIndex()

        if (startIndex < endIndex) {
            for (i in endIndex - 1 downTo head) {
                action(data[i])
            }
        } else {
            for (i in endIndex - 1 downTo 0) {
                action(data[i])
            }
            for (i in data.size - 1 downTo head) {
                action(data[i])
            }
        }
    }

    /** Iterates elements from first to last, passing the logical [index] and [value] to [action]. */
    inline fun forEachIndexed(action: (index: Int, value: Int) -> Unit) {
        var index = 0
        forEach { action(index++, it) }
    }

    /** Iterates elements from last to first, passing the logical [index] and [value] to [action]. */
    inline fun forEachReversedIndexed(action: (index: Int, value: Int) -> Unit) {
        var index = size
        forEachReversed { action(--index, it) }
    }


    /** Returns `true` if [value] exists in this deque. */
    operator fun contains(value: Int): Boolean {
        forEach { if (it == value) return true }
        return false
    }

    /** Returns the index of the first occurrence of [value], or -1 if not found. */
    fun indexOf(value: Int): Int {
        forEachIndexed { index, it -> if (it == value) return index }
        return -1
    }

    /** Returns the index of the last occurrence of [value], or -1 if not found. */
    fun lastIndexOf(value: Int): Int {
        forEachReversedIndexed { index, it -> if (it == value) return index }
        return -1
    }

    /** Returns `true` if this deque contains no elements. */
    inline fun isEmpty() = _size == 0
    /** Returns `true` if this deque contains one or more elements. */
    inline fun isNotEmpty() = !isEmpty()

    /** Removes all elements from this deque. Capacity is preserved. */
    fun clear() {
        head = 0
        _size = 0
    }

    private inline fun Int.previousIndex() = if (this == 0) data.lastIndex else this - 1

    private inline fun Int.nextIndex() = if (this == data.lastIndex) 0 else this + 1

    @PublishedApi internal inline fun Int.internalIndex() = adjustedIndex(this + head)

    @PublishedApi internal inline fun adjustedIndex(index: Int): Int = if (index >= data.size) index - data.size else index


    /**
     * Ensures the internal storage can hold at least [capacity] elements.
     * If growth is required, elements are copied to a new buffer preserving logical order.
     */
    private fun ensureCapacity(capacity: Int) {
        val old = data
        if (old.size >= capacity) return
        val oldSize = old.size
        val newSize = maxOf(capacity, if (oldSize > 0) oldSize * 3 / 2 else capacity)

        val newData = IntArray(newSize)

        val tail = _size.internalIndex()

        if (head < tail) {
            old.copyInto(newData, 0, head, tail)
        } else {
            old.copyInto(newData, 0, head, oldSize)
            old.copyInto(newData, oldSize - head, 0, tail)
        }

        data = newData
        head = 0
    }
}

/** Returns a live [MutableList] view backed by this deque. Structural changes are reflected both ways. */
fun IntDeque.asMutableList(): MutableList<Int> = IntDequeMutableListView(this)
/** Returns a live [List] view backed by this deque. */
fun IntDeque.asList(): List<Int> = asMutableList()

/** A [MutableList] view over a [IntDeque]. Mutating the view mutates the deque and vice versa. */
internal class IntDequeMutableListView(private val deque: IntDeque) : AbstractMutableList<Int>(), RandomAccess {
    override val size: Int
        get() = deque.size

    override fun get(index: Int) = deque[index]

    override fun set(index: Int, element: Int) = deque.set(index, element)

    override fun add(index: Int, element: Int) = deque.add(index, element)

    override fun removeAt(index: Int): Int = deque.removeAt(index)
}
