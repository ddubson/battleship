package com.ddubson.battleship.game

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GameTest {
    @Test
    fun `a game should have a finished status`() {
        val player1arrangement = PlayerArrangement()
        val player2arrangement = PlayerArrangement()
        val game = Game(player1arrangement, player2arrangement)
        assertEquals(false, game.finished())
    }
}