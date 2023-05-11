import Rating.*
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe


class WatchListTest : StringSpec({

    "WatchList should create read only list of shows" {
        watchList {
            add(show {
                title = "Doctor Who"
                year = 2005
                favorite = true
                rating = FIVE_STARS
            })
            add(show {
                title = "The Mentalist"
                year = 2008
                favorite = true
                rating = FOUR_STARS
            })
            add(show {
                title = "Grey's Anatomy"
                year = 2005
                favorite = false
                rating = THREE_STARS
            })
        }.run {
            isNullOrEmpty() shouldBe false
            size shouldBe 3
            get(0).title shouldBe "Doctor Who"
            get(1).title shouldBe "The Mentalist"
            get(2).title shouldBe "Grey's Anatomy"
        }
    }
})
