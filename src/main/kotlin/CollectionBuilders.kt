object Collections {
	fun <T> setBuilderOf(mutation: Mutation<MutableSet<T>>): Set<T> =
		mutableSetOf<T>()
			.apply(mutation)
			.toSet()

	fun <T> Set<T>.builder(mutation: Mutation<MutableSet<T>>): Set<T> =
		toMutableSet()
			.apply(mutation)
			.toSet()
}
