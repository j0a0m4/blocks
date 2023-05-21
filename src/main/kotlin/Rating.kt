typealias StarEmoji = String

enum class Rating(val value: Int, val stars: StarEmoji) {
    One(1, "⭐"),
    Two(2, "⭐⭐"),
    Three(3, "⭐⭐⭐"),
    Four(4, "⭐⭐⭐⭐"),
    Five(5, "⭐⭐⭐⭐⭐"),
    ;

    val star = stars

    infix operator fun plus(increment: Rating): Rating =
        Rating[value + increment.value]
            ?: throw UnsupportedOperationException()

    companion object {
        infix operator fun invoke(predicate: (Rating) -> Boolean): Rating? =
            Rating.values().find { predicate(it) }

        infix operator fun <P> contains(property: P): Boolean =
            Rating[property] != null

        infix operator fun <P> get(property: P): Rating? = when (property) {
            is StarEmoji -> Rating { property == it.stars }
            is Int -> Rating { property == it.value }
            else -> throw NoSuchFieldError()
        }
    }
}
