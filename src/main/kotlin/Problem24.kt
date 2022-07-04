/**
 * Lexicographic permutations
 *
 * A permutation is an ordered arrangement of objects. For example, 3124 is one possible permutation of the digits 1, 2,
 * 3 and 4. If all of the permutations are listed numerically or alphabetically, we call it lexicographic order. The
 * lexicographic permutations of 0, 1 and 2 are:
 *
 * 012   021   102   120   201   210
 *
 * What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
 *
 * https://projecteuler.net/problem=24
 */
fun main() {
    println(lexicographicPermutations(listOf('0', '1', '2')))
    println(lexicographicPermutationsAt(listOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'), 1_000_000))
}

private fun lexicographicPermutations(digits: List<Char>): List<String> = buildList {
    val arr = digits.toCharArray()
    while (true) {
        add(String(arr))

        if (!arr.nextPermutation()) break
    }
}

private fun lexicographicPermutationsAt(digits: List<Char>, n: Int): String {
    val arr = digits.toCharArray()
    var count = 0
    while (true) {
        count++
        if (count == n) {
            return String(arr)
        }

        if (!arr.nextPermutation()) break
    }
    return ""
}

private fun CharArray.nextPermutation(): Boolean {
    // Find the rightmost character which is smaller than its next character.
    val i = ((lastIndex - 1) downTo 0).firstOrNull { this[it] < this[it + 1] } ?: return false

    // Find the ceil of 'first char' in right of first character. Ceil of a character is the smallest character
    // greater than it.
    val ceilIndex = findCeil(this[i], i + 1, lastIndex)

    // Swap first and second characters
    swap(i, ceilIndex)

    // reverse the string on right of 'first  char'
    reverse(i + 1, lastIndex)
    return true
}

// This function finds the index of the smallest
// character which is greater than 'first' and is
// present in str[l..h]
private fun CharArray.findCeil(first: Char, l: Int, h: Int): Int {
    // initialize index of ceiling element
    var ceilIndex = l

    // Now iterate through rest of the elements and find
    // the smallest character greater than 'first'
    for (i in l + 1..h) {
        if (this[i] > first && this[i] < this[ceilIndex]) {
            ceilIndex = i
        }
    }
    return ceilIndex
}

// A utility function two swap two characters a and b
private fun CharArray.swap(i: Int, j: Int) {
    val t = this[i]
    this[i] = this[j]
    this[j] = t
}

// A utility function to reverse a string str[l..h]
private fun CharArray.reverse(l: Int, h: Int) {
    var low = l
    var high = h
    while (low < high) {
        swap(low, high)
        low++
        high--
    }
}
