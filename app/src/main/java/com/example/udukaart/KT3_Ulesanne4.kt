package com.example.udukaart

data class DayEntry(val day: Int, val meters: Double?, val steps: Int)

fun main() {
    val history = listOf(
        DayEntry(0, 500.0, 1200),
        DayEntry(1, 1500.0, 3000),
        DayEntry(2, null, 0),
        DayEntry(3, 2100.0, 4100),
        DayEntry(4, 5200.0, 7020),
        DayEntry(5, 3400.0, 6000),
        DayEntry(6, 1150.0, 2500),
        DayEntry(7, null, 0),
        DayEntry(8, 1800.0, 3600),
        DayEntry(9, 3200.0, 5800)
    )

    // filter eemaldab null-meetritega päevad.
    // mapNotNull võtab allesjäänud päevadest meetrid.
    // sortedDescending järjestab meetrid kahanevalt.
    // take valib kolm suurimat väärtust.
    val topThree = history
        .filter { it.meters != null }
        .mapNotNull { it.meters }
        .sortedDescending()
        .take(3)

    println("Kolm pikimat päeva: $topThree")

    val weeks = history.groupBy { it.day / 7 }

    for ((week, entries) in weeks) {
        val total = entries.fold(0.0) { sum, entry ->
            sum + (entry.meters ?: 0.0)
        }

        println("Nädal $week: ${entries.size} päeva, kokku ${total.toInt()} m")
    }

    val byDay = history.associateBy { it.day }

    println("Päev 4: ${byDay[4]}")
    println("Päev 99: ${byDay[99]}")

    val totalMeters = history.fold(0.0) { sum, entry ->
        sum + (entry.meters ?: 0.0)
    }

    println("Kogumeetrid: ${totalMeters.toInt()}")

    val reduceResult = runCatching {
        emptyList<Double>().reduce { a, b -> a + b }
    }

    println(
        "reduce tühjal loendil: ${reduceResult.exceptionOrNull()?.javaClass?.simpleName}"
    )

    val foldResult = emptyList<Double>().fold(0.0) { a, b -> a + b }

    println("fold tühjal loendil: $foldResult")

    var listCount = 0

    val listResult = (1..100_000)
        .map {
            listCount++
            it * 3
        }
        .filter { it % 7 == 0 }
        .take(5)

    var sequenceCount = 0

    val sequenceResult = (1..100_000)
        .asSequence()
        .map {
            sequenceCount++
            it * 3
        }
        .filter { it % 7 == 0 }
        .take(5)
        .toList()

    println(
        "Jada tulemus: $sequenceResult, läbitud elemente: $sequenceCount"
    )

    println(
        "Loendi tulemus: $listResult, läbitud elemente: $listCount"
    )

    println(
        "Sama vastus, aga jada tegi ${listCount / sequenceCount} korda vähem tööd"
    )
}
