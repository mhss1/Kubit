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

/*
 * This file is based on the IntList from androidx.collection, but it has been
 * substantially modified and will continue to be developed and maintained independently.
 * Original code © The Android Open Source Project and licensed under the Apache License, Version 2.0.
 * Further modifications copyright © 2025 Mohamed Shaaban.
 */

@file:Suppress("RedundantVisibilityModifier")

package kubit.collections.list


import kubit.collections.list.internal.throwIllegalArgumentException
import kubit.collections.list.internal.throwIndexOutOfBoundsException
import kubit.collections.list.internal.throwNoSuchElementException
import kubit.collections.list.internal.EmptyIntArray
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.descriptors.buildSerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to the kotlin source file.
//
// This file was generated from a template in the template directory.
// Make a change to the original template and run the generatePrimitiveLists.sh script
// to ensure the change is available on all versions of the list.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

/**
 * [IntList] is a [List]-like collection for [Int] values. It allows retrieving
 * the elements without boxing. [IntList] is always backed by a [MutableIntList],
 * its [MutableList]-like subclass. The purpose of this class is to avoid the performance
 * overhead of auto-boxing due to generics since [Collection] classes all operate on objects.
 *
 * This implementation is not thread-safe: if multiple threads access this
 * container concurrently, and one or more threads modify the structure of
 * the list (insertion or removal for instance), the calling code must provide
 * the appropriate synchronization. It is also not safe to mutate during reentrancy --
 * in the middle of a [forEach], for example. However, concurrent reads are safe.
 */
@Serializable(with = IntListSerializer::class)
public sealed class IntList(initialCapacity: Int) {
    @JvmField
    @PublishedApi
    internal var content: IntArray = if (initialCapacity == 0) {
        EmptyIntArray
    } else {
        IntArray(initialCapacity)
    }

    @Suppress("PropertyName")
    @JvmField
    @PublishedApi
    internal var _size: Int = 0

    /**
     * The number of elements in the [IntList].
     */
    public inline val size: Int
        get() = _size

    /**
     * Returns the last valid index in the [IntList]. This can be `-1` when the list is empty.
     */
    public inline val lastIndex: Int get() = _size - 1

    /**
     * Returns an [IntRange] of the valid indices for this [IntList].
     */
    public inline val indices: IntRange get() = 0 until _size

    /**
     * Returns `true` if the collection has no elements in it.
     */
    public inline fun none(): Boolean {
        return isEmpty()
    }

    /**
     * Returns `true` if none of the elements give a `true` return value for [predicate].
     */
    public inline fun none(predicate: (element: Int) -> Boolean): Boolean {
        contract { callsInPlace(predicate) }
        forEach {
            if (predicate(it)) {
                return false
            }
        }
        return true
    }

    /**
     * Returns `true` if there's at least one element in the collection.
     */
    public inline fun any(): Boolean {
        return isNotEmpty()
    }

    /**
     * Returns `true` if any of the elements give a `true` return value for [predicate].
     */
    public inline fun any(predicate: (element: Int) -> Boolean): Boolean {
        contract { callsInPlace(predicate) }
        forEach {
            if (predicate(it)) {
                return true
            }
        }
        return false
    }

    /**
     * Returns `true` if any of the elements give a `true` return value for [predicate] while
     * iterating in the reverse order.
     */
    public inline fun reversedAny(predicate: (element: Int) -> Boolean): Boolean {
        contract { callsInPlace(predicate) }
        forEachReversed {
            if (predicate(it)) {
                return true
            }
        }
        return false
    }

    /** Returns `true` if all elements match the given [predicate]. */
    public inline fun all(predicate: (element: Int) -> Boolean): Boolean {
        contract { callsInPlace(predicate) }
        forEach { if (!predicate(it)) return false }
        return true
    }

    /**
     * Returns `true` if all elements match the given [predicate] while
     * iterating in the reverse order.
     */
    public inline fun reversedAll(predicate: (element: Int) -> Boolean): Boolean {
        contract { callsInPlace(predicate) }
        forEachReversed { if (!predicate(it)) return false }
        return true
    }

    /**
     * Returns `true` if at least [n] elements in the list match the given [predicate].
     *
     * @param n The minimum number of elements that must match the predicate.
     * @param predicate A function that takes an element of type T and returns a Boolean.
     * @return `true` if at least [n] elements match the predicate, `false` otherwise.
     * @throws IllegalArgumentException if [n] is negative.
     */
    inline fun atLeast(n: Int, predicate: (Int) -> Boolean): Boolean {
        if (n < 0) throwIllegalArgumentException("n must be >= 0")
        if (n == 0) return true
        var count = 0
        forEach { element ->
            if (predicate(element) && ++count >= n) {
                return true
            }
        }
        return false
    }

    /**
     * Returns `true` if at most [n] elements in the list match the given [predicate].
     *
     * @param n The maximum number of elements that may match the predicate.
     * @param predicate A function that takes an element of type T and returns a Boolean.
     * @return `true` if at most [n] elements match the predicate, `false` otherwise.
     * @throws IllegalArgumentException if [n] is negative.
     */
    inline fun atMost(n: Int, predicate: (Int) -> Boolean): Boolean {
        if (n < 0) throwIllegalArgumentException("n must be >= 0")
        var count = 0
        forEach { element ->
            if (predicate(element) && ++count > n) {
                return false
            }
        }
        return true
    }

