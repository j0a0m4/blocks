package io.blocks.core.dsl

fun interface DslBlock<C, P, R> {
	fun Dsl<C, P, R>.configuration()

	val block: Block<C, P, R>
		get() = BlockDslBuilder<C, P, R>()
			.apply { configuration() }
			.build()

	infix operator fun invoke(instructions: C.(P) -> Unit) =
		block accept instructions
}
