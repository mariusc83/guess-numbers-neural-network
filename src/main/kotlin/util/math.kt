package util

inline fun <reified T> generateMatrix(height: Int, width: Int, noinline generator: () -> T): Array<Array<T>> {
    val matrix = Array<Array<T>>(height, {
        Array<T>(width, { generator() })
    })
    return matrix
}