    /**
     * Returns `true` if the [IntList] contains [element] or `false` otherwise.
     */
    public operator fun contains(element: Int): Boolean {
        forEach {
            if (it == element) {
                return true
            }
        }
        return false
    }

    /**
     * Returns `true` if the [IntList] contains all elements in [elements] or `false` if
     * one or more are missing.
     */
    public fun containsAll(elements: IntList): Boolean {
        for (i in elements.indices) {
            if (!contains(elements[i])) return false
        }
        return true
    }

    /**
     * Returns the number of elements in this list.
     */
    public inline fun count(): Int = _size

    /**
     * Counts the number of elements matching [predicate].
     * @return The number of elements in this list for which [predicate] returns true.
     */
    public inline fun count(predicate: (element: Int) -> Boolean): Int {
        contract { callsInPlace(predicate) }
        var count = 0
        forEach { if (predicate(it)) count++ }
        return count
    }

    /**
     * Returns the first element in the [IntList] or throws a [NoSuchElementException] if
     * it [isEmpty].
     */
    public fun first(): Int {
        if (isEmpty()) {
            throwNoSuchElementException("IntList is empty.")
        }
        return content[0]
    }

    /**
     * Returns the first element in the [IntList] for which [predicate] returns `true` or
     * throws [NoSuchElementException] if nothing matches.
     * @see indexOfFirst
     */
    public inline fun first(predicate: (element: Int) -> Boolean): Int {
        contract { callsInPlace(predicate) }
        forEach { item ->
            if (predicate(item)) return item
        }
        throw NoSuchElementException("IntList contains no element matching the predicate.")
    }

    /**
     * Accumulates values, starting with [initial], and applying [operation] to each element
     * in the [IntList] in order.
     * @param initial The value of `acc` for the first call to [operation] or return value if
     * there are no elements in this list.
     * @param operation function that takes current accumulator value and an element, and
     * calculates the next accumulator value.
     */
    public inline fun <R> fold(initial: R, operation: (acc: R, element: Int) -> R): R {
        contract { callsInPlace(operation) }
        var acc = initial
        forEach { item ->
            acc = operation(acc, item)
        }
        return acc
    }

    /**
     * Accumulates values, starting with [initial], and applying [operation] to each element
     * in the [IntList] in order.
     */
    public inline fun <R> foldIndexed(
        initial: R,
        operation: (index: Int, acc: R, element: Int) -> R
    ): R {
        contract { callsInPlace(operation) }
        var acc = initial
        forEachIndexed { i, item ->
            acc = operation(i, acc, item)
        }
        return acc
    }

    /**
     * Accumulates values, starting with [initial], and applying [operation] to each element
     * in the [IntList] in reverse order.
     * @param initial The value of `acc` for the first call to [operation] or return value if
     * there are no elements in this list.
     * @param operation function that takes an element and the current accumulator value, and
     * calculates the next accumulator value.
     */
    public inline fun <R> foldRight(initial: R, operation: (element: Int, acc: R) -> R): R {
        contract { callsInPlace(operation) }
        var acc = initial
        forEachReversed { item ->
            acc = operation(item, acc)
        }
        return acc
    }

    /**
     * Accumulates values, starting with [initial], and applying [operation] to each element
     * in the [IntList] in reverse order.
     */
    public inline fun <R> foldRightIndexed(
        initial: R,
        operation: (index: Int, element: Int, acc: R) -> R
    ): R {
        contract { callsInPlace(operation) }
        var acc = initial
        forEachReversedIndexed { i, item ->
            acc = operation(i, item, acc)
        }
        return acc
    }

    /**
     * Calls [block] for each element in the [IntList], in order.
     * @param block will be executed for every element in the list, accepting an element from
     * the list
     */
    public inline fun forEach(block: (element: Int) -> Unit) {
        contract { callsInPlace(block) }
        val content = content
        for (i in 0 until _size) {
            block(content[i])
        }
    }

    /**
     * Calls [block] for each element in the [IntList] along with its index, in order.
     * @param block will be executed for every element in the list, accepting the index and
     * the element at that index.
     */
    public inline fun forEachIndexed(block: (index: Int, element: Int) -> Unit) {
        contract { callsInPlace(block) }
        val content = content
        for (i in 0 until _size) {
            block(i, content[i])
        }
    }

    /**
     * Calls [block] for each element in the [IntList] in reverse order.
     * @param block will be executed for every element in the list, accepting an element from
     * the list
     */
    public inline fun forEachReversed(block: (element: Int) -> Unit) {
        contract { callsInPlace(block) }
        val content = content
        for (i in _size - 1 downTo 0) {
            block(content[i])
        }
    }

    /**
     * Calls [block] for each element in the [IntList] along with its index, in reverse
     * order.
     * @param block will be executed for every element in the list, accepting the index and
     * the element at that index.
     */
    public inline fun forEachReversedIndexed(block: (index: Int, element: Int) -> Unit) {
        contract { callsInPlace(block) }
        val content = content
        for (i in _size - 1 downTo 0) {
            block(i, content[i])
        }
    }

