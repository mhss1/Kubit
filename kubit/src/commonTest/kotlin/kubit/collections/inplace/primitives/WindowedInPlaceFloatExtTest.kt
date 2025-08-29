package kubit.collections.inplace.primitives

import kubit.collections.list.*
import kotlin.test.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a template in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

class WindowedInPlaceFloatTest {

    private fun subListToStdList(sub: InPlaceFloatSubList): List<Float> {
        val out = mutableListOf<Float>()
        sub.forEach { out.add(it) }
        return out
    }

    @Test
    fun `windowedInPlace with valid parameters`() {
        val list = FloatList(10) { it.toFloat() }
        val result = list.windowedInPlace(4, 3)
        assertEquals(3, result.size)
        assertEquals(listOf(0f, 1f, 2f, 3f), subListToStdList(result[0]))
        assertEquals(listOf(3f, 4f, 5f, 6f), subListToStdList(result[1]))
        assertEquals(listOf(6f, 7f, 8f, 9f), subListToStdList(result[2]))
    }

    @Test
    fun `windowedInPlace with partial windows`() {
        val list = FloatList(10) { it.toFloat() }
        val result = list.windowedInPlace(4, 3, true)
        assertEquals(4, result.size)
        assertEquals(listOf(0f, 1f, 2f, 3f), subListToStdList(result[0]))
        assertEquals(listOf(3f, 4f, 5f, 6f), subListToStdList(result[1]))
        assertEquals(listOf(6f, 7f, 8f, 9f), subListToStdList(result[2]))
        assertEquals(listOf(9f), subListToStdList(result[3]))
    }

    @Test
    fun `windowedInPlace with same size and step`() {
        val list = FloatList(10) { it.toFloat() }
        val result = list.windowedInPlace(3, 3)
        assertEquals(3, result.size)
        assertEquals(listOf(0f, 1f, 2f), subListToStdList(result[0]))
        assertEquals(listOf(3f, 4f, 5f), subListToStdList(result[1]))
        assertEquals(listOf(6f, 7f, 8f), subListToStdList(result[2]))
    }

    @Test
    fun `windowedInPlace with default step`() {
        val list = FloatList(10) { it.toFloat() }
        val result = list.windowedInPlace(3)
        assertEquals(8, result.size)
        assertEquals(listOf(0f, 1f, 2f), subListToStdList(result[0]))
        assertEquals(listOf(1f, 2f, 3f), subListToStdList(result[1]))
        assertEquals(listOf(2f, 3f, 4f), subListToStdList(result[2]))
        assertEquals(listOf(3f, 4f, 5f), subListToStdList(result[3]))
        assertEquals(listOf(4f, 5f, 6f), subListToStdList(result[4]))
        assertEquals(listOf(5f, 6f, 7f), subListToStdList(result[5]))
        assertEquals(listOf(6f, 7f, 8f), subListToStdList(result[6]))
        assertEquals(listOf(7f, 8f, 9f), subListToStdList(result[7]))
    }

    @Test
    fun `windowedInPlace with single element list`() {
        val list = FloatList(1) { 42f.toFloat() }
        val result = list.windowedInPlace(2, 1)
        assertTrue(result.isEmpty())
    }

    @Test
    fun `windowedInPlace with step larger than list size`() {
        val list = FloatList(5) { it.toFloat() }
        val result = list.windowedInPlace(2, 10)
        assertEquals(1, result.size)
        assertEquals(listOf(0f, 1f), subListToStdList(result[0]))
    }

    @Test
    fun `windowedInPlace with step larger than list size and partial windows`() {
        val list = FloatList(5) { it.toFloat() }
        val result = list.windowedInPlace(2, 10, true)
        assertEquals(1, result.size)
        assertEquals(listOf(0f, 1f), subListToStdList(result[0]))
    }

    @Test
    fun `windowedInPlace with window size larger than list size`() {
        val list = FloatList(5) { it.toFloat() }
        val result = list.windowedInPlace(10, 1)
        assertTrue(result.isEmpty())
    }

    @Test
    fun `windowedInPlace with empty list`() {
        val list = FloatList(0) { it.toFloat() }
        val result = list.windowedInPlace(3, 1)
        assertTrue(result.isEmpty())
    }

    @Test
    fun `windowedInPlace with invalid parameters throws IllegalArgumentException`() {
        val list = FloatList(10) { it.toFloat() }
        assertFailsWith<IllegalArgumentException> { list.windowedInPlace(0, 1) }
        assertFailsWith<IllegalArgumentException> { list.windowedInPlace(5, 0) }
    }

