package io.blocks.core.dsl

import io.blocks.core.Bindings
import io.blocks.core.error.UnsupportedCommand
import io.blocks.core.interfaces.*
import kotlin.reflect.KClass

class BlockDslBuilder<C, P, R> : Dsl<C, P, R>, Builder<Block<C, P, R>> {
	private val memory = mutableListOf<Result<R>>()
	private val mappings = mutableMapOf<KClass<*>, Mapper<Command, R>>()

	private val Command.mapped
		get() = mappings[this::class]?.map(this)

	private val forwarder = Forwarder<Command> {
		when (val m = it.mapped) {
			is Any -> Result.success(m)
			else   -> Result.failure(UnsupportedCommand of it)
		}.also { result -> memory.add(result) }
	}

	override fun settings(block: Forwarder<Command>.() -> Unit) =
		block { forwarder.forward(it) }

	override fun mapping(to: Mapping<Command, R>.() -> Unit): Unit =
		to { mapper -> mappings[this] = mapper }

	private val builder = BlockBuilder<C, P, R>()

	override val context: Bindings.Of<C>
		get() = with(builder) { Bindings.Of { context = it } }

	override val parameter: Bindings.Of<P>
		get() = with(builder) { Bindings.Of { parameter = it } }

	override fun build() = with(builder) {
		results = memory
		build()
	}
}
