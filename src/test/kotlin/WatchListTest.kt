import Rating.*
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate
import java.time.Month


class WatchListTest : StringSpec({

    "WatchList should create read only list of shows" {
        watchList {
            add(show {
                title = "Doctor Who"
                start = LocalDate.of(2005, Month.MARCH, 26)
                favorite = true
                rating = FIVE_STARS
            })
            add(show {
                title = "The Mentalist"
                start = LocalDate.of(2008, Month.SEPTEMBER, 23)
                end = LocalDate.of(2015, Month.FEBRUARY, 18)
                favorite = true
                rating = FOUR_STARS
            })
            add(show {
                title = "Grey's Anatomy"
                start = LocalDate.of(2005, Month.MARCH, 27)
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
