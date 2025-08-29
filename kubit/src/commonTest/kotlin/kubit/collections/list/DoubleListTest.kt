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
class DoubleListTest {
    private val list: MutableDoubleList = mutableDoubleListOf(1.0, 2.0, 3.0, 4.0, 5.0)

    @Test
    fun emptyConstruction() {
        val l = mutableDoubleListOf()
        assertEquals(0, l.size)
        assertEquals(16, l.capacity)
    }

    @Test
    fun sizeConstruction() {
        val l = MutableDoubleList(4)
        assertEquals(4, l.capacity)
    }

    @Test
    fun contentConstruction() {
        val l = mutableDoubleListOf(1.0, 2.0, 3.0)
        assertEquals(3, l.size)
        assertEquals(1.0, l[0])
        assertEquals(2.0, l[1])
        assertEquals(3.0, l[2])
        assertEquals(3, l.capacity)
        repeat(2) {
            val l2 = mutableDoubleListOf(1.0, 2.0, 3.0, 4.0, 5.0)
            assertEquals(list, l2)
            l2.removeAt(0)
        }
    }

    @Test
    fun hashCodeTest() {
        val l2 = mutableDoubleListOf(1.0, 2.0, 3.0, 4.0, 5.0)
        assertEquals(list.hashCode(), l2.hashCode())
        l2.removeAt(4)
        assertNotEquals(list.hashCode(), l2.hashCode())
        l2.add(5.0)
        assertEquals(list.hashCode(), l2.hashCode())
        l2.clear()
        assertNotEquals(list.hashCode(), l2.hashCode())
    }

    @Test
    fun equalsTest() {
        val l2 = mutableDoubleListOf(1.0, 2.0, 3.0, 4.0, 5.0)
        assertEquals(list, l2)
        assertNotEquals(list, mutableDoubleListOf())
        l2.removeAt(4)
        assertNotEquals(list, l2)
        l2.add(5.0)
        assertEquals(list, l2)
        l2.clear()
        assertNotEquals(list, l2)
    }

    @Test
    fun string() {
        assertEquals("[${1.0}, ${2.0}, ${3.0}, ${4.0}, ${5.0}]", list.toString())
        assertEquals("[]", mutableDoubleListOf().toString())
    }

    @Test
    fun joinToString() {
        assertEquals("${1.0}, ${2.0}, ${3.0}, ${4.0}, ${5.0}", list.joinToString())
        assertEquals(
            "x${1.0}, ${2.0}, ${3.0}, ...y",
            list.joinToString(prefix = "x", postfix = "y", limit = 3)
        )
        assertEquals(
            ">${1.0}-${2.0}-${3.0}-${4.0}-${5.0}<",
            list.joinToString(separator = "-", prefix = ">", postfix = "<")
        )
        assertEquals("one, two, three, ...", list.joinToString(limit = 3) {
            when (it) {
                1.0 -> "one"
                2.0 -> "two"
                3.0 -> "three"
                else -> "whoops"
            }
        })
    }

    @Test
    fun size() {
        assertEquals(5, list.size)
        assertEquals(5, list.count())
        val l2 = mutableDoubleListOf()
        assertEquals(0, l2.size)
        assertEquals(0, l2.count())
        l2 += 1.0
        assertEquals(1, l2.size)
        assertEquals(1, l2.count())
    }

    @Test
    fun get() {
        assertEquals(1.0, list[0])
        assertEquals(5.0, list[4])
        assertEquals(1.0, list.elementAt(0))
        assertEquals(5.0, list.elementAt(4))
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
        assertEquals(1.0, list.elementAtOrElse(0) {
            assertEquals(0, it)
            0.0
        })
        assertEquals(0.0, list.elementAtOrElse(-1) {
            assertEquals(-1, it)
            0.0
        })
        assertEquals(0.0, list.elementAtOrElse(5) {
            assertEquals(5, it)
            0.0
        })
    }

    @Test
    fun count() {
        assertEquals(1, list.count { it < 2.0 })
        assertEquals(0, list.count { it < 0.0 })
        assertEquals(5, list.count { it < 10.0 })
    }

    @Test
    fun isEmpty() {
        assertFalse(list.isEmpty())
        assertFalse(list.none())
        assertTrue(mutableDoubleListOf().isEmpty())
        assertTrue(mutableDoubleListOf().none())
    }

    @Test
    fun isNotEmpty() {
        assertTrue(list.isNotEmpty())
        assertTrue(list.any())
        assertFalse(mutableDoubleListOf().isNotEmpty())
    }

    @Test
    fun indices() {
        assertEquals(IntRange(0, 4), list.indices)
        assertEquals(IntRange(0, -1), mutableDoubleListOf().indices)
    }

    @Test
    fun any() {
        assertTrue(list.any { it == 5.0 })
        assertTrue(list.any { it == 1.0 })
        assertFalse(list.any { it == 0.0 })
    }

