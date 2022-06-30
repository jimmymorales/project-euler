import java.math.BigInteger

/**
 * Factorial digit sum
 *
 * n! means n × (n − 1) × ... × 3 × 2 × 1
 *
 * For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
 * and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
 *
 * Find the sum of the digits in the number 100!
 */
fun main() {
    println(factorialDigitSum(10))
    println(factorialDigitSum(100))
}

private fun factorialDigitSum(n: Int): Int {
    val result = mutableListOf<Int>().apply {
        var num = n
        while (num != 0) {
            add(num % 10)
            num /= 10
        }
    }


    for (factor in (n - 1) downTo 2) {
        var acc = 0
        for ((i, num) in result.withIndex()) {
            val res = (num * factor) + acc
            result[i] = res % 10
            acc = res / 10
        }

        while (acc != 0) {
            result.add(acc % 10)
            acc /= 10
        }
    }
    return result.sum()
}