import java.time.LocalDate
import java.time.Month
import java.time.Month.*

fun interface LocalDatePicker {
    fun accept(date: LocalDate)
    infix fun on(day: Int) = Scheduler { month, year ->
        LocalDate.of(year, month, day).run(::accept)
    }
}

fun interface Scheduler {
    fun schedule(month: Int, year: Int)
    private infix fun Month.of(year: Int) {
        schedule(value, year)
    }

    infix fun January(year: Int) = JANUARY of year
    infix fun February(year: Int) = FEBRUARY of year
    infix fun March(year: Int) = MARCH of year
    infix fun April(year: Int) = APRIL of year
    infix fun May(year: Int) = MAY of year
    infix fun June(year: Int) = JUNE of year
    infix fun July(year: Int) = JULY of year
    infix fun August(year: Int) = AUGUST of year
    infix fun September(year: Int) = SEPTEMBER of year
    infix fun October(year: Int) = OCTOBER of year
    infix fun November(year: Int) = NOVEMBER of year
    infix fun December(year: Int) = DECEMBER of year
}
