package dev.hsuliz.casino.game.clients

import dev.hsuliz.casino.game.types.Odds
import dev.hsuliz.casino.protos.OddsGrpcKt
import dev.hsuliz.casino.protos.oddsRequest
import org.springframework.stereotype.Component

@Component
class GrpcClient(private val simpleGrpcStub: OddsGrpcKt.OddsCoroutineStub) : Odds {

  override suspend fun getOdds(): Int {
    val request = oddsRequest { name = "Sasha" }
    return simpleGrpcStub.generateOdds(request).odds
  }
}
