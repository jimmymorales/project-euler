/**
 * Double-base palindromes
 *
 * The decimal number, 585 = 10010010012 (binary), is palindromic in both bases.
 *
 * Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.
 *
 * (Please note that the palindromic number, in either base, may not include leading zeros.)
 *
 * https://projecteuler.net/problem=36
 */
fun main() {
	println(585.isDoubleBasePalindrome())
	println(sumOfDoubleBasePalindrome())
}

private fun sumOfDoubleBasePalindrome(limit: Int = 1_000_000): Int {
	return (1 until limit).filter(Int::isDoubleBasePalindrome).sum()
}

private fun Int.isDoubleBasePalindrome(): Boolean = isPalindrome && toString(radix = 2).isPalindrome()

@Suppress("ReturnCount")
private fun String.isPalindrome(): Boolean {
	if (isEmpty()) return false
	for (i in 0 until length / 2) {
		if (this[i] != this[lastIndex - i]) {
			return false
		}
	}
	return true
}
