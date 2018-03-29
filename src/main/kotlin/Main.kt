import util.firstMaxIndex
import util.transposed

class Main {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val trainingFileName = "mnist_train_100.txt"
            val testFileName = "mnist_test_10.txt"
            val trainingDataRepo = TrainingDataRepo()

            println("Start Neural Network")
            val inputNodes = 784
            val hiddenNodes = 100
            val outputNodes = 10
            val learningRate = 0.3f
            val sigmoid = { currentValue: Double ->
                1 / (1 + Math.pow(Math.E, -currentValue))
            }

            val neuralNetwork = Network(inputNodes, hiddenNodes, outputNodes, sigmoid, learningRate)
            trainingDataRepo.dataStream(trainingFileName)?.consumeAsSequence {
                it.forEach { (input, output) ->
                    neuralNetwork.train(input.toTypedArray(), output.toTypedArray())
                }
            }

            trainingDataRepo.dataStream(testFileName)?.consumeAsSequence {
                var outputDataSize = 0
                var matchedResults = 0

                it.forEach { (input, output) ->
                    outputDataSize = output.size
                    val inputValue = input.toTypedArray()
                    val outputValue = output.toTypedArray()
                    val expectedValue = outputValue.firstMaxIndex()
                    println("Expected values was: $expectedValue")
                    val (_, outputFinal) = neuralNetwork.query(inputValue.transposed())
                    val networkOutput: Array<Double> = outputFinal.map { it.get(0) }.toTypedArray()
                    val guessedValue = networkOutput.firstMaxIndex()
                    println("Guessed value was: $guessedValue")
                    if (guessedValue == expectedValue) {
                        matchedResults++
                    }
                }

                println("Current network performance is ${(matchedResults.toFloat() / outputDataSize)}")

            }

        }

    }
}


