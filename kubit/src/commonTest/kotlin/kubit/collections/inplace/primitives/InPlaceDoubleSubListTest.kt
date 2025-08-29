package kubit.collections.inplace.primitives

import kubit.collections.list.*
import kotlin.test.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a template in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

class InPlaceDoubleSubListTest {

    @Test
    fun basic_access_and_iteration() {
        val list = MutableDoubleList(5) { (it + 1).toDouble() }
        val sub = InPlaceDoubleSubList(list, 1, 3)
        assertEquals(3, sub.size)
        assertEquals(2.0, sub[0])
        assertEquals(3.0, sub[1])
        assertEquals(4.0, sub[2])

        val out = MutableDoubleList()
        sub.forEach { out.add(it) }
        assertEquals(mutableDoubleListOf(2.0, 3.0, 4.0), out)

        val indexed = mutableListOf<Pair<Int, Double>>()
        sub.forEachIndexed { i, v -> indexed.add(i to v) }
        assertEquals(listOf(0 to 2.0, 1 to 3.0, 2 to 4.0), indexed)
    }

    @Test
    fun invalid_indices_throw() {
        val list = MutableDoubleList(5) { (it + 1).toDouble() }
        assertFailsWith<IllegalArgumentException> { InPlaceDoubleSubList(list, -1, 2) }
        assertFailsWith<IllegalArgumentException> { InPlaceDoubleSubList(list, 1, 10) }
        assertFailsWith<IllegalArgumentException> { InPlaceDoubleSubList(list, 3, 2) }
    }
}


