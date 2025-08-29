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
class LongListTest {
    private val list: MutableLongList = mutableLongListOf(1L, 2L, 3L, 4L, 5L)

    @Test
    fun emptyConstruction() {
        val l = mutableLongListOf()
        assertEquals(0, l.size)
        assertEquals(16, l.capacity)
    }

    @Test
    fun sizeConstruction() {
        val l = MutableLongList(4)
        assertEquals(4, l.capacity)
    }

    @Test
    fun contentConstruction() {
        val l = mutableLongListOf(1L, 2L, 3L)
        assertEquals(3, l.size)
        assertEquals(1L, l[0])
        assertEquals(2L, l[1])
        assertEquals(3L, l[2])
        assertEquals(3, l.capacity)
        repeat(2) {
            val l2 = mutableLongListOf(1L, 2L, 3L, 4L, 5L)
            assertEquals(list, l2)
            l2.removeAt(0)
        }
    }

    @Test
    fun hashCodeTest() {
        val l2 = mutableLongListOf(1L, 2L, 3L, 4L, 5L)
        assertEquals(list.hashCode(), l2.hashCode())
        l2.removeAt(4)
        assertNotEquals(list.hashCode(), l2.hashCode())
        l2.add(5L)
        assertEquals(list.hashCode(), l2.hashCode())
        l2.clear()
        assertNotEquals(list.hashCode(), l2.hashCode())
    }

    @Test
    fun equalsTest() {
        val l2 = mutableLongListOf(1L, 2L, 3L, 4L, 5L)
        assertEquals(list, l2)
        assertNotEquals(list, mutableLongListOf())
        l2.removeAt(4)
        assertNotEquals(list, l2)
        l2.add(5L)
        assertEquals(list, l2)
        l2.clear()
        assertNotEquals(list, l2)
    }

    @Test
    fun string() {
        assertEquals("[${1L}, ${2L}, ${3L}, ${4L}, ${5L}]", list.toString())
        assertEquals("[]", mutableLongListOf().toString())
    }

    @Test
    fun joinToString() {
        assertEquals("${1L}, ${2L}, ${3L}, ${4L}, ${5L}", list.joinToString())
        assertEquals(
            "x${1L}, ${2L}, ${3L}, ...y",
            list.joinToString(prefix = "x", postfix = "y", limit = 3)
        )
        assertEquals(
            ">${1L}-${2L}-${3L}-${4L}-${5L}<",
            list.joinToString(separator = "-", prefix = ">", postfix = "<")
        )
        assertEquals("one, two, three, ...", list.joinToString(limit = 3) {
            when (it) {
                1L -> "one"
                2L -> "two"
                3L -> "three"
                else -> "whoops"
            }
        })
    }

    @Test
    fun size() {
        assertEquals(5, list.size)
        assertEquals(5, list.count())
        val l2 = mutableLongListOf()
        assertEquals(0, l2.size)
        assertEquals(0, l2.count())
        l2 += 1L
        assertEquals(1, l2.size)
        assertEquals(1, l2.count())
    }

    @Test
    fun get() {
        assertEquals(1L, list[0])
        assertEquals(5L, list[4])
        assertEquals(1L, list.elementAt(0))
        assertEquals(5L, list.elementAt(4))
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
        assertEquals(1L, list.elementAtOrElse(0) {
            assertEquals(0, it)
            0L
        })
        assertEquals(0L, list.elementAtOrElse(-1) {
            assertEquals(-1, it)
            0L
        })
        assertEquals(0L, list.elementAtOrElse(5) {
            assertEquals(5, it)
            0L
        })
    }

    @Test
    fun count() {
        assertEquals(1, list.count { it < 2L })
        assertEquals(0, list.count { it < 0L })
        assertEquals(5, list.count { it < 10L })
    }

    @Test
    fun isEmpty() {
        assertFalse(list.isEmpty())
        assertFalse(list.none())
        assertTrue(mutableLongListOf().isEmpty())
        assertTrue(mutableLongListOf().none())
    }

    @Test
    fun isNotEmpty() {
        assertTrue(list.isNotEmpty())
        assertTrue(list.any())
        assertFalse(mutableLongListOf().isNotEmpty())
    }

    @Test
    fun indices() {
        assertEquals(IntRange(0, 4), list.indices)
        assertEquals(IntRange(0, -1), mutableLongListOf().indices)
    }

    @Test
    fun any() {
        assertTrue(list.any { it == 5L })
        assertTrue(list.any { it == 1L })
        assertFalse(list.any { it == 0L })
    }

