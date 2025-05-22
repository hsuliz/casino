package dev.hsuliz.casino.game.clients

import dev.hsuliz.casino.game.types.Game
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

interface MongodbClient : CoroutineCrudRepository<Game, UUID>
