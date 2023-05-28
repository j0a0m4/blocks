object Collections {
	fun <T> setBuilderOf(mutation: MutableSet<T>.() -> Unit): Set<T> =
		mutableSetOf<T>()
			.apply(mutation)
			.toSet()

	fun <T> Set<T>.builder(mutation: MutableSet<T>.() -> Unit): Set<T> =
		toMutableSet()
			.apply(mutation)
			.toSet()
}
