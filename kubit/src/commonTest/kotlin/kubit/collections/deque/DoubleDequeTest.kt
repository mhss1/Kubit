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

class DoubleDequeTest {

    private fun DoubleDeque.toList(): List<Double> {
        val result = mutableListOf<Double>()
        forEach { result += it }
        return result
    }

    @Test
    fun constructorNegativeCapacity() {
        assertFailsWith<IllegalArgumentException> { DoubleDeque(-1) }
    }

    @Test
    fun emptyState() {
        val d = DoubleDeque(0)
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
        val d = DoubleDeque(1)
        for (i in 0 until 10) d.addLast(i.toDouble())
        assertEquals((0 until 10).map { it.toDouble() }, d.toList())
        assertEquals(10, d.size)
        assertEquals(0.0, d.first())
        assertEquals(9.0, d.last())
    }

    @Test
    fun addFirst() {
        val d = DoubleDeque(2)
        for (i in 0 until 5) d.addFirst(i.toDouble())
        assertEquals(listOf(4.0,3.0,2.0,1.0,0.0), d.toList())
        assertEquals(5, d.size)
        assertEquals(4.0, d.first())
        assertEquals(0.0, d.last())
    }

    @Test
    fun addAlias() {
        val d = DoubleDeque(2)
        d.add(1.0)
        d.add(2.0)
        assertEquals(listOf(1.0,2.0), d.toList())
    }

    @Test
    fun setReturnsOld() {
        val d = DoubleDeque(4)
        d.addLast(1.0); d.addLast(2.0); d.addLast(3.0)
        val old = d.set(1, 9.0)
        assertEquals(2.0, old)
        assertEquals(listOf(1.0,9.0,3.0), d.toList())
    }

    @Test
    fun getSetOutOfBounds() {
        val d = DoubleDeque(1)
        d.addLast(1.0)
        assertFailsWith<IndexOutOfBoundsException> { d[1] }
        assertFailsWith<IndexOutOfBoundsException> { d[-1] }
        assertFailsWith<IndexOutOfBoundsException> { d.set(1, 2.0) }
        assertFailsWith<IndexOutOfBoundsException> { d.set(-1, 2.0) }
    }

    @Test
    fun addIndexOutOfBounds() {
        val d = DoubleDeque(2)
        assertFailsWith<IndexOutOfBoundsException> { d.add(-1, 1.0) }
        assertFailsWith<IndexOutOfBoundsException> { d.add(1, 1.0) }
        d.addLast(5.0)
        assertFailsWith<IndexOutOfBoundsException> { d.add(3, 2.0) }
    }

    @Test
    fun removeIndexOutOfBounds() {
        val d = DoubleDeque(2)
        assertFailsWith<IndexOutOfBoundsException> { d.removeAt(0) }
        d.addLast(1.0)
        assertFailsWith<IndexOutOfBoundsException> { d.removeAt(1) }
        assertFailsWith<IndexOutOfBoundsException> { d.removeAt(-1) }
    }

    @Test
    fun removeFirstLast() {
        val d = DoubleDeque(3)
        d.addLast(1.0); d.addLast(2.0); d.addLast(3.0)
        assertEquals(1.0, d.removeFirst())
        assertEquals(3.0, d.removeLast())
        assertEquals(listOf(2.0), d.toList())
        assertEquals(1, d.size)
    }

    @Test
    fun removeFirstLastEmptyExceptions() {
        val d = DoubleDeque(1)
        assertFailsWith<NoSuchElementException> { d.removeFirst() }
        assertFailsWith<NoSuchElementException> { d.removeLast() }
    }

    @Test
    fun removeFirstLastOrNull() {
        val d = DoubleDeque(2)
        assertNull(d.removeFirstOrNull())
        assertNull(d.removeLastOrNull())
        d.addLast(1.0)
        assertEquals(1.0, d.removeFirstOrNull())
        d.addLast(2.0)
        assertEquals(2.0, d.removeLastOrNull())
    }

