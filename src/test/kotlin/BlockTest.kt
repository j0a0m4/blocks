import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContainExactly

enum class Direction { Left, Right }
enum class Speed { Fast, Slow }

object DirectionSpeedReceiver {
	val left = Direction.Left
	val right = Direction.Right
	val fast = Speed.Fast
	val slow = Speed.Slow
}

interface RobotController<T> {
	val memory: T
	infix fun turns(direction: Direction)
	infix fun runs(speed: Speed)
}

class BlockTest : BehaviorSpec({
	Given("A robot with memory and operations implemented") {
		class Robot : RobotController<Set<String>> {
			private val state: MutableSet<String> = mutableSetOf()

			override val memory
				get() = state.toSet()

			override infix fun turns(direction: Direction) {
				state += "Robot turns $direction"
			}

			override infix fun runs(speed: Speed) {
				state += "Robot runs $speed"
			}
		}
		When("the user creates a block from a receiver and a controller") {
			val robot = Block(DirectionSpeedReceiver, Robot() as RobotController<Set<String>>)
			Then("it should record operations in its memory") {
				robot {
					it turns left
					it runs fast
					it turns right
					it runs slow
				}.run {
					memory shouldContainExactly setOf(
						"Robot turns Left", "Robot runs Fast",
						"Robot turns Right", "Robot runs Slow"
					)
				}
			}
		}
	}
})