package com.ddubson.battleship.game

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerArrangementBuilderTest {
    private lateinit var player: Player

    @BeforeEach
    fun before() {
        player = Player("A Player")
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