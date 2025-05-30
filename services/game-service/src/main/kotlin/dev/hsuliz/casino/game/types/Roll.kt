package dev.hsuliz.casino.game.types

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Roll(
    val username: String,
    val multiplier: Double,
    val bet: Double,
    @Id var id: String? = null,
)
