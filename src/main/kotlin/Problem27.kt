import kotlin.math.pow

/**
 * Quadratic primes
 *
 * Euler discovered the remarkable quadratic formula: n^2 + n + 41
 *
 * It turns out that the formula will produce 40 primes for the consecutive integer values 0 <= n <= 39. However, when
 * is n = 40, 40^2 + 40 +41 = 40(40+1) + 41 divisible by 41, and certainly when n = 41, 41^2 + 41 + 41 is clearly
 * divisible by 41.
 *
 * The incredible formula n^2 - 79n + 1601 was discovered, which produces 80 primes for the consecutive values
 * 0 <= n <= 79. The product of the coefficients, −79 and 1601, is −126479.
 *
 * Considering quadratics of the form:
 *
 *      n ^2 + an + b, where |a| < 1000 and |b| <= 1000
 *      where |n| is the modulus/absolute value of n
 *      e.g. |11| = 11 and |-4| = 4
 *
 * Find the product of the coefficients, a and b, for the quadratic expression that produces the maximum number of
 * primes for consecutive values of n, starting with n = 0.
 *
 * https://projecteuler.net/problem=27
 */

fun main() {
    println(numOfPrimes(a = 1, b = 41))
    println(numOfPrimes(a = -79, b = 1601))
    println(quadraticPrimes())
}

private fun quadraticPrimes(): Int {
    var numOfPrimes = 0
    var abPair = 0 to 0
    for (a in -999 until 1_000) {
        for (b in -1_000..1_000) {
            val count = numOfPrimes(a, b)
            if (count > numOfPrimes) {
                numOfPrimes = count
                abPair = a to b
            }
        }
    }
    return abPair.first * abPair.second
}

private fun numOfPrimes(a: Int, b: Int): Int {
    var count = -1
    var n = -1
    do {
        count++
        n++
        val res = n.toDouble().pow(2.0) + (a * n) + b
    } while (isPrime(res.toLong()))
    return count
}