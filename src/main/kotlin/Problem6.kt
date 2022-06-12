import kotlin.math.pow

/**
 * Sum square difference
 *
 * The sum of the squares of the first ten natural numbers is,
 *
 * 1^2 + 2^2 + ... + 10^2 = 385
 *
 * The square of the sum of the first ten natural numbers is,
 *
 * (1 + 2 + ... + 10)^2 = 55^2 = 3025
 *
 * Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is
 * 3025 - 385 = 2640
 *
 * Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
 */

fun main() {
    println(sumSquareDifference(10))
    println(sumSquareDifference(100))
}

private fun sumSquareDifference(n: Int): Long {
    val sum = (0.5 * n * (n + 1)).pow(2).toLong() // (0.5n(n + 1))^2
    val power = ((2 * n + 1) * (n + 1) * n) / 6 // (2n + 1)(n + 1) n / 6
    return sum - power
}