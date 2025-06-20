package dev.hsuliz.casino.game.domain

import dev.hsuliz.casino.game.type.SlotSymbol

typealias Grid = List<List<SlotSymbol>>

interface Game {

  suspend fun getPayout(bet: Double): Double

  suspend fun generateGrid(multiplier: Double): Grid
}
