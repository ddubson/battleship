package com.ddubson.battleship.game

import com.ddubson.battleship.game.ship.Carrier
import com.ddubson.battleship.game.ship.ShipPlacer

class BattleshipGameEngine(private val uiAdapter: BattleshipGameUiAdapter) {
    fun engage() {
        uiAdapter.printBanner()
        val player1 = uiAdapter.createPlayerOne()
        uiAdapter.announcePlayer(player1)
        val player2 = uiAdapter.createPlayerTwo()
        uiAdapter.announcePlayer(player2)

        val carrier = Carrier()
        uiAdapter.placeShipBanner(carrier.type())
        val carrierInitialCell = uiAdapter.askForCell()
        val carrierDirection = uiAdapter.askForDirection()

        val oceanGrid = OceanGrid()
        val targetGrid = TargetGrid()
        oceanGrid.placeCarrier(ShipPlacer()
                .ship(carrier)
                .initialCell(carrierInitialCell)
                .direction(carrierDirection).place())

        val player1Arrangement = PlayerArrangementBuilder().player(player1)
                .oceanGrid(oceanGrid).targetGrid(targetGrid).build()
    }
}