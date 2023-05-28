import Show.ShowBuilder
import ShowDsl.Name
import ShowDsl.Rate
import java.time.LocalDate

data class Show(
	val name: String,
	val start: LocalDate,
	val end: LocalDate? = null,
	val rating: Rating? = null
) {
	constructor(b: ShowBuilder) : this(
		b.title ?: throw RequiredField of "name",
		b.start ?: throw RequiredField of "start",
		b.end,
		b.rating
	)

	abstract class ShowBuilder : Builder<Show> {
		var title: String? = null
		var start: LocalDate? = null
		var end: LocalDate? = null
		var rating: Rating? = null
		override fun build() = Show(this)
	}

	val favorite: Boolean = rating?.isFavorite ?: false
	val hasEnded = end != null
}

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

class ShowDslBuilder : ShowDsl, ShowBuilder() {
	override val started = LocalDatePicker { start = it }
	override val ended = LocalDatePicker { end = it }
	override val name = Name { title = it }
	override val rate = Rate { rating = Rating[it] }
}
