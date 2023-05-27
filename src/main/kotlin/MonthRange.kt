import java.time.Month

class MonthRange(private val range: IntRange = (1..12)) : Iterable<Month> {

	constructor(start: Month, endInclusive: Month) : this(start.value..endInclusive.value)

	constructor(start: Int, endInclusive: Int) : this(start..endInclusive)

	constructor(start: String, endInclusive: String) : this(
		Month.valueOf(start).value..Month.valueOf(endInclusive).value
	)

	override fun iterator() = range.map(Month::of).iterator()
}