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
            }
            show {
                title = "The Mentalist"
                start = LocalDate.of(2008, Month.SEPTEMBER, 23)
                end = LocalDate.of(2015, Month.FEBRUARY, 18)
                favorite = true
                rating = Four
            }
            show {
                title = "Grey's Anatomy"
                start = LocalDate.of(2005, Month.MARCH, 27)
                favorite = false
                rating = Three
            }
        }.run {
            isNullOrEmpty() shouldBe false
            size shouldBe 3
            first { it.title == "Doctor Who" }
                .run {
                    start shouldBe LocalDate.of(2005, Month.MARCH, 26)
                    favorite shouldBe true
                    rating shouldBe Rating["⭐⭐⭐⭐⭐"]
                    hasEnded shouldBe false
                }
            first { it.title == "The Mentalist" }
                .run {
                    start shouldBe LocalDate.of(2008, Month.SEPTEMBER, 23)
                    end shouldBe LocalDate.of(2015, Month.FEBRUARY, 18)
                    favorite shouldBe true
                    rating shouldBe Rating["⭐⭐⭐⭐"]
                    hasEnded shouldBe true
                }
            first { it.title == "Grey's Anatomy" }
                .run {
                    start shouldBe LocalDate.of(2005, Month.MARCH, 27)
                    favorite shouldBe false
                    rating shouldBe Rating["⭐⭐⭐"]
                    hasEnded shouldBe false
                }
        }
    }
})
