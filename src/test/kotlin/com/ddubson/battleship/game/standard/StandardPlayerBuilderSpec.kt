package com.ddubson.battleship.game.standard

import com.ddubson.battleship.game.core.builders.PlayerBuilder
import com.ddubson.battleship.game.standard.builders.StandardPlayerBuilder
import com.nhaarman.mockito_kotlin.mock
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.jupiter.api.Assertions.assertTrue

internal class StandardPlayerBuilderSpec : Spek({
    given("a standard player builder") {
        on("new player") {
            val playerBuilder: PlayerBuilder = StandardPlayerBuilder()
            val actualPlayer = playerBuilder.newPlayer("Player1", mock {}, mock {})

            it("should return a new standard player") {
                assertTrue(actualPlayer is StandardPlayer)
            }
        }
    }
})