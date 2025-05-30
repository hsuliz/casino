package dev.hsuliz.casino.odds.domain

import dev.hsuliz.casino.odds.type.Spin
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Component

@Component
class Odds(private val repository: CoroutineCrudRepository<Spin, String>) {

  suspend fun choseMultiplier(
      username: String,
      bet: Double,
      multipliers: List<Double>,
  ): Double {
    val finalMultipliers = multipliers.toMutableList()
    repeat(50) { finalMultipliers += 0.0 }
    val multiplier = finalMultipliers.random()
    repository.save(
        Spin(
            username,
            bet,
            multiplier,
        ))
    println("Generated multiplier: $finalMultipliers")
    return multiplier
  }
}
