package com.ddubson.battleship.game

class BattleshipGameEngine(private val uiAdapter: BattleshipGameUiAdapter) {
    fun engage() {
        uiAdapter.printBanner()
        val player1 = uiAdapter.createPlayerOne()
        uiAdapter.announcePlayer(player1)
        val player2 = uiAdapter.createPlayerTwo()
        uiAdapter.announcePlayer(player2)

        /*oceanGrid.placeCarrier(ShipPlacer()
                .ship(carrier)
                .initialCell(Cell(0,0))
                .direction(HORIZONTAL).place())*/

        // val player1Arrangement = PlayerArrangementBuilder.player(player1).oceanGrid().targetGrid(targetGrid).build()
    }
}