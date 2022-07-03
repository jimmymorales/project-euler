/**
 * Names scores
 *
 * Using names.txt (right click and 'Save Link/Target As...'), a 46K text file containing over five-thousand first
 * names, begin by sorting it into alphabetical order. Then working out the alphabetical value for each name, multiply
 * this value by its alphabetical position in the list to obtain a name score.
 *
 * For example, when the list is sorted into alphabetical order, COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53, is the
 * 938th name in the list. So, COLIN would obtain a score of 938 × 53 = 49714.
 *
 * What is the total of all the name scores in the file?
 *
 * https://projecteuler.net/problem=22
 */
fun main() {
    println("COLIN".score)
    val total = loadResource("p022_names.txt")
        .splitToSequence(',')
        .map { it.removeSurrounding("\"") }
        .sorted()
        .mapIndexed { index, s -> s.score * (index + 1) }
        .sum()
    println(total)
}

private val String.score: Int get() = map { it.lowercaseChar() - 'a' + 1 }.sum()

private fun loadResource(name: String) = object {}.javaClass.getResource(name).readText()
