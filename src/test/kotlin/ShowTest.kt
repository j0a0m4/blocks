import Rating.THREE_STARS
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.Year

class ShowTest : StringSpec({
 "show DSL constructor should create new instance of Show" {
     shouldNotThrowAny {
         show {
             title = "Elite"
             year = 2018
             rating = THREE_STARS
         }.run {
             title shouldBe "Elite"
             year.value shouldBe 2018
             rating shouldBe THREE_STARS
         }
     }
 }
})
