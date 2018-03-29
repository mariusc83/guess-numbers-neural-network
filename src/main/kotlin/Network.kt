import util.*

class Network(val inputNodes: Int,
              val hiddenNodes: Int,
              val outputNodes: Int,
              val sigmoidFunction: (Double) -> Double,
              val learningRate: Float) {

    var wih: Array<Array<Double>>
    var who: Array<Array<Double>>

    init {
        val limitHidden = Math.pow(hiddenNodes.toDouble(), -0.5)
        val limitOutput = Math.pow(outputNodes.toDouble(), -0.5)
        wih = generateMatrix(hiddenNodes, inputNodes, weightProducer(limitHidden))
        who = generateMatrix(outputNodes, hiddenNodes, weightProducer(limitOutput))
    }

    fun weightProducer(limit: Double): () -> Double {
        return {
            -limit + ((2 * limit) * Math.random())
        }
    }

    fun train(inputList: Array<Double>, expectedOutputList: Array<Double>) {
        val input = inputList.transposed()
        val (outputHidden, outputFinal) = query(input)
        val expectedOutput = expectedOutputList.transposed()
        val errorsOutput = (expectedOutput - outputFinal)!!
        val errorsHidden = (who.transposed() * errorsOutput)!!


        val outputScm1 = errorsOutput.scalarMultiplication(outputFinal)
        val outputScm2 = 1.0 - outputFinal
        val t1 = ((outputScm1.scalarMultiplication(outputScm2)) * outputHidden.transposed())!!.map { it * learningRate }
        who = (who + t1)!!

        val hiddenScm1 = errorsHidden.scalarMultiplication(outputHidden)
        val hiddenScm2 = 1.0 - outputHidden
        val t2 = ((hiddenScm1.scalarMultiplication(hiddenScm2)) * input.transposed())!!.map { it * learningRate }
        wih = (wih + t2)!!
    }

    fun query(input: Array<Array<Double>>): Pair<Array<Array<Double>>, Array<Array<Double>>> {
        val xHidden: Array<Array<Double>>
        val oHidden: Array<Array<Double>>
        val xOutput: Array<Array<Double>>
        val oOutput: Array<Array<Double>>

        xHidden = (wih * input)!!
        oHidden = xHidden.map(sigmoidFunction)
        xOutput = (who * oHidden)!!
        oOutput = xOutput.map(sigmoidFunction)
        return Pair(oHidden, oOutput)
    }
}