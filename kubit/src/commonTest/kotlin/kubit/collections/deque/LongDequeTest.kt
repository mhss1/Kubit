package kubit.collections.deque

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertFailsWith

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to the kotlin source file.
//
// This file was generated from a template in the template directory.
// Make a change to the original template and run the generatePrimitiveDeques.sh script
// to ensure the change is available on all versions of the deque.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

class LongDequeTest {

    private fun LongDeque.toList(): List<Long> {
        val result = mutableListOf<Long>()
        forEach { result += it }
        return result
    }

    @Test
    fun constructorNegativeCapacity() {
        assertFailsWith<IllegalArgumentException> { LongDeque(-1) }
    }

    @Test
    fun emptyState() {
        val d = LongDeque(0)
        assertEquals(0, d.size)
        assertTrue(d.isEmpty())
        assertFalse(d.isNotEmpty())
        assertFailsWith<NoSuchElementException> { d.first() }
        assertFailsWith<NoSuchElementException> { d.last() }
        assertNull(d.firstOrNull())
        assertNull(d.lastOrNull())
        assertNull(d.removeFirstOrNull())
        assertNull(d.removeLastOrNull())
    }

    @Test
    fun addLastAndIteration() {
        val d = LongDeque(1)
        for (i in 0 until 10) d.addLast(i.toLong())
        assertEquals((0 until 10).map { it.toLong() }, d.toList())
        assertEquals(10, d.size)
        assertEquals(0L, d.first())
        assertEquals(9L, d.last())
    }

    @Test
    fun addFirst() {
        val d = LongDeque(2)
        for (i in 0 until 5) d.addFirst(i.toLong())
        assertEquals(listOf(4L,3L,2L,1L,0L), d.toList())
        assertEquals(5, d.size)
        assertEquals(4L, d.first())
        assertEquals(0L, d.last())
    }

    @Test
    fun addAlias() {
        val d = LongDeque(2)
        d.add(1L)
        d.add(2L)
        assertEquals(listOf(1L,2L), d.toList())
    }

    @Test
    fun setReturnsOld() {
        val d = LongDeque(4)
        d.addLast(1L); d.addLast(2L); d.addLast(3L)
        val old = d.set(1, 9L)
        assertEquals(2L, old)
        assertEquals(listOf(1L,9L,3L), d.toList())
    }

    @Test
    fun getSetOutOfBounds() {
        val d = LongDeque(1)
        d.addLast(1L)
        assertFailsWith<IndexOutOfBoundsException> { d[1] }
        assertFailsWith<IndexOutOfBoundsException> { d[-1] }
        assertFailsWith<IndexOutOfBoundsException> { d.set(1, 2L) }
        assertFailsWith<IndexOutOfBoundsException> { d.set(-1, 2L) }
    }

    @Test
    fun addIndexOutOfBounds() {
        val d = LongDeque(2)
        assertFailsWith<IndexOutOfBoundsException> { d.add(-1, 1L) }
        assertFailsWith<IndexOutOfBoundsException> { d.add(1, 1L) }
        d.addLast(5L)
        assertFailsWith<IndexOutOfBoundsException> { d.add(3, 2L) }
    }

    @Test
    fun removeIndexOutOfBounds() {
        val d = LongDeque(2)
        assertFailsWith<IndexOutOfBoundsException> { d.removeAt(0) }
        d.addLast(1L)
        assertFailsWith<IndexOutOfBoundsException> { d.removeAt(1) }
        assertFailsWith<IndexOutOfBoundsException> { d.removeAt(-1) }
    }

    @Test
    fun removeFirstLast() {
        val d = LongDeque(3)
        d.addLast(1L); d.addLast(2L); d.addLast(3L)
        assertEquals(1L, d.removeFirst())
        assertEquals(3L, d.removeLast())
        assertEquals(listOf(2L), d.toList())
        assertEquals(1, d.size)
    }

