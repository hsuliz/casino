package dev.hsuliz.casino.game.client

interface Odds {

  suspend fun getMultiplier(
      username: String,
      bet: Double,
      multipliers: Set<Double>,
  ): Double
}
