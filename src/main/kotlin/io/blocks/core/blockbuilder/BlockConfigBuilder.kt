package io.blocks.core.blockbuilder

import io.blocks.common.*

class BlockConfigBuilder<C, O, M> : BlockConfig<C, O, M>, ConfigBuilder<C, O, M>() {
	override fun binder(binder: Dispatcher<Command>.() -> Unit) {
		binder(::addCommandToMemory)
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
