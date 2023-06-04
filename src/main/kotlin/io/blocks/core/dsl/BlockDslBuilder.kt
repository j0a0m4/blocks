package io.blocks.core.dsl

import io.blocks.core.Bindings
import io.blocks.core.interfaces.*

class BlockDslBuilder<C, P, R> : Dsl<C, P, R>, Builder<Block<C, P, R>> {
	private val builder = BlockBuilder<C, P, R>()
	private val memory = mutableListOf<R>()
	private var mapper: Mapper<Command, R>? = null

	override fun settings(block: Forwarder<Command>.() -> Unit) = block {
		mapper?.map(it)?.also { memory.add(it) }
	}

	override fun mapping(block: Mapper<Command, R>) {
		mapper = block
	}

	override val context: Bindings.Of<C>
		get() = with(builder) { Bindings.Of { context = it } }

	override val parameter: Bindings.Of<P>
		get() = with(builder) { Bindings.Of { parameter = it } }

	override fun build() = with(builder) {
		results = memory
		build()
	}
}
