package dev.hsuliz.casino.game

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication class OddsApplication

fun main(args: Array<String>) {
  runApplication<OddsApplication>(*args)
}
