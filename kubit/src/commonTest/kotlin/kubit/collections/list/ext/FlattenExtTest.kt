package kubit.collections.list.ext

import kotlin.test.Test
import kotlin.test.assertEquals
import kubit.collections.list.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

class FlattenExtTest {

    @Test
    fun `flatten list of Double list`() {
        val input = listOf(listOf(), listOf(1.0, 2.0), listOf(3.0))
        val result = input.flatten()

        val expected = MutableDoubleList(3).apply {
            addAll(listOf(1.0, 2.0, 3.0))
        }

        assertEquals(expected.size, result.size)
        for (index in expected.indices) {
            assertEquals(expected[index], result[index])
        }
    }

    @Test
    fun `flatten list of DoubleList`() {
        val list1 = MutableDoubleList(2).apply { addAll(listOf(1.0, 2.0)) }
        val list2 = MutableDoubleList(1).apply { add(3.0) }
        val input = listOf(list1, list2)
        val result = input.flatten()

        val expected = MutableDoubleList(3).apply {
            addAll(listOf(1.0, 2.0, 3.0))
        }

        assertEquals(expected.size, result.size)
        for (index in expected.indices) {
            assertEquals(expected[index], result[index])
        }
    }

    @Test
    fun `flatten list of Float list`() {
        val input = listOf(listOf(), listOf(1f, 2f), listOf(3f))
        val result = input.flatten()

        val expected = MutableFloatList(3).apply {
            addAll(listOf(1f, 2f, 3f))
        }

        assertEquals(expected.size, result.size)
        for (index in expected.indices) {
            assertEquals(expected[index], result[index])
        }
    }

    @Test
    fun `flatten list of FloatList`() {
        val list1 = MutableFloatList(2).apply { addAll(listOf(1f, 2f)) }
        val list2 = MutableFloatList(1).apply { add(3f) }
        val input = listOf(list1, list2)
        val result = input.flatten()

        val expected = MutableFloatList(3).apply {
            addAll(listOf(1f, 2f, 3f))
        }

        assertEquals(expected.size, result.size)
        for (index in expected.indices) {
            assertEquals(expected[index], result[index])
        }
    }

    @Test
    fun `flatten list of Long list`() {
        val input = listOf(listOf(), listOf(1L, 2L), listOf(3L))
        val result = input.flatten()

        val expected = MutableLongList(3).apply {
            addAll(listOf(1L, 2L, 3L))
        }

        assertEquals(expected.size, result.size)
        for (index in expected.indices) {
            assertEquals(expected[index], result[index])
        }
    }

    @Test
    fun `flatten list of LongList`() {
        val list1 = MutableLongList(2).apply { addAll(listOf(1L, 2L)) }
        val list2 = MutableLongList(1).apply { add(3L) }
        val input = listOf(list1, list2)
        val result = input.flatten()

        val expected = MutableLongList(3).apply {
            addAll(listOf(1L, 2L, 3L))
        }

        assertEquals(expected.size, result.size)
        for (index in expected.indices) {
            assertEquals(expected[index], result[index])
        }
    }

    @Test
    fun `flatten list of Int list`() {
        val input = listOf(listOf(), listOf(1, 2), listOf(3))
        val result = input.flatten()

        val expected = MutableIntList(3).apply {
            addAll(listOf(1, 2, 3))
        }

        assertEquals(expected.size, result.size)
        for (index in expected.indices) {
            assertEquals(expected[index], result[index])
        }
    }

    @Test
    fun `flatten list of IntList`() {
        val list1 = MutableIntList(2).apply { addAll(listOf(1, 2)) }
        val list2 = MutableIntList(1).apply { add(3) }
        val input = listOf(list1, list2)
        val result = input.flatten()

        val expected = MutableIntList(3).apply {
            addAll(listOf(1, 2, 3))
        }

        assertEquals(expected.size, result.size)
        for (index in expected.indices) {
            assertEquals(expected[index], result[index])
        }
    }
}
