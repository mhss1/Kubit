package kubit.collections.array

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class ArrayQuantifiersTest {

    @Test
    fun `Array atLeast returns true when count greater than or equal to n`() {
        val arr = arrayOf(1, 2, 3, 4, 5)
        val result = arr.atLeast(2) { it > 3 }
        assertTrue(result)
    }

    @Test
    fun `Array atLeast returns false when count less than n`() {
        val arr = arrayOf(1, 2, 3, 4, 5)
        val result = arr.atLeast(3) { it > 3 }
        assertEquals(false, result)
    }

    @Test
    fun `Array atLeast with n equals 0 returns true`() {
        val arr = arrayOf(1, 2, 3, 4, 5)
        val result = arr.atLeast(0) { it > 100 }
        assertTrue(result)
    }

    @Test
    fun `Array atMost returns true when count less than or equal to n`() {
        val arr = arrayOf(1, 2, 3, 4, 5)
        val result = arr.atMost(2) { it > 3 }
        assertTrue(result)
    }

    @Test
    fun `Array atMost returns false when count greater than n`() {
        val arr = arrayOf(1, 2, 3, 4, 5)
        val result = arr.atMost(1) { it > 3 }
        assertEquals(false, result)
    }

    @Test
    fun `Array atMost with n equals 0 and no matches returns true`() {
        val arr = arrayOf(1, 2, 3)
        val result = arr.atMost(0) { it > 10 }
        assertTrue(result)
    }

    @Test
    fun `Array atMost with n equals 0 and matches returns false`() {
        val arr = arrayOf(1, 2, 3)
        val result = arr.atMost(0) { it > 1 }
        assertEquals(false, result)
    }

    @Test
    fun `Array atLeast throws IllegalArgumentException when n less than 0`() {
        val arr = arrayOf(1, 2, 3, 4, 5)
        assertFailsWith<IllegalArgumentException> {
            arr.atLeast(-1) { it > 3 }
        }
    }

    @Test
    fun `Array atMost throws IllegalArgumentException when n less than 0`() {
        val arr = arrayOf(1, 2, 3, 4, 5)
        assertFailsWith<IllegalArgumentException> {
            arr.atMost(-1) { it > 3 }
        }
    }

    @Test
    fun `IntArray atLeast returns true when count greater than or equal to n`() {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        val result = arr.atLeast(2) { it > 3 }
        assertTrue(result)
    }

    @Test
    fun `IntArray atLeast returns false when count less than n`() {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        val result = arr.atLeast(3) { it > 3 }
        assertEquals(false, result)
    }

    @Test
    fun `IntArray atLeast with n equals 0 returns true`() {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        val result = arr.atLeast(0) { it > 100 }
        assertTrue(result)
    }

    @Test
    fun `IntArray atMost returns true when count less than or equal to n`() {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        val result = arr.atMost(2) { it > 3 }
        assertTrue(result)
    }

    @Test
    fun `IntArray atMost returns false when count greater than n`() {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        val result = arr.atMost(1) { it > 3 }
        assertEquals(false, result)
    }

    @Test
    fun `IntArray atMost with n equals 0 and no matches returns true`() {
        val arr = intArrayOf(1, 2, 3)
        val result = arr.atMost(0) { it > 10 }
        assertTrue(result)
    }

    @Test
    fun `IntArray atMost with n equals 0 and matches returns false`() {
        val arr = intArrayOf(1, 2, 3)
        val result = arr.atMost(0) { it > 1 }
        assertEquals(false, result)
    }

    @Test
    fun `IntArray atLeast throws IllegalArgumentException when n less than 0`() {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        assertFailsWith<IllegalArgumentException> {
            arr.atLeast(-1) { it > 3 }
        }
    }

    @Test
    fun `IntArray atMost throws IllegalArgumentException when n less than 0`() {
        val arr = intArrayOf(1, 2, 3, 4, 5)
        assertFailsWith<IllegalArgumentException> {
            arr.atMost(-1) { it > 3 }
        }
    }

    @Test
    fun `LongArray atLeast returns true when count greater than or equal to n`() {
        val arr = longArrayOf(1, 2, 3, 4, 5)
        val result = arr.atLeast(2) { it > 3L }
        assertTrue(result)
    }

    @Test
    fun `LongArray atLeast returns false when count less than n`() {
        val arr = longArrayOf(1, 2, 3, 4, 5)
        val result = arr.atLeast(3) { it > 3L }
        assertEquals(false, result)
    }

    @Test
    fun `LongArray atLeast with n equals 0 returns true`() {
        val arr = longArrayOf(1, 2, 3, 4, 5)
        val result = arr.atLeast(0) { it > 100L }
        assertTrue(result)
    }

    @Test
    fun `LongArray atMost returns true when count less than or equal to n`() {
        val arr = longArrayOf(1, 2, 3, 4, 5)
        val result = arr.atMost(2) { it > 3L }
        assertTrue(result)
    }

    @Test
    fun `LongArray atMost returns false when count greater than n`() {
        val arr = longArrayOf(1, 2, 3, 4, 5)
        val result = arr.atMost(1) { it > 3L }
        assertEquals(false, result)
    }

    @Test
    fun `LongArray atMost with n equals 0 and no matches returns true`() {
        val arr = longArrayOf(1, 2, 3)
        val result = arr.atMost(0) { it > 10L }
        assertTrue(result)
    }

    @Test
    fun `LongArray atMost with n equals 0 and matches returns false`() {
        val arr = longArrayOf(1, 2, 3)
        val result = arr.atMost(0) { it > 1L }
        assertEquals(false, result)
    }

    @Test
    fun `LongArray atLeast throws IllegalArgumentException when n less than 0`() {
        val arr = longArrayOf(1, 2, 3, 4, 5)
        assertFailsWith<IllegalArgumentException> {
            arr.atLeast(-1) { it > 3L }
        }
    }

    @Test
    fun `LongArray atMost throws IllegalArgumentException when n less than 0`() {
        val arr = longArrayOf(1, 2, 3, 4, 5)
        assertFailsWith<IllegalArgumentException> {
            arr.atMost(-1) { it > 3L }
        }
    }

    @Test
    fun `FloatArray atLeast returns true when count greater than or equal to n`() {
        val arr = floatArrayOf(1f, 2f, 3f, 4f, 5f)
        val result = arr.atLeast(2) { it > 3f }
        assertTrue(result)
    }

    @Test
    fun `FloatArray atLeast returns false when count less than n`() {
        val arr = floatArrayOf(1f, 2f, 3f, 4f, 5f)
        val result = arr.atLeast(3) { it > 3f }
        assertEquals(false, result)
    }

    @Test
    fun `FloatArray atLeast with n equals 0 returns true`() {
        val arr = floatArrayOf(1f, 2f, 3f, 4f, 5f)
        val result = arr.atLeast(0) { it > 100f }
        assertTrue(result)
    }

    @Test
    fun `FloatArray atMost returns true when count less than or equal to n`() {
        val arr = floatArrayOf(1f, 2f, 3f, 4f, 5f)
        val result = arr.atMost(2) { it > 3f }
        assertTrue(result)
    }

    @Test
    fun `FloatArray atMost returns false when count greater than n`() {
        val arr = floatArrayOf(1f, 2f, 3f, 4f, 5f)
        val result = arr.atMost(1) { it > 3f }
        assertEquals(false, result)
    }

    @Test
    fun `FloatArray atMost with n equals 0 and no matches returns true`() {
        val arr = floatArrayOf(1f, 2f, 3f)
        val result = arr.atMost(0) { it > 10f }
        assertTrue(result)
    }

    @Test
    fun `FloatArray atMost with n equals 0 and matches returns false`() {
        val arr = floatArrayOf(1f, 2f, 3f)
        val result = arr.atMost(0) { it > 1f }
        assertEquals(false, result)
    }

    @Test
    fun `FloatArray atLeast throws IllegalArgumentException when n less than 0`() {
        val arr = floatArrayOf(1f, 2f, 3f, 4f, 5f)
        assertFailsWith<IllegalArgumentException> {
            arr.atLeast(-1) { it > 3f }
        }
    }

    @Test
    fun `FloatArray atMost throws IllegalArgumentException when n less than 0`() {
        val arr = floatArrayOf(1f, 2f, 3f, 4f, 5f)
        assertFailsWith<IllegalArgumentException> {
            arr.atMost(-1) { it > 3f }
        }
    }

    @Test
    fun `DoubleArray atLeast returns true when count greater than or equal to n`() {
        val arr = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0)
        val result = arr.atLeast(2) { it > 3.0 }
        assertTrue(result)
    }

    @Test
    fun `DoubleArray atLeast returns false when count less than n`() {
        val arr = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0)
        val result = arr.atLeast(3) { it > 3.0 }
        assertEquals(false, result)
    }

    @Test
    fun `DoubleArray atLeast with n equals 0 returns true`() {
        val arr = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0)
        val result = arr.atLeast(0) { it > 100.0 }
        assertTrue(result)
    }

    @Test
    fun `DoubleArray atMost returns true when count less than or equal to n`() {
        val arr = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0)
        val result = arr.atMost(2) { it > 3.0 }
        assertTrue(result)
    }

    @Test
    fun `DoubleArray atMost returns false when count greater than n`() {
        val arr = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0)
        val result = arr.atMost(1) { it > 3.0 }
        assertEquals(false, result)
    }

    @Test
    fun `DoubleArray atMost with n equals 0 and no matches returns true`() {
        val arr = doubleArrayOf(1.0, 2.0, 3.0)
        val result = arr.atMost(0) { it > 10.0 }
        assertTrue(result)
    }

    @Test
    fun `DoubleArray atMost with n equals 0 and matches returns false`() {
        val arr = doubleArrayOf(1.0, 2.0, 3.0)
        val result = arr.atMost(0) { it > 1.0 }
        assertEquals(false, result)
    }

    @Test
    fun `DoubleArray atLeast throws IllegalArgumentException when n less than 0`() {
        val arr = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0)
        assertFailsWith<IllegalArgumentException> {
            arr.atLeast(-1) { it > 3.0 }
        }
    }

    @Test
    fun `DoubleArray atMost throws IllegalArgumentException when n less than 0`() {
        val arr = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0)
        assertFailsWith<IllegalArgumentException> {
            arr.atMost(-1) { it > 3.0 }
        }
    }
}