package dev.hsuliz.casino.odds.handlers

import dev.hsuliz.casino.odds.domain.Odds
import dev.hsuliz.casino.protos.HelloReply
import dev.hsuliz.casino.protos.HelloRequest
import dev.hsuliz.casino.protos.SimpleGrpcKt
import dev.hsuliz.casino.protos.helloReply
import org.springframework.grpc.server.service.GrpcService

@GrpcService()
class GrpcHandler(private val oddsDomain: Odds) : SimpleGrpcKt.SimpleCoroutineImplBase() {
  override suspend fun sayHello(request: HelloRequest): HelloReply {
    println(request)
    return helloReply { message = "Hello" }
  }
}
