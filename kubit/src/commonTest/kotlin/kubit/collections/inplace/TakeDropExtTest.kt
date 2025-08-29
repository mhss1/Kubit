package kubit.collections.inplace

import kotlin.test.*

class TakeDropExtTest {

    private val list = (1..20).toList()

    @Test
    fun `takeInPlace with valid n`() {
        val sublist = list.takeInPlace(5)
        assertEquals(listOf(1, 2, 3, 4, 5), sublist)
    }

    @Test
    fun `takeInPlace with n greater than size returns same list`() {
        val sublist = list.takeInPlace(25)
        assertEquals(list, sublist)
    }

    @Test
    fun `takeInPlace with n equals zero returns empty list`() {
        assertTrue(list.takeInPlace(0).isEmpty())
    }

    @Test
    fun `takeLastInPlace with valid n`() {
        val sublist = list.takeLastInPlace(5)
        assertEquals(listOf(16, 17, 18, 19, 20), sublist)
    }

    @Test
    fun `takeLastInPlace with n greater than size returns same list`() {
        val sublist = list.takeLastInPlace(25)
        assertEquals(list, sublist)
    }

    @Test
    fun `takeLastInPlace with n equals zero returns empty list`() {
        assertTrue(list.takeLastInPlace(0).isEmpty())
    }

    @Test
    fun `dropInPlace with valid n`() {
        val sublist = list.dropInPlace(5)
        assertEquals((6..20).toList(), sublist)
    }

    @Test
    fun `dropInPlace with n greater than size returns empty list`() {
        val sublist = list.dropInPlace(25)
        assertTrue(sublist.isEmpty())
    }

    @Test
    fun `dropInPlace with n equals zero returns same list`() {
        val sublist = list.dropInPlace(0)
        assertEquals(list, sublist)
    }

    @Test
    fun `dropLastInPlace with valid n`() {
        val sublist = list.dropLastInPlace(5)
        assertEquals((1..15).toList(), sublist)
    }

    @Test
    fun `dropLastInPlace with n greater than size returns empty list`() {
        val sublist = list.dropLastInPlace(25)
        assertTrue(sublist.isEmpty())
    }

    @Test
    fun `dropLastInPlace with n equals zero returns empty list`() {
        val sublist = list.dropLastInPlace(0)
        assertEquals(list, sublist)
    }

    @Test
    fun `takeInPlace negative n throws IllegalArgumentException`() {
        assertFailsWith<IllegalArgumentException> { list.takeInPlace(-5) }
    }

    @Test
    fun `takeLastInPlace negative n throws IllegalArgumentException`() {
        assertFailsWith<IllegalArgumentException> { list.takeLastInPlace(-5) }
    }

    @Test
    fun `dropInPlace negative n throws IllegalArgumentException`() {
        assertFailsWith<IllegalArgumentException> { list.dropInPlace(-5) }
    }

    @Test
    fun `dropLastInPlace negative n throws IllegalArgumentException`() {
        assertFailsWith<IllegalArgumentException> { list.dropLastInPlace(-5) }
    }
}