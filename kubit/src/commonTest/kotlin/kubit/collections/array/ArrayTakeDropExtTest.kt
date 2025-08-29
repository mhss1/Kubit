package kubit.collections.array

import kotlin.test.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

class ArrayTakeDropExtTest {

    @Test
    fun testIntArrayTake() {
        val array = IntArray(5) { (it + 1) }

        val result1 = array.take(3)
        assertEquals(3, result1.size)
        assertEquals(1, result1[0])
        assertEquals(2, result1[1])
        assertEquals(3, result1[2])

        val result2 = array.take(0)
        assertEquals(0, result2.size)

        val result3 = array.take(10)
        assertEquals(5, result3.size)

        assertFailsWith<IllegalArgumentException> { array.take(-1) }
    }

    @Test
    fun testIntArrayTakeLast() {
        val array = IntArray(5) { (it + 1) }

        val result1 = array.takeLast(3)
        assertEquals(3, result1.size)
        assertEquals(3, result1[0])
        assertEquals(4, result1[1])
        assertEquals(5, result1[2])

        val result2 = array.takeLast(0)
        assertEquals(0, result2.size)

        val result3 = array.takeLast(10)
        assertEquals(5, result3.size)

        assertFailsWith<IllegalArgumentException> { array.takeLast(-1) }
    }

    @Test
    fun testIntArrayDrop() {
        val array = IntArray(5) { (it + 1) }

        val result1 = array.drop(2)
        assertEquals(3, result1.size)
        assertEquals(3, result1[0])
        assertEquals(4, result1[1])
        assertEquals(5, result1[2])

        val result2 = array.drop(0)
        assertEquals(5, result2.size)

        val result3 = array.drop(10)
        assertEquals(0, result3.size)

        assertFailsWith<IllegalArgumentException> { array.drop(-1) }
    }

    @Test
    fun testIntArrayDropLast() {
        val array = IntArray(5) { (it + 1) }

        val result1 = array.dropLast(2)
        assertEquals(3, result1.size)
        assertEquals(1, result1[0])
        assertEquals(2, result1[1])
        assertEquals(3, result1[2])

        val result2 = array.dropLast(0)
        assertEquals(5, result2.size)

        val result3 = array.dropLast(10)
        assertEquals(0, result3.size)

        assertFailsWith<IllegalArgumentException> { array.dropLast(-1) }
    }

    @Test
    fun testLongArrayTake() {
        val array = LongArray(5) { (it + 1L) }

        val result1 = array.take(3)
        assertEquals(3, result1.size)
        assertEquals(1L, result1[0])
        assertEquals(2L, result1[1])
        assertEquals(3L, result1[2])

        val result2 = array.take(0)
        assertEquals(0, result2.size)

        val result3 = array.take(10)
        assertEquals(5, result3.size)

        assertFailsWith<IllegalArgumentException> { array.take(-1) }
    }

    @Test
    fun testLongArrayTakeLast() {
        val array = LongArray(5) { (it + 1L) }

        val result1 = array.takeLast(3)
        assertEquals(3, result1.size)
        assertEquals(3L, result1[0])
        assertEquals(4L, result1[1])
        assertEquals(5L, result1[2])

        val result2 = array.takeLast(0)
        assertEquals(0, result2.size)

        val result3 = array.takeLast(10)
        assertEquals(5, result3.size)

        assertFailsWith<IllegalArgumentException> { array.takeLast(-1) }
    }

    @Test
    fun testLongArrayDrop() {
        val array = LongArray(5) { (it + 1L) }

        val result1 = array.drop(2)
        assertEquals(3, result1.size)
        assertEquals(3L, result1[0])
        assertEquals(4L, result1[1])
        assertEquals(5L, result1[2])

        val result2 = array.drop(0)
        assertEquals(5, result2.size)

        val result3 = array.drop(10)
        assertEquals(0, result3.size)

        assertFailsWith<IllegalArgumentException> { array.drop(-1) }
    }

    @Test
    fun testLongArrayDropLast() {
        val array = LongArray(5) { (it + 1L) }

        val result1 = array.dropLast(2)
        assertEquals(3, result1.size)
        assertEquals(1L, result1[0])
        assertEquals(2L, result1[1])
        assertEquals(3L, result1[2])

        val result2 = array.dropLast(0)
        assertEquals(5, result2.size)

        val result3 = array.dropLast(10)
        assertEquals(0, result3.size)

        assertFailsWith<IllegalArgumentException> { array.dropLast(-1) }
    }

