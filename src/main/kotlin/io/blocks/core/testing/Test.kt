package io.blocks.core.testing

import io.blocks.core.interfaces.Function
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

object Test {
	enum class Type {
		Positive, Negative
	}

	fun <Input, Expected> positive(
		block: Assertion<Input, Expected>.() -> Unit
	) =
		Case<Input, Expected>(Type.Positive).apply {
			this accepts block
		}

	fun <Input, Expected> negative(
		block: Assertion<Input, Expected>.() -> Unit
	) =
		Case<Input, Expected>(Type.Negative).apply {
			this accepts block
		}

	class Case<Input, Expected>(private val type: Type = Type.Positive) {
		private val mappings = mutableMapOf<Input, Expected>()
		private val dispatcher = That<Input, Expected> { input ->
			Expect { expected ->
				mappings[input] = expected
			}
		}

		infix fun accepts(block: Assertion<Input, Expected>.() -> Unit) {
			Assertion(dispatcher).block()
		}

		fun <Actual> execute(function: Function<Input, Actual>) =
			DynamicAssertion<Input, Expected, Actual> { actual, expected ->
				when (type) {
					Type.Positive -> actual shouldBe expected
					Type.Negative -> actual shouldNotBe expected
				}
			}.run { mappings.execute(type, function) }
	}

}
