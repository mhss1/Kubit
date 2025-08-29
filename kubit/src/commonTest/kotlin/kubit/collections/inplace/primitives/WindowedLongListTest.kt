package kubit.collections.inplace.primitives

import kubit.collections.list.*
import kotlin.test.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a template in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

class WindowedLongListTest {

    private fun seqList(size: Int): LongList = MutableLongList(size) { (it + 1).toLong() }

    @Test
    fun windows_basic() {
        val list = seqList(10)
        val windows = WindowedLongList(list, 3, 2, false)
        assertEquals(4, windows.size)
        assertEquals(mutableLongListOf(1L, 2L, 3L), MutableLongList().also { out -> windows[0].forEach { out.add(it) } })
        assertEquals(mutableLongListOf(3L, 4L, 5L), MutableLongList().also { out -> windows[1].forEach { out.add(it) } })
        assertEquals(mutableLongListOf(5L, 6L, 7L), MutableLongList().also { out -> windows[2].forEach { out.add(it) } })
        assertEquals(mutableLongListOf(7L, 8L, 9L), MutableLongList().also { out -> windows[3].forEach { out.add(it) } })
    }

    @Test
    fun windows_partial() {
        val list = seqList(10)
        val windows = WindowedLongList(list, 4, 3, true)
        assertEquals(4, windows.size)
        val last = MutableLongList().also { out -> windows[3].forEach { out.add(it) } }
        assertEquals(mutableLongListOf(10L), last)
    }
}


