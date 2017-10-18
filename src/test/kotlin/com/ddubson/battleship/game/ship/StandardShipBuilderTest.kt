package com.ddubson.battleship.game.ship

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.jupiter.api.Assertions.assertTrue

class StandardShipBuilderTest: Spek({
    given("a standard ship builder") {
        val shipBuilder = StandardShipBuilder()

        it("should create a new carrier") {
            assertTrue(shipBuilder.newCarrier() is Carrier)
        }

        it("should create a new cruiser") {
            assertTrue(shipBuilder.newCruiser() is Cruiser)
        }

        it("should create a new destroyer") {
            assertTrue(shipBuilder.newDestroyer() is Destroyer)
        }

        it("should create a new submarine") {
            assertTrue(shipBuilder.newSubmarine() is Submarine)
        }

        it("should create a new battleship") {
            assertTrue(shipBuilder.newBattleship() is Battleship)
        }
    }
})