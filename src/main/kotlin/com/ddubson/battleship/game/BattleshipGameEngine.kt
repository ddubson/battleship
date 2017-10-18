package com.ddubson.battleship.game

import com.ddubson.battleship.game.ship.Carrier

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
    }
}