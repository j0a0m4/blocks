package io.blocks.core.interfaces

fun interface Mapper<I, O> {
	infix fun map(from: I): O
}
