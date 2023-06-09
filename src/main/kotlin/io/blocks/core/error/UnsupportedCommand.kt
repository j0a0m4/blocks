package io.blocks.core.error

class UnsupportedCommand(override val message: String?) : Exception() {
	companion object {
		infix fun of(obj: Any) =
			UnsupportedCommand("$obj of ${obj::class} doesn't have any mappings")
	}
}
