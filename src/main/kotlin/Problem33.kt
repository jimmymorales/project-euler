@file:Suppress("MagicNumber")

/**
 * Digit cancelling fractions
 *
 * The fraction 49/98 is a curious fraction, as an inexperienced mathematician in attempting to simplify it may
 * incorrectly believe that 49/98 = 4/8, which is correct, is obtained by cancelling the 9s.
 *
 * We shall consider fractions like, 30/50 = 3/5, to be trivial examples.
 *
 * There are exactly four non-trivial examples of this type of fraction, less than one in value, and containing two
 * digits in the numerator and denominator.
 *
 * If the product of these four fractions is given in its lowest common terms, find the value of the denominator.
 *
 * https://projecteuler.net/problem=33
 */
fun main() {
	println(isDigitCancellingFractions(numerator = 49, denominator = 98))
	println(findDigitCancellingFractionsProductDenominator())
}

private fun findDigitCancellingFractionsProductDenominator(): Int = buildList {
	// From 10 to 99, since we only support two digits.
	for (i in 11 until 100) {
		for (j in 10 until i) {
			if (isDigitCancellingFractions(numerator = j, denominator = i)) {
				add(j to i)
			}
		}
	}
}.reduce { (n1, d1), (n2, d2) ->
	(n1 * n2) to (d1 * d2)
}.let { (numerator, denominator) ->
	val denBI = denominator.toBigInteger()
	val gcd = numerator.toBigInteger().gcd(denominator.toBigInteger())
	(denBI / gcd).toInt()
}

private fun isDigitCancellingFractions(numerator: Int, denominator: Int): Boolean {
	check(numerator in 10..99)
	check(denominator in 10..99)
	check(numerator < denominator)

	val numDigits = numerator.digits().toSet()
	val denDigits = denominator.digits().toSet()
	val repeatedDigits = numDigits intersect denDigits
	if (numDigits.size == 1 || denDigits.size == 1 || repeatedDigits.size != 1) {
		return false
	}

	val digit = repeatedDigits.first()
	val newNumerator = numDigits.first { it != digit }.toDouble()
	val newDenominator = denDigits.first { it != digit }.toDouble()
	// ignore trivial fractions were common digit is zero
	return digit != 0 &&
		newDenominator != 0.0 &&
		(numerator.toDouble() / denominator.toDouble()) == (newNumerator / newDenominator)
}