    @Test
    fun reversedAny() {
        val reversedList = mutableLongListOf()
        assertFalse(
            list.reversedAny {
                reversedList.add(it)
                false
            }
        )
        val reversedContent = mutableLongListOf(5L, 4L, 3L, 2L, 1L)
        assertEquals(reversedContent, reversedList)

        val reversedSublist = mutableLongListOf()
        assertTrue(
            list.reversedAny {
                reversedSublist.add(it)
                reversedSublist.size == 2
            }
        )
        assertEquals(reversedSublist, mutableLongListOf(5L, 4L))
    }
    
    @Test
    fun `atLeast returns true when count greater than or equal to n`() {
        val list = longListOf(1L, 2L, 3L, 4L, 5L)
        val result = list.atLeast(2) { it > 3L }
        assertTrue(result)
    }

    @Test
    fun `atLeast returns false when count less than n`() {
        val list = longListOf(1L, 2L, 3L, 4L, 5L)
        val result = list.atLeast(3) { it > 3L }
        assertEquals(false, result)
    }

    @Test
    fun `atLeast with n equals 0 returns true`() {
        val list = longListOf(1L, 2L, 3L, 4L, 5L)
        val result = list.atLeast(0) { it > 100L }
        assertTrue(result)
    }

    @Test
    fun `atMost returns true when count less than or equal to n`() {
        val list = longListOf(1L, 2L, 3L, 4L, 5L)
        val result = list.atMost(2) { it > 3L }
        assertTrue(result)
    }

    @Test
    fun `atMost returns false when count greater than n`() {
        val list = longListOf(1L, 2L, 3L, 4L, 5L)
        val result = list.atMost(1) { it > 3L }
        assertEquals(false, result)
    }

    @Test
    fun `atMost with n equals 0 and no matches returns true`() {
        val list = longListOf(1L, 2L, 3L)
        val result = list.atMost(0) { it > 10L }
        assertTrue(result)
    }

    @Test
    fun `atMost with n equals 0 and matches returns false`() {
        val list = longListOf(1L, 2L, 3L)
        val result = list.atMost(0) { it > 1L }
        assertEquals(false, result)
    }
    
    @Test
    fun `atLeast throws IllegalArgumentException when n less than 0`() {
        val list = longListOf(1L, 2L, 3L, 4L, 5L)
        assertFailsWith<IllegalArgumentException> {
            list.atLeast(-1) { it > 3L }
        }
    }

    @Test
    fun `atMost throws IllegalArgumentException when n less than 0`() {
        val list = longListOf(1L, 2L, 3L, 4L, 5L)
        assertFailsWith<IllegalArgumentException> {
            list.atMost(-1) { it > 3L }
        }
    }

    @Test
    fun forEach() {
        val copy = mutableLongListOf()
        list.forEach { copy += it }
        assertEquals(list, copy)
    }

    @Test
    fun forEachReversed() {
        val copy = mutableLongListOf()
        list.forEachReversed { copy += it }
        assertEquals(copy, mutableLongListOf(5L, 4L, 3L, 2L, 1L))
    }

    @Test
    fun forEachIndexed() {
        val copy = mutableLongListOf()
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
        val copy = mutableLongListOf()
        val indices = mutableIntListOf()
        list.forEachReversedIndexed { index, item ->
            copy += item
            indices += index
        }
        assertEquals(copy, mutableLongListOf(5L, 4L, 3L, 2L, 1L))
        assertEquals(indices, intListOf(4, 3, 2, 1, 0))
    }

    @Test
    fun indexOfFirst() {
        assertEquals(0, list.indexOfFirst { it == 1L })
        assertEquals(4, list.indexOfFirst { it == 5L })
        assertEquals(-1, list.indexOfFirst { it == 0L })
        assertEquals(0, mutableLongListOf(8L, 8L).indexOfFirst { it == 8L })
    }

    @Test
    fun indexOfLast() {
        assertEquals(0, list.indexOfLast { it == 1L })
        assertEquals(4, list.indexOfLast { it == 5L })
        assertEquals(-1, list.indexOfLast { it == 0L })
        assertEquals(1, mutableLongListOf(8L, 8L).indexOfLast { it == 8L })
    }

    @Test
    fun contains() {
        assertTrue(list.contains(5L))
        assertTrue(list.contains(1L))
        assertFalse(list.contains(0L))
    }

    @Test
    fun containsAllList() {
        assertTrue(list.containsAll(mutableLongListOf(2L, 3L, 1L)))
        assertFalse(list.containsAll(mutableLongListOf(2L, 3L, 6L)))
    }

    @Test
    fun lastIndexOf() {
        assertEquals(4, list.lastIndexOf(5L))
        assertEquals(1, list.lastIndexOf(2L))
        val copy = mutableLongListOf()
        copy.addAll(list)
        copy.addAll(list)
        assertEquals(5, copy.lastIndexOf(1L))
    }

    @Test
    fun first() {
        assertEquals(1L, list.first())
    }

