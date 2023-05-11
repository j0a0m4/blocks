typealias WatchList = List<Show>

fun watchList(init: MutableList<Show>.() -> Unit): WatchList =
    mutableListOf<Show>()
        .apply(init)
        .run(WatchList::toList)
