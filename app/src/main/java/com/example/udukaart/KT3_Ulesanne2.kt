package com.example.udukaart

data class DayStats(val meters: Double, val steps: Int, val minutes: Int)

typealias BadgeRule = (DayStats) -> Boolean

fun halfHour(stats: DayStats): Boolean {
    return stats.minutes >= 30
}

fun earnedBy(
    stats: DayStats,
    rules: Map<String, BadgeRule>
): List<String> {
    return rules.filter { (_, rule) -> rule(stats) }.keys.toList()
}

fun ifEarned(
    stats: DayStats,
    rule: BadgeRule,
    message: () -> String
) {
    if (rule(stats)) {
        println(message())
    }
}

fun main() {
    val kilometer: BadgeRule = { it.meters >= 1000.0 }
    val thousandSteps: BadgeRule = { stats -> stats.steps >= 1000 }
    val longDay: BadgeRule = {
        it.meters >= 5000.0 && it.minutes >= 45
    }
    val halfHourRule: BadgeRule = ::halfHour

    val rules: Map<String, BadgeRule> = linkedMapOf(
        "Kilomeeter päevas" to kilometer,
        "Tuhat sammu" to thousandSteps,
        "Pool tundi liikumist" to halfHourRule,
        "Pikk päev" to longDay
    )

    val days = listOf(
        DayStats(320.0, 430, 8),
        DayStats(1450.0, 1960, 31),
        DayStats(5200.0, 7020, 62)
    )

    for (day in days) {
        val badges = earnedBy(day, rules)
        val result = if (badges.isEmpty()) {
            "ei ühtki"
        } else {
            badges.joinToString(", ")
        }

        println(
            "${day.meters.toInt()} m, ${day.steps} sammu, ${day.minutes} min -> $result"
        )
    }

    // it tähendab lambda ainsat parameetrit ilma nime kirjutamata.
    val shortRule: BadgeRule = { it.meters >= 1000.0 }

    // stats on sama parameeter, kuid sellele on antud selge nimi.
    val namedRule: BadgeRule = { stats -> stats.meters >= 1000.0 }

    ifEarned(days[2], shortRule) {
        "Kolmas päev ületas kilomeetri"
    }

    ifEarned(days[0], namedRule) {
        "Esimene päev ületas kilomeetri"
    }

    val unusedMessage: () -> String = {
        "Seda teadet ei kuvata"
    }

    println(
        "Hoiutud reegel esimese päeva peal: ${namedRule(days[0])}"
    )
}
