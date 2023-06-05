package io.blocks.core.interfaces

import kotlin.reflect.KClass

fun interface Mapping<I, O> {
	infix fun KClass<*>.to(mapper: Mapper<I, O>)
}
