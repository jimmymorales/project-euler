import kotlin.math.floor
import kotlin.math.sqrt
import kotlin.system.measureNanoTime

/**
 * 10001st prime
 *
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
 *
 * What is the 10001st prime number?
 *
 * https://projecteuler.net/problem=7
 */

fun main() {
    println(findPrime1(6))
    println(findPrime(6))
    val time1 = measureNanoTime {
        println(findPrime1(10_001))
    }
    val time2 = measureNanoTime {
        println(findPrime(10_001))
    }
    println(time1)
    println(time2)
}

private fun findPrime1(n: Int): Long {
    var prime = 2L
    val primes = mutableListOf(prime)
    while (primes.size != n) {
        prime++
        if (!primes.any { prime % it == 0L }) {
            primes.add(prime)
        }
    }
    return prime
}

fun findPrime(n: Int): Long {
    if (n == 1) return 2
    var count = 1
    var candidate = 1L
    do {
        candidate += 2
        if (isPrime(candidate)) count++
    } while (count != n)
    return candidate
}

fun isPrime(n: Long): Boolean {
    return when {
        n < 2 -> false // 1 is not prime
        n < 4 -> true // 2 and 3 are prime
        n % 2 == 0L -> false // all primes except 2 are odd
        n < 9 -> true // we have already excluded 4. 6 and 8
        n % 3 == 0L -> false
        else -> { // All primes greater than 3 can be written in the form 6k+/-1.
            val r = floor(sqrt(n.toDouble())).toLong()
            for (f in 5L .. r step 6) {
                if (n % f == 0L) return false
                if (n % (f + 2) == 0L) return false
            }
            true
        }
    }
}