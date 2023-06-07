package io.blocks.core.block

import io.blocks.core.Bindings
import io.blocks.core.interfaces.*

interface Dsl<C, P, R> {
	val context: Bindings.Of<C>
	val parameter: Bindings.Of<P>
	infix fun settings(block: Forwarder<Command>.() -> Unit)
	infix fun mapping(to: Mapping<Command, R>.() -> Unit)
}
