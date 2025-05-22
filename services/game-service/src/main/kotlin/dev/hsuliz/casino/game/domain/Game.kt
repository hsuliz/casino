package dev.hsuliz.casino.game.domain

import dev.hsuliz.casino.game.clients.GrpcClient
import org.springframework.stereotype.Component

@Component
class Game(private val client: GrpcClient) {

  suspend fun roll(): Boolean {
    return client.getOdds() > 60
  }
}
