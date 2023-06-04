package io.blocks.core.exceptions

class RequiredField(override val message: String?) : Exception() {
	companion object {
		infix fun of(field: String) = RequiredField("$field is a required field")
	}
}
