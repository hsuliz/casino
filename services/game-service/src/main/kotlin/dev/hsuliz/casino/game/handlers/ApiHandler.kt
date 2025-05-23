package dev.hsuliz.casino.game.handlers

import dev.hsuliz.casino.game.domain.Game
import dev.hsuliz.casino.game.domain.Grid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ApiHandler(private val game: Game) {

  @GetMapping
  suspend fun roll(): Grid {
    return game.roll()
  }
}
