import org.junit.Test
import org.junit.Assert.assertTrue
import util.print

class NetworkTest {

    val sigmoid = { currentValue: Double ->
        1 / (1 + Math.pow(Math.E, -currentValue))
    }
    val limit = Math.pow(3.0, -0.5)

    @Test
    fun networkWeights_willAlwaysBe_inBetween_givenLimits() {

        val network = Network(3, sigmoid, 0.5f)

        network.who.forEach {
            it.forEach {
                println(it)
                assertTrue(it > -limit && it < limit)
            }
        }

        network.wih.forEach {
            it.forEach {
                println(it)
                assertTrue(it > -limit && it < limit)
            }
        }
    }

    @Test
    fun whenQueryingTheNetwork_theOutputsWillBeGenerated() {
        val network = Network(3, sigmoid, 0.5f)

        network.query(arrayOf(1.0, 0.5, -1.5))

        assertTrue(network.oHidden.size == 3)
        assertTrue(network.oOutput.size == 3)
        println("Hidden layer output:")
        network.oHidden.print()
        println("Output layer output:")
        network.oOutput.print()
    }

    @Test
    fun whenTrainingTheNetwork_theOutputsWillBeGenerated() {
        val network = Network(3, sigmoid, 0.5f)

        network.train(arrayOf(1.0, 0.5, -1.5), arrayOf(0.3, 0.5, 0.6))

        assertTrue(network.oHidden.size == 3)
        assertTrue(network.oOutput.size == 3)
        assertTrue(network.wih.size == 3)
        assertTrue(network.who.size == 3)
        assertTrue(network.wih.size == 3)
        assertTrue(network.who.size == 3)
        println("adjusted hidden weights")
        network.wih.print()
        println("adjusted output weights")
        network.who.print()
    }
}