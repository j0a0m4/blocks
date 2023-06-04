package io.blocks.examples.watchlist

import io.blocks.core.date.LocalDatePicker
import io.blocks.examples.watchlist.ShowDsl.Name
import io.blocks.examples.watchlist.ShowDsl.Rate

class ShowDslBuilder : ShowDsl, ShowBuilder() {
	override val started = LocalDatePicker { start = it }
	override val ended = LocalDatePicker { end = it }
	override val name = Name { title = it }
	override val rate = Rate { rating = Rating[it] }
}
