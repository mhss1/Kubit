/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kubit.collections.list

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue
import kotlinx.serialization.json.Json

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to the kotlin source file.
//
// This file was generated from a template in the template directory.
// Make a change to the original template and run the generateCollections.sh script
// to ensure the change is available on all versions of the map.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

@Suppress("ReplaceGetOrSet", "RemoveRedundantCallsOfConversionMethods")
class FloatListTest {
    private val list: MutableFloatList = mutableFloatListOf(1f, 2f, 3f, 4f, 5f)

    @Test
    fun emptyConstruction() {
        val l = mutableFloatListOf()
        assertEquals(0, l.size)
        assertEquals(16, l.capacity)
    }

    @Test
    fun sizeConstruction() {
        val l = MutableFloatList(4)
        assertEquals(4, l.capacity)
    }

    @Test
    fun contentConstruction() {
        val l = mutableFloatListOf(1f, 2f, 3f)
        assertEquals(3, l.size)
        assertEquals(1f, l[0])
        assertEquals(2f, l[1])
        assertEquals(3f, l[2])
        assertEquals(3, l.capacity)
        repeat(2) {
            val l2 = mutableFloatListOf(1f, 2f, 3f, 4f, 5f)
            assertEquals(list, l2)
            l2.removeAt(0)
        }
    }

    @Test
    fun hashCodeTest() {
        val l2 = mutableFloatListOf(1f, 2f, 3f, 4f, 5f)
        assertEquals(list.hashCode(), l2.hashCode())
        l2.removeAt(4)
        assertNotEquals(list.hashCode(), l2.hashCode())
        l2.add(5f)
        assertEquals(list.hashCode(), l2.hashCode())
        l2.clear()
        assertNotEquals(list.hashCode(), l2.hashCode())
    }

    @Test
    fun equalsTest() {
        val l2 = mutableFloatListOf(1f, 2f, 3f, 4f, 5f)
        assertEquals(list, l2)
        assertNotEquals(list, mutableFloatListOf())
        l2.removeAt(4)
        assertNotEquals(list, l2)
        l2.add(5f)
        assertEquals(list, l2)
        l2.clear()
        assertNotEquals(list, l2)
    }

    @Test
    fun string() {
        assertEquals("[${1f}, ${2f}, ${3f}, ${4f}, ${5f}]", list.toString())
        assertEquals("[]", mutableFloatListOf().toString())
    }

    @Test
    fun joinToString() {
        assertEquals("${1f}, ${2f}, ${3f}, ${4f}, ${5f}", list.joinToString())
        assertEquals(
            "x${1f}, ${2f}, ${3f}, ...y",
            list.joinToString(prefix = "x", postfix = "y", limit = 3)
        )
        assertEquals(
            ">${1f}-${2f}-${3f}-${4f}-${5f}<",
            list.joinToString(separator = "-", prefix = ">", postfix = "<")
        )
        assertEquals("one, two, three, ...", list.joinToString(limit = 3) {
            when (it) {
                1f -> "one"
                2f -> "two"
                3f -> "three"
                else -> "whoops"
            }
        })
    }

    @Test
    fun size() {
        assertEquals(5, list.size)
        assertEquals(5, list.count())
        val l2 = mutableFloatListOf()
        assertEquals(0, l2.size)
        assertEquals(0, l2.count())
        l2 += 1f
        assertEquals(1, l2.size)
        assertEquals(1, l2.count())
    }

    @Test
    fun get() {
        assertEquals(1f, list[0])
        assertEquals(5f, list[4])
        assertEquals(1f, list.elementAt(0))
        assertEquals(5f, list.elementAt(4))
    }

    @Test
    fun getOutOfBounds() {
        assertFailsWith(IndexOutOfBoundsException::class) {
            list[5]
        }
    }

    @Test
    fun getOutOfBoundsNegative() {
        assertFailsWith(IndexOutOfBoundsException::class) {
            list[-1]
        }
    }

    @Test
    fun elementAtOfBounds() {
        assertFailsWith(IndexOutOfBoundsException::class) {
            list.elementAt(5)
        }
    }

    @Test
    fun elementAtOfBoundsNegative() {
        assertFailsWith(IndexOutOfBoundsException::class) {
            list.elementAt(-1)
        }
    }

    @Test
    fun elementAtOrElse() {
        assertEquals(1f, list.elementAtOrElse(0) {
            assertEquals(0, it)
            0f
        })
        assertEquals(0f, list.elementAtOrElse(-1) {
            assertEquals(-1, it)
            0f
        })
        assertEquals(0f, list.elementAtOrElse(5) {
            assertEquals(5, it)
            0f
        })
    }

    @Test
    fun count() {
        assertEquals(1, list.count { it < 2f })
        assertEquals(0, list.count { it < 0f })
        assertEquals(5, list.count { it < 10f })
    }

    @Test
    fun isEmpty() {
        assertFalse(list.isEmpty())
        assertFalse(list.none())
        assertTrue(mutableFloatListOf().isEmpty())
        assertTrue(mutableFloatListOf().none())
    }

    @Test
    fun isNotEmpty() {
        assertTrue(list.isNotEmpty())
        assertTrue(list.any())
        assertFalse(mutableFloatListOf().isNotEmpty())
    }

