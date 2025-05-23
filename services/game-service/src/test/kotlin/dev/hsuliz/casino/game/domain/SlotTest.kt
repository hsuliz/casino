package dev.hsuliz.casino.game.domain

import io.kotest.core.spec.style.ShouldSpec

class SlotTest :
    ShouldSpec({
      should("return grid") {
        for (i in 0..100) {
          val result = generateGrid(5.0)
          println(result.get(0))
        }
      }
    })