    @Test
    fun removeFirstLastEmptyExceptions() {
        val d = LongDeque(1)
        assertFailsWith<NoSuchElementException> { d.removeFirst() }
        assertFailsWith<NoSuchElementException> { d.removeLast() }
    }

    @Test
    fun removeFirstLastOrNull() {
        val d = LongDeque(2)
        assertNull(d.removeFirstOrNull())
        assertNull(d.removeLastOrNull())
        d.addLast(1L)
        assertEquals(1L, d.removeFirstOrNull())
        d.addLast(2L)
        assertEquals(2L, d.removeLastOrNull())
    }

    @Test
    fun clearResets() {
        val d = LongDeque(4)
        repeat(5) { d.addLast(it.toLong()) }
        d.clear()
        assertTrue(d.isEmpty())
        d.addLast(10L)
        assertEquals(listOf(10L), d.toList())
    }

    @Test
    fun addIndexShiftBackContiguous() {
        val d = LongDeque(8)
        repeat(5) { d.addLast(it.toLong()) }
        d.add(1, 99L)
        assertEquals(listOf(0L,99L,1L,2L,3L,4L), d.toList())
    }

    @Test
    fun addIndexShiftForwardContiguous() {
        val d = LongDeque(8)
        repeat(6) { d.addLast(it.toLong()) }
        d.add(5, 77L)
        assertEquals(listOf(0L,1L,2L,3L,4L,77L,5L), d.toList())
    }

    @Test
    fun removeAtShiftBackContiguous() {
        val d = LongDeque(8)
        repeat(6) { d.addLast(it.toLong()) }
        val removed = d.removeAt(1)
        assertEquals(1L, removed)
        assertEquals(listOf(0L,2L,3L,4L,5L), d.toList())
    }

    @Test
    fun removeAtShiftForwardContiguous() {
        val d = LongDeque(8)
        repeat(7) { d.addLast(it.toLong()) }
        val removed = d.removeAt(5)
        assertEquals(5L, removed)
        assertEquals(listOf(0L,1L,2L,3L,4L,6L), d.toList())
    }

    private fun buildDeque(startIndex: Int, capacity: Int, size: Int): LongDeque {
        require(capacity >= 0)
        require(size >= 0 && size <= capacity)
        val d = LongDeque(capacity)
        if (capacity == 0) return d
        require(startIndex in 0 until capacity)
        d.head = startIndex
        d._size = size
        for (i in 0 until size) {
            d.data[(startIndex + i) % capacity] = (i + 1).toLong()
        }
        return d
    }

    @Test
    fun addIndexShiftBackWrapped() {
        val d = buildDeque(3, 5, 4)
        d.add(1, 99L)
        assertEquals(listOf(1L,99L,2L,3L,4L), d.toList())
    }

    @Test
    fun addIndexShiftForwardWrapped() {
        val d = buildDeque(3, 5, 4)
        d.add(3, 77L)
        assertEquals(listOf(1L,2L,3L,77L,4L), d.toList())
    }

    @Test
    fun removeAtShiftForwardWrapped() {
        val d = buildDeque(3, 5, 4)
        val removed = d.removeAt(1)
        assertEquals(2L, removed)
        assertEquals(listOf(1L,3L,4L), d.toList())
    }

    @Test
    fun removeAtShiftBackWrapped() {
        val d = buildDeque(3, 5, 4)
        val removed = d.removeAt(2)
        assertEquals(3L, removed)
        assertEquals(listOf(1L,2L,4L), d.toList())
    }

    @Test
    fun removeAtShiftBackContiguousTailZero() {
        val d = buildDeque(2, 5, 3)
        val removed = d.removeAt(1)
        assertEquals(2L, removed)
        assertEquals(listOf(1L,3L), d.toList())
    }

    @Test
    fun ensureCapacityFromWrappedMaintainsOrder() {
        val d = buildDeque(3, 5, 4)
        d.addLast(5L)
        d.addLast(6L)
        d.addLast(7L)
        assertEquals(listOf(1L,2L,3L,4L,5L,6L,7L), d.toList())
    }

