import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue


class TrainingDataRepoTest_WhenDataFileIsValid {
    lateinit var underTest: TrainingDataRepo
    val validFileName = "mnist_train_100.txt"

    @Before
    fun setUp() {
        underTest = TrainingDataRepo()
    }

    @Test
    fun returns_the_right_amount_of_data() {
        val trainingData = underTest.fetchTrainingData(validFileName)

        assertEquals(100, trainingData!!.size)
        trainingData.forEach {
            val (input, output) = it
            assertEquals(784, input.size)
            assertEquals(10, output.size)
            output.forEach {
                assertTrue(it >= 0.01 && it <= 0.99)
            }
            input.forEach {
                assertTrue(it >= 0.01 && it <= 1)
            }
        }
    }

    @Test
    fun returns_the_right_amount_of_data_as_sequence() {
        underTest.dataStream(validFileName)
                ?.consumeAsSequence {
                    val trainingData = it.toList()
                    assertEquals(100, trainingData.size)
                    trainingData.forEach {
                        val (input, output) = it
                        assertEquals(784, input.size)
                        assertEquals(10, output.size)
                        output.forEach {
                            assertTrue(it >= 0.01 && it <= 0.99)
                        }
                        input.forEach {
                            assertTrue(it >= 0.01 && it <= 1)
                        }
                    }
                }

    }

}