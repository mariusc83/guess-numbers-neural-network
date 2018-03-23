package util

import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

internal class MathUtilTest {

    @Test
    fun testMatrix() {
        val matrix = generateMatrix(5, 3, { Math.random() })
        assertEquals(5, matrix.size)
        matrix.forEach {
            assertEquals(3, it.size)
            it.forEach {
                assertTrue(it > 0 && it < 1)
            }
        }

    }

}