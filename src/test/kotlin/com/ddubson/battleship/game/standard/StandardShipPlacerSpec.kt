package com.ddubson.battleship.game.standard

import com.ddubson.battleship.game.core.Direction
import com.ddubson.battleship.game.core.InvalidInputException
import com.ddubson.battleship.game.core.OceanGrid
import com.ddubson.battleship.game.core.ShipOverlapsException
import com.ddubson.battleship.game.core.adapters.BattleshipGameCLIAdapter
import com.ddubson.battleship.game.core.cell.Cell
import com.ddubson.battleship.game.core.ship.Carrier
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.doThrow
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
internal class StandardShipPlacerSpec : Spek({
    given("a standard ship placer") {
        val ship = Carrier()
        val goodCell = Cell(0, 1)
        val direction = Direction.VERTICAL
        var cliAdapter: BattleshipGameCLIAdapter
        var oceanGrid: OceanGrid

        on("placing a ship") {
            val cell = Cell(0, 0)
            cliAdapter = mock {
                on { askForCell(ship) } doReturn cell
                on { askForDirection(ship) } doReturn direction
            }
            val shipPlacer = StandardShipPlacer(cliAdapter)

            oceanGrid = mock {}
            shipPlacer.place(oceanGrid, ship)

            it("should place ship successfully") {
                verify(cliAdapter).placeShipBanner(ship.type())
                verify(cliAdapter).askForCell(ship)
                verify(cliAdapter).askForDirection(ship)
                verify(oceanGrid).place(ship, cell, direction)
            }
        }

        on("placing a ship over another") {
            val badCell = Cell(0, 0)

            oceanGrid = mock {
                on { place(ship, badCell, direction) } doThrow ShipOverlapsException()
            }

            cliAdapter = mock {
                on { askForCell(ship) } doReturn badCell doReturn goodCell
                on { askForDirection(ship) } doReturn direction
            }
            val shipPlacer = StandardShipPlacer(cliAdapter)

            shipPlacer.place(oceanGrid, ship)

            it("should prompt the user to choose another space") {
                verify(oceanGrid).place(ship, badCell, direction)
                verify(cliAdapter).displayWarning("Carrier overlaps another! please choose different coordinates.")
                verify(oceanGrid).place(ship, goodCell, direction)
            }
        }

        on("placing a ship with bad cell coordinates") {
            oceanGrid = mock {}
            cliAdapter = mock {
                on { askForCell(ship) } doThrow InvalidInputException() doReturn goodCell
                on { askForDirection(ship) } doReturn direction
            }

            val shipPlacer = StandardShipPlacer(cliAdapter)

            shipPlacer.place(oceanGrid, ship)

            it("should prompt the user for good cell coordinates") {
                verify(cliAdapter, times(2)).askForCell(ship)
                verify(cliAdapter).displayWarning("Please enter proper coordinates. Try again!")
            }
        }
    }
})
