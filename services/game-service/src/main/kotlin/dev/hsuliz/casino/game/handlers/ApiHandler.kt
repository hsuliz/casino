package dev.hsuliz.casino.game.handlers

import dev.hsuliz.casino.game.domain.Games
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ApiHandler(private val games: Games) {

  @GetMapping
  suspend fun roll(): Boolean {
    return games.roll()
  }
}
