import Rating.Three
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate
import java.time.Month

class ShowTest : StringSpec({
 "show DSL constructor should create new instance of Show" {
     shouldNotThrowAny {
         show {
             title = "Elite"
             start = LocalDate.of(2018, Month.OCTOBER, 5)
             rating = Three
         }.run {
             title.shouldBe("Elite")
             start shouldBe LocalDate.of(2018, Month.OCTOBER, 5)
             rating shouldBe Three
             hasEnded shouldBe false
         }
     }
 }
})
