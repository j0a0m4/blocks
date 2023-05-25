import ShowDsl.Name
import ShowDsl.Rate
import java.time.LocalDate

data class Show(
    val name: String,
    val start: LocalDate,
    val end: LocalDate? = null,
    val favorite: Boolean = false,
    val rating: Rating? = null
) {
    val hasEnded = end != null

    constructor(b: Builder) : this(
        b.title ?: throw IllegalArgumentException("Title is mandatory field"),
        b.start ?: throw IllegalArgumentException("Start is mandatory field"),
        b.end,
        b.rating?.isFavorite ?: false,
        b.rating
    )

    interface Builder {
        var title: String?
        var start: LocalDate?
        var end: LocalDate?
        var rating: Rating?
    }
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

class ShowDslBuilder : ShowDsl {
    val builder: Show.Builder = object : Show.Builder {
        override var title: String? = null
        override var start: LocalDate? = null
        override var end: LocalDate? = null
        override var rating: Rating? = null
    }
    override val started = LocalDatePicker { builder.start = it }
    override val ended = LocalDatePicker { builder.end = it }
    override val name = Name { builder.title = it }
    override val rate = Rate { builder.rating = Rating[it] }
}
