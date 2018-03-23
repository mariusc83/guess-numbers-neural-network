package util

import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

class ArrayExtensionsTest {

    @Test
    fun testMatrixMultiplication() {

        val a = Array(3, {
            Array(3, {
                it.toDouble()
            })
        })
        println("current a:")
        a.print()

        val b = Array(3) {
            Array(2) { it.toDouble() }
        }

        println("current b:")
        b.print()


        val multiplied = a * b
        println("Multiplied:")
        multiplied?.print()

    }

    @Test
    fun testMatrixMultiplication2() {

        val a = Array(3, {
            Array(1, {
                it.toDouble()
            })
        })
        println("current a:")
        a.print()

        val b = Array(1) {
            Array(3) { it.toDouble() }
        }

        println("current b:")
        b.print()


        val multiplied = a * b
        println("Multiplied:")
        multiplied?.print()

    }

    @Test
    fun testMatrixScalarMultiplication() {

        val a = Array(3, {
            Array(3, {
                it.toDouble()
            })
        })
        println("current a:")
        a.print()

        val b = Array(3) {
            Array(3) { it.toDouble() }
        }

        println("current b:")
        b.print()


        val multiplied = a.scalarMultiplication(b)
        println("Multiplied:")
        multiplied.print()

    }

    @Test
    fun testArrayToMatrixTransposed() {
        val a = arrayOf(0, 1, 2, 3)
        val transposed = a.transposed()

        transposed.forEachIndexed { index, subarray ->
            assertEquals(index, subarray[0])
        }

        transposed.print()
    }

    @Test
    fun testMatrixTransposed() {
        val a = Array(3, {
            Array(4, {
                it.toDouble()
            })
        })

        val b = a.transposed()
        assertEquals(4, b.size)
        b.print()
    }

    @Test
    fun testMapper() {
        val a = Array(3, {
            Array(3, {
                it.toDouble()
            })
        })
        println("current a:")
        a.print()
        val transformed = a.map {
            it * 2
        }
        println("transformed a:")
        transformed.print()
        transformed.forEach {
            it.forEachIndexed { index, d ->
                assertEquals(index.toDouble() * 2, d, 0.0)
            }
        }
    }

    @Test
    fun testMatrixDifference() {
        val a = Array(3, {
            Array(3, {
                it.toDouble()
            })
        })
        val b = Array(3, {
            Array(3, {
                it.toDouble()
            })
        })
        val c = (a - b)!!
        c.forEach {
            it.forEach {
                assertEquals(0.0, it, 0.0)
            }
        }
    }
}