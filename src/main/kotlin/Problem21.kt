import kotlin.math.floor
import kotlin.math.sqrt

/**
 * Amicable numbers
 *
 * Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
 * If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called amicable
 * numbers.
 *
 * For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284. The
 * proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.
 *
 * Evaluate the sum of all the amicable numbers under 10000.
 *
 * https://projecteuler.net/problem=21
 */

fun main() {
    println(sumOfProperDivisors(220))
    println(sumOfProperDivisors(284))
    println(sumOfAmicables(10_000))
}

private fun sumOfAmicables(n: Int): Int {
    var sum = 0
    val amicables = mutableSetOf<Int>()
    for (a in 2 until n) {
        if (a in amicables) continue
        val b = sumOfProperDivisors(a)
        if (b > a && a == sumOfProperDivisors(b)) {
            sum += a + b
            amicables.add(b)
        }
    }
    return sum
}

private fun sumOfProperDivisors(n: Int): Int {
    var sum = 1
    var start = 2
    var step = 1

    var r = floor(sqrt(n.toDouble())).toInt()
    if (r * r == n) {
        sum += r
        r--
    }

    if (n % 2 != 0) {
        start = 3
        step = 2
    }

    for (i in start..r step step) {
        if (n % i == 0) {
            sum += i + (n / i)
        }
    }
    return sum
}
