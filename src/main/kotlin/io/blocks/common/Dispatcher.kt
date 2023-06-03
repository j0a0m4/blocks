package io.blocks.common

fun interface Dispatcher<T> {
	infix fun dispatch(t: T)
}
