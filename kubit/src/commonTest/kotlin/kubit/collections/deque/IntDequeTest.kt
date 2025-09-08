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

class IntDequeTest {

    private fun IntDeque.toList(): List<Int> {
        val result = mutableListOf<Int>()
        forEach { result += it }
        return result
    }

    @Test
    fun constructorNegativeCapacity() {
        assertFailsWith<IllegalArgumentException> { IntDeque(-1) }
    }

    @Test
    fun emptyState() {
        val d = IntDeque(0)
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
        val d = IntDeque(1)
        for (i in 0 until 10) d.addLast(i.toInt())
        assertEquals((0 until 10).map { it.toInt() }, d.toList())
        assertEquals(10, d.size)
        assertEquals(0, d.first())
        assertEquals(9, d.last())
    }

    @Test
    fun addFirst() {
        val d = IntDeque(2)
        for (i in 0 until 5) d.addFirst(i.toInt())
        assertEquals(listOf(4,3,2,1,0), d.toList())
        assertEquals(5, d.size)
        assertEquals(4, d.first())
        assertEquals(0, d.last())
    }

    @Test
    fun addAlias() {
        val d = IntDeque(2)
        d.add(1)
        d.add(2)
        assertEquals(listOf(1,2), d.toList())
    }

    @Test
    fun setReturnsOld() {
        val d = IntDeque(4)
        d.addLast(1); d.addLast(2); d.addLast(3)
        val old = d.set(1, 9)
        assertEquals(2, old)
        assertEquals(listOf(1,9,3), d.toList())
    }

    @Test
    fun getSetOutOfBounds() {
        val d = IntDeque(1)
        d.addLast(1)
        assertFailsWith<IndexOutOfBoundsException> { d[1] }
        assertFailsWith<IndexOutOfBoundsException> { d[-1] }
        assertFailsWith<IndexOutOfBoundsException> { d.set(1, 2) }
        assertFailsWith<IndexOutOfBoundsException> { d.set(-1, 2) }
    }

    @Test
    fun addIndexOutOfBounds() {
        val d = IntDeque(2)
        assertFailsWith<IndexOutOfBoundsException> { d.add(-1, 1) }
        assertFailsWith<IndexOutOfBoundsException> { d.add(1, 1) }
        d.addLast(5)
        assertFailsWith<IndexOutOfBoundsException> { d.add(3, 2) }
    }

    @Test
    fun removeIndexOutOfBounds() {
        val d = IntDeque(2)
        assertFailsWith<IndexOutOfBoundsException> { d.removeAt(0) }
        d.addLast(1)
        assertFailsWith<IndexOutOfBoundsException> { d.removeAt(1) }
        assertFailsWith<IndexOutOfBoundsException> { d.removeAt(-1) }
    }

    @Test
    fun removeFirstLast() {
        val d = IntDeque(3)
        d.addLast(1); d.addLast(2); d.addLast(3)
        assertEquals(1, d.removeFirst())
        assertEquals(3, d.removeLast())
        assertEquals(listOf(2), d.toList())
        assertEquals(1, d.size)
    }

    @Test
    fun removeFirstLastEmptyExceptions() {
        val d = IntDeque(1)
        assertFailsWith<NoSuchElementException> { d.removeFirst() }
        assertFailsWith<NoSuchElementException> { d.removeLast() }
    }

    @Test
    fun removeFirstLastOrNull() {
        val d = IntDeque(2)
        assertNull(d.removeFirstOrNull())
        assertNull(d.removeLastOrNull())
        d.addLast(1)
        assertEquals(1, d.removeFirstOrNull())
        d.addLast(2)
        assertEquals(2, d.removeLastOrNull())
    }

    @Test
    fun clearResets() {
        val d = IntDeque(4)
        repeat(5) { d.addLast(it.toInt()) }
        d.clear()
        assertTrue(d.isEmpty())
        d.addLast(10)
        assertEquals(listOf(10), d.toList())
    }

    @Test
    fun addIndexShiftBackContiguous() {
        val d = IntDeque(8)
        repeat(5) { d.addLast(it.toInt()) }
        d.add(1, 99)
        assertEquals(listOf(0,99,1,2,3,4), d.toList())
    }

    @Test
    fun addIndexShiftForwardContiguous() {
        val d = IntDeque(8)
        repeat(6) { d.addLast(it.toInt()) }
        d.add(5, 77)
        assertEquals(listOf(0,1,2,3,4,77,5), d.toList())
    }

    @Test
    fun removeAtShiftBackContiguous() {
        val d = IntDeque(8)
        repeat(6) { d.addLast(it.toInt()) }
        val removed = d.removeAt(1)
        assertEquals(1, removed)
        assertEquals(listOf(0,2,3,4,5), d.toList())
    }

