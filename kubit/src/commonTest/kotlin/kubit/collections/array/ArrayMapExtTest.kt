package kubit.collections.array

import kotlin.test.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

class ArrayMapExtTest {

    @Test
    fun testIntArrayMapToList() {
        val array = IntArray(3) { (it + 1) }
        val result = array.map { it * 2 }
        
        assertEquals(3, result.size)
        assertEquals(2, result[0])
        assertEquals(4, result[1])
        assertEquals(6, result[2])
    }

    @Test
    fun testIntArrayMapToArray() {
        val array = IntArray(3) { (it + 1) }
        val result = array.mapArray { it * 2 }
        
        assertEquals(3, result.size)
        assertEquals(2, result[0])
        assertEquals(4, result[1])
        assertEquals(6, result[2])
    }

    @Test
    fun testLongArrayMapToList() {
        val array = LongArray(3) { (it + 1L) }
        val result = array.map { it * 2 }
        
        assertEquals(3, result.size)
        assertEquals(2L, result[0])
        assertEquals(4L, result[1])
        assertEquals(6L, result[2])
    }

    @Test
    fun testLongArrayMapToArray() {
        val array = LongArray(3) { (it + 1L) }
        val result = array.mapArray { it * 2 }
        
        assertEquals(3, result.size)
        assertEquals(2L, result[0])
        assertEquals(4L, result[1])
        assertEquals(6L, result[2])
    }

    @Test
    fun testFloatArrayMapToList() {
        val array = FloatArray(3) { (it + 1f) }
        val result = array.map { it * 2 }
        
        assertEquals(3, result.size)
        assertEquals(2f, result[0])
        assertEquals(4f, result[1])
        assertEquals(6f, result[2])
    }

    @Test
    fun testFloatArrayMapToArray() {
        val array = FloatArray(3) { (it + 1f) }
        val result = array.mapArray { it * 2 }
        
        assertEquals(3, result.size)
        assertEquals(2f, result[0])
        assertEquals(4f, result[1])
        assertEquals(6f, result[2])
    }

    @Test
    fun testDoubleArrayMapToList() {
        val array = DoubleArray(3) { (it + 1.0) }
        val result = array.map { it * 2 }
        
        assertEquals(3, result.size)
        assertEquals(2.0, result[0])
        assertEquals(4.0, result[1])
        assertEquals(6.0, result[2])
    }

    @Test
    fun testDoubleArrayMapToArray() {
        val array = DoubleArray(3) { (it + 1.0) }
        val result = array.mapArray { it * 2 }
        
        assertEquals(3, result.size)
        assertEquals(2.0, result[0])
        assertEquals(4.0, result[1])
        assertEquals(6.0, result[2])
    }

    @Test
    fun testIntArrayMapToLongList() {
        val array = IntArray(3) { (it + 1) }
        val result = array.map { it.toLong() }
        
        assertEquals(3, result.size)
        assertEquals(1L, result[0])
        assertEquals(2L, result[1])
        assertEquals(3L, result[2])
    }

    @Test
    fun testIntArrayMapToLongArray() {
        val array = IntArray(3) { (it + 1) }
        val result = array.mapArray { it.toLong() }
        
        assertEquals(3, result.size)
        assertEquals(1L, result[0])
        assertEquals(2L, result[1])
        assertEquals(3L, result[2])
    }

    @Test
    fun testIntArrayMapToFloatList() {
        val array = IntArray(3) { (it + 1) }
        val result = array.map { it.toFloat() }
        
        assertEquals(3, result.size)
        assertEquals(1f, result[0])
        assertEquals(2f, result[1])
        assertEquals(3f, result[2])
    }

    @Test
    fun testIntArrayMapToFloatArray() {
        val array = IntArray(3) { (it + 1) }
        val result = array.mapArray { it.toFloat() }
        
        assertEquals(3, result.size)
        assertEquals(1f, result[0])
        assertEquals(2f, result[1])
        assertEquals(3f, result[2])
    }

    @Test
    fun testIntArrayMapToDoubleList() {
        val array = IntArray(3) { (it + 1) }
        val result = array.map { it.toDouble() }
        
        assertEquals(3, result.size)
        assertEquals(1.0, result[0])
        assertEquals(2.0, result[1])
        assertEquals(3.0, result[2])
    }

