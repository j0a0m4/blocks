import Show.Companion.Builder as ShowBuilder

class WatchList : ArrayList<Show>() {
    fun show(block: ShowBuilder.() -> Unit): Show =
        ShowBuilder()
            .apply(block)
            .run(::Show)
            .also(::add)
}


fun watchList(block: WatchList.() -> Unit): List<Show> =
    WatchList().apply(block).toList()