    @Test
    fun forEachVariantsContiguous() {
        val d = LongDeque(10)
        repeat(5) { d.addLast(it.toLong()) }
        val forward = mutableListOf<Long>()
        val reverse = mutableListOf<Long>()
        val forwardIndexed = mutableListOf<Pair<Int,Long>>()
        val reverseIndexed = mutableListOf<Pair<Int,Long>>()
        d.forEach { forward += it }
        d.forEachReversed { reverse += it }
        d.forEachIndexed { idx, v -> forwardIndexed += idx to v }
        d.forEachReversedIndexed { idx, v -> reverseIndexed += idx to v }
        assertEquals(listOf(0L,1L,2L,3L,4L), forward)
        assertEquals(listOf(4L,3L,2L,1L,0L), reverse)
        assertEquals(listOf(0 to 0L,1 to 1L,2 to 2L,3 to 3L,4 to 4L), forwardIndexed)
        assertEquals(listOf(4 to 4L,3 to 3L,2 to 2L,1 to 1L,0 to 0L), reverseIndexed)
    }

    @Test
    fun forEachVariantsWrapped() {
        val d = buildDeque(3, 5, 4)
        val f = mutableListOf<Long>()
        val r = mutableListOf<Long>()
        d.forEach { f += it }
        d.forEachReversed { r += it }
        assertEquals(listOf(1L,2L,3L,4L), f)
        assertEquals(listOf(4L,3L,2L,1L), r)
    }

    @Test
    fun forEachIndexedVariantsWrapped() {
        val d = buildDeque(3, 5, 4)
        val forwardIndexed = mutableListOf<Pair<Int,Long>>()
        val reverseIndexed = mutableListOf<Pair<Int,Long>>()
        d.forEachIndexed { idx, v -> forwardIndexed += idx to v }
        d.forEachReversedIndexed { idx, v -> reverseIndexed += idx to v }
        assertEquals(listOf(0 to 1L,1 to 2L,2 to 3L,3 to 4L), forwardIndexed)
        assertEquals(listOf(3 to 4L,2 to 3L,1 to 2L,0 to 1L), reverseIndexed)
    }

    @Test
    fun setOnWrappedReturnsOld() {
        val d = buildDeque(3, 5, 4)
        val old = d.set(2, 99L)
        assertEquals(3L, old)
        assertEquals(listOf(1L,2L,99L,4L), d.toList())
    }

    @Test
    fun addIndexBoundariesWrapped() {
        val d = buildDeque(3, 5, 4)
        d.add(0, 77L)
        assertEquals(listOf(77L,1L,2L,3L,4L), d.toList())
        d.add(d.size, 88L)
        assertEquals(listOf(77L,1L,2L,3L,4L,88L), d.toList())
    }

    @Test
    fun internalShape_3_5_2_Ops() {
        val d = buildDeque(3, 5, 2)
        assertEquals(listOf(1L,2L), d.toList())
        val f = mutableListOf<Long>()
        val r = mutableListOf<Long>()
        d.forEach { f += it }
        d.forEachReversed { r += it }
        assertEquals(listOf(1L,2L), f)
        assertEquals(listOf(2L,1L), r)
        d.add(1, 99L)
        assertEquals(listOf(1L,99L,2L), d.toList())
        assertEquals(99L, d.removeAt(1))
        assertEquals(listOf(1L,2L), d.toList())
        d.addLast(3L)
        d.addLast(4L)
        d.addLast(5L)
        d.addLast(6L)
        assertEquals(listOf(1L,2L,3L,4L,5L,6L), d.toList())
    }

