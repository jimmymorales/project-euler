import kotlin.system.measureNanoTime

/**
 * Multiples of 3 or 5
 *
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these
 * multiples is 23.
 *
 * Find the sum of all the multiples of 3 or 5 below 1000.
 *
 * https://projecteuler.net/problem=1
 */

fun main() {
    val max = 999
    val solution1Time = measureNanoTime {
        solution1(max)
    }
    val solution2Time = measureNanoTime {
        solution2(max)
    }

    println(solution1Time)
    println(solution2Time)
}

private fun solution1(limit: Int) {
    val res = (1 .. limit).asSequence()
        .filter { n -> n % 3 == 0 || n % 5 == 0 }
        .sum()
    println(res)
}

private fun solution2(limit: Int) {
    val res2 = sumDivisibleBy(3, limit) + sumDivisibleBy(5, limit) - sumDivisibleBy(15, limit)
    println(res2)
}

fun sumDivisibleBy(n: Int, max: Int): Int {
    // e.g. 3 + 6 + 9 + 12 + ... + 999 = 3 (1 + 2 + 3 + 4 + ... + 333)
    // 333 = 999 / 3 => p = max / n
    val p = max / n
    // 1 + 2 + 3 + 4 + ... + p = 1/2 * p * (p + 1)
    return (n * ( 0.5 * p * (p + 1))).toInt()
}

