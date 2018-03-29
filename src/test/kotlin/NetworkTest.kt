/*
import org.junit.Test
import org.junit.Assert.assertTrue
import util.print

class NetworkTest {

    val sigmoid = { currentValue: Double ->
        1 / (1 + Math.pow(Math.E, -currentValue))
    }
    val limitHidden = Math.pow(100.0, -0.5)
    val limitOutput = Math.pow(10.0, -0.5)

    @Test
    fun networkWeights_willAlwaysBe_inBetween_givenLimits() {

        val network = Network(784,100, 10, sigmoid, 0.5f)

        network.wih.forEach {
            it.forEach {
                println(it)
                assertTrue(it > -limitHidden && it < limitHidden)
            }
        }

        network.who.forEach {
            it.forEach {
                println(it)
                assertTrue(it > -limitOutput && it < limitOutput)
            }
        }
    }

    @Test
    fun whenTrainingTheNetwork_theOutputsWillBeGenerated() {
        val network = Network(3,100, 3, sigmoid, 0.5f)

        network.train(arrayOf(1.0, 0.5, -1.5), arrayOf(0.3, 0.5, 0.6))

        assertTrue(network.oHidden.size == 100)
        assertTrue(network.oOutput.size == 3)
        assertTrue(network.wih.size == 100)
        assertTrue(network.who.size == 3)
        println("adjusted hidden weights")
        network.wih.print()
        println("adjusted output weights")
        network.who.print()
    }
}*/
