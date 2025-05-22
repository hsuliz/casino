package dev.hsuliz.casino.odds.domain

import kotlin.random.Random
import org.springframework.stereotype.Component

@Component
class Odds {

  fun generateOdd(): Int {
    val odd = Random.nextInt(0, 100)
    println("Generated odd: $odd")
    return odd
  }
}
