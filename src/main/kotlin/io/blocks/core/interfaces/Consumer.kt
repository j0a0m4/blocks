package io.blocks.core.interfaces

fun interface Consumer<T> {
    infix fun accept(t: T)
}
