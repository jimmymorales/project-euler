@file:Suppress("MagicNumber")

import kotlin.math.pow

/**
 * Truncatable primes
 *
 * The number 3797 has an interesting property. Being prime itself, it is possible to continuously remove digits from
 * left to right, and remain prime at each stage: 3797, 797, 97, and 7. Similarly, we can work from right to left: 3797,
 * 379, 37, and 3.
 *
 * Find the sum of the only eleven primes that are both truncatable from left to right and right to left.
 *
 * NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
 *
 * https://projecteuler.net/problem=37
 */
fun main() {
	println(3797.isTruncatablePrime())
	println(sumAllTruncatablePrime())
}

private fun sumAllTruncatablePrime(): Int = primes(1_000_000)
	.asSequence()
	.filter { it > BASE_10 && it.isTruncatablePrime() }
	.take(11)
	.onEach(::println)
	.sum()

private fun Int.isTruncatablePrime(): Boolean {
	var num = this
	var count = 0
	var res = 0
	while (num != 0) {
		res += (num % BASE_10) * (BASE_10.toDouble().pow(count).toInt())
		if (!isPrime(res.toLong()) || !isPrime(num.toLong())) {
			return false
		}
		num /= BASE_10
		count++
	}

	return true
}

private const val BASE_10 = 10
