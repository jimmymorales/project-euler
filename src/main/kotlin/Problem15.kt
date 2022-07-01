/**
 * Lattice paths
 *
 * Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down, there are exactly 6
 * routes to the bottom right corner.
 *
 * How many such routes are there through a 20×20 grid?
 *
 * https://projecteuler.net/problem=15
 */
fun main() {
    println(latticePathsRec(2))
    println(latticePathsRec(20))
    println(latticePathsIter(20))
}

fun latticePathsIter(size: Int): Long {
    val array = Array(size + 1) { i ->
        Array(size + 1) { j ->
            if (j == 0 || i == 0) 1L else 0L
        }
    }

    for (i in 1 until array.size) {
        for (j in 1 until array[i].size) {
            array[i][j] = array[i - 1][j] + array[i][j - 1]
        }
    }

    return array[size][size]
}

fun latticePathsRec(size: Int): Long {
    if (size <= 0) return 0
    cache.clear()

    return latticePathsHelper(0 + 1, 0, size) * 2
}

private val cache = mutableMapOf<Pair<Int, Int>, Long>()

fun latticePathsHelper(x: Int, y: Int, size: Int): Long {
    if (x == size && y == size) {
        return 1
    }

    if (x to y in cache) {
        return cache[x to y]!!
    }

    var count = 0L
    if (x < size) {
        count += latticePathsHelper(x + 1, y, size)
    }
    if (y < size) {
        count += latticePathsHelper(x, y + 1, size)
    }
    cache[x to y] = count
    return count
}