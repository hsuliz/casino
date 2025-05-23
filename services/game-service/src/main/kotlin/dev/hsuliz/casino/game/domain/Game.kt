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
        listOf(
            PayoutRule(listOf(SlotSymbol.A, SlotSymbol.A, SlotSymbol.A), 10.0),
            PayoutRule(listOf(SlotSymbol.A, SlotSymbol.A, null), 5.0),
            PayoutRule(listOf(SlotSymbol.A, null, null), 2.0),
            PayoutRule(listOf(SlotSymbol.K, SlotSymbol.K, SlotSymbol.K), 5.0),
            PayoutRule(listOf(SlotSymbol.Q, SlotSymbol.Q, SlotSymbol.Q), 2.5),
            PayoutRule(listOf(SlotSymbol.J, SlotSymbol.J, SlotSymbol.J), 1.0),
            PayoutRule(listOf(SlotSymbol.TEN, SlotSymbol.TEN, SlotSymbol.TEN), 0.5),
        )
  }

  suspend fun roll(): Grid {
    val odds = odds.getOdds()
    val grid = generateGrid(2.0)
    repository.save(Game("Sasha", true, odds))
    return grid
  }
}
