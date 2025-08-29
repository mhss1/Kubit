package kubit.collections.array

import kotlin.test.*

class BitArrayTest {

    @Test
    fun `set and get value works properly`() {
        val array = BitArray(50)

        for (i in 0 until 10) {
            assertFalse(array[i])
        }

        array[0] = true
        array[1] = true
        array[8] = true
        array[31] = true
        array[32] = true
        array[40] = true

        assertTrue(array[0])
        assertTrue(array[1])
        assertTrue(array[8])
        assertTrue(array[31])
        assertTrue(array[32])
        assertTrue(array[40])
    }

    @Test
    fun `set overwrite value correctly`() {
        val array = BitArray(10)
        array[2] = true
        assertTrue(array[2])

        array[2] = false
        assertFalse(array[2])
    }

    @Test
    fun `setTrue sets values to true correctly`() {
        val array = BitArray(50)

        for (i in 0 until 50) {
            array.setTrue(i)
        }

        for (i in 0 until 50) {
            assertTrue(array[i])
        }
    }

    @Test
    fun `setFalse sets values to false correctly`() {
        val array = BitArray(50)

        for (i in 0 until 50) {
            array.setTrue(i)
        }

        for (i in 0 until 50) {
            array.setFalse(i)
        }

        for (i in 0 until 50) {
            assertFalse(array[i])
        }
    }

    @Test
    fun `forEach iterates over all values correctly`() {
        val array = BitArray(67)

        array[1] = true
        array[6] = true
        array[12] = true
        array[31] = true
        array[32] = true
        array[45] = true
        array[55] = true
        array[66] = true

        var size = 0
        var count = 0
        array.forEach {
            if (it) count++
            size++
        }
        assertEquals(8, count)
        assertEquals(67, size)
    }

    @Test
    fun `forEach iterates over all values correctly with size multiple of 32`() {
        val array = BitArray(64)

        array[1] = true
        array[6] = true
        array[12] = true
        array[31] = true
        array[32] = true
        array[45] = true
        array[55] = true
        array[63] = true

        var size = 0
        var count = 0
        array.forEach {
            if (it) count++
            size++
        }
        assertEquals(8, count)
        assertEquals(64, size)
    }
    
    @Test
    fun `forEach iterates correctly over empty array`() {
        val array = BitArray(0)
        var count = 0
        array.forEach {
            count++
        }
        assertEquals(0, count)
    }

    @Test
    fun `forEachIndexed iterates with correct index and value`() {
        val bitArray = BitArray(10).apply {
            this[0] = true
            this[8] = true
        }
        var count = 0
        bitArray.forEachIndexed { index, value ->
            when (index) {
                0, 8 -> assertTrue(value)
                else -> assertFalse(value)
            }
            count++
        }
        assertEquals(bitArray.size, count)
    }

    @Test
    fun `get throws IndexOutOfBoundsException for invalid indices`() {
        val array = BitArray(33)

        assertFailsWith<IndexOutOfBoundsException> { array[-1] }
        assertFailsWith<IndexOutOfBoundsException> { array[50] }
        assertFailsWith<IndexOutOfBoundsException> { array[-22] }
        assertFailsWith<IndexOutOfBoundsException> { array[33] }
    }

    @Test
    fun `large array size works correctly`() {
        val array = BitArray(1000)

        for (i in 0 until 1000) {
            array[i] = i % 2 == 0
        }

        for (i in 0 until 1000) {
            assertEquals(i % 2 == 0, array[i])
        }
    }

    @Test
    fun `different sizes work correctly`() {
        for (size in 1..100) {
            val array = BitArray(size)
            for (i in 0 until size) {
                array[i] = i % 2 == 0
            }
            for (i in 0 until size) {
                assertEquals(i % 2 == 0, array[i])
            }
        }
    }

    @Test
    fun `allTrue returns true only when all values are true`() {
        val array = BitArray(40)

        for (i in 0 until 40) {
            array[i] = true
        }

        assertTrue(array.allTrue())

        array[35] = false
        assertFalse(array.allTrue())
    }

