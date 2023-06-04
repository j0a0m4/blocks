package io.blocks.core.interfaces

fun interface Dispatcher<T> {
	infix fun dispatch(t: T)
}
