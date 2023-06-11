package io.blocks.core.block

import io.blocks.core.error.RequiredField
import io.blocks.core.interfaces.Builder

class BlockBuilder<C, P, R> : Builder<BlockData<C, P, R>> {
	var context: C? = null
	var parameter: P? = null
	var results: Collection<Result<R>>? = emptyList()

	override fun build(): BlockData<C, P, R> {
		return BlockData(
			context ?: throw RequiredField of "context",
			parameter ?: throw RequiredField of "parameter",
			results ?: throw RequiredField of "results"
		)
	}
}