    @Test
    fun `allTrue works correctly for size multiple of 32`() {
        val array = BitArray(64)

        for (i in 0 until 64) {
            array[i] = true
        }

        assertTrue(array.allTrue())

        array[40] = false
        assertFalse(array.allTrue())
    }

    @Test
    fun `allTrue returns true for empty array`() {
        val array = BitArray(0)
        assertTrue(array.allTrue())
    }

    @Test
    fun `allFalse returns true only when all values are false`() {
        val array = BitArray(50)
        assertTrue(array.allFalse())

        array[40] = true
        assertFalse(array.allFalse())
    }

    @Test
    fun `anyTrue returns true if any value is true`() {
        val array = BitArray(50)

        assertFalse(array.anyTrue())

        array[40] = true

        assertTrue(array.anyTrue())
    }

    @Test
    fun `anyFalse returns true if any value is false`() {
        val array = BitArray(50)

        array[40] = true

        assertTrue(array.anyFalse())

        for (i in 0 until 50) {
            array[i] = true
        }

        assertFalse(array.anyFalse())
    }
    
    @Test
    fun `noneTrue returns true when no bits are true`() {
        val array = BitArray(20)
        // Initially all false
        assertTrue(array.noneTrue())

        array[5] = true
        assertFalse(array.noneTrue())

        array[5] = false
        assertTrue(array.noneTrue())
    }

    @Test
    fun `noneFalse returns true when no bits are false`() {
        val array = BitArray(20)

        assertFalse(array.noneFalse())

        for (i in 0 until array.size) array[i] = true
        assertTrue(array.noneFalse())

        array[10] = false
        assertFalse(array.noneFalse())
    }

    @Test
    fun `clear sets all values to false`() {
        val array = BitArray(5)
        array[0] = true
        array[2] = true
        array.clear()
        for (i in 0 until array.size) {
            assertFalse(array[i])
        }
    }

    @Test
    fun `copyOf creates a copy of the array`() {
        val array = BitArray(55)
        array[0] = true
        array[1] = true
        array[31] = true
        array[32] = true
        array[43] = true
        val copy = array.copyOf()
        assertEquals(array.size, copy.size)
        for (i in 0 until array.size) {
            assertEquals(array[i], copy[i])
        }

        array[40] = true
        assertFalse(copy[40])
    }

    @Test
    fun `to boolean array returns BooleanArray with same values`() {
        val array = BitArray(55)
        array[0] = true
        array[1] = true
        array[31] = true
        array[32] = true
        array[43] = true
        val booleanArray = array.toBooleanArray()
        assertEquals(array.size, booleanArray.size)
        for (i in 0 until array.size) {
            assertEquals(array[i], booleanArray[i])
        }
    }

    @Test
    fun `count works correctly`() {
        val array = BitArray(40)
        array[0] = true
        array[1] = true
        array[9] = true
        array[18] = true
        array[36] = true

        assertEquals(5, array.count(true))
        assertEquals(35, array.count(false))

        array[0] = false
        assertEquals(4, array.count(true))
        assertEquals(36, array.count(false))
    }

    @Test
    fun `indices returns correct range`() {
        val array = BitArray(50)
        assertEquals(0..49, array.indices)
    }

    @Test
    fun `isEmpty returns true for empty array`() {
        var array = BitArray(0)
        assertTrue(array.isEmpty())
        array = BitArray(10)
        assertFalse(array.isEmpty())
    }

    @Test
    fun `set within single page sets bits inclusively`() {
        val array = BitArray(50)
        array.set(5, 9, true)
        for (i in 0 until 50) {
            if (i in 5..9) assertTrue(array[i]) else assertFalse(array[i])
        }
    }

    @Test
    fun `set across pages sets bits correctly`() {
        val array = BitArray(70)
        array.set(28, 35, true)
        for (i in 0 until 70) {
            if (i in 28..35) assertTrue(array[i]) else assertFalse(array[i])
        }
    }

