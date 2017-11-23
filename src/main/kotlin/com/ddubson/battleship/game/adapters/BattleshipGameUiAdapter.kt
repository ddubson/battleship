package com.ddubson.battleship.game.adapters

import com.ddubson.battleship.game.*
import com.ddubson.battleship.game.ship.Ship

interface BattleshipGameUiAdapter {
    fun printBanner()
    fun announcePlayer(player: Player)
    fun placeShipBanner(shipType: String)
    fun askForCell(ship: Ship): Cell
    fun askForDirection(ship: Ship): Direction
    fun askForPlayerName(): String
    fun askForAttackCell(): Cell
    fun displayWarning(message: String)
    fun displayOceanGrid(oceanGrid: OceanGrid)
    fun displayTargetGrid(targetGrid: TargetGrid)
    fun announceWinner(player: Player)
}