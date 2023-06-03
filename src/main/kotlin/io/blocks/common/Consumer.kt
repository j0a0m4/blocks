package io.blocks.common

fun interface Consumer<T> {
    infix fun accept(t: T)
}
