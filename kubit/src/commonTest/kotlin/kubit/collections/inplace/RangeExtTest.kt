package kubit.collections.inplace

import kubit.collections.range.fastSum
import kubit.collections.range.median
import kubit.collections.range.fastAverage
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith


class RangeExtTest {

    @Test
    fun `fast sum of empty range`() {
        assertEquals(0, IntRange.EMPTY.fastSum())
    }

    @Test
    fun `fast sum of single element range`() {
        assertEquals(5, (5..5).fastSum())
    }

    @Test
    fun `fast sum of positive range`() {
        assertEquals(15, (1..5).fastSum())
    }

    @Test
    fun `fast sum of negative range`() {
        assertEquals(-15, (-5..-1).fastSum())
    }

    @Test
    fun `fast sum of range spanning zero`() {
        assertEquals(0, (-2..2).fastSum())
    }

    @Test
    fun `fast sum of large positive range`() {
        assertEquals(500500, (1..1000).fastSum())
    }

    @Test
    fun `fast sum of large negative range`() {
        assertEquals(-500500, (-1000..-1).fastSum())
    }

    @Test
    fun `median of single element range`() {
        assertEquals(5.0, (5..5).median())
    }

    @Test
    fun `median of odd elements range`() {
        assertEquals(3.0, (1..5).median())
    }

    @Test
    fun `median of even elements range`() {
        assertEquals(3.5, (1..6).median())
    }

    @Test
    fun `median of negative range with odd elements`() {
        assertEquals(-3.0, (-5..-1).median())
    }

    @Test
    fun `median of negative range with even elements`() {
        assertEquals(-3.5, (-6..-1).median())
    }

    @Test
    fun `median of range spanning zero with odd elements`() {
        assertEquals(0.0, (-2..2).median())
    }

    @Test
    fun `median of range spanning zero with even elements`() {
        assertEquals(0.5, (-2..3).median())
    }

    @Test
    fun `median of large range with odd elements`() {
        assertEquals(500.0, (1..999).median())
    }

    @Test
    fun `median of large range with even elements`() {
        assertEquals(500.5, (1..1000).median())
    }

    @Test
    fun `median of empty range throws exception`() {
        assertFailsWith<ArithmeticException> {
            IntRange.EMPTY.median()
        }
    }

    @Test
    fun `fast average of single element range`() {
        assertEquals(5.0, (5..5).fastAverage())
    }

    @Test
    fun `fast average of positive range`() {
        assertEquals(3.0, (1..5).fastAverage())
    }

    @Test
    fun `fast average of negative range`() {
        assertEquals(-3.0, (-5..-1).fastAverage())
    }

    @Test
    fun `fast average of range spanning zero`() {
        assertEquals(0.0, (-2..2).fastAverage())
    }

    @Test
    fun `fast average of large positive range`() {
        assertEquals(500.5, (1..1000).fastAverage())
    }

    @Test
    fun `fast average of large negative range`() {
        assertEquals(-500.5, (-1000..-1).fastAverage())
    }

    @Test
    fun `fast average of empty range returns 0`() {
        assertEquals(0.0, IntRange.EMPTY.fastAverage())
    }

}