    /**
     * Returns the element at the given [index] or throws [IndexOutOfBoundsException] if
     * the [index] is out of bounds of this collection.
     */
    public operator fun get(index: Int): Int {
        if (index !in 0 until _size) {
            throwIndexOutOfBoundsException("Index must be between 0 and size")
        }
        return content[index]
    }

    /**
     * Returns the element at the given [index] or throws [IndexOutOfBoundsException] if
     * the [index] is out of bounds of this collection.
     */
    public fun elementAt(index: Int): Int {
        if (index !in 0 until _size) {
            throwIndexOutOfBoundsException("Index must be between 0 and size")
        }
        return content[index]
    }

    /**
     * Returns the element at the given [index] or [defaultValue] if [index] is out of bounds
     * of the collection.
     * @param index The index of the element whose value should be returned
     * @param defaultValue A lambda to call with [index] as a parameter to return a value at
     * an index not in the list.
     */
    public inline fun elementAtOrElse(
        index: Int,
        defaultValue: (index: Int) -> Int
    ): Int {
        if (index !in 0 until _size) {
            return defaultValue(index)
        }
        return content[index]
    }

    /**
     * Returns the index of [element] in the [IntList] or `-1` if [element] is not there.
     */
    public fun indexOf(element: Int): Int {
        forEachIndexed { i, item ->
            if (element == item) {
                return i
            }
        }
        return -1
    }

    /**
     * Returns the index if the first element in the [IntList] for which [predicate]
     * returns `true`.
     */
    public inline fun indexOfFirst(predicate: (element: Int) -> Boolean): Int {
        contract { callsInPlace(predicate) }
        forEachIndexed { i, item ->
            if (predicate(item)) {
                return i
            }
        }
        return -1
    }

    /**
     * Returns the index if the last element in the [IntList] for which [predicate]
     * returns `true`.
     */
    public inline fun indexOfLast(predicate: (element: Int) -> Boolean): Int {
        contract { callsInPlace(predicate) }
        forEachReversedIndexed { i, item ->
            if (predicate(item)) {
                return i
            }
        }
        return -1
    }

    /**
     * Returns `true` if the [IntList] has no elements in it or `false` otherwise.
     */
    public inline fun isEmpty(): Boolean = _size == 0

    /**
     * Returns `true` if there are elements in the [IntList] or `false` if it is empty.
     */
    public inline fun isNotEmpty(): Boolean = _size != 0

    /**
     * Returns the last element in the [IntList] or throws a [NoSuchElementException] if
     * it [isEmpty].
     */
    public fun last(): Int {
        if (isEmpty()) {
            throwNoSuchElementException("IntList is empty.")
        }
        return content[lastIndex]
    }

    /**
     * Returns the last element in the [IntList] for which [predicate] returns `true` or
     * throws [NoSuchElementException] if nothing matches.
     * @see indexOfLast
     */
    public inline fun last(predicate: (element: Int) -> Boolean): Int {
        contract { callsInPlace(predicate) }
        forEachReversed { item ->
            if (predicate(item)) {
                return item
            }
        }
        throw NoSuchElementException("IntList contains no element matching the predicate.")
    }

    /**
     * Returns the index of the last element in the [IntList] that is the same as
     * [element] or `-1` if no elements match.
     */
    public fun lastIndexOf(element: Int): Int {
        forEachReversedIndexed { i, item ->
            if (item == element) {
                return i
            }
        }
        return -1
    }

    /**
     * Searches this list the specified element in the range defined by [fromIndex] and [toIndex].
     * The list is expected to be sorted into ascending order according to the natural ordering of
     * its elements, otherwise the result is undefined.
     *
     * [fromIndex] must be >= 0 and < [toIndex], and [toIndex] must be <= [size], otherwise an
     * an [IndexOutOfBoundsException] will be thrown.
     *
     * @return the index of the element if it is contained in the list within the specified range.
     *   otherwise, the inverted insertion point `(-insertionPoint - 1)`. The insertion point is
     *   defined as the index at which the element should be inserted, so that the list remains
     *   sorted.
     */
    @JvmOverloads
    public fun binarySearch(element: Int, fromIndex: Int = 0, toIndex: Int = size): Int {
        if (fromIndex < 0 || fromIndex >= toIndex || toIndex > _size) {
            throwIndexOutOfBoundsException("")
        }

        var low = fromIndex
        var high = toIndex - 1

        while (low <= high) {
            val mid = low + high ushr 1
            val midVal = content[mid]
            if (midVal < element) {
                low = mid + 1
            } else if (midVal > element) {
                high = mid - 1
            } else {
                return mid // key found
            }
        }

        return -(low + 1) // key not found.
    }

