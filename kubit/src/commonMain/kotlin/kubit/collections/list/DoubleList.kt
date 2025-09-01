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
 * This file is based on the DoubleList from androidx.collection, but it has been
 * substantially modified and will continue to be developed and maintained independently.
 * Original code © The Android Open Source Project and licensed under the Apache License, Version 2.0.
 * Further modifications copyright © 2025 Mohamed Shaaban.
 */

@file:Suppress("RedundantVisibilityModifier")

package kubit.collections.list


import kubit.collections.list.internal.throwIllegalArgumentException
import kubit.collections.list.internal.throwIndexOutOfBoundsException
import kubit.collections.list.internal.throwNoSuchElementException
import kubit.collections.list.internal.EmptyDoubleArray
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
 * [DoubleList] is a [List]-like collection for [Double] values. It allows retrieving
 * the elements without boxing. [DoubleList] is always backed by a [MutableDoubleList],
 * its [MutableList]-like subclass. The purpose of this class is to avoid the performance
 * overhead of auto-boxing due to generics since [Collection] classes all operate on objects.
 *
 * This implementation is not thread-safe: if multiple threads access this
 * container concurrently, and one or more threads modify the structure of
 * the list (insertion or removal for instance), the calling code must provide
 * the appropriate synchronization. It is also not safe to mutate during reentrancy --
 * in the middle of a [forEach], for example. However, concurrent reads are safe.
 */
@Serializable(with = DoubleListSerializer::class)
public sealed class DoubleList(initialCapacity: Int) {
    @JvmField
    @PublishedApi
    internal var content: DoubleArray = if (initialCapacity == 0) {
        EmptyDoubleArray
    } else {
        DoubleArray(initialCapacity)
    }

    @Suppress("PropertyName")
    @JvmField
    @PublishedApi
    internal var _size: Int = 0

    /**
     * The number of elements in the [DoubleList].
     */
    public inline val size: Int
        get() = _size

    /**
     * Returns the last valid index in the [DoubleList]. This can be `-1` when the list is empty.
     */
    public inline val lastIndex: Int get() = _size - 1

    /**
     * Returns an [IntRange] of the valid indices for this [DoubleList].
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
    public inline fun none(predicate: (element: Double) -> Boolean): Boolean {
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
    public inline fun any(predicate: (element: Double) -> Boolean): Boolean {
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
    public inline fun reversedAny(predicate: (element: Double) -> Boolean): Boolean {
        contract { callsInPlace(predicate) }
        forEachReversed {
            if (predicate(it)) {
                return true
            }
        }
        return false
    }

    /** Returns `true` if all elements match the given [predicate]. */
    public inline fun all(predicate: (element: Double) -> Boolean): Boolean {
        contract { callsInPlace(predicate) }
        forEach { if (!predicate(it)) return false }
        return true
    }

