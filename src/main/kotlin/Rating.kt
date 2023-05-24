typealias StarEmoji = String

enum class Rating(val value: Int, val stars: StarEmoji) {
    One(1, "⭐"),
    Two(2, "⭐⭐"),
    Three(3, "⭐⭐⭐"),
    Four(4, "⭐⭐⭐⭐"),
    Five(5, "⭐⭐⭐⭐⭐"),
    ;

    val isFavorite: Boolean
        get() = value > 3

    infix operator fun plus(increment: Rating) =
        try {
            Rating[value + increment.value]
        } catch (e: Exception) {
            throw UnsupportedOperationException()
        }

    companion object {
        infix operator fun invoke(predicate: (Rating) -> Boolean) =
            Rating.values().first { predicate(it) }

        infix operator fun get(stars: StarEmoji) = Rating { stars == it.stars }
        infix operator fun get(value: Int) = Rating { value == it.value }
    }
}
