package io.blocks.core.error

class RequiredField(override val message: String?) : Exception() {
	companion object {
		infix fun of(field: String) = RequiredField("$field is a required field")
	}
}