    @Test
    fun reversedAny() {
        val reversedList = mutableDoubleListOf()
        assertFalse(
            list.reversedAny {
                reversedList.add(it)
                false
            }
        )
        val reversedContent = mutableDoubleListOf(5.0, 4.0, 3.0, 2.0, 1.0)
        assertEquals(reversedContent, reversedList)

        val reversedSublist = mutableDoubleListOf()
        assertTrue(
            list.reversedAny {
                reversedSublist.add(it)
                reversedSublist.size == 2
            }
        )
        assertEquals(reversedSublist, mutableDoubleListOf(5.0, 4.0))
    }
    
    @Test
    fun `atLeast returns true when count greater than or equal to n`() {
        val list = doubleListOf(1.0, 2.0, 3.0, 4.0, 5.0)
        val result = list.atLeast(2) { it > 3.0 }
        assertTrue(result)
    }

    @Test
    fun `atLeast returns false when count less than n`() {
        val list = doubleListOf(1.0, 2.0, 3.0, 4.0, 5.0)
        val result = list.atLeast(3) { it > 3.0 }
        assertEquals(false, result)
    }

    @Test
    fun `atLeast with n equals 0 returns true`() {
        val list = doubleListOf(1.0, 2.0, 3.0, 4.0, 5.0)
        val result = list.atLeast(0) { it > 100.0 }
        assertTrue(result)
    }

    @Test
    fun `atMost returns true when count less than or equal to n`() {
        val list = doubleListOf(1.0, 2.0, 3.0, 4.0, 5.0)
        val result = list.atMost(2) { it > 3.0 }
        assertTrue(result)
    }

    @Test
    fun `atMost returns false when count greater than n`() {
        val list = doubleListOf(1.0, 2.0, 3.0, 4.0, 5.0)
        val result = list.atMost(1) { it > 3.0 }
        assertEquals(false, result)
    }

    @Test
    fun `atMost with n equals 0 and no matches returns true`() {
        val list = doubleListOf(1.0, 2.0, 3.0)
        val result = list.atMost(0) { it > 10.0 }
        assertTrue(result)
    }

    @Test
    fun `atMost with n equals 0 and matches returns false`() {
        val list = doubleListOf(1.0, 2.0, 3.0)
        val result = list.atMost(0) { it > 1.0 }
        assertEquals(false, result)
    }
    
    @Test
    fun `atLeast throws IllegalArgumentException when n less than 0`() {
        val list = doubleListOf(1.0, 2.0, 3.0, 4.0, 5.0)
        assertFailsWith<IllegalArgumentException> {
            list.atLeast(-1) { it > 3.0 }
        }
    }

    @Test
    fun `atMost throws IllegalArgumentException when n less than 0`() {
        val list = doubleListOf(1.0, 2.0, 3.0, 4.0, 5.0)
        assertFailsWith<IllegalArgumentException> {
            list.atMost(-1) { it > 3.0 }
        }
    }

    @Test
    fun forEach() {
        val copy = mutableDoubleListOf()
        list.forEach { copy += it }
        assertEquals(list, copy)
    }

    @Test
    fun forEachReversed() {
        val copy = mutableDoubleListOf()
        list.forEachReversed { copy += it }
        assertEquals(copy, mutableDoubleListOf(5.0, 4.0, 3.0, 2.0, 1.0))
    }

    @Test
    fun forEachIndexed() {
        val copy = mutableDoubleListOf()
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
        val copy = mutableDoubleListOf()
        val indices = mutableIntListOf()
        list.forEachReversedIndexed { index, item ->
            copy += item
            indices += index
        }
        assertEquals(copy, mutableDoubleListOf(5.0, 4.0, 3.0, 2.0, 1.0))
        assertEquals(indices, intListOf(4, 3, 2, 1, 0))
    }

    @Test
    fun indexOfFirst() {
        assertEquals(0, list.indexOfFirst { it == 1.0 })
        assertEquals(4, list.indexOfFirst { it == 5.0 })
        assertEquals(-1, list.indexOfFirst { it == 0.0 })
        assertEquals(0, mutableDoubleListOf(8.0, 8.0).indexOfFirst { it == 8.0 })
    }

    @Test
    fun indexOfLast() {
        assertEquals(0, list.indexOfLast { it == 1.0 })
        assertEquals(4, list.indexOfLast { it == 5.0 })
        assertEquals(-1, list.indexOfLast { it == 0.0 })
        assertEquals(1, mutableDoubleListOf(8.0, 8.0).indexOfLast { it == 8.0 })
    }

    @Test
    fun contains() {
        assertTrue(list.contains(5.0))
        assertTrue(list.contains(1.0))
        assertFalse(list.contains(0.0))
    }

    @Test
    fun containsAllList() {
        assertTrue(list.containsAll(mutableDoubleListOf(2.0, 3.0, 1.0)))
        assertFalse(list.containsAll(mutableDoubleListOf(2.0, 3.0, 6.0)))
    }

    @Test
    fun lastIndexOf() {
        assertEquals(4, list.lastIndexOf(5.0))
        assertEquals(1, list.lastIndexOf(2.0))
        val copy = mutableDoubleListOf()
        copy.addAll(list)
        copy.addAll(list)
        assertEquals(5, copy.lastIndexOf(1.0))
    }

    @Test
    fun first() {
        assertEquals(1.0, list.first())
    }

