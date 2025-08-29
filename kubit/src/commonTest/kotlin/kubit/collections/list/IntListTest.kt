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
class IntListTest {
    private val list: MutableIntList = mutableIntListOf(1, 2, 3, 4, 5)

    @Test
    fun emptyConstruction() {
        val l = mutableIntListOf()
        assertEquals(0, l.size)
        assertEquals(16, l.capacity)
    }

    @Test
    fun sizeConstruction() {
        val l = MutableIntList(4)
        assertEquals(4, l.capacity)
    }

    @Test
    fun contentConstruction() {
        val l = mutableIntListOf(1, 2, 3)
        assertEquals(3, l.size)
        assertEquals(1, l[0])
        assertEquals(2, l[1])
        assertEquals(3, l[2])
        assertEquals(3, l.capacity)
        repeat(2) {
            val l2 = mutableIntListOf(1, 2, 3, 4, 5)
            assertEquals(list, l2)
            l2.removeAt(0)
        }
    }

    @Test
    fun hashCodeTest() {
        val l2 = mutableIntListOf(1, 2, 3, 4, 5)
        assertEquals(list.hashCode(), l2.hashCode())
        l2.removeAt(4)
        assertNotEquals(list.hashCode(), l2.hashCode())
        l2.add(5)
        assertEquals(list.hashCode(), l2.hashCode())
        l2.clear()
        assertNotEquals(list.hashCode(), l2.hashCode())
    }

    @Test
    fun equalsTest() {
        val l2 = mutableIntListOf(1, 2, 3, 4, 5)
        assertEquals(list, l2)
        assertNotEquals(list, mutableIntListOf())
        l2.removeAt(4)
        assertNotEquals(list, l2)
        l2.add(5)
        assertEquals(list, l2)
        l2.clear()
        assertNotEquals(list, l2)
    }

    @Test
    fun string() {
        assertEquals("[${1}, ${2}, ${3}, ${4}, ${5}]", list.toString())
        assertEquals("[]", mutableIntListOf().toString())
    }

    @Test
    fun joinToString() {
        assertEquals("${1}, ${2}, ${3}, ${4}, ${5}", list.joinToString())
        assertEquals(
            "x${1}, ${2}, ${3}, ...y",
            list.joinToString(prefix = "x", postfix = "y", limit = 3)
        )
        assertEquals(
            ">${1}-${2}-${3}-${4}-${5}<",
            list.joinToString(separator = "-", prefix = ">", postfix = "<")
        )
        assertEquals("one, two, three, ...", list.joinToString(limit = 3) {
            when (it) {
                1 -> "one"
                2 -> "two"
                3 -> "three"
                else -> "whoops"
            }
        })
    }

    @Test
    fun size() {
        assertEquals(5, list.size)
        assertEquals(5, list.count())
        val l2 = mutableIntListOf()
        assertEquals(0, l2.size)
        assertEquals(0, l2.count())
        l2 += 1
        assertEquals(1, l2.size)
        assertEquals(1, l2.count())
    }

    @Test
    fun get() {
        assertEquals(1, list[0])
        assertEquals(5, list[4])
        assertEquals(1, list.elementAt(0))
        assertEquals(5, list.elementAt(4))
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
        assertEquals(1, list.elementAtOrElse(0) {
            assertEquals(0, it)
            0
        })
        assertEquals(0, list.elementAtOrElse(-1) {
            assertEquals(-1, it)
            0
        })
        assertEquals(0, list.elementAtOrElse(5) {
            assertEquals(5, it)
            0
        })
    }

    @Test
    fun count() {
        assertEquals(1, list.count { it < 2 })
        assertEquals(0, list.count { it < 0 })
        assertEquals(5, list.count { it < 10 })
    }

    @Test
    fun isEmpty() {
        assertFalse(list.isEmpty())
        assertFalse(list.none())
        assertTrue(mutableIntListOf().isEmpty())
        assertTrue(mutableIntListOf().none())
    }

    @Test
    fun isNotEmpty() {
        assertTrue(list.isNotEmpty())
        assertTrue(list.any())
        assertFalse(mutableIntListOf().isNotEmpty())
    }

    @Test
    fun indices() {
        assertEquals(IntRange(0, 4), list.indices)
        assertEquals(IntRange(0, -1), mutableIntListOf().indices)
    }

    @Test
    fun any() {
        assertTrue(list.any { it == 5 })
        assertTrue(list.any { it == 1 })
        assertFalse(list.any { it == 0 })
    }

