fun interface Processor<R, C, M> {
	fun EngineDsl<R, C, M>.engine()
	infix operator fun invoke(block: R.(C) -> Unit): Collection<M> =
		EngineDslBuilder<R, C, M>()
			.apply { engine() }
			.build()
			.apply { receiver.block(controller) }
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

interface EngineDsl<R, C, M> : Dispatcher<Any> {
	infix fun receiver(block: Supplier<R>)
	infix fun controller(block: Supplier<C>)
	infix fun dispatcher(block: Mapper<Any, M>)
}

data class Engine<R, C, M>(
	val receiver: R,
	val controller: C,
	val memory: Collection<M>
)

abstract class EngineBuilder<R, C, M> : Builder<Engine<R, C, M>> {
	protected var receiver: R? = null
	protected var controller: C? = null
	protected var action: Mapper<Any, M>? = null
	private val memory: MutableSet<M> = mutableSetOf()

	fun accept(event: Any) {
		action
			?.map(event)
			?.let(memory::add)
	}

	override fun build(): Engine<R, C, M> =
		Engine(
			receiver ?: throw RequiredField of "receiver",
			controller ?: throw RequiredField of "controller",
			memory.takeIf { it.isEmpty() } ?: throw RequiredField of "memory"
		)
}

class EngineDslBuilder<R, C, M> : EngineDsl<R, C, M>, EngineBuilder<R, C, M>() {
	override fun dispatch(event: Any) {
		super.accept(event)
	}

	override fun dispatcher(block: Mapper<Any, M>) {
		super.action = block
	}

	override fun receiver(block: Supplier<R>) {
		super.receiver = block.invoke()
	}

	override fun controller(block: Supplier<C>) {
		super.controller = block.invoke()
	}
}
