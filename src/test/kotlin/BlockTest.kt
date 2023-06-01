import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactly

class BlockTest : BehaviorSpec({
	Given("A robot with memory and operations implemented") {
		When("the user creates a block from a receiver and a controller") {
			Then("it should record operations in its memory") {
				Robot {
					it turns left
					it runs fast
					it turns right
					it runs slow
				}.run {
					this shouldContainExactly setOf(
						"Robot turns Left", "Robot runs Fast",
						"Robot turns Right", "Robot runs Slow"
					)
				}
			}
		}
	}
})