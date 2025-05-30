package dev.hsuliz.casino.game.domain

import dev.hsuliz.casino.game.types.SlotSymbol

typealias Grid = List<List<SlotSymbol>>

fun generateGrid(multiplier: Double): Grid {
  val symbols = SlotSymbol.entries.toTypedArray()
  val game = Game.rules
  var winLine: MutableList<SlotSymbol>

  if (multiplier == 0.0) {
    val winRules = game.values.flatten()
    val symbols = SlotSymbol.entries.toList()
    generate@ while (true) {
      winLine = MutableList(3) { symbols.random() }

      for (i in 1..winLine.size) {
        val prefix = winLine.take(i)
        val matchingRules = winRules.filter { it.lines == i }
        for (rule in matchingRules) {
          if (prefix.all { it == rule.symbol }) {
            continue@generate
          }
        }
      }
      break
    }
  } else {
    val winRules =
        game[multiplier]
            ?: throw NoSuchElementException("Can't find rule with given multiplier $multiplier")
    val winRule =
        winRules
            .ifEmpty {
              throw NoSuchElementException("Payout rule is empty for given multiplier $multiplier")
            }
            .random()

    println("Using win rule $winRule")
    winLine = MutableList(3) { symbols.filterNot { it == winRule.symbol }.random() }
    for (i in 0 until winRule.lines) {
      winLine[i] = winRule.symbol
    }
  }
  return List(3) { index ->
    if (index == 1) {
      winLine
    } else {
      List(3) { symbols.random() }
    }
  }
}
