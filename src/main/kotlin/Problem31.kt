@file:Suppress("MagicNumber")

/**
 * Coin sums
 *
 * In the United Kingdom the currency is made up of pound (£) and pence (p). There are eight coins in general
 * circulation:
 *
 * 1p, 2p, 5p, 10p, 20p, 50p, £1 (100p), and £2 (200p).
 *
 * It is possible to make £2 in the following way: 1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
 *
 * How many different ways can £2 be made using any number of coins?
 *
 * https://projecteuler.net/problem=31
 */
fun main() {
	println(countCoinsRec(index = 0, target = 200))
	println(countCoinsOpt(target = 200))

	assert(countCoinsOpt(target = 10_000) == 1_133_873_304_647_601)
}

val coins = listOf(1, 2, 5, 10, 20, 50, 100, 200)
fun countCoinsRec(index: Int, target: Int): Int {
	val coin = coins[index]
	if (target < coin) return 0

	val maxCoins = target / coin
	var count = 0
	for (i in maxCoins downTo 0) {
		val value = i * coin
		if (value == target) {
			count++
		} else if (index != coins.lastIndex) {
			count += countCoinsRec(index = index + 1, target = target - value)
		}
	}
	return count
}

private fun countCoinsOpt(target: Int): Long {
	val array = LongArray(target + 1)
	array[0] = 1

	for (coin in coins) {
		for (j in coin..target) {
			array[j] = array[j] + array[j - coin]
		}
	}
	return array[target]
}
