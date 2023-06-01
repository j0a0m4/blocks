enum class Direction { Left, Right; }
enum class Speed { Fast, Slow }

object RobotReceiver {
	val left = Direction.Left
	val right = Direction.Right
	val fast = Speed.Fast
	val slow = Speed.Slow
}

fun interface RobotController : Dispatcher<Any> {
	infix fun turns(direction: Direction) = dispatch(direction)
	infix fun runs(speed: Speed) = dispatch(speed)
}

val Robot = Processor {
	receiver { RobotReceiver }
	controller { RobotController { dispatch(it) } }
	dispatcher {
		when (it) {
			is Direction -> "Robot turns $it"
			is Speed     -> "Robot runs $it"
			else         -> throw UnsupportedOperationException()
		}
	}
}
