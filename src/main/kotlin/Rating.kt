enum class Rating(val value: Int) {
    ONE_STAR(1),
    TWO_STARS(2),
    THREE_STARS(3),
    FOUR_STARS(4),
    FIVE_STARS(5),
    ;

    companion object {
        infix fun of(value: Int): Rating =
            Rating.values().find { it.value == value }
                ?: throw IllegalArgumentException()
    }

//    operator fun plus(increment: Rating): Rating =
//        with(value + increment.value) {
//            if (isBiggerThan(5)) throw UnsupportedOperationException()
//            else convertToRating()
//        }

    operator fun plus(increment: Rating): Rating =
        (value + increment.value).let {
            if (it > 5) {
                throw UnsupportedOperationException()
            }
            Rating of it
        }

    override fun toString(): String = "⭐".repeat(value)

}

private fun Int.convertToRating() = Rating of this

private infix fun Int.isBiggerThan(i: Int) =
    compareTo(i).isPositive()

private fun Int.isPositive() = this > 0
