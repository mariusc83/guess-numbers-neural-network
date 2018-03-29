package util

operator fun Double.minus(matrix: Array<Array<Double>>): Array<Array<Double>> {
    return Array(matrix.size) { outerIndex ->
        Array(matrix[outerIndex].size) {
            //            if (it == outerIndex) {
            this@minus - matrix[outerIndex][it]
            /*} else {
                0 - matrix[outerIndex][it]
            }*/
        }
    }
}