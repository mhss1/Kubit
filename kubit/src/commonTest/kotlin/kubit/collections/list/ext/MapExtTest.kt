package kubit.collections.list.ext

import kotlin.test.*
import kubit.collections.list.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

@Suppress("USELESS_IS_CHECK")
class ListMapExtTest {

    @Test
    fun `map List to IntList`() {
        val input = listOf(1, 2, 3)
        val result = input.map { it * 2 }

        assertTrue(result is IntList)
        assertEquals(3, result.size)
        assertEquals(2, result[0])
        assertEquals(4, result[1])
        assertEquals(6, result[2])
    }
    
    @Test
    fun `mapNotNull List to IntList`() {
        val input = listOf(1, null, 2, null, 3)
        val result = input.mapNotNull { it?.let { it * 2 } }

        assertTrue(result is IntList)
        assertEquals(3, result.size)
        assertEquals(2, result[0])
        assertEquals(4, result[1])
        assertEquals(6, result[2])
    }

    @Test
    fun `map List to LongList`() {
        val input = listOf(1, 2, 3)
        val result = input.map { it * 2L }

        assertTrue(result is LongList)
        assertEquals(3, result.size)
        assertEquals(2L, result[0])
        assertEquals(4L, result[1])
        assertEquals(6L, result[2])
    }
    
    @Test
    fun `mapNotNull List to LongList`() {
        val input = listOf(1, null, 2, null, 3)
        val result = input.mapNotNull { it?.let { it * 2L } }

        assertTrue(result is LongList)
        assertEquals(3, result.size)
        assertEquals(2L, result[0])
        assertEquals(4L, result[1])
        assertEquals(6L, result[2])
    }

    @Test
    fun `map List to FloatList`() {
        val input = listOf(1, 2, 3)
        val result = input.map { it * 1.5f }

        assertTrue(result is FloatList)
        assertEquals(3, result.size)
        assertEquals(1.5f, result[0])
        assertEquals(3.0f, result[1])
        assertEquals(4.5f, result[2])
    }
    
    @Test
    fun `mapNotNull List to FloatList`() {
        val input = listOf(1, null, 2, null, 3)
        val result = input.mapNotNull { it?.let { it * 1.5f } }

        assertTrue(result is FloatList)
        assertEquals(3, result.size)
        assertEquals(1.5f, result[0])
        assertEquals(3.0f, result[1])
        assertEquals(4.5f, result[2])
    }

    @Test
    fun `map List to DoubleList`() {
        val input = listOf(1, 2, 3)
        val result = input.map { it * 1.5 }

        assertTrue(result is DoubleList)
        assertEquals(3, result.size)
        assertEquals(1.5, result[0])
        assertEquals(3.0, result[1])
        assertEquals(4.5, result[2])
    }
    
    @Test
    fun `mapNotNull List to DoubleList`() {
        val input = listOf(1, null, 2, null, 3)
        val result = input.mapNotNull { it?.let { it * 1.5 } }

        assertTrue(result is DoubleList)
        assertEquals(3, result.size)
        assertEquals(1.5, result[0])
        assertEquals(3.0, result[1])
        assertEquals(4.5, result[2])
    }

    @Test
    fun `map IntList to IntList`() {
        val input = mutableIntListOf(1, 2, 3)
        val result = input.map { it * 2 }

        assertTrue(result is IntList)
        assertEquals(3, result.size)
        assertEquals(2, result[0])
        assertEquals(4, result[1])
        assertEquals(6, result[2])
    }

    @Test
    fun `map IntList to LongList`() {
        val input = mutableIntListOf(1, 2, 3)
        val result = input.map { it.toLong() * 2 }

        assertTrue(result is LongList)
        assertEquals(3, result.size)
        assertEquals(2L, result[0])
        assertEquals(4L, result[1])
        assertEquals(6L, result[2])
    }

    @Test
    fun `map IntList to FloatList`() {
        val input = mutableIntListOf(1, 2, 3)
        val result = input.map { it * 1.5f }

        assertTrue(result is FloatList)
        assertEquals(3, result.size)
        assertEquals(1.5f, result[0])
        assertEquals(3.0f, result[1])
        assertEquals(4.5f, result[2])
    }

    @Test
    fun `map IntList to DoubleList`() {
        val input = mutableIntListOf(1, 2, 3)
        val result = input.map { it * 1.5 }

        assertTrue(result is DoubleList)
        assertEquals(3, result.size)
        assertEquals(1.5, result[0])
        assertEquals(3.0, result[1])
        assertEquals(4.5, result[2])
    }

    @Test
    fun `map LongList to IntList`() {
        val input = mutableLongListOf(1L, 2L, 3L)
        val result = input.map { it.toInt() * 2 }

        assertTrue(result is IntList)
        assertEquals(3, result.size)
        assertEquals(2, result[0])
        assertEquals(4, result[1])
        assertEquals(6, result[2])
    }

    @Test
    fun `map LongList to LongList`() {
        val input = mutableLongListOf(1L, 2L, 3L)
        val result = input.map { it * 2 }

        assertTrue(result is LongList)
        assertEquals(3, result.size)
        assertEquals(2L, result[0])
        assertEquals(4L, result[1])
        assertEquals(6L, result[2])
    }

