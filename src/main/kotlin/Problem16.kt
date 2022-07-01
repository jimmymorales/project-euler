import java.math.BigInteger

/**
 * Power digit sum
 *
 * 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
 *
 * What is the sum of the digits of the number 2^1000?
 *
 * https://projecteuler.net/problem=16
 */
fun main() {
    println(powerOfSum(15))
    println(powerOfSum(1_000))
    println(powerOfSum2(15))
    println(powerOfSum2(1_000))
}

// Cheating using BigInteger
private fun powerOfSum(n: Int): Int = BigInteger.TWO.pow(n)
    .toString(10)
    .sumOf(Char::digitToInt)

// Without cheating xD
private fun powerOfSum2(n: Int): Int {
    val array = IntArray(1000)
    array[0] = 1
    var end = 0
    repeat(n) {
        var acc = 0
        var i = 0
        while (i <= end) {
            val value = (array[i] * 2) + acc
            val digit = value % 10
            array[i] = digit
            acc = if (value >= 10) value / 10 else 0
            i++
        }
        if (acc > 0) {
            while (acc != 0) {
                val digit = acc % 10
                array[i] = digit
                acc /= 10
                i++
            }
            end = i - 1
        }
    }

    return (0..end).sumOf { array[it] }
}