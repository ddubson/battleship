package com.ddubson.battleship.game

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.jupiter.api.Test

class BattleshipGameEngineTest {
    @Test
    fun `engage should print Battleship banner`() {
        val uiAdapter = mock<BattleshipGameUiAdapter> {}
        val engine = BattleshipGameEngine(uiAdapter)

        engine.engage()

        verify(uiAdapter).printBanner()
    }

    @Test
    fun `engage should create and announce players`() {
        val player1 = Player("player1")
        val player2 = Player("player2")
        val uiAdapter = mock<BattleshipGameUiAdapter> {
            on { createPlayerOne() } doReturn player1
            on { createPlayerTwo() } doReturn player2
        }
        val engine = BattleshipGameEngine(uiAdapter)

        engine.engage()
        verify(uiAdapter).announcePlayer(player1)
        verify(uiAdapter).announcePlayer(player2)
    }

    @Test
    fun `player 1 and player 2 shall load game`() {
        /*

        player 1 and player 2 set up their respective grids
         - ocean grid (8x8)
         - target grid (empty initially) (8x8)

        ships
         - carrier (5)
         - battleship (4)
         - cruiser (3)
         - submarine (3)
         - destroyer (2)
         */

        /*val player1 = Player()
        val player2 = Player()

        val oceanGrid1 = OceanGrid()
        val targetGrid1 = TargetGrid()

        val carrier1 = Carrier()
        val battleship1 = Battleship()
        val cruiser1 = Cruiser()
        val submarine1 = Submarine()
        val destroyer1 = Destroyer()

        val oceanGrid = OceanGrid()

        oceanGrid.placeCarrier(ShipPlacer()
                .ship(carrier)
                .initialCell(Cell(0,0))
                .direction(HORIZONTAL).place())

        val targetGrid = TargetGrid()
        val player1Arrangement = PlayerArrangementBuilder.player(player1).oceanGrid().targetGrid(targetGrid).build()
        val player2Arrangement = PlayerArrangementBuilder.player(player2).oceanGrid().targetGrid().build()

        val game = Game(player1Arrangement, player2Arrangement)

        game.start()

        while(!game.finished()) {
            var turn = game.nextTurn()
            // prompt player for cell
            // player's target grid is updated
            // opponent's oceangrid is updated
        }

        game.winner()

        */


    }
}