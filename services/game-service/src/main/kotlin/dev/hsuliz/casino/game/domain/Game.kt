package dev.hsuliz.casino.game.domain

import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class Game {
  fun roll(): Boolean {
    return Random.nextBoolean()
  }
}
