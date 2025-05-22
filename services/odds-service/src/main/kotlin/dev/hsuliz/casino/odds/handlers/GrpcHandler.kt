package dev.hsuliz.casino.odds.handlers

import dev.hsuliz.casino.odds.domain.Odds
import dev.hsuliz.casino.protos.OddsGrpcKt
import dev.hsuliz.casino.protos.OddsReply
import dev.hsuliz.casino.protos.OddsRequest
import dev.hsuliz.casino.protos.oddsReply
import org.springframework.grpc.server.service.GrpcService

@GrpcService
class GrpcHandler(private val odds: Odds) : OddsGrpcKt.OddsCoroutineImplBase() {
  override suspend fun generateOdds(request: OddsRequest): OddsReply {
    println("Got request: $request")
    return oddsReply { odd = odds.generateOdd() }
  }
}
