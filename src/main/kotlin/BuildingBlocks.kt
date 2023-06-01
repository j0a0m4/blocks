fun interface Processor<R, C, M> {
	fun ConfigBlock<R, C, M>.configuration()

	infix operator fun invoke(instructions: R.(C) -> Unit): Collection<M> =
		ConfigBlockBuilder<R, C, M>()
			.apply { configuration() }
			.build()
			.apply { receiver.instructions(controller) }
			.memory
}

fun interface Mapper<I, O> {
	infix fun map(input: I): O
}

fun interface Dispatcher<T> {
	infix fun dispatch(event: T)
}

fun interface ControllerBinder {
	infix fun eventDispatcher(event: Any)
}

data class Config<R, C, M>(
	val receiver: R,
	val controller: C,
	val memory: Collection<M>
)

abstract class ConfigBuilder<R, C, M> : Builder<Config<R, C, M>> {
	protected var receiver: R? = null
	protected var controller: C? = null
	protected var dispatcher: Mapper<Any, M>? = null
	protected val memory = mutableListOf<M>()

	override fun build(): Config<R, C, M> =
		Config(
			receiver ?: throw RequiredField of "receiver",
			controller ?: throw RequiredField of "controller",
			memory.takeIf { it.isEmpty() } ?: throw RequiredField of "memory"
		)
}

interface ConfigBlock<R, C, M> {
	val bind: ConfigBlock<R, C, M>
		get() = this
	val route: String
		get() = ""

	infix fun bindings(controllerBinder: ControllerBinder.() -> C)
	infix fun receiver(receiver: R)
	infix fun dispatcher(mapper: Mapper<Any, M>)
}

class ConfigBlockBuilder<R, C, M> : ConfigBlock<R, C, M>, ConfigBuilder<R, C, M>() {
	override fun dispatcher(mapper: Mapper<Any, M>) {
		dispatcher = mapper
	}

	override fun receiver(receiver: R) {
		this.receiver = receiver
	}
	override fun bindings(controllerBinder: ControllerBinder.() -> C) {
		controller = controllerBinder {
			dispatcher?.map(it)?.let(memory::add)
		}
	}
}
