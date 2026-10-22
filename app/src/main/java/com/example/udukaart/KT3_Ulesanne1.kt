package com.example.udukaart

data class Badge(val code: String, val name: String, val tier: Int)

class BadgeCollection {
    private val badges = mutableListOf<Badge>()

    val all: List<Badge>
        get() = badges.toList()

    fun add(badge: Badge) {
        badges.add(badge)
    }
}

fun main() {
    val all: List<Badge> = listOf(
        Badge("KM1", "Esimene kilomeeter", 1),
        Badge("S1000", "Tuhat sammu", 1),
        Badge("MIN30", "Pool tundi", 1),
        Badge("KM5", "Viis kilomeetrit", 2),
        Badge("S10K", "Kümme tuhat sammu", 2),
        Badge("RING", "Terve linnaosa", 3)
    )

    val earned: Set<String> = setOf(
        "KM1", "S1000", "KM5", "S1000"
    )

    val descriptions: Map<String, String> = mapOf(
        "KM1" to "Esimene täis kilomeeter ühe päevaga",
        "S1000" to "Tuhat sammu ühe päevaga",
        "KM5" to "Viis kilomeetrit ühe päevaga",
        "S10K" to "Kümme tuhat sammu ühe päevaga",
        "RING" to "Kogu linnaosa läbitud"
    )

    println("Teenitud koode anti 4, hulgas on ${earned.size}")
    println("Märgid:")

    for (badge in all) {
        val status = if (badge.code in earned) "TEENITUD" else "lukus"
        val description = descriptions[badge.code] ?: "(kirjeldus puudub)"

        println(
            "  %-6s %-20s tase %-2d %-7s %s".format(
                badge.code,
                badge.name,
                badge.tier,
                status,
                description
            )
        )
    }

    val collection = BadgeCollection()
    collection.add(all[0])
    collection.add(all[3])

    println(
        "Kogus on ${collection.all.size} märki: ${
            collection.all.joinToString(", ") { it.code }
        }"
    )

    val empty = emptyList<Badge>()

    println(
        "Tühja loendi suurus: ${empty.size}, esimene: ${empty.firstOrNull()}"
    )

    println(
        "Tühja loendi kõrgeim tase: ${empty.maxOfOrNull { it.tier } ?: 0}"
    )
}
