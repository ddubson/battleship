package com.ddubson.battleship.game.standard

import com.ddubson.battleship.game.core.OceanGrid
import com.ddubson.battleship.game.core.ShipOverlapsException
import com.ddubson.battleship.game.core.ShipPlacer
import com.ddubson.battleship.game.core.adapters.BattleshipGameCLIAdapter
import com.ddubson.battleship.game.core.ship.Ship

class StandardShipPlacer(private val cliAdapter: BattleshipGameCLIAdapter) : ShipPlacer {
    override fun place(oceanGrid: OceanGrid, ship: Ship) {
        cliAdapter.placeShipBanner(ship.type())
        val initialCell = cliAdapter.askForCell(ship)
        val direction = cliAdapter.askForDirection(ship)

        try {
            oceanGrid.place(ship, initialCell, direction)
        } catch (ex: ShipOverlapsException) {
            warnOfShipOverlap(oceanGrid, ship)
        }
    }

    private fun warnOfShipOverlap(grid: OceanGrid, ship: Ship) {
        cliAdapter.displayWarning("${ship.type()} overlaps another! please choose different coordinates.")
        place(grid, ship)
    }
}