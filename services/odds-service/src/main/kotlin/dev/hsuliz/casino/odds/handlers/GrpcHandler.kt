package dev.hsuliz.casino.odds.handlers

import dev.hsuliz.casino.odds.domain.Odds
import dev.hsuliz.casino.protos.OddsGrpcKt
import dev.hsuliz.casino.protos.OddsResponse
import dev.hsuliz.casino.protos.OddsRequest
import dev.hsuliz.casino.protos.oddsResponse
import org.springframework.grpc.server.service.GrpcService

@GrpcService
class GrpcHandler(private val oddsDomain: Odds) : OddsGrpcKt.OddsCoroutineImplBase() {
  override suspend fun generateOdds(request: OddsRequest): OddsResponse {
    println("Got request: $request")
    return oddsResponse { odds = oddsDomain.generateOdds() }
  }
}
