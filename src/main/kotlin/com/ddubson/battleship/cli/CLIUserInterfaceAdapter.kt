package com.ddubson.battleship.cli

import com.ddubson.battleship.game.core.Direction
import com.ddubson.battleship.game.core.Direction.HORIZONTAL
import com.ddubson.battleship.game.core.Direction.VERTICAL
import com.ddubson.battleship.game.core.InvalidInputException
import com.ddubson.battleship.game.core.OceanGrid
import com.ddubson.battleship.game.core.Player
import com.ddubson.battleship.game.core.TargetGrid
import com.ddubson.battleship.game.core.adapters.UserInterfaceAdapter
import com.ddubson.battleship.game.core.cell.Cell
import com.ddubson.battleship.game.core.ship.Ship

class CLIUserInterfaceAdapter(private val systemCliAdapter: SystemCLIAdapter,
                              private val clearScreen: ClearScreen) : UserInterfaceAdapter {
    override fun displayTargetGrid(targetGrid: TargetGrid) {
        systemCliAdapter.println(targetGrid.as2DString())
    }

    override fun announceWinner(player: Player) {
        systemCliAdapter.println("#### Player ${player.playerName()} wins! ####")
    }

    override fun displayOceanGrid(oceanGrid: OceanGrid) {
        clearScreen.clear()
        systemCliAdapter.println(oceanGrid.as2DString())
    }

    override fun displayWarning(message: String) {
        systemCliAdapter.println(message)
    }

    override fun askForAttackCell(): Cell = readCellFromCLI("Enter attack cell coordinates: ")

    override fun askForPlayerName(): String {
        systemCliAdapter.print("Enter player name: ")
        return systemCliAdapter.readLine()
    }

    override fun askForDirection(ship: Ship): Direction {
        systemCliAdapter.print("Enter direction for ${ship.type()} [h|v]: ")
        val direction = systemCliAdapter.readLine()
        return if (direction == "h") {
            HORIZONTAL
        } else
            VERTICAL
    }

    override fun askForCell(ship: Ship): Cell =
            readCellFromCLI("Enter initial coordinates for ${ship.type()} (e.g. '1 2' for [1,2]): ")

    override fun placeShipBanner(shipType: String) {
        systemCliAdapter.println("Enter $shipType coordinates...")
    }

    override fun announcePlayer(player: Player) {
        clearScreen.clear()
        systemCliAdapter.println("Player ${player.playerName()} has entered the battlespace!")
    }

    override fun printBanner() {
        clearScreen.clear()
        systemCliAdapter.println("--- Welcome to Battleship! ---")
    }

    private fun readCellFromCLI(msg: String): Cell {
        systemCliAdapter.print(msg)

        val rawInput = systemCliAdapter.readLine()
        if (rawInput.isBlank() ||
                !rawInput.matches(Regex("^[0-9] [0-9]$"))) {
            throw InvalidInputException()
        }

        val coordinates = rawInput.split(" ").map { it.toInt() }
        return Cell(coordinates[0], coordinates[1])
    }
}
