package io.blocks.core.dsl

data class Block<C, P, R>(
	private val context: C,
	private val parameter: P,
	private val results: Collection<R>
) {
	infix fun accept(block: C.(P) -> Unit) = run {
		context.block(parameter)
		results
	}
}
