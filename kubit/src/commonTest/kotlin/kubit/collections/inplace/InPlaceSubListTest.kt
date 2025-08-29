package kubit.collections.inplace
import kotlin.test.*

class InPlaceSubListTest {

    @Test
    fun `size is calculated correctly`() {
        val list = List(100) { it }
        val sublist = InPlaceSubList(list, 10, 50)
        assertEquals(41, sublist.size)
    }

    @Test
    fun `get retrieves correct elements`() {
        val list = List(100) { it }
        val sublist = InPlaceSubList(list, 10, 50)
        assertEquals(10, sublist[0])
        assertEquals(30, sublist[20])
        assertEquals(50, sublist[40])
    }

    @Test
    fun `get throws IndexOutOfBoundsException for invalid index`() {
        val list = List(50) { it }
        val sublist = InPlaceSubList(list, 10, 30)
        assertFailsWith<IndexOutOfBoundsException> { sublist[-1] }
        assertFailsWith<IndexOutOfBoundsException> { sublist[21] }
    }


    @Test
    fun `fastForEach iterates over correct elements`() {
        val list = List(100) { it }
        val sublist = InPlaceSubList(list, 10, 20)
        val collected = mutableListOf<Int>()
        sublist.fastForEach { collected.add(it) }
        assertEquals(List(11) { it + 10 }, collected)
    }

    @Test
    fun `fastForEachIndexed iterates with correct indices`() {
        val list = List(100) { it }
        val sublist = InPlaceSubList(list, 10, 20)
        sublist.fastForEachIndexed { i, value ->
            assertEquals(i + 10, value)
        }
    }


    @Test
    fun `sublist with end index equal to start index has size 1`() {
        val list = List(50) { it }
        val sublist = InPlaceSubList(list, 10, 10)
        assertEquals(1, sublist.size)
        assertEquals(10, sublist[0])
    }

    @Test
    fun `sublist covering entire original list`() {
        val list = List(50) { it }
        val sublist = InPlaceSubList(list, 0, 49)
        assertEquals(50, sublist.size)
        sublist.fastForEachIndexed { i, value ->
            assertEquals(i, value)
        }
    }

    @Test
    fun `throws IllegalArgumentException for invalid start index`() {
        val list = List(50) { it }
        assertFailsWith<IllegalArgumentException> { InPlaceSubList(list, -1, 10) }
    }

    @Test
    fun `throws IllegalArgumentException for invalid end index`() {
        val list = List(50) { it }
        assertFailsWith<IllegalArgumentException> { InPlaceSubList(list, 10, 51) }
    }

    @Test
    fun `throws IllegalArgumentException for start index greater than end index`() {
        val list = List(50) { it }
        assertFailsWith<IllegalArgumentException> { InPlaceSubList(list, 10, 9) }
    }
}
