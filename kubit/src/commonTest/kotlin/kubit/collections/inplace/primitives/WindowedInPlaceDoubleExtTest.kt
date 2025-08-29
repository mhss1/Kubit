package kubit.collections.inplace.primitives

import kubit.collections.list.*
import kotlin.test.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a template in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

class WindowedInPlaceDoubleTest {

    private fun subListToStdList(sub: InPlaceDoubleSubList): List<Double> {
        val out = mutableListOf<Double>()
        sub.forEach { out.add(it) }
        return out
    }

    @Test
    fun `windowedInPlace with valid parameters`() {
        val list = DoubleList(10) { it.toDouble() }
        val result = list.windowedInPlace(4, 3)
        assertEquals(3, result.size)
        assertEquals(listOf(0.0, 1.0, 2.0, 3.0), subListToStdList(result[0]))
        assertEquals(listOf(3.0, 4.0, 5.0, 6.0), subListToStdList(result[1]))
        assertEquals(listOf(6.0, 7.0, 8.0, 9.0), subListToStdList(result[2]))
    }

    @Test
    fun `windowedInPlace with partial windows`() {
        val list = DoubleList(10) { it.toDouble() }
        val result = list.windowedInPlace(4, 3, true)
        assertEquals(4, result.size)
        assertEquals(listOf(0.0, 1.0, 2.0, 3.0), subListToStdList(result[0]))
        assertEquals(listOf(3.0, 4.0, 5.0, 6.0), subListToStdList(result[1]))
        assertEquals(listOf(6.0, 7.0, 8.0, 9.0), subListToStdList(result[2]))
        assertEquals(listOf(9.0), subListToStdList(result[3]))
    }

    @Test
    fun `windowedInPlace with same size and step`() {
        val list = DoubleList(10) { it.toDouble() }
        val result = list.windowedInPlace(3, 3)
        assertEquals(3, result.size)
        assertEquals(listOf(0.0, 1.0, 2.0), subListToStdList(result[0]))
        assertEquals(listOf(3.0, 4.0, 5.0), subListToStdList(result[1]))
        assertEquals(listOf(6.0, 7.0, 8.0), subListToStdList(result[2]))
    }

    @Test
    fun `windowedInPlace with default step`() {
        val list = DoubleList(10) { it.toDouble() }
        val result = list.windowedInPlace(3)
        assertEquals(8, result.size)
        assertEquals(listOf(0.0, 1.0, 2.0), subListToStdList(result[0]))
        assertEquals(listOf(1.0, 2.0, 3.0), subListToStdList(result[1]))
        assertEquals(listOf(2.0, 3.0, 4.0), subListToStdList(result[2]))
        assertEquals(listOf(3.0, 4.0, 5.0), subListToStdList(result[3]))
        assertEquals(listOf(4.0, 5.0, 6.0), subListToStdList(result[4]))
        assertEquals(listOf(5.0, 6.0, 7.0), subListToStdList(result[5]))
        assertEquals(listOf(6.0, 7.0, 8.0), subListToStdList(result[6]))
        assertEquals(listOf(7.0, 8.0, 9.0), subListToStdList(result[7]))
    }

    @Test
    fun `windowedInPlace with single element list`() {
        val list = DoubleList(1) { 42.0.toDouble() }
        val result = list.windowedInPlace(2, 1)
        assertTrue(result.isEmpty())
    }

    @Test
    fun `windowedInPlace with step larger than list size`() {
        val list = DoubleList(5) { it.toDouble() }
        val result = list.windowedInPlace(2, 10)
        assertEquals(1, result.size)
        assertEquals(listOf(0.0, 1.0), subListToStdList(result[0]))
    }

    @Test
    fun `windowedInPlace with step larger than list size and partial windows`() {
        val list = DoubleList(5) { it.toDouble() }
        val result = list.windowedInPlace(2, 10, true)
        assertEquals(1, result.size)
        assertEquals(listOf(0.0, 1.0), subListToStdList(result[0]))
    }

    @Test
    fun `windowedInPlace with window size larger than list size`() {
        val list = DoubleList(5) { it.toDouble() }
        val result = list.windowedInPlace(10, 1)
        assertTrue(result.isEmpty())
    }

    @Test
    fun `windowedInPlace with empty list`() {
        val list = DoubleList(0) { it.toDouble() }
        val result = list.windowedInPlace(3, 1)
        assertTrue(result.isEmpty())
    }

    @Test
    fun `windowedInPlace with invalid parameters throws IllegalArgumentException`() {
        val list = DoubleList(10) { it.toDouble() }
        assertFailsWith<IllegalArgumentException> { list.windowedInPlace(0, 1) }
        assertFailsWith<IllegalArgumentException> { list.windowedInPlace(5, 0) }
    }