    @Test
    fun firstException() {
        assertFailsWith(NoSuchElementException::class) {
            mutableDoubleListOf().first()
        }
    }

    @Test
    fun firstWithPredicate() {
        assertEquals(5.0, list.first { it == 5.0 })
        assertEquals(1.0, mutableDoubleListOf(1.0, 5.0).first { it != 0.0 })
    }

    @Test
    fun firstWithPredicateException() {
        assertFailsWith(NoSuchElementException::class) {
            mutableDoubleListOf().first { it == 8.0 }
        }
    }

    @Test
    fun last() {
        assertEquals(5.0, list.last())
    }

    @Test
    fun lastException() {
        assertFailsWith(NoSuchElementException::class) {
            mutableDoubleListOf().last()
        }
    }

    @Test
    fun lastWithPredicate() {
        assertEquals(1.0, list.last { it == 1.0 })
        assertEquals(5.0, mutableDoubleListOf(1.0, 5.0).last { it != 0.0 })
    }

    @Test
    fun lastWithPredicateException() {
        assertFailsWith(NoSuchElementException::class) {
            mutableDoubleListOf().last { it == 8.0 }
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
        val l = mutableDoubleListOf(1.0, 2.0, 3.0)
        l += 4.0
        l.add(5.0)
        assertEquals(list, l)
    }

    @Test
    fun addAtIndex() {
        val l = mutableDoubleListOf(2.0, 4.0)
        l.add(2, 5.0)
        l.add(0, 1.0)
        l.add(2, 3.0)
        assertEquals(list, l)
        assertFailsWith(IndexOutOfBoundsException::class) {
            l.add(-1, 2.0)
        }
        assertFailsWith(IndexOutOfBoundsException::class) {
            l.add(6, 2.0)
        }
    }

    @Test
    fun addAllListAtIndex() {
        val l = mutableDoubleListOf(4.0)
        val l2 = mutableDoubleListOf(1.0, 2.0)
        val l3 = mutableDoubleListOf(5.0)
        val l4 = mutableDoubleListOf(3.0)
        assertTrue(l4.addAll(1, l3))
        assertTrue(l4.addAll(0, l2))
        assertTrue(l4.addAll(3, l))
        assertFalse(l4.addAll(0, mutableDoubleListOf()))
        assertEquals(list, l4)
        assertFailsWith(IndexOutOfBoundsException::class) {
            l4.addAll(6, mutableDoubleListOf())
        }
        assertFailsWith(IndexOutOfBoundsException::class) {
            l4.addAll(-1, mutableDoubleListOf())
        }
    }

    @Test
    fun addAllList() {
        val l = MutableDoubleList()
        l.add(3.0)
        l.add(4.0)
        l.add(5.0)
        val l2 = mutableDoubleListOf(1.0, 2.0)
        assertTrue(l2.addAll(l))
        assertEquals(list, l2)
        assertFalse(l2.addAll(mutableDoubleListOf()))
    }

    @Test
    fun plusAssignList() {
        val l = MutableDoubleList()
        l.add(3.0)
        l.add(4.0)
        l.add(5.0)
        val l2 = mutableDoubleListOf(1.0, 2.0)
        l2 += l
        assertEquals(list, l2)
    }

    @Test
    fun addAllArrayAtIndex() {
        val a1 = doubleArrayOf(4.0)
        val a2 = doubleArrayOf(1.0, 2.0)
        val a3 = doubleArrayOf(5.0)
        val l = mutableDoubleListOf(3.0)
        assertTrue(l.addAll(1, a3))
        assertTrue(l.addAll(0, a2))
        assertTrue(l.addAll(3, a1))
        assertFalse(l.addAll(0, doubleArrayOf()))
        assertEquals(list, l)
        assertFailsWith(IndexOutOfBoundsException::class) {
            l.addAll(6, doubleArrayOf())
        }
        assertFailsWith(IndexOutOfBoundsException::class) {
            l.addAll(-1, doubleArrayOf())
        }
    }

    @Test
    fun addAllArray() {
        val a = doubleArrayOf(3.0, 4.0, 5.0)
        val v = mutableDoubleListOf(1.0, 2.0)
        v.addAll(a)
        assertEquals(5, v.size)
        assertEquals(3.0, v[2])
        assertEquals(4.0, v[3])
        assertEquals(5.0, v[4])
    }
    
    @Test
    fun addAllGenericList() {
        val original = mutableDoubleListOf(1.0, 2.0)
        val toAdd = mutableListOf(3.0, 4.0, 5.0)
        
        assertTrue(original.addAll(toAdd))
        assertEquals(5, original.size)
        assertEquals(list, original)
        
        // Test adding empty list
        assertFalse(original.addAll(mutableListOf()))
        assertEquals(5, original.size)
        assertEquals(list, original)
        
        // Test adding to empty list
        val empty = mutableDoubleListOf()
        assertTrue(empty.addAll(toAdd))
        assertEquals(mutableDoubleListOf(3.0, 4.0, 5.0), empty)
        
        // Test capacity expansion
        val small = MutableDoubleList(2)
        small.add(1.0)
        assertTrue(small.addAll(mutableListOf(2.0, 3.0, 4.0)))
        assertEquals(mutableDoubleListOf(1.0, 2.0, 3.0, 4.0), small)
    }

    @Test
    fun plusAssignArray() {
        val a = doubleArrayOf(3.0, 4.0, 5.0)
        val v = mutableDoubleListOf(1.0, 2.0)
        v += a
        assertEquals(list, v)
    }

    @Test
    fun clear() {
        val l = mutableDoubleListOf()
        l.addAll(list)
        assertTrue(l.isNotEmpty())
        l.clear()
        assertTrue(l.isEmpty())
    }

    @Test
    fun trim() {
        val l = mutableDoubleListOf(1.0)
        l.trim()
        assertEquals(1, l.capacity)
        l += doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0)
        l.trim()
        assertEquals(6, l.capacity)
        assertEquals(6, l.size)
        l.clear()
        l.trim()
        assertEquals(0, l.capacity)
        l.trim(100)
        assertEquals(0, l.capacity)
        l += doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0)
        l -= 5.0
        l.trim(5)
        assertEquals(5, l.capacity)
        l.trim(4)
        assertEquals(4, l.capacity)
        l.trim(3)
        assertEquals(4, l.capacity)
    }

    @Test
    fun remove() {
        val l = mutableDoubleListOf(1.0, 2.0, 3.0, 4.0, 5.0)
        l.remove(3.0)
        assertEquals(mutableDoubleListOf(1.0, 2.0, 4.0, 5.0), l)
    }

    @Test
    fun removeAt() {
        val l = mutableDoubleListOf(1.0, 2.0, 3.0, 4.0, 5.0)
        l.removeAt(2)
        assertEquals(mutableDoubleListOf(1.0, 2.0, 4.0, 5.0), l)
        assertFailsWith(IndexOutOfBoundsException::class) {
            l.removeAt(6)
        }
        assertFailsWith(IndexOutOfBoundsException::class) {
            l.removeAt(-1)
        }
    }

    @Test
    fun set() {
        val l = mutableDoubleListOf(0.0, 0.0, 0.0, 0.0, 0.0)
        l[0] = 1.0
        l[4] = 5.0
        l[2] = 3.0
        l[1] = 2.0
        l[3] = 4.0
        assertEquals(list, l)
        assertFailsWith<IndexOutOfBoundsException> {
            l.set(-1, 1.0)
        }
        assertFailsWith<IndexOutOfBoundsException> {
            l.set(6, 1.0)
        }
        assertEquals(4.0, l.set(3, 1.0))
    }

    @Test
    fun ensureCapacity() {
        val l = mutableDoubleListOf(1.0)
        assertEquals(1, l.capacity)
        l.ensureCapacity(5)
        assertEquals(5, l.capacity)
    }

    @Test
    fun removeAllList() {
        assertFalse(list.removeAll(mutableDoubleListOf(0.0, 10.0, 15.0)))
        val l = mutableDoubleListOf(0.0, 1.0, 15.0, 10.0, 2.0, 3.0, 10.0, 4.0, 5.0, 20.0)
        assertTrue(l.removeAll(mutableDoubleListOf(20.0, 0.0, 15.0, 10.0)))
        assertEquals(list, l)
    }

    @Test
    fun removeAllDoubleArray() {
        assertFalse(list.removeAll(doubleArrayOf(0.0, 10.0, 15.0)))
        val l = mutableDoubleListOf(0.0, 1.0, 15.0, 10.0, 2.0, 3.0, 10.0, 4.0, 5.0, 20.0)
        assertTrue(l.removeAll(doubleArrayOf(20.0, 0.0, 15.0, 10.0)))
        assertEquals(list, l)
    }

    @Test
    fun minusAssignList() {
        val l = mutableDoubleListOf().also { it += list }
        l -= mutableDoubleListOf(0.0, 10.0, 15.0)
        assertEquals(list, l)
        val l2 = mutableDoubleListOf(0.0, 1.0, 15.0, 10.0, 2.0, 3.0, 10.0, 4.0, 5.0, 20.0)
        l2 -= mutableDoubleListOf(20.0, 0.0, 15.0, 10.0)
        assertEquals(list, l2)
    }

    @Test
    fun minusAssignDoubleArray() {
        val l = mutableDoubleListOf().also { it += list }
        l -= doubleArrayOf(0.0, 10.0, 15.0)
        assertEquals(list, l)
        val l2 = mutableDoubleListOf(0.0, 1.0, 15.0, 10.0, 2.0, 3.0, 10.0, 4.0, 5.0, 20.0)
        l2 -= doubleArrayOf(20.0, 0.0, 15.0, 10.0)
        assertEquals(list, l2)
    }

    @Test
    fun retainAll() {
        assertFalse(list.retainAll(mutableDoubleListOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0)))
        val l = mutableDoubleListOf(0.0, 1.0, 15.0, 10.0, 2.0, 3.0, 4.0, 5.0, 20.0)
        assertTrue(l.retainAll(mutableDoubleListOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0)))
        assertEquals(list, l)
    }

    @Test
    fun retainAllDoubleArray() {
        assertFalse(list.retainAll(doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0)))
        val l = mutableDoubleListOf(0.0, 1.0, 15.0, 10.0, 2.0, 3.0, 4.0, 5.0, 20.0)
        assertTrue(l.retainAll(doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0)))
        assertEquals(list, l)
    }

    @Test
    fun removeRange() {
        val l = mutableDoubleListOf(1.0, 9.0, 7.0, 6.0, 2.0, 3.0, 4.0, 5.0)
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
        val l = mutableDoubleListOf(1.0, 4.0, 2.0, 5.0, 3.0)
        l.sort()
        assertEquals(list, l)
    }

    @Test
    fun sortDescending() {
        val l = mutableDoubleListOf(1.0, 4.0, 2.0, 5.0, 3.0)
        l.sortDescending()
        assertEquals(mutableDoubleListOf(5.0, 4.0, 3.0, 2.0, 1.0), l)
    }

    @Test
    fun sortEmpty() {
        val l = MutableDoubleList(0)
        l.sort()
        l.sortDescending()
        assertEquals(MutableDoubleList(0), l)
    }

    @Test
    fun testEmptyDoubleList() {
        val l = emptyDoubleList()
        assertEquals(0, l.size)
    }

    @Test
    fun doubleListOfEmpty() {
        val l = doubleListOf()
        assertEquals(0, l.size)
    }

    @Test
    fun doubleListOfOneValue() {
        val l = doubleListOf(2.0)
        assertEquals(1, l.size)
        assertEquals(2.0, l[0])
    }

    @Test
    fun doubleListOfTwoValues() {
        val l = doubleListOf(2.0, 1.0)
        assertEquals(2, l.size)
        assertEquals(2.0, l[0])
        assertEquals(1.0, l[1])
    }

    @Test
    fun doubleListOfThreeValues() {
        val l = doubleListOf(2.0, 10.0, -1.0)
        assertEquals(3, l.size)
        assertEquals(2.0, l[0])
        assertEquals(10.0, l[1])
        assertEquals(-1.0, l[2])
    }

    @Test
    fun doubleListOfFourValues() {
        val l = doubleListOf(2.0, 10.0, -1.0, 10.0)
        assertEquals(4, l.size)
        assertEquals(2.0, l[0])
        assertEquals(10.0, l[1])
        assertEquals(-1.0, l[2])
        assertEquals(10.0, l[3])
    }

    @Test
    fun mutableDoubleListOfOneValue() {
        val l = mutableDoubleListOf(2.0)
        assertEquals(1, l.size)
        assertEquals(1, l.capacity)
        assertEquals(2.0, l[0])
    }

    @Test
    fun mutableDoubleListOfTwoValues() {
        val l = mutableDoubleListOf(2.0, 1.0)
        assertEquals(2, l.size)
        assertEquals(2, l.capacity)
        assertEquals(2.0, l[0])
        assertEquals(1.0, l[1])
    }

    @Test
    fun mutableDoubleListOfThreeValues() {
        val l = mutableDoubleListOf(2.0, 10.0, -1.0)
        assertEquals(3, l.size)
        assertEquals(3, l.capacity)
        assertEquals(2.0, l[0])
        assertEquals(10.0, l[1])
        assertEquals(-1.0, l[2])
    }

    @Test
    fun mutableDoubleListOfFourValues() {
        val l = mutableDoubleListOf(2.0, 10.0, -1.0, 10.0)
        assertEquals(4, l.size)
        assertEquals(4, l.capacity)
        assertEquals(2.0, l[0])
        assertEquals(10.0, l[1])
        assertEquals(-1.0, l[2])
        assertEquals(10.0, l[3])
    }

    @Test
    fun buildDoubleListFunction() {
        val contract: Boolean
        val l = buildDoubleList {
            contract = true
            add(2.0)
            add(10.0)
        }
        assertTrue(contract)
        assertEquals(2, l.size)
        assertEquals(2.0, l[0])
        assertEquals(10.0, l[1])
    }

    @Test
    fun buildDoubleListWithCapacityFunction() {
        val contract: Boolean
        val l = buildDoubleList(20) {
            contract = true
            add(2.0)
            add(10.0)
        }
        assertTrue(contract)
        assertEquals(2, l.size)
        assertTrue(l.content.size >= 20)
        assertEquals(2.0, l[0])
        assertEquals(10.0, l[1])
    }

    @Test
    fun binarySearchDoubleList() {
        val l = mutableDoubleListOf(-2.0, -1.0, 2.0, 10.0, 10.0)
        assertEquals(0, l.binarySearch(-2))
        assertEquals(2, l.binarySearch(2))
        assertEquals(3, l.binarySearch(10))

        assertEquals(-1, l.binarySearch(-20))
        assertEquals(-4, l.binarySearch(3))
        assertEquals(-6, l.binarySearch(20))

    }
    
    @Test
    fun take() {
        assertEquals(doubleListOf(1.0, 2.0, 3.0), list.take(3))
        assertEquals(doubleListOf(), list.take(0))
        assertEquals(list, list.take(100))
    }

    @Test
    fun takeLast() {
        assertEquals(doubleListOf(3.0, 4.0, 5.0), list.takeLast(3))
        assertEquals(doubleListOf(), list.takeLast(0))
        assertEquals(list, list.takeLast(100))
    }

    @Test
    fun drop() {
        assertEquals(doubleListOf(4.0, 5.0), list.drop(3))
        assertEquals(list, list.drop(0))
        assertEquals(doubleListOf(), list.drop(100))
    }

    @Test
    fun dropLast() {
        assertEquals(doubleListOf(1.0, 2.0), list.dropLast(3))
        assertEquals(list, list.dropLast(0))
        assertEquals(doubleListOf(), list.dropLast(100))
    }
    
    @Test
    fun takeWhile() {
        assertEquals(doubleListOf(1.0, 2.0), list.takeWhile { it < 3.0 })
        assertEquals(doubleListOf(), list.takeWhile { it < 0.0 })
        assertEquals(list, list.takeWhile { it <= 5.0 })
        assertEquals(doubleListOf(), mutableDoubleListOf().takeWhile { it < 10.0 })
    }

    @Test
    fun takeLastWhile() {
        assertEquals(doubleListOf(4.0, 5.0), list.takeLastWhile { it > 3.0 })
        assertEquals(doubleListOf(), list.takeLastWhile { it > 5.0 })
        assertEquals(list, list.takeLastWhile { it >= 1.0 })
        assertEquals(doubleListOf(), mutableDoubleListOf().takeLastWhile { it > 0.0 })
    }

    @Test
    fun dropWhile() {
        assertEquals(doubleListOf(3.0, 4.0, 5.0), list.dropWhile { it < 3.0 })
        assertEquals(list, list.dropWhile { it < 0.0 })
        assertEquals(doubleListOf(), list.dropWhile { it <= 5.0 })
        assertEquals(doubleListOf(), mutableDoubleListOf().dropWhile { it < 10.0 })
    }

    @Test
    fun dropLastWhile() {
        assertEquals(doubleListOf(1.0, 2.0, 3.0), list.dropLastWhile { it > 3.0 })
        assertEquals(list, list.dropLastWhile { it > 5.0 })
        assertEquals(doubleListOf(), list.dropLastWhile { it >= 1.0 })
        assertEquals(doubleListOf(), mutableDoubleListOf().dropLastWhile { it > 0.0 })
    }
    
    @Test
    fun filter() {
        assertEquals(mutableDoubleListOf(1.0, 2.0), list.filter { it < 3.0 })
        assertEquals(mutableDoubleListOf(4.0, 5.0), list.filter { it > 3.0 })
        assertEquals(mutableDoubleListOf(), list.filter { it > 10.0 })
        assertEquals(list, list.filter { true })
        assertEquals(mutableDoubleListOf(), list.filter { false })
    }

    @Test
    fun copy() {
        val copy = list.copy()
        assertEquals(list, copy)
        assertNotEquals(list.content, copy.content)
    }

    @Test
    fun distinct() {
        val duplicates = mutableDoubleListOf(1.0, 2.0, 2.0, 3.0, 3.0, 3.0, 4.0, 4.0, 5.0)
        assertEquals(list, duplicates.distinct())
        assertEquals(list, list.distinct()) 
        assertEquals(mutableDoubleListOf(), mutableDoubleListOf().distinct())
        assertEquals(mutableDoubleListOf(1.0), mutableDoubleListOf(1.0, 1.0, 1.0).distinct())
    }

    @Test
    fun sorted() {
        val unsorted = MutableDoubleList().also {
            it.addAll(doubleArrayOf(5.0, 2.0, 4.0, 3.0, 1.0))
        }
        assertEquals(list, unsorted.sorted())
        assertEquals(list, list.sorted())
        assertEquals(mutableDoubleListOf(), mutableDoubleListOf().sorted())
        assertEquals(mutableDoubleListOf(1.0), mutableDoubleListOf(1.0).sorted())
    }

    @Test
    fun sortedDescending() {
        val unsorted = MutableDoubleList().also {
            it.addAll(doubleArrayOf(5.0, 2.0, 4.0, 3.0, 1.0))
        }
        assertEquals(mutableDoubleListOf(5.0, 4.0, 3.0, 2.0, 1.0), unsorted.sortedDescending())
        assertEquals(mutableDoubleListOf(5.0, 4.0, 3.0, 2.0, 1.0), list.sortedDescending())
        assertEquals(mutableDoubleListOf(), mutableDoubleListOf().sortedDescending())
        assertEquals(mutableDoubleListOf(1.0), mutableDoubleListOf(1.0).sortedDescending())
    }
    
    @Test
    fun reversed() {
        assertEquals(mutableDoubleListOf(5.0, 4.0, 3.0, 2.0, 1.0), list.reversed())
        assertEquals(mutableDoubleListOf(), mutableDoubleListOf().reversed())
        assertEquals(mutableDoubleListOf(1.0), mutableDoubleListOf(1.0).reversed())
        
        val duplicates = mutableDoubleListOf(1.0, 1.0, 2.0, 2.0)
        assertEquals(mutableDoubleListOf(2.0, 2.0, 1.0, 1.0), duplicates.reversed())
    }

    @Test
    fun chunked() {
        val result = list.chunked(2)
        assertEquals(3, result.size)
        assertEquals(mutableDoubleListOf(1.0, 2.0), result[0])
        assertEquals(mutableDoubleListOf(3.0, 4.0), result[1]) 
        assertEquals(mutableDoubleListOf(5.0), result[2])

        assertEquals(0, mutableDoubleListOf().chunked(2).size)

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
    fun mutableDoubleListWithInit() {
        val list = MutableDoubleList(4) { (it + 1).toDouble() }
        assertEquals(4, list.size)
        assertEquals(1.0, list[0])
        assertEquals(2.0, list[1])
        assertEquals(3.0, list[2])
        assertEquals(4.0, list[3])

        val empty = MutableDoubleList(0) { it.toDouble() }
        assertEquals(0, empty.size)

        val zeros = MutableDoubleList(3) { 0.0 }
        assertEquals(3, zeros.size)
        assertEquals(0.0, zeros[0])
        assertEquals(0.0, zeros[1])
        assertEquals(0.0, zeros[2])

        val complex = MutableDoubleList(3) { index ->
            when (index) {
                0 -> -1.0
                1 -> 0.0
                else -> 1.0
            }
        }
        assertEquals(3, complex.size)
        assertEquals(-1.0, complex[0])
        assertEquals(0.0, complex[1])
        assertEquals(1.0, complex[2])
    }

    @Test
    fun doubleListWithInit() {
        val list = DoubleList(4) { (it + 1).toDouble() }
        assertEquals(4, list.size)
        assertEquals(1.0, list[0])
        assertEquals(2.0, list[1])
        assertEquals(3.0, list[2])
        assertEquals(4.0, list[3])
    }
    
    @Test
    fun all() {
        assertTrue(list.all { it > 0.0 })
        assertTrue(list.all { it < 6.0 })
        assertFalse(list.all { it < 5.0 })
        assertFalse(list.all { it > 3.0 })
        
        assertTrue(mutableDoubleListOf().all { it > 0.0 })
        
        assertTrue(mutableDoubleListOf(1.0).all { it == 1.0 })
        assertFalse(mutableDoubleListOf(1.0).all { it == 2.0 })
        
        val duplicates = mutableDoubleListOf(2.0, 2.0, 2.0)
        assertTrue(duplicates.all { it == 2.0 })
        
        var count = 0
        assertFalse(list.all { 
            count++
            it > 3.0 
        })
        assertTrue(count < list.size)
    }

    @Test
    fun reversedAll() {
        assertTrue(list.reversedAll { it < 6.0 })
        assertTrue(list.reversedAll { it > 0.0 })
        assertFalse(list.reversedAll { it > 3.0 })
        assertFalse(list.reversedAll { it < 3.0 })
        
        assertTrue(mutableDoubleListOf().reversedAll { it > 0.0 })
        
        assertTrue(mutableDoubleListOf(1.0).reversedAll { it == 1.0 })
        assertFalse(mutableDoubleListOf(1.0).reversedAll { it == 2.0 })
        
        val duplicates = mutableDoubleListOf(2.0, 2.0, 2.0)
        assertTrue(duplicates.reversedAll { it == 2.0 })
        
        val evaluated = mutableDoubleListOf()
        assertTrue(list.reversedAll { 
            evaluated.add(it)
            true
        })
        assertEquals(mutableDoubleListOf(5.0, 4.0, 3.0, 2.0, 1.0), evaluated)
        
        var count = 0
        assertFalse(list.reversedAll { 
            count++
            it < 3.0 
        })
        assertTrue(count < list.size)
    }
    
    @Test
    fun sum() {
        assertEquals(15.0, list.sum())
        assertEquals(0.0, mutableDoubleListOf().sum())
        assertEquals(1.0, mutableDoubleListOf(1.0).sum())
        assertEquals(3.0, mutableDoubleListOf(1.0, 2.0).sum())
        
        val negatives = mutableDoubleListOf(-2.0, -1.0, 0.0, 1.0, 2.0)
        assertEquals(0.0, negatives.sum())
    }

    @Test 
    fun sumOf() {
        assertEquals(30.0, list.sumOf { it * 2 })
        assertEquals(0.0, mutableDoubleListOf().sumOf { it * 2 })
        assertEquals(2.0, mutableDoubleListOf(1.0).sumOf { it * 2 })
        
        val result = list.sumOf { 
            if (it % 2.0 == 0.0) it
            else 0.0
        }
        assertEquals(6.0, result)
        
        assertEquals(5.0, list.sumOf { 1.0 })
        assertEquals(15.0, list.sumOf { it })
    }
    
    @Test
    fun orEmpty() {
        // Test non-null list returns itself
        val nonNullList = doubleListOf(1.0, 2.0, 3.0)
        assertEquals(nonNullList, nonNullList.orEmpty())
        
        // Test null list returns empty list
        val nullList: DoubleList? = null
        val emptyResult = nullList.orEmpty()
        assertTrue(emptyResult.isEmpty())

        // Test empty list returns itself
        val emptyList = doubleListOf()
        assertEquals(emptyList, emptyList.orEmpty())
    }

    @Test
    fun jsonSerialization() {
        // Test basic serialization and deserialization
        val json = Json
        val list = doubleListOf(1.0, 2.0, 3.0, 4.0, 5.0)

        val serialized = json.encodeToString(list)
        val deserialized = json.decodeFromString<DoubleList>(serialized)
        assertEquals(list, deserialized)

        // Test empty list
        val emptyList = doubleListOf()
        val emptyListSerialized = json.encodeToString(emptyList)
        val emptyListDeserialized = json.decodeFromString<DoubleList>(emptyListSerialized)
        assertEquals(emptyList, emptyListDeserialized)

        // Test single element
        val singleList = doubleListOf(1.0)
        val singleListSerialized = json.encodeToString(singleList)
        val singleListDeserialized = json.decodeFromString<DoubleList>(singleListSerialized)
        assertEquals(singleList, singleListDeserialized)

        // Compare with standard List serialization
        val standardList = listOf(1.0, 2.0, 3.0, 4.0, 5.0)
        val standardListSerialized = json.encodeToString(standardList)
        assertEquals(serialized, standardListSerialized)

        // Test with custom values
        val mixedList = doubleListOf(-1.0, 0.0, 1.0, 100.0)
        val mixedListSerialized = json.encodeToString(mixedList)
        val mixedListDeserialized = json.decodeFromString<DoubleList>(mixedListSerialized)
        assertEquals(mixedList, mixedListDeserialized)

        // Compare mixed list with standard List
        val standardMixedList = listOf(-1.0, 0.0, 1.0, 100.0)
        val standardMixedSerialized = json.encodeToString(standardMixedList)
        assertEquals(mixedListSerialized, standardMixedSerialized)
    }
    
    @Test
    fun toDoubleArray() {
        // Test regular list
        val array = list.toDoubleArray()
        assertEquals(5, array.size)
        assertEquals(1.0, array[0])
        assertEquals(2.0, array[1])
        assertEquals(3.0, array[2])
        assertEquals(4.0, array[3])
        assertEquals(5.0, array[4])
        
        // Test empty list
        val emptyArray = mutableDoubleListOf().toDoubleArray()
        assertEquals(0, emptyArray.size)
        
        // Test single element list
        val singleArray = mutableDoubleListOf(1.0).toDoubleArray()
        assertEquals(1, singleArray.size)
        assertEquals(1.0, singleArray[0])
        
        // Verify array is a copy
        val originalList = mutableDoubleListOf(1.0, 2.0, 3.0)
        val copiedArray = originalList.toDoubleArray()
        originalList[0] = 5.0
        assertEquals(1.0, copiedArray[0])
    }
    
    @Test
    fun toSet() {
        // Test regular list
        val set = list.toSet()
        assertEquals(5, set.size)
        assertTrue(set.contains(1.0))
        assertTrue(set.contains(2.0))
        assertTrue(set.contains(3.0))
        assertTrue(set.contains(4.0))
        assertTrue(set.contains(5.0))
        
        // Test empty list
        val emptySet = mutableDoubleListOf().toSet()
        assertTrue(emptySet.isEmpty())
        
        // Test single element list
        val singleSet = mutableDoubleListOf(1.0).toSet()
        assertEquals(1, singleSet.size)
        assertTrue(singleSet.contains(1.0))
        
        // Test list with duplicates
        val duplicateList = mutableDoubleListOf(1.0, 2.0, 2.0, 3.0, 3.0, 3.0)
        val duplicateSet = duplicateList.toSet()
        assertEquals(3, duplicateSet.size)
        assertTrue(duplicateSet.contains(1.0))
        assertTrue(duplicateSet.contains(2.0))
        assertTrue(duplicateSet.contains(3.0))
    }

    @Test
    fun toMutableSet() {
        // Test regular list
        val set = list.toMutableSet()
        assertEquals(5, set.size)
        assertTrue(set.contains(1.0))
        assertTrue(set.contains(2.0))
        assertTrue(set.contains(3.0))
        assertTrue(set.contains(4.0))
        assertTrue(set.contains(5.0))

        // Verify mutability
        set.add(6.0)
        assertEquals(6, set.size)
        assertTrue(set.contains(6.0))
        
        // Test empty list
        val emptySet = mutableDoubleListOf().toMutableSet()
        assertTrue(emptySet.isEmpty())
        emptySet.add(1.0)
        assertEquals(1, emptySet.size)
        
        // Test single element list
        val singleSet = mutableDoubleListOf(1.0).toMutableSet()
        assertEquals(1, singleSet.size)
        assertTrue(singleSet.contains(1.0))
        
        // Test list with duplicates
        val duplicateList = mutableDoubleListOf(1.0, 2.0, 2.0, 3.0, 3.0, 3.0)
        val duplicateSet = duplicateList.toMutableSet()
        assertEquals(3, duplicateSet.size)
        assertTrue(duplicateSet.contains(1.0))
        assertTrue(duplicateSet.contains(2.0))
        assertTrue(duplicateSet.contains(3.0))
    }
}
