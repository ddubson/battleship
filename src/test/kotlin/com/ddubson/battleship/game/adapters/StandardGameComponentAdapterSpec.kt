package com.ddubson.battleship.game.adapters

import com.ddubson.battleship.game.ship.ShipBuilder
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class StandardGameComponentAdapter : Spek({
    given("standard player ui adapter") {
        on("creating an ocean grid") {
            val uiAdapter: BattleshipGameUiAdapter = mock {}
            val shipBuilder: ShipBuilder = mock {}
            val gameComponentAdapter = StandardGameComponentAdapter(uiAdapter, shipBuilder)

            it("should ask to place Carrier") {
                verify(uiAdapter).placeShipBanner("Carrier")
            }
        }

        on("creating a target grid") {

        }

        on("creating a player arrangement") {

        }
    }
})