package io.blocks.core.dsl

import io.blocks.core.Bindings
import io.blocks.core.interfaces.Command
import io.blocks.core.interfaces.Mapper

interface ConfigDsl<C, P, R> {
	val context: Bindings.Of<C>
	val parameter: Bindings.Of<P>
	infix fun settings(block: Forwarder<Command>.() -> Unit)
	infix fun mapping(mapper: Mapper<Command, R>)
}