    @Test
    fun indices() {
        assertEquals(IntRange(0, 4), list.indices)
        assertEquals(IntRange(0, -1), mutableFloatListOf().indices)
    }

    @Test
    fun any() {
        assertTrue(list.any { it == 5f })
        assertTrue(list.any { it == 1f })
        assertFalse(list.any { it == 0f })
    }

    @Test
    fun reversedAny() {
        val reversedList = mutableFloatListOf()
        assertFalse(
            list.reversedAny {
                reversedList.add(it)
                false
            }
        )
        val reversedContent = mutableFloatListOf(5f, 4f, 3f, 2f, 1f)
        assertEquals(reversedContent, reversedList)

        val reversedSublist = mutableFloatListOf()
        assertTrue(
            list.reversedAny {
                reversedSublist.add(it)
                reversedSublist.size == 2
            }
        )
        assertEquals(reversedSublist, mutableFloatListOf(5f, 4f))
    }
    
    @Test
    fun `atLeast returns true when count greater than or equal to n`() {
        val list = floatListOf(1f, 2f, 3f, 4f, 5f)
        val result = list.atLeast(2) { it > 3f }
        assertTrue(result)
    }

    @Test
    fun `atLeast returns false when count less than n`() {
        val list = floatListOf(1f, 2f, 3f, 4f, 5f)
        val result = list.atLeast(3) { it > 3f }
        assertEquals(false, result)
    }

    @Test
    fun `atLeast with n equals 0 returns true`() {
        val list = floatListOf(1f, 2f, 3f, 4f, 5f)
        val result = list.atLeast(0) { it > 100f }
        assertTrue(result)
    }

    @Test
    fun `atMost returns true when count less than or equal to n`() {
        val list = floatListOf(1f, 2f, 3f, 4f, 5f)
        val result = list.atMost(2) { it > 3f }
        assertTrue(result)
    }

    @Test
    fun `atMost returns false when count greater than n`() {
        val list = floatListOf(1f, 2f, 3f, 4f, 5f)
        val result = list.atMost(1) { it > 3f }
        assertEquals(false, result)
    }

    @Test
    fun `atMost with n equals 0 and no matches returns true`() {
        val list = floatListOf(1f, 2f, 3f)
        val result = list.atMost(0) { it > 10f }
        assertTrue(result)
    }

    @Test
    fun `atMost with n equals 0 and matches returns false`() {
        val list = floatListOf(1f, 2f, 3f)
        val result = list.atMost(0) { it > 1f }
        assertEquals(false, result)
    }
    
    @Test
    fun `atLeast throws IllegalArgumentException when n less than 0`() {
        val list = floatListOf(1f, 2f, 3f, 4f, 5f)
        assertFailsWith<IllegalArgumentException> {
            list.atLeast(-1) { it > 3f }
        }
    }

    @Test
    fun `atMost throws IllegalArgumentException when n less than 0`() {
        val list = floatListOf(1f, 2f, 3f, 4f, 5f)
        assertFailsWith<IllegalArgumentException> {
            list.atMost(-1) { it > 3f }
        }
    }

    @Test
    fun forEach() {
        val copy = mutableFloatListOf()
        list.forEach { copy += it }
        assertEquals(list, copy)
    }

    @Test
    fun forEachReversed() {
        val copy = mutableFloatListOf()
        list.forEachReversed { copy += it }
        assertEquals(copy, mutableFloatListOf(5f, 4f, 3f, 2f, 1f))
    }

    @Test
    fun forEachIndexed() {
        val copy = mutableFloatListOf()
        val indices = mutableIntListOf()
        list.forEachIndexed { index, item ->
            copy += item
            indices += index
        }
        assertEquals(list, copy)
        assertEquals(indices, intListOf(0, 1, 2, 3, 4))
    }

    @Test
    fun forEachReversedIndexed() {
        val copy = mutableFloatListOf()
        val indices = mutableIntListOf()
        list.forEachReversedIndexed { index, item ->
            copy += item
            indices += index
        }
        assertEquals(copy, mutableFloatListOf(5f, 4f, 3f, 2f, 1f))
        assertEquals(indices, intListOf(4, 3, 2, 1, 0))
    }

    @Test
    fun indexOfFirst() {
        assertEquals(0, list.indexOfFirst { it == 1f })
        assertEquals(4, list.indexOfFirst { it == 5f })
        assertEquals(-1, list.indexOfFirst { it == 0f })
        assertEquals(0, mutableFloatListOf(8f, 8f).indexOfFirst { it == 8f })
    }

    @Test
    fun indexOfLast() {
        assertEquals(0, list.indexOfLast { it == 1f })
        assertEquals(4, list.indexOfLast { it == 5f })
        assertEquals(-1, list.indexOfLast { it == 0f })
        assertEquals(1, mutableFloatListOf(8f, 8f).indexOfLast { it == 8f })
    }

    @Test
    fun contains() {
        assertTrue(list.contains(5f))
        assertTrue(list.contains(1f))
        assertFalse(list.contains(0f))
    }

    @Test
    fun containsAllList() {
        assertTrue(list.containsAll(mutableFloatListOf(2f, 3f, 1f)))
        assertFalse(list.containsAll(mutableFloatListOf(2f, 3f, 6f)))
    }

