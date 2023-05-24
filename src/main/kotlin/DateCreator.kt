import java.time.LocalDate
import java.time.Month
import java.time.Month.*

fun interface LocalDatePicker {
    fun action(date: LocalDate)
    infix fun on(day: Int): Scheduler = Scheduler { month, year ->
        LocalDate.of(year, month, day).run(::action)
    }
}

fun interface Scheduler {
    fun schedule(month: Month, year: Int)
    fun setMonthAndYear(month: Month, year: Int) = schedule(month, year)
    infix fun January(year: Int) = setMonthAndYear(JANUARY, year)
    infix fun February(year: Int) = setMonthAndYear(FEBRUARY, year)
    infix fun March(year: Int) = setMonthAndYear(MARCH, year)
    infix fun April(year: Int) = setMonthAndYear(APRIL, year)
    infix fun May(year: Int) = setMonthAndYear(MAY, year)
    infix fun June(year: Int) = setMonthAndYear(JUNE, year)
    infix fun July(year: Int) = setMonthAndYear(JULY, year)
    infix fun August(year: Int) = setMonthAndYear(AUGUST, year)
    infix fun September(year: Int) = setMonthAndYear(SEPTEMBER, year)
    infix fun October(year: Int) = setMonthAndYear(OCTOBER, year)
    infix fun November(year: Int) = setMonthAndYear(NOVEMBER, year)
    infix fun December(year: Int) = setMonthAndYear(DECEMBER, year)
}
