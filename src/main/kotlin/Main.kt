class Main {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            println("Start Neural Network")
            val nodes = 3
            val learningRate = 0.3f
            val sigmoid = { currentValue: Double ->
                1 / (1 + Math.pow(Math.E, -currentValue))
            }

            val neuralNetwork = Network(nodes, sigmoid, learningRate)
        }

    }
}


