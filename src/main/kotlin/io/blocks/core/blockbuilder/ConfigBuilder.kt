package io.blocks.core.blockbuilder

import io.blocks.common.*

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
