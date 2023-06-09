package io.blocks.core.interfaces

fun interface Function<I, O> {
	infix operator fun invoke(input: I): O
}