    /**
     * Creates a String from the elements separated by [separator] and using [prefix] before
     * and [postfix] after, if supplied.
     *
     * When a non-negative value of [limit] is provided, a maximum of [limit] items are used
     * to generate the string. If the collection holds more than [limit] items, the string
     * is terminated with [truncated].
     */
    @JvmOverloads
    public fun joinToString(
        separator: CharSequence = ", ",
        prefix: CharSequence = "",
        postfix: CharSequence = "",
        limit: Int = -1,
        truncated: CharSequence = "...",
    ): String = buildString {
        append(prefix)
        run {
            this@IntList.forEachIndexed { index, element ->
                if (index != 0) {
                    append(separator)
                }
                if (index == limit) {
                    append(truncated)
                    return@run
                }
                append(element)
            }
        }
        append(postfix)
    }

    /**
     * Creates a String from the elements separated by [separator] and using [prefix] before
     * and [postfix] after, if supplied. [transform] dictates how each element will be represented.
     *
     * When a non-negative value of [limit] is provided, a maximum of [limit] items are used
     * to generate the string. If the collection holds more than [limit] items, the string
     * is terminated with [truncated].
     */
    @JvmOverloads
    public inline fun joinToString(
        separator: CharSequence = ", ",
        prefix: CharSequence = "",
        postfix: CharSequence = "",
        limit: Int = -1,
        truncated: CharSequence = "...",
        crossinline transform: (Int) -> CharSequence
    ): String = buildString {
        append(prefix)
        run {
            this@IntList.forEachIndexed { index, element ->
                if (index != 0) {
                    append(separator)
                }
                if (index == limit) {
                    append(truncated)
                    return@run
                }
                append(transform(element))
            }
        }
        append(postfix)
    }

    /**
     * Returns a hash code based on the contents of the [IntList].
     */
    override fun hashCode(): Int {
        var hashCode = 0
        forEach { element ->
            hashCode += 31 * element.hashCode()
        }
        return hashCode
    }

    /**
     * Returns `true` if [other] is a [IntList] and the contents of this and [other] are the
     * same.
     */
    override fun equals(other: Any?): Boolean {
        if (other !is IntList || other._size != _size) {
            return false
        }
        val content = content
        val otherContent = other.content
        for (i in indices) {
            if (content[i] != otherContent[i]) {
                return false
            }
        }
        return true
    }

    /**
     * Returns a String representation of the list, surrounded by "[]" and each element
     * separated by ", ".
     */
    override fun toString(): String = joinToString(prefix = "[", postfix = "]")
    
    /**
     * Returns a new list containing the first [n] elements of this list.
     *
     * @param n Number of elements to take from the beginning of the list.
     * @throws IllegalArgumentException if [n] is negative.
     */
    public fun take(n: Int): IntList {
        if (n < 0) throwIllegalArgumentException("n must be >= 0")
        val newSize = n.coerceAtMost(_size)
        return MutableIntList(newSize).also {
            content.copyInto(it.content, startIndex = 0, endIndex = newSize)
            it._size = newSize
        }
    }

    /**
     * Returns a new [IntList] containing the first elements that satisfy the given [predicate].
     *
     * @param predicate Function that tests each element for a condition.
     * @return A [IntList] containing the initial contiguous elements matching [predicate].
     */
    public inline fun takeWhile(predicate: (Int) -> Boolean): IntList {
        contract { callsInPlace(predicate) }
        forEachIndexed { index, element ->
            if (!predicate(element)) return take(index)
        }
        return copy()
    }

    /**
     * Returns a new list containing the last [n] elements of this list.
     *
     * @param n Number of elements to take from the end of the list.
     * @throws IllegalArgumentException if [n] is negative.
     */
    public fun takeLast(n: Int): IntList {
        if (n < 0) throwIllegalArgumentException("n must be >= 0")
        val newSize = n.coerceAtMost(_size)
        return MutableIntList(newSize).also {
            content.copyInto(it.content, startIndex = _size - newSize, endIndex = _size)
            it._size = newSize
        }
    }

    /**
     * Returns a new [IntList] containing the last elements that satisfy the given [predicate].
     *
     * @param predicate Function that tests each element for a condition.
     * @return A [IntList] containing the final contiguous elements matching [predicate].
     */
    public inline fun takeLastWhile(predicate: (Int) -> Boolean): IntList {
        contract { callsInPlace(predicate) }
        forEachReversedIndexed { index, element ->
            if (!predicate(element)) return drop(index + 1)
        }
        return copy()
    }

    /**
     * Returns a new list containing all elements except first [n] elements.
     *
     * @param n Number of elements to drop from the beginning of the list.
     * @throws IllegalArgumentException if [n] is negative.
     */
    public fun drop(n: Int): IntList {
        if (n < 0) throwIllegalArgumentException("n must be >= 0")
        val newSize = (_size - n).coerceAtLeast(0)
        return MutableIntList(newSize).also {
            content.copyInto(it.content, startIndex = n.coerceAtMost(_size), endIndex = _size)
            it._size = newSize
        }
    }
    
    /**
     * Returns a new list containing all elements except the first elements that satisfy the given [predicate].
     *
     * @param predicate Function that tests each element for a condition.
     * @return A [IntList] containing the trailing elements after the initial contiguous elements matching [predicate].
     */
    public inline fun dropWhile(predicate: (Int) -> Boolean): IntList {
        contract { callsInPlace(predicate) }
        forEachIndexed { index, element ->
            if (!predicate(element)) return drop(index)
        }
        return emptyIntList()
    }

