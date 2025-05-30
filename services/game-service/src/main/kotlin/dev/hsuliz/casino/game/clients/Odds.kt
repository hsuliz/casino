package dev.hsuliz.casino.game.clients

interface Odds {

  suspend fun getMultiplier(multipliers: Set<Double>): Double
}
