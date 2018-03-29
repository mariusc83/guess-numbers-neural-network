import java.io.BufferedReader

class TrainingDataRepo {


    fun fetchTrainingData(fileName: String): List<TrainingData>? {
        val inputStream = javaClass.classLoader.getResourceAsStream(fileName)
        val data = inputStream?.bufferedReader()?.use {
            it.lineSequence()
                    .fold(initial = mutableListOf<String>()) { acc, line ->
                        acc.add(line)
                        acc
                    }
        }

        return data?.map { it.split(",") }
                ?.map {
                    val label = it.get(0).toInt()
                    val output = MutableList(10) {
                        0.01
                    }
                    output[label] = 0.99
                    val input = it
                            .subList(1, it.size)
                            .map {
                                it.toInt()
                            }
                            .map {
                                (it / 255.0) * 0.99 + 0.01
                            }
                    TrainingData(input, output)
                }
    }

    fun dataStream(fileName: String): BufferedReader? {
        val inputStream = javaClass.classLoader.getResourceAsStream(fileName)
        return inputStream?.bufferedReader()
    }
/*
    inline fun consumeTrainingDataSequence(fileName: String, consumer: (Sequence<TrainingData>) -> Unit) {
        val inputStream = javaClass.classLoader.getResourceAsStream(fileName)
        inputStream?.bufferedReader()?.use {
            val sequence = it.lineSequence()
                    .map {
                        it.split(",")
                    }
                    .map {
                        val label = it.get(0).toInt()
                        val output = MutableList(10) {
                            0.01
                        }
                        output[label] = 0.99
                        val input = it
                                .subList(1, it.size)
                                .map {
                                    it.toInt()
                                }
                                .map {
                                    (it / 255.0) * 0.99 + 0.01
                                }
                        TrainingData(input, output)
                    }
            consumer(sequence)
        }
    }
*/

}

inline fun BufferedReader.consumeAsSequence(consumer: (Sequence<TrainingData>) -> Unit) {
    use {
        val sequence = it.lineSequence()
                .map {
                    it.split(",")
                }
                .map {
                    val label = it.get(0).toInt()
                    val output = MutableList(10) {
                        0.01
                    }
                    output[label] = 0.99
                    val input = it
                            .subList(1, it.size)
                            .map {
                                it.toInt()
                            }
                            .map {
                                (it / 255.0) * 0.99 + 0.01
                            }
                    TrainingData(input, output)
                }
        consumer(sequence)
    }
}


data class TrainingData(val input: List<Double>, val output: List<Double>)