    @Test
    fun removeAtShiftForwardContiguous() {
        val d = IntDeque(8)
        repeat(7) { d.addLast(it.toInt()) }
        val removed = d.removeAt(5)
        assertEquals(5, removed)
        assertEquals(listOf(0,1,2,3,4,6), d.toList())
    }

    private fun buildDeque(startIndex: Int, capacity: Int, size: Int): IntDeque {
        require(capacity >= 0)
        require(size >= 0 && size <= capacity)
        val d = IntDeque(capacity)
        if (capacity == 0) return d
        require(startIndex in 0 until capacity)
        d.head = startIndex
        d._size = size
        for (i in 0 until size) {
            d.data[(startIndex + i) % capacity] = (i + 1).toInt()
        }
        return d
    }

    @Test
    fun addIndexShiftBackWrapped() {
        val d = buildDeque(3, 5, 4)
        d.add(1, 99)
        assertEquals(listOf(1,99,2,3,4), d.toList())
    }

    @Test
    fun addIndexShiftForwardWrapped() {
        val d = buildDeque(3, 5, 4)
        d.add(3, 77)
        assertEquals(listOf(1,2,3,77,4), d.toList())
    }

    @Test
    fun removeAtShiftForwardWrapped() {
        val d = buildDeque(3, 5, 4)
        val removed = d.removeAt(1)
        assertEquals(2, removed)
        assertEquals(listOf(1,3,4), d.toList())
    }

    @Test
    fun removeAtShiftBackWrapped() {
        val d = buildDeque(3, 5, 4)
        val removed = d.removeAt(2)
        assertEquals(3, removed)
        assertEquals(listOf(1,2,4), d.toList())
    }

    @Test
    fun removeAtShiftBackContiguousTailZero() {
        val d = buildDeque(2, 5, 3)
        val removed = d.removeAt(1)
        assertEquals(2, removed)
        assertEquals(listOf(1,3), d.toList())
    }

    @Test
    fun ensureCapacityFromWrappedMaintainsOrder() {
        val d = buildDeque(3, 5, 4)
        d.addLast(5)
        d.addLast(6)
        d.addLast(7)
        assertEquals(listOf(1,2,3,4,5,6,7), d.toList())
    }

    @Test
    fun forEachVariantsContiguous() {
        val d = IntDeque(10)
        repeat(5) { d.addLast(it.toInt()) }
        val forward = mutableListOf<Int>()
        val reverse = mutableListOf<Int>()
        val forwardIndexed = mutableListOf<Pair<Int,Int>>()
        val reverseIndexed = mutableListOf<Pair<Int,Int>>()
        d.forEach { forward += it }
        d.forEachReversed { reverse += it }
        d.forEachIndexed { idx, v -> forwardIndexed += idx to v }
        d.forEachReversedIndexed { idx, v -> reverseIndexed += idx to v }
        assertEquals(listOf(0,1,2,3,4), forward)
        assertEquals(listOf(4,3,2,1,0), reverse)
        assertEquals(listOf(0 to 0,1 to 1,2 to 2,3 to 3,4 to 4), forwardIndexed)
        assertEquals(listOf(4 to 4,3 to 3,2 to 2,1 to 1,0 to 0), reverseIndexed)
    }

    @Test
    fun forEachVariantsWrapped() {
        val d = buildDeque(3, 5, 4)
        val f = mutableListOf<Int>()
        val r = mutableListOf<Int>()
        d.forEach { f += it }
        d.forEachReversed { r += it }
        assertEquals(listOf(1,2,3,4), f)
        assertEquals(listOf(4,3,2,1), r)
    }

    @Test
    fun forEachIndexedVariantsWrapped() {
        val d = buildDeque(3, 5, 4)
        val forwardIndexed = mutableListOf<Pair<Int,Int>>()
        val reverseIndexed = mutableListOf<Pair<Int,Int>>()
        d.forEachIndexed { idx, v -> forwardIndexed += idx to v }
        d.forEachReversedIndexed { idx, v -> reverseIndexed += idx to v }
        assertEquals(listOf(0 to 1,1 to 2,2 to 3,3 to 4), forwardIndexed)
        assertEquals(listOf(3 to 4,2 to 3,1 to 2,0 to 1), reverseIndexed)
    }

    @Test
    fun setOnWrappedReturnsOld() {
        val d = buildDeque(3, 5, 4)
        val old = d.set(2, 99)
        assertEquals(3, old)
        assertEquals(listOf(1,2,99,4), d.toList())
    }

    @Test
    fun addIndexBoundariesWrapped() {
        val d = buildDeque(3, 5, 4)
        d.add(0, 77)
        assertEquals(listOf(77,1,2,3,4), d.toList())
        d.add(d.size, 88)
        assertEquals(listOf(77,1,2,3,4,88), d.toList())
    }

