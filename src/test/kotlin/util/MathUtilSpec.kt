//package util
//
//import org.jetbrains.spek.api.Spek
//import org.jetbrains.spek.api.dsl.context
//import org.jetbrains.spek.api.dsl.describe
//import org.jetbrains.spek.api.dsl.it
//import org.junit.Assert.assertEquals
//import org.junit.Assert.assertTrue
//
//object MathUtilSpec : Spek({
//
//    describe("Matrix Generation") {
//        context("when generating a double matrix") {
//            it("will return the expected result") {
//                var matrix = generateMatrix(5, 3, { Math.random() })
//                assertEquals(5, matrix.size)
//                matrix.forEach {
//                    assertEquals(3, it.size)
//                    it.forEach {
//                        assertTrue(it > 0 && it < 1)
//                    }
//                }
//            }
//        }
//    }
//})