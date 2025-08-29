package kubit.collections.inplace.primitives

import kubit.collections.list.*
import kotlin.test.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a template in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

class WindowedFloatListTest {

    private fun seqList(size: Int): FloatList = MutableFloatList(size) { (it + 1).toFloat() }

    @Test
    fun windows_basic() {
        val list = seqList(10)
        val windows = WindowedFloatList(list, 3, 2, false)
        assertEquals(4, windows.size)
        assertEquals(mutableFloatListOf(1f, 2f, 3f), MutableFloatList().also { out -> windows[0].forEach { out.add(it) } })
        assertEquals(mutableFloatListOf(3f, 4f, 5f), MutableFloatList().also { out -> windows[1].forEach { out.add(it) } })
        assertEquals(mutableFloatListOf(5f, 6f, 7f), MutableFloatList().also { out -> windows[2].forEach { out.add(it) } })
        assertEquals(mutableFloatListOf(7f, 8f, 9f), MutableFloatList().also { out -> windows[3].forEach { out.add(it) } })
    }

    @Test
    fun windows_partial() {
        val list = seqList(10)
        val windows = WindowedFloatList(list, 4, 3, true)
        assertEquals(4, windows.size)
        val last = MutableFloatList().also { out -> windows[3].forEach { out.add(it) } }
        assertEquals(mutableFloatListOf(10f), last)
    }
}


