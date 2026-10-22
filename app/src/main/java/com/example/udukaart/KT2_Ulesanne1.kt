package com.example.udukaart

import java.util.Locale

fun main() {
    val stepsPerMinute = listOf(96, 104, 0, 112, 108, 87, 0, 95)
    val strideCm: Int = 77

    var totalSteps = 0
    var movingMinutes = 0

    for (steps in stepsPerMinute) {
        totalSteps += steps

        if (steps > 0) {
            movingMinutes++
        }
    }

    val totalMinutes = stepsPerMinute.size

    val intAverage = totalSteps / movingMinutes
    val doubleAverage = totalSteps.toDouble() / movingMinutes

    val distanceMeters = totalSteps * strideCm / 100.0

    val tempo = if (distanceMeters > 0) {
        totalMinutes / (distanceMeters / 1000.0)
    } else {
        0.0
    }

    val movingSteps = stepsPerMinute.filter { it > 0 }
    val slowestMinute = movingSteps.minOrNull() ?: 0
    val fastestMinute = movingSteps.maxOrNull() ?: 0

    println("Minuteid kokku: $totalMinutes, neist liikuvaid: $movingMinutes")
    println("Samme kokku: $totalSteps")
    println("Int-jagamine: $intAverage sammu minutis")
    println("Double-jagamine: %.1f sammu minutis".format(Locale.ROOT, doubleAverage))
    println("Läbitud: %.1f m".format(Locale.ROOT, distanceMeters))
    println("Tempo: %.1f min/km".format(Locale.ROOT, tempo))
    println("Meetrid LÕIGATUNA: ${distanceMeters.toInt()}")
    println("Meetrid ümardatuna: ${Math.round(distanceMeters)}")
    println("Laisim liikuv minut: $slowestMinute sammu")
    println("Kiireim minut: $fastestMinute sammu")
}
