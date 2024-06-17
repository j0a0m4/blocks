import io.blocks.core.block.DslBlock
import io.blocks.core.error.UnsupportedCommand
import io.blocks.core.interfaces.Command
import io.blocks.core.interfaces.Dispatcher
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

enum class Direction : Command { Left, Right; }
enum class Speed : Command { Fast, Slow }

object RobotCommands {
	val left = Direction.Left
	val right = Direction.Right
	val fast = Speed.Fast
	val slow = Speed.Slow
}

fun interface RobotOperations : Dispatcher<Command> {
	infix fun turns(direction: Direction) = dispatch(direction)
	infix fun runs(speed: Speed) = dispatch(speed)
}

enum class Fake : Command { Unsupported }

class BlockTest : BehaviorSpec({
	Given("A DslBlock with settings and mapping configured") {
		@Suppress("LocalVariableName")
		val Robot = DslBlock {
			settings {
				context of RobotCommands
				parameter of RobotOperations { forward(it) }
			}
			mapping {
				Direction::class to { "Robot turns $it" }
				Speed::class to { "Robot runs $it" }
			}
		}
		When("the user send commands that are mapped") {
			Then("it should return successful results") {
				Robot {
					it turns left
					it runs fast
					it turns right
					it runs slow
				}.run {
					map { it.getOrNull() } shouldContainExactly setOf(
						"Robot turns Left",
						"Robot runs Fast",
						"Robot turns Right",
						"Robot runs Slow"
					)
				}
			}
		}
		When("the user send commands that are not mapped") {
			Then("it should return a UnsupportedCommand failure") {
				Robot { it dispatch Fake.Unsupported }.run {
					forEach {
						it.isFailure shouldBe true
						(it.exceptionOrNull() is UnsupportedCommand) shouldBe true
						it.exceptionOrNull()
							?.shouldHaveMessage("Unsupported of class Fake doesn't have any mappings")
					}
				}
			}
		}
	}
})
