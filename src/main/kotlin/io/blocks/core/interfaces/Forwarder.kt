package io.blocks.core.interfaces

fun interface Forwarder<T> {
	fun toMapping(t: T)
}
