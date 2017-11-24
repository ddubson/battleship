package com.ddubson.battleship.game.standard

import com.ddubson.battleship.game.standard.builders.StandardGridBuilder
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.jupiter.api.Assertions.assertTrue

class StandardGridBuilderTest: Spek({
    given("a standard grid builder") {
        val standardGridBuilder = StandardGridBuilder()

        it("should return a standard ocean grid") {
            assertTrue(standardGridBuilder.newOceanGrid() is StandardOceanGrid)
        }

        it("should return a standard target grid") {
            assertTrue(standardGridBuilder.newTargetGrid() is StandardTargetGrid)
        }
    }
})