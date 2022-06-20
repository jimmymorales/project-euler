/**
 * Longest Collatz sequence
 *
 * The following iterative sequence is defined for the set of positive integers:
 *
 * n → n/2 (n is even)
 * n → 3n + 1 (n is odd)
 *
 * Using the rule above and starting with 13, we generate the following sequence:
 *
 * 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
 * It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although it has not been
 * proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.
 *
 * Which starting number, under one million, produces the longest chain?
 *
 * NOTE: Once the chain starts the terms are allowed to go above one million.
 *
 * https://projecteuler.net/problem=14
 */
fun main() {
    println(longestCollatzSequence(1_000_000))
}

private fun longestCollatzSequence(max: Int): Int {
    var longest = 0
    var answer = 0
    for (n in (max / 2) until max) {
        val count = collatzSequenceSize(n)
        if (count > longest) {
            longest = count
            answer = n
        }
    }
    return answer
}

private val cache = mutableMapOf(1 to 1)

private fun collatzSequenceSize(n: Int): Int {
    val cached = cache[n]
    if (cached != null) {
        return cached
    }

    val next = if (n % 2 == 0) {
        collatzSequenceSize(n / 2) + 1
    } else {
        collatzSequenceSize((3 * n + 1) / 2) + 2
    }
    if (cache.size <= 1 shl 30) {
        cache[n] = next
    }

    return next
}
