package io.blocks.examples.watchlist

typealias WatchList = ArrayList<Show>

fun WatchList.show(block: ShowDsl.() -> Unit): Show =
    ShowDslBuilder()
        .apply(block)
        .build()
        .also(::add)

fun watchList(block: WatchList.() -> Unit): List<Show> =
    WatchList().apply(block).toList()
