/**
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
 *                                                              a2 + b2 = c2
 *
 * For example, 32 + 42 = 9 + 16 = 25 = 52.
 *
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
 * Find the product abc.
 *
 * https://projecteuler.net/problem=9
 */
fun main() {
    println(pythagoreanTripletProduct(1_000))
}

fun pythagoreanTripletProduct(n: Int): Int {
    for (a in 3..((n - 3) / 3)) {
        for (b in (a + 1)..((n - a - 1) / 2)) {
            val c = n - a - b
            if (c * c == a * a + b * b) {
                return a * b * c
            }
        }
    }
    return 0
}

