@file:Suppress("MagicNumber")

/**
 * Pandigital products
 *
 * We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once; for example,
 * the 5-digit number, 15234, is 1 through 5 pandigital.
 *
 * The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing multiplicand, multiplier, and product is 1
 * through 9 pandigital.
 *
 * Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through 9
 * pandigital.
 *
 * HINT: Some products can be obtained in more than one way so be sure to only include it once in your sum.
 *
 * https://projecteuler.net/problem=32
 */
fun main() {
	println(isMultiplicationPandigital(multiplicand = 39, multiplier = 186, product = 7254))
	println(checkPandigitalsMultiplications())
}

private fun checkPandigitalsMultiplications(): Int = buildSet {
	for (i in 2 until 100) {
		for (j in (i + 1) until 2_000) {
			val p = i * j
			if (isMultiplicationPandigital(multiplicand = i, multiplier = j, product = p)) {
				add(p)
			}
		}
	}
}.sum()

private fun isMultiplicationPandigital(multiplicand: Int, multiplier: Int, product: Int): Boolean {
	check(multiplicand * multiplier == product)

	var digitsFlags = 0
	val digits = multiplicand.digits() + multiplier.digits() + product.digits()

	if (digits.size != 9) {
		return false
	}

	digits.forEach {
		digitsFlags = digitsFlags or (1 shl (it - 1))
	}

	return digitsFlags == 511
}

fun Int.digits(): List<Int> = buildList {
	var num = this@digits
	while (num != 0) {
		add(num % 10)
		num /= 10
	}
}
