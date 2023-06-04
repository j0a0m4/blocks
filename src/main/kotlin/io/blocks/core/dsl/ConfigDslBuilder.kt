package io.blocks.core.dsl

import io.blocks.core.Bindings
import io.blocks.core.interfaces.*

class ConfigDslBuilder<C, P, R> : ConfigDsl<C, P, R>, Builder<BlockConfig<C, P, R>> {
	private val builder = BlockBuilder<C, P, R>()
	private val memory = mutableListOf<R>()
	private var mapper: Mapper<Command, R>? = null

	override fun settings(block: Forwarder<Command>.() -> Unit) =
		block { command ->
			mapper?.map(command)
				?.also { memory.add(it) }
		}

	override fun mapping(mapper: Mapper<Command, R>) {
		this.mapper = mapper
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