    @Test
    fun lastIndexOf() {
        assertEquals(4, list.lastIndexOf(5f))
        assertEquals(1, list.lastIndexOf(2f))
        val copy = mutableFloatListOf()
        copy.addAll(list)
        copy.addAll(list)
        assertEquals(5, copy.lastIndexOf(1f))
    }

    @Test
    fun first() {
        assertEquals(1f, list.first())
    }

    @Test
    fun firstException() {
        assertFailsWith(NoSuchElementException::class) {
            mutableFloatListOf().first()
        }
    }

    @Test
    fun firstWithPredicate() {
        assertEquals(5f, list.first { it == 5f })
        assertEquals(1f, mutableFloatListOf(1f, 5f).first { it != 0f })
    }

    @Test
    fun firstWithPredicateException() {
        assertFailsWith(NoSuchElementException::class) {
            mutableFloatListOf().first { it == 8f }
        }
    }

    @Test
    fun last() {
        assertEquals(5f, list.last())
    }

    @Test
    fun lastException() {
        assertFailsWith(NoSuchElementException::class) {
            mutableFloatListOf().last()
        }
    }

    @Test
    fun lastWithPredicate() {
        assertEquals(1f, list.last { it == 1f })
        assertEquals(5f, mutableFloatListOf(1f, 5f).last { it != 0f })
    }

    @Test
    fun lastWithPredicateException() {
        assertFailsWith(NoSuchElementException::class) {
            mutableFloatListOf().last { it == 8f }
        }
    }

    @Test
    fun fold() {
        assertEquals("12345", list.fold("") { acc, i -> acc + i.toInt().toString() })
    }

    @Test
    fun foldIndexed() {
        assertEquals(
            "01-12-23-34-45-",
            list.foldIndexed("") { index, acc, i ->
                "$acc$index${i.toInt()}-"
            }
        )
    }

    @Test
    fun foldRight() {
        assertEquals("54321", list.foldRight("") { i, acc -> acc + i.toInt().toString() })
    }

    @Test
    fun foldRightIndexed() {
        assertEquals(
            "45-34-23-12-01-",
            list.foldRightIndexed("") { index, i, acc ->
                "$acc$index${i.toInt()}-"
            }
        )
    }

    @Test
    fun add() {
        val l = mutableFloatListOf(1f, 2f, 3f)
        l += 4f
        l.add(5f)
        assertEquals(list, l)
    }

    @Test
    fun addAtIndex() {
        val l = mutableFloatListOf(2f, 4f)
        l.add(2, 5f)
        l.add(0, 1f)
        l.add(2, 3f)
        assertEquals(list, l)
        assertFailsWith(IndexOutOfBoundsException::class) {
            l.add(-1, 2f)
        }
        assertFailsWith(IndexOutOfBoundsException::class) {
            l.add(6, 2f)
        }
    }

    @Test
    fun addAllListAtIndex() {
        val l = mutableFloatListOf(4f)
        val l2 = mutableFloatListOf(1f, 2f)
        val l3 = mutableFloatListOf(5f)
        val l4 = mutableFloatListOf(3f)
        assertTrue(l4.addAll(1, l3))
        assertTrue(l4.addAll(0, l2))
        assertTrue(l4.addAll(3, l))
        assertFalse(l4.addAll(0, mutableFloatListOf()))
        assertEquals(list, l4)
        assertFailsWith(IndexOutOfBoundsException::class) {
            l4.addAll(6, mutableFloatListOf())
        }
        assertFailsWith(IndexOutOfBoundsException::class) {
            l4.addAll(-1, mutableFloatListOf())
        }
    }

    @Test
    fun addAllList() {
        val l = MutableFloatList()
        l.add(3f)
        l.add(4f)
        l.add(5f)
        val l2 = mutableFloatListOf(1f, 2f)
        assertTrue(l2.addAll(l))
        assertEquals(list, l2)
        assertFalse(l2.addAll(mutableFloatListOf()))
    }

    @Test
    fun plusAssignList() {
        val l = MutableFloatList()
        l.add(3f)
        l.add(4f)
        l.add(5f)
        val l2 = mutableFloatListOf(1f, 2f)
        l2 += l
        assertEquals(list, l2)
    }

    @Test
    fun addAllArrayAtIndex() {
        val a1 = floatArrayOf(4f)
        val a2 = floatArrayOf(1f, 2f)
        val a3 = floatArrayOf(5f)
        val l = mutableFloatListOf(3f)
        assertTrue(l.addAll(1, a3))
        assertTrue(l.addAll(0, a2))
        assertTrue(l.addAll(3, a1))
        assertFalse(l.addAll(0, floatArrayOf()))
        assertEquals(list, l)
        assertFailsWith(IndexOutOfBoundsException::class) {
            l.addAll(6, floatArrayOf())
        }
        assertFailsWith(IndexOutOfBoundsException::class) {
            l.addAll(-1, floatArrayOf())
        }
    }

    @Test
    fun addAllArray() {
        val a = floatArrayOf(3f, 4f, 5f)
        val v = mutableFloatListOf(1f, 2f)
        v.addAll(a)
        assertEquals(5, v.size)
        assertEquals(3f, v[2])
        assertEquals(4f, v[3])
        assertEquals(5f, v[4])
    }
    
