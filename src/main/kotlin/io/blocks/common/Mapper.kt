package io.blocks.common

fun interface Mapper<I, O> {
	infix fun map(from: I): O
}
