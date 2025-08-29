package kubit.collections.list.ext

import kotlin.test.*
import kubit.collections.list.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

@Suppress("USELESS_IS_CHECK")
class ListMapIndexedExtTest {

    @Test
    fun `mapIndexed List to IntList`() {
        val input = listOf(1, 2, 3)
        val result = input.mapIndexed { index, it -> it * 2 + index }

        assertTrue(result is IntList)
        assertEquals(3, result.size)
        assertEquals(2, result[0])
        assertEquals(5, result[1])
        assertEquals(8, result[2])
    }

    @Test
    fun `mapIndexed List to LongList`() {
        val input = listOf(1, 2, 3)
        val result = input.mapIndexed { index, it -> it * 2L + index }

        assertTrue(result is LongList)
        assertEquals(3, result.size)
        assertEquals(2L, result[0])
        assertEquals(5L, result[1])
        assertEquals(8L, result[2])
    }

    @Test
    fun `mapIndexed List to FloatList`() {
        val input = listOf(1, 2, 3)
        val result = input.mapIndexed { index, it -> it * 1.5f + index }

        assertTrue(result is FloatList)
        assertEquals(3, result.size)
        assertEquals(1.5f, result[0])
        assertEquals(4.0f, result[1])
        assertEquals(6.5f, result[2])
    }

    @Test
    fun `mapIndexed List to DoubleList`() {
        val input = listOf(1, 2, 3)
        val result = input.mapIndexed { index, it -> it * 1.5 + index }

        assertTrue(result is DoubleList)
        assertEquals(3, result.size)
        assertEquals(1.5, result[0])
        assertEquals(4.0, result[1])
        assertEquals(6.5, result[2])
    }

    @Test
    fun `mapIndexed IntList to IntList`() {
        val input = mutableIntListOf(1, 2, 3)
        val result = input.mapIndexed { index, it -> it * 2 + index }

        assertTrue(result is IntList)
        assertEquals(3, result.size)
        assertEquals(2, result[0])
        assertEquals(5, result[1])
        assertEquals(8, result[2])
    }

    @Test
    fun `mapIndexed IntList to LongList`() {
        val input = mutableIntListOf(1, 2, 3)
        val result = input.mapIndexed { index, it -> it.toLong() * 2 + index }

        assertTrue(result is LongList)
        assertEquals(3, result.size)
        assertEquals(2L, result[0])
        assertEquals(5L, result[1])
        assertEquals(8L, result[2])
    }

    @Test
    fun `mapIndexed IntList to FloatList`() {
        val input = mutableIntListOf(1, 2, 3)
        val result = input.mapIndexed { index, it -> it.toFloat() * 1.5f + index }

        assertTrue(result is FloatList)
        assertEquals(3, result.size)
        assertEquals(1.5f, result[0])
        assertEquals(4.0f, result[1])
        assertEquals(6.5f, result[2])
    }

    @Test
    fun `mapIndexed IntList to DoubleList`() {
        val input = mutableIntListOf(1, 2, 3)
        val result = input.mapIndexed { index, it -> it.toDouble() * 1.5 + index }

        assertTrue(result is DoubleList)
        assertEquals(3, result.size)
        assertEquals(1.5, result[0])
        assertEquals(4.0, result[1])
        assertEquals(6.5, result[2])
    }

    @Test
    fun `mapIndexed LongList to IntList`() {
        val input = mutableLongListOf(1L, 2L, 3L)
        val result = input.mapIndexed { index, it -> it.toInt() * 2 + index }

        assertTrue(result is IntList)
        assertEquals(3, result.size)
        assertEquals(2, result[0])
        assertEquals(5, result[1])
        assertEquals(8, result[2])
    }

    @Test
    fun `mapIndexed LongList to LongList`() {
        val input = mutableLongListOf(1L, 2L, 3L)
        val result = input.mapIndexed { index, it -> it * 2 + index }

        assertTrue(result is LongList)
        assertEquals(3, result.size)
        assertEquals(2L, result[0])
        assertEquals(5L, result[1])
        assertEquals(8L, result[2])
    }

    @Test
    fun `mapIndexed LongList to FloatList`() {
        val input = mutableLongListOf(1L, 2L, 3L)
        val result = input.mapIndexed { index, it -> it.toFloat() * 1.5f + index }

        assertTrue(result is FloatList)
        assertEquals(3, result.size)
        assertEquals(1.5f, result[0])
        assertEquals(4.0f, result[1])
        assertEquals(6.5f, result[2])
    }

    @Test
    fun `mapIndexed LongList to DoubleList`() {
        val input = mutableLongListOf(1L, 2L, 3L)
        val result = input.mapIndexed { index, it -> it.toDouble() * 1.5 + index }

        assertTrue(result is DoubleList)
        assertEquals(3, result.size)
        assertEquals(1.5, result[0])
        assertEquals(4.0, result[1])
        assertEquals(6.5, result[2])
    }

