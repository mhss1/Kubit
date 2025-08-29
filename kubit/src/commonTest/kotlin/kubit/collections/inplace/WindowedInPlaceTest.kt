package kubit.collections.inplace

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class WindowedInPlaceTest {

    @Test
    fun `windowedInPlace with valid parameters`() {
        val list = List(10) { it }
        val result = list.windowedInPlace(4, 3)
        assertEquals(3, result.size)
        assertEquals(listOf(0, 1, 2, 3), result[0])
        assertEquals(listOf(3, 4, 5, 6), result[1])
        assertEquals(listOf(6, 7, 8, 9), result[2])
    }

    @Test
    fun `windowedInPlace with partial windows`() {
        val list = List(10) { it }
        val result = list.windowedInPlace(4, 3, true)
        assertEquals(4, result.size)
        assertEquals(listOf(0, 1, 2, 3), result[0])
        assertEquals(listOf(3, 4, 5, 6), result[1])
        assertEquals(listOf(6, 7, 8, 9), result[2])
        assertEquals(listOf(9), result[3])
    }

    @Test
    fun `windowedInPlace with same size and step`() {
        val list = List(10) { it }
        val result = list.windowedInPlace(3, 3)
        assertEquals(3, result.size)
        assertEquals(listOf(0, 1, 2), result[0])
        assertEquals(listOf(3, 4, 5), result[1])
        assertEquals(listOf(6, 7, 8), result[2])
    }

    @Test
    fun `windowedInPlace with default step`() {
        val list = List(10) { it }
        val result = list.windowedInPlace(3)
        assertEquals(8, result.size)
        assertEquals(listOf(0, 1, 2), result[0])
        assertEquals(listOf(1, 2, 3), result[1])
        assertEquals(listOf(2, 3, 4), result[2])
        assertEquals(listOf(3, 4, 5), result[3])
        assertEquals(listOf(4, 5, 6), result[4])
        assertEquals(listOf(5, 6, 7), result[5])
        assertEquals(listOf(6, 7, 8), result[6])
        assertEquals(listOf(7, 8, 9), result[7])
    }

    @Test
    fun `windowedInPlace with single element list`() {
        val list = listOf(42)
        val result = list.windowedInPlace(2, 1)
        assertTrue(result.isEmpty())
    }

    @Test
    fun `windowedInPlace with step larger than list size`() {
        val list = List(5) { it }
        val result = list.windowedInPlace(2, 10)
        assertEquals(1, result.size)
        assertEquals(listOf(0, 1), result[0])
    }

    @Test
    fun `windowedInPlace with step larger than list size and partial windows`() {
        val list = List(5) { it }
        val result = list.windowedInPlace(2, 10, true)
        assertEquals(1, result.size)
        assertEquals(listOf(0, 1), result[0])
    }

    @Test
    fun `windowedInPlace with window size larger than list size`() {
        val list = List(5) { it }
        val result = list.windowedInPlace(10, 1)
        assertTrue(result.isEmpty())
    }


    @Test
    fun `windowedInPlace with empty list`() {
        val list = emptyList<Int>()
        val result = list.windowedInPlace(3, 1)
        assertTrue(result.isEmpty())
    }

    @Test
    fun `windowedInPlace with invalid parameters throws IllegalArgumentException`() {
        val list = List(10) { it }
        assertFailsWith<IllegalArgumentException> { list.windowedInPlace(0, 1) }
        assertFailsWith<IllegalArgumentException> { list.windowedInPlace(5, 0) }
    }

    @Test
    fun `windowedInPlace with negative parameters throws IllegalArgumentException`() {
        val list = List(10) { it }
        assertFailsWith<IllegalArgumentException> { list.windowedInPlace(-1, 1) }
        assertFailsWith<IllegalArgumentException> { list.windowedInPlace(5, -1) }
    }

    @Test
    fun `forEachWindow with valid parameters`() {
        val list = List(10) { it }
        val result = mutableListOf<List<Int>>()
        list.forEachWindow(3, 2) { result.add(it.toList()) }
        assertEquals(4, result.size)
        assertEquals(listOf(0, 1, 2), result[0])
        assertEquals(listOf(2, 3, 4), result[1])
    }

    @Test
    fun `forEachWindow with partial windows`() {
        val list = List(10) { it }
        val result = mutableListOf<List<Int>>()
        list.forEachWindow(4, 3, true) { result.add(it.toList()) }
        assertEquals(4, result.size)
        assertEquals(listOf(0, 1, 2, 3), result[0])
        assertEquals(listOf(3, 4, 5, 6), result[1])
        assertEquals(listOf(6, 7, 8, 9), result[2])
        assertEquals(listOf(9), result[3])

    }

    @Test
    fun `forEachWindow with same size and step`() {
        val list = List(10) { it }
        val result = mutableListOf<List<Int>>()
        list.forEachWindow(3, 3) { result.add(it.toList()) }
        assertEquals(3, result.size)
        assertEquals(listOf(0, 1, 2), result[0])
        assertEquals(listOf(3, 4, 5), result[1])
        assertEquals(listOf(6, 7, 8), result[2])
    }

    @Test
    fun `forEachWindow with default step`() {
        val list = List(10) { it }
        val result = mutableListOf<List<Int>>()
        list.forEachWindow(3) { result.add(it.toList()) }
        assertEquals(8, result.size)
        assertEquals(listOf(0, 1, 2), result[0])
        assertEquals(listOf(1, 2, 3), result[1])

    }

    @Test
    fun `forEachWindow with single element list`() {
        val list = listOf(1)
        val result = mutableListOf<List<Int>>()
        list.forEachWindow(2) { result.add(it.toList()) }
        assertTrue(result.isEmpty())

    }

    @Test
    fun `forEachWindow with step larger than list size`() {
        val list = List(5) { it }
        val result = mutableListOf<List<Int>>()
        list.forEachWindow(2, 10) { result.add(it.toList()) }
        assertEquals(1, result.size)
        assertEquals(listOf(0, 1), result[0])
    }

    @Test
    fun `forEachWindow with step larger than list size and partial windows`() {
        val list = List(5) { it }
        val result = mutableListOf<List<Int>>()
        list.forEachWindow(2, 10, true) { result.add(it.toList()) }
        assertEquals(1, result.size)
        assertEquals(listOf(0, 1), result[0])
    }


    @Test
    fun `forEachWindow with window size larger than list size`() {
        val list = List(5) { it }
        val result = mutableListOf<List<Int>>()
        list.forEachWindow(10) { result.add(it.toList()) }
        assertTrue(result.isEmpty())
    }

    @Test
    fun `forEachWindow with empty list`() {
        val list = emptyList<Int>()
        val result = mutableListOf<List<Int>>()
        list.forEachWindow(2) { result.add(it.toList()) }
        assertTrue(result.isEmpty())
    }


    @Test
    fun `forEachWindow with invalid parameters throws IllegalArgumentException`() {
        val list = List(10) { it }
        assertFailsWith<IllegalArgumentException> { list.forEachWindow(0, 1) {} }
        assertFailsWith<IllegalArgumentException> { list.forEachWindow(5, 0) {} }
    }

    @Test
    fun `forEachWindow with negative parameters throws IllegalArgumentException`() {
        val list = List(10) { it }
        assertFailsWith<IllegalArgumentException> { list.forEachWindow(-1, 1) {} }
        assertFailsWith<IllegalArgumentException> { list.forEachWindow(5, -1) {} }
    }

    @Test
    fun `forEachWindowIndexed with valid parameters`() {
        val list = List(10) { it }
        val result = mutableListOf<Pair<Int, List<Int>>>()
        list.forEachWindowIndexed(3, 2) { index, window -> result.add(index to window.toList()) }
        assertEquals(4, result.size)
        assertEquals(0 to listOf(0, 1, 2), result[0])
        assertEquals(1 to listOf(2, 3, 4), result[1])
    }

    @Test
    fun `forEachWindowIndexed with partial windows`() {
        val list = List(10) { it }
        val result = mutableListOf<Pair<Int, List<Int>>>()
        list.forEachWindowIndexed(
            4,
            3,
            true
        ) { index, window -> result.add(index to window.toList()) }
        assertEquals(4, result.size)
        assertEquals(0 to listOf(0, 1, 2, 3), result[0])
        assertEquals(1 to listOf(3, 4, 5, 6), result[1])
        assertEquals(2 to listOf(6, 7, 8, 9), result[2])
        assertEquals(3 to listOf(9), result[3])
    }

    @Test
    fun `forEachWindowIndexed with same size and step`() {
        val list = List(10) { it }
        val result = mutableListOf<Pair<Int, List<Int>>>()
        list.forEachWindowIndexed(3, 3) { index, window -> result.add(index to window.toList()) }
        assertEquals(3, result.size)
        assertEquals(0 to listOf(0, 1, 2), result[0])
        assertEquals(1 to listOf(3, 4, 5), result[1])
        assertEquals(2 to listOf(6, 7, 8), result[2])
    }

    @Test
    fun `forEachWindowIndexed with default step`() {
        val list = List(10) { it }
        val result = mutableListOf<Pair<Int, List<Int>>>()
        list.forEachWindowIndexed(3) { index, window -> result.add(index to window.toList()) }
        assertEquals(8, result.size)
        assertEquals(0 to listOf(0, 1, 2), result[0])
        assertEquals(1 to listOf(1, 2, 3), result[1])

    }

    @Test
    fun `forEachWindowIndexed with single element list`() {
        val list = listOf(1)
        val result = mutableListOf<Pair<Int, List<Int>>>()
        list.forEachWindowIndexed(2) { index, window -> result.add(index to window.toList()) }
        assertTrue(result.isEmpty())

    }

    @Test
    fun `forEachWindowIndexed with step larger than list size`() {
        val list = List(5) { it }
        val result = mutableListOf<Pair<Int, List<Int>>>()
        list.forEachWindowIndexed(2, 10) { index, window -> result.add(index to window.toList()) }
        assertEquals(1, result.size)
        assertEquals(0 to listOf(0, 1), result[0])
    }

    @Test
    fun `forEachWindowIndexed with step larger than list size and partial windows`() {
        val list = List(5) { it }
        val result = mutableListOf<Pair<Int, List<Int>>>()
        list.forEachWindowIndexed(
            2,
            10,
            true
        ) { index, window -> result.add(index to window.toList()) }
        assertEquals(1, result.size)
        assertEquals(0 to listOf(0, 1), result[0])
    }


    @Test
    fun `forEachWindowIndexed with window size larger than list size`() {
        val list = List(5) { it }
        val result = mutableListOf<Pair<Int, List<Int>>>()
        list.forEachWindowIndexed(10) { index, window -> result.add(index to window.toList()) }
        assertTrue(result.isEmpty())
    }

    @Test
    fun `forEachWindowIndexed with empty list`() {
        val list = emptyList<Int>()
        val result = mutableListOf<Pair<Int, List<Int>>>()
        list.forEachWindowIndexed(2) { index, window -> result.add(index to window.toList()) }
        assertTrue(result.isEmpty())
    }


    @Test
    fun `forEachWindowIndexed with invalid parameters throws IllegalArgumentException`() {
        val list = List(10) { it }
        assertFailsWith<IllegalArgumentException> { list.forEachWindowIndexed(0, 1) { _, _ -> } }
        assertFailsWith<IllegalArgumentException> { list.forEachWindowIndexed(5, 0) { _, _ -> } }
    }

    @Test
    fun `forEachWindowIndexed with negative parameters throws IllegalArgumentException`() {
        val list = List(10) { it }
        assertFailsWith<IllegalArgumentException> { list.forEachWindowIndexed(-1, 1) { _, _ -> } }
        assertFailsWith<IllegalArgumentException> { list.forEachWindowIndexed(5, -1) { _, _ -> } }
    }


}