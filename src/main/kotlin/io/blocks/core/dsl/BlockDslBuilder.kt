package io.blocks.core.dsl

import io.blocks.core.Bindings
import io.blocks.core.error.UnsupportedCommand
import io.blocks.core.interfaces.*
import kotlin.reflect.KClass


class BlockDslBuilder<C, P, R> : Dsl<C, P, R>, Builder<Block<C, P, R>> {
	private val builder = BlockBuilder<C, P, R>()
	private val mappings = mutableMapOf<KClass<*>, Mapper<Command, R>>()
	private val memory = mutableListOf<R>()

	override fun settings(block: Forwarder<Command>.() -> Unit) =
		block { command ->
			mappings[command::class]
				?.map(command)?.also { memory.add(it) }
				?: throw UnsupportedCommand of command
		}

	override fun mapping(to: Mapping<Command, R>.() -> Unit): Unit =
		to { mapper -> mappings[this] = mapper }

	override val context: Bindings.Of<C>
		get() = with(builder) { Bindings.Of { context = it } }

	override val parameter: Bindings.Of<P>
		get() = with(builder) { Bindings.Of { parameter = it } }

	override fun build() = with(builder) {
		results = memory
		build()
	}
}
