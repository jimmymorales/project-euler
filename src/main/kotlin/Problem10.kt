import kotlin.math.floor
import kotlin.math.sqrt
import kotlin.system.measureNanoTime

/**
 * Summation of primes
 *
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 * Find the sum of all the primes below two million.
 *
 * https://projecteuler.net/problem=10
 */

fun main() {
    println(sumOfPrimes(10))
    println(sumOfPrimesSieve(10))
    val time1 = measureNanoTime { println(sumOfPrimes(2_000_000)) }
    val time2 = measureNanoTime { println(sumOfPrimesSieve(2_000_000)) }

    println(time1)
    println(time2)
}

private fun sumOfPrimes(n: Long): Long {
    if (n < 2) return 0
    return (3..n step 2).asSequence()
        .filter(::isPrime)
        .sum() + 2
}

private fun sumOfPrimesSieve(n: Long): Long {
    return primes(n).sumOf(Int::toLong)
}

// The sieve of Eratosthenes optimized
fun primes(n: Long): List<Int> {
    val sieveBound = ((n - 1) / 2).toInt()
    val sieve = Array(sieveBound) { true }
    val crosslimit = (floor(sqrt(n.toDouble())).toInt() - 1) / 2

    for (i in 1..crosslimit) {
        if (!sieve[i-1]) continue
        for (j in (2 * i * (i + 1)) .. sieveBound step 2 * i + 1) {
            sieve[j-1] = false
        }
    }

    return listOf(2) + sieve.mapIndexedNotNull { i, isPrime -> if (isPrime) 2 * (i + 1) + 1 else null }
}
