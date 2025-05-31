package dev.hsuliz.casino.game.config

import dev.hsuliz.casino.protos.OddsGrpcKt
import io.grpc.ManagedChannel
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.grpc.client.GrpcChannelFactory

@Configuration
class GrpcConfig {

  @Bean
  fun simpleGrpcStub(channels: GrpcChannelFactory): OddsGrpcKt.OddsCoroutineStub {
    val channel: ManagedChannel = channels.createChannel("odds-service-grpc:50051")
    return OddsGrpcKt.OddsCoroutineStub(channel)
  }
}
