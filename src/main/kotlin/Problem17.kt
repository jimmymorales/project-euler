import kotlin.math.floor

/**
 * Number letter counts
 *
 * If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are 3 + 3 + 5 + 4 + 4 = 19
 * letters used in total.
 *
 * If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?
 *
 * NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains 23 letters and 115 (one
 * hundred and fifteen) contains 20 letters. The use of "and" when writing out numbers is in compliance with British usage.
 */

fun main() {
    println(numberLetterCount(115))
    println(numberLetterCount(342))
    println(sumNumberLetterCounts(5))
    println(sumNumberLetterCounts(1_000))
}

private fun sumNumberLetterCounts(max: Int): Int = (1..max).sumOf(::numberLetterCount)

private fun numberLetterCount(number: Int): Int {
    if (number < 100)
        return below100(number)

    var res = 0;
    val h = floor(number / 100f) % 10
    val t = floor(number / 1000f).toInt()
    val s = number % 100

    if (number > 999) {
        res += below100(t) + "thousand".length
    }
    if (h != 0f) {
        res += proper[h.toInt()] + "hundred".length
    }
    if (s != 0) {
        res += "and".length + below100(s)
    }
    return res
}

// Returns the length of the numbers between 0 and 99
private fun below100(n: Int): Int = if (n < 20) proper[n] else tenth[n / 10 - 2] + proper[n % 10]

// proper nouns
private val proper = arrayOf(
    0,
    "one".length,
    "two".length,
    "three".length,
    "four".length,
    "five".length,
    "six".length,
    "seven".length,
    "eight".length,
    "nine".length,
    "ten".length,
    "eleven".length,
    "twelve".length,
    "thirteen".length,
    "fourteen".length,
    "fifteen".length,
    "sixteen".length,
    "seventeen".length,
    "eighteen".length,
    "nineteen".length,
)

// tenth prefixes
private val tenth = arrayOf(
    "twenty".length,
    "thirty".length,
    "forty".length,
    "fifty".length,
    "sixty".length,
    "seventy".length,
    "eighty".length,
    "ninety".length,
)