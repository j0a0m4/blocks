package io.blocks.core.interfaces

fun interface Block<C, P> {
	infix fun C.instructions(p: P)
}
