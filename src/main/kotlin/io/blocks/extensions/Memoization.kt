package io.blocks.extensions

fun <T, R> useMemo(function: (T) -> R): (T) -> R = with(mutableMapOf<T, R>()) {
    { input: T ->
        if (input !in this) {
            this[input] = function(input)
        }
        this[input]!!
    }
}

val <T, R> ((T) -> R).memoized : (T) -> R
    get() = useMemo(this)
