package dev.hsuliz.casino.game.types

data class PayoutRule(
    val pattern: List<SlotSymbol?>,
    val multiplier: Double,
)
