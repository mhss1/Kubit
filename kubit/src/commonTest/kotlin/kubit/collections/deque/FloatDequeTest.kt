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

class FloatDequeTest {

    private fun FloatDeque.toList(): List<Float> {
        val result = mutableListOf<Float>()
        forEach { result += it }
        return result
    }

    @Test
    fun constructorNegativeCapacity() {
        assertFailsWith<IllegalArgumentException> { FloatDeque(-1) }
    }

    @Test
    fun emptyState() {
        val d = FloatDeque(0)
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
        val d = FloatDeque(1)
        for (i in 0 until 10) d.addLast(i.toFloat())
        assertEquals((0 until 10).map { it.toFloat() }, d.toList())
        assertEquals(10, d.size)
        assertEquals(0f, d.first())
        assertEquals(9f, d.last())
    }

    @Test
    fun addFirst() {
        val d = FloatDeque(2)
        for (i in 0 until 5) d.addFirst(i.toFloat())
        assertEquals(listOf(4f,3f,2f,1f,0f), d.toList())
        assertEquals(5, d.size)
        assertEquals(4f, d.first())
        assertEquals(0f, d.last())
    }

    @Test
    fun addAlias() {
        val d = FloatDeque(2)
        d.add(1f)
        d.add(2f)
        assertEquals(listOf(1f,2f), d.toList())
    }

    @Test
    fun setReturnsOld() {
        val d = FloatDeque(4)
        d.addLast(1f); d.addLast(2f); d.addLast(3f)
        val old = d.set(1, 9f)
        assertEquals(2f, old)
        assertEquals(listOf(1f,9f,3f), d.toList())
    }

    @Test
    fun getSetOutOfBounds() {
        val d = FloatDeque(1)
        d.addLast(1f)
        assertFailsWith<IndexOutOfBoundsException> { d[1] }
        assertFailsWith<IndexOutOfBoundsException> { d[-1] }
        assertFailsWith<IndexOutOfBoundsException> { d.set(1, 2f) }
        assertFailsWith<IndexOutOfBoundsException> { d.set(-1, 2f) }
    }

    @Test
    fun addIndexOutOfBounds() {
        val d = FloatDeque(2)
        assertFailsWith<IndexOutOfBoundsException> { d.add(-1, 1f) }
        assertFailsWith<IndexOutOfBoundsException> { d.add(1, 1f) }
        d.addLast(5f)
        assertFailsWith<IndexOutOfBoundsException> { d.add(3, 2f) }
    }

    @Test
    fun removeIndexOutOfBounds() {
        val d = FloatDeque(2)
        assertFailsWith<IndexOutOfBoundsException> { d.removeAt(0) }
        d.addLast(1f)
        assertFailsWith<IndexOutOfBoundsException> { d.removeAt(1) }
        assertFailsWith<IndexOutOfBoundsException> { d.removeAt(-1) }
    }

    @Test
    fun removeFirstLast() {
        val d = FloatDeque(3)
        d.addLast(1f); d.addLast(2f); d.addLast(3f)
        assertEquals(1f, d.removeFirst())
        assertEquals(3f, d.removeLast())
        assertEquals(listOf(2f), d.toList())
        assertEquals(1, d.size)
    }

    @Test
    fun removeFirstLastEmptyExceptions() {
        val d = FloatDeque(1)
        assertFailsWith<NoSuchElementException> { d.removeFirst() }
        assertFailsWith<NoSuchElementException> { d.removeLast() }
    }

    @Test
    fun removeFirstLastOrNull() {
        val d = FloatDeque(2)
        assertNull(d.removeFirstOrNull())
        assertNull(d.removeLastOrNull())
        d.addLast(1f)
        assertEquals(1f, d.removeFirstOrNull())
        d.addLast(2f)
        assertEquals(2f, d.removeLastOrNull())
    }

    @Test
    fun clearResets() {
        val d = FloatDeque(4)
        repeat(5) { d.addLast(it.toFloat()) }
        d.clear()
        assertTrue(d.isEmpty())
        d.addLast(10f)
        assertEquals(listOf(10f), d.toList())
    }

    @Test
    fun addIndexShiftBackContiguous() {
        val d = FloatDeque(8)
        repeat(5) { d.addLast(it.toFloat()) }
        d.add(1, 99f)
        assertEquals(listOf(0f,99f,1f,2f,3f,4f), d.toList())
    }

    @Test
    fun addIndexShiftForwardContiguous() {
        val d = FloatDeque(8)
        repeat(6) { d.addLast(it.toFloat()) }
        d.add(5, 77f)
        assertEquals(listOf(0f,1f,2f,3f,4f,77f,5f), d.toList())
    }

    @Test
    fun removeAtShiftBackContiguous() {
        val d = FloatDeque(8)
        repeat(6) { d.addLast(it.toFloat()) }
        val removed = d.removeAt(1)
        assertEquals(1f, removed)
        assertEquals(listOf(0f,2f,3f,4f,5f), d.toList())
    }

