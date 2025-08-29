package kubit.collections.list.ext

import kotlin.test.*
import kubit.collections.list.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

@Suppress("USELESS_IS_CHECK")
class GenericListExtTest {

    @Test
    fun `filter list of Int to IntList`() {
        val list = listOf(1, 2, 3, 4, 5)
        val result = list.filter { it > 3 }
        assertEquals(2, result.size)
        assertEquals(4, result[0])
        assertEquals(5, result[1])
        assertTrue(result is IntList)
    }

    @Test
    fun `filterNot list of Int to IntList`() {
        val list = listOf(1, 2, 3, 4, 5)
        val result = list.filterNot { it > 3 }
        assertEquals(3, result.size)
        assertEquals(1, result[0])
        assertEquals(2, result[1])
        assertEquals(3, result[2])
        assertTrue(result is IntList)
    }

    @Test
    fun `filterNotNull list of Int to IntList`() {
        val list = listOf(1, null, 3, null, 5)
        val result = list.filterNotNull()
        assertEquals(3, result.size)
        assertEquals(1, result[0])
        assertEquals(3, result[1])
        assertEquals(5, result[2])
        assertTrue(result is IntList)
    }

    @Test
    fun `filter list of Long to LongList`() {
        val list = listOf(1L, 2L, 3L, 4L, 5L)
        val result = list.filter { it > 3L }
        assertEquals(2, result.size)
        assertEquals(4L, result[0])
        assertEquals(5L, result[1])
        assertTrue(result is LongList)
    }

    @Test
    fun `filterNot list of Long to LongList`() {
        val list = listOf(1L, 2L, 3L, 4L, 5L)
        val result = list.filterNot { it > 3L }
        assertEquals(3, result.size)
        assertEquals(1L, result[0])
        assertEquals(2L, result[1])
        assertEquals(3L, result[2])
        assertTrue(result is LongList)
    }

    @Test
    fun `filterNotNull list of Long to LongList`() {
        val list = listOf(1L, null, 3L, null, 5L)
        val result = list.filterNotNull()
        assertEquals(3, result.size)
        assertEquals(1L, result[0])
        assertEquals(3L, result[1])
        assertEquals(5L, result[2])
        assertTrue(result is LongList)
    }

    @Test
    fun `filter list of Float to FloatList`() {
        val list = listOf(1f, 2f, 3f, 4f, 5f)
        val result = list.filter { it > 3f }
        assertEquals(2, result.size)
        assertEquals(4f, result[0])
        assertEquals(5f, result[1])
        assertTrue(result is FloatList)
    }

    @Test
    fun `filterNot list of Float to FloatList`() {
        val list = listOf(1f, 2f, 3f, 4f, 5f)
        val result = list.filterNot { it > 3f }
        assertEquals(3, result.size)
        assertEquals(1f, result[0])
        assertEquals(2f, result[1])
        assertEquals(3f, result[2])
        assertTrue(result is FloatList)
    }

    @Test
    fun `filterNotNull list of Float to FloatList`() {
        val list = listOf(1f, null, 3f, null, 5f)
        val result = list.filterNotNull()
        assertEquals(3, result.size)
        assertEquals(1f, result[0])
        assertEquals(3f, result[1])
        assertEquals(5f, result[2])
        assertTrue(result is FloatList)
    }

    @Test
    fun `filter list of Double to DoubleList`() {
        val list = listOf(1.0, 2.0, 3.0, 4.0, 5.0)
        val result = list.filter { it > 3.0 }
        assertEquals(2, result.size)
        assertEquals(4.0, result[0])
        assertEquals(5.0, result[1])
        assertTrue(result is DoubleList)
    }

    @Test
    fun `filterNot list of Double to DoubleList`() {
        val list = listOf(1.0, 2.0, 3.0, 4.0, 5.0)
        val result = list.filterNot { it > 3.0 }
        assertEquals(3, result.size)
        assertEquals(1.0, result[0])
        assertEquals(2.0, result[1])
        assertEquals(3.0, result[2])
        assertTrue(result is DoubleList)
    }

    @Test
    fun `filterNotNull list of Double to DoubleList`() {
        val list = listOf(1.0, null, 3.0, null, 5.0)
        val result = list.filterNotNull()
        assertEquals(3, result.size)
        assertEquals(1.0, result[0])
        assertEquals(3.0, result[1])
        assertEquals(5.0, result[2])
        assertTrue(result is DoubleList)
    }

    @Test
    fun `atLeast returns true when count greater than or equal to n`() {
        val list = listOf(1, 2, 3, 4, 5)
        val result = list.atLeast(2) { it > 3 }
        assertTrue(result)
    }

    @Test
    fun `atLeast returns false when count less than n`() {
        val list = listOf(1, 2, 3, 4, 5)
        val result = list.atLeast(3) { it > 3 }
        assertEquals(false, result)
    }

    @Test
    fun `atLeast with n equals 0 returns true`() {
        val list = listOf(1, 2, 3, 4, 5)
        val result = list.atLeast(0) { it > 100 }
        assertTrue(result)
    }

    @Test
    fun `atMost returns true when count less than or equal to n`() {
        val list = listOf(1, 2, 3, 4, 5)
        val result = list.atMost(2) { it > 3 }
        assertTrue(result)
    }

    @Test
    fun `atMost returns false when count greater than n`() {
        val list = listOf(1, 2, 3, 4, 5)
        val result = list.atMost(1) { it > 3 }
        assertEquals(false, result)
    }

    @Test
    fun `atMost with n equals 0 and no matches returns true`() {
        val list = listOf(1, 2, 3)
        val result = list.atMost(0) { it > 10 }
        assertTrue(result)
    }

    @Test
    fun `atMost with n equals 0 and matches returns false`() {
        val list = listOf(1, 2, 3)
        val result = list.atMost(0) { it > 1 }
        assertEquals(false, result)
    }

    @Test
    fun `atLeast throws IllegalArgumentException when n less than 0`() {
        val list = listOf(1, 2, 3, 4, 5)
        assertFailsWith<IllegalArgumentException> {
            list.atLeast(-1) { it > 3 }
        }
    }

    @Test
    fun `atMost throws IllegalArgumentException when n less than 0`() {
        val list = listOf(1, 2, 3, 4, 5)
        assertFailsWith<IllegalArgumentException> {
            list.atMost(-1) { it > 3 }
        }
    }
}
