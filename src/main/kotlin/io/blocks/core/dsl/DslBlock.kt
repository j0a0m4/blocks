package io.blocks.core.dsl

import io.blocks.common.*

fun interface DslBlock<C, P, R> {
	fun Dsl<C, P, R>.configuration()

	val block: Block<C, P, R>
		get() = DslBlockBuilder<C, P, R>()
			.apply { configuration() }
			.build()

	infix operator fun invoke(instructions: C.(P) -> Unit): Collection<R> =
		block.apply { context.instructions(parameter) }.results
}

fun interface Forwarder<T> {
	fun toMapping(t: T)
}

interface Dsl<C, P, R> {
	val context: Bindings.Of<C>
	val parameter: Bindings.Of<P>
	infix fun settings(block: Forwarder<Command>.() -> Unit)
	infix fun mapping(mapper: Mapper<Command, R>)
}

data class Block<C, P, R>(
	val context: C,
	val parameter: P,
	val results: Collection<R>
)

class BlockBuilder<C, P, R> : Builder<Block<C, P, R>> {
	var context: C? = null
	var parameter: P? = null
	var results: Collection<R>? = emptyList()

	override fun build(): Block<C, P, R> =
		Block(
			context ?: throw RequiredField of "context",
			parameter ?: throw RequiredField of "parameter",
			results ?: throw RequiredField of "results"
		)
}

class DslBlockBuilder<C, P, R> : Dsl<C, P, R>, Builder<Block<C, P, R>> {
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
