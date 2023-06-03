package io.blocks.examples.watchlist

import java.time.LocalDate

data class Show(
	val name: String,
	val start: LocalDate,
	val end: LocalDate? = null,
	val rating: Rating? = null
) {
	val favorite: Boolean = rating?.isFavorite ?: false
	val hasEnded = end != null
}
