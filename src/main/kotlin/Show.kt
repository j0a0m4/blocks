import java.time.Year

data class Show(val title: String, val year: Year, val favorite: Boolean = false, val rating: Rating?) {
    constructor(name: String, year: Int, favorite: Boolean = false, rating: Rating?) :
            this(name, Year.of(year), favorite, rating)
    constructor(b: ShowBuilder): this(b.title, b.year, b.favorite, b.rating)
}

interface ShowBuilder {
    var title: String
    var year: Int
    var favorite: Boolean
    var rating: Rating?
}

fun show(init: ShowBuilder.() -> Unit): Show =
    object : ShowBuilder {
        override var title: String = ""
        override var year: Int = 0
        override var favorite: Boolean = false
        override var rating: Rating? = null
    }.apply(init)
        .run(::Show)
