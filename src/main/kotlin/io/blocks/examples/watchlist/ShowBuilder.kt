package io.blocks.examples.watchlist

import io.blocks.core.interfaces.Builder
import io.blocks.core.exceptions.RequiredField
import java.time.LocalDate

abstract class ShowBuilder : Builder<Show> {
	protected var title: String? = null
		set(value) = when (value.isNullOrBlank()) {
			true -> throw RequiredField of "name"
			else -> field = value
		}
	protected var start: LocalDate? = null
	protected var end: LocalDate? = null
	protected var rating: Rating? = null

	override fun build() = Show(
		name = title ?: throw RequiredField of "name",
		start = start ?: throw RequiredField of "start",
		end = end,
		rating = rating
	)
}
