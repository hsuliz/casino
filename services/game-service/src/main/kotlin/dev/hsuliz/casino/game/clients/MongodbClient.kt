package dev.hsuliz.casino.game.clients

import dev.hsuliz.casino.game.types.Roll
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

interface MongodbClient : CoroutineCrudRepository<Roll, UUID>
