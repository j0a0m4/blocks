import Show.ShowBuilder
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

class ShowTest : StringSpec({
	"Should throw when builder provides null value for required fields" {
		forAll(
			row(object : ShowBuilder() {
				init {
					title = "Lucifer"
				}
			}),
			row(object : ShowBuilder() {
				init {
					start = LocalDate.MIN
				}
			}),
			row(object : ShowBuilder() {})
		) { assertThrows<RequiredField> { Show(it) } }
	}

	"Should not throw when builder provides values for required fields" {
		forAll(
			row(object : ShowBuilder() {
				init {
					title = "House"
					start = LocalDate.MIN
				}
			}),
			row(object : ShowBuilder() {
				init {
					title = "House"
					start = LocalDate.MIN
					rating = Rating[3]
				}
			}),
			row(object : ShowBuilder() {
				init {
					title = "House"
					start = LocalDate.MIN
					end = LocalDate.MAX
					rating = Rating[3]
				}
			})
		) { assertDoesNotThrow { Show(it) } }
	}
})
