import kotlin.math.sqrt

/**
 * Largest prime factor
 *
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * What is the largest prime factor of the number 600851475143 ?
 */

fun main() {
    println(solution3(600851475143L))
}

private fun solution3(n: Long): Long {
    var num = n
    var lastFactor = if (n % 2 == 0L) {
        num /= 2
        while (num % 2 == 0L) {
            num /= 2
        }
        2L
    } else {
        1L
    }
    var factor = 3L
    var maxFactor = sqrt(num.toDouble()).toLong()
    while (num > 1 && factor <= maxFactor) {
        if (num % factor == 0L) {
            num /= factor
            lastFactor = factor
            while (num % factor == 0L) {
                num /= factor
            }
            maxFactor = sqrt(num.toDouble()).toLong()
        }
        factor += 2
    }

    return if (num == 1L) lastFactor else num
}
