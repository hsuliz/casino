package dev.hsuliz.casino.game.client

import dev.hsuliz.casino.protos.OddsGrpcKt
import dev.hsuliz.casino.protos.choseMultiplierRequest
import org.springframework.stereotype.Component

@Component
class GrpcClient(private val simpleGrpcStub: OddsGrpcKt.OddsCoroutineStub) : Odds {

  override suspend fun getMultiplier(multipliers: Set<Double>): Double {
    val request = choseMultiplierRequest {
      this.username = "Sasha"
      this.multipliers.addAll(multipliers)
    }
    return simpleGrpcStub.choseMultiplier(request).multiplier
  }
}
