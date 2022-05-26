import kotlin.math.sqrt

/**
 * Largest palindrome product
 *
 * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is
 * 9009 = 91 × 99.
 *
 * Find the largest palindrome made from the product of two 3-digit numbers.
 *
 */

fun main() {
    val upper = 999 * 999
    val lower = 100 * 100
    for (n in upper downTo lower) {
        if (n.isPalindrome) {
            val sqr = sqrt(n.toDouble()).toInt().coerceAtMost(999)
            for (f in sqr downTo 100) {
                if (n % f == 0) {
                    val f2 = n / f
                    if (f2 in 100..999) {
                        println(n)
                    }
                }
            }
        }
    }
    arrayOf<Int>().reverse()
}

val Int.isPalindrome: Boolean get() = this == toString().reversed().toInt()

