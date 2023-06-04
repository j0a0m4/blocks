package io.blocks.core.dsl

fun interface Forwarder<T> {
	fun toMapping(t: T)
}