import java.time.LocalDate

data class Show(
    val title: String,
    val start: LocalDate,
    val end: LocalDate? = null,
    val favorite: Boolean = false,
    val rating: Rating? = null
) {
    val hasEnded = end != null

    constructor(b: Builder) : this(b.title, b.start ?: throw IllegalArgumentException(), b.end, b.favorite, b.rating)

    companion object {
        data class Builder(
            var title: String = "",
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
                if (it.value > 3) {
                    favorite = true
                }
            }
        }
    }
}


