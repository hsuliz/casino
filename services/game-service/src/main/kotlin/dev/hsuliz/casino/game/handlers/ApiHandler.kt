package dev.hsuliz.casino.game.handlers

import dev.hsuliz.casino.game.domain.SimpleGame
import dev.hsuliz.casino.game.types.api.SpinRequest
import dev.hsuliz.casino.game.types.api.SpinResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ApiHandler(private val simpleGame: SimpleGame) {

  @PostMapping
  suspend fun spin(@RequestBody request: SpinRequest): SpinResponse {
    val payout = simpleGame.getPayout(request.bet)
    val grid = simpleGame.generateGrid(payout / request.bet)
    return SpinResponse(payout, grid)
  }
}
