package io.blocks.core

import kotlin.properties.Delegates
import kotlin.reflect.KProperty

object Delegates {
	val notBlankVal
		get() = validator("") {
			"$it cannot be modified once assigned" { old.isNotBlank() }
			"$it should not be blank" { new.isBlank() }
		}

	fun <T> validator(initialValue: T, validate: Validation<T>.(KProperty<*>) -> Unit) =
		Delegates.observable(initialValue) { property, old, new ->
			Value(initialValue, old, new).validate(property)
		}

	sealed interface Validation<T> {
		val initial: T
		val old: T
		val new: T

		infix operator fun String.invoke(isInvalid: () -> Boolean) {
			if (isInvalid()) throw InvalidAssignmentException(this)
		}
	}

	private data class Value<T>(
		override val initial: T,
		override val old: T,
		override val new: T,
	) : Validation<T>

	private class InvalidAssignmentException(override val message: String) : Exception()
}
