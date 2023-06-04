package io.blocks.common

fun interface Block<T> {
	infix operator fun invoke(block: () -> Unit): T
}