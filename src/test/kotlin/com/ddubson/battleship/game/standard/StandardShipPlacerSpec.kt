package com.ddubson.battleship.game.standard

import com.ddubson.battleship.game.core.Direction
import com.ddubson.battleship.game.core.OceanGrid
import com.ddubson.battleship.game.core.ShipOverlapsException
import com.ddubson.battleship.game.core.adapters.BattleshipGameCLIAdapter
import com.ddubson.battleship.game.core.cell.Cell
import com.ddubson.battleship.game.core.ship.Carrier
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.doThrow
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

internal class StandardShipPlacerSpec: Spek({
    given("a standard ship placer") {
        on("placing a ship") {
            val carrier = Carrier()
            val cell = Cell(0,0)
            val direction = Direction.VERTICAL
            val cliAdapter: BattleshipGameCLIAdapter = mock {
                on { askForCell(carrier) } doReturn cell
                on { askForDirection(carrier) } doReturn direction
            }
            val shipPlacer = StandardShipPlacer(cliAdapter)

            val oceanGrid: OceanGrid = mock {}
            shipPlacer.place(oceanGrid, carrier)

            it("should place ship successfully") {
                verify(cliAdapter).placeShipBanner(carrier.type())
                verify(cliAdapter).askForCell(carrier)
                verify(cliAdapter).askForDirection(carrier)
                verify(oceanGrid).place(carrier, cell, direction)
            }
        }

        on("placing a ship over another") {
            val ship = Carrier()
            val badCell = Cell(0, 0)
            val goodCell = Cell(0, 1)
            val direction = Direction.VERTICAL

            val oceanGrid: OceanGrid = mock {
                on { place(ship, badCell, direction) } doThrow ShipOverlapsException()
            }
            val CLIAdapter: BattleshipGameCLIAdapter = mock {
                on { askForCell(ship) } doReturn badCell doReturn goodCell
                on { askForDirection(ship) } doReturn direction
            }
            val shipPlacer = StandardShipPlacer(CLIAdapter)

            shipPlacer.place(oceanGrid, ship)

            it("should prompt the user to choose another space") {
                verify(oceanGrid).place(ship, badCell, direction)
                verify(CLIAdapter).displayWarning("Carrier overlaps another! please choose different coordinates.")
                verify(oceanGrid).place(ship, goodCell, direction)
            }
        }
    }
})
