package com.ddubson.battleship.cli

import com.ddubson.battleship.game.Cell
import com.ddubson.battleship.game.Direction
import com.ddubson.battleship.game.Direction.HORIZONTAL
import com.ddubson.battleship.game.Direction.VERTICAL
import com.ddubson.battleship.game.Player
import com.ddubson.battleship.game.adapters.BattleshipGameUiAdapter
import com.ddubson.battleship.game.ship.Ship

class BattleshipGameCLI(private val cliAdapter: CLIAdapter) : BattleshipGameUiAdapter {
    override fun displayWarning(message: String) {
        cliAdapter.println(message)
    }

    override fun askForAttackCell(): Cell {
        return Cell(0, 0)
    }

    override fun askForPlayerName(): String {
        cliAdapter.print("Enter player name: ")
        return cliAdapter.readLine()
    }

    override fun askForDirection(ship: Ship): Direction {
        cliAdapter.print("Enter direction for ${ship.type()} [h|v]: ")
        val direction = cliAdapter.readLine()
        return if (direction == "h") {
            HORIZONTAL
        } else
            VERTICAL
    }

    override fun askForCell(ship: Ship): Cell {
        cliAdapter.print("Enter initial coordinates for ${ship.type()} (e.g. '1 2' for [1,2]): ")
        val cellInput = cliAdapter.readLine().split(" ")
        return Cell(cellInput[0].toInt(), cellInput[1].toInt())
    }

    override fun placeShipBanner(shipType: String) {
        cliAdapter.println("Enter $shipType coordinates...")
    }

    override fun announcePlayer(player: Player) {
        cliAdapter.println("Player ${player.playerName()} has entered the battlespace!")
    }

    override fun printBanner() {
        cliAdapter.println("--- Welcome to Battleship! ---")
    }
}