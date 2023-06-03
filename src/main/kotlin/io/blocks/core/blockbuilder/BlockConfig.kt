package io.blocks.core.blockbuilder

import io.blocks.common.*

interface BlockConfig<C, O, M> {
	val bind: BlockConfig<C, O, M>
		get() = this

	infix fun binder(binder: Dispatcher<Command>.() -> Unit)
	infix fun commands(commands: C)
	infix fun operations(operations: O)
	infix fun mapper(mapper: Mapper<Command, M>)
}
