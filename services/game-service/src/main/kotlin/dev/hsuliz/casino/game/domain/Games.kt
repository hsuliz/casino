package dev.hsuliz.casino.game.domain

import dev.hsuliz.casino.game.types.Game
import dev.hsuliz.casino.game.types.Odds
import java.util.*
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Component

@Component
class Games(
    private val odds: Odds,
    private val repository: CoroutineCrudRepository<Game, UUID>,
) {

  suspend fun roll(): Boolean {
    val odds = odds.getOdds()
    val result = odds > 60
    repository.save(Game("Sasha", result, odds))
    return result
  }
}
