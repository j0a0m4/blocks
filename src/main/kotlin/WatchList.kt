typealias WatchList = List<Show>

operator fun MutableList<Show>.plus(show: Show) {
    add(show)
}

fun watchList(supplier: () -> WatchList): WatchList =
    supplier()
