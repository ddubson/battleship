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

            it("should create a game") {
                verify(gameComponentAdapter).createGame(player1, player2)
            }

            it("should the newly created game as a subscriber to events for both players") {
                verify(player1).subscribe(game1)
                verify(player2).subscribe(game1)
            }

            it("should choose player turns and continue until game is finished") {
                verify(uiAdapter).displayWarning("${player1.playerName()} goes first.")
                verify(player1).attack(player2)
                verify(uiAdapter).displayTargetGrid(targetGrid1)
                verify(uiAdapter).displayWarning("${player2.playerName()}, take your turn.")
                verify(player2).attack(player1)
                verify(uiAdapter).displayTargetGrid(targetGrid2)
            }

            it("should announce winner of the game once turns have been exhausted") {
                verify(uiAdapter).announceWinner(player2)
            }
        }
    }
})