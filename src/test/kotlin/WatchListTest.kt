import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate
import java.time.Month

class WatchListTest : StringSpec({

    "WatchList should create read only list of shows" {
        watchList {
            show {
                title = "Doctor Who"
                Rate it "⭐⭐⭐⭐⭐"
                Started on LocalDate.of(2005, Month.MARCH, 26)
            }
            show {
                title = "The Mentalist"
                Rate it "⭐⭐⭐⭐"
                Started on LocalDate.of(2008, Month.SEPTEMBER, 23)
                end = LocalDate.of(2015, Month.FEBRUARY, 18)
            }
            show {
                title = "Grey's Anatomy"
                Started on LocalDate.of(2005, Month.MARCH, 27)
                Rate it "⭐⭐⭐"
            }
        }.run {
            isNullOrEmpty() shouldBe false
            size shouldBe 3
            first { it.title == "Doctor Who" }
                .run {
                    start shouldBe LocalDate.of(2005, Month.MARCH, 26)
                    favorite shouldBe true
                    rating shouldBe Rating[5]
                    hasEnded shouldBe false
                }
            first { it.title == "The Mentalist" }
                .run {
                    start shouldBe LocalDate.of(2008, Month.SEPTEMBER, 23)
                    end shouldBe LocalDate.of(2015, Month.FEBRUARY, 18)
                    favorite shouldBe true
                    rating shouldBe Rating[4]
                    hasEnded shouldBe true
                }
            first { it.title == "Grey's Anatomy" }
                .run {
                    start shouldBe LocalDate.of(2005, Month.MARCH, 27)
                    favorite shouldBe false
                    rating shouldBe Rating[3]
                    hasEnded shouldBe false
                }
        }
    }
})
