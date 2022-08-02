/**
 * Circular primes
 *
 * The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, are themselves
 * prime.
 *
 * There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.
 *
 * How many circular primes are there below one million?
 *
 * https://projecteuler.net/problem=35
 */
fun main() {
	println(197.isCircularPrime())
	println(countCircularPrimes(limit = 100))
	println(countCircularPrimes(limit = 1_000_000))
}

private fun countCircularPrimes(limit: Long): Int {
	return primes(limit).count(Int::isCircularPrime)
}

private fun Int.isCircularPrime(): Boolean {
	val digits = digits()
	for (i in digits.indices) {
		val nextDigits = digits.subList(fromIndex = i, digits.size) + digits.subList(fromIndex = 0, toIndex = i)
		if (!isPrime(nextDigits.joinToString(separator = "").toLong())) {
			return false
		}
	}
	return true
}
