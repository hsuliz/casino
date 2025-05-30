package dev.hsuliz.casino.game.client

import dev.hsuliz.casino.protos.OddsGrpcKt
import dev.hsuliz.casino.protos.choseMultiplierRequest
import org.springframework.stereotype.Component

@Component
class GrpcClient(private val simpleGrpcStub: OddsGrpcKt.OddsCoroutineStub) : Odds {

  override suspend fun getMultiplier(
      username: String,
      bet: Double,
      multipliers: Set<Double>,
  ): Double {
    val request = choseMultiplierRequest {
      this.username = username
      this.bet = bet
      this.multipliers.addAll(multipliers)
    }
    return simpleGrpcStub.choseMultiplier(request).multiplier
  }
}
