import Collections.builder
import Collections.setBuilderOf
import io.kotest.core.spec.style.FeatureSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class CollectionsBuilderTest : FeatureSpec({
	feature("Set builder") {
		scenario("runs block then returns immutable set") {
			setBuilderOf {
				add("Peter")
				add("MJ")
			}.run {
				size shouldBe 2
				this shouldContainExactly setOf("Peter", "MJ")
			}
		}
		scenario("immutable sets can be transformed into set builders") {
			(1..10).toSet().builder {
				removeIf { it % 2 == 0 }
			}.run {
				this shouldContainExactly setOf(1, 3, 5, 7, 9)
			}
		}
	}
})
