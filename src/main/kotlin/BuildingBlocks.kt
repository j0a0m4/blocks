class Block<R, C>(
	private val receiver: R,
	private val controller: C
) {
	infix operator fun invoke(block: R.(C) -> Unit) =
		controller.apply { receiver.block(this) }
}