    @Test
    fun internalShape_3_5_2_Ops() {
        val d = buildDeque(3, 5, 2)
        assertEquals(listOf(1,2), d.toList())
        val f = mutableListOf<Int>()
        val r = mutableListOf<Int>()
        d.forEach { f += it }
        d.forEachReversed { r += it }
        assertEquals(listOf(1,2), f)
        assertEquals(listOf(2,1), r)
        d.add(1, 99)
        assertEquals(listOf(1,99,2), d.toList())
        assertEquals(99, d.removeAt(1))
        assertEquals(listOf(1,2), d.toList())
        d.addLast(3)
        d.addLast(4)
        d.addLast(5)
        d.addLast(6)
        assertEquals(listOf(1,2,3,4,5,6), d.toList())
    }

    @Test
    fun internalShape_1_5_3_Ops() {
        val d = buildDeque(1, 5, 3)
        assertEquals(listOf(1,2,3), d.toList())
        d.add(2, 77)
        assertEquals(listOf(1,2,77,3), d.toList())
        assertEquals(2, d.removeAt(1))
        assertEquals(listOf(1,77,3), d.toList())
        d.addLast(4)
        d.addLast(5)
        d.addLast(6)
        assertEquals(listOf(1,77,3,4,5,6), d.toList())
    }

    @Test
    fun containsAndIndexOf() {
        val d = IntDeque(4)
        d.addLast(1); d.addLast(2); d.addLast(1); d.addLast(3)
        assertTrue(1 in d)
        assertTrue(d.contains(2))
        assertFalse(d.contains(9))
        assertEquals(0, d.indexOf(1))
        assertEquals(2, d.lastIndexOf(1))
        assertEquals(-1, d.indexOf(9))
    }

    @Test
    fun removeAtFirstAndLastShortcuts() {
        val d = IntDeque(5)
        d.addLast(1); d.addLast(2); d.addLast(3)
        assertEquals(1, d.removeAt(0))
        assertEquals(3, d.removeAt(d.lastIndex))
        assertEquals(listOf(2), d.toList())
    }

    @Test
    fun asMutableListViewReflectsChanges() {
        val d = IntDeque(2)
        d.addLast(1); d.addLast(2)

        val view = d.asMutableList()
        view.add(2, 3)
        view.add(0, 0)
        assertEquals(listOf(0,1,2,3), d.toList())

        val old = view.set(1, 9)
        assertEquals(1, old)
        assertEquals(listOf(0,9,2,3), d.toList())

        assertEquals(9, view.removeAt(1))
        assertEquals(listOf(0,2,3), d.toList())
    }

    @Test
    fun firstLastOrNull() {
        val d = IntDeque(2)
        assertNull(d.firstOrNull())
        assertNull(d.lastOrNull())
        d.addLast(5)
        assertEquals(5, d.firstOrNull())
        assertEquals(5, d.lastOrNull())
        d.addFirst(4)
        assertEquals(4, d.firstOrNull())
        assertEquals(5, d.lastOrNull())
    }

    @Test
    fun sizeAndLastIndex() {
        val d = IntDeque(1)
        assertEquals(-1, d.lastIndex)
        d.addLast(1)
        assertEquals(0, d.lastIndex)
        d.addLast(2)
        assertEquals(1, d.lastIndex)
        d.removeFirst()
        assertEquals(0, d.lastIndex)
    }

    @Test
    fun push() {
        val d = IntDeque(2)
        d.push(1)
        d.push(2)
        assertEquals(listOf(2,1), d.toList())
        assertEquals(2, d.first())
    }

    @Test
    fun pop() {
        val d = IntDeque(3)
        d.addLast(1); d.addLast(2); d.addLast(3)
        assertEquals(1, d.pop())
        assertEquals(listOf(2,3), d.toList())
    }

    @Test
    fun enqueue() {
        val d = IntDeque(1)
        d.enqueue(1)
        d.enqueue(2)
        d.enqueue(3)
        assertEquals(listOf(1,2,3), d.toList())
        assertEquals(3, d.last())
    }

    @Test
    fun dequeue() {
        val d = IntDeque(2)
        d.addFirst(2)
        d.addFirst(1)
        assertEquals(1, d.dequeue())
        assertEquals(listOf(2), d.toList())
    }

    @Test
    fun asListReflectsChanges() {
        val d = IntDeque(2)
        d.addLast(1)
        d.addLast(2)
        val list = d.asList()
        assertEquals(listOf(1, 2), list)

        d.addFirst(0)
        assertEquals(listOf(0, 1, 2), list)

        d.removeLast()
        assertEquals(listOf(0, 1), list)
    }
}