    @Test
    fun removeAtShiftForwardContiguous() {
        val d = FloatDeque(8)
        repeat(7) { d.addLast(it.toFloat()) }
        val removed = d.removeAt(5)
        assertEquals(5f, removed)
        assertEquals(listOf(0f,1f,2f,3f,4f,6f), d.toList())
    }

    private fun buildDeque(startIndex: Int, capacity: Int, size: Int): FloatDeque {
        require(capacity >= 0)
        require(size >= 0 && size <= capacity)
        val d = FloatDeque(capacity)
        if (capacity == 0) return d
        require(startIndex in 0 until capacity)
        d.head = startIndex
        d._size = size
        for (i in 0 until size) {
            d.data[(startIndex + i) % capacity] = (i + 1).toFloat()
        }
        return d
    }

    @Test
    fun addIndexShiftBackWrapped() {
        val d = buildDeque(3, 5, 4)
        d.add(1, 99f)
        assertEquals(listOf(1f,99f,2f,3f,4f), d.toList())
    }

    @Test
    fun addIndexShiftForwardWrapped() {
        val d = buildDeque(3, 5, 4)
        d.add(3, 77f)
        assertEquals(listOf(1f,2f,3f,77f,4f), d.toList())
    }

    @Test
    fun removeAtShiftForwardWrapped() {
        val d = buildDeque(3, 5, 4)
        val removed = d.removeAt(1)
        assertEquals(2f, removed)
        assertEquals(listOf(1f,3f,4f), d.toList())
    }

    @Test
    fun removeAtShiftBackWrapped() {
        val d = buildDeque(3, 5, 4)
        val removed = d.removeAt(2)
        assertEquals(3f, removed)
        assertEquals(listOf(1f,2f,4f), d.toList())
    }

    @Test
    fun removeAtShiftBackContiguousTailZero() {
        val d = buildDeque(2, 5, 3)
        val removed = d.removeAt(1)
        assertEquals(2f, removed)
        assertEquals(listOf(1f,3f), d.toList())
    }

    @Test
    fun ensureCapacityFromWrappedMaintainsOrder() {
        val d = buildDeque(3, 5, 4)
        d.addLast(5f)
        d.addLast(6f)
        d.addLast(7f)
        assertEquals(listOf(1f,2f,3f,4f,5f,6f,7f), d.toList())
    }

    @Test
    fun forEachVariantsContiguous() {
        val d = FloatDeque(10)
        repeat(5) { d.addLast(it.toFloat()) }
        val forward = mutableListOf<Float>()
        val reverse = mutableListOf<Float>()
        val forwardIndexed = mutableListOf<Pair<Int,Float>>()
        val reverseIndexed = mutableListOf<Pair<Int,Float>>()
        d.forEach { forward += it }
        d.forEachReversed { reverse += it }
        d.forEachIndexed { idx, v -> forwardIndexed += idx to v }
        d.forEachReversedIndexed { idx, v -> reverseIndexed += idx to v }
        assertEquals(listOf(0f,1f,2f,3f,4f), forward)
        assertEquals(listOf(4f,3f,2f,1f,0f), reverse)
        assertEquals(listOf(0 to 0f,1 to 1f,2 to 2f,3 to 3f,4 to 4f), forwardIndexed)
        assertEquals(listOf(4 to 4f,3 to 3f,2 to 2f,1 to 1f,0 to 0f), reverseIndexed)
    }

    @Test
    fun forEachVariantsWrapped() {
        val d = buildDeque(3, 5, 4)
        val f = mutableListOf<Float>()
        val r = mutableListOf<Float>()
        d.forEach { f += it }
        d.forEachReversed { r += it }
        assertEquals(listOf(1f,2f,3f,4f), f)
        assertEquals(listOf(4f,3f,2f,1f), r)
    }

    @Test
    fun forEachIndexedVariantsWrapped() {
        val d = buildDeque(3, 5, 4)
        val forwardIndexed = mutableListOf<Pair<Int,Float>>()
        val reverseIndexed = mutableListOf<Pair<Int,Float>>()
        d.forEachIndexed { idx, v -> forwardIndexed += idx to v }
        d.forEachReversedIndexed { idx, v -> reverseIndexed += idx to v }
        assertEquals(listOf(0 to 1f,1 to 2f,2 to 3f,3 to 4f), forwardIndexed)
        assertEquals(listOf(3 to 4f,2 to 3f,1 to 2f,0 to 1f), reverseIndexed)
    }

    @Test
    fun setOnWrappedReturnsOld() {
        val d = buildDeque(3, 5, 4)
        val old = d.set(2, 99f)
        assertEquals(3f, old)
        assertEquals(listOf(1f,2f,99f,4f), d.toList())
    }

    @Test
    fun addIndexBoundariesWrapped() {
        val d = buildDeque(3, 5, 4)
        d.add(0, 77f)
        assertEquals(listOf(77f,1f,2f,3f,4f), d.toList())
        d.add(d.size, 88f)
        assertEquals(listOf(77f,1f,2f,3f,4f,88f), d.toList())
    }

