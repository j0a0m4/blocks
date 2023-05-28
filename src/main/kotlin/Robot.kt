enum class Direction { Left, Right }
enum class Speed { Fast, Slow }

object DirectionSpeed {
	val left = Direction.Left
	val right = Direction.Right
	val fast = Speed.Fast
	val slow = Speed.Slow
}

interface RobotDsl<T> {
	val memory: T
	infix fun turns(direction: Direction)
	infix fun runs(speed: Speed)
}

class Robot : RobotDsl<Set<String>> {
	private val state: MutableSet<String> = mutableSetOf()

	override val memory
		get() = state.toSet()

	override infix fun turns(direction: Direction) {
		state += "Robot turns $direction"
	}

	override infix fun runs(speed: Speed) {
		state += "Robot runs $speed"
	}

	companion object: ReceiverController<DirectionSpeed, RobotDsl<Set<String>>> {
		override fun invoke(block: DirectionSpeed.(RobotDsl<Set<String>>) -> Unit) =
			Robot().apply { DirectionSpeed.block(this) }
	}
}
