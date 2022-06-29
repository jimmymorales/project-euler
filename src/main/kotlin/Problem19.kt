/**
 * You are given the following information, but you may prefer to do some research for yourself.
 * - 1 Jan 1900 was a Monday.
 * - Thirty days has September,
 *   April, June and November.
 *   All the rest have thirty-one,
 *   Saving February alone,
 *   Which has twenty-eight, rain or shine.
 *   And on leap years, twenty-nine.
 * - A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
 * How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
 */
fun main() {
    println(
        countMondaysFirstOfMonth(
            MyDate(day = 1, month = 1, year = 1901),
            MyDate(day = 31, month = 12, year = 2000)
        )
    )
}

fun countMondaysFirstOfMonth(from: MyDate, to: MyDate): Int {
    var date = MyDate(day = 31, month = 12, year = 1899)
    var count = 0
    while (date <= to) {
        if (date >= from && date.day == 1) {
            count++
        }
        date = date.nextSunday()
    }
    return count
}

private fun MyDate.nextSunday(): MyDate {
    var nextDay = day + 7
    var nextMonth = month
    var nextYear = year
    if (nextDay > daysOfMonth[month]) {
        nextDay %= daysOfMonth[month]
        nextMonth++
        if (nextMonth > 12) {
            nextMonth = 1
            nextYear++
            updateLeapYear(nextYear)
        }
    }
    return MyDate(nextDay, nextMonth, nextYear)
}

private fun updateLeapYear(year: Int) {
    val days = when {
        year % 400 == 0 -> 29
        year % 100 == 0 -> 28
        year % 4 == 0 -> 29
        else -> 28
    }
    daysOfMonth[2] = days
}

data class MyDate(val day: Int, val month: Int, val year: Int)

private operator fun MyDate.compareTo(other: MyDate): Int {
    val y = year.compareTo(other.year)
    if (y != 0) {
        return y
    }
    val m = month.compareTo(other.month)
    if (m != 0) {
        return m
    }

    return day.compareTo(other.day)
}

private val daysOfMonth = arrayOf(
    0,
    31,
    28,
    31,
    30,
    31,
    30,
    31,
    31,
    30,
    31,
    30,
    31,
)
