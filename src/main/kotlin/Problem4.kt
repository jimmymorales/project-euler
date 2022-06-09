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
    println(largestPalindrome())
}

fun largestPalindrome(): Int {
    var largestPalindrome = 0
    for (a in 999 downTo 100) {
        var b = 999
        while (b >= a) {
            val p = a * b
            if (p <= largestPalindrome) {
                break // since p is always going to be small
            }
            if (p.isPalindrome) {
                largestPalindrome = p
            }
            b--
        }
    }
    return largestPalindrome
}

val Int.isPalindrome: Boolean get() = this == reverse()

fun Int.reverse(): Int {
    var num = this
    var reversed = 0
    while (num > 0) {
        reversed = (10 * reversed) + (num % 10)
        num /= 10
    }
    return reversed
}

