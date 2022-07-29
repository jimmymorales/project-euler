/**
 * Digit factorials
 *
 * 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.
 *
 * Find the sum of all numbers which are equal to the sum of the factorial of their digits.
 *
 * Note: As 1! = 1 and 2! = 2 are not sums they are not included.
 *
 * https://projecteuler.net/problem=34
 */
fun main() {
	check(145.isDigitFactorial())
	println(sumOfDigitFactorials())
}

private fun sumOfDigitFactorials(from: Int = 10, to: Int = 99999): Int =
	(from..to).filter(Int::isDigitFactorial).sum()

private fun Int.isDigitFactorial(): Boolean = digits().sumOf(Int::factorial) == this

private fun Int.factorial(): Int = (1..this).fold(1) { x, y -> x * y }
