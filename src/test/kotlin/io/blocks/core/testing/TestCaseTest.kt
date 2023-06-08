package io.blocks.core.testing

import org.junit.jupiter.api.TestFactory

class TestCaseTest {
	@TestFactory
	fun `should uppercase letters`() =
		testCase {
			assert that "Daniel Caesar" mapsTo "DANIEL CAESAR"
			assert that "Skrillex" mapsTo "SKRILLEX"
		}.execute { it.uppercase() }

	@TestFactory
	fun `should sum two numbers`() =
		testCase {
			assert that (5 to 6) mapsTo 11
			assert that (1 to 1) mapsTo 2
		}.execute { (x, y) -> x + y }

	@TestFactory
	fun `should contain even numbers`() =
		testCase {
			assert that (1..10).toSet() mapsTo setOf(2, 4, 6, 8, 10)
		}.execute { it.filter { x -> x % 2 == 0 } }
}
