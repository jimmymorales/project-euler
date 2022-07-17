import kotlin.math.pow

/**
 * Digit fifth powers
 *
 * Surprisingly there are only three numbers that can be written as the sum of fourth powers of their digits:
 *
 * 1634 = 1^4 + 6^4 + 3^4 + 4^4
 * 8208 = 8^4 + 2^4 + 0^4 + 8^4
 * 9474 = 9^4 + 4^4 + 7^4 + 4^4
 *
 * As 1 = 14 is not a sum it is not included.
 *
 * The sum of these numbers is 1634 + 8208 + 9474 = 19316.
 *
 * Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.
 *
 * https://projecteuler.net/problem=30
 */

fun main() {
    println(1634.isDigitPower(4))
    println(8208.isDigitPower(4))
    println(9474.isDigitPower(4))
    println(sumOfDigitPowersOf(4))
    println(sumOfDigitPowersOf(5))
}

private fun sumOfDigitPowersOf(n: Int): Int {
    var sum = 0
    val limit = 9.0.pow(n).times(n).toInt()
    for (i in 10..limit) {
        if (i.isDigitPower(n)) {
            sum += i
        }
    }
    return sum
}

private fun Int.isDigitPower(n: Int): Boolean {
    var sum = 0
    var num = this
    while (num != 0) {
        val d = num % 10
        sum += d.toDouble().pow(n).toInt()
        num /= 10
    }
    return sum == this
}