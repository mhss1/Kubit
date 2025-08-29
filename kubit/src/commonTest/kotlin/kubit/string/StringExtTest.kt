package kubit.string

import kotlin.test.Test
import kotlin.test.assertEquals

class StringExtTest {

    @Test
    fun `countWords with default separator`() {
        val input = "This is a test string"
        val expected = 5
        assertEquals(expected, input.countWords())
    }

    @Test
    fun `countWords with custom separator`() {
        val input = "This,is,a,test"
        val expected = 4
        assertEquals(expected, input.countWords { it == ',' })
    }

    @Test
    fun `countWords with no words`() {
        val input = "    "
        val expected = 0
        assertEquals(expected, input.countWords())
    }

    @Test
    fun `countWords with single word`() {
        val input = "word"
        val expected = 1
        assertEquals(expected, input.countWords())
    }

    @Test
    fun `countWords with double separator`() {
        val input = "word  word"
        val expected = 2
        assertEquals(expected, input.countWords())
    }

    @Test
    fun `countWords with spaces at the beginning and end`() {
        val input = "   word  "
        val expected = 1
        assertEquals(expected, input.countWords())
    }

    @Test
    fun `forEachSplit basic test`() {
        val input = "a,b,c"
        val expected = listOf("a", "b", "c")
        val actual = mutableListOf<String>()
        input.forEachSplit(',') { actual.add(it.toString()) }
        assertEquals(expected, actual)
    }

    @Test
    fun `forEachSplit empty string`() {
        val input = ""
        val expected = listOf("")
        val actual = mutableListOf<String>()
        input.forEachSplit(',') { actual.add(it.toString()) }
        assertEquals(expected, actual)
    }

    @Test
    fun `forEachSplit no delimiter`() {
        val input = "abc"
        val expected = listOf("abc")
        val actual = mutableListOf<String>()
        input.forEachSplit(',') { actual.add(it.toString()) }
        assertEquals(expected, actual)
    }

    @Test
    fun `forEachSplit starts with delimiter`() {
        val input = ",a,b"
        val expected = listOf("", "a", "b")
        val actual = mutableListOf<String>()
        input.forEachSplit(',') { actual.add(it.toString()) }
        assertEquals(expected, actual)
    }

    @Test
    fun `forEachSplit ends with delimiter`() {
        val input = "a,b,"
        val expected = listOf("a", "b", "")
        val actual = mutableListOf<String>()
        input.forEachSplit(',') { actual.add(it.toString()) }
        assertEquals(expected, actual)
    }

    @Test
    fun `forEachSplit consecutive delimiters`() {
        val input = "a,,b"
        val expected = listOf("a", "", "b")
        val actual = mutableListOf<String>()
        input.forEachSplit(',') { actual.add(it.toString()) }
        assertEquals(expected, actual)
    }
}