    @Test
    fun testIntArrayMapToDoubleArray() {
        val array = IntArray(3) { (it + 1) }
        val result = array.mapArray { it.toDouble() }
        
        assertEquals(3, result.size)
        assertEquals(1.0, result[0])
        assertEquals(2.0, result[1])
        assertEquals(3.0, result[2])
    }

    @Test
    fun testLongArrayMapToIntList() {
        val array = LongArray(3) { (it + 1L) }
        val result = array.map { it.toInt() }
        
        assertEquals(3, result.size)
        assertEquals(1, result[0])
        assertEquals(2, result[1])
        assertEquals(3, result[2])
    }

    @Test
    fun testLongArrayMapToIntArray() {
        val array = LongArray(3) { (it + 1L) }
        val result = array.mapArray { it.toInt() }
        
        assertEquals(3, result.size)
        assertEquals(1, result[0])
        assertEquals(2, result[1])
        assertEquals(3, result[2])
    }

    @Test
    fun testLongArrayMapToFloatList() {
        val array = LongArray(3) { (it + 1L) }
        val result = array.map { it.toFloat() }
        
        assertEquals(3, result.size)
        assertEquals(1f, result[0])
        assertEquals(2f, result[1])
        assertEquals(3f, result[2])
    }

    @Test
    fun testLongArrayMapToFloatArray() {
        val array = LongArray(3) { (it + 1L) }
        val result = array.mapArray { it.toFloat() }
        
        assertEquals(3, result.size)
        assertEquals(1f, result[0])
        assertEquals(2f, result[1])
        assertEquals(3f, result[2])
    }

    @Test
    fun testLongArrayMapToDoubleList() {
        val array = LongArray(3) { (it + 1L) }
        val result = array.map { it.toDouble() }
        
        assertEquals(3, result.size)
        assertEquals(1.0, result[0])
        assertEquals(2.0, result[1])
        assertEquals(3.0, result[2])
    }

    @Test
    fun testLongArrayMapToDoubleArray() {
        val array = LongArray(3) { (it + 1L) }
        val result = array.mapArray { it.toDouble() }
        
        assertEquals(3, result.size)
        assertEquals(1.0, result[0])
        assertEquals(2.0, result[1])
        assertEquals(3.0, result[2])
    }

    @Test
    fun testFloatArrayMapToIntList() {
        val array = FloatArray(3) { (it + 1f) }
        val result = array.map { it.toInt() }
        
        assertEquals(3, result.size)
        assertEquals(1, result[0])
        assertEquals(2, result[1])
        assertEquals(3, result[2])
    }

    @Test
    fun testFloatArrayMapToIntArray() {
        val array = FloatArray(3) { (it + 1f) }
        val result = array.mapArray { it.toInt() }
        
        assertEquals(3, result.size)
        assertEquals(1, result[0])
        assertEquals(2, result[1])
        assertEquals(3, result[2])
    }

    @Test
    fun testFloatArrayMapToLongList() {
        val array = FloatArray(3) { (it + 1f) }
        val result = array.map { it.toLong() }
        
        assertEquals(3, result.size)
        assertEquals(1L, result[0])
        assertEquals(2L, result[1])
        assertEquals(3L, result[2])
    }

    @Test
    fun testFloatArrayMapToLongArray() {
        val array = FloatArray(3) { (it + 1f) }
        val result = array.mapArray { it.toLong() }
        
        assertEquals(3, result.size)
        assertEquals(1L, result[0])
        assertEquals(2L, result[1])
        assertEquals(3L, result[2])
    }

    @Test
    fun testFloatArrayMapToDoubleList() {
        val array = FloatArray(3) { (it + 1f) }
        val result = array.map { it.toDouble() }
        
        assertEquals(3, result.size)
        assertEquals(1.0, result[0])
        assertEquals(2.0, result[1])
        assertEquals(3.0, result[2])
    }

    @Test
    fun testFloatArrayMapToDoubleArray() {
        val array = FloatArray(3) { (it + 1f) }
        val result = array.mapArray { it.toDouble() }
        
        assertEquals(3, result.size)
        assertEquals(1.0, result[0])
        assertEquals(2.0, result[1])
        assertEquals(3.0, result[2])
    }

    @Test
    fun testDoubleArrayMapToIntList() {
        val array = DoubleArray(3) { (it + 1.0) }
        val result = array.map { it.toInt() }
        
        assertEquals(3, result.size)
        assertEquals(1, result[0])
        assertEquals(2, result[1])
        assertEquals(3, result[2])
    }

