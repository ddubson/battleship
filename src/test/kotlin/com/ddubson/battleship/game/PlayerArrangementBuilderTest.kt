package com.ddubson.battleship.game

import com.ddubson.battleship.game.OceanGrid
import com.ddubson.battleship.game.Player
import com.ddubson.battleship.game.PlayerArrangementBuilder
import com.ddubson.battleship.game.TargetGrid
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerArrangementBuilderTest {
    private lateinit var player: Player

    @BeforeEach
    fun before() {
        player = Player()
    }

    @Test
    fun `should return arrangement with designated player`() {
        val arrangement = PlayerArrangementBuilder().player(player).build()
        assertEquals(player, arrangement.player)
    }

    @Test
    fun `should return arrangement with designated ocean grid`() {
        val oceanGrid = OceanGrid()
        val arrangement = PlayerArrangementBuilder().oceanGrid(oceanGrid).build()
        assertEquals(oceanGrid, arrangement.oceanGrid)
    }

    @Test
    fun `should return arrangement with designated target grid`() {
        val targetGrid = TargetGrid()
        val arrangement = PlayerArrangementBuilder().targetGrid(targetGrid).build()
        assertEquals(targetGrid, arrangement.targetGrid)
    }
}