    @Test
    fun firstException() {
        assertFailsWith(NoSuchElementException::class) {
            mutableLongListOf().first()
        }
    }

    @Test
    fun firstWithPredicate() {
        assertEquals(5L, list.first { it == 5L })
        assertEquals(1L, mutableLongListOf(1L, 5L).first { it != 0L })
    }

    @Test
    fun firstWithPredicateException() {
        assertFailsWith(NoSuchElementException::class) {
            mutableLongListOf().first { it == 8L }
        }
    }

    @Test
    fun last() {
        assertEquals(5L, list.last())
    }

    @Test
    fun lastException() {
        assertFailsWith(NoSuchElementException::class) {
            mutableLongListOf().last()
        }
    }

    @Test
    fun lastWithPredicate() {
        assertEquals(1L, list.last { it == 1L })
        assertEquals(5L, mutableLongListOf(1L, 5L).last { it != 0L })
    }

    @Test
    fun lastWithPredicateException() {
        assertFailsWith(NoSuchElementException::class) {
            mutableLongListOf().last { it == 8L }
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
        val l = mutableLongListOf(1L, 2L, 3L)
        l += 4L
        l.add(5L)
        assertEquals(list, l)
    }

    @Test
    fun addAtIndex() {
        val l = mutableLongListOf(2L, 4L)
        l.add(2, 5L)
        l.add(0, 1L)
        l.add(2, 3L)
        assertEquals(list, l)
        assertFailsWith(IndexOutOfBoundsException::class) {
            l.add(-1, 2L)
        }
        assertFailsWith(IndexOutOfBoundsException::class) {
            l.add(6, 2L)
        }
    }

    @Test
    fun addAllListAtIndex() {
        val l = mutableLongListOf(4L)
        val l2 = mutableLongListOf(1L, 2L)
        val l3 = mutableLongListOf(5L)
        val l4 = mutableLongListOf(3L)
        assertTrue(l4.addAll(1, l3))
        assertTrue(l4.addAll(0, l2))
        assertTrue(l4.addAll(3, l))
        assertFalse(l4.addAll(0, mutableLongListOf()))
        assertEquals(list, l4)
        assertFailsWith(IndexOutOfBoundsException::class) {
            l4.addAll(6, mutableLongListOf())
        }
        assertFailsWith(IndexOutOfBoundsException::class) {
            l4.addAll(-1, mutableLongListOf())
        }
    }

    @Test
    fun addAllList() {
        val l = MutableLongList()
        l.add(3L)
        l.add(4L)
        l.add(5L)
        val l2 = mutableLongListOf(1L, 2L)
        assertTrue(l2.addAll(l))
        assertEquals(list, l2)
        assertFalse(l2.addAll(mutableLongListOf()))
    }

    @Test
    fun plusAssignList() {
        val l = MutableLongList()
        l.add(3L)
        l.add(4L)
        l.add(5L)
        val l2 = mutableLongListOf(1L, 2L)
        l2 += l
        assertEquals(list, l2)
    }

    @Test
    fun addAllArrayAtIndex() {
        val a1 = longArrayOf(4L)
        val a2 = longArrayOf(1L, 2L)
        val a3 = longArrayOf(5L)
        val l = mutableLongListOf(3L)
        assertTrue(l.addAll(1, a3))
        assertTrue(l.addAll(0, a2))
        assertTrue(l.addAll(3, a1))
        assertFalse(l.addAll(0, longArrayOf()))
        assertEquals(list, l)
        assertFailsWith(IndexOutOfBoundsException::class) {
            l.addAll(6, longArrayOf())
        }
        assertFailsWith(IndexOutOfBoundsException::class) {
            l.addAll(-1, longArrayOf())
        }
    }

    @Test
    fun addAllArray() {
        val a = longArrayOf(3L, 4L, 5L)
        val v = mutableLongListOf(1L, 2L)
        v.addAll(a)
        assertEquals(5, v.size)
        assertEquals(3L, v[2])
        assertEquals(4L, v[3])
        assertEquals(5L, v[4])
    }
    
    @Test
    fun addAllGenericList() {
        val original = mutableLongListOf(1L, 2L)
        val toAdd = mutableListOf(3L, 4L, 5L)
        
        assertTrue(original.addAll(toAdd))
        assertEquals(5, original.size)
        assertEquals(list, original)
        
        // Test adding empty list
        assertFalse(original.addAll(mutableListOf()))
        assertEquals(5, original.size)
        assertEquals(list, original)
        
        // Test adding to empty list
        val empty = mutableLongListOf()
        assertTrue(empty.addAll(toAdd))
        assertEquals(mutableLongListOf(3L, 4L, 5L), empty)
        
        // Test capacity expansion
        val small = MutableLongList(2)
        small.add(1L)
        assertTrue(small.addAll(mutableListOf(2L, 3L, 4L)))
        assertEquals(mutableLongListOf(1L, 2L, 3L, 4L), small)
    }

    @Test
    fun plusAssignArray() {
        val a = longArrayOf(3L, 4L, 5L)
        val v = mutableLongListOf(1L, 2L)
        v += a
        assertEquals(list, v)
    }

    @Test
    fun clear() {
        val l = mutableLongListOf()
        l.addAll(list)
        assertTrue(l.isNotEmpty())
        l.clear()
        assertTrue(l.isEmpty())
    }

    @Test
    fun trim() {
        val l = mutableLongListOf(1L)
        l.trim()
        assertEquals(1, l.capacity)
        l += longArrayOf(1L, 2L, 3L, 4L, 5L)
        l.trim()
        assertEquals(6, l.capacity)
        assertEquals(6, l.size)
        l.clear()
        l.trim()
        assertEquals(0, l.capacity)
        l.trim(100)
        assertEquals(0, l.capacity)
        l += longArrayOf(1L, 2L, 3L, 4L, 5L)
        l -= 5L
        l.trim(5)
        assertEquals(5, l.capacity)
        l.trim(4)
        assertEquals(4, l.capacity)
        l.trim(3)
        assertEquals(4, l.capacity)
    }

    @Test
    fun remove() {
        val l = mutableLongListOf(1L, 2L, 3L, 4L, 5L)
        l.remove(3L)
        assertEquals(mutableLongListOf(1L, 2L, 4L, 5L), l)
    }

    @Test
    fun removeAt() {
        val l = mutableLongListOf(1L, 2L, 3L, 4L, 5L)
        l.removeAt(2)
        assertEquals(mutableLongListOf(1L, 2L, 4L, 5L), l)
        assertFailsWith(IndexOutOfBoundsException::class) {
            l.removeAt(6)
        }
        assertFailsWith(IndexOutOfBoundsException::class) {
            l.removeAt(-1)
        }
    }

    @Test
    fun set() {
        val l = mutableLongListOf(0L, 0L, 0L, 0L, 0L)
        l[0] = 1L
        l[4] = 5L
        l[2] = 3L
        l[1] = 2L
        l[3] = 4L
        assertEquals(list, l)
        assertFailsWith<IndexOutOfBoundsException> {
            l.set(-1, 1L)
        }
        assertFailsWith<IndexOutOfBoundsException> {
            l.set(6, 1L)
        }
        assertEquals(4L, l.set(3, 1L))
    }

    @Test
    fun ensureCapacity() {
        val l = mutableLongListOf(1L)
        assertEquals(1, l.capacity)
        l.ensureCapacity(5)
        assertEquals(5, l.capacity)
    }

    @Test
    fun removeAllList() {
        assertFalse(list.removeAll(mutableLongListOf(0L, 10L, 15L)))
        val l = mutableLongListOf(0L, 1L, 15L, 10L, 2L, 3L, 10L, 4L, 5L, 20L)
        assertTrue(l.removeAll(mutableLongListOf(20L, 0L, 15L, 10L)))
        assertEquals(list, l)
    }

    @Test
    fun removeAllLongArray() {
        assertFalse(list.removeAll(longArrayOf(0L, 10L, 15L)))
        val l = mutableLongListOf(0L, 1L, 15L, 10L, 2L, 3L, 10L, 4L, 5L, 20L)
        assertTrue(l.removeAll(longArrayOf(20L, 0L, 15L, 10L)))
        assertEquals(list, l)
    }

    @Test
    fun minusAssignList() {
        val l = mutableLongListOf().also { it += list }
        l -= mutableLongListOf(0L, 10L, 15L)
        assertEquals(list, l)
        val l2 = mutableLongListOf(0L, 1L, 15L, 10L, 2L, 3L, 10L, 4L, 5L, 20L)
        l2 -= mutableLongListOf(20L, 0L, 15L, 10L)
        assertEquals(list, l2)
    }

    @Test
    fun minusAssignLongArray() {
        val l = mutableLongListOf().also { it += list }
        l -= longArrayOf(0L, 10L, 15L)
        assertEquals(list, l)
        val l2 = mutableLongListOf(0L, 1L, 15L, 10L, 2L, 3L, 10L, 4L, 5L, 20L)
        l2 -= longArrayOf(20L, 0L, 15L, 10L)
        assertEquals(list, l2)
    }

    @Test
    fun retainAll() {
        assertFalse(list.retainAll(mutableLongListOf(1L, 2L, 3L, 4L, 5L, 6L)))
        val l = mutableLongListOf(0L, 1L, 15L, 10L, 2L, 3L, 4L, 5L, 20L)
        assertTrue(l.retainAll(mutableLongListOf(1L, 2L, 3L, 4L, 5L, 6L)))
        assertEquals(list, l)
    }

    @Test
    fun retainAllLongArray() {
        assertFalse(list.retainAll(longArrayOf(1L, 2L, 3L, 4L, 5L, 6L)))
        val l = mutableLongListOf(0L, 1L, 15L, 10L, 2L, 3L, 4L, 5L, 20L)
        assertTrue(l.retainAll(longArrayOf(1L, 2L, 3L, 4L, 5L, 6L)))
        assertEquals(list, l)
    }

    @Test
    fun removeRange() {
        val l = mutableLongListOf(1L, 9L, 7L, 6L, 2L, 3L, 4L, 5L)
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
        val l = mutableLongListOf(1L, 4L, 2L, 5L, 3L)
        l.sort()
        assertEquals(list, l)
    }

    @Test
    fun sortDescending() {
        val l = mutableLongListOf(1L, 4L, 2L, 5L, 3L)
        l.sortDescending()
        assertEquals(mutableLongListOf(5L, 4L, 3L, 2L, 1L), l)
    }

    @Test
    fun sortEmpty() {
        val l = MutableLongList(0)
        l.sort()
        l.sortDescending()
        assertEquals(MutableLongList(0), l)
    }

    @Test
    fun testEmptyLongList() {
        val l = emptyLongList()
        assertEquals(0, l.size)
    }

    @Test
    fun longListOfEmpty() {
        val l = longListOf()
        assertEquals(0, l.size)
    }

    @Test
    fun longListOfOneValue() {
        val l = longListOf(2L)
        assertEquals(1, l.size)
        assertEquals(2L, l[0])
    }

    @Test
    fun longListOfTwoValues() {
        val l = longListOf(2L, 1L)
        assertEquals(2, l.size)
        assertEquals(2L, l[0])
        assertEquals(1L, l[1])
    }

    @Test
    fun longListOfThreeValues() {
        val l = longListOf(2L, 10L, -1L)
        assertEquals(3, l.size)
        assertEquals(2L, l[0])
        assertEquals(10L, l[1])
        assertEquals(-1L, l[2])
    }

    @Test
    fun longListOfFourValues() {
        val l = longListOf(2L, 10L, -1L, 10L)
        assertEquals(4, l.size)
        assertEquals(2L, l[0])
        assertEquals(10L, l[1])
        assertEquals(-1L, l[2])
        assertEquals(10L, l[3])
    }

    @Test
    fun mutableLongListOfOneValue() {
        val l = mutableLongListOf(2L)
        assertEquals(1, l.size)
        assertEquals(1, l.capacity)
        assertEquals(2L, l[0])
    }

    @Test
    fun mutableLongListOfTwoValues() {
        val l = mutableLongListOf(2L, 1L)
        assertEquals(2, l.size)
        assertEquals(2, l.capacity)
        assertEquals(2L, l[0])
        assertEquals(1L, l[1])
    }

    @Test
    fun mutableLongListOfThreeValues() {
        val l = mutableLongListOf(2L, 10L, -1L)
        assertEquals(3, l.size)
        assertEquals(3, l.capacity)
        assertEquals(2L, l[0])
        assertEquals(10L, l[1])
        assertEquals(-1L, l[2])
    }

    @Test
    fun mutableLongListOfFourValues() {
        val l = mutableLongListOf(2L, 10L, -1L, 10L)
        assertEquals(4, l.size)
        assertEquals(4, l.capacity)
        assertEquals(2L, l[0])
        assertEquals(10L, l[1])
        assertEquals(-1L, l[2])
        assertEquals(10L, l[3])
    }

    @Test
    fun buildLongListFunction() {
        val contract: Boolean
        val l = buildLongList {
            contract = true
            add(2L)
            add(10L)
        }
        assertTrue(contract)
        assertEquals(2, l.size)
        assertEquals(2L, l[0])
        assertEquals(10L, l[1])
    }

    @Test
    fun buildLongListWithCapacityFunction() {
        val contract: Boolean
        val l = buildLongList(20) {
            contract = true
            add(2L)
            add(10L)
        }
        assertTrue(contract)
        assertEquals(2, l.size)
        assertTrue(l.content.size >= 20)
        assertEquals(2L, l[0])
        assertEquals(10L, l[1])
    }

    @Test
    fun binarySearchLongList() {
        val l = mutableLongListOf(-2L, -1L, 2L, 10L, 10L)
        assertEquals(0, l.binarySearch(-2))
        assertEquals(2, l.binarySearch(2))
        assertEquals(3, l.binarySearch(10))

        assertEquals(-1, l.binarySearch(-20))
        assertEquals(-4, l.binarySearch(3))
        assertEquals(-6, l.binarySearch(20))

    }
    
    @Test
    fun take() {
        assertEquals(longListOf(1L, 2L, 3L), list.take(3))
        assertEquals(longListOf(), list.take(0))
        assertEquals(list, list.take(100))
    }

    @Test
    fun takeLast() {
        assertEquals(longListOf(3L, 4L, 5L), list.takeLast(3))
        assertEquals(longListOf(), list.takeLast(0))
        assertEquals(list, list.takeLast(100))
    }

    @Test
    fun drop() {
        assertEquals(longListOf(4L, 5L), list.drop(3))
        assertEquals(list, list.drop(0))
        assertEquals(longListOf(), list.drop(100))
    }

    @Test
    fun dropLast() {
        assertEquals(longListOf(1L, 2L), list.dropLast(3))
        assertEquals(list, list.dropLast(0))
        assertEquals(longListOf(), list.dropLast(100))
    }
    
    @Test
    fun takeWhile() {
        assertEquals(longListOf(1L, 2L), list.takeWhile { it < 3L })
        assertEquals(longListOf(), list.takeWhile { it < 0L })
        assertEquals(list, list.takeWhile { it <= 5L })
        assertEquals(longListOf(), mutableLongListOf().takeWhile { it < 10L })
    }

    @Test
    fun takeLastWhile() {
        assertEquals(longListOf(4L, 5L), list.takeLastWhile { it > 3L })
        assertEquals(longListOf(), list.takeLastWhile { it > 5L })
        assertEquals(list, list.takeLastWhile { it >= 1L })
        assertEquals(longListOf(), mutableLongListOf().takeLastWhile { it > 0L })
    }

    @Test
    fun dropWhile() {
        assertEquals(longListOf(3L, 4L, 5L), list.dropWhile { it < 3L })
        assertEquals(list, list.dropWhile { it < 0L })
        assertEquals(longListOf(), list.dropWhile { it <= 5L })
        assertEquals(longListOf(), mutableLongListOf().dropWhile { it < 10L })
    }

    @Test
    fun dropLastWhile() {
        assertEquals(longListOf(1L, 2L, 3L), list.dropLastWhile { it > 3L })
        assertEquals(list, list.dropLastWhile { it > 5L })
        assertEquals(longListOf(), list.dropLastWhile { it >= 1L })
        assertEquals(longListOf(), mutableLongListOf().dropLastWhile { it > 0L })
    }
    
    @Test
    fun filter() {
        assertEquals(mutableLongListOf(1L, 2L), list.filter { it < 3L })
        assertEquals(mutableLongListOf(4L, 5L), list.filter { it > 3L })
        assertEquals(mutableLongListOf(), list.filter { it > 10L })
        assertEquals(list, list.filter { true })
        assertEquals(mutableLongListOf(), list.filter { false })
    }

    @Test
    fun copy() {
        val copy = list.copy()
        assertEquals(list, copy)
        assertNotEquals(list.content, copy.content)
    }

    @Test
    fun distinct() {
        val duplicates = mutableLongListOf(1L, 2L, 2L, 3L, 3L, 3L, 4L, 4L, 5L)
        assertEquals(list, duplicates.distinct())
        assertEquals(list, list.distinct()) 
        assertEquals(mutableLongListOf(), mutableLongListOf().distinct())
        assertEquals(mutableLongListOf(1L), mutableLongListOf(1L, 1L, 1L).distinct())
    }

    @Test
    fun sorted() {
        val unsorted = MutableLongList().also {
            it.addAll(longArrayOf(5L, 2L, 4L, 3L, 1L))
        }
        assertEquals(list, unsorted.sorted())
        assertEquals(list, list.sorted())
        assertEquals(mutableLongListOf(), mutableLongListOf().sorted())
        assertEquals(mutableLongListOf(1L), mutableLongListOf(1L).sorted())
    }

    @Test
    fun sortedDescending() {
        val unsorted = MutableLongList().also {
            it.addAll(longArrayOf(5L, 2L, 4L, 3L, 1L))
        }
        assertEquals(mutableLongListOf(5L, 4L, 3L, 2L, 1L), unsorted.sortedDescending())
        assertEquals(mutableLongListOf(5L, 4L, 3L, 2L, 1L), list.sortedDescending())
        assertEquals(mutableLongListOf(), mutableLongListOf().sortedDescending())
        assertEquals(mutableLongListOf(1L), mutableLongListOf(1L).sortedDescending())
    }
    
    @Test
    fun reversed() {
        assertEquals(mutableLongListOf(5L, 4L, 3L, 2L, 1L), list.reversed())
        assertEquals(mutableLongListOf(), mutableLongListOf().reversed())
        assertEquals(mutableLongListOf(1L), mutableLongListOf(1L).reversed())
        
        val duplicates = mutableLongListOf(1L, 1L, 2L, 2L)
        assertEquals(mutableLongListOf(2L, 2L, 1L, 1L), duplicates.reversed())
    }

    @Test
    fun chunked() {
        val result = list.chunked(2)
        assertEquals(3, result.size)
        assertEquals(mutableLongListOf(1L, 2L), result[0])
        assertEquals(mutableLongListOf(3L, 4L), result[1]) 
        assertEquals(mutableLongListOf(5L), result[2])

        assertEquals(0, mutableLongListOf().chunked(2).size)

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
    fun mutableLongListWithInit() {
        val list = MutableLongList(4) { (it + 1).toLong() }
        assertEquals(4, list.size)
        assertEquals(1L, list[0])
        assertEquals(2L, list[1])
        assertEquals(3L, list[2])
        assertEquals(4L, list[3])

        val empty = MutableLongList(0) { it.toLong() }
        assertEquals(0, empty.size)

        val zeros = MutableLongList(3) { 0L }
        assertEquals(3, zeros.size)
        assertEquals(0L, zeros[0])
        assertEquals(0L, zeros[1])
        assertEquals(0L, zeros[2])

        val complex = MutableLongList(3) { index ->
            when (index) {
                0 -> -1L
                1 -> 0L
                else -> 1L
            }
        }
        assertEquals(3, complex.size)
        assertEquals(-1L, complex[0])
        assertEquals(0L, complex[1])
        assertEquals(1L, complex[2])
    }

    @Test
    fun longListWithInit() {
        val list = LongList(4) { (it + 1).toLong() }
        assertEquals(4, list.size)
        assertEquals(1L, list[0])
        assertEquals(2L, list[1])
        assertEquals(3L, list[2])
        assertEquals(4L, list[3])
    }
    
    @Test
    fun all() {
        assertTrue(list.all { it > 0L })
        assertTrue(list.all { it < 6L })
        assertFalse(list.all { it < 5L })
        assertFalse(list.all { it > 3L })
        
        assertTrue(mutableLongListOf().all { it > 0L })
        
        assertTrue(mutableLongListOf(1L).all { it == 1L })
        assertFalse(mutableLongListOf(1L).all { it == 2L })
        
        val duplicates = mutableLongListOf(2L, 2L, 2L)
        assertTrue(duplicates.all { it == 2L })
        
        var count = 0
        assertFalse(list.all { 
            count++
            it > 3L 
        })
        assertTrue(count < list.size)
    }

    @Test
    fun reversedAll() {
        assertTrue(list.reversedAll { it < 6L })
        assertTrue(list.reversedAll { it > 0L })
        assertFalse(list.reversedAll { it > 3L })
        assertFalse(list.reversedAll { it < 3L })
        
        assertTrue(mutableLongListOf().reversedAll { it > 0L })
        
        assertTrue(mutableLongListOf(1L).reversedAll { it == 1L })
        assertFalse(mutableLongListOf(1L).reversedAll { it == 2L })
        
        val duplicates = mutableLongListOf(2L, 2L, 2L)
        assertTrue(duplicates.reversedAll { it == 2L })
        
        val evaluated = mutableLongListOf()
        assertTrue(list.reversedAll { 
            evaluated.add(it)
            true
        })
        assertEquals(mutableLongListOf(5L, 4L, 3L, 2L, 1L), evaluated)
        
        var count = 0
        assertFalse(list.reversedAll { 
            count++
            it < 3L 
        })
        assertTrue(count < list.size)
    }
    
    @Test
    fun sum() {
        assertEquals(15L, list.sum())
        assertEquals(0L, mutableLongListOf().sum())
        assertEquals(1L, mutableLongListOf(1L).sum())
        assertEquals(3L, mutableLongListOf(1L, 2L).sum())
        
        val negatives = mutableLongListOf(-2L, -1L, 0L, 1L, 2L)
        assertEquals(0L, negatives.sum())
    }

    @Test 
    fun sumOf() {
        assertEquals(30L, list.sumOf { it * 2 })
        assertEquals(0L, mutableLongListOf().sumOf { it * 2 })
        assertEquals(2L, mutableLongListOf(1L).sumOf { it * 2 })
        
        val result = list.sumOf { 
            if (it % 2L == 0L) it
            else 0L
        }
        assertEquals(6L, result)
        
        assertEquals(5L, list.sumOf { 1L })
        assertEquals(15L, list.sumOf { it })
    }
    
    @Test
    fun orEmpty() {
        // Test non-null list returns itself
        val nonNullList = longListOf(1L, 2L, 3L)
        assertEquals(nonNullList, nonNullList.orEmpty())
        
        // Test null list returns empty list
        val nullList: LongList? = null
        val emptyResult = nullList.orEmpty()
        assertTrue(emptyResult.isEmpty())

        // Test empty list returns itself
        val emptyList = longListOf()
        assertEquals(emptyList, emptyList.orEmpty())
    }

    @Test
    fun jsonSerialization() {
        // Test basic serialization and deserialization
        val json = Json
        val list = longListOf(1L, 2L, 3L, 4L, 5L)

        val serialized = json.encodeToString(list)
        val deserialized = json.decodeFromString<LongList>(serialized)
        assertEquals(list, deserialized)

        // Test empty list
        val emptyList = longListOf()
        val emptyListSerialized = json.encodeToString(emptyList)
        val emptyListDeserialized = json.decodeFromString<LongList>(emptyListSerialized)
        assertEquals(emptyList, emptyListDeserialized)

        // Test single element
        val singleList = longListOf(1L)
        val singleListSerialized = json.encodeToString(singleList)
        val singleListDeserialized = json.decodeFromString<LongList>(singleListSerialized)
        assertEquals(singleList, singleListDeserialized)

        // Compare with standard List serialization
        val standardList = listOf(1L, 2L, 3L, 4L, 5L)
        val standardListSerialized = json.encodeToString(standardList)
        assertEquals(serialized, standardListSerialized)

        // Test with custom values
        val mixedList = longListOf(-1L, 0L, 1L, 100L)
        val mixedListSerialized = json.encodeToString(mixedList)
        val mixedListDeserialized = json.decodeFromString<LongList>(mixedListSerialized)
        assertEquals(mixedList, mixedListDeserialized)

        // Compare mixed list with standard List
        val standardMixedList = listOf(-1L, 0L, 1L, 100L)
        val standardMixedSerialized = json.encodeToString(standardMixedList)
        assertEquals(mixedListSerialized, standardMixedSerialized)
    }
    
    @Test
    fun toLongArray() {
        // Test regular list
        val array = list.toLongArray()
        assertEquals(5, array.size)
        assertEquals(1L, array[0])
        assertEquals(2L, array[1])
        assertEquals(3L, array[2])
        assertEquals(4L, array[3])
        assertEquals(5L, array[4])
        
        // Test empty list
        val emptyArray = mutableLongListOf().toLongArray()
        assertEquals(0, emptyArray.size)
        
        // Test single element list
        val singleArray = mutableLongListOf(1L).toLongArray()
        assertEquals(1, singleArray.size)
        assertEquals(1L, singleArray[0])
        
        // Verify array is a copy
        val originalList = mutableLongListOf(1L, 2L, 3L)
        val copiedArray = originalList.toLongArray()
        originalList[0] = 5L
        assertEquals(1L, copiedArray[0])
    }
    
    @Test
    fun toSet() {
        // Test regular list
        val set = list.toSet()
        assertEquals(5, set.size)
        assertTrue(set.contains(1L))
        assertTrue(set.contains(2L))
        assertTrue(set.contains(3L))
        assertTrue(set.contains(4L))
        assertTrue(set.contains(5L))
        
        // Test empty list
        val emptySet = mutableLongListOf().toSet()
        assertTrue(emptySet.isEmpty())
        
        // Test single element list
        val singleSet = mutableLongListOf(1L).toSet()
        assertEquals(1, singleSet.size)
        assertTrue(singleSet.contains(1L))
        
        // Test list with duplicates
        val duplicateList = mutableLongListOf(1L, 2L, 2L, 3L, 3L, 3L)
        val duplicateSet = duplicateList.toSet()
        assertEquals(3, duplicateSet.size)
        assertTrue(duplicateSet.contains(1L))
        assertTrue(duplicateSet.contains(2L))
        assertTrue(duplicateSet.contains(3L))
    }

    @Test
    fun toMutableSet() {
        // Test regular list
        val set = list.toMutableSet()
        assertEquals(5, set.size)
        assertTrue(set.contains(1L))
        assertTrue(set.contains(2L))
        assertTrue(set.contains(3L))
        assertTrue(set.contains(4L))
        assertTrue(set.contains(5L))

        // Verify mutability
        set.add(6L)
        assertEquals(6, set.size)
        assertTrue(set.contains(6L))
        
        // Test empty list
        val emptySet = mutableLongListOf().toMutableSet()
        assertTrue(emptySet.isEmpty())
        emptySet.add(1L)
        assertEquals(1, emptySet.size)
        
        // Test single element list
        val singleSet = mutableLongListOf(1L).toMutableSet()
        assertEquals(1, singleSet.size)
        assertTrue(singleSet.contains(1L))
        
        // Test list with duplicates
        val duplicateList = mutableLongListOf(1L, 2L, 2L, 3L, 3L, 3L)
        val duplicateSet = duplicateList.toMutableSet()
        assertEquals(3, duplicateSet.size)
        assertTrue(duplicateSet.contains(1L))
        assertTrue(duplicateSet.contains(2L))
        assertTrue(duplicateSet.contains(3L))
    }
}
