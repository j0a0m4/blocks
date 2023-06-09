package io.blocks.core.testing

import io.blocks.core.interfaces.Function
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.function.Executable

fun interface DynamicAssertion<Input, Expected, Actual> {
	fun dynamicAssert(actual: Actual, expected: Expected)

	fun MutableMap<Input, Expected>.execute(
		type: Test.Type, function: Function<Input, Actual>
	): Collection<DynamicTest> =
		map { (input, expected) ->
			when (type) {
				Test.Type.Positive -> "$input maps to $expected"
				Test.Type.Negative -> "$input doesn't map to $expected"
			} { dynamicAssert(function(input), expected) }
		}

	private operator fun String.invoke(executable: Executable): DynamicTest =
		DynamicTest.dynamicTest(this, executable)
}
