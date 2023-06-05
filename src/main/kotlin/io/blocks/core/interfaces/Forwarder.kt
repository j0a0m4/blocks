package io.blocks.core.interfaces

fun interface Forwarder<T> {
	fun asIt(t: T)
}