    @Test
    fun `map LongList to FloatList`() {
        val input = mutableLongListOf(1L, 2L, 3L)
        val result = input.map { it * 1.5f }

        assertTrue(result is FloatList)
        assertEquals(3, result.size)
        assertEquals(1.5f, result[0])
        assertEquals(3.0f, result[1])
        assertEquals(4.5f, result[2])
    }

    @Test
    fun `map LongList to DoubleList`() {
        val input = mutableLongListOf(1L, 2L, 3L)
        val result = input.map { it * 1.5 }

        assertTrue(result is DoubleList)
        assertEquals(3, result.size)
        assertEquals(1.5, result[0])
        assertEquals(3.0, result[1])
        assertEquals(4.5, result[2])
    }

    @Test
    fun `map FloatList to IntList`() {
        val input = mutableFloatListOf(1f, 2f, 3f)
        val result = input.map { it.toInt() * 2 }

        assertTrue(result is IntList)
        assertEquals(3, result.size)
        assertEquals(2, result[0])
        assertEquals(4, result[1])
        assertEquals(6, result[2])
    }

    @Test
    fun `map FloatList to LongList`() {
        val input = mutableFloatListOf(1f, 2f, 3f)
        val result = input.map { it.toLong() * 2 }

        assertTrue(result is LongList)
        assertEquals(3, result.size)
        assertEquals(2L, result[0])
        assertEquals(4L, result[1])
        assertEquals(6L, result[2])
    }

    @Test
    fun `map FloatList to FloatList`() {
        val input = mutableFloatListOf(1f, 2f, 3f)
        val result = input.map { it * 1.5f }

        assertTrue(result is FloatList)
        assertEquals(3, result.size)
        assertEquals(1.5f, result[0])
        assertEquals(3.0f, result[1])
        assertEquals(4.5f, result[2])
    }

    @Test
    fun `map FloatList to DoubleList`() {
        val input = mutableFloatListOf(1f, 2f, 3f)
        val result = input.map { it * 1.5 }

        assertTrue(result is DoubleList)
        assertEquals(3, result.size)
        assertEquals(1.5, result[0])
        assertEquals(3.0, result[1])
        assertEquals(4.5, result[2])
    }

    @Test
    fun `map DoubleList to IntList`() {
        val input = mutableDoubleListOf(1.0, 2.0, 3.0)
        val result = input.map { it.toInt() * 2 }

        assertTrue(result is IntList)
        assertEquals(3, result.size)
        assertEquals(2, result[0])
        assertEquals(4, result[1])
        assertEquals(6, result[2])
    }

    @Test
    fun `map DoubleList to LongList`() {
        val input = mutableDoubleListOf(1.0, 2.0, 3.0)
        val result = input.map { it.toLong() * 2 }

        assertTrue(result is LongList)
        assertEquals(3, result.size)
        assertEquals(2L, result[0])
        assertEquals(4L, result[1])
        assertEquals(6L, result[2])
    }

    @Test
    fun `map DoubleList to FloatList`() {
        val input = mutableDoubleListOf(1.0, 2.0, 3.0)
        val result = input.map { (it * 1.5).toFloat() }

        assertTrue(result is FloatList)
        assertEquals(3, result.size)
        assertEquals(1.5f, result[0])
        assertEquals(3.0f, result[1])
        assertEquals(4.5f, result[2])
    }

    @Test
    fun `map DoubleList to DoubleList`() {
        val input = mutableDoubleListOf(1.0, 2.0, 3.0)
        val result = input.map { it * 1.5 }

        assertTrue(result is DoubleList)
        assertEquals(3, result.size)
        assertEquals(1.5, result[0])
        assertEquals(3.0, result[1])
        assertEquals(4.5, result[2])
    }

    @Test
    fun `map IntList to Generic List`() {
        val input = mutableIntListOf(1, 2, 3)
        val result = input.map { "Number $it" }

        assertTrue(result is List<*>)
        assertEquals(3, result.size)
        assertEquals("Number 1", result[0])
        assertEquals("Number 2", result[1])
        assertEquals("Number 3", result[2])
    }

    @Test
    fun `map LongList to Generic List`() {
        val input = mutableLongListOf(1, 2, 3)
        val result = input.map { "Number $it" }

        assertTrue(result is List<*>)
        assertEquals(3, result.size)
        assertEquals("Number 1", result[0])
        assertEquals("Number 2", result[1])
        assertEquals("Number 3", result[2])
    }

    @Test
    fun `map FloatList to Generic List`() {
        val input = mutableFloatListOf(1f, 2f, 3f)
        val result = input.map { "Number $it" }

        assertTrue(result is List<*>)
        assertEquals(3, result.size)
        assertEquals("Number 1.0", result[0])
        assertEquals("Number 2.0", result[1])
        assertEquals("Number 3.0", result[2])
    }

    @Test
    fun `map DoubleList to Generic List`() {
        val input = mutableDoubleListOf(1.0, 2.0, 3.0)
        val result = input.map { "Number $it" }

        assertTrue(result is List<*>)
        assertEquals(3, result.size)
        assertEquals("Number 1.0", result[0])
        assertEquals("Number 2.0", result[1])
        assertEquals("Number 3.0", result[2])
    }
}
