package io.blocks.core.dsl

data class BlockConfig<C, P, R>(
	val context: C,
	val parameter: P,
	val results: Collection<R>
)
