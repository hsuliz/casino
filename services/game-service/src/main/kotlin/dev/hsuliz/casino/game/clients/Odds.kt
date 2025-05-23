package dev.hsuliz.casino.game.clients

interface Odds {

  suspend fun getOdds(): Int
}