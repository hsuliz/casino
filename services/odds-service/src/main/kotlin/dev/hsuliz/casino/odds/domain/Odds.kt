package dev.hsuliz.casino.odds.domain

import org.springframework.stereotype.Component

@Component
class Odds {

  fun choseMultiplier(multipliers: List<Double>): Double {
    val finalMultipliers = multipliers.toMutableList()
    repeat(50) { finalMultipliers += 0.0 }
    val multiplier = finalMultipliers.random()
    println("Generated multiplier: $finalMultipliers")
    return multiplier
  }
}
