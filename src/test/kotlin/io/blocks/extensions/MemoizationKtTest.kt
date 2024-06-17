package io.blocks.extensions

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MemoizationKtTest : StringSpec({
    fun increment(x: Int) = x + 1

    "should memoize a function by using useMemo function" {
        val incrementMemo = useMemo(::increment)
        increment(5) shouldBe incrementMemo(5)
    }

    "should memoize a function by using memoized extension" {
        val incrementMemo = ::increment.memoized
        increment(5) shouldBe incrementMemo(5)
    }

    "should memoize an anonymous function" {
        val incrementMemo = useMemo { input: Int -> input + 1 }
        increment(5) shouldBe incrementMemo(5)
    }
})
