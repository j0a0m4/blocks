package io.blocks.core.error

sealed interface Error {
	infix fun <T> of(t: T)
}

sealed class DslError : Error {
	abstract val message: String
}

class DslRequiredField(override val message: String) : DslError() {
	override fun <T> of(t: T) {
		DslRequiredField("$t is a required field")
	}
}

class DslUnsupportedCommand(override val message: String) : DslError() {
	override fun <T> of(t: T) {
		DslUnsupportedCommand("$t of ${t!!::class} doesn't have any mappings")
	}
}