    /**
     * Returns a new list containing all elements except last [n] elements.
     *
     * @param n Number of elements to drop from the end of the list.
     * @throws IllegalArgumentException if [n] is negative.
     */
    public fun dropLast(n: Int): IntList {
        if (n < 0) throwIllegalArgumentException("n must be >= 0")
        val newSize = (_size - n).coerceAtLeast(0)
        return MutableIntList(newSize).also {
            content.copyInto(it.content, startIndex = 0, endIndex = newSize)
            it._size = newSize
        }
    }
    
    /**
     * Returns a new [IntList] containing all elements except the last elements that satisfy the given [predicate].
     *
     * @param predicate Function that tests each element for a condition.
     * @return A [IntList] with the trailing contiguous elements matching [predicate] removed.
     */
    public inline fun dropLastWhile(predicate: (Int) -> Boolean): IntList {
        contract { callsInPlace(predicate) }
        forEachReversedIndexed { index, element ->
            if (!predicate(element)) return take(index + 1)
        }
        return emptyIntList()
    }

    /**
     * Returns a list containing only elements matching the given [predicate].
     */
    public inline fun filter(predicate: (Int) -> Boolean): IntList {
        contract { callsInPlace(predicate) }
        val result = MutableIntList()
        forEach { element ->
            if (predicate(element)) result.add(element)
        }
        return result
    }

    /**
     * Returns a new list that is a copy of the current list.
     */
    public fun copy(): IntList {
        return MutableIntList(_size).also {
            content.copyInto(it.content, startIndex = 0, endIndex = _size)
            it._size = _size
        }
    }

    /**
     * Returns a new list containing only distinct elements from the current list.
     */
    public fun distinct(): IntList {
        val set = HashSet<Int>()
        return filter { element -> set.add(element) }
    }


    /**
     * Returns a new list of all elements sorted according to natural sort order.
     */
    public fun sorted(): IntList {
        return copy().also { it.content.sort() }
    }

    /**
     * Returns a new list of all elements sorted according to descending natural sort order.
     */
    public fun sortedDescending(): IntList {
        return copy().also { it.content.sortDescending() }
    }

    /**
     * Returns a new list with all elements in reversed order.
     */
    public fun reversed(): IntList {
        return MutableIntList(_size).also { list ->
            forEachIndexed { index, element ->
                list.content[_size - index - 1] = element
            }
            list._size = _size
        }
    }

    /**
     * Splits this list into chunks of specified size.
     *
     * @param chunkSize the size of each chunk
     * @throws IllegalArgumentException if [chunkSize] is not positive
     */
    public fun chunked(chunkSize: Int): List<IntList> {
        if (chunkSize <= 0) throwIllegalArgumentException("Size must be positive")

        val numChunks = _size / chunkSize + if (_size % chunkSize != 0) 1 else 0
        val result = ArrayList<IntList>(numChunks)

        var startIndex = 0
        while (startIndex < _size) {
            val endIndex = minOf(startIndex + chunkSize, _size)
            val chunk = MutableIntList(endIndex - startIndex).also { list ->
                content.copyInto(
                    destination = list.content,
                    destinationOffset = 0,
                    startIndex = startIndex,
                    endIndex = endIndex
                )
                list._size = endIndex - startIndex
            }
            result.add(chunk)
            startIndex = endIndex
        }

        return result
    }

    /**
     * Returns the sum of all elements in the list.
     */
    public fun sum(): Int {
        var sum = 0
        forEach { sum += it }
        return sum
    }

    /**
     * Returns the sum of all elements in the list after transforming them by the given [element] function.
     *
     * @param element The transformation function applied to each element before summing.
     */
    public inline fun sumOf(element: (Int) -> Int): Int {
        var sum = 0
        forEach { sum += element(it) }
        return sum
    }
    
    /**
     * Returns a [IntArray] containing all elements from this list in the same order.
     */
    public fun toIntArray(): IntArray = content.copyOf(_size)
    
    /**
     * Return a [MutableSet] of all elements.
     * The returned set does NOT guarantee the element iteration order of the list.
     */
    public fun toMutableSet(): MutableSet<Int> {
        return HashSet<Int>().also { set ->
            forEach { set.add(it) }
        }
    }
    
    /**
     * Return a [Set] of all elements. 
     * The returned set does NOT guarantee the element iteration order of the list.
     */
    public fun toSet(): Set<Int> = toMutableSet()
}

/**
 * [MutableIntList] is a [MutableList]-like collection for [Int] values.
 * It allows storing and retrieving the elements without boxing. Immutable
 * access is available through its base class [IntList], which has a [List]-like
 * interface.
 *
 * This implementation is not thread-safe: if multiple threads access this
 * container concurrently, and one or more threads modify the structure of
 * the list (insertion or removal for instance), the calling code must provide
 * the appropriate synchronization. It is also not safe to mutate during reentrancy --
 * in the middle of a [forEach], for example. However, concurrent reads are safe.
 *
 * @constructor Creates a [MutableIntList] with a [capacity] of `initialCapacity`.
 */