    @Test
    fun internalShape_1_5_3_Ops() {
        val d = buildDeque(1, 5, 3)
        assertEquals(listOf(1L,2L,3L), d.toList())
        d.add(2, 77L)
        assertEquals(listOf(1L,2L,77L,3L), d.toList())
        assertEquals(2L, d.removeAt(1))
        assertEquals(listOf(1L,77L,3L), d.toList())
        d.addLast(4L)
        d.addLast(5L)
        d.addLast(6L)
        assertEquals(listOf(1L,77L,3L,4L,5L,6L), d.toList())
    }

    @Test
    fun containsAndIndexOf() {
        val d = LongDeque(4)
        d.addLast(1L); d.addLast(2L); d.addLast(1L); d.addLast(3L)
        assertTrue(1L in d)
        assertTrue(d.contains(2L))
        assertFalse(d.contains(9L))
        assertEquals(0, d.indexOf(1L))
        assertEquals(2, d.lastIndexOf(1L))
        assertEquals(-1, d.indexOf(9L))
    }

    @Test
    fun removeAtFirstAndLastShortcuts() {
        val d = LongDeque(5)
        d.addLast(1L); d.addLast(2L); d.addLast(3L)
        assertEquals(1L, d.removeAt(0))
        assertEquals(3L, d.removeAt(d.lastIndex))
        assertEquals(listOf(2L), d.toList())
    }

    @Test
    fun asMutableListViewReflectsChanges() {
        val d = LongDeque(2)
        d.addLast(1L); d.addLast(2L)

        val view = d.asMutableList()
        view.add(2, 3L)
        view.add(0, 0L)
        assertEquals(listOf(0L,1L,2L,3L), d.toList())

        val old = view.set(1, 9L)
        assertEquals(1L, old)
        assertEquals(listOf(0L,9L,2L,3L), d.toList())

        assertEquals(9L, view.removeAt(1))
        assertEquals(listOf(0L,2L,3L), d.toList())
    }

    @Test
    fun firstLastOrNull() {
        val d = LongDeque(2)
        assertNull(d.firstOrNull())
        assertNull(d.lastOrNull())
        d.addLast(5L)
        assertEquals(5L, d.firstOrNull())
        assertEquals(5L, d.lastOrNull())
        d.addFirst(4L)
        assertEquals(4L, d.firstOrNull())
        assertEquals(5L, d.lastOrNull())
    }

    @Test
    fun sizeAndLastIndex() {
        val d = LongDeque(1)
        assertEquals(-1, d.lastIndex)
        d.addLast(1L)
        assertEquals(0, d.lastIndex)
        d.addLast(2L)
        assertEquals(1, d.lastIndex)
        d.removeFirst()
        assertEquals(0, d.lastIndex)
    }

    @Test
    fun push() {
        val d = LongDeque(2)
        d.push(1L)
        d.push(2L)
        assertEquals(listOf(2L,1L), d.toList())
        assertEquals(2L, d.first())
    }

    @Test
    fun pop() {
        val d = LongDeque(3)
        d.addLast(1L); d.addLast(2L); d.addLast(3L)
        assertEquals(1L, d.pop())
        assertEquals(listOf(2L,3L), d.toList())
    }

    @Test
    fun enqueue() {
        val d = LongDeque(1)
        d.enqueue(1L)
        d.enqueue(2L)
        d.enqueue(3L)
        assertEquals(listOf(1L,2L,3L), d.toList())
        assertEquals(3L, d.last())
    }

    @Test
    fun dequeue() {
        val d = LongDeque(2)
        d.addFirst(2L)
        d.addFirst(1L)
        assertEquals(1L, d.dequeue())
        assertEquals(listOf(2L), d.toList())
    }

    @Test
    fun asListReflectsChanges() {
        val d = LongDeque(2)
        d.addLast(1L)
        d.addLast(2L)
        val list = d.asList()
        assertEquals(listOf(1L, 2L), list)

        d.addFirst(0L)
        assertEquals(listOf(0L, 1L, 2L), list)

        d.removeLast()
        assertEquals(listOf(0L, 1L), list)
    }
}


