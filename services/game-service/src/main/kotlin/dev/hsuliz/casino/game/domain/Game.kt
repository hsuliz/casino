package dev.hsuliz.casino.game.domain

import org.springframework.stereotype.Component

@Component
class Game {
  fun roll(odd: Int): Boolean {
    return odd > 60
  }
}
