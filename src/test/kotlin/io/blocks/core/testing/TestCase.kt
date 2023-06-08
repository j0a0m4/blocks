package io.blocks.core.testing

fun <Input, Expected> testCase(block: Assertion<Input, Expected>.() -> Unit) =
	Case<Input, Expected>().apply {
		this accepts block
	}