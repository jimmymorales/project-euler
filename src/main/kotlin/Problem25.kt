import kotlin.math.max

/**
 * 1000-digit Fibonacci number
 *
 * The Fibonacci sequence is defined by the recurrence relation:
 *      Fn = Fn−1 + Fn−2, where F1 = 1 and F2 = 1.
 * Hence the first 12 terms will be:
 *      F1 = 1
 *      F2 = 1
 *      F3 = 2
 *      F4 = 3
 *      F5 = 5
 *      F6 = 8
 *      F7 = 13
 *      F8 = 21
 *      F9 = 34
 *      F10 = 55
 *      F11 = 89
 *      F12 = 144
 *
 * The 12th term, F12, is the first term to contain three digits.
 * What is the index of the first term in the Fibonacci sequence to contain 1000 digits?
 *
 * https://projecteuler.net/problem=25
 */
fun main() {
    println(fibIndexOfDigitsCount(digitsCount = 3))
    println(fibIndexOfDigitsCount(digitsCount = 1_000))
}

private fun fibIndexOfDigitsCount(digitsCount: Int): Int {
    if (digitsCount == 1) return 1
    val f1 = mutableListOf(1)
    val f2 = mutableListOf(1)
    var index = 2
    var turn = true // true for f1, false for f2
    while (f1.size != digitsCount && f2.size != digitsCount) {
        if (turn) {
            sum(f1, f2)
        } else {
            sum(f2, f1)
        }
        turn = !turn
        index++
    }
    return index
}

private fun sum(list1: MutableList<Int>, list2: MutableList<Int>) {
    var acc = 0
    for (i in 0 until max(list1.size, list2.size)) {
        val sum = list1.getOrElse(i) { 0 } + list2.getOrElse(i) { 0 } + acc
        if (i < list1.size) {
            list1[i] = sum % 10
        } else {
            list1.add(sum % 10)
        }
        acc = sum / 10
    }
    while (acc != 0) {
        list1.add(acc % 10)
        acc /= 10
    }
}
