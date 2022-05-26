import kotlin.math.sqrt
import kotlin.system.measureNanoTime

/**
 * Largest prime factor
 *
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * What is the largest prime factor of the number 600851475143 ?
 */

fun main() {
    val n = Int.MAX_VALUE
    val solution1Time = measureNanoTime {
        solution1(n)
    }
    val solution2Time = measureNanoTime {
        solution2(n)
    }

    println("Time #1 = $solution1Time")
    println("Time #2 = $solution2Time")
}

private fun solution1(n: Int) {
    var num = n
    var factor: Int
    do {
        factor = primes(n).first { num % it == 0 }
        num /= factor
    } while (num != 1)
    println(factor)
}

private fun solution2(n: Int) {
    var num = n
    var factor: Int
    do {
        factor = primes2(n).first { num % it == 0 }
        num /= factor
    } while (num != 1)
    println(factor)
}

fun primes(n: Int) = (2..n).asSequence().filter { num -> (2 until num).none { num % it == 0 } }

// implementation using Sieve of Eratosthenes
fun primes2(n: Int) = sequence {
    val flags = Array(n + 1) { it != 0 && it != 1 }
    var prime = 2

    while (prime <= sqrt(n.toDouble())) {
        yield(prime)
        for (i in (prime * prime) until flags.size step prime) {
            flags[i] = false
        }

        do {
            prime++
        } while (prime < flags.size && !flags[prime])
    }
}