import util.*

class Network(val nodes: Int,
              val sigmoidFunction: (Double) -> Double,
              val learningRate: Float) {

    var wih: Array<Array<Double>>
    var who: Array<Array<Double>>
    lateinit var xHidden: Array<Array<Double>>
    lateinit var oHidden: Array<Array<Double>>
    lateinit var xOutput: Array<Array<Double>>
    lateinit var oOutput: Array<Array<Double>>

    init {
        val limit = 1 - Math.pow(nodes.toDouble(), -0.5)
        wih = generateMatrix(nodes, nodes, { Math.random() - limit })
        who = generateMatrix(nodes, nodes, { Math.random() - limit })
    }

    fun train(inputList: Array<Double>, expectedOutputList: Array<Double>) {
        val input = inputList.transposed()
        val expectedOutput = expectedOutputList.transposed()

        xHidden = (wih * input)!!
        oHidden = xHidden.map(sigmoidFunction)
        xOutput = (who * oHidden)!!
        oOutput = oHidden.map(sigmoidFunction)

        val errorsOutput = (expectedOutput - oOutput)!!
        val errorsHidden = (who.transposed() * errorsOutput)!!

        val errorsMapAdjustedOutput = errorsOutput.map { it * learningRate }
        val t1 = (errorsMapAdjustedOutput.scalarMultiplication(oOutput.scalarMultiplication(1.0 - oOutput))) * oHidden.transposed()
        who = (who + t1!!)!!
        val errorMapAdjustedHidden = errorsHidden.map { it * learningRate }
        wih = (wih + ((errorMapAdjustedHidden.scalarMultiplication(oHidden.scalarMultiplication(1.0 - oHidden)))
                * input.transposed())!!)!!
    }

    fun query(inputList: Array<Double>) {
        val input = inputList.transposed()
        xHidden = (wih * input)!!
        oHidden = xHidden.map(sigmoidFunction)
        xOutput = (who * oHidden)!!
        oOutput = oHidden.map(sigmoidFunction)
    }
}