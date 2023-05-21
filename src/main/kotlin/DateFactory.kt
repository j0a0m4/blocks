import java.time.LocalDate

fun interface On {
    infix fun on(day: Int): DateFactory
}

class DateFactory(
    private val day: Int, private
    val accept: (LocalDate) -> Unit
) {
    private fun setScheduledOn(month: Int, year: Int) {
        accept(LocalDate.of(year, month, day))
    }

    infix fun January(year: Int) = setScheduledOn(1, year)
    infix fun February(year: Int) = setScheduledOn(2, year)
    infix fun March(year: Int) = setScheduledOn(3, year)
    infix fun April(year: Int) = setScheduledOn(4, year)
    infix fun May(year: Int) = setScheduledOn(5, year)
    infix fun June(year: Int) = setScheduledOn(6, year)
    infix fun July(year: Int) = setScheduledOn(7, year)
    infix fun August(year: Int) = setScheduledOn(8, year)
    infix fun September(year: Int) = setScheduledOn(9, year)
    infix fun October(year: Int) = setScheduledOn(10, year)
    infix fun November(year: Int) = setScheduledOn(11, year)
    infix fun December(year: Int) = setScheduledOn(12, year)
}