import io.blocks.examples.watchlist.Rating.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.blocks.examples.watchlist.Rating

class RatingTest : StringSpec({
    "watchlist.watchlist.Rating should add compatible ratings" {
        forAll(
            row(One, One, Two),
            row(One, Two, Three),
            row(One, Three, Four),
            row(One, Four, Five),
            row(Two, Two, Four),
            row(Two, Three, Five)
        ) { x, y, result -> x + y shouldBe result }
    }

    "watchlist.watchlist.Rating should not add incompatible ratings and throw error" {
        forAll(
            row(Two, Four),
            row(Two, Five),
            row(Three, Three),
            row(Three, Four),
            row(Three, Five),
            row(Four, Four),
            row(Four, Five),
            row(Five, Five)
        ) { x, y -> shouldThrow<UnsupportedOperationException> { x + y } }
    }

    "watchlist.watchlist.Rating should convert compatible integers into itself" {
        forAll(
            row(1, One),
            row(2, Two),
            row(3, Three),
            row(4, Four),
            row(5, Five)
        ) { num, rating -> Rating[num] shouldBe rating }
    }

    "watchlist.watchlist.Rating should convert compatible strings into itself" {
        forAll(
            row("⭐", One),
            row("⭐⭐", Two),
            row("⭐⭐⭐", Three),
            row("⭐⭐⭐⭐", Four),
            row("⭐⭐⭐⭐⭐", Five)
        ) { emoji, rating -> Rating[emoji] shouldBe rating }
    }
})
