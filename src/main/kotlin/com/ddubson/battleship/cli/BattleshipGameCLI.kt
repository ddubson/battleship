package com.ddubson.battleship.cli

import com.ddubson.battleship.game.Cell
import com.ddubson.battleship.game.Direction
import com.ddubson.battleship.game.Direction.HORIZONTAL
import com.ddubson.battleship.game.Player
import com.ddubson.battleship.game.adapters.BattleshipGameUiAdapter
import com.ddubson.battleship.game.ship.Ship

class BattleshipGameCLI : BattleshipGameUiAdapter {
    override fun askForPlayerName(): String {
        println("Enter player name: ")
        return readLine()!!
    }

    override fun askForDirection(ship: Ship): Direction {
        return HORIZONTAL
    }

    override fun askForCell(ship: Ship): Cell {
        print("Enter initial coordinates for ${ship.type()} (e.g. '1 2' for [1,2]): ")
        val cellInput = readLine()!!.split(" ")
        return Cell(cellInput[0].toInt(), cellInput[1].toInt())
    }

    override fun placeShipBanner(shipType: String) {
        println("Enter $shipType coordinates...")
    }

    override fun announcePlayer(player: Player) {
        println("Player ${player.playerName} has entered the battlespace!")
    }

    override fun printBanner() {
        println("--- Welcome to Battleship! ---")
    }
}