    @Test
    fun `set range overload works with inclusive indices`() {
        val array = BitArray(70)
        array.set(0..0, true)
        assertTrue(array[0])
        for (i in 1 until 70) assertFalse(array[i])

        array.set(31..32, true)
        assertTrue(array[31])
        assertTrue(array[32])
        for (i in 0 until 70) {
            if (i !in setOf(0, 31, 32)) assertFalse(array[i])
        }
    }

    @Test
    fun `set false clears bits in range`() {
        val array = BitArray(80)
        array.set(0, 79, true)
        array.set(12, 63, false)
        for (i in 0 until 80) {
            if (i in 12..63) assertFalse(array[i]) else assertTrue(array[i])
        }
    }

    @Test
    fun `set does nothing when from greater than to`() {
        val array = BitArray(40)
        array[10] = true
        array[20] = true
        array.set(25, 10, true)
        assertTrue(array[10])
        assertTrue(array[20])
        for (i in 0 until 40) {
            if (i !in setOf(10, 20)) assertFalse(array[i])
        }
    }

    @Test
    fun `set range does nothing for empty range`() {
        val array = BitArray(40)
        array[5] = true
        array.set(IntRange.EMPTY, true)
        assertTrue(array[5])
        for (i in 0 until 40) {
            if (i != 5) assertFalse(array[i])
        }
    }

    @Test
    fun `set throws IndexOutOfBoundsException for invalid bounds`() {
        val array = BitArray(10)
        assertFailsWith<IndexOutOfBoundsException> { array.set(-1, 0, true) }
        assertFailsWith<IndexOutOfBoundsException> { array.set(0, 10, true) }
        assertFailsWith<IndexOutOfBoundsException> { array.set(-2, -1, true) }
        assertFailsWith<IndexOutOfBoundsException> { array.set(5, 15, true) }
    }

    @Test
    fun `set range throws IndexOutOfBoundsException for invalid bounds`() {
        val array = BitArray(10)
        assertFailsWith<IndexOutOfBoundsException> { array.set((-1)..5, true) }
        assertFailsWith<IndexOutOfBoundsException> { array.set(0..10, true) }
    }
    
    @Test
    fun `fill fills entire range by default`() {
        val array = BitArray(20)
        array.fill(true)
        for (i in 0 until 20) assertTrue(array[i])

        array.fill(false)
        for (i in 0 until 20) assertFalse(array[i])
    }

    @Test
    fun `fill fills subrange correctly inclusive-exclusive`() {
        val array = BitArray(20)
        array.fill(true, fromIndex = 5, toIndex = 10) // should set 5..9
        for (i in 0 until 20) {
            if (i in 5 until 10) assertTrue(array[i]) else assertFalse(array[i])
        }

        // flip a different subrange to false
        array.fill(false, fromIndex = 7, toIndex = 9) // should clear 7..8
        for (i in 0 until 20) {
            when (i) {
                5,6,9-> assertTrue(array[i])
                7,8 -> assertFalse(array[i])
                else -> assertFalse(array[i])
            }
        }
    }

    @Test
    fun `fill works at boundaries and across pages`() {
        val size = 32 + 10
        val array = BitArray(size)
        array.fill(true, fromIndex = 30, toIndex = size) // from near end of first page to end
        for (i in 0 until size) {
            if (i >= 30) assertTrue(array[i]) else assertFalse(array[i])
        }

        // clear a range that spans multiple pages
        array.fill(false, fromIndex = 0, toIndex = 35)
        for (i in 0 until size) {
            if (i >= 35) assertTrue(array[i]) else assertFalse(array[i])
        }
    }

    @Test
    fun `fill with zero-length range does nothing`() {
        val array = BitArray(15)
        array[5] = true
        array.fill(false, fromIndex = 3, toIndex = 3) // empty range
        assertTrue(array[5])
        for (i in 0 until 15) {
            if (i != 5) assertFalse(array[i])
        }
    }

