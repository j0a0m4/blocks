package io.blocks.core.block

fun interface DslBlock<C, P, R> {
	fun Dsl<C, P, R>.configuration()

	val block: BlockData<C, P, R>
		get() = BlockDslBuilder<C, P, R>()
			.apply { configuration() }
			.build()

	infix operator fun invoke(instructions: C.(P) -> Unit) =
		block accept instructions
}
