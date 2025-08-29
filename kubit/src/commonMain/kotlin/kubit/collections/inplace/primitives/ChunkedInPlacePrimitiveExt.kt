package kubit.collections.inplace.primitives

import kubit.collections.list.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a script in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

/**
 * Splits this list into a list of [InPlaceIntSubList] each not exceeding the given [chunkSize].
 * The last list may have less elements than the given [chunkSize].
 *
 * Each chunk is a view into the original list. Changes in the original list will be reflected in the chunks.
 *
 * @param chunkSize the number of elements to take in each window.
 */
fun IntList.chunkedInPlace(chunkSize: Int): WindowedIntList {
    return windowedInPlace(chunkSize, chunkSize, true)
}

/**
 * Iterates through the windowed chunks of this collection with given [chunkSize],
 * where window step is equals to [chunkSize] and calls [action] on each chunk.
 *
 * The returned chunk is a view into the original list. Changes in the original list will be reflected in the chunk.
 *
 * @param chunkSize the number of elements to take in each window.
 * @param action function that takes the chunk as an argument.
 */
inline fun IntList.forEachChunk(chunkSize: Int, action: (InPlaceIntSubList) -> Unit) {
    forEachWindow(chunkSize, chunkSize, true, action)
}

/**
 * Iterates through the windowed chunks of this collection with given [chunkSize],
 * where window step is equals to [chunkSize] and calls [action] on each chunk with its index.
 *
 * The returned chunk is a view into the original list. Changes in the original list will be reflected in the chunk.
 *
 * @param chunkSize the number of elements to take in each window.
 * @param action function that takes the index of a chunk and the chunk itself as arguments.
 */
inline fun IntList.forEachChunkIndexed(chunkSize: Int, action: (index: Int, InPlaceIntSubList) -> Unit) {
    var index = 0
    forEachChunk(chunkSize) { chunk ->
        action(index++, chunk)
    }
}

/**
 * Splits this list into a list of [InPlaceLongSubList] each not exceeding the given [chunkSize].
 * The last list may have less elements than the given [chunkSize].
 *
 * Each chunk is a view into the original list. Changes in the original list will be reflected in the chunks.
 *
 * @param chunkSize the number of elements to take in each window.
 */
fun LongList.chunkedInPlace(chunkSize: Int): WindowedLongList {
    return windowedInPlace(chunkSize, chunkSize, true)
}

/**
 * Iterates through the windowed chunks of this collection with given [chunkSize],
 * where window step is equals to [chunkSize] and calls [action] on each chunk.
 *
 * The returned chunk is a view into the original list. Changes in the original list will be reflected in the chunk.
 *
 * @param chunkSize the number of elements to take in each window.
 * @param action function that takes the chunk as an argument.
 */
inline fun LongList.forEachChunk(chunkSize: Int, action: (InPlaceLongSubList) -> Unit) {
    forEachWindow(chunkSize, chunkSize, true, action)
}

/**
 * Iterates through the windowed chunks of this collection with given [chunkSize],
 * where window step is equals to [chunkSize] and calls [action] on each chunk with its index.
 *
 * The returned chunk is a view into the original list. Changes in the original list will be reflected in the chunk.
 *
 * @param chunkSize the number of elements to take in each window.
 * @param action function that takes the index of a chunk and the chunk itself as arguments.
 */
inline fun LongList.forEachChunkIndexed(chunkSize: Int, action: (index: Int, InPlaceLongSubList) -> Unit) {
    var index = 0
    forEachChunk(chunkSize) { chunk ->
        action(index++, chunk)
    }
}

/**
 * Splits this list into a list of [InPlaceFloatSubList] each not exceeding the given [chunkSize].
 * The last list may have less elements than the given [chunkSize].
 *
 * Each chunk is a view into the original list. Changes in the original list will be reflected in the chunks.
 *
 * @param chunkSize the number of elements to take in each window.
 */
fun FloatList.chunkedInPlace(chunkSize: Int): WindowedFloatList {
    return windowedInPlace(chunkSize, chunkSize, true)
}

/**
 * Iterates through the windowed chunks of this collection with given [chunkSize],
 * where window step is equals to [chunkSize] and calls [action] on each chunk.
 *
 * The returned chunk is a view into the original list. Changes in the original list will be reflected in the chunk.
 *
 * @param chunkSize the number of elements to take in each window.
 * @param action function that takes the chunk as an argument.
 */
inline fun FloatList.forEachChunk(chunkSize: Int, action: (InPlaceFloatSubList) -> Unit) {
    forEachWindow(chunkSize, chunkSize, true, action)
}

/**
 * Iterates through the windowed chunks of this collection with given [chunkSize],
 * where window step is equals to [chunkSize] and calls [action] on each chunk with its index.
 *
 * The returned chunk is a view into the original list. Changes in the original list will be reflected in the chunk.
 *
 * @param chunkSize the number of elements to take in each window.
 * @param action function that takes the index of a chunk and the chunk itself as arguments.
 */
inline fun FloatList.forEachChunkIndexed(chunkSize: Int, action: (index: Int, InPlaceFloatSubList) -> Unit) {
    var index = 0
    forEachChunk(chunkSize) { chunk ->
        action(index++, chunk)
    }
}

/**
 * Splits this list into a list of [InPlaceDoubleSubList] each not exceeding the given [chunkSize].
 * The last list may have less elements than the given [chunkSize].
 *
 * Each chunk is a view into the original list. Changes in the original list will be reflected in the chunks.
 *
 * @param chunkSize the number of elements to take in each window.
 */
fun DoubleList.chunkedInPlace(chunkSize: Int): WindowedDoubleList {
    return windowedInPlace(chunkSize, chunkSize, true)
}

/**
 * Iterates through the windowed chunks of this collection with given [chunkSize],
 * where window step is equals to [chunkSize] and calls [action] on each chunk.
 *
 * The returned chunk is a view into the original list. Changes in the original list will be reflected in the chunk.
 *
 * @param chunkSize the number of elements to take in each window.
 * @param action function that takes the chunk as an argument.
 */
inline fun DoubleList.forEachChunk(chunkSize: Int, action: (InPlaceDoubleSubList) -> Unit) {
    forEachWindow(chunkSize, chunkSize, true, action)
}

/**
 * Iterates through the windowed chunks of this collection with given [chunkSize],
 * where window step is equals to [chunkSize] and calls [action] on each chunk with its index.
 *
 * The returned chunk is a view into the original list. Changes in the original list will be reflected in the chunk.
 *
 * @param chunkSize the number of elements to take in each window.
 * @param action function that takes the index of a chunk and the chunk itself as arguments.
 */
inline fun DoubleList.forEachChunkIndexed(chunkSize: Int, action: (index: Int, InPlaceDoubleSubList) -> Unit) {
    var index = 0
    forEachChunk(chunkSize) { chunk ->
        action(index++, chunk)
    }
}
