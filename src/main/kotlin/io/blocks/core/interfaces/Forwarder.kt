package io.blocks.core.interfaces

fun interface Forwarder<T> {
	fun forward(t: T)
}
