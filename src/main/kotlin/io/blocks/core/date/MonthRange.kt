package io.blocks.core.date

import java.time.Month

class MonthRange(private val range: IntProgression = (1..12)) : Iterable<Month>, ClosedRange<Month> {
	override val endInclusive: Month = Month.of(range.last)
	override val start: Month = Month.of(range.first)

	constructor(start: Month, endInclusive: Month) : this(start.value..endInclusive.value)

	constructor(start: String, endInclusive: String) : this(
		Month.valueOf(start.uppercase()),
		Month.valueOf(endInclusive.uppercase())
	)

	override fun iterator() = range.map(Month::of).iterator()
}

val IntProgression.month: MonthRange
	get() = MonthRange(this)

infix operator fun Month.rangeTo(endInclusive: Month) =
	MonthRange(this, endInclusive)
