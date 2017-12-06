package com.ddubson.battleship.game.standard

import com.ddubson.battleship.game.core.*
import com.ddubson.battleship.game.core.adapters.BattleshipGameCLIAdapter
import com.ddubson.battleship.game.core.adapters.GameComponentAdapter
import com.ddubson.battleship.game.core.builders.GridBuilder
import com.ddubson.battleship.game.core.builders.ShipBuilder
import com.ddubson.battleship.game.core.cell.Cell
import com.ddubson.battleship.game.core.ship.*
import com.ddubson.battleship.game.standard.adapters.StandardGameComponentAdapter
import com.nhaarman.mockito_kotlin.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert.fail

class BattleshipGameEngineTest : Spek({
    given("battleship game engine") {
        val targetGrid1: TargetGrid = mock {}
        val targetGrid2: TargetGrid = mock {}
        val oceanGrid1: OceanGrid = mock {}
        val oceanGrid2: OceanGrid = mock {}

        val player1: Player = mock {
            on { targetGrid() } doReturn targetGrid1
            on { playerName() } doReturn "player1"
            on { hasShipsLeft() } doReturn false
        }
        val player2: Player = mock {
            on { targetGrid() } doReturn targetGrid2
            on { playerName() } doReturn "player2"
            on { hasShipsLeft() } doReturn true
        }
        val game1: Game = mock {
            on { nextPlayer() } doReturn player1 doReturn player2
            on { currentOpponent() } doReturn player2 doReturn player1
            on { currentAttacker() } doReturn player2
        }

        val gameComponentAdapter: GameComponentAdapter = mock {
            on { createOceanGrid() } doReturn oceanGrid1 doReturn oceanGrid2
            on { createPlayerOne("player1", oceanGrid1) } doReturn player1
            on { createPlayerTwo("player2", oceanGrid2) } doReturn player2
            on { createGame(player1, player2) } doReturn game1
        }

        val carrier1 = Carrier()
        val battleship1 = Battleship()
        val cruiser1 = Cruiser()
        val submarine1 = Submarine()
        val destroyer1 = Destroyer()

        val carrier2 = Carrier()
        val battleship2 = Battleship()
        val cruiser2 = Cruiser()
        val submarine2 = Submarine()
        val destroyer2 = Destroyer()

        val cell = Cell(0, 0)
        val direction = Direction.HORIZONTAL

        val CLIAdapter: BattleshipGameCLIAdapter = mock {
            on { askForPlayerName() } doReturn "player1" doReturn "player2"
            on { askForCell(carrier1) } doReturn cell
            on { askForCell(carrier2) } doReturn cell
            on { askForDirection(carrier1) } doReturn direction
            on { askForDirection(carrier2) } doReturn direction
            on { askForCell(cruiser1) } doReturn cell
            on { askForCell(cruiser2) } doReturn cell
            on { askForDirection(cruiser1) } doReturn direction
            on { askForDirection(cruiser2) } doReturn direction
            on { askForCell(destroyer1) } doReturn cell
            on { askForCell(destroyer2) } doReturn cell
            on { askForDirection(destroyer1) } doReturn direction
            on { askForDirection(destroyer2) } doReturn direction
            on { askForCell(submarine1) } doReturn cell
            on { askForCell(submarine2) } doReturn cell
            on { askForDirection(submarine1) } doReturn direction
            on { askForDirection(submarine2) } doReturn direction
            on { askForCell(battleship1) } doReturn cell
            on { askForCell(battleship2) } doReturn cell
            on { askForDirection(battleship1) } doReturn direction
            on { askForDirection(battleship2) } doReturn direction
        }

        val shipBuilder: ShipBuilder = mock {
            on { newCarrier() } doReturn carrier1 doReturn carrier2
            on { newBattleship() } doReturn battleship1 doReturn battleship2
            on { newCruiser() } doReturn cruiser1 doReturn cruiser2
            on { newSubmarine() } doReturn submarine1 doReturn submarine2
            on { newDestroyer() } doReturn destroyer1 doReturn destroyer2
        }
        val engine = BattleshipGameEngine(CLIAdapter, gameComponentAdapter, shipBuilder)

        on("engage") {
            engine.engage()

            it("should print Battleship game banner") {
                verify(CLIAdapter).printBanner()
            }

            it("should create an ocean grid for both players") {
                verify(gameComponentAdapter, times(2)).createOceanGrid()
            }


            it("should display ocean grid as the user is placing the ships") {
                verify(CLIAdapter, times(5)).displayOceanGrid(oceanGrid1)
                verify(CLIAdapter, times(5)).displayOceanGrid(oceanGrid2)
            }

            it("should place Carrier") {
                verify(shipBuilder, times(2)).newCarrier()

                verify(CLIAdapter, times(2)).placeShipBanner(carrier1.type())
                verify(CLIAdapter).askForCell(carrier1)
                verify(CLIAdapter).askForDirection(carrier1)

                verify(CLIAdapter).askForCell(carrier2)
                verify(CLIAdapter).askForDirection(carrier2)
            }

            it("should place Cruiser") {
                verify(shipBuilder, times(2)).newCruiser()
                verify(CLIAdapter, times(2)).placeShipBanner(cruiser1.type())
                verify(CLIAdapter).askForCell(cruiser1)
                verify(CLIAdapter).askForDirection(cruiser1)

                verify(CLIAdapter).askForCell(cruiser2)
                verify(CLIAdapter).askForDirection(cruiser2)
            }

            it("should place Destroyer") {
                verify(shipBuilder, times(2)).newDestroyer()
                verify(CLIAdapter, times(2)).placeShipBanner(destroyer1.type())
                verify(CLIAdapter).askForCell(destroyer1)
                verify(CLIAdapter).askForDirection(destroyer1)

                verify(CLIAdapter).askForCell(destroyer2)
                verify(CLIAdapter).askForDirection(destroyer2)
            }

            it("should place Submarine") {
                verify(shipBuilder, times(2)).newSubmarine()
                verify(CLIAdapter, times(2)).placeShipBanner(submarine1.type())
                verify(CLIAdapter).askForCell(submarine1)
                verify(CLIAdapter).askForDirection(submarine1)

                verify(CLIAdapter).askForCell(submarine2)
                verify(CLIAdapter).askForDirection(submarine2)
            }

            it("should place Battleship") {
                verify(shipBuilder, times(2)).newBattleship()
                verify(CLIAdapter, times(2)).placeShipBanner(battleship1.type())
                verify(CLIAdapter).askForCell(battleship1)
                verify(CLIAdapter).askForDirection(battleship1)

                verify(CLIAdapter).askForCell(battleship2)
                verify(CLIAdapter).askForDirection(battleship2)
            }

            it("should create and announce players") {
                verify(CLIAdapter).announcePlayer(player1)
                verify(CLIAdapter).announcePlayer(player2)
            }

            it("should create two players") {
                verify(gameComponentAdapter).createPlayerOne("player1", oceanGrid1)
                verify(gameComponentAdapter).createPlayerTwo("player2", oceanGrid2)
            }

            it("should create a game") {
                verify(gameComponentAdapter).createGame(player1, player2)
            }

            it("should the newly created game as a subscriber to events for both players") {
                verify(player1).subscribe(game1)
                verify(player2).subscribe(game1)
            }

            it("should choose player turns and continue until game is finished") {
                verify(CLIAdapter).displayWarning("${player1.playerName()} goes first.")
                verify(player1).attack(player2)
                verify(CLIAdapter).displayTargetGrid(targetGrid1)
                verify(CLIAdapter).displayWarning("${player2.playerName()}, take your turn.")
                verify(player2).attack(player1)
                verify(CLIAdapter).displayTargetGrid(targetGrid2)
            }

            it("should announce winner of the game once turns have been exhausted") {
                verify(CLIAdapter).announceWinner(player2)
            }
        }

        on("placing a ship over another") {
            fail()
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
            val gridBuilder: GridBuilder = mock {
                on { newOceanGrid() } doReturn oceanGrid
            }

            val gameComponentAdapter = StandardGameComponentAdapter(CLIAdapter,
                    gridBuilder, mock {}, mock {})

            it("should prompt the user to choose another space") {
                verify(oceanGrid).place(ship, badCell, direction)
                verify(CLIAdapter).displayWarning("Carrier overlaps another! please choose different coordinates.")
                verify(oceanGrid).place(ship, goodCell, direction)
            }
        }
    }
})