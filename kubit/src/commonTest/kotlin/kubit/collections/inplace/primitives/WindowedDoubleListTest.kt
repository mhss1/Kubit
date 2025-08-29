package kubit.collections.inplace.primitives

import kubit.collections.list.*
import kotlin.test.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a template in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

class WindowedDoubleListTest {

    private fun seqList(size: Int): DoubleList = MutableDoubleList(size) { (it + 1).toDouble() }

    @Test
    fun windows_basic() {
        val list = seqList(10)
        val windows = WindowedDoubleList(list, 3, 2, false)
        assertEquals(4, windows.size)
        assertEquals(mutableDoubleListOf(1.0, 2.0, 3.0), MutableDoubleList().also { out -> windows[0].forEach { out.add(it) } })
        assertEquals(mutableDoubleListOf(3.0, 4.0, 5.0), MutableDoubleList().also { out -> windows[1].forEach { out.add(it) } })
        assertEquals(mutableDoubleListOf(5.0, 6.0, 7.0), MutableDoubleList().also { out -> windows[2].forEach { out.add(it) } })
        assertEquals(mutableDoubleListOf(7.0, 8.0, 9.0), MutableDoubleList().also { out -> windows[3].forEach { out.add(it) } })
    }

    @Test
    fun windows_partial() {
        val list = seqList(10)
        val windows = WindowedDoubleList(list, 4, 3, true)
        assertEquals(4, windows.size)
        val last = MutableDoubleList().also { out -> windows[3].forEach { out.add(it) } }
        assertEquals(mutableDoubleListOf(10.0), last)
    }
}