    @Test
    fun `mapIndexed FloatList to IntList`() {
        val input = mutableFloatListOf(1f, 2f, 3f)
        val result = input.mapIndexed { index, it -> it.toInt() * 2 + index }

        assertTrue(result is IntList)
        assertEquals(3, result.size)
        assertEquals(2, result[0])
        assertEquals(5, result[1])
        assertEquals(8, result[2])
    }

    @Test
    fun `mapIndexed FloatList to LongList`() {
        val input = mutableFloatListOf(1f, 2f, 3f)
        val result = input.mapIndexed { index, it -> it.toLong() * 2 + index }

        assertTrue(result is LongList)
        assertEquals(3, result.size)
        assertEquals(2L, result[0])
        assertEquals(5L, result[1])
        assertEquals(8L, result[2])
    }

    @Test
    fun `mapIndexed FloatList to FloatList`() {
        val input = mutableFloatListOf(1f, 2f, 3f)
        val result = input.mapIndexed { index, it -> it * 1.5f + index }

        assertTrue(result is FloatList)
        assertEquals(3, result.size)
        assertEquals(1.5f, result[0])
        assertEquals(4.0f, result[1])
        assertEquals(6.5f, result[2])
    }

    @Test
    fun `mapIndexed FloatList to DoubleList`() {
        val input = mutableFloatListOf(1f, 2f, 3f)
        val result = input.mapIndexed { index, it -> it.toDouble() * 1.5 + index }

        assertTrue(result is DoubleList)
        assertEquals(3, result.size)
        assertEquals(1.5, result[0])
        assertEquals(4.0, result[1])
        assertEquals(6.5, result[2])
    }

    @Test
    fun `mapIndexed DoubleList to IntList`() {
        val input = mutableDoubleListOf(1.0, 2.0, 3.0)
        val result = input.mapIndexed { index, it -> it.toInt() * 2 + index }

        assertTrue(result is IntList)
        assertEquals(3, result.size)
        assertEquals(2, result[0])
        assertEquals(5, result[1])
        assertEquals(8, result[2])
    }

    @Test
    fun `mapIndexed DoubleList to LongList`() {
        val input = mutableDoubleListOf(1.0, 2.0, 3.0)
        val result = input.mapIndexed { index, it -> it.toLong() * 2 + index }

        assertTrue(result is LongList)
        assertEquals(3, result.size)
        assertEquals(2L, result[0])
        assertEquals(5L, result[1])
        assertEquals(8L, result[2])
    }

    @Test
    fun `mapIndexed DoubleList to FloatList`() {
        val input = mutableDoubleListOf(1.0, 2.0, 3.0)
        val result = input.mapIndexed { index, it -> it.toFloat() * 1.5f + index }

        assertTrue(result is FloatList)
        assertEquals(3, result.size)
        assertEquals(1.5f, result[0])
        assertEquals(4.0f, result[1])
        assertEquals(6.5f, result[2])
    }

    @Test
    fun `mapIndexed DoubleList to DoubleList`() {
        val input = mutableDoubleListOf(1.0, 2.0, 3.0)
        val result = input.mapIndexed { index, it -> it * 1.5 + index }

        assertTrue(result is DoubleList)
        assertEquals(3, result.size)
        assertEquals(1.5, result[0])
        assertEquals(4.0, result[1])
        assertEquals(6.5, result[2])
    }

    @Test
    fun `mapIndexed IntList to List`() {
        val input = mutableIntListOf(1, 2, 3)
        val result = input.mapIndexed { index, it -> "Number $it at $index" }

        assertTrue(result is List<*>)
        assertEquals(3, result.size)
        assertEquals("Number 1 at 0", result[0])
        assertEquals("Number 2 at 1", result[1])
        assertEquals("Number 3 at 2", result[2])
    }

    @Test
    fun `mapIndexed LongList to List`() {
        val input = mutableLongListOf(1, 2, 3)
        val result = input.mapIndexed { index, it -> "Number $it at $index" }

        assertTrue(result is List<*>)
        assertEquals(3, result.size)
        assertEquals("Number 1 at 0", result[0])
        assertEquals("Number 2 at 1", result[1])
        assertEquals("Number 3 at 2", result[2])
    }

    @Test
    fun `mapIndexed FloatList to List`() {
        val input = mutableFloatListOf(1f, 2f, 3f)
        val result = input.mapIndexed { index, it -> "Number $it at $index" }

        assertTrue(result is List<*>)
        assertEquals(3, result.size)
        assertEquals("Number 1.0 at 0", result[0])
        assertEquals("Number 2.0 at 1", result[1])
        assertEquals("Number 3.0 at 2", result[2])
    }

    @Test
    fun `mapIndexed DoubleList to List`() {
        val input = mutableDoubleListOf(1.0, 2.0, 3.0)
        val result = input.mapIndexed { index, it -> "Number $it at $index" }

        assertTrue(result is List<*>)
        assertEquals(3, result.size)
        assertEquals("Number 1.0 at 0", result[0])
        assertEquals("Number 2.0 at 1", result[1])
        assertEquals("Number 3.0 at 2", result[2])
    }
}
