package dev.hsuliz.casino.game.types

interface Odds {

  suspend fun getOdds(): Int
}