    @Test
    fun addAllGenericList() {
        val original = mutableFloatListOf(1f, 2f)
        val toAdd = mutableListOf(3f, 4f, 5f)
        
        assertTrue(original.addAll(toAdd))
        assertEquals(5, original.size)
        assertEquals(list, original)
        
        // Test adding empty list
        assertFalse(original.addAll(mutableListOf()))
        assertEquals(5, original.size)
        assertEquals(list, original)
        
        // Test adding to empty list
        val empty = mutableFloatListOf()
        assertTrue(empty.addAll(toAdd))
        assertEquals(mutableFloatListOf(3f, 4f, 5f), empty)
        
        // Test capacity expansion
        val small = MutableFloatList(2)
        small.add(1f)
        assertTrue(small.addAll(mutableListOf(2f, 3f, 4f)))
        assertEquals(mutableFloatListOf(1f, 2f, 3f, 4f), small)
    }

    @Test
    fun plusAssignArray() {
        val a = floatArrayOf(3f, 4f, 5f)
        val v = mutableFloatListOf(1f, 2f)
        v += a
        assertEquals(list, v)
    }

    @Test
    fun clear() {
        val l = mutableFloatListOf()
        l.addAll(list)
        assertTrue(l.isNotEmpty())
        l.clear()
        assertTrue(l.isEmpty())
    }

    @Test
    fun trim() {
        val l = mutableFloatListOf(1f)
        l.trim()
        assertEquals(1, l.capacity)
        l += floatArrayOf(1f, 2f, 3f, 4f, 5f)
        l.trim()
        assertEquals(6, l.capacity)
        assertEquals(6, l.size)
        l.clear()
        l.trim()
        assertEquals(0, l.capacity)
        l.trim(100)
        assertEquals(0, l.capacity)
        l += floatArrayOf(1f, 2f, 3f, 4f, 5f)
        l -= 5f
        l.trim(5)
        assertEquals(5, l.capacity)
        l.trim(4)
        assertEquals(4, l.capacity)
        l.trim(3)
        assertEquals(4, l.capacity)
    }

    @Test
    fun remove() {
        val l = mutableFloatListOf(1f, 2f, 3f, 4f, 5f)
        l.remove(3f)
        assertEquals(mutableFloatListOf(1f, 2f, 4f, 5f), l)
    }

    @Test
    fun removeAt() {
        val l = mutableFloatListOf(1f, 2f, 3f, 4f, 5f)
        l.removeAt(2)
        assertEquals(mutableFloatListOf(1f, 2f, 4f, 5f), l)
        assertFailsWith(IndexOutOfBoundsException::class) {
            l.removeAt(6)
        }
        assertFailsWith(IndexOutOfBoundsException::class) {
            l.removeAt(-1)
        }
    }

    @Test
    fun set() {
        val l = mutableFloatListOf(0f, 0f, 0f, 0f, 0f)
        l[0] = 1f
        l[4] = 5f
        l[2] = 3f
        l[1] = 2f
        l[3] = 4f
        assertEquals(list, l)
        assertFailsWith<IndexOutOfBoundsException> {
            l.set(-1, 1f)
        }
        assertFailsWith<IndexOutOfBoundsException> {
            l.set(6, 1f)
        }
        assertEquals(4f, l.set(3, 1f))
    }

    @Test
    fun ensureCapacity() {
        val l = mutableFloatListOf(1f)
        assertEquals(1, l.capacity)
        l.ensureCapacity(5)
        assertEquals(5, l.capacity)
    }

    @Test
    fun removeAllList() {
        assertFalse(list.removeAll(mutableFloatListOf(0f, 10f, 15f)))
        val l = mutableFloatListOf(0f, 1f, 15f, 10f, 2f, 3f, 10f, 4f, 5f, 20f)
        assertTrue(l.removeAll(mutableFloatListOf(20f, 0f, 15f, 10f)))
        assertEquals(list, l)
    }

    @Test
    fun removeAllFloatArray() {
        assertFalse(list.removeAll(floatArrayOf(0f, 10f, 15f)))
        val l = mutableFloatListOf(0f, 1f, 15f, 10f, 2f, 3f, 10f, 4f, 5f, 20f)
        assertTrue(l.removeAll(floatArrayOf(20f, 0f, 15f, 10f)))
        assertEquals(list, l)
    }

    @Test
    fun minusAssignList() {
        val l = mutableFloatListOf().also { it += list }
        l -= mutableFloatListOf(0f, 10f, 15f)
        assertEquals(list, l)
        val l2 = mutableFloatListOf(0f, 1f, 15f, 10f, 2f, 3f, 10f, 4f, 5f, 20f)
        l2 -= mutableFloatListOf(20f, 0f, 15f, 10f)
        assertEquals(list, l2)
    }

    @Test
    fun minusAssignFloatArray() {
        val l = mutableFloatListOf().also { it += list }
        l -= floatArrayOf(0f, 10f, 15f)
        assertEquals(list, l)
        val l2 = mutableFloatListOf(0f, 1f, 15f, 10f, 2f, 3f, 10f, 4f, 5f, 20f)
        l2 -= floatArrayOf(20f, 0f, 15f, 10f)
        assertEquals(list, l2)
    }