    @Test
    fun testDoubleArrayMapToIntArray() {
        val array = DoubleArray(3) { (it + 1.0) }
        val result = array.mapArray { it.toInt() }
        
        assertEquals(3, result.size)
        assertEquals(1, result[0])
        assertEquals(2, result[1])
        assertEquals(3, result[2])
    }

    @Test
    fun testDoubleArrayMapToLongList() {
        val array = DoubleArray(3) { (it + 1.0) }
        val result = array.map { it.toLong() }
        
        assertEquals(3, result.size)
        assertEquals(1L, result[0])
        assertEquals(2L, result[1])
        assertEquals(3L, result[2])
    }

    @Test
    fun testDoubleArrayMapToLongArray() {
        val array = DoubleArray(3) { (it + 1.0) }
        val result = array.mapArray { it.toLong() }
        
        assertEquals(3, result.size)
        assertEquals(1L, result[0])
        assertEquals(2L, result[1])
        assertEquals(3L, result[2])
    }

    @Test
    fun testDoubleArrayMapToFloatList() {
        val array = DoubleArray(3) { (it + 1.0) }
        val result = array.map { it.toFloat() }
        
        assertEquals(3, result.size)
        assertEquals(1f, result[0])
        assertEquals(2f, result[1])
        assertEquals(3f, result[2])
    }

    @Test
    fun testDoubleArrayMapToFloatArray() {
        val array = DoubleArray(3) { (it + 1.0) }
        val result = array.mapArray { it.toFloat() }
        
        assertEquals(3, result.size)
        assertEquals(1f, result[0])
        assertEquals(2f, result[1])
        assertEquals(3f, result[2])
    }

    @Test
    fun testGenericArrayMapToIntList() {
        val array = Array(3) { (it + 1).toString() }
        val result = array.map { it.toInt() }
        
        assertEquals(3, result.size)
        assertEquals(1, result[0])
        assertEquals(2, result[1])
        assertEquals(3, result[2])
    }

    @Test
    fun testGenericArrayMapToIntArray() {
        val array = Array(3) { (it + 1).toString() }
        val result = array.mapArray { it.toInt() }
        
        assertEquals(3, result.size)
        assertEquals(1, result[0])
        assertEquals(2, result[1])
        assertEquals(3, result[2])
    }

    @Test
    fun testGenericArrayMapToLongList() {
        val array = Array(3) { (it + 1).toString() }
        val result = array.map { it.toLong() }
        
        assertEquals(3, result.size)
        assertEquals(1L, result[0])
        assertEquals(2L, result[1])
        assertEquals(3L, result[2])
    }

    @Test
    fun testGenericArrayMapToLongArray() {
        val array = Array(3) { (it + 1).toString() }
        val result = array.mapArray { it.toLong() }
        
        assertEquals(3, result.size)
        assertEquals(1L, result[0])
        assertEquals(2L, result[1])
        assertEquals(3L, result[2])
    }

    @Test
    fun testGenericArrayMapToFloatList() {
        val array = Array(3) { (it + 1).toString() }
        val result = array.map { it.toFloat() }
        
        assertEquals(3, result.size)
        assertEquals(1f, result[0])
        assertEquals(2f, result[1])
        assertEquals(3f, result[2])
    }

    @Test
    fun testGenericArrayMapToFloatArray() {
        val array = Array(3) { (it + 1).toString() }
        val result = array.mapArray { it.toFloat() }
        
        assertEquals(3, result.size)
        assertEquals(1f, result[0])
        assertEquals(2f, result[1])
        assertEquals(3f, result[2])
    }

    @Test
    fun testGenericArrayMapToDoubleList() {
        val array = Array(3) { (it + 1).toString() }
        val result = array.map { it.toDouble() }
        
        assertEquals(3, result.size)
        assertEquals(1.0, result[0])
        assertEquals(2.0, result[1])
        assertEquals(3.0, result[2])
    }

    @Test
    fun testGenericArrayMapToDoubleArray() {
        val array = Array(3) { (it + 1).toString() }
        val result = array.mapArray { it.toDouble() }
        
        assertEquals(3, result.size)
        assertEquals(1.0, result[0])
        assertEquals(2.0, result[1])
        assertEquals(3.0, result[2])
    }
}
