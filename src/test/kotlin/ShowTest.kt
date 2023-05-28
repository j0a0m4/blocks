import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

class ShowTest : StringSpec({
	"Should throw when builder provides null values for required fields" {
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
		) { assertThrows<RequiredField> { it.build() } }
	}

	"Should throw when title is empty" {
		assertThrows<RequiredField> {
			object : ShowBuilder() {
				init {
					title = ""
				}
			}
		}
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
		) { assertDoesNotThrow { it.build() } }
	}
})
