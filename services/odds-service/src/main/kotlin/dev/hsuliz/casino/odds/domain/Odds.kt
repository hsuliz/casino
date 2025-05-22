package dev.hsuliz.casino.odds.domain

import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class Odds {

  fun generateOdds(): Int {
    val odds = Random.nextInt(0, 100)
    println("Generated odd: $odds")
    return odds
  }
}
