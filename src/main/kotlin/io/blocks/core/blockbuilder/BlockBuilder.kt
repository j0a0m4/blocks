package io.blocks.core.blockbuilder

fun interface BlockBuilder<C, O, M> {
	fun BlockConfig<C, O, M>.configuration()

	infix operator fun invoke(bindTo: C.(O) -> Unit): Collection<M> =
		BlockConfigBuilder<C, O, M>()
			.apply { configuration() }
			.build()
			.apply { commands.bindTo(operations) }
			.memory
}