    @Test
    fun clearResets() {
        val d = DoubleDeque(4)
        repeat(5) { d.addLast(it.toDouble()) }
        d.clear()
        assertTrue(d.isEmpty())
        d.addLast(10.0)
        assertEquals(listOf(10.0), d.toList())
    }

    @Test
    fun addIndexShiftBackContiguous() {
        val d = DoubleDeque(8)
        repeat(5) { d.addLast(it.toDouble()) }
        d.add(1, 99.0)
        assertEquals(listOf(0.0,99.0,1.0,2.0,3.0,4.0), d.toList())
    }

    @Test
    fun addIndexShiftForwardContiguous() {
        val d = DoubleDeque(8)
        repeat(6) { d.addLast(it.toDouble()) }
        d.add(5, 77.0)
        assertEquals(listOf(0.0,1.0,2.0,3.0,4.0,77.0,5.0), d.toList())
    }

    @Test
    fun removeAtShiftBackContiguous() {
        val d = DoubleDeque(8)
        repeat(6) { d.addLast(it.toDouble()) }
        val removed = d.removeAt(1)
        assertEquals(1.0, removed)
        assertEquals(listOf(0.0,2.0,3.0,4.0,5.0), d.toList())
    }

    @Test
    fun removeAtShiftForwardContiguous() {
        val d = DoubleDeque(8)
        repeat(7) { d.addLast(it.toDouble()) }
        val removed = d.removeAt(5)
        assertEquals(5.0, removed)
        assertEquals(listOf(0.0,1.0,2.0,3.0,4.0,6.0), d.toList())
    }

    private fun buildDeque(startIndex: Int, capacity: Int, size: Int): DoubleDeque {
        require(capacity >= 0)
        require(size >= 0 && size <= capacity)
        val d = DoubleDeque(capacity)
        if (capacity == 0) return d
        require(startIndex in 0 until capacity)
        d.head = startIndex
        d._size = size
        for (i in 0 until size) {
            d.data[(startIndex + i) % capacity] = (i + 1).toDouble()
        }
        return d
    }

    @Test
    fun addIndexShiftBackWrapped() {
        val d = buildDeque(3, 5, 4)
        d.add(1, 99.0)
        assertEquals(listOf(1.0,99.0,2.0,3.0,4.0), d.toList())
    }

    @Test
    fun addIndexShiftForwardWrapped() {
        val d = buildDeque(3, 5, 4)
        d.add(3, 77.0)
        assertEquals(listOf(1.0,2.0,3.0,77.0,4.0), d.toList())
    }

    @Test
    fun removeAtShiftForwardWrapped() {
        val d = buildDeque(3, 5, 4)
        val removed = d.removeAt(1)
        assertEquals(2.0, removed)
        assertEquals(listOf(1.0,3.0,4.0), d.toList())
    }

    @Test
    fun removeAtShiftBackWrapped() {
        val d = buildDeque(3, 5, 4)
        val removed = d.removeAt(2)
        assertEquals(3.0, removed)
        assertEquals(listOf(1.0,2.0,4.0), d.toList())
    }

    @Test
    fun removeAtShiftBackContiguousTailZero() {
        val d = buildDeque(2, 5, 3)
        val removed = d.removeAt(1)
        assertEquals(2.0, removed)
        assertEquals(listOf(1.0,3.0), d.toList())
    }

    @Test
    fun ensureCapacityFromWrappedMaintainsOrder() {
        val d = buildDeque(3, 5, 4)
        d.addLast(5.0)
        d.addLast(6.0)
        d.addLast(7.0)
        assertEquals(listOf(1.0,2.0,3.0,4.0,5.0,6.0,7.0), d.toList())
    }

