package kubit.collections.inplace.primitives

import kubit.collections.list.*
import kotlin.test.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a template in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

class InPlaceIntSubListTest {

    @Test
    fun basic_access_and_iteration() {
        val list = MutableIntList(5) { (it + 1).toInt() }
        val sub = InPlaceIntSubList(list, 1, 3)
        assertEquals(3, sub.size)
        assertEquals(2, sub[0])
        assertEquals(3, sub[1])
        assertEquals(4, sub[2])

        val out = MutableIntList()
        sub.forEach { out.add(it) }
        assertEquals(mutableIntListOf(2, 3, 4), out)

        val indexed = mutableListOf<Pair<Int, Int>>()
        sub.forEachIndexed { i, v -> indexed.add(i to v) }
        assertEquals(listOf(0 to 2, 1 to 3, 2 to 4), indexed)
    }

    @Test
    fun invalid_indices_throw() {
        val list = MutableIntList(5) { (it + 1).toInt() }
        assertFailsWith<IllegalArgumentException> { InPlaceIntSubList(list, -1, 2) }
        assertFailsWith<IllegalArgumentException> { InPlaceIntSubList(list, 1, 10) }
        assertFailsWith<IllegalArgumentException> { InPlaceIntSubList(list, 3, 2) }
    }
}


