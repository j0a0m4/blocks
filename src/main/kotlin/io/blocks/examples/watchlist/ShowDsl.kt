package io.blocks.examples.watchlist

import io.blocks.core.date.LocalDatePicker

interface ShowDsl {
	val started: LocalDatePicker
	val ended: LocalDatePicker

	val name: Name

	fun interface Name {
		infix fun of(name: String)
	}

	val rate: Rate

	fun interface Rate {
		infix fun it(emoji: StarEmoji)
	}
}
