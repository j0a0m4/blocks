import Rating.*
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate
import java.time.Month

class WatchListTest : StringSpec({


    "WatchList should create read only list of shows" {
        watchList {
            show {
                title = "Doctor Who"
                start = LocalDate.of(2005, Month.MARCH, 26)
                favorite = true
                rating = Five
            } + show {
                title = "The Mentalist"
                start = LocalDate.of(2008, Month.SEPTEMBER, 23)
                end = LocalDate.of(2015, Month.FEBRUARY, 18)
                favorite = true
                rating = Four
            } + show {
                title = "Grey's Anatomy"
                start = LocalDate.of(2005, Month.MARCH, 27)
                favorite = false
                rating = Three
            }
        }.run {
            isNullOrEmpty() shouldBe false
            size shouldBe 3
            get(0).title shouldBe "Doctor Who"
            get(1).title shouldBe "The Mentalist"
            get(2).title shouldBe "Grey's Anatomy"
        }
    }

    "WatchList should create read only list of shows 2" {
        watchList {
            show {
                title = "Billions"
                start = LocalDate.of(2016, Month.JANUARY, 17)
                favorite = true
                rating = Five
            } + show {
                title = "Jane the Virgin"
                start = LocalDate.of(2014, Month.OCTOBER, 13)
                end = LocalDate.of(2019, Month.JULY, 31)
                rating = Two
            }
        }.run {
            isNullOrEmpty() shouldBe false
            size shouldBe 2
            first().title shouldBe "Billions"
            last().title shouldBe "Jane the Virgin"
        }
    }
})
