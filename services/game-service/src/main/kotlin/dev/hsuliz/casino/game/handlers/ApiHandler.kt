package dev.hsuliz.casino.game.handlers

import dev.hsuliz.casino.game.clients.GrpcClient
import dev.hsuliz.casino.game.domain.Game
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ApiHandler(private val game: Game, private val client: GrpcClient) {

  @GetMapping
  suspend fun roll(): Boolean {
    return game.roll(client.getOdds())
  }
}
