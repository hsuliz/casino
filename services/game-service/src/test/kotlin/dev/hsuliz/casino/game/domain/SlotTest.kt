package dev.hsuliz.casino.game.domain

import io.kotest.core.spec.style.ShouldSpec

class SlotTest :
    ShouldSpec({
      should("return grid") {
          repeat(100) {
              val result = generateGrid(0.0)
          }


      }
    })
