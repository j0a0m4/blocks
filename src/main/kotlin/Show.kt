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
        b.name ?: throw RequiredField of "name",
        b.start ?: throw RequiredField of "start",
        b.end,
        b.rating
    )

    interface ShowBuilder : Builder<Show> {
        var name: String?
        var start: LocalDate?
        var end: LocalDate?
        var rating: Rating?
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

class ShowDslBuilder : ShowDsl, Builder<Show> {
    private val builder: ShowBuilder = object : ShowBuilder {
        override var name: String? = null
        override var start: LocalDate? = null
        override var end: LocalDate? = null
        override var rating: Rating? = null
    }

    override fun build() = builder.build()
    override val started = LocalDatePicker { builder.start = it }
    override val ended = LocalDatePicker { builder.end = it }
    override val name = Name { builder.name = it }
    override val rate = Rate { builder.rating = Rating[it] }
}