public class MutableIntList(
    initialCapacity: Int = 16
) : IntList(initialCapacity) {

    /**
     * Returns the total number of elements that can be held before the [MutableIntList] must
     * grow.
     *
     * @see ensureCapacity
     */
    public inline val capacity: Int
        get() = content.size

    /**
     * Adds [element] to the [MutableIntList] and returns `true`.
     */
    public fun add(element: Int): Boolean {
        ensureCapacity(_size + 1)
        content[_size] = element
        _size++
        return true
    }

    /**
     * Adds [element] to the [MutableIntList] at the given [index], shifting over any
     * elements at [index] and after, if any.
     * @throws IndexOutOfBoundsException if [index] isn't between 0 and [size], inclusive
     */
    public fun add(index: Int, element: Int) {
        if (index !in 0.._size) {
            throwIndexOutOfBoundsException("Index must be between 0 and size")
        }
        ensureCapacity(_size + 1)
        val content = content
        if (index != _size) {
            content.copyInto(
                destination = content,
                destinationOffset = index + 1,
                startIndex = index,
                endIndex = _size
            )
        }
        content[index] = element
        _size++
    }

    /**
     * Adds all [elements] to the [MutableIntList] at the given [index], shifting over any
     * elements at [index] and after, if any.
     * @return `true` if the [MutableIntList] was changed or `false` if [elements] was empty
     * @throws IndexOutOfBoundsException if [index] isn't between 0 and [size], inclusive.
     */
    public fun addAll(
        index: Int,
        elements: IntArray
    ): Boolean {
        if (index !in 0.._size) {
            throwIndexOutOfBoundsException("")
        }
        if (elements.isEmpty()) return false
        ensureCapacity(_size + elements.size)
        val content = content
        if (index != _size) {
            content.copyInto(
                destination = content,
                destinationOffset = index + elements.size,
                startIndex = index,
                endIndex = _size
            )
        }
        elements.copyInto(content, index)
        _size += elements.size
        return true
    }

    /**
     * Adds all [elements] to the [MutableIntList] at the given [index], shifting over any
     * elements at [index] and after, if any.
     * @return `true` if the [MutableIntList] was changed or `false` if [elements] was empty
     * @throws IndexOutOfBoundsException if [index] isn't between 0 and [size], inclusive
     */
    public fun addAll(
        index: Int,
        elements: IntList
    ): Boolean {
        if (index !in 0.._size) {
            throwIndexOutOfBoundsException("")
        }
        if (elements.isEmpty()) return false
        ensureCapacity(_size + elements._size)
        val content = content
        if (index != _size) {
            content.copyInto(
                destination = content,
                destinationOffset = index + elements._size,
                startIndex = index,
                endIndex = _size
            )
        }
        elements.content.copyInto(
            destination = content,
            destinationOffset = index,
            startIndex = 0,
            endIndex = elements._size
        )
        _size += elements._size
        return true
    }

    /**
     * Adds all [elements] to the end of the [MutableIntList] and returns `true` if the
     * [MutableIntList] was changed or `false` if [elements] was empty.
     */
    public inline fun addAll(elements: IntList): Boolean {
        return addAll(_size, elements)
    }

    /**
     * Adds all [elements] to the end of the [MutableIntList] and returns `true` if the
     * [MutableIntList] was changed or `false` if [elements] was empty.
     */
    public inline fun addAll(elements: IntArray): Boolean {
        return addAll(_size, elements)
    }

    /**
     * Adds all [elements] to the end of the [MutableIntList] and returns `true` if the
     * [MutableIntList] was changed or `false` if [elements] was empty.
     */
    public inline fun addAll(elements: List<Int>): Boolean {
        if (elements.isEmpty()) return false
        ensureCapacity(_size + elements.size)
        var curr = _size
        for (i in elements.indices) content[curr++] = elements[i]
        _size += elements.size
        return true
    }

    /**
     * Adds all [elements] to the end of the [MutableIntList].
     */
    public inline operator fun plusAssign(elements: IntList) {
        addAll(_size, elements)
    }

    /**
     * Adds all [elements] to the end of the [MutableIntList].
     */
    public inline operator fun plusAssign(elements: IntArray) {
        addAll(_size, elements)
    }

    /**
     * Removes all elements in the [MutableIntList]. The storage isn't released.
     * @see trim
     */
    public fun clear() {
        _size = 0
    }

    /**
     * Reduces the internal storage. If [capacity] is greater than [minCapacity] and [size], the
     * internal storage is reduced to the maximum of [size] and [minCapacity].
     * @see ensureCapacity
     */
    public fun trim(minCapacity: Int = _size) {
        val minSize = maxOf(minCapacity, _size)
        if (capacity > minSize) {
            content = content.copyOf(minSize)
        }
    }

    /**
     * Ensures that there is enough space to store [capacity] elements in the [MutableIntList].
     * @see trim
     */
    public fun ensureCapacity(capacity: Int) {
        val oldContent = content
        if (oldContent.size < capacity) {
            val newSize = maxOf(capacity, oldContent.size * 3 / 2)
            content = oldContent.copyOf(newSize)
        }
    }

    /**
     * [add] [element] to the [MutableIntList].
     */
    public inline operator fun plusAssign(element: Int) {
        add(element)
    }

    /**
     * [remove] [element] from the [MutableIntList]
     */
    public inline operator fun minusAssign(element: Int) {
        remove(element)
    }

    /**
     * Removes [element] from the [MutableIntList]. If [element] was in the [MutableIntList]
     * and was removed, `true` will be returned, or `false` will be returned if the element
     * was not found.
     */
    public fun remove(element: Int): Boolean {
        val index = indexOf(element)
        if (index >= 0) {
            removeAt(index)
            return true
        }
        return false
    }

    /**
     * Removes all elements matching the given [predicate] and returns `true` if anything was removed.
     */
    private inline fun removeAll(predicate: (Int) -> Boolean): Boolean {
        val initialSize = _size
        var w = 0
        forEach { item ->
            if (!predicate(item)) content[w++] = item
        }
        _size = w
        return initialSize != _size
    }

    /**
     * Removes all [elements] from the [MutableIntList] and returns `true` if anything was removed.
     */
    public fun removeAll(elements: IntArray): Boolean {
        return removeAll { item -> elements.any { it == item } }
    }

    /**
     * Removes all [elements] from the [MutableIntList] and returns `true` if anything was removed.
     */
    public fun removeAll(elements: IntList): Boolean {
        return removeAll { item -> elements.any { it == item } }
    }

    /**
     * Removes all [elements] from the [MutableIntList].
     */
    public operator fun minusAssign(elements: IntArray) {
        removeAll(elements)
    }

    /**
     * Removes all [elements] from the [MutableIntList].
     */
    public operator fun minusAssign(elements: IntList) {
        removeAll(elements)
    }

    /**
     * Removes the element at the given [index] and returns it.
     * @throws IndexOutOfBoundsException if [index] isn't between 0 and [lastIndex], inclusive
     */
    public fun removeAt(index: Int): Int {
        if (index !in 0 until _size) {
            throwIndexOutOfBoundsException("Index must be between 0 and size")
        }
        val content = content
        val item = content[index]
        if (index != lastIndex) {
            content.copyInto(
                destination = content,
                destinationOffset = index,
                startIndex = index + 1,
                endIndex = _size
            )
        }
        _size--
        return item
    }

    /**
     * Removes items from index [start] (inclusive) to [end] (exclusive).
     * @throws IndexOutOfBoundsException if [start] or [end] isn't between 0 and [size], inclusive
     * @throws IllegalArgumentException if [start] is greater than [end]
     */
    public fun removeRange(
        start: Int,
        end: Int
    ) {
        if (start !in 0.._size || end !in 0.._size) {
            throwIndexOutOfBoundsException("Index must be between 0 and size")
        }
        if (end < start) {
            throwIllegalArgumentException("The end index must be >= start index")
        }
        if (end != start) {
            if (end < _size) {
                content.copyInto(
                    destination = content,
                    destinationOffset = start,
                    startIndex = end,
                    endIndex = _size
                )
            }
            _size -= (end - start)
        }
    }

    /**
     * Keeps only [elements] in the [MutableIntList] and removes all other values.
     * @return `true` if the [MutableIntList] has changed.
     */
    public fun retainAll(elements: IntArray): Boolean {
        return removeAll { item -> elements.none { it == item } }
    }

    /**
     * Keeps only [elements] in the [MutableIntList] and removes all other values.
     * @return `true` if the [MutableIntList] has changed.
     */
    public fun retainAll(elements: IntList): Boolean {
       return removeAll { item -> elements.none { it == item } }
    }

    /**
     * Sets the value at [index] to [element].
     * @return the previous value set at [index]
     * @throws IndexOutOfBoundsException if [index] isn't between 0 and [lastIndex], inclusive
     */
    public operator fun set(
        index: Int,
        element: Int
    ): Int {
        if (index !in 0 until _size) {
            throwIndexOutOfBoundsException("Index must be between 0 and size")
        }
        val content = content
        val old = content[index]
        content[index] = element
        return old
    }

    /**
     * Sorts the [MutableIntList] elements in ascending order.
     */
    public fun sort() {
        if (_size == 0) return
        content.sort(fromIndex = 0, toIndex = _size)
    }

    /**
     * Sorts the [MutableIntList] elements in descending order.
     */
    public fun sortDescending() {
        if (_size == 0) return
        content.sortDescending(fromIndex = 0, toIndex = _size)
    }
}

