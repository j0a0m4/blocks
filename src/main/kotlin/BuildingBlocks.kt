fun interface Processor<R, C, M> {
	fun ConfigBlock<R, C, M>.configuration()

	infix operator fun invoke(instructions: R.(C) -> Unit): Collection<M> =
		ConfigBlockBuilder<R, C, M>()
			.apply { configuration() }
			.build()
			.apply { receiver.instructions(controller) }
			.memory
}

fun interface Supplier<T> {
	operator fun invoke(): T
}

fun interface Mapper<I, O> {
	infix fun map(input: I): O
}

fun interface Dispatcher<T> {
	infix fun dispatch(event: T)
}

data class Config<R, C, M>(
	val receiver: R,
	val controller: C,
	val memory: Collection<M>
)

abstract class ConfigBuilder<R, C, M> : Builder<Config<R, C, M>> {
	protected var receiver: R? = null
	protected var controller: C? = null
	protected var action: Mapper<Any, M>? = null
	protected val memory = mutableListOf<M>()

	override fun build(): Config<R, C, M> =
		Config(
			receiver ?: throw RequiredField of "receiver",
			controller ?: throw RequiredField of "controller",
			memory.takeIf { it.isEmpty() } ?: throw RequiredField of "memory"
		)
}

interface ConfigBlock<R, C, M> : Dispatcher<Any> {
	infix fun receiver(block: Supplier<R>)
	infix fun controller(block: Supplier<C>)
	infix fun dispatcher(block: Mapper<Any, M>)
}

class ConfigBlockBuilder<R, C, M> : ConfigBlock<R, C, M>, ConfigBuilder<R, C, M>() {
	override fun dispatch(event: Any) {
		action?.map(event)?.let(memory::add)
	}

	override fun dispatcher(block: Mapper<Any, M>) {
		action = block
	}

	override fun receiver(block: Supplier<R>) {
		receiver = block.invoke()
	}

	override fun controller(block: Supplier<C>) {
		controller = block.invoke()
	}
}
