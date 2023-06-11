package io.blocks.core.interfaces

fun interface Block<T, R> {
	infix operator fun invoke(block: T.() -> Unit): R
}
