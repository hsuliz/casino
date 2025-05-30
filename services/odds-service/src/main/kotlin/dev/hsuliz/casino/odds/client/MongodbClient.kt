package dev.hsuliz.casino.odds.client

import dev.hsuliz.casino.odds.type.Spin
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface MongodbClient : CoroutineCrudRepository<Spin, String>
