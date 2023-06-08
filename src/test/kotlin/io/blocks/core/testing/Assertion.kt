package io.blocks.core.testing

class Assertion<Input, Expected>(
	val assert: That<Input, Expected>
) {
	val mapper: (Input) -> Any = { { } }
}


fun interface Expect<Expected> {
	infix fun mapsTo(expected: Expected)
}

fun interface That<Input, Expected> {
	infix fun that(input: Input): Expect<Expected>
}