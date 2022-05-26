/**
 * Smallest multiple
 *
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 *
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 */
fun main() {
    for (n in (2520..Int.MAX_VALUE)) {
        val smallest = (1..20).all { n % it == 0 }
        if (smallest) {
            println(n)
            return
        }
    }
}
