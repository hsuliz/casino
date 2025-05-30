package dev.hsuliz.casino.game.domain

import dev.hsuliz.casino.game.client.Odds
import dev.hsuliz.casino.game.type.PayoutRule
import dev.hsuliz.casino.game.type.Roll
import dev.hsuliz.casino.game.type.SlotSymbol
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class SimpleGame(
    private val odds: Odds,
    private val repository: CoroutineCrudRepository<Roll, UUID>,
) : Game {
  companion object {
    val rules =
        mapOf<Double, List<PayoutRule>>(
            10.0 to listOf(PayoutRule(SlotSymbol.A, 3)),
            5.0 to
                listOf(
                    PayoutRule(SlotSymbol.K, 3),
                    PayoutRule(SlotSymbol.A, 2),
                ),
            2.5 to listOf(PayoutRule(SlotSymbol.Q, 3)),
            2.0 to listOf(PayoutRule(SlotSymbol.A, 1)),
            1.0 to listOf(PayoutRule(SlotSymbol.J, 3)),
            0.5 to listOf(PayoutRule(SlotSymbol.TEN, 3)),
        )
  }

  override suspend fun getPayout(bet: Double): Double {
    val multiplier = odds.getMultiplier(rules.keys)
    return multiplier * bet
  }

  override suspend fun generateGrid(multiplier: Double): Grid {
    val symbols = SlotSymbol.entries.toTypedArray()
    var winLine: MutableList<SlotSymbol>

    if (multiplier == 0.0) {
      val winRules = rules.values.flatten()
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
          rules[multiplier]
              ?: throw NoSuchElementException("Can't find rule with given multiplier $multiplier")
      val winRule =
          winRules
              .ifEmpty {
                throw NoSuchElementException(
                    "Payout rule is empty for given multiplier $multiplier")
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
}
