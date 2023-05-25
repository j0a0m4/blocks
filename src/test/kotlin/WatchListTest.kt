import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate
import java.time.Month.*

class WatchListTest : StringSpec({

    "WatchList should create read only list of shows" {
        watchList {
            show {
                name of "Doctor Who"
                started on 26 March 2005
                rate it "⭐⭐⭐⭐⭐"
            }
            show {
                name of "The Mentalist"
                started on 23 September 2008
                ended on 18 February 2015
                rate it "⭐⭐⭐⭐"
            }
            show {
                name of "Grey's Anatomy"
                started on 27 March 2005
                rate it "⭐⭐⭐"
            }
        }.run {
            isNullOrEmpty() shouldBe false
            size shouldBe 3
            first { it.name == "Doctor Who" }
                .run {
                    start shouldBe LocalDate.of(2005, MARCH, 26)
                    favorite shouldBe true
                    rating shouldBe Rating[5]
                    hasEnded shouldBe false
                }
            first { it.name == "The Mentalist" }
                .run {
                    start shouldBe LocalDate.of(2008, SEPTEMBER, 23)
                    end shouldBe LocalDate.of(2015, FEBRUARY, 18)
                    favorite shouldBe true
                    rating shouldBe Rating[4]
                    hasEnded shouldBe true
                }
            first { it.name == "Grey's Anatomy" }
                .run {
                    start shouldBe LocalDate.of(2005, MARCH, 27)
                    favorite shouldBe false
                    rating shouldBe Rating[3]
                    hasEnded shouldBe false
                }
        }
    }
})