    @Test
    fun retainAll() {
        assertFalse(list.retainAll(mutableFloatListOf(1f, 2f, 3f, 4f, 5f, 6f)))
        val l = mutableFloatListOf(0f, 1f, 15f, 10f, 2f, 3f, 4f, 5f, 20f)
        assertTrue(l.retainAll(mutableFloatListOf(1f, 2f, 3f, 4f, 5f, 6f)))
        assertEquals(list, l)
    }

    @Test
    fun retainAllFloatArray() {
        assertFalse(list.retainAll(floatArrayOf(1f, 2f, 3f, 4f, 5f, 6f)))
        val l = mutableFloatListOf(0f, 1f, 15f, 10f, 2f, 3f, 4f, 5f, 20f)
        assertTrue(l.retainAll(floatArrayOf(1f, 2f, 3f, 4f, 5f, 6f)))
        assertEquals(list, l)
    }

    @Test
    fun removeRange() {
        val l = mutableFloatListOf(1f, 9f, 7f, 6f, 2f, 3f, 4f, 5f)
        l.removeRange(1, 4)
        assertEquals(list, l)
        assertFailsWith<IndexOutOfBoundsException> {
            l.removeRange(6, 6)
        }
        assertFailsWith<IndexOutOfBoundsException> {
            l.removeRange(100, 200)
        }
        assertFailsWith<IndexOutOfBoundsException> {
            l.removeRange(-1, 0)
        }
        assertFailsWith<IllegalArgumentException> {
            l.removeRange(3, 2)
        }
    }

    @Test
    fun sort() {
        val l = mutableFloatListOf(1f, 4f, 2f, 5f, 3f)
        l.sort()
        assertEquals(list, l)
    }

    @Test
    fun sortDescending() {
        val l = mutableFloatListOf(1f, 4f, 2f, 5f, 3f)
        l.sortDescending()
        assertEquals(mutableFloatListOf(5f, 4f, 3f, 2f, 1f), l)
    }

    @Test
    fun sortEmpty() {
        val l = MutableFloatList(0)
        l.sort()
        l.sortDescending()
        assertEquals(MutableFloatList(0), l)
    }

    @Test
    fun testEmptyFloatList() {
        val l = emptyFloatList()
        assertEquals(0, l.size)
    }

    @Test
    fun floatListOfEmpty() {
        val l = floatListOf()
        assertEquals(0, l.size)
    }

    @Test
    fun floatListOfOneValue() {
        val l = floatListOf(2f)
        assertEquals(1, l.size)
        assertEquals(2f, l[0])
    }

    @Test
    fun floatListOfTwoValues() {
        val l = floatListOf(2f, 1f)
        assertEquals(2, l.size)
        assertEquals(2f, l[0])
        assertEquals(1f, l[1])
    }

    @Test
    fun floatListOfThreeValues() {
        val l = floatListOf(2f, 10f, -1f)
        assertEquals(3, l.size)
        assertEquals(2f, l[0])
        assertEquals(10f, l[1])
        assertEquals(-1f, l[2])
    }

    @Test
    fun floatListOfFourValues() {
        val l = floatListOf(2f, 10f, -1f, 10f)
        assertEquals(4, l.size)
        assertEquals(2f, l[0])
        assertEquals(10f, l[1])
        assertEquals(-1f, l[2])
        assertEquals(10f, l[3])
    }

    @Test
    fun mutableFloatListOfOneValue() {
        val l = mutableFloatListOf(2f)
        assertEquals(1, l.size)
        assertEquals(1, l.capacity)
        assertEquals(2f, l[0])
    }

    @Test
    fun mutableFloatListOfTwoValues() {
        val l = mutableFloatListOf(2f, 1f)
        assertEquals(2, l.size)
        assertEquals(2, l.capacity)
        assertEquals(2f, l[0])
        assertEquals(1f, l[1])
    }

    @Test
    fun mutableFloatListOfThreeValues() {
        val l = mutableFloatListOf(2f, 10f, -1f)
        assertEquals(3, l.size)
        assertEquals(3, l.capacity)
        assertEquals(2f, l[0])
        assertEquals(10f, l[1])
        assertEquals(-1f, l[2])
    }

    @Test
    fun mutableFloatListOfFourValues() {
        val l = mutableFloatListOf(2f, 10f, -1f, 10f)
        assertEquals(4, l.size)
        assertEquals(4, l.capacity)
        assertEquals(2f, l[0])
        assertEquals(10f, l[1])
        assertEquals(-1f, l[2])
        assertEquals(10f, l[3])
    }

    @Test
    fun buildFloatListFunction() {
        val contract: Boolean
        val l = buildFloatList {
            contract = true
            add(2f)
            add(10f)
        }
        assertTrue(contract)
        assertEquals(2, l.size)
        assertEquals(2f, l[0])
        assertEquals(10f, l[1])
    }

    @Test
    fun buildFloatListWithCapacityFunction() {
        val contract: Boolean
        val l = buildFloatList(20) {
            contract = true
            add(2f)
            add(10f)
        }
        assertTrue(contract)
        assertEquals(2, l.size)
        assertTrue(l.content.size >= 20)
        assertEquals(2f, l[0])
        assertEquals(10f, l[1])
    }

