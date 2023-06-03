package io.blocks.core.blockbuilder

data class Config<C, O, M>(
	val commands: C,
	val operations: O,
	val memory: Collection<M>
)