    @Test
    fun internalShape_3_5_2_Ops() {
        val d = buildDeque(3, 5, 2)
        assertEquals(listOf(1f,2f), d.toList())
        val f = mutableListOf<Float>()
        val r = mutableListOf<Float>()
        d.forEach { f += it }
        d.forEachReversed { r += it }
        assertEquals(listOf(1f,2f), f)
        assertEquals(listOf(2f,1f), r)
        d.add(1, 99f)
        assertEquals(listOf(1f,99f,2f), d.toList())
        assertEquals(99f, d.removeAt(1))
        assertEquals(listOf(1f,2f), d.toList())
        d.addLast(3f)
        d.addLast(4f)
        d.addLast(5f)
        d.addLast(6f)
        assertEquals(listOf(1f,2f,3f,4f,5f,6f), d.toList())
    }

    @Test
    fun internalShape_1_5_3_Ops() {
        val d = buildDeque(1, 5, 3)
        assertEquals(listOf(1f,2f,3f), d.toList())
        d.add(2, 77f)
        assertEquals(listOf(1f,2f,77f,3f), d.toList())
        assertEquals(2f, d.removeAt(1))
        assertEquals(listOf(1f,77f,3f), d.toList())
        d.addLast(4f)
        d.addLast(5f)
        d.addLast(6f)
        assertEquals(listOf(1f,77f,3f,4f,5f,6f), d.toList())
    }

    @Test
    fun containsAndIndexOf() {
        val d = FloatDeque(4)
        d.addLast(1f); d.addLast(2f); d.addLast(1f); d.addLast(3f)
        assertTrue(1f in d)
        assertTrue(d.contains(2f))
        assertFalse(d.contains(9f))
        assertEquals(0, d.indexOf(1f))
        assertEquals(2, d.lastIndexOf(1f))
        assertEquals(-1, d.indexOf(9f))
    }

    @Test
    fun removeAtFirstAndLastShortcuts() {
        val d = FloatDeque(5)
        d.addLast(1f); d.addLast(2f); d.addLast(3f)
        assertEquals(1f, d.removeAt(0))
        assertEquals(3f, d.removeAt(d.lastIndex))
        assertEquals(listOf(2f), d.toList())
    }

    @Test
    fun asMutableListViewReflectsChanges() {
        val d = FloatDeque(2)
        d.addLast(1f); d.addLast(2f)

        val view = d.asMutableList()
        view.add(2, 3f)
        view.add(0, 0f)
        assertEquals(listOf(0f,1f,2f,3f), d.toList())

        val old = view.set(1, 9f)
        assertEquals(1f, old)
        assertEquals(listOf(0f,9f,2f,3f), d.toList())

        assertEquals(9f, view.removeAt(1))
        assertEquals(listOf(0f,2f,3f), d.toList())
    }

    @Test
    fun firstLastOrNull() {
        val d = FloatDeque(2)
        assertNull(d.firstOrNull())
        assertNull(d.lastOrNull())
        d.addLast(5f)
        assertEquals(5f, d.firstOrNull())
        assertEquals(5f, d.lastOrNull())
        d.addFirst(4f)
        assertEquals(4f, d.firstOrNull())
        assertEquals(5f, d.lastOrNull())
    }

    @Test
    fun sizeAndLastIndex() {
        val d = FloatDeque(1)
        assertEquals(-1, d.lastIndex)
        d.addLast(1f)
        assertEquals(0, d.lastIndex)
        d.addLast(2f)
        assertEquals(1, d.lastIndex)
        d.removeFirst()
        assertEquals(0, d.lastIndex)
    }

    @Test
    fun push() {
        val d = FloatDeque(2)
        d.push(1f)
        d.push(2f)
        assertEquals(listOf(2f,1f), d.toList())
        assertEquals(2f, d.first())
    }

    @Test
    fun pop() {
        val d = FloatDeque(3)
        d.addLast(1f); d.addLast(2f); d.addLast(3f)
        assertEquals(1f, d.pop())
        assertEquals(listOf(2f,3f), d.toList())
    }

    @Test
    fun enqueue() {
        val d = FloatDeque(1)
        d.enqueue(1f)
        d.enqueue(2f)
        d.enqueue(3f)
        assertEquals(listOf(1f,2f,3f), d.toList())
        assertEquals(3f, d.last())
    }

    @Test
    fun dequeue() {
        val d = FloatDeque(2)
        d.addFirst(2f)
        d.addFirst(1f)
        assertEquals(1f, d.dequeue())
        assertEquals(listOf(2f), d.toList())
    }

    @Test
    fun asListReflectsChanges() {
        val d = FloatDeque(2)
        d.addLast(1f)
        d.addLast(2f)
        val list = d.asList()
        assertEquals(listOf(1f, 2f), list)

        d.addFirst(0f)
        assertEquals(listOf(0f, 1f, 2f), list)

        d.removeLast()
        assertEquals(listOf(0f, 1f), list)
    }
}


