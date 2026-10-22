
package com.example.udukaart

import java.util.Locale

fun paceLabel(minPerKm: Double): String = when {
    minPerKm < 10.0 -> "KIIRE"
    minPerKm < 13.0 -> "REIPAS"
    minPerKm < 20.0 -> "JALUTUS"
    else -> "SEISAK"
}

fun headline(
    meters: Int,
    minPerKm: Double,
    prefix: String = "Külastus"
): String = "$prefix: $meters m, ${String.format(Locale.ROOT, "%.1f", minPerKm)} min/km, ${paceLabel(minPerKm)}"

fun main() {
    val meters = listOf(320, 1450, 5200, 180)
    val tempos = listOf(14.2, 11.5, 9.4, 21.5)

    println("Külastused tempo järgi")

    for (i in meters.indices) {
        println("  " + headline(meters[i], tempos[i]))
    }

    println(headline(1450, 11.5, prefix = "Hommikune külastus"))

    println("Vahemikud: 1..5, 5 downTo 1 step 1, 0..9 step 3")

    println("11.5 on vahemikus 10.0..13.0: ${11.5 in 10.0..13.0}")
    println("14.2 on vahemikus 10.0..13.0: ${14.2 in 10.0..13.0}")
}
