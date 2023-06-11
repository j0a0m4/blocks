package io.blocks.core.testing

import io.blocks.core.interfaces.Function
import org.junit.jupiter.api.DynamicTest

fun interface DynamicAssertion<Input, Expected, Actual> {
	fun dynamicAssert(actual: Actual, expected: Expected)

	fun Map<Input, Expected>.make(displayName: String, function: Function<Input, Actual>) =
		map { (input, expected) ->
			DynamicTest.dynamicTest(displayName) {
				dynamicAssert(function(input), expected)
			}
		}
}
