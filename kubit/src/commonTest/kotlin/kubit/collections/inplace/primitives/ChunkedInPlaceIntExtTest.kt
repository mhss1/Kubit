package kubit.collections.inplace.primitives

import kubit.collections.list.*
import kotlin.test.*

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// DO NOT MAKE CHANGES to this kotlin file.
// This file was generated from a template in the template directory
// to ensure the change is available on all versions of the function.
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

class ChunkedInPlaceIntTest {

    private fun seqList(size: Int): IntList = MutableIntList(size) { (it + 1).toInt() }

    private fun subListToStdList(sub: InPlaceIntSubList): List<Int> {
        val out = mutableListOf<Int>()
        sub.forEach { out.add(it) }
        return out
    }

    @Test
    fun `empty list returns empty chunked list`() {
        val list = emptyIntList().chunkedInPlace(1)
        assertTrue(list.isEmpty())
        assertEquals(0, list.size)
    }

    @Test
    fun `list smaller than chunk size returns one chunk`() {
        val sourceList = seqList(3)
        val list = sourceList.chunkedInPlace(5)
        assertEquals(1, list.size)
        assertEquals(listOf(1, 2, 3), subListToStdList(list[0]))
    }

    @Test
    fun `list divisible by chunk size returns correct chunks`() {
        val sourceList = seqList(120)
        val list = sourceList.chunkedInPlace(12)
        assertEquals(10, list.size)
        for (i in 0 until 10) {
            val expected = MutableList(12) { (i * 12 + it + 1).toInt() }
            assertEquals(expected, subListToStdList(list[i]))
        }
    }

    @Test
    fun `list not divisible by chunk size returns correct chunks`() {
        val sourceList = seqList(120)
        val list = sourceList.chunkedInPlace(19)
        assertEquals(7, list.size)

        for (i in 0 until 6) {
            val expected = MutableList(19) { (i * 19 + it + 1).toInt() }
            assertEquals(expected, subListToStdList(list[i]))
        }

        val expected = MutableList(120 % 19) { (6 * 19 + it + 1).toInt() }
        assertEquals(expected, subListToStdList(list[6]))
    }

    @Test
    fun `chunk size of 1 returns list of lists with single elements`() {
        val sourceList = seqList(65)
        val list = sourceList.chunkedInPlace(1)
        assertEquals(65, list.size)
        for (i in 0 until 65) {
            assertEquals(listOf((i + 1).toInt()), subListToStdList(list[i]))
        }
    }

    @Test
    fun `accessing index out of bounds throws exception`() {
        val sourceList = seqList(53)
        val list = sourceList.chunkedInPlace(12)
        assertFailsWith<IndexOutOfBoundsException> { list[-1] }
        assertFailsWith<IndexOutOfBoundsException> { list[5] }
    }

    @Test
    fun `zero chunk size throws exception`() {
        assertFailsWith<IllegalArgumentException> { seqList(8).chunkedInPlace(0) }
    }

    @Test
    fun `fastForEach iterates over all sublists`() {
        val sourceList = seqList(7)
        val list = sourceList.chunkedInPlace(2)
        val collectedChunks = mutableListOf<List<Int>>()
        list.fastForEach { chunk -> collectedChunks.add(subListToStdList(chunk)) }
        assertEquals(
            listOf(
                listOf(1, 2),
                listOf(3, 4),
                listOf(5, 6),
                listOf(7)
            ),
            collectedChunks
        )
    }

    @Test
    fun `fastForEachIndexed iterates with correct indices`() {
        val sourceList = seqList(11)
        val list = sourceList.chunkedInPlace(2)
        val expectedChunks = mutableListOf<List<Int>>()
        sourceList.forEachChunk(2) { chunk -> expectedChunks.add(subListToStdList(chunk)) }
        list.fastForEachIndexed { i, subList ->
            assertEquals(expectedChunks[i], subListToStdList(subList))
        }
    }

    @Test
    fun `forEachChunk iterates on the correct chunks`() {
        val sourceList = seqList(120)
        var chunkIndex = 0
        sourceList.forEachChunk(9) { chunk ->
            val start = chunkIndex * 9 + 1
            val size = minOf(9, 120 - chunkIndex * 9)
            val expected = MutableList(size) { (start + it).toInt() }
            assertEquals(expected, subListToStdList(chunk))
            chunkIndex++
        }
    }

    @Test
    fun `forEachChunk empty list does nothing`() {
        val empty = emptyIntList()
        var count = 0
        empty.forEachChunk(2) { count++ }
        assertEquals(0, count)
    }

    @Test
    fun `forEachChunk list divisible by chunk size`() {
        val list = seqList(4)
        val chunks = mutableListOf<List<Int>>()
        list.forEachChunk(2) { chunks.add(subListToStdList(it)) }
        assertEquals(2, chunks.size)
        assertEquals(listOf(1, 2), chunks[0])
        assertEquals(listOf(3, 4), chunks[1])
    }

    @Test
    fun `forEachChunk list not divisible by chunk size`() {
        val list = seqList(5)
        val chunks = mutableListOf<List<Int>>()
        list.forEachChunk(2) { chunks.add(subListToStdList(it)) }
        assertEquals(3, chunks.size)
        assertEquals(listOf(1, 2), chunks[0])
        assertEquals(listOf(3, 4), chunks[1])
        assertEquals(listOf(5), chunks[2])
    }

    @Test
    fun `forEachChunk chunk size of 1`() {
        val list = seqList(5)
        val chunks = mutableListOf<List<Int>>()
        list.forEachChunk(1) { chunks.add(subListToStdList(it)) }
        assertEquals(5, chunks.size)
        assertEquals(listOf(1), chunks[0])
        assertEquals(listOf(2), chunks[1])
        assertEquals(listOf(3), chunks[2])
        assertEquals(listOf(4), chunks[3])
        assertEquals(listOf(5), chunks[4])
    }

    @Test
    fun `forEachChunk chunk size larger than list size`() {
        val list = seqList(3)
        val chunks = mutableListOf<List<Int>>()
        list.forEachChunk(5) { chunks.add(subListToStdList(it)) }
        assertEquals(1, chunks.size)
        assertEquals(listOf(1, 2, 3), chunks[0])
    }

    @Test
    fun `forEachChunk throws exception for invalid chunk size`() {
        val list = seqList(3)
        assertFailsWith<IllegalArgumentException> { list.forEachChunk(0) { } }
        assertFailsWith<IllegalArgumentException> { list.forEachChunk(-1) { } }
    }

    @Test
    fun `forEachChunkIndexed iterates on the correct chunks`() {
        val sourceList = seqList(120)
        sourceList.forEachChunkIndexed(9) { i, chunk ->
            val start = i * 9 + 1
            val size = minOf(9, 120 - i * 9)
            val expected = MutableList(size) { (start + it).toInt() }
            assertEquals(expected, subListToStdList(chunk))
        }
    }

    @Test
    fun `forEachChunkIndexed chunk size of 1`() {
        val list = seqList(6)
        list.forEachChunkIndexed(1) { i, chunk ->
            assertEquals(listOf((i + 1).toInt()), subListToStdList(chunk))
        }
    }

    @Test
    fun `forEachChunkIndexed throws exception for invalid chunk size`() {
        val list = seqList(3)
        assertFailsWith<IllegalArgumentException> { list.forEachChunkIndexed(0) { _, _ -> } }
        assertFailsWith<IllegalArgumentException> { list.forEachChunkIndexed(-1) { _, _ -> } }
    }
}
