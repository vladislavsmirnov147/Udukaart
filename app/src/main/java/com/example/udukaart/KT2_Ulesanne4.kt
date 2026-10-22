package com.example.udukaart

data class Visit(
    val id: Long,
    val start: Long,
    val end: Long?,
    val meters: Double,
    val points: Int
) {
    init {
        require(meters >= 0) {
            "meetrid ei tohi olla negatiivsed: $meters"
        }
        require(points >= 0) {
            "punktid ei tohi olla negatiivsed: $points"
        }
    }

    val finished: Boolean
        get() = end != null

    val metersPerPoint: Double
        get() = if (points == 0) 0.0 else meters / points

    var note: String = ""
        set(value) {
            field = value.trim()
        }

    companion object {
        fun started(id: Long, moment: Long): Visit =
            Visit(id, moment, null, 0.0, 0)
    }
}

object VisitLimits {
    var minMeters: Double = 0.0

    fun reset() {
        minMeters = 0.0
    }
}

interface Timed {
    val durationSec: Long
}

abstract class Activity(val label: String) : Timed {
    abstract fun clearsFog(): Boolean

    fun describe(): String =
        "$label: $durationSec s, udu ${if (clearsFog()) "avaneb" else "ei avane"}"
}

class Walking(override val durationSec: Long) : Activity("Jalutus") {
    override fun clearsFog(): Boolean = true
}

class Driving(override val durationSec: Long) : Activity("Sõit") {
    override fun clearsFog(): Boolean = false
}

fun main() {
    val started = Visit.started(1, 1700000000L)
    println("Alanud: $started")

    val finished = started.copy(end = 1700001845L, meters = 1450.0, points = 305)
    println("Lõppenud: $finished")

    println("Kas lõppenud: ${if (finished.finished) "true, punktide keskmine vahe ${"%.1f".format(java.util.Locale.ROOT, finished.metersPerPoint)} m" else "false"}")

    val same = finished.copy()
    val different = finished.copy(id = 2)

    println("valmis == sama: ${finished == same}")
    println("valmis == teine: ${finished == different}")

    val (id, _, _, meters, _) = finished
    println("Destruktureeritud: id $id, meetrid $meters")

    finished.note = "  vanalinna tagahoovid  "
    println("Märkus lõigatuna: '${finished.note}'")

    val visits = listOf(started, finished, different)

    VisitLimits.minMeters = 500.0
    val suitableCount = visits.count { it.meters >= VisitLimits.minMeters }
    println("Lävega 500.0 sobib $suitableCount külastust kolmest")

    VisitLimits.reset()
    println("Lävi taastatud: ${VisitLimits.minMeters}")

    try {
        Visit(3, 1700000000L, null, -5.0, 0)
    } catch (e: IllegalArgumentException) {
        println("Vigane külastus jäi tekkimata: ${e.message}")
    }

    val walking = Walking(1845)
    val driving = Driving(600)

    println(walking.describe())
    println(driving.describe())

}