    @Test
    fun `windowedInPlace with negative parameters throws IllegalArgumentException`() {
        val list = DoubleList(10) { it.toDouble() }
        assertFailsWith<IllegalArgumentException> { list.windowedInPlace(-1, 1) }
        assertFailsWith<IllegalArgumentException> { list.windowedInPlace(5, -1) }
    }

    @Test
    fun `forEachWindow with valid parameters`() {
        val list = DoubleList(10) { it.toDouble() }
        val result = mutableListOf<List<Double>>()
        list.forEachWindow(3, 2) { result.add(subListToStdList(it)) }
        assertEquals(4, result.size)
        assertEquals(listOf(0.0, 1.0, 2.0), result[0])
        assertEquals(listOf(2.0, 3.0, 4.0), result[1])
    }

    @Test
    fun `forEachWindow with partial windows`() {
        val list = DoubleList(10) { it.toDouble() }
        val result = mutableListOf<List<Double>>()
        list.forEachWindow(4, 3, true) { result.add(subListToStdList(it)) }
        assertEquals(4, result.size)
        assertEquals(listOf(0.0, 1.0, 2.0, 3.0), result[0])
        assertEquals(listOf(3.0, 4.0, 5.0, 6.0), result[1])
        assertEquals(listOf(6.0, 7.0, 8.0, 9.0), result[2])
        assertEquals(listOf(9.0), result[3])
    }

    @Test
    fun `forEachWindow with same size and step`() {
        val list = DoubleList(10) { it.toDouble() }
        val result = mutableListOf<List<Double>>()
        list.forEachWindow(3, 3) { result.add(subListToStdList(it)) }
        assertEquals(3, result.size)
        assertEquals(listOf(0.0, 1.0, 2.0), result[0])
        assertEquals(listOf(3.0, 4.0, 5.0), result[1])
        assertEquals(listOf(6.0, 7.0, 8.0), result[2])
    }

    @Test
    fun `forEachWindow with default step`() {
        val list = DoubleList(10) { it.toDouble() }
        val result = mutableListOf<List<Double>>()
        list.forEachWindow(3) { result.add(subListToStdList(it)) }
        assertEquals(8, result.size)
        assertEquals(listOf(0.0, 1.0, 2.0), result[0])
        assertEquals(listOf(1.0, 2.0, 3.0), result[1])
    }

    @Test
    fun `forEachWindow with single element list`() {
        val list = DoubleList(1) { 1.0.toDouble() }
        val result = mutableListOf<List<Double>>()
        list.forEachWindow(2) { result.add(subListToStdList(it)) }
        assertTrue(result.isEmpty())
    }

    @Test
    fun `forEachWindow with step larger than list size`() {
        val list = DoubleList(5) { it.toDouble() }
        val result = mutableListOf<List<Double>>()
        list.forEachWindow(2, 10) { result.add(subListToStdList(it)) }
        assertEquals(1, result.size)
        assertEquals(listOf(0.0, 1.0), result[0])
    }

    @Test
    fun `forEachWindow with step larger than list size and partial windows`() {
        val list = DoubleList(5) { it.toDouble() }
        val result = mutableListOf<List<Double>>()
        list.forEachWindow(2, 10, true) { result.add(subListToStdList(it)) }
        assertEquals(1, result.size)
        assertEquals(listOf(0.0, 1.0), result[0])
    }

    @Test
    fun `forEachWindow with window size larger than list size`() {
        val list = DoubleList(5) { it.toDouble() }
        val result = mutableListOf<List<Double>>()
        list.forEachWindow(10) { result.add(subListToStdList(it)) }
        assertTrue(result.isEmpty())
    }

    @Test
    fun `forEachWindow with empty list`() {
        val list = DoubleList(0) { it.toDouble() }
        val result = mutableListOf<List<Double>>()
        list.forEachWindow(2) { result.add(subListToStdList(it)) }
        assertTrue(result.isEmpty())
    }

    @Test
    fun `forEachWindow with invalid parameters throws IllegalArgumentException`() {
        val list = DoubleList(10) { it.toDouble() }
        assertFailsWith<IllegalArgumentException> { list.forEachWindow(0, 1) {} }
        assertFailsWith<IllegalArgumentException> { list.forEachWindow(5, 0) {} }
    }

    @Test
    fun `forEachWindow with negative parameters throws IllegalArgumentException`() {
        val list = DoubleList(10) { it.toDouble() }
        assertFailsWith<IllegalArgumentException> { list.forEachWindow(-1, 1) {} }
        assertFailsWith<IllegalArgumentException> { list.forEachWindow(5, -1) {} }
    }

