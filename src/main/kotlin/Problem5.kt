import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Smallest multiple
 *
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 *
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 *
 * https://projecteuler.net/problem=5
 */
fun main() {
    println(smallestNumberDivisibleBy(20))
}

// Initial answer
/*fun smallestNumberDivisibleBy(k : Int): Long {
    for (n in (k..Long.MAX_VALUE)) {
        if ((2..k).all { n % it == 0L }) {
            return n
        }
    }
    return 0
}*/

private fun smallestNumberDivisibleBy(k: Int): Long {
    val p = primes(k)
    val a = Array(p.size) { 0 }
    val limit = sqrt(k.toDouble())
    var n = 1L
    var check = true
    for (i in 1 until p.size) {
        a[i] = 1
        if (check) {
            if (p[i] <= limit) {
                a[i] = floor(log10(k.toDouble())/ log10(p[i].toDouble())).toInt()
            } else {
                check = false
            }
        }
        n *= p[i].toDouble().pow(a[i]).toLong()
    }
    return n
}

// Generating a List of Primes: The Sieve of Eratosthenes
fun primes(n: Int): List<Int> {
    val flags = Array(n) { true }
    var prime = 2

    while (prime <= sqrt(n.toDouble())) {
        flags.crossOff(prime)
        prime = flags.indexOfFirst(from = prime + 1) { it }
    }

    return flags.mapIndexedNotNull { index, isPrime -> if (isPrime) index else null }
}

private fun Array<Boolean>.crossOff(prime: Int) {
    for (i in (prime * prime) until size step prime) {
        this[i] = false
    }
}


inline fun <T> Array<out T>.indexOfFirst(from: Int, predicate: (T) -> Boolean): Int {
    for (index in from until size) {
        if (predicate(this[index])) {
            return index
        }
    }
    return -1
}
