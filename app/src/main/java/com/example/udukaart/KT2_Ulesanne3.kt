package com.example.udukaart

fun duration(start: Long, end: Long?): Long? = end?.minus(start)

fun durationLabel(start: Long, end: Long?): String =
    duration(start, end)?.toString() ?: "käib veel"

fun finishedHour(end: Long?): Int? = end?.let { ((it / 3600) % 24).toInt() }

fun main() {
    val visits = listOf(
        Pair(1700000000L, 1700001845L),
        Pair(1700000000L, null),
        Pair(1700000000L, 1700000000L)
    )

    val names = listOf("Lõppenud", "Käimasolev", "Null-pikkusega")

    for (i in visits.indices) {
        val start = visits[i].first
        val end = visits[i].second

        println(
            "${names[i]}: algus $start, kestus ${duration(start, end)} s, " +
                    "silt '${durationLabel(start, end)}', tund ${finishedHour(end)}"
        )
    }

    val finishedEnd: Long? = 1700001845L
    val ongoingEnd: Long? = null

    finishedEnd?.let {
        println("Lõppenud külastus: lõpuaeg on olemas, $it")
    }

    ongoingEnd?.let {
        println("See rida ei ilmu")
    } ?: println("Käimasolev külastus: let jäi vahele, rida jäi trükkimata")

    val unknown: Any = 1_700_000_600L

    if (unknown is Long) {
        println("Tundmatu väärtus oli Long, tunniks teisendatuna ${finishedHour(unknown)}")
    }

    println("Turvakutse puuduval: ${finishedHour(null)}")
    println("Sama koos elvisega: ${finishedHour(null) ?: "väärtust ei ole"}")
}
