package kubit.collections.inplace.primitives

import kubit.collections.list.*
import kotlin.test.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a template in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

class WindowedIntListTest {

    private fun seqList(size: Int): IntList = MutableIntList(size) { (it + 1).toInt() }

    @Test
    fun windows_basic() {
        val list = seqList(10)
        val windows = WindowedIntList(list, 3, 2, false)
        assertEquals(4, windows.size)
        assertEquals(mutableIntListOf(1, 2, 3), MutableIntList().also { out -> windows[0].forEach { out.add(it) } })
        assertEquals(mutableIntListOf(3, 4, 5), MutableIntList().also { out -> windows[1].forEach { out.add(it) } })
        assertEquals(mutableIntListOf(5, 6, 7), MutableIntList().also { out -> windows[2].forEach { out.add(it) } })
        assertEquals(mutableIntListOf(7, 8, 9), MutableIntList().also { out -> windows[3].forEach { out.add(it) } })
    }

    @Test
    fun windows_partial() {
        val list = seqList(10)
        val windows = WindowedIntList(list, 4, 3, true)
        assertEquals(4, windows.size)
        val last = MutableIntList().also { out -> windows[3].forEach { out.add(it) } }
        assertEquals(mutableIntListOf(10), last)
    }
}