/**
 * Creates a [MutableIntList] with the specified size, where each element is calculated by calling the specified [init] function.
 * [init] is called for each list element sequentially starting from the first one. It should return the value for a list element given its index.
 *
 * @param size The initial capacity of the list.
 * @param init Function that takes an index and returns the element to be placed at that index.
 */
public inline fun MutableIntList(size: Int, init: (index: Int) -> Int): MutableIntList {
    return MutableIntList(size).apply {
        for (i in 0 until size) { content[i] = init(i) }
        _size = size
    }
}

/**
 * Creates a [IntList] with the specified size, where each element is calculated by calling the specified [init] function.
 * [init] is called for each list element sequentially starting from the first one. It should return the value for a list element given its index.
 *
 * @param size The initial capacity of the list.
 * @param init Function that takes an index and returns the element to be placed at that index.
 */
public inline fun IntList(size: Int, init: (index: Int) -> Int): IntList = MutableIntList(size, init)

private val EmptyIntList: IntList = MutableIntList(0)

/**
 * @return a read-only [IntList] with nothing in it.
 */
public fun emptyIntList(): IntList = EmptyIntList

/**
 * @return a read-only [IntList] with nothing in it.
 */
public fun intListOf(): IntList = EmptyIntList

/**
 * @return a new read-only [IntList] with [element1] as the only item in the list.
 */
