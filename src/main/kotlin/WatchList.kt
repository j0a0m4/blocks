class WatchList : ArrayList<Show>()

fun WatchList.show(block: ShowDsl.() -> Unit): Show =
    ShowDslBuilder()
        .apply(block)
        .builder
        .run(::Show)
        .also(::add)

fun watchList(block: WatchList.() -> Unit): List<Show> =
    WatchList().apply(block).toList()