    @Test
    fun `windowedInPlace with negative parameters throws IllegalArgumentException`() {
        val list = FloatList(10) { it.toFloat() }
        assertFailsWith<IllegalArgumentException> { list.windowedInPlace(-1, 1) }
        assertFailsWith<IllegalArgumentException> { list.windowedInPlace(5, -1) }
    }

    @Test
    fun `forEachWindow with valid parameters`() {
        val list = FloatList(10) { it.toFloat() }
        val result = mutableListOf<List<Float>>()
        list.forEachWindow(3, 2) { result.add(subListToStdList(it)) }
        assertEquals(4, result.size)
        assertEquals(listOf(0f, 1f, 2f), result[0])
        assertEquals(listOf(2f, 3f, 4f), result[1])
    }

    @Test
    fun `forEachWindow with partial windows`() {
        val list = FloatList(10) { it.toFloat() }
        val result = mutableListOf<List<Float>>()
        list.forEachWindow(4, 3, true) { result.add(subListToStdList(it)) }
        assertEquals(4, result.size)
        assertEquals(listOf(0f, 1f, 2f, 3f), result[0])
        assertEquals(listOf(3f, 4f, 5f, 6f), result[1])
        assertEquals(listOf(6f, 7f, 8f, 9f), result[2])
        assertEquals(listOf(9f), result[3])
    }

    @Test
    fun `forEachWindow with same size and step`() {
        val list = FloatList(10) { it.toFloat() }
        val result = mutableListOf<List<Float>>()
        list.forEachWindow(3, 3) { result.add(subListToStdList(it)) }
        assertEquals(3, result.size)
        assertEquals(listOf(0f, 1f, 2f), result[0])
        assertEquals(listOf(3f, 4f, 5f), result[1])
        assertEquals(listOf(6f, 7f, 8f), result[2])
    }

    @Test
    fun `forEachWindow with default step`() {
        val list = FloatList(10) { it.toFloat() }
        val result = mutableListOf<List<Float>>()
        list.forEachWindow(3) { result.add(subListToStdList(it)) }
        assertEquals(8, result.size)
        assertEquals(listOf(0f, 1f, 2f), result[0])
        assertEquals(listOf(1f, 2f, 3f), result[1])
    }

    @Test
    fun `forEachWindow with single element list`() {
        val list = FloatList(1) { 1f.toFloat() }
        val result = mutableListOf<List<Float>>()
        list.forEachWindow(2) { result.add(subListToStdList(it)) }
        assertTrue(result.isEmpty())
    }

    @Test
    fun `forEachWindow with step larger than list size`() {
        val list = FloatList(5) { it.toFloat() }
        val result = mutableListOf<List<Float>>()
        list.forEachWindow(2, 10) { result.add(subListToStdList(it)) }
        assertEquals(1, result.size)
        assertEquals(listOf(0f, 1f), result[0])
    }

    @Test
    fun `forEachWindow with step larger than list size and partial windows`() {
        val list = FloatList(5) { it.toFloat() }
        val result = mutableListOf<List<Float>>()
        list.forEachWindow(2, 10, true) { result.add(subListToStdList(it)) }
        assertEquals(1, result.size)
        assertEquals(listOf(0f, 1f), result[0])
    }

    @Test
    fun `forEachWindow with window size larger than list size`() {
        val list = FloatList(5) { it.toFloat() }
        val result = mutableListOf<List<Float>>()
        list.forEachWindow(10) { result.add(subListToStdList(it)) }
        assertTrue(result.isEmpty())
    }

    @Test
    fun `forEachWindow with empty list`() {
        val list = FloatList(0) { it.toFloat() }
        val result = mutableListOf<List<Float>>()
        list.forEachWindow(2) { result.add(subListToStdList(it)) }
        assertTrue(result.isEmpty())
    }

    @Test
    fun `forEachWindow with invalid parameters throws IllegalArgumentException`() {
        val list = FloatList(10) { it.toFloat() }
        assertFailsWith<IllegalArgumentException> { list.forEachWindow(0, 1) {} }
        assertFailsWith<IllegalArgumentException> { list.forEachWindow(5, 0) {} }
    }

    @Test
    fun `forEachWindow with negative parameters throws IllegalArgumentException`() {
        val list = FloatList(10) { it.toFloat() }
        assertFailsWith<IllegalArgumentException> { list.forEachWindow(-1, 1) {} }
        assertFailsWith<IllegalArgumentException> { list.forEachWindow(5, -1) {} }
    }

    @Test
    fun `forEachWindowIndexed with valid parameters`() {
        val list = FloatList(10) { it.toFloat() }
        val result = mutableListOf<Pair<Int, List<Float>>>()
        list.forEachWindowIndexed(3, 2) { index, window -> result.add(index to subListToStdList(window)) }
        assertEquals(4, result.size)
        assertEquals(0 to listOf(0f, 1f, 2f), result[0])
        assertEquals(1 to listOf(2f, 3f, 4f), result[1])
    }

