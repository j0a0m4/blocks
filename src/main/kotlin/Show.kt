import ShowDsl.Name
import ShowDsl.Rate
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
