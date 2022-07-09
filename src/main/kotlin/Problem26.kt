import java.util.Comparator

/**
 * Reciprocal cycles
 *
 * A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions with denominators 2 to
 * 10 are given:
 *
 * 1/2	= 	0.5
 * 1/3	= 	0.(3)
 * 1/4	= 	0.25
 * 1/5	= 	0.2
 * 1/6	= 	0.1(6)
 * 1/7	= 	0.(142857)
 * 1/8	= 	0.125
 * 1/9	= 	0.(1)
 * 1/10	= 	0.1
 *
 * Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 1/7 has a 6-digit recurring
 * cycle.
 *
 * Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.
 *
 * https://projecteuler.net/problem=26
 */
fun main() {
    println(2.countRepeatingDecimalsInUnitFraction())
    println(3.countRepeatingDecimalsInUnitFraction())
    println(4.countRepeatingDecimalsInUnitFraction())
    println(5.countRepeatingDecimalsInUnitFraction())
    println(6.countRepeatingDecimalsInUnitFraction())
    println(7.countRepeatingDecimalsInUnitFraction())
    println(8.countRepeatingDecimalsInUnitFraction())
    println(9.countRepeatingDecimalsInUnitFraction())
    println(10.countRepeatingDecimalsInUnitFraction())

    println(findLongestRecurringCycle(1_000))
}

private fun findLongestRecurringCycle(limit: Int): Int = (2 until limit)
    .map { it to it.countRepeatingDecimalsInUnitFraction() }
    .maxWithOrNull(Comparator.comparingInt(Pair<Int, Int>::second))!!
    .first

private fun Int.countRepeatingDecimalsInUnitFraction(): Int {
    var acc = 10
    var rem: Int
    val rems = mutableMapOf<Int, Int>()
    var index = 0
    do {
        rem = acc / this
        acc -= (this * rem)
        if (acc in rems) {
            return rems.size - rems[acc]!!
        } else {
            rems[acc] = index
            index++
        }
        acc *= 10
    } while (acc > 0)
    return 0
}