package kubit.collections.list.ext

import kotlin.test.*
import kubit.collections.list.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

@Suppress("USELESS_IS_CHECK")
class GenericCompoundActionExtTest {

    @Test
    fun `forEachFlattened executes action for each element in nested lists`() {
        val src = listOf(
            listOf("a", "b"),
            listOf("c"),
            listOf("d", "e")
        )
        val result = mutableListOf<String>()
        src.forEachFlattened { result.add(it) }
        assertEquals(listOf("a", "b", "c", "d", "e"), result)
    }

    @Test
    fun `filterFlattened with generic lists`() {
        val src = listOf(
            listOf("a", "b"),
            listOf("c", "d"),
            listOf("e")
        )
        val result = src.filterFlattened { it > "b" }
        assertEquals(listOf("c", "d", "e"), result)
    }

    @Test
    fun `mapFlattened with generic lists`() {
        val src = listOf(
            listOf("a", "b"),
            listOf("c")
        )
        val result = src.mapFlattened { it.length }
        assertEquals(intListOf(1, 1, 1), result)
    }

    @Test
    fun `mapFlattened to IntList from nested lists`() {
        val src = listOf(
            listOf("1", "2"),
            listOf("3")
        )
        val result = src.mapFlattened { it.toInt() }
        assertTrue(result is IntList)
        assertEquals(3, result.size)
        assertEquals(1, result[0])
        assertEquals(2, result[1])
        assertEquals(3, result[2])
    }

    @Test
    fun `filterFlattened from nested Int lists`() {
        val src = listOf(
            listOf(1, 2),
            listOf(3, 4)
        )
        val result = src.filterFlattened { it > 2 }
        assertTrue(result is IntList)
        assertEquals(2, result.size)
        assertEquals(3, result[0])
        assertEquals(4, result[1])
    }


    @Test
    fun `mapFlattened to LongList from nested lists`() {
        val src = listOf(
            listOf("1", "2"),
            listOf("3")
        )
        val result = src.mapFlattened { it.toLong() }
        assertTrue(result is LongList)
        assertEquals(3, result.size)
        assertEquals(1L, result[0])
        assertEquals(2L, result[1])
        assertEquals(3L, result[2])
    }

    @Test
    fun `filterFlattened from nested Long lists`() {
        val src = listOf(
            listOf(1L, 2L),
            listOf(3L, 4L)
        )
        val result = src.filterFlattened { it > 2L }
        assertTrue(result is LongList)
        assertEquals(2, result.size)
        assertEquals(3L, result[0])
        assertEquals(4L, result[1])
    }


    @Test
    fun `mapFlattened to FloatList from nested lists`() {
        val src = listOf(
            listOf("1", "2"),
            listOf("3")
        )
        val result = src.mapFlattened { it.toFloat() }
        assertTrue(result is FloatList)
        assertEquals(3, result.size)
        assertEquals(1f, result[0])
        assertEquals(2f, result[1])
        assertEquals(3f, result[2])
    }

    @Test
    fun `filterFlattened from nested Float lists`() {
        val src = listOf(
            listOf(1f, 2f),
            listOf(3f, 4f)
        )
        val result = src.filterFlattened { it > 2f }
        assertTrue(result is FloatList)
        assertEquals(2, result.size)
        assertEquals(3f, result[0])
        assertEquals(4f, result[1])
    }


    @Test
    fun `mapFlattened to DoubleList from nested lists`() {
        val src = listOf(
            listOf("1", "2"),
            listOf("3")
        )
        val result = src.mapFlattened { it.toDouble() }
        assertTrue(result is DoubleList)
        assertEquals(3, result.size)
        assertEquals(1.0, result[0])
        assertEquals(2.0, result[1])
        assertEquals(3.0, result[2])
    }

    @Test
    fun `filterFlattened from nested Double lists`() {
        val src = listOf(
            listOf(1.0, 2.0),
            listOf(3.0, 4.0)
        )
        val result = src.filterFlattened { it > 2.0 }
        assertTrue(result is DoubleList)
        assertEquals(2, result.size)
        assertEquals(3.0, result[0])
        assertEquals(4.0, result[1])
    }


    @Test
    fun `findFlattened returns first matching element in nested list`() {
        val src = listOf(
            listOf("z", "y"),
            listOf("match", "other")
        )
        val result = src.findFlattened { it.startsWith("m") }
        assertEquals("match", result)
    }

    @Test
    fun `findFlattened returns null when not found`() {
        val src = listOf(
            listOf("a"),
            listOf("b")
        )
        val result = src.findFlattened { it == "missing" }
        assertNull(result)
    }
}
