package io.blocks.core.dsl

import io.blocks.core.error.RequiredField
import io.blocks.core.interfaces.Builder

class BlockBuilder<C, P, R> : Builder<Block<C, P, R>> {
	var context: C? = null
	var parameter: P? = null
	var results: Collection<Result<R>>? = emptyList()

	override fun build(): Block<C, P, R> {
		return Block(
			context ?: throw RequiredField of "context",
			parameter ?: throw RequiredField of "parameter",
			results ?: throw RequiredField of "results"
		)
	}
}
