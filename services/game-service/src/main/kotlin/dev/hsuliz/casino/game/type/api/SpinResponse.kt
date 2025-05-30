package dev.hsuliz.casino.game.type.api

import dev.hsuliz.casino.game.domain.Grid

data class SpinResponse(
    val payout: Double,
    val grid: Grid,
)