    @Test
    fun binarySearchFloatList() {
        val l = mutableFloatListOf(-2f, -1f, 2f, 10f, 10f)
        assertEquals(0, l.binarySearch(-2))
        assertEquals(2, l.binarySearch(2))
        assertEquals(3, l.binarySearch(10))

        assertEquals(-1, l.binarySearch(-20))
        assertEquals(-4, l.binarySearch(3))
        assertEquals(-6, l.binarySearch(20))

    }
    
    @Test
    fun take() {
        assertEquals(floatListOf(1f, 2f, 3f), list.take(3))
        assertEquals(floatListOf(), list.take(0))
        assertEquals(list, list.take(100))
    }

    @Test
    fun takeLast() {
        assertEquals(floatListOf(3f, 4f, 5f), list.takeLast(3))
        assertEquals(floatListOf(), list.takeLast(0))
        assertEquals(list, list.takeLast(100))
    }

    @Test
    fun drop() {
        assertEquals(floatListOf(4f, 5f), list.drop(3))
        assertEquals(list, list.drop(0))
        assertEquals(floatListOf(), list.drop(100))
    }

    @Test
    fun dropLast() {
        assertEquals(floatListOf(1f, 2f), list.dropLast(3))
        assertEquals(list, list.dropLast(0))
        assertEquals(floatListOf(), list.dropLast(100))
    }
    
    @Test
    fun takeWhile() {
        assertEquals(floatListOf(1f, 2f), list.takeWhile { it < 3f })
        assertEquals(floatListOf(), list.takeWhile { it < 0f })
        assertEquals(list, list.takeWhile { it <= 5f })
        assertEquals(floatListOf(), mutableFloatListOf().takeWhile { it < 10f })
    }

    @Test
    fun takeLastWhile() {
        assertEquals(floatListOf(4f, 5f), list.takeLastWhile { it > 3f })
        assertEquals(floatListOf(), list.takeLastWhile { it > 5f })
        assertEquals(list, list.takeLastWhile { it >= 1f })
        assertEquals(floatListOf(), mutableFloatListOf().takeLastWhile { it > 0f })
    }

    @Test
    fun dropWhile() {
        assertEquals(floatListOf(3f, 4f, 5f), list.dropWhile { it < 3f })
        assertEquals(list, list.dropWhile { it < 0f })
        assertEquals(floatListOf(), list.dropWhile { it <= 5f })
        assertEquals(floatListOf(), mutableFloatListOf().dropWhile { it < 10f })
    }

    @Test
    fun dropLastWhile() {
        assertEquals(floatListOf(1f, 2f, 3f), list.dropLastWhile { it > 3f })
        assertEquals(list, list.dropLastWhile { it > 5f })
        assertEquals(floatListOf(), list.dropLastWhile { it >= 1f })
        assertEquals(floatListOf(), mutableFloatListOf().dropLastWhile { it > 0f })
    }
    
    @Test
    fun filter() {
        assertEquals(mutableFloatListOf(1f, 2f), list.filter { it < 3f })
        assertEquals(mutableFloatListOf(4f, 5f), list.filter { it > 3f })
        assertEquals(mutableFloatListOf(), list.filter { it > 10f })
        assertEquals(list, list.filter { true })
        assertEquals(mutableFloatListOf(), list.filter { false })
    }

    @Test
    fun copy() {
        val copy = list.copy()
        assertEquals(list, copy)
        assertNotEquals(list.content, copy.content)
    }

    @Test
    fun distinct() {
        val duplicates = mutableFloatListOf(1f, 2f, 2f, 3f, 3f, 3f, 4f, 4f, 5f)
        assertEquals(list, duplicates.distinct())
        assertEquals(list, list.distinct()) 
        assertEquals(mutableFloatListOf(), mutableFloatListOf().distinct())
        assertEquals(mutableFloatListOf(1f), mutableFloatListOf(1f, 1f, 1f).distinct())
    }

    @Test
    fun sorted() {
        val unsorted = MutableFloatList().also {
            it.addAll(floatArrayOf(5f, 2f, 4f, 3f, 1f))
        }
        assertEquals(list, unsorted.sorted())
        assertEquals(list, list.sorted())
        assertEquals(mutableFloatListOf(), mutableFloatListOf().sorted())
        assertEquals(mutableFloatListOf(1f), mutableFloatListOf(1f).sorted())
    }

    @Test
    fun sortedDescending() {
        val unsorted = MutableFloatList().also {
            it.addAll(floatArrayOf(5f, 2f, 4f, 3f, 1f))
        }
        assertEquals(mutableFloatListOf(5f, 4f, 3f, 2f, 1f), unsorted.sortedDescending())
        assertEquals(mutableFloatListOf(5f, 4f, 3f, 2f, 1f), list.sortedDescending())
        assertEquals(mutableFloatListOf(), mutableFloatListOf().sortedDescending())
        assertEquals(mutableFloatListOf(1f), mutableFloatListOf(1f).sortedDescending())
    }
    
