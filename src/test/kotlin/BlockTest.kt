import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.assertThrows

enum class Direction : Command { Left, Right; }
enum class Speed : Command { Fast, Slow }

object RobotCommands {
	val left = Direction.Left
	val right = Direction.Right
	val fast = Speed.Fast
	val slow = Speed.Slow
}

fun interface RobotOperations : Dispatcher {
	infix fun turns(direction: Direction) = dispatches(direction)
	infix fun runs(speed: Speed) = dispatches(speed)
}

enum class FakeCommand : Command { Unsupported }

class BlockTest : BehaviorSpec({
	Given("A BlockBuilder with binder and mapper configured") {
		val Robot = BlockBuilder {
			binder {
				bind commands RobotCommands
				bind operations RobotOperations(::dispatches)
			}
			mapper {
				when (it) {
					is Direction -> "Robot turns $it"
					is Speed     -> "Robot runs $it"
					else         -> throw UnsupportedCommand of it
				}
			}
		}
		When("the user send commands supported in mapper") {
			Then("it should map and record it") {
				Robot {
					it turns left
					it runs fast
					it turns right
					it runs slow
				}.run {
					this shouldContainExactly setOf(
						"Robot turns Left",
						"Robot runs Fast",
						"Robot turns Right",
						"Robot runs Slow"
					)
				}
			}
		}
		When("the user send commands that are not supported") {
			Then("it should return a UnsupportedCommand exception") {
				assertThrows<UnsupportedCommand> {
					Robot { it dispatches FakeCommand.Unsupported }
				}.shouldHaveMessage("Unsupported of class FakeCommand doesn't have any mappings")
			}
		}
	}
})
