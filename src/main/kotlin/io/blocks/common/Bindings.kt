package io.blocks.common

object Bindings {
	fun interface Of<T> {
		infix fun of(t: T)
	}

	fun interface On<T> {
		infix fun on(t: T)
	}

	fun interface At<T> {
		infix fun at(t: T)
	}

	fun interface From<T> {
		infix fun from(t: T)
	}

	fun interface To<T> {
		infix fun to(t: T)
	}
}
