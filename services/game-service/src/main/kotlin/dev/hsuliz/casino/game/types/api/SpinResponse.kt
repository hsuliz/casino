package dev.hsuliz.casino.game.types.api

import dev.hsuliz.casino.game.domain.Grid

data class SpinResponse(
    val payout: Double,
    val grid: Grid,
)