    @Test
    fun forEachVariantsContiguous() {
        val d = DoubleDeque(10)
        repeat(5) { d.addLast(it.toDouble()) }
        val forward = mutableListOf<Double>()
        val reverse = mutableListOf<Double>()
        val forwardIndexed = mutableListOf<Pair<Int,Double>>()
        val reverseIndexed = mutableListOf<Pair<Int,Double>>()
        d.forEach { forward += it }
        d.forEachReversed { reverse += it }
        d.forEachIndexed { idx, v -> forwardIndexed += idx to v }
        d.forEachReversedIndexed { idx, v -> reverseIndexed += idx to v }
        assertEquals(listOf(0.0,1.0,2.0,3.0,4.0), forward)
        assertEquals(listOf(4.0,3.0,2.0,1.0,0.0), reverse)
        assertEquals(listOf(0 to 0.0,1 to 1.0,2 to 2.0,3 to 3.0,4 to 4.0), forwardIndexed)
        assertEquals(listOf(4 to 4.0,3 to 3.0,2 to 2.0,1 to 1.0,0 to 0.0), reverseIndexed)
    }

    @Test
    fun forEachVariantsWrapped() {
        val d = buildDeque(3, 5, 4)
        val f = mutableListOf<Double>()
        val r = mutableListOf<Double>()
        d.forEach { f += it }
        d.forEachReversed { r += it }
        assertEquals(listOf(1.0,2.0,3.0,4.0), f)
        assertEquals(listOf(4.0,3.0,2.0,1.0), r)
    }

    @Test
    fun forEachIndexedVariantsWrapped() {
        val d = buildDeque(3, 5, 4)
        val forwardIndexed = mutableListOf<Pair<Int,Double>>()
        val reverseIndexed = mutableListOf<Pair<Int,Double>>()
        d.forEachIndexed { idx, v -> forwardIndexed += idx to v }
        d.forEachReversedIndexed { idx, v -> reverseIndexed += idx to v }
        assertEquals(listOf(0 to 1.0,1 to 2.0,2 to 3.0,3 to 4.0), forwardIndexed)
        assertEquals(listOf(3 to 4.0,2 to 3.0,1 to 2.0,0 to 1.0), reverseIndexed)
    }

    @Test
    fun setOnWrappedReturnsOld() {
        val d = buildDeque(3, 5, 4)
        val old = d.set(2, 99.0)
        assertEquals(3.0, old)
        assertEquals(listOf(1.0,2.0,99.0,4.0), d.toList())
    }

    @Test
    fun addIndexBoundariesWrapped() {
        val d = buildDeque(3, 5, 4)
        d.add(0, 77.0)
        assertEquals(listOf(77.0,1.0,2.0,3.0,4.0), d.toList())
        d.add(d.size, 88.0)
        assertEquals(listOf(77.0,1.0,2.0,3.0,4.0,88.0), d.toList())
    }

    @Test
    fun internalShape_3_5_2_Ops() {
        val d = buildDeque(3, 5, 2)
        assertEquals(listOf(1.0,2.0), d.toList())
        val f = mutableListOf<Double>()
        val r = mutableListOf<Double>()
        d.forEach { f += it }
        d.forEachReversed { r += it }
        assertEquals(listOf(1.0,2.0), f)
        assertEquals(listOf(2.0,1.0), r)
        d.add(1, 99.0)
        assertEquals(listOf(1.0,99.0,2.0), d.toList())
        assertEquals(99.0, d.removeAt(1))
        assertEquals(listOf(1.0,2.0), d.toList())
        d.addLast(3.0)
        d.addLast(4.0)
        d.addLast(5.0)
        d.addLast(6.0)
        assertEquals(listOf(1.0,2.0,3.0,4.0,5.0,6.0), d.toList())
    }

    @Test
    fun internalShape_1_5_3_Ops() {
        val d = buildDeque(1, 5, 3)
        assertEquals(listOf(1.0,2.0,3.0), d.toList())
        d.add(2, 77.0)
        assertEquals(listOf(1.0,2.0,77.0,3.0), d.toList())
        assertEquals(2.0, d.removeAt(1))
        assertEquals(listOf(1.0,77.0,3.0), d.toList())
        d.addLast(4.0)
        d.addLast(5.0)
        d.addLast(6.0)
        assertEquals(listOf(1.0,77.0,3.0,4.0,5.0,6.0), d.toList())
    }

