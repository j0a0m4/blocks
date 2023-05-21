import Show.Companion.Builder

typealias WatchList = List<Show>
typealias MutableWatchList = MutableList<Show>

fun MutableWatchList.show(block: Builder.() -> Unit): Show =
    Builder().apply(block)
        .run(::Show)
        .also(::add)

fun watchList(block: MutableWatchList.() -> Unit): WatchList =
    mutableListOf<Show>().apply(block).toList()