public fun intListOf(element1: Int): IntList = mutableIntListOf(element1)

/**
 * @return a new read-only [IntList] with 2 elements, [element1] and [element2], in order.
 */
public fun intListOf(element1: Int, element2: Int): IntList =
    mutableIntListOf(element1, element2)

/**
 * @return a new read-only [IntList] with 3 elements, [element1], [element2], and [element3],
 * in order.
 */
public fun intListOf(element1: Int, element2: Int, element3: Int): IntList =
    mutableIntListOf(element1, element2, element3)

/**
 * @return a new read-only [IntList] with [elements] in order.
 */
public fun intListOf(vararg elements: Int): IntList =
    MutableIntList(elements.size).apply { plusAssign(elements) }

/**
 * @return a new empty [MutableIntList] with the default capacity.
 */
public inline fun mutableIntListOf(): MutableIntList = MutableIntList()

/**
 * @return a new [MutableIntList] with [element1] as the only item in the list.
 */
public fun mutableIntListOf(element1: Int): MutableIntList {
    val list = MutableIntList(1)
    list += element1
    return list
}

/**
 * @return a new [MutableIntList] with 2 elements, [element1] and [element2], in order.
 */
public fun mutableIntListOf(element1: Int, element2: Int): MutableIntList {
    val list = MutableIntList(2)
    list += element1
    list += element2
    return list
}

/**
 * @return a new [MutableIntList] with 3 elements, [element1], [element2], and [element3],
 * in order.
 */
public fun mutableIntListOf(element1: Int, element2: Int, element3: Int): MutableIntList {
    val list = MutableIntList(3)
    list += element1
    list += element2
    list += element3
    return list
}

/**
 * @return a new [MutableIntList] with the given elements, in order.
 */
public inline fun mutableIntListOf(vararg elements: Int): MutableIntList =
    MutableIntList(elements.size).apply { plusAssign(elements) }

/**
 * Builds a new [IntList] by populating a [MutableIntList] using the given
 * [builderAction].
 *
 * The instance passed as a receiver to the [builderAction] is valid only inside that function.
 * Using it outside of the function produces an unspecified behavior.
 *
 * @param builderAction Lambda in which the [MutableIntList] can be populated.
 */
public inline fun buildIntList(
    builderAction: MutableIntList.() -> Unit,
): IntList {
    contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }
    return MutableIntList().apply(builderAction)
}

/**
 * Builds a new [IntList] by populating a [MutableIntList] using the given
 * [builderAction].
 *
 * The instance passed as a receiver to the [builderAction] is valid only inside that function.
 * Using it outside of the function produces an unspecified behavior.
 *
 * @param initialCapacity Hint for the expected number of elements added in the [builderAction].
 * @param builderAction Lambda in which the [MutableIntList] can be populated.
 */
public inline fun buildIntList(
    initialCapacity: Int,
    builderAction: MutableIntList.() -> Unit,
): IntList {
    contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }
    return MutableIntList(initialCapacity).apply(builderAction)
}

/**
 * Returns the [IntList] itself if it's not null; otherwise returns an empty [IntList].
 */
public fun IntList?.orEmpty(): IntList = this ?: emptyIntList()

/**
 * Serializer for [IntList] that handles serialization and deserialization of the list.
 */
object IntListSerializer : KSerializer<IntList> {
    @OptIn(InternalSerializationApi::class)
    override val descriptor =
        buildSerialDescriptor("kubit.collections.list.IntList", StructureKind.LIST)

    override fun serialize(encoder: Encoder, value: IntList) {
        val size = value.size
        val composite = encoder.beginCollection(descriptor, size)
        value.forEachIndexed { index, element ->
            composite.encodeIntElement(descriptor, index, element)
        }
        composite.endStructure(descriptor)
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun deserialize(decoder: Decoder): IntList {
        val composite = decoder.beginStructure(descriptor)
        val size = composite.decodeCollectionSize(descriptor)
        val items: MutableIntList
        if (composite.decodeSequentially()) {
            items = MutableIntList(size)
            for (i in 0 until size) {
                items.content[i] = composite.decodeIntElement(descriptor, i)
            }
            items._size = size
        } else {
            items = MutableIntList(if (size == -1) 16 else size)
            var index = composite.decodeElementIndex(descriptor)
            while (index != CompositeDecoder.DECODE_DONE) {
                items.add(composite.decodeIntElement(descriptor, index))
                index = composite.decodeElementIndex(descriptor)
            }
        }
        composite.endStructure(descriptor)
        return items
    }
}
