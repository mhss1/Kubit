package kubit.collections.list.ext

import kotlin.test.Test
import kotlin.test.assertEquals
import kubit.collections.list.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

class FlatMapExtTest {

    @Test
    fun `flat map list with IntList`() {
        val input = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        val expected = intListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        val result = input.flatMap { intListOf(it) }
        assertEquals(expected, result)
    }

    @Test
    fun `flat map list with IntList and empty lists`() {
        val input = listOf(1, 2, 3, 4)
        val expected = intListOf(3, 4)
        val result = input.flatMap { if (it > 2) intListOf(it) else intListOf() }
        assertEquals(expected, result)
    }
    
    @Test
    fun `flat map list with List of Int`() {
        val input = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        val expected = intListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        val result = input.flatMap { listOf(it) }
        assertEquals(expected, result)
    }

    @Test
    fun `flat map list with LongList`() {
        val input = listOf(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L)
        val expected = longListOf(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L)
        val result = input.flatMap { longListOf(it) }
        assertEquals(expected, result)
    }

    @Test
    fun `flat map list with LongList and empty lists`() {
        val input = listOf(1L, 2L, 3L, 4L)
        val expected = longListOf(3L, 4L)
        val result = input.flatMap { if (it > 2L) longListOf(it) else longListOf() }
        assertEquals(expected, result)
    }
    
    @Test
    fun `flat map list with List of Long`() {
        val input = listOf(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L)
        val expected = longListOf(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L)
        val result = input.flatMap { listOf(it) }
        assertEquals(expected, result)
    }

    @Test
    fun `flat map list with FloatList`() {
        val input = listOf(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f)
        val expected = floatListOf(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f)
        val result = input.flatMap { floatListOf(it) }
        assertEquals(expected, result)
    }

    @Test
    fun `flat map list with FloatList and empty lists`() {
        val input = listOf(1f, 2f, 3f, 4f)
        val expected = floatListOf(3f, 4f)
        val result = input.flatMap { if (it > 2f) floatListOf(it) else floatListOf() }
        assertEquals(expected, result)
    }
    
    @Test
    fun `flat map list with List of Float`() {
        val input = listOf(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f)
        val expected = floatListOf(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f)
        val result = input.flatMap { listOf(it) }
        assertEquals(expected, result)
    }

    @Test
    fun `flat map list with DoubleList`() {
        val input = listOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0)
        val expected = doubleListOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0)
        val result = input.flatMap { doubleListOf(it) }
        assertEquals(expected, result)
    }

    @Test
    fun `flat map list with DoubleList and empty lists`() {
        val input = listOf(1.0, 2.0, 3.0, 4.0)
        val expected = doubleListOf(3.0, 4.0)
        val result = input.flatMap { if (it > 2.0) doubleListOf(it) else doubleListOf() }
        assertEquals(expected, result)
    }
    
    @Test
    fun `flat map list with List of Double`() {
        val input = listOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0)
        val expected = doubleListOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0)
        val result = input.flatMap { listOf(it) }
        assertEquals(expected, result)
    }

    @Test
    fun `flat map list with IntList computeSize true`() {
        val input = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        val expected = intListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        val result = input.flatMap(computeSize = true) { intListOf(it) }
        assertEquals(expected, result)
    }
    
    @Test
    fun `flat map list with List of Int computeSize true`() {
        val input = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        val expected = intListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        val result = input.flatMap(computeSize = true) { listOf(it) }
        assertEquals(expected, result)
    }

    @Test
    fun `flat map list with LongList computeSize true`() {
        val input = listOf(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L)
        val expected = longListOf(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L)
        val result = input.flatMap(computeSize = true) { longListOf(it) }
        assertEquals(expected, result)
    }
    
    @Test
    fun `flat map list with List of Long computeSize true`() {
        val input = listOf(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L)
        val expected = longListOf(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L)
        val result = input.flatMap(computeSize = true) { listOf(it) }
        assertEquals(expected, result)
    }

    @Test
    fun `flat map list with FloatList computeSize true`() {
        val input = listOf(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f)
        val expected = floatListOf(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f)
        val result = input.flatMap(computeSize = true) { floatListOf(it) }
        assertEquals(expected, result)
    }
    
    @Test
    fun `flat map list with List of Float computeSize true`() {
        val input = listOf(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f)
        val expected = floatListOf(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f)
        val result = input.flatMap(computeSize = true) { listOf(it) }
        assertEquals(expected, result)
    }

    @Test
    fun `flat map list with DoubleList computeSize true`() {
        val input = listOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0)
        val expected = doubleListOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0)
        val result = input.flatMap(computeSize = true) { doubleListOf(it) }
        assertEquals(expected, result)
    }
    
    @Test
    fun `flat map list with List of Double computeSize true`() {
        val input = listOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0)
        val expected = doubleListOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0)
        val result = input.flatMap(computeSize = true) { listOf(it) }
        assertEquals(expected, result)
    }
}