    @Test
    fun reversed() {
        assertEquals(mutableFloatListOf(5f, 4f, 3f, 2f, 1f), list.reversed())
        assertEquals(mutableFloatListOf(), mutableFloatListOf().reversed())
        assertEquals(mutableFloatListOf(1f), mutableFloatListOf(1f).reversed())
        
        val duplicates = mutableFloatListOf(1f, 1f, 2f, 2f)
        assertEquals(mutableFloatListOf(2f, 2f, 1f, 1f), duplicates.reversed())
    }

    @Test
    fun chunked() {
        val result = list.chunked(2)
        assertEquals(3, result.size)
        assertEquals(mutableFloatListOf(1f, 2f), result[0])
        assertEquals(mutableFloatListOf(3f, 4f), result[1]) 
        assertEquals(mutableFloatListOf(5f), result[2])

        assertEquals(0, mutableFloatListOf().chunked(2).size)

        val chunks = list.chunked(5)
        assertEquals(1, chunks.size)
        assertEquals(list, chunks[0])

        val largeChunks = list.chunked(10)
        assertEquals(1, largeChunks.size)
        assertEquals(list, largeChunks[0])

        assertFailsWith<IllegalArgumentException> { list.chunked(0) }
        assertFailsWith<IllegalArgumentException> { list.chunked(-1) }
    }
    
    @Test
    fun mutableFloatListWithInit() {
        val list = MutableFloatList(4) { (it + 1).toFloat() }
        assertEquals(4, list.size)
        assertEquals(1f, list[0])
        assertEquals(2f, list[1])
        assertEquals(3f, list[2])
        assertEquals(4f, list[3])

        val empty = MutableFloatList(0) { it.toFloat() }
        assertEquals(0, empty.size)

        val zeros = MutableFloatList(3) { 0f }
        assertEquals(3, zeros.size)
        assertEquals(0f, zeros[0])
        assertEquals(0f, zeros[1])
        assertEquals(0f, zeros[2])

        val complex = MutableFloatList(3) { index ->
            when (index) {
                0 -> -1f
                1 -> 0f
                else -> 1f
            }
        }
        assertEquals(3, complex.size)
        assertEquals(-1f, complex[0])
        assertEquals(0f, complex[1])
        assertEquals(1f, complex[2])
    }

    @Test
    fun floatListWithInit() {
        val list = FloatList(4) { (it + 1).toFloat() }
        assertEquals(4, list.size)
        assertEquals(1f, list[0])
        assertEquals(2f, list[1])
        assertEquals(3f, list[2])
        assertEquals(4f, list[3])
    }
    
    @Test
    fun all() {
        assertTrue(list.all { it > 0f })
        assertTrue(list.all { it < 6f })
        assertFalse(list.all { it < 5f })
        assertFalse(list.all { it > 3f })
        
        assertTrue(mutableFloatListOf().all { it > 0f })
        
        assertTrue(mutableFloatListOf(1f).all { it == 1f })
        assertFalse(mutableFloatListOf(1f).all { it == 2f })
        
        val duplicates = mutableFloatListOf(2f, 2f, 2f)
        assertTrue(duplicates.all { it == 2f })
        
        var count = 0
        assertFalse(list.all { 
            count++
            it > 3f 
        })
        assertTrue(count < list.size)
    }

    @Test
    fun reversedAll() {
        assertTrue(list.reversedAll { it < 6f })
        assertTrue(list.reversedAll { it > 0f })
        assertFalse(list.reversedAll { it > 3f })
        assertFalse(list.reversedAll { it < 3f })
        
        assertTrue(mutableFloatListOf().reversedAll { it > 0f })
        
        assertTrue(mutableFloatListOf(1f).reversedAll { it == 1f })
        assertFalse(mutableFloatListOf(1f).reversedAll { it == 2f })
        
        val duplicates = mutableFloatListOf(2f, 2f, 2f)
        assertTrue(duplicates.reversedAll { it == 2f })
        
        val evaluated = mutableFloatListOf()
        assertTrue(list.reversedAll { 
            evaluated.add(it)
            true
        })
        assertEquals(mutableFloatListOf(5f, 4f, 3f, 2f, 1f), evaluated)
        
        var count = 0
        assertFalse(list.reversedAll { 
            count++
            it < 3f 
        })
        assertTrue(count < list.size)
    }
    
    @Test
    fun sum() {
        assertEquals(15f, list.sum())
        assertEquals(0f, mutableFloatListOf().sum())
        assertEquals(1f, mutableFloatListOf(1f).sum())
        assertEquals(3f, mutableFloatListOf(1f, 2f).sum())
        
        val negatives = mutableFloatListOf(-2f, -1f, 0f, 1f, 2f)
        assertEquals(0f, negatives.sum())
    }

    @Test 
    fun sumOf() {
        assertEquals(30f, list.sumOf { it * 2 })
        assertEquals(0f, mutableFloatListOf().sumOf { it * 2 })
        assertEquals(2f, mutableFloatListOf(1f).sumOf { it * 2 })
        
        val result = list.sumOf { 
            if (it % 2f == 0f) it
            else 0f
        }
        assertEquals(6f, result)
        
        assertEquals(5f, list.sumOf { 1f })
        assertEquals(15f, list.sumOf { it })
    }
    
