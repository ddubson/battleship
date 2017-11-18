package com.ddubson.battleship.game

import com.ddubson.battleship.game.adapters.BattleshipGameUiAdapter
import com.ddubson.battleship.game.adapters.GameComponentAdapter
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

class BattleshipGameEngineTest : Spek({
    given("battleship game engine") {
        val oceanGrid1: OceanGrid = mock {}
        val targetGrid1: TargetGrid = mock {}
        val oceanGrid2: OceanGrid = mock {}
        val targetGrid2: TargetGrid = mock {}

        val player1: Player = mock {
            on { playerName() } doReturn "player1"
            on { hasShipsLeft() } doReturn false
        }
        val player2: Player = mock {
            on { playerName() } doReturn "player2"
            on { hasShipsLeft() } doReturn true
        }
        val game1: Game = mock {
            on { nextPlayer() } doReturn player1 doReturn player2
            on { currentOpponent() } doReturn player2 doReturn player1
            on { currentAttacker() } doReturn player2
        }

        val gameComponentAdapter: GameComponentAdapter = mock {
            on { createOceanGrid(player1) } doReturn oceanGrid1
            on { createTargetGrid(player1) } doReturn targetGrid1
            on { createOceanGrid(player2) } doReturn oceanGrid2
            on { createTargetGrid(player2) } doReturn targetGrid2
            on { createPlayerOne() } doReturn player1
            on { createPlayerTwo() } doReturn player2
            on { createGame(player1, player2) } doReturn game1
        }

        val uiAdapter: BattleshipGameUiAdapter = mock {}
        val engine = BattleshipGameEngine(uiAdapter, gameComponentAdapter)

        on("engage") {
            engine.engage()

            it("should print Battleship game banner") {
                verify(uiAdapter).printBanner()
            }

            it("should create and announce players") {
                verify(uiAdapter).announcePlayer(player1)
                verify(uiAdapter).announcePlayer(player2)
            }

            it("should ask player 1 to create ocean grid") {
                verify(gameComponentAdapter).createOceanGrid(player1)
            }

            it("should add a target grid to player 1") {
                verify(gameComponentAdapter).createTargetGrid(player1)
            }

            it("should add an ocean grid and target grid to player 1") {
                verify(gameComponentAdapter).addOceanGridToPlayer(player1, oceanGrid1)
                verify(gameComponentAdapter).addTargetGridToPlayer(player1, targetGrid1)
            }

            it("should ask player 2 to create ocean grid") {
                verify(gameComponentAdapter).createOceanGrid(player2)
            }

            it("should create a target grid for player 2") {
                verify(gameComponentAdapter).createTargetGrid(player2)
            }

            it("should add an ocean grid and target grid to player 2") {
                verify(gameComponentAdapter).addOceanGridToPlayer(player2, oceanGrid2)
                verify(gameComponentAdapter).addTargetGridToPlayer(player2, targetGrid2)
            }

            it("should create a game") {
                verify(gameComponentAdapter).createGame(player1, player2)
            }

            it("should notify that player 1 goes first") {
                verify(uiAdapter).displayWarning("${player1.playerName()} goes first.")
            }

            it("should choose player turns and continue until game is finished") {
                verify(player1).attack(player2)
                verify(uiAdapter).displayWarning("${player2.playerName()}, take your turn.")
                verify(player2).attack(player1)
            }

            it("should announce winner of the game once turns have been exhausted") {
                verify(uiAdapter).announceWinner(player2)
            }
        }
    }
})