    @Test
    fun `fill throws IndexOutOfBoundsException for invalid bounds`() {
        val array = BitArray(10)
        assertFailsWith<IndexOutOfBoundsException> { array.fill(true, fromIndex = -1, toIndex = 5) }
        assertFailsWith<IndexOutOfBoundsException> { array.fill(true, fromIndex = 0, toIndex = 11) }
        assertFailsWith<IndexOutOfBoundsException> { array.fill(true, fromIndex = -2, toIndex = -1) }
    }

    @Test
    fun `or returns union and orInPlace mutates correctly`() {
        val size = 35
        val a = BitArray(size)
        val b = BitArray(size)
        listOf(0, 2, 31, 32).forEach { a[it] = true }
        listOf(1, 2, 33, 34).forEach { b[it] = true }

        val c = a or b
        for (i in 0 until size) {
            val expected = i in setOf(0, 1, 2, 31, 32, 33, 34)
            assertEquals(expected, c[i])
        }

        val aBefore = BitArray(size).apply { listOf(0, 2, 31, 32).forEach { this[it] = true } }
        val bBefore = BitArray(size).apply { listOf(1, 2, 33, 34).forEach { this[it] = true } }
        assertTrue(a.contentEquals(aBefore))
        assertTrue(b.contentEquals(bBefore))

        a orInPlace b
        assertTrue(a.contentEquals(c))
        assertTrue(b.contentEquals(bBefore))
    }

    @Test
    fun `and returns intersection and andInPlace mutates correctly`() {
        val size = 35
        val a = BitArray(size)
        val b = BitArray(size)
        listOf(0, 2, 31, 32).forEach { a[it] = true }
        listOf(2, 32, 34).forEach { b[it] = true }

        val c = a and b
        for (i in 0 until size) {
            val expected = i in setOf(2, 32)
            assertEquals(expected, c[i])
        }

        val aBefore = BitArray(size).apply { listOf(0, 2, 31, 32).forEach { this[it] = true } }
        val bBefore = BitArray(size).apply { listOf(2, 32, 34).forEach { this[it] = true } }
        assertTrue(a.contentEquals(aBefore))
        assertTrue(b.contentEquals(bBefore))

        a andInPlace b
        assertTrue(a.contentEquals(c))
        assertTrue(b.contentEquals(bBefore))
    }

    @Test
    fun `xor returns symmetric difference and xorInPlace mutates correctly`() {
        val size = 35
        val a = BitArray(size)
        val b = BitArray(size)
        listOf(0, 2, 31, 32).forEach { a[it] = true }
        listOf(2, 33).forEach { b[it] = true }

        val c = a xor b
        for (i in 0 until size) {
            val expected = i in setOf(0, 31, 32, 33)
            assertEquals(expected, c[i])
        }

        val aBefore = BitArray(size).apply { listOf(0, 2, 31, 32).forEach { this[it] = true } }
        val bBefore = BitArray(size).apply { listOf(2, 33).forEach { this[it] = true } }
        assertTrue(a.contentEquals(aBefore))
        assertTrue(b.contentEquals(bBefore))

        a xorInPlace b
        assertTrue(a.contentEquals(c))
        assertTrue(b.contentEquals(bBefore))
    }

    @Test
    fun `nand returns inverse of and masked and nandInPlace mutates correctly`() {
        val size = 35
        val a = BitArray(size)
        val b = BitArray(size)
        listOf(2, 32).forEach { a[it] = true }
        listOf(2, 32).forEach { b[it] = true }

        val c = a nand b
        assertFalse(c[2])
        assertFalse(c[32])
        assertEquals(size - 2, c.count(true))

        val aBefore = BitArray(size).apply { listOf(2, 32).forEach { this[it] = true } }
        val bBefore = BitArray(size).apply { listOf(2, 32).forEach { this[it] = true } }
        assertTrue(a.contentEquals(aBefore))
        assertTrue(b.contentEquals(bBefore))

        a nandInPlace b
        assertEquals(size - 2, a.count(true))
        assertTrue(b.contentEquals(bBefore))
    }

