package kubit.collections.inplace

typealias ChunkedList<T> = WindowedList<T>

/**
 * Splits this list into a list of [InPlaceSubList] each not exceeding the given [chunkSize].
 * The last list may have less elements than the given [chunkSize].
 *
 * Each chunk is a view into the original list. Changes in the original list will be reflected in the chunks.
 *
 * @param chunkSize the number of elements to take in each window.
 */
fun <T> List<T>.chunkedInPlace(chunkSize: Int): ChunkedList<T> {
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
inline fun <T> List<T>.forEachChunk(chunkSize: Int, action: (InPlaceSubList<T>) -> Unit) {
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
inline fun <T> List<T>.forEachChunkIndexed(chunkSize: Int, action: (index: Int, InPlaceSubList<T>) -> Unit) {
    var index = 0
    forEachChunk(chunkSize) { chunk ->
        action(index++, chunk)
    }
}