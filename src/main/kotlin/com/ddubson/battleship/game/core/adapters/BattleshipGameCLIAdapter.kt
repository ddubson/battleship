package com.ddubson.battleship.game.core.adapters

import com.ddubson.battleship.game.core.Direction
import com.ddubson.battleship.game.core.OceanGrid
import com.ddubson.battleship.game.core.Player
import com.ddubson.battleship.game.core.TargetGrid
import com.ddubson.battleship.game.core.cell.Cell
import com.ddubson.battleship.game.core.ship.Ship

interface BattleshipGameCLIAdapter {
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