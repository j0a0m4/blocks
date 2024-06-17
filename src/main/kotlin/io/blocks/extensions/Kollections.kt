package io.blocks.extensions

object Kollections {
    val <T> Collection<T>.first
        get() = first()

    val <T> Collection<T>.head
        get() = first

    val <T> Collection<T>.last
        get() = last()

    val <T> Collection<T>.tail
        get() = drop(1)

    val <T> Collection<T>.isEmpty
        get() = isEmpty()

    val <T> Collection<T>.isNotEmpty
        get() = isNotEmpty()

    val <T> Collection<T>.count
        get() = count()

    val <T> Collection<T>.random
        get() = random()

    val <T> Collection<T>.randomOrNull
        get() = randomOrNull()

    fun <T> Collection<T>.takeLast(n: Int): Collection<T> =
        drop(size - n)
}
