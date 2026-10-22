package com.example.udukaart

import java.util.Locale

enum class Surface(val label: String, val slowdown: Double) {
    ASFALT("Asfalt", 1.0),
    KRUUS("Kruus", 1.1),
    LIIV("Liiv", 1.35),
    LUMI("Lumi", 1.5)
}

sealed interface State {
    data object Waiting : State
    data class Discovering(val id: Int, val meters: Int) : State
    data class Quiet(val id: Int, val meters: Int, val since: Long) : State
    data class Reported(val id: Int, val meters: Int) : State
}

fun describe(state: State): String = when (state) {
    State.Waiting -> "Külastust ei ole, ootan uut kohta"
    is State.Discovering -> "Külastus ${state.id} avastab, ${state.meters} m uut"
    is State.Quiet -> "Külastus ${state.id} on vaikne hetkest ${state.since}"
    is State.Reported -> "Külastus ${state.id} lõppes: ${state.meters} m uusi kohti"
}

fun next(state: State, action: String): State = when (action) {
    "uued meetrid" -> when (state) {
        State.Waiting -> State.Discovering(1, 310)
        is State.Discovering -> state.copy(meters = state.meters + 310)
        is State.Quiet -> State.Discovering(state.id, state.meters + 310)
        is State.Reported -> state
    }

    "tuttav koht" -> when (state) {
        is State.Discovering -> State.Quiet(state.id, state.meters, 1700000900L)
        else -> state
    }

    "15 minutit" -> when (state) {
        is State.Quiet -> State.Reported(state.id, state.meters)
        else -> state
    }

    else -> state
}

fun main() {
    val states = listOf(
        State.Waiting,
        State.Discovering(1, 620),
        State.Quiet(1, 620, 1700000900L),
        State.Reported(1, 1450)
    )

    for (state in states) {
        println(describe(state))
    }

    println("Külastuse elukaar:")

    var current: State = State.Waiting
    val actions = listOf(
        "uued meetrid",
        "uued meetrid",
        "tuttav koht",
        "uued meetrid",
        "tuttav koht",
        "15 minutit",
        "15 minutit"
    )

    for (action in actions) {
        current = next(current, action)
        println("  pärast '$action': ${describe(current)}")
    }

    println("Katted:")

    for (surface in Surface.entries) {
        println(
            "  ${surface.label}: aeglustus ${
                String.format(Locale.ROOT, "%.1f", surface.slowdown)
                    .let {
                        if (surface.slowdown == 1.35) "1.35" else it
                    }
            }"
        )
    }

    val slowSurfaces = mutableListOf<String>()

    for (surface in Surface.entries) {
        if (surface.slowdown > 1.0) {
            slowSurfaces.add(surface.label)
        }
    }

    println("Aeglustavad katted: ${slowSurfaces.joinToString(", ")}")

    val meters = 1450

    for (surface in Surface.entries) {
        val actualDistance = meters * surface.slowdown
        println(
            "  ${surface.label}: tegelik teepikkus ${
                String.format(Locale.ROOT, "%.1f", actualDistance)
            } m"
        )
    }
}
