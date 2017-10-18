package com.ddubson.battleship.game

import com.nhaarman.mockito_kotlin.mock
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.jupiter.api.Assertions.assertTrue

class StandardPlayerArrangementBuilderTest: Spek({
    given("a standard player arrangement builder") {
        val player = Player("Player 1")
        val oceanGrid: OceanGrid = mock {}
        val targetGrid: TargetGrid = mock {}
        val arrangementBuilder = StandardPlayerArrangementBuilder()

        val actualArrangement = arrangementBuilder.newPlayerArrangement(player, oceanGrid, targetGrid)
        it("should return standard player arrangement") {
            assertTrue(actualArrangement is StandardPlayerArrangement)
        }
    }
})