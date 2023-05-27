import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactly
import java.time.Month.*

class MonthRangeTest : BehaviorSpec({
	Given("a start month and an end month") {
		When("I directly create a month range from it") {
			Then("it should contain expected months") {
				MonthRange(MARCH, JUNE).run {
					this shouldContainExactly setOf(MARCH, APRIL, MAY, JUNE)
				}
			}
		}
		And("I write them as strings") {
			When("I directly create a month range from it") {
				Then("it should contain expected months") {
					MonthRange("September", "November").run {
						this shouldContainExactly setOf(SEPTEMBER, OCTOBER, NOVEMBER)
					}
				}
			}
		}
		When("I create a month range with dot syntax 'A..B'") {
			Then("it should contain expected months") {
				with(JANUARY..MARCH) {
					this shouldContainExactly setOf(JANUARY, FEBRUARY, MARCH)
				}
			}
		}
	}
	Given("an integer progression") {
		When("I create a month range from it") {
			Then("it should contain expected months") {
				(1..12 step 2).month.run {
					this shouldContainExactly setOf(JANUARY, MARCH, MAY, JULY, SEPTEMBER, NOVEMBER)
				}
			}
		}
	}
})
