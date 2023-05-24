import java.time.LocalDate

data class Show(
    val title: String,
    val start: LocalDate,
    val end: LocalDate? = null,
    val favorite: Boolean = false,
    val rating: Rating? = null
) {
    val hasEnded = end != null

    constructor(builder: Builder) : this(
        builder.title ?: throw IllegalArgumentException("Title is mandatory field"),
        builder.start ?: throw IllegalArgumentException("Start is mandatory field"),
        builder.end,
        builder.favorite,
        builder.rating
    )

    companion object {
        data class Builder(
            var title: String? = null,
            var start: LocalDate? = null,
            var end: LocalDate? = null,
            var favorite: Boolean = false,
            var rating: Rating? = null
        ) {
            val started = LocalDatePicker { start = it }
            val ended = LocalDatePicker { end = it }

            val rate = this
            infix fun it(emoji: StarEmoji) = Rating[emoji].let {
                rating = it
                favorite = it.isFavorite
            }
        }
    }
}

