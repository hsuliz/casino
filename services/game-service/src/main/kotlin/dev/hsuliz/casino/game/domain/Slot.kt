package dev.hsuliz.casino.game.domain

import dev.hsuliz.casino.game.types.SlotSymbol

typealias Grid = List<List<SlotSymbol>>

fun generateGrid(multiplier: Double): Grid {
  val symbols = SlotSymbol.entries.toTypedArray()
  val winRules =
      Game.rules
          .filter { it -> it.multiplier == multiplier }
          .ifEmpty {
            throw NoSuchElementException("Can't find rule with given multiplier $multiplier")
          }
  val winRule = winRules.random()
  return List(3) { index ->
    if (index == 1 && multiplier > 0) {
      winRule.pattern.map {
        it ?: symbols.filter { slotSymbol -> winRule.pattern.contains(slotSymbol).not() }.random()
      }
    } else {
      List(3) { symbols.random() }
    }
  }
}
