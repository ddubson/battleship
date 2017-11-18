package com.ddubson.battleship.game

import com.nhaarman.mockito_kotlin.mock
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert.assertTrue

class StandardGameBuilderTest : Spek({
    given("a standard game builder") {
        val gameBuilder = StandardGameBuilder()

        it("should return a standard game") {
            assertTrue(gameBuilder.newGame(mock {}, mock {}, mock {}) is StandardGame)
        }
    }
})