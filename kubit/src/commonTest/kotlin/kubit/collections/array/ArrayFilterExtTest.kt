package kubit.collections.array

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertContentEquals
import kubit.collections.list.*

class ArrayFilterExtTest {

    @Test
    fun `IntArray filter returns only matching elements as List`() {
        val array = IntArray(5) { it + 1 }
        val result = array.filter { it % 2 == 0 }
        assertEquals(intListOf(2, 4), result)
    }

    @Test
    fun `IntArray filterArray returns only matching elements as Array`() {
        val array = IntArray(5) { it + 1 }
        val result = array.filterArray { it > 3 }
        assertContentEquals(intArrayOf(4, 5), result)
    }

    @Test
    fun `Array of Int filter returns only matching elements as List`() {
        val array = arrayOf(1, 2, 3, 4, 5)
        val result = array.filter { it < 3 }
        assertEquals(intListOf(1, 2), result)
    }

    @Test
    fun `Array of Int filterArray returns only matching elements as Array`() {
        val array = arrayOf(1, 2, 3, 4, 5)
        val result = array.filterArray { it == 5 }
        assertContentEquals(intArrayOf(5), result)
    }

    @Test
    fun `LongArray filter returns only matching elements as List`() {
        val array = LongArray(5) { it + 1L }
        val result = array.filter { it % 2L == 0L }
        assertEquals(longListOf(2L, 4L), result)
    }

    @Test
    fun `LongArray filterArray returns only matching elements as Array`() {
        val array = LongArray(5) { it + 1L }
        val result = array.filterArray { it > 3L }
        assertContentEquals(longArrayOf(4L, 5L), result)
    }

    @Test
    fun `Array of Long filter returns only matching elements as List`() {
        val array = arrayOf(1L, 2L, 3L, 4L, 5L)
        val result = array.filter { it < 3L }
        assertEquals(longListOf(1L, 2L), result)
    }

    @Test
    fun `Array of Long filterArray returns only matching elements as Array`() {
        val array = arrayOf(1L, 2L, 3L, 4L, 5L)
        val result = array.filterArray { it == 5L }
        assertContentEquals(longArrayOf(5L), result)
    }

    @Test
    fun `FloatArray filter returns only matching elements as List`() {
        val array = FloatArray(5) { it + 1f }
        val result = array.filter { it % 2f == 0f }
        assertEquals(floatListOf(2f, 4f), result)
    }

    @Test
    fun `FloatArray filterArray returns only matching elements as Array`() {
        val array = FloatArray(5) { it + 1f }
        val result = array.filterArray { it > 3f }
        assertContentEquals(floatArrayOf(4f, 5f), result)
    }

    @Test
    fun `Array of Float filter returns only matching elements as List`() {
        val array = arrayOf(1f, 2f, 3f, 4f, 5f)
        val result = array.filter { it < 3f }
        assertEquals(floatListOf(1f, 2f), result)
    }

    @Test
    fun `Array of Float filterArray returns only matching elements as Array`() {
        val array = arrayOf(1f, 2f, 3f, 4f, 5f)
        val result = array.filterArray { it == 5f }
        assertContentEquals(floatArrayOf(5f), result)
    }

    @Test
    fun `DoubleArray filter returns only matching elements as List`() {
        val array = DoubleArray(5) { it + 1.0 }
        val result = array.filter { it % 2.0 == 0.0 }
        assertEquals(doubleListOf(2.0, 4.0), result)
    }

    @Test
    fun `DoubleArray filterArray returns only matching elements as Array`() {
        val array = DoubleArray(5) { it + 1.0 }
        val result = array.filterArray { it > 3.0 }
        assertContentEquals(doubleArrayOf(4.0, 5.0), result)
    }

    @Test
    fun `Array of Double filter returns only matching elements as List`() {
        val array = arrayOf(1.0, 2.0, 3.0, 4.0, 5.0)
        val result = array.filter { it < 3.0 }
        assertEquals(doubleListOf(1.0, 2.0), result)
    }

    @Test
    fun `Array of Double filterArray returns only matching elements as Array`() {
        val array = arrayOf(1.0, 2.0, 3.0, 4.0, 5.0)
        val result = array.filterArray { it == 5.0 }
        assertContentEquals(doubleArrayOf(5.0), result)
    }

}
