import Show.Companion.Builder
import java.time.LocalDate

data class Show(
    val title: String,
    val start: LocalDate,
    val end: LocalDate?,
    val favorite: Boolean = false,
    val rating: Rating?
) {
    val hasEnded = end != null

    constructor(b: Builder) : this(b.title, b.start, b.end, b.favorite, b.rating)

    companion object {
        interface Builder {
            var title: String
            var start: LocalDate
            var end: LocalDate?
            var favorite: Boolean
            var rating: Rating?
        }
    }
}

fun show(init: Builder.() -> Unit): Show =
    object : Builder {
        override var title: String = ""
        override var start: LocalDate = LocalDate.MIN
        override var end: LocalDate? = null
        override var favorite: Boolean = false
        override var rating: Rating? = null
    }.apply(init)
        .run(::Show)
