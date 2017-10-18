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
    val player1 = Player("player1")
    val player2 = Player("player2")

    given("battleship game engine") {
        val oceanGrid1: OceanGrid= mock {}
        val targetGrid1: TargetGrid = mock {}
        val p1Arrangement: PlayerArrangement = mock {}
        val oceanGrid2: OceanGrid= mock {}
        val targetGrid2: TargetGrid = mock {}
        val p2Arrangement: PlayerArrangement = mock {}
        val game1: Game = mock {}

        val gameComponentAdapter: GameComponentAdapter = mock {
            on { createOceanGrid(player1) } doReturn oceanGrid1
            on { createTargetGrid(player1) } doReturn targetGrid1
            on { createPlayerArrangement(player1, oceanGrid1, targetGrid1)} doReturn p1Arrangement
            on { createOceanGrid(player2) } doReturn oceanGrid2
            on { createTargetGrid(player2) } doReturn targetGrid2
            on { createPlayerArrangement(player2, oceanGrid2, targetGrid2)} doReturn p2Arrangement
            on { createPlayerOne() } doReturn player1
            on { createPlayerTwo() } doReturn player2
            on { createGame(p1Arrangement, p2Arrangement) } doReturn game1
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

            it("should create a target grid for player 1") {
                verify(gameComponentAdapter).createTargetGrid(player1)
            }

            it("should create a player 1 arrangement") {
                verify(gameComponentAdapter).createPlayerArrangement(player1, oceanGrid1, targetGrid1)
            }

            it("should ask player 2 to create ocean grid") {
                verify(gameComponentAdapter).createOceanGrid(player2)
            }

            it("should create a target grid for player 2") {
                verify(gameComponentAdapter).createTargetGrid(player2)
            }

            it("should create a player 2 arrangement") {
                verify(gameComponentAdapter).createPlayerArrangement(player2, oceanGrid2, targetGrid2)
            }

            it("should create a game") {
                verify(gameComponentAdapter).createGame(p1Arrangement, p2Arrangement)
            }
        }
    }
})