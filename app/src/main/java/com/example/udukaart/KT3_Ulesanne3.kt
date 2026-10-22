package com.example.udukaart

class BadgeSettings {
    var meterTarget: Int = 0
    var stepTarget: Int = 0
    var name: String? = null

    val summary: String by lazy {
        println("  (kirjeldus arvutatakse nüüd, esimest ja ainsat korda)")
        "$name: $meterTarget m või $stepTarget sammu"
    }
}

fun List<Double>.mean(): Double {
    return if (isEmpty()) 0.0 else average()
}

val List<Double>.largest: Double?
    get() = maxOrNull()

fun main() {
    val settings = BadgeSettings().apply {
        meterTarget = 1000
        stepTarget = 1000
        name = "Esimene kilomeeter"
    }

    println("Seadistus valmis: ${settings.name}")

    settings.also {
        println("Nimi on olemas: ${it.name}")
    }

    val name = settings.name?.let {
        it
    }

    val total = settings.let {
        it.meterTarget + it.stepTarget
    }

    println("Lattide summa: $total")

    val distances = listOf(320.0, 1450.0, 5200.0, 180.0)
    println("Keskmine: ${distances.mean()}")
    println("Suurim: ${distances.largest}")

    val empty = emptyList<Double>()
    println("Tühja loendi keskmine: ${empty.mean()}, suurim: ${empty.largest}")

    println("Laisk omadus, kaks lugemist:")
    println("  1. ${settings.summary}")
    println("  2. ${settings.summary}")

    println("Viis ulatusfunktsiooni:")
    println("  let    it    lambda tulemus   null-kontroll, teisendus")
    println("  run    this  lambda tulemus   arvutus objekti kontekstis")
    println("  with   this  lambda tulemus   mitu lugemist ühelt objektilt")
    println("  apply  this  objekt ise       objekti seadistamine")
    println("  also   it    objekt ise       logimine ja kontroll ahela sees")

    val missingSettings: BadgeSettings? = null
    val missingTarget = missingSettings?.run {
        meterTarget
    } ?: -1

    println("Puuduva seadistuse latt: $missingTarget")
}
