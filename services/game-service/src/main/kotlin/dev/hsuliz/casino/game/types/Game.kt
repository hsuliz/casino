package dev.hsuliz.casino.game.types

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Game(
    val client: String,
    val result: Boolean,
    val odds: Int,
    @Id var id: String? = null,
)
