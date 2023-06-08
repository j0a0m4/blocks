package io.blocks.core.testing

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DynamicTest

class Case<Input, Expected> {
	private val mappings = mutableMapOf<Input, Expected>()
	private val dispatcher = That<Input, Expected> { input ->
		Expect { expected ->
			mappings[input] = expected
		}
	}

	infix fun accepts(block: Assertion<Input, Expected>.() -> Unit) {
		Assertion(dispatcher).block()
	}

	fun <Actual> execute(executable: (Input) -> Actual): Collection<DynamicTest> =
		mappings.map { (input, expected) ->
			DynamicTest.dynamicTest("$input -> $expected") {
				executable(input) shouldBe expected
			}
		}
}