package dev.hsuliz.casino.game.client

import dev.hsuliz.casino.game.type.Roll
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

interface MongodbClient : CoroutineCrudRepository<Roll, UUID>