    @Test
    fun containsAndIndexOf() {
        val d = DoubleDeque(4)
        d.addLast(1.0); d.addLast(2.0); d.addLast(1.0); d.addLast(3.0)
        assertTrue(1.0 in d)
        assertTrue(d.contains(2.0))
        assertFalse(d.contains(9.0))
        assertEquals(0, d.indexOf(1.0))
        assertEquals(2, d.lastIndexOf(1.0))
        assertEquals(-1, d.indexOf(9.0))
    }

    @Test
    fun removeAtFirstAndLastShortcuts() {
        val d = DoubleDeque(5)
        d.addLast(1.0); d.addLast(2.0); d.addLast(3.0)
        assertEquals(1.0, d.removeAt(0))
        assertEquals(3.0, d.removeAt(d.lastIndex))
        assertEquals(listOf(2.0), d.toList())
    }

    @Test
    fun asMutableListViewReflectsChanges() {
        val d = DoubleDeque(2)
        d.addLast(1.0); d.addLast(2.0)

        val view = d.asMutableList()
        view.add(2, 3.0)
        view.add(0, 0.0)
        assertEquals(listOf(0.0,1.0,2.0,3.0), d.toList())

        val old = view.set(1, 9.0)
        assertEquals(1.0, old)
        assertEquals(listOf(0.0,9.0,2.0,3.0), d.toList())

        assertEquals(9.0, view.removeAt(1))
        assertEquals(listOf(0.0,2.0,3.0), d.toList())
    }

    @Test
    fun firstLastOrNull() {
        val d = DoubleDeque(2)
        assertNull(d.firstOrNull())
        assertNull(d.lastOrNull())
        d.addLast(5.0)
        assertEquals(5.0, d.firstOrNull())
        assertEquals(5.0, d.lastOrNull())
        d.addFirst(4.0)
        assertEquals(4.0, d.firstOrNull())
        assertEquals(5.0, d.lastOrNull())
    }

    @Test
    fun sizeAndLastIndex() {
        val d = DoubleDeque(1)
        assertEquals(-1, d.lastIndex)
        d.addLast(1.0)
        assertEquals(0, d.lastIndex)
        d.addLast(2.0)
        assertEquals(1, d.lastIndex)
        d.removeFirst()
        assertEquals(0, d.lastIndex)
    }

    @Test
    fun push() {
        val d = DoubleDeque(2)
        d.push(1.0)
        d.push(2.0)
        assertEquals(listOf(2.0,1.0), d.toList())
        assertEquals(2.0, d.first())
    }

    @Test
    fun pop() {
        val d = DoubleDeque(3)
        d.addLast(1.0); d.addLast(2.0); d.addLast(3.0)
        assertEquals(1.0, d.pop())
        assertEquals(listOf(2.0,3.0), d.toList())
    }

    @Test
    fun enqueue() {
        val d = DoubleDeque(1)
        d.enqueue(1.0)
        d.enqueue(2.0)
        d.enqueue(3.0)
        assertEquals(listOf(1.0,2.0,3.0), d.toList())
        assertEquals(3.0, d.last())
    }

    @Test
    fun dequeue() {
        val d = DoubleDeque(2)
        d.addFirst(2.0)
        d.addFirst(1.0)
        assertEquals(1.0, d.dequeue())
        assertEquals(listOf(2.0), d.toList())
    }

    @Test
    fun asListReflectsChanges() {
        val d = DoubleDeque(2)
        d.addLast(1.0)
        d.addLast(2.0)
        val list = d.asList()
        assertEquals(listOf(1.0, 2.0), list)

        d.addFirst(0.0)
        assertEquals(listOf(0.0, 1.0, 2.0), list)

        d.removeLast()
        assertEquals(listOf(0.0, 1.0), list)
    }
}


