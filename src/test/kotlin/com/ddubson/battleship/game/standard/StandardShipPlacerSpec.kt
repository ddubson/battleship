package com.ddubson.battleship.game.standard

import com.ddubson.battleship.game.core.OceanGrid
import com.ddubson.battleship.game.core.adapters.BattleshipGameCLIAdapter
import com.ddubson.battleship.game.core.ship.Carrier
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

internal class StandardShipPlacerSpec: Spek({
    given("a standard ship placer") {
        val cliAdapter: BattleshipGameCLIAdapter = mock {}
        val shipPlacer = StandardShipPlacer(cliAdapter)

        on("placing a ship") {
            val oceanGrid: OceanGrid = mock {}
            val carrier = Carrier()
            shipPlacer.place(oceanGrid, carrier)

            it("should place ship successfully") {
                verify(cliAdapter).placeShipBanner(carrier.type())
                verify(cliAdapter).askForCell(carrier)
                verify(cliAdapter).askForDirection(carrier)
                verify(oceanGrid).place(carrier, )
            }
        }
    }
})
