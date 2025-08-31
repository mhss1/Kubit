package kubit.collections.inplace.primitives

import kubit.collections.list.*
import kotlin.test.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a template in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

class InPlaceLongSubListTest {

    @Test
    fun basic_access_and_iteration() {
        val list = MutableLongList(5) { (it + 1).toLong() }
        val sub = InPlaceLongSubList(list, 1, 4)
        assertEquals(3, sub.size)
        assertEquals(2L, sub[0])
        assertEquals(3L, sub[1])
        assertEquals(4L, sub[2])

        val out = MutableLongList()
        sub.forEach { out.add(it) }
        assertEquals(mutableLongListOf(2L, 3L, 4L), out)

        val indexed = mutableListOf<Pair<Int, Long>>()
        sub.forEachIndexed { i, v -> indexed.add(i to v) }
        assertEquals(listOf(0 to 2L, 1 to 3L, 2 to 4L), indexed)
    }

    @Test
    fun invalid_indices_throw() {
        val list = MutableLongList(5) { (it + 1).toLong() }
        assertFailsWith<IllegalArgumentException> { InPlaceLongSubList(list, -1, 2) }
        assertFailsWith<IllegalArgumentException> { InPlaceLongSubList(list, 1, 10) }
        assertFailsWith<IllegalArgumentException> { InPlaceLongSubList(list, 3, 2) }
    }
}