    @Test
    fun `forEachWindowIndexed with partial windows`() {
        val list = FloatList(10) { it.toFloat() }
        val result = mutableListOf<Pair<Int, List<Float>>>()
        list.forEachWindowIndexed(
            4,
            3,
            true
        ) { index, window -> result.add(index to subListToStdList(window)) }
        assertEquals(4, result.size)
        assertEquals(0 to listOf(0f, 1f, 2f, 3f), result[0])
        assertEquals(1 to listOf(3f, 4f, 5f, 6f), result[1])
        assertEquals(2 to listOf(6f, 7f, 8f, 9f), result[2])
        assertEquals(3 to listOf(9f), result[3])
    }

    @Test
    fun `forEachWindowIndexed with same size and step`() {
        val list = FloatList(10) { it.toFloat() }
        val result = mutableListOf<Pair<Int, List<Float>>>()
        list.forEachWindowIndexed(3, 3) { index, window -> result.add(index to subListToStdList(window)) }
        assertEquals(3, result.size)
        assertEquals(0 to listOf(0f, 1f, 2f), result[0])
        assertEquals(1 to listOf(3f, 4f, 5f), result[1])
        assertEquals(2 to listOf(6f, 7f, 8f), result[2])
    }

    @Test
    fun `forEachWindowIndexed with default step`() {
        val list = FloatList(10) { it.toFloat() }
        val result = mutableListOf<Pair<Int, List<Float>>>()
        list.forEachWindowIndexed(3) { index, window -> result.add(index to subListToStdList(window)) }
        assertEquals(8, result.size)
        assertEquals(0 to listOf(0f, 1f, 2f), result[0])
        assertEquals(1 to listOf(1f, 2f, 3f), result[1])
    }

    @Test
    fun `forEachWindowIndexed with single element list`() {
        val list = FloatList(1) { 1f.toFloat() }
        val result = mutableListOf<Pair<Int, List<Float>>>()
        list.forEachWindowIndexed(2) { index, window -> result.add(index to subListToStdList(window)) }
        assertTrue(result.isEmpty())
    }

    @Test
    fun `forEachWindowIndexed with step larger than list size`() {
        val list = FloatList(5) { it.toFloat() }
        val result = mutableListOf<Pair<Int, List<Float>>>()
        list.forEachWindowIndexed(2, 10) { index, window -> result.add(index to subListToStdList(window)) }
        assertEquals(1, result.size)
        assertEquals(0 to listOf(0f, 1f), result[0])
    }

    @Test
    fun `forEachWindowIndexed with step larger than list size and partial windows`() {
        val list = FloatList(5) { it.toFloat() }
        val result = mutableListOf<Pair<Int, List<Float>>>()
        list.forEachWindowIndexed(
            2,
            10,
            true
        ) { index, window -> result.add(index to subListToStdList(window)) }
        assertEquals(1, result.size)
        assertEquals(0 to listOf(0f, 1f), result[0])
    }

    @Test
    fun `forEachWindowIndexed with window size larger than list size`() {
        val list = FloatList(5) { it.toFloat() }
        val result = mutableListOf<Pair<Int, List<Float>>>()
        list.forEachWindowIndexed(10) { index, window -> result.add(index to subListToStdList(window)) }
        assertTrue(result.isEmpty())
    }

    @Test
    fun `forEachWindowIndexed with empty list`() {
        val list = FloatList(0) { it.toFloat() }
        val result = mutableListOf<Pair<Int, List<Float>>>()
        list.forEachWindowIndexed(2) { index, window -> result.add(index to subListToStdList(window)) }
        assertTrue(result.isEmpty())
    }

    @Test
    fun `forEachWindowIndexed with invalid parameters throws IllegalArgumentException`() {
        val list = FloatList(10) { it.toFloat() }
        assertFailsWith<IllegalArgumentException> { list.forEachWindowIndexed(0, 1) { _, _ -> } }
        assertFailsWith<IllegalArgumentException> { list.forEachWindowIndexed(5, 0) { _, _ -> } }
    }

    @Test
    fun `forEachWindowIndexed with negative parameters throws IllegalArgumentException`() {
        val list = FloatList(10) { it.toFloat() }
        assertFailsWith<IllegalArgumentException> { list.forEachWindowIndexed(-1, 1) { _, _ -> } }
        assertFailsWith<IllegalArgumentException> { list.forEachWindowIndexed(5, -1) { _, _ -> } }
    }
}