    /**
     * Returns `true` if all elements match the given [predicate] while
     * iterating in the reverse order.
     */
    public inline fun reversedAll(predicate: (element: Double) -> Boolean): Boolean {
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
    inline fun atLeast(n: Int, predicate: (Double) -> Boolean): Boolean {
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
    inline fun atMost(n: Int, predicate: (Double) -> Boolean): Boolean {
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
     * Returns `true` if the [DoubleList] contains [element] or `false` otherwise.
     */
    public operator fun contains(element: Double): Boolean {
        forEach {
            if (it == element) {
                return true
            }
        }
        return false
    }

    /**
     * Returns `true` if the [DoubleList] contains all elements in [elements] or `false` if
     * one or more are missing.
     */
    public fun containsAll(elements: DoubleList): Boolean {
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
    public inline fun count(predicate: (element: Double) -> Boolean): Int {
        contract { callsInPlace(predicate) }
        var count = 0
        forEach { if (predicate(it)) count++ }
        return count
    }

    /**
     * Returns the first element in the [DoubleList] or throws a [NoSuchElementException] if
     * it [isEmpty].
     */
    public fun first(): Double {
        if (isEmpty()) {
            throwNoSuchElementException("DoubleList is empty.")
        }
        return content[0]
    }

    /**
     * Returns the first element in the [DoubleList] for which [predicate] returns `true` or
     * throws [NoSuchElementException] if nothing matches.
     * @see indexOfFirst
     */
    public inline fun first(predicate: (element: Double) -> Boolean): Double {
        contract { callsInPlace(predicate) }
        forEach { item ->
            if (predicate(item)) return item
        }
        throw NoSuchElementException("DoubleList contains no element matching the predicate.")
    }

    /**
     * Accumulates values, starting with [initial], and applying [operation] to each element
     * in the [DoubleList] in order.
     * @param initial The value of `acc` for the first call to [operation] or return value if
     * there are no elements in this list.
     * @param operation function that takes current accumulator value and an element, and
     * calculates the next accumulator value.
     */
    public inline fun <R> fold(initial: R, operation: (acc: R, element: Double) -> R): R {
        contract { callsInPlace(operation) }
        var acc = initial
        forEach { item ->
            acc = operation(acc, item)
        }
        return acc
    }

    /**
     * Accumulates values, starting with [initial], and applying [operation] to each element
     * in the [DoubleList] in order.
     */
    public inline fun <R> foldIndexed(
        initial: R,
        operation: (index: Int, acc: R, element: Double) -> R
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
     * in the [DoubleList] in reverse order.
     * @param initial The value of `acc` for the first call to [operation] or return value if
     * there are no elements in this list.
     * @param operation function that takes an element and the current accumulator value, and
     * calculates the next accumulator value.
     */
    public inline fun <R> foldRight(initial: R, operation: (element: Double, acc: R) -> R): R {
        contract { callsInPlace(operation) }
        var acc = initial
        forEachReversed { item ->
            acc = operation(item, acc)
        }
        return acc
    }

    /**
     * Accumulates values, starting with [initial], and applying [operation] to each element
     * in the [DoubleList] in reverse order.
     */
    public inline fun <R> foldRightIndexed(
        initial: R,
        operation: (index: Int, element: Double, acc: R) -> R
    ): R {
        contract { callsInPlace(operation) }
        var acc = initial
        forEachReversedIndexed { i, item ->
            acc = operation(i, item, acc)
        }
        return acc
    }

    /**
     * Calls [block] for each element in the [DoubleList], in order.
     * @param block will be executed for every element in the list, accepting an element from
     * the list
     */
    public inline fun forEach(block: (element: Double) -> Unit) {
        contract { callsInPlace(block) }
        val content = content
        for (i in 0 until _size) {
            block(content[i])
        }
    }

    /**
     * Calls [block] for each element in the [DoubleList] along with its index, in order.
     * @param block will be executed for every element in the list, accepting the index and
     * the element at that index.
     */
    public inline fun forEachIndexed(block: (index: Int, element: Double) -> Unit) {
        contract { callsInPlace(block) }
        val content = content
        for (i in 0 until _size) {
            block(i, content[i])
        }
    }

    /**
     * Calls [block] for each element in the [DoubleList] in reverse order.
     * @param block will be executed for every element in the list, accepting an element from
     * the list
     */
    public inline fun forEachReversed(block: (element: Double) -> Unit) {
        contract { callsInPlace(block) }
        val content = content
        for (i in _size - 1 downTo 0) {
            block(content[i])
        }
    }

    /**
     * Calls [block] for each element in the [DoubleList] along with its index, in reverse
     * order.
     * @param block will be executed for every element in the list, accepting the index and
     * the element at that index.
     */
    public inline fun forEachReversedIndexed(block: (index: Int, element: Double) -> Unit) {
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
    public operator fun get(index: Int): Double {
        if (index !in 0 until _size) {
            throwIndexOutOfBoundsException("Index must be between 0 and size")
        }
        return content[index]
    }

    /**
     * Returns the element at the given [index] or throws [IndexOutOfBoundsException] if
     * the [index] is out of bounds of this collection.
     */
    public fun elementAt(index: Int): Double {
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
        defaultValue: (index: Int) -> Double
    ): Double {
        if (index !in 0 until _size) {
            return defaultValue(index)
        }
        return content[index]
    }

    /**
     * Returns the index of [element] in the [DoubleList] or `-1` if [element] is not there.
     */
    public fun indexOf(element: Double): Int {
        forEachIndexed { i, item ->
            if (element == item) {
                return i
            }
        }
        return -1
    }

    /**
     * Returns the index if the first element in the [DoubleList] for which [predicate]
     * returns `true`.
     */
    public inline fun indexOfFirst(predicate: (element: Double) -> Boolean): Int {
        contract { callsInPlace(predicate) }
        forEachIndexed { i, item ->
            if (predicate(item)) {
                return i
            }
        }
        return -1
    }

    /**
     * Returns the index if the last element in the [DoubleList] for which [predicate]
     * returns `true`.
     */
    public inline fun indexOfLast(predicate: (element: Double) -> Boolean): Int {
        contract { callsInPlace(predicate) }
        forEachReversedIndexed { i, item ->
            if (predicate(item)) {
                return i
            }
        }
        return -1
    }

    /**
     * Returns `true` if the [DoubleList] has no elements in it or `false` otherwise.
     */
    public inline fun isEmpty(): Boolean = _size == 0

    /**
     * Returns `true` if there are elements in the [DoubleList] or `false` if it is empty.
     */
    public inline fun isNotEmpty(): Boolean = _size != 0

    /**
     * Returns the last element in the [DoubleList] or throws a [NoSuchElementException] if
     * it [isEmpty].
     */
    public fun last(): Double {
        if (isEmpty()) {
            throwNoSuchElementException("DoubleList is empty.")
        }
        return content[lastIndex]
    }

    /**
     * Returns the last element in the [DoubleList] for which [predicate] returns `true` or
     * throws [NoSuchElementException] if nothing matches.
     * @see indexOfLast
     */
    public inline fun last(predicate: (element: Double) -> Boolean): Double {
        contract { callsInPlace(predicate) }
        forEachReversed { item ->
            if (predicate(item)) {
                return item
            }
        }
        throw NoSuchElementException("DoubleList contains no element matching the predicate.")
    }

    /**
     * Returns the index of the last element in the [DoubleList] that is the same as
     * [element] or `-1` if no elements match.
     */
    public fun lastIndexOf(element: Double): Int {
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
            this@DoubleList.forEachIndexed { index, element ->
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
        crossinline transform: (Double) -> CharSequence
    ): String = buildString {
        append(prefix)
        run {
            this@DoubleList.forEachIndexed { index, element ->
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
     * Returns a hash code based on the contents of the [DoubleList].
     */
    override fun hashCode(): Int {
        var hashCode = 0
        forEach { element ->
            hashCode += 31 * element.hashCode()
        }
        return hashCode
    }

    /**
     * Returns `true` if [other] is a [DoubleList] and the contents of this and [other] are the
     * same.
     */
    override fun equals(other: Any?): Boolean {
        if (other !is DoubleList || other._size != _size) {
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
    public fun take(n: Int): DoubleList {
        if (n < 0) throwIllegalArgumentException("n must be >= 0")
        val newSize = n.coerceAtMost(_size)
        return MutableDoubleList(newSize).also {
            content.copyInto(it.content, startIndex = 0, endIndex = newSize)
            it._size = newSize
        }
    }

    /**
     * Returns a new [DoubleList] containing the first elements that satisfy the given [predicate].
     *
     * @param predicate Function that tests each element for a condition.
     * @return A [DoubleList] containing the initial contiguous elements matching [predicate].
     */
    public inline fun takeWhile(predicate: (Double) -> Boolean): DoubleList {
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
    public fun takeLast(n: Int): DoubleList {
        if (n < 0) throwIllegalArgumentException("n must be >= 0")
        val newSize = n.coerceAtMost(_size)
        return MutableDoubleList(newSize).also {
            content.copyInto(it.content, startIndex = _size - newSize, endIndex = _size)
            it._size = newSize
        }
    }

    /**
     * Returns a new [DoubleList] containing the last elements that satisfy the given [predicate].
     *
     * @param predicate Function that tests each element for a condition.
     * @return A [DoubleList] containing the final contiguous elements matching [predicate].
     */
    public inline fun takeLastWhile(predicate: (Double) -> Boolean): DoubleList {
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
    public fun drop(n: Int): DoubleList {
        if (n < 0) throwIllegalArgumentException("n must be >= 0")
        val newSize = (_size - n).coerceAtLeast(0)
        return MutableDoubleList(newSize).also {
            content.copyInto(it.content, startIndex = n.coerceAtMost(_size), endIndex = _size)
            it._size = newSize
        }
    }
    
    /**
     * Returns a new list containing all elements except the first elements that satisfy the given [predicate].
     *
     * @param predicate Function that tests each element for a condition.
     * @return A [DoubleList] containing the trailing elements after the initial contiguous elements matching [predicate].
     */
    public inline fun dropWhile(predicate: (Double) -> Boolean): DoubleList {
        contract { callsInPlace(predicate) }
        forEachIndexed { index, element ->
            if (!predicate(element)) return drop(index)
        }
        return emptyDoubleList()
    }

    /**
     * Returns a new list containing all elements except last [n] elements.
     *
     * @param n Number of elements to drop from the end of the list.
     * @throws IllegalArgumentException if [n] is negative.
     */
    public fun dropLast(n: Int): DoubleList {
        if (n < 0) throwIllegalArgumentException("n must be >= 0")
        val newSize = (_size - n).coerceAtLeast(0)
        return MutableDoubleList(newSize).also {
            content.copyInto(it.content, startIndex = 0, endIndex = newSize)
            it._size = newSize
        }
    }
    
    /**
     * Returns a new [DoubleList] containing all elements except the last elements that satisfy the given [predicate].
     *
     * @param predicate Function that tests each element for a condition.
     * @return A [DoubleList] with the trailing contiguous elements matching [predicate] removed.
     */
    public inline fun dropLastWhile(predicate: (Double) -> Boolean): DoubleList {
        contract { callsInPlace(predicate) }
        forEachReversedIndexed { index, element ->
            if (!predicate(element)) return take(index + 1)
        }
        return emptyDoubleList()
    }

    /**
     * Returns a list containing only elements matching the given [predicate].
     */
    public inline fun filter(predicate: (Double) -> Boolean): DoubleList {
        contract { callsInPlace(predicate) }
        val result = MutableDoubleList()
        forEach { element ->
            if (predicate(element)) result.add(element)
        }
        return result
    }

    /**
     * Returns a new list that is a copy of the current list.
     */
    public fun copy(): DoubleList {
        return MutableDoubleList(_size).also {
            content.copyInto(it.content, startIndex = 0, endIndex = _size)
            it._size = _size
        }
    }

    /**
     * Returns a new list containing only distinct elements from the current list.
     */
    public fun distinct(): DoubleList {
        val set = HashSet<Double>()
        return filter { element -> set.add(element) }
    }


    /**
     * Returns a new list of all elements sorted according to natural sort order.
     */
    public fun sorted(): DoubleList {
        return copy().also { it.content.sort() }
    }

    /**
     * Returns a new list of all elements sorted according to descending natural sort order.
     */
    public fun sortedDescending(): DoubleList {
        return copy().also { it.content.sortDescending() }
    }

    /**
     * Returns a new list with all elements in reversed order.
     */
    public fun reversed(): DoubleList {
        return MutableDoubleList(_size).also { list ->
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
    public fun chunked(chunkSize: Int): List<DoubleList> {
        if (chunkSize <= 0) throwIllegalArgumentException("Size must be positive")

        val numChunks = _size / chunkSize + if (_size % chunkSize != 0) 1 else 0
        val result = ArrayList<DoubleList>(numChunks)

        var startIndex = 0
        while (startIndex < _size) {
            val endIndex = minOf(startIndex + chunkSize, _size)
            val chunk = MutableDoubleList(endIndex - startIndex).also { list ->
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
    public fun sum(): Double {
        var sum = 0.0
        forEach { sum += it }
        return sum
    }

    /**
     * Returns the sum of all elements in the list after transforming them by the given [element] function.
     *
     * @param element The transformation function applied to each element before summing.
     */
    public inline fun sumOf(element: (Double) -> Double): Double {
        var sum = 0.0
        forEach { sum += element(it) }
        return sum
    }
    
    /**
     * Returns a [DoubleArray] containing all elements from this list in the same order.
     */
    public fun toDoubleArray(): DoubleArray = content.copyOf(_size)
    
    /**
     * Return a [MutableSet] of all elements.
     * The returned set does NOT guarantee the element iteration order of the list.
     */
    public fun toMutableSet(): MutableSet<Double> {
        return HashSet<Double>().also { set ->
            forEach { set.add(it) }
        }
    }
    
    /**
     * Return a [Set] of all elements. 
     * The returned set does NOT guarantee the element iteration order of the list.
     */
    public fun toSet(): Set<Double> = toMutableSet()
}

/**
 * [MutableDoubleList] is a [MutableList]-like collection for [Double] values.
 * It allows storing and retrieving the elements without boxing. Immutable
 * access is available through its base class [DoubleList], which has a [List]-like
 * interface.
 *
 * This implementation is not thread-safe: if multiple threads access this
 * container concurrently, and one or more threads modify the structure of
 * the list (insertion or removal for instance), the calling code must provide
 * the appropriate synchronization. It is also not safe to mutate during reentrancy --
 * in the middle of a [forEach], for example. However, concurrent reads are safe.
 *
 * @constructor Creates a [MutableDoubleList] with a [capacity] of `initialCapacity`.
 */
public class MutableDoubleList(
    initialCapacity: Int = 16
) : DoubleList(initialCapacity) {

    /**
     * Returns the total number of elements that can be held before the [MutableDoubleList] must
     * grow.
     *
     * @see ensureCapacity
     */
    public inline val capacity: Int
        get() = content.size

    /**
     * Adds [element] to the [MutableDoubleList] and returns `true`.
     */
    public fun add(element: Double): Boolean {
        ensureCapacity(_size + 1)
        content[_size] = element
        _size++
        return true
    }

    /**
     * Adds [element] to the [MutableDoubleList] at the given [index], shifting over any
     * elements at [index] and after, if any.
     * @throws IndexOutOfBoundsException if [index] isn't between 0 and [size], inclusive
     */
    public fun add(index: Int, element: Double) {
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
     * Adds all [elements] to the [MutableDoubleList] at the given [index], shifting over any
     * elements at [index] and after, if any.
     * @return `true` if the [MutableDoubleList] was changed or `false` if [elements] was empty
     * @throws IndexOutOfBoundsException if [index] isn't between 0 and [size], inclusive.
     */
    public fun addAll(
        index: Int,
        elements: DoubleArray
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
     * Adds all [elements] to the [MutableDoubleList] at the given [index], shifting over any
     * elements at [index] and after, if any.
     * @return `true` if the [MutableDoubleList] was changed or `false` if [elements] was empty
     * @throws IndexOutOfBoundsException if [index] isn't between 0 and [size], inclusive
     */
    public fun addAll(
        index: Int,
        elements: DoubleList
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
     * Adds all [elements] to the end of the [MutableDoubleList] and returns `true` if the
     * [MutableDoubleList] was changed or `false` if [elements] was empty.
     */
    public inline fun addAll(elements: DoubleList): Boolean {
        return addAll(_size, elements)
    }

    /**
     * Adds all [elements] to the end of the [MutableDoubleList] and returns `true` if the
     * [MutableDoubleList] was changed or `false` if [elements] was empty.
     */
    public inline fun addAll(elements: DoubleArray): Boolean {
        return addAll(_size, elements)
    }

    /**
     * Adds all [elements] to the end of the [MutableDoubleList] and returns `true` if the
     * [MutableDoubleList] was changed or `false` if [elements] was empty.
     */
    public inline fun addAll(elements: List<Double>): Boolean {
        if (elements.isEmpty()) return false
        ensureCapacity(_size + elements.size)
        var curr = _size
        for (i in elements.indices) content[curr++] = elements[i]
        _size += elements.size
        return true
    }

    /**
     * Adds all [elements] to the end of the [MutableDoubleList].
     */
    public inline operator fun plusAssign(elements: DoubleList) {
        addAll(_size, elements)
    }

    /**
     * Adds all [elements] to the end of the [MutableDoubleList].
     */
    public inline operator fun plusAssign(elements: DoubleArray) {
        addAll(_size, elements)
    }

    /**
     * Removes all elements in the [MutableDoubleList]. The storage isn't released.
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
     * Ensures that there is enough space to store [capacity] elements in the [MutableDoubleList].
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
     * [add] [element] to the [MutableDoubleList].
     */
    public inline operator fun plusAssign(element: Double) {
        add(element)
    }

    /**
     * [remove] [element] from the [MutableDoubleList]
     */
    public inline operator fun minusAssign(element: Double) {
        remove(element)
    }

    /**
     * Removes [element] from the [MutableDoubleList]. If [element] was in the [MutableDoubleList]
     * and was removed, `true` will be returned, or `false` will be returned if the element
     * was not found.
     */
    public fun remove(element: Double): Boolean {
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
    private inline fun removeAll(predicate: (Double) -> Boolean): Boolean {
        val initialSize = _size
        var w = 0
        forEach { item ->
            if (!predicate(item)) content[w++] = item
        }
        _size = w
        return initialSize != _size
    }

    /**
     * Removes all [elements] from the [MutableDoubleList] and returns `true` if anything was removed.
     */
    public fun removeAll(elements: DoubleArray): Boolean {
        return removeAll { item -> elements.any { it == item } }
    }

    /**
     * Removes all [elements] from the [MutableDoubleList] and returns `true` if anything was removed.
     */
    public fun removeAll(elements: DoubleList): Boolean {
        return removeAll { item -> elements.any { it == item } }
    }

    /**
     * Removes all [elements] from the [MutableDoubleList].
     */
    public operator fun minusAssign(elements: DoubleArray) {
        removeAll(elements)
    }

    /**
     * Removes all [elements] from the [MutableDoubleList].
     */
    public operator fun minusAssign(elements: DoubleList) {
        removeAll(elements)
    }

    /**
     * Removes the element at the given [index] and returns it.
     * @throws IndexOutOfBoundsException if [index] isn't between 0 and [lastIndex], inclusive
     */
    public fun removeAt(index: Int): Double {
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
     * Keeps only [elements] in the [MutableDoubleList] and removes all other values.
     * @return `true` if the [MutableDoubleList] has changed.
     */
    public fun retainAll(elements: DoubleArray): Boolean {
        return removeAll { item -> elements.none { it == item } }
    }

    /**
     * Keeps only [elements] in the [MutableDoubleList] and removes all other values.
     * @return `true` if the [MutableDoubleList] has changed.
     */
    public fun retainAll(elements: DoubleList): Boolean {
       return removeAll { item -> elements.none { it == item } }
    }

    /**
     * Sets the value at [index] to [element].
     * @return the previous value set at [index]
     * @throws IndexOutOfBoundsException if [index] isn't between 0 and [lastIndex], inclusive
     */
    public operator fun set(
        index: Int,
        element: Double
    ): Double {
        if (index !in 0 until _size) {
            throwIndexOutOfBoundsException("Index must be between 0 and size")
        }
        val content = content
        val old = content[index]
        content[index] = element
        return old
    }

    /**
     * Sorts the [MutableDoubleList] elements in ascending order.
     */
    public fun sort() {
        if (_size == 0) return
        content.sort(fromIndex = 0, toIndex = _size)
    }

    /**
     * Sorts the [MutableDoubleList] elements in descending order.
     */
    public fun sortDescending() {
        if (_size == 0) return
        content.sortDescending(fromIndex = 0, toIndex = _size)
    }
}

/**
 * Creates a [MutableDoubleList] with the specified size, where each element is calculated by calling the specified [init] function.
 * [init] is called for each list element sequentially starting from the first one. It should return the value for a list element given its index.
 *
 * @param size The initial capacity of the list.
 * @param init Function that takes an index and returns the element to be placed at that index.
 */
public inline fun MutableDoubleList(size: Int, init: (index: Int) -> Double): MutableDoubleList {
    return MutableDoubleList(size).apply {
        for (i in 0 until size) { content[i] = init(i) }
        _size = size
    }
}

/**
 * Creates a [DoubleList] with the specified size, where each element is calculated by calling the specified [init] function.
 * [init] is called for each list element sequentially starting from the first one. It should return the value for a list element given its index.
 *
 * @param size The initial capacity of the list.
 * @param init Function that takes an index and returns the element to be placed at that index.
 */
public inline fun DoubleList(size: Int, init: (index: Int) -> Double): DoubleList = MutableDoubleList(size, init)

private val EmptyDoubleList: DoubleList = MutableDoubleList(0)

/**
 * @return a read-only [DoubleList] with nothing in it.
 */
public fun emptyDoubleList(): DoubleList = EmptyDoubleList

/**
 * @return a read-only [DoubleList] with nothing in it.
 */
public fun doubleListOf(): DoubleList = EmptyDoubleList

/**
 * @return a new read-only [DoubleList] with [element1] as the only item in the list.
 */
public fun doubleListOf(element1: Double): DoubleList = mutableDoubleListOf(element1)

/**
 * @return a new read-only [DoubleList] with 2 elements, [element1] and [element2], in order.
 */
public fun doubleListOf(element1: Double, element2: Double): DoubleList =
    mutableDoubleListOf(element1, element2)

/**
 * @return a new read-only [DoubleList] with 3 elements, [element1], [element2], and [element3],
 * in order.
 */
public fun doubleListOf(element1: Double, element2: Double, element3: Double): DoubleList =
    mutableDoubleListOf(element1, element2, element3)

/**
 * @return a new read-only [DoubleList] with [elements] in order.
 */
public fun doubleListOf(vararg elements: Double): DoubleList =
    MutableDoubleList(elements.size).apply { plusAssign(elements) }

/**
 * @return a new empty [MutableDoubleList] with the default capacity.
 */
public inline fun mutableDoubleListOf(): MutableDoubleList = MutableDoubleList()

/**
 * @return a new [MutableDoubleList] with [element1] as the only item in the list.
 */
public fun mutableDoubleListOf(element1: Double): MutableDoubleList {
    val list = MutableDoubleList(1)
    list += element1
    return list
}

/**
 * @return a new [MutableDoubleList] with 2 elements, [element1] and [element2], in order.
 */
public fun mutableDoubleListOf(element1: Double, element2: Double): MutableDoubleList {
    val list = MutableDoubleList(2)
    list += element1
    list += element2
    return list
}

/**
 * @return a new [MutableDoubleList] with 3 elements, [element1], [element2], and [element3],
 * in order.
 */
public fun mutableDoubleListOf(element1: Double, element2: Double, element3: Double): MutableDoubleList {
    val list = MutableDoubleList(3)
    list += element1
    list += element2
    list += element3
    return list
}

/**
 * @return a new [MutableDoubleList] with the given elements, in order.
 */
public inline fun mutableDoubleListOf(vararg elements: Double): MutableDoubleList =
    MutableDoubleList(elements.size).apply { plusAssign(elements) }

/**
 * Builds a new [DoubleList] by populating a [MutableDoubleList] using the given
 * [builderAction].
 *
 * The instance passed as a receiver to the [builderAction] is valid only inside that function.
 * Using it outside of the function produces an unspecified behavior.
 *
 * @param builderAction Lambda in which the [MutableDoubleList] can be populated.
 */
public inline fun buildDoubleList(
    builderAction: MutableDoubleList.() -> Unit,
): DoubleList {
    contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }
    return MutableDoubleList().apply(builderAction)
}

/**
 * Builds a new [DoubleList] by populating a [MutableDoubleList] using the given
 * [builderAction].
 *
 * The instance passed as a receiver to the [builderAction] is valid only inside that function.
 * Using it outside of the function produces an unspecified behavior.
 *
 * @param initialCapacity Hint for the expected number of elements added in the [builderAction].
 * @param builderAction Lambda in which the [MutableDoubleList] can be populated.
 */
public inline fun buildDoubleList(
    initialCapacity: Int,
    builderAction: MutableDoubleList.() -> Unit,
): DoubleList {
    contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }
    return MutableDoubleList(initialCapacity).apply(builderAction)
}

/**
 * Returns the [DoubleList] itself if it's not null; otherwise returns an empty [DoubleList].
 */
public fun DoubleList?.orEmpty(): DoubleList = this ?: emptyDoubleList()

/**
 * Serializer for [DoubleList] that handles serialization and deserialization of the list.
 */
object DoubleListSerializer : KSerializer<DoubleList> {
    @OptIn(InternalSerializationApi::class)
    override val descriptor =
        buildSerialDescriptor("kubit.collections.list.DoubleList", StructureKind.LIST)

    override fun serialize(encoder: Encoder, value: DoubleList) {
        val size = value.size
        val composite = encoder.beginCollection(descriptor, size)
        value.forEachIndexed { index, element ->
            composite.encodeDoubleElement(descriptor, index, element)
        }
        composite.endStructure(descriptor)
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun deserialize(decoder: Decoder): DoubleList {
        val composite = decoder.beginStructure(descriptor)
        val size = composite.decodeCollectionSize(descriptor)
        val items: MutableDoubleList
        if (composite.decodeSequentially()) {
            items = MutableDoubleList(size)
            for (i in 0 until size) {
                items.content[i] = composite.decodeDoubleElement(descriptor, i)
            }
            items._size = size
        } else {
            items = MutableDoubleList(if (size == -1) 16 else size)
            var index = composite.decodeElementIndex(descriptor)
            while (index != CompositeDecoder.DECODE_DONE) {
                items.add(composite.decodeDoubleElement(descriptor, index))
                index = composite.decodeElementIndex(descriptor)
            }
        }
        composite.endStructure(descriptor)
        return items
    }
}
