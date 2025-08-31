package kubit.collections.inplace.primitives

import kubit.collections.list.*
import kotlin.test.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a template in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

class InPlaceFloatSubListTest {

    @Test
    fun basic_access_and_iteration() {
        val list = MutableFloatList(5) { (it + 1).toFloat() }
        val sub = InPlaceFloatSubList(list, 1, 4)
        assertEquals(3, sub.size)
        assertEquals(2f, sub[0])
        assertEquals(3f, sub[1])
        assertEquals(4f, sub[2])

        val out = MutableFloatList()
        sub.forEach { out.add(it) }
        assertEquals(mutableFloatListOf(2f, 3f, 4f), out)

        val indexed = mutableListOf<Pair<Int, Float>>()
        sub.forEachIndexed { i, v -> indexed.add(i to v) }
        assertEquals(listOf(0 to 2f, 1 to 3f, 2 to 4f), indexed)
    }

    @Test
    fun invalid_indices_throw() {
        val list = MutableFloatList(5) { (it + 1).toFloat() }
        assertFailsWith<IllegalArgumentException> { InPlaceFloatSubList(list, -1, 2) }
        assertFailsWith<IllegalArgumentException> { InPlaceFloatSubList(list, 1, 10) }
        assertFailsWith<IllegalArgumentException> { InPlaceFloatSubList(list, 3, 2) }
    }
}