    @Test
    fun reversedAny() {
        val reversedList = mutableIntListOf()
        assertFalse(
            list.reversedAny {
                reversedList.add(it)
                false
            }
        )
        val reversedContent = mutableIntListOf(5, 4, 3, 2, 1)
        assertEquals(reversedContent, reversedList)

        val reversedSublist = mutableIntListOf()
        assertTrue(
            list.reversedAny {
                reversedSublist.add(it)
                reversedSublist.size == 2
            }
        )
        assertEquals(reversedSublist, mutableIntListOf(5, 4))
    }
    
    @Test
    fun `atLeast returns true when count greater than or equal to n`() {
        val list = intListOf(1, 2, 3, 4, 5)
        val result = list.atLeast(2) { it > 3 }
        assertTrue(result)
    }

    @Test
    fun `atLeast returns false when count less than n`() {
        val list = intListOf(1, 2, 3, 4, 5)
        val result = list.atLeast(3) { it > 3 }
        assertEquals(false, result)
    }

    @Test
    fun `atLeast with n equals 0 returns true`() {
        val list = intListOf(1, 2, 3, 4, 5)
        val result = list.atLeast(0) { it > 100 }
        assertTrue(result)
    }

    @Test
    fun `atMost returns true when count less than or equal to n`() {
        val list = intListOf(1, 2, 3, 4, 5)
        val result = list.atMost(2) { it > 3 }
        assertTrue(result)
    }

    @Test
    fun `atMost returns false when count greater than n`() {
        val list = intListOf(1, 2, 3, 4, 5)
        val result = list.atMost(1) { it > 3 }
        assertEquals(false, result)
    }

    @Test
    fun `atMost with n equals 0 and no matches returns true`() {
        val list = intListOf(1, 2, 3)
        val result = list.atMost(0) { it > 10 }
        assertTrue(result)
    }

    @Test
    fun `atMost with n equals 0 and matches returns false`() {
        val list = intListOf(1, 2, 3)
        val result = list.atMost(0) { it > 1 }
        assertEquals(false, result)
    }
    
    @Test
    fun `atLeast throws IllegalArgumentException when n less than 0`() {
        val list = intListOf(1, 2, 3, 4, 5)
        assertFailsWith<IllegalArgumentException> {
            list.atLeast(-1) { it > 3 }
        }
    }

    @Test
    fun `atMost throws IllegalArgumentException when n less than 0`() {
        val list = intListOf(1, 2, 3, 4, 5)
        assertFailsWith<IllegalArgumentException> {
            list.atMost(-1) { it > 3 }
        }
    }

    @Test
    fun forEach() {
        val copy = mutableIntListOf()
        list.forEach { copy += it }
        assertEquals(list, copy)
    }

    @Test
    fun forEachReversed() {
        val copy = mutableIntListOf()
        list.forEachReversed { copy += it }
        assertEquals(copy, mutableIntListOf(5, 4, 3, 2, 1))
    }

    @Test
    fun forEachIndexed() {
        val copy = mutableIntListOf()
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
        val copy = mutableIntListOf()
        val indices = mutableIntListOf()
        list.forEachReversedIndexed { index, item ->
            copy += item
            indices += index
        }
        assertEquals(copy, mutableIntListOf(5, 4, 3, 2, 1))
        assertEquals(indices, intListOf(4, 3, 2, 1, 0))
    }

    @Test
    fun indexOfFirst() {
        assertEquals(0, list.indexOfFirst { it == 1 })
        assertEquals(4, list.indexOfFirst { it == 5 })
        assertEquals(-1, list.indexOfFirst { it == 0 })
        assertEquals(0, mutableIntListOf(8, 8).indexOfFirst { it == 8 })
    }

    @Test
    fun indexOfLast() {
        assertEquals(0, list.indexOfLast { it == 1 })
        assertEquals(4, list.indexOfLast { it == 5 })
        assertEquals(-1, list.indexOfLast { it == 0 })
        assertEquals(1, mutableIntListOf(8, 8).indexOfLast { it == 8 })
    }

    @Test
    fun contains() {
        assertTrue(list.contains(5))
        assertTrue(list.contains(1))
        assertFalse(list.contains(0))
    }

    @Test
    fun containsAllList() {
        assertTrue(list.containsAll(mutableIntListOf(2, 3, 1)))
        assertFalse(list.containsAll(mutableIntListOf(2, 3, 6)))
    }

    @Test
    fun lastIndexOf() {
        assertEquals(4, list.lastIndexOf(5))
        assertEquals(1, list.lastIndexOf(2))
        val copy = mutableIntListOf()
        copy.addAll(list)
        copy.addAll(list)
        assertEquals(5, copy.lastIndexOf(1))
    }

