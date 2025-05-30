package dev.hsuliz.casino.odds.handlers

import dev.hsuliz.casino.odds.domain.Odds
import dev.hsuliz.casino.protos.ChoseMultiplierRequest
import dev.hsuliz.casino.protos.ChoseMultiplierResponse
import dev.hsuliz.casino.protos.OddsGrpcKt
import dev.hsuliz.casino.protos.choseMultiplierResponse
import org.springframework.grpc.server.service.GrpcService

@GrpcService
class GrpcHandler(private val oddsDomain: Odds) : OddsGrpcKt.OddsCoroutineImplBase() {

  override suspend fun choseMultiplier(request: ChoseMultiplierRequest): ChoseMultiplierResponse {
    println("Got request: $request")
    return choseMultiplierResponse {
      multiplier = oddsDomain.choseMultiplier(request.multipliersList)
    }
  }
}
