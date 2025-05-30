package dev.hsuliz.casino.odds.type

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document
data class Spin(
    val username: String,
    val bet: Double,
    val multiplier: Double,
    @CreatedDate val createdAt: Instant? = null,
    @Id var id: String? = null,
)
