package dev.hsuliz.casino.game.domain

import dev.hsuliz.casino.game.clients.Odds
import dev.hsuliz.casino.game.types.Game
import dev.hsuliz.casino.game.types.PayoutRule
import dev.hsuliz.casino.game.types.SlotSymbol
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class Game(
    private val odds: Odds,
    private val repository: CoroutineCrudRepository<Game, UUID>,
) {
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

  suspend fun roll(bet: Double): Grid {
    val multiplier = odds.getMultiplier(rules.keys)
    val grid = generateGrid(multiplier)
    repository.save(Game("Sasha", multiplier, bet))
    return grid
  }
}
