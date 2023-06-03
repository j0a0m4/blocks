package io.blocks.core.datecreator

import io.blocks.common.Consumer
import java.time.LocalDate

fun interface LocalDatePicker : Consumer<LocalDate> {
	infix fun on(day: Int) = Scheduler { month, year ->
		LocalDate.of(year, month, day).run(::accept)
	}
}
