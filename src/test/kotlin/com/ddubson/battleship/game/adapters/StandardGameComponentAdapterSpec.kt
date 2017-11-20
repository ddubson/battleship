package com.ddubson.battleship.game.adapters

import com.ddubson.battleship.game.*
import com.ddubson.battleship.game.ship.*
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
            val player: Player = mock {}
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

            it("should display ocean grid as the user is placing the ships") {
                verify(uiAdapter, times(5)).displayOceanGrid(oceanGrid)
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

            val gameComponentAdapter = StandardGameComponentAdapter(
                    mock {}, mock {}, gridBuilder, mock {}, mock {})

            val actualTargetGrid = gameComponentAdapter.createTargetGrid()

            it("should create an empty Target Grid") {
                verify(gridBuilder).newTargetGrid()
            }

            it("should return the empty Target Grid") {
                assertEquals(targetGrid, actualTargetGrid)
            }
        }

        on("creating a game") {
            val targetGrid: TargetGrid = mock {}
            val player1 = StandardPlayer("Player1", targetGrid)
            val player2 = StandardPlayer("Player2", targetGrid)
            val game: Game = mock {}
            val uiAdapter: BattleshipGameUiAdapter = mock {}
            val gameBuilder: GameBuilder = mock {
                on { newGame(player1, player2, uiAdapter) } doReturn game
            }
            val gridBuilder: GridBuilder = mock {
                on { newTargetGrid() } doReturn targetGrid
            }

            val gameComponentAdapter = StandardGameComponentAdapter(
                    uiAdapter, mock {}, gridBuilder, gameBuilder, mock {})

            val actualGame = gameComponentAdapter.createGame(player1, player2)

            it("should create a new game") {
                assertEquals(game, actualGame)
            }
        }

        on("adding an ocean grid to a player") {

            val gameComponentAdapter = StandardGameComponentAdapter(
                    mock {}, mock {}, mock {}, mock {}, mock {})

            val oceanGrid: OceanGrid = mock {}
            val player = StandardPlayer("Player", mock{})

            it("should add ocean grid to player") {
                assertEquals(null, player.oceanGrid())
                gameComponentAdapter.addOceanGridToPlayer(player, oceanGrid)
                assertEquals(oceanGrid, player.oceanGrid())
            }
        }

        on("creating player one") {
            val playerName = "Player 1"
            val player: Player = mock {}
            val targetGrid: TargetGrid = mock {}
            val playerBuilder: PlayerBuilder = mock {
                on { newPlayer(playerName, targetGrid) } doReturn player
            }
            val gridBuilder: GridBuilder = mock {
                on { newTargetGrid() } doReturn targetGrid
            }
            val uiAdapter: BattleshipGameUiAdapter = mock {
                on { askForPlayerName()} doReturn playerName
            }

            val gameComponentAdapter = StandardGameComponentAdapter(
                    uiAdapter, mock {}, gridBuilder, mock {}, playerBuilder)

            val actualPlayer = gameComponentAdapter.createPlayerOne()

            it("should call player builder") {
                verify(playerBuilder).newPlayer(playerName, targetGrid)
            }

            it("should ask for player name") {
                verify(uiAdapter).askForPlayerName()
            }

            it("should return player") {
                assertEquals(player, actualPlayer)
            }
        }

        on("creating player two") {
            val playerName = "Player 2"
            val player: Player = mock {}
            val targetGrid: TargetGrid = mock {}
            val playerBuilder: PlayerBuilder = mock {
                on { newPlayer(playerName, targetGrid) } doReturn player
            }
            val uiAdapter: BattleshipGameUiAdapter = mock {
                on { askForPlayerName()} doReturn playerName
            }
            val gridBuilder: GridBuilder = mock {
                on { newTargetGrid() } doReturn targetGrid
            }

            val gameComponentAdapter = StandardGameComponentAdapter(
                    uiAdapter, mock {}, gridBuilder, mock {}, playerBuilder)

            val actualPlayer = gameComponentAdapter.createPlayerTwo()

            it("should call player builder") {
                verify(playerBuilder).newPlayer(playerName, targetGrid)
            }

            it("should ask for player name") {
                verify(uiAdapter).askForPlayerName()
            }

            it("should return player") {
                assertEquals(player, actualPlayer)
            }
        }

        on("placing a ship over another") {
            val ship = Carrier()
            val badCell = Cell(0,0)
            val goodCell = Cell(0, 1)
            val direction = Direction.VERTICAL

            val oceanGrid: OceanGrid = mock {
                on { place(ship, badCell, direction) } doThrow ShipOverlapsException()
            }
            val uiAdapter: BattleshipGameUiAdapter = mock {
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

            val player = StandardPlayer("", mock {})
            val gameComponentAdapter = StandardGameComponentAdapter(uiAdapter,
                    shipBuilder, gridBuilder, mock {}, mock {})

            gameComponentAdapter.createOceanGrid(player)

            it("should prompt the user to choose another space") {
                verify(oceanGrid).place(ship, badCell, direction)
                verify(uiAdapter).displayWarning("Carrier overlaps another! please choose different coordinates.")
                verify(oceanGrid).place(ship, goodCell, direction)
            }
        }
    }
})