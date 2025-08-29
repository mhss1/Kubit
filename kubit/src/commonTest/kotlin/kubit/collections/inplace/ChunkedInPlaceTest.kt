package kubit.collections.inplace

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFailsWith

class ChunkedInPlaceTest {

    @Test
    fun `empty list returns empty chunked list`() {
        val list = emptyList<Int>().chunkedInPlace(1)
        assertTrue(list.isEmpty())
        assertEquals(0, list.size)
    }

    @Test
    fun `list smaller than chunk size returns one chunk`() {
        val sourceList = listOf(1, 2, 3)
        val list = sourceList.chunkedInPlace(5)
        assertEquals(1, list.size)
        assertEquals(sourceList, list[0])
    }

    @Test
    fun `list divisible by chunk size returns correct chunks`() {
        val sourceList = List(120) { it }
        val list = sourceList.chunkedInPlace(12)
        assertEquals(10, list.size)
        for (i in 0 until 10) {
            assertEquals(List(12) { i * 12 + it }, list[i])
        }
    }

    @Test
    fun `list not divisible by chunk size returns correct chunks`() {
        val sourceList = List(120) { it }
        val list = sourceList.chunkedInPlace(19)
        assertEquals(7, list.size)

        for (i in 0 until  6) {
            assertEquals(List(19) { i * 19 + it }, list[i])
        }

        assertEquals(List(120 % 19) { 6 * 19 + it }, list[6])
    }

    @Test
    fun `chunk size of 1 returns list of lists with single elements`() {
        val sourceList = List(65) { it }
        val list = sourceList.chunkedInPlace(1)
        assertEquals(65, list.size)
        for (i in 0 until 65) {
            assertEquals(listOf(i), list[i])
        }
    }

    @Test
    fun `accessing index out of bounds throws exception`() {
        val sourceList = List(53) { it }
        val list = sourceList.chunkedInPlace(12)
        assertFailsWith<IndexOutOfBoundsException> { list[-1] }
        assertFailsWith<IndexOutOfBoundsException> { list[5] }
    }

    @Test
    fun `zero chunk size throws exception`() {
        assertFailsWith<IllegalArgumentException> { listOf(1, 2, 3, 4, 5, 6, 7, 8).chunkedInPlace(0) }
    }

    @Test
    fun `fastForEach iterates over all sublists`() {
        val sourceList = listOf(1, 2, 3, 4, 5, 6, 7)
        val list = sourceList.chunkedInPlace(2)
        val collectedChunks = mutableListOf<List<Int>>()
        list.fastForEach { chunk -> collectedChunks.add(chunk) }
        assertEquals(listOf(listOf(1, 2), listOf(3, 4), listOf(5, 6), listOf(7)), collectedChunks)
    }

    @Test
    fun `fastForEachIndexed iterates with correct indices`() {
        val sourceList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
        val list = sourceList.chunkedInPlace(2)
        val chunked = sourceList.chunked(2)
        list.fastForEachIndexed { i, subList ->
            assertEquals(chunked[i], subList)
        }
    }

    @Test
    fun `forEachChunk iterates on the correct chunks`() {
        val sourceList = List(120) { it }
        val chunked = sourceList.chunked(9)
        var i = 0
        sourceList.forEachChunk(9) { chunk ->
            assertEquals(chunk, chunked[i])
            i++
        }
    }

    @Test
    fun `forEachChunk empty list does nothing`() {
        val emptyList = emptyList<Int>()
        var count = 0
        emptyList.forEachChunk(2) {
            count++
        }
        assertEquals(0, count)
    }

    @Test
    fun `forEachChunk list divisible by chunk size`() {
        val list = listOf(1, 2, 3, 4)
        val chunks = mutableListOf<List<Int>>()
        list.forEachChunk(2) {
            chunks.add(it.toList())
        }
        assertEquals(2, chunks.size)
        assertEquals(listOf(1, 2), chunks[0])
        assertEquals(listOf(3, 4), chunks[1])
    }


    @Test
    fun `forEachChunk list not divisible by chunk size`() {
        val list = listOf(1, 2, 3, 4, 5)
        val chunks = mutableListOf<List<Int>>()
        list.forEachChunk(2) {
            chunks.add(it.toList())
        }
        assertEquals(3, chunks.size)
        assertEquals(listOf(1, 2), chunks[0])
        assertEquals(listOf(3, 4), chunks[1])
        assertEquals(listOf(5), chunks[2])
    }

    @Test
    fun `forEachChunk chunk size of 1`() {
        val list = listOf(1, 2, 3, 4, 5)
        val chunks = mutableListOf<List<Int>>()
        list.forEachChunk(1) {
            chunks.add(it.toList())
        }
        assertEquals(5, chunks.size)
        assertEquals(listOf(1), chunks[0])
        assertEquals(listOf(2), chunks[1])
        assertEquals(listOf(3), chunks[2])
        assertEquals(listOf(4), chunks[3])
        assertEquals(listOf(5), chunks[4])
    }

    @Test
    fun `forEachChunk chunk size larger than list size`() {
        val list = listOf(1, 2, 3)
        val chunks = mutableListOf<List<Int>>()
        list.forEachChunk(5) {
            chunks.add(it.toList())
        }
        assertEquals(1, chunks.size)
        assertEquals(listOf(1, 2, 3), chunks[0])
    }

    @Test
    fun `forEachChunk throws exception for invalid chunk size`() {
        val list = listOf(1, 2, 3)
        assertFailsWith<IllegalArgumentException> {
            list.forEachChunk(0) { }
        }
        assertFailsWith<IllegalArgumentException> {
            list.forEachChunk(-1) { }
        }
    }

    @Test
    fun `forEachChunkIndexed iterates on the correct chunks`() {
        val sourceList = List(120) { it }
        val chunked = sourceList.chunked(9)
        sourceList.forEachChunkIndexed(9) { i, chunk ->
            assertEquals(chunk, chunked[i])
        }
    }

    @Test
    fun `forEachChunkIndexed chunk size of 1`() {
        val list = listOf(0, 1, 2, 3, 4, 5)
        list.forEachChunkIndexed(1) { i, chunk ->
            assertEquals(listOf(i), chunk)
        }
    }

    @Test
    fun `forEachChunkIndexed throws exception for invalid chunk size`() {
        val list = listOf(0, 1, 2)
        assertFailsWith<IllegalArgumentException> {
            list.forEachChunkIndexed(0) { _, _ -> }
        }
        assertFailsWith<IllegalArgumentException> {
            list.forEachChunkIndexed(-1) { _, _ -> }
        }
    }

}