    @Test
    fun first() {
        assertEquals(1, list.first())
    }

    @Test
    fun firstException() {
        assertFailsWith(NoSuchElementException::class) {
            mutableIntListOf().first()
        }
    }

    @Test
    fun firstWithPredicate() {
        assertEquals(5, list.first { it == 5 })
        assertEquals(1, mutableIntListOf(1, 5).first { it != 0 })
    }

    @Test
    fun firstWithPredicateException() {
        assertFailsWith(NoSuchElementException::class) {
            mutableIntListOf().first { it == 8 }
        }
    }

    @Test
    fun last() {
        assertEquals(5, list.last())
    }

    @Test
    fun lastException() {
        assertFailsWith(NoSuchElementException::class) {
            mutableIntListOf().last()
        }
    }

    @Test
    fun lastWithPredicate() {
        assertEquals(1, list.last { it == 1 })
        assertEquals(5, mutableIntListOf(1, 5).last { it != 0 })
    }

    @Test
    fun lastWithPredicateException() {
        assertFailsWith(NoSuchElementException::class) {
            mutableIntListOf().last { it == 8 }
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
        val l = mutableIntListOf(1, 2, 3)
        l += 4
        l.add(5)
        assertEquals(list, l)
    }

    @Test
    fun addAtIndex() {
        val l = mutableIntListOf(2, 4)
        l.add(2, 5)
        l.add(0, 1)
        l.add(2, 3)
        assertEquals(list, l)
        assertFailsWith(IndexOutOfBoundsException::class) {
            l.add(-1, 2)
        }
        assertFailsWith(IndexOutOfBoundsException::class) {
            l.add(6, 2)
        }
    }

    @Test
    fun addAllListAtIndex() {
        val l = mutableIntListOf(4)
        val l2 = mutableIntListOf(1, 2)
        val l3 = mutableIntListOf(5)
        val l4 = mutableIntListOf(3)
        assertTrue(l4.addAll(1, l3))
        assertTrue(l4.addAll(0, l2))
        assertTrue(l4.addAll(3, l))
        assertFalse(l4.addAll(0, mutableIntListOf()))
        assertEquals(list, l4)
        assertFailsWith(IndexOutOfBoundsException::class) {
            l4.addAll(6, mutableIntListOf())
        }
        assertFailsWith(IndexOutOfBoundsException::class) {
            l4.addAll(-1, mutableIntListOf())
        }
    }

    @Test
    fun addAllList() {
        val l = MutableIntList()
        l.add(3)
        l.add(4)
        l.add(5)
        val l2 = mutableIntListOf(1, 2)
        assertTrue(l2.addAll(l))
        assertEquals(list, l2)
        assertFalse(l2.addAll(mutableIntListOf()))
    }

    @Test
    fun plusAssignList() {
        val l = MutableIntList()
        l.add(3)
        l.add(4)
        l.add(5)
        val l2 = mutableIntListOf(1, 2)
        l2 += l
        assertEquals(list, l2)
    }

    @Test
    fun addAllArrayAtIndex() {
        val a1 = intArrayOf(4)
        val a2 = intArrayOf(1, 2)
        val a3 = intArrayOf(5)
        val l = mutableIntListOf(3)
        assertTrue(l.addAll(1, a3))
        assertTrue(l.addAll(0, a2))
        assertTrue(l.addAll(3, a1))
        assertFalse(l.addAll(0, intArrayOf()))
        assertEquals(list, l)
        assertFailsWith(IndexOutOfBoundsException::class) {
            l.addAll(6, intArrayOf())
        }
        assertFailsWith(IndexOutOfBoundsException::class) {
            l.addAll(-1, intArrayOf())
        }
    }

    @Test
    fun addAllArray() {
        val a = intArrayOf(3, 4, 5)
        val v = mutableIntListOf(1, 2)
        v.addAll(a)
        assertEquals(5, v.size)
        assertEquals(3, v[2])
        assertEquals(4, v[3])
        assertEquals(5, v[4])
    }
    
    @Test
    fun addAllGenericList() {
        val original = mutableIntListOf(1, 2)
        val toAdd = mutableListOf(3, 4, 5)
        
        assertTrue(original.addAll(toAdd))
        assertEquals(5, original.size)
        assertEquals(list, original)
        
        // Test adding empty list
        assertFalse(original.addAll(mutableListOf()))
        assertEquals(5, original.size)
        assertEquals(list, original)
        
        // Test adding to empty list
        val empty = mutableIntListOf()
        assertTrue(empty.addAll(toAdd))
        assertEquals(mutableIntListOf(3, 4, 5), empty)
        
        // Test capacity expansion
        val small = MutableIntList(2)
        small.add(1)
        assertTrue(small.addAll(mutableListOf(2, 3, 4)))
        assertEquals(mutableIntListOf(1, 2, 3, 4), small)
    }

    @Test
    fun plusAssignArray() {
        val a = intArrayOf(3, 4, 5)
        val v = mutableIntListOf(1, 2)
        v += a
        assertEquals(list, v)
    }

    @Test
    fun clear() {
        val l = mutableIntListOf()
        l.addAll(list)
        assertTrue(l.isNotEmpty())
        l.clear()
        assertTrue(l.isEmpty())
    }

    @Test
    fun trim() {
        val l = mutableIntListOf(1)
        l.trim()
        assertEquals(1, l.capacity)
        l += intArrayOf(1, 2, 3, 4, 5)
        l.trim()
        assertEquals(6, l.capacity)
        assertEquals(6, l.size)
        l.clear()
        l.trim()
        assertEquals(0, l.capacity)
        l.trim(100)
        assertEquals(0, l.capacity)
        l += intArrayOf(1, 2, 3, 4, 5)
        l -= 5
        l.trim(5)
        assertEquals(5, l.capacity)
        l.trim(4)
        assertEquals(4, l.capacity)
        l.trim(3)
        assertEquals(4, l.capacity)
    }

    @Test
    fun remove() {
        val l = mutableIntListOf(1, 2, 3, 4, 5)
        l.remove(3)
        assertEquals(mutableIntListOf(1, 2, 4, 5), l)
    }

    @Test
    fun removeAt() {
        val l = mutableIntListOf(1, 2, 3, 4, 5)
        l.removeAt(2)
        assertEquals(mutableIntListOf(1, 2, 4, 5), l)
        assertFailsWith(IndexOutOfBoundsException::class) {
            l.removeAt(6)
        }
        assertFailsWith(IndexOutOfBoundsException::class) {
            l.removeAt(-1)
        }
    }

    @Test
    fun set() {
        val l = mutableIntListOf(0, 0, 0, 0, 0)
        l[0] = 1
        l[4] = 5
        l[2] = 3
        l[1] = 2
        l[3] = 4
        assertEquals(list, l)
        assertFailsWith<IndexOutOfBoundsException> {
            l.set(-1, 1)
        }
        assertFailsWith<IndexOutOfBoundsException> {
            l.set(6, 1)
        }
        assertEquals(4, l.set(3, 1))
    }

    @Test
    fun ensureCapacity() {
        val l = mutableIntListOf(1)
        assertEquals(1, l.capacity)
        l.ensureCapacity(5)
        assertEquals(5, l.capacity)
    }

    @Test
    fun removeAllList() {
        assertFalse(list.removeAll(mutableIntListOf(0, 10, 15)))
        val l = mutableIntListOf(0, 1, 15, 10, 2, 3, 10, 4, 5, 20)
        assertTrue(l.removeAll(mutableIntListOf(20, 0, 15, 10)))
        assertEquals(list, l)
    }

    @Test
    fun removeAllIntArray() {
        assertFalse(list.removeAll(intArrayOf(0, 10, 15)))
        val l = mutableIntListOf(0, 1, 15, 10, 2, 3, 10, 4, 5, 20)
        assertTrue(l.removeAll(intArrayOf(20, 0, 15, 10)))
        assertEquals(list, l)
    }

    @Test
    fun minusAssignList() {
        val l = mutableIntListOf().also { it += list }
        l -= mutableIntListOf(0, 10, 15)
        assertEquals(list, l)
        val l2 = mutableIntListOf(0, 1, 15, 10, 2, 3, 10, 4, 5, 20)
        l2 -= mutableIntListOf(20, 0, 15, 10)
        assertEquals(list, l2)
    }

    @Test
    fun minusAssignIntArray() {
        val l = mutableIntListOf().also { it += list }
        l -= intArrayOf(0, 10, 15)
        assertEquals(list, l)
        val l2 = mutableIntListOf(0, 1, 15, 10, 2, 3, 10, 4, 5, 20)
        l2 -= intArrayOf(20, 0, 15, 10)
        assertEquals(list, l2)
    }

    @Test
    fun retainAll() {
        assertFalse(list.retainAll(mutableIntListOf(1, 2, 3, 4, 5, 6)))
        val l = mutableIntListOf(0, 1, 15, 10, 2, 3, 4, 5, 20)
        assertTrue(l.retainAll(mutableIntListOf(1, 2, 3, 4, 5, 6)))
        assertEquals(list, l)
    }

    @Test
    fun retainAllIntArray() {
        assertFalse(list.retainAll(intArrayOf(1, 2, 3, 4, 5, 6)))
        val l = mutableIntListOf(0, 1, 15, 10, 2, 3, 4, 5, 20)
        assertTrue(l.retainAll(intArrayOf(1, 2, 3, 4, 5, 6)))
        assertEquals(list, l)
    }

    @Test
    fun removeRange() {
        val l = mutableIntListOf(1, 9, 7, 6, 2, 3, 4, 5)
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
        val l = mutableIntListOf(1, 4, 2, 5, 3)
        l.sort()
        assertEquals(list, l)
    }

    @Test
    fun sortDescending() {
        val l = mutableIntListOf(1, 4, 2, 5, 3)
        l.sortDescending()
        assertEquals(mutableIntListOf(5, 4, 3, 2, 1), l)
    }

    @Test
    fun sortEmpty() {
        val l = MutableIntList(0)
        l.sort()
        l.sortDescending()
        assertEquals(MutableIntList(0), l)
    }

    @Test
    fun testEmptyIntList() {
        val l = emptyIntList()
        assertEquals(0, l.size)
    }

    @Test
    fun intListOfEmpty() {
        val l = intListOf()
        assertEquals(0, l.size)
    }

    @Test
    fun intListOfOneValue() {
        val l = intListOf(2)
        assertEquals(1, l.size)
        assertEquals(2, l[0])
    }

    @Test
    fun intListOfTwoValues() {
        val l = intListOf(2, 1)
        assertEquals(2, l.size)
        assertEquals(2, l[0])
        assertEquals(1, l[1])
    }

    @Test
    fun intListOfThreeValues() {
        val l = intListOf(2, 10, -1)
        assertEquals(3, l.size)
        assertEquals(2, l[0])
        assertEquals(10, l[1])
        assertEquals(-1, l[2])
    }

    @Test
    fun intListOfFourValues() {
        val l = intListOf(2, 10, -1, 10)
        assertEquals(4, l.size)
        assertEquals(2, l[0])
        assertEquals(10, l[1])
        assertEquals(-1, l[2])
        assertEquals(10, l[3])
    }

    @Test
    fun mutableIntListOfOneValue() {
        val l = mutableIntListOf(2)
        assertEquals(1, l.size)
        assertEquals(1, l.capacity)
        assertEquals(2, l[0])
    }

    @Test
    fun mutableIntListOfTwoValues() {
        val l = mutableIntListOf(2, 1)
        assertEquals(2, l.size)
        assertEquals(2, l.capacity)
        assertEquals(2, l[0])
        assertEquals(1, l[1])
    }

    @Test
    fun mutableIntListOfThreeValues() {
        val l = mutableIntListOf(2, 10, -1)
        assertEquals(3, l.size)
        assertEquals(3, l.capacity)
        assertEquals(2, l[0])
        assertEquals(10, l[1])
        assertEquals(-1, l[2])
    }

    @Test
    fun mutableIntListOfFourValues() {
        val l = mutableIntListOf(2, 10, -1, 10)
        assertEquals(4, l.size)
        assertEquals(4, l.capacity)
        assertEquals(2, l[0])
        assertEquals(10, l[1])
        assertEquals(-1, l[2])
        assertEquals(10, l[3])
    }

    @Test
    fun buildIntListFunction() {
        val contract: Boolean
        val l = buildIntList {
            contract = true
            add(2)
            add(10)
        }
        assertTrue(contract)
        assertEquals(2, l.size)
        assertEquals(2, l[0])
        assertEquals(10, l[1])
    }

    @Test
    fun buildIntListWithCapacityFunction() {
        val contract: Boolean
        val l = buildIntList(20) {
            contract = true
            add(2)
            add(10)
        }
        assertTrue(contract)
        assertEquals(2, l.size)
        assertTrue(l.content.size >= 20)
        assertEquals(2, l[0])
        assertEquals(10, l[1])
    }

    @Test
    fun binarySearchIntList() {
        val l = mutableIntListOf(-2, -1, 2, 10, 10)
        assertEquals(0, l.binarySearch(-2))
        assertEquals(2, l.binarySearch(2))
        assertEquals(3, l.binarySearch(10))

        assertEquals(-1, l.binarySearch(-20))
        assertEquals(-4, l.binarySearch(3))
        assertEquals(-6, l.binarySearch(20))

    }
    
    @Test
    fun take() {
        assertEquals(intListOf(1, 2, 3), list.take(3))
        assertEquals(intListOf(), list.take(0))
        assertEquals(list, list.take(100))
    }

    @Test
    fun takeLast() {
        assertEquals(intListOf(3, 4, 5), list.takeLast(3))
        assertEquals(intListOf(), list.takeLast(0))
        assertEquals(list, list.takeLast(100))
    }

    @Test
    fun drop() {
        assertEquals(intListOf(4, 5), list.drop(3))
        assertEquals(list, list.drop(0))
        assertEquals(intListOf(), list.drop(100))
    }

    @Test
    fun dropLast() {
        assertEquals(intListOf(1, 2), list.dropLast(3))
        assertEquals(list, list.dropLast(0))
        assertEquals(intListOf(), list.dropLast(100))
    }
    
    @Test
    fun takeWhile() {
        assertEquals(intListOf(1, 2), list.takeWhile { it < 3 })
        assertEquals(intListOf(), list.takeWhile { it < 0 })
        assertEquals(list, list.takeWhile { it <= 5 })
        assertEquals(intListOf(), mutableIntListOf().takeWhile { it < 10 })
    }

    @Test
    fun takeLastWhile() {
        assertEquals(intListOf(4, 5), list.takeLastWhile { it > 3 })
        assertEquals(intListOf(), list.takeLastWhile { it > 5 })
        assertEquals(list, list.takeLastWhile { it >= 1 })
        assertEquals(intListOf(), mutableIntListOf().takeLastWhile { it > 0 })
    }

    @Test
    fun dropWhile() {
        assertEquals(intListOf(3, 4, 5), list.dropWhile { it < 3 })
        assertEquals(list, list.dropWhile { it < 0 })
        assertEquals(intListOf(), list.dropWhile { it <= 5 })
        assertEquals(intListOf(), mutableIntListOf().dropWhile { it < 10 })
    }

    @Test
    fun dropLastWhile() {
        assertEquals(intListOf(1, 2, 3), list.dropLastWhile { it > 3 })
        assertEquals(list, list.dropLastWhile { it > 5 })
        assertEquals(intListOf(), list.dropLastWhile { it >= 1 })
        assertEquals(intListOf(), mutableIntListOf().dropLastWhile { it > 0 })
    }
    
    @Test
    fun filter() {
        assertEquals(mutableIntListOf(1, 2), list.filter { it < 3 })
        assertEquals(mutableIntListOf(4, 5), list.filter { it > 3 })
        assertEquals(mutableIntListOf(), list.filter { it > 10 })
        assertEquals(list, list.filter { true })
        assertEquals(mutableIntListOf(), list.filter { false })
    }

    @Test
    fun copy() {
        val copy = list.copy()
        assertEquals(list, copy)
        assertNotEquals(list.content, copy.content)
    }

    @Test
    fun distinct() {
        val duplicates = mutableIntListOf(1, 2, 2, 3, 3, 3, 4, 4, 5)
        assertEquals(list, duplicates.distinct())
        assertEquals(list, list.distinct()) 
        assertEquals(mutableIntListOf(), mutableIntListOf().distinct())
        assertEquals(mutableIntListOf(1), mutableIntListOf(1, 1, 1).distinct())
    }

    @Test
    fun sorted() {
        val unsorted = MutableIntList().also {
            it.addAll(intArrayOf(5, 2, 4, 3, 1))
        }
        assertEquals(list, unsorted.sorted())
        assertEquals(list, list.sorted())
        assertEquals(mutableIntListOf(), mutableIntListOf().sorted())
        assertEquals(mutableIntListOf(1), mutableIntListOf(1).sorted())
    }

    @Test
    fun sortedDescending() {
        val unsorted = MutableIntList().also {
            it.addAll(intArrayOf(5, 2, 4, 3, 1))
        }
        assertEquals(mutableIntListOf(5, 4, 3, 2, 1), unsorted.sortedDescending())
        assertEquals(mutableIntListOf(5, 4, 3, 2, 1), list.sortedDescending())
        assertEquals(mutableIntListOf(), mutableIntListOf().sortedDescending())
        assertEquals(mutableIntListOf(1), mutableIntListOf(1).sortedDescending())
    }
    
    @Test
    fun reversed() {
        assertEquals(mutableIntListOf(5, 4, 3, 2, 1), list.reversed())
        assertEquals(mutableIntListOf(), mutableIntListOf().reversed())
        assertEquals(mutableIntListOf(1), mutableIntListOf(1).reversed())
        
        val duplicates = mutableIntListOf(1, 1, 2, 2)
        assertEquals(mutableIntListOf(2, 2, 1, 1), duplicates.reversed())
    }

    @Test
    fun chunked() {
        val result = list.chunked(2)
        assertEquals(3, result.size)
        assertEquals(mutableIntListOf(1, 2), result[0])
        assertEquals(mutableIntListOf(3, 4), result[1]) 
        assertEquals(mutableIntListOf(5), result[2])

        assertEquals(0, mutableIntListOf().chunked(2).size)

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
    fun mutableIntListWithInit() {
        val list = MutableIntList(4) { (it + 1).toInt() }
        assertEquals(4, list.size)
        assertEquals(1, list[0])
        assertEquals(2, list[1])
        assertEquals(3, list[2])
        assertEquals(4, list[3])

        val empty = MutableIntList(0) { it.toInt() }
        assertEquals(0, empty.size)

        val zeros = MutableIntList(3) { 0 }
        assertEquals(3, zeros.size)
        assertEquals(0, zeros[0])
        assertEquals(0, zeros[1])
        assertEquals(0, zeros[2])

        val complex = MutableIntList(3) { index ->
            when (index) {
                0 -> -1
                1 -> 0
                else -> 1
            }
        }
        assertEquals(3, complex.size)
        assertEquals(-1, complex[0])
        assertEquals(0, complex[1])
        assertEquals(1, complex[2])
    }

    @Test
    fun intListWithInit() {
        val list = IntList(4) { (it + 1).toInt() }
        assertEquals(4, list.size)
        assertEquals(1, list[0])
        assertEquals(2, list[1])
        assertEquals(3, list[2])
        assertEquals(4, list[3])
    }
    
    @Test
    fun all() {
        assertTrue(list.all { it > 0 })
        assertTrue(list.all { it < 6 })
        assertFalse(list.all { it < 5 })
        assertFalse(list.all { it > 3 })
        
        assertTrue(mutableIntListOf().all { it > 0 })
        
        assertTrue(mutableIntListOf(1).all { it == 1 })
        assertFalse(mutableIntListOf(1).all { it == 2 })
        
        val duplicates = mutableIntListOf(2, 2, 2)
        assertTrue(duplicates.all { it == 2 })
        
        var count = 0
        assertFalse(list.all { 
            count++
            it > 3 
        })
        assertTrue(count < list.size)
    }

    @Test
    fun reversedAll() {
        assertTrue(list.reversedAll { it < 6 })
        assertTrue(list.reversedAll { it > 0 })
        assertFalse(list.reversedAll { it > 3 })
        assertFalse(list.reversedAll { it < 3 })
        
        assertTrue(mutableIntListOf().reversedAll { it > 0 })
        
        assertTrue(mutableIntListOf(1).reversedAll { it == 1 })
        assertFalse(mutableIntListOf(1).reversedAll { it == 2 })
        
        val duplicates = mutableIntListOf(2, 2, 2)
        assertTrue(duplicates.reversedAll { it == 2 })
        
        val evaluated = mutableIntListOf()
        assertTrue(list.reversedAll { 
            evaluated.add(it)
            true
        })
        assertEquals(mutableIntListOf(5, 4, 3, 2, 1), evaluated)
        
        var count = 0
        assertFalse(list.reversedAll { 
            count++
            it < 3 
        })
        assertTrue(count < list.size)
    }
    
    @Test
    fun sum() {
        assertEquals(15, list.sum())
        assertEquals(0, mutableIntListOf().sum())
        assertEquals(1, mutableIntListOf(1).sum())
        assertEquals(3, mutableIntListOf(1, 2).sum())
        
        val negatives = mutableIntListOf(-2, -1, 0, 1, 2)
        assertEquals(0, negatives.sum())
    }

    @Test 
    fun sumOf() {
        assertEquals(30, list.sumOf { it * 2 })
        assertEquals(0, mutableIntListOf().sumOf { it * 2 })
        assertEquals(2, mutableIntListOf(1).sumOf { it * 2 })
        
        val result = list.sumOf { 
            if (it % 2 == 0) it
            else 0
        }
        assertEquals(6, result)
        
        assertEquals(5, list.sumOf { 1 })
        assertEquals(15, list.sumOf { it })
    }
    
    @Test
    fun orEmpty() {
        // Test non-null list returns itself
        val nonNullList = intListOf(1, 2, 3)
        assertEquals(nonNullList, nonNullList.orEmpty())
        
        // Test null list returns empty list
        val nullList: IntList? = null
        val emptyResult = nullList.orEmpty()
        assertTrue(emptyResult.isEmpty())

        // Test empty list returns itself
        val emptyList = intListOf()
        assertEquals(emptyList, emptyList.orEmpty())
    }

    @Test
    fun jsonSerialization() {
        // Test basic serialization and deserialization
        val json = Json
        val list = intListOf(1, 2, 3, 4, 5)

        val serialized = json.encodeToString(list)
        val deserialized = json.decodeFromString<IntList>(serialized)
        assertEquals(list, deserialized)

        // Test empty list
        val emptyList = intListOf()
        val emptyListSerialized = json.encodeToString(emptyList)
        val emptyListDeserialized = json.decodeFromString<IntList>(emptyListSerialized)
        assertEquals(emptyList, emptyListDeserialized)

        // Test single element
        val singleList = intListOf(1)
        val singleListSerialized = json.encodeToString(singleList)
        val singleListDeserialized = json.decodeFromString<IntList>(singleListSerialized)
        assertEquals(singleList, singleListDeserialized)

        // Compare with standard List serialization
        val standardList = listOf(1, 2, 3, 4, 5)
        val standardListSerialized = json.encodeToString(standardList)
        assertEquals(serialized, standardListSerialized)

        // Test with custom values
        val mixedList = intListOf(-1, 0, 1, 100)
        val mixedListSerialized = json.encodeToString(mixedList)
        val mixedListDeserialized = json.decodeFromString<IntList>(mixedListSerialized)
        assertEquals(mixedList, mixedListDeserialized)

        // Compare mixed list with standard List
        val standardMixedList = listOf(-1, 0, 1, 100)
        val standardMixedSerialized = json.encodeToString(standardMixedList)
        assertEquals(mixedListSerialized, standardMixedSerialized)
    }
    
    @Test
    fun toIntArray() {
        // Test regular list
        val array = list.toIntArray()
        assertEquals(5, array.size)
        assertEquals(1, array[0])
        assertEquals(2, array[1])
        assertEquals(3, array[2])
        assertEquals(4, array[3])
        assertEquals(5, array[4])
        
        // Test empty list
        val emptyArray = mutableIntListOf().toIntArray()
        assertEquals(0, emptyArray.size)
        
        // Test single element list
        val singleArray = mutableIntListOf(1).toIntArray()
        assertEquals(1, singleArray.size)
        assertEquals(1, singleArray[0])
        
        // Verify array is a copy
        val originalList = mutableIntListOf(1, 2, 3)
        val copiedArray = originalList.toIntArray()
        originalList[0] = 5
        assertEquals(1, copiedArray[0])
    }
    
    @Test
    fun toSet() {
        // Test regular list
        val set = list.toSet()
        assertEquals(5, set.size)
        assertTrue(set.contains(1))
        assertTrue(set.contains(2))
        assertTrue(set.contains(3))
        assertTrue(set.contains(4))
        assertTrue(set.contains(5))
        
        // Test empty list
        val emptySet = mutableIntListOf().toSet()
        assertTrue(emptySet.isEmpty())
        
        // Test single element list
        val singleSet = mutableIntListOf(1).toSet()
        assertEquals(1, singleSet.size)
        assertTrue(singleSet.contains(1))
        
        // Test list with duplicates
        val duplicateList = mutableIntListOf(1, 2, 2, 3, 3, 3)
        val duplicateSet = duplicateList.toSet()
        assertEquals(3, duplicateSet.size)
        assertTrue(duplicateSet.contains(1))
        assertTrue(duplicateSet.contains(2))
        assertTrue(duplicateSet.contains(3))
    }

    @Test
    fun toMutableSet() {
        // Test regular list
        val set = list.toMutableSet()
        assertEquals(5, set.size)
        assertTrue(set.contains(1))
        assertTrue(set.contains(2))
        assertTrue(set.contains(3))
        assertTrue(set.contains(4))
        assertTrue(set.contains(5))

        // Verify mutability
        set.add(6)
        assertEquals(6, set.size)
        assertTrue(set.contains(6))
        
        // Test empty list
        val emptySet = mutableIntListOf().toMutableSet()
        assertTrue(emptySet.isEmpty())
        emptySet.add(1)
        assertEquals(1, emptySet.size)
        
        // Test single element list
        val singleSet = mutableIntListOf(1).toMutableSet()
        assertEquals(1, singleSet.size)
        assertTrue(singleSet.contains(1))
        
        // Test list with duplicates
        val duplicateList = mutableIntListOf(1, 2, 2, 3, 3, 3)
        val duplicateSet = duplicateList.toMutableSet()
        assertEquals(3, duplicateSet.size)
        assertTrue(duplicateSet.contains(1))
        assertTrue(duplicateSet.contains(2))
        assertTrue(duplicateSet.contains(3))
    }
}
