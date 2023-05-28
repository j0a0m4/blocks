fun interface ReceiverController<R, C> {
	infix operator fun invoke(block: R.(C) -> Unit): C
}

fun interface Receiver<R> {
	infix operator fun invoke(block: R.() -> Unit): R
}

fun interface Controller<C> {
	infix operator fun invoke(block: (C) -> Unit): C
}
