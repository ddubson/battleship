package com.ddubson.battleship.game.standard

import com.ddubson.battleship.game.core.Game
import com.ddubson.battleship.game.core.OceanGrid
import com.ddubson.battleship.game.core.Player
import com.ddubson.battleship.game.core.ShipPlacer
import com.ddubson.battleship.game.core.TargetGrid
import com.ddubson.battleship.game.core.adapters.GameComponentAdapter
import com.ddubson.battleship.game.core.adapters.UserInterfaceAdapter
import com.ddubson.battleship.game.core.builders.ShipBuilder
import com.ddubson.battleship.game.core.ship.Battleship
import com.ddubson.battleship.game.core.ship.Carrier
import com.ddubson.battleship.game.core.ship.Cruiser
import com.ddubson.battleship.game.core.ship.Destroyer
import com.ddubson.battleship.game.core.ship.Submarine
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

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
            on { createPlayer("player1", oceanGrid1) } doReturn player1
            on { createPlayer("player2", oceanGrid2) } doReturn player2
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

        val CLIAdapter: UserInterfaceAdapter = mock {
            on { askForPlayerName() } doReturn "player1" doReturn "player2"
        }

        val shipBuilder: ShipBuilder = mock {
            on { newCarrier() } doReturn carrier1 doReturn carrier2
            on { newBattleship() } doReturn battleship1 doReturn battleship2
            on { newCruiser() } doReturn cruiser1 doReturn cruiser2
            on { newSubmarine() } doReturn submarine1 doReturn submarine2
            on { newDestroyer() } doReturn destroyer1 doReturn destroyer2
        }
        val shipPlacer: ShipPlacer = mock {}

        val engine = StandardBattleshipGameEngine(CLIAdapter, gameComponentAdapter, shipBuilder, shipPlacer)

        on("engage") {
            engine.engage()

            it("should print Battleship game banner") {
                verify(CLIAdapter).printBanner()
            }

            it("should create an ocean grid for both players") {
                verify(gameComponentAdapter, times(2)).createOceanGrid()
            }

            it("should place each ship and display position") {
                verify(shipPlacer).place(oceanGrid1, carrier1)
                verify(shipPlacer).place(oceanGrid1, cruiser1)
                verify(shipPlacer).place(oceanGrid1, destroyer1)
                verify(shipPlacer).place(oceanGrid1, submarine1)
                verify(shipPlacer).place(oceanGrid1, battleship1)

                verify(shipPlacer).place(oceanGrid2, carrier2)
                verify(shipPlacer).place(oceanGrid2, cruiser2)
                verify(shipPlacer).place(oceanGrid2, destroyer2)
                verify(shipPlacer).place(oceanGrid2, submarine2)
                verify(shipPlacer).place(oceanGrid2, battleship2)

                verify(CLIAdapter, times(5)).displayOceanGrid(oceanGrid1)
                verify(CLIAdapter, times(5)).displayOceanGrid(oceanGrid2)
            }

            it("should create and announce players") {
                verify(CLIAdapter).announcePlayer(player1)
                verify(CLIAdapter).announcePlayer(player2)
            }

            it("should create two players") {
                verify(gameComponentAdapter).createPlayer("player1", oceanGrid1)
                verify(gameComponentAdapter).createPlayer("player2", oceanGrid2)
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
    }
})