    @Test
    fun `forEachWindowIndexed with valid parameters`() {
        val list = DoubleList(10) { it.toDouble() }
        val result = mutableListOf<Pair<Int, List<Double>>>()
        list.forEachWindowIndexed(3, 2) { index, window -> result.add(index to subListToStdList(window)) }
        assertEquals(4, result.size)
        assertEquals(0 to listOf(0.0, 1.0, 2.0), result[0])
        assertEquals(1 to listOf(2.0, 3.0, 4.0), result[1])
    }

    @Test
    fun `forEachWindowIndexed with partial windows`() {
        val list = DoubleList(10) { it.toDouble() }
        val result = mutableListOf<Pair<Int, List<Double>>>()
        list.forEachWindowIndexed(
            4,
            3,
            true
        ) { index, window -> result.add(index to subListToStdList(window)) }
        assertEquals(4, result.size)
        assertEquals(0 to listOf(0.0, 1.0, 2.0, 3.0), result[0])
        assertEquals(1 to listOf(3.0, 4.0, 5.0, 6.0), result[1])
        assertEquals(2 to listOf(6.0, 7.0, 8.0, 9.0), result[2])
        assertEquals(3 to listOf(9.0), result[3])
    }

    @Test
    fun `forEachWindowIndexed with same size and step`() {
        val list = DoubleList(10) { it.toDouble() }
        val result = mutableListOf<Pair<Int, List<Double>>>()
        list.forEachWindowIndexed(3, 3) { index, window -> result.add(index to subListToStdList(window)) }
        assertEquals(3, result.size)
        assertEquals(0 to listOf(0.0, 1.0, 2.0), result[0])
        assertEquals(1 to listOf(3.0, 4.0, 5.0), result[1])
        assertEquals(2 to listOf(6.0, 7.0, 8.0), result[2])
    }

    @Test
    fun `forEachWindowIndexed with default step`() {
        val list = DoubleList(10) { it.toDouble() }
        val result = mutableListOf<Pair<Int, List<Double>>>()
        list.forEachWindowIndexed(3) { index, window -> result.add(index to subListToStdList(window)) }
        assertEquals(8, result.size)
        assertEquals(0 to listOf(0.0, 1.0, 2.0), result[0])
        assertEquals(1 to listOf(1.0, 2.0, 3.0), result[1])
    }

    @Test
    fun `forEachWindowIndexed with single element list`() {
        val list = DoubleList(1) { 1.0.toDouble() }
        val result = mutableListOf<Pair<Int, List<Double>>>()
        list.forEachWindowIndexed(2) { index, window -> result.add(index to subListToStdList(window)) }
        assertTrue(result.isEmpty())
    }

    @Test
    fun `forEachWindowIndexed with step larger than list size`() {
        val list = DoubleList(5) { it.toDouble() }
        val result = mutableListOf<Pair<Int, List<Double>>>()
        list.forEachWindowIndexed(2, 10) { index, window -> result.add(index to subListToStdList(window)) }
        assertEquals(1, result.size)
        assertEquals(0 to listOf(0.0, 1.0), result[0])
    }

    @Test
    fun `forEachWindowIndexed with step larger than list size and partial windows`() {
        val list = DoubleList(5) { it.toDouble() }
        val result = mutableListOf<Pair<Int, List<Double>>>()
        list.forEachWindowIndexed(
            2,
            10,
            true
        ) { index, window -> result.add(index to subListToStdList(window)) }
        assertEquals(1, result.size)
        assertEquals(0 to listOf(0.0, 1.0), result[0])
    }

    @Test
    fun `forEachWindowIndexed with window size larger than list size`() {
        val list = DoubleList(5) { it.toDouble() }
        val result = mutableListOf<Pair<Int, List<Double>>>()
        list.forEachWindowIndexed(10) { index, window -> result.add(index to subListToStdList(window)) }
        assertTrue(result.isEmpty())
    }

    @Test
    fun `forEachWindowIndexed with empty list`() {
        val list = DoubleList(0) { it.toDouble() }
        val result = mutableListOf<Pair<Int, List<Double>>>()
        list.forEachWindowIndexed(2) { index, window -> result.add(index to subListToStdList(window)) }
        assertTrue(result.isEmpty())
    }

    @Test
    fun `forEachWindowIndexed with invalid parameters throws IllegalArgumentException`() {
        val list = DoubleList(10) { it.toDouble() }
        assertFailsWith<IllegalArgumentException> { list.forEachWindowIndexed(0, 1) { _, _ -> } }
        assertFailsWith<IllegalArgumentException> { list.forEachWindowIndexed(5, 0) { _, _ -> } }
    }

    @Test
    fun `forEachWindowIndexed with negative parameters throws IllegalArgumentException`() {
        val list = DoubleList(10) { it.toDouble() }
        assertFailsWith<IllegalArgumentException> { list.forEachWindowIndexed(-1, 1) { _, _ -> } }
        assertFailsWith<IllegalArgumentException> { list.forEachWindowIndexed(5, -1) { _, _ -> } }
    }
}


