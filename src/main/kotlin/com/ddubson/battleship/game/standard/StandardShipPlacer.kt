package com.ddubson.battleship.game.standard

import com.ddubson.battleship.game.core.Direction
import com.ddubson.battleship.game.core.InvalidInputException
import com.ddubson.battleship.game.core.OceanGrid
import com.ddubson.battleship.game.core.ShipBeyondBoundsException
import com.ddubson.battleship.game.core.ShipOverlapsException
import com.ddubson.battleship.game.core.ShipPlacer
import com.ddubson.battleship.game.core.adapters.BattleshipGameCLIAdapter
import com.ddubson.battleship.game.core.cell.Cell
import com.ddubson.battleship.game.core.ship.Ship

class StandardShipPlacer(private val cliAdapter: BattleshipGameCLIAdapter) : ShipPlacer {
    override fun place(oceanGrid: OceanGrid, ship: Ship) {
        cliAdapter.placeShipBanner(ship.type())
        val initialCell = askForShipStartCoordinates(ship)
        val direction = cliAdapter.askForDirection(ship)

        placeShip(oceanGrid, ship, initialCell, direction)
    }

    private fun placeShip(oceanGrid: OceanGrid, ship: Ship, initialCell: Cell, direction: Direction) {
        try {
            oceanGrid.place(ship, initialCell, direction)
            return
        } catch (ex: ShipOverlapsException) {
            cliAdapter.displayWarning(
                    "${ship.type()} overlaps another! please choose different coordinates.")
            place(oceanGrid, ship)
        } catch (ex: ShipBeyondBoundsException) {
            cliAdapter.displayWarning("Initial cell or direction puts the ship beyond battlespace boundaries. Please try again!")
            place(oceanGrid, ship)
        }
    }

    private fun askForShipStartCoordinates(ship: Ship): Cell {
        while (true) {
            try {
                return cliAdapter.askForCell(ship)
            } catch (e: InvalidInputException) {
                cliAdapter.displayWarning("Please enter proper coordinates. Try again!")
            }
        }
    }
}