    @Test
    fun `nor returns inverse of or masked and norInPlace mutates correctly`() {
        val size = 8
        val a = BitArray(size)
        val b = BitArray(size)
        listOf(0, 5).forEach { a[it] = true }
        listOf(5).forEach { b[it] = true }

        val c = a nor b
        for (i in 0 until size) {
            val expected = i !in setOf(0, 5)
            assertEquals(expected, c[i])
        }

        val aBefore = BitArray(size).apply { listOf(0, 5).forEach { this[it] = true } }
        val bBefore = BitArray(size).apply { listOf(5).forEach { this[it] = true } }
        assertTrue(a.contentEquals(aBefore))
        assertTrue(b.contentEquals(bBefore))

        a norInPlace b
        for (i in 0 until size) {
            val expected = i !in setOf(0, 5)
            assertEquals(expected, a[i])
        }
        assertTrue(b.contentEquals(bBefore))
    }

    @Test
    fun `intersects reports overlap correctly`() {
        val size = 35
        val a = BitArray(size)
        val b = BitArray(size)
        listOf(1, 10).forEach { a[it] = true }
        listOf(10, 20).forEach { b[it] = true }
        assertTrue(a intersects b)

        val c = BitArray(size)
        val d = BitArray(size)
        listOf(0, 5, 34).forEach { c[it] = true }
        listOf(1, 6, 33).forEach { d[it] = true }
        assertFalse(c intersects d)
    }

    @Test
    fun `subset and superset are evaluated correctly`() {
        val size = 35
        val a = BitArray(size)
        val b = BitArray(size)
        listOf(1, 2).forEach { a[it] = true }
        listOf(1, 2, 30).forEach { b[it] = true }

        assertTrue(a isSubsetOf b)
        assertTrue(b isSupersetOf a)
        assertFalse(b isSubsetOf a)
        assertFalse(a isSupersetOf b)

        val c = BitArray(size)
        val d = BitArray(size)
        listOf(3, 4, 5).forEach { c[it] = true }
        listOf(3, 4, 5).forEach { d[it] = true }
        assertTrue(c isSubsetOf d)
        assertTrue(c isSupersetOf d)
    }

    @Test
    fun `contentEquals compares arrays correctly`() {
        val size = 33
        val a = BitArray(size)
        val b = BitArray(size)
        listOf(0, 1, 31, 32).forEach { a[it] = true }
        listOf(0, 1, 31, 32).forEach { b[it] = true }
        assertTrue(a.contentEquals(b))

        b[1] = false
        assertFalse(a.contentEquals(b))

        val emptyA = BitArray(0)
        val emptyB = BitArray(0)
        assertTrue(emptyA.contentEquals(emptyB))
    }

    @Test
    fun `contains in operator works for true and false`() {
        val size = 10
        val a = BitArray(size)
        assertTrue(false in a)
        assertFalse(true in a)

        a[3] = true
        assertTrue(true in a)
        assertTrue(false in a)

        for (i in 0 until size) a[i] = true
        assertTrue(true in a)
        assertFalse(false in a)
    }

    @Test
    fun `operations throw IllegalArgumentException on size mismatch`() {
        val a = BitArray(8)
        val b = BitArray(9)

        assertFailsWith<IllegalArgumentException> { a or b }
        assertFailsWith<IllegalArgumentException> { a and b }
        assertFailsWith<IllegalArgumentException> { a xor b }
        assertFailsWith<IllegalArgumentException> { a nand b }
        assertFailsWith<IllegalArgumentException> { a nor b }

        assertFailsWith<IllegalArgumentException> { a orInPlace b }
        assertFailsWith<IllegalArgumentException> { a andInPlace b }
        assertFailsWith<IllegalArgumentException> { a xorInPlace b }
        assertFailsWith<IllegalArgumentException> { a nandInPlace b }
        assertFailsWith<IllegalArgumentException> { a norInPlace b }

        assertFailsWith<IllegalArgumentException> { a intersects b }
        assertFailsWith<IllegalArgumentException> { a isSubsetOf b }
        assertFailsWith<IllegalArgumentException> { a isSupersetOf b }
    }
}
