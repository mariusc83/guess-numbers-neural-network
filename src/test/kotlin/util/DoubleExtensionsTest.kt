package util

import org.junit.Assert.assertEquals
import org.junit.Test

class DoubleExtensionsTest {

    @Test
    fun testDoubleMatrixSubstraction() {
        val a = Array(3, {
            Array(4, {
                it.toDouble()
            })
        })
        a.print()
        val toSubstractFrom = 5.0
        val b = toSubstractFrom - a
        assertEquals(3, b.size)
        b.forEachIndexed { outerIndex, outerD ->
            outerD.forEachIndexed { index, d ->
                if (index == outerIndex) {
                    assertEquals(toSubstractFrom - index, d, 0.0)
                }
            }
        }
        b.print()
    }
}