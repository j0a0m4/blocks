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
            row(object : ShowBuilder {
                override var name: String? = "Lucifer"
                override var start: LocalDate? = null
                override var end: LocalDate? = null
                override var rating: Rating? = null
            }),
            row(object : ShowBuilder {
                override var name: String? = null
                override var start: LocalDate? = LocalDate.MIN
                override var end: LocalDate? = null
                override var rating: Rating? = null
            }),
            row(object : ShowBuilder {
                override var name: String? = null
                override var start: LocalDate? = null
                override var end: LocalDate? = null
                override var rating: Rating? = null
            })
        ) { assertThrows<RequiredField> { Show(it) } }
    }

    "Should not throw when builder provides values for required fields" {
        forAll(
            row(object : ShowBuilder {
                override var name: String? = "House"
                override var start: LocalDate? = LocalDate.MIN
                override var end: LocalDate? = null
                override var rating: Rating? = null
            }),
            row(object : ShowBuilder {
                override var name: String? = "House"
                override var start: LocalDate? = LocalDate.MIN
                override var end: LocalDate? = null
                override var rating: Rating? = Rating[3]
            }),
            row(object : ShowBuilder {
                override var name: String? = "House"
                override var start: LocalDate? = LocalDate.MIN
                override var end: LocalDate? = LocalDate.MAX
                override var rating: Rating? = Rating[3]
            })
        ) { assertDoesNotThrow { Show(it) } }
    }
})
