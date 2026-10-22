package com.example.udukaart

data class Achievement(
    val code: String,
    val name: String,
    val target: Double
)

sealed interface AchievementState {
    data object Locked : AchievementState
    data class InProgress(val percent: Int) : AchievementState
    data class Earned(val collected: Double) : AchievementState
}

fun stateOf(a: Achievement, collected: Double?): AchievementState {
    if (collected == null) return AchievementState.Locked

    return if (collected >= a.target) {
        AchievementState.Earned(collected)
    } else {
        AchievementState.InProgress(
            (collected / a.target * 100).toInt()
        )
    }
}

fun describe(state: AchievementState): String =
    when (state) {
        AchievementState.Locked -> "lukus"
        is AchievementState.InProgress -> "pooleli ${state.percent}%"
        is AchievementState.Earned -> "TEENITUD (${state.collected.toInt()})"
    }

fun targetFromText(text: String): Double? =
    runCatching {
        text.toDouble()
    }.getOrNull()

fun List<Achievement>.earnedCodes(
    collected: Map<String, Double>
): List<String> =
    filter {
        stateOf(it, collected[it.code]) is AchievementState.Earned
    }.map { it.code }

fun main() {
    val achievements = listOf(
        Achievement("KM1", "Esimene kilomeeter", 1000.0),
        Achievement("KM5", "Viis kilomeetrit", 5000.0),
        Achievement("S10K", "Kümme tuhat sammu", 10000.0),
        Achievement("MIN30", "Pool tundi", 30.0),
        Achievement("RING", "Terve linnaosa", 100.0)
    )

    val collected = mapOf(
        "KM1" to 1450.0,
        "KM5" to 3400.0,
        "S10K" to 12800.0,
        "MIN30" to 21.0
    )

    println("Saavutused:")

    for (achievement in achievements) {
        val state = stateOf(
            achievement,
            collected[achievement.code]
        )

        println(
            "  %-6s %-20s latt %6d  %s".format(
                achievement.code,
                achievement.name,
                achievement.target.toInt(),
                describe(state)
            )
        )
    }

    val earnedCount = achievements.count {
        stateOf(it, collected[it.code]) is AchievementState.Earned
    }

    val earnedPercent = earnedCount.toDouble() / achievements.size * 100

    println(
        "Teenitud $earnedCount / ${achievements.size} ehk %.1f%%".format(
            earnedPercent
        )
    )

    println("Edenemise järgi kolm esimest:")

    val topThree = achievements
        .filter { collected[it.code] != null }
        .sortedByDescending {
            collected[it.code]!! / it.target
        }
        .take(3)

    for (achievement in topThree) {
        val percent = collected[achievement.code]!! / achievement.target * 100
        println(
            "  %-6s %.1f%%".format(
                achievement.code,
                percent
            )
        )
    }

    println("Latt tekstist '1500': ${targetFromText("1500")}")
    println("Latt tekstist 'viisteist': ${targetFromText("viisteist")}")

    val earned = achievements.earnedCodes(collected)
    println("Teenitud koodid: ${earned.joinToString(", ")}")

    val emptyEarned = emptyList<Achievement>().earnedCodes(collected)
    println(
        "Tühja sisendi tulemus: ${
            emptyEarned.joinToString(", ").ifEmpty { "ei ühtki" }
        }"
    )

    val inProgressCount = achievements.count {
        stateOf(it, collected[it.code]) is AchievementState.InProgress
    }

    val lockedCount = achievements.count {
        stateOf(it, collected[it.code]) is AchievementState.Locked
    }

    println(
        "Kokkuvõte: ${achievements.size} saavutust, " +
                "$earnedCount teenitud, " +
                "$inProgressCount pooleli, " +
                "$lockedCount lukus"
    )
}
