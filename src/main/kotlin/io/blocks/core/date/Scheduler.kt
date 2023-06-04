package io.blocks.core.date

import java.time.Month

@Suppress("FunctionName")
fun interface Scheduler {
	fun schedule(month: Int, year: Int)
	private infix fun Month.of(year: Int) = schedule(value, year)
	infix fun January(year: Int) = Month.JANUARY of year
	infix fun February(year: Int) = Month.FEBRUARY of year
	infix fun March(year: Int) = Month.MARCH of year
	infix fun April(year: Int) = Month.APRIL of year
	infix fun May(year: Int) = Month.MAY of year
	infix fun June(year: Int) = Month.JUNE of year
	infix fun July(year: Int) = Month.JULY of year
	infix fun August(year: Int) = Month.AUGUST of year
	infix fun September(year: Int) = Month.SEPTEMBER of year
	infix fun October(year: Int) = Month.OCTOBER of year
	infix fun November(year: Int) = Month.NOVEMBER of year
	infix fun December(year: Int) = Month.DECEMBER of year
}
