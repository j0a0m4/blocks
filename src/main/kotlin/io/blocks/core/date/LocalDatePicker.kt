package io.blocks.core.date

import io.blocks.core.interfaces.Consumer
import java.time.LocalDate

fun interface LocalDatePicker : Consumer<LocalDate> {
	infix fun on(day: Int) = Scheduler { month, year ->
		LocalDate.of(year, month, day).run(::accept)
	}
}
