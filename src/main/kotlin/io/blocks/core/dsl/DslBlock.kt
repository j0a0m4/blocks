package io.blocks.core.dsl


fun interface DslBlock<C, P, R> {
	fun ConfigDsl<C, P, R>.configuration()

	val blockConfig: BlockConfig<C, P, R>
		get() = ConfigDslBuilder<C, P, R>()
			.apply { configuration() }
			.build()

	infix operator fun invoke(instructions: C.(P) -> Unit): Collection<R> =
		blockConfig.apply { context.instructions(parameter) }.results
}

