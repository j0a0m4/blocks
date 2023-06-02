fun interface BlockBuilder<C, O, M> {
	fun BlockConfig<C, O, M>.configuration()

	infix operator fun invoke(bindTo: C.(O) -> Unit): Collection<M> =
		BlockConfigBuilder<C, O, M>()
			.apply { configuration() }
			.build()
			.apply { commands.bindTo(operations) }
			.memory
}

interface Command

fun interface Mapper<C, M> {
	infix fun map(command: C): M
}

fun interface Dispatcher {
	infix fun dispatches(command: Command)
}

class UnsupportedCommand(override val message: String?) : Exception() {
	companion object {
		infix fun of(obj: Any) =
			UnsupportedCommand("$obj of ${obj::class} doesn't have any mappings")
	}
}

data class Config<C, O, M>(
	val commands: C,
	val operations: O,
	val memory: Collection<M>
)

abstract class ConfigBuilder<C, O, M> : Builder<Config<C, O, M>> {
	protected var commands: C? = null
	protected var operations: O? = null
	protected var mapper: Mapper<Command, M>? = null
	private val memory = mutableListOf<M>()

	fun addCommandToMemory(command: Command) {
		mapper?.map(command)?.also(memory::add)
	}

	override fun build(): Config<C, O, M> =
		Config(
			commands ?: throw RequiredField of "commands",
			operations ?: throw RequiredField of "operations",
			memory.takeIf { it.isEmpty() } ?: throw RequiredField of "memory"
		)
}

interface BlockConfig<C, O, M> {
	val bind: BlockConfig<C, O, M>
		get() = this

	infix fun binder(dispatcher: Dispatcher.() -> Unit)
	infix fun commands(commands: C)
	infix fun operations(operations: O)
	infix fun mapper(mapper: Mapper<Command, M>)
}

class BlockConfigBuilder<C, O, M> : BlockConfig<C, O, M>, ConfigBuilder<C, O, M>() {
	override fun binder(dispatcher: Dispatcher.() -> Unit) {
		dispatcher(::addCommandToMemory)
	}

	override fun commands(commands: C) {
		super.commands = commands
	}

	override fun operations(operations: O) {
		super.operations = operations
	}

	override fun mapper(mapper: Mapper<Command, M>) {
		super.mapper = mapper
	}
}
