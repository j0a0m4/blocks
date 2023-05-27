typealias Mutation<R> = R.() -> Unit
typealias Middleware<R> = Mutation<R>
typealias Function<I, O> = (I) -> O
typealias Predicate<I> = Function<I, Boolean>
typealias Getter<O> = () -> O
typealias Action = () -> Unit

fun interface Mutator<Receiver, Result> {
    operator fun invoke(block: Mutation<Receiver>): Result
}

fun interface Interceptor<Receiver> {
    operator fun invoke(block: Middleware<Receiver>): Receiver
}

fun interface Mapper<Input, Output> {
    operator fun invoke(block: Function<Input, Output>): Output
}

fun interface Checker<Input> {
    operator fun invoke(block: Predicate<Input>): Boolean
}

fun interface Supplier<Value> {
    operator fun invoke(block: Getter<Value>): Value
}

fun interface Executor {
    operator fun invoke(block: Action)
}