    @Test
    fun testFloatArrayTake() {
        val array = FloatArray(5) { (it + 1f) }

        val result1 = array.take(3)
        assertEquals(3, result1.size)
        assertEquals(1f, result1[0])
        assertEquals(2f, result1[1])
        assertEquals(3f, result1[2])

        val result2 = array.take(0)
        assertEquals(0, result2.size)

        val result3 = array.take(10)
        assertEquals(5, result3.size)

        assertFailsWith<IllegalArgumentException> { array.take(-1) }
    }

    @Test
    fun testFloatArrayTakeLast() {
        val array = FloatArray(5) { (it + 1f) }

        val result1 = array.takeLast(3)
        assertEquals(3, result1.size)
        assertEquals(3f, result1[0])
        assertEquals(4f, result1[1])
        assertEquals(5f, result1[2])

        val result2 = array.takeLast(0)
        assertEquals(0, result2.size)

        val result3 = array.takeLast(10)
        assertEquals(5, result3.size)

        assertFailsWith<IllegalArgumentException> { array.takeLast(-1) }
    }

    @Test
    fun testFloatArrayDrop() {
        val array = FloatArray(5) { (it + 1f) }

        val result1 = array.drop(2)
        assertEquals(3, result1.size)
        assertEquals(3f, result1[0])
        assertEquals(4f, result1[1])
        assertEquals(5f, result1[2])

        val result2 = array.drop(0)
        assertEquals(5, result2.size)

        val result3 = array.drop(10)
        assertEquals(0, result3.size)

        assertFailsWith<IllegalArgumentException> { array.drop(-1) }
    }

    @Test
    fun testFloatArrayDropLast() {
        val array = FloatArray(5) { (it + 1f) }

        val result1 = array.dropLast(2)
        assertEquals(3, result1.size)
        assertEquals(1f, result1[0])
        assertEquals(2f, result1[1])
        assertEquals(3f, result1[2])

        val result2 = array.dropLast(0)
        assertEquals(5, result2.size)

        val result3 = array.dropLast(10)
        assertEquals(0, result3.size)

        assertFailsWith<IllegalArgumentException> { array.dropLast(-1) }
    }

    @Test
    fun testDoubleArrayTake() {
        val array = DoubleArray(5) { (it + 1.0) }

        val result1 = array.take(3)
        assertEquals(3, result1.size)
        assertEquals(1.0, result1[0])
        assertEquals(2.0, result1[1])
        assertEquals(3.0, result1[2])

        val result2 = array.take(0)
        assertEquals(0, result2.size)

        val result3 = array.take(10)
        assertEquals(5, result3.size)

        assertFailsWith<IllegalArgumentException> { array.take(-1) }
    }

    @Test
    fun testDoubleArrayTakeLast() {
        val array = DoubleArray(5) { (it + 1.0) }

        val result1 = array.takeLast(3)
        assertEquals(3, result1.size)
        assertEquals(3.0, result1[0])
        assertEquals(4.0, result1[1])
        assertEquals(5.0, result1[2])

        val result2 = array.takeLast(0)
        assertEquals(0, result2.size)

        val result3 = array.takeLast(10)
        assertEquals(5, result3.size)

        assertFailsWith<IllegalArgumentException> { array.takeLast(-1) }
    }

    @Test
    fun testDoubleArrayDrop() {
        val array = DoubleArray(5) { (it + 1.0) }

        val result1 = array.drop(2)
        assertEquals(3, result1.size)
        assertEquals(3.0, result1[0])
        assertEquals(4.0, result1[1])
        assertEquals(5.0, result1[2])

        val result2 = array.drop(0)
        assertEquals(5, result2.size)

        val result3 = array.drop(10)
        assertEquals(0, result3.size)

        assertFailsWith<IllegalArgumentException> { array.drop(-1) }
    }

    @Test
    fun testDoubleArrayDropLast() {
        val array = DoubleArray(5) { (it + 1.0) }

        val result1 = array.dropLast(2)
        assertEquals(3, result1.size)
        assertEquals(1.0, result1[0])
        assertEquals(2.0, result1[1])
        assertEquals(3.0, result1[2])

        val result2 = array.dropLast(0)
        assertEquals(5, result2.size)

        val result3 = array.dropLast(10)
        assertEquals(0, result3.size)

        assertFailsWith<IllegalArgumentException> { array.dropLast(-1) }
    }
}
