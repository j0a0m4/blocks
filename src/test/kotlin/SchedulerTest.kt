import io.blocks.core.date.MonthRange
import io.blocks.core.date.month
import io.blocks.core.date.*
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import java.time.Month
import java.time.Month.*
import kotlin.reflect.KFunction1

internal typealias MonthScheduler = KFunction1<Int, Unit>

class SchedulerTest {
	@TestFactory
	fun `Should schedule on every month`() =
		(1994..2014).associateWith { (1..12).month }
			.flatMap(::toYearMonthKey)
			.associateWith(::schedulerAssertions)
			.mapValues(::toMonthScheduler)
			.map(::toDynamicTest)

	data class YearMonthKey(val year: Int, val month: Month)

	private infix fun Int.to(month: Month) = YearMonthKey(this, month)

	private fun toYearMonthKey(entry: Map.Entry<Int, MonthRange>): List<YearMonthKey> =
		entry.let { (year, months) -> months.map { year to it } }

	private fun schedulerAssertions(expected: YearMonthKey) = Scheduler { month, year ->
		month shouldBe expected.month.value
		year shouldBe expected.year
	}

	private infix fun Scheduler.of(month: Month): MonthScheduler = when (month) {
		JANUARY   -> ::January
		FEBRUARY  -> ::February
		MARCH     -> ::March
		APRIL     -> ::April
		MAY       -> ::May
		JUNE      -> ::June
		JULY      -> ::July
		AUGUST    -> ::August
		SEPTEMBER -> ::September
		OCTOBER   -> ::October
		NOVEMBER  -> ::November
		DECEMBER  -> ::December
	}

	private fun toMonthScheduler(entry: Map.Entry<YearMonthKey, Scheduler>): MonthScheduler =
		entry.let { (key, scheduler) -> scheduler of key.month }

	private fun toDynamicTest(entry: Map.Entry<YearMonthKey, MonthScheduler>): DynamicTest =
		entry.let { (key, setMonthWith) ->
			"Month: ${key.month}, Year: ${key.year}" { setMonthWith(key.year) }
		}
}
