package dev.hsuliz.casino.game.handlers

import dev.hsuliz.casino.game.domain.Game
import dev.hsuliz.casino.game.domain.Grid
import dev.hsuliz.casino.game.types.RollRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ApiHandler(private val game: Game) {

  @PostMapping
  suspend fun roll(@RequestBody rooRequest: RollRequest): Grid {
    return game.roll(rooRequest.bet)
  }
}
