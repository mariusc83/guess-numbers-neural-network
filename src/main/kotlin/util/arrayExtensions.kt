package util

operator fun Array<Array<Double>>.times(with: Array<Array<Double>>): Array<Array<Double>>? {
    if (with.size == 0) return null
    if (size == 0) return null
    if (this[0].size != with.size) return null
    val result: Array<Array<Double>> = Array(this.size, { outerIndex ->
        Array(with[0].size, { innerIndex ->
            this@times[outerIndex].reduceIndexed { index, acc, fl ->
                acc + fl * with[index][innerIndex]
            }
        })
    })
    return result
}

fun Array<Array<Double>>.scalarMultiplication(with: Array<Array<Double>>): Array<Array<Double>> {
    return Array(size) { outerIndex ->
        Array(this@scalarMultiplication[outerIndex].size) {
            with[outerIndex][it] * this@scalarMultiplication[outerIndex][it]
        }
    }
}

operator fun Array<Array<Double>>.minus(with: Array<Array<Double>>): Array<Array<Double>>? {
    if (with.size == 0) return null
    if (size == 0) return null
    if (this.size != with.size) return null
    if (this[0].size != with[0].size) return null

    return Array(size, { outerIndex ->
        Array(this@minus[outerIndex].size) {
            this@minus[outerIndex][it] - with[outerIndex][it]
        }
    })
}

operator fun Array<Array<Double>>.plus(with: Array<Array<Double>>): Array<Array<Double>>? {
    if (with.size == 0) return null
    if (size == 0) return null
    if (this.size != with.size) return null
    if (this[0].size != with[0].size) return null

    return Array(size, { outerIndex ->
        Array(this@plus[outerIndex].size) {
            this@plus[outerIndex][it] + with[outerIndex][it]
        }
    })
}

fun <T : Number> Array<Array<T>>.print() {
    forEach {
        var s = ""
        it.forEach {
            s += " || $it"
        }
        s += "||"
        println(s)
    }
}

inline fun <T : Number, reified S : Number> Array<Array<T>>.map(operator: (T) -> S): Array<Array<S>> {
    return Array(size) { outerIndex ->
        Array(this@map[outerIndex].size) { innerIndex ->
            operator(this@map[outerIndex][innerIndex])
        }
    }
}


inline fun <reified T : Any> Array<T>.transposed(): Array<Array<T>> {
    return Array(size) { outerIndex ->
        Array(1, {
            this@transposed[outerIndex]
        })
    }
}

inline fun <reified T : Any> Array<Array<T>>.transposed(): Array<Array<T>> {
    return Array(this[0].size) { outerIndex ->
        Array(size) { innerIndex ->
            this@transposed[innerIndex][outerIndex]
        }
    }
}