    @Test
    fun orEmpty() {
        // Test non-null list returns itself
        val nonNullList = floatListOf(1f, 2f, 3f)
        assertEquals(nonNullList, nonNullList.orEmpty())
        
        // Test null list returns empty list
        val nullList: FloatList? = null
        val emptyResult = nullList.orEmpty()
        assertTrue(emptyResult.isEmpty())

        // Test empty list returns itself
        val emptyList = floatListOf()
        assertEquals(emptyList, emptyList.orEmpty())
    }

    @Test
    fun jsonSerialization() {
        // Test basic serialization and deserialization
        val json = Json
        val list = floatListOf(1f, 2f, 3f, 4f, 5f)

        val serialized = json.encodeToString(list)
        val deserialized = json.decodeFromString<FloatList>(serialized)
        assertEquals(list, deserialized)

        // Test empty list
        val emptyList = floatListOf()
        val emptyListSerialized = json.encodeToString(emptyList)
        val emptyListDeserialized = json.decodeFromString<FloatList>(emptyListSerialized)
        assertEquals(emptyList, emptyListDeserialized)

        // Test single element
        val singleList = floatListOf(1f)
        val singleListSerialized = json.encodeToString(singleList)
        val singleListDeserialized = json.decodeFromString<FloatList>(singleListSerialized)
        assertEquals(singleList, singleListDeserialized)

        // Compare with standard List serialization
        val standardList = listOf(1f, 2f, 3f, 4f, 5f)
        val standardListSerialized = json.encodeToString(standardList)
        assertEquals(serialized, standardListSerialized)

        // Test with custom values
        val mixedList = floatListOf(-1f, 0f, 1f, 100f)
        val mixedListSerialized = json.encodeToString(mixedList)
        val mixedListDeserialized = json.decodeFromString<FloatList>(mixedListSerialized)
        assertEquals(mixedList, mixedListDeserialized)

        // Compare mixed list with standard List
        val standardMixedList = listOf(-1f, 0f, 1f, 100f)
        val standardMixedSerialized = json.encodeToString(standardMixedList)
        assertEquals(mixedListSerialized, standardMixedSerialized)
    }
    
    @Test
    fun toFloatArray() {
        // Test regular list
        val array = list.toFloatArray()
        assertEquals(5, array.size)
        assertEquals(1f, array[0])
        assertEquals(2f, array[1])
        assertEquals(3f, array[2])
        assertEquals(4f, array[3])
        assertEquals(5f, array[4])
        
        // Test empty list
        val emptyArray = mutableFloatListOf().toFloatArray()
        assertEquals(0, emptyArray.size)
        
        // Test single element list
        val singleArray = mutableFloatListOf(1f).toFloatArray()
        assertEquals(1, singleArray.size)
        assertEquals(1f, singleArray[0])
        
        // Verify array is a copy
        val originalList = mutableFloatListOf(1f, 2f, 3f)
        val copiedArray = originalList.toFloatArray()
        originalList[0] = 5f
        assertEquals(1f, copiedArray[0])
    }
    
    @Test
    fun toSet() {
        // Test regular list
        val set = list.toSet()
        assertEquals(5, set.size)
        assertTrue(set.contains(1f))
        assertTrue(set.contains(2f))
        assertTrue(set.contains(3f))
        assertTrue(set.contains(4f))
        assertTrue(set.contains(5f))
        
        // Test empty list
        val emptySet = mutableFloatListOf().toSet()
        assertTrue(emptySet.isEmpty())
        
        // Test single element list
        val singleSet = mutableFloatListOf(1f).toSet()
        assertEquals(1, singleSet.size)
        assertTrue(singleSet.contains(1f))
        
        // Test list with duplicates
        val duplicateList = mutableFloatListOf(1f, 2f, 2f, 3f, 3f, 3f)
        val duplicateSet = duplicateList.toSet()
        assertEquals(3, duplicateSet.size)
        assertTrue(duplicateSet.contains(1f))
        assertTrue(duplicateSet.contains(2f))
        assertTrue(duplicateSet.contains(3f))
    }

    @Test
    fun toMutableSet() {
        // Test regular list
        val set = list.toMutableSet()
        assertEquals(5, set.size)
        assertTrue(set.contains(1f))
        assertTrue(set.contains(2f))
        assertTrue(set.contains(3f))
        assertTrue(set.contains(4f))
        assertTrue(set.contains(5f))

        // Verify mutability
        set.add(6f)
        assertEquals(6, set.size)
        assertTrue(set.contains(6f))
        
        // Test empty list
        val emptySet = mutableFloatListOf().toMutableSet()
        assertTrue(emptySet.isEmpty())
        emptySet.add(1f)
        assertEquals(1, emptySet.size)
        
        // Test single element list
        val singleSet = mutableFloatListOf(1f).toMutableSet()
        assertEquals(1, singleSet.size)
        assertTrue(singleSet.contains(1f))
        
        // Test list with duplicates
        val duplicateList = mutableFloatListOf(1f, 2f, 2f, 3f, 3f, 3f)
        val duplicateSet = duplicateList.toMutableSet()
        assertEquals(3, duplicateSet.size)
        assertTrue(duplicateSet.contains(1f))
        assertTrue(duplicateSet.contains(2f))
        assertTrue(duplicateSet.contains(3f))
    }
}
