import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.function.Executable
import java.time.LocalDate
import java.time.Month
import java.time.Month.*

private infix fun Month.testWith(expectedYear: Int) = Scheduler { month, year ->
    month shouldBe value
    year shouldBe expectedYear
}

internal infix fun String.test(executable: Executable) =
    DynamicTest.dynamicTest(this, executable)

class DateCreatorTest : StringSpec({

    "Should return picked LocalDate" {
        LocalDatePicker { it shouldBe LocalDate.of(2000, JANUARY, 20) }
            .let { it on 20 January 2000 }
        LocalDatePicker { it shouldBe LocalDate.of(2001, FEBRUARY, 19) }
            .let { it on 19 February 2001 }
        LocalDatePicker { it shouldBe LocalDate.of(2002, MARCH, 1) }
            .let { it on 1 March 2002 }
    }

}) {

    @TestFactory
    fun `Should schedule on every month`() = (1..12).map(Month::of)
        .flatMap { month -> (2022..2062).map { it to month } }
        .map { (year, month) -> Triple(year, month, month testWith year) }
        .map { (year, month, scheduler) -> scheduler.dynamicTestWith(month, year) }

    private fun Scheduler.dynamicTestWith(month: Month, year: Int) =
        when (month) {
            JANUARY -> ::January
            FEBRUARY -> ::February
            MARCH -> ::March
            APRIL -> ::April
            MAY -> ::May
            JUNE -> ::June
            JULY -> ::July
            AUGUST -> ::August
            SEPTEMBER -> ::September
            OCTOBER -> ::October
            NOVEMBER -> ::November
            DECEMBER -> ::December
        }.let {
            "On $month $year" test { it(year) }
        }
}

