import Rating.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class RatingTest : StringSpec({
    "Rating should add compatible ratings" {
        forAll(
            row(ONE_STAR, ONE_STAR, TWO_STARS),
            row(ONE_STAR, TWO_STARS, THREE_STARS),
            row(ONE_STAR, THREE_STARS, FOUR_STARS),
            row(ONE_STAR, FOUR_STARS, FIVE_STARS),
            row(TWO_STARS, TWO_STARS, FOUR_STARS),
            row(TWO_STARS, THREE_STARS, FIVE_STARS)
        ) { x, y, result -> x + y shouldBe result }
    }

    "Rating should not add incompatible ratings and throw error" {
        forAll(
            row(TWO_STARS, FOUR_STARS),
            row(TWO_STARS, FIVE_STARS),
            row(THREE_STARS, THREE_STARS),
            row(THREE_STARS, FOUR_STARS),
            row(THREE_STARS, FIVE_STARS),
            row(FOUR_STARS, FOUR_STARS),
            row(FOUR_STARS, FIVE_STARS),
            row(FIVE_STARS, FIVE_STARS)
        ) { x, y -> shouldThrow<UnsupportedOperationException> { x + y } }
    }

    "Rating should show correct number of stars emoji" {
        forAll(
            row(ONE_STAR, "⭐"),
            row(TWO_STARS, "⭐⭐"),
            row(THREE_STARS, "⭐⭐⭐"),
            row(FOUR_STARS, "⭐⭐⭐⭐"),
            row(FIVE_STARS, "⭐⭐⭐⭐⭐")
        ) { enum, starEmoji -> enum.toString() shouldBe starEmoji }
    }

    "Rating should convert compatible integers into itself" {
        forAll(
            row(1, ONE_STAR),
            row(2, TWO_STARS),
            row(3, THREE_STARS),
            row(4, FOUR_STARS),
            row(5, FIVE_STARS)
        ) { num, ratingEnum -> Rating of num shouldBe ratingEnum }
    }
})
