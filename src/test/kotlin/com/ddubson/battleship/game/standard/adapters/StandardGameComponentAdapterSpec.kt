package com.ddubson.battleship.game.standard.adapters

import com.ddubson.battleship.game.core.*
import com.ddubson.battleship.game.core.adapters.BattleshipGameCLIAdapter
import com.ddubson.battleship.game.core.builders.GameBuilder
import com.ddubson.battleship.game.core.builders.GridBuilder
import com.ddubson.battleship.game.core.builders.PlayerBuilder
import com.ddubson.battleship.game.core.builders.ShipBuilder
import com.ddubson.battleship.game.core.cell.Cell
import com.ddubson.battleship.game.core.ship.*
import com.ddubson.battleship.game.standard.StandardPlayer
import com.nhaarman.mockito_kotlin.*
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
            val oceanGrid: OceanGrid = mock {}

            val CLIAdapter: BattleshipGameCLIAdapter = mock {
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
                    CLIAdapter, shipBuilder, gridBuilder, mock {}, mock {})

            val actualOceanGrid = gameComponentAdapter.createOceanGrid()

            it("should create an empty Ocean Grid") {
                verify(gridBuilder).newOceanGrid()
            }

            it("should display ocean grid as the user is placing the ships") {
                verify(CLIAdapter, times(5)).displayOceanGrid(oceanGrid)
            }

            it("should place Carrier") {
                verify(CLIAdapter).placeShipBanner(carrier.type())
                verify(shipBuilder).newCarrier()
                verify(CLIAdapter).askForCell(carrier)
                verify(CLIAdapter).askForDirection(carrier)
            }

            it("should place Cruiser") {
                verify(CLIAdapter).placeShipBanner(cruiser.type())
                verify(shipBuilder).newCruiser()
                verify(CLIAdapter).askForCell(cruiser)
                verify(CLIAdapter).askForDirection(cruiser)
            }

            it("should place Destroyer") {
                verify(CLIAdapter).placeShipBanner(destroyer.type())
                verify(shipBuilder).newDestroyer()
                verify(CLIAdapter).askForCell(destroyer)
                verify(CLIAdapter).askForDirection(destroyer)
            }

            it("should place Submarine") {
                verify(CLIAdapter).placeShipBanner(submarine.type())
                verify(shipBuilder).newSubmarine()
                verify(CLIAdapter).askForCell(submarine)
                verify(CLIAdapter).askForDirection(submarine)
            }

            it("should place Battleship") {
                verify(CLIAdapter).placeShipBanner(battleship.type())
                verify(shipBuilder).newBattleship()
                verify(CLIAdapter).askForCell(battleship)
                verify(CLIAdapter).askForDirection(battleship)
            }

            it("should create an ocean grid") {
                assertEquals(oceanGrid, actualOceanGrid)
            }
        }

        on("creating a game") {
            val targetGrid: TargetGrid = mock {}
            val player1 = StandardPlayer("Player1", mock {}, targetGrid)
            val player2 = StandardPlayer("Player2", mock {}, targetGrid)
            val game: Game = mock {}
            val CLIAdapter: BattleshipGameCLIAdapter = mock {}
            val gameBuilder: GameBuilder = mock {
                on { newGame(player1, player2, CLIAdapter) } doReturn game
            }
            val gridBuilder: GridBuilder = mock {
                on { newTargetGrid() } doReturn targetGrid
            }

            val gameComponentAdapter = StandardGameComponentAdapter(
                    CLIAdapter, mock {}, gridBuilder, gameBuilder, mock {})

            val actualGame = gameComponentAdapter.createGame(player1, player2)

            it("should create a new game") {
                assertEquals(game, actualGame)
            }
        }

        on("creating player one") {
            val playerName = "Player 1"
            val player: Player = mock {}
            val targetGrid: TargetGrid = mock {}
            val oceanGrid: OceanGrid = mock {}
            val playerBuilder: PlayerBuilder = mock {
                on { newPlayer(playerName, oceanGrid, targetGrid) } doReturn player
            }
            val gridBuilder: GridBuilder = mock {
                on { newTargetGrid() } doReturn targetGrid
                on { newOceanGrid() } doReturn oceanGrid
            }
            val shipBuilder: ShipBuilder = mock {
                on { newCarrier() } doReturn Carrier()
                on { newBattleship() } doReturn Battleship()
                on { newCruiser() } doReturn Cruiser()
                on { newSubmarine() } doReturn Submarine()
                on { newDestroyer() } doReturn Destroyer()
            }

            val gameComponentAdapter = StandardGameComponentAdapter(
                    mock {}, shipBuilder, gridBuilder, mock {}, playerBuilder)

            val actualPlayer = gameComponentAdapter.createPlayerOne(playerName, oceanGrid)

            it("should call player builder") {
                verify(playerBuilder).newPlayer(playerName, oceanGrid, targetGrid)
            }

            it("should return player") {
                assertEquals(player, actualPlayer)
            }
        }

        on("creating player two") {
            val playerName = "Player 2"
            val player: Player = mock {}
            val targetGrid: TargetGrid = mock {}
            val oceanGrid: OceanGrid = mock {}
            val playerBuilder: PlayerBuilder = mock {
                on { newPlayer(playerName, oceanGrid, targetGrid) } doReturn player
            }
            val shipBuilder: ShipBuilder = mock {
                on { newCarrier() } doReturn Carrier()
                on { newBattleship() } doReturn Battleship()
                on { newCruiser() } doReturn Cruiser()
                on { newSubmarine() } doReturn Submarine()
                on { newDestroyer() } doReturn Destroyer()
            }
            val gridBuilder: GridBuilder = mock {
                on { newTargetGrid() } doReturn targetGrid
                on { newOceanGrid() } doReturn oceanGrid
            }

            val gameComponentAdapter = StandardGameComponentAdapter(
                    mock{}, shipBuilder, gridBuilder, mock {}, playerBuilder)

            val actualPlayer = gameComponentAdapter.createPlayerTwo(playerName, oceanGrid)

            it("should call player builder") {
                verify(playerBuilder).newPlayer(playerName, oceanGrid, targetGrid)
            }

            it("should return player") {
                assertEquals(player, actualPlayer)
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
            val shipBuilder: ShipBuilder = mock {
                on { newCarrier() } doReturn ship
                on { newCruiser() } doReturn Cruiser()
                on { newDestroyer() } doReturn Destroyer()
                on { newSubmarine() } doReturn Submarine()
                on { newBattleship() } doReturn Battleship()
            }
            val gridBuilder: GridBuilder = mock {
                on { newOceanGrid() } doReturn oceanGrid
            }

            val gameComponentAdapter = StandardGameComponentAdapter(CLIAdapter,
                    shipBuilder, gridBuilder, mock {}, mock {})

            gameComponentAdapter.createOceanGrid()

            it("should prompt the user to choose another space") {
                verify(oceanGrid).place(ship, badCell, direction)
                verify(CLIAdapter).displayWarning("Carrier overlaps another! please choose different coordinates.")
                verify(oceanGrid).place(ship, goodCell, direction)
            }
        }

        on("failing to enter cell coordinates for a ship") {
            val oceanGrid: OceanGrid = mock {}
            val gridBuilder: GridBuilder = mock {
                on { newOceanGrid() } doReturn oceanGrid
            }
            val shipBuilder: ShipBuilder = mock {
                on { newCarrier() } doReturn Carrier()
                on { newBattleship() } doReturn Battleship()
                on { newCruiser() } doReturn Cruiser()
                on { newSubmarine() } doReturn Submarine()
                on { newDestroyer() } doReturn Destroyer()
            }

            val componentAdapter = StandardGameComponentAdapter(mock {}, shipBuilder, gridBuilder, mock {}, mock {})

            componentAdapter.createOceanGrid()

            it("should ask to enter cell coordinates again") {
                verify(cliAdapter).displayWarning("Incorrect coordinates! Try again.")
            }
        }
    }
})