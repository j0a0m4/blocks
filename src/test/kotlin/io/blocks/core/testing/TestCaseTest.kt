package io.blocks.core.testing

import org.junit.jupiter.api.TestFactory

class TestCaseTest {
	@TestFactory
	fun `should uppercase letters`() =
		Test.positive {
			assert that "Daniel Caesar" mapsTo "DANIEL CAESAR"
			assert that "Skrillex" mapsTo "SKRILLEX"
		}.execute { it.uppercase() }

	@TestFactory
	fun `should sum two numbers`() =
		Test.positive {
			assert that (5 to 6) mapsTo 11
			assert that (1 to 1) mapsTo 2
		}.execute { (x, y) -> x + y }

	@TestFactory
	fun `should contain even numbers`() =
		Test.positive {
			assert that (1..10).toSet() mapsTo setOf(2, 4, 6, 8, 10)
		}.execute { it.filter { x -> x % 2 == 0 } }

	@TestFactory
	fun `should not map to expected when list order is different`() =
		Test.negative {
			assert that listOf(true, false) mapsTo listOf(false, true)
			assert that listOf(false, true) mapsTo listOf(true, false)
			assert that listOf(1, 2, 3) mapsTo listOf(3, 2, 1)
		}.execute { it }
}
