package dev.hsuliz.casino.game.client

interface Odds {

  suspend fun getMultiplier(multipliers: Set<Double>): Double
}
