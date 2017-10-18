package com.ddubson.battleship.game.adapters

import com.ddubson.battleship.game.Cell
import com.ddubson.battleship.game.Direction
import com.ddubson.battleship.game.Player
import com.ddubson.battleship.game.ship.Ship

interface BattleshipGameUiAdapter {
    fun printBanner()
    fun announcePlayer(player: Player)
    fun placeShipBanner(shipType: String)
    fun askForCell(ship: Ship): Cell
    fun askForDirection(ship: Ship): Direction
    fun askForPlayerName(): String
}