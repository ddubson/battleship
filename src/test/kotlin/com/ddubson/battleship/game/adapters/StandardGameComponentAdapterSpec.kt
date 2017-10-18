package com.ddubson.battleship.game.adapters

import com.ddubson.battleship.game.*
import com.ddubson.battleship.game.ship.*
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.jupiter.api.Assertions.assertEquals

class StandardGameComponentAdapterSpec : Spek({
    given("standard game component adapter") {
        on("creating an ocean grid") {
            val carrier = Carrier()
            val cruiser = Cruiser()
            val destroyer = Destroyer()
            val submarine = Submarine()
            val battleship = Battleship()
            val cell = Cell(0, 0)
            val direction = Direction.HORIZONTAL
            val player = Player("Player1")
            val oceanGrid: OceanGrid = mock {}

            val uiAdapter: BattleshipGameUiAdapter = mock {
                on { askForCell(carrier) } doReturn cell
                on { askForDirection(carrier) } doReturn direction
                on { askForCell(cruiser) } doReturn cell
                on { askForDirection(cruiser) } doReturn direction
                on { askForCell(destroyer) } doReturn cell
                on { askForDirection(destroyer) } doReturn direction
                on { askForCell(submarine) } doReturn cell
                on { askForDirection(submarine) } doReturn direction
                on { askForCell(battleship) } doReturn cell
                on { askForDirection(battleship) } doReturn direction
            }
            val shipBuilder: ShipBuilder = mock {
                on { newCarrier() } doReturn carrier
                on { newCruiser() } doReturn cruiser
                on { newDestroyer() } doReturn destroyer
                on { newBattleship() } doReturn battleship
                on { newSubmarine() } doReturn submarine
            }
            val gridBuilder: GridBuilder = mock {
                on { newOceanGrid() } doReturn oceanGrid
            }

            val gameComponentAdapter = StandardGameComponentAdapter(
                    uiAdapter, shipBuilder, gridBuilder, mock {}, mock {})

            val actualOceanGrid = gameComponentAdapter.createOceanGrid(player)

            it("should create an empty Ocean Grid") {
                verify(gridBuilder).newOceanGrid()
            }

            it("should place Carrier") {
                verify(uiAdapter).placeShipBanner(carrier.type())
                verify(shipBuilder).newCarrier()
                verify(uiAdapter).askForCell(carrier)
                verify(uiAdapter).askForDirection(carrier)
            }

            it("should place Cruiser") {
                verify(uiAdapter).placeShipBanner(cruiser.type())
                verify(shipBuilder).newCruiser()
                verify(uiAdapter).askForCell(cruiser)
                verify(uiAdapter).askForDirection(cruiser)
            }

            it("should place Destroyer") {
                verify(uiAdapter).placeShipBanner(destroyer.type())
                verify(shipBuilder).newDestroyer()
                verify(uiAdapter).askForCell(destroyer)
                verify(uiAdapter).askForDirection(destroyer)
            }

            it("should place Submarine") {
                verify(uiAdapter).placeShipBanner(submarine.type())
                verify(shipBuilder).newSubmarine()
                verify(uiAdapter).askForCell(submarine)
                verify(uiAdapter).askForDirection(submarine)
            }

            it("should place Battleship") {
                verify(uiAdapter).placeShipBanner(battleship.type())
                verify(shipBuilder).newBattleship()
                verify(uiAdapter).askForCell(battleship)
                verify(uiAdapter).askForDirection(battleship)
            }

            it("should create an ocean grid") {
                assertEquals(oceanGrid, actualOceanGrid)
            }
        }

        on("creating a target grid") {
            val targetGrid: TargetGrid = mock {}
            val gridBuilder: GridBuilder = mock {
                on { newTargetGrid() } doReturn targetGrid
            }
            val player = Player("Player1")

            val gameComponentAdapter = StandardGameComponentAdapter(
                    mock {}, mock {}, gridBuilder, mock {}, mock {})

            val actualTargetGrid = gameComponentAdapter.createTargetGrid(player)

            it("should create an empty Target Grid") {
                verify(gridBuilder).newTargetGrid()
            }

            it("should return the empty Target Grid") {
                assertEquals(targetGrid, actualTargetGrid)
            }
        }

        on("creating a player arrangement") {
            val player = Player("Player1")
            val oceanGrid: OceanGrid = mock {}
            val targetGrid: TargetGrid = mock {}
            val arrangement: PlayerArrangement = mock {}
            val pArrangementBuilder: PlayerArrangementBuilder = mock {
                on { newPlayerArrangement(player, oceanGrid, targetGrid) } doReturn arrangement
            }
            val gameComponentAdapter = StandardGameComponentAdapter(
                    mock {}, mock {}, mock {}, pArrangementBuilder, mock {})

            val actualArrangement = gameComponentAdapter.createPlayerArrangement(
                    player, oceanGrid, targetGrid)

            it("should create a player arrangement") {
                assertEquals(arrangement, actualArrangement)
            }
        }

        on("creating a game") {
            val arrangement1: PlayerArrangement = mock {}
            val arrangement2: PlayerArrangement = mock {}
            val game: Game = mock {}
            val gameBuilder: GameBuilder = mock {
                on { newGame(arrangement1, arrangement2) } doReturn game
            }

            val gameComponentAdapter = StandardGameComponentAdapter(
                    mock {}, mock {}, mock {}, mock {}, gameBuilder)

            val actualGame = gameComponentAdapter.createGame(arrangement1, arrangement2)

            it("should create a new game") {
                assertEquals(game, actualGame)
